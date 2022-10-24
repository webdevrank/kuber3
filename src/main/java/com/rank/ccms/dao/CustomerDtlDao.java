package com.rank.ccms.dao;

import com.rank.ccms.entities.CustomerDtl;

public interface CustomerDtlDao extends GenericDao<CustomerDtl> {

    public CustomerDtl findIDByAccounrNumber(String accountNumber);

    public CustomerDtl findIDByEmailAddress(String emailAddress);

    public CustomerDtl findIDByPhoneNo(String phoneNo);

}
