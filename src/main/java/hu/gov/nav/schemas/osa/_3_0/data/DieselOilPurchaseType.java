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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import hu.gov.nav.schemas.osa._3_0.base.SimpleAddressType;


/**
 * Data of gas oil acquisition after taxation � point a), paragraph (1) of Section 75 of the NGM Decree No. 45/2016. (XI. 29.)
 * 
 * <p>Java class for DieselOilPurchaseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DieselOilPurchaseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="purchaseLocation" type="{http://schemas.nav.gov.hu/OSA/3.0/base}SimpleAddressType"/>
 *         &lt;element name="purchaseDate" type="{http://schemas.nav.gov.hu/OSA/3.0/base}InvoiceDateType"/>
 *         &lt;element name="vehicleRegistrationNumber" type="{http://schemas.nav.gov.hu/NTCA/1.0/common}PlateNumberType"/>
 *         &lt;element name="dieselOilQuantity" type="{http://schemas.nav.gov.hu/OSA/3.0/data}QuantityType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DieselOilPurchaseType", propOrder = {
    "purchaseLocation",
    "purchaseDate",
    "vehicleRegistrationNumber",
    "dieselOilQuantity"
})
public class DieselOilPurchaseType {

    @XmlElement(required = true)
    protected SimpleAddressType purchaseLocation;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar purchaseDate;
    @XmlElement(required = true)
    protected String vehicleRegistrationNumber;
    protected BigDecimal dieselOilQuantity;

    /**
     * Gets the value of the purchaseLocation property.
     * 
     * @return
     *     possible object is
     *     {@link SimpleAddressType }
     *     
     */
    public SimpleAddressType getPurchaseLocation() {
        return purchaseLocation;
    }

    /**
     * Sets the value of the purchaseLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link SimpleAddressType }
     *     
     */
    public void setPurchaseLocation(SimpleAddressType value) {
        this.purchaseLocation = value;
    }

    /**
     * Gets the value of the purchaseDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPurchaseDate() {
        return purchaseDate;
    }

    /**
     * Sets the value of the purchaseDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPurchaseDate(XMLGregorianCalendar value) {
        this.purchaseDate = value;
    }

    /**
     * Gets the value of the vehicleRegistrationNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVehicleRegistrationNumber() {
        return vehicleRegistrationNumber;
    }

    /**
     * Sets the value of the vehicleRegistrationNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVehicleRegistrationNumber(String value) {
        this.vehicleRegistrationNumber = value;
    }

    /**
     * Gets the value of the dieselOilQuantity property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDieselOilQuantity() {
        return dieselOilQuantity;
    }

    /**
     * Sets the value of the dieselOilQuantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDieselOilQuantity(BigDecimal value) {
        this.dieselOilQuantity = value;
    }

}
