
package com.rank.ccms.ws.guest;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
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
 *         &lt;element name="pin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="referenceNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="extData" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="extDataType" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;any minOccurs="0"/>
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
    "pin",
    "referenceNumber",
    "extData",
    "extDataType",
    "any"
})
@XmlRootElement(name = "GuestJoinConferenceRequest")
public class GuestJoinConferenceRequest {

    protected int guestID;
    @XmlElementRef(name = "pin", namespace = "http://portal.vidyo.com/guest", type = JAXBElement.class, required = false)
    protected JAXBElement<String> pin;
    @XmlElementRef(name = "referenceNumber", namespace = "http://portal.vidyo.com/guest", type = JAXBElement.class, required = false)
    protected JAXBElement<String> referenceNumber;
    protected String extData;
    protected Integer extDataType;
    @XmlAnyElement(lax = true)
    protected Object any;

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
     * Gets the value of the pin property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPin() {
        return pin;
    }

    /**
     * Sets the value of the pin property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPin(JAXBElement<String> value) {
        this.pin = value;
    }

    /**
     * Gets the value of the referenceNumber property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getReferenceNumber() {
        return referenceNumber;
    }

    /**
     * Sets the value of the referenceNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setReferenceNumber(JAXBElement<String> value) {
        this.referenceNumber = value;
    }

    /**
     * Gets the value of the extData property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtData() {
        return extData;
    }

    /**
     * Sets the value of the extData property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtData(String value) {
        this.extData = value;
    }

    /**
     * Gets the value of the extDataType property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getExtDataType() {
        return extDataType;
    }

    /**
     * Sets the value of the extDataType property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setExtDataType(Integer value) {
        this.extDataType = value;
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

}
