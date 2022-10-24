
package com.rank.ccms.ws.admin;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "ResourceNotAvailableFault", targetNamespace = "http://portal.vidyo.com/admin/v1_1")
public class ResourceNotAvailableFault_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private ResourceNotAvailableFault faultInfo;

    /**
     * 
     * @param message
     * @param faultInfo
     */
    public ResourceNotAvailableFault_Exception(String message, ResourceNotAvailableFault faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param message
     * @param faultInfo
     * @param cause
     */
    public ResourceNotAvailableFault_Exception(String message, ResourceNotAvailableFault faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: com.rank.ibl.ws.admin.ResourceNotAvailableFault
     */
    public ResourceNotAvailableFault getFaultInfo() {
        return faultInfo;
    }

}