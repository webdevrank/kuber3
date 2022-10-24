package com.rank.ccms.service;

import com.rank.ccms.entities.TenancyEmployeeMap;
import java.io.Serializable;
import java.util.List;

public interface TenancyEmployeeMapService extends Serializable {

    List<TenancyEmployeeMap> findVidyoTenantUrlByEmpId(Long empid);

    TenancyEmployeeMap saveTenancyEmployeeMap(TenancyEmployeeMap tenancyEmployeeMap);

    void deleteTenancyEmployeeMap(TenancyEmployeeMap tenancyEmployeeMap);

    List<TenancyEmployeeMap> findEmployeeByroomurl(String roomLink);

    TenancyEmployeeMap findById(Long id);

}
