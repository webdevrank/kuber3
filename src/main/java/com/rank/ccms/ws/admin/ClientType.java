
package com.rank.ccms.ws.admin;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ClientType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ClientType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="M"/>
 *     &lt;enumeration value="V"/>
 *     &lt;enumeration value="R"/>
 *     &lt;enumeration value="S"/>
 *     &lt;enumeration value="U"/>
 *     &lt;enumeration value="T"/>
 *     &lt;enumeration value="X"/>
 *     &lt;enumeration value="P"/>
 *     &lt;enumeration value="N"/>
 *     &lt;enumeration value="E"/>
 *     &lt;enumeration value="O"/>
 *     &lt;enumeration value="Q"/>
 *     &lt;enumeration value="Y"/>
 *     &lt;enumeration value="F"/>
 *     &lt;enumeration value="Z"/>
 *     &lt;enumeration value="B"/>
 *     &lt;enumeration value="C"/>
 *     &lt;enumeration value="D"/>
 *     &lt;enumeration value="W32"/>
 *     &lt;enumeration value="W64"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ClientType")
@XmlEnum
public enum ClientType {

    M("M"),
    V("V"),
    R("R"),
    S("S"),
    U("U"),
    T("T"),
    X("X"),
    P("P"),
    N("N"),
    E("E"),
    O("O"),
    Q("Q"),
    Y("Y"),
    F("F"),
    Z("Z"),
    B("B"),
    C("C"),
    D("D"),
    @XmlEnumValue("W32")
    W_32("W32"),
    @XmlEnumValue("W64")
    W_64("W64");
    private final String value;

    ClientType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ClientType fromValue(String v) {
        for (ClientType c: ClientType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
