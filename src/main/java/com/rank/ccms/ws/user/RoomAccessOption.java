
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
 *         &lt;element name="accessOption" type="{http://portal.vidyo.com/user/v1_1}AccessOptionName"/>
 *         &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "accessOption",
    "value"
})
@XmlRootElement(name = "RoomAccessOption")
public class RoomAccessOption {

    @XmlElement(required = true)
    protected AccessOptionName accessOption;
    @XmlElement(required = true)
    protected String value;

    /**
     * Gets the value of the accessOption property.
     * 
     * @return
     *     possible object is
     *     {@link AccessOptionName }
     *     
     */
    public AccessOptionName getAccessOption() {
        return accessOption;
    }

    /**
     * Sets the value of the accessOption property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccessOptionName }
     *     
     */
    public void setAccessOption(AccessOptionName value) {
        this.accessOption = value;
    }

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

}
