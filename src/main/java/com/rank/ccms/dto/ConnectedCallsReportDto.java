package com.rank.ccms.dto;

import java.io.Serializable;

public class ConnectedCallsReportDto implements Serializable {

    private Long callId;
    private Long SlNo;
    private String CustomerId;
    private String CustomerName;
    private String CustomerMobile;
    private String CustomerEmail;
    private String CustomerAccountNo;
    private String ServiceType;
    private String DeviceIp;
    private String DeviceBrand;
    private String DeviceOs;
    private String StaticIp;
    private String agentECN;
    private String agentName;
    private String SegmentCode;
    private String SegmentDescription;
    private String Langauage;
    private String LanguageCd;
    private String Medium;
    private String deviceName;
    private String ServiceCode;
    private String ServiceName;
    private String CallDuration;
    private String CallType;
    private String CallStartTime;
    private String CallEndTime;
    private String pickupTime;
    private String chatText;
    private String fileDetails;

    public String getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(String pickupTime) {
        this.pickupTime = pickupTime;
    }

    public Long getCallId() {
        return callId;
    }

    public void setCallId(Long callId) {
        this.callId = callId;
    }

    public Long getSlNo() {
        return SlNo;
    }

    public void setSlNo(Long SlNo) {
        this.SlNo = SlNo;
    }

    public String getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(String CustomerId) {
        this.CustomerId = CustomerId;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String CustomerName) {
        this.CustomerName = CustomerName;
    }

    public String getCustomerMobile() {
        return CustomerMobile;
    }

    public void setCustomerMobile(String CustomerMobile) {
        this.CustomerMobile = CustomerMobile;
    }

    public String getCustomerEmail() {
        return CustomerEmail;
    }

    public void setCustomerEmail(String CustomerEmail) {
        this.CustomerEmail = CustomerEmail;
    }

    public String getCustomerAccountNo() {
        return CustomerAccountNo;
    }

    public void setCustomerAccountNo(String CustomerAccountNo) {
        this.CustomerAccountNo = CustomerAccountNo;
    }

    public String getServiceType() {
        return ServiceType;
    }

    public void setServiceType(String ServiceType) {
        this.ServiceType = ServiceType;
    }

    public String getDeviceIp() {
        return DeviceIp;
    }

    public void setDeviceIp(String DeviceIp) {
        this.DeviceIp = DeviceIp;
    }

    public String getDeviceBrand() {
        return DeviceBrand;
    }

    public void setDeviceBrand(String DeviceBrand) {
        this.DeviceBrand = DeviceBrand;
    }

    public String getDeviceOs() {
        return DeviceOs;
    }

    public void setDeviceOs(String DeviceOs) {
        this.DeviceOs = DeviceOs;
    }

    public String getStaticIp() {
        return StaticIp;
    }

    public void setStaticIp(String StaticIp) {
        this.StaticIp = StaticIp;
    }

    public String getAgentECN() {
        return agentECN;
    }

    public void setAgentECN(String agentECN) {
        this.agentECN = agentECN;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getSegmentCode() {
        return SegmentCode;
    }

    public void setSegmentCode(String SegmentCode) {
        this.SegmentCode = SegmentCode;
    }

    public String getSegmentDescription() {
        return SegmentDescription;
    }

    public void setSegmentDescription(String SegmentDescription) {
        this.SegmentDescription = SegmentDescription;
    }

    public String getLanguageCd() {
        return LanguageCd;
    }

    public void setLanguageCd(String LanguageCd) {
        this.LanguageCd = LanguageCd;
    }

    public String getLangauage() {
        return Langauage;
    }

    public void setLangauage(String Langauage) {
        this.Langauage = Langauage;
    }

    public String getMedium() {
        return Medium;
    }

    public void setMedium(String Medium) {
        this.Medium = Medium;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getServiceCode() {
        return ServiceCode;
    }

    public void setServiceCode(String ServiceCode) {
        this.ServiceCode = ServiceCode;
    }

    public String getServiceName() {
        return ServiceName;
    }

    public void setServiceName(String ServiceName) {
        this.ServiceName = ServiceName;
    }

    public String getCallDuration() {
        return CallDuration;
    }

    public void setCallDuration(String CallDuration) {
        this.CallDuration = CallDuration;
    }

    public String getCallType() {
        return CallType;
    }

    public void setCallType(String CallType) {
        this.CallType = CallType;
    }

    public String getCallStartTime() {
        return CallStartTime;
    }

    public void setCallStartTime(String CallStartTime) {
        this.CallStartTime = CallStartTime;
    }

    public String getCallEndTime() {
        return CallEndTime;
    }

    public void setCallEndTime(String CallEndTime) {
        this.CallEndTime = CallEndTime;
    }

    public String getChatText() {
        return chatText;
    }

    public void setChatText(String chatText) {
        this.chatText = chatText;
    }

    public String getFileDetails() {
        return fileDetails;
    }

    public void setFileDetails(String fileDetails) {
        this.fileDetails = fileDetails;
    }

}
