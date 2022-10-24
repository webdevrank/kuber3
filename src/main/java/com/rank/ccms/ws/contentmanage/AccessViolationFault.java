
package com.rank.ccms.ws.contentmanage;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="AccessViolationFault" type="{http://replay.vidyo.com/apiservice}Exception" minOccurs="0"/>
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
    "accessViolationFault"
})
@XmlRootElement(name = "AccessViolationFault")
public class AccessViolationFault {

    @XmlElementRef(name = "AccessViolationFault", namespace = "http://replay.vidyo.com/apiservice", type = JAXBElement.class, required = false)
    protected JAXBElement<Exception> accessViolationFault;

    /**
     * Gets the value of the accessViolationFault property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Exception }{@code >}
     *     
     */
    public JAXBElement<Exception> getAccessViolationFault() {
        return accessViolationFault;
    }

    /**
     * Sets the value of the accessViolationFault property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Exception }{@code >}
     *     
     */
    public void setAccessViolationFault(JAXBElement<Exception> value) {
        this.accessViolationFault = value;
    }

}
