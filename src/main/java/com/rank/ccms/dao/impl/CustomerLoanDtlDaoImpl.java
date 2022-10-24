package com.rank.ccms.dao.impl;

import com.rank.ccms.dao.CustomerLoanDtlDao;
import com.rank.ccms.entities.CustomerLoanDtl;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("customerLoanDtlDao")
@Transactional
public class CustomerLoanDtlDaoImpl extends GenericDaoImpl<CustomerLoanDtl> implements CustomerLoanDtlDao {

    @Override
    public CustomerLoanDtl findIDByAccounrNumber(String accountNumber) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CustomerLoanDtl.class, "cadtl");
        detachedCriteria.add(Restrictions.eq("cadtl.accountNumber", accountNumber));
        detachedCriteria.addOrder(Order.desc("id"));
        if (!(findByCriteria(detachedCriteria).isEmpty())) {
            return (CustomerLoanDtl) findByCriteria(detachedCriteria).get(0);
        } else {
            return null;
        }
    }

    @Override
    public CustomerLoanDtl findIDByEmailAddress(String emailAddress) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CustomerLoanDtl.class, "cadtl");
        detachedCriteria.add(Restrictions.eq("cadtl.email", emailAddress));
        detachedCriteria.addOrder(Order.desc("id"));
        if (!(findByCriteria(detachedCriteria).isEmpty())) {
            return (CustomerLoanDtl) findByCriteria(detachedCriteria).get(0);
        } else {
            return null;
        }
    }

    @Override
    public CustomerLoanDtl findByScheduleID(Long scheduleId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CustomerLoanDtl.class, "cadtl");
        detachedCriteria.add(Restrictions.eq("cadtl.scheduleId", scheduleId));
        detachedCriteria.addOrder(Order.desc("id"));
        if (!(findByCriteria(detachedCriteria).isEmpty())) {
            return (CustomerLoanDtl) findByCriteria(detachedCriteria).get(0);
        } else {
            return null;
        }
    }

    @Override
    public CustomerLoanDtl findIDByPhoneNo(String phoneNo) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CustomerLoanDtl.class, "cadtl");
        detachedCriteria.add(Restrictions.eq("cadtl.phoneNo", phoneNo));
        detachedCriteria.addOrder(Order.desc("id"));
        if (!(findByCriteria(detachedCriteria).isEmpty())) {
            return (CustomerLoanDtl) findByCriteria(detachedCriteria).get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<CustomerLoanDtl> findAllBySignature() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CustomerLoanDtl.class, "cadtl");
        detachedCriteria.add(Restrictions.isNotNull("cadtl.customerSignCord"));
        detachedCriteria.addOrder(Order.desc("id"));

        return (List<CustomerLoanDtl>) findByCriteria(detachedCriteria);
    }

}
