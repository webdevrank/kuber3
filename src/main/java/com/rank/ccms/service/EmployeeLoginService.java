package com.rank.ccms.service;

import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.entities.EmployeeTypeMst;
import java.io.Serializable;

public interface EmployeeLoginService extends Serializable {

    EmployeeMst checkLogin(String userLoginId, String userPassword, String sessionId);

    Integer deleteAndRePopulateCallProficiencies(EmployeeMst employeeMaster, EmployeeTypeMst employeeTypeAgentMst);

    boolean doLogout(EmployeeMst employeeMaster);

}
