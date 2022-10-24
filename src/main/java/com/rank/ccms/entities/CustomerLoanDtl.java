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
@Table(name = "customer_loan_dtl")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CustomerLoanDtl.findAll", query = "SELECT c FROM CustomerLoanDtl c"),
    @NamedQuery(name = "CustomerLoanDtl.findById", query = "SELECT c FROM CustomerLoanDtl c WHERE c.id = :id"),
    @NamedQuery(name = "CustomerLoanDtl.findByFullName", query = "SELECT c FROM CustomerLoanDtl c WHERE c.fullName = :fullName"),
    @NamedQuery(name = "CustomerLoanDtl.findByAccountNumber", query = "SELECT c FROM CustomerLoanDtl c WHERE c.accountNumber = :accountNumber"),
    @NamedQuery(name = "CustomerLoanDtl.findByDob", query = "SELECT c FROM CustomerLoanDtl c WHERE c.dob = :dob"),
    @NamedQuery(name = "CustomerLoanDtl.findByNationality", query = "SELECT c FROM CustomerLoanDtl c WHERE c.nationality = :nationality"),
    @NamedQuery(name = "CustomerLoanDtl.findByGender", query = "SELECT c FROM CustomerLoanDtl c WHERE c.gender = :gender"),
    @NamedQuery(name = "CustomerLoanDtl.findByMaritailStatus", query = "SELECT c FROM CustomerLoanDtl c WHERE c.maritailStatus = :maritailStatus"),
    @NamedQuery(name = "CustomerLoanDtl.findByEmail", query = "SELECT c FROM CustomerLoanDtl c WHERE c.email = :email"),
    @NamedQuery(name = "CustomerLoanDtl.findByPhoneNo", query = "SELECT c FROM CustomerLoanDtl c WHERE c.phoneNo = :phoneNo"),
    @NamedQuery(name = "CustomerLoanDtl.findByAddress", query = "SELECT c FROM CustomerLoanDtl c WHERE c.address = :address"),
    @NamedQuery(name = "CustomerLoanDtl.findByOccupation", query = "SELECT c FROM CustomerLoanDtl c WHERE c.occupation = :occupation"),
    @NamedQuery(name = "CustomerLoanDtl.findByAnnualIncome", query = "SELECT c FROM CustomerLoanDtl c WHERE c.annualIncome = :annualIncome"),
    @NamedQuery(name = "CustomerLoanDtl.findByLoanAmount", query = "SELECT c FROM CustomerLoanDtl c WHERE c.loanAmount = :loanAmount"),
    @NamedQuery(name = "CustomerLoanDtl.findByEducation", query = "SELECT c FROM CustomerLoanDtl c WHERE c.education = :education"),
    @NamedQuery(name = "CustomerLoanDtl.findByCustomerSign", query = "SELECT c FROM CustomerLoanDtl c WHERE c.customerSign = :customerSign"),
    @NamedQuery(name = "CustomerLoanDtl.findByCustomerSignCord", query = "SELECT c FROM CustomerLoanDtl c WHERE c.customerSignCord = :customerSignCord"),
    @NamedQuery(name = "CustomerLoanDtl.findByCustomerImage", query = "SELECT c FROM CustomerLoanDtl c WHERE c.customerImage = :customerImage"),
    @NamedQuery(name = "CustomerLoanDtl.findByUtilityBill", query = "SELECT c FROM CustomerLoanDtl c WHERE c.utilityBill = :utilityBill"),
    @NamedQuery(name = "CustomerLoanDtl.findByNationalId", query = "SELECT c FROM CustomerLoanDtl c WHERE c.nationalId = :nationalId")})
public class CustomerLoanDtl implements Serializable {

    @Size(max = 100)
    private String loanNo;

    private Long scheduleId;

    @Size(max = 100)
    private String loanStatus;
    private Long bankMstId;

    private static final long serialVersionUID = 1L;

    private Long id;
    @Size(max = 50)
    private String fullName;
    @Size(max = 50)
    private String accountNumber;
    private Date dob;
    @Size(max = 50)
    private String nationality;
    @Size(max = 10)
    private String gender;
    @Size(max = 50)
    private String maritailStatus;
    @Size(max = 50)
    private String email;
    @Size(max = 20)
    private String phoneNo;
    @Size(max = 150)
    private String address;
    @Size(max = 50)
    private String occupation;
    @Size(max = 100)
    private String annualIncome;
    @Size(max = 100)
    private String loanAmount;
    @Size(max = 50)
    private String education;
    @Size(max = 2147483647)
    private String customerSign;
    @Size(max = 2147483647)
    private String customerSignCord;
    @Size(max = 250)
    private String customerImage;
    @Size(max = 250)
    private String utilityBill;
    @Size(max = 250)
    private String nationalId;

    public CustomerLoanDtl() {
    }

    public CustomerLoanDtl(Long id) {
        this.id = id;
    }

    @Id
    @SequenceGenerator(name = "customerLoanDtlIdSeq", sequenceName = "customer_loan_dtl_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "customerLoanDtlIdSeq", strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "full_name")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Column(name = "account_number")
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Column(name = "dob")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    @Column(name = "nationality")
    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Column(name = "gender")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Column(name = "maritail_status")
    public String getMaritailStatus() {
        return maritailStatus;
    }

    public void setMaritailStatus(String maritailStatus) {
        this.maritailStatus = maritailStatus;
    }

    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "phone_no")
    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "occupation")
    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    @Column(name = "annual_income")
    public String getAnnualIncome() {
        return annualIncome;
    }

    public void setAnnualIncome(String annualIncome) {
        this.annualIncome = annualIncome;
    }

    @Column(name = "loan_amount")
    public String getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(String loanAmount) {
        this.loanAmount = loanAmount;
    }

    @Column(name = "education")
    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    @Column(name = "customer_sign")
    public String getCustomerSign() {
        return customerSign;
    }

    public void setCustomerSign(String customerSign) {
        this.customerSign = customerSign;
    }

    @Column(name = "customer_sign_cord")
    public String getCustomerSignCord() {
        return customerSignCord;
    }

    public void setCustomerSignCord(String customerSignCord) {
        this.customerSignCord = customerSignCord;
    }

    @Column(name = "customer_image")
    public String getCustomerImage() {
        return customerImage;
    }

    public void setCustomerImage(String customerImage) {
        this.customerImage = customerImage;
    }

    @Column(name = "utility_bill")
    public String getUtilityBill() {
        return utilityBill;
    }

    public void setUtilityBill(String utilityBill) {
        this.utilityBill = utilityBill;
    }

    @Column(name = "national_id")
    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
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
        if (!(object instanceof CustomerLoanDtl)) {
            return false;
        }
        CustomerLoanDtl other = (CustomerLoanDtl) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.rank.ccms.entities.CustomerLoanDtl[ id=" + id + " ]";
    }

    @Column(name = "loan_status")
    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }

    @Column(name = "bank_mst_id")
    public Long getBankMstId() {
        return bankMstId;
    }

    public void setBankMstId(Long bankMstId) {
        this.bankMstId = bankMstId;
    }

    @Column(name = "schedule_id")
    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    @Column(name = "loan_no")
    public String getLoanNo() {
        return loanNo;
    }

    public void setLoanNo(String loanNo) {
        this.loanNo = loanNo;
    }

}
