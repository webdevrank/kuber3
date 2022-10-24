package com.rank.ccms.dto;

import java.io.Serializable;
import java.util.Date;

public class CallSettingsDto implements Serializable {

    private Long Id;
    private boolean otpFlag;
    private String serviceDay;
    private String otpSelect;
    private String serviceStartTime;
    private String serviceEndTime;
    private Date startDate;
    private Date endDate;

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public boolean isOtpFlag() {
        return otpFlag;
    }

    public void setOtpFlag(boolean otpFlag) {
        this.otpFlag = otpFlag;
    }

    public String getServiceStartTime() {
        return serviceStartTime;
    }

    public void setServiceStartTime(String serviceStartTime) {
        this.serviceStartTime = serviceStartTime;
    }

    public String getServiceEndTime() {
        return serviceEndTime;
    }

    public void setServiceEndTime(String serviceEndTime) {
        this.serviceEndTime = serviceEndTime;
    }

    public String getOtpSelect() {
        return otpSelect;
    }

    public void setOtpSelect(String otpSelect) {
        this.otpSelect = otpSelect;
    }

    public String getServiceDay() {
        return serviceDay;
    }

    public void setServiceDay(String serviceDay) {
        this.serviceDay = serviceDay;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

}
