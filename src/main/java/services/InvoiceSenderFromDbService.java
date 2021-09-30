package services;

import config.Config;
import data.dao.DaoFactory;
import data.dao.navStatus.NavStatusDao;
import data.dao.szamla.*;
import data.entity.NavStatus;
import data.entity.Szamla;
import exception.AES128Exception;
import exception.DatabaseException;
import exception.InvoiceRequestGenException;
import exception.TokenExchangeRequestGenException;
import hu.gov.nav.schemas.osa._3_0.api.*;
import network.NetworkManager;
import network.response.NetworkCallback;
import requestFactories.ManageInvoiceGenerator;
import requestFactories.TokenExchangeGenerator;
import ui.other.Messages;
import utils.Algos;

import java.sql.Timestamp;
import java.util.*;

public class InvoiceSenderFromDbService extends RepeatableService {

    private static final String TAG = "InvoiceSenderFromDbService";

    private final NavStatusDao navStatusDao;
    private final Map<Szamla.SzamlaType, SzamlaDao> szamlaDaoMap;

    public InvoiceSenderFromDbService() {
        szamlaDaoMap = DaoFactory.getSzamlaDaoMap();
        navStatusDao = new NavStatusDao();
    }

    @Override
    protected String getTag() {
        return TAG;
    }

    @Override
    protected TimerTask getTimerTask() {
        return new InvoiceSender();
    }

    private class InvoiceSender extends TimerTask {

        @Override
        public void run() {
            for (Map.Entry<Szamla.SzamlaType, SzamlaDao> szamlaDaoEntry : szamlaDaoMap.entrySet()) {
                try {
                    List<Szamla> szamlas = szamlaDaoEntry.getValue().getAll();
                    for (Szamla szamla : szamlas) {
                        try {
                            TokenExchangeRequest tokenExchangeRequest = TokenExchangeGenerator.INSTANCE.generateObject();
                            NetworkManager.INSTANCE.exchangeToken(tokenExchangeRequest, new NetworkCallback<TokenExchangeResponse>() {
                                @Override
                                public void onSuccess(TokenExchangeResponse response) {
                                    try {
                                        ManageInvoiceRequest manageInvoiceRequest = ManageInvoiceGenerator.INSTANCE.generateObjFromEntity(
                                                szamla, Algos.decryptAES128(response.getEncodedExchangeToken(), Config.exchangeKey));
                                        utils.Logger.logMessage(TAG, "Sending invoice with IKTSZAM: " + szamla.getIktSzam());
                                        NetworkManager.INSTANCE.manageInvoice(manageInvoiceRequest, new NetworkCallback<ManageInvoiceResponse>() {
                                            @Override
                                            public void onSuccess(ManageInvoiceResponse response) {
                                                handleTransactionIdResponse(szamla, response.getTransactionId(), manageInvoiceRequest.getHeader().getRequestId());
                                                changeCallback.onSuccessfulAttemp();
                                            }

                                            @Override
                                            public void onError(GeneralErrorResponse errorResponse) {
                                                changeCallback.onFailedAttemp();
                                                changeCallback.info(StateChangeCallback.MessageType.ERROR,
                                                        Messages.ERROR_MANAGEINVOICE_REQUEST + errorResponse.getResult().getMessage());
                                            }

                                            @Override
                                            public void onFail(String msg) {
                                                changeCallback.onFailedAttemp();
                                                changeCallback.info(StateChangeCallback.MessageType.NETWORKERROR, msg);
                                            }
                                        });
                                    } catch (AES128Exception | InvoiceRequestGenException e) {
                                        e.printStackTrace();
                                        changeCallback.info(StateChangeCallback.MessageType.ERROR,
                                                "Nem sikerult a ManageInvoiceRequest-et legeneralni!");
                                    }
                                }

                                @Override
                                public void onError(GeneralErrorResponse errorResponse) {
                                    changeCallback.onFailedAttemp();
                                    changeCallback.info(StateChangeCallback.MessageType.ERROR,
                                            errorResponse.getResult().getFuncCode().value() + " "
                                                    + errorResponse.getResult().getMessage());
                                }

                                @Override
                                public void onFail(String msg) {
                                    changeCallback.onFailedAttemp();
                                    changeCallback.info(StateChangeCallback.MessageType.NETWORKERROR, msg);
                                }
                            });
                            changeCallback.onSendingAttemp();
                        } catch (TokenExchangeRequestGenException e) {
                            changeCallback.info(StateChangeCallback.MessageType.ERROR, Messages.ERROR_TOKEN_GENERATION);
                            e.printStackTrace();
                        }
                    }
                } catch (DatabaseException e) {
                    changeCallback.info(StateChangeCallback.MessageType.ERROR, e.getMessage());
                    e.printStackTrace();
                }
            }
        }

        private void handleTransactionIdResponse(Szamla szamla, String transId, String requestId) {
            try {
                szamlaDaoMap.get(szamla.getType()).updateStatusz(szamla.getIktSzam(), Szamla.States.RECEIVED.name());
                NavStatus navStatus = new NavStatus();
                navStatus.setIktszam(szamla.getIktSzam());
                navStatus.setType(szamla.getType().name());
                navStatus.setInvoicestatus(Szamla.States.RECEIVED.name());
                navStatus.setTransactionid(transId);
                navStatus.setRequestid(requestId);
                navStatus.setDatum(new Timestamp(System.currentTimeMillis()));
                navStatusDao.insert(navStatus);
            } catch (DatabaseException e) {
                changeCallback.info(StateChangeCallback.MessageType.ERROR, e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
