//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.12.09 at 03:22:27 PM CET 
//


package hu.gov.nav.schemas.osa._3_0.data;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Product / service items
 * 
 * <p>Java class for LinesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LinesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="mergedItemIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="line" type="{http://schemas.nav.gov.hu/OSA/3.0/data}LineType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LinesType", propOrder = {
    "mergedItemIndicator",
    "line"
})
public class LinesType {

    protected boolean mergedItemIndicator;
    @XmlElement(required = true)
    protected List<LineType> line;

    /**
     * Gets the value of the mergedItemIndicator property.
     * 
     */
    public boolean isMergedItemIndicator() {
        return mergedItemIndicator;
    }

    /**
     * Sets the value of the mergedItemIndicator property.
     * 
     */
    public void setMergedItemIndicator(boolean value) {
        this.mergedItemIndicator = value;
    }

    /**
     * Gets the value of the line property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the line property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLine().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LineType }
     * 
     * 
     */
    public List<LineType> getLine() {
        if (line == null) {
            line = new ArrayList<LineType>();
        }
        return this.line;
    }

}
