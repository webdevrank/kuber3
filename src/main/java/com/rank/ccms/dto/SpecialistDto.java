package com.rank.ccms.dto;

import java.io.Serializable;

public class SpecialistDto implements Serializable {

    private long empId;
    private String loginid;
    private String agentName;

    public String getLoginid() {
        return loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public long getEmpId() {
        return empId;
    }

    public void setEmpId(long empId) {
        this.empId = empId;
    }

   

}
