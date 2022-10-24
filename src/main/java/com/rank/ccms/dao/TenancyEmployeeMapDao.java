package com.rank.ccms.dao;

import com.rank.ccms.entities.TenancyEmployeeMap;
import java.util.List;

public interface TenancyEmployeeMapDao extends GenericDao<TenancyEmployeeMap> {

    public List<TenancyEmployeeMap> findEqRoomlink(String roomLink);

    public List<TenancyEmployeeMap> findTenancyEmployeeMapByEmpId(Long empId);
}
