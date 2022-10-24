package com.rank.ccms.entities;

import java.io.Serializable;
import java.util.Collection;
import java.sql.Timestamp;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "call_mst")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CallMst.findAll", query = "SELECT c FROM CallMst c")})
public class CallMst implements Serializable {

    @NotNull
    private Long serviceId;
    @NotNull
    private Long categoryId;

    private Long languageId;
    @Size(max = 500)
    private String callLocation;
    @Size(max = 500)
    private String callLatitude;
    @Size(max = 500)
    private String callLongitude;

    private static final long serialVersionUID = 1L;

    private Long id;
    private String custId;
    private String callType;
    private String callStatus;
    private String deviceId;
    private String deviceIp;
    private String staticIp;
    private String deviceBrand;
    private String deviceOs;
    private String deviceName;
    private String callOption;
    @Size(max = 50)
    private String iMEIno;
    @NotNull
    private Timestamp startTime;
    private Timestamp endTime;
    private boolean activeFlg;
    private boolean deleteFlg;
    private String callMedium;
    private Timestamp agentPickupTime;
    private Timestamp customerRequestTime;
    private Timestamp customerPickupTime;
    private Timestamp routingCallTime;
    private Timestamp customerHangupTime;
    private String roomName;
    private Collection<CallRecords> callRecordsCollection;
    private Collection<CallDtl> callDtlCollection;
    private Collection<ForwardedCall> forwardedCallCollection;
    private CustomerMst customerId;
    private Collection<FeedbackDtl> feedbackDtlCollection;

    public CallMst() {
    }

    public CallMst(Long id) {
        this.id = id;
    }

    public CallMst(Long id, String custId, Long serviceId, Long categoryId, Timestamp startTime, Timestamp endTime, boolean activeFlg, boolean deleteFlg, Timestamp agentPickupTime, Timestamp customerRequestTime, Timestamp routingCallTime, Timestamp customerHangupTime) {
        this.id = id;
        this.custId = custId;
        this.serviceId = serviceId;
        this.categoryId = categoryId;
        this.startTime = startTime;
        this.activeFlg = activeFlg;
        this.deleteFlg = deleteFlg;
        this.agentPickupTime = agentPickupTime;
        this.customerRequestTime = customerRequestTime;
        this.routingCallTime = routingCallTime;
        this.customerHangupTime = customerHangupTime;
    }

    @Id
    @SequenceGenerator(name = "callmstSeq", sequenceName = "CALL_MST_ID_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "callmstSeq", strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic(optional = false)
    @Column(name = "cust_id")
    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    @Column(name = "call_type")
    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    @Column(name = "call_status")
    public String getCallStatus() {
        return callStatus;
    }

    public void setCallStatus(String callStatus) {
        this.callStatus = callStatus;
    }

    @Column(name = "device_id")
    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @Column(name = "device_ip")
    public String getDeviceIp() {
        return deviceIp;
    }

    public void setDeviceIp(String deviceIp) {
        this.deviceIp = deviceIp;
    }

    @Column(name = "static_ip")
    public String getStaticIp() {
        return staticIp;
    }

    public void setStaticIp(String staticIp) {
        this.staticIp = staticIp;
    }

    @Column(name = "device_brand")
    public String getDeviceBrand() {
        return deviceBrand;
    }

    public void setDeviceBrand(String deviceBrand) {
        this.deviceBrand = deviceBrand;
    }

    @Column(name = "device_os")
    public String getDeviceOs() {
        return deviceOs;
    }

    public void setDeviceOs(String deviceOs) {
        this.deviceOs = deviceOs;
    }

    @Column(name = "device_name")
    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    @Column(name = "IMEI_no")
    public String getiMEIno() {
        return iMEIno;
    }

    public void setiMEIno(String iMEIno) {
        this.iMEIno = iMEIno;
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

    @Column(name = "call_medium")
    public String getCallMedium() {
        return callMedium;
    }

    public void setCallMedium(String callMedium) {
        this.callMedium = callMedium;
    }

    @Column(name = "room_name")
    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "callId", fetch = FetchType.LAZY)
    @XmlTransient
    public Collection<CallRecords> getCallRecordsCollection() {
        return callRecordsCollection;
    }

    public void setCallRecordsCollection(Collection<CallRecords> callRecordsCollection) {
        this.callRecordsCollection = callRecordsCollection;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "callMstId", fetch = FetchType.LAZY)
    @XmlTransient
    public Collection<CallDtl> getCallDtlCollection() {
        return callDtlCollection;
    }

    public void setCallDtlCollection(Collection<CallDtl> callDtlCollection) {
        this.callDtlCollection = callDtlCollection;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "callId", fetch = FetchType.LAZY)
    @XmlTransient
    public Collection<ForwardedCall> getForwardedCallCollection() {
        return forwardedCallCollection;
    }

    public void setForwardedCallCollection(Collection<ForwardedCall> forwardedCallCollection) {
        this.forwardedCallCollection = forwardedCallCollection;
    }

    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    public CustomerMst getCustomerId() {
        return customerId;
    }

    public void setCustomerId(CustomerMst customerId) {
        this.customerId = customerId;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "callMstId", fetch = FetchType.LAZY)
    @XmlTransient
    public Collection<FeedbackDtl> getFeedbackDtlCollection() {
        return feedbackDtlCollection;
    }

    public void setFeedbackDtlCollection(Collection<FeedbackDtl> feedbackDtlCollection) {
        this.feedbackDtlCollection = feedbackDtlCollection;
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
        if (!(object instanceof CallMst)) {
            return false;
        }
        CallMst other = (CallMst) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.rank.ccms.entities.CallMst[ id=" + id + " ]";
    }

    @Basic(optional = false)
    @Column(name = "service_id")
    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    @Basic(optional = false)
    @Column(name = "category_id")
    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    @Basic(optional = false)
    @Column(name = "start_time")
    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    @Column(name = "end_time")
    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    @Column(name = "language_id")
    public Long getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Long languageId) {
        this.languageId = languageId;
    }

    @Column(name = "agent_pickup_time")
    public Timestamp getAgentPickupTime() {
        return agentPickupTime;
    }

    public void setAgentPickupTime(Timestamp agentPickupTime) {
        this.agentPickupTime = agentPickupTime;
    }

    @Column(name = "customer_request_time")
    public Timestamp getCustomerRequestTime() {
        return customerRequestTime;
    }

    public void setCustomerRequestTime(Timestamp customerRequestTime) {
        this.customerRequestTime = customerRequestTime;
    }

    @Column(name = "routing_call_time")
    public Timestamp getRoutingCallTime() {
        return routingCallTime;
    }

    public void setRoutingCallTime(Timestamp routingCallTime) {
        this.routingCallTime = routingCallTime;
    }

    @Column(name = "customer_hangup_time")
    public Timestamp getCustomerHangupTime() {
        return customerHangupTime;
    }

    public void setCustomerHangupTime(Timestamp customerHangupTime) {
        this.customerHangupTime = customerHangupTime;
    }

    @Column(name = "customer_pickup_time")
    public Timestamp getCustomerPickupTime() {
        return customerPickupTime;
    }

    public void setCustomerPickupTime(Timestamp customerPickupTime) {
        this.customerPickupTime = customerPickupTime;
    }

    @Column(name = "call_location")
    public String getCallLocation() {
        return callLocation;
    }

    public void setCallLocation(String callLocation) {
        this.callLocation = callLocation;
    }

    @Column(name = "call_latitude")
    public String getCallLatitude() {
        return callLatitude;
    }

    public void setCallLatitude(String callLatitude) {
        this.callLatitude = callLatitude;
    }

    @Column(name = "call_longitude")
    public String getCallLongitude() {
        return callLongitude;
    }

    public void setCallLongitude(String callLongitude) {
        this.callLongitude = callLongitude;
    }

    @Column(name = "call_option")
    public String getCallOption() {
        return callOption;
    }

    public void setCallOption(String callOption) {
        this.callOption = callOption;
    }

}
