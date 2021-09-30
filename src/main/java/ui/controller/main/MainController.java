package ui.controller.main;

import config.Config;
import data.dao.sajatData.SajatDataDao;
import exception.ConfigFormatException;
import exception.DatabaseException;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import program.ThreadPool;
import services.TransactionStatusCheckService;
import ui.Coordinator;
import ui.controller.BaseController;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MainController extends BaseController {

    private boolean isFirstOpen = true;

    public MainController(Coordinator coordinator) {
        super(coordinator);
    }

    @Override
    public void onOpen() {
        if (isFirstOpen) {
            initApp();
            showLoading("Az alkalmazas elokeszitese folyamatban, kerem varjon!");
            isFirstOpen = false;
        }
    }

    @FXML
    private Label dbWorkingLabel;
    @FXML
    private Label xmlWorkingLabel;
    @FXML
    private Label taxNumberLabel;
    @FXML
    private Label navApiAddressLabel;
    @FXML
    private Button manageInvoiceButton;
    @FXML
    private Button transactionStatusButton;
    @FXML
    private Button invoiceDataButton;
    @FXML
    private VBox mainVBox;

    @FXML
    protected void handleExitButtonAction(ActionEvent event) {
        coordinator.exit();
    }

    @FXML
    protected void handleInvoiceStatusCheckButtonAction(ActionEvent event) {
        coordinator.goToInvoiceStatusCheck();
    }

    @FXML
    protected void handleManageInvoiceRequestButtonAction(ActionEvent event) {
        coordinator.goToManageInvoice();
    }

    @FXML
    protected void handleInvoiceDataButtonAction(ActionEvent event) {
        coordinator.goToInvoiceData();
    }

    private void initApp() {
        ThreadPool.INSTANCE.runTask(new Task<Void>() {
            @Override
            protected Void call() throws DatabaseException, FileNotFoundException, ConfigFormatException {
                readConfig();
                if (Config.appMode == Config.AppModes.MSSQL) new SajatDataDao().readCompanyData();
                return null;
            }

            @Override
            protected void failed() {
                super.failed();
                Throwable exception = getException();
                dbWorkingLabel.setText("NEM");
                xmlWorkingLabel.setText("NEM");
                disableButtons();
                if (exception instanceof FileNotFoundException) showAlert("A konfig fajl nem talalhato, az alkalmazas mukodeskeptelen! Az alkalmazas egy config.txt fajlt var.", Alert.AlertType.ERROR);
                else if (exception instanceof DatabaseException) {
                    showAlert("A sajat ceges adatokat nem tudtam beolvasni, igy az alkalmazas nem fog jol mukodni! Adatbazis hiba: " + getException().getMessage(), Alert.AlertType.ERROR);
                } else if (exception instanceof ConfigFormatException) {
                    showAlert("Config fajl beolvasasnal hiba: " + getException().getMessage(), Alert.AlertType.ERROR);
                }
                dissmisLoading();
            }

            @Override
            protected void succeeded() {
                super.succeeded();
                dbWorkingLabel.setText(Config.appMode == Config.AppModes.XML ? "NEM" : "IGEN");
                xmlWorkingLabel.setText("IGEN");
                navApiAddressLabel.setText(Config.baseUrl);
                taxNumberLabel.setText(Config.taxNumber);
                dissmisLoading();
                TransactionStatusCheckService.INSTANCE.start(null);
            }

        });
    }

    private void readConfig() throws ConfigFormatException, FileNotFoundException {
        Config.appMode = Config.AppModes.NONE;
        try (Scanner scanner = new Scanner(new File("config.txt"))) {
            Config.AppModes mode = Config.AppModes.valueOf(scanner.next());
            switch (mode) {
                case XML:
                    Config.baseUrl = scanner.next();
                    Config.userName = scanner.next();
                    Config.password = scanner.next();
                    Config.signKey = scanner.next();
                    Config.exchangeKey = scanner.next();
                    Config.taxNumber = scanner.next();
                    Config.intervalTime = scanner.nextInt();
                    Config.navImport = scanner.next();
                    Config.navExport = scanner.next();
                    Config.softwareId = scanner.next();
                    scanner.nextLine();
                    Config.softwareName = scanner.nextLine();
                    Config.softwareMainVersion = scanner.next();
                    scanner.nextLine();
                    Config.softwareDevName = scanner.nextLine();
                    Config.softwareDevContact = scanner.next();
                    Config.softwareDevCountryCode = scanner.next();
                    Config.softwareDevTaxNumber = scanner.next();
                    break;
                case MSSQL:
                    Config.dbUrl = scanner.next();
                    Config.dbName = scanner.next();
                    Config.dbUsername = scanner.next();
                    Config.dbPassword = scanner.next();
                    break;
                default:
                    throw new ConfigFormatException("A config file nem jo formatumu!");
            }
            Config.appMode = mode;
        }
    }

    private void disableButtons() {
        manageInvoiceButton.setDisable(true);
        transactionStatusButton.setDisable(true);
        invoiceDataButton.setDisable(true);
    }

}
