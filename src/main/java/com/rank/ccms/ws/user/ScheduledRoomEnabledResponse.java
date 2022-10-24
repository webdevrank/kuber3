
package com.rank.ccms.ws.user;

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
 *         &lt;element name="scheduledRoomEnabled" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "scheduledRoomEnabled"
})
@XmlRootElement(name = "ScheduledRoomEnabledResponse")
public class ScheduledRoomEnabledResponse {

    protected boolean scheduledRoomEnabled;

    /**
     * Gets the value of the scheduledRoomEnabled property.
     * 
     */
    public boolean isScheduledRoomEnabled() {
        return scheduledRoomEnabled;
    }

    /**
     * Sets the value of the scheduledRoomEnabled property.
     * 
     */
    public void setScheduledRoomEnabled(boolean value) {
        this.scheduledRoomEnabled = value;
    }

}
