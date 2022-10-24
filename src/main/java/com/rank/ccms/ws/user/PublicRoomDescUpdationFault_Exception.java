
package com.rank.ccms.ws.user;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "PublicRoomDescUpdationFault", targetNamespace = "http://portal.vidyo.com/user/v1_1")
public class PublicRoomDescUpdationFault_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private PublicRoomDescUpdationFault faultInfo;

    /**
     * 
     * @param message
     * @param faultInfo
     */
    public PublicRoomDescUpdationFault_Exception(String message, PublicRoomDescUpdationFault faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param message
     * @param faultInfo
     * @param cause
     */
    public PublicRoomDescUpdationFault_Exception(String message, PublicRoomDescUpdationFault faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: com.rank.ibl.ws.user.PublicRoomDescUpdationFault
     */
    public PublicRoomDescUpdationFault getFaultInfo() {
        return faultInfo;
    }

}
