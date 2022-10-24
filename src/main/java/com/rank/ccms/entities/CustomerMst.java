package com.rank.ccms.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "customer_mst")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CustomerMst.findAll", query = "SELECT c FROM CustomerMst c")})
public class CustomerMst implements Serializable {

    private Long homePhone;
    @NotNull
    private Long cellPhone;
    private Integer policyCount;

    @Size(max = 20)
    private String nationalId;
    @Size(max = 200)
    private String natinality;

    @Size(max = 20)
    private String custIdNo;
    @Size(max = 500)
    private String custNameAsIdCard;
    @Size(max = 200)
    private String custNationalityAsIdCard;

    private static final long serialVersionUID = 1L;

    private Long id;
    private String salute;
    private String firstName;
    private String midName;
    private String lastName;
    private String fatherName;
    private String custId;
    private String custPwd;
    private String accountNo;
    private String custDesc;
    private String custSeg;
    private String custLang1;
    private String custLang2;
    private String custLang3;
    private Date custDob;
    private String addrsLine1;
    private String addrsLine2;
    private String email;
    private String custType;

    private boolean activeFlg;
    private boolean deleteFlg;
    private boolean isRegistered;

    private transient String imagePathText;

    private Collection<ScheduleCall> scheduleCallCollection;
    private Collection<CallMst> callMstCollection;
    private Collection<FeedbackDtl> feedbackDtlCollection;
    private Collection<CustomerRmMap> customerRmMapCollection;

    private Collection<CustomerDeviceDtl> customerDeviceDtlList;

    public CustomerMst() {
    }

    public CustomerMst(Long id) {
        this.id = id;
    }

    public CustomerMst(Long id, String salute, String firstName, String lastName, String custId, String email, Long cellPhone, int policyCount, boolean activeFlg, boolean deleteFlg, boolean isRegistered) {
        this.id = id;
        this.salute = salute;
        this.firstName = firstName;
        this.lastName = lastName;
        this.custId = custId;
        this.email = email;
        this.cellPhone = cellPhone;
        this.policyCount = policyCount;
        this.activeFlg = activeFlg;
        this.deleteFlg = deleteFlg;
        this.isRegistered = isRegistered;
    }

    @Id
    @SequenceGenerator(name = "customerMstSeq", sequenceName = "CUSTOMER_MST_ID_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "customerMstSeq", strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic(optional = true)
    @Column(name = "salute")
    public String getSalute() {
        return salute;
    }

    public void setSalute(String salute) {
        this.salute = salute;
    }

    @Basic(optional = false)
    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "mid_name")
    public String getMidName() {
        return midName;
    }

    public void setMidName(String midName) {
        this.midName = midName;
    }

    @Basic(optional = false)
    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "father_name")
    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    @Basic(optional = false)
    @Column(name = "cust_id")
    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    @Column(name = "cust_pwd")
    public String getCustPwd() {
        return custPwd;
    }

    public void setCustPwd(String custPwd) {
        this.custPwd = custPwd;
    }

    @Column(name = "account_no")
    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    @Column(name = "cust_desc")
    public String getCustDesc() {
        return custDesc;
    }

    public void setCustDesc(String custDesc) {
        this.custDesc = custDesc;
    }

    @Column(name = "cust_seg")
    public String getCustSeg() {
        return custSeg;
    }

    public void setCustSeg(String custSeg) {
        this.custSeg = custSeg;
    }

    @Column(name = "cust_lang1")
    public String getCustLang1() {
        return custLang1;
    }

    public void setCustLang1(String custLang1) {
        this.custLang1 = custLang1;
    }

    @Column(name = "cust_lang2")
    public String getCustLang2() {
        return custLang2;
    }

    public void setCustLang2(String custLang2) {
        this.custLang2 = custLang2;
    }

    @Column(name = "cust_lang3")
    public String getCustLang3() {
        return custLang3;
    }

    public void setCustLang3(String custLang3) {
        this.custLang3 = custLang3;
    }

    @Column(name = "cust_dob")
    @Temporal(TemporalType.DATE)
    public Date getCustDob() {
        return custDob;
    }

    public void setCustDob(Date custDob) {
        this.custDob = custDob;
    }

    @Column(name = "addrs_line1")
    public String getAddrsLine1() {
        return addrsLine1;
    }

    public void setAddrsLine1(String addrsLine1) {
        this.addrsLine1 = addrsLine1;
    }

    @Column(name = "addrs_line2")
    public String getAddrsLine2() {
        return addrsLine2;
    }

    public void setAddrsLine2(String addrsLine2) {
        this.addrsLine2 = addrsLine2;
    }

    @Basic(optional = false)
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "cust_type")
    public String getCustType() {
        return custType;
    }

    public void setCustType(String custType) {
        this.custType = custType;
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

    @Column(name = "is_registered")
    public boolean getIsRegistered() {
        return isRegistered;
    }

    public void setIsRegistered(boolean isRegistered) {
        this.isRegistered = isRegistered;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "custId", fetch = FetchType.LAZY)
    @XmlTransient
    public Collection<CustomerRmMap> getCustomerRmMapCollection() {
        return customerRmMapCollection;
    }

    public void setCustomerRmMapCollection(Collection<CustomerRmMap> customerRmMapCollection) {
        this.customerRmMapCollection = customerRmMapCollection;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customerId", fetch = FetchType.LAZY)
    @XmlTransient
    public Collection<ScheduleCall> getScheduleCallCollection() {
        return scheduleCallCollection;
    }

    public void setScheduleCallCollection(Collection<ScheduleCall> scheduleCallCollection) {
        this.scheduleCallCollection = scheduleCallCollection;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customerId", fetch = FetchType.LAZY)
    @XmlTransient
    public Collection<CallMst> getCallMstCollection() {
        return callMstCollection;
    }

    public void setCallMstCollection(Collection<CallMst> callMstCollection) {
        this.callMstCollection = callMstCollection;
    }

    @OneToMany(mappedBy = "customerId", fetch = FetchType.LAZY)
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
        if (!(object instanceof CustomerMst)) {
            return false;
        }
        CustomerMst other = (CustomerMst) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "com.rank.ccms.entities.CustomerMst[ id=" + id + " ]";
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customerId", fetch = FetchType.LAZY)
    @XmlTransient
    public Collection<CustomerDeviceDtl> getCustomerDeviceDtlList() {
        return customerDeviceDtlList;
    }

    public void setCustomerDeviceDtlList(Collection<CustomerDeviceDtl> customerDeviceDtlList) {
        this.customerDeviceDtlList = customerDeviceDtlList;
    }

    @Transient
    public String getImagePathText() {
        return imagePathText;
    }

    public void setImagePathText(String imagePathText) {
        this.imagePathText = imagePathText;
    }

    @Column(name = "cust_id_no")
    public String getCustIdNo() {
        return custIdNo;
    }

    public void setCustIdNo(String custIdNo) {
        this.custIdNo = custIdNo;
    }

    @Column(name = "cust_name_as_id_card")
    public String getCustNameAsIdCard() {
        return custNameAsIdCard;
    }

    public void setCustNameAsIdCard(String custNameAsIdCard) {
        this.custNameAsIdCard = custNameAsIdCard;
    }

    @Column(name = "cust_nationality_as_id_card")
    public String getCustNationalityAsIdCard() {
        return custNationalityAsIdCard;
    }

    public void setCustNationalityAsIdCard(String custNationalityAsIdCard) {
        this.custNationalityAsIdCard = custNationalityAsIdCard;
    }

    @Column(name = "national_id")
    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    @Column(name = "natinality")
    public String getNatinality() {
        return natinality;
    }

    public void setNatinality(String natinality) {
        this.natinality = natinality;
    }

    @Column(name = "home_phone")
    public Long getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(Long homePhone) {
        this.homePhone = homePhone;
    }

    @Basic(optional = false)
    @Column(name = "cell_phone")
    public Long getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(Long cellPhone) {
        this.cellPhone = cellPhone;
    }

    @Column(name = "policy_count")
    public Integer getPolicyCount() {
        return policyCount;
    }

    public void setPolicyCount(Integer policyCount) {
        this.policyCount = policyCount;
    }

}
