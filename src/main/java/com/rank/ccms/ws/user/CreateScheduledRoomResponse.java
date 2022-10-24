
package com.rank.ccms.ws.user;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 *         &lt;element name="extension">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="pin" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="inviteContent" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="roomURL" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
 *         &lt;element name="inviteSubject" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="htmlContent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element ref="{http://portal.vidyo.com/user/v1_1}Room" minOccurs="0"/>
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
    "extension",
    "pin",
    "inviteContent",
    "roomURL",
    "inviteSubject",
    "htmlContent",
    "room"
})
@XmlRootElement(name = "CreateScheduledRoomResponse")
public class CreateScheduledRoomResponse {

    @XmlElement(required = true)
    protected String extension;
    protected String pin;
    @XmlElement(required = true)
    protected String inviteContent;
    @XmlElement(required = true)
    @XmlSchemaType(name = "anyURI")
    protected String roomURL;
    protected String inviteSubject;
    protected String htmlContent;
    @XmlElement(name = "Room")
    protected Room room;

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
     * Gets the value of the pin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPin() {
        return pin;
    }

    /**
     * Sets the value of the pin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPin(String value) {
        this.pin = value;
    }

    /**
     * Gets the value of the inviteContent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInviteContent() {
        return inviteContent;
    }

    /**
     * Sets the value of the inviteContent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInviteContent(String value) {
        this.inviteContent = value;
    }

    /**
     * Gets the value of the roomURL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoomURL() {
        return roomURL;
    }

    /**
     * Sets the value of the roomURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoomURL(String value) {
        this.roomURL = value;
    }

    /**
     * Gets the value of the inviteSubject property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInviteSubject() {
        return inviteSubject;
    }

    /**
     * Sets the value of the inviteSubject property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInviteSubject(String value) {
        this.inviteSubject = value;
    }

    /**
     * Gets the value of the htmlContent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHtmlContent() {
        return htmlContent;
    }

    /**
     * Sets the value of the htmlContent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHtmlContent(String value) {
        this.htmlContent = value;
    }

    /**
     * Gets the value of the room property.
     * 
     * @return
     *     possible object is
     *     {@link Room }
     *     
     */
    public Room getRoom() {
        return room;
    }

    /**
     * Sets the value of the room property.
     * 
     * @param value
     *     allowed object is
     *     {@link Room }
     *     
     */
    public void setRoom(Room value) {
        this.room = value;
    }

}
