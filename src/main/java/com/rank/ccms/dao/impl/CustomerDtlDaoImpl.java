package com.rank.ccms.dao.impl;

import com.rank.ccms.dao.CustomerDtlDao;
import com.rank.ccms.entities.CustomerAccDtl;
import com.rank.ccms.entities.CustomerDtl;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

@Repository("customerDtlDao")
@Transactional
public class CustomerDtlDaoImpl extends GenericDaoImpl<CustomerDtl> implements CustomerDtlDao {

    private static final long serialVersionUID = -1683158686552639671L;

    @Transactional(readOnly = true)
    @Override
    public CustomerDtl findIDByAccounrNumber(String accountNumber) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CustomerAccDtl.class, "cadtl");
        detachedCriteria.add(Restrictions.eq("cadtl.accNo", accountNumber));
        detachedCriteria.setProjection(Projections.distinct(Projections.property("customerDtlId")));
        if (!(findByCriteria(detachedCriteria).isEmpty())) {
            return (CustomerDtl) findByCriteria(detachedCriteria).get(0);
        } else {
            return null;
        }
    }

    @Transactional(readOnly = true)
    @Override
    public CustomerDtl findIDByEmailAddress(String emailAddress) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CustomerDtl.class, "cadtl");
        detachedCriteria.add(Restrictions.eq("cadtl.email", emailAddress));
        detachedCriteria.addOrder(Order.desc("id"));
        if (!(findByCriteria(detachedCriteria).isEmpty())) {
            return (CustomerDtl) findByCriteria(detachedCriteria).get(0);
        } else {
            return null;
        }
    }

    @Override
    public CustomerDtl findIDByPhoneNo(String phoneNo) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CustomerDtl.class, "cadtl");
        detachedCriteria.add(Restrictions.eq("cadtl.phoneNo", phoneNo));
        detachedCriteria.addOrder(Order.desc("id"));
        if (!(findByCriteria(detachedCriteria).isEmpty())) {
            return (CustomerDtl) findByCriteria(detachedCriteria).get(0);
        } else {
            return null;
        }
    }

}
