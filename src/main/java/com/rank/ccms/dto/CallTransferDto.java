package com.rank.ccms.dto;

import java.io.Serializable;

public class CallTransferDto implements Serializable {

    private Long id;
    private Long callId;
    private String custId;
    private String customerName;
    private String categoryName;
    private Long cellNo;
    private String deviceId;
    private String deviceOs;
    private String deviceIp;
    private String deviceName;
    private String fromService;
    private String fromServiceCd;
    private String toServiceCd;
    private String toService;
    private String fromEmployee;
    private String toEmployee;
    private String toEmployeeECN;
    private String fromEmployeeECN;
    private String sourceStartTime;
    private String forwardTime;
    private String customerAccountNo;
    private String customerEmail;
    private String customerSeg;
    private String customerSegDesc;
    private String customerLanguage;
    private String customerLanguageCode;
    private String callMedium;
    private String forwardStartTime;
    private String forwardEndTime;
    private String forwardCallDuration;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCallId() {
        return callId;
    }

    public void setCallId(Long callId) {
        this.callId = callId;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getCellNo() {
        return cellNo;
    }

    public void setCellNo(Long cellNo) {
        this.cellNo = cellNo;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceOs() {
        return deviceOs;
    }

    public void setDeviceOs(String deviceOs) {
        this.deviceOs = deviceOs;
    }

    public String getDeviceIp() {
        return deviceIp;
    }

    public void setDeviceIp(String deviceIp) {
        this.deviceIp = deviceIp;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getFromService() {
        return fromService;
    }

    public void setFromService(String fromService) {
        this.fromService = fromService;
    }

    public String getFromServiceCd() {
        return fromServiceCd;
    }

    public void setFromServiceCd(String fromServiceCd) {
        this.fromServiceCd = fromServiceCd;
    }

    public String getToServiceCd() {
        return toServiceCd;
    }

    public void setToServiceCd(String toServiceCd) {
        this.toServiceCd = toServiceCd;
    }

    public String getToService() {
        return toService;
    }

    public void setToService(String toService) {
        this.toService = toService;
    }

    public String getFromEmployee() {
        return fromEmployee;
    }

    public void setFromEmployee(String fromEmployee) {
        this.fromEmployee = fromEmployee;
    }

    public String getToEmployee() {
        return toEmployee;
    }

    public void setToEmployee(String toEmployee) {
        this.toEmployee = toEmployee;
    }

    public String getToEmployeeECN() {
        return toEmployeeECN;
    }

    public void setToEmployeeECN(String toEmployeeECN) {
        this.toEmployeeECN = toEmployeeECN;
    }

    public String getFromEmployeeECN() {
        return fromEmployeeECN;
    }

    public void setFromEmployeeECN(String fromEmployeeECN) {
        this.fromEmployeeECN = fromEmployeeECN;
    }

    public String getSourceStartTime() {
        return sourceStartTime;
    }

    public void setSourceStartTime(String sourceStartTime) {
        this.sourceStartTime = sourceStartTime;
    }

    public String getForwardTime() {
        return forwardTime;
    }

    public void setForwardTime(String forwardTime) {
        this.forwardTime = forwardTime;
    }

    public String getCustomerAccountNo() {
        return customerAccountNo;
    }

    public void setCustomerAccountNo(String customerAccountNo) {
        this.customerAccountNo = customerAccountNo;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerSeg() {
        return customerSeg;
    }

    public void setCustomerSeg(String customerSeg) {
        this.customerSeg = customerSeg;
    }

    public String getCustomerSegDesc() {
        return customerSegDesc;
    }

    public void setCustomerSegDesc(String customerSegDesc) {
        this.customerSegDesc = customerSegDesc;
    }

    public String getCustomerLanguage() {
        return customerLanguage;
    }

    public void setCustomerLanguage(String customerLanguage) {
        this.customerLanguage = customerLanguage;
    }

    public String getCustomerLanguageCode() {
        return customerLanguageCode;
    }

    public void setCustomerLanguageCode(String customerLanguageCode) {
        this.customerLanguageCode = customerLanguageCode;
    }

    public String getCallMedium() {
        return callMedium;
    }

    public void setCallMedium(String callMedium) {
        this.callMedium = callMedium;
    }

    public String getForwardStartTime() {
        return forwardStartTime;
    }

    public void setForwardStartTime(String forwardStartTime) {
        this.forwardStartTime = forwardStartTime;
    }

    public String getForwardEndTime() {
        return forwardEndTime;
    }

    public void setForwardEndTime(String forwardEndTime) {
        this.forwardEndTime = forwardEndTime;
    }

    public String getForwardCallDuration() {
        return forwardCallDuration;
    }

    public void setForwardCallDuration(String forwardCallDuration) {
        this.forwardCallDuration = forwardCallDuration;
    }

}
