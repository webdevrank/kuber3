package com.rank.ccms.rest.response;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class CustomerAccountInformationDetailsDto implements Serializable {

    @JsonIgnore
    private static final long serialVersionUID = 7555038371872141329L;
    private MultipartFile signatureFileData;
    private String signatureFileName;
    private Long custDtlId;
    private String gender;
    private String fullName;
    private String dob;
    private String maritialStatus;
    private String email;
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
    private String callId;
    private String annualIncome;
    private String accountNo;
    private String loanAmount;
    private String customerStatus;
    private String custId;

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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
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

    /**
     * @return the signatureFileData
     */
    public MultipartFile getSignatureFileData() {
        return signatureFileData;
    }

    /**
     * @param signatureFileData the signatureFileData to set
     */
    public void setSignatureFileData(MultipartFile signatureFileData) {
        this.signatureFileData = signatureFileData;
    }

    /**
     * @return the signatureFileName
     */
    public String getSignatureFileName() {
        return signatureFileName;
    }

    /**
     * @param signatureFileName the signatureFileName to set
     */
    public void setSignatureFileName(String signatureFileName) {
        this.signatureFileName = signatureFileName;
    }

    /**
     * @return the callId
     */
    public String getCallId() {
        return callId;
    }

    /**
     * @param callId the callId to set
     */
    public void setCallId(String callId) {
        this.callId = callId;
    }

    /**
     * @return the annualIncome
     */
    public String getAnnualIncome() {
        return annualIncome;
    }

    /**
     * @param annualIncome the annualIncome to set
     */
    public void setAnnualIncome(String annualIncome) {
        this.annualIncome = annualIncome;
    }

    /**
     * @return the accountNo
     */
    public String getAccountNo() {
        return accountNo;
    }

    /**
     * @param accountNo the accountNo to set
     */
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    /**
     * @return the loanAmount
     */
    public String getLoanAmount() {
        return loanAmount;
    }

    /**
     * @param loanAmount the loanAmount to set
     */
    public void setLoanAmount(String loanAmount) {
        this.loanAmount = loanAmount;
    }

    /**
     * @return the customerStatus
     */
    public String getCustomerStatus() {
        return customerStatus;
    }

    /**
     * @param customerStatus the customerStatus to set
     */
    public void setCustomerStatus(String customerStatus) {
        this.customerStatus = customerStatus;
    }

    /**
     * @return the custId
     */
    public String getCustId() {
        return custId;
    }

    /**
     * @param custId the custId to set
     */
    public void setCustId(String custId) {
        this.custId = custId;
    }

    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "CustomerAccountInformationDetailsDto [signatureFileName=" + signatureFileName + ", custDtlId="
                + custDtlId + ", gender=" + gender + ", fullName=" + fullName + ", dob=" + dob + ", maritialStatus="
                + maritialStatus + ", email=" + email + ", phone=" + phone + ", address=" + address + ", occupation="
                + occupation + ", salary=" + salary + ", education=" + education + ", nationality=" + nationality
                + ", signature=" + signature + ", signatureCord=" + signatureCord + ", custImage=" + custImage
                + ", idCard=" + idCard + ", addressProof=" + addressProof + ", customerType=" + customerType
                + ", callId=" + callId + ", annualIncome=" + annualIncome + ", accountNo=" + accountNo
                + ", loanAmount=" + loanAmount + ", customerStatus=" + customerStatus + "]";
    }

}
