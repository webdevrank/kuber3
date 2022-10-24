package com.rank.ccms.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class RecordingData implements Serializable {

    private long id;

    private String recorderID;
    private String customerID;

    private Long employeeID;
    private Long custID;
    private Long serviceID;
    private Long skillID;
    private Long categoryID;

    private Timestamp recordStartTime;
    private Timestamp recordEndTime;

    private String employeeName;
    private String agentECN;
    private String startTime;
    private String endTime;
    private String custId;
    private String customerMobile;
    private String customerEmail;
    private String customerName;
    private String categoryName;
    private String categoryDesc;
    private String serviceName;
    private String serviceDesc;
    private String skillName;
    private String skillDesc;
    private String callType;
    private String medium;
    private String deviceName;
    private String deviceId;
    private String deviceIp;
    private String deviceOs;
    private String callDuration;
    private String playbackLink;
    private String chatText;
    private boolean renderDownloadLink = false;
    private String fileDetails;
    private Long callMstId;
    private List<String> playBackLinkList = new ArrayList<>();

    private String accountNo;

    public String getAgentECN() {
        return agentECN;
    }

    public void setAgentECN(String agentECN) {
        this.agentECN = agentECN;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
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

    public String getCategoryDesc() {
        return categoryDesc;
    }

    public void setCategoryDesc(String categoryDesc) {
        this.categoryDesc = categoryDesc;
    }

    public String getServiceDesc() {
        return serviceDesc;
    }

    public void setServiceDesc(String serviceDesc) {
        this.serviceDesc = serviceDesc;
    }

    public String getSkillDesc() {
        return skillDesc;
    }

    public void setSkillDesc(String skillDesc) {
        this.skillDesc = skillDesc;
    }

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceIp() {
        return deviceIp;
    }

    public void setDeviceIp(String deviceIp) {
        this.deviceIp = deviceIp;
    }

    public String getDeviceOs() {
        return deviceOs;
    }

    public void setDeviceOs(String deviceOs) {
        this.deviceOs = deviceOs;
    }

    public String getCallDuration() {
        return callDuration;
    }

    public void setCallDuration(String callDuration) {
        this.callDuration = callDuration;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
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

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getPlaybackLink() {
        return playbackLink;
    }

    public void setPlaybackLink(String playbackLink) {
        this.playbackLink = playbackLink;
    }

    public Long getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Long employeeID) {
        this.employeeID = employeeID;
    }

    public Long getCustID() {
        return custID;
    }

    public void setCustID(Long custID) {
        this.custID = custID;
    }

    public Long getServiceID() {
        return serviceID;
    }

    public void setServiceID(Long serviceID) {
        this.serviceID = serviceID;
    }

    public Long getSkillID() {
        return skillID;
    }

    public void setSkillID(Long skillID) {
        this.skillID = skillID;
    }

    public Long getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Long categoryID) {
        this.categoryID = categoryID;
    }

    public Timestamp getRecordStartTime() {
        return recordStartTime;
    }

    public void setRecordStartTime(Timestamp recordStartTime) {
        this.recordStartTime = recordStartTime;
    }

    public Timestamp getRecordEndTime() {
        return recordEndTime;
    }

    public void setRecordEndTime(Timestamp recordEndTime) {
        this.recordEndTime = recordEndTime;
    }

    public String getRecorderID() {
        return recorderID;
    }

    public void setRecorderID(String recorderID) {
        this.recorderID = recorderID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getChatText() {
        return chatText;
    }

    public void setChatText(String chatText) {
        this.chatText = chatText;
    }

    public boolean isRenderDownloadLink() {
        return renderDownloadLink;
    }

    public void setRenderDownloadLink(boolean renderDownloadLink) {
        this.renderDownloadLink = renderDownloadLink;
    }

    public Long getCallMstId() {
        return callMstId;
    }

    public void setCallMstId(Long callMstId) {
        this.callMstId = callMstId;
    }

    public String getFileDetails() {
        return fileDetails;
    }

    public void setFileDetails(String fileDetails) {
        this.fileDetails = fileDetails;
    }

    public List<String> getPlayBackLinkList() {
        return playBackLinkList;
    }

    public void setPlayBackLinkList(List<String> playBackLinkList) {
        this.playBackLinkList = playBackLinkList;
    }

}
