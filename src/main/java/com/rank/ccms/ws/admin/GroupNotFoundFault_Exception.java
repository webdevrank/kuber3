
package com.rank.ccms.ws.admin;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "GroupNotFoundFault", targetNamespace = "http://portal.vidyo.com/admin/v1_1")
public class GroupNotFoundFault_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private GroupNotFoundFault faultInfo;

    /**
     * 
     * @param message
     * @param faultInfo
     */
    public GroupNotFoundFault_Exception(String message, GroupNotFoundFault faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param message
     * @param faultInfo
     * @param cause
     */
    public GroupNotFoundFault_Exception(String message, GroupNotFoundFault faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: com.rank.ibl.ws.admin.GroupNotFoundFault
     */
    public GroupNotFoundFault getFaultInfo() {
        return faultInfo;
    }

}