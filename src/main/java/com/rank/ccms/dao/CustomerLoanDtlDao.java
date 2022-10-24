package com.rank.ccms.dao;

import com.rank.ccms.entities.CustomerLoanDtl;
import java.util.List;

public interface CustomerLoanDtlDao extends GenericDao<CustomerLoanDtl> {

    public CustomerLoanDtl findIDByAccounrNumber(String accountNumber);

    public CustomerLoanDtl findIDByEmailAddress(String emailAddress);

    public CustomerLoanDtl findByScheduleID(Long scheduleId);

    public CustomerLoanDtl findIDByPhoneNo(String phoneNo);

    public List<CustomerLoanDtl> findAllBySignature();
}
