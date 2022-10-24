
package com.rank.ccms.ws.user;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="query" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="queryField" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="memberType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="start" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="limit" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="sortBy" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sortDir" type="{http://portal.vidyo.com/user/v1_1}sortDir"/>
 *         &lt;element name="total" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element ref="{http://portal.vidyo.com/user/v1_1}Member" maxOccurs="unbounded" minOccurs="0"/>
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
    "query",
    "queryField",
    "memberType",
    "start",
    "limit",
    "sortBy",
    "sortDir",
    "total",
    "member"
})
@XmlRootElement(name = "SearchMembersResponse")
public class SearchMembersResponse {

    @XmlElement(required = true)
    protected String query;
    @XmlElement(required = true)
    protected String queryField;
    @XmlElement(required = true)
    protected String memberType;
    @XmlElement(required = true)
    protected BigInteger start;
    @XmlElement(required = true)
    protected BigInteger limit;
    @XmlElement(required = true)
    protected String sortBy;
    @XmlElement(required = true)
    protected SortDir sortDir;
    protected int total;
    @XmlElement(name = "Member")
    protected List<Member> member;

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
     * Gets the value of the queryField property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQueryField() {
        return queryField;
    }

    /**
     * Sets the value of the queryField property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQueryField(String value) {
        this.queryField = value;
    }

    /**
     * Gets the value of the memberType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMemberType() {
        return memberType;
    }

    /**
     * Sets the value of the memberType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMemberType(String value) {
        this.memberType = value;
    }

    /**
     * Gets the value of the start property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getStart() {
        return start;
    }

    /**
     * Sets the value of the start property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setStart(BigInteger value) {
        this.start = value;
    }

    /**
     * Gets the value of the limit property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getLimit() {
        return limit;
    }

    /**
     * Sets the value of the limit property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setLimit(BigInteger value) {
        this.limit = value;
    }

    /**
     * Gets the value of the sortBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSortBy() {
        return sortBy;
    }

    /**
     * Sets the value of the sortBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSortBy(String value) {
        this.sortBy = value;
    }

    /**
     * Gets the value of the sortDir property.
     * 
     * @return
     *     possible object is
     *     {@link SortDir }
     *     
     */
    public SortDir getSortDir() {
        return sortDir;
    }

    /**
     * Sets the value of the sortDir property.
     * 
     * @param value
     *     allowed object is
     *     {@link SortDir }
     *     
     */
    public void setSortDir(SortDir value) {
        this.sortDir = value;
    }

    /**
     * Gets the value of the total property.
     * 
     */
    public int getTotal() {
        return total;
    }

    /**
     * Sets the value of the total property.
     * 
     */
    public void setTotal(int value) {
        this.total = value;
    }

    /**
     * Gets the value of the member property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the member property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMember().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Member }
     * 
     * 
     */
    public List<Member> getMember() {
        if (member == null) {
            member = new ArrayList<Member>();
        }
        return this.member;
    }

}
