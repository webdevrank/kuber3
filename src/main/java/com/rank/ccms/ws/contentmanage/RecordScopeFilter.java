
package com.rank.ccms.ws.contentmanage;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for recordScopeFilter.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="recordScopeFilter">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="all"/>
 *     &lt;enumeration value="allUser"/>
 *     &lt;enumeration value="new"/>
 *     &lt;enumeration value="private"/>
 *     &lt;enumeration value="organizational"/>
 *     &lt;enumeration value="public"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "recordScopeFilter")
@XmlEnum
public enum RecordScopeFilter {

    @XmlEnumValue("all")
    ALL("all"),
    @XmlEnumValue("allUser")
    ALL_USER("allUser"),
    @XmlEnumValue("new")
    NEW("new"),
    @XmlEnumValue("private")
    PRIVATE("private"),
    @XmlEnumValue("organizational")
    ORGANIZATIONAL("organizational"),
    @XmlEnumValue("public")
    PUBLIC("public");
    private final String value;

    RecordScopeFilter(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static RecordScopeFilter fromValue(String v) {
        for (RecordScopeFilter c: RecordScopeFilter.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
