
package com.rank.ccms.ws.contentmanage;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
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
 *         &lt;element name="tenantName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="roomFilter" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="usernameFilter" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="query" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="recordScope" type="{http://replay.vidyo.com/apiservice}recordScopeFilter"/>
 *         &lt;element name="sortBy" type="{http://replay.vidyo.com/apiservice}sortBy"/>
 *         &lt;element name="dir" type="{http://replay.vidyo.com/apiservice}sortDirection"/>
 *         &lt;element name="limit" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="start" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="webcast" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
    "tenantName",
    "roomFilter",
    "usernameFilter",
    "query",
    "recordScope",
    "sortBy",
    "dir",
    "limit",
    "start",
    "webcast"
})
@XmlRootElement(name = "RecordsSearchRequest")
public class RecordsSearchRequest {

    @XmlElement(required = true, nillable = true)
    protected String tenantName;
    @XmlElement(required = true, nillable = true)
    protected String roomFilter;
    @XmlElement(required = true, nillable = true)
    protected String usernameFilter;
    @XmlElement(required = true, nillable = true)
    protected String query;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "string")
    protected RecordScopeFilter recordScope;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "string")
    protected SortBy sortBy;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "string")
    protected SortDirection dir;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer limit;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer start;
    @XmlElement(required = true, type = Boolean.class, nillable = true)
    protected Boolean webcast;

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
     * Gets the value of the roomFilter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoomFilter() {
        return roomFilter;
    }

    /**
     * Sets the value of the roomFilter property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoomFilter(String value) {
        this.roomFilter = value;
    }

    /**
     * Gets the value of the usernameFilter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsernameFilter() {
        return usernameFilter;
    }

    /**
     * Sets the value of the usernameFilter property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsernameFilter(String value) {
        this.usernameFilter = value;
    }

    /**
     * Gets the value of the query property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuery() {
        return query;
    }

    /**
     * Sets the value of the query property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuery(String value) {
        this.query = value;
    }

    /**
     * Gets the value of the recordScope property.
     * 
     * @return
     *     possible object is
     *     {@link RecordScopeFilter }
     *     
     */
    public RecordScopeFilter getRecordScope() {
        return recordScope;
    }

    /**
     * Sets the value of the recordScope property.
     * 
     * @param value
     *     allowed object is
     *     {@link RecordScopeFilter }
     *     
     */
    public void setRecordScope(RecordScopeFilter value) {
        this.recordScope = value;
    }

    /**
     * Gets the value of the sortBy property.
     * 
     * @return
     *     possible object is
     *     {@link SortBy }
     *     
     */
    public SortBy getSortBy() {
        return sortBy;
    }

    /**
     * Sets the value of the sortBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link SortBy }
     *     
     */
    public void setSortBy(SortBy value) {
        this.sortBy = value;
    }

    /**
     * Gets the value of the dir property.
     * 
     * @return
     *     possible object is
     *     {@link SortDirection }
     *     
     */
    public SortDirection getDir() {
        return dir;
    }

    /**
     * Sets the value of the dir property.
     * 
     * @param value
     *     allowed object is
     *     {@link SortDirection }
     *     
     */
    public void setDir(SortDirection value) {
        this.dir = value;
    }

    /**
     * Gets the value of the limit property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getLimit() {
        return limit;
    }

    /**
     * Sets the value of the limit property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setLimit(Integer value) {
        this.limit = value;
    }

    /**
     * Gets the value of the start property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getStart() {
        return start;
    }

    /**
     * Sets the value of the start property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setStart(Integer value) {
        this.start = value;
    }

    /**
     * Gets the value of the webcast property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isWebcast() {
        return webcast;
    }

    /**
     * Sets the value of the webcast property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setWebcast(Boolean value) {
        this.webcast = value;
    }

}
