//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.12.09 at 03:22:27 PM CET 
//


package hu.gov.nav.schemas.osa._3_0.data;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CustomerVatStatusType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="CustomerVatStatusType">
 *   &lt;restriction base="{http://schemas.nav.gov.hu/NTCA/1.0/common}AtomicStringType15">
 *     &lt;enumeration value="DOMESTIC"/>
 *     &lt;enumeration value="OTHER"/>
 *     &lt;enumeration value="PRIVATE_PERSON"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "CustomerVatStatusType")
@XmlEnum
public enum CustomerVatStatusType {


    /**
     * Domestic VAT subject
     * 
     */
    DOMESTIC,

    /**
     * Other (domestic non-VAT subject, non-natural person, foreign VAT subject and foreign non-VAT subject, non-natural person)
     * 
     */
    OTHER,

    /**
     * Non-VAT subject (domestic or foreign) natural person
     * 
     */
    PRIVATE_PERSON;

    public String value() {
        return name();
    }

    public static CustomerVatStatusType fromValue(String v) {
        return valueOf(v);
    }

}
