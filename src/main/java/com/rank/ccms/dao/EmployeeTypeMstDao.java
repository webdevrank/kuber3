package com.rank.ccms.dao;

import com.rank.ccms.entities.EmployeeTypeMst;

public interface EmployeeTypeMstDao extends GenericDao<EmployeeTypeMst> {

    public EmployeeTypeMst findEmployeeTypeByEmployeeTypeName(String employeeTypeName);

}
