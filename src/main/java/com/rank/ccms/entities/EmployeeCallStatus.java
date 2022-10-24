package com.rank.ccms.entities;

import java.io.Serializable;
import java.sql.Timestamp;
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
@Table(name = "employee_call_status")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmployeeCallStatus.findAll", query = "SELECT e FROM EmployeeCallStatus e")})
public class EmployeeCallStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Boolean status;
    private int callCount;
    private Timestamp endTime;
    private EmployeeMst empId;

    public EmployeeCallStatus() {
    }

    public EmployeeCallStatus(Long id) {
        this.id = id;
    }

    public EmployeeCallStatus(Long id, int callCount, Timestamp endTime) {
        this.id = id;
        this.callCount = callCount;
        this.endTime = endTime;
    }

    @Id
    @SequenceGenerator(name = "employeeCallStatusSeq", sequenceName = "EMPLOYEE_CALL_STATUS_ID_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "employeeCallStatusSeq", strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "status")
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Basic(optional = false)
    @Column(name = "call_count")
    public int getCallCount() {
        return callCount;
    }

    public void setCallCount(int callCount) {
        this.callCount = callCount;
    }

    @Basic(optional = true)
    @Column(name = "end_time")
    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
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
        if (!(object instanceof EmployeeCallStatus)) {
            return false;
        }
        EmployeeCallStatus other = (EmployeeCallStatus) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "com.rank.ccms.entities.EmployeeCallStatus[ id=" + id + " ]";
    }

}
