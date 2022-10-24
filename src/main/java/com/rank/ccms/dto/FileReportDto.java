package com.rank.ccms.dto;

import java.io.Serializable;

public class FileReportDto implements Serializable {

    private String fileName;
    private String fileSendByType;
    private String fileLocation;
    private String fileSendBy;
    private String fileSendTo;
    private String uploadTime;
    private String fileCaption;
    private String fileSendToType;
    private String actualFilePath;


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public String getFileSendBy() {
        return fileSendBy;
    }

    public void setFileSendBy(String fileSendBy) {
        this.fileSendBy = fileSendBy;
    }

    public String getFileSendTo() {
        return fileSendTo;
    }

    public void setFileSendTo(String fileSendTo) {
        this.fileSendTo = fileSendTo;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getFileCaption() {
        return fileCaption;
    }

    public void setFileCaption(String fileCaption) {
        this.fileCaption = fileCaption;
    }

    public String getFileSendByType() {
        return fileSendByType;
    }

    public void setFileSendByType(String fileSendByType) {
        this.fileSendByType = fileSendByType;
    }

    public String getFileSendToType() {
        return fileSendToType;
    }

    public void setFileSendToType(String fileSendToType) {
        this.fileSendToType = fileSendToType;
    }

    public String getActualFilePath() {
        return actualFilePath;
    }

    public void setActualFilePath(String actualFilePath) {
        this.actualFilePath = actualFilePath;
    }

}
