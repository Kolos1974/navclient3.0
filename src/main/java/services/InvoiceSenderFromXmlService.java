package services;

import config.Config;
import exception.AES128Exception;
import exception.InvoiceRequestGenException;
import exception.TokenExchangeRequestGenException;
import hu.gov.nav.schemas.osa._3_0.api.*;
import javafx.concurrent.Task;
import network.NetworkManager;
import network.response.NetworkCallback;
import program.ThreadPool;
import requestFactories.ManageInvoiceGenerator;
import requestFactories.TokenExchangeGenerator;
import utils.Algos;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;

import static java.nio.file.StandardWatchEventKinds.*;

public class InvoiceSenderFromXmlService extends Task<Void> {

    private static final String TAG = "InvoiceSenderFromXmlService";

    private final WatchService watcher;
    private final Map<WatchKey, Path> keys;
    private boolean shouldStop = false;
    private StateChangeCallback changeCallback;

    public InvoiceSenderFromXmlService(StateChangeCallback changeCallback) throws IOException {
        this.changeCallback = changeCallback;
        this.watcher = FileSystems.getDefault().newWatchService();
        this.keys = new HashMap<>();
        register();
    }

    @SuppressWarnings("unchecked")
    private <T> WatchEvent<T> cast(WatchEvent<?> event) {
        return (WatchEvent<T>) event;
    }

    private void register() throws IOException {
        File dir = new File(Config.navImport);
        if (!dir.exists()) throw new IOException("A mappa nem letezik!");
        if (!dir.isDirectory()) throw new IOException("A konfigban beallitott NAV import utvonal nem mappara mutat!");
        Path pathToDir = dir.getAbsoluteFile().toPath();
        WatchKey key = pathToDir.register(watcher, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
        Path prev = keys.get(key);
        if (prev == null) {
            utils.Logger.logMessage(TAG, "Register folder: " + pathToDir);
        } else if (!pathToDir.equals(prev)) {
            utils.Logger.logMessage(TAG, "update: " + pathToDir);
        }
        keys.put(key, pathToDir);
    }

    public static InvoiceSenderFromXmlService startService(StateChangeCallback changeCallback) throws IOException {
        InvoiceSenderFromXmlService invoiceSenderFromXmlService = new InvoiceSenderFromXmlService(changeCallback);
        ThreadPool.INSTANCE.runTask(invoiceSenderFromXmlService);
        utils.Logger.logMessage(TAG, "Starting service...");
        return invoiceSenderFromXmlService;
    }

    public void stopService() {
        utils.Logger.logMessage(TAG, "Stopping service...");
        shouldStop = true;
        changeCallback = null;
        if (watcher != null) {
            try {
                watcher.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.cancel();
    }

    @Override
    protected Void call() throws Exception {
        while (!shouldStop) {
            // wait for key to be signalled
            WatchKey key;
            try {
                key = watcher.take();
            } catch (InterruptedException x) {
                return null;
            }

            Path dir = keys.get(key);
            if (dir == null) {
                System.err.println("WatchKey not recognized!!");
                continue;
            }

            for (WatchEvent<?> event : key.pollEvents()) {
                WatchEvent.Kind kind = event.kind();
                // Context for directory entry event is the file name of entry
                WatchEvent<Path> ev = cast(event);
                Path name = ev.context();
                Path path = dir.resolve(name);

                // TBD - provide example of how OVERFLOW event is handled
                String fileName = path.getFileName().toString();
                if (kind == OVERFLOW) {
                    continue;
                } else if (kind == ENTRY_CREATE && fileName.endsWith(".xml")) {
//                    if (fileName.contains(ManageInvoiceOperationType.ANNUL.name())) sendInvoice(path, ManageInvoiceOperationType.ANNUL); //TODO ez mar nincs??
                    if (fileName.contains(ManageInvoiceOperationType.MODIFY.name())) sendInvoice(path, ManageInvoiceOperationType.MODIFY);
                    else if (fileName.contains(ManageInvoiceOperationType.STORNO.name())) sendInvoice(path, ManageInvoiceOperationType.STORNO);
                    else sendInvoice(path, ManageInvoiceOperationType.CREATE);
                }


                // print out event
                utils.Logger.logMessage(TAG, String.format("%s: %s", event.kind().name(), path));
            }

            // reset key and remove from set if directory no longer accessible
            boolean valid = key.reset();
            if (!valid) {
                keys.remove(key);

                // all directories are inaccessible
                if (keys.isEmpty()) {
                    break;
                }
            }
        }
        utils.Logger.logMessage(TAG, "Service ended...");
        return null;
    }

    private void sendInvoice(Path path, ManageInvoiceOperationType type) {
        File oldFile = new File(path.toAbsolutePath().toUri());
        try {
            TokenExchangeRequest tokenExchangeRequest = TokenExchangeGenerator.INSTANCE.generateObject();
            NetworkManager.INSTANCE.exchangeToken(tokenExchangeRequest, new NetworkCallback<TokenExchangeResponse>() {
                @Override
                public void onSuccess(TokenExchangeResponse response) {
                    try {
                        ManageInvoiceRequest manageInvoiceRequest = ManageInvoiceGenerator.INSTANCE.generateObjFromFile(
                                path, Algos.decryptAES128(response.getEncodedExchangeToken(), Config.exchangeKey), type);
                        NetworkManager.INSTANCE.manageInvoice(manageInvoiceRequest, new NetworkCallback<ManageInvoiceResponse>() {
                            @Override
                            public void onSuccess(ManageInvoiceResponse response) {
                                changeCallback.onSuccessfulAttemp();
                                File sentFile = new File(path.toAbsolutePath().toString().replace(".xml", ".sent"));
                                if (!sentFile.exists()) {
                                    oldFile.renameTo(sentFile);
                                    TransactionStatusCheckService.INSTANCE.addTransactionId(response.getTransactionId(), path);
                                }
                            }

                            @Override
                            public void onError(GeneralErrorResponse errorResponse) {
                                changeCallback.onFailedAttemp();
                                changeCallback.info(StateChangeCallback.MessageType.ERROR,
                                        errorResponse.getResult().getFuncCode().value() + " " + errorResponse.getResult().getMessage()
                                                + ": " + errorResponse.getTechnicalValidationMessages().get(0).getMessage());
                            }

                            @Override
                            public void onFail(String msg) {
                                changeCallback.onFailedAttemp();
                                changeCallback.info(StateChangeCallback.MessageType.NETWORKERROR, msg);
                            }
                        });
                    } catch (InvoiceRequestGenException e) {
                        File sentFile = new File(path.toAbsolutePath().toString().replace(".xml", ".badxmlformat"));
                        if (!sentFile.exists()) {
                            oldFile.renameTo(sentFile);
                        }
                        e.printStackTrace();
                    } catch (AES128Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onError(GeneralErrorResponse errorResponse) {
                    changeCallback.onFailedAttemp();
                    changeCallback.info(StateChangeCallback.MessageType.ERROR,
                            errorResponse.getResult().getFuncCode().value() + " " + errorResponse.getResult().getMessage()
                                    + ": " + errorResponse.getTechnicalValidationMessages().get(0).getMessage());
                }

                @Override
                public void onFail(String msg) {
                    changeCallback.onFailedAttemp();
                    changeCallback.info(StateChangeCallback.MessageType.NETWORKERROR, msg);
                }
            });
            changeCallback.onSendingAttemp();
        } catch (TokenExchangeRequestGenException e) {
            e.printStackTrace();
        }
    }

}
