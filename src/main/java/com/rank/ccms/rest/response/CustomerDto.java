package com.rank.ccms.rest.response;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class CustomerDto implements Serializable {

    @JsonIgnore
    private static final long serialVersionUID = 486620286337912737L;

    private Long id;
    private String firstName;
    private String midName;
    private String lastName;
    private String email;
    private String customerName;
    private String promotionalVideo;
    private String cellPhone;
    private String custId;
    private String custName;
    private String custSocket;
    private String socketHostPublic;
    private String timeZone;
    private String otp;
    private String segment;
    private String callMedium;
    private String incommingCallId;
    private String language;
    private String service;
    private String deviceBrand;
    private String deviceOs;
    private String deviceName;
    private String imeiNo;
    private String deviceIp;
    private String deviceId;
    private String staticIp;
    private String token;
    private String callId;
    private String status;
    private String category;
    private String resourceId;
    private String agentId;
    private boolean resStatus;
    private String resMessage;
    private List<String> ServiceList;
    private List<String> catogeryList;
    private List<String> languageList;
    private String downMessage;
    private String downMessageTitle;
    private String resFileURL;
    private String qualityQuery;
    private String abilityQuery;
    private String recommendQuery;
    private Boolean serverStatus;
    private String policyNumber;
    private String latitude;
    private String longitude;
    private String fieldAgentName;
    private String customerMstId;
    private String password;
    private Long fieldAgentId;
    private String schDateTime;
    private String nationality;
    private String scheduleId;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustSocket() {
        return custSocket;
    }

    public void setCustSocket(String custSocket) {
        this.custSocket = custSocket;
    }

    public String getSocketHostPublic() {
        return socketHostPublic;
    }

    public void setSocketHostPublic(String socketHostPublic) {
        this.socketHostPublic = socketHostPublic;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public String getCallMedium() {
        return callMedium;
    }

    public void setCallMedium(String callMedium) {
        this.callMedium = callMedium;
    }

    public String getIncommingCallId() {
        return incommingCallId;
    }

    public void setIncommingCallId(String incommingCallId) {
        this.incommingCallId = incommingCallId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getDeviceBrand() {
        return deviceBrand;
    }

    public void setDeviceBrand(String deviceBrand) {
        this.deviceBrand = deviceBrand;
    }

    public String getDeviceOs() {
        return deviceOs;
    }

    public void setDeviceOs(String deviceOs) {
        this.deviceOs = deviceOs;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getImeiNo() {
        return imeiNo;
    }

    public void setImeiNo(String imeiNo) {
        this.imeiNo = imeiNo;
    }

    public String getDeviceIp() {
        return deviceIp;
    }

    public void setDeviceIp(String deviceIp) {
        this.deviceIp = deviceIp;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getStaticIp() {
        return staticIp;
    }

    public void setStaticIp(String staticIp) {
        this.staticIp = staticIp;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCallId() {
        return callId;
    }

    public void setCallId(String callId) {
        this.callId = callId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public boolean isResStatus() {
        return resStatus;
    }

    public void setResStatus(boolean resStatus) {
        this.resStatus = resStatus;
    }

    public String getResMessage() {
        return resMessage;
    }

    public void setResMessage(String resMessage) {
        this.resMessage = resMessage;
    }

    public List<String> getServiceList() {
        return ServiceList;
    }

    public void setServiceList(List<String> ServiceList) {
        this.ServiceList = ServiceList;
    }

    public List<String> getCatogeryList() {
        return catogeryList;
    }

    public void setCatogeryList(List<String> catogeryList) {
        this.catogeryList = catogeryList;
    }

    public List<String> getLanguageList() {
        return languageList;
    }

    public void setLanguageList(List<String> languageList) {
        this.languageList = languageList;
    }

    public String getDownMessage() {
        return downMessage;
    }

    public void setDownMessage(String downMessage) {
        this.downMessage = downMessage;
    }

    public String getResFileURL() {
        return resFileURL;
    }

    public void setResFileURL(String resFileURL) {
        this.resFileURL = resFileURL;
    }

    public String getPromotionalVideo() {
        return promotionalVideo;
    }

    public void setPromotionalVideo(String promotionalVideo) {
        this.promotionalVideo = promotionalVideo;
    }

    public String getQualityQuery() {
        return qualityQuery;
    }

    public void setQualityQuery(String qualityQuery) {
        this.qualityQuery = qualityQuery;
    }

    public String getAbilityQuery() {
        return abilityQuery;
    }

    public void setAbilityQuery(String abilityQuery) {
        this.abilityQuery = abilityQuery;
    }

    public String getRecommendQuery() {
        return recommendQuery;
    }

    public void setRecommendQuery(String recommendQuery) {
        this.recommendQuery = recommendQuery;
    }

    public String getDownMessageTitle() {
        return downMessageTitle;
    }

    public void setDownMessageTitle(String downMessageTitle) {
        this.downMessageTitle = downMessageTitle;
    }

    public Boolean getServerStatus() {
        return serverStatus;
    }

    public void setServerStatus(Boolean serverStatus) {
        this.serverStatus = serverStatus;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * @return the fieldAgentName
     */
    public String getFieldAgentName() {
        return fieldAgentName;
    }

    /**
     * @param fieldAgentName the fieldAgentName to set
     */
    public void setFieldAgentName(String fieldAgentName) {
        this.fieldAgentName = fieldAgentName;
    }

    /**
     * @return the customerMstId
     */
    public String getCustomerMstId() {
        return customerMstId;
    }

    /**
     * @param customerMstId the customerMstId to set
     */
    public void setCustomerMstId(String customerMstId) {
        this.customerMstId = customerMstId;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the midName
     */
    public String getMidName() {
        return midName;
    }

    /**
     * @param midName the midName to set
     */
    public void setMidName(String midName) {
        this.midName = midName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the fieldAgentId
     */
    public Long getFieldAgentId() {
        return fieldAgentId;
    }

    /**
     * @param fieldAgentId the fieldAgentId to set
     */
    public void setFieldAgentId(Long fieldAgentId) {
        this.fieldAgentId = fieldAgentId;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the schDateTime
     */
    public String getSchDateTime() {
        return schDateTime;
    }

    /**
     * @param schDateTime the schDateTime to set
     */
    public void setSchDateTime(String schDateTime) {
        this.schDateTime = schDateTime;
    }

    @Override
    public String toString() {
        return "CustomerDto [firstName=" + firstName + ", midName=" + midName + ", lastName=" + lastName + ", email="
                + email + ", cellPhone=" + cellPhone + ", custId=" + custId + ", custName=" + custName + ", custSocket="
                + custSocket + ", socketHostPublic=" + socketHostPublic + ", language=" + language + ", service="
                + service + ", token=" + token + ", callId=" + callId + ", category=" + category + ", resourceId="
                + resourceId + ", agentId=" + agentId + ", customerMstId=" + customerMstId + ", password=" + password
                + "]";
    }

    /**
     * @return the nationality
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * @param nationality the nationality to set
     */
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    /**
     * @return the scheduleId
     */
    public String getScheduleId() {
        return scheduleId;
    }

    /**
     * @param scheduleId the scheduleId to set
     */
    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
