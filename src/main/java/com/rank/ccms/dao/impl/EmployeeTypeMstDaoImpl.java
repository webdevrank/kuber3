package com.rank.ccms.dao.impl;

import com.rank.ccms.dao.EmployeeTypeMstDao;
import com.rank.ccms.entities.EmployeeTypeMst;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("employeeTypeMstDao")
@Transactional
public class EmployeeTypeMstDaoImpl extends GenericDaoImpl<EmployeeTypeMst> implements EmployeeTypeMstDao {

    @Override
    public EmployeeTypeMst findEmployeeTypeByEmployeeTypeName(String employeeTypeName) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeTypeMst.class)
                .add(Restrictions.eq("deleteFlg", false))
                .add(Restrictions.eq("activeFlg", true))
                .add(Restrictions.eq("typeName", employeeTypeName).ignoreCase());

        List<EmployeeTypeMst> employeeList = (List<EmployeeTypeMst>) findByCriteria(detachedCriteria);
        if (!employeeList.isEmpty()) {
            return employeeList.get(0);
        } else {
            return null;
        }
    }
}
