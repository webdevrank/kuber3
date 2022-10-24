package com.rank.ccms.web;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.rank.ccms.dto.FeedbackReportDtlDto;
import com.rank.ccms.entities.CallDtl;
import com.rank.ccms.entities.CallMst;
import com.rank.ccms.entities.CustomerMst;
import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.entities.FeedbackDtl;
import com.rank.ccms.service.CallDtlService;
import com.rank.ccms.service.CallMstService;
import com.rank.ccms.service.CustomerMstService;
import com.rank.ccms.service.EmployeeMstService;
import com.rank.ccms.service.FeedbackDtlService;
import com.rank.ccms.util.CustomConvert;
import com.rank.ccms.util.ThreadSafeSimpleDateFormat;

@Component
@Scope("session")
public class FeedbackReportComponent {

    private static final Logger logger = Logger.getLogger(FeedbackReportComponent.class);
    private Date startTime;
    private Date endTime;
    private Date presentDate;
    List<FeedbackReportDtlDto> finalList = new ArrayList<>();
    private boolean exportToCsvBtnStatus1;
    @Autowired
    CallMstService callMstService;
    @Autowired
    FeedbackDtlService feedbackDtlService;

    @Autowired
    CallDtlService callDtlService;

    @Autowired
    EmployeeMstService employeeMstService;

    @Autowired
    CustomerMstService customerMstService;

    public void newFeedbackReport() throws ParseException {

        this.startTime = null;
        this.endTime = null;
        setExportToCsvBtnStatus1(false);
        finalList = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        Date d = cal.getTime();
        setPresentDate(d);
    }

    public List<FeedbackReportDtlDto> loadFeedbackReportBySearch(Date starttime, Date endtime) {
        logger.info("value " + starttime + " " + endtime);

        finalList = new ArrayList<>();
        ThreadSafeSimpleDateFormat sdf1 = new ThreadSafeSimpleDateFormat("dd-MMM-yyyy");
        ThreadSafeSimpleDateFormat sdf2 = new ThreadSafeSimpleDateFormat("HH:mm:ss");

        if (starttime == null && endtime != null || starttime != null && endtime == null || starttime == null && endtime == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please select Start Date and End Date,both!", "Search Unsuccessfull!!"));
            this.startTime = null;
            this.endTime = null;
            setExportToCsvBtnStatus1(false);
        }
        if (starttime != null && endtime != null) {

            try {

                if (starttime.after(endtime)) {
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Start Date should be less than End Date!", "Search Unsuccessfull!!"));
                } else {

                    Timestamp dt_time1 = CustomConvert.javaDateToTimeStamp(starttime);

                    Calendar cal = Calendar.getInstance();
                    cal.setTime(endtime);
                    cal.set(Calendar.HOUR_OF_DAY, 23);
                    cal.set(Calendar.MINUTE, 59);
                    cal.set(Calendar.SECOND, 0);
                    cal.set(Calendar.MILLISECOND, 0);
                    Date d = cal.getTime();

                    Timestamp dt_time2 = CustomConvert.javaDateToTimeStamp(d);

                    List<FeedbackDtl> listOfRecord = feedbackDtlService.findAllFeedBackReport(dt_time1, dt_time2);
                    logger.info(" listOfRecord " + listOfRecord);
                    if (!listOfRecord.isEmpty()) {

                        for (FeedbackDtl listObj : listOfRecord) {

                            FeedbackReportDtlDto feedbackReportDtlDto = new FeedbackReportDtlDto();

                            feedbackReportDtlDto.setFeedBackDtlId(listObj.getId());

                            if (listObj.getComments() != null) {
                                feedbackReportDtlDto.setComments(listObj.getComments());
                            } else {
                                feedbackReportDtlDto.setComments("");
                            }
                            if (listObj.getFeedbackDate() != null) {
                                feedbackReportDtlDto.setFeedbackDate(sdf1.format(listObj.getFeedbackDate()));
                                feedbackReportDtlDto.setFeedbackTime(sdf2.format(listObj.getFeedbackDate()));
                            } else {
                                feedbackReportDtlDto.setFeedbackDate(null);
                                feedbackReportDtlDto.setFeedbackTime(null);
                            }

                            String ratVal[] = listObj.getStarRating().split("~");
                            feedbackReportDtlDto.setQuality(ratVal[0]);
                            feedbackReportDtlDto.setQualityFeedback(setFeedbackComments(ratVal[0]));

                            feedbackReportDtlDto.setAbility(ratVal[1]);
                            feedbackReportDtlDto.setAbilityFeedback(setFeedbackComments(ratVal[1]));
                            if (ratVal[2] != null && !ratVal[2].equals("")) {
                                if (ratVal[2].matches("\\d+")) {
                                    feedbackReportDtlDto.setRecommend(ratVal[2]);
                                    feedbackReportDtlDto.setRecommendFeedback(setFeedbackComments(ratVal[2]));
                                } else if (ratVal[2].equalsIgnoreCase("MayBe")) {
                                    feedbackReportDtlDto.setRecommend("3");
                                    feedbackReportDtlDto.setRecommendFeedback(setFeedbackComments("3"));
                                } else if (ratVal[2].equalsIgnoreCase("Yes")) {
                                    feedbackReportDtlDto.setRecommend("5");
                                    feedbackReportDtlDto.setRecommendFeedback(setFeedbackComments("5"));
                                } else if (ratVal[2].equalsIgnoreCase("No")) {
                                    feedbackReportDtlDto.setRecommend("1");
                                    feedbackReportDtlDto.setRecommendFeedback(setFeedbackComments("1"));
                                } else {
                                    feedbackReportDtlDto.setRecommend("1");
                                    feedbackReportDtlDto.setRecommendFeedback(setFeedbackComments("1"));
                                }
                            } else {
                                feedbackReportDtlDto.setRecommend("0");
                            }

                            if (listObj.getFeedbackQuery() != null && !listObj.getFeedbackQuery().equals("")) {
                                String ratValQue[] = listObj.getFeedbackQuery().split("~");
                                feedbackReportDtlDto.setQualityQuery(ratValQue[0]);
                                feedbackReportDtlDto.setAbilityQuery(ratValQue[1]);
                                feedbackReportDtlDto.setRecommendQuery(ratValQue[2]);
                            } else {
                                feedbackReportDtlDto.setQualityQuery("How would you rate the quality of the service provided by our representative?");
                                feedbackReportDtlDto.setAbilityQuery("How would you rate your level of satisfaction with the resolution provided by our representative?");
                                feedbackReportDtlDto.setRecommendQuery("How would you rate the overall quality of our video channel?");
                            }

                            CustomerMst cust = customerMstService.findCustomerMstByCustId(listObj.getCustomerId().getCustId());
                            feedbackReportDtlDto.setCustomerName(cust.getFirstName() + " " + cust.getLastName());
                            feedbackReportDtlDto.setCustomerPhoneNo(cust.getCellPhone().toString());
                            if (cust.getEmail() != null) {
                                feedbackReportDtlDto.setCustomerEmail(cust.getEmail());
                            } else {
                                feedbackReportDtlDto.setCustomerEmail("");
                            }

                            CallMst lCallMst = callMstService.findCallMstById(listObj.getCallMstId().getId());
                            feedbackReportDtlDto.setCallMstId(listObj.getCallMstId().getId());
                            List<CallDtl> lCallDtlList = callDtlService.findNonDeletedCallDtlByCallId(lCallMst);
                            CallDtl lCallDtl = lCallDtlList.get(lCallDtlList.size() - 1);
                            EmployeeMst empMst = employeeMstService.findEmployeeMstById(lCallDtl.getHandeledById().getId());

                            feedbackReportDtlDto.setAgentLoginId(empMst.getLoginId());
                            feedbackReportDtlDto.setAgentEmail(empMst.getEmail());
                            feedbackReportDtlDto.setAgentPhoneNo(empMst.getCellPhone() + "");

                            if (empMst.getMidName() != null && !empMst.getMidName().equals("")) {
                                feedbackReportDtlDto.setAgentName(empMst.getFirstName() + " " + empMst.getMidName() + " " + empMst.getLastName());
                            } else {
                                feedbackReportDtlDto.setAgentName(empMst.getFirstName() + " " + empMst.getLastName());
                            }

                            finalList.add(feedbackReportDtlDto);

                        }

                    }
                }
                if (!finalList.isEmpty()) {
                    setExportToCsvBtnStatus1(true);
                } else {
                    setExportToCsvBtnStatus1(false);
                }
            } catch (Exception ex) {
                logger.info("Exception " + ex);
            }
        }

        return finalList;

    }

    public void clear() {
        this.startTime = null;
        this.endTime = null;

        if (finalList != null) {
            if (!finalList.isEmpty()) {
                finalList.clear();
            }
        }

        setExportToCsvBtnStatus1(false);
    }

    public String setFeedbackComments(String feedbackValue) {
        String ret = "";
        try {
            Integer val = Integer.parseInt(feedbackValue);

            if (val == 1) {
                ret = "TERRIBLE";
            }
            if (val == 2) {
                ret = "BAD";
            }
            if (val == 3) {
                ret = "OKAY";
            }
            if (val == 4) {
                ret = "GOOD";
            }
            if (val == 5) {
                ret = "GREAT";
            }
        } catch (Exception e) {
            return "";
        }
        return ret;
    }

    /*getter setter*/

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getPresentDate() {
        presentDate = new Date();
        return presentDate;
    }

    public void setPresentDate(Date presentDate) {
        this.presentDate = presentDate;
    }

    public boolean isExportToCsvBtnStatus1() {
        return exportToCsvBtnStatus1;
    }

    public void setExportToCsvBtnStatus1(boolean exportToCsvBtnStatus1) {
        this.exportToCsvBtnStatus1 = exportToCsvBtnStatus1;
    }

    public List<FeedbackReportDtlDto> getFinalList() {
        return finalList;
    }

    public void setFinalList(List<FeedbackReportDtlDto> finalList) {
        this.finalList = finalList;
    }
}
