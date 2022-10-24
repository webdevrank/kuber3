package com.rank.ccms.service;

import com.rank.ccms.entities.CustomerLoanDtl;
import java.io.Serializable;
import java.util.List;

public interface CustomerLoanDtlService extends Serializable {

    CustomerLoanDtl saveCustomerLoanDtl(CustomerLoanDtl customerLoanDtl) throws Exception;

    List<CustomerLoanDtl> findAllCustomerLoanDtl();

    CustomerLoanDtl findIDByAccounrNumber(String accountNumber);

    CustomerLoanDtl findByID(Long id);

    CustomerLoanDtl findByScheduleID(Long scheduleId);

    CustomerLoanDtl findIDByEmailAddress(String emailAddress);

    CustomerLoanDtl findIDByPhoneNo(String phoneNo);
}
