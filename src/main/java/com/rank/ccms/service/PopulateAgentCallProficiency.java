package com.rank.ccms.service;

import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.entities.EmployeeTypeMst;
import java.io.Serializable;

public interface PopulateAgentCallProficiency extends Serializable {

    Integer loadAgentCallProficiencies(EmployeeMst employeeMaster, EmployeeTypeMst employeeTypeAgentMst);

}
