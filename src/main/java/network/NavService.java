package network;

import hu.gov.nav.schemas.osa._3_0.api.*;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface NavService {

    @Headers({
            "Accept: application/xml",
            "Content-Type: application/xml"
    })
    @POST("tokenExchange")
    Call<TokenExchangeResponse> exchangeToken(@Body TokenExchangeRequest request);

    @Headers({
            "Accept: application/xml",
            "Content-Type: application/xml"
    })
    @POST("manageInvoice")
    Call<ManageInvoiceResponse> manageInvoice(@Body ManageInvoiceRequest request);

    @Headers({
            "Accept: application/xml",
            "Content-Type: application/xml"
    })
    @POST("queryTransactionStatus")
    Call<QueryTransactionStatusResponse> queryTransactionStatus(@Body QueryTransactionStatusRequest request);

    @Headers({
            "Accept: application/xml",
            "Content-Type: application/xml"
    })
    @POST("queryInvoiceData")
    Call<QueryInvoiceDataResponse> queryInvoiceData(@Body QueryInvoiceDataRequest request);

}
