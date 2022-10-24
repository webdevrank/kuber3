package com.rank.ccms.web;

import com.rank.ccms.entities.CallDtl;
import com.rank.ccms.entities.CallMst;
import com.rank.ccms.entities.CategoryMst;
import com.rank.ccms.entities.CustomerMst;
import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.entities.ServiceMst;
import com.rank.ccms.entities.LanguageMst;
import com.rank.ccms.dto.ConnectedCallsReportDto;
import com.rank.ccms.entities.CallFileUploadDtls;
import com.rank.ccms.entities.CallRecords;
import com.rank.ccms.service.CallDtlService;
import com.rank.ccms.service.CallFileUploadDtlsService;
import com.rank.ccms.service.CallMstService;
import com.rank.ccms.service.CallRecordsService;
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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class ConnectedCallsReportComponent implements Serializable {

    private Date startTime;
    private Date endTime;
    private Date presentDate;
    private String mobileNo;
    private String accountNo;
    private String custid;
    private String chatHtml = "";

    private List<ConnectedCallsReportDto> listConnectedCallsReport = new ArrayList<>();

    private boolean exportToCsvBtnStatus;
    private String outputTextToExport = "";

    @Autowired
    private CallMstService callMstService;

    @Autowired
    private CustomerMstService customerMstService;

    @Autowired
    private ServiceMstService serviceMstService;

    @Autowired
    private CallDtlService callDtlService;

    @Autowired
    private EmployeeMstService employeeMstService;

    @Autowired
    private CategoryMstService categoryMstService;

    @Autowired
    private LanguageMstService skillMstService;

    @Autowired
    private CallFileUploadDtlsService fileUploadDtlsService;

    @Autowired
    CallRecordsService callRecordsService;

    public void newConnectedCallsReport() {

        this.startTime = null;
        this.endTime = null;
        this.accountNo = null;
        this.mobileNo = null;
        this.custid = null;
        if (listConnectedCallsReport != null) {
            if (!listConnectedCallsReport.isEmpty()) {
                listConnectedCallsReport.clear();
            }
        }
        setExportToCsvBtnStatus(false);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        Date d = cal.getTime();
        setPresentDate(d);
    }

    public void clear() {

        this.startTime = null;
        this.endTime = null;
        this.accountNo = null;
        this.mobileNo = null;
        this.custid = null;
        if (listConnectedCallsReport != null) {
            if (!listConnectedCallsReport.isEmpty()) {
                listConnectedCallsReport.clear();
            }
        }

        setExportToCsvBtnStatus(false);
    }

    public List<ConnectedCallsReportDto> loadConnectedCallsReportBySearch(Date starttimearg, Date endtimearg, String cust, String mobile, String account) {
        Boolean searchflg = true;

        String startTimeOutput = "";
        String endTimeOutput = "";
        String custIdOutput = "";
        String mobileOutput = "";
        String accountOutput = "";

        ThreadSafeSimpleDateFormat dateFormat = new ThreadSafeSimpleDateFormat("dd.MM.yyyy HH:mm");
        if (starttimearg != null) {

            startTimeOutput = "Start Date and Time : " + dateFormat.format(startTime);

        }
        if (endtimearg != null) {

            endTimeOutput = "End Date and Time : " + dateFormat.format(endTime);
        }
        if (!(cust == null) && !(cust.trim().equals(""))) {

            custIdOutput = "Customer ID : " + custid;
        }
        if (!(mobile == null) && !(mobile.trim().equals(""))) {

            mobileOutput = "Mobile No : " + mobileNo;
        }
        if (!(account == null) && !(account.trim().equals(""))) {

            accountOutput = "Account No : " + accountNo;
        }

        if ((mobile == null || mobile.trim().equals("")) && (cust == null || cust.trim().equals("")) && (account == null || account.trim().equals("")) && starttimearg == null && endtimearg == null) {
            this.startTime = null;
            this.endTime = null;
            this.accountNo = null;
            this.mobileNo = null;
            this.custid = null;
            searchflg = false;
            if (listConnectedCallsReport != null) {
                if (!listConnectedCallsReport.isEmpty()) {
                    listConnectedCallsReport.clear();
                }
            }
            setExportToCsvBtnStatus(false);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "No search input !", "Search Unsuccessful!!"));

        } else if (starttimearg != null || endtimearg != null) {
            this.startTime = null;
            this.endTime = null;
            this.accountNo = null;
            this.mobileNo = null;
            this.custid = null;

            if (listConnectedCallsReport != null) {
                if (!listConnectedCallsReport.isEmpty()) {
                    listConnectedCallsReport.clear();
                }
            }
            setExportToCsvBtnStatus(false);
            if (starttimearg == null && endtimearg != null || starttimearg != null && endtimearg == null || starttimearg == null && endtimearg == null) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please select Start Date and End Date,both!", "Search Unsuccessfull!!"));
                searchflg = false;
            } else if (starttimearg != null && endtimearg == null) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please select End Date!", "Search Unsuccessfull!!"));
                searchflg = false;
            } else if (starttimearg == null && endtimearg != null) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please select Start Date!", "Search Unsuccessfull!!"));
                searchflg = false;
            }

        }

        this.exportToCsvBtnStatus = false;
        if (searchflg) {
            try {
                List<ConnectedCallsReportDto> localListConnectedCallsReport = new ArrayList<>();

                List<CallMst> localCallMstList = new ArrayList<>();
                if (starttimearg != null && endtimearg != null) {
                    if (starttimearg.after(endtimearg)) {
                        FacesContext.getCurrentInstance().addMessage(null,
                                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Start Date Time is greater than End Date Time!", "Search Unsuccessfull!!"));
                    } else {

                        Timestamp starttime = null;
                        Timestamp endtime = null;
                        try {
                            endtime = CustomConvert.javaDateToTimeStamp(endtimearg);
                            starttime = CustomConvert.javaDateToTimeStamp(starttimearg);
                        } catch (ParseException ex) {
                            Logger.getLogger(ConnectedCallsReportComponent.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        if (cust == null || cust.trim().equals("")) {
                            localCallMstList = callMstService.findCallMstByExceptThisCallStatus("No Agent Found", starttime, endtime);

                        } else {
                            localCallMstList = callMstService.findNonDropedCallByDateCustid(starttime, endtime, cust);
                        }
                    }
                } else if (cust == null || cust.trim().equals("")) {
                    localCallMstList = callMstService.findCallMstByExceptThisCallStatus("No Agent Found", null, null);

                } else {
                    localCallMstList = callMstService.findNonDropedCallByDateCustid(null, null, cust);
                }
                Long increment = (long) 0;
                for (CallMst cMst : localCallMstList) {
                    CustomerMst custMst;
                    if (mobile == null && account == null && cust == null) {
                        custMst = customerMstService.findCustomerMstByCustomerId(cMst.getCustId());
                    } else if (mobile != null && account != null) {
                        custMst = customerMstService.findCustomerMstByCustIdMobileNumAccount(cMst.getCustId(), mobile.trim(), account.trim());
                    } else {
                        custMst = customerMstService.findCustomerMstByCustIdMobileNumAccount(cMst.getCustId(), mobile, account);
                    }

                    if (custMst != null) {

                        ConnectedCallsReportDto localConnectedCallsReport = new ConnectedCallsReportDto();
                        CallRecords cRecords = callRecordsService.findCallRecordsByCallIdOnly(cMst.getId());
                        if (null != cRecords && cRecords.getChatText() != null) {
                            if (cRecords.getChatText().contains("chatMessage")) {
                                localConnectedCallsReport.setChatText(cRecords.getChatText().trim());

                            } else {
                                localConnectedCallsReport.setChatText("");
                            }
                        } else {
                            localConnectedCallsReport.setChatText("");
                        }
                        if (cMst.getServiceId() != null) {
                            if (cMst.getServiceId() != 0) {
                                ServiceMst localServiceMst = serviceMstService.findAllServiceMstById(cMst.getServiceId());
                                if (localServiceMst != null) {
                                    localConnectedCallsReport.setServiceType(localServiceMst.getServiceDesc());
                                    localConnectedCallsReport.setServiceCode(localServiceMst.getServiceCd());
                                    localConnectedCallsReport.setServiceName(localServiceMst.getServiceName());
                                }
                            }
                        }
                        if (cMst.getCategoryId() != null) {
                            if (cMst.getCategoryId() != 0) {
                                CategoryMst localCategoryMst = categoryMstService.findCategoryMstById(cMst.getCategoryId());
                                if (localCategoryMst != null) {
                                    localConnectedCallsReport.setSegmentCode(localCategoryMst.getCatgCd());
                                    localConnectedCallsReport.setSegmentDescription(localCategoryMst.getCatgDesc());
                                }
                            }
                        }
                        if (cMst.getLanguageId() != null) {
                            if (cMst.getLanguageId() != 0) {
                                LanguageMst localcLanguageMst = skillMstService.findAllLanguageMstById(cMst.getLanguageId());
                                if (localcLanguageMst != null) {
                                    localConnectedCallsReport.setLangauage(localcLanguageMst.getLanguageDesc());
                                    localConnectedCallsReport.setLanguageCd(localcLanguageMst.getLanguageCd());
                                }
                            }
                        }
                        List<CallDtl> listCallDtl = callDtlService.findCallDtlByCallMasterId(cMst.getId());
                        CallDtl cDtl;
                        if (!listCallDtl.isEmpty()) {
                            cDtl = listCallDtl.get(0);
                            if (cDtl != null) {
                                EmployeeMst employeeMst = employeeMstService.findEmployeeMstById(cDtl.getHandeledById().getId());
                                localConnectedCallsReport.setAgentECN(employeeMst.getLoginId());
                                localConnectedCallsReport.setAgentName(employeeMst.getFirstName() + " " + employeeMst.getLastName());
                            }
                        }

                        localConnectedCallsReport.setCallId(cMst.getId());
                        localConnectedCallsReport.setDeviceBrand(cMst.getDeviceBrand());
                        localConnectedCallsReport.setDeviceIp(cMst.getDeviceIp());
                        localConnectedCallsReport.setDeviceName(cMst.getDeviceName());

                        localConnectedCallsReport.setDeviceOs(cMst.getDeviceOs());
                        localConnectedCallsReport.setStaticIp(cMst.getStaticIp());
                        localConnectedCallsReport.setCallType(cMst.getCallType());
                        localConnectedCallsReport.setMedium(cMst.getCallMedium());
                        List<CallFileUploadDtls> agentFileMstList = fileUploadDtlsService.findAllFileByCallMst(cMst.getId());
                        if (agentFileMstList.isEmpty()) {
                            localConnectedCallsReport.setFileDetails("");
                        } else {
                            localConnectedCallsReport.setFileDetails("View File");
                        }

                        if (cMst.getAgentPickupTime() != null && cMst.getCustomerRequestTime() != null) {
                            long diff1;
                            if (cMst.getAgentPickupTime().after(cMst.getCustomerRequestTime())) {
                                diff1 = cMst.getAgentPickupTime().getTime() - cMst.getCustomerRequestTime().getTime();
                            } else {
                                diff1 = cMst.getCustomerRequestTime().getTime() - cMst.getAgentPickupTime().getTime();
                            }

                            long hours, minutes, seconds;
                            long timeInSeconds = diff1 / 1000;
                            hours = timeInSeconds / 3600;
                            timeInSeconds = timeInSeconds - (hours * 3600);
                            minutes = timeInSeconds / 60;
                            timeInSeconds = timeInSeconds - (minutes * 60);
                            seconds = timeInSeconds;
                            String callDate = String.valueOf(hours) + ":" + String.valueOf(minutes) + ":" + String.valueOf(seconds);
                            localConnectedCallsReport.setPickupTime(callDate);
                        }

                        if (cMst.getEndTime() != null && cMst.getStartTime() != null) {
                            long diff1 = cMst.getEndTime().getTime() - cMst.getStartTime().getTime();

                            long hours, minutes, seconds;
                            long timeInSeconds = diff1 / 1000;
                            hours = timeInSeconds / 3600;
                            timeInSeconds = timeInSeconds - (hours * 3600);
                            minutes = timeInSeconds / 60;
                            timeInSeconds = timeInSeconds - (minutes * 60);
                            seconds = timeInSeconds;
                            String callDate = String.valueOf(hours) + ":" + String.valueOf(minutes) + ":" + String.valueOf(seconds);
                            ThreadSafeSimpleDateFormat sdf = new ThreadSafeSimpleDateFormat("dd-MMM-yyyy HH:mm");
                            localConnectedCallsReport.setCallStartTime(sdf.format(cMst.getStartTime()));
                            localConnectedCallsReport.setCallEndTime(sdf.format(cMst.getEndTime()));
                            localConnectedCallsReport.setCallDuration(callDate);
                        }
                        localConnectedCallsReport.setCustomerEmail(custMst.getEmail());
                        localConnectedCallsReport.setCustomerMobile(custMst.getCellPhone().toString());
                        localConnectedCallsReport.setCustomerAccountNo(custMst.getAccountNo());
                        localConnectedCallsReport.setCustomerId(custMst.getCustId());
                        localConnectedCallsReport.setCustomerName(custMst.getFirstName() + " " + custMst.getLastName());

                        increment += 1;
                        localConnectedCallsReport.setSlNo(increment);
                        localListConnectedCallsReport.add(localConnectedCallsReport);

                    }
                }
                setListConnectedCallsReport(localListConnectedCallsReport);

                if (!listConnectedCallsReport.isEmpty()) {
                    setExportToCsvBtnStatus(true);
                    outputTextToExport = startTimeOutput + "   " + endTimeOutput + "  " + custIdOutput + "  " + mobileOutput + "  " + accountOutput;
                }

            } catch (Exception ex) {
                listConnectedCallsReport.clear();
                Logger.getLogger(ConnectedCallsReportComponent.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                RequestContext.getCurrentInstance().execute("setLoadRender2();");
            }
        }

        return listConnectedCallsReport;

    }

    public String goToChatView(String chattext) {
        chattext = chattext.replaceAll("&quot;", "'");
        this.setChatHtml(chattext);

        return "/pages/reports/viewChatHistory.xhtml?faces-redirect=true";
    }

    public String back() {

        return "/pages/reports/connectedCallsReport.xhtml?faces-redirect=true";
    }

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
        return presentDate;
    }

    public void setPresentDate(Date presentDate) {
        this.presentDate = presentDate;
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

    public String getCustid() {
        return custid;
    }

    public void setCustid(String custid) {
        this.custid = custid;
    }

    public List<ConnectedCallsReportDto> getListConnectedCallsReport() {
        return listConnectedCallsReport;
    }

    public void setListConnectedCallsReport(List<ConnectedCallsReportDto> listConnectedCallsReport) {
        this.listConnectedCallsReport = listConnectedCallsReport;
    }

    public boolean getExportToCsvBtnStatus() {
        return exportToCsvBtnStatus;
    }

    public void setExportToCsvBtnStatus(boolean exportToCsvBtnStatus) {
        this.exportToCsvBtnStatus = exportToCsvBtnStatus;
    }

    public String getOutputTextToExport() {
        return outputTextToExport;
    }

    public void setOutputTextToExport(String outputTextToExport) {
        this.outputTextToExport = outputTextToExport;
    }

    public String getChatHtml() {
        return chatHtml;
    }

    public void setChatHtml(String chatHtml) {
        this.chatHtml = chatHtml;
    }

}
