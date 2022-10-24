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
@Table(name = "employee_activity_dtl")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmployeeActivityDtl.findAll", query = "SELECT e FROM EmployeeActivityDtl e")})
public class EmployeeActivityDtl implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String activity;
    private Timestamp startTime;
    private Timestamp endTime;
    private String reasonCd;
    private String reasonDesc;
    private Long callMstId;
    private Timestamp notification;
    private Long respectiveLoginId;
    private ReasonMst reasonId;
    private EmployeeMst empId;

    public EmployeeActivityDtl() {
    }

    public EmployeeActivityDtl(Long id) {
        this.id = id;
    }

    public EmployeeActivityDtl(Long id, String activity, Timestamp startTime, Timestamp endTime, Long callMstId, Timestamp notification, Long respectiveLoginId) {
        this.id = id;
        this.activity = activity;
        this.startTime = startTime;
        this.endTime = endTime;
        this.callMstId = callMstId;
        this.notification = notification;
        this.respectiveLoginId = respectiveLoginId;
    }

    @Id
    @SequenceGenerator(name = "employeeActivityDtlSeq", sequenceName = "EMPLOYEE_ACTIVITY_DTL_ID_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "employeeActivityDtlSeq", strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic(optional = false)
    @Column(name = "activity")
    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    @Basic(optional = false)
    @Column(name = "start_time")
    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    @Basic(optional = false)
    @Column(name = "end_time")
    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
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

    @Basic(optional = true)
    @Column(name = "call_mst_id")
    public Long getCallMstId() {
        return callMstId;
    }

    public void setCallMstId(Long callMstId) {
        this.callMstId = callMstId;
    }

    @Basic(optional = false)
    @Column(name = "notification")
    public Timestamp getNotification() {
        return notification;
    }

    public void setNotification(Timestamp notification) {
        this.notification = notification;
    }

    @Basic(optional = true)
    @Column(name = "respective_login_id")
    public Long getRespectiveLoginId() {
        return respectiveLoginId;
    }

    public void setRespectiveLoginId(Long respectiveLoginId) {
        this.respectiveLoginId = respectiveLoginId;
    }

    @JoinColumn(name = "reason_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    public ReasonMst getReasonId() {
        return reasonId;
    }

    public void setReasonId(ReasonMst reasonId) {
        this.reasonId = reasonId;
    }

    @JoinColumn(name = "emp_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
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
        if (!(object instanceof EmployeeActivityDtl)) {
            return false;
        }
        EmployeeActivityDtl other = (EmployeeActivityDtl) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "com.rank.ccms.entities.EmployeeActivityDtl[ id=" + id + " ]";
    }

}
