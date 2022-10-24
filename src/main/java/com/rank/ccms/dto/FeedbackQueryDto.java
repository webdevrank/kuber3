package com.rank.ccms.dto;

import java.io.Serializable;

public class FeedbackQueryDto implements Serializable {

    private Long id;
    private String feedbackQuery;
    private boolean activeFlg;
    private boolean deleteFlg;
    private boolean selectedFlg;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFeedbackQuery() {
        return feedbackQuery;
    }

    public void setFeedbackQuery(String feedbackQuery) {
        this.feedbackQuery = feedbackQuery;
    }

    public boolean getActiveFlg() {
        return activeFlg;
    }

    public void setActiveFlg(boolean activeFlg) {
        this.activeFlg = activeFlg;
    }

    public boolean getDeleteFlg() {
        return deleteFlg;
    }

    public void setDeleteFlg(boolean deleteFlg) {
        this.deleteFlg = deleteFlg;
    }

    public boolean getSelectedFlg() {
        return selectedFlg;
    }

    public void setSelectedFlg(boolean selectedFlg) {
        this.selectedFlg = selectedFlg;
    }

}
