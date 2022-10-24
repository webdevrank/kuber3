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
@Table(name = "call_dtl")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CallDtl.findAll", query = "SELECT c FROM CallDtl c")})
public class CallDtl implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String callTypeInfo;
    private Timestamp startTime;
    private Timestamp endTime;
    private String agentComments;
    private boolean activeFlg;
    private boolean deleteFlg;
    private EmployeeMst handeledById;
    private CallMst callMstId;

    public CallDtl() {
    }

    public CallDtl(Long id) {
        this.id = id;
    }

    public CallDtl(Long id, Timestamp startTime, Timestamp endTime, boolean activeFlg, boolean deleteFlg) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.activeFlg = activeFlg;
        this.deleteFlg = deleteFlg;
    }

    @Id
    @SequenceGenerator(name = "calldtlSeq", sequenceName = "CALL_DTL_ID_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "calldtlSeq", strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "call_type_info")
    public String getCallTypeInfo() {
        return callTypeInfo;
    }

    public void setCallTypeInfo(String callTypeInfo) {
        this.callTypeInfo = callTypeInfo;
    }

    @Basic(optional = false)
    @Column(name = "start_time")
    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    @Basic(optional = true)
    @Column(name = "end_time")
    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    @Column(name = "agent_comments")
    public String getAgentComments() {
        return agentComments;
    }

    public void setAgentComments(String agentComments) {
        this.agentComments = agentComments;
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

    @JoinColumn(name = "handeled_by_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    public EmployeeMst getHandeledById() {
        return handeledById;
    }

    public void setHandeledById(EmployeeMst handeledById) {
        this.handeledById = handeledById;
    }

    @JoinColumn(name = "call_mst_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    public CallMst getCallMstId() {
        return callMstId;
    }

    public void setCallMstId(CallMst callMstId) {
        this.callMstId = callMstId;
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
        if (!(object instanceof CallDtl)) {
            return false;
        }
        CallDtl other = (CallDtl) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "com.rank.ccms.entities.CallDtl[ id=" + id + " ]";
    }

}
