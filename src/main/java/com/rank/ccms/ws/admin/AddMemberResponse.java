
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
 *         &lt;element ref="{http://portal.vidyo.com/admin/v1_1}OK"/>
 *         &lt;element name="member" type="{http://portal.vidyo.com/admin/v1_1}Member" minOccurs="0"/>
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
    "ok",
    "member"
})
@XmlRootElement(name = "AddMemberResponse")
public class AddMemberResponse {

    @XmlElement(name = "OK", required = true)
    protected String ok;
    protected Member member;

    /**
     * Gets the value of the ok property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOK() {
        return ok;
    }

    /**
     * Sets the value of the ok property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOK(String value) {
        this.ok = value;
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
