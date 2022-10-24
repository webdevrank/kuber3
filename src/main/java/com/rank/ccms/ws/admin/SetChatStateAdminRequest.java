
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
 *         &lt;element name="setPrivateChatState" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="setPublicChatState" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
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
    "setPrivateChatState",
    "setPublicChatState"
})
@XmlRootElement(name = "setChatStateAdminRequest")
public class SetChatStateAdminRequest {

    protected Boolean setPrivateChatState;
    protected Boolean setPublicChatState;

    /**
     * Gets the value of the setPrivateChatState property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSetPrivateChatState() {
        return setPrivateChatState;
    }

    /**
     * Sets the value of the setPrivateChatState property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSetPrivateChatState(Boolean value) {
        this.setPrivateChatState = value;
    }

    /**
     * Gets the value of the setPublicChatState property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSetPublicChatState() {
        return setPublicChatState;
    }

    /**
     * Sets the value of the setPublicChatState property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSetPublicChatState(Boolean value) {
        this.setPublicChatState = value;
    }

}
