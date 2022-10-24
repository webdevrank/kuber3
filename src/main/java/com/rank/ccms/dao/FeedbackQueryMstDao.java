package com.rank.ccms.dao;

import com.rank.ccms.entities.FeedbackQueryMst;
import java.util.List;

public interface FeedbackQueryMstDao extends GenericDao<FeedbackQueryMst> {

    public List<FeedbackQueryMst> selectActiveStatus();

    public List<FeedbackQueryMst> findAllActiveSelectedFeedbackQueryMst();

}
