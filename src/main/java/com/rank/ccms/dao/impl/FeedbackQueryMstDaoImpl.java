package com.rank.ccms.dao.impl;

import com.rank.ccms.dao.FeedbackQueryMstDao;
import com.rank.ccms.entities.FeedbackQueryMst;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("feedbackQueryMstDao")
@Transactional
public class FeedbackQueryMstDaoImpl extends GenericDaoImpl<FeedbackQueryMst> implements FeedbackQueryMstDao {

    @Override
    public List<FeedbackQueryMst> selectActiveStatus() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FeedbackQueryMst.class)
                .add(Restrictions.eq("deleteFlg", false))
                .add(Restrictions.eq("activeFlg", true))
                .add(Restrictions.eq("selectedFlg", true));
        return (List<FeedbackQueryMst>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<FeedbackQueryMst> findAllActiveSelectedFeedbackQueryMst() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FeedbackQueryMst.class)
                .add(Restrictions.eq("selectedFlg", true))
                .add(Restrictions.eq("activeFlg", true))
                .add(Restrictions.eq("deleteFlg", false));

        detachedCriteria.addOrder(Order.desc("id"));
        List<FeedbackQueryMst> promotionalVideoMstList = (List<FeedbackQueryMst>) findByCriteria(detachedCriteria);

        return promotionalVideoMstList;

    }

}
