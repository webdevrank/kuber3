package com.rank.ccms.rest.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScheduleDetailsResponse {

    private Long id;
    private String startDateText;
    private String startTimeText;
    private String endDateText;
    private String endTimeText;
    private String customerName;
    private String callType;
    private String serviceName;
    private String roomId;
    private String roomName;
    private Long callMstId;
    private String custLoginId;
    private String agentLoginId;
    private String agentName;
    private String message;
    private Long serviceMstId;

    public Long getServiceMstId() {
        return serviceMstId;
    }

    public void setServiceMstId(Long serviceMstId) {
        this.serviceMstId = serviceMstId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAgentLoginId() {
        return agentLoginId;
    }

    public void setAgentLoginId(String agentLoginId) {
        this.agentLoginId = agentLoginId;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getCustLoginId() {
        return custLoginId;
    }

    public void setCustLoginId(String custLoginId) {
        this.custLoginId = custLoginId;
    }

    public Long getCallMstId() {
        return callMstId;
    }

    public void setCallMstId(Long callMstId) {
        this.callMstId = callMstId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStartDateText() {
        return startDateText;
    }

    public void setStartDateText(String startDateText) {
        this.startDateText = startDateText;
    }

    public String getStartTimeText() {
        return startTimeText;
    }

    public void setStartTimeText(String startTimeText) {
        this.startTimeText = startTimeText;
    }

    public String getEndDateText() {
        return endDateText;
    }

    public void setEndDateText(String endDateText) {
        this.endDateText = endDateText;
    }

    public String getEndTimeText() {
        return endTimeText;
    }

    public void setEndTimeText(String endTimeText) {
        this.endTimeText = endTimeText;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

}
