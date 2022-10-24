
package com.rank.ccms.ws.user;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "LogAggregationDisabledFault", targetNamespace = "http://portal.vidyo.com/user/v1_1")
public class LogAggregationDisabledFault_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private LogAggregationDisabledFault faultInfo;

    /**
     * 
     * @param message
     * @param faultInfo
     */
    public LogAggregationDisabledFault_Exception(String message, LogAggregationDisabledFault faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param message
     * @param faultInfo
     * @param cause
     */
    public LogAggregationDisabledFault_Exception(String message, LogAggregationDisabledFault faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: com.rank.ibl.ws.user.LogAggregationDisabledFault
     */
    public LogAggregationDisabledFault getFaultInfo() {
        return faultInfo;
    }

}
