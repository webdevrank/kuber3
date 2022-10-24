
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
 *         &lt;element name="moderatorURL" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "moderatorURL"
})
@XmlRootElement(name = "GetModeratorURLWithTokenResponse")
public class GetModeratorURLWithTokenResponse {

    @XmlElement(required = true, nillable = true)
    protected String moderatorURL;

    /**
     * Gets the value of the moderatorURL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModeratorURL() {
        return moderatorURL;
    }

    /**
     * Sets the value of the moderatorURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModeratorURL(String value) {
        this.moderatorURL = value;
    }

}
