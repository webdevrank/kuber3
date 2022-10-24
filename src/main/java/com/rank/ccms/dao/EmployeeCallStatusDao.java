package com.rank.ccms.dao;

import com.rank.ccms.entities.EmployeeCallStatus;
import com.rank.ccms.entities.EmployeeMst;
import java.util.List;

public interface EmployeeCallStatusDao extends GenericDao<EmployeeCallStatus> {

    @Override
    public EmployeeCallStatus findById(Long id);

    public List<EmployeeCallStatus> findOnlineFreeAgents();

    public List<EmployeeCallStatus> findOnlineFreeSpecialists();

    public boolean updateAllEmplyeeStatusToZero();

    public List<EmployeeCallStatus> findFreeOnlineRMs(String loginId);

    public List<EmployeeCallStatus> findFreeOnlineSRMs(Long srmId);

    public List<EmployeeCallStatus> findFreeOnlineBMs(Long bmId);

    public List<EmployeeCallStatus> findEmployeeCallStatusEmployeeMst(EmployeeMst employeeMst);

    public EmployeeCallStatus findEmployeeCurrentCallStatusByEmpId(Long agentId);
}
