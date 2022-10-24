
package com.rank.ccms.ws.user;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "FileTooLargeFault", targetNamespace = "http://portal.vidyo.com/user/v1_1")
public class FileTooLargeFault_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private FileTooLargeFault faultInfo;

    /**
     * 
     * @param message
     * @param faultInfo
     */
    public FileTooLargeFault_Exception(String message, FileTooLargeFault faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param message
     * @param faultInfo
     * @param cause
     */
    public FileTooLargeFault_Exception(String message, FileTooLargeFault faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: com.rank.ibl.ws.user.FileTooLargeFault
     */
    public FileTooLargeFault getFaultInfo() {
        return faultInfo;
    }

}