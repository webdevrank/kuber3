
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
 *         &lt;element name="member" type="{http://portal.vidyo.com/admin/v1_1}Member"/>
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
    "member"
})
@XmlRootElement(name = "AddMemberRequest")
public class AddMemberRequest {

    protected ReturnObjectInResponse returnObjectInResponse;
    @XmlElement(required = true)
    protected Member member;

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
     * Gets the value of the member property.
     * 
     * @return
     *     possible object is
     *     {@link Member }
     *     
     */
    public Member getMember() {
        return member;
    }

    /**
     * Sets the value of the member property.
     * 
     * @param value
     *     allowed object is
     *     {@link Member }
     *     
     */
    public void setMember(Member value) {
        this.member = value;
    }

}
