package ui.controller.manageInvoice;

import config.Config;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import services.InvoiceSenderFromDbService;
import services.InvoiceSenderFromXmlService;
import services.StateChangeCallback;
import services.StatusAndRemainingCallback;
import ui.Coordinator;
import ui.controller.BaseController;

import java.io.IOException;

public class ManageInvoiceController extends BaseController {

    private boolean isXmlStarted;
    private boolean isDbStarted;

    private InvoiceSenderFromDbService invoiceSenderFromDbService;
    private InvoiceSenderFromXmlService invoiceSenderFromXmlService;

    private AttempHolder xmlAttemps;
    private AttempHolder dbAttemps;

    @FXML
    private Label numberOfUploadAttemptsXmlLabel;
    @FXML
    private Label numberOfUploadAttemptsDbLabel;
    @FXML
    private Label numberOfSuccAttemptsXmlLabel;
    @FXML
    private Label numberOfFailAttemptsXmlLabel;
    @FXML
    private Label numberOfSuccAttemptsDbLabel;
    @FXML
    private Label numberOfFailAttemptsDbLabel;
    @FXML
    private Label remaingTimeDbLabel;
    @FXML
    private TextArea infoDbTextArea;
    @FXML
    private TextArea infoXmlTextArea;
    @FXML
    private Button startStopXmlButton;
    @FXML
    private Button startStopDbButton;
    @FXML
    private VBox dbVBox;
    @FXML
    private SplitPane mainSplitPane;

    public ManageInvoiceController(Coordinator coordinator) {
        super(coordinator);
        isXmlStarted = false;
        isDbStarted = false;
        dbAttemps = new AttempHolder();
        xmlAttemps = new AttempHolder();
        if (Config.appMode == Config.AppModes.MSSQL) invoiceSenderFromDbService = new InvoiceSenderFromDbService();
    }

    @Override
    public void onOpen() {
        if (Config.appMode != Config.AppModes.MSSQL) {
            mainSplitPane.getItems().remove(dbVBox);
        }
    }

    @FXML
    protected void handlestartStopXmlButtonAction(ActionEvent event) {
        isXmlStarted = !isXmlStarted;
        startStopXmlButton.setText(isXmlStarted ? "Stop" : "Start");
        if (isXmlStarted) {
            try {
                invoiceSenderFromXmlService = InvoiceSenderFromXmlService.startService(new StateChangeCallback() {
                    @Override
                    public void onSendingAttemp() {
                        xmlAttemps.allAttemps++;
                        Platform.runLater(() -> numberOfUploadAttemptsXmlLabel.setText(Integer.toString(xmlAttemps.allAttemps)));
                    }

                    @Override
                    public void onSuccessfulAttemp() {
                        xmlAttemps.succAttemps++;
                        Platform.runLater(() -> numberOfSuccAttemptsXmlLabel.setText(Integer.toString(xmlAttemps.succAttemps)));
                    }

                    @Override
                    public void onFailedAttemp() {
                        xmlAttemps.failAttemps++;
                        Platform.runLater(() -> numberOfFailAttemptsXmlLabel.setText(Integer.toString(xmlAttemps.failAttemps)));
                    }

                    @Override
                    public void info(MessageType type, String info) {
                        Platform.runLater(() -> infoXmlTextArea.setText(type.name() + ": " + infoXmlTextArea.getText() + info + "\n"));
                    }

                });
            } catch (IOException e) {
                e.printStackTrace();
                handlestartStopXmlButtonAction(null);
                showAlert(e.getMessage(), Alert.AlertType.ERROR);
            }
        } else {
            if (invoiceSenderFromXmlService != null) {
                invoiceSenderFromXmlService.stopService();
                invoiceSenderFromXmlService = null;
            }
        }
    }

    @FXML
    protected void handlestartStopDbButtonAction(ActionEvent event) {
        isDbStarted = !isDbStarted;
        startStopDbButton.setText(isDbStarted ? "Stop" : "Start");
        if (isDbStarted) {
            invoiceSenderFromDbService.start(new StatusAndRemainingCallback() {
                @Override
                public void onSendingAttemp() {
                    dbAttemps.allAttemps++;
                    Platform.runLater(() -> numberOfUploadAttemptsDbLabel.setText(Integer.toString(dbAttemps.allAttemps)));
                }

                @Override
                public void onSuccessfulAttemp() {
                    dbAttemps.succAttemps++;
                    Platform.runLater(() -> numberOfSuccAttemptsDbLabel.setText(Integer.toString(dbAttemps.succAttemps)));
                }

                @Override
                public void onFailedAttemp() {
                    dbAttemps.failAttemps++;
                    Platform.runLater(() -> numberOfFailAttemptsDbLabel.setText(Integer.toString(dbAttemps.failAttemps)));
                }

                @Override
                public void info(MessageType type, String info) {
                    Platform.runLater(() -> infoDbTextArea.setText(type.name() + ": " + infoDbTextArea.getText() + info + "\n"));
                }

                @Override
                public void remainingTime(long secs) {
                    Platform.runLater(() -> remaingTimeDbLabel.setText(secsToTimeFormat(secs)));
                }

            });
        } else {
            invoiceSenderFromDbService.stop();
        }
    }

    @FXML
    protected void handleBackButtonAction(ActionEvent event) {
        if (isDbStarted || isXmlStarted) {
            showAlert("Az XML vagy adatbazis mod meg mindig fut, elobb allitsd le oket!", Alert.AlertType.WARNING);
            return;
        }
        coordinator.goToMain();
    }

    private String secsToTimeFormat(long secs) {
        return (secs / 60) + " perc " + (secs % 60) + " masodperc";
    }

}
