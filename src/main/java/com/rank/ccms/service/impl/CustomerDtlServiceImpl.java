package com.rank.ccms.service.impl;

import com.rank.ccms.dao.CustomerDtlDao;
import com.rank.ccms.entities.CustomerDtl;
import com.rank.ccms.service.CustomerDtlService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("customerDtlService")
public class CustomerDtlServiceImpl implements CustomerDtlService {

    @Autowired
    private CustomerDtlDao customerDtlDao;

    @Override
    public CustomerDtl saveCustomerDtl(CustomerDtl customerDtl) throws Exception {

        CustomerDtl customerDtlExisting = customerDtlDao.findById(customerDtl.getId());
        if (customerDtlExisting != null) {
            if (customerDtlExisting != customerDtl) {
                customerDtlExisting.setId(customerDtl.getId());
                customerDtlExisting.setAddress(customerDtl.getAddress());
                customerDtlExisting.setBankMstId(customerDtl.getBankMstId());
                customerDtlExisting.setDob(customerDtl.getDob());
                customerDtlExisting.setEducation(customerDtl.getEducation());
                customerDtlExisting.setEmail(customerDtl.getEmail());
                customerDtlExisting.setFullName(customerDtl.getFullName());
                customerDtlExisting.setGender(customerDtl.getGender());
                customerDtlExisting.setMaritailStatus(customerDtl.getMaritailStatus());
                customerDtlExisting.setNationality(customerDtl.getNationality());
                customerDtlExisting.setOccupation(customerDtl.getOccupation());
                customerDtlExisting.setPhoneNo(customerDtl.getPhoneNo());
                customerDtlExisting.setSalary(customerDtl.getSalary());
                customerDtlExisting.setCustomerSign(customerDtl.getCustomerSign());
                customerDtlExisting.setCustomerSignCord(customerDtl.getCustomerSignCord());
                customerDtlExisting.setCustomerType(customerDtl.getCustomerType());

            }
            try {
                customerDtl = customerDtlDao.mergeRow(customerDtlExisting);
            } catch (Exception e) {
                throw new Exception(e);
            }
        } else {
            customerDtl = customerDtlDao.saveRow(customerDtl);
        }

        return customerDtl;
    }

    @Override
    public List<CustomerDtl> findAllCustomerDtl() {
        return customerDtlDao.findAllNonDeleted();
    }

    @Override
    public CustomerDtl findIDByAccounrNumber(String accountNumber) {
        return customerDtlDao.findIDByAccounrNumber(accountNumber);
    }

    @Override
    public CustomerDtl findIDByEmailAddress(String emailAddress) {
        return customerDtlDao.findIDByEmailAddress(emailAddress);
    }

    @Override
    public CustomerDtl findIDByPhoneNo(String phoneNo) {
        return customerDtlDao.findIDByPhoneNo(phoneNo);
    }

}
