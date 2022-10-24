package com.rank.ccms.dto;

import java.io.Serializable;

public class PromotionalVideoDto implements Serializable {

    private Long promotionalVideoMstId;
    private String caption;
    private String fileUrl;
    private boolean activeFlg;
    private String fileName;
    private Boolean selectFlag;

    public Long getPromotionalVideoMstId() {
        return promotionalVideoMstId;
    }

    public void setPromotionalVideoMstId(Long promotionalVideoMstId) {
        this.promotionalVideoMstId = promotionalVideoMstId;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public boolean isActiveFlg() {
        return activeFlg;
    }

    public void setActiveFlg(boolean activeFlg) {
        this.activeFlg = activeFlg;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Boolean getSelectFlag() {
        return selectFlag;
    }

    public void setSelectFlag(Boolean selectFlag) {
        this.selectFlag = selectFlag;
    }

}
