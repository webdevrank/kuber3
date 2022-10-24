package com.rank.ccms.dto;

import java.io.Serializable;

public class CurrentAgentStatusDto implements Serializable {

    private String agenyId;
    private String AgentName;
    private int statusBusy;
    private int statusAvailable;
    private int statusReady;
    private String statusLogout;
    private int busyOrAvailable;
    private String statusReasonBusy;
    private String statusReasonAvailable;
    private String statusReasonReady;
    private String statusTimeBusy;
    private String statusTimeAvailable;
    private String statusTimeReady;
    private String statusAgent;
    private String statusTime;

    public String getStatusReasonBusy() {
        return statusReasonBusy;
    }

    public void setStatusReasonBusy(String statusReasonBusy) {
        this.statusReasonBusy = statusReasonBusy;
    }

    public String getStatusReasonAvailable() {
        return statusReasonAvailable;
    }

    public void setStatusReasonAvailable(String statusReasonAvailable) {
        this.statusReasonAvailable = statusReasonAvailable;
    }

    public String getStatusReasonReady() {
        return statusReasonReady;
    }

    public void setStatusReasonReady(String statusReasonReady) {
        this.statusReasonReady = statusReasonReady;
    }

    public String getStatusTimeBusy() {
        return statusTimeBusy;
    }

    public void setStatusTimeBusy(String statusTimeBusy) {
        this.statusTimeBusy = statusTimeBusy;
    }

    public String getStatusTimeAvailable() {
        return statusTimeAvailable;
    }

    public void setStatusTimeAvailable(String statusTimeAvailable) {
        this.statusTimeAvailable = statusTimeAvailable;
    }

    public String getStatusTimeReady() {
        return statusTimeReady;
    }

    public void setStatusTimeReady(String statusTimeReady) {
        this.statusTimeReady = statusTimeReady;
    }

    public String getAgenyId() {
        return agenyId;
    }

    public void setAgenyId(String agenyId) {
        this.agenyId = agenyId;
    }

    public String getAgentName() {
        return AgentName;
    }

    public void setAgentName(String AgentName) {
        this.AgentName = AgentName;
    }

    public int getStatusBusy() {
        return statusBusy;
    }

    public void setStatusBusy(int statusBusy) {
        this.statusBusy = statusBusy;
    }

    public int getStatusAvailable() {
        return statusAvailable;
    }

    public void setStatusAvailable(int statusAvailable) {
        this.statusAvailable = statusAvailable;
    }

    public int getStatusReady() {
        return statusReady;
    }

    public void setStatusReady(int statusReady) {
        this.statusReady = statusReady;
    }

    public String getStatusLogout() {
        return statusLogout;
    }

    public void setStatusLogout(String statusLogout) {
        this.statusLogout = statusLogout;
    }

    public int getBusyOrAvailable() {
        return busyOrAvailable;
    }

    public void setBusyOrAvailable(int busyOrAvailable) {
        this.busyOrAvailable = busyOrAvailable;
    }

    public String getStatusAgent() {
        return statusAgent;
    }

    public void setStatusAgent(String statusAgent) {
        this.statusAgent = statusAgent;
    }

    public String getStatusTime() {
        return statusTime;
    }

    public void setStatusTime(String statusTime) {
        this.statusTime = statusTime;
    }

}
