package com.rank.ccms.service;

import com.rank.ccms.entities.EmployeeCallStatus;
import com.rank.ccms.entities.EmployeeMst;
import java.io.Serializable;
import java.util.List;

public interface EmployeeCallStatusService extends Serializable {

    List<EmployeeCallStatus> findFreeOnlineAgents();

    List<EmployeeCallStatus> findFreeOnlineSpecialists();

    List<EmployeeCallStatus> findAllEmployeeCallStatus();

    EmployeeCallStatus saveEmployeeCallStatus(EmployeeCallStatus employeeCallStatus);

    List<EmployeeCallStatus> findEmployeeCallStatusByEmpId(EmployeeMst employeeMst);

    boolean updateAllEmplyeeStatusToZero() throws Exception;

    List<EmployeeCallStatus> findFreeOnlineRMs(String loginId);

    List<EmployeeCallStatus> findFreeOnlineSRMs(Long srmId);

    List<EmployeeCallStatus> findFreeOnlineBMs(Long bmId);

    EmployeeCallStatus findEmployeeCurrentCallStatusByEmpId(Long agentId);

}
