package com.rank.ccms.dto;

import java.io.Serializable;

public class SrmBmMapDto implements Serializable {

    private Long id;
    private Long bmId;
    private Long srmId;
    private String bmName;
    private String mappedSrm;
    private String srmName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSrmId() {
        return srmId;
    }

    public void setSrmId(Long srmId) {
        this.srmId = srmId;
    }

    public String getSrmName() {
        return srmName;
    }

    public void setSrmName(String srmName) {
        this.srmName = srmName;
    }

    public Long getBmId() {
        return bmId;
    }

    public void setBmId(Long bmId) {
        this.bmId = bmId;
    }

    public String getBmName() {
        return bmName;
    }

    public void setBmName(String bmName) {
        this.bmName = bmName;
    }

    public String getMappedSrm() {
        return mappedSrm;
    }

    public void setMappedSrm(String mappedSrm) {
        this.mappedSrm = mappedSrm;
    }

}
