
package com.rank.ccms.ws.user;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
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
 *         &lt;element name="loginBannerText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element ref="{http://portal.vidyo.com/user/v1_1}WelcomeBannerContent" minOccurs="0"/>
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
    "loginBannerText",
    "welcomeBannerContent"
})
@XmlRootElement(name = "getLoginAndWelcomeBannerResponse")
public class GetLoginAndWelcomeBannerResponse {

    @XmlElementRef(name = "loginBannerText", namespace = "http://portal.vidyo.com/user/v1_1", type = JAXBElement.class, required = false)
    protected JAXBElement<String> loginBannerText;
    @XmlElement(name = "WelcomeBannerContent")
    protected WelcomeBannerContent welcomeBannerContent;

    /**
     * Gets the value of the loginBannerText property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getLoginBannerText() {
        return loginBannerText;
    }

    /**
     * Sets the value of the loginBannerText property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setLoginBannerText(JAXBElement<String> value) {
        this.loginBannerText = value;
    }

    /**
     * Gets the value of the welcomeBannerContent property.
     * 
     * @return
     *     possible object is
     *     {@link WelcomeBannerContent }
     *     
     */
    public WelcomeBannerContent getWelcomeBannerContent() {
        return welcomeBannerContent;
    }

    /**
     * Sets the value of the welcomeBannerContent property.
     * 
     * @param value
     *     allowed object is
     *     {@link WelcomeBannerContent }
     *     
     */
    public void setWelcomeBannerContent(WelcomeBannerContent value) {
        this.welcomeBannerContent = value;
    }

}
