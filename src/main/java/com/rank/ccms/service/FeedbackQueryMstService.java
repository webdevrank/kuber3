package com.rank.ccms.service;

import com.rank.ccms.entities.FeedbackQueryMst;
import java.io.Serializable;
import java.util.List;

public interface FeedbackQueryMstService extends Serializable {

    FeedbackQueryMst saveFeedbackQueryMst(FeedbackQueryMst feedbackMst);

    FeedbackQueryMst findFeedbackQueryMstById(Long id);

    List<FeedbackQueryMst> findAllActiveFeedbackQueryMst();

    List<FeedbackQueryMst> findAllActiveSelectedFeedbackQueryMst();

    FeedbackQueryMst saveFeedbackQsn(FeedbackQueryMst feedbackQueryMst);

    void deleteFeedBackQuery(FeedbackQueryMst feedbackMst);

    List<FeedbackQueryMst> selectActiveStatus();

    FeedbackQueryMst saveActiveStatus(FeedbackQueryMst feedbackMst);

    FeedbackQueryMst saveDeactiveStatus(FeedbackQueryMst feedbackMst);

}
