package com.rank.ccms.service;

import com.rank.ccms.entities.CustomerMst;
import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.rest.response.CustomerDto;
import java.io.Serializable;
import java.util.List;

public interface CustomerMstService extends Serializable {

    Integer countCustomerMasters();

    void deleteCustomerMst(CustomerMst customerMst, EmployeeMst employeeMst);

    List<CustomerMst> findAllCustomerMasters();

    List<CustomerMst> findAllRegisteredCustomerMasters();

    CustomerMst findCustomerMstById(Long id);

    CustomerDto findCustomerById(Long id);

    CustomerMst findCustomerMstByCustomerId(String custId);

    CustomerMst findNonActiveByCustId(String custId);

    CustomerMst findCustomerMstByCustIdMobileNumAccount(String custId, String mobileNo, String acctNo);

    CustomerMst findCustomerMstByCustIdPassword(String custId, String pwd);

    CustomerMst findCustomerMstByMobileNumAccount(String mobileNo, String acctNo);

    CustomerMst saveCustomerMst(CustomerMst customerMst, EmployeeMst employeeMst);

    List<CustomerMst> findCustomerMstByCustomerIdGuest(String custId);

    CustomerMst findCustomerMstByMobileNo(String mobileNo);

    CustomerMst findCustomerMstByCustId(String custId);

}
