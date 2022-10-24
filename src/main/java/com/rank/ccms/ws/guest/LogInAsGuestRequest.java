
package com.rank.ccms.ws.guest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
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
 *         &lt;element name="roomKey" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="guestName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="clientType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="returnEndpointBehavior" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;any minOccurs="0"/>
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
    "roomKey",
    "guestName",
    "clientType",
    "returnEndpointBehavior",
    "any"
})
@XmlRootElement(name = "LogInAsGuestRequest")
public class LogInAsGuestRequest {

    @XmlElement(required = true)
    protected String roomKey;
    @XmlElement(required = true)
    protected String guestName;
    protected String clientType;
    @XmlElement(defaultValue = "false")
    protected Boolean returnEndpointBehavior;
    @XmlAnyElement(lax = true)
    protected Object any;

    /**
     * Gets the value of the roomKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoomKey() {
        return roomKey;
    }

    /**
     * Sets the value of the roomKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoomKey(String value) {
        this.roomKey = value;
    }

    /**
     * Gets the value of the guestName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGuestName() {
        return guestName;
    }

    /**
     * Sets the value of the guestName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGuestName(String value) {
        this.guestName = value;
    }

    /**
     * Gets the value of the clientType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClientType() {
        return clientType;
    }

    /**
     * Sets the value of the clientType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClientType(String value) {
        this.clientType = value;
    }

    /**
     * Gets the value of the returnEndpointBehavior property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isReturnEndpointBehavior() {
        return returnEndpointBehavior;
    }

    /**
     * Sets the value of the returnEndpointBehavior property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setReturnEndpointBehavior(Boolean value) {
        this.returnEndpointBehavior = value;
    }

    /**
     * Gets the value of the any property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getAny() {
        return any;
    }

    /**
     * Sets the value of the any property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setAny(Object value) {
        this.any = value;
    }

}
