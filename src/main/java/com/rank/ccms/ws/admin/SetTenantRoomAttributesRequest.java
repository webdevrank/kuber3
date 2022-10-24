
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
 *         &lt;element name="setWaitingRoomState" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="supportedClientsOnly" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
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
    "setWaitingRoomState",
    "supportedClientsOnly"
})
@XmlRootElement(name = "SetTenantRoomAttributesRequest")
public class SetTenantRoomAttributesRequest {

    protected Integer setWaitingRoomState;
    protected Boolean supportedClientsOnly;

    /**
     * Gets the value of the setWaitingRoomState property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSetWaitingRoomState() {
        return setWaitingRoomState;
    }

    /**
     * Sets the value of the setWaitingRoomState property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSetWaitingRoomState(Integer value) {
        this.setWaitingRoomState = value;
    }

    /**
     * Gets the value of the supportedClientsOnly property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSupportedClientsOnly() {
        return supportedClientsOnly;
    }

    /**
     * Sets the value of the supportedClientsOnly property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSupportedClientsOnly(Boolean value) {
        this.supportedClientsOnly = value;
    }

}
