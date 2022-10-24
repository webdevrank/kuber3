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
@Table(name = "category_mst")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CategoryMst.findAll", query = "SELECT c FROM CategoryMst c")})
public class CategoryMst implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String catgCd;
    private String catgName;
    private String catgDesc;
    private boolean activeFlg;
    private boolean deleteFlg;

    public CategoryMst() {
    }

    public CategoryMst(Long id) {
        this.id = id;
    }

    public CategoryMst(Long id, String catgCd, String catgName, boolean activeFlg, boolean deleteFlg) {
        this.id = id;
        this.catgCd = catgCd;
        this.catgName = catgName;
        this.activeFlg = activeFlg;
        this.deleteFlg = deleteFlg;
    }

    @Id
    @SequenceGenerator(name = "categorymstSeq", sequenceName = "CATEGORY_MST_ID_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "categorymstSeq", strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic(optional = false)
    @Column(name = "catg_cd")
    public String getCatgCd() {
        return catgCd;
    }

    public void setCatgCd(String catgCd) {
        this.catgCd = catgCd;
    }

    @Basic(optional = false)
    @Column(name = "catg_name")
    public String getCatgName() {
        return catgName;
    }

    public void setCatgName(String catgName) {
        this.catgName = catgName;
    }

    @Column(name = "catg_desc")
    public String getCatgDesc() {
        return catgDesc;
    }

    public void setCatgDesc(String catgDesc) {
        this.catgDesc = catgDesc;
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
        if (!(object instanceof CategoryMst)) {
            return false;
        }
        CategoryMst other = (CategoryMst) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "com.rank.ccms.entities.CategoryMst[ id=" + id + " ]";
    }

}
