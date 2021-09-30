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
 * Data of vessel
 * 
 * <p>Java class for VesselType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="VesselType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="length" type="{http://schemas.nav.gov.hu/OSA/3.0/data}QuantityType"/>
 *         &lt;element name="activityReferred" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="sailedHours" type="{http://schemas.nav.gov.hu/OSA/3.0/data}QuantityType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VesselType", propOrder = {
    "length",
    "activityReferred",
    "sailedHours"
})
public class VesselType {

    @XmlElement(required = true)
    protected BigDecimal length;
    protected boolean activityReferred;
    @XmlElement(required = true)
    protected BigDecimal sailedHours;

    /**
     * Gets the value of the length property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getLength() {
        return length;
    }

    /**
     * Sets the value of the length property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setLength(BigDecimal value) {
        this.length = value;
    }

    /**
     * Gets the value of the activityReferred property.
     * 
     */
    public boolean isActivityReferred() {
        return activityReferred;
    }

    /**
     * Sets the value of the activityReferred property.
     * 
     */
    public void setActivityReferred(boolean value) {
        this.activityReferred = value;
    }

    /**
     * Gets the value of the sailedHours property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSailedHours() {
        return sailedHours;
    }

    /**
     * Sets the value of the sailedHours property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSailedHours(BigDecimal value) {
        this.sailedHours = value;
    }

}
