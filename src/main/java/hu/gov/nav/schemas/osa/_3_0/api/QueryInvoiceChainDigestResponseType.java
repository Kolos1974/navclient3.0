//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.12.09 at 03:22:16 PM CET 
//


package hu.gov.nav.schemas.osa._3_0.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * Response type of the POST /queryInvoiceChainDigest REST operation
 * 
 * <p>Java class for QueryInvoiceChainDigestResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="QueryInvoiceChainDigestResponseType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.nav.gov.hu/OSA/3.0/api}BasicOnlineInvoiceResponseType">
 *       &lt;sequence>
 *         &lt;element name="invoiceChainDigestResult" type="{http://schemas.nav.gov.hu/OSA/3.0/api}InvoiceChainDigestResultType"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QueryInvoiceChainDigestResponseType", propOrder = {
    "invoiceChainDigestResult"
})
@XmlSeeAlso({
    QueryInvoiceChainDigestResponse.class
})
public class QueryInvoiceChainDigestResponseType
    extends BasicOnlineInvoiceResponseType
{

    @XmlElement(required = true)
    protected InvoiceChainDigestResultType invoiceChainDigestResult;

    /**
     * Gets the value of the invoiceChainDigestResult property.
     * 
     * @return
     *     possible object is
     *     {@link InvoiceChainDigestResultType }
     *     
     */
    public InvoiceChainDigestResultType getInvoiceChainDigestResult() {
        return invoiceChainDigestResult;
    }

    /**
     * Sets the value of the invoiceChainDigestResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link InvoiceChainDigestResultType }
     *     
     */
    public void setInvoiceChainDigestResult(InvoiceChainDigestResultType value) {
        this.invoiceChainDigestResult = value;
    }

}
