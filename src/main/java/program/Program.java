package program;

import data.ConnectionFactory;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.Coordinator;
import ui.controller.*;
import ui.controller.invoiceData.InvoiceDataController;
import ui.controller.transactionStatus.TransactionStatusCheckController;
import ui.controller.main.MainController;
import ui.controller.manageInvoice.ManageInvoiceController;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Program extends Application implements Coordinator {

    private Stage window;
    private Scene mainScene;
    private Scene manageInvoiceScene;
    private Scene transactionStatusCheckScene;
    private Scene invoiceDataScene;

    private Map<Scene, BaseController> controllerMap = new HashMap<>();

    private List<Shutdownable> shutdownables = new ArrayList<>();

    public static void main(String[] args) {
        launch(args);
    }

    private double width;
    private double height;

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/Main.fxml"));
        MainController mainController = new MainController(this);
        fxmlLoader.setController(mainController);

        //----------------
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        width = screenSize.getWidth();
        height = screenSize.getHeight();
        //----------------

        Parent mainFXML = fxmlLoader.load();
        mainScene = new Scene(mainFXML, width, height);
        controllerMap.put(mainScene, mainController);
        window.setScene(mainScene);
        window.setTitle("Nav szamlabekuldo");
        window.show();
        goToMain();
    }

    @Override
    public void stop() throws Exception {
        setUpShotdownables();
        utils.Logger.logMessage("Program", "Stage is ending...");
        shutdownables.forEach(shutdownable -> shutdownable.shutdown());
        System.exit(0);
    }

    @Override
    public void goToMain() {
        controllerMap.get(mainScene).onOpen();
        window.setScene(mainScene);
    }

    @Override
    public void goToManageInvoice() {
        if (manageInvoiceScene == null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/ManageInvoice.fxml"));
            ManageInvoiceController manageInvoiceController = new ManageInvoiceController(this);
            fxmlLoader.setController(manageInvoiceController);
            manageInvoiceScene = loadScene(fxmlLoader);
            controllerMap.put(manageInvoiceScene, manageInvoiceController);
        }
        controllerMap.get(manageInvoiceScene).onOpen();
        window.setScene(manageInvoiceScene);
    }

    @Override
    public void goToInvoiceStatusCheck() {
        if (transactionStatusCheckScene == null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/InvoiceStatusCheck.fxml"));
            TransactionStatusCheckController transactionStatusCheckController = new TransactionStatusCheckController(this);
            fxmlLoader.setController(transactionStatusCheckController);
            transactionStatusCheckScene = loadScene(fxmlLoader);
            controllerMap.put(transactionStatusCheckScene, transactionStatusCheckController);
        }
        controllerMap.get(transactionStatusCheckScene).onOpen();
        window.setScene(transactionStatusCheckScene);
    }

    @Override
    public void goToInvoiceData() {
        if (invoiceDataScene == null) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/InvoiceData.fxml"));
            InvoiceDataController invoiceDataController = new InvoiceDataController(this);
            fxmlLoader.setController(invoiceDataController);
            invoiceDataScene = loadScene(fxmlLoader);
            controllerMap.put(invoiceDataScene, invoiceDataController);
        }
        controllerMap.get(invoiceDataScene).onOpen();
        window.setScene(invoiceDataScene);
    }

    private Scene loadScene(FXMLLoader fxmlLoader) {
        try {
            Parent fxml = fxmlLoader.load();
            return new Scene(fxml, width, height);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("FXML coulnt be loaded!");
        }
    }

    @Override
    public void exit() {
        Platform.exit();
    }

    private void setUpShotdownables() {
        shutdownables.add(ThreadPool.INSTANCE);
        shutdownables.add(ConnectionFactory.getInstance());
    }
}
