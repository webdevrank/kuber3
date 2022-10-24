package com.rank.ccms.service.impl;

import com.rank.ccms.dao.FeedbackQueryMstDao;
import com.rank.ccms.entities.FeedbackQueryMst;
import com.rank.ccms.service.FeedbackQueryMstService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("feedbackQueryMstService")
public class FeedbackQueryMstServiceImpl implements FeedbackQueryMstService {

    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(FeedbackQueryMstService.class);
    @Autowired
    private FeedbackQueryMstDao feedbackQueryMstDao;

    @Override
    public FeedbackQueryMst saveFeedbackQueryMst(FeedbackQueryMst feedbackMst) {
        if (feedbackMst.getId() == null) {
            try {
                feedbackMst = feedbackQueryMstDao.saveRow(feedbackMst);
                logger.info(" In sevice save method " + feedbackMst);
            } catch (Exception ex) {

                logger.error("1.Date Parsing Exception:" + ex.getMessage(), ex);
            }
        } else {
            FeedbackQueryMst existingFeedbackQueryMst = feedbackQueryMstDao.findById(feedbackMst.getId());
            if (existingFeedbackQueryMst != null) {
                if (existingFeedbackQueryMst != feedbackMst) {
                    existingFeedbackQueryMst.setId(feedbackMst.getId());
                    existingFeedbackQueryMst.setFeedbackQuery(feedbackMst.getFeedbackQuery());
                    existingFeedbackQueryMst.setSelectedFlg(feedbackMst.getSelectedFlg());
                    existingFeedbackQueryMst.setDeleteFlg(feedbackMst.getDeleteFlg());
                    existingFeedbackQueryMst.setActiveFlg(feedbackMst.getActiveFlg());
                }
                try {

                    feedbackMst = feedbackQueryMstDao.mergeRow(existingFeedbackQueryMst);

                } catch (Exception e) {

                    logger.error("2.Date Parsing Exception:" + e.getMessage(), e);
                }
            } else {
                try {
                    feedbackMst = feedbackQueryMstDao.saveRow(feedbackMst);

                } catch (Exception ex) {

                    logger.error("3.Date Parsing Exception:" + ex.getMessage(), ex);
                }
            }
        }

        return feedbackMst;
    }

    @Override
    public FeedbackQueryMst saveFeedbackQsn(FeedbackQueryMst feedbackQueryMst) {

        if (feedbackQueryMst.getId() == null) {
            feedbackQueryMst = feedbackQueryMstDao.saveRow(feedbackQueryMst);

        }
        return feedbackQueryMst;
    }

    @Override
    public FeedbackQueryMst findFeedbackQueryMstById(Long id) {
        return feedbackQueryMstDao.findNonDeletedById(id);
    }

    @Override
    public List<FeedbackQueryMst> findAllActiveFeedbackQueryMst() {
        return feedbackQueryMstDao.findAllNonDeleted();
    }

    @Override
    public List<FeedbackQueryMst> findAllActiveSelectedFeedbackQueryMst() {

        List<FeedbackQueryMst> listFeedbackQueryMst = feedbackQueryMstDao.findAllActiveSelectedFeedbackQueryMst();
        return listFeedbackQueryMst;
    }

    @Override
    public void deleteFeedBackQuery(FeedbackQueryMst feedbackMst) {
        feedbackQueryMstDao.deleteRow(feedbackMst);
    }

    @Override
    public List<FeedbackQueryMst> selectActiveStatus() {
        return feedbackQueryMstDao.selectActiveStatus(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public FeedbackQueryMst saveActiveStatus(FeedbackQueryMst feedbackMst) {
        if (feedbackMst.getId() == null) {
            logger.info(" In sevice save method " + feedbackMst);
        } else {
            FeedbackQueryMst existingFeedbackQueryMst = feedbackQueryMstDao.findById(feedbackMst.getId());
            if (existingFeedbackQueryMst != null) {
                if (existingFeedbackQueryMst != feedbackMst) {
                    existingFeedbackQueryMst.setId(feedbackMst.getId());
                    existingFeedbackQueryMst.setFeedbackQuery(feedbackMst.getFeedbackQuery());
                    existingFeedbackQueryMst.setSelectedFlg(true);
                    existingFeedbackQueryMst.setDeleteFlg(feedbackMst.getDeleteFlg());
                    existingFeedbackQueryMst.setActiveFlg(feedbackMst.getActiveFlg());
                }
                try {

                    feedbackMst = feedbackQueryMstDao.mergeRow(existingFeedbackQueryMst);

                } catch (Exception e) {

                    logger.error("2.Date Parsing Exception:" + e.getMessage(), e);
                }
            } else {
                try {
                    feedbackMst = feedbackQueryMstDao.saveRow(feedbackMst);

                } catch (Exception ex) {

                    logger.error("3.Date Parsing Exception:" + ex.getMessage(), ex);
                }
            }
        }

        return feedbackMst;
    }

    @Override
    public FeedbackQueryMst saveDeactiveStatus(FeedbackQueryMst feedbackMst) {
        if (feedbackMst.getId() == null) {
            logger.info(" In sevice save method " + feedbackMst);
        } else {
            FeedbackQueryMst existingFeedbackQueryMst = feedbackQueryMstDao.findById(feedbackMst.getId());
            if (existingFeedbackQueryMst != null) {
                if (existingFeedbackQueryMst != feedbackMst) {
                    existingFeedbackQueryMst.setId(feedbackMst.getId());
                    existingFeedbackQueryMst.setFeedbackQuery(feedbackMst.getFeedbackQuery());
                    existingFeedbackQueryMst.setSelectedFlg(false);
                    existingFeedbackQueryMst.setDeleteFlg(feedbackMst.getDeleteFlg());
                    existingFeedbackQueryMst.setActiveFlg(feedbackMst.getActiveFlg());
                }
                try {

                    feedbackMst = feedbackQueryMstDao.mergeRow(existingFeedbackQueryMst);

                } catch (Exception e) {

                    logger.error("2.Date Parsing Exception:" + e.getMessage(), e);
                }
            } else {
                try {
                    feedbackMst = feedbackQueryMstDao.saveRow(feedbackMst);

                } catch (Exception ex) {

                    logger.error("3.Date Parsing Exception:" + ex.getMessage(), ex);
                }
            }
        }

        return feedbackMst;
    }
}
