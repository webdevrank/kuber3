package com.rank.ccms.dto;

import java.io.Serializable;

public class EmployeeCallProffDto implements Serializable {

    private Long callProffPkId;
    private Long empFkId;
    private Services serviceList;
    private Categories segmentList;
    private Languages languageList;

    public Long getCallProffPkId() {
        return callProffPkId;
    }

    public void setCallProffPkId(Long callProffPkId) {
        this.callProffPkId = callProffPkId;
    }

    public Long getEmpFkId() {
        return empFkId;
    }

    public void setEmpFkId(Long empFkId) {
        this.empFkId = empFkId;
    }

    public Services getServiceList() {
        return serviceList;
    }

    public void setServiceList(Services serviceList) {
        this.serviceList = serviceList;
    }

    public Categories getSegmentList() {
        return segmentList;
    }

    public void setSegmentList(Categories segmentList) {
        this.segmentList = segmentList;
    }

    public Languages getLanguageList() {
        return languageList;
    }

    public void setLanguageList(Languages languageList) {
        this.languageList = languageList;
    }

}
