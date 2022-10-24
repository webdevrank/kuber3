
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
 *         &lt;element name="entityID" type="{http://portal.vidyo.com/admin/v1_1}EntityID"/>
 *         &lt;element name="participantID" type="{http://portal.vidyo.com/admin/v1_1}EntityID"/>
 *         &lt;element ref="{http://portal.vidyo.com/admin/v1_1}EntityType"/>
 *         &lt;element name="displayName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="extension" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="audio" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="video" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="appshare" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="handRaised" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="presenter" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
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
    "entityID",
    "participantID",
    "entityType",
    "displayName",
    "extension",
    "audio",
    "video",
    "appshare",
    "handRaised",
    "presenter"
})
@XmlRootElement(name = "LectureModeParticipant")
public class LectureModeParticipant {

    protected int entityID;
    protected int participantID;
    @XmlElement(name = "EntityType", required = true)
    protected String entityType;
    @XmlElement(required = true)
    protected String displayName;
    @XmlElement(required = true)
    protected String extension;
    @XmlElementRef(name = "audio", namespace = "http://portal.vidyo.com/admin/v1_1", type = JAXBElement.class, required = false)
    protected JAXBElement<Boolean> audio;
    @XmlElementRef(name = "video", namespace = "http://portal.vidyo.com/admin/v1_1", type = JAXBElement.class, required = false)
    protected JAXBElement<Boolean> video;
    @XmlElementRef(name = "appshare", namespace = "http://portal.vidyo.com/admin/v1_1", type = JAXBElement.class, required = false)
    protected JAXBElement<Boolean> appshare;
    @XmlElementRef(name = "handRaised", namespace = "http://portal.vidyo.com/admin/v1_1", type = JAXBElement.class, required = false)
    protected JAXBElement<Boolean> handRaised;
    @XmlElementRef(name = "presenter", namespace = "http://portal.vidyo.com/admin/v1_1", type = JAXBElement.class, required = false)
    protected JAXBElement<Boolean> presenter;

    /**
     * Gets the value of the entityID property.
     * 
     */
    public int getEntityID() {
        return entityID;
    }

    /**
     * Sets the value of the entityID property.
     * 
     */
    public void setEntityID(int value) {
        this.entityID = value;
    }

    /**
     * Gets the value of the participantID property.
     * 
     */
    public int getParticipantID() {
        return participantID;
    }

    /**
     * Sets the value of the participantID property.
     * 
     */
    public void setParticipantID(int value) {
        this.participantID = value;
    }

    /**
     * Gets the value of the entityType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntityType() {
        return entityType;
    }

    /**
     * Sets the value of the entityType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntityType(String value) {
        this.entityType = value;
    }

    /**
     * Gets the value of the displayName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Sets the value of the displayName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisplayName(String value) {
        this.displayName = value;
    }

    /**
     * Gets the value of the extension property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtension() {
        return extension;
    }

    /**
     * Sets the value of the extension property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtension(String value) {
        this.extension = value;
    }

    /**
     * Gets the value of the audio property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     *     
     */
    public JAXBElement<Boolean> getAudio() {
        return audio;
    }

    /**
     * Sets the value of the audio property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     *     
     */
    public void setAudio(JAXBElement<Boolean> value) {
        this.audio = value;
    }

    /**
     * Gets the value of the video property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     *     
     */
    public JAXBElement<Boolean> getVideo() {
        return video;
    }

    /**
     * Sets the value of the video property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     *     
     */
    public void setVideo(JAXBElement<Boolean> value) {
        this.video = value;
    }

    /**
     * Gets the value of the appshare property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     *     
     */
    public JAXBElement<Boolean> getAppshare() {
        return appshare;
    }

    /**
     * Sets the value of the appshare property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     *     
     */
    public void setAppshare(JAXBElement<Boolean> value) {
        this.appshare = value;
    }

    /**
     * Gets the value of the handRaised property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     *     
     */
    public JAXBElement<Boolean> getHandRaised() {
        return handRaised;
    }

    /**
     * Sets the value of the handRaised property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     *     
     */
    public void setHandRaised(JAXBElement<Boolean> value) {
        this.handRaised = value;
    }

    /**
     * Gets the value of the presenter property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     *     
     */
    public JAXBElement<Boolean> getPresenter() {
        return presenter;
    }

    /**
     * Sets the value of the presenter property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Boolean }{@code >}
     *     
     */
    public void setPresenter(JAXBElement<Boolean> value) {
        this.presenter = value;
    }

}
