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
@Table(name = "employee_type_mst")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmployeeTypeMst.findAll", query = "SELECT e FROM EmployeeTypeMst e")})
public class EmployeeTypeMst implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String typeName;
    private String typeDesc;
    private boolean activeFlg;
    private boolean deleteFlg;
    private Collection<EmployeeMst> employeeMstCollection;

    public EmployeeTypeMst() {
    }

    public EmployeeTypeMst(Long id) {
        this.id = id;
    }

    public EmployeeTypeMst(Long id, String typeName, boolean activeFlg, boolean deleteFlg) {
        this.id = id;
        this.typeName = typeName;
        this.activeFlg = activeFlg;
        this.deleteFlg = deleteFlg;
    }

    @Id
    @SequenceGenerator(name = "employeeTypeMstSeq", sequenceName = "EMPLOYEE_TYPE_MST_ID_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "employeeTypeMstSeq", strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic(optional = false)
    @Column(name = "type_name")
    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Column(name = "type_desc")
    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empTypId", fetch = FetchType.LAZY)
    @XmlTransient
    public Collection<EmployeeMst> getEmployeeMstCollection() {
        return employeeMstCollection;
    }

    public void setEmployeeMstCollection(Collection<EmployeeMst> employeeMstCollection) {
        this.employeeMstCollection = employeeMstCollection;
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
        if (!(object instanceof EmployeeTypeMst)) {
            return false;
        }
        EmployeeTypeMst other = (EmployeeTypeMst) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "com.rank.ccms.entities.EmployeeTypeMst[ id=" + id + " ]";
    }

}
