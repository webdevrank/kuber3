package com.rank.ccms.dto;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

public class AgentStatusDto implements Serializable {

    private BigInteger employeeid;
    private String loginid;
    private String activity;
    private Timestamp starttime;
    private String agentfullname;
    private Integer statusbusy;
    private Integer statusavailable;
    private Integer statusready;
    private String statusagent;

    public BigInteger getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(BigInteger employeeid) {
        this.employeeid = employeeid;
    }

    public String getLoginid() {
        return loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public Timestamp getStarttime() {
        return starttime;
    }

    public void setStarttime(Timestamp starttime) {
        this.starttime = starttime;
    }

    public String getAgentfullname() {
        return agentfullname;
    }

    public void setAgentfullname(String agentfullname) {
        this.agentfullname = agentfullname;
    }

    public Integer getStatusbusy() {
        return statusbusy;
    }

    public void setStatusbusy(Integer statusbusy) {
        this.statusbusy = statusbusy;
    }

    public Integer getStatusavailable() {
        return statusavailable;
    }

    public void setStatusavailable(Integer statusavailable) {
        this.statusavailable = statusavailable;
    }

    public Integer getStatusready() {
        return statusready;
    }

    public void setStatusready(Integer statusready) {
        this.statusready = statusready;
    }

    public String getStatusagent() {
        return statusagent;
    }

    public void setStatusagent(String statusagent) {
        this.statusagent = statusagent;
    }

}
