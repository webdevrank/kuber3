package com.rank.ccms.dto;

import java.io.Serializable;

public class BargeInCallDto implements Serializable {

    private Long callId;
    private Long agentPkId;
    private String agentECN;
    private String agentName;
    private Long customerPkId;
    private String custId;
    private String customerName;
    private String callType;
    private Long scheduleCallId;
    private String roomUrl;
    private String customerPhone;
    private String roomKey;
    private String entityId;

    public Long getAgentPkId() {
        return agentPkId;
    }

    public void setAgentPkId(Long agentPkId) {
        this.agentPkId = agentPkId;
    }

    public Long getCallId() {
        return callId;
    }

    public void setCallId(Long callId) {
        this.callId = callId;
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

    public Long getCustomerPkId() {
        return customerPkId;
    }

    public void setCustomerPkId(Long customerPkId) {
        this.customerPkId = customerPkId;
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

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    public Long getScheduleCallId() {
        return scheduleCallId;
    }

    public void setScheduleCallId(Long scheduleCallId) {
        this.scheduleCallId = scheduleCallId;
    }

    public String getRoomUrl() {
        return roomUrl;
    }

    public void setRoomUrl(String roomUrl) {
        this.roomUrl = roomUrl;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getRoomKey() {
        return roomKey;
    }

    public void setRoomKey(String roomKey) {
        this.roomKey = roomKey;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

}
