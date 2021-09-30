package network;

import config.Config;
import hu.gov.nav.schemas.osa._3_0.api.*;
import network.response.NetworkCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jaxb.JaxbConverterFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;

public class NetworkManager {

    private static final String TAG = "NetworkManager";

    private NavService navService;

    private NetworkManager() {
        setUp();
    }

    public static final NetworkManager INSTANCE = new NetworkManager();

    private void setUp() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.baseUrl)
                .client(UnsafeOkHttpClient.getUnsafeOkHttpClient())
                .addConverterFactory(JaxbConverterFactory.create())
                .build();

        navService = retrofit.create(NavService.class);
    }

    public void exchangeToken(TokenExchangeRequest request, NetworkCallback callback) {
        Call<TokenExchangeResponse> call = navService.exchangeToken(request);
        call.enqueue(new Callback<TokenExchangeResponse>() {
            public void onResponse(Call<TokenExchangeResponse> call, Response<TokenExchangeResponse> response) {
                if (response.isSuccessful()) {
                    utils.Logger.logMessage(TAG, "ExchangeToken: Success");
                    callback.onSuccess(response.body());
                } else {
                    utils.Logger.logMessage(TAG, "ExchangeToken: Error");
                    utils.Logger.logMessage(TAG, response.message());
                    try {
                        GeneralErrorResponse generalError = getGeneralError(response.errorBody().byteStream());
                        callback.onError(generalError);
                    } catch (JAXBException e) {
                        e.printStackTrace();
                        callback.onFail("ExchangeToken: Message couldnt be parsed");
                    }
                }
            }

            public void onFailure(Call<TokenExchangeResponse> call, Throwable throwable) {
                utils.Logger.logMessage(TAG, "Fail: " + throwable.getMessage());
                callback.onFail(throwable.getMessage());
            }
        });
    }

    public void manageInvoice(ManageInvoiceRequest request, NetworkCallback<ManageInvoiceResponse> callback) {
        Call<ManageInvoiceResponse> call = navService.manageInvoice(request);
        call.enqueue(new Callback<ManageInvoiceResponse>() {
            @Override
            public void onResponse(Call<ManageInvoiceResponse> call, Response<ManageInvoiceResponse> response) {
                if (response.isSuccessful()) {
                    utils.Logger.logMessage(TAG, "ManageInvoice: Success");
                    callback.onSuccess(response.body());
                } else {
                    utils.Logger.logMessage(TAG, "ManageInvoice: Error");
                    utils.Logger.logMessage(TAG, response.message());
                    try {
                        GeneralErrorResponse generalError = getGeneralError(response.errorBody().byteStream());
                        callback.onError(generalError);
                    } catch (JAXBException e) {
                        e.printStackTrace();
                        callback.onFail("ManageInvoice: Error message couldnt be parsed");
                    }
                }
            }

            @Override
            public void onFailure(Call<ManageInvoiceResponse> call, Throwable throwable) {
                utils.Logger.logMessage(TAG, "ManageInvoice: Fail");
                callback.onFail(throwable.getMessage());
            }
        });
    }

    // TODO: refactor these error code duplication
    public void queryTransactionStatus(QueryTransactionStatusRequest request, NetworkCallback<QueryTransactionStatusResponse> callback) {
        Call<QueryTransactionStatusResponse> call = navService.queryTransactionStatus(request);
        call.enqueue(new Callback<QueryTransactionStatusResponse>() {
            @Override
            public void onResponse(Call<QueryTransactionStatusResponse> call, Response<QueryTransactionStatusResponse> response) {
                if (response.isSuccessful()) {
                    utils.Logger.logMessage(TAG, "QueryInvoiceStatus: Success");
                    callback.onSuccess(response.body());
                } else {
                    utils.Logger.logMessage(TAG, "QueryInvoiceStatus: Error");
                    utils.Logger.logMessage(TAG, response.message());
                    try {
                        callback.onError(getGeneralError(response.errorBody().byteStream()));
                    } catch (JAXBException e) {
                        e.printStackTrace();
                        callback.onFail("QueryInvoiceStatus: Error message couldn't be parsed. Error message: " + response.message());
                    }
                }
            }

            @Override
            public void onFailure(Call<QueryTransactionStatusResponse> call, Throwable throwable) {
                utils.Logger.logMessage(TAG, "QueryInvoiceStatus: Fail");
                callback.onFail(throwable.getMessage());
            }
        });
    }

    public void queryInvoiceData(QueryInvoiceDataRequest request, NetworkCallback<QueryInvoiceDataResponse> callback) {
        Call<QueryInvoiceDataResponse> call = navService.queryInvoiceData(request);
        call.enqueue(new Callback<QueryInvoiceDataResponse>() {
            @Override
            public void onResponse(Call<QueryInvoiceDataResponse> call, Response<QueryInvoiceDataResponse> response) {
                if (response.isSuccessful()) {
                    utils.Logger.logMessage(TAG, "QueryInvoiceData: Success");
                    callback.onSuccess(response.body());
                } else {
                    utils.Logger.logMessage(TAG, "QueryInvoiceData: Error");
                    utils.Logger.logMessage(TAG, response.message());
                    try {
                        callback.onError(getGeneralError(response.errorBody().byteStream()));
                    } catch (JAXBException e) {
                        e.printStackTrace();
                        callback.onFail("QueryInvoiceData: Error message couldnt be parsed");
                    }
                }
            }

            @Override
            public void onFailure(Call<QueryInvoiceDataResponse> call, Throwable throwable) {
                utils.Logger.logMessage(TAG, "QueryInvoiceStatus: Fail");
                callback.onFail(throwable.getMessage());
            }
        });
    }

    private GeneralErrorResponse getGeneralError(InputStream inputStream) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(GeneralErrorResponse.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        GeneralErrorResponse errorResponse = (GeneralErrorResponse) unmarshaller.unmarshal(inputStream);
        utils.Logger.logMessage(TAG, "Error: " + errorResponse.getResult().getMessage());
        return errorResponse;
    }

}
