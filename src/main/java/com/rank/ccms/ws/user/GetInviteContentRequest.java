
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
 *         &lt;element name="roomID" type="{http://portal.vidyo.com/user/v1_1}EntityID" minOccurs="0"/>
 *         &lt;element name="returnHtmlContent" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
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
    "roomID",
    "returnHtmlContent"
})
@XmlRootElement(name = "GetInviteContentRequest")
public class GetInviteContentRequest {

    protected String roomID;
    @XmlElement(defaultValue = "false")
    protected Boolean returnHtmlContent;

    /**
     * Gets the value of the roomID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoomID() {
        return roomID;
    }

    /**
     * Sets the value of the roomID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoomID(String value) {
        this.roomID = value;
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

}
