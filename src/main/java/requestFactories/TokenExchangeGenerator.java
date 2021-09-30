package requestFactories;

import exception.SHA512Exception;
import exception.TokenExchangeRequestGenException;
import hu.gov.nav.schemas.ntca._1_0.common.*;
import hu.gov.nav.schemas.osa._3_0.api.SoftwareType;
import hu.gov.nav.schemas.osa._3_0.api.TokenExchangeRequest;
import utils.DateConverter;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.Instant;

public class TokenExchangeGenerator {

    private TokenExchangeGenerator() {}

    public static final TokenExchangeGenerator INSTANCE = new TokenExchangeGenerator();

    public TokenExchangeRequest generateObject() throws TokenExchangeRequestGenException {
        try {
            Instant now = Instant.now();
            String requestId = Common.getUid(now);
            XMLGregorianCalendar xmlGregorianCalendar = DateConverter.convertInstantToXmlGregorianCalendar(now);
            BasicHeaderType basicHeaderType = Common.getBasicHeaderType(requestId, xmlGregorianCalendar);
            UserHeaderType userHeaderType = Common.getUserHeaderTypeNormal(now, requestId);
            SoftwareType softwareType = Common.getSoftwareType();
            TokenExchangeRequest tokenExchangeRequest = new TokenExchangeRequest();
            tokenExchangeRequest.setHeader(basicHeaderType);
            tokenExchangeRequest.setUser(userHeaderType);
            tokenExchangeRequest.setSoftware(softwareType);
            return tokenExchangeRequest;
        } catch (DatatypeConfigurationException e) {
            throw new TokenExchangeRequestGenException("Date couldn't be set!");
        } catch (SHA512Exception e) {
            throw new TokenExchangeRequestGenException("SHA couldnt be generated!");
        }
    }


}
