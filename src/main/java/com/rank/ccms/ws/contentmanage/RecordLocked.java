
package com.rank.ccms.ws.contentmanage;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for recordLocked.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="recordLocked">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="true"/>
 *     &lt;enumeration value="false"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "recordLocked")
@XmlEnum
public enum RecordLocked {

    @XmlEnumValue("true")
    TRUE("true"),
    @XmlEnumValue("false")
    FALSE("false");
    private final String value;

    RecordLocked(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static RecordLocked fromValue(String v) {
        for (RecordLocked c: RecordLocked.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
