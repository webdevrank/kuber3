
package com.rank.ccms.ws.user;

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
 *         &lt;element name="conferenceID" type="{http://portal.vidyo.com/user/v1_1}EntityID"/>
 *         &lt;choice>
 *           &lt;element name="entityID" type="{http://portal.vidyo.com/user/v1_1}EntityID"/>
 *           &lt;element name="invite" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;/choice>
 *         &lt;element name="moderatorPIN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="callFromIdentifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "conferenceID",
    "entityID",
    "invite",
    "moderatorPIN",
    "callFromIdentifier"
})
@XmlRootElement(name = "InviteToConferenceRequest")
public class InviteToConferenceRequest {

    @XmlElement(required = true)
    protected String conferenceID;
    protected String entityID;
    protected String invite;
    @XmlElementRef(name = "moderatorPIN", namespace = "http://portal.vidyo.com/user/v1_1", type = JAXBElement.class, required = false)
    protected JAXBElement<String> moderatorPIN;
    protected String callFromIdentifier;

    /**
     * Gets the value of the conferenceID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConferenceID() {
        return conferenceID;
    }

    /**
     * Sets the value of the conferenceID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConferenceID(String value) {
        this.conferenceID = value;
    }

    /**
     * Gets the value of the entityID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntityID() {
        return entityID;
    }

    /**
     * Sets the value of the entityID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntityID(String value) {
        this.entityID = value;
    }

    /**
     * Gets the value of the invite property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInvite() {
        return invite;
    }

    /**
     * Sets the value of the invite property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInvite(String value) {
        this.invite = value;
    }

    /**
     * Gets the value of the moderatorPIN property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getModeratorPIN() {
        return moderatorPIN;
    }

    /**
     * Sets the value of the moderatorPIN property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setModeratorPIN(JAXBElement<String> value) {
        this.moderatorPIN = value;
    }

    /**
     * Gets the value of the callFromIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCallFromIdentifier() {
        return callFromIdentifier;
    }

    /**
     * Sets the value of the callFromIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCallFromIdentifier(String value) {
        this.callFromIdentifier = value;
    }

}
