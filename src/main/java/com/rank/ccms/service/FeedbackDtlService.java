package com.rank.ccms.service;

import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.entities.FeedbackDtl;
import java.sql.Timestamp;
import java.util.List;

public interface FeedbackDtlService {

    FeedbackDtl saveFeedbackDtl(FeedbackDtl feedbackDt, EmployeeMst employeeMst);

    List<FeedbackDtl> findFeedbackDtlByCallId(Integer CallId);

    List<FeedbackDtl> findAllFeedBackReport(Timestamp startDate, Timestamp endDate);

}
