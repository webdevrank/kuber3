package com.rank.ccms.service;

import java.util.List;

import com.rank.ccms.dto.CustomerAccountDetailsDto;
import com.rank.ccms.entities.CustomerAccDtl;
import com.rank.ccms.entities.CustomerDtl;

public interface CustomerAccDtlService {

    CustomerAccDtl getLatestBalance(String accountNumber);

    CustomerAccDtl getAccDtlByCustomerDtl(CustomerDtl customerDtlId);

    CustomerAccDtl save(CustomerAccDtl customerAccDtl);

    List<CustomerAccountDetailsDto> listCustomerAccountDetails();

}
