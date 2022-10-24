package com.rank.ccms.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "reason_mst")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ReasonMst.findAll", query = "SELECT r FROM ReasonMst r")})
public class ReasonMst implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String type;
    private String reasonCd;
    private String reasonDesc;
    private boolean activeFlg;
    private boolean deleteFlg;
    private Collection<EmployeeActivityDtl> employeeActivityDtlCollection;

    public ReasonMst() {
    }

    public ReasonMst(Long id) {
        this.id = id;
    }

    public ReasonMst(Long id, String type, boolean activeFlg, boolean deleteFlg) {
        this.id = id;
        this.type = type;
        this.activeFlg = activeFlg;
        this.deleteFlg = deleteFlg;
    }

    @Id
    @SequenceGenerator(name = "reasonMstSeq", sequenceName = "REASON_MST_ID_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "reasonMstSeq", strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic(optional = false)
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(name = "reason_cd")
    public String getReasonCd() {
        return reasonCd;
    }

    public void setReasonCd(String reasonCd) {
        this.reasonCd = reasonCd;
    }

    @Column(name = "reason_desc")
    public String getReasonDesc() {
        return reasonDesc;
    }

    public void setReasonDesc(String reasonDesc) {
        this.reasonDesc = reasonDesc;
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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "reasonId", fetch = FetchType.LAZY)
    @XmlTransient
    public Collection<EmployeeActivityDtl> getEmployeeActivityDtlCollection() {
        return employeeActivityDtlCollection;
    }

    public void setEmployeeActivityDtlCollection(Collection<EmployeeActivityDtl> employeeActivityDtlCollection) {
        this.employeeActivityDtlCollection = employeeActivityDtlCollection;
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
        if (!(object instanceof ReasonMst)) {
            return false;
        }
        ReasonMst other = (ReasonMst) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "com.rank.ccms.entities.ReasonMst[ id=" + id + " ]";
    }

}
