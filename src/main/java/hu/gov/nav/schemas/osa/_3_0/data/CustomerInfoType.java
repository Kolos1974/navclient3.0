//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.12.09 at 03:22:27 PM CET 
//


package hu.gov.nav.schemas.osa._3_0.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import hu.gov.nav.schemas.osa._3_0.base.AddressType;


/**
 * Customer data
 * 
 * <p>Java class for CustomerInfoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CustomerInfoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="customerVatStatus" type="{http://schemas.nav.gov.hu/OSA/3.0/data}CustomerVatStatusType"/>
 *         &lt;element name="customerVatData" type="{http://schemas.nav.gov.hu/OSA/3.0/data}CustomerVatDataType" minOccurs="0"/>
 *         &lt;element name="customerName" type="{http://schemas.nav.gov.hu/NTCA/1.0/common}SimpleText512NotBlankType" minOccurs="0"/>
 *         &lt;element name="customerAddress" type="{http://schemas.nav.gov.hu/OSA/3.0/base}AddressType" minOccurs="0"/>
 *         &lt;element name="customerBankAccountNumber" type="{http://schemas.nav.gov.hu/NTCA/1.0/common}BankAccountNumberType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustomerInfoType", propOrder = {
    "customerVatStatus",
    "customerVatData",
    "customerName",
    "customerAddress",
    "customerBankAccountNumber"
})
public class CustomerInfoType {

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected CustomerVatStatusType customerVatStatus;
    protected CustomerVatDataType customerVatData;
    protected String customerName;
    protected AddressType customerAddress;
    protected String customerBankAccountNumber;

    /**
     * Gets the value of the customerVatStatus property.
     * 
     * @return
     *     possible object is
     *     {@link CustomerVatStatusType }
     *     
     */
    public CustomerVatStatusType getCustomerVatStatus() {
        return customerVatStatus;
    }

    /**
     * Sets the value of the customerVatStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link CustomerVatStatusType }
     *     
     */
    public void setCustomerVatStatus(CustomerVatStatusType value) {
        this.customerVatStatus = value;
    }

    /**
     * Gets the value of the customerVatData property.
     * 
     * @return
     *     possible object is
     *     {@link CustomerVatDataType }
     *     
     */
    public CustomerVatDataType getCustomerVatData() {
        return customerVatData;
    }

    /**
     * Sets the value of the customerVatData property.
     * 
     * @param value
     *     allowed object is
     *     {@link CustomerVatDataType }
     *     
     */
    public void setCustomerVatData(CustomerVatDataType value) {
        this.customerVatData = value;
    }

    /**
     * Gets the value of the customerName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Sets the value of the customerName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerName(String value) {
        this.customerName = value;
    }

    /**
     * Gets the value of the customerAddress property.
     * 
     * @return
     *     possible object is
     *     {@link AddressType }
     *     
     */
    public AddressType getCustomerAddress() {
        return customerAddress;
    }

    /**
     * Sets the value of the customerAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressType }
     *     
     */
    public void setCustomerAddress(AddressType value) {
        this.customerAddress = value;
    }

    /**
     * Gets the value of the customerBankAccountNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerBankAccountNumber() {
        return customerBankAccountNumber;
    }

    /**
     * Sets the value of the customerBankAccountNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerBankAccountNumber(String value) {
        this.customerBankAccountNumber = value;
    }

}
