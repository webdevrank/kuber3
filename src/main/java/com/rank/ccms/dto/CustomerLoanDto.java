package com.rank.ccms.dto;

import java.io.Serializable;
import java.util.Date;

public class CustomerLoanDto implements Serializable {

    private Long custDtlId;
    private String gender;
    private String fullName;
    private Date dob;
    private String maritialStatus;
    private String email;
    private String phone;
    private String address;
    private String occupation;
    private String annualIncome;
    private String education;
    private String nationality;
    private String signature;
    private String signatureCord;
    private String custImage;
    private String idCard;
    private String addressProof;
    private String accountNo;
    private String loanAmount;

    public Long getCustDtlId() {
        return custDtlId;
    }

    public void setCustDtlId(Long custDtlId) {
        this.custDtlId = custDtlId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getMaritialStatus() {
        return maritialStatus;
    }

    public void setMaritialStatus(String maritialStatus) {
        this.maritialStatus = maritialStatus;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getAnnualIncome() {
        return annualIncome;
    }

    public void setAnnualIncome(String annualIncome) {
        this.annualIncome = annualIncome;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getSignatureCord() {
        return signatureCord;
    }

    public void setSignatureCord(String signatureCord) {
        this.signatureCord = signatureCord;
    }

    public String getCustImage() {
        return custImage;
    }

    public void setCustImage(String custImage) {
        this.custImage = custImage;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getAddressProof() {
        return addressProof;
    }

    public void setAddressProof(String addressProof) {
        this.addressProof = addressProof;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(String loanAmount) {
        this.loanAmount = loanAmount;
    }

}
