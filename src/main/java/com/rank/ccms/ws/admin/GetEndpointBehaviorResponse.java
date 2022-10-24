
package com.rank.ccms.ws.admin;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 *         &lt;element name="endpointBehavior" type="{http://portal.vidyo.com/admin/v1_1}EndpointBehaviorDataType"/>
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
    "endpointBehavior"
})
@XmlRootElement(name = "getEndpointBehaviorResponse")
public class GetEndpointBehaviorResponse {

    @XmlElement(required = true)
    protected EndpointBehaviorDataType endpointBehavior;

    /**
     * Gets the value of the endpointBehavior property.
     * 
     * @return
     *     possible object is
     *     {@link EndpointBehaviorDataType }
     *     
     */
    public EndpointBehaviorDataType getEndpointBehavior() {
        return endpointBehavior;
    }

    /**
     * Sets the value of the endpointBehavior property.
     * 
     * @param value
     *     allowed object is
     *     {@link EndpointBehaviorDataType }
     *     
     */
    public void setEndpointBehavior(EndpointBehaviorDataType value) {
        this.endpointBehavior = value;
    }

}
