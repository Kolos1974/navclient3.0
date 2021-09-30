//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2020.12.09 at 03:22:41 PM CET 
//


package hu.gov.nav.schemas.osa._3_0.metrics;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * Response type of the GET /queryServiceMetrics/list REST operation
 * 
 * <p>Java class for QueryServiceMetricsListResponseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="QueryServiceMetricsListResponseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="metricDefinition" type="{http://schemas.nav.gov.hu/OSA/3.0/metrics}MetricDefinitionType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QueryServiceMetricsListResponseType", propOrder = {
    "metricDefinition"
})
@XmlSeeAlso({
    QueryServiceMetricsListResponse.class
})
public class QueryServiceMetricsListResponseType {

    protected List<MetricDefinitionType> metricDefinition;

    /**
     * Gets the value of the metricDefinition property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the metricDefinition property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMetricDefinition().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MetricDefinitionType }
     * 
     * 
     */
    public List<MetricDefinitionType> getMetricDefinition() {
        if (metricDefinition == null) {
            metricDefinition = new ArrayList<MetricDefinitionType>();
        }
        return this.metricDefinition;
    }

}
