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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "employee_mst")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmployeeMst.findAll", query = "SELECT e FROM EmployeeMst e")})
public class EmployeeMst implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String firstName;
    private String midName;
    private String lastName;
    private String loginId;
    private String loginPasswd;
    private Date empDob;
    private String addrsLine1;
    private String addrsLine2;
    private String city;
    private String stateNm;
    private Integer pin;
    private String country;
    private String email;
    private Long officePhone;
    private Long homePhone;
    private Long cellPhone;
    private boolean activeFlg;
    private boolean deleteFlg;
    private boolean deactivateFlg;
    private Collection<EmployeeCallStatus> employeeCallStatusCollection;
    private Collection<EmployeeProficiencyMap> employeeProficiencyMapCollection;
    private Collection<CallRecords> callRecordsCollection;
    private EmployeeTypeMst empTypId;
    private Collection<EmployeeCallProficiency> employeeCallProficiencyCollection;
    private Collection<CallDtl> callDtlCollection;
    private Collection<ForwardedCall> forwardedCallCollection;
    private Collection<TenancyEmployeeMap> tenancyEmployeeMapCollection;
    private Collection<EmployeeActivityDtl> employeeActivityDtlCollection;
    private Collection<ScheduleCall> scheduleCallCollection;
    private Collection<CustomerRmMap> customerRmMapCollection;
    private Collection<RmSrmMap> rmSrmMapCollection;
    private Collection<RmSrmMap> rmSrmMapCollection1;
    private Collection<SrmBmMap> srmBmMapCollection;
    private Collection<SrmBmMap> srmBmMapCollection1;
    private transient String officePhoneStr;

    private transient String homePhoneStr;

    private transient String cellPhoneStr;

    public EmployeeMst() {
    }

    public EmployeeMst(Long id) {
        this.id = id;
    }

    public EmployeeMst(Long id, String firstName, String lastName, String loginId, String email, long cellPhone, boolean activeFlg, boolean deleteFlg) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.loginId = loginId;
        this.email = email;
        this.cellPhone = cellPhone;
        this.activeFlg = activeFlg;
        this.deleteFlg = deleteFlg;
    }

    @Id
    @SequenceGenerator(name = "employeeMstSeq", sequenceName = "EMPLOYEE_MST_ID_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "employeeMstSeq", strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Basic(optional = false)
    @Column(name = "login_id")
    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    @Basic(optional = false)
    @Column(name = "login_passwd")
    public String getLoginPasswd() {
        return loginPasswd;
    }

    public void setLoginPasswd(String loginPasswd) {
        this.loginPasswd = loginPasswd;
    }

    @Column(name = "emp_dob")
    @Temporal(TemporalType.DATE)
    public Date getEmpDob() {
        return empDob;
    }

    public void setEmpDob(Date empDob) {
        this.empDob = empDob;
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

    @Column(name = "city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "state_nm")
    public String getStateNm() {
        return stateNm;
    }

    public void setStateNm(String stateNm) {
        this.stateNm = stateNm;
    }

    @Column(name = "pin")
    public Integer getPin() {
        return pin;
    }

    public void setPin(Integer pin) {
        this.pin = pin;
    }

    @Column(name = "country")
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Basic(optional = false)
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "office_phone")
    public Long getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(Long officePhone) {
        this.officePhone = officePhone;
        this.officePhoneStr = officePhone.toString();
    }

    @Column(name = "home_phone")
    public Long getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(Long homePhone) {
        this.homePhone = homePhone;
        this.homePhoneStr = homePhone.toString();
    }

    @Basic(optional = false)
    @Column(name = "cell_phone")
    public Long getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(long cellPhone) {
        this.cellPhone = cellPhone;
        this.cellPhoneStr = (cellPhone + "");
    }

    public void setCellPhone(Long cellPhone) {
        this.cellPhone = cellPhone;
        this.cellPhoneStr = cellPhone.toString();
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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empId", fetch = FetchType.LAZY)
    @XmlTransient
    public Collection<EmployeeCallStatus> getEmployeeCallStatusCollection() {
        return employeeCallStatusCollection;
    }

    public void setEmployeeCallStatusCollection(Collection<EmployeeCallStatus> employeeCallStatusCollection) {
        this.employeeCallStatusCollection = employeeCallStatusCollection;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empId", fetch = FetchType.LAZY)
    @XmlTransient
    public Collection<EmployeeProficiencyMap> getEmployeeProficiencyMapCollection() {
        return employeeProficiencyMapCollection;
    }

    public void setEmployeeProficiencyMapCollection(Collection<EmployeeProficiencyMap> employeeProficiencyMapCollection) {
        this.employeeProficiencyMapCollection = employeeProficiencyMapCollection;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empId", fetch = FetchType.LAZY)
    @XmlTransient
    public Collection<CallRecords> getCallRecordsCollection() {
        return callRecordsCollection;
    }

    public void setCallRecordsCollection(Collection<CallRecords> callRecordsCollection) {
        this.callRecordsCollection = callRecordsCollection;
    }

    @JoinColumn(name = "emp_typ_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    public EmployeeTypeMst getEmpTypId() {
        return empTypId;
    }

    public void setEmpTypId(EmployeeTypeMst empTypId) {
        this.empTypId = empTypId;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empId", fetch = FetchType.LAZY)
    @XmlTransient
    public Collection<EmployeeCallProficiency> getEmployeeCallProficiencyCollection() {
        return employeeCallProficiencyCollection;
    }

    public void setEmployeeCallProficiencyCollection(Collection<EmployeeCallProficiency> employeeCallProficiencyCollection) {
        this.employeeCallProficiencyCollection = employeeCallProficiencyCollection;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "handeledById", fetch = FetchType.LAZY)
    @XmlTransient
    public Collection<CallDtl> getCallDtlCollection() {
        return callDtlCollection;
    }

    public void setCallDtlCollection(Collection<CallDtl> callDtlCollection) {
        this.callDtlCollection = callDtlCollection;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empId", fetch = FetchType.LAZY)
    @XmlTransient
    public Collection<ForwardedCall> getForwardedCallCollection() {
        return forwardedCallCollection;
    }

    public void setForwardedCallCollection(Collection<ForwardedCall> forwardedCallCollection) {
        this.forwardedCallCollection = forwardedCallCollection;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empId", fetch = FetchType.LAZY)
    @XmlTransient
    public Collection<TenancyEmployeeMap> getTenancyEmployeeMapCollection() {
        return tenancyEmployeeMapCollection;
    }

    public void setTenancyEmployeeMapCollection(Collection<TenancyEmployeeMap> tenancyEmployeeMapCollection) {
        this.tenancyEmployeeMapCollection = tenancyEmployeeMapCollection;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empId", fetch = FetchType.LAZY)
    @XmlTransient
    public Collection<EmployeeActivityDtl> getEmployeeActivityDtlCollection() {
        return employeeActivityDtlCollection;
    }

    public void setEmployeeActivityDtlCollection(Collection<EmployeeActivityDtl> employeeActivityDtlCollection) {
        this.employeeActivityDtlCollection = employeeActivityDtlCollection;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employeeId", fetch = FetchType.LAZY)
    @XmlTransient
    public Collection<ScheduleCall> getScheduleCallCollection() {
        return scheduleCallCollection;
    }

    public void setScheduleCallCollection(Collection<ScheduleCall> scheduleCallCollection) {
        this.scheduleCallCollection = scheduleCallCollection;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rmId", fetch = FetchType.LAZY)
    @XmlTransient
    public Collection<CustomerRmMap> getCustomerRmMapCollection() {
        return customerRmMapCollection;
    }

    public void setCustomerRmMapCollection(Collection<CustomerRmMap> customerRmMapCollection) {
        this.customerRmMapCollection = customerRmMapCollection;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "srmId", fetch = FetchType.LAZY)
    @XmlTransient
    public Collection<RmSrmMap> getRmSrmMapCollection() {
        return rmSrmMapCollection;
    }

    public void setRmSrmMapCollection(Collection<RmSrmMap> rmSrmMapCollection) {
        this.rmSrmMapCollection = rmSrmMapCollection;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rmId", fetch = FetchType.LAZY)
    @XmlTransient
    public Collection<RmSrmMap> getRmSrmMapCollection1() {
        return rmSrmMapCollection1;
    }

    public void setRmSrmMapCollection1(Collection<RmSrmMap> rmSrmMapCollection1) {
        this.rmSrmMapCollection1 = rmSrmMapCollection1;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bmId", fetch = FetchType.LAZY)
    @XmlTransient
    public Collection<SrmBmMap> getSrmBmMapCollection() {
        return srmBmMapCollection;
    }

    public void setSrmBmMapCollection(Collection<SrmBmMap> srmBmMapCollection) {
        this.srmBmMapCollection = srmBmMapCollection;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "srmId", fetch = FetchType.LAZY)
    @XmlTransient
    public Collection<SrmBmMap> getSrmBmMapCollection1() {
        return srmBmMapCollection1;
    }

    public void setSrmBmMapCollection1(Collection<SrmBmMap> srmBmMapCollection1) {
        this.srmBmMapCollection1 = srmBmMapCollection1;
    }

    @Transient
    public String getOfficePhoneStr() {
        return officePhoneStr;
    }

    public void setOfficePhoneStr(String officePhoneStr) {
        this.officePhoneStr = officePhoneStr;
        this.officePhone = Long.parseLong(officePhoneStr);
    }

    @Transient
    public String getHomePhoneStr() {
        return homePhoneStr;
    }

    public void setHomePhoneStr(String homePhoneStr) {
        this.homePhoneStr = homePhoneStr;
        this.homePhone = Long.parseLong(homePhoneStr);
    }

    @Transient
    public String getCellPhoneStr() {
        return cellPhoneStr;
    }

    public void setCellPhoneStr(String cellPhoneStr) {
        this.cellPhoneStr = cellPhoneStr;
        this.cellPhone = Long.parseLong(cellPhoneStr);
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
        if (!(object instanceof EmployeeMst)) {
            return false;
        }
        EmployeeMst other = (EmployeeMst) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "com.rank.ccms.entities.EmployeeMst[ id=" + id + " ]";
    }

    @Basic(optional = false)
    @Column(name = "deactivate_flg")
    public boolean getDeactivateFlg() {
        return deactivateFlg;
    }

    public void setDeactivateFlg(boolean deactivateFlg) {
        this.deactivateFlg = deactivateFlg;
    }

}
