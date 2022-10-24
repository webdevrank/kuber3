package com.rank.ccms.dao.impl;

import com.rank.ccms.dao.PromotionalVideoMstDao;
import com.rank.ccms.entities.PromotionalVideoMst;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("promotionalVideoMstDao")
@Transactional
public class PromotionalVideoMstDaoImpl extends GenericDaoImpl<PromotionalVideoMst> implements PromotionalVideoMstDao {

    @Override
    public PromotionalVideoMst findSelectedPromotionalVideo() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PromotionalVideoMst.class)
                .add(Restrictions.eq("selectedFlg", true))
                .add(Restrictions.eq("activeFlg", true))
                .add(Restrictions.eq("deleteFlg", false));

        detachedCriteria.addOrder(Order.desc("id"));
        List<PromotionalVideoMst> promotionalVideoMstList = (List<PromotionalVideoMst>) findByCriteria(detachedCriteria);
        if (!promotionalVideoMstList.isEmpty()) {
            return promotionalVideoMstList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public PromotionalVideoMst findByCaptionName(String caption) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PromotionalVideoMst.class)
                .add(Restrictions.eq("videoCaption", caption))
                .add(Restrictions.eq("activeFlg", true))
                .add(Restrictions.eq("deleteFlg", false));

        detachedCriteria.addOrder(Order.desc("id"));
        List<PromotionalVideoMst> promotionalVideoMstList = (List<PromotionalVideoMst>) findByCriteria(detachedCriteria);
        if (!promotionalVideoMstList.isEmpty()) {
            return promotionalVideoMstList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public PromotionalVideoMst findByVideoFileName(String fileName) {

        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(PromotionalVideoMst.class)
                .add(Restrictions.eq("fileName", fileName))
                .add(Restrictions.eq("activeFlg", true))
                .add(Restrictions.eq("deleteFlg", false));

        detachedCriteria.addOrder(Order.desc("id"));
        List<PromotionalVideoMst> promotionalVideoMstList = (List<PromotionalVideoMst>) findByCriteria(detachedCriteria);
        if (!promotionalVideoMstList.isEmpty()) {
            return promotionalVideoMstList.get(0);
        } else {
            return null;
        }

    }

}
