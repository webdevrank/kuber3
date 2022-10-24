
package com.rank.ccms.ws.user;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SilenceSpeakerServerType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SilenceSpeakerServerType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="conferenceID" type="{http://portal.vidyo.com/user/v1_1}EntityID"/>
 *         &lt;element name="participantID" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/>
 *         &lt;element name="silenceState" type="{http://portal.vidyo.com/user/v1_1}SilenceState"/>
 *         &lt;element name="moderatorPIN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SilenceSpeakerServerType", propOrder = {
    "conferenceID",
    "participantID",
    "silenceState",
    "moderatorPIN"
})
public class SilenceSpeakerServerType {

    @XmlElement(required = true)
    protected String conferenceID;
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger participantID;
    protected int silenceState;
    protected String moderatorPIN;

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
     * Gets the value of the participantID property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getParticipantID() {
        return participantID;
    }

    /**
     * Sets the value of the participantID property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setParticipantID(BigInteger value) {
        this.participantID = value;
    }

    /**
     * Gets the value of the silenceState property.
     * 
     */
    public int getSilenceState() {
        return silenceState;
    }

    /**
     * Sets the value of the silenceState property.
     * 
     */
    public void setSilenceState(int value) {
        this.silenceState = value;
    }

    /**
     * Gets the value of the moderatorPIN property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModeratorPIN() {
        return moderatorPIN;
    }

    /**
     * Sets the value of the moderatorPIN property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModeratorPIN(String value) {
        this.moderatorPIN = value;
    }

}
