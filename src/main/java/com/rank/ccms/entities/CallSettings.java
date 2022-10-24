package com.rank.ccms.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "call_settings")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CallSettings.findAll", query = "SELECT c FROM CallSettings c")})
public class CallSettings implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "callSettingsSeq", sequenceName = "call_stng_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "callSettingsSeq", strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "otp")
    private boolean otp;
    @Column(name = "service_start_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date serviceStartTime;
    @Column(name = "service_end_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date serviceEndTime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "active_flg")
    private boolean activeFlg;
    @Basic(optional = false)
    @NotNull
    @Column(name = "delete_flg")
    private boolean deleteFlg;
    @Size(max = 30)
    @Column(name = "service_day")
    private String serviceDay;

    public CallSettings() {
    }

    public CallSettings(Long id) {
        this.id = id;
    }

    public CallSettings(Long id, boolean otp, boolean activeFlg, boolean deleteFlg) {
        this.id = id;
        this.otp = otp;
        this.activeFlg = activeFlg;
        this.deleteFlg = deleteFlg;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean getOtp() {
        return otp;
    }

    public void setOtp(boolean otp) {
        this.otp = otp;
    }

    public Date getServiceStartTime() {
        return serviceStartTime;
    }

    public void setServiceStartTime(Date serviceStartTime) {
        this.serviceStartTime = serviceStartTime;
    }

    public Date getServiceEndTime() {
        return serviceEndTime;
    }

    public void setServiceEndTime(Date serviceEndTime) {
        this.serviceEndTime = serviceEndTime;
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

    public String getServiceDay() {
        return serviceDay;
    }

    public void setServiceDay(String serviceDay) {
        this.serviceDay = serviceDay;
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
        if (!(object instanceof CallSettings)) {
            return false;
        }
        CallSettings other = (CallSettings) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.rank.vanguard.ccms.CallSettings[ id=" + id + " ]";
    }
    
}
