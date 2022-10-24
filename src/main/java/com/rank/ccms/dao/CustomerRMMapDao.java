package com.rank.ccms.dao;

import java.util.List;

import com.rank.ccms.entities.CustomerRmMap;

public interface CustomerRMMapDao extends GenericDao<CustomerRmMap> {

    public List<CustomerRmMap> getCustomersMappedWithEmployee(Long id);

    public List<CustomerRmMap> findRMCustomerMapByRMandCustomer(Long employeeId, Long custId);

    public List<CustomerRmMap> getRMMappedWithCustomer(Long custId);

}
