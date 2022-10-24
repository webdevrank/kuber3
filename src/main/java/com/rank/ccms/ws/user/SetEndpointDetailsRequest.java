
package com.rank.ccms.ws.user;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
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
 *         &lt;element name="EID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{http://portal.vidyo.com/user/v1_1}EndpointFeature" maxOccurs="unbounded"/>
 *         &lt;element name="applicationName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="applicationVersion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="applicationOs" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deviceModel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="extData" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="extDataType" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;any maxOccurs="unbounded" minOccurs="0"/>
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
    "eid",
    "endpointFeature",
    "applicationName",
    "applicationVersion",
    "applicationOs",
    "deviceModel",
    "extData",
    "extDataType",
    "any"
})
@XmlRootElement(name = "SetEndpointDetailsRequest")
public class SetEndpointDetailsRequest {

    @XmlElement(name = "EID", required = true)
    protected String eid;
    @XmlElement(name = "EndpointFeature", required = true)
    protected List<EndpointFeature> endpointFeature;
    protected String applicationName;
    protected String applicationVersion;
    protected String applicationOs;
    protected String deviceModel;
    protected String extData;
    protected Integer extDataType;
    @XmlAnyElement(lax = true)
    protected List<Object> any;

    /**
     * Gets the value of the eid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEID() {
        return eid;
    }

    /**
     * Sets the value of the eid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEID(String value) {
        this.eid = value;
    }

    /**
     * Gets the value of the endpointFeature property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the endpointFeature property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEndpointFeature().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EndpointFeature }
     * 
     * 
     */
    public List<EndpointFeature> getEndpointFeature() {
        if (endpointFeature == null) {
            endpointFeature = new ArrayList<EndpointFeature>();
        }
        return this.endpointFeature;
    }

    /**
     * Gets the value of the applicationName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplicationName() {
        return applicationName;
    }

    /**
     * Sets the value of the applicationName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplicationName(String value) {
        this.applicationName = value;
    }

    /**
     * Gets the value of the applicationVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplicationVersion() {
        return applicationVersion;
    }

    /**
     * Sets the value of the applicationVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplicationVersion(String value) {
        this.applicationVersion = value;
    }

    /**
     * Gets the value of the applicationOs property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplicationOs() {
        return applicationOs;
    }

    /**
     * Sets the value of the applicationOs property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplicationOs(String value) {
        this.applicationOs = value;
    }

    /**
     * Gets the value of the deviceModel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeviceModel() {
        return deviceModel;
    }

    /**
     * Sets the value of the deviceModel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeviceModel(String value) {
        this.deviceModel = value;
    }

    /**
     * Gets the value of the extData property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtData() {
        return extData;
    }

    /**
     * Sets the value of the extData property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtData(String value) {
        this.extData = value;
    }

    /**
     * Gets the value of the extDataType property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getExtDataType() {
        return extDataType;
    }

    /**
     * Sets the value of the extDataType property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setExtDataType(Integer value) {
        this.extDataType = value;
    }

    /**
     * Gets the value of the any property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the any property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAny().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Object }
     * 
     * 
     */
    public List<Object> getAny() {
        if (any == null) {
            any = new ArrayList<Object>();
        }
        return this.any;
    }

}
