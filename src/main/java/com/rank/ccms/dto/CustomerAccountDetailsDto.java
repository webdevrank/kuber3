package com.rank.ccms.dto;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

public class CustomerAccountDetailsDto implements Serializable {

    private static final long serialVersionUID = 660689250689917796L;

    private String rowId;
    private String first_name;
    private Date dob;
    private String nationality;
    private BigInteger cust_id;
    private String gender;
    private String maritail_status;
    private String email;
    private String phone_no;
    private String cus_address;
    private String occupation;
    private String salary;
    private String education;
    private String bank_name;
    private String branch_code;
    private String bnk_address;
    private String ifsc_code;
    private String acc_no;
    private String balance_amt;
    private Date effective_date;
    private String customer_image;
    private String utility_bill;
    private String national_id;
    private String national_id_no;
    private String account_no;
    private String verifyStatus;
    private BigInteger rn;
    private String customer_type;
    private String customer_sign_cord;
    private String customer_sign;

    public String getRowId() {
        return rowId;
    }

    public void setRowId(String rowId) {
        this.rowId = rowId;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMaritail_status() {
        return maritail_status;
    }

    public void setMaritail_status(String maritail_status) {
        this.maritail_status = maritail_status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public String getCus_address() {
        return cus_address;
    }

    public void setCus_address(String cus_address) {
        this.cus_address = cus_address;
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

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getBranch_code() {
        return branch_code;
    }

    public void setBranch_code(String branch_code) {
        this.branch_code = branch_code;
    }

    public String getBnk_address() {
        return bnk_address;
    }

    public void setBnk_address(String bnk_address) {
        this.bnk_address = bnk_address;
    }

    public String getIfsc_code() {
        return ifsc_code;
    }

    public void setIfsc_code(String ifsc_code) {
        this.ifsc_code = ifsc_code;
    }

    public String getAcc_no() {
        return acc_no;
    }

    public void setAcc_no(String acc_no) {
        this.acc_no = acc_no;
    }

    public String getBalance_amt() {
        return balance_amt;
    }

    public void setBalance_amt(String balance_amt) {
        this.balance_amt = balance_amt;
    }

    public Date getEffective_date() {
        return effective_date;
    }

    public void setEffective_date(Date effective_date) {
        this.effective_date = effective_date;
    }

    public String getCustomer_image() {
        return customer_image;
    }

    public void setCustomer_image(String customer_image) {
        this.customer_image = customer_image;
    }

    public String getUtility_bill() {
        return utility_bill;
    }

    public void setUtility_bill(String utility_bill) {
        this.utility_bill = utility_bill;
    }

    public String getNational_id() {
        return national_id;
    }

    public void setNational_id(String national_id) {
        this.national_id = national_id;
    }

    public String getAccount_no() {
        return account_no;
    }

    public void setAccount_no(String account_no) {
        this.account_no = account_no;
    }

    public String getNational_id_no() {
        return national_id_no;
    }

    public void setNational_id_no(String national_id_no) {
        this.national_id_no = national_id_no;
    }

    public BigInteger getCust_id() {
        return cust_id;
    }

    public void setCust_id(BigInteger cust_id) {
        this.cust_id = cust_id;
    }

    public BigInteger getRn() {
        return rn;
    }

    public void setRn(BigInteger rn) {
        this.rn = rn;
    }

    public String getVerifyStatus() {
        return verifyStatus;
    }

    public void setVerifyStatus(String verifyStatus) {
        this.verifyStatus = verifyStatus;
    }

    public String getCustomer_type() {
        return customer_type;
    }

    public void setCustomer_type(String customer_type) {
        this.customer_type = customer_type;
    }

    public String getCustomer_sign_cord() {
        return customer_sign_cord;
    }

    public void setCustomer_sign_cord(String customer_sign_cord) {
        this.customer_sign_cord = customer_sign_cord;
    }

    public String getCustomer_sign() {
        return customer_sign;
    }

    public void setCustomer_sign(String customer_sign) {
        this.customer_sign = customer_sign;
    }

}
