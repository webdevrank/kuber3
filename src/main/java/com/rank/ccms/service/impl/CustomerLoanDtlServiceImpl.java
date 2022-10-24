package com.rank.ccms.service.impl;

import com.rank.ccms.dao.CustomerLoanDtlDao;
import com.rank.ccms.entities.CustomerLoanDtl;
import com.rank.ccms.service.CustomerLoanDtlService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("customerLoanDtlService")
public class CustomerLoanDtlServiceImpl implements CustomerLoanDtlService {

    @Autowired
    private CustomerLoanDtlDao customerLoanDtlDao;

    @Override
    public CustomerLoanDtl saveCustomerLoanDtl(CustomerLoanDtl customerLoanDtl) throws Exception {

        CustomerLoanDtl customerLoanDtlExisting = customerLoanDtlDao.findById(customerLoanDtl.getId());
        if (customerLoanDtlExisting != null) {
            if (customerLoanDtlExisting != customerLoanDtl) {
                customerLoanDtlExisting.setId(customerLoanDtl.getId());
                customerLoanDtlExisting.setAddress(customerLoanDtl.getAddress());
                customerLoanDtlExisting.setBankMstId(customerLoanDtl.getBankMstId());
                customerLoanDtlExisting.setDob(customerLoanDtl.getDob());
                customerLoanDtlExisting.setEducation(customerLoanDtl.getEducation());
                customerLoanDtlExisting.setEmail(customerLoanDtl.getEmail());
                customerLoanDtlExisting.setFullName(customerLoanDtl.getFullName());
                customerLoanDtlExisting.setGender(customerLoanDtl.getGender());
                customerLoanDtlExisting.setMaritailStatus(customerLoanDtl.getMaritailStatus());
                customerLoanDtlExisting.setNationality(customerLoanDtl.getNationality());
                customerLoanDtlExisting.setOccupation(customerLoanDtl.getOccupation());
                customerLoanDtlExisting.setPhoneNo(customerLoanDtl.getPhoneNo());
                customerLoanDtlExisting.setAnnualIncome(customerLoanDtl.getAnnualIncome());
                customerLoanDtlExisting.setLoanAmount(customerLoanDtl.getLoanAmount());
                customerLoanDtlExisting.setAccountNumber(customerLoanDtl.getAccountNumber());
                customerLoanDtlExisting.setCustomerSign(customerLoanDtl.getCustomerSign());
                customerLoanDtlExisting.setCustomerSignCord(customerLoanDtl.getCustomerSignCord());
                customerLoanDtlExisting.setLoanStatus(customerLoanDtl.getLoanStatus());
                customerLoanDtlExisting.setScheduleId(customerLoanDtl.getScheduleId());
                customerLoanDtlExisting.setLoanNo(customerLoanDtl.getLoanNo());

            }
            try {
                customerLoanDtl = customerLoanDtlDao.mergeRow(customerLoanDtlExisting);
            } catch (Exception e) {
                throw new Exception(e);
            }
        } else {
            customerLoanDtl = customerLoanDtlDao.saveRow(customerLoanDtl);
        }

        return customerLoanDtl;
    }

    @Override
    public List<CustomerLoanDtl> findAllCustomerLoanDtl() {
        return customerLoanDtlDao.findAllBySignature();
    }

    @Override
    public CustomerLoanDtl findIDByAccounrNumber(String accountNumber) {
        return customerLoanDtlDao.findIDByAccounrNumber(accountNumber);
    }

    @Override
    public CustomerLoanDtl findIDByEmailAddress(String emailAddress) {
        return customerLoanDtlDao.findIDByEmailAddress(emailAddress);
    }

    @Override
    public CustomerLoanDtl findByID(Long id) {
        return customerLoanDtlDao.findById(id);
    }

    @Override
    public CustomerLoanDtl findByScheduleID(Long scheduleId) {
        return customerLoanDtlDao.findByScheduleID(scheduleId);
    }

    @Override
    public CustomerLoanDtl findIDByPhoneNo(String phoneNo) {
        return customerLoanDtlDao.findIDByPhoneNo(phoneNo);
    }

}
