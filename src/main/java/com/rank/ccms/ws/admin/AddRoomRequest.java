
package com.rank.ccms.ws.admin;

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
 *         &lt;element name="returnObjectInResponse" type="{http://portal.vidyo.com/admin/v1_1}ReturnObjectInResponse" minOccurs="0"/>
 *         &lt;element name="room" type="{http://portal.vidyo.com/admin/v1_1}Room"/>
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
    "returnObjectInResponse",
    "room"
})
@XmlRootElement(name = "AddRoomRequest")
public class AddRoomRequest {

    protected ReturnObjectInResponse returnObjectInResponse;
    @XmlElement(required = true)
    protected Room room;

    /**
     * Gets the value of the returnObjectInResponse property.
     * 
     * @return
     *     possible object is
     *     {@link ReturnObjectInResponse }
     *     
     */
    public ReturnObjectInResponse getReturnObjectInResponse() {
        return returnObjectInResponse;
    }

    /**
     * Sets the value of the returnObjectInResponse property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReturnObjectInResponse }
     *     
     */
    public void setReturnObjectInResponse(ReturnObjectInResponse value) {
        this.returnObjectInResponse = value;
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
