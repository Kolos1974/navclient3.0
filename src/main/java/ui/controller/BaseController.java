package ui.controller;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import ui.Coordinator;
import ui.other.Messages;

public abstract class BaseController {

    protected Coordinator coordinator;

    private Alert alert ;

    public BaseController(Coordinator coordinator) {
        this.coordinator = coordinator;
    }

    protected void showAlert(String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle("Message");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    protected void showNetworkErrorAlert(String msg) {
        Platform.runLater(() -> showAlert(Messages.ERROR_NETWORK + msg, Alert.AlertType.ERROR));
    }

    protected void showLoading(String msg) {
        alert = new Alert(Alert.AlertType.NONE, msg);
        alert.setResult(ButtonType.OK);
        alert.showAndWait();
    }

    protected void dissmisLoading() {
        if (alert != null) {
            alert.close();
        }
    }

    public abstract void onOpen();

}
