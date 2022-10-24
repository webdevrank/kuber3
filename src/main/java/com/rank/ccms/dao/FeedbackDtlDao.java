package com.rank.ccms.dao;

import com.rank.ccms.entities.FeedbackDtl;
import java.sql.Timestamp;
import java.util.List;

public interface FeedbackDtlDao extends GenericDao<FeedbackDtl> {

    public List<FeedbackDtl> findFeedbackDtlByFeedbackMasterPkId(Integer feedbackMstId);

    public List<FeedbackDtl> findFeedbackDtlByCallId(Integer callId);

    public List<FeedbackDtl> findAllFeedBackReport(Timestamp startDate, Timestamp endDate);

}
