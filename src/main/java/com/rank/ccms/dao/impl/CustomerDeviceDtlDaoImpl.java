package com.rank.ccms.dao.impl;

import com.rank.ccms.dao.CustomerDeviceDtlDao;
import com.rank.ccms.entities.CustomerDeviceDtl;
import com.rank.ccms.entities.CustomerMst;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("customerDeviceDtlDao")
@Transactional
public class CustomerDeviceDtlDaoImpl extends GenericDaoImpl<CustomerDeviceDtl> implements CustomerDeviceDtlDao {

    @Override
    public CustomerDeviceDtl findCustomerDeviceDtlByCustId(CustomerMst custMst) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CustomerDeviceDtl.class)
                .add(Restrictions.eq("customerId", custMst))
                .addOrder(Order.desc("id"));
        List<CustomerDeviceDtl> ls = (List<CustomerDeviceDtl>) findByCriteria(detachedCriteria);
        if (ls.isEmpty()) {
            return null;
        } else {
            return ls.get(0);
        }
    }

    @Override
    public CustomerDeviceDtl findCustomerDeviceDtlByOTP(CustomerMst custMst) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CustomerDeviceDtl.class)
                .add(Restrictions.eq("otpVerified", 1))
                .add(Restrictions.eq("customerId", custMst));
        List<CustomerDeviceDtl> ls = (List<CustomerDeviceDtl>) findByCriteria(detachedCriteria);
        if (ls.isEmpty()) {
            return null;
        } else {
            return ls.get(0);
        }
    }

}
