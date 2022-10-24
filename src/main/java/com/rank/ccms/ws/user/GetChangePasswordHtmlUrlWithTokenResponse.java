
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
 *         &lt;element name="changePasswordHtmlUrlWithToken" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "changePasswordHtmlUrlWithToken"
})
@XmlRootElement(name = "GetChangePasswordHtmlUrlWithTokenResponse")
public class GetChangePasswordHtmlUrlWithTokenResponse {

    @XmlElement(required = true)
    protected String changePasswordHtmlUrlWithToken;

    /**
     * Gets the value of the changePasswordHtmlUrlWithToken property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChangePasswordHtmlUrlWithToken() {
        return changePasswordHtmlUrlWithToken;
    }

    /**
     * Sets the value of the changePasswordHtmlUrlWithToken property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChangePasswordHtmlUrlWithToken(String value) {
        this.changePasswordHtmlUrlWithToken = value;
    }

}
