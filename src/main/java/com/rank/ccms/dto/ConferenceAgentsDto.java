package com.rank.ccms.dto;

import java.io.Serializable;

public class ConferenceAgentsDto implements Serializable {

    private String agentName;
    private Long agentId;
    private Long empId;
    private String empFirstName;
    private String empMidName;
    private String empLastName;
    private String empLoginId;
    private Long callDtlsId;
    private Long empTypeId;
    private String empTypeName;

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
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

    public Long getCallDtlsId() {
        return callDtlsId;
    }

    public void setCallDtlsId(Long callDtlsId) {
        this.callDtlsId = callDtlsId;
    }

    public Long getEmpTypeId() {
        return empTypeId;
    }

    public void setEmpTypeId(Long empTypeId) {
        this.empTypeId = empTypeId;
    }

    public String getEmpTypeName() {
        return empTypeName;
    }

    public void setEmpTypeName(String empTypeName) {
        this.empTypeName = empTypeName;
    }

    public String getEmpLoginId() {
        return empLoginId;
    }

    public void setEmpLoginId(String empLoginId) {
        this.empLoginId = empLoginId;
    }

}
