
package com.rank.ccms.ws.contentmanage;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for recordScopeUpdate.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="recordScopeUpdate">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="private"/>
 *     &lt;enumeration value="organization"/>
 *     &lt;enumeration value="public"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "recordScopeUpdate")
@XmlEnum
public enum RecordScopeUpdate {

    @XmlEnumValue("private")
    PRIVATE("private"),
    @XmlEnumValue("organization")
    ORGANIZATION("organization"),
    @XmlEnumValue("public")
    PUBLIC("public");
    private final String value;

    RecordScopeUpdate(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static RecordScopeUpdate fromValue(String v) {
        for (RecordScopeUpdate c: RecordScopeUpdate.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
