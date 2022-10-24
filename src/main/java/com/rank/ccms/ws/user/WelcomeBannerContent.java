
package com.rank.ccms.ws.user;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


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
 *         &lt;element name="welcomeBannerText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element ref="{http://portal.vidyo.com/user/v1_1}LoginAttempt" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="welcomeBannerPasswordExpiryDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
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
    "welcomeBannerText",
    "loginAttempt",
    "welcomeBannerPasswordExpiryDate"
})
@XmlRootElement(name = "WelcomeBannerContent")
public class WelcomeBannerContent {

    @XmlElementRef(name = "welcomeBannerText", namespace = "http://portal.vidyo.com/user/v1_1", type = JAXBElement.class, required = false)
    protected JAXBElement<String> welcomeBannerText;
    @XmlElement(name = "LoginAttempt")
    protected List<LoginAttempt> loginAttempt;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar welcomeBannerPasswordExpiryDate;

    /**
     * Gets the value of the welcomeBannerText property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getWelcomeBannerText() {
        return welcomeBannerText;
    }

    /**
     * Sets the value of the welcomeBannerText property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setWelcomeBannerText(JAXBElement<String> value) {
        this.welcomeBannerText = value;
    }

    /**
     * Gets the value of the loginAttempt property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the loginAttempt property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLoginAttempt().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LoginAttempt }
     * 
     * 
     */
    public List<LoginAttempt> getLoginAttempt() {
        if (loginAttempt == null) {
            loginAttempt = new ArrayList<LoginAttempt>();
        }
        return this.loginAttempt;
    }

    /**
     * Gets the value of the welcomeBannerPasswordExpiryDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getWelcomeBannerPasswordExpiryDate() {
        return welcomeBannerPasswordExpiryDate;
    }

    /**
     * Sets the value of the welcomeBannerPasswordExpiryDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setWelcomeBannerPasswordExpiryDate(XMLGregorianCalendar value) {
        this.welcomeBannerPasswordExpiryDate = value;
    }

}
