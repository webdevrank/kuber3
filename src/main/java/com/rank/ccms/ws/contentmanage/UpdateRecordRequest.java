
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
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="comments" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tags" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="recordScope" type="{http://replay.vidyo.com/apiservice}recordScopeUpdate" minOccurs="0"/>
 *         &lt;element name="pin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="locked" type="{http://replay.vidyo.com/apiservice}recordLocked" minOccurs="0"/>
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
    "id",
    "title",
    "comments",
    "tags",
    "recordScope",
    "pin",
    "locked"
})
@XmlRootElement(name = "UpdateRecordRequest")
public class UpdateRecordRequest {

    protected int id;
    protected String title;
    @XmlElementRef(name = "comments", namespace = "http://replay.vidyo.com/apiservice", type = JAXBElement.class, required = false)
    protected JAXBElement<String> comments;
    @XmlElementRef(name = "tags", namespace = "http://replay.vidyo.com/apiservice", type = JAXBElement.class, required = false)
    protected JAXBElement<String> tags;
    @XmlElementRef(name = "recordScope", namespace = "http://replay.vidyo.com/apiservice", type = JAXBElement.class, required = false)
    protected JAXBElement<RecordScopeUpdate> recordScope;
    @XmlElementRef(name = "pin", namespace = "http://replay.vidyo.com/apiservice", type = JAXBElement.class, required = false)
    protected JAXBElement<String> pin;
    @XmlElementRef(name = "locked", namespace = "http://replay.vidyo.com/apiservice", type = JAXBElement.class, required = false)
    protected JAXBElement<RecordLocked> locked;

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
     * Gets the value of the comments property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getComments() {
        return comments;
    }

    /**
     * Sets the value of the comments property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setComments(JAXBElement<String> value) {
        this.comments = value;
    }

    /**
     * Gets the value of the tags property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTags() {
        return tags;
    }

    /**
     * Sets the value of the tags property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTags(JAXBElement<String> value) {
        this.tags = value;
    }

    /**
     * Gets the value of the recordScope property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link RecordScopeUpdate }{@code >}
     *     
     */
    public JAXBElement<RecordScopeUpdate> getRecordScope() {
        return recordScope;
    }

    /**
     * Sets the value of the recordScope property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link RecordScopeUpdate }{@code >}
     *     
     */
    public void setRecordScope(JAXBElement<RecordScopeUpdate> value) {
        this.recordScope = value;
    }

    /**
     * Gets the value of the pin property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPin() {
        return pin;
    }

    /**
     * Sets the value of the pin property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPin(JAXBElement<String> value) {
        this.pin = value;
    }

    /**
     * Gets the value of the locked property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link RecordLocked }{@code >}
     *     
     */
    public JAXBElement<RecordLocked> getLocked() {
        return locked;
    }

    /**
     * Sets the value of the locked property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link RecordLocked }{@code >}
     *     
     */
    public void setLocked(JAXBElement<RecordLocked> value) {
        this.locked = value;
    }

}
