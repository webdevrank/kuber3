package com.rank.ccms.dto;

import java.io.Serializable;

public class CustomerRMMapDto implements Serializable {

    private Long id;
    private Long rmId;
    private Long custId;
    private String rmName;
    private String mappedCustomers;
    private String customerName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRmId() {
        return rmId;
    }

    public void setRmId(Long rmId) {
        this.rmId = rmId;
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public String getRmName() {
        return rmName;
    }

    public void setRmName(String rmName) {
        this.rmName = rmName;
    }

    public String getMappedCustomers() {
        return mappedCustomers;
    }

    public void setMappedCustomers(String mappedCustomers) {
        this.mappedCustomers = mappedCustomers;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

}
