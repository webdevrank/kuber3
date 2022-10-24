package com.rank.ccms.dto;

import java.io.Serializable;
import java.util.Date;

public class DownTimeDto implements Serializable {

    private Long Id;
    private String downStartTime;
    private String downEndTime;
    private Date downStartDate;
    private Date downEndDate;
    private Boolean renderEdit;
    private String reason;

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public String getDownStartTime() {
        return downStartTime;
    }

    public void setDownStartTime(String downStartTime) {
        this.downStartTime = downStartTime;
    }

    public String getDownEndTime() {
        return downEndTime;
    }

    public void setDownEndTime(String downEndTime) {
        this.downEndTime = downEndTime;
    }

    public Date getDownStartDate() {
        return downStartDate;
    }

    public void setDownStartDate(Date downStartDate) {
        this.downStartDate = downStartDate;
    }

    public Date getDownEndDate() {
        return downEndDate;
    }

    public void setDownEndDate(Date downEndDate) {
        this.downEndDate = downEndDate;
    }

    public Boolean getRenderEdit() {
        return renderEdit;
    }

    public void setRenderEdit(Boolean renderEdit) {
        this.renderEdit = renderEdit;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

}
