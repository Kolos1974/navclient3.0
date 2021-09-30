//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.12.09 at 03:22:27 PM CET 
//


package hu.gov.nav.schemas.osa._3_0.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * VAT subjectivity data of the customer
 * 
 * <p>Java class for CustomerVatDataType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CustomerVatDataType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="customerTaxNumber" type="{http://schemas.nav.gov.hu/OSA/3.0/data}CustomerTaxNumberType"/>
 *         &lt;element name="communityVatNumber" type="{http://schemas.nav.gov.hu/NTCA/1.0/common}CommunityVatNumberType"/>
 *         &lt;element name="thirdStateTaxId" type="{http://schemas.nav.gov.hu/NTCA/1.0/common}SimpleText50NotBlankType"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustomerVatDataType", propOrder = {
    "customerTaxNumber",
    "communityVatNumber",
    "thirdStateTaxId"
})
public class CustomerVatDataType {

    protected CustomerTaxNumberType customerTaxNumber;
    protected String communityVatNumber;
    protected String thirdStateTaxId;

    /**
     * Gets the value of the customerTaxNumber property.
     * 
     * @return
     *     possible object is
     *     {@link CustomerTaxNumberType }
     *     
     */
    public CustomerTaxNumberType getCustomerTaxNumber() {
        return customerTaxNumber;
    }

    /**
     * Sets the value of the customerTaxNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link CustomerTaxNumberType }
     *     
     */
    public void setCustomerTaxNumber(CustomerTaxNumberType value) {
        this.customerTaxNumber = value;
    }

    /**
     * Gets the value of the communityVatNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCommunityVatNumber() {
        return communityVatNumber;
    }

    /**
     * Sets the value of the communityVatNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommunityVatNumber(String value) {
        this.communityVatNumber = value;
    }

    /**
     * Gets the value of the thirdStateTaxId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getThirdStateTaxId() {
        return thirdStateTaxId;
    }

    /**
     * Sets the value of the thirdStateTaxId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setThirdStateTaxId(String value) {
        this.thirdStateTaxId = value;
    }

}
