package com.rank.ccms.web;

import com.rank.ccms.dto.FileReportDto;
import com.rank.ccms.entities.CallMst;
import com.rank.ccms.entities.CallRecords;
import com.rank.ccms.entities.CategoryMst;
import com.rank.ccms.entities.CustomerMst;
import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.entities.EmployeeTypeMst;
import com.rank.ccms.entities.ServiceMst;
import com.rank.ccms.entities.LanguageMst;
import com.rank.ccms.dto.RecordingData;
import com.rank.ccms.entities.CallDtl;
import com.rank.ccms.entities.CallFileUploadDtls;
import com.rank.ccms.entities.TenancyEmployeeMap;
import com.rank.ccms.service.CallDtlService;
import com.rank.ccms.service.CallFileUploadDtlsService;
import com.rank.ccms.service.CallMstService;
import com.rank.ccms.service.CallRecordsService;
import com.rank.ccms.service.CategoryMstService;
import com.rank.ccms.service.CustomerMstService;
import com.rank.ccms.service.EmployeeMstService;
import com.rank.ccms.service.EmployeeTypeMstService;
import com.rank.ccms.service.ServiceMstService;
import com.rank.ccms.service.LanguageMstService;
import com.rank.ccms.service.TenancyEmployeeMapService;
import com.rank.ccms.util.Constants;
import java.io.Serializable;
import java.sql.Timestamp;
import com.rank.ccms.util.ThreadSafeSimpleDateFormat;
import com.rank.ccms.vidyo.util.VidyoAccessAdmin;
import com.rank.ccms.vidyo.util.VidyoAccessReplay;
import com.rank.ccms.vidyo.util.VidyoAccessUser;
import com.rank.ccms.ws.contentmanage.RecordsSearchResponse;
import com.rank.ccms.ws.user.ControlMeetingFault_Exception;
import com.rank.ccms.ws.user.GeneralFault_Exception;
import com.rank.ccms.ws.user.InvalidArgumentFault_Exception;
import com.rank.ccms.ws.user.NotLicensedFault_Exception;
import com.rank.ccms.ws.user.ResourceNotAvailableFault_Exception;
import com.rank.ccms.ws.user.SeatLicenseExpiredFault_Exception;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.MalformedObjectNameException;
import javax.servlet.http.HttpSession;
import org.joda.time.DateTime;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.Seconds;
import org.primefaces.context.RequestContext;

@Component
@Scope("session")
public class CallRecordsComponent implements Serializable {

    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CallRecordsComponent.class);
    private Long customerId;
    private Date recordStartDate;
    private Date recordEndDate;
    private Long service_id;
    private Long category_id;
    private Long skill_id;
    private Long employee_id;
    private ServiceMst servicesMst;
    private LanguageMst languageMst;
    private CategoryMst categoryMst;
    private CustomerMst customerMst;
    private EmployeeMst employeeMst;
    private List<ServiceMst> selectService = new ArrayList<>();
    private List<LanguageMst> selectLanguage = new ArrayList<>();
    private List<CategoryMst> selectCategory = new ArrayList<>();
    private List<CustomerMst> selectCustomer = new ArrayList<>();
    private List<EmployeeMst> selectEmployee = new ArrayList<>();
    private List<CallRecords> listRecord;
    private List<CallRecords> selectRecords;
    private List<RecordingData> listRecord1;
    private List<RecordingData> selectRecords1;
    private String recordingStatus = "";
    private boolean recordingState = false;
    private String customerId1;
    private Date presentDate;
    private String outputTextToExport = "";
    private String mobileNo;
    private String chatHtml;
    private String recorderId = "";
    private String videoLink = "";
    private Boolean exportPdfOrXlsForFileDetails;
    private int recordcnt = 0;
    private RecordingData recordingData;
    private CallRecords callrecords;
    private boolean exportToCsvBtnStatus;
    private Boolean callFromwhichComponent = false;
    private List<FileReportDto> fileReportDtoList;

    @Autowired
    private ServiceMstService serviceMstService;

    @Autowired
    private LanguageMstService languageMstService;

    @Autowired
    private CategoryMstService categoryMstService;

    @Autowired
    private CustomerMstService customerMstService;

    @Autowired
    private EmployeeMstService employeeMstService;

    @Autowired
    private CallMstService callMstService;

    @Autowired
    private CallRecordsService callRecordsService;

    @Autowired
    private EmployeeTypeMstService employeeTypeMstService;

    @Autowired
    private CallFileUploadDtlsService fileUploadDtlsService;

    @Autowired
    TenancyEmployeeMapService tenancyEmployeeMapService;

    @Autowired
    private CallDtlService callDtlService;

    public CallRecordsComponent() {
        callrecords = new CallRecords();
        this.listRecord = new ArrayList<>();
        this.selectRecords = new ArrayList<>();
        this.listRecord1 = new ArrayList<>();
    }

    public void newCallRecordsComponent() {

        setEmployee_id(null);
        setCustomerId1("");
        setRecordStartDate(null);
        setRecordEndDate(null);
        setService_id(null);
        setCategory_id(null);
        setSkill_id(null);
        setListRecord1(null);
        setMobileNo("");
        servicesMst = new ServiceMst();
        languageMst = new LanguageMst();
        categoryMst = new CategoryMst();
        customerMst = new CustomerMst();
        employeeMst = new EmployeeMst();

        selectService = serviceMstService.loadAllService();
        selectLanguage = languageMstService.findAllNonDeletedLanguageMsts();
        selectCategory = categoryMstService.findAllNonDeletedCategoryMsts();
        selectCustomer = customerMstService.findAllCustomerMasters();

        EmployeeTypeMst empTypeMstAgent = employeeTypeMstService.findEmployeeTypeByEmployeeTypeName("agent");

        if (null != empTypeMstAgent) {
            selectEmployee = employeeMstService.findEmployeeByEmpTypeId(empTypeMstAgent);
        }
        java.util.Date datepresent = new java.util.Date();
        java.sql.Timestamp timestamp1 = new Timestamp(datepresent.getTime());
        java.util.Date date3 = new java.sql.Date(timestamp1.getTime());
        setPresentDate(date3);
        exportToCsvBtnStatus = false;
    }

    public void clear() {

        setEmployee_id(null);
        setCustomerId1("");
        setRecordStartDate(null);
        setRecordEndDate(null);
        setService_id(null);
        setCategory_id(null);
        setSkill_id(null);
        setMobileNo("");
        if (listRecord1 != null) {
            if (!listRecord1.isEmpty()) {
                listRecord1.clear();
            }
        }

        servicesMst = new ServiceMst();
        languageMst = new LanguageMst();
        categoryMst = new CategoryMst();
        customerMst = new CustomerMst();
        employeeMst = new EmployeeMst();

        selectService = serviceMstService.findAllServiceMsts();
        selectLanguage = languageMstService.findAllNonDeletedLanguageMsts();
        selectCategory = categoryMstService.findAllCategoryMsts();
        selectCustomer = customerMstService.findAllCustomerMasters();
        selectEmployee = employeeMstService.findAllNonDeletedEmployeeMsts();
        exportToCsvBtnStatus = false;
    }

    public synchronized void getOngoingCallRecording(HttpServletRequest request) throws Exception {
        EmployeeMst empmst = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
        CallMst callMst = (CallMst) request.getSession().getAttribute("callMst");

        if (null != callMst) {
            callMst = callMstService.findCallMstById(callMst.getId());
            if (callMst.getCallOption().equalsIgnoreCase("chat")) {

            } else {
                int intRecorderID = ongoingCallRecording(request);
                if (intRecorderID != 0) {
                    this.recordingState = true;
                    this.recordingStatus = "Recording....";
                    RequestContext.getCurrentInstance().execute("document.getElementById('recording').style.display='block';");

                } else {
                    Boolean recFlag = false;
                    for (int i = 0; i < 10; i++) {

                        int res = ongoingCallRecording(request);

                        if (res != 0) {

                            recFlag = true;
                            this.recordingState = true;
                            this.recordingStatus = "Recording....";
                            RequestContext.getCurrentInstance().execute("document.getElementById('recording').style.display='block';");

                            break;
                        }
                    }

                    if (!recFlag) {
                        RequestContext.getCurrentInstance().execute("PF('retryRecordingDlg').show();");
                    }
                }
            }
            logger.info("Component : getOngoingCallRecording: Ended for agent id:" + empmst.getId() + "and call id:" + callMst.getId());
        }

    }

    public synchronized int ongoingCallRecording(HttpServletRequest request) throws Exception {

        EmployeeMst empmst = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
        CallMst callMst = (CallMst) request.getSession().getAttribute("callMst");
        logger.info("Component : ongoingCallRecording: Start for agent id:" + empmst.getId() + "and call id:" + callMst.getId());
        String strUserID = Constants.adminUserId;
        String strPassword = Constants.adminPwd;

        List<TenancyEmployeeMap> tenancyEmployeeMaplist = tenancyEmployeeMapService.findVidyoTenantUrlByEmpId(empmst.getId());
        List<Long> unsortList = new ArrayList<>();
        for (TenancyEmployeeMap tenancyEmployeeMaplist1 : tenancyEmployeeMaplist) {
            unsortList.add(tenancyEmployeeMaplist1.getId());
        }
        Collections.sort(unsortList);

        TenancyEmployeeMap tenancyEmployeeMap = tenancyEmployeeMapService.findById(unsortList.get(unsortList.size() - 1));

        String strEntityID = tenancyEmployeeMap.getEntityId();

        int intRecorderID;

        try {
            VidyoAccessAdmin vidyoAccessAdmin = new VidyoAccessAdmin(Constants.vidyoportalAdminServiceWSDL);
            intRecorderID = vidyoAccessAdmin.startRecording(Integer.parseInt(strEntityID), strUserID, strPassword, Constants.vidyoportalAdminServiceWSDL);

        } catch (ResourceNotAvailableFault_Exception | SeatLicenseExpiredFault_Exception | MalformedURLException e) {
            logger.info("Exception :startRecording" + e.getMessage());
            throw e;
        }

        return intRecorderID;
    }

    public void retryStartRecording(HttpServletRequest request) {

        try {
            int res = ongoingCallRecording(request);
            logger.info("retryStartRecording: response" + res);
            if (res != 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Success"));

                this.recordingState = true;
                this.recordingStatus = "Recording....";
                RequestContext.getCurrentInstance().execute("document.getElementById('recording').style.display='block';");

                RequestContext.getCurrentInstance().execute("PF('retryRecordingDlg').hide();");

            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: Try Again", "Error: Try Again"));
            }
        } catch (Exception e) {
            logger.info("Exception in retryStartRecording()--", e);
        }
    }

    public void getOnlyStopRecording() {
        recordcnt++;
        HttpSession session = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession(false);
        CallMst callMst = (CallMst) session.getAttribute("callMst");
        CallRecords callR = callRecordsService.findCallRecordsByCallId(callMst.getId(), null);
        try {
            this.recordingState = false;
            this.recordingStatus = "";

            logger.info("Component : getOnlyOngoingCallRecording: Start for call id:" + callMst.getId());

            CallDtl calldtl = callDtlService.findNonDeletedCallDtlByCallIdAndActivity(callMst, "Incoming Call");

            if (calldtl == null) {
                calldtl = callDtlService.findNonDeletedCallDtlByCallIdAndActivity(callMst, "Schedule Call");
            }

            EmployeeMst empmst = employeeMstService.findEmployeeMstById(calldtl.getHandeledById().getId());

            String strUserID = Constants.adminUserId;
            String strPassword = Constants.adminPwd;

            List<TenancyEmployeeMap> tenancyEmployeeMaplist = tenancyEmployeeMapService.findVidyoTenantUrlByEmpId(empmst.getId());
            List<Long> unsortList = new ArrayList<>();
            for (TenancyEmployeeMap tenancyEmployeeMaplist1 : tenancyEmployeeMaplist) {
                unsortList.add(tenancyEmployeeMaplist1.getId());
            }
            Collections.sort(unsortList);

            TenancyEmployeeMap tenancyEmployeeMap = tenancyEmployeeMapService.findById(unsortList.get(unsortList.size() - 1));
            String strRoomURL = tenancyEmployeeMap.getRoomLink();

            if (callR.getConferenceId() != null) {
                String entityId = callR.getConferenceId() + "";
                VidyoAccessUser vidyoAccessUser1 = new VidyoAccessUser(Constants.vidyoportalUserServiceWSDL);
                vidyoAccessUser1.stopRecording(strRoomURL, strUserID, strPassword, Constants.vidyoportalUserServiceWSDL, entityId);
            }

            recordcnt = 0;
        } catch (MalformedURLException | ControlMeetingFault_Exception | SeatLicenseExpiredFault_Exception ex) {
            if (recordcnt < 5) {
                try {
                    Thread.sleep(2000);

                } catch (InterruptedException ex1) {
                    Logger.getLogger(CallRecordsComponent.class.getName()).log(Level.SEVERE, null, ex1);
                }
                getOnlyStopRecording();

            }

            Logger.getLogger(CallRecordsComponent.class.getName()).log(Level.SEVERE, null, ex);
        } catch (com.rank.ccms.ws.user.GeneralFault_Exception | com.rank.ccms.ws.user.InvalidArgumentFault_Exception | com.rank.ccms.ws.user.NotLicensedFault_Exception ex) {
            Logger.getLogger(CallRecordsComponent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getStopRecording(Long callId) {

        CallMst callMst = callMstService.findCallMstById(callId);
        CallRecords callRecords = callRecordsService.findCallRecordsByCallId(callMst.getId(), null);
        this.recordingState = false;
        this.recordingStatus = "";

        CallDtl calldtl = callDtlService.findNonDeletedCallDtlByCallIdAndActivity(callMst, "Normal");

        if (calldtl == null) {
            calldtl = callDtlService.findNonDeletedCallDtlByCallIdAndActivity(callMst, "ScheduleCall");
        }

        EmployeeMst empmst = employeeMstService.findEmployeeMstById(calldtl.getHandeledById().getId());

        String strUserID = Constants.adminUserId;
        String strPassword = Constants.adminPwd;

        List<TenancyEmployeeMap> tenancyEmployeeMaplist = tenancyEmployeeMapService.findVidyoTenantUrlByEmpId(empmst.getId());
        List<Long> unsortList = new ArrayList<>();
        for (TenancyEmployeeMap tenancyEmployeeMaplist1 : tenancyEmployeeMaplist) {
            unsortList.add(tenancyEmployeeMaplist1.getId());
        }
        Collections.sort(unsortList);
        TenancyEmployeeMap tenancyEmployeeMap = tenancyEmployeeMapService.findById(unsortList.get(unsortList.size() - 1));
        String strRoomURL = tenancyEmployeeMap.getRoomLink();

        try {
            VidyoAccessReplay vidyoAccessReplay = new VidyoAccessReplay(Constants.vidyoportalReplayServiceWSDL);
            RecordsSearchResponse searchRecordDetail = vidyoAccessReplay.searchRecord(strUserID, strPassword, Constants.vidyoportalReplayServiceWSDL, tenancyEmployeeMap.getRoomName());
            String playBackLink;
            Integer recordID;
            String strRecordID = "";

            if (!searchRecordDetail.getRecords().isEmpty()) {
                playBackLink = searchRecordDetail.getRecords().get(0).getExternalPlaybackLink();
                recordID = searchRecordDetail.getRecords().get(0).getId();
                strRecordID = recordID.toString();

            } else {
                playBackLink = "Not Saved";
            }
            logger.info(" playBackLink " + playBackLink + " recordID " + strRecordID);

            if (null != playBackLink && !playBackLink.equals("")) {
                callRecords.setExternalPlaybackLink(playBackLink);
            } else {
                callRecords.setExternalPlaybackLink("Not Saved");
            }

            callRecords.setRecorderId(strRecordID);
            logger.info(" strRecordID " + strRecordID);
            if (!strRecordID.trim().equals("")) {
                logger.info("Call Records save from getStopRecording try block with recoder id for call id:" + callMst.getId());
                callrecords = callRecordsService.saveCallRecord(callRecords);
            }

        } catch (Exception e) {
            logger.error("getStopRecording: Unable to save recording roomlink");
        } finally {
            if (callRecords.getConferenceId() != null) {
                try {
                    VidyoAccessUser vidyoAccessUser1 = new VidyoAccessUser(Constants.vidyoportalUserServiceWSDL);
                    vidyoAccessUser1.stopRecording(strRoomURL, strUserID, strPassword, Constants.vidyoportalUserServiceWSDL, callRecords.getConferenceId());
                } catch (MalformedURLException | ControlMeetingFault_Exception | GeneralFault_Exception | InvalidArgumentFault_Exception | NotLicensedFault_Exception | SeatLicenseExpiredFault_Exception ex) {
                    Logger.getLogger(CallRecordsComponent.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void loadCallRecords(HttpServletRequest request) {

        String startTimeOutput = "";
        String endTimeOutput = "";
        String custIdOutput = "";
        String agentOutput = "";
        String serviceOutput = "";
        String segmentOutput = "";
        String skillOutput = "";

        try {

            List<RecordingData> rdList = new ArrayList();
            List<CallMst> cmList;

            Long employeeID = this.getEmployee_id();
            String customerID = this.getCustomerId1();
            String mobile = this.mobileNo;
            if (this.mobileNo == null) {
                mobile = "";
            }
            Long serviceID = this.getService_id();
            Long skillID = this.getSkill_id();
            Long categoryID = this.getCategory_id();

            ThreadSafeSimpleDateFormat dateFormat = new ThreadSafeSimpleDateFormat("dd-MM-yyyy");
            Timestamp startTime = null;
            Timestamp endTime = null;
            boolean datecheck = true;

            Date endDate1;

            if (this.recordStartDate != null) {
                startTime = new Timestamp(dateFormat.parse(dateFormat.format(this.recordStartDate)).getTime());
            }
            if (this.recordEndDate != null) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(this.recordEndDate);
                cal.add(Calendar.DATE, 1);
                endDate1 = cal.getTime();
                endTime = new Timestamp(dateFormat.parse(dateFormat.format(endDate1)).getTime());
            }
            if (this.recordStartDate == null && this.recordEndDate == null) {
                datecheck = true;
            }

            if ((employeeID == null || employeeID == 0) && (serviceID == null || serviceID == 0) && categoryID == null && mobile.trim().equals("") && startTime == null && endTime == null) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "No search input !", "Search Unsuccessful!!"));
                datecheck = false;

            } else {
                int counter = 0;
                if (startTime != null && endTime == null) {
                    counter++;
                }
                if (startTime == null && endTime != null) {
                    counter++;
                }
                if (counter > 0) {

                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Select start date time, end date time both!", "Search Unsuccessfull!!"));
                    datecheck = false;

                }
                if (this.recordStartDate != null && this.recordEndDate != null) {
                    if (this.recordStartDate.compareTo(this.recordEndDate) > 0) {
                        datecheck = false;
                        FacesContext.getCurrentInstance().addMessage(null,
                                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Start Date Time is greater than End Date Time!", "Search Unsuccessful!!"));
                    } else {
                        datecheck = true;
                    }
                }
            }
            if (datecheck) {
                recordingData = new RecordingData();
                recordingData.setEmployeeID(employeeID);
                recordingData.setCustomerID(customerID);
                recordingData.setServiceID(serviceID);
                recordingData.setSkillID(skillID);
                recordingData.setCategoryID(categoryID);

                if (datecheck) {
                    recordingData.setRecordStartTime(startTime);
                    recordingData.setRecordEndTime(endTime);
                } else {
                    recordingData.setRecordStartTime(null);
                    recordingData.setRecordEndTime(null);
                }

                cmList = callRecordsService.findCallMst(recordingData);

                if (!cmList.isEmpty()) {
                    for (CallMst cmList1 : cmList) {
                        CallRecords cRecords = callRecordsService.findCallRecordsByCallId(cmList1.getId(), employeeID);
                        ThreadSafeSimpleDateFormat sdf = new ThreadSafeSimpleDateFormat("dd-MMM-yyyy HH:mm");
                        if (cRecords != null) {
                            recordingData = new RecordingData();
                            recordingData.setCallMstId(cmList1.getId());
                            recordingData.setCallType(cmList1.getCallType());
                            recordingData.setDeviceId(cmList1.getDeviceId());
                            recordingData.setDeviceIp(cmList1.getDeviceIp());
                            recordingData.setDeviceName(cmList1.getDeviceName());
                            recordingData.setDeviceOs(cmList1.getDeviceOs());
                            if (cmList1.getEndTime() == null) {
                                recordingData.setEndTime("");
                            } else {
                                recordingData.setEndTime(sdf.format(cmList1.getEndTime()));
                            }
                            if (cmList1.getStartTime() == null) {
                                recordingData.setStartTime("");
                            } else {
                                recordingData.setStartTime(sdf.format(cmList1.getStartTime()));
                            }
                            recordingData.setMedium(cmList1.getCallMedium());
                            if (cRecords.getChatText() != null) {
                                if (cRecords.getChatText().contains("chatMessage")) {
                                    recordingData.setChatText(cRecords.getChatText().trim());
                                } else {
                                    recordingData.setChatText("");
                                }
                            } else {
                                recordingData.setChatText("");
                            }

                            List<CallFileUploadDtls> agentFileMstList = fileUploadDtlsService.findAllFileByCallMst(cmList1.getId());
                            if (agentFileMstList.isEmpty()) {
                                recordingData.setFileDetails("");
                            } else {
                                recordingData.setFileDetails("View File");
                            }

                            Timestamp dateStart = cmList1.getStartTime();

                            Timestamp dateStop = cmList1.getEndTime();

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

                                recordingData.setCallDuration(callDate);
                            } catch (ParseException e) {

                            }

                            Long empID = cRecords.getEmpId().getId();
                            String custID = cRecords.getCustomerId();
                            Long callID = cRecords.getCallId().getId();

                            if (empID != 0) {
                                EmployeeMst employeeMst1 = employeeMstService.findEmployeeMstById(empID);
                                String empFName = employeeMst1.getFirstName();
                                String empLName = employeeMst1.getLastName();
                                String empName = empFName + " " + empLName;
                                recordingData.setEmployeeName(empName);
                                recordingData.setAgentECN(employeeMst1.getLoginId());

                            } else {
                                recordingData.setEmployeeName(" ");
                                recordingData.setAgentECN(" ");
                            }
                            if (custID != null || !"".equals(custID)) {
                                CustomerMst customerMst1 = customerMstService.findCustomerMstByCustomerId(custID);
                                String custFName = "";
                                String custLName = "";
                                if (customerMst1 != null) {
                                    if (customerMst1.getFirstName() != null) {
                                        custFName = customerMst1.getFirstName();
                                    }

                                    if (customerMst1.getLastName() != null) {
                                        custLName = customerMst1.getLastName();
                                    }
                                    String custName = custFName + " " + custLName;
                                    recordingData.setCustomerName(custName);
                                    recordingData.setAccountNo(customerMst1.getAccountNo());
                                    recordingData.setCustId(customerMst1.getCustId());
                                    recordingData.setCustomerEmail(customerMst1.getEmail());
                                    recordingData.setCustomerMobile(customerMst1.getCellPhone().toString());
                                } else {
                                    recordingData.setCustomerName("");
                                    recordingData.setAccountNo("");
                                    recordingData.setCustId("");
                                    recordingData.setCustomerEmail("");
                                    recordingData.setCustomerMobile("");
                                }

                            } else {
                                recordingData.setCustomerName("");
                                recordingData.setAccountNo("");
                                recordingData.setCustId("");
                                recordingData.setCustomerEmail("");
                                recordingData.setCustomerMobile("");
                            }

                            if (callID != 0) {

                                CallMst callMst = callMstService.findNonDeletedCallMstById(callID);

                                Long skillID1 = callMst.getLanguageId();

                                if (null == callMst.getServiceId() || callMst.getServiceId() == 0) {
                                    recordingData.setServiceName("");
                                    recordingData.setServiceDesc("");
                                } else {

                                    ServiceMst serviceMst = serviceMstService.findAllServiceMstById(callMst.getServiceId());
                                    recordingData.setServiceName(serviceMst.getServiceCd());
                                    recordingData.setServiceDesc(serviceMst.getServiceDesc());

                                }
                                if (null == callMst.getLanguageId() || callMst.getLanguageId() == 0) {
                                    recordingData.setSkillName("");
                                    recordingData.setSkillDesc("");
                                } else {

                                    LanguageMst skillMst1 = languageMstService.findNonDeletedLanguageMstById(skillID1);
                                    recordingData.setSkillName(skillMst1.getLanguageCd());
                                    recordingData.setSkillDesc(skillMst1.getLanguageDesc());
                                }

                                if (null == callMst.getCategoryId() || callMst.getCategoryId() == 0) {
                                    recordingData.setCategoryName("");
                                    recordingData.setCategoryDesc("");
                                } else {

                                    CategoryMst categoryMst1 = categoryMstService.findCategoryMstById(callMst.getCategoryId());
                                    recordingData.setCategoryName(categoryMst1.getCatgCd());
                                    recordingData.setCategoryDesc(categoryMst1.getCatgDesc());
                                }
                            }

                            if (cRecords.getExternalPlaybackLink() != null) {
                                if (cRecords.getExternalPlaybackLink().equalsIgnoreCase("Not saved")) {
                                    recordingData.setPlaybackLink("#");
                                    recordingData.setRenderDownloadLink(false);
                                }
                                if (cRecords.getExternalPlaybackLink().equalsIgnoreCase("Not Found")) {
                                    recordingData.setPlaybackLink("##");
                                    recordingData.setRenderDownloadLink(false);
                                } else {
                                    recordingData.setRenderDownloadLink(true);
                                    recordingData.setPlaybackLink(cRecords.getExternalPlaybackLink());
                                }
                            } else {
                                recordingData.setPlaybackLink("#");
                            }

                            recordingData.setId(cRecords.getId());
                            boolean mobileflag = true;
                            if (mobile != null && !"".equals(mobile.trim())) {
                                mobileflag = mobile.equals(recordingData.getCustomerMobile().trim());
                            }

                            if (mobileflag) {
                                rdList.add(recordingData);
                            }
                        }
                    }
                }

                setListRecord1(rdList);
                if (!rdList.isEmpty()) {
                    exportToCsvBtnStatus = true;
                    ThreadSafeSimpleDateFormat dateFormat1 = new ThreadSafeSimpleDateFormat("dd.MM.yyyy ");
                    if (recordStartDate != null) {
                        startTimeOutput = "Start Date : " + dateFormat1.format(recordStartDate);
                    }
                    if (recordEndDate != null) {
                        endTimeOutput = "End Date : " + dateFormat1.format(recordEndDate);
                    }
                    if (skill_id != null && skill_id != 0) {
                        LanguageMst skillMaster = languageMstService.findAllLanguageMstById(skill_id);
                        skillOutput = "Skill Name : " + skillMaster.getLanguageName();
                    }
                    if (category_id != null && category_id != 0) {
                        CategoryMst categoryMaster = categoryMstService.findCategoryMstById(category_id);
                        segmentOutput = "Segment Name : " + categoryMaster.getCatgName();
                    }
                    if (service_id != null && service_id != 0) {
                        ServiceMst serviceMaster = serviceMstService.findAllServiceMstById(service_id);
                        serviceOutput = "Service Name : " + serviceMaster.getServiceName();
                    }
                    if (customerId1 != null && !customerId1.trim().equals("")) {
                        custIdOutput = "Customer ID : " + customerId1;
                    }
                    if (employee_id != null && employee_id != 0) {
                        EmployeeMst emMst = employeeMstService.findEmployeeMstById(employee_id);
                        String empName;
                        if (emMst.getMidName() != null) {
                            empName = emMst.getFirstName() + " " + emMst.getMidName() + " " + emMst.getLastName();
                        } else {
                            empName = emMst.getFirstName() + " " + emMst.getLastName();
                        }
                        agentOutput = "Agent : " + empName;
                    }

                    outputTextToExport = startTimeOutput + "   " + endTimeOutput + "  " + agentOutput + "  " + custIdOutput + "  " + serviceOutput + "  " + segmentOutput + "  " + skillOutput;

                } else {
                    exportToCsvBtnStatus = false;
                }
            } else {
                if (listRecord1 != null) {
                    if (!listRecord1.isEmpty()) {
                        this.listRecord1.clear();
                    }
                }
                exportToCsvBtnStatus = false;
            }
        } catch (ParseException e) {
            if (listRecord1 != null) {
                if (!listRecord1.isEmpty()) {
                    this.listRecord1.clear();
                }
            }

        } finally {
            RequestContext.getCurrentInstance().execute("setLoadRender2();");
        }
    }

    public String goToChatView(String chattext, String componentType) {
        callFromwhichComponent = componentType.equalsIgnoreCase("CallRecords");
        chattext = chattext.replaceAll("&quot;", "'");
        this.setChatHtml(chattext);

        return "/pages/supervisor/viewChat.xhtml?faces-redirect=true";
    }

    public void doOpenFile(String docName) {
        logger.info("In doOpenFile================ " + docName);
        HttpSession session = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession();
//        String jbossHome = System.getenv("JBOSS_HOME");
//        String fileLoc = jbossHome + File.separator + "standalone" + File.separator + "tmp" + File.separator + "vfs" + File.separator + docName;
//        logger.info(" File Path " + fileLoc);
        session.setAttribute("imagePath", docName);
    }

    public void JSPopup(HttpServletRequest request) throws IOException, MalformedObjectNameException, ParseException {
        String link;
        String linkName = "View Chat";
        String website;
        website = request.getRequestURL().substring(0, request.getRequestURL().indexOf("/", 9));
        logger.info("website:" + website);

        website = Constants.WEB_PATH_URL;
        logger.info("website:" + website);
        link = website + "/viewPreviousChat";
        HttpSession session = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession(false);
        CallMst callMstLocal = (CallMst) session.getAttribute("callMst");
        CallRecords callRecords = callRecordsService.findCallRecordsByCallId(callMstLocal.getId(), (long) 0);
        String chattext = callRecords.getChatText();
        chattext = chattext.replaceAll("&quot;", "'");
        this.setChatHtml(chattext);
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("openChat('" + link + "','" + linkName + "')");
    }

    public String back() {
        if (callFromwhichComponent) {
            return "/pages/supervisor/listCallRecords.xhtml?faces-redirect=true";
        } else {
            return "/pages/reports/connectedCallsReport.xhtml?faces-redirect=true";
        }
    }

    public String back(HttpServletRequest request) {
        loadCallRecords(request);

        return "/pages/supervisor/listCallRecords.xhtml?faces-redirect=true";
    }

    public void selectRecordsById(Long id) {
        setCallrecords(callRecordsService.findRecordsById(id));
    }

    public String goToFileView(Long callMstId, String componentType) {
        logger.info(" In goToFileView " + callMstId);

        try {
            callFromwhichComponent = componentType.equalsIgnoreCase("CallRecords");
            fileReportDtoList = new ArrayList<>();
            if (callMstId != null && callMstId != 0) {
                logger.info("Call Mst id " + callMstId);
                CallMst lCallMst = callMstService.findCallMstById(callMstId);
                if (lCallMst != null) {
                    List<CallFileUploadDtls> lCallFileDtlList = fileUploadDtlsService.findAllFileByCallMst(callMstId);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                    for (CallFileUploadDtls lCallFileDtl : lCallFileDtlList) {
                        FileReportDto lFileReportDto = new FileReportDto();
                        lFileReportDto.setActualFilePath(lCallFileDtl.getFilePath());
                        lFileReportDto.setFileName(lCallFileDtl.getFilePath().substring(lCallFileDtl.getFilePath().lastIndexOf("/") + 1));
                        lFileReportDto.setUploadTime(dateFormat.format(lCallFileDtl.getCreatedDateTime()));
                        if (lCallFileDtl.getDocTitle() != null) {
                            lFileReportDto.setFileCaption(lCallFileDtl.getDocTitle());
                        } else {
                            lFileReportDto.setFileCaption("");
                        }
                        if (lCallFileDtl.getFileSentbyType().equalsIgnoreCase("Employee")) {
                            EmployeeMst lEmpMst = employeeMstService.findEmployeeMstById(lCallFileDtl.getFileSentBy());
                            if (lEmpMst.getMidName() != null && !lEmpMst.getMidName().equals("")) {
                                lFileReportDto.setFileSendBy(lEmpMst.getFirstName() + " " + lEmpMst.getMidName() + " " + lEmpMst.getLastName());
                            } else {
                                lFileReportDto.setFileSendBy(lEmpMst.getFirstName() + " " + lEmpMst.getLastName());
                            }
                            lFileReportDto.setFileSendByType("Employee");
                        } else {
                            CustomerMst lDtl = customerMstService.findCustomerMstById(lCallFileDtl.getFileSentBy());
                            lFileReportDto.setFileSendBy(lDtl.getFirstName() + " " + lDtl.getLastName());
                            lFileReportDto.setFileSendByType("Customer");
                        }
                        if (lCallFileDtl.getFileReceivedbyType().equalsIgnoreCase("Employee")) {
                            EmployeeMst lEmpMst = employeeMstService.findEmployeeMstById(lCallFileDtl.getFileReceivedBy());
                            if (lEmpMst.getMidName() != null && !lEmpMst.getMidName().equals("")) {
                                lFileReportDto.setFileSendTo(lEmpMst.getFirstName() + " " + lEmpMst.getMidName() + " " + lEmpMst.getLastName());
                            } else {
                                lFileReportDto.setFileSendTo(lEmpMst.getFirstName() + " " + lEmpMst.getLastName());
                            }
                            lFileReportDto.setFileSendToType("Employee");
                        } else {
                            CustomerMst lDtl = customerMstService.findCustomerMstById(lCallFileDtl.getFileReceivedBy());
                            lFileReportDto.setFileSendTo(lDtl.getFirstName() + " " + lDtl.getLastName());
                            lFileReportDto.setFileSendToType("Customer");
                        }

                        fileReportDtoList.add(lFileReportDto);

                    }
                    exportPdfOrXlsForFileDetails = !fileReportDtoList.isEmpty();
                }

            }
        } catch (Exception e) {
            logger.info("Error " + e);
        }
        return "/pages/supervisor/fileReportForCallRecords.xhtml?faces-redirect=true";
    }

    public void checkErrors() {

    }

    public EmployeeMst getEmployeeById(Long id) {
        setEmployeeMst(employeeMstService.findEmployeeMstById(id));
        return this.employeeMst;

    }

    public CustomerMst getCustomerById(Long id) {
        setCustomerMst(customerMstService.findCustomerMstById(id));
        return this.customerMst;

    }

    public CategoryMst getCategoryById(Long id) {
        setCategoryMst(categoryMstService.findCategoryMstById(id));
        return this.categoryMst;

    }

    public ServiceMst getServiceMstById(Long id) {
        setServicesMst(serviceMstService.findAllServiceMstById(id));
        return this.servicesMst;

    }

    public LanguageMst getSkillMstById(Long id) {
        setLanguageMst(languageMstService.findNonDeletedLanguageMstById(id));
        return this.languageMst;

    }

    public Date getRecordStartDate() {
        return recordStartDate;
    }

    public void setRecordStartDate(Date recordStartDate) {
        this.recordStartDate = recordStartDate;
    }

    public Date getRecordEndDate() {
        return recordEndDate;
    }

    public void setRecordEndDate(Date recordEndDate) {
        this.recordEndDate = recordEndDate;
    }

    public Long getService_id() {
        return service_id;
    }

    public void setService_id(Long service_id) {
        this.service_id = service_id;
    }

    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }

    public Long getSkill_id() {
        return skill_id;
    }

    public void setSkill_id(Long skill_id) {
        this.skill_id = skill_id;
    }

    public Long getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(Long employee_id) {
        this.employee_id = employee_id;
    }

    public ServiceMst getServicesMst() {
        return servicesMst;
    }

    public void setServicesMst(ServiceMst servicesMst) {
        this.servicesMst = servicesMst;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public LanguageMst getLanguageMst() {
        return languageMst;
    }

    public void setLanguageMst(LanguageMst languageMst) {
        this.languageMst = languageMst;
    }

    public RecordingData getRecordingData() {
        return recordingData;
    }

    public void setRecordingData(RecordingData recordingData) {
        this.recordingData = recordingData;
    }

    public CategoryMst getCategoryMst() {
        return categoryMst;
    }

    public void setCategoryMst(CategoryMst categoryMst) {
        this.categoryMst = categoryMst;
    }

    public CustomerMst getCustomerMst() {
        return customerMst;
    }

    public void setCustomerMst(CustomerMst customerMst) {
        this.customerMst = customerMst;
    }

    public EmployeeMst getEmployeeMst() {
        return employeeMst;
    }

    public void setEmployeeMst(EmployeeMst employeeMst) {
        this.employeeMst = employeeMst;
    }

    public List<ServiceMst> getSelectService() {
        return selectService;
    }

    public void setSelectService(List<ServiceMst> selectService) {
        this.selectService = selectService;
    }

    public List<LanguageMst> getSelectSkill() {
        return selectLanguage;
    }

    public void setSelectSkill(List<LanguageMst> selectSkill) {
        this.selectLanguage = selectSkill;
    }

    public List<CategoryMst> getSelectCategory() {
        return selectCategory;
    }

    public void setSelectCategory(List<CategoryMst> selectCategory) {
        this.selectCategory = selectCategory;
    }

    public List<CustomerMst> getSelectCustomer() {
        return selectCustomer;
    }

    public void setSelectCustomer(List<CustomerMst> selectCustomer) {
        this.selectCustomer = selectCustomer;
    }

    public List<EmployeeMst> getSelectEmployee() {
        return selectEmployee;
    }

    public void setSelectEmployee(List<EmployeeMst> selectEmployee) {
        this.selectEmployee = selectEmployee;
    }

    public CallRecords getCallrecords() {
        return callrecords;
    }

    public void setCallrecords(CallRecords callrecords) {
        this.callrecords = callrecords;
    }

    public String getRecordingStatus() {
        return recordingStatus;
    }

    public void setRecordingStatus(String recordingStatus) {
        this.recordingStatus = recordingStatus;
    }

    public boolean setRecordingState() {
        return recordingState;
    }

    public void setRecordingState(boolean recordingState) {
        this.recordingState = recordingState;
    }

    public String getCustomerId1() {
        return customerId1;
    }

    public void setCustomerId1(String customerId1) {
        this.customerId1 = customerId1;
    }

    public Date getPresentDate() {
        return presentDate;
    }

    public void setPresentDate(Date presentDate) {
        this.presentDate = presentDate;
    }

    public boolean isExportToCsvBtnStatus() {
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

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getChatHtml() {
        return chatHtml;
    }

    public void setChatHtml(String chatHtml) {
        this.chatHtml = chatHtml;
    }

    public List<RecordingData> getListRecord1() {
        return listRecord1;
    }

    public void setListRecord1(List<RecordingData> listRecord1) {
        this.listRecord1 = listRecord1;
    }

    public List<RecordingData> getSelectRecords1() {
        return selectRecords1;
    }

    public void setSelectRecords1(List<RecordingData> selectRecords1) {
        this.selectRecords1 = selectRecords1;
    }

    public List<CallRecords> getListRecord() {
        return listRecord;
    }

    public void setListRecord(List<CallRecords> listRecord) {
        this.listRecord = listRecord;
    }

    public List<CallRecords> getSelectRecords() {
        return selectRecords;
    }

    public void setSelectRecords(List<CallRecords> selectRecords) {
        this.selectRecords = selectRecords;
    }

    public String getRecorderId() {
        return recorderId;
    }

    public void setRecorderId(String recorderId) {
        this.recorderId = recorderId;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    public Boolean getCallFromwhichComponent() {
        return callFromwhichComponent;
    }

    public void setCallFromwhichComponent(Boolean callFromwhichComponent) {
        this.callFromwhichComponent = callFromwhichComponent;
    }

    public List<FileReportDto> getFileReportDtoList() {
        return fileReportDtoList;
    }

    public void setFileReportDtoList(List<FileReportDto> fileReportDtoList) {
        this.fileReportDtoList = fileReportDtoList;
    }

    public Boolean getExportPdfOrXlsForFileDetails() {
        return exportPdfOrXlsForFileDetails;
    }

    public void setExportPdfOrXlsForFileDetails(Boolean exportPdfOrXlsForFileDetails) {
        this.exportPdfOrXlsForFileDetails = exportPdfOrXlsForFileDetails;
    }

}
