//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.12.09 at 03:22:16 PM CET 
//


package hu.gov.nav.schemas.osa._3_0.api;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import hu.gov.nav.schemas.ntca._1_0.common.TechnicalValidationResultType;


/**
 * Invoice processing result
 * 
 * <p>Java class for ProcessingResultType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProcessingResultType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="index" type="{http://schemas.nav.gov.hu/OSA/3.0/base}InvoiceIndexType"/>
 *         &lt;element name="batchIndex" type="{http://schemas.nav.gov.hu/OSA/3.0/base}InvoiceUnboundedIndexType" minOccurs="0"/>
 *         &lt;element name="invoiceStatus" type="{http://schemas.nav.gov.hu/OSA/3.0/api}InvoiceStatusType"/>
 *         &lt;element name="technicalValidationMessages" type="{http://schemas.nav.gov.hu/NTCA/1.0/common}TechnicalValidationResultType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="businessValidationMessages" type="{http://schemas.nav.gov.hu/OSA/3.0/api}BusinessValidationResultType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="compressedContentIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="originalRequest" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProcessingResultType", propOrder = {
    "index",
    "batchIndex",
    "invoiceStatus",
    "technicalValidationMessages",
    "businessValidationMessages",
    "compressedContentIndicator",
    "originalRequest"
})
public class ProcessingResultType {

    protected int index;
    protected Integer batchIndex;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected InvoiceStatusType invoiceStatus;
    protected List<TechnicalValidationResultType> technicalValidationMessages;
    protected List<BusinessValidationResultType> businessValidationMessages;
    protected boolean compressedContentIndicator;
    protected byte[] originalRequest;

    /**
     * Gets the value of the index property.
     * 
     */
    public int getIndex() {
        return index;
    }

    /**
     * Sets the value of the index property.
     * 
     */
    public void setIndex(int value) {
        this.index = value;
    }

    /**
     * Gets the value of the batchIndex property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getBatchIndex() {
        return batchIndex;
    }

    /**
     * Sets the value of the batchIndex property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setBatchIndex(Integer value) {
        this.batchIndex = value;
    }

    /**
     * Gets the value of the invoiceStatus property.
     * 
     * @return
     *     possible object is
     *     {@link InvoiceStatusType }
     *     
     */
    public InvoiceStatusType getInvoiceStatus() {
        return invoiceStatus;
    }

    /**
     * Sets the value of the invoiceStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link InvoiceStatusType }
     *     
     */
    public void setInvoiceStatus(InvoiceStatusType value) {
        this.invoiceStatus = value;
    }

    /**
     * Gets the value of the technicalValidationMessages property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the technicalValidationMessages property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTechnicalValidationMessages().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TechnicalValidationResultType }
     * 
     * 
     */
    public List<TechnicalValidationResultType> getTechnicalValidationMessages() {
        if (technicalValidationMessages == null) {
            technicalValidationMessages = new ArrayList<TechnicalValidationResultType>();
        }
        return this.technicalValidationMessages;
    }

    /**
     * Gets the value of the businessValidationMessages property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the businessValidationMessages property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBusinessValidationMessages().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BusinessValidationResultType }
     * 
     * 
     */
    public List<BusinessValidationResultType> getBusinessValidationMessages() {
        if (businessValidationMessages == null) {
            businessValidationMessages = new ArrayList<BusinessValidationResultType>();
        }
        return this.businessValidationMessages;
    }

    /**
     * Gets the value of the compressedContentIndicator property.
     * 
     */
    public boolean isCompressedContentIndicator() {
        return compressedContentIndicator;
    }

    /**
     * Sets the value of the compressedContentIndicator property.
     * 
     */
    public void setCompressedContentIndicator(boolean value) {
        this.compressedContentIndicator = value;
    }

    /**
     * Gets the value of the originalRequest property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getOriginalRequest() {
        return originalRequest;
    }

    /**
     * Sets the value of the originalRequest property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setOriginalRequest(byte[] value) {
        this.originalRequest = value;
    }

}
