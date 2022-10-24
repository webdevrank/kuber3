package com.rank.ccms.service;

import com.rank.ccms.entities.CustomerRmMap;
import java.io.Serializable;
import java.util.List;

public interface CustomerRMMapService extends Serializable {

    List<CustomerRmMap> getCustomersMappedWithEmployee(Long id);

    List<CustomerRmMap> getRMMappedWithCustomer(Long custId);

    List<CustomerRmMap> findRMCustomerMapByRMandCustomer(Long employeeId, Long custId);

    void deleteCustomerRmMap(CustomerRmMap customerRmMap);

    CustomerRmMap saveRMCustomerMap(CustomerRmMap customerRmMap);
}
