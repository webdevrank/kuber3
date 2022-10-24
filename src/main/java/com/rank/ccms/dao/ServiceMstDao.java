package com.rank.ccms.dao;

import com.rank.ccms.entities.ServiceMst;
import com.rank.ccms.rest.response.ServiceDto;

import java.util.List;

public interface ServiceMstDao extends GenericDao<ServiceMst> {

    public List<ServiceMst> getAllActiveServiceDetails();

    public ServiceMst findNonDeletedServiceMstById(Long id);

    public ServiceMst findAllServiceMstById(Long id);

    public List<ServiceDto> findAllNonDeletedServicesAsServiceDtoList();

    public ServiceMst findServiceByServiceName(String serviceName);

    public ServiceMst findServiceByServiceCode(String serviceCode);

}
