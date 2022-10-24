package com.rank.ccms.service;

import com.rank.ccms.entities.CustomerDeviceDtl;
import com.rank.ccms.entities.CustomerMst;
import com.rank.ccms.entities.EmployeeMst;
import java.io.Serializable;

public interface CustomerDeviceDtlService extends Serializable {

    CustomerDeviceDtl findCustomerDeviceDtlByCustId(CustomerMst custMst);

    CustomerDeviceDtl findCustomerDeviceDtlByOTP(CustomerMst custMst);

    CustomerDeviceDtl saveCustomerDeviceDtl(CustomerDeviceDtl customerDeviceDtl, EmployeeMst employeeMst);
}
