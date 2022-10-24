package com.rank.ccms.dto;

import java.io.Serializable;
import java.util.Date;

public class ScheduleCallDto implements Serializable {

    private Long id;
    private Long scheduleCallMstId;
    private Date scheduleStartDateTime;
    private Date scheduleEndDateTime;
    private Long employeeMstId;
    private Long customerMstId;
    private String status;
    private Date createdDateTime;
    private Long callMstPkId;
    private Long service;
    private String serviceName;
    private String callType;
    private String empFirstName;
    private String empMidName;
    private String empLastName;
    private Long empCellPhone;
    private String customerName;

    private Long refAgentSchId;
    private String roomLink;
    private Long customerPkId;
    private String agentName;
    private Long schedulePkId;
    private String scheduleTime;
    private String firstName;
    private String lastName;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getScheduleCallMstId() {
        return scheduleCallMstId;
    }

    public void setScheduleCallMstId(Long scheduleCallMstId) {
        this.scheduleCallMstId = scheduleCallMstId;
    }

    public Long getEmployeeMstId() {
        return employeeMstId;
    }

    public void setEmployeeMstId(Long employeeMstId) {
        this.employeeMstId = employeeMstId;
    }

    public Long getCustomerMstId() {
        return customerMstId;
    }

    public void setCustomerMstId(Long customerMstId) {
        this.customerMstId = customerMstId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(Date createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public Long getCallMstPkId() {
        return callMstPkId;
    }

    public void setCallMstPkId(Long callMstPkId) {
        this.callMstPkId = callMstPkId;
    }

    public Long getRefAgentSchId() {
        return refAgentSchId;
    }

    public void setRefAgentSchId(Long refAgentSchId) {
        this.refAgentSchId = refAgentSchId;
    }

    public String getRoomLink() {
        return roomLink;
    }

    public void setRoomLink(String roomLink) {
        this.roomLink = roomLink;
    }

    public Long getCustomerPkId() {
        return customerPkId;
    }

    public void setCustomerPkId(Long customerPkId) {
        this.customerPkId = customerPkId;
    }

    public Long getSchedulePkId() {
        return schedulePkId;
    }

    public void setSchedulePkId(Long schedulePkId) {
        this.schedulePkId = schedulePkId;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(String scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public Date getScheduleStartDateTime() {
        return scheduleStartDateTime;
    }

    public void setScheduleStartDateTime(Date scheduleStartDateTime) {
        this.scheduleStartDateTime = scheduleStartDateTime;
    }

    public Date getScheduleEndDateTime() {
        return scheduleEndDateTime;
    }

    public void setScheduleEndDateTime(Date scheduleEndDateTime) {
        this.scheduleEndDateTime = scheduleEndDateTime;
    }

    public Long getService() {
        return service;
    }

    public void setService(Long service) {
        this.service = service;
    }

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    public String getEmpFirstName() {
        return empFirstName;
    }

    public void setEmpFirstName(String empFirstName) {
        this.empFirstName = empFirstName;
    }

    public String getEmpMidName() {
        return empMidName;
    }

    public void setEmpMidName(String empMidName) {
        this.empMidName = empMidName;
    }

    public String getEmpLastName() {
        return empLastName;
    }

    public void setEmpLastName(String empLastName) {
        this.empLastName = empLastName;
    }

    public Long getEmpCellPhone() {
        return empCellPhone;
    }

    public void setEmpCellPhone(Long empCellPhone) {
        this.empCellPhone = empCellPhone;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

}
