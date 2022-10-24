package com.rank.ccms.config.callroutingprops.jaxb;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "crProperty"
})
@XmlRootElement(name = "CallRoutingProperties")
public class CallRoutingProperties {

    @XmlElement(name = "CRProperty", required = true)
    protected List<CRProperty> crProperty;

    public List<CRProperty> getCRProperty() {
        if (crProperty == null) {
            crProperty = new ArrayList<>();
        }
        return this.crProperty;
    }

}
