//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.12.09 at 03:22:16 PM CET 
//


package hu.gov.nav.schemas.osa._3_0.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * Response type of the POST /queryInvoiceCheck REST operation
 * 
 * <p>Java class for QueryInvoiceCheckResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="QueryInvoiceCheckResponseType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.nav.gov.hu/OSA/3.0/api}BasicOnlineInvoiceResponseType">
 *       &lt;sequence>
 *         &lt;element name="invoiceCheckResult" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QueryInvoiceCheckResponseType", propOrder = {
    "invoiceCheckResult"
})
@XmlSeeAlso({
    QueryInvoiceCheckResponse.class
})
public class QueryInvoiceCheckResponseType
    extends BasicOnlineInvoiceResponseType
{

    protected boolean invoiceCheckResult;

    /**
     * Gets the value of the invoiceCheckResult property.
     * 
     */
    public boolean isInvoiceCheckResult() {
        return invoiceCheckResult;
    }

    /**
     * Sets the value of the invoiceCheckResult property.
     * 
     */
    public void setInvoiceCheckResult(boolean value) {
        this.invoiceCheckResult = value;
    }

}
