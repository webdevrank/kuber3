
package com.rank.ccms.ws.guest;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "GeneralFault", targetNamespace = "http://portal.vidyo.com/guest")
public class GeneralFault_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private GeneralFault faultInfo;

    /**
     * 
     * @param message
     * @param faultInfo
     */
    public GeneralFault_Exception(String message, GeneralFault faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param message
     * @param faultInfo
     * @param cause
     */
    public GeneralFault_Exception(String message, GeneralFault faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: com.rank.ibl.ws.guest.GeneralFault
     */
    public GeneralFault getFaultInfo() {
        return faultInfo;
    }

}
