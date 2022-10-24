package com.rank.ccms.service.impl;

import com.rank.ccms.entities.EmployeeCallProficiency;
import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.entities.EmployeeTypeMst;
import com.rank.ccms.service.EmployeeCallProficiencyService;
import com.rank.ccms.service.EmployeeLoginService;
import com.rank.ccms.service.EmployeeMstService;
import com.rank.ccms.service.PopulateAgentCallProficiency;
import java.util.List;
import java.util.Objects;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("employeeLoginService")
public class EmployeeLoginServiceImpl implements EmployeeLoginService {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private EmployeeMstService employeeMstService;
    @Autowired
    private EmployeeCallProficiencyService employeeCallProficiencyService;
    @Autowired
    private PopulateAgentCallProficiency populateAgentCallProficiency;

    @Override
    public EmployeeMst checkLogin(String userLoginId, String userPassword, String sessionId) {
        EmployeeMst employeeMst = null;
        try {
            employeeMst = employeeMstService.findEmployeeInfoForLogin(userLoginId, userPassword);
            if (null != employeeMst) {
                if (employeeMst.getActiveFlg() == true) {
                    logger.info("Login Credentials are valid and login successful.");
                } else {
                    logger.error("Employee has been Deactivated. Contact Administrator.");
                }
            } else {
                //Login failed.
                logger.info("Login Failed for Invalid Credentials.");
            }
        } catch (Exception ex) {

            logger.error("Login Failed with exception. " + ex.getMessage());
        }
        return employeeMst;
    }

    @Override
    public Integer deleteAndRePopulateCallProficiencies(EmployeeMst employeeMaster, EmployeeTypeMst employeeTypeAgentMst) {
        if (Objects.equals(employeeMaster.getEmpTypId().getId(), employeeTypeAgentMst.getId())) {
            List<EmployeeCallProficiency> agentCallProficiencyList = employeeCallProficiencyService.findCallProficiencyListByEmployeeMst(employeeMaster);
            for (EmployeeCallProficiency empCallProficiency : agentCallProficiencyList) {
                employeeCallProficiencyService.deleteActuallyFromDb(empCallProficiency);
            }
        }
        return populateAgentCallProficiency.loadAgentCallProficiencies(employeeMaster, employeeTypeAgentMst);
    }

    @Override
    public boolean doLogout(EmployeeMst employeeMaster) {
        boolean l_Return;
        //First delete all the old Call Proficiencies from Database then insert again for fresh values
        List<EmployeeCallProficiency> agentCallProficiencyList = employeeCallProficiencyService.findCallProficiencyListByEmployeeMst(employeeMaster);
        if (agentCallProficiencyList == null || agentCallProficiencyList.isEmpty()) {
            l_Return = true;
        } else {
            for (EmployeeCallProficiency empCallProficiency : agentCallProficiencyList) {
                employeeCallProficiencyService.deleteActuallyFromDb(empCallProficiency);
            }
            l_Return = true;
        }
        return l_Return;
    }

}
