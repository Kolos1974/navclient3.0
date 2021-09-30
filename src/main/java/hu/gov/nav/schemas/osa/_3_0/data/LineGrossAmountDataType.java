//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.12.09 at 03:22:27 PM CET 
//


package hu.gov.nav.schemas.osa._3_0.data;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Line gross data
 * 
 * <p>Java class for LineGrossAmountDataType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LineGrossAmountDataType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="lineGrossAmountNormal" type="{http://schemas.nav.gov.hu/OSA/3.0/base}MonetaryType"/>
 *         &lt;element name="lineGrossAmountNormalHUF" type="{http://schemas.nav.gov.hu/OSA/3.0/base}MonetaryType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LineGrossAmountDataType", propOrder = {
    "lineGrossAmountNormal",
    "lineGrossAmountNormalHUF"
})
public class LineGrossAmountDataType {

    @XmlElement(required = true)
    protected BigDecimal lineGrossAmountNormal;
    @XmlElement(required = true)
    protected BigDecimal lineGrossAmountNormalHUF;

    /**
     * Gets the value of the lineGrossAmountNormal property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getLineGrossAmountNormal() {
        return lineGrossAmountNormal;
    }

    /**
     * Sets the value of the lineGrossAmountNormal property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setLineGrossAmountNormal(BigDecimal value) {
        this.lineGrossAmountNormal = value;
    }

    /**
     * Gets the value of the lineGrossAmountNormalHUF property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getLineGrossAmountNormalHUF() {
        return lineGrossAmountNormalHUF;
    }

    /**
     * Sets the value of the lineGrossAmountNormalHUF property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setLineGrossAmountNormalHUF(BigDecimal value) {
        this.lineGrossAmountNormalHUF = value;
    }

}
