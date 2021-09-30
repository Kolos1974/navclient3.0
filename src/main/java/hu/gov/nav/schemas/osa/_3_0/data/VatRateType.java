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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * Marking tax rate or tax exempt supply
 * 
 * <p>Java class for VatRateType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="VatRateType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="vatPercentage" type="{http://schemas.nav.gov.hu/OSA/3.0/data}RateType"/>
 *         &lt;element name="vatContent" type="{http://schemas.nav.gov.hu/OSA/3.0/data}RateType"/>
 *         &lt;element name="vatExemption" type="{http://schemas.nav.gov.hu/OSA/3.0/data}DetailedReasonType"/>
 *         &lt;element name="vatOutOfScope" type="{http://schemas.nav.gov.hu/OSA/3.0/data}DetailedReasonType"/>
 *         &lt;element name="vatDomesticReverseCharge" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="marginSchemeIndicator" type="{http://schemas.nav.gov.hu/OSA/3.0/data}MarginSchemeType"/>
 *         &lt;element name="vatAmountMismatch" type="{http://schemas.nav.gov.hu/OSA/3.0/data}VatAmountMismatchType"/>
 *         &lt;element name="noVatCharge" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VatRateType", propOrder = {
    "vatPercentage",
    "vatContent",
    "vatExemption",
    "vatOutOfScope",
    "vatDomesticReverseCharge",
    "marginSchemeIndicator",
    "vatAmountMismatch",
    "noVatCharge"
})
public class VatRateType {

    protected BigDecimal vatPercentage;
    protected BigDecimal vatContent;
    protected DetailedReasonType vatExemption;
    protected DetailedReasonType vatOutOfScope;
    protected Boolean vatDomesticReverseCharge;
    @XmlSchemaType(name = "string")
    protected MarginSchemeType marginSchemeIndicator;
    protected VatAmountMismatchType vatAmountMismatch;
    protected Boolean noVatCharge;

    /**
     * Gets the value of the vatPercentage property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getVatPercentage() {
        return vatPercentage;
    }

    /**
     * Sets the value of the vatPercentage property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setVatPercentage(BigDecimal value) {
        this.vatPercentage = value;
    }

    /**
     * Gets the value of the vatContent property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getVatContent() {
        return vatContent;
    }

    /**
     * Sets the value of the vatContent property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setVatContent(BigDecimal value) {
        this.vatContent = value;
    }

    /**
     * Gets the value of the vatExemption property.
     * 
     * @return
     *     possible object is
     *     {@link DetailedReasonType }
     *     
     */
    public DetailedReasonType getVatExemption() {
        return vatExemption;
    }

    /**
     * Sets the value of the vatExemption property.
     * 
     * @param value
     *     allowed object is
     *     {@link DetailedReasonType }
     *     
     */
    public void setVatExemption(DetailedReasonType value) {
        this.vatExemption = value;
    }

    /**
     * Gets the value of the vatOutOfScope property.
     * 
     * @return
     *     possible object is
     *     {@link DetailedReasonType }
     *     
     */
    public DetailedReasonType getVatOutOfScope() {
        return vatOutOfScope;
    }

    /**
     * Sets the value of the vatOutOfScope property.
     * 
     * @param value
     *     allowed object is
     *     {@link DetailedReasonType }
     *     
     */
    public void setVatOutOfScope(DetailedReasonType value) {
        this.vatOutOfScope = value;
    }

    /**
     * Gets the value of the vatDomesticReverseCharge property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isVatDomesticReverseCharge() {
        return vatDomesticReverseCharge;
    }

    /**
     * Sets the value of the vatDomesticReverseCharge property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setVatDomesticReverseCharge(Boolean value) {
        this.vatDomesticReverseCharge = value;
    }

    /**
     * Gets the value of the marginSchemeIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link MarginSchemeType }
     *     
     */
    public MarginSchemeType getMarginSchemeIndicator() {
        return marginSchemeIndicator;
    }

    /**
     * Sets the value of the marginSchemeIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link MarginSchemeType }
     *     
     */
    public void setMarginSchemeIndicator(MarginSchemeType value) {
        this.marginSchemeIndicator = value;
    }

    /**
     * Gets the value of the vatAmountMismatch property.
     * 
     * @return
     *     possible object is
     *     {@link VatAmountMismatchType }
     *     
     */
    public VatAmountMismatchType getVatAmountMismatch() {
        return vatAmountMismatch;
    }

    /**
     * Sets the value of the vatAmountMismatch property.
     * 
     * @param value
     *     allowed object is
     *     {@link VatAmountMismatchType }
     *     
     */
    public void setVatAmountMismatch(VatAmountMismatchType value) {
        this.vatAmountMismatch = value;
    }

    /**
     * Gets the value of the noVatCharge property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isNoVatCharge() {
        return noVatCharge;
    }

    /**
     * Sets the value of the noVatCharge property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setNoVatCharge(Boolean value) {
        this.noVatCharge = value;
    }

}
