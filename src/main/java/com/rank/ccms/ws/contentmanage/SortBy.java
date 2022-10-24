
package com.rank.ccms.ws.contentmanage;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sortBy.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="sortBy">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="date"/>
 *     &lt;enumeration value="title"/>
 *     &lt;enumeration value="room"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "sortBy")
@XmlEnum
public enum SortBy {

    @XmlEnumValue("date")
    DATE("date"),
    @XmlEnumValue("title")
    TITLE("title"),
    @XmlEnumValue("room")
    ROOM("room");
    private final String value;

    SortBy(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SortBy fromValue(String v) {
        for (SortBy c: SortBy.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
