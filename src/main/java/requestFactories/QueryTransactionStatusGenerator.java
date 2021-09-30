package requestFactories;

import exception.QueryInvoiceStatusGenException;
import exception.SHA512Exception;
import hu.gov.nav.schemas.ntca._1_0.common.*;
import hu.gov.nav.schemas.osa._3_0.api.QueryTransactionStatusRequest;
import hu.gov.nav.schemas.osa._3_0.api.SoftwareType;
import utils.DateConverter;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.Instant;

public class QueryTransactionStatusGenerator {

    private QueryTransactionStatusGenerator() { }

    public static final QueryTransactionStatusGenerator INSTANCE = new QueryTransactionStatusGenerator();

    public QueryTransactionStatusRequest generateObj(String transactionId) throws QueryInvoiceStatusGenException {
        Instant now = Instant.now();
        String requestId = Common.getUid(now);
        try {
            XMLGregorianCalendar xmlGregorianCalendar = DateConverter.convertInstantToXmlGregorianCalendar(now);
            BasicHeaderType basicHeaderType = Common.getBasicHeaderType(requestId, xmlGregorianCalendar);
            UserHeaderType userHeaderType = Common.getUserHeaderTypeNormal(now ,requestId);
            SoftwareType softwareType = Common.getSoftwareType();
            QueryTransactionStatusRequest queryTransactionStatusRequest = new QueryTransactionStatusRequest();
            queryTransactionStatusRequest.setReturnOriginalRequest(true);
            queryTransactionStatusRequest.setTransactionId(transactionId);
            queryTransactionStatusRequest.setHeader(basicHeaderType);
            queryTransactionStatusRequest.setUser(userHeaderType);
            queryTransactionStatusRequest.setSoftware(softwareType);
            return queryTransactionStatusRequest;
        } catch (SHA512Exception e) {
            throw new QueryInvoiceStatusGenException("SHA couldnt be generated!");
        } catch (DatatypeConfigurationException e) {
            throw new QueryInvoiceStatusGenException(e.getMessage());
        }
    }

}
