
package com.rank.ccms.ws.guest;

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
 *         &lt;element name="CurrentTag" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="InstallerURI" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "currentTag",
    "installerURI"
})
@XmlRootElement(name = "ClientVersionResponse")
public class ClientVersionResponse {

    @XmlElement(name = "CurrentTag", required = true)
    protected String currentTag;
    @XmlElement(name = "InstallerURI", required = true)
    protected String installerURI;

    /**
     * Gets the value of the currentTag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrentTag() {
        return currentTag;
    }

    /**
     * Sets the value of the currentTag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrentTag(String value) {
        this.currentTag = value;
    }

    /**
     * Gets the value of the installerURI property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstallerURI() {
        return installerURI;
    }

    /**
     * Sets the value of the installerURI property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstallerURI(String value) {
        this.installerURI = value;
    }

}
