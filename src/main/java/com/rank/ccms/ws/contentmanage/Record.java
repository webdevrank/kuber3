
package com.rank.ccms.ws.contentmanage;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * 
 *                         This elements array will be returned after user search request
 *                         
 * 
 * <p>Java class for Record complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Record">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="guid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tenantName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="userName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="userFullName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dateCreated" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="dateCreatedString" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="endTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="duration" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="resolution" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="framerate" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="pin" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="recordScope" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="roomName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fileLink" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="recorderId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="webcast" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="tags" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="comments" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="locked" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="externalPlaybackLink" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="fileSize" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Record", propOrder = {
    "id",
    "guid",
    "tenantName",
    "userName",
    "userFullName",
    "dateCreated",
    "dateCreatedString",
    "endTime",
    "duration",
    "resolution",
    "framerate",
    "pin",
    "recordScope",
    "title",
    "roomName",
    "fileLink",
    "recorderId",
    "webcast",
    "tags",
    "comments",
    "locked",
    "externalPlaybackLink",
    "fileSize"
})
public class Record {

    protected int id;
    @XmlElement(required = true)
    protected String guid;
    @XmlElement(required = true)
    protected String tenantName;
    @XmlElement(required = true)
    protected String userName;
    @XmlElement(required = true)
    protected String userFullName;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateCreated;
    @XmlElement(required = true)
    protected String dateCreatedString;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar endTime;
    @XmlElement(required = true)
    protected String duration;
    @XmlElement(required = true)
    protected String resolution;
    protected int framerate;
    @XmlElement(required = true, nillable = true)
    protected String pin;
    @XmlElement(required = true)
    protected String recordScope;
    @XmlElement(required = true)
    protected String title;
    @XmlElement(required = true)
    protected String roomName;
    @XmlElement(required = true)
    protected String fileLink;
    @XmlElement(required = true)
    protected String recorderId;
    protected boolean webcast;
    @XmlElement(required = true, nillable = true)
    protected String tags;
    @XmlElement(required = true, nillable = true)
    protected String comments;
    protected boolean locked;
    @XmlElement(required = true, nillable = true)
    protected String externalPlaybackLink;
    @XmlElement(required = true, nillable = true)
    protected String fileSize;

    /**
     * Gets the value of the id property.
     * 
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(int value) {
        this.id = value;
    }

    /**
     * Gets the value of the guid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGuid() {
        return guid;
    }

    /**
     * Sets the value of the guid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGuid(String value) {
        this.guid = value;
    }

    /**
     * Gets the value of the tenantName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTenantName() {
        return tenantName;
    }

    /**
     * Sets the value of the tenantName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTenantName(String value) {
        this.tenantName = value;
    }

    /**
     * Gets the value of the userName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the value of the userName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserName(String value) {
        this.userName = value;
    }

    /**
     * Gets the value of the userFullName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserFullName() {
        return userFullName;
    }

    /**
     * Sets the value of the userFullName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserFullName(String value) {
        this.userFullName = value;
    }

    /**
     * Gets the value of the dateCreated property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateCreated() {
        return dateCreated;
    }

    /**
     * Sets the value of the dateCreated property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateCreated(XMLGregorianCalendar value) {
        this.dateCreated = value;
    }

    /**
     * Gets the value of the dateCreatedString property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDateCreatedString() {
        return dateCreatedString;
    }

    /**
     * Sets the value of the dateCreatedString property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDateCreatedString(String value) {
        this.dateCreatedString = value;
    }

    /**
     * Gets the value of the endTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEndTime() {
        return endTime;
    }

    /**
     * Sets the value of the endTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEndTime(XMLGregorianCalendar value) {
        this.endTime = value;
    }

    /**
     * Gets the value of the duration property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDuration() {
        return duration;
    }

    /**
     * Sets the value of the duration property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDuration(String value) {
        this.duration = value;
    }

    /**
     * Gets the value of the resolution property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResolution() {
        return resolution;
    }

    /**
     * Sets the value of the resolution property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResolution(String value) {
        this.resolution = value;
    }

    /**
     * Gets the value of the framerate property.
     * 
     */
    public int getFramerate() {
        return framerate;
    }

    /**
     * Sets the value of the framerate property.
     * 
     */
    public void setFramerate(int value) {
        this.framerate = value;
    }

    /**
     * Gets the value of the pin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPin() {
        return pin;
    }

    /**
     * Sets the value of the pin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPin(String value) {
        this.pin = value;
    }

    /**
     * Gets the value of the recordScope property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecordScope() {
        return recordScope;
    }

    /**
     * Sets the value of the recordScope property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecordScope(String value) {
        this.recordScope = value;
    }

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Gets the value of the roomName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     * Sets the value of the roomName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoomName(String value) {
        this.roomName = value;
    }

    /**
     * Gets the value of the fileLink property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFileLink() {
        return fileLink;
    }

    /**
     * Sets the value of the fileLink property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileLink(String value) {
        this.fileLink = value;
    }

    /**
     * Gets the value of the recorderId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecorderId() {
        return recorderId;
    }

    /**
     * Sets the value of the recorderId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecorderId(String value) {
        this.recorderId = value;
    }

    /**
     * Gets the value of the webcast property.
     * 
     */
    public boolean isWebcast() {
        return webcast;
    }

    /**
     * Sets the value of the webcast property.
     * 
     */
    public void setWebcast(boolean value) {
        this.webcast = value;
    }

    /**
     * Gets the value of the tags property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTags() {
        return tags;
    }

    /**
     * Sets the value of the tags property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTags(String value) {
        this.tags = value;
    }

    /**
     * Gets the value of the comments property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComments() {
        return comments;
    }

    /**
     * Sets the value of the comments property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComments(String value) {
        this.comments = value;
    }

    /**
     * Gets the value of the locked property.
     * 
     */
    public boolean isLocked() {
        return locked;
    }

    /**
     * Sets the value of the locked property.
     * 
     */
    public void setLocked(boolean value) {
        this.locked = value;
    }

    /**
     * Gets the value of the externalPlaybackLink property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalPlaybackLink() {
        return externalPlaybackLink;
    }

    /**
     * Sets the value of the externalPlaybackLink property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalPlaybackLink(String value) {
        this.externalPlaybackLink = value;
    }

    /**
     * Gets the value of the fileSize property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFileSize() {
        return fileSize;
    }

    /**
     * Sets the value of the fileSize property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileSize(String value) {
        this.fileSize = value;
    }

}
