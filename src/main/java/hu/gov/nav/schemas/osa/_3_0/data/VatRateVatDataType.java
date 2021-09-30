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
 * VAT data of given tax rate
 * 
 * <p>Java class for VatRateVatDataType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="VatRateVatDataType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="vatRateVatAmount" type="{http://schemas.nav.gov.hu/OSA/3.0/base}MonetaryType"/>
 *         &lt;element name="vatRateVatAmountHUF" type="{http://schemas.nav.gov.hu/OSA/3.0/base}MonetaryType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VatRateVatDataType", propOrder = {
    "vatRateVatAmount",
    "vatRateVatAmountHUF"
})
public class VatRateVatDataType {

    @XmlElement(required = true)
    protected BigDecimal vatRateVatAmount;
    @XmlElement(required = true)
    protected BigDecimal vatRateVatAmountHUF;

    /**
     * Gets the value of the vatRateVatAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getVatRateVatAmount() {
        return vatRateVatAmount;
    }

    /**
     * Sets the value of the vatRateVatAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setVatRateVatAmount(BigDecimal value) {
        this.vatRateVatAmount = value;
    }

    /**
     * Gets the value of the vatRateVatAmountHUF property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getVatRateVatAmountHUF() {
        return vatRateVatAmountHUF;
    }

    /**
     * Sets the value of the vatRateVatAmountHUF property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setVatRateVatAmountHUF(BigDecimal value) {
        this.vatRateVatAmountHUF = value;
    }

}
