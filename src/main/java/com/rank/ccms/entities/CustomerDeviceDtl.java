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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "customer_device_dtl")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CustomerDeviceDtl.findAll", query = "SELECT c FROM CustomerDeviceDtl c")})
public class CustomerDeviceDtl implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    @NotNull
    @Size(min = 1, max = 50)
    private String mobileOsType;
    @Size(max = 250)
    private String deviceId;
    @Size(max = 50)
    private String deviceIp;
    @Size(max = 50)
    private String staticIp;
    @Size(max = 200)
    private String deviceBrand;
    @Size(max = 200)
    private String imeiNo;
    @Size(max = 10)
    private String customerOtp;
    private Integer loginInfo;
    private Integer otpVerified;
    private CustomerMst customerId;
    private Integer audioVideo;
    private Integer logout;
    @Size(max = 45)
    private String timezone;

    public CustomerDeviceDtl() {
    }

    public CustomerDeviceDtl(Long id) {
        this.id = id;
    }

    public CustomerDeviceDtl(Long id, String mobileOsType) {
        this.id = id;
        this.mobileOsType = mobileOsType;
    }

    @Id
    @SequenceGenerator(name = "customerDeviceDtlSeq", sequenceName = "CUSTOMER_DEVICE_DTL_ID_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "customerDeviceDtlSeq", strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic(optional = false)
    @Column(name = "mobile_os_type")
    public String getMobileOsType() {
        return mobileOsType;
    }

    public void setMobileOsType(String mobileOsType) {
        this.mobileOsType = mobileOsType;
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

    @Column(name = "imei_no")
    public String getImeiNo() {
        return imeiNo;
    }

    public void setImeiNo(String imeiNo) {
        this.imeiNo = imeiNo;
    }

    @Column(name = "customer_otp")
    public String getCustomerOtp() {
        return customerOtp;
    }

    public void setCustomerOtp(String customerOtp) {
        this.customerOtp = customerOtp;
    }

    @Column(name = "login_info")
    public Integer getLoginInfo() {
        return loginInfo;
    }

    public void setLoginInfo(Integer loginInfo) {
        this.loginInfo = loginInfo;
    }

    @Column(name = "otp_verified")
    public Integer getOtpVerified() {
        return otpVerified;
    }

    public void setOtpVerified(Integer otpVerified) {
        this.otpVerified = otpVerified;
    }

    @Column(name = "audio_video")
    public Integer getAudioVideo() {
        return audioVideo;
    }

    public void setAudioVideo(Integer audioVideo) {
        this.audioVideo = audioVideo;
    }

    @Column(name = "logout")
    public Integer getLogout() {
        return logout;
    }

    public void setLogout(Integer logout) {
        this.logout = logout;
    }

    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    public CustomerMst getCustomerId() {
        return customerId;
    }

    public void setCustomerId(CustomerMst customerId) {
        this.customerId = customerId;
    }

    @Column(name = "timezone")
    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
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
        if (!(object instanceof CustomerDeviceDtl)) {
            return false;
        }
        CustomerDeviceDtl other = (CustomerDeviceDtl) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "com.rank.ccms.entities.CustomerDeviceDtl[ id=" + id + " ]";
    }

}
