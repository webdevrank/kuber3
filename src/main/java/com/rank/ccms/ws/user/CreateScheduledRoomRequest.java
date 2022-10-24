
package com.rank.ccms.ws.user;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 *         &lt;element name="recurring" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}int">
 *               &lt;minInclusive value="0"/>
 *               &lt;maxInclusive value="400"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="setPIN" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="returnHtmlContent" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="returnRoomDetails" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
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
    "recurring",
    "setPIN",
    "returnHtmlContent",
    "returnRoomDetails"
})
@XmlRootElement(name = "CreateScheduledRoomRequest")
public class CreateScheduledRoomRequest {

    protected Integer recurring;
    protected Boolean setPIN;
    @XmlElement(defaultValue = "false")
    protected Boolean returnHtmlContent;
    @XmlElement(defaultValue = "false")
    protected Boolean returnRoomDetails;

    /**
     * Gets the value of the recurring property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRecurring() {
        return recurring;
    }

    /**
     * Sets the value of the recurring property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRecurring(Integer value) {
        this.recurring = value;
    }

    /**
     * Gets the value of the setPIN property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSetPIN() {
        return setPIN;
    }

    /**
     * Sets the value of the setPIN property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSetPIN(Boolean value) {
        this.setPIN = value;
    }

    /**
     * Gets the value of the returnHtmlContent property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isReturnHtmlContent() {
        return returnHtmlContent;
    }

    /**
     * Sets the value of the returnHtmlContent property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setReturnHtmlContent(Boolean value) {
        this.returnHtmlContent = value;
    }

    /**
     * Gets the value of the returnRoomDetails property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isReturnRoomDetails() {
        return returnRoomDetails;
    }

    /**
     * Sets the value of the returnRoomDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setReturnRoomDetails(Boolean value) {
        this.returnRoomDetails = value;
    }

}
