
package com.rank.ccms.ws.guest;

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
 *         &lt;element name="endpointExternalIPAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "endpointExternalIPAddress"
})
@XmlRootElement(name = "WhatIsMyIPAddressResponse")
public class WhatIsMyIPAddressResponse {

    @XmlElement(required = true)
    protected String endpointExternalIPAddress;

    /**
     * Gets the value of the endpointExternalIPAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndpointExternalIPAddress() {
        return endpointExternalIPAddress;
    }

    /**
     * Sets the value of the endpointExternalIPAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndpointExternalIPAddress(String value) {
        this.endpointExternalIPAddress = value;
    }

}
