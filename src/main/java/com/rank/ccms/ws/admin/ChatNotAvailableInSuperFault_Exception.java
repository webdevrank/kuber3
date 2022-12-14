
package com.rank.ccms.ws.admin;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "ChatNotAvailableInSuperFault", targetNamespace = "http://portal.vidyo.com/admin/v1_1")
public class ChatNotAvailableInSuperFault_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private ChatNotAvailableInSuperFault faultInfo;

    /**
     * 
     * @param message
     * @param faultInfo
     */
    public ChatNotAvailableInSuperFault_Exception(String message, ChatNotAvailableInSuperFault faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param message
     * @param faultInfo
     * @param cause
     */
    public ChatNotAvailableInSuperFault_Exception(String message, ChatNotAvailableInSuperFault faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: com.rank.ibl.ws.admin.ChatNotAvailableInSuperFault
     */
    public ChatNotAvailableInSuperFault getFaultInfo() {
        return faultInfo;
    }

}
