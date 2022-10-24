package com.rank.ccms.service;

import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.entities.EmployeeTypeMst;
import java.io.Serializable;
import java.util.List;

public interface EmployeeMstService extends Serializable {

    EmployeeMst findEmployeeMstById(Long id);

    Integer countEmployeeMsts();

    void deleteEmployeeMst(EmployeeMst employeemst, EmployeeMst employeeMst);

    List<EmployeeMst> findAllNonDeletedEmployeeMsts();

    List<EmployeeMst> findAllEmployeeMsts();

    EmployeeMst saveEmployeeMst(EmployeeMst employeemst, EmployeeMst loginEmployeeMst);

    EmployeeMst findEmployeeInfoForLogin(String userLoginId, String userPassword);

    EmployeeMst findEmployeeByUserId(String userLoginId);
    
    EmployeeMst findEmployeeByEmailId(String userEmailId);
    
    EmployeeMst findEmployeeByMobileno(String mobileno);

    List<EmployeeMst> findEmployeeByEmpTypeId(EmployeeTypeMst employeeTypeMst);

    List<EmployeeMst> findNonDeletedEmployeeByEmpTypeId(EmployeeTypeMst employeeTypeMst);

    List<EmployeeMst> findNonDeletedEmployeeByEmpType(String empType);

    EmployeeMst findAllEmployeeByUserId(String userLoginId);

    EmployeeMst findEmployeeById_FirstName(Integer employeeId, String firstName);

}
