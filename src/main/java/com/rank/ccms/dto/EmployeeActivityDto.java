package com.rank.ccms.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class EmployeeActivityDto implements Serializable {

    private Long activityId;
    private Timestamp activityEndTime;

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Timestamp getActivityEndTime() {
        return activityEndTime;
    }

    public void setActivityEndTime(Timestamp activityEndTime) {
        this.activityEndTime = activityEndTime;
    }

}
