package com.rank.ccms.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "schedule_call")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ScheduleCall.findAll", query = "SELECT s FROM ScheduleCall s")})
public class ScheduleCall implements Serializable {

    @NotNull
    private Date scheduleDate;
    @NotNull
    private Date scheduleEndDate;
    @NotNull
    private Long schedulerId;
    @NotNull
    private Date creationDatetime;
    private Timestamp customerTime;
    @NotNull
    @Size(min = 1, max = 20)
    private String executeStatus;
    private Long service;
    private Long category;
    private Long language;
    private Long supervisorId;
    private String callType;

    private static final long serialVersionUID = 1L;

    private Long id;
    private String scheduledBy;
    private String callMedium;
    private boolean activeFlg;
    private boolean deleteFlg;
    private Long callmstid;
    private CustomerMst customerId;
    private EmployeeMst employeeId;

    public ScheduleCall() {
    }

    public ScheduleCall(Long id) {
        this.id = id;
    }

    public ScheduleCall(Long id, Date scheduleDate, String scheduledBy, Long schedulerId, Date creationDatetime, String executestatus, boolean activeFlg, boolean deleteFlg) {
        this.id = id;
        this.scheduleDate = scheduleDate;
        this.scheduledBy = scheduledBy;
        this.schedulerId = schedulerId;
        this.creationDatetime = creationDatetime;
        this.executeStatus = executestatus;
        this.activeFlg = activeFlg;
        this.deleteFlg = deleteFlg;
    }

    @Id
    @SequenceGenerator(name = "scheduleCallSeq", sequenceName = "SCHEDULE_CALL_ID_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "scheduleCallSeq", strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic(optional = false)
    @Column(name = "scheduled_by")
    public String getScheduledBy() {
        return scheduledBy;
    }

    public void setScheduledBy(String scheduledBy) {
        this.scheduledBy = scheduledBy;
    }

    @Basic(optional = false)
    @Column(name = "execute_status")
    public String getExecuteStatus() {
        return executeStatus;
    }

    public void setExecuteStatus(String executeStatus) {
        this.executeStatus = executeStatus;
    }

    @Column(name = "service")
    public Long getService() {
        return service;
    }

    public void setService(Long service) {
        this.service = service;
    }

    @Column(name = "category")
    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }

    @Column(name = "language")
    public Long getLanguage() {
        return language;
    }

    public void setLanguage(Long language) {
        this.language = language;
    }

    @Column(name = "call_medium")
    public String getCallMedium() {
        return callMedium;
    }

    public void setCallMedium(String callMedium) {
        this.callMedium = callMedium;
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

    @Column(name = "call_mst_id")
    public Long getCallmstid() {
        return callmstid;
    }

    public void setCallmstid(Long callmstid) {
        this.callmstid = callmstid;
    }

    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    public CustomerMst getCustomerId() {
        return customerId;
    }

    public void setCustomerId(CustomerMst customerId) {
        this.customerId = customerId;
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
        if (!(object instanceof ScheduleCall)) {
            return false;
        }
        ScheduleCall other = (ScheduleCall) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "com.rank.ccms.entities.ScheduleCall[ id=" + id + " ]";
    }

    @Basic(optional = false)
    @Column(name = "schedule_date")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(Date scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    @Basic(optional = false)
    @Column(name = "scheduler_id")
    public Long getSchedulerId() {
        return schedulerId;
    }

    public void setSchedulerId(Long schedulerId) {
        this.schedulerId = schedulerId;
    }

    @Basic(optional = false)
    @Column(name = "creation_datetime")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreationDatetime() {
        return creationDatetime;
    }

    public void setCreationDatetime(Date creationDatetime) {
        this.creationDatetime = creationDatetime;
    }

    @Column(name = "supervisor_id")
    public Long getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(Long supervisorId) {
        this.supervisorId = supervisorId;
    }

    @Column(name = "customer_time")
    public Timestamp getCustomerTime() {
        return customerTime;
    }

    public void setCustomerTime(Timestamp customerTime) {
        this.customerTime = customerTime;
    }

    @Column(name = "call_type")
    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    public EmployeeMst getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(EmployeeMst employeeId) {
        this.employeeId = employeeId;
    }

    @Basic(optional = false)
    @Column(name = "schedule_end_date")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getScheduleEndDate() {
        return scheduleEndDate;
    }

    public void setScheduleEndDate(Date scheduleEndDate) {
        this.scheduleEndDate = scheduleEndDate;
    }

}
