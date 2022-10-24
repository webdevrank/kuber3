package com.rank.ccms.entities;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "srm_bm_map")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SrmBmMap.findAll", query = "SELECT s FROM SrmBmMap s"),
    @NamedQuery(name = "SrmBmMap.findById", query = "SELECT s FROM SrmBmMap s WHERE s.id = :id"),
    @NamedQuery(name = "SrmBmMap.findByActiveFlg", query = "SELECT s FROM SrmBmMap s WHERE s.activeFlg = :activeFlg"),
    @NamedQuery(name = "SrmBmMap.findByDeleteFlg", query = "SELECT s FROM SrmBmMap s WHERE s.deleteFlg = :deleteFlg")})
public class SrmBmMap implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "active_flg")
    private boolean activeFlg;
    @Basic(optional = false)
    @Column(name = "delete_flg")
    private boolean deleteFlg;
    @JoinColumn(name = "bm_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EmployeeMst bmId;
    @JoinColumn(name = "srm_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EmployeeMst srmId;

    public SrmBmMap() {
    }

    public SrmBmMap(Long id) {
        this.id = id;
    }

    public SrmBmMap(Long id, boolean activeFlg, boolean deleteFlg) {
        this.id = id;
        this.activeFlg = activeFlg;
        this.deleteFlg = deleteFlg;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getActiveFlg() {
        return activeFlg;
    }

    public void setActiveFlg(boolean activeFlg) {
        this.activeFlg = activeFlg;
    }

    public boolean getDeleteFlg() {
        return deleteFlg;
    }

    public void setDeleteFlg(boolean deleteFlg) {
        this.deleteFlg = deleteFlg;
    }

    public EmployeeMst getBmId() {
        return bmId;
    }

    public void setBmId(EmployeeMst bmId) {
        this.bmId = bmId;
    }

    public EmployeeMst getSrmId() {
        return srmId;
    }

    public void setSrmId(EmployeeMst srmId) {
        this.srmId = srmId;
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
        if (!(object instanceof SrmBmMap)) {
            return false;
        }
        SrmBmMap other = (SrmBmMap) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.rank.ccms.entities.SrmBmMap[ id=" + id + " ]";
    }

}
