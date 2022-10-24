package com.rank.ccms.web;

import com.rank.ccms.dto.CallAbandonedDto;
import com.rank.ccms.entities.CallDtl;
import com.rank.ccms.entities.CallMst;
import com.rank.ccms.entities.CategoryMst;
import com.rank.ccms.entities.CustomerMst;
import com.rank.ccms.entities.EmployeeActivityDtl;
import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.entities.LanguageMst;
import com.rank.ccms.entities.ServiceMst;
import com.rank.ccms.service.CallDtlService;
import com.rank.ccms.service.CallMstService;
import com.rank.ccms.service.CategoryMstService;
import com.rank.ccms.service.CustomerMstService;
import com.rank.ccms.service.EmployeeActivityDtlService;
import com.rank.ccms.service.EmployeeMstService;
import com.rank.ccms.service.LanguageMstService;
import com.rank.ccms.service.ServiceMstService;
import com.rank.ccms.util.CustomConvert;
import java.io.File;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.apache.log4j.Logger;
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
public class AbandonCallComponent implements Serializable {

    private File file;
    private CallMst callMst;

    private Date searchStartTime;
    private Date searchEndTime;

    private List<CallMst> ListAbandonCalls = new ArrayList<>();
    private List<CallAbandonedDto> listAllAbandonCalls = new ArrayList<>();

    private String Diff;
    private String downloadedFileName;
    private String downloadCSS;
    private Date presentDate;
    private boolean exportToCSVBtnStatus;
    private String mobileNo;
    private String accountNo;
    private String custid;
    private boolean renderStartTime;
    private boolean renderEndTime;
    private boolean renderCustId;
    private boolean renderMobile;
    private boolean renderAccount;
    private String outputTextToExport = "";

    Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private CallMstService callMstService;
    @Autowired
    private CallDtlService callDtlService;
    @Autowired
    private CustomerMstService customerMstService;
    @Autowired
    private CategoryMstService categoryMstService;
    @Autowired
    private ServiceMstService serviceMstService;
    @Autowired
    private LanguageMstService languageMstService;
    @Autowired
    private EmployeeMstService employeeMstService;
    @Autowired
    private EmployeeActivityDtlService employeeActivityDtlService;

    public void newAbandonCalls() throws ParseException {
        this.searchStartTime = null;
        this.searchEndTime = null;
        this.custid = "";
        this.mobileNo = "";
        this.accountNo = "";

        this.downloadCSS = "display:none";
        this.downloadedFileName = "";
        setListAbandonCalls(null);
        setExportToCSVBtnStatus(false);
        listAllAbandonCalls.clear();

        this.renderStartTime = false;
        this.renderEndTime = false;
        this.renderCustId = false;
        this.renderMobile = false;
        this.renderAccount = false;

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        Date d = cal.getTime();
        setPresentDate(d);

    }

    public void clear() {
        this.searchStartTime = null;
        this.searchEndTime = null;
        mobileNo = null;
        accountNo = null;
        custid = null;
        this.outputTextToExport = "";
        this.renderStartTime = false;
        this.renderEndTime = false;
        this.renderCustId = false;
        this.renderMobile = false;
        this.renderAccount = false;

        this.downloadCSS = "display:none";
        this.downloadedFileName = "";
        if (ListAbandonCalls != null) {
            if (!ListAbandonCalls.isEmpty()) {
                ListAbandonCalls.clear();
            }
        }
        if (listAllAbandonCalls != null) {
            if (!listAllAbandonCalls.isEmpty()) {
                listAllAbandonCalls.clear();
            }
        }
        setExportToCSVBtnStatus(false);
    }

    public void reset() {
        RequestContext.getCurrentInstance().reset("mainform:abandonlistpanel");
    }

    public void loadAbandonCalls(Date starttime, Date endtime, String custId, String mobile, String account) {

        this.downloadCSS = "display:none";
        this.downloadedFileName = "";
        String startTimeOutput = "";
        String endTimeOutput = "";
        String custIdOutput = "";
        String mobileOutput = "";
        String accountOutput = "";

        //render the header to the export links
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        if (starttime != null) {

            startTimeOutput = "Start Date and Time : " + dateFormat.format(searchStartTime);

        }
        if (endtime != null) {

            endTimeOutput = "End Date and Time : " + dateFormat.format(searchEndTime);
        }
        if (!(custId == null) && !(custId.trim().equals(""))) {

            custIdOutput = "Customer ID : " + custid;
        }
        if (!(mobile == null) && !(mobile.trim().equals(""))) {

            mobileOutput = "Mobile No : " + mobileNo;
        }
        if (!(account == null) && !(account.trim().equals(""))) {

            accountOutput = "Account No : " + accountNo;
        }

        if (listAllAbandonCalls != null) {
            if (!listAllAbandonCalls.isEmpty()) {
                listAllAbandonCalls.clear();
            }
        }
        exportToCSVBtnStatus = false;
        boolean doSearch = true;
        if ((mobile == null || mobile.trim().equals("")) && (custId == null || custId.trim().equals("")) && (account == null || account.trim().equals("")) && starttime == null && endtime == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "No search input !", "Search Unsuccessful!!"));

            doSearch = false;
        } else if (starttime != null || endtime != null) {
            if (starttime == null && endtime != null || starttime != null && endtime == null || starttime == null && endtime == null) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please select Start Date and End Date,both!", "Search Unsuccessfull!!"));
                this.searchStartTime = null;
                this.searchEndTime = null;
                this.downloadCSS = "display:none";
                this.downloadedFileName = "";
                if (ListAbandonCalls != null) {
                    if (!ListAbandonCalls.isEmpty()) {
                        ListAbandonCalls.clear();
                    }
                }
                doSearch = false;
                setExportToCSVBtnStatus(false);
            }

        }
        if (doSearch) {
            if ((starttime == null && endtime == null) || (starttime != null && endtime != null)) {

                try {
                    this.downloadCSS = "display:none";
                    this.downloadedFileName = "";
                    List<CallMst> listAbandonCall = new ArrayList<>();

                    if ((starttime != null && endtime != null) && (starttime.after(endtime))) {
                        FacesContext.getCurrentInstance().addMessage(null,
                                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Start Date is greater than End Date!", "Search Unsuccessfull!!"));
                    } else {
                        Timestamp dt_time1;
                        Timestamp dt_time2;
                        if (starttime == null) {
                            dt_time1 = null;
                        } else {
                            dt_time1 = CustomConvert.javaDateToTimeStamp(starttime);
                        }
                        Date endDate1;
                        if (endtime == null) {
                            dt_time2 = null;
                        } else {
                            Calendar cal = Calendar.getInstance();
                            cal.setTime(endtime);
                            cal.add(Calendar.DATE, 1);
                            endDate1 = cal.getTime();

                            dt_time2 = CustomConvert.javaDateToTimeStamp(endDate1);
                        }

                        List<CallMst> localListAbandonCalls = callMstService.findCallMstByCallStatus("ABANDONED", dt_time1, dt_time2, custId, mobile, account);

                        if (localListAbandonCalls == null || localListAbandonCalls.isEmpty()) {

                        } else {
                            for (CallMst clmst : localListAbandonCalls) {
                                CustomerMst custMst = customerMstService.findCustomerMstById(clmst.getCustomerId().getId());
                                clmst.setCustomerId(custMst);

                                listAbandonCall.add(clmst);
                            }
                        }

                        List<EmployeeActivityDtl> empActivityDtlList = employeeActivityDtlService.findbyActivityReasonCode("Hang Up", "ABN01", dt_time1, dt_time2);
                        if (empActivityDtlList.isEmpty()) {
                            //Nothing to add in the List
                        } else {
                            for (EmployeeActivityDtl empActivityDtl : empActivityDtlList) {
                                Long callMstId = empActivityDtl.getCallMstId();
                                if (callMstId == null || callMstId.equals((long) 0)) {
                                    logger.info("CallMstId: " + callMstId + " Not Found for the HangUp Activity Code: ABN01");
                                } else {
                                    CallMst calMst = callMstService.findNonDeletedById(callMstId, custId, mobile, account);
                                    if (calMst == null || calMst.getCustomerId() == null) {
                                        logger.info("CallMst Data Not Found in Master for Id:" + callMstId);
                                    } else {
                                        CustomerMst custMst = customerMstService.findCustomerMstById(calMst.getCustomerId().getId());
                                        calMst.setCustomerId(custMst);

                                        listAbandonCall.add(calMst);
                                    }
                                }
                            }
                        }

                        setListAbandonCalls(listAbandonCall);

                        if (!listAbandonCall.isEmpty()) {
                            for (CallMst listAbandonCall1 : listAbandonCall) {
                                CallAbandonedDto callMstDto = new CallAbandonedDto();
                                CallMst callmst = listAbandonCall1;
                                callMstDto.setStartTime(callmst.getStartTime());
                                callMstDto.setEndTime(callmst.getEndTime());
                                callMstDto.setAbandonTime(callmst.getStartTime());
                                CallDtl calDtl = findCallDtlfromCallMst(callmst);
                                if (calDtl == null) {
                                    callMstDto.setAgentName(" ");
                                    callMstDto.setAgentLoginCd(" ");
                                    callMstDto.setAbandonReason(" ");
                                } else {
                                    callMstDto.setAbandonReason(calDtl.getAgentComments());
                                    EmployeeMst emMst = calDtl.getHandeledById();
                                    if (emMst == null) {
                                        callMstDto.setAgentName(" ");
                                        callMstDto.setAgentLoginCd(" ");
                                    } else {
                                        callMstDto.setAgentName(emMst.getFirstName() + " " + emMst.getLastName());
                                        callMstDto.setAgentLoginCd(emMst.getLoginId());
                                    }
                                }
                                callMstDto.setAcctNo(callmst.getCustomerId().getAccountNo());
                                callMstDto.setCustomerId(callmst.getCustomerId().getCustId());
                                callMstDto.setCustomerName(callmst.getCustomerId().getFirstName());
                                callMstDto.setMobileNo(callmst.getCustomerId().getCellPhone().toString());
                                callMstDto.setCustomerEmail(callmst.getCustomerId().getEmail());
                                try {
                                    CategoryMst catgMst = categoryMstService.findCategoryMstById(callmst.getCategoryId());
                                    if (catgMst == null) {
                                        callMstDto.setCategoryCode(" ");
                                        callMstDto.setCategoryName(" ");
                                    } else {
                                        callMstDto.setCategoryCode(catgMst.getCatgCd());
                                        callMstDto.setCategoryName(catgMst.getCatgDesc());
                                    }
                                } catch (Exception ex) {
                                    callMstDto.setCategoryCode(" ");
                                    callMstDto.setCategoryName(" ");
                                }
                                try {
                                    LanguageMst language = languageMstService.findAllLanguageMstById(callMst.getLanguageId());
                                    if (language == null) {
                                        callMstDto.setSkillCode(" ");
                                        callMstDto.setSkillName(" ");
                                    } else {
                                        callMstDto.setSkillCode(language.getLanguageCd());
                                        callMstDto.setSkillName(language.getLanguageName());
                                    }
                                } catch (Exception ex) {
                                    callMstDto.setSkillCode(" ");
                                    callMstDto.setSkillName(" ");
                                }
                                try {
                                    ServiceMst servcMst = serviceMstService.findAllServiceMstById(callmst.getServiceId());
                                    if (null == servcMst) {
                                        callMstDto.setServiceCode(" ");
                                        callMstDto.setServiceName(" ");
                                        callMstDto.setService(" ");
                                    } else {
                                        callMstDto.setServiceCode(servcMst.getServiceCd());
                                        callMstDto.setServiceName(servcMst.getServiceDesc());
                                        callMstDto.setService(servcMst.getServiceDesc());
                                    }
                                } catch (Exception ex) {
                                    callMstDto.setServiceCode(" ");
                                    callMstDto.setServiceName(" ");
                                    callMstDto.setService(" ");
                                }
                                callMstDto.setCallType(callmst.getCallType());
                                callMstDto.setDeviceId(callmst.getDeviceId());
                                callMstDto.setDeviceName(callmst.getDeviceName());
                                callMstDto.setDeviceIP(callmst.getDeviceIp());
                                callMstDto.setDeviceOs(callmst.getDeviceOs());
                                callMstDto.setCallMedium(callmst.getCallMedium());
                                listAllAbandonCalls.add(callMstDto);
                            }
                            setExportToCSVBtnStatus(true);

                            outputTextToExport = startTimeOutput + "   " + endTimeOutput + "  " + custIdOutput + "  " + mobileOutput + "  " + accountOutput;
                        }
                    }
                    if (listAllAbandonCalls == null || listAllAbandonCalls.isEmpty()) {
                        FacesContext.getCurrentInstance().addMessage(null,
                                new FacesMessage(FacesMessage.SEVERITY_INFO, "No Abandoned Call Data Found.", "No Data Found!!"));
                    }
                } catch (ParseException e) {
                    listAllAbandonCalls.clear();

                } finally {
                    RequestContext.getCurrentInstance().execute("setLoadRender2();");
                }

            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please select Start Date and End Date,both!", "Search Unsuccessfull!!"));
            }
        }
    }

    public CallDtl findCallDtlfromCallMst(CallMst callmst) {
        //CallMst ClMST = callMstService.findCallMstById(callmst);
        List<CallDtl> ListCLDTL = callDtlService.findNonDeletedCallDtlByCallId(callmst);
        CallDtl CLDTL;
        EmployeeMst empMst;
        if (!ListCLDTL.isEmpty()) {
            CLDTL = ListCLDTL.get(0);
            empMst = employeeMstService.findEmployeeMstById(CLDTL.getHandeledById().getId());
            CLDTL.setHandeledById(empMst);
            return CLDTL;
        } else {
            return null;
        }
    }

    public String getDateDiff(Timestamp Date1, Timestamp Date2) {
        String callDate = "";
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss:SS");
            Date d1 = null;
            Date d2 = null;
            Timestamp dateStart;
            if (Date1 != null) {
                dateStart = Date1;
                d1 = new Timestamp(format.parse(format.format(dateStart)).getTime());
            }
            Timestamp dateStop;
            if (Date2 != null) {
                dateStop = Date2;
                d2 = new Timestamp(format.parse(format.format(dateStop)).getTime());
            }

            if (d1 != null && d2 != null) {
                //in milliseconds
                long diff = d2.getTime() - d1.getTime();
                long diffSeconds = diff / 1000 % 60;
                long diffMinutes = diff / (60 * 1000) % 60;
                long diffHours = diff / (60 * 60 * 1000) % 24;
                long diffDays = diff / (24 * 60 * 60 * 1000);

                String callDateDays = String.valueOf(diffDays);
                String callDateHours = String.valueOf(diffHours);
                String callDatemin = String.valueOf(diffMinutes);
                String callDatesec = String.valueOf(diffSeconds);

                callDate = callDateDays + ":" + callDateHours + ":" + callDatemin + ":" + callDatesec;
            }

        } catch (ParseException ex) {

            logger.error("Time Parsing Exception." + ex.getMessage(), ex);
        }
        return callDate;
    }

    public String getDateDiffInHours(Timestamp Date1, Timestamp Date2) {
        String callDate = "";

        if (Date1 != null && Date2 != null) {
            try {

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss:SS");

                Timestamp dateStart = Date1;
                Date d1 = new Timestamp(format.parse(format.format(dateStart)).getTime());

                Timestamp dateStop = Date2;
                Date d2 = new Timestamp(format.parse(format.format(dateStop)).getTime());

                DateTime dt1 = new DateTime(d1);
                DateTime dt2 = new DateTime(d2);

                callDate = Hours.hoursBetween(dt1, dt2).getHours() + ":" + Minutes.minutesBetween(dt1, dt2).getMinutes() % 60 + ":" + Seconds.secondsBetween(dt1, dt2).getSeconds() % 60;

            } catch (ParseException e) {

            }
        }
        return callDate;

    }

    public String getDiff() {
        return Diff;
    }

    public void setDiff(String Diff) {
        this.Diff = Diff;
    }

    public CallMst getCallMst() {
        return callMst;
    }

    public void setCallMst(CallMst callMst) {
        this.callMst = callMst;
    }

    public List<CallMst> getListAbandonCalls() {
        return ListAbandonCalls;
    }

    public void setListAbandonCalls(List<CallMst> ListAbandonCalls) {
        this.ListAbandonCalls = ListAbandonCalls;
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

    public CallMstService getCallMstService() {
        return callMstService;
    }

    public void setCallMstService(CallMstService callMstService) {
        this.callMstService = callMstService;
    }

    public CallDtlService getCallDtlService() {
        return callDtlService;
    }

    public void setCallDtlService(CallDtlService callDtlService) {
        this.callDtlService = callDtlService;
    }

    public ServiceMstService getServiceMstService() {
        return serviceMstService;
    }

    public void setServiceMstService(ServiceMstService serviceMstService) {
        this.serviceMstService = serviceMstService;
    }

    public CustomerMstService getCustomerMstService() {
        return customerMstService;
    }

    public void setCustomerMstService(CustomerMstService customerMstService) {
        this.customerMstService = customerMstService;
    }

    public EmployeeMstService getEmployeeMstService() {
        return employeeMstService;
    }

    public void setEmployeeMstService(EmployeeMstService employeeMstService) {
        this.employeeMstService = employeeMstService;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
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

    public Date getPresentDate() {
        return presentDate;
    }

    public void setPresentDate(Date presentDate) {
        this.presentDate = presentDate;
    }

    public boolean isExportToCSVBtnStatus() {
        return exportToCSVBtnStatus;
    }

    public void setExportToCSVBtnStatus(boolean exportToCSVBtnStatus) {
        this.exportToCSVBtnStatus = exportToCSVBtnStatus;
    }

    public List<CallAbandonedDto> getListAllAbandonCalls() {
        return listAllAbandonCalls;
    }

    public void setListAllAbandonCalls(List<CallAbandonedDto> listAllAbandonCalls) {
        this.listAllAbandonCalls = listAllAbandonCalls;
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

    public boolean isRenderStartTime() {
        return renderStartTime;
    }

    public void setRenderStartTime(boolean renderStartTime) {
        this.renderStartTime = renderStartTime;
    }

    public boolean isRenderEndTime() {
        return renderEndTime;
    }

    public void setRenderEndTime(boolean renderEndTime) {
        this.renderEndTime = renderEndTime;
    }

    public boolean isRenderCustId() {
        return renderCustId;
    }

    public void setRenderCustId(boolean renderCustId) {
        this.renderCustId = renderCustId;
    }

    public boolean isRenderMobile() {
        return renderMobile;
    }

    public void setRenderMobile(boolean renderMobile) {
        this.renderMobile = renderMobile;
    }

    public boolean isRenderAccount() {
        return renderAccount;
    }

    public void setRenderAccount(boolean renderAccount) {
        this.renderAccount = renderAccount;
    }

    public String getOutputTextToExport() {
        return outputTextToExport;
    }

    public void setOutputTextToExport(String outputTextToExport) {
        this.outputTextToExport = outputTextToExport;
    }

}
