package com.rank.ccms.web;

import com.rank.ccms.dto.FeedbackQueryDto;
import com.rank.ccms.entities.FeedbackQueryMst;
import com.rank.ccms.service.FeedbackQueryMstService;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class FeedbackQueryComponent implements Serializable {

    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(FeedbackQueryComponent.class);

    private List<FeedbackQueryDto> listFeedbackQueryDto;
    private FeedbackQueryMst feedbackQueryMst;
    private String feedbackQuestion;
    private FeedbackQueryDto valueDelete;
    private FeedbackQueryDto selectFeedbackQuery;
    private FeedbackQueryDto filterFeedbackQuery;
    private Long selectionId;

    @Autowired
    private FeedbackQueryMstService feedbackQueryMstService;

    private Long activeid;

    public void checkErrors() {

    }

    public void onEdit(RowEditEvent event) {
        try {

            FeedbackQueryDto fbDto = (FeedbackQueryDto) event.getObject();
            FeedbackQueryMst fbMst = feedbackQueryMstService.findFeedbackQueryMstById(fbDto.getId());
            fbMst.setFeedbackQuery(fbDto.getFeedbackQuery());
            fbMst = feedbackQueryMstService.saveFeedbackQueryMst(fbMst);

            if (fbMst == null) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed to Update Feedback Question ", "Please try again!");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Updated Feedback Question Successfully!", "");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

    }

    public void onCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Update Cancelled");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void loadFeedbackQueryMst() {
        try {

            listFeedbackQueryDto = new ArrayList<>();

            List<FeedbackQueryMst> listFeedbackQueryMst = feedbackQueryMstService.findAllActiveFeedbackQueryMst();
            for (FeedbackQueryMst lQueryMst : listFeedbackQueryMst) {

                FeedbackQueryDto lQueryDto = new FeedbackQueryDto();

                lQueryDto.setFeedbackQuery(lQueryMst.getFeedbackQuery());
                lQueryDto.setId(lQueryMst.getId());
                lQueryDto.setSelectedFlg(lQueryMst.getSelectedFlg());
                listFeedbackQueryDto.add(lQueryDto);
            }

        } catch (Exception e) {

            logger.info("In load" + e.getMessage());
        }
    }

    public void onsave() throws ParseException {

        if (null != feedbackQuestion) {
            if (!feedbackQuestion.trim().equals("")) {
                feedbackQueryMst = new FeedbackQueryMst();
                feedbackQueryMst.setFeedbackQuery(feedbackQuestion);
                feedbackQueryMst.setActiveFlg(true);
                feedbackQueryMst.setDeleteFlg(false);
                feedbackQueryMst.setSelectedFlg(false);
                feedbackQueryMst = feedbackQueryMstService.saveFeedbackQsn(feedbackQueryMst);

                if (feedbackQueryMst != null) {
                    feedbackQueryMst = new FeedbackQueryMst();
                    this.setFeedbackQuestion("");

                    loadFeedbackQueryMst();
                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Saved Successfully", "Saved Successfully");
                    FacesContext.getCurrentInstance().addMessage(null, msg);

                } else {
                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Please Try Again");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                }
                //loadFeedbackQueryMst();

            } else {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: Feedback Question is Required", "Feedback Question is Required");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: Feedback Question is Required", "Feedback Question is Required");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

    }

    public void clear() {
        feedbackQueryMst = new FeedbackQueryMst();
        this.feedbackQuestion = "";

        loadFeedbackQueryMst();
    }

    public void onCheckActiveDeactive(Long id) {
        try {
            logger.info("INSIDE onCheckActiveDeactive" + id);
            if (id != null) {
                this.setSelectionId(id);

                RequestContext.getCurrentInstance().execute("PF('queryActiveDeactiveDialog').show();");
            }

        } catch (Exception e) {
            logger.error("ERROR: ", e);
        }
    }

    public String activateDeactiveStatus(HttpServletRequest request) {
        logger.info("In ActiveService ......" + this.getSelectionId());
        activeid = this.getSelectionId();
        FeedbackQueryMst fbMst = feedbackQueryMstService.findFeedbackQueryMstById(activeid);

        try {

            if (fbMst.getSelectedFlg() == false) {

                List<FeedbackQueryMst> activeList = feedbackQueryMstService.selectActiveStatus();
                if (activeList.size() >= 3) {
                    FacesContext.getCurrentInstance()
                            .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                                    "Can not active more the 3 Questions", "Can not active more the 3 Questions"));

                } else {

                    fbMst = feedbackQueryMstService.saveActiveStatus(fbMst);

                    if (fbMst == null) {

                        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed to Update Status ", "Please try again!");
                        FacesContext.getCurrentInstance().addMessage(null, msg);
                    } else {

                        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Activate Successfully", "Activate Successfully");
                        FacesContext.getCurrentInstance().addMessage(null, msg);
                    }

                }

            } else if (fbMst.getSelectedFlg() == true) {

                fbMst = feedbackQueryMstService.saveDeactiveStatus(fbMst);

                if (fbMst == null) {

                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed to Update Status ", "Please try again!");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                } else {

                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Deactivate Successfully", "Deactivate Successfully");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                }

            }

            loadFeedbackQueryMst();

            //selectFeedbackQuery = null;
            return "/pages/configuration/addeditFeedbackQuery.xhtml";
        } catch (Exception e) {

            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "failed,Please try again", "Please try again!!"));
            return "/pages/configuration/addeditFeedbackQuery.xhtml";

        }

    }

    public void deleteServiceToBean(FeedbackQueryDto fedq) {
        logger.info(" value when press delete " + fedq.getId());
        valueDelete = fedq;
    }

    public String deactivateService(HttpServletRequest request) {
        logger.info("In deactiveService ");

        try {
            FeedbackQueryMst fqm = feedbackQueryMstService.findFeedbackQueryMstById(valueDelete.getId());

            feedbackQueryMstService.deleteFeedBackQuery(fqm);

            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Deleted Successfully", "Deleted Successfully"));
            loadFeedbackQueryMst();
            return "/pages/configuration/addeditFeedbackQuery.xhtml";

        } catch (Exception e) {

            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "failed,Please try again", "Please try again!!"));
            return "/pages/configuration/addeditFeedbackQuery.xhtml";

        }
    }

    //All setter and getter..
    public List<FeedbackQueryDto> getListFeedbackQueryDto() {
        return listFeedbackQueryDto;
    }

    public void setListFeedbackQueryDto(List<FeedbackQueryDto> listFeedbackQueryDto) {
        this.listFeedbackQueryDto = listFeedbackQueryDto;
    }

    public String getFeedbackQuestion() {
        return feedbackQuestion;
    }

    public void setFeedbackQuestion(String feedbackQuestion) {
        this.feedbackQuestion = feedbackQuestion;
    }

    public FeedbackQueryMst getFeedbackQueryMst() {
        return feedbackQueryMst;
    }

    public void setFeedbackQueryMst(FeedbackQueryMst feedbackQueryMst) {
        this.feedbackQueryMst = feedbackQueryMst;
    }

    public FeedbackQueryDto getSelectFeedbackQuery() {
        return selectFeedbackQuery;
    }

    public void setSelectFeedbackQuery(FeedbackQueryDto selectFeedbackQuery) {
        this.selectFeedbackQuery = selectFeedbackQuery;
    }

    public FeedbackQueryDto getFilterFeedbackQuery() {
        return filterFeedbackQuery;
    }

    public void setFilterFeedbackQuery(FeedbackQueryDto filterFeedbackQuery) {
        this.filterFeedbackQuery = filterFeedbackQuery;
    }

    public Long getSelectionId() {
        return selectionId;
    }

    public void setSelectionId(Long selectionId) {
        this.selectionId = selectionId;
    }

}
