package com.rank.ccms.rest.response;

import java.io.Serializable;
import java.util.List;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class FileHandleDto implements Serializable {

    @JsonIgnore
    private static final long serialVersionUID = -3598925337441864675L;

    private org.json.JSONArray conferenceUsersDtoList;
    private MultipartFile file;
    private String fileName;
    private Long empId;
    private String custId;
    private Long callId;
    private String fileSendByType;
    private String documentTitle;
    private String participantType;
    private List<String> filePath;
    private String filePaths;

    public org.json.JSONArray getConferenceUsersDtoList() {
        return conferenceUsersDtoList;
    }

    public void setConferenceUsersDtoList(org.json.JSONArray conferenceUsersDtoList) {
        this.conferenceUsersDtoList = conferenceUsersDtoList;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getFileSendByType() {
        return fileSendByType;
    }

    public void setFileSendByType(String fileSendByType) {
        this.fileSendByType = fileSendByType;
    }

    public String getDocumentTitle() {
        return documentTitle;
    }

    public void setDocumentTitle(String documentTitle) {
        this.documentTitle = documentTitle;
    }

    public String getParticipantType() {
        return participantType;
    }

    public void setParticipantType(String participantType) {
        this.participantType = participantType;
    }

    public List<String> getFilePath() {
        return filePath;
    }

    public void setFilePath(List<String> filePath) {
        this.filePath = filePath;
    }

    public Long getCallId() {
        return callId;
    }

    public void setCallId(Long callId) {
        this.callId = callId;
    }

    public String getFilePaths() {
        return filePaths;
    }

    public void setFilePaths(String filePaths) {
        this.filePaths = filePaths;
    }

    @Override
    public String toString() {
        return "FileHandleDto [conferenceUsersDtoList=" + conferenceUsersDtoList + ", fileName=" + fileName
                + ", empId=" + empId + ", custId=" + custId + ", callMstId=" + callId + ", fileSendByType="
                + fileSendByType + ", documentTitle=" + documentTitle + ", participantType=" + participantType
                + ", filePath=" + filePath + "]";
    }

}
