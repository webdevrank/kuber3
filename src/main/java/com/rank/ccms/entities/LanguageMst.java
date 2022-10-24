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
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "language_mst")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LanguageMst.findAll", query = "SELECT l FROM LanguageMst l")})
public class LanguageMst implements Serializable {

    private Long id;
    private String languageCd;
    private String languageName;
    private String languageDesc;
    private boolean activeFlg;
    private boolean deleteFlg;

    public LanguageMst() {
    }

    public LanguageMst(Long id) {
        this.id = id;
    }

    public LanguageMst(Long id, String languageCd, String languageName, boolean activeFlg, boolean deleteFlg) {
        this.id = id;
        this.languageCd = languageCd;
        this.languageName = languageName;
        this.activeFlg = activeFlg;
        this.deleteFlg = deleteFlg;
    }

    @Id
    @SequenceGenerator(name = "languageMstSeq", sequenceName = "LANGUAGE_MST_ID_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "languageMstSeq", strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic(optional = false)
    @Column(name = "language_cd")
    public String getLanguageCd() {
        return languageCd;
    }

    public void setLanguageCd(String languageCd) {
        this.languageCd = languageCd;
    }

    @Basic(optional = false)
    @Column(name = "language_name")
    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    @Column(name = "language_desc")
    public String getLanguageDesc() {
        return languageDesc;
    }

    public void setLanguageDesc(String languageDesc) {
        this.languageDesc = languageDesc;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LanguageMst)) {
            return false;
        }
        LanguageMst other = (LanguageMst) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "com.rank.ccms.entities.LanguageMst[ id=" + id + " ]";
    }

}
