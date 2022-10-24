package com.rank.ccms.dao;

import com.rank.ccms.dto.CustomerAccountDetailsDto;
import com.rank.ccms.entities.CustomerAccDtl;
import com.rank.ccms.entities.CustomerDtl;
import java.util.List;

public interface CustomerAccDtlDao extends GenericDao<CustomerAccDtl> {

    public CustomerAccDtl getLatestBalance(String accountNumber);

    public CustomerAccDtl getAccDtlByCustomerDtl(CustomerDtl customerDtlId);

    public List<CustomerAccountDetailsDto> listCustomerAccountDetails();

}
