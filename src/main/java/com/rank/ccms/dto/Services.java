package com.rank.ccms.dto;

import java.io.Serializable;

public class Services implements Serializable {

    private Long servicePrimaryId;
    private String servicePrimaryCode;
    private String servicePrimaryName;
    private Long serviceSecondaryId;
    private String serviceSecondaryCode;
    private String serviceSecondaryName;

    public Long getServicePrimaryId() {
        return servicePrimaryId;
    }

    public void setServicePrimaryId(Long servicePrimaryId) {
        this.servicePrimaryId = servicePrimaryId;
    }

    public String getServicePrimaryCode() {
        return servicePrimaryCode;
    }

    public void setServicePrimaryCode(String servicePrimaryCode) {
        this.servicePrimaryCode = servicePrimaryCode;
    }

    public String getServicePrimaryName() {
        return servicePrimaryName;
    }

    public void setServicePrimaryName(String servicePrimaryName) {
        this.servicePrimaryName = servicePrimaryName;
    }

    public Long getServiceSecondaryId() {
        return serviceSecondaryId;
    }

    public void setServiceSecondaryId(Long serviceSecondaryId) {
        this.serviceSecondaryId = serviceSecondaryId;
    }

    public String getServiceSecondaryCode() {
        return serviceSecondaryCode;
    }

    public void setServiceSecondaryCode(String serviceSecondaryCode) {
        this.serviceSecondaryCode = serviceSecondaryCode;
    }

    public String getServiceSecondaryName() {
        return serviceSecondaryName;
    }

    public void setServiceSecondaryName(String serviceSecondaryName) {
        this.serviceSecondaryName = serviceSecondaryName;
    }

}
