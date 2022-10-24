package com.rank.ccms.service.impl;

import com.rank.ccms.dao.CustomerRMMapDao;
import com.rank.ccms.entities.CustomerRmMap;
import com.rank.ccms.service.CustomerRMMapService;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("customerRMMapService")
public class CustomerRMMapServiceImpl implements CustomerRMMapService {

    Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    CustomerRMMapDao customerRMMapDao;

    @Override
    public List<CustomerRmMap> getCustomersMappedWithEmployee(Long id) {
        return customerRMMapDao.getCustomersMappedWithEmployee(id);
    }

    @Override
    public List<CustomerRmMap> findRMCustomerMapByRMandCustomer(Long employeeId, Long custId) {
        return customerRMMapDao.findRMCustomerMapByRMandCustomer(employeeId, custId);
    }

    @Override
    public void deleteCustomerRmMap(CustomerRmMap customerRmMap) {
        customerRMMapDao.deleteRow(customerRmMap);

    }

    @Override
    public CustomerRmMap saveRMCustomerMap(CustomerRmMap customerRmMap) {

        if (customerRmMap.getId() == null) {
            try {
                customerRmMap = customerRMMapDao.saveRow(customerRmMap);
            } catch (Exception e) {

            }
        } else {
            CustomerRmMap customerRmMapExisting = null;
            try {
                customerRmMapExisting = customerRMMapDao.findById(customerRmMap.getId());
            } catch (Exception e) {

            }
            if (customerRmMapExisting != null) {
                if (customerRmMapExisting != customerRmMap) {
                    customerRmMapExisting.setId(customerRmMap.getId());
                    customerRmMapExisting.setActiveFlg(customerRmMap.getActiveFlg());
                    customerRmMapExisting.setDeleteFlg(customerRmMap.getDeleteFlg());
                    customerRmMapExisting.setCustId(customerRmMap.getCustId());
                    customerRmMapExisting.setRmId(customerRmMap.getRmId());

                }
                try {
                    customerRmMap = customerRMMapDao.mergeRow(customerRmMapExisting);
                } catch (Exception e) {

                }
            } else {
                try {
                    customerRmMap = customerRMMapDao.saveRow(customerRmMap);
                } catch (Exception e) {

                }
            }
        }
        return customerRmMap;
    }

    @Override
    public List<CustomerRmMap> getRMMappedWithCustomer(Long custId) {
        List<CustomerRmMap> customerRmMapList = customerRMMapDao.getRMMappedWithCustomer(custId);
        return customerRmMapList;
    }

}
