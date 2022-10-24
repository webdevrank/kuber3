package com.rank.ccms.rest.response;

import java.io.Serializable;
import org.codehaus.jackson.annotate.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.rank.ccms.dto.PerticipentDto;

import io.swagger.annotations.ApiModelProperty;
import java.util.List;

@JsonInclude(Include.NON_NULL)
public class IncomingCallResponse implements Serializable {

    @JsonIgnore
    private static final long serialVersionUID = -1479056760382399901L;
    private String url;
    @ApiModelProperty(value = "Current call master id if it exists")
    private String callId;
    private String status;
    private String category;
    private String language;
    private String service;
    private String agentId;
    @ApiModelProperty(value = "Contains Socket URL")
    private String socketHostPublic;
    @ApiModelProperty(value = "Customer Id of the customer")
    private String customerId;
    private String custFname;
    private String custLname;
    private Long custMstId;
    private String promotionalVideo;
    private String scheduleTime;
    private String timeZone;
    private String mobileNo;
    private String customerType;
    private List<PerticipentDto> holdUnHoldDtoList;
    private String roomName;
    private String entityId;

    public IncomingCallResponse() {
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public IncomingCallResponse(String url, String roomName, String callId, String status, String category, String language, String service, String customerId) {
        this.url = url;
        this.callId = callId;
        this.status = status;
        this.category = category;
        this.language = language;
        this.service = service;
        this.customerId = customerId;
    }

    public IncomingCallResponse(String url, String callId, String status, String category, String language, String service) {
        this.url = url;
        this.callId = callId;
        this.status = status;
        this.category = category;
        this.language = language;
        this.service = service;

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    @Override
    public String toString() {
        return "IncomingCallResponse [url=" + url + ", callId=" + callId + ", status="
                + status + ", category=" + category + ", language="
                + language + ", service=" + service + ", agentId="
                + agentId + ", customerId="
                + customerId + "]";
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getSocketHostPublic() {
        return socketHostPublic;
    }

    public void setSocketHostPublic(String socketHostPublic) {
        this.socketHostPublic = socketHostPublic;
    }

    public String getCustFname() {
        return custFname;
    }

    public void setCustFname(String custFname) {
        this.custFname = custFname;
    }

    public String getCustLname() {
        return custLname;
    }

    public void setCustLname(String custLname) {
        this.custLname = custLname;
    }

    public Long getCustMstId() {
        return custMstId;
    }

    public void setCustMstId(Long custMstId) {
        this.custMstId = custMstId;
    }

    public String getPromotionalVideo() {
        return promotionalVideo;
    }

    public void setPromotionalVideo(String promotionalVideo) {
        this.promotionalVideo = promotionalVideo;
    }

    public String getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(String scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public List<PerticipentDto> getHoldUnHoldDtoList() {
        return holdUnHoldDtoList;
    }

    public void setHoldUnHoldDtoList(List<PerticipentDto> holdUnHoldDtoList) {
        this.holdUnHoldDtoList = holdUnHoldDtoList;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

}
