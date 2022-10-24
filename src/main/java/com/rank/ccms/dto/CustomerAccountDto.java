package com.rank.ccms.dto;

import java.io.Serializable;
import java.util.Date;

public class CustomerAccountDto implements Serializable {

    private Long custDtlId;
    private String gender;
    private String fullName;
    private Date dob;
    private String maritialStatus;
    private String email;
    private String father;
    private String panNumber;
    private String imageFromPan;
    private String signatureFromPan;
    private String phone;
    private String address;
    private String occupation;
    private String salary;
    private String education;
    private String nationality;
    private String signature;
    private String signatureCord;
    private String custImage;
    private String idCard;
    private String addressProof;
    private String customerType;

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

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
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

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
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

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father;
    }

    public String getPanNumber() {
        return panNumber;
    }

    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }

    public String getImageFromPan() {
        return imageFromPan;
    }

    public void setImageFromPan(String imageFromPan) {
        this.imageFromPan = imageFromPan;
    }

    public String getSignatureFromPan() {
        return signatureFromPan;
    }

    public void setSignatureFromPan(String signatureFromPan) {
        this.signatureFromPan = signatureFromPan;
    }

    @Override
    public String toString() {
        return "CustomerAccountDto{" + "gender=" + gender + ", fullName=" + fullName + ", dob=" + dob + ", maritialStatus=" + maritialStatus + ", email=" + email + ", phone=" + phone + ", address=" + address + ", occupation=" + occupation + ", salary=" + salary + ", education=" + education + ", nationality=" + nationality + '}';
    }

}
