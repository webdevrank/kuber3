
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
 *         &lt;element ref="{http://portal.vidyo.com/user/v1_1}ClientType" minOccurs="0"/>
 *         &lt;element name="returnEndpointBehavior" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="returnAuthToken" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="returnPortalVersion" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="returnServiceAddress" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="returnNeoRoomPermanentPairingDeviceUserAttribute" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="returnUserRole" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
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
    "clientType",
    "returnEndpointBehavior",
    "returnAuthToken",
    "returnPortalVersion",
    "returnServiceAddress",
    "returnNeoRoomPermanentPairingDeviceUserAttribute",
    "returnUserRole"
})
@XmlRootElement(name = "LogInRequest")
public class LogInRequest {

    @XmlElement(name = "ClientType")
    protected String clientType;
    @XmlElement(defaultValue = "false")
    protected Boolean returnEndpointBehavior;
    @XmlElement(defaultValue = "false")
    protected Boolean returnAuthToken;
    @XmlElement(defaultValue = "false")
    protected Boolean returnPortalVersion;
    @XmlElement(defaultValue = "false")
    protected Boolean returnServiceAddress;
    @XmlElement(defaultValue = "false")
    protected Boolean returnNeoRoomPermanentPairingDeviceUserAttribute;
    @XmlElement(defaultValue = "false")
    protected Boolean returnUserRole;

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
     * Gets the value of the returnAuthToken property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isReturnAuthToken() {
        return returnAuthToken;
    }

    /**
     * Sets the value of the returnAuthToken property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setReturnAuthToken(Boolean value) {
        this.returnAuthToken = value;
    }

    /**
     * Gets the value of the returnPortalVersion property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isReturnPortalVersion() {
        return returnPortalVersion;
    }

    /**
     * Sets the value of the returnPortalVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setReturnPortalVersion(Boolean value) {
        this.returnPortalVersion = value;
    }

    /**
     * Gets the value of the returnServiceAddress property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isReturnServiceAddress() {
        return returnServiceAddress;
    }

    /**
     * Sets the value of the returnServiceAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setReturnServiceAddress(Boolean value) {
        this.returnServiceAddress = value;
    }

    /**
     * Gets the value of the returnNeoRoomPermanentPairingDeviceUserAttribute property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isReturnNeoRoomPermanentPairingDeviceUserAttribute() {
        return returnNeoRoomPermanentPairingDeviceUserAttribute;
    }

    /**
     * Sets the value of the returnNeoRoomPermanentPairingDeviceUserAttribute property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setReturnNeoRoomPermanentPairingDeviceUserAttribute(Boolean value) {
        this.returnNeoRoomPermanentPairingDeviceUserAttribute = value;
    }

    /**
     * Gets the value of the returnUserRole property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isReturnUserRole() {
        return returnUserRole;
    }

    /**
     * Sets the value of the returnUserRole property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setReturnUserRole(Boolean value) {
        this.returnUserRole = value;
    }

}
