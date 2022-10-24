package com.rank.ccms.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "promotional_video_mst")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PromotionalVideoMst.findAll", query = "SELECT p FROM PromotionalVideoMst p"),
    @NamedQuery(name = "PromotionalVideoMst.findById", query = "SELECT p FROM PromotionalVideoMst p WHERE p.id = :id"),
    @NamedQuery(name = "PromotionalVideoMst.findByFileUrl", query = "SELECT p FROM PromotionalVideoMst p WHERE p.fileUrl = :fileUrl"),
    @NamedQuery(name = "PromotionalVideoMst.findByActiveFlg", query = "SELECT p FROM PromotionalVideoMst p WHERE p.activeFlg = :activeFlg"),
    @NamedQuery(name = "PromotionalVideoMst.findByDeleteFlg", query = "SELECT p FROM PromotionalVideoMst p WHERE p.deleteFlg = :deleteFlg"),
    @NamedQuery(name = "PromotionalVideoMst.findBySelectedFlg", query = "SELECT p FROM PromotionalVideoMst p WHERE p.selectedFlg = :selectedFlg"),
    @NamedQuery(name = "PromotionalVideoMst.findByFileName", query = "SELECT p FROM PromotionalVideoMst p WHERE p.fileName = :fileName"),
    @NamedQuery(name = "PromotionalVideoMst.findByVideoCaption", query = "SELECT p FROM PromotionalVideoMst p WHERE p.videoCaption = :videoCaption")})
public class PromotionalVideoMst implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    @Size(max = 2147483647)
    private String fileUrl;
    @NotNull
    private boolean activeFlg;
    @NotNull
    private boolean deleteFlg;
    @NotNull
    private boolean selectedFlg;
    @Size(max = 2147483647)
    private String fileName;
    @Size(max = 2147483647)
    private String videoCaption;

    public PromotionalVideoMst() {
    }

    public PromotionalVideoMst(Long id) {
        this.id = id;
    }

    public PromotionalVideoMst(Long id, boolean activeFlg, boolean deleteFlg, boolean selectedFlg) {
        this.id = id;
        this.activeFlg = activeFlg;
        this.deleteFlg = deleteFlg;
        this.selectedFlg = selectedFlg;
    }

    @Id
    @SequenceGenerator(name = "promotionalVideoMstSeq", sequenceName = "PROMOTIONAL_VIDEO_MST_ID_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "promotionalVideoMstSeq", strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "file_url")
    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    @Basic(optional = false)
    @Column(name = "active_flg")
    public boolean getActiveFlg() {
        return activeFlg;
    }

    public void setActiveFlg(boolean activeFlg) {
        this.activeFlg = activeFlg;
    }

    @Basic(optional = false)
    @Column(name = "delete_flg")
    public boolean getDeleteFlg() {
        return deleteFlg;
    }

    public void setDeleteFlg(boolean deleteFlg) {
        this.deleteFlg = deleteFlg;
    }

    @Basic(optional = false)
    @Column(name = "selected_flg")
    public boolean getSelectedFlg() {
        return selectedFlg;
    }

    public void setSelectedFlg(boolean selectedFlg) {
        this.selectedFlg = selectedFlg;
    }

    @Column(name = "file_name")
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Column(name = "video_caption")
    public String getVideoCaption() {
        return videoCaption;
    }

    public void setVideoCaption(String videoCaption) {
        this.videoCaption = videoCaption;
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
        if (!(object instanceof PromotionalVideoMst)) {
            return false;
        }
        PromotionalVideoMst other = (PromotionalVideoMst) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.rank.ccms.entities.PromotionalVideoMst[ id=" + id + " ]";
    }

}
