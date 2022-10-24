
package com.rank.ccms.ws.user;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
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
 *         &lt;element name="pak" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="vmaddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="proxyaddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="loctag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pak2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="endpointExternalIPAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="minimumPINLength" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="maximumPINLength" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="minMediaPort" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="maxMediaPort" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="vrProxyConfig" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="endpointBehavior" type="{http://portal.vidyo.com/user/v1_1}EndpointBehaviorDataType" minOccurs="0"/>
 *         &lt;element name="authToken" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="portalVersion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pairingService" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *         &lt;element name="registrationService" type="{http://www.w3.org/2001/XMLSchema}anyURI" minOccurs="0"/>
 *         &lt;element name="neoRoomPermanentPairingDeviceUser" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="userRole" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "pak",
    "vmaddress",
    "proxyaddress",
    "loctag",
    "pak2",
    "endpointExternalIPAddress",
    "minimumPINLength",
    "maximumPINLength",
    "minMediaPort",
    "maxMediaPort",
    "vrProxyConfig",
    "endpointBehavior",
    "authToken",
    "portalVersion",
    "pairingService",
    "registrationService",
    "neoRoomPermanentPairingDeviceUser",
    "userRole"
})
@XmlRootElement(name = "LogInResponse")
public class LogInResponse {

    @XmlElement(required = true)
    protected String pak;
    @XmlElementRef(name = "vmaddress", namespace = "http://portal.vidyo.com/user/v1_1", type = JAXBElement.class, required = false)
    protected JAXBElement<String> vmaddress;
    @XmlElementRef(name = "proxyaddress", namespace = "http://portal.vidyo.com/user/v1_1", type = JAXBElement.class, required = false)
    protected JAXBElement<String> proxyaddress;
    @XmlElementRef(name = "loctag", namespace = "http://portal.vidyo.com/user/v1_1", type = JAXBElement.class, required = false)
    protected JAXBElement<String> loctag;
    @XmlElementRef(name = "pak2", namespace = "http://portal.vidyo.com/user/v1_1", type = JAXBElement.class, required = false)
    protected JAXBElement<String> pak2;
    protected String endpointExternalIPAddress;
    protected Integer minimumPINLength;
    protected Integer maximumPINLength;
    protected Integer minMediaPort;
    protected Integer maxMediaPort;
    protected String vrProxyConfig;
    protected EndpointBehaviorDataType endpointBehavior;
    protected String authToken;
    protected String portalVersion;
    @XmlSchemaType(name = "anyURI")
    protected String pairingService;
    @XmlSchemaType(name = "anyURI")
    protected String registrationService;
    protected Boolean neoRoomPermanentPairingDeviceUser;
    protected String userRole;

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
     * Gets the value of the pak2 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPak2() {
        return pak2;
    }

    /**
     * Sets the value of the pak2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPak2(JAXBElement<String> value) {
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
     * Gets the value of the minimumPINLength property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMinimumPINLength() {
        return minimumPINLength;
    }

    /**
     * Sets the value of the minimumPINLength property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMinimumPINLength(Integer value) {
        this.minimumPINLength = value;
    }

    /**
     * Gets the value of the maximumPINLength property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMaximumPINLength() {
        return maximumPINLength;
    }

    /**
     * Sets the value of the maximumPINLength property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMaximumPINLength(Integer value) {
        this.maximumPINLength = value;
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

    /**
     * Gets the value of the authToken property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthToken() {
        return authToken;
    }

    /**
     * Sets the value of the authToken property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthToken(String value) {
        this.authToken = value;
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
     * Gets the value of the pairingService property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPairingService() {
        return pairingService;
    }

    /**
     * Sets the value of the pairingService property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPairingService(String value) {
        this.pairingService = value;
    }

    /**
     * Gets the value of the registrationService property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegistrationService() {
        return registrationService;
    }

    /**
     * Sets the value of the registrationService property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegistrationService(String value) {
        this.registrationService = value;
    }

    /**
     * Gets the value of the neoRoomPermanentPairingDeviceUser property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isNeoRoomPermanentPairingDeviceUser() {
        return neoRoomPermanentPairingDeviceUser;
    }

    /**
     * Sets the value of the neoRoomPermanentPairingDeviceUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setNeoRoomPermanentPairingDeviceUser(Boolean value) {
        this.neoRoomPermanentPairingDeviceUser = value;
    }

    /**
     * Gets the value of the userRole property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserRole() {
        return userRole;
    }

    /**
     * Sets the value of the userRole property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserRole(String value) {
        this.userRole = value;
    }

}
