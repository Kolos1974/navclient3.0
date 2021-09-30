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
 * <p>Java class for LineNatureIndicatorType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="LineNatureIndicatorType">
 *   &lt;restriction base="{http://schemas.nav.gov.hu/NTCA/1.0/common}AtomicStringType15">
 *     &lt;enumeration value="PRODUCT"/>
 *     &lt;enumeration value="SERVICE"/>
 *     &lt;enumeration value="OTHER"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "LineNatureIndicatorType")
@XmlEnum
public enum LineNatureIndicatorType {


    /**
     * Supply of goods
     * 
     */
    PRODUCT,

    /**
     * Supply of services
     * 
     */
    SERVICE,

    /**
     * Other, non-qualifiable
     * 
     */
    OTHER;

    public String value() {
        return name();
    }

    public static LineNatureIndicatorType fromValue(String v) {
        return valueOf(v);
    }

}
