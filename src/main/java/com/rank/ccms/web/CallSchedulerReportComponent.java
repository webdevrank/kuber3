package com.rank.ccms.web;

import com.rank.ccms.entities.CallDtl;
import com.rank.ccms.entities.CallMst;
import com.rank.ccms.entities.CategoryMst;
import com.rank.ccms.entities.CustomerMst;
import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.entities.ScheduleCall;
import com.rank.ccms.entities.ServiceMst;
import com.rank.ccms.entities.LanguageMst;
import com.rank.ccms.dto.CallSchedulerReportDto;
import com.rank.ccms.service.CallDtlService;
import com.rank.ccms.service.CallMstService;
import com.rank.ccms.service.CallSchedulerService;
import com.rank.ccms.service.CategoryMstService;
import com.rank.ccms.service.CustomerMstService;
import com.rank.ccms.service.EmployeeMstService;
import com.rank.ccms.service.ServiceMstService;
import com.rank.ccms.service.LanguageMstService;
import com.rank.ccms.util.CustomConvert;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import com.rank.ccms.util.ThreadSafeSimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.joda.time.DateTime;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.Seconds;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class CallSchedulerReportComponent implements Serializable {

    private Date searchStartTime;
    private Date searchEndTime;
    private ScheduleCall scheduleCall = new ScheduleCall();
    private CallSchedulerReportDto callScReportDto = new CallSchedulerReportDto();

    private List<ScheduleCall> listScheduleCall = new ArrayList<>();
    private List<CallSchedulerReportDto> listScheduleCallReport = new ArrayList<>();
    private List<String> listRmEmail = new ArrayList<>();

    private String downloadedFileName;
    private String downloadCSS;
    private boolean exportToCsvBooleanBtnStatus;
    private Date presentDate;
    private String custid;
    private String mobileNo;
    private String accountNo;
    private String outputTextToExport;

    @Autowired
    private CallSchedulerService callSchedulerService;

    @Autowired
    private CustomerMstService customerMstService;

    @Autowired
    private EmployeeMstService employeeMstService;

    @Autowired
    private CallDtlService callDtlService;

    @Autowired
    private CallMstService callMstService;

    @Autowired
    private CategoryMstService categoryMstService;

    @Autowired
    private LanguageMstService languageMstService;

    @Autowired
    private ServiceMstService serviceMstService;

    public CallSchedulerReportComponent() {

    }

    public void clear() {

        this.searchStartTime = null;
        this.searchEndTime = null;
        this.accountNo = "";
        this.mobileNo = "";
        this.custid = "";
        this.downloadCSS = "display:none";
        this.downloadedFileName = "";
        if (listScheduleCallReport != null) {
            if (!listScheduleCallReport.isEmpty()) {
                listScheduleCallReport.clear();
            }
        }

        setExportToCsvBooleanBtnStatus(false);
    }

    public void loadScheduleCallReportListBySearch(Date starttime1, Date endtime1, String cust, String account, String mobile) {
        this.exportToCsvBooleanBtnStatus = false;
        Boolean searchflg = true;
        Timestamp starttime = null;
        Timestamp endtime = null;

        String startTimeOutput = "";
        String endTimeOutput = "";
        String custIdOutput = "";
        String mobileOutput = "";
        String accountOutput = "";
        String custNameOutput = "";
        String agentNameOutput = "";

        //render the header to the export links
        ThreadSafeSimpleDateFormat dateFormat = new ThreadSafeSimpleDateFormat("dd.MM.yyyy HH:mm");
        if (starttime1 != null) {

            startTimeOutput = "Start Date and Time : " + dateFormat.format(searchStartTime);

        }
        if (endtime1 != null) {

            endTimeOutput = "End Date and Time : " + dateFormat.format(searchEndTime);
        }
        if (!(cust == null) && !(cust.trim().equals(""))) {

            custIdOutput = "Customer ID : " + custid;
        }
        if (!(account == null) && !(account.trim().equals(""))) {

            accountOutput = "Account No : " + accountNo;
        }
        if (!(mobile == null) && !(mobile.trim().equals(""))) {

            mobileOutput = "Customer Mobile : " + mobileNo;
        }

        if ((mobile == null || mobile.trim().equals("")) && (cust == null || cust.trim().equals("")) && (account == null || account.trim().equals("")) && starttime1 == null && endtime1 == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "No search input !", "Search Unsuccessful!!"));
            this.searchStartTime = null;
            this.searchEndTime = null;
            this.accountNo = null;
            this.mobileNo = null;
            this.custid = null;
            searchflg = false;
            this.downloadCSS = "display:none";
            this.downloadedFileName = "";
            if (listScheduleCallReport != null) {
                if (!listScheduleCallReport.isEmpty()) {
                    listScheduleCallReport.clear();
                }
            }
            setExportToCsvBooleanBtnStatus(false);
        }

        if (searchflg) {
            List<CallSchedulerReportDto> localListScheduleCallReport = new ArrayList<>();
            listScheduleCall = new ArrayList<>();
            if (starttime1 != null && endtime1 != null) {
                if (starttime1.after(endtime1)) {
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Start Date Time is greater than End Date Time!", "Search Unsuccessfull!!"));
                } else {

                    try {

                        endtime = CustomConvert.javaDateToTimeStamp(endtime1);
                        starttime = CustomConvert.javaDateToTimeStamp(starttime1);
                    } catch (ParseException ex) {
                        Logger.getLogger(CallSchedulerReportComponent.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if ((cust != null && !cust.trim().equals("")) && (account != null && !account.trim().equals("")) && (mobile != null && !mobile.trim().equals(""))) {
                        CustomerMst cMst = customerMstService.findCustomerMstByCustomerId(cust);
                        List<ScheduleCall> sccallList = callSchedulerService.findAllTakenScheduleCallByGivenTimeRangeAndCustId(starttime, endtime, cMst);
                        for (ScheduleCall sccallList1 : sccallList) {
                            if (cMst.getAccountNo().equalsIgnoreCase(account) && cMst.getCellPhone() == Long.parseLong(mobile)) {
                                listScheduleCall.add(sccallList1);
                            }
                        }

                    }
                    if ((cust == null || cust.trim().equals("")) && (account != null && !account.trim().equals("")) && (mobile != null && !mobile.trim().equals(""))) {
                        CustomerMst cMst = customerMstService.findCustomerMstByMobileNumAccount(mobile, account);
                        List<ScheduleCall> sccallList = callSchedulerService.findAllTakenScheduleCallByGivenTimeRange(starttime, endtime);
                        for (ScheduleCall sccallList1 : sccallList) {
                            if (Objects.equals(sccallList1.getCustomerId().getId(), cMst.getId())) {
                                listScheduleCall.add(sccallList1);
                            }
                        }

                    }
                    if ((cust != null && !cust.trim().equals("")) && (account == null || account.trim().equals("")) && (mobile != null && !mobile.trim().equals(""))) {
                        CustomerMst cMst = customerMstService.findCustomerMstByCustomerId(cust);
                        List<ScheduleCall> sccallList = callSchedulerService.findAllTakenScheduleCallByGivenTimeRangeAndCustId(starttime, endtime, cMst);
                        for (ScheduleCall sccallList1 : sccallList) {

                            if (cMst.getCellPhone() == Long.parseLong(mobile)) {
                                listScheduleCall.add(sccallList1);
                            }
                        }
                    }
                    if ((cust != null && !cust.trim().equals("")) && (account != null && !account.trim().equals("")) && (mobile == null || mobile.trim().equals(""))) {
                        CustomerMst cMst = customerMstService.findCustomerMstByCustomerId(cust);
                        List<ScheduleCall> sccallList = callSchedulerService.findAllTakenScheduleCallByGivenTimeRangeAndCustId(starttime, endtime, cMst);
                        for (ScheduleCall sccallList1 : sccallList) {
                            if (cMst.getAccountNo().equalsIgnoreCase(account)) {
                                listScheduleCall.add(sccallList1);
                            }
                        }
                    }
                    if ((cust == null || cust.trim().equals("")) && (account == null || account.trim().equals("")) && (mobile != null && !mobile.trim().equals(""))) {
                        CustomerMst cMst = customerMstService.findCustomerMstByMobileNo(mobile);
                        List<ScheduleCall> sccallList = callSchedulerService.findAllTakenScheduleCallByGivenTimeRange(starttime, endtime);
                        for (ScheduleCall sccallList1 : sccallList) {
                            if (Objects.equals(sccallList1.getCustomerId().getId(), cMst.getId())) {
                                listScheduleCall.add(sccallList1);
                            }
                        }

                    }
                    if ((cust != null && !cust.trim().equals("")) && (account == null || account.trim().equals("")) && (mobile == null || mobile.trim().equals(""))) {
                        CustomerMst cMst = customerMstService.findCustomerMstByCustomerId(cust);
                        setListScheduleCall(callSchedulerService.findAllTakenScheduleCallByGivenTimeRangeAndCustId(starttime, endtime, cMst));

                    }
                    if ((cust == null || cust.trim().equals("")) && (account != null && !account.trim().equals("")) && (mobile == null || mobile.trim().equals(""))) {
                        CustomerMst cMst = customerMstService.findCustomerMstByMobileNumAccount(null, account);
                        List<ScheduleCall> sccallList = callSchedulerService.findAllTakenScheduleCallByGivenTimeRange(starttime, endtime);
                        for (ScheduleCall sccallList1 : sccallList) {
                            if (Objects.equals(sccallList1.getCustomerId().getId(), cMst.getId())) {
                                listScheduleCall.add(sccallList1);
                            }
                        }
                    }
                    if ((cust == null || cust.trim().equals("")) && (account == null || account.trim().equals("")) && (mobile == null || mobile.trim().equals(""))) {
                        setListScheduleCall(callSchedulerService.findAllTakenScheduleCallByGivenTimeRange(starttime, endtime));
                    }

                }
            } else if (starttime1 != null && endtime1 == null) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please select End Date!", "Search Unsuccessful!!"));
            } else if (starttime1 == null && endtime1 != null) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please select Start Date!", "Search Unsuccessful!!"));
            } else {
                if ((cust != null && !cust.trim().equals("")) && (account != null && !account.trim().equals("")) && (mobile != null && !mobile.trim().equals(""))) {
                    CustomerMst cMst = customerMstService.findCustomerMstByCustomerId(cust);
                    List<ScheduleCall> sccallList = callSchedulerService.findAllTakenScheduleCallByGivenTimeRangeAndCustId(null, null, cMst);
                    for (ScheduleCall sccallList1 : sccallList) {
                        if (cMst.getAccountNo().equalsIgnoreCase(account) && cMst.getCellPhone() == Long.parseLong(mobile)) {
                            listScheduleCall.add(sccallList1);
                        }
                    }
                }
                if ((cust == null || cust.trim().equals("")) && (account != null && !account.trim().equals("")) && (mobile != null && !mobile.trim().equals(""))) {
                    CustomerMst cMst = customerMstService.findCustomerMstByMobileNumAccount(mobile, account);
                    List<ScheduleCall> sccallList = callSchedulerService.findAllScheduleCall();
                    for (ScheduleCall sccallList1 : sccallList) {
                        if (Objects.equals(sccallList1.getCustomerId().getId(), cMst.getId())) {
                            listScheduleCall.add(sccallList1);
                        }
                    }

                }
                if ((cust != null && !cust.trim().equals("")) && (account == null || account.trim().equals("")) && (mobile != null && !mobile.trim().equals(""))) {
                    CustomerMst cMst = customerMstService.findCustomerMstByCustomerId(cust);
                    List<ScheduleCall> sccallList = callSchedulerService.findAllTakenScheduleCallByGivenTimeRangeAndCustId(null, null, cMst);
                    for (ScheduleCall sccallList1 : sccallList) {
                        if (cMst.getCellPhone() == Long.parseLong(mobile)) {
                            listScheduleCall.add(sccallList1);
                        }
                    }
                }
                if ((cust != null && !cust.trim().equals("")) && (account != null && !account.trim().equals("")) && (mobile == null || mobile.trim().equals(""))) {
                    CustomerMst cMst = customerMstService.findCustomerMstByCustomerId(cust);
                    List<ScheduleCall> sccallList = callSchedulerService.findAllTakenScheduleCallByGivenTimeRangeAndCustId(null, null, cMst);
                    for (ScheduleCall sccallList1 : sccallList) {
                        if (cMst.getAccountNo().equalsIgnoreCase(account)) {
                            listScheduleCall.add(sccallList1);
                        }
                    }

                }
                if ((cust == null || cust.trim().equals("")) && (account == null || account.trim().equals("")) && (mobile != null && !mobile.trim().equals(""))) {
                    CustomerMst cMst = customerMstService.findCustomerMstByMobileNo(mobile);
                    List<ScheduleCall> sccallList = callSchedulerService.findAllScheduleCall();
                    for (ScheduleCall sccallList1 : sccallList) {
                        if (Objects.equals(sccallList1.getCustomerId().getId(), cMst.getId())) {
                            listScheduleCall.add(sccallList1);
                        }
                    }

                }
                if ((cust != null && !cust.trim().equals("")) && (account == null || account.trim().equals("")) && (mobile == null || mobile.trim().equals(""))) {
                    CustomerMst cMst = customerMstService.findCustomerMstByCustomerId(cust);
                    setListScheduleCall(callSchedulerService.findAllTakenScheduleCallByGivenTimeRangeAndCustId(null, null, cMst));
                }
                if ((cust == null || cust.trim().equals("")) && (account != null && !account.trim().equals("")) && (mobile == null || mobile.trim().equals(""))) {
                    CustomerMst cMst = customerMstService.findCustomerMstByMobileNumAccount(null, account);
                    List<ScheduleCall> sccallList = callSchedulerService.findAllScheduleCall();
                    for (ScheduleCall sccallList1 : sccallList) {
                        if (Objects.equals(sccallList1.getCustomerId().getId(), cMst.getId())) {
                            listScheduleCall.add(sccallList1);
                        }
                    }
                }

            }

            if (!listScheduleCall.isEmpty()) {
                ThreadSafeSimpleDateFormat sdf = new ThreadSafeSimpleDateFormat("dd-MMM-yyyy HH:mm");
                for (ScheduleCall singleScheduledCall : listScheduleCall) {
                    CustomerMst localCustomerMst = customerMstService.findCustomerMstById(singleScheduledCall.getCustomerId().getId());

                    Boolean scheduleAcc = true;
                    if (account != null && !account.trim().equals("")) {
                        scheduleAcc = account.trim().equals(localCustomerMst.getAccountNo());
                    }
                    Boolean scheduleMob = true;
                    if (mobile == null || "".equals(mobile.trim())) {
                    } else {
                        scheduleMob = mobile.trim().equals(localCustomerMst.getCellPhone().toString());
                    }

                    if (scheduleAcc && scheduleMob) {

                        CallSchedulerReportDto localCallScReport = new CallSchedulerReportDto();
                        CustomerMst customerMst = customerMstService.findCustomerMstById(singleScheduledCall.getCustomerId().getId());
                        localCallScReport.setCustId(customerMst.getCustId());
                        localCallScReport.setCustName(customerMst.getFirstName());
                        localCallScReport.setCustomerAccNo(customerMst.getAccountNo());
                        localCallScReport.setCustomerMobile(customerMst.getCellPhone().toString());
                        localCallScReport.setCustomerEmail(customerMst.getEmail());

                        localCallScReport.setScheduleStatus(singleScheduledCall.getExecuteStatus());
                        if (singleScheduledCall.getSchedulerId() != 0) {
                            EmployeeMst employeeMst = employeeMstService.findEmployeeMstById(singleScheduledCall.getSchedulerId());
                            localCallScReport.setAgentId(employeeMst.getLoginId());
                            localCallScReport.setAgentName(employeeMst.getFirstName());
                        }

                        if (singleScheduledCall.getCallmstid() != null) {
                            CallDtl callDtl = callDtlService.findCallDtlByCallMstIdForSchduledCall(singleScheduledCall.getCallmstid());
                            if (callDtl != null) {
                                localCallScReport.setStartTime(sdf.format(callDtl.getStartTime()));
                                if (callDtl.getEndTime() != null) {
                                    localCallScReport.setEndTime(sdf.format(callDtl.getEndTime()));
                                }
                                Timestamp dateStart = callDtl.getStartTime();

                                Timestamp dateStop = callDtl.getEndTime();

                                ThreadSafeSimpleDateFormat format = new ThreadSafeSimpleDateFormat("yyyy-MM-dd kk:mm:ss:SS");

                                String callDate = "";

                                try {

                                    if (dateStart != null && dateStop != null) {

                                        Date d1 = new Timestamp(format.parse(format.format(dateStart)).getTime());

                                        Date d2 = new Timestamp(format.parse(format.format(dateStop)).getTime());

                                        //in milliseconds
                                        DateTime dt1 = new DateTime(d1);
                                        DateTime dt2 = new DateTime(d2);

                                        callDate = Hours.hoursBetween(dt1, dt2).getHours() + ":" + Minutes.minutesBetween(dt1, dt2).getMinutes() % 60 + ":" + Seconds.secondsBetween(dt1, dt2).getSeconds() % 60;
                                    }

                                    localCallScReport.setCallDuration(callDate);
                                } catch (ParseException e) {

                                }

                                CallMst callMst = callMstService.findCallMstById(singleScheduledCall.getCallmstid());
                                if (callMst != null) {
                                    localCallScReport.setStartTime(sdf.format(callMst.getStartTime()));

                                    if (singleScheduledCall.getExecuteStatus().equalsIgnoreCase("Refuse")) {
                                        localCallScReport.setCallStatus("Failure - Customer rejected call");
                                        localCallScReport.setEndTime("N/A");
                                    } else if (callMst.getAgentPickupTime() != null && callMst.getCustomerPickupTime() != null) {
                                        localCallScReport.setEndTime(sdf.format(callMst.getEndTime()));
                                        localCallScReport.setCallStatus("Successful");
                                    } else if (callMst.getAgentPickupTime() != null && callMst.getCustomerPickupTime() == null) {
                                        localCallScReport.setEndTime("N/A");
                                        localCallScReport.setCallDuration("N/A");
                                        localCallScReport.setCallStatus("Failure - Customer did not join");
                                    } else if (callMst.getAgentPickupTime() == null && callMst.getCustomerPickupTime() != null) {
                                        localCallScReport.setEndTime("N/A");
                                        localCallScReport.setCallDuration("N/A");
                                        localCallScReport.setCallStatus("Failure - Agent did not join");
                                    } else if (callMst.getAgentPickupTime() == null && callMst.getCustomerPickupTime() == null) {
                                        localCallScReport.setEndTime("N/A");
                                        localCallScReport.setCallDuration("N/A");
                                        localCallScReport.setCallStatus("Failure - Both did not join");
                                    }
                                    localCallScReport.setCallType(callMst.getCallType());
                                    localCallScReport.setMedium(callMst.getCallMedium());
                                }
                            }
                        } else {
                            localCallScReport.setStartTime("N/A");
                            localCallScReport.setEndTime("N/A");
                            localCallScReport.setCallDuration("N/A");
                            if (singleScheduledCall.getExecuteStatus().equalsIgnoreCase("Reject")) {
                                localCallScReport.setCallStatus("Rejected - Admin rejected the schedule");
                            }
                            localCallScReport.setCallType("");
                            localCallScReport.setMedium("N/A");

                        }

                        localCallScReport.setScheduledDate(sdf.format(singleScheduledCall.getScheduleDate()));

                        if (singleScheduledCall.getService() != null && singleScheduledCall.getService() != (long) 0) {

                            ServiceMst servicesMst = serviceMstService.findAllServiceMstById(singleScheduledCall.getService());

                            if (servicesMst != null) {
                                localCallScReport.setServiceCd(servicesMst.getServiceCd());
                                localCallScReport.setServiceDesc(servicesMst.getServiceDesc());

                            } else {
                                localCallScReport.setServiceCd("N/A");
                                localCallScReport.setServiceDesc("N/A");

                            }
                        } else {
                            localCallScReport.setServiceCd("N/A");
                            localCallScReport.setServiceDesc("N/A");

                        }
                        if (singleScheduledCall.getLanguage() != null && singleScheduledCall.getLanguage() != (long) 0) {
                            LanguageMst languageMst = languageMstService.findAllLanguageMstById(singleScheduledCall.getLanguage());
                            if (languageMst != null) {
                                localCallScReport.setLanguageCd(languageMst.getLanguageCd());
                                localCallScReport.setLanguageDesc(languageMst.getLanguageDesc());
                            }
                        } else if (customerMst.getCustLang1() != null && !customerMst.getCustLang1().equals("")) {
                            LanguageMst languageMst = languageMstService.findLanguageMstByLanguageName(customerMst.getCustLang1());
                            if (languageMst != null) {
                                localCallScReport.setLanguageCd(languageMst.getLanguageCd());
                                localCallScReport.setLanguageDesc(languageMst.getLanguageDesc());
                            }
                        } else {
                            localCallScReport.setLanguageCd("N/A");
                            localCallScReport.setLanguageDesc("N/A");
                        }
                        if (singleScheduledCall.getCategory() != null && singleScheduledCall.getCategory() != (long) 0) {
                            CategoryMst categoryMst = categoryMstService.findCategoryMstById(singleScheduledCall.getCategory());
                            if (categoryMst != null) {
                                localCallScReport.setSegmentCd(categoryMst.getCatgCd());
                                localCallScReport.setSegmentDesc(categoryMst.getCatgDesc());
                            }
                        } else if (customerMst.getCustSeg() != null && !customerMst.getCustSeg().equals("")) {
                            CategoryMst categoryMst = categoryMstService.findCategoryByCategoryName(customerMst.getCustSeg());
                            if (categoryMst != null) {
                                localCallScReport.setSegmentCd(categoryMst.getCatgCd());
                                localCallScReport.setSegmentDesc(categoryMst.getCatgDesc());
                            }
                        } else {
                            localCallScReport.setSegmentCd("N/A");
                            localCallScReport.setSegmentDesc("N/A");
                        }

                        localListScheduleCallReport.add(localCallScReport);
                    } else {
                        CallSchedulerReportDto localCallScReport = new CallSchedulerReportDto();
                        CustomerMst customerMst = customerMstService.findCustomerMstById(singleScheduledCall.getCustomerId().getId());
                        localCallScReport.setCustId(customerMst.getCustId());
                        localCallScReport.setCustName(customerMst.getFirstName());
                        localCallScReport.setCustomerAccNo(customerMst.getAccountNo());
                        localCallScReport.setCustomerMobile(customerMst.getCellPhone().toString());
                        localCallScReport.setCustomerEmail(customerMst.getEmail());

                        localCallScReport.setScheduleStatus(singleScheduledCall.getExecuteStatus());
                        if (singleScheduledCall.getSchedulerId() != 0) {
                            EmployeeMst employeeMst = employeeMstService.findEmployeeMstById(singleScheduledCall.getSchedulerId());
                            localCallScReport.setAgentId(employeeMst.getLoginId());
                            localCallScReport.setAgentName(employeeMst.getFirstName());
                        } else {
                            localCallScReport.setAgentId("N/A");
                            localCallScReport.setAgentName("N/A");
                        }
                        localCallScReport.setStartTime("N/A");
                        localCallScReport.setEndTime("N/A");
                        localCallScReport.setCallDuration("N/A");
                        localCallScReport.setScheduledDate(sdf.format(singleScheduledCall.getScheduleDate()));
                        if (singleScheduledCall.getService() != null && singleScheduledCall.getService() != (long) 0) {

                            ServiceMst servicesMst = serviceMstService.findAllServiceMstById(singleScheduledCall.getService());

                            if (servicesMst != null) {
                                localCallScReport.setServiceCd(servicesMst.getServiceCd());
                                localCallScReport.setServiceDesc(servicesMst.getServiceDesc());

                            } else {
                                localCallScReport.setServiceCd("N/A");
                                localCallScReport.setServiceDesc("N/A");

                            }
                        } else {
                            localCallScReport.setServiceCd("N/A");
                            localCallScReport.setServiceDesc("N/A");

                        }
                        if (singleScheduledCall.getLanguage() != null && singleScheduledCall.getLanguage() != (long) 0) {
                            LanguageMst languageMst = languageMstService.findAllLanguageMstById(singleScheduledCall.getLanguage());
                            if (languageMst != null) {
                                localCallScReport.setLanguageCd(languageMst.getLanguageCd());
                                localCallScReport.setLanguageDesc(languageMst.getLanguageDesc());
                            }
                        } else if (customerMst.getCustLang1() != null && !customerMst.getCustLang1().equals("")) {
                            LanguageMst languageMst = languageMstService.findLanguageMstByLanguageName(customerMst.getCustLang1());
                            if (languageMst != null) {
                                localCallScReport.setLanguageCd(languageMst.getLanguageCd());
                                localCallScReport.setLanguageDesc(languageMst.getLanguageDesc());
                            }
                        } else {
                            localCallScReport.setLanguageCd("N/A");
                            localCallScReport.setLanguageDesc("N/A");
                        }
                        if (singleScheduledCall.getCategory() != null && singleScheduledCall.getCategory() != (long) 0) {
                            CategoryMst categoryMst = categoryMstService.findCategoryMstById(singleScheduledCall.getCategory());
                            if (categoryMst != null) {
                                localCallScReport.setSegmentCd(categoryMst.getCatgCd());
                                localCallScReport.setSegmentDesc(categoryMst.getCatgDesc());
                            }
                        } else if (customerMst.getCustSeg() != null && !customerMst.getCustSeg().equals("")) {
                            CategoryMst categoryMst = categoryMstService.findCategoryByCategoryName(customerMst.getCustSeg());
                            if (categoryMst != null) {
                                localCallScReport.setSegmentCd(categoryMst.getCatgCd());
                                localCallScReport.setSegmentDesc(categoryMst.getCatgDesc());
                            }
                        } else {
                            localCallScReport.setSegmentCd("N/A");
                            localCallScReport.setSegmentDesc("N/A");
                        }

                        if (singleScheduledCall.getExecuteStatus().equalsIgnoreCase("Reject")) {
                            localCallScReport.setCallStatus("Rejected - Admin rejected the schedule");
                        }
                        localCallScReport.setCallType("");
                        localCallScReport.setMedium("N/A");

                        localListScheduleCallReport.add(localCallScReport);
                    }
                }

            }
            setListScheduleCallReport(localListScheduleCallReport);
            RequestContext.getCurrentInstance().execute("setLoadRender2();");
            if (!listScheduleCallReport.isEmpty()) {
                setExportToCsvBooleanBtnStatus(true);

                outputTextToExport = startTimeOutput + "   " + endTimeOutput + "  " + custIdOutput + "  " + mobileOutput + "  " + accountOutput + "  " + custNameOutput + "  " + agentNameOutput;
            }
        }
    }

    public void newScheduledCallreport() {

        this.searchStartTime = null;
        this.searchEndTime = null;
        this.accountNo = null;
        this.mobileNo = null;
        this.custid = null;
        this.downloadCSS = "display:none";
        this.downloadedFileName = "";
        if (!listScheduleCallReport.isEmpty()) {
            listScheduleCallReport.clear();
        }
        setExportToCsvBooleanBtnStatus(false);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        Date d = cal.getTime();
        setPresentDate(d);
        Set<String> localset = new HashSet();

        List<String> localList = new ArrayList<>(localset);
        setListRmEmail(localList);
    }

    public void loadScheduleCallReportList() {
        ThreadSafeSimpleDateFormat dateFormat = new ThreadSafeSimpleDateFormat("yyyy-MM-dd kk:mm:ss:SS");
        //get current date time with Date()
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, -24);

        Timestamp starttime = null;
        Timestamp endtime = null;
        try {
            endtime = CustomConvert.javaDateToTimeStamp(new Date());
            starttime = new Timestamp(dateFormat.parse(dateFormat.format(cal.getTimeInMillis())).getTime());
        } catch (ParseException ex) {
            Logger.getLogger(CallSchedulerReportComponent.class.getName()).log(Level.SEVERE, null, ex);
        }
        listScheduleCall = new ArrayList<>();
        List<CallSchedulerReportDto> localListScheduleCallReport = new ArrayList<>();
        setListScheduleCall(callSchedulerService.findAllTakenScheduleCallByGivenTimeRange(starttime, endtime));

        if (!listScheduleCall.isEmpty()) {
            for (ScheduleCall singleScheduledCall : listScheduleCall) {
                ThreadSafeSimpleDateFormat sdf = new ThreadSafeSimpleDateFormat("dd-MMM-yyyy HH:mm");
                CallSchedulerReportDto localCallScReport = new CallSchedulerReportDto();
                CustomerMst customerMst = customerMstService.findCustomerMstById(singleScheduledCall.getCustomerId().getId());
                localCallScReport.setCustId(customerMst.getCustId());
                localCallScReport.setCustName(customerMst.getFirstName());
                if (singleScheduledCall.getSchedulerId() != 0) {
                    EmployeeMst employeeMst = employeeMstService.findEmployeeMstById(singleScheduledCall.getSchedulerId());
                    localCallScReport.setAgentId(employeeMst.getLoginId());
                    localCallScReport.setAgentName(employeeMst.getFirstName());
                }
                if (singleScheduledCall.getCallmstid() != null) {

                    CallDtl callDtl = callDtlService.findCallDtlByCallMstIdForSchduledCall(singleScheduledCall.getCallmstid());
                    if (callDtl != null) {
                        localCallScReport.setStartTime(sdf.format(callDtl.getStartTime()));
                        if (callDtl.getEndTime() != null) {
                            localCallScReport.setEndTime(sdf.format(callDtl.getEndTime()));
                        }
                        Timestamp dateStart = callDtl.getStartTime();

                        Timestamp dateStop = callDtl.getEndTime();

                        ThreadSafeSimpleDateFormat format = new ThreadSafeSimpleDateFormat("yyyy-MM-dd kk:mm:ss:SS");

                        String callDate = "";

                        try {

                            if (dateStart != null && dateStop != null) {

                                Date d1 = new Timestamp(format.parse(format.format(dateStart)).getTime());

                                Date d2 = new Timestamp(format.parse(format.format(dateStop)).getTime());

                                //in milliseconds
                                DateTime dt1 = new DateTime(d1);
                                DateTime dt2 = new DateTime(d2);

                                callDate = Hours.hoursBetween(dt1, dt2).getHours() + ":" + Minutes.minutesBetween(dt1, dt2).getMinutes() % 60 + ":" + Seconds.secondsBetween(dt1, dt2).getSeconds() % 60;
                            }

                            localCallScReport.setCallDuration(callDate);
                        } catch (ParseException e) {

                        }

                    }
                    CallMst callMst = callMstService.findCallMstById(singleScheduledCall.getCallmstid());
                    localCallScReport.setStartTime(sdf.format(callMst.getStartTime()));

                    if (singleScheduledCall.getExecuteStatus().equalsIgnoreCase("Refuse")) {
                        localCallScReport.setCallStatus("Failure - Customer rejected call");
                    } else if (callMst.getAgentPickupTime() != null && callMst.getCustomerPickupTime() != null) {
                        localCallScReport.setEndTime(sdf.format(callMst.getEndTime()));
                        localCallScReport.setCallStatus("Successful");
                    } else if (callMst.getAgentPickupTime() != null && callMst.getCustomerPickupTime() == null) {
                        localCallScReport.setEndTime("N/A");
                        localCallScReport.setCallDuration("N/A");
                        localCallScReport.setCallStatus("Failure - Customer did not join");
                    } else if (callMst.getAgentPickupTime() == null && callMst.getCustomerPickupTime() != null) {
                        localCallScReport.setEndTime("N/A");
                        localCallScReport.setCallDuration("N/A");
                        localCallScReport.setCallStatus("Failure - Agent did not join");
                    } else if (callMst.getAgentPickupTime() == null && callMst.getCustomerPickupTime() == null) {
                        localCallScReport.setEndTime("N/A");
                        localCallScReport.setCallDuration("N/A");
                        localCallScReport.setCallStatus("Failure - Both did not join");

                    }

                } else {
                    localCallScReport.setStartTime("N/A");
                    localCallScReport.setEndTime("N/A");
                    localCallScReport.setCallDuration("N/A");
                    if (singleScheduledCall.getExecuteStatus().equalsIgnoreCase("Rejected")) {
                        localCallScReport.setCallStatus("Rejected - Admin rejected the schedule");
                    }
                    localCallScReport.setCallType("");
                    localCallScReport.setMedium("");
                }

                localCallScReport.setScheduledDate(sdf.format(singleScheduledCall.getScheduleDate()));

                if (singleScheduledCall.getService() != null && singleScheduledCall.getService() != (long) 0) {

                    ServiceMst servicesMst = serviceMstService.findAllServiceMstById(singleScheduledCall.getService());

                    if (servicesMst != null) {
                        localCallScReport.setServiceCd(servicesMst.getServiceCd());
                        localCallScReport.setServiceDesc(servicesMst.getServiceDesc());

                    } else {
                        localCallScReport.setServiceCd("N/A");
                        localCallScReport.setServiceDesc("N/A");

                    }
                } else {
                    localCallScReport.setServiceCd("N/A");
                    localCallScReport.setServiceDesc("N/A");

                }
                if (singleScheduledCall.getLanguage() != null && singleScheduledCall.getLanguage() != (long) 0) {
                    LanguageMst languageMst = languageMstService.findAllLanguageMstById(singleScheduledCall.getLanguage());
                    if (languageMst != null) {
                        localCallScReport.setLanguageCd(languageMst.getLanguageCd());
                        localCallScReport.setLanguageDesc(languageMst.getLanguageDesc());
                    }
                } else if (customerMst.getCustLang1() != null && !customerMst.getCustLang1().equals("")) {
                    LanguageMst languageMst = languageMstService.findLanguageMstByLanguageName(customerMst.getCustLang1());
                    if (languageMst != null) {
                        localCallScReport.setLanguageCd(languageMst.getLanguageCd());
                        localCallScReport.setLanguageDesc(languageMst.getLanguageDesc());
                    }
                } else {
                    localCallScReport.setLanguageCd("N/A");
                    localCallScReport.setLanguageDesc("N/A");
                }
                if (singleScheduledCall.getCategory() != null && singleScheduledCall.getCategory() != (long) 0) {
                    CategoryMst categoryMst = categoryMstService.findCategoryMstById(singleScheduledCall.getCategory());
                    if (categoryMst != null) {
                        localCallScReport.setSegmentCd(categoryMst.getCatgCd());
                        localCallScReport.setSegmentDesc(categoryMst.getCatgDesc());
                    }
                } else if (customerMst.getCustSeg() != null && !customerMst.getCustSeg().equals("")) {
                    CategoryMst categoryMst = categoryMstService.findCategoryByCategoryName(customerMst.getCustSeg());
                    if (categoryMst != null) {
                        localCallScReport.setSegmentCd(categoryMst.getCatgCd());
                        localCallScReport.setSegmentDesc(categoryMst.getCatgDesc());
                    }
                } else {
                    localCallScReport.setSegmentCd("N/A");
                    localCallScReport.setSegmentDesc("N/A");
                }

                localListScheduleCallReport.add(localCallScReport);
            }
        }
        setListScheduleCallReport(localListScheduleCallReport);

    }

    public List<String> completeText(String query) {
        List<String> results = new ArrayList<>();
        for (String val : listRmEmail) {
            if (val != null) {
                if (val.contains(query)) {
                    results.add(val);
                }
            }
        }
        return results;
    }

    public List<String> getListRmEmail() {
        return listRmEmail;
    }

    public void setListRmEmail(List<String> listRmEmail) {
        this.listRmEmail = listRmEmail;
    }

    public String getCustid() {
        return custid;
    }

    public void setCustid(String custid) {
        this.custid = custid;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getDownloadedFileName() {
        return downloadedFileName;
    }

    public void setDownloadedFileName(String downloadedFileName) {
        this.downloadedFileName = downloadedFileName;
    }

    public String getDownloadCSS() {
        return downloadCSS;
    }

    public void setDownloadCSS(String downloadCSS) {
        this.downloadCSS = downloadCSS;
    }

    public Date getSearchStartTime() {
        return searchStartTime;
    }

    public void setSearchStartTime(Date searchStartTime) {
        this.searchStartTime = searchStartTime;
    }

    public Date getSearchEndTime() {
        return searchEndTime;
    }

    public void setSearchEndTime(Date searchEndTime) {
        this.searchEndTime = searchEndTime;
    }

    public List<ScheduleCall> getListScheduleCall() {
        return listScheduleCall;
    }

    public void setListScheduleCall(List<ScheduleCall> listScheduleCall) {
        this.listScheduleCall = listScheduleCall;
    }

    public List<CallSchedulerReportDto> getListScheduleCallReport() {
        return listScheduleCallReport;
    }

    public void setListScheduleCallReport(List<CallSchedulerReportDto> listScheduleCallReport) {
        this.listScheduleCallReport = listScheduleCallReport;
    }

    public ScheduleCall getScheduleCall() {
        return scheduleCall;
    }

    public void setScheduleCall(ScheduleCall scheduleCall) {
        this.scheduleCall = scheduleCall;
    }

    public CallSchedulerReportDto getCallScReportDto() {
        return callScReportDto;
    }

    public void setCallScReportDto(CallSchedulerReportDto callScReportDto) {
        this.callScReportDto = callScReportDto;
    }

    public boolean isExportToCsvBooleanBtnStatus() {
        return exportToCsvBooleanBtnStatus;
    }

    public void setExportToCsvBooleanBtnStatus(boolean exportToCsvBooleanBtnStatus) {
        this.exportToCsvBooleanBtnStatus = exportToCsvBooleanBtnStatus;
    }

    public Date getPresentDate() {
        return presentDate;
    }

    public void setPresentDate(Date presentDate) {
        this.presentDate = presentDate;
    }

    public String getOutputTextToExport() {
        return outputTextToExport;
    }

    public void setOutputTextToExport(String outputTextToExport) {
        this.outputTextToExport = outputTextToExport;
    }

}
