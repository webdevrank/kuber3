
package com.rank.ccms.ws.guest;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
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
 *         &lt;element name="guestID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="isLocked" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="hasPin" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="vmaddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="proxyaddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="loctag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="un" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="pak" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="portal" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="portalVersion" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;any minOccurs="0"/>
 *         &lt;element name="pak2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="endpointExternalIPAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="roomName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="roomDisplayName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="minMediaPort" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="maxMediaPort" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="vrProxyConfig" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="endpointBehavior" type="{http://portal.vidyo.com/guest}EndpointBehaviorDataType" minOccurs="0"/>
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
    "guestID",
    "isLocked",
    "hasPin",
    "vmaddress",
    "proxyaddress",
    "loctag",
    "un",
    "pak",
    "portal",
    "portalVersion",
    "any",
    "pak2",
    "endpointExternalIPAddress",
    "roomName",
    "roomDisplayName",
    "minMediaPort",
    "maxMediaPort",
    "vrProxyConfig",
    "endpointBehavior"
})
@XmlRootElement(name = "LogInAsGuestResponse")
public class LogInAsGuestResponse {

    protected int guestID;
    protected boolean isLocked;
    protected boolean hasPin;
    @XmlElementRef(name = "vmaddress", namespace = "http://portal.vidyo.com/guest", type = JAXBElement.class, required = false)
    protected JAXBElement<String> vmaddress;
    @XmlElementRef(name = "proxyaddress", namespace = "http://portal.vidyo.com/guest", type = JAXBElement.class, required = false)
    protected JAXBElement<String> proxyaddress;
    @XmlElementRef(name = "loctag", namespace = "http://portal.vidyo.com/guest", type = JAXBElement.class, required = false)
    protected JAXBElement<String> loctag;
    @XmlElement(required = true)
    protected String un;
    @XmlElement(required = true)
    protected String pak;
    @XmlElement(required = true)
    protected String portal;
    @XmlElement(required = true)
    protected String portalVersion;
    @XmlAnyElement(lax = true)
    protected Object any;
    protected String pak2;
    protected String endpointExternalIPAddress;
    protected String roomName;
    protected String roomDisplayName;
    protected Integer minMediaPort;
    protected Integer maxMediaPort;
    protected String vrProxyConfig;
    protected EndpointBehaviorDataType endpointBehavior;

    /**
     * Gets the value of the guestID property.
     * 
     */
    public int getGuestID() {
        return guestID;
    }

    /**
     * Sets the value of the guestID property.
     * 
     */
    public void setGuestID(int value) {
        this.guestID = value;
    }

    /**
     * Gets the value of the isLocked property.
     * 
     */
    public boolean isIsLocked() {
        return isLocked;
    }

    /**
     * Sets the value of the isLocked property.
     * 
     */
    public void setIsLocked(boolean value) {
        this.isLocked = value;
    }

    /**
     * Gets the value of the hasPin property.
     * 
     */
    public boolean isHasPin() {
        return hasPin;
    }

    /**
     * Sets the value of the hasPin property.
     * 
     */
    public void setHasPin(boolean value) {
        this.hasPin = value;
    }

    /**
     * Gets the value of the vmaddress property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getVmaddress() {
        return vmaddress;
    }

    /**
     * Sets the value of the vmaddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setVmaddress(JAXBElement<String> value) {
        this.vmaddress = value;
    }

    /**
     * Gets the value of the proxyaddress property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getProxyaddress() {
        return proxyaddress;
    }

    /**
     * Sets the value of the proxyaddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setProxyaddress(JAXBElement<String> value) {
        this.proxyaddress = value;
    }

    /**
     * Gets the value of the loctag property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getLoctag() {
        return loctag;
    }

    /**
     * Sets the value of the loctag property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setLoctag(JAXBElement<String> value) {
        this.loctag = value;
    }

    /**
     * Gets the value of the un property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUn() {
        return un;
    }

    /**
     * Sets the value of the un property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUn(String value) {
        this.un = value;
    }

    /**
     * Gets the value of the pak property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPak() {
        return pak;
    }

    /**
     * Sets the value of the pak property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPak(String value) {
        this.pak = value;
    }

    /**
     * Gets the value of the portal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPortal() {
        return portal;
    }

    /**
     * Sets the value of the portal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPortal(String value) {
        this.portal = value;
    }

    /**
     * Gets the value of the portalVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPortalVersion() {
        return portalVersion;
    }

    /**
     * Sets the value of the portalVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPortalVersion(String value) {
        this.portalVersion = value;
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

    /**
     * Gets the value of the pak2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPak2() {
        return pak2;
    }

    /**
     * Sets the value of the pak2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPak2(String value) {
        this.pak2 = value;
    }

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

    /**
     * Gets the value of the roomName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     * Sets the value of the roomName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoomName(String value) {
        this.roomName = value;
    }

    /**
     * Gets the value of the roomDisplayName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoomDisplayName() {
        return roomDisplayName;
    }

    /**
     * Sets the value of the roomDisplayName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoomDisplayName(String value) {
        this.roomDisplayName = value;
    }

    /**
     * Gets the value of the minMediaPort property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMinMediaPort() {
        return minMediaPort;
    }

    /**
     * Sets the value of the minMediaPort property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMinMediaPort(Integer value) {
        this.minMediaPort = value;
    }

    /**
     * Gets the value of the maxMediaPort property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMaxMediaPort() {
        return maxMediaPort;
    }

    /**
     * Sets the value of the maxMediaPort property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMaxMediaPort(Integer value) {
        this.maxMediaPort = value;
    }

    /**
     * Gets the value of the vrProxyConfig property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVrProxyConfig() {
        return vrProxyConfig;
    }

    /**
     * Sets the value of the vrProxyConfig property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVrProxyConfig(String value) {
        this.vrProxyConfig = value;
    }

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
