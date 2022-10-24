package com.rank.ccms.dao.impl;

import com.rank.ccms.dao.CustomerRMMapDao;
import com.rank.ccms.entities.CustomerRmMap;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("customerRMMapDao")
@Transactional
public class CustomerRMMapDaoImpl extends GenericDaoImpl<CustomerRmMap> implements CustomerRMMapDao {

    @Override
    public List<CustomerRmMap> getCustomersMappedWithEmployee(Long id) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CustomerRmMap.class)
                .add(Restrictions.eq("rmId.id", id));

        return (List<CustomerRmMap>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<CustomerRmMap> findRMCustomerMapByRMandCustomer(Long employeeId, Long custId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CustomerRmMap.class)
                .add(Restrictions.eq("rmId.id", employeeId))
                .add(Restrictions.eq("custId.id", custId));

        return (List<CustomerRmMap>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<CustomerRmMap> getRMMappedWithCustomer(Long custId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CustomerRmMap.class)
                .add(Restrictions.eq("custId.id", custId));
        return (List<CustomerRmMap>) findByCriteria(detachedCriteria);
    }

}
