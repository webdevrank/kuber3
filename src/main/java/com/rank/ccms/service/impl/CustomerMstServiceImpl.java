package com.rank.ccms.service.impl;

import com.rank.ccms.dao.CustomerMstDao;
import com.rank.ccms.entities.CustomerMst;
import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.rest.response.CustomerDto;
import com.rank.ccms.service.CustomerMstService;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

@Service("customerMstService")
public class CustomerMstServiceImpl extends SpringBeanAutowiringSupport implements CustomerMstService {

    Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private CustomerMstDao customerMstDao;

    @Override
    public Integer countCustomerMasters() {
        return customerMstDao.findAll().size();
    }

    @Override
    public void deleteCustomerMst(CustomerMst customerMst, EmployeeMst employeeMst) {

        customerMst.setDeleteFlg(true);
        customerMstDao.mergeRow(customerMst);

    }

    @Override
    public List<CustomerMst> findAllCustomerMasters() {
        return customerMstDao.findAllNonDeleted();
    }

    @Override
    public CustomerMst saveCustomerMst(CustomerMst customerMst, EmployeeMst employeeMst) {

        if (customerMst.getId() == null) {
            customerMst = customerMstDao.saveRow(customerMst);
        } else {
            customerMst = customerMstDao.mergeRow(customerMst);
        }

        return customerMst;
    }

    @Override
    public CustomerMst findCustomerMstById(Long id) {
        return customerMstDao.findNonDeletedByCustomerId(id);
    }

    @Override
    public CustomerMst findCustomerMstByCustomerId(String custId) {
        return customerMstDao.findNonDeletedByCustId(custId);
    }

    @Override
    public CustomerMst findNonActiveByCustId(String custId) {
        return customerMstDao.findNonActiveByCustId(custId);
    }

    @Override
    public CustomerMst findCustomerMstByCustIdMobileNumAccount(String custId, String mobileNo, String acctNo) {
        return customerMstDao.findCustomerMstByCustIdMobileNumAccount(custId, mobileNo, acctNo);
    }

    @Override
    public CustomerMst findCustomerMstByCustIdPassword(String custId, String pwd) {
        return customerMstDao.findCustomerMstByCustIdPassword(custId, pwd);
    }

    @Override
    public CustomerMst findCustomerMstByMobileNumAccount(String mobileNo, String acctNo) {
        return customerMstDao.findCustomerMstByMobileNumAccount(mobileNo, acctNo);
    }

    @Override
    public List<CustomerMst> findCustomerMstByCustomerIdGuest(String custId) {
        return (List<CustomerMst>) customerMstDao.findNonDeletedByCustIdGuest(custId);
    }

    @Override
    public CustomerMst findCustomerMstByMobileNo(String mobileNo) {
        return customerMstDao.findCustomerMstByMobileNo(mobileNo);
    }

    @Override
    public CustomerMst findCustomerMstByCustId(String custId) {
        return customerMstDao.findCustomerMstByCustId(custId);
    }

    @Override
    public List<CustomerMst> findAllRegisteredCustomerMasters() {
        return customerMstDao.findAllRegisteredCustomerMasters();
    }

    @Override
    public CustomerDto findCustomerById(Long id) {
        return customerMstDao.findCustomerById(id);
    }

}
