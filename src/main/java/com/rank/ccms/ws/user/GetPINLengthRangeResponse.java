
package com.rank.ccms.ws.user;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="minimumPINLength" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="maximumPINLength" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "minimumPINLength",
    "maximumPINLength"
})
@XmlRootElement(name = "GetPINLengthRangeResponse")
public class GetPINLengthRangeResponse {

    protected int minimumPINLength;
    protected int maximumPINLength;

    /**
     * Gets the value of the minimumPINLength property.
     * 
     */
    public int getMinimumPINLength() {
        return minimumPINLength;
    }

    /**
     * Sets the value of the minimumPINLength property.
     * 
     */
    public void setMinimumPINLength(int value) {
        this.minimumPINLength = value;
    }

    /**
     * Gets the value of the maximumPINLength property.
     * 
     */
    public int getMaximumPINLength() {
        return maximumPINLength;
    }

    /**
     * Sets the value of the maximumPINLength property.
     * 
     */
    public void setMaximumPINLength(int value) {
        this.maximumPINLength = value;
    }

}
