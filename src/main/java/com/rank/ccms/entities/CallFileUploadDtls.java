package com.rank.ccms.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "call_file_upload_dtls")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CallFileUploadDtls.findAll", query = "SELECT c FROM CallFileUploadDtls c"),
    @NamedQuery(name = "CallFileUploadDtls.findById", query = "SELECT c FROM CallFileUploadDtls c WHERE c.id = :id"),
    @NamedQuery(name = "CallFileUploadDtls.findByFilePath", query = "SELECT c FROM CallFileUploadDtls c WHERE c.filePath = :filePath"),
    @NamedQuery(name = "CallFileUploadDtls.findByFileSentBy", query = "SELECT c FROM CallFileUploadDtls c WHERE c.fileSentBy = :fileSentBy"),
    @NamedQuery(name = "CallFileUploadDtls.findByFileReceivedBy", query = "SELECT c FROM CallFileUploadDtls c WHERE c.fileReceivedBy = :fileReceivedBy"),
    @NamedQuery(name = "CallFileUploadDtls.findByCreatedBy", query = "SELECT c FROM CallFileUploadDtls c WHERE c.createdBy = :createdBy"),
    @NamedQuery(name = "CallFileUploadDtls.findByCreatedDateTime", query = "SELECT c FROM CallFileUploadDtls c WHERE c.createdDateTime = :createdDateTime")})
public class CallFileUploadDtls implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "file_path")
    private String filePath;
    @Basic(optional = false)
    @NotNull
    @Column(name = "file_sent_by")
    private long fileSentBy;
    @Basic(optional = false)
    @NotNull
    @Column(name = "file_received_by")
    private long fileReceivedBy;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_by")
    private long createdBy;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDateTime;
    @Basic(optional = false)
    @JoinColumn(name = "call_mst_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CallMst callMstId;
    @NotNull
    @Column(name = "document_title")
    private String docTitle;
    @NotNull
    @Column(name = "file_sentby_type")
    private String fileSentbyType;
    @NotNull
    @Column(name = "file_receivedby_type")
    private String fileReceivedbyType;

    public CallFileUploadDtls() {
    }

    public CallFileUploadDtls(Long id) {
        this.id = id;
    }

    public CallFileUploadDtls(Long id, String filePath, long fileSentBy, long fileReceivedBy, long createdBy, Date createdDateTime, String docTitle, String fileSentbyType, String fileReceivedbyType) {
        this.id = id;
        this.filePath = filePath;
        this.fileSentBy = fileSentBy;
        this.fileReceivedBy = fileReceivedBy;
        this.createdBy = createdBy;
        this.createdDateTime = createdDateTime;
        this.docTitle = docTitle;
        this.fileSentbyType = fileSentbyType;
        this.fileReceivedbyType = fileReceivedbyType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public long getFileSentBy() {
        return fileSentBy;
    }

    public void setFileSentBy(long fileSentBy) {
        this.fileSentBy = fileSentBy;
    }

    public long getFileReceivedBy() {
        return fileReceivedBy;
    }

    public void setFileReceivedBy(long fileReceivedBy) {
        this.fileReceivedBy = fileReceivedBy;
    }

    public long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(Date createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public CallMst getCallMstId() {
        return callMstId;
    }

    public void setCallMstId(CallMst callMstId) {
        this.callMstId = callMstId;
    }

    public String getDocTitle() {
        return docTitle;
    }

    public void setDocTitle(String docTitle) {
        this.docTitle = docTitle;
    }

    public String getFileSentbyType() {
        return fileSentbyType;
    }

    public void setFileSentbyType(String fileSentbyType) {
        this.fileSentbyType = fileSentbyType;
    }

    public String getFileReceivedbyType() {
        return fileReceivedbyType;
    }

    public void setFileReceivedbyType(String fileReceivedbyType) {
        this.fileReceivedbyType = fileReceivedbyType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CallFileUploadDtls)) {
            return false;
        }
        CallFileUploadDtls other = (CallFileUploadDtls) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.rank.ccms.entities.CallFileUploadDtls[ id=" + id + " ]";
    }

}
