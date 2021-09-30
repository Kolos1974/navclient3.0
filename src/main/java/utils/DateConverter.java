package utils;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateConverter {

    public static XMLGregorianCalendar convertInstantToXmlGregorianCalendar(Instant now)
            throws DatatypeConfigurationException {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTimeInMillis(now.toEpochMilli());
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
    }

    public static XMLGregorianCalendar convertTimestampToXmlGregorianCalendarNoUTC(Timestamp ts)
            throws DatatypeConfigurationException {
        XMLGregorianCalendar xmlGregorianCalendar = getXmlGregorianCalendar(ts);
        xmlGregorianCalendar.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
        return xmlGregorianCalendar;
    }

    public static XMLGregorianCalendar convertTimestampToXmlGregorianCalendarWithUTC(Timestamp ts)
            throws DatatypeConfigurationException {
        return getXmlGregorianCalendar(ts).normalize();
    }

    private static XMLGregorianCalendar getXmlGregorianCalendar(Timestamp ts) throws DatatypeConfigurationException {
        Date date = new Date(ts.getTime());
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(date);
        return DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
    }

}
