package com.rank.ccms.service.impl;

import com.rank.ccms.dao.EmployeeCallStatusDao;
import com.rank.ccms.entities.EmployeeCallStatus;
import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.service.EmployeeCallStatusService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("employeeCallStatusService")
public class EmployeeCallStatusServiceImpl implements EmployeeCallStatusService {

    @Autowired
    private EmployeeCallStatusDao employeeCallStatusDao;
    org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(this.getClass());

    @Override
    public List<EmployeeCallStatus> findFreeOnlineAgents() {
        return employeeCallStatusDao.findOnlineFreeAgents();

    }

    @Override
    public List<EmployeeCallStatus> findFreeOnlineSpecialists() {
        return employeeCallStatusDao.findOnlineFreeSpecialists();

    }

    @Override
    public List<EmployeeCallStatus> findAllEmployeeCallStatus() {
        return employeeCallStatusDao.findAll();
    }

    @Override
    public EmployeeCallStatus saveEmployeeCallStatus(EmployeeCallStatus employeeCallStatus) {
        if (employeeCallStatus.getId() == null) {
            employeeCallStatus = employeeCallStatusDao.saveRow(employeeCallStatus);

        } else {
            EmployeeCallStatus existingEmployeeCallStatus = employeeCallStatusDao.findById(employeeCallStatus.getId());
            if (existingEmployeeCallStatus != null) {
                if (existingEmployeeCallStatus != employeeCallStatus) {
                    existingEmployeeCallStatus.setId(employeeCallStatus.getId());
                    existingEmployeeCallStatus.setCallCount(employeeCallStatus.getCallCount());
                    existingEmployeeCallStatus.setEndTime(employeeCallStatus.getEndTime());
                    existingEmployeeCallStatus.setStatus(employeeCallStatus.getStatus());
                    existingEmployeeCallStatus.setEmpId(employeeCallStatus.getEmpId());
                }
                try {
                    employeeCallStatus = employeeCallStatusDao.mergeRow(existingEmployeeCallStatus);
                } catch (Exception e) {
                    logger.info("Error:saveEmployeeCallStatus" + e.getMessage());
                }
            } else {
                employeeCallStatus = employeeCallStatusDao.saveRow(employeeCallStatus);
            }
        }
        return employeeCallStatus;
    }

    @Override
    public List<EmployeeCallStatus> findEmployeeCallStatusByEmpId(EmployeeMst employeeMst) {
        List<EmployeeCallStatus> employeeCallStatusList = employeeCallStatusDao.findEmployeeCallStatusEmployeeMst(employeeMst);
        return employeeCallStatusList;
    }

    @Override
    public boolean updateAllEmplyeeStatusToZero() throws Exception {
        boolean flag;
        try {

            flag = true;
            List<EmployeeCallStatus> lgy = employeeCallStatusDao.findOnlineFreeAgents();
            if (!lgy.isEmpty()) {
                for (EmployeeCallStatus lgy1 : lgy) {
                    lgy1.setStatus(false);
                    employeeCallStatusDao.mergeRow(lgy1);
                }
            }
        } catch (Exception e) {
            flag = false;
            throw new Exception(e);
        }
        return flag;
    }

    @Override
    public List<EmployeeCallStatus> findFreeOnlineRMs(String loginId) {
        return employeeCallStatusDao.findFreeOnlineRMs(loginId);
    }

    @Override
    public List<EmployeeCallStatus> findFreeOnlineSRMs(Long srmId) {
        return employeeCallStatusDao.findFreeOnlineSRMs(srmId);
    }

    @Override
    public List<EmployeeCallStatus> findFreeOnlineBMs(Long bmId) {
        return employeeCallStatusDao.findFreeOnlineBMs(bmId);
    }

    @Override
    public EmployeeCallStatus findEmployeeCurrentCallStatusByEmpId(Long agentId) {
        return employeeCallStatusDao.findEmployeeCurrentCallStatusByEmpId(agentId);
    }

}
