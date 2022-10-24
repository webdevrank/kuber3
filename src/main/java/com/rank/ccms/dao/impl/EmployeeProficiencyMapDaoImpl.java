package com.rank.ccms.dao.impl;

import com.rank.ccms.dao.EmployeeProficiencyMapDao;
import com.rank.ccms.entities.EmployeeProficiencyMap;
import java.util.List;
import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("employeeProficiencyMapDao")
@Transactional
public class EmployeeProficiencyMapDaoImpl extends GenericDaoImpl<EmployeeProficiencyMap> implements EmployeeProficiencyMapDao {

    @Override
    public List<EmployeeProficiencyMap> findAllActivenNonDeletedEmployeeProficiencyMapsByEmpId(Long empPkId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeProficiencyMap.class)
                .add(Restrictions.eq("empId.id", empPkId))
                .add(Restrictions.eq("deleteFlg", false))
                .add(Restrictions.eq("activeFlg", true))
                .addOrder(Order.asc("type"));
        detachedCriteria.setFetchMode("empId", FetchMode.JOIN);
        return (List<EmployeeProficiencyMap>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<EmployeeProficiencyMap> findAllActivenNonDeletedEmployeeProficiencyMapsByEmpId(Long empPkId, Long empTypPkId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeProficiencyMap.class)
                .add(Restrictions.eq("empId.id", empPkId))
                .add(Restrictions.eq("empTypId", empTypPkId))
                .add(Restrictions.eq("deleteFlg", false))
                .add(Restrictions.eq("activeFlg", true))
                .addOrder(Order.asc("type"));
        detachedCriteria.setFetchMode("empId", FetchMode.JOIN);
        return (List<EmployeeProficiencyMap>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<EmployeeProficiencyMap> findEmployeeProficiencyMapsByEmpPkId(Long empPkId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeProficiencyMap.class)
                .add(Restrictions.eq("empId.id", empPkId))
                .addOrder(Order.asc("type"));
        return (List<EmployeeProficiencyMap>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<EmployeeProficiencyMap> findEmployeeProficiencyMapsBySkillId(String type, Long skillId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeProficiencyMap.class)
                .add(Restrictions.eq("type", type).ignoreCase())
                .add(Restrictions.eq("deleteFlg", false))
                .add(Restrictions.eq("activeFlg", true))
                .add(Restrictions.eq("skillId", skillId));
        return (List<EmployeeProficiencyMap>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<EmployeeProficiencyMap> findEmployeeProficiencyMapsBySkillId(String type, Integer skillId, Integer empTypPkId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeProficiencyMap.class)
                .add(Restrictions.eq("type", type.toLowerCase()).ignoreCase())
                .add(Restrictions.eq("empTypId", empTypPkId))
                .add(Restrictions.eq("skillId", skillId))
                .add(Restrictions.eq("deleteFlg", false))
                .add(Restrictions.eq("activeFlg", true))
                .addOrder(Order.asc("type"))
                .addOrder(Order.asc("empId.id"));
        return (List<EmployeeProficiencyMap>) findByCriteria(detachedCriteria);
    }

}
