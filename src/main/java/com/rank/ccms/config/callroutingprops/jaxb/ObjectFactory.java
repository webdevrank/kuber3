package com.rank.ccms.config.callroutingprops.jaxb;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the
 * com.rank.ccms.config.core.callroutingprops.jaxb package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 *
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _PropertyName_QNAME = new QName("", "PropertyName");
    private final static QName _PropertySequence_QNAME = new QName("", "propertySequence");

    public ObjectFactory() {
    }

    public CRProperty createCRProperty() {
        return new CRProperty();
    }

    public CallRoutingProperties createCallRoutingProperties() {
        return new CallRoutingProperties();
    }

    @XmlElementDecl(namespace = "", name = "PropertyName")
    public JAXBElement<String> createPropertyName(String value) {
        return new JAXBElement<>(_PropertyName_QNAME, String.class, null, value);
    }

    @XmlElementDecl(namespace = "", name = "propertySequence")
    public JAXBElement<Byte> createPropertySequence(Byte value) {
        return new JAXBElement<>(_PropertySequence_QNAME, Byte.class, null, value);
    }

}
