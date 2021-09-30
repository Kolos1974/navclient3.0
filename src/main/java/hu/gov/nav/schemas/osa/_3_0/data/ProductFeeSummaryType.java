//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.12.09 at 03:22:27 PM CET 
//


package hu.gov.nav.schemas.osa._3_0.data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * Summary of product charges
 * 
 * <p>Java class for ProductFeeSummaryType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProductFeeSummaryType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="productFeeOperation" type="{http://schemas.nav.gov.hu/OSA/3.0/data}ProductFeeOperationType"/>
 *         &lt;element name="productFeeData" type="{http://schemas.nav.gov.hu/OSA/3.0/data}ProductFeeDataType" maxOccurs="unbounded"/>
 *         &lt;element name="productChargeSum" type="{http://schemas.nav.gov.hu/OSA/3.0/base}MonetaryType"/>
 *         &lt;element name="paymentEvidenceDocumentData" type="{http://schemas.nav.gov.hu/OSA/3.0/data}PaymentEvidenceDocumentDataType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProductFeeSummaryType", propOrder = {
    "productFeeOperation",
    "productFeeData",
    "productChargeSum",
    "paymentEvidenceDocumentData"
})
public class ProductFeeSummaryType {

    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected ProductFeeOperationType productFeeOperation;
    @XmlElement(required = true)
    protected List<ProductFeeDataType> productFeeData;
    @XmlElement(required = true)
    protected BigDecimal productChargeSum;
    protected PaymentEvidenceDocumentDataType paymentEvidenceDocumentData;

    /**
     * Gets the value of the productFeeOperation property.
     * 
     * @return
     *     possible object is
     *     {@link ProductFeeOperationType }
     *     
     */
    public ProductFeeOperationType getProductFeeOperation() {
        return productFeeOperation;
    }

    /**
     * Sets the value of the productFeeOperation property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductFeeOperationType }
     *     
     */
    public void setProductFeeOperation(ProductFeeOperationType value) {
        this.productFeeOperation = value;
    }

    /**
     * Gets the value of the productFeeData property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the productFeeData property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProductFeeData().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProductFeeDataType }
     * 
     * 
     */
    public List<ProductFeeDataType> getProductFeeData() {
        if (productFeeData == null) {
            productFeeData = new ArrayList<ProductFeeDataType>();
        }
        return this.productFeeData;
    }

    /**
     * Gets the value of the productChargeSum property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getProductChargeSum() {
        return productChargeSum;
    }

    /**
     * Sets the value of the productChargeSum property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setProductChargeSum(BigDecimal value) {
        this.productChargeSum = value;
    }

    /**
     * Gets the value of the paymentEvidenceDocumentData property.
     * 
     * @return
     *     possible object is
     *     {@link PaymentEvidenceDocumentDataType }
     *     
     */
    public PaymentEvidenceDocumentDataType getPaymentEvidenceDocumentData() {
        return paymentEvidenceDocumentData;
    }

    /**
     * Sets the value of the paymentEvidenceDocumentData property.
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentEvidenceDocumentDataType }
     *     
     */
    public void setPaymentEvidenceDocumentData(PaymentEvidenceDocumentDataType value) {
        this.paymentEvidenceDocumentData = value;
    }

}
