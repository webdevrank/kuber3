package com.rank.ccms.dto;

import java.io.Serializable;
import java.util.Date;

public class CurrentScheduledCallStatus implements Serializable {

    private String scheduleDate;
    private String scheduleTime;
    private Date scheduleDateTimestamp;
    private Date scheduleTimeTimestamp;
    private String customerName;
    private String customerMobile;
    private String customerEmail;
    private String customerAccount;
    private String customerSeg;
    private String rmMobile;
    private Long AgentId;
    private String AgentName;
    private String AgentStatus;
    private String agentUserId;
    private Long id;
    private String RmOrBm;
    private String rmBmMobile;
    private String rmBmEmail;
    private Date presentDate;
    private String additionalStatus;
    private String serviceCd;
    private String serviceDesc;
    private String segmentCd;
    private String segmentDesc;
    private String languageCd;
    private String languageDesc;
    

   
    
    public String getServiceCd() {
        return serviceCd;
    }

    public void setServiceCd(String serviceCd) {
        this.serviceCd = serviceCd;
    }

    public String getServiceDesc() {
        return serviceDesc;
    }

    public void setServiceDesc(String serviceDesc) {
        this.serviceDesc = serviceDesc;
    }

    public String getSegmentCd() {
        return segmentCd;
    }

    public void setSegmentCd(String segmentCd) {
        this.segmentCd = segmentCd;
    }

    public String getSegmentDesc() {
        return segmentDesc;
    }

    public void setSegmentDesc(String segmentDesc) {
        this.segmentDesc = segmentDesc;
    }

    public String getLanguageCd() {
        return languageCd;
    }

    public void setLanguageCd(String languageCd) {
        this.languageCd = languageCd;
    }

    public String getLanguageDesc() {
        return languageDesc;
    }

    public void setLanguageDesc(String languageDesc) {
        this.languageDesc = languageDesc;
    }
    

    
    public String getAdditionalStatus() {
        return additionalStatus;
    }

    public void setAdditionalStatus(String additionalStatus) {
        this.additionalStatus = additionalStatus;
    }
    

    public String getCustomerAccount() {
        return customerAccount;
    }

    public void setCustomerAccount(String customerAccount) {
        this.customerAccount = customerAccount;
    }

    public String getCustomerSeg() {
        return customerSeg;
    }

    public void setCustomerSeg(String customerSeg) {
        this.customerSeg = customerSeg;
    }

    public Date getScheduleDateTimestamp() {
        return scheduleDateTimestamp;
    }

    public void setScheduleDateTimestamp(Date scheduleDateTimestamp) {
        this.scheduleDateTimestamp = scheduleDateTimestamp;
    }

    public Date getScheduleTimeTimestamp() {
        return scheduleTimeTimestamp;
    }

    public void setScheduleTimeTimestamp(Date scheduleTimeTimestamp) {
        this.scheduleTimeTimestamp = scheduleTimeTimestamp;
    }

    public String getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(String scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public String getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(String scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getRmMobile() {
        return rmMobile;
    }

    public void setRmMobile(String rmMobile) {
        this.rmMobile = rmMobile;
    }

    public Long getAgentId() {
        return AgentId;
    }

    public void setAgentId(Long AgentId) {
        this.AgentId = AgentId;
    }

    public String getAgentName() {
        return AgentName;
    }

    public void setAgentName(String AgentName) {
        this.AgentName = AgentName;
    }

    public String getAgentStatus() {
        return AgentStatus;
    }

    public void setAgentStatus(String AgentStatus) {
        this.AgentStatus = AgentStatus;
    }

    public String getAgentUserId() {
        return agentUserId;
    }

    public void setAgentUserId(String agentUserId) {
        this.agentUserId = agentUserId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getRmOrBm() {
        return RmOrBm;
    }

    public void setRmOrBm(String RmOrBm) {
        this.RmOrBm = RmOrBm;
    }

    public String getRmBmMobile() {
        return rmBmMobile;
    }

    public void setRmBmMobile(String rmBmMobile) {
        this.rmBmMobile = rmBmMobile;
    }

    public String getRmBmEmail() {
        return rmBmEmail;
    }

    public void setRmBmEmail(String rmBmEmail) {
        this.rmBmEmail = rmBmEmail;
    }

    public Date getPresentDate() {
        return presentDate;
    }

    public void setPresentDate(Date presentDate) {
        this.presentDate = presentDate;
    }

}
