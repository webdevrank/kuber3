
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
 *         &lt;element ref="{http://portal.vidyo.com/user/v1_1}Filter" minOccurs="0"/>
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
@XmlType(name = "", propOrder = {
    "conferenceID",
    "filter",
    "moderatorPIN"
})
@XmlRootElement(name = "GetLectureModeParticipantsRequest")
public class GetLectureModeParticipantsRequest {

    @XmlElement(required = true)
    protected String conferenceID;
    @XmlElement(name = "Filter")
    protected Filter filter;
    @XmlElementRef(name = "moderatorPIN", namespace = "http://portal.vidyo.com/user/v1_1", type = JAXBElement.class, required = false)
    protected JAXBElement<String> moderatorPIN;

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
     * Gets the value of the filter property.
     * 
     * @return
     *     possible object is
     *     {@link Filter }
     *     
     */
    public Filter getFilter() {
        return filter;
    }

    /**
     * Sets the value of the filter property.
     * 
     * @param value
     *     allowed object is
     *     {@link Filter }
     *     
     */
    public void setFilter(Filter value) {
        this.filter = value;
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

}
