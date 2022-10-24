package com.rank.ccms.dao.impl;

import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.rank.ccms.dao.CustomerAccDtlDao;
import com.rank.ccms.dto.CustomerAccountDetailsDto;
import com.rank.ccms.entities.CustomerAccDtl;
import com.rank.ccms.entities.CustomerDtl;
import com.rank.ccms.util.SqlConstant;

@Repository("customerAccDtlDao")
@Transactional
public class CustomerAccDtlDaoImpl extends GenericDaoImpl<CustomerAccDtl> implements CustomerAccDtlDao {

    private static final long serialVersionUID = 4001661821069179907L;

    @Transactional(readOnly = true)
    @Override
    public CustomerAccDtl getLatestBalance(String accountNumber) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CustomerAccDtl.class, "cadtl")
                .add(Restrictions.eq("cadtl.accNo", accountNumber))
                .addOrder(Order.desc("id"));
        if (!(findByCriteria(detachedCriteria).isEmpty())) {
            return (CustomerAccDtl) findByCriteria(detachedCriteria).get(0);
        } else {
            return null;
        }

    }

    @SuppressWarnings("unchecked")
    @Override
    public List<CustomerAccountDetailsDto> listCustomerAccountDetails() {

        org.hibernate.Query query = getSessionFactory().getCurrentSession().
                createSQLQuery(SqlConstant.CUSTOMER_ACCOUNT_DETAIL_LIST).setResultTransformer(Transformers.aliasToBean(CustomerAccountDetailsDto.class));
        List<CustomerAccountDetailsDto> results = query.list();
        return results.isEmpty() ? null : results;

    }

    @Override
    public CustomerAccDtl getAccDtlByCustomerDtl(CustomerDtl customerDtlId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CustomerAccDtl.class, "cadtl")
                .add(Restrictions.eq("cadtl.customerDtlId", customerDtlId))
                .addOrder(Order.desc("id"));
        if (!(findByCriteria(detachedCriteria).isEmpty())) {
            return (CustomerAccDtl) findByCriteria(detachedCriteria).get(0);
        } else {
            return null;
        }

    }

}
