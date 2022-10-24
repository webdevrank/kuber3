package com.rank.ccms.dto;

import java.io.Serializable;

public class CallEmployeeMap implements Serializable {

    private Long employeeId;
    private Long callId;
    private Long scheduleCallId;
    private String callType;
    private String custId;
    private String roomLink;

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
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

    public Long getScheduleCallId() {
        return scheduleCallId;
    }

    public void setScheduleCallId(Long scheduleCallId) {
        this.scheduleCallId = scheduleCallId;
    }

    public String getRoomLink() {
        return roomLink;
    }

    public void setRoomLink(String roomLink) {
        this.roomLink = roomLink;
    }

    @Override
    public String toString() {
        return "CallEmployeeMap{" + "employeeId=" + employeeId + ", callId=" + callId + ", scheduleCallId=" + scheduleCallId + ", callType=" + callType + ", custId=" + custId + '}';
    }

}
