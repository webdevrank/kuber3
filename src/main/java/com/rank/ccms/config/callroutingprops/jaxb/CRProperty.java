package com.rank.ccms.config.callroutingprops.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "propertyName",
    "propertySequence"
})
@XmlRootElement(name = "CRProperty")
public class CRProperty {

    @XmlElement(name = "PropertyName", required = true)
    protected String propertyName;
    protected byte propertySequence;

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String value) {
        this.propertyName = value;
    }

    public byte getPropertySequence() {
        return propertySequence;
    }

    public void setPropertySequence(byte value) {
        this.propertySequence = value;
    }

}
