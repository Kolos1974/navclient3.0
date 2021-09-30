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
 * <p>Java class for ProductCodeCategoryType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ProductCodeCategoryType">
 *   &lt;restriction base="{http://schemas.nav.gov.hu/NTCA/1.0/common}AtomicStringType8">
 *     &lt;minLength value="2"/>
 *     &lt;maxLength value="6"/>
 *     &lt;enumeration value="VTSZ"/>
 *     &lt;enumeration value="SZJ"/>
 *     &lt;enumeration value="KN"/>
 *     &lt;enumeration value="AHK"/>
 *     &lt;enumeration value="CSK"/>
 *     &lt;enumeration value="KT"/>
 *     &lt;enumeration value="EJ"/>
 *     &lt;enumeration value="TESZOR"/>
 *     &lt;enumeration value="OWN"/>
 *     &lt;enumeration value="OTHER"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ProductCodeCategoryType")
@XmlEnum
public enum ProductCodeCategoryType {


    /**
     * Customs tariff number CTN
     * 
     */
    VTSZ,

    /**
     * Business service list number BSL
     * 
     */
    SZJ,

    /**
     * CN code (Combined Nomenclature, 2658/87/ECC Annex I)
     * 
     */
    KN,

    /**
     * Administrative reference code of e-TKO defined in the Act LXVIII of 2016 on Excise Duties
     * 
     */
    AHK,

    /**
     * Packaging product catalogue code of the product according to the Title A) in the Schedule No.1 of the Government Decree No. 343/2011. (XII. 29.)
     * 
     */
    CSK,

    /**
     * Environmental protection product code of the product according to the Title B) in the Schedule No.1 of the Government Decree No. 343/2011. (XII. 29.)
     * 
     */
    KT,

    /**
     * Classification of Inventory of Construction
     * 
     */
    EJ,

    /**
     * Product code according to the TESZOR (Hungarian Classification of Goods and Services), Classification of Product by Activity, CPA - regulation 451/2008/EC
     * 
     */
    TESZOR,

    /**
     * The own product code of the enterprise
     * 
     */
    OWN,

    /**
     * Other product code
     * 
     */
    OTHER;

    public String value() {
        return name();
    }

    public static ProductCodeCategoryType fromValue(String v) {
        return valueOf(v);
    }

}
