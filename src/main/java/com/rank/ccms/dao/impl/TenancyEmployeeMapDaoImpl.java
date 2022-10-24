package com.rank.ccms.dao.impl;

import com.rank.ccms.dao.TenancyEmployeeMapDao;
import com.rank.ccms.entities.TenancyEmployeeMap;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("tenancyEmployeeMapDao")
@Transactional
public class TenancyEmployeeMapDaoImpl extends GenericDaoImpl<TenancyEmployeeMap> implements TenancyEmployeeMapDao {

    @Override
    public List<TenancyEmployeeMap> findEqRoomlink(String roomLink) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TenancyEmployeeMap.class)
                .add(Restrictions.eq("roomLink", roomLink))
                .addOrder(Order.desc("id"));
        return (List<TenancyEmployeeMap>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<TenancyEmployeeMap> findTenancyEmployeeMapByEmpId(Long empId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(TenancyEmployeeMap.class)
                .add(Restrictions.eq("empId.id", empId));

        return (List<TenancyEmployeeMap>) findByCriteria(detachedCriteria);

    }

}
