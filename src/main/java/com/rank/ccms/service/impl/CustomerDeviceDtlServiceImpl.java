package com.rank.ccms.service.impl;

import com.rank.ccms.dao.CustomerDeviceDtlDao;
import com.rank.ccms.entities.CustomerDeviceDtl;
import com.rank.ccms.entities.CustomerMst;
import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.service.CustomerDeviceDtlService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("customerDeviceDtlService")
public class CustomerDeviceDtlServiceImpl implements CustomerDeviceDtlService {

    Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private CustomerDeviceDtlDao customerDeviceDtlDao;

    @Override
    public CustomerDeviceDtl findCustomerDeviceDtlByCustId(CustomerMst custMst) {
        return customerDeviceDtlDao.findCustomerDeviceDtlByCustId(custMst);
    }

    @Override
    public CustomerDeviceDtl findCustomerDeviceDtlByOTP(CustomerMst custMst) {
        return customerDeviceDtlDao.findCustomerDeviceDtlByOTP(custMst);
    }

    @Override
    public CustomerDeviceDtl saveCustomerDeviceDtl(CustomerDeviceDtl customerDeviceDtl, EmployeeMst employeeMst) {

        if (customerDeviceDtl.getId() == null) {
            customerDeviceDtl = customerDeviceDtlDao.saveRow(customerDeviceDtl);
        } else {
            customerDeviceDtl = customerDeviceDtlDao.mergeRow(customerDeviceDtl);
        }

        return customerDeviceDtl;
    }

}
