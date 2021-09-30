package network.response;

import hu.gov.nav.schemas.osa._3_0.api.GeneralErrorResponse;
import hu.gov.nav.schemas.osa._3_0.api.TokenExchangeResponse;

public interface NetworkCallback<T> {

    void onSuccess(T response);

    void onError(GeneralErrorResponse errorResponse);

    void onFail(String msg);

}
