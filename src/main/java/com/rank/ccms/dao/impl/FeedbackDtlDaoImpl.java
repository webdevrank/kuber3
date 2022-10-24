package com.rank.ccms.dao.impl;

import com.rank.ccms.dao.FeedbackDtlDao;
import com.rank.ccms.entities.FeedbackDtl;
import java.sql.Timestamp;
import java.util.List;
import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("feedbackDtlDao")
@Transactional
public class FeedbackDtlDaoImpl extends GenericDaoImpl<FeedbackDtl> implements FeedbackDtlDao {

    @Override
    public List<FeedbackDtl> findFeedbackDtlByFeedbackMasterPkId(Integer feedbackMstId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FeedbackDtl.class)
                .add(Restrictions.eq("feedbackParamId", feedbackMstId));
        return (List<FeedbackDtl>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<FeedbackDtl> findFeedbackDtlByCallId(Integer callId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FeedbackDtl.class)
                .add(Restrictions.eq("callMstId", callId));
        return (List<FeedbackDtl>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<FeedbackDtl> findAllFeedBackReport(Timestamp startDate, Timestamp endDate) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(FeedbackDtl.class);
        if (startDate != null && endDate != null) {
            detachedCriteria.add(Restrictions.between("feedbackDate", startDate, endDate));
            detachedCriteria.setFetchMode("callMstId", FetchMode.SELECT);
            detachedCriteria.setFetchMode("customerId", FetchMode.SELECT);
        }
        return (List<FeedbackDtl>) findByCriteria(detachedCriteria);
    }

}
