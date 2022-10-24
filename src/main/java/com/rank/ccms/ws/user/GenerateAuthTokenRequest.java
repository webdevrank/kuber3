
package com.rank.ccms.ws.user;

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
 *         &lt;element name="validityTime">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *               &lt;minInclusive value="0"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="endpointId" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "validityTime",
    "endpointId"
})
@XmlRootElement(name = "GenerateAuthTokenRequest")
public class GenerateAuthTokenRequest {

    protected int validityTime;
    @XmlElement(required = true)
    protected String endpointId;

    /**
     * Gets the value of the validityTime property.
     * 
     */
    public int getValidityTime() {
        return validityTime;
    }

    /**
     * Sets the value of the validityTime property.
     * 
     */
    public void setValidityTime(int value) {
        this.validityTime = value;
    }

    /**
     * Gets the value of the endpointId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndpointId() {
        return endpointId;
    }

    /**
     * Sets the value of the endpointId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndpointId(String value) {
        this.endpointId = value;
    }

}
