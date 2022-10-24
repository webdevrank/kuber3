package com.rank.ccms.dao.impl;

import com.rank.ccms.dao.CustomerMstDao;
import com.rank.ccms.entities.CustomerMst;
import com.rank.ccms.rest.response.CustomerDto;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("customerMstDao")
@Transactional
public class CustomerMstDaoImpl extends GenericDaoImpl<CustomerMst> implements CustomerMstDao {

    @Transactional(readOnly = true)
    @Override
    public List<CustomerMst> findAllActivenNonDeletedCustomerMsts() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CustomerMst.class)
                .add(Restrictions.eq("deleteFlg", false))
                .add(Restrictions.eq("activeFlg", true));
        return (List<CustomerMst>) findByCriteria(detachedCriteria);
    }

    @Override
    public CustomerMst findNonDeletedByCustId(String custid) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CustomerMst.class)
                .add(Restrictions.eq("custId", custid))
                .add(Restrictions.eq("deleteFlg", false))
                .add(Restrictions.eq("activeFlg", true));
        detachedCriteria.addOrder(Order.desc("id"));
        List<CustomerMst> ls = (List<CustomerMst>) findByCriteria(detachedCriteria);
        if (ls.isEmpty()) {
            return null;
        } else {
            return ls.get(0);
        }
    }

    @Override
    public CustomerMst findNonActiveByCustId(String custid) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CustomerMst.class)
                .add(Restrictions.eq("custId", custid))
                .add(Restrictions.eq("deleteFlg", false))
                .add(Restrictions.eq("activeFlg", false));
        detachedCriteria.addOrder(Order.desc("id"));
        List<CustomerMst> ls = (List<CustomerMst>) findByCriteria(detachedCriteria);
        if (ls.isEmpty()) {
            return null;
        } else {
            return ls.get(0);
        }
    }

    @Override
    public List<CustomerMst> findNonDeletedByCustIdGuest(String custid) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CustomerMst.class)
                .add(Restrictions.ilike("custId", custid))
                .add(Restrictions.eq("deleteFlg", false))
                .add(Restrictions.eq("activeFlg", true));
        return (List<CustomerMst>) findByCriteria(detachedCriteria);

    }

    @Override
    public CustomerMst findNonDeletedByCustomerId(Long custid) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CustomerMst.class)
                .add(Restrictions.eq("id", custid))
                .add(Restrictions.eq("deleteFlg", false))
                .add(Restrictions.eq("activeFlg", true));
        List<CustomerMst> ls = (List<CustomerMst>) findByCriteria(detachedCriteria);
        if (ls.isEmpty()) {
            return null;
        } else {
            return ls.get(0);
        }
    }

    @Override
    public CustomerMst findCustomerMstByCustIdMobileNumAccount(String custId, String mobileNo, String acctNo) {
        DetachedCriteria criteria = DetachedCriteria.forClass(CustomerMst.class, "customerMst");

        if (custId != null || !"".equals(custId)) {
            criteria.add(Restrictions.eq("customerMst.custId", custId));
        }
        if (mobileNo != null && !"".equals(mobileNo)) {
            try {
                Long mob = Long.parseLong(mobileNo);
                criteria.add(Restrictions.eq("customerMst.cellPhone", mob));
            } catch (NumberFormatException e) {

            }

        }
        if (acctNo != null && !"".equals(acctNo)) {
            criteria.add(Restrictions.eq("customerMst.accountNo", acctNo));
        }

        List<CustomerMst> listCustomerMst = (List<CustomerMst>) findByCriteria(criteria);

        if (!listCustomerMst.isEmpty()) {
            return listCustomerMst.get(0);
        } else {
            return null;
        }
    }

    @Override
    public CustomerMst findCustomerMstByCustIdPassword(String custId, String pwd) {
        DetachedCriteria criteria = DetachedCriteria.forClass(CustomerMst.class, "customerMst");

        if (custId != null || !"".equals(custId)) {
            criteria.add(Restrictions.eq("customerMst.custId", custId));
        }
        if (pwd != null && !"".equals(pwd)) {
            try {

                criteria.add(Restrictions.eq("customerMst.custPwd", pwd));
            } catch (NumberFormatException e) {

            }
        }

        List<CustomerMst> listCustomerMst = (List<CustomerMst>) findByCriteria(criteria);

        if (!listCustomerMst.isEmpty()) {
            return listCustomerMst.get(0);
        } else {
            return null;
        }
    }

    @Override
    public CustomerMst findCustomerMstByCustId(String custId) {
        DetachedCriteria criteria = DetachedCriteria.forClass(CustomerMst.class, "customerMst");

        if (custId != null || !"".equals(custId)) {
            criteria.add(Restrictions.eq("customerMst.custId", custId));
        }

        List<CustomerMst> listCustomerMst = (List<CustomerMst>) findByCriteria(criteria);

        if (!listCustomerMst.isEmpty()) {
            return listCustomerMst.get(0);
        } else {
            return null;
        }
    }

    @Override
    public CustomerMst findCustomerMstByMobileNumAccount(String mobileNo, String acctNo) {
        DetachedCriteria criteria = DetachedCriteria.forClass(CustomerMst.class, "customerMst");

        if (mobileNo != null && !"".equals(mobileNo)) {
            try {
                Long mob = Long.parseLong(mobileNo);
                criteria.add(Restrictions.eq("customerMst.cellPhone", mob));
            } catch (NumberFormatException e) {

            }

        }
        if (acctNo != null && !"".equals(acctNo)) {
            criteria.add(Restrictions.eq("customerMst.accountNo", acctNo));
        }

        List<CustomerMst> listCustomerMst = (List<CustomerMst>) findByCriteria(criteria);

        if (!listCustomerMst.isEmpty()) {
            return listCustomerMst.get(0);
        } else {
            return null;
        }
    }

    @Override
    public CustomerMst findCustomerMstByMobileNo(String mobileNo) {
        DetachedCriteria criteria = DetachedCriteria.forClass(CustomerMst.class, "customerMst");
        CustomerMst customerMst = null;
        if (mobileNo != null && !"".equals(mobileNo)) {
            try {
                criteria.add(Restrictions.eq("customerMst.cellPhone", Long.parseLong(mobileNo)));
            } catch (NumberFormatException e) {

            }
        }

        List<CustomerMst> listCustomerMst = (List<CustomerMst>) findByCriteria(criteria);

        if (!listCustomerMst.isEmpty()) {
            customerMst = listCustomerMst.get(0);
        }
        return customerMst;
    }

    @Override
    public List<CustomerMst> findAllRegisteredCustomerMasters() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CustomerMst.class)
                .add(Restrictions.eq("isRegistered", true))
                .add(Restrictions.eq("deleteFlg", false))
                .add(Restrictions.eq("activeFlg", true));
        return (List<CustomerMst>) findByCriteria(detachedCriteria);
    }

    @Override
    public CustomerDto findCustomerById(Long id) {
        CustomerDto customer = null;
        try {
            DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CustomerMst.class, "CM")
                    .add(Restrictions.eq("CM.activeFlg", true))
                    .add(Restrictions.eq("CM.deleteFlg", false))
                    .add(Restrictions.eq("CM.id", id));

            ProjectionList projections = Projections.projectionList();
            detachedCriteria
                    .setProjection(projections.add(Projections.property("CM.id"), "id"))
                    .setProjection(projections.add(Projections.property("CM.firstName"), "firstName"))
                    .setProjection(projections.add(Projections.property("CM.custId"), "custId"));

            detachedCriteria.setResultTransformer(Transformers.aliasToBean(CustomerDto.class));

            List<CustomerDto> ls = (List<CustomerDto>) findByCriteria(detachedCriteria);
            if (ls.isEmpty()) {
                return null;
            } else {
                return ls.get(0);
            }
        } catch (Exception e) {
            logger.error("Error : ", e);
            return null;
        }
    }

}
