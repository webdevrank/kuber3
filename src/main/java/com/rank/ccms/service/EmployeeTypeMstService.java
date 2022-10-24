package com.rank.ccms.service;

import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.entities.EmployeeTypeMst;
import java.io.Serializable;
import java.util.List;

public interface EmployeeTypeMstService extends Serializable {

    EmployeeTypeMst saveEmployeeTypeMst(EmployeeTypeMst employeeTypeMst, EmployeeMst employeeMst);

    List<EmployeeTypeMst> findAllNonDeletedEmployeeTypeMst();

    EmployeeTypeMst findEmployeeTypeMstById(Long id);

    EmployeeTypeMst findNonDeletedEmployeeTypeMstById(Long id);

    List<EmployeeTypeMst> findAllEmployeeTypeMsts();

    EmployeeTypeMst findEmployeeTypeByEmployeeTypeName(String employeeTypeName);

}
