package com.rank.ccms.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "tenancy_employee_map")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TenancyEmployeeMap.findAll", query = "SELECT t FROM TenancyEmployeeMap t")})
public class TenancyEmployeeMap implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String roomLink;
    private String roomName;
    private String entityId;
    private EmployeeMst empId;

    public TenancyEmployeeMap() {
    }

    public TenancyEmployeeMap(Long id) {
        this.id = id;
    }

    @Id
    @SequenceGenerator(name = "tenancyEmployeeMapSeq", sequenceName = "TENANCY_EMPLOYEE_MAP_ID_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "tenancyEmployeeMapSeq", strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "room_link")
    public String getRoomLink() {
        return roomLink;
    }

    public void setRoomLink(String roomLink) {
        this.roomLink = roomLink;
    }

    @Column(name = "room_name")
    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    @Column(name = "entity_id")
    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    @JoinColumn(name = "emp_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    public EmployeeMst getEmpId() {
        return empId;
    }

    public void setEmpId(EmployeeMst empId) {
        this.empId = empId;
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
        if (!(object instanceof TenancyEmployeeMap)) {
            return false;
        }
        TenancyEmployeeMap other = (TenancyEmployeeMap) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "com.rank.ccms.entities.TenancyEmployeeMap[ id=" + id + " ]";
    }

}
