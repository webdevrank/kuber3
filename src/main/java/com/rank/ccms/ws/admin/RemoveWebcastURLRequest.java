
package com.rank.ccms.ws.admin;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="roomID" type="{http://portal.vidyo.com/admin/v1_1}EntityID"/>
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
    "roomID"
})
@XmlRootElement(name = "RemoveWebcastURLRequest")
public class RemoveWebcastURLRequest {

    protected int roomID;

    /**
     * Gets the value of the roomID property.
     * 
     */
    public int getRoomID() {
        return roomID;
    }

    /**
     * Sets the value of the roomID property.
     * 
     */
    public void setRoomID(int value) {
        this.roomID = value;
    }

}
