package ui.controller.invoiceData;

import config.Config;
import exception.QueryInvoiceStatusGenException;
import exception.XmlPrettifyException;
import hu.gov.nav.schemas.osa._3_0.api.GeneralErrorResponse;
import hu.gov.nav.schemas.osa._3_0.api.QueryInvoiceDataRequest;
import hu.gov.nav.schemas.osa._3_0.api.QueryInvoiceDataResponse;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import network.NetworkManager;
import network.response.NetworkCallback;
import requestFactories.QueryInvoiceDataGenerator;
import ui.Coordinator;
import ui.controller.BaseController;
import utils.XmlFormatter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.StringWriter;

public class InvoiceDataController extends BaseController implements NetworkCallback<QueryInvoiceDataResponse> {

    @FXML
    private TextField invoiceNumberTextField;
    @FXML
    private TextArea resultTextArea;

    public InvoiceDataController(Coordinator coordinator) {
        super(coordinator);
    }

    @Override
    public void onOpen() {

    }

    @FXML
    protected void handleQueryInvoiceButtonAction(ActionEvent event) {
        if (invoiceNumberTextField.getText().isEmpty()) return;
        try {
            QueryInvoiceDataRequest request = QueryInvoiceDataGenerator.INSTANCE.generateObj(invoiceNumberTextField.getText());
            NetworkManager.INSTANCE.queryInvoiceData(request, this);
        } catch (QueryInvoiceStatusGenException e) {
            e.printStackTrace();
            showAlert("A QuerInvoiceDataRequest generalasa nem sikerult!", Alert.AlertType.ERROR);
        }
    }

    @FXML
    protected void handleBackButtonAction(javafx.event.ActionEvent event) {
        coordinator.goToMain();
    }

    @Override
    public void onSuccess(QueryInvoiceDataResponse response) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(QueryInvoiceDataResponse.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter stringWriter = new StringWriter();
            marshaller.marshal(response, stringWriter);
            String filePath = Config.navExport + "\\" + invoiceNumberTextField.getText().replace("/", "_") + "Data.xml";
            marshaller.marshal(response, new File(filePath));
            String s = XmlFormatter.prettify(stringWriter.toString());
            Platform.runLater(() -> {
                resultTextArea.setText(s);
                showAlert("A valaszt itt is, illetve ebben a fajlban is megtalalod: " + filePath,
                        Alert.AlertType.INFORMATION);
            });
        } catch (JAXBException | XmlPrettifyException e) {
            Platform.runLater(() -> resultTextArea.setText("A valasz XML-e alakitasa nem sikerult!"));
            e.printStackTrace();
        }
    }

    @Override
    public void onError(GeneralErrorResponse errorResponse) {
        Platform.runLater(() -> showAlert(errorResponse.getResult().getMessage(), Alert.AlertType.ERROR));
    }

    @Override
    public void onFail(String msg) {
        showNetworkErrorAlert(msg);
    }
}
