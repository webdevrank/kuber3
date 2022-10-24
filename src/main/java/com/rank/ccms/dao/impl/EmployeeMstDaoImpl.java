package com.rank.ccms.dao.impl;

import com.rank.ccms.dao.EmployeeMstDao;
import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.entities.EmployeeTypeMst;
import java.util.List;
import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("employeeMstDao")
@Transactional
public class EmployeeMstDaoImpl extends GenericDaoImpl<EmployeeMst> implements EmployeeMstDao {

    EmployeeMstDaoImpl() {
    }

    @Override
    public EmployeeMst findNonDeletedById(Long id) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeMst.class, "employee");
        detachedCriteria.setFetchMode("employee.empTypId", FetchMode.JOIN);
        List<EmployeeMst> employeeMstList = (List<EmployeeMst>) findByCriteria(detachedCriteria);
        if (null == employeeMstList || employeeMstList.isEmpty()) {
            return null;
        }
        return (EmployeeMst) employeeMstList.get(0);
    }

    @Override
    public List<EmployeeMst> findNonDeletedEmployeeByEmpType(EmployeeTypeMst employeeTypeMst) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeMst.class, "employee")
                .add(Restrictions.eq("employee.empTypId.id", employeeTypeMst.getId()))
                .add(Restrictions.eq("employee.activeFlg", true))
                .add(Restrictions.eq("employee.deleteFlg", false));
        detachedCriteria.setFetchMode("employee.empTypId", FetchMode.JOIN);
        detachedCriteria.addOrder(Order.asc("firstName"));
        detachedCriteria.addOrder(Order.asc("lastName"));
        return (List<EmployeeMst>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<EmployeeMst> findAllNonDeletedEmployee() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeMst.class, "escm");
        detachedCriteria.setFetchMode("escm.empTypId", FetchMode.SELECT);
        detachedCriteria.add(Restrictions.eq("escm.deleteFlg", false));
        detachedCriteria.createCriteria("escm.empTypId", JoinType.LEFT_OUTER_JOIN);
        detachedCriteria.addOrder(Order.asc("escm.id"));
        return (List<EmployeeMst>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<EmployeeMst> findEmployeeByEmpTypeId(Integer employeeTypeId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeMst.class, "employee")
                .add(Restrictions.eq("employee.empTypId.id", employeeTypeId));
        return (List<EmployeeMst>) findByCriteria(detachedCriteria);
    }

    @Override
    public EmployeeMst findEmployeeInfoForLogin(String userLoginId, String userPassword) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeMst.class, "escm");
        detachedCriteria.setFetchMode("escm.empTypId", FetchMode.SELECT);
        detachedCriteria.add(Restrictions.eq("escm.deleteFlg", false));
        detachedCriteria.add(Restrictions.eq("escm.loginId", userLoginId));
        // detachedCriteria.add(Restrictions.eq("escm.vidyoPasswd", userPassword));
        detachedCriteria.add(Restrictions.eq("escm.loginPasswd", userPassword));
        detachedCriteria.createCriteria("escm.empTypId", JoinType.LEFT_OUTER_JOIN);
        List<EmployeeMst> empMstList = (List<EmployeeMst>) findByCriteria(detachedCriteria);
        if (!empMstList.isEmpty()) {
            return (EmployeeMst) empMstList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public EmployeeMst findEmployeeById_FirstName(Integer employeeId, String firstName) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeMst.class, "employee")
                .createCriteria("employee.empTypId", JoinType.INNER_JOIN)
                .add(Restrictions.eq("employee.id", employeeId));
        if (firstName == null || firstName.equals("")) {
        } else {
            detachedCriteria.add(Restrictions.ilike("employee.firstName", firstName));
        }
        detachedCriteria.setFetchMode("employee.empTypId", FetchMode.SELECT);
        List<EmployeeMst> employeeMstList = (List<EmployeeMst>) findByCriteria(detachedCriteria);
        if (null == employeeMstList || employeeMstList.isEmpty()) {
            return null;
        }
        return employeeMstList.get(0);
    }

    @Override
    public List<EmployeeMst> findNonDeletedEmployeeByEmpType(String empType) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeMst.class, "employee")
                .createAlias("employee.empTypId", "ETM")
                .add(Restrictions.eq("ETM.typeName", empType).ignoreCase())
                .add(Restrictions.eq("employee.activeFlg", true))
                .add(Restrictions.eq("employee.deleteFlg", false));
        return (List<EmployeeMst>) findByCriteria(detachedCriteria);
    }

    @Override
    public EmployeeMst findEmployeeByUserId(String userLoginId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeMst.class, "EMPLOYEE");
        detachedCriteria.add(Restrictions.eq("EMPLOYEE.loginId", userLoginId));
        List<EmployeeMst> employeeMst = (List<EmployeeMst>) findByCriteria(detachedCriteria);

        if (!employeeMst.isEmpty()) {
            return employeeMst.get(0);
        } else {
            return null;
        }

    }
    
    @Override
    public EmployeeMst findEmployeeByEmailId(String userEmailId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeMst.class, "EMPLOYEE");
        detachedCriteria.add(Restrictions.eq("EMPLOYEE.email", userEmailId));
        List<EmployeeMst> employeeMst = (List<EmployeeMst>) findByCriteria(detachedCriteria);

        if (!employeeMst.isEmpty()) {
            return employeeMst.get(0);
        } else {
            return null;
        }

    }
    
    @Override
    public EmployeeMst findEmployeeByMobileno(String mobileno) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeMst.class, "EMPLOYEE");
        detachedCriteria.add(Restrictions.eq("EMPLOYEE.cellPhone", Long.parseLong(mobileno)));
        List<EmployeeMst> employeeMst = (List<EmployeeMst>) findByCriteria(detachedCriteria);

        if (!employeeMst.isEmpty()) {
            return employeeMst.get(0);
        } else {
            return null;
        }

    }

    @Override
    public List<EmployeeMst> findEmployeeByEmpType(EmployeeTypeMst employeeTypeMst) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeMst.class, "employee")
                .add(Restrictions.eq("employee.empTypId.id", employeeTypeMst.getId()))
                .add(Restrictions.eq("employee.deleteFlg", false));
        detachedCriteria.setFetchMode("employee.empTypId", FetchMode.JOIN);
        detachedCriteria.addOrder(Order.asc("firstName"));
        detachedCriteria.addOrder(Order.asc("lastName"));
        return (List<EmployeeMst>) findByCriteria(detachedCriteria);
    }

}
