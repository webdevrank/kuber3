package com.rank.ccms.service.impl;

import com.rank.ccms.dao.TenancyEmployeeMapDao;
import com.rank.ccms.entities.TenancyEmployeeMap;
import com.rank.ccms.service.TenancyEmployeeMapService;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tenancyEmployeeMapService")
public class TenancyEmployeeMapServiceImpl implements TenancyEmployeeMapService {

    Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private TenancyEmployeeMapDao tenancyEmployeeMapDao;

    @Override
    public List<TenancyEmployeeMap> findVidyoTenantUrlByEmpId(Long empid) {

        if (!tenancyEmployeeMapDao.findTenancyEmployeeMapByEmpId(empid).isEmpty()) {
            return tenancyEmployeeMapDao.findTenancyEmployeeMapByEmpId(empid);
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public TenancyEmployeeMap saveTenancyEmployeeMap(TenancyEmployeeMap tenancyEmployeeMap) {
        return tenancyEmployeeMapDao.saveRow(tenancyEmployeeMap);

    }

    @Override
    public void deleteTenancyEmployeeMap(TenancyEmployeeMap tenancyEmployeeMap) {
        tenancyEmployeeMapDao.deleteRow(tenancyEmployeeMap);

    }

    @Override
    public List<TenancyEmployeeMap> findEmployeeByroomurl(String roomLink) {
        if (!tenancyEmployeeMapDao.findEqRoomlink(roomLink).isEmpty()) {
            return tenancyEmployeeMapDao.findEqRoomlink(roomLink);
        } else {
            return null;
        }
    }

    @Override
    public TenancyEmployeeMap findById(Long id) {
        return tenancyEmployeeMapDao.findById(id);
    }
}
