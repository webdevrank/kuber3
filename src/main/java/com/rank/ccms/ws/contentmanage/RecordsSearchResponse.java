
package com.rank.ccms.ws.contentmanage;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="allVideosCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="searchCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="myVideosCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="webcastCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="newCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="privateCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="organizationalCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="publicCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="records" type="{http://replay.vidyo.com/apiservice}Record" maxOccurs="unbounded" minOccurs="0"/>
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
    "allVideosCount",
    "searchCount",
    "myVideosCount",
    "webcastCount",
    "newCount",
    "privateCount",
    "organizationalCount",
    "publicCount",
    "records"
})
@XmlRootElement(name = "RecordsSearchResponse")
public class RecordsSearchResponse {

    protected int allVideosCount;
    protected int searchCount;
    protected int myVideosCount;
    protected int webcastCount;
    protected int newCount;
    protected int privateCount;
    protected int organizationalCount;
    protected int publicCount;
    protected List<Record> records;

    /**
     * Gets the value of the allVideosCount property.
     * 
     */
    public int getAllVideosCount() {
        return allVideosCount;
    }

    /**
     * Sets the value of the allVideosCount property.
     * 
     */
    public void setAllVideosCount(int value) {
        this.allVideosCount = value;
    }

    /**
     * Gets the value of the searchCount property.
     * 
     */
    public int getSearchCount() {
        return searchCount;
    }

    /**
     * Sets the value of the searchCount property.
     * 
     */
    public void setSearchCount(int value) {
        this.searchCount = value;
    }

    /**
     * Gets the value of the myVideosCount property.
     * 
     */
    public int getMyVideosCount() {
        return myVideosCount;
    }

    /**
     * Sets the value of the myVideosCount property.
     * 
     */
    public void setMyVideosCount(int value) {
        this.myVideosCount = value;
    }

    /**
     * Gets the value of the webcastCount property.
     * 
     */
    public int getWebcastCount() {
        return webcastCount;
    }

    /**
     * Sets the value of the webcastCount property.
     * 
     */
    public void setWebcastCount(int value) {
        this.webcastCount = value;
    }

    /**
     * Gets the value of the newCount property.
     * 
     */
    public int getNewCount() {
        return newCount;
    }

    /**
     * Sets the value of the newCount property.
     * 
     */
    public void setNewCount(int value) {
        this.newCount = value;
    }

    /**
     * Gets the value of the privateCount property.
     * 
     */
    public int getPrivateCount() {
        return privateCount;
    }

    /**
     * Sets the value of the privateCount property.
     * 
     */
    public void setPrivateCount(int value) {
        this.privateCount = value;
    }

    /**
     * Gets the value of the organizationalCount property.
     * 
     */
    public int getOrganizationalCount() {
        return organizationalCount;
    }

    /**
     * Sets the value of the organizationalCount property.
     * 
     */
    public void setOrganizationalCount(int value) {
        this.organizationalCount = value;
    }

    /**
     * Gets the value of the publicCount property.
     * 
     */
    public int getPublicCount() {
        return publicCount;
    }

    /**
     * Sets the value of the publicCount property.
     * 
     */
    public void setPublicCount(int value) {
        this.publicCount = value;
    }

    /**
     * Gets the value of the records property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the records property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRecords().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Record }
     * 
     * 
     */
    public List<Record> getRecords() {
        if (records == null) {
            records = new ArrayList<Record>();
        }
        return this.records;
    }

}
