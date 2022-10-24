package com.rank.ccms.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class CustomerCallDto implements Serializable{
    
    private Long id;
    private String custId;
    private String customerName;
    
    private String categoryName;
    private String serviceName;
    private String languageName;
    
    private String categoryCode;
    private String serviceCode;
    private String languageCode;
    
    private String deviceId;
    private String deviceName;
    private String deviceIP;
    private String deviceOs;
    
    private String callMedium;
    private String callStatus;
    
    private String accountNo;
    private Long cellNo;
    private String email;
    
    private Timestamp droppedCallTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Timestamp getDroppedCallTime() {
        return droppedCallTime;
    }

    public void setDroppedCallTime(Timestamp droppedCallTime) {
        this.droppedCallTime = droppedCallTime;
    }

    public String getDeviceIP() {
        return deviceIP;
    }

    public void setDeviceIP(String deviceIP) {
        this.deviceIP = deviceIP;
    }

    public String getDeviceOs() {
        return deviceOs;
    }

    public void setDeviceOs(String deviceOs) {
        this.deviceOs = deviceOs;
    }

    public String getCallMedium() {
        return callMedium;
    }

    public void setCallMedium(String callMedium) {
        this.callMedium = callMedium;
    }

    public String getCallStatus() {
        return callStatus;
    }

    public void setCallStatus(String callStatus) {
        this.callStatus = callStatus;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public Long getCellNo() {
        return cellNo;
    }

    public void setCellNo(Long cellNo) {
        this.cellNo = cellNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
