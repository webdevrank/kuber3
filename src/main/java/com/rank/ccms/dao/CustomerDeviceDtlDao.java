package com.rank.ccms.dao;

import com.rank.ccms.entities.CustomerDeviceDtl;
import com.rank.ccms.entities.CustomerMst;

public interface CustomerDeviceDtlDao extends GenericDao<CustomerDeviceDtl> {

    public CustomerDeviceDtl findCustomerDeviceDtlByCustId(CustomerMst custMst);

    public CustomerDeviceDtl findCustomerDeviceDtlByOTP(CustomerMst custMst);

}
