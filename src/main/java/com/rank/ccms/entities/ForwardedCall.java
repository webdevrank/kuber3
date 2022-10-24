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
@Table(name = "forwarded_call")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ForwardedCall.findAll", query = "SELECT f FROM ForwardedCall f")})
public class ForwardedCall implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String roomLink;
    private Timestamp forwardedDatetime;
    private boolean activeFlg;
    private boolean deleteFlg;
    private Long prevEmpId;
    private Long selectedServiceId;
    private Long fromServiceId;
    private String forwardStatus;
    private Long callDtlId;
    private Timestamp callPickupTime;
    private CallMst callId;
    private EmployeeMst empId;
    private String roomName;
    private String entityId;
     private String forwardType;

    public ForwardedCall() {
    }

    public ForwardedCall(Long id) {
        this.id = id;
    }

    public ForwardedCall(Long id, String roomLink, Timestamp forwardedDatetime, boolean activeFlg, boolean deleteFlg, Long prevEmpId, Long selectedServiceId, Long fromServiceId, String forwardStatus, Long callDtlId, Timestamp callPickupTime) {
        this.id = id;
        this.roomLink = roomLink;
        this.forwardedDatetime = forwardedDatetime;
        this.activeFlg = activeFlg;
        this.deleteFlg = deleteFlg;
        this.prevEmpId = prevEmpId;
        this.selectedServiceId = selectedServiceId;
        this.fromServiceId = fromServiceId;
        this.forwardStatus = forwardStatus;
        this.callDtlId = callDtlId;
        this.callPickupTime = callPickupTime;
    }

    @Id
    @SequenceGenerator(name = "forwardedCallSeq", sequenceName = "FORWARDED_CALL_ID_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "forwardedCallSeq", strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic(optional = false)
    @Column(name = "room_link")
    public String getRoomLink() {
        return roomLink;
    }

    public void setRoomLink(String roomLink) {
        this.roomLink = roomLink;
    }

    @Basic(optional = false)
    @Column(name = "forwarded_datetime")
    public Timestamp getForwardedDatetime() {
        return forwardedDatetime;
    }

    public void setForwardedDatetime(Timestamp forwardedDatetime) {
        this.forwardedDatetime = forwardedDatetime;
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

    @Basic(optional = false)
    @Column(name = "prev_emp_id")
    public Long getPrevEmpId() {
        return prevEmpId;
    }

    public void setPrevEmpId(Long prevEmpId) {
        this.prevEmpId = prevEmpId;
    }

    @Basic(optional = false)
    @Column(name = "selected_service_id")
    public Long getSelectedServiceId() {
        return selectedServiceId;
    }

    public void setSelectedServiceId(Long selectedServiceId) {
        this.selectedServiceId = selectedServiceId;
    }

    @Basic(optional = false)
    @Column(name = "from_service_id")
    public Long getFromServiceId() {
        return fromServiceId;
    }

    public void setFromServiceId(Long fromServiceId) {
        this.fromServiceId = fromServiceId;
    }

    @Basic(optional = false)
    @Column(name = "forward_status")
    public String getForwardStatus() {
        return forwardStatus;
    }

    public void setForwardStatus(String forwardStatus) {
        this.forwardStatus = forwardStatus;
    }

    @Basic(optional = false)
    @Column(name = "call_dtl_id")
    public Long getCallDtlId() {
        return callDtlId;
    }

    public void setCallDtlId(Long callDtlId) {
        this.callDtlId = callDtlId;
    }

    @Basic(optional = false)
    @Column(name = "call_pickup_time")
    public Timestamp getCallPickupTime() {
        return callPickupTime;
    }

    public void setCallPickupTime(Timestamp callPickupTime) {
        this.callPickupTime = callPickupTime;
    }

    @JoinColumn(name = "call_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    public CallMst getCallId() {
        return callId;
    }

    public void setCallId(CallMst callId) {
        this.callId = callId;
    }

    @JoinColumn(name = "emp_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    public EmployeeMst getEmpId() {
        return empId;
    }

    public void setEmpId(EmployeeMst empId) {
        this.empId = empId;
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

    @Column(name = "forward_type")
    public String getForwardType() {
        return forwardType;
    }

    public void setForwardType(String forwardType) {
        this.forwardType = forwardType;
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
        if (!(object instanceof ForwardedCall)) {
            return false;
        }
        ForwardedCall other = (ForwardedCall) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "com.rank.ccms.entities.ForwardedCall[ id=" + id + " ]";
    }

}
