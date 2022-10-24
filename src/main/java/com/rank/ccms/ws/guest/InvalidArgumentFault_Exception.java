
package com.rank.ccms.ws.guest;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "InvalidArgumentFault", targetNamespace = "http://portal.vidyo.com/guest")
public class InvalidArgumentFault_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private InvalidArgumentFault faultInfo;

    /**
     * 
     * @param message
     * @param faultInfo
     */
    public InvalidArgumentFault_Exception(String message, InvalidArgumentFault faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param message
     * @param faultInfo
     * @param cause
     */
    public InvalidArgumentFault_Exception(String message, InvalidArgumentFault faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: com.rank.ibl.ws.guest.InvalidArgumentFault
     */
    public InvalidArgumentFault getFaultInfo() {
        return faultInfo;
    }

}