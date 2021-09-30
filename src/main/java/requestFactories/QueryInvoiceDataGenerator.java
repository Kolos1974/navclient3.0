package requestFactories;

import config.Config;
import exception.QueryInvoiceStatusGenException;
import exception.SHA512Exception;
import hu.gov.nav.schemas.ntca._1_0.common.*;
import hu.gov.nav.schemas.osa._3_0.api.*;
import utils.Algos;
import utils.DateConverter;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.Instant;

public class QueryInvoiceDataGenerator {

    private QueryInvoiceDataGenerator() { }

    public static final QueryInvoiceDataGenerator INSTANCE = new QueryInvoiceDataGenerator();

    public QueryInvoiceDataRequest generateObj(String invoiceNumber) throws QueryInvoiceStatusGenException {

        Instant now = Instant.now();
        String requestId = Common.getUid(now);
        try {
            XMLGregorianCalendar xmlGregorianCalendar = DateConverter.convertInstantToXmlGregorianCalendar(now);
            BasicHeaderType basicHeaderType = Common.getBasicHeaderType(requestId, xmlGregorianCalendar);
            UserHeaderType userHeaderType = Common.getUserHeaderTypeNormal(now, requestId);
            SoftwareType softwareType = Common.getSoftwareType();

            InvoiceNumberQueryType invoiceNumberQuery = new InvoiceNumberQueryType();
            invoiceNumberQuery.setInvoiceNumber(invoiceNumber);
            invoiceNumberQuery.setInvoiceDirection(InvoiceDirectionType.OUTBOUND);

            QueryInvoiceDataRequest queryInvoiceDataRequest = new QueryInvoiceDataRequest();
            queryInvoiceDataRequest.setHeader(basicHeaderType);
            queryInvoiceDataRequest.setUser(userHeaderType);
            queryInvoiceDataRequest.setSoftware(softwareType);
            queryInvoiceDataRequest.setInvoiceNumberQuery(invoiceNumberQuery);
            return queryInvoiceDataRequest;
        } catch (SHA512Exception | DatatypeConfigurationException e) {
            throw new QueryInvoiceStatusGenException(e.getMessage());
        }

    }
}
