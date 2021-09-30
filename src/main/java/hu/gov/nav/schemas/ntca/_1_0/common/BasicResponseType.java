//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.12.09 at 03:22:41 PM CET 
//


package hu.gov.nav.schemas.ntca._1_0.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * Basic response data
 * 
 * <p>Java class for BasicResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BasicResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="header" type="{http://schemas.nav.gov.hu/NTCA/1.0/common}BasicHeaderType"/>
 *         &lt;element name="result" type="{http://schemas.nav.gov.hu/NTCA/1.0/common}BasicResultType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BasicResponseType", propOrder = {
    "header",
    "result"
})
@XmlSeeAlso({
    GeneralErrorHeaderResponseType.class
})
public class BasicResponseType {

    @XmlElement(required = true)
    protected BasicHeaderType header;
    @XmlElement(required = true)
    protected BasicResultType result;

    /**
     * Gets the value of the header property.
     * 
     * @return
     *     possible object is
     *     {@link BasicHeaderType }
     *     
     */
    public BasicHeaderType getHeader() {
        return header;
    }

    /**
     * Sets the value of the header property.
     * 
     * @param value
     *     allowed object is
     *     {@link BasicHeaderType }
     *     
     */
    public void setHeader(BasicHeaderType value) {
        this.header = value;
    }

    /**
     * Gets the value of the result property.
     * 
     * @return
     *     possible object is
     *     {@link BasicResultType }
     *     
     */
    public BasicResultType getResult() {
        return result;
    }

    /**
     * Sets the value of the result property.
     * 
     * @param value
     *     allowed object is
     *     {@link BasicResultType }
     *     
     */
    public void setResult(BasicResultType value) {
        this.result = value;
    }

}
