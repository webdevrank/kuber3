
package com.rank.ccms.ws.admin;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="endpointBehaviorKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "endpointBehaviorKey"
})
@XmlRootElement(name = "deleteEndpointBehaviorRequest")
public class DeleteEndpointBehaviorRequest {

    protected String endpointBehaviorKey;

    /**
     * Gets the value of the endpointBehaviorKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndpointBehaviorKey() {
        return endpointBehaviorKey;
    }

    /**
     * Sets the value of the endpointBehaviorKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndpointBehaviorKey(String value) {
        this.endpointBehaviorKey = value;
    }

}
