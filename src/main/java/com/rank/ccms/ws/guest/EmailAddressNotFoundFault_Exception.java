
package com.rank.ccms.ws.guest;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "EmailAddressNotFoundFault", targetNamespace = "http://portal.vidyo.com/guest")
public class EmailAddressNotFoundFault_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private EmailAddressNotFoundFault faultInfo;

    /**
     * 
     * @param message
     * @param faultInfo
     */
    public EmailAddressNotFoundFault_Exception(String message, EmailAddressNotFoundFault faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param message
     * @param faultInfo
     * @param cause
     */
    public EmailAddressNotFoundFault_Exception(String message, EmailAddressNotFoundFault faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: com.rank.ibl.ws.guest.EmailAddressNotFoundFault
     */
    public EmailAddressNotFoundFault getFaultInfo() {
        return faultInfo;
    }

}