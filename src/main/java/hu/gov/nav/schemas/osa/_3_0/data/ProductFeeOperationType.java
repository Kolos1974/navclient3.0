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
 * <p>Java class for ProductFeeOperationType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ProductFeeOperationType">
 *   &lt;restriction base="{http://schemas.nav.gov.hu/NTCA/1.0/common}AtomicStringType8">
 *     &lt;enumeration value="REFUND"/>
 *     &lt;enumeration value="DEPOSIT"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ProductFeeOperationType")
@XmlEnum
public enum ProductFeeOperationType {


    /**
     * Refund
     * 
     */
    REFUND,

    /**
     * Deposit
     * 
     */
    DEPOSIT;

    public String value() {
        return name();
    }

    public static ProductFeeOperationType fromValue(String v) {
        return valueOf(v);
    }

}
