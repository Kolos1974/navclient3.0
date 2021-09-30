package ui.controller.transactionStatus;

import exception.QueryInvoiceStatusGenException;
import exception.XmlPrettifyException;
import hu.gov.nav.schemas.ntca._1_0.common.*; 
import hu.gov.nav.schemas.osa._3_0.api.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import network.NetworkManager;
import network.response.NetworkCallback;
import requestFactories.QueryTransactionStatusGenerator;
import ui.Coordinator;
import ui.controller.BaseController;
import utils.XmlFormatter;

public class TransactionStatusCheckController extends BaseController implements NetworkCallback<QueryTransactionStatusResponse> {

    private static final String TAG = "InvoiceStatusCheckController";

    @FXML
    private TextField transactionIdTextField;
    @FXML
    private TextArea invoiceTextArea;
    @FXML
    private Label invoiceStatusLabel;
    @FXML
    private Label errorCodeLabel;
    @FXML
    private Label messageLabel;
    @FXML
    private HBox errorBox;

    public TransactionStatusCheckController(Coordinator coordinator) {
        super(coordinator);
    }

    @Override
    public void onOpen() {
        clearFields();
    }

    @FXML
    protected void handleStatusCheckButtonAction(ActionEvent event) {
        String transactionId = transactionIdTextField.getText();
        if (transactionId.equals("")) return;
        clearFields();
        try {
            QueryTransactionStatusRequest queryTransactionStatusRequest = QueryTransactionStatusGenerator.INSTANCE.generateObj(transactionId);
            NetworkManager.INSTANCE.queryTransactionStatus(queryTransactionStatusRequest, this);
        } catch (QueryInvoiceStatusGenException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void handleBackButtonAction(ActionEvent event) {
        coordinator.goToMain();
    }

    @Override
    public void onSuccess(QueryTransactionStatusResponse response) {
        if (response.getProcessingResults() == null) {
            Platform.runLater(() -> showAlert("Nincsen ehhez a Transaction ID-hoz Invoice!", Alert.AlertType.WARNING));
        } else {
            ProcessingResultType processingResultType = response.getProcessingResults().getProcessingResult().get(0);
            String invoice = new String(processingResultType.getOriginalRequest());
            Platform.runLater(() -> {
                try {
                    invoiceTextArea.setText(XmlFormatter.prettify(invoice));
                } catch (XmlPrettifyException e) {
                    invoiceTextArea.setText(invoice);
                }
                invoiceStatusLabel.setText(processingResultType.getInvoiceStatus().value());
            });
        }
    }

    @Override
    public void onError(GeneralErrorResponse errorResponse) {
        Platform.runLater(() -> {
            showErrors();
            showAlert(errorResponse.getResult().getMessage(), Alert.AlertType.ERROR);
            if (errorResponse.getTechnicalValidationMessages() == null) {
                errorCodeLabel.setText(errorResponse.getResult().getErrorCode());
                messageLabel.setText(errorResponse.getResult().getMessage());
            } else {
                TechnicalValidationResultType technicalValidationResultType = errorResponse.getTechnicalValidationMessages().get(0);
                errorCodeLabel.setText(technicalValidationResultType.getValidationResultCode().value());
                messageLabel.setText(errorResponse.getResult().getMessage());
                invoiceTextArea.setText(technicalValidationResultType.getMessage());
            }
        });
    }

    @Override
    public void onFail(String msg) {
        showNetworkErrorAlert(msg);
    }

    private void clearFields() {
        invoiceStatusLabel.setText("");
        invoiceTextArea.setText("");
        errorCodeLabel.setText("");
        messageLabel.setText("");
        hideErrors();
    }

    private void hideErrors() {
        errorBox.setManaged(false);
        errorBox.setVisible(false);
    }

    private void showErrors() {
        errorBox.setManaged(true);
        errorBox.setVisible(true);
    }
}
