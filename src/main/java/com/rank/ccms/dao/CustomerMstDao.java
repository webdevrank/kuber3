package com.rank.ccms.dao;

import com.rank.ccms.entities.CustomerMst;
import com.rank.ccms.rest.response.CustomerDto;

import java.util.List;

public interface CustomerMstDao extends GenericDao<CustomerMst> {

    public CustomerMst findNonDeletedByCustId(String custid);

    public CustomerMst findNonActiveByCustId(String custid);

    public List<CustomerMst> findNonDeletedByCustIdGuest(String custid);

    public List<CustomerMst> findAllRegisteredCustomerMasters();

    public CustomerMst findNonDeletedByCustomerId(Long custid);

    public List<CustomerMst> findAllActivenNonDeletedCustomerMsts();

    public CustomerMst findCustomerMstByCustIdMobileNumAccount(String custId, String mobileNo, String acctNo);

    public CustomerMst findCustomerMstByCustIdPassword(String custId, String pwd);

    public CustomerMst findCustomerMstByMobileNumAccount(String mobileNo, String acctNo);

    public CustomerMst findCustomerMstByMobileNo(String mobileNo);

    public CustomerMst findCustomerMstByCustId(String custId);

    public CustomerDto findCustomerById(Long id);

}
