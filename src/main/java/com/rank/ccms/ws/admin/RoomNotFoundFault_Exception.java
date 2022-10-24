
package com.rank.ccms.ws.admin;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "RoomNotFoundFault", targetNamespace = "http://portal.vidyo.com/admin/v1_1")
public class RoomNotFoundFault_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private RoomNotFoundFault faultInfo;

    /**
     * 
     * @param message
     * @param faultInfo
     */
    public RoomNotFoundFault_Exception(String message, RoomNotFoundFault faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param message
     * @param faultInfo
     * @param cause
     */
    public RoomNotFoundFault_Exception(String message, RoomNotFoundFault faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: com.rank.ibl.ws.admin.RoomNotFoundFault
     */
    public RoomNotFoundFault getFaultInfo() {
        return faultInfo;
    }

}
