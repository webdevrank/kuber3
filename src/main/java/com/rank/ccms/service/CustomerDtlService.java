package com.rank.ccms.service;

import com.rank.ccms.entities.CustomerDtl;
import java.io.Serializable;
import java.util.List;

public interface CustomerDtlService extends Serializable {

    CustomerDtl saveCustomerDtl(CustomerDtl customerDtl) throws Exception;

    List<CustomerDtl> findAllCustomerDtl();

    CustomerDtl findIDByAccounrNumber(String accountNumber);

    CustomerDtl findIDByEmailAddress(String emailAddress);

    CustomerDtl findIDByPhoneNo(String phoneNo);
}
