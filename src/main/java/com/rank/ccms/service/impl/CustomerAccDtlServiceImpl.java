package com.rank.ccms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rank.ccms.dao.CustomerAccDtlDao;
import com.rank.ccms.dto.CustomerAccountDetailsDto;
import com.rank.ccms.entities.CustomerAccDtl;
import com.rank.ccms.entities.CustomerDtl;
import com.rank.ccms.service.CustomerAccDtlService;

@Service("customerAccDtlService")
public class CustomerAccDtlServiceImpl implements CustomerAccDtlService {

    @Autowired
    CustomerAccDtlDao customerAccDtlDao;

    @Transactional
    @Override
    public CustomerAccDtl getLatestBalance(String accountNumber) {

        return customerAccDtlDao.getLatestBalance(accountNumber);
    }

    @Transactional
    @Override
    public CustomerAccDtl save(CustomerAccDtl customerAccDtl) {

        if (null == customerAccDtl.getId()) {
            customerAccDtl = customerAccDtlDao.saveRow(customerAccDtl);
        }

        return customerAccDtl;
    }

    @Transactional
    @Override
    public List<CustomerAccountDetailsDto> listCustomerAccountDetails() {

        return customerAccDtlDao.listCustomerAccountDetails();
    }

    @Transactional
    @Override
    public CustomerAccDtl getAccDtlByCustomerDtl(CustomerDtl customerDtlId) {
        return customerAccDtlDao.getAccDtlByCustomerDtl(customerDtlId);
    }

}
