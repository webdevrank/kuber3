package com.rank.ccms.service.impl;

import com.rank.ccms.dao.FeedbackDtlDao;
import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.entities.FeedbackDtl;
import com.rank.ccms.service.FeedbackDtlService;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("feedbackDtlService")
public class FeedbackDtlServiceImpl implements FeedbackDtlService {

    @Autowired
    private FeedbackDtlDao feedbackDtDao;

    @Override
    public FeedbackDtl saveFeedbackDtl(FeedbackDtl feedbackDt, EmployeeMst employeeMst) {

        if (feedbackDt.getId() == null) {
            feedbackDt = feedbackDtDao.saveRow(feedbackDt);
        } else {

            FeedbackDtl existingFeedbackDt = feedbackDtDao.findById(feedbackDt.getId());
            if (existingFeedbackDt != null) {
                if (existingFeedbackDt != feedbackDt) {
                    existingFeedbackDt.setDeleteFlg(feedbackDt.getDeleteFlg());
                    existingFeedbackDt.setActiveFlg(feedbackDt.getActiveFlg());
                }
                feedbackDt = feedbackDtDao.mergeRow(existingFeedbackDt);

            } else {
                feedbackDt = feedbackDtDao.saveRow(feedbackDt);
            }
        }

        return feedbackDt;
    }

    @Override
    public List<FeedbackDtl> findFeedbackDtlByCallId(Integer CallId) {
        return feedbackDtDao.findFeedbackDtlByCallId(CallId);
    }

    @Override
    public List<FeedbackDtl> findAllFeedBackReport(Timestamp startDate, Timestamp endDate) {
        return feedbackDtDao.findAllFeedBackReport(startDate, endDate);
    }

}
