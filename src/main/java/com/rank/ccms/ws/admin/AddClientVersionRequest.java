
package com.rank.ccms.ws.admin;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="clientType" type="{http://portal.vidyo.com/admin/v1_1}ClientType"/>
 *         &lt;element name="currentTag" type="{http://portal.vidyo.com/admin/v1_1}EndpointVersionPattern"/>
 *         &lt;element name="installerURL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="setActive" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
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
    "currentTag",
    "installerURL",
    "setActive"
})
@XmlRootElement(name = "addClientVersionRequest")
public class AddClientVersionRequest {

    @XmlElement(required = true)
    protected ClientType clientType;
    @XmlElement(required = true)
    protected String currentTag;
    @XmlElement(required = true)
    protected String installerURL;
    @XmlElementRef(name = "setActive", namespace = "http://portal.vidyo.com/admin/v1_1", type = JAXBElement.class, required = false)
    protected JAXBElement<Boolean> setActive;

    /**
     * Gets the value of the clientType property.
     * 
     * @return
     *     possible object is
     *     {@link ClientType }
     *     
     */
    public ClientType getClientType() {
        return clientType;
    }

    /**
     * Sets the value of the clientType property.
     * 
     * @param value
     *     allowed object is
     *     {@link ClientType }
     *     
     */
    public void setClientType(ClientType value) {
        this.clientType = value;
    }

    /**
     * Gets the value of the currentTag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrentTag() {
        return currentTag;
    }

    /**
     * Sets the value of the currentTag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrentTag(String value) {
        this.currentTag = value;
    }

    /**
     * Gets the value of the installerURL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstallerURL() {
        return installerURL;
    }

    /**
     * Sets the value of the installerURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstallerURL(String value) {
        this.installerURL = value;
    }

    /**
     * Gets the value of the setActive property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     *     
     */
    public JAXBElement<Boolean> getSetActive() {
        return setActive;
    }

    /**
     * Sets the value of the setActive property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     *     
     */
    public void setSetActive(JAXBElement<Boolean> value) {
        this.setActive = value;
    }

}
