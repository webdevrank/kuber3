package com.rank.ccms.dto;

public class FileDownloadDto {

    private String uploadedFilePath;
    private String fileName;
    private String downloadCss;

    public String getUploadedFilePath() {
        return uploadedFilePath;
    }

    public void setUploadedFilePath(String uploadedFilePath) {
        this.uploadedFilePath = uploadedFilePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDownloadCss() {
        return downloadCss;
    }

    public void setDownloadCss(String downloadCss) {
        this.downloadCss = downloadCss;
    }

}
