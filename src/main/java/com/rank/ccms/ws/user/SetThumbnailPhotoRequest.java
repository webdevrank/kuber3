
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
 *         &lt;element name="thumbNailPhoto" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
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
    "thumbNailPhoto"
})
@XmlRootElement(name = "SetThumbnailPhotoRequest")
public class SetThumbnailPhotoRequest {

    @XmlElement(required = true)
    protected byte[] thumbNailPhoto;

    /**
     * Gets the value of the thumbNailPhoto property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getThumbNailPhoto() {
        return thumbNailPhoto;
    }

    /**
     * Sets the value of the thumbNailPhoto property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setThumbNailPhoto(byte[] value) {
        this.thumbNailPhoto = value;
    }

}
