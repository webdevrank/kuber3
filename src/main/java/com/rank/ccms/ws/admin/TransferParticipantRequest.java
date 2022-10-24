
package com.rank.ccms.ws.admin;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="participantID" type="{http://portal.vidyo.com/admin/v1_1}EntityID"/>
 *         &lt;element name="conferenceID" type="{http://portal.vidyo.com/admin/v1_1}EntityID"/>
 *         &lt;element name="roomPIN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "participantID",
    "conferenceID",
    "roomPIN"
})
@XmlRootElement(name = "TransferParticipantRequest")
public class TransferParticipantRequest {

    protected int participantID;
    protected int conferenceID;
    @XmlElementRef(name = "roomPIN", namespace = "http://portal.vidyo.com/admin/v1_1", type = JAXBElement.class, required = false)
    protected JAXBElement<String> roomPIN;

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
     * Gets the value of the conferenceID property.
     * 
     */
    public int getConferenceID() {
        return conferenceID;
    }

    /**
     * Sets the value of the conferenceID property.
     * 
     */
    public void setConferenceID(int value) {
        this.conferenceID = value;
    }

    /**
     * Gets the value of the roomPIN property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getRoomPIN() {
        return roomPIN;
    }

    /**
     * Sets the value of the roomPIN property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRoomPIN(JAXBElement<String> value) {
        this.roomPIN = value;
    }

}
