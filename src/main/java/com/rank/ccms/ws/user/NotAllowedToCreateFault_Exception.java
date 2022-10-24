
package com.rank.ccms.ws.user;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "NotAllowedToCreateFault", targetNamespace = "http://portal.vidyo.com/user/v1_1")
public class NotAllowedToCreateFault_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private NotAllowedToCreateFault faultInfo;

    /**
     * 
     * @param message
     * @param faultInfo
     */
    public NotAllowedToCreateFault_Exception(String message, NotAllowedToCreateFault faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param message
     * @param faultInfo
     * @param cause
     */
    public NotAllowedToCreateFault_Exception(String message, NotAllowedToCreateFault faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: com.rank.ibl.ws.user.NotAllowedToCreateFault
     */
    public NotAllowedToCreateFault getFaultInfo() {
        return faultInfo;
    }

}