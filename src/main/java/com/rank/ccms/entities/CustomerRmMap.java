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
@Table(name = "customer_rm_map")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CustomerRmMap.findAll", query = "SELECT c FROM CustomerRmMap c"),
    @NamedQuery(name = "CustomerRmMap.findById", query = "SELECT c FROM CustomerRmMap c WHERE c.id = :id"),
    @NamedQuery(name = "CustomerRmMap.findByActiveFlg", query = "SELECT c FROM CustomerRmMap c WHERE c.activeFlg = :activeFlg"),
    @NamedQuery(name = "CustomerRmMap.findByDeleteFlg", query = "SELECT c FROM CustomerRmMap c WHERE c.deleteFlg = :deleteFlg")})
public class CustomerRmMap implements Serializable {

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
    @JoinColumn(name = "rm_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EmployeeMst rmId;
    @JoinColumn(name = "cust_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CustomerMst custId;

    public CustomerRmMap() {
    }

    public CustomerRmMap(Long id) {
        this.id = id;
    }

    public CustomerRmMap(Long id, boolean activeFlg, boolean deleteFlg) {
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

    public EmployeeMst getRmId() {
        return rmId;
    }

    public void setRmId(EmployeeMst rmId) {
        this.rmId = rmId;
    }

    public CustomerMst getCustId() {
        return custId;
    }

    public void setCustId(CustomerMst custId) {
        this.custId = custId;
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
        if (!(object instanceof CustomerRmMap)) {
            return false;
        }
        CustomerRmMap other = (CustomerRmMap) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.rank.ccms.entities.CustomerRmMap[ id=" + id + " ]";
    }

}
