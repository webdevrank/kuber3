package com.rank.ccms.dto;

import java.io.Serializable;

public class RmSrmMapDto implements Serializable {

    private static final long serialVersionUID = -2341870260074481272L;
    private Long id;
    private Long srmId;
    private Long rmId;
    private String srmName;
    private String mappedRm;
    private String rmName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRmId() {
        return rmId;
    }

    public void setRmId(Long rmId) {
        this.rmId = rmId;
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

    public String getMappedRm() {
        return mappedRm;
    }

    public void setMappedRm(String mappedRm) {
        this.mappedRm = mappedRm;
    }

    public String getRmName() {
        return rmName;
    }

    public void setRmName(String rmName) {
        this.rmName = rmName;
    }

}
