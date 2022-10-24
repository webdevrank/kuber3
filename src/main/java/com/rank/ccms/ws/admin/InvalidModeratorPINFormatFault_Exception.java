
package com.rank.ccms.ws.admin;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "InvalidModeratorPINFormatFault", targetNamespace = "http://portal.vidyo.com/admin/v1_1")
public class InvalidModeratorPINFormatFault_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private InvalidModeratorPINFormatFault faultInfo;

    /**
     * 
     * @param message
     * @param faultInfo
     */
    public InvalidModeratorPINFormatFault_Exception(String message, InvalidModeratorPINFormatFault faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param message
     * @param faultInfo
     * @param cause
     */
    public InvalidModeratorPINFormatFault_Exception(String message, InvalidModeratorPINFormatFault faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: com.rank.ibl.ws.admin.InvalidModeratorPINFormatFault
     */
    public InvalidModeratorPINFormatFault getFaultInfo() {
        return faultInfo;
    }

}
