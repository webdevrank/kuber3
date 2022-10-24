package com.rank.ccms.dto;

import java.io.Serializable;

public class CustomerDetails implements Serializable {

    private Long id;
    private String agentName;
    private String agentEcn;
    private String firstName;
    private String lastName;

    private Long serviceId;
    private String serviceCd;
    private String serviceName;
    private String langCd;
    private String langName;
    private String categoryName;

    private String custid;
    private String accountNo;
    private String cellPhone;
    private String customerAccountNo;
    private String customerName;
    private String customerMobile;
    private String customerEmail;
    private String customerSegmentCode;
    private String customerSegmentDescription;
    private String customerLangauage;
    private String callMedium;
    private String deviceId;
    private String deviceName;
    private String deviceOs;
    private String deviceIp;

    private int numberOfAbandonCalls;
    private int numberOfDroppedCalls;
    private int numberOfCalls;

    private String registrationStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAgentEcn() {
        return agentEcn;
    }

    public void setAgentEcn(String agentEcn) {
        this.agentEcn = agentEcn;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
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

    public String getRegistrationStatus() {
        return registrationStatus;
    }

    public void setRegistrationStatus(String registrationStatus) {
        this.registrationStatus = registrationStatus;
    }

    public int getNumberOfCalls() {
        return numberOfCalls;
    }

    public void setNumberOfCalls(int numberOfCalls) {
        this.numberOfCalls = numberOfCalls;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getCustid() {
        return custid;
    }

    public void setCustid(String custid) {
        this.custid = custid;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getCustomerAccountNo() {
        return customerAccountNo;
    }

    public void setCustomerAccountNo(String customerAccountNo) {
        this.customerAccountNo = customerAccountNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public String getCustomerSegmentCode() {
        return customerSegmentCode;
    }

    public void setCustomerSegmentCode(String customerSegmentCode) {
        this.customerSegmentCode = customerSegmentCode;
    }

    public String getCustomerSegmentDescription() {
        return customerSegmentDescription;
    }

    public void setCustomerSegmentDescription(String customerSegmentDescription) {
        this.customerSegmentDescription = customerSegmentDescription;
    }

    public String getCustomerLangauage() {
        return customerLangauage;
    }

    public void setCustomerLangauage(String customerLangauage) {
        this.customerLangauage = customerLangauage;
    }

    public String getCallMedium() {
        return callMedium;
    }

    public void setCallMedium(String callMedium) {
        this.callMedium = callMedium;
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

    public String getLangCd() {
        return langCd;
    }

    public void setLangCd(String langCd) {
        this.langCd = langCd;
    }

    public String getLangName() {
        return langName;
    }

    public void setLangName(String langName) {
        this.langName = langName;
    }

    public String getServiceCd() {
        return serviceCd;
    }

    public void setServiceCd(String serviceCd) {
        this.serviceCd = serviceCd;
    }

    public int getNumberOfAbandonCalls() {
        return numberOfAbandonCalls;
    }

    public void setNumberOfAbandonCalls(int numberOfAbandonCalls) {
        this.numberOfAbandonCalls = numberOfAbandonCalls;
    }

    public int getNumberOfDroppedCalls() {
        return numberOfDroppedCalls;
    }

    public void setNumberOfDroppedCalls(int numberOfDroppedCalls) {
        this.numberOfDroppedCalls = numberOfDroppedCalls;
    }

}
