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
 * Calculation of simplified invoice totals
 * 
 * <p>Java class for SummarySimplifiedType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SummarySimplifiedType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="vatRate" type="{http://schemas.nav.gov.hu/OSA/3.0/data}VatRateType"/>
 *         &lt;element name="vatContentGrossAmount" type="{http://schemas.nav.gov.hu/OSA/3.0/base}MonetaryType"/>
 *         &lt;element name="vatContentGrossAmountHUF" type="{http://schemas.nav.gov.hu/OSA/3.0/base}MonetaryType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SummarySimplifiedType", propOrder = {
    "vatRate",
    "vatContentGrossAmount",
    "vatContentGrossAmountHUF"
})
public class SummarySimplifiedType {

    @XmlElement(required = true)
    protected VatRateType vatRate;
    @XmlElement(required = true)
    protected BigDecimal vatContentGrossAmount;
    @XmlElement(required = true)
    protected BigDecimal vatContentGrossAmountHUF;

    /**
     * Gets the value of the vatRate property.
     * 
     * @return
     *     possible object is
     *     {@link VatRateType }
     *     
     */
    public VatRateType getVatRate() {
        return vatRate;
    }

    /**
     * Sets the value of the vatRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link VatRateType }
     *     
     */
    public void setVatRate(VatRateType value) {
        this.vatRate = value;
    }

    /**
     * Gets the value of the vatContentGrossAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getVatContentGrossAmount() {
        return vatContentGrossAmount;
    }

    /**
     * Sets the value of the vatContentGrossAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setVatContentGrossAmount(BigDecimal value) {
        this.vatContentGrossAmount = value;
    }

    /**
     * Gets the value of the vatContentGrossAmountHUF property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getVatContentGrossAmountHUF() {
        return vatContentGrossAmountHUF;
    }

    /**
     * Sets the value of the vatContentGrossAmountHUF property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setVatContentGrossAmountHUF(BigDecimal value) {
        this.vatContentGrossAmountHUF = value;
    }

}
