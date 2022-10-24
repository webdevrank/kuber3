package com.rank.ccms.web;

import com.rank.ccms.entities.CallMst;
import com.rank.ccms.entities.CustomerMst;
import com.rank.ccms.entities.EmployeeActivityDtl;
import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.entities.ForwardedCall;
import com.rank.ccms.service.EmployeeMstService;
import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class EmployeeActivityDtlComponent implements Serializable {

    private File file;
    private String downloadedFileName;
    private String downloadCSS;
    private List<ForwardedCall> forwardedCallList;
    private EmployeeActivityDtl employeeActivityDtl;
    private List<EmployeeActivityDtl> employeeNotReadyList;

    private CallMst callMst;
    private List<CallMst> agentWiseCallReportList;
    private Date internalfromDate;
    private Date internaltoDate;
    private Date deviceOSfromDate;
    private Date deviceOStoDate;
    private List<EmployeeActivityDtl> employeeNotReadyList1;
    private Date fromDate;
    private Date toDate;
    private Integer empid;
    private String activity;
    private CustomerMst customerMst;

    private Date callfromDate;
    private Date calltoDate;
    private Integer callempid;
    private Date inboundfromDate;
    private Date inboundtoDate;
    private Integer inboundempid;
    private Date outboundfromDate;
    private Date outboundtoDate;
    private Integer outboundempid;
    private Date forwardfromDate;
    private Date forwardtoDate;
    private Integer forwardempid;
    private Date conferencefromDate;
    private Date conferencetoDate;
    private Integer conferenceempid;
    private boolean exportToCsvBtnStatus;

    @Autowired
    private EmployeeMstService employeeMstService;

    org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(this.getClass());

    public String findEmployeeName(Long empid) {
        EmployeeMst empMst = employeeMstService.findEmployeeMstById(empid);
        String employeeName = empMst.getFirstName() + " " + empMst.getLastName();
        return employeeName;
    }

    public String findEmployeeLoginId(Long empId) {
        EmployeeMst empMst = employeeMstService.findEmployeeMstById(empId);
        if (empMst == null || empMst.getLoginId() == null) {
            return (" ");
        }
        return empMst.getLoginId();
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getDownloadedFileName() {
        return downloadedFileName;
    }

    public void setDownloadedFileName(String downloadedFileName) {
        this.downloadedFileName = downloadedFileName;
    }

    public String getDownloadCSS() {
        return downloadCSS;
    }

    public void setDownloadCSS(String downloadCSS) {
        this.downloadCSS = downloadCSS;
    }

    public List<ForwardedCall> getForwardedCallList() {
        return forwardedCallList;
    }

    public void setForwardedCallList(List<ForwardedCall> forwardedCallList) {
        this.forwardedCallList = forwardedCallList;
    }

    public EmployeeActivityDtl getEmployeeActivityDtl() {
        return employeeActivityDtl;
    }

    public void setEmployeeActivityDtl(EmployeeActivityDtl employeeActivityDtl) {
        this.employeeActivityDtl = employeeActivityDtl;
    }

    public List<EmployeeActivityDtl> getEmployeeNotReadyList() {
        return employeeNotReadyList;
    }

    public void setEmployeeNotReadyList(List<EmployeeActivityDtl> employeeNotReadyList) {
        this.employeeNotReadyList = employeeNotReadyList;
    }

    public CallMst getCallMst() {
        return callMst;
    }

    public void setCallMst(CallMst callMst) {
        this.callMst = callMst;
    }

    public List<CallMst> getAgentWiseCallReportList() {
        return agentWiseCallReportList;
    }

    public void setAgentWiseCallReportList(List<CallMst> agentWiseCallReportList) {
        this.agentWiseCallReportList = agentWiseCallReportList;
    }

    public Date getInternalfromDate() {
        return internalfromDate;
    }

    public void setInternalfromDate(Date internalfromDate) {
        this.internalfromDate = internalfromDate;
    }

    public Date getInternaltoDate() {
        return internaltoDate;
    }

    public void setInternaltoDate(Date internaltoDate) {
        this.internaltoDate = internaltoDate;
    }

    public Date getDeviceOSfromDate() {
        return deviceOSfromDate;
    }

    public void setDeviceOSfromDate(Date deviceOSfromDate) {
        this.deviceOSfromDate = deviceOSfromDate;
    }

    public Date getDeviceOStoDate() {
        return deviceOStoDate;
    }

    public void setDeviceOStoDate(Date deviceOStoDate) {
        this.deviceOStoDate = deviceOStoDate;
    }

    public List<EmployeeActivityDtl> getEmployeeNotReadyList1() {
        return employeeNotReadyList1;
    }

    public void setEmployeeNotReadyList1(List<EmployeeActivityDtl> employeeNotReadyList1) {
        this.employeeNotReadyList1 = employeeNotReadyList1;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public Integer getEmpid() {
        return empid;
    }

    public void setEmpid(Integer empid) {
        this.empid = empid;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public CustomerMst getCustomerMst() {
        return customerMst;
    }

    public void setCustomerMst(CustomerMst customerMst) {
        this.customerMst = customerMst;
    }

    public Date getCallfromDate() {
        return callfromDate;
    }

    public void setCallfromDate(Date callfromDate) {
        this.callfromDate = callfromDate;
    }

    public Date getCalltoDate() {
        return calltoDate;
    }

    public void setCalltoDate(Date calltoDate) {
        this.calltoDate = calltoDate;
    }

    public Integer getCallempid() {
        return callempid;
    }

    public void setCallempid(Integer callempid) {
        this.callempid = callempid;
    }

    public Date getInboundfromDate() {
        return inboundfromDate;
    }

    public void setInboundfromDate(Date inboundfromDate) {
        this.inboundfromDate = inboundfromDate;
    }

    public Date getInboundtoDate() {
        return inboundtoDate;
    }

    public void setInboundtoDate(Date inboundtoDate) {
        this.inboundtoDate = inboundtoDate;
    }

    public Integer getInboundempid() {
        return inboundempid;
    }

    public void setInboundempid(Integer inboundempid) {
        this.inboundempid = inboundempid;
    }

    public Date getOutboundfromDate() {
        return outboundfromDate;
    }

    public void setOutboundfromDate(Date outboundfromDate) {
        this.outboundfromDate = outboundfromDate;
    }

    public Date getOutboundtoDate() {
        return outboundtoDate;
    }

    public void setOutboundtoDate(Date outboundtoDate) {
        this.outboundtoDate = outboundtoDate;
    }

    public Integer getOutboundempid() {
        return outboundempid;
    }

    public void setOutboundempid(Integer outboundempid) {
        this.outboundempid = outboundempid;
    }

    public Date getForwardfromDate() {
        return forwardfromDate;
    }

    public void setForwardfromDate(Date forwardfromDate) {
        this.forwardfromDate = forwardfromDate;
    }

    public Date getForwardtoDate() {
        return forwardtoDate;
    }

    public void setForwardtoDate(Date forwardtoDate) {
        this.forwardtoDate = forwardtoDate;
    }

    public Integer getForwardempid() {
        return forwardempid;
    }

    public void setForwardempid(Integer forwardempid) {
        this.forwardempid = forwardempid;
    }

    public Date getConferencefromDate() {
        return conferencefromDate;
    }

    public void setConferencefromDate(Date conferencefromDate) {
        this.conferencefromDate = conferencefromDate;
    }

    public Date getConferencetoDate() {
        return conferencetoDate;
    }

    public void setConferencetoDate(Date conferencetoDate) {
        this.conferencetoDate = conferencetoDate;
    }

    public Integer getConferenceempid() {
        return conferenceempid;
    }

    public void setConferenceempid(Integer conferenceempid) {
        this.conferenceempid = conferenceempid;
    }

    public boolean isExportToCsvBtnStatus() {
        return exportToCsvBtnStatus;
    }

    public void setExportToCsvBtnStatus(boolean exportToCsvBtnStatus) {
        this.exportToCsvBtnStatus = exportToCsvBtnStatus;
    }

}
