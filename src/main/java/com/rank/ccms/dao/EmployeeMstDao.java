package com.rank.ccms.dao;

import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.entities.EmployeeTypeMst;
import java.util.List;

public interface EmployeeMstDao extends GenericDao<EmployeeMst> {

    public List<EmployeeMst> findNonDeletedEmployeeByEmpType(EmployeeTypeMst employeeTypeMst);

    public List<EmployeeMst> findAllNonDeletedEmployee();

    public List<EmployeeMst> findEmployeeByEmpTypeId(Integer employeeTypeId);

    public EmployeeMst findEmployeeInfoForLogin(String userLoginId, String userPassword);

    public EmployeeMst findEmployeeById_FirstName(Integer employeeId, String firstName);

    public List<EmployeeMst> findNonDeletedEmployeeByEmpType(String empType);

    public EmployeeMst findEmployeeByUserId(String userLoginId);
    
    public EmployeeMst findEmployeeByEmailId(String userEmailId);
    
    public EmployeeMst findEmployeeByMobileno(String mobileno);

    public List<EmployeeMst> findEmployeeByEmpType(EmployeeTypeMst employeeTypeMst);

}
