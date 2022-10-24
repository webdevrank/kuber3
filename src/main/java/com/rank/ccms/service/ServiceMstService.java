package com.rank.ccms.service;

import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.entities.ServiceMst;
import java.io.Serializable;
import java.util.List;

public interface ServiceMstService extends Serializable {

    Integer countServiceMsts();

    void deleteServiceMst(ServiceMst servicemst, EmployeeMst employeeMst);

    List<ServiceMst> findAllNonDeletedServiceMsts();

    List<ServiceMst> findAllServiceMsts();

    ServiceMst findAllServiceMstById(Long id);

    ServiceMst findNonDeletedServiceMstById(Long id);

    ServiceMst saveServiceMst(ServiceMst servicemst, EmployeeMst employeeMst);

    List<ServiceMst> loadAllService();

    ServiceMst findServiceByServiceName(String serviceName);

    ServiceMst findServiceByServiceCode(String serviceCode);
}
