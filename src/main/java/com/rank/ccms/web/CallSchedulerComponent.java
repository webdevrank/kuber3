package com.rank.ccms.web;

import com.rank.ccms.entities.CategoryMst;
import com.rank.ccms.entities.CustomerMst;
import com.rank.ccms.entities.EmployeeCallProficiency;
import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.entities.EmployeeTypeMst;
import com.rank.ccms.entities.ScheduleCall;
import com.rank.ccms.entities.ServiceMst;
import com.rank.ccms.entities.LanguageMst;
import com.rank.ccms.dto.CurrentScheduledCallStatus;
import com.rank.ccms.service.CallSchedulerService;
import com.rank.ccms.service.CategoryMstService;
import com.rank.ccms.service.CustomerMstService;
import com.rank.ccms.service.EmployeeMstService;
import com.rank.ccms.service.EmployeeTypeMstService;
import com.rank.ccms.service.ServiceMstService;
import com.rank.ccms.service.LanguageMstService;
import com.rank.ccms.util.CustomConvert;
import com.rank.ccms.util.SendingMailUtil;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import com.rank.ccms.util.ThreadSafeSimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class CallSchedulerComponent implements Serializable {

    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CallSchedulerComponent.class);
    private ScheduleCall scheduleCall = new ScheduleCall();

    private String name;
    private String custId;
    private String accountNo;
    private String branchCode;
    private String c5Code;
    private String custDesc;
    private String custSeg;
    private String addrsLine1;
    private String email;
    private Long cellPhone;
    private String callPref;
    private Integer callPrefValue;
    private Date presentDate;
    private Long customerId;
    private Long customerFound;
    private Date scheduleDate;
    private String executeStatus;
    private Long service_id;
    private Long category_id;
    private Long language_id;
    private Date startTime;
    private Date endTime;

    private List<ScheduleCall> listScheduleCall = new ArrayList<>();
    private List<ScheduleCall> selectScheduleCall = new ArrayList<>();
    private HashSet<EmployeeMst> listOfScheduledAgents;
    private List<String> agentStatusList;
    private List<String> additinalStatusList;

    private ServiceMst servicesMst;
    private LanguageMst languageMst;
    private CategoryMst categoryMst;
    private CustomerMst customerMst;
    private EmployeeMst employeeMst;
    private EmployeeCallProficiency employeeCallProficiency;

    private List<ServiceMst> selectService = new ArrayList<>();
    private List<LanguageMst> selectLanguage = new ArrayList<>();
    private List<CategoryMst> selectCategory = new ArrayList<>();
    private List<CustomerMst> selectCustomer = new ArrayList<>();
    private List<CurrentScheduledCallStatus> currentScheduledCallStatusList = new ArrayList<>();
    private CurrentScheduledCallStatus currentScheduledCallStatus = new CurrentScheduledCallStatus();
    private List<CurrentScheduledCallStatus> selectedScheduledCallStatus = new ArrayList<>();
    private Boolean pastRender = false;
    private Boolean currRender = true;
    private boolean csvButtonStatus;
    private List<CurrentScheduledCallStatus> filteredcurrentScheduledCallStatusList = new ArrayList<>();
    private String outputTextToExport = "";

    InputStream input = null;
    Properties prop = new Properties();

    @Autowired
    private ServiceMstService serviceMstService;

    @Autowired
    private LanguageMstService languageMstService;

    @Autowired
    private CategoryMstService categoryMstService;

    @Autowired
    private CallSchedulerService callSchedulerService;

    @Autowired
    private CustomerMstService customerMstService;

    @Autowired
    private EmployeeMstService employeeMstService;

    @Autowired
    private EmployeeTypeMstService employeeTypeMstService;

    public CallSchedulerComponent() {

    }

    public void newCallSchedulerComponent() {
        servicesMst = new ServiceMst();
        languageMst = new LanguageMst();
        categoryMst = new CategoryMst();
        customerMst = new CustomerMst();
        selectService = serviceMstService.findAllServiceMsts();
        selectLanguage = languageMstService.findAllNonDeletedLanguageMsts();
        selectCategory = categoryMstService.findAllCategoryMsts();
        csvButtonStatus = !currentScheduledCallStatusList.isEmpty();
        customerFound = (long) 0;
        java.util.Date datepresent = new java.util.Date();
        java.sql.Timestamp timestamp1 = new Timestamp(datepresent.getTime());
        java.util.Date date3 = new java.sql.Date(timestamp1.getTime());
        setPresentDate(date3);

    }

    public void customerSearch() {
        customerMst = new CustomerMst();
        customerMst = customerMstService.findCustomerMstByCustIdMobileNumAccount(this.custId, this.email, this.accountNo);
        if (customerMst != null) {
            this.customerFound = (long) 1;
            this.customerId = customerMst.getId();
            categoryMst = new CategoryMst();
            categoryMst = categoryMstService.findCategoryByCategoryName(customerMst.getCustSeg());
            this.custSeg = customerMst.getCustSeg();

        } else {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error! No Such Customer Found!!", ""));
        }

    }

    public String scheduleCallMethod(HttpServletRequest request) {
        EmployeeMst employeeMstlocal = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
        ThreadSafeSimpleDateFormat dateFormat = new ThreadSafeSimpleDateFormat("yyyy-MM-dd kk:mm:ss:SS");
        scheduleCall = new ScheduleCall();
        try {
            Timestamp dt_time = new Timestamp(dateFormat.parse(dateFormat.format(new Date())).getTime());
            Timestamp dt_time1 = new Timestamp(dateFormat.parse(dateFormat.format(this.scheduleDate)).getTime());
            categoryMst = new CategoryMst();
            categoryMst = categoryMstService.findCategoryByCategoryName(customerMst.getCustSeg());
            scheduleCall.setCategory(categoryMst.getId());
            scheduleCall.setLanguage(this.language_id);
            scheduleCall.setService(this.service_id);
            scheduleCall.setCreationDatetime(dt_time);
            scheduleCall.setExecuteStatus("Request");
            scheduleCall.setScheduledBy("Supervisor");
            scheduleCall.setSchedulerId((long) 0);
            scheduleCall.setScheduleDate(dt_time1);
            scheduleCall.setCustomerId(customerMst);
            scheduleCall.setSupervisorId(employeeMstlocal.getId());
            scheduleCall.setCallMedium("PC");
            scheduleCall.setActiveFlg(true);
            scheduleCall.setDeleteFlg(false);

            if (null != callPrefValue) switch (callPrefValue) {
                case 1:
                    callPref = "RM";
                    break;
                case 2:
                    callPref = "BM";
                    break;
                case 3:
                    break;
                default:
                    break;
            }

        } catch (ParseException e) {
            logger.info("Error:" + e.getMessage());
        }

        ScheduleCall schCall = callSchedulerService.saveScheduleCall(scheduleCall, employeeMstlocal);

        if (schCall == null) {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error!", "Save Unsuccessful!!"));
            newCallSchedulerComponent();
            return "/pages/schedule/scheduleCall.xhtml";
        } else {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Saved Successfully!", ""));

            currentSchedulCallStatus();
            return "/pages/schedule/currentScheduleCallStatus.xhtml";
        }

    }

    public void checkErrors() {

    }

    public void loadScheduleCallList() {
        selectScheduleCall.clear();
        Timestamp fromtime = null;
        try {
            fromtime = CustomConvert.javaDateToTimeStamp(new Date());
            String date1 = fromtime.toString().split(" ")[0];
            String time1 = "00:00";
            ThreadSafeSimpleDateFormat sdf = new ThreadSafeSimpleDateFormat("dd-MMM-yyyy HH:mm");
            java.util.Date date2 = sdf.parse(date1 + " " + time1);
            Timestamp datetime = CustomConvert.javaDateToTimeStamp(date2);
            fromtime = datetime;
        } catch (ParseException ex) {
            Logger.getLogger(CallSchedulerComponent.class.getName()).log(Level.SEVERE, null, ex);
        }
        setListScheduleCall(callSchedulerService.findAllNonTakenScheduleCallsByDate(fromtime));
    }

    public List<CurrentScheduledCallStatus> currentSchedulCallStatus() {
        setCsvButtonStatus(false);
        Timestamp fromtime = null;

        selectedScheduledCallStatus = new ArrayList<>();
        try {
            fromtime = CustomConvert.javaDateToTimeStamp(new Date());
            String date1 = fromtime.toString().split(" ")[0];
            String time1 = "00:00";
            ThreadSafeSimpleDateFormat sdf = new ThreadSafeSimpleDateFormat("yyyy-MM-dd HH:mm");
            java.util.Date date2 = sdf.parse(date1 + " " + time1);
            Timestamp datetime = CustomConvert.javaDateToTimeStamp(date2);
            fromtime = datetime;
        } catch (ParseException ex) {

            Logger.getLogger(CallSchedulerComponent.class.getName()).log(Level.SEVERE, null, ex);
        }
        listScheduleCall = new ArrayList<>();
        currentScheduledCallStatusList = new ArrayList<>();
        setListScheduleCall(callSchedulerService.findAllNonTakenScheduleCallsByDate(fromtime));

        if (!listScheduleCall.isEmpty()) {

            for (ScheduleCall singleScheduledCall : listScheduleCall) {

                currentScheduledCallStatus = new CurrentScheduledCallStatus();

                customerMst = customerMstService.findCustomerMstById(singleScheduledCall.getCustomerId().getId());

                String cFname;
                String mName;
                String lName;
                if (customerMst.getFirstName() == null) {
                    cFname = "";
                } else {
                    cFname = customerMst.getFirstName();
                }
                if (customerMst.getMidName() == null) {
                    mName = "";
                } else {
                    mName = customerMst.getMidName();
                }
                if (customerMst.getLastName() == null) {
                    lName = "";
                } else {
                    lName = customerMst.getLastName();
                }
                currentScheduledCallStatus.setCustomerName(cFname + mName + lName);

                currentScheduledCallStatus.setCustomerMobile(customerMst.getCellPhone().toString());

                currentScheduledCallStatus.setCustomerEmail(customerMst.getEmail());

                currentScheduledCallStatus.setCustomerAccount(customerMst.getAccountNo());

                currentScheduledCallStatus.setCustomerSeg(customerMst.getCustSeg());

                ThreadSafeSimpleDateFormat sdf = new ThreadSafeSimpleDateFormat("dd-MM-yyyy HH:mm");

                //java.sql.Timestamp timestamp = singleScheduledCall.getScheduleDate();
                java.util.Date date2 = singleScheduledCall.getScheduleDate();
                String date1 = sdf.format(date2);

                String[] date = date1.split(" ");

                java.util.Date datepresent = new java.util.Date();
                java.sql.Timestamp timestamp1 = new Timestamp(datepresent.getTime());
                java.util.Date date3 = new java.sql.Date(timestamp1.getTime());
                currentScheduledCallStatus.setPresentDate(date3);

                currentScheduledCallStatus.setScheduleDate(date[0]);
                currentScheduledCallStatus.setScheduleTime(date[1]);
                currentScheduledCallStatus.setScheduleDateTimestamp(date2);
                currentScheduledCallStatus.setScheduleTimeTimestamp(date2);
                currentScheduledCallStatus.setAgentStatus(singleScheduledCall.getExecuteStatus());

                currentScheduledCallStatus.setId(singleScheduledCall.getId());
                employeeMst = null;
                employeeMst = employeeMstService.findEmployeeMstById(singleScheduledCall.getSchedulerId());

                if (null != employeeMst) {
                    currentScheduledCallStatus.setAgentId(employeeMst.getId());
                    currentScheduledCallStatus.setAgentUserId(employeeMst.getLoginId());
                }
                if (singleScheduledCall.getService() != null && singleScheduledCall.getService() != (long) 0) {
                    ServiceMst servicesMstlocal = serviceMstService.findAllServiceMstById(singleScheduledCall.getService());
                    if (servicesMstlocal != null) {
                        currentScheduledCallStatus.setServiceCd(servicesMstlocal.getServiceCd());
                        currentScheduledCallStatus.setServiceDesc(servicesMstlocal.getServiceDesc());
                    } else {
                        currentScheduledCallStatus.setServiceCd("N/A");
                        currentScheduledCallStatus.setServiceDesc("N/A");
                    }
                } else {
                    currentScheduledCallStatus.setServiceCd("N/A");
                    currentScheduledCallStatus.setServiceDesc("N/A");
                }
                if (singleScheduledCall.getLanguage() != null && singleScheduledCall.getLanguage() != (long) 0) {
                    LanguageMst languageMstlocal = languageMstService.findAllLanguageMstById(singleScheduledCall.getLanguage());
                    if (languageMstlocal != null) {
                        currentScheduledCallStatus.setLanguageCd(languageMstlocal.getLanguageCd());
                        currentScheduledCallStatus.setLanguageDesc(languageMstlocal.getLanguageDesc());
                    }
                } else if (customerMst.getCustLang1() != null && !customerMst.getCustLang1().trim().equals("")) {
                    LanguageMst languageMstlocal = languageMstService.findLanguageMstByLanguageName(customerMst.getCustLang1());
                    if (languageMstlocal != null) {
                        currentScheduledCallStatus.setLanguageCd(languageMstlocal.getLanguageCd());
                        currentScheduledCallStatus.setLanguageDesc(languageMstlocal.getLanguageDesc());
                    }
                } else {
                    currentScheduledCallStatus.setLanguageCd("N/A");
                    currentScheduledCallStatus.setLanguageDesc("N/A");
                }
                if (singleScheduledCall.getCategory() != null && singleScheduledCall.getCategory() != (long) 0) {
                    CategoryMst categoryMstlocal = categoryMstService.findCategoryMstById(singleScheduledCall.getCategory());
                    if (categoryMstlocal != null) {
                        currentScheduledCallStatus.setSegmentCd(categoryMstlocal.getCatgCd());
                        currentScheduledCallStatus.setSegmentDesc(categoryMstlocal.getCatgDesc());
                    }
                } else if (customerMst.getCustSeg() != null && !customerMst.getCustSeg().trim().equals("")) {
                    CategoryMst categoryMstlocal = categoryMstService.findCategoryByCategoryName(customerMst.getCustSeg());
                    if (categoryMstlocal != null) {
                        currentScheduledCallStatus.setSegmentCd(categoryMstlocal.getCatgCd());
                        currentScheduledCallStatus.setSegmentDesc(categoryMstlocal.getCatgDesc());
                    }
                } else {
                    currentScheduledCallStatus.setSegmentCd("N/A");
                    currentScheduledCallStatus.setSegmentDesc("N/A");
                }

                currentScheduledCallStatusList.add(currentScheduledCallStatus);

            }

        }

        if (!currentScheduledCallStatusList.isEmpty()) {
            setCsvButtonStatus(true);
        } else {
            setCsvButtonStatus(false);
        }
        this.setFilteredcurrentScheduledCallStatusList(currentScheduledCallStatusList);
        return currentScheduledCallStatusList;

    }

    public void clear() {
        this.startTime = null;
        this.endTime = null;

        if (currentScheduledCallStatusList != null) {
            if (!currentScheduledCallStatusList.isEmpty()) {
                currentScheduledCallStatusList.clear();
            }
        }

//        setExportToCsvBtnStatus1(false);
    }

    public void currentSchedulCallStatusByDateInitialize(Date starttime, Date endtime) {
        if (starttime != null && endtime != null) {
            if (starttime.after(endtime)) {

            } else {
                RequestContext.getCurrentInstance().execute("document.getElementById('search').click();");
            }
        }
    }

    public List<CurrentScheduledCallStatus> currentSchedulCallStatusByDate(Date starttime, Date endtime) {
        logger.info("chayan inside currentSchedulCallStatusByDate");
        setCsvButtonStatus(false);
        Timestamp fromtime = null;
        Timestamp totime = null;
        logger.info("11111111111111");
        selectedScheduledCallStatus = new ArrayList<>();
        listScheduleCall = new ArrayList<>();
        if (starttime != null && endtime != null) {
            if (starttime.after(endtime)) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Start Date Time is greater than End Date Time!", "Search Unsuccessfull!!"));
            } else {
                try {
                    fromtime = CustomConvert.javaDateToTimeStamp(starttime);
                    totime = CustomConvert.javaDateToTimeStamp(endtime);

                } catch (ParseException ex) {

                    Logger.getLogger(CallSchedulerComponent.class.getName()).log(Level.SEVERE, null, ex);
                }

                currentScheduledCallStatusList = new ArrayList<>();
                setListScheduleCall(callSchedulerService.findAllNonTakenScheduleCallsByDateRange(fromtime, totime));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please select Start Date and End Date,both!", "Search Unsuccessfull!!"));
        }
        if (!listScheduleCall.isEmpty()) {
            logger.info("222222222222");
            for (ScheduleCall singleScheduledCall : listScheduleCall) {

                currentScheduledCallStatus = new CurrentScheduledCallStatus();

                customerMst = customerMstService.findCustomerMstById(singleScheduledCall.getCustomerId().getId());

                String cFname;
                String mName;
                String lName;
                if (customerMst.getFirstName() == null) {
                    cFname = "";
                } else {
                    cFname = customerMst.getFirstName();
                }
                if (customerMst.getMidName() == null) {
                    mName = "";
                } else {
                    mName = customerMst.getMidName();
                }
                if (customerMst.getLastName() == null) {
                    lName = "";
                } else {
                    lName = customerMst.getLastName();
                }
                currentScheduledCallStatus.setCustomerName(cFname + mName + lName);

                currentScheduledCallStatus.setCustomerMobile(customerMst.getCellPhone().toString());

                currentScheduledCallStatus.setCustomerEmail(customerMst.getEmail());

                currentScheduledCallStatus.setCustomerAccount(customerMst.getAccountNo());

                currentScheduledCallStatus.setCustomerSeg(customerMst.getCustSeg());

                ThreadSafeSimpleDateFormat sdf = new ThreadSafeSimpleDateFormat("dd-MM-yyyy HH:mm");

                //java.sql.Timestamp timestamp = singleScheduledCall.getScheduleDate();
                java.util.Date date2 = singleScheduledCall.getScheduleDate();
                String date1 = sdf.format(date2);

                String[] date = date1.split(" ");

                java.util.Date datepresent = new java.util.Date();
                java.sql.Timestamp timestamp1 = new Timestamp(datepresent.getTime());
                java.util.Date date3 = new java.sql.Date(timestamp1.getTime());
                currentScheduledCallStatus.setPresentDate(date3);

                currentScheduledCallStatus.setScheduleDate(date[0]);
                currentScheduledCallStatus.setScheduleTime(date[1]);
                currentScheduledCallStatus.setScheduleDateTimestamp(date2);
                currentScheduledCallStatus.setScheduleTimeTimestamp(date2);
                currentScheduledCallStatus.setAgentStatus(singleScheduledCall.getExecuteStatus());

                currentScheduledCallStatus.setId(singleScheduledCall.getId());
                employeeMst = null;
                employeeMst = employeeMstService.findEmployeeMstById(singleScheduledCall.getSchedulerId());

                if (null != employeeMst) {
                    currentScheduledCallStatus.setAgentId(employeeMst.getId());
                    currentScheduledCallStatus.setAgentUserId(employeeMst.getLoginId());
                }
                if (singleScheduledCall.getService() != null && singleScheduledCall.getService() != (long) 0) {
                    servicesMst = serviceMstService.findAllServiceMstById(singleScheduledCall.getService());
                    if (servicesMst != null) {
                        currentScheduledCallStatus.setServiceCd(servicesMst.getServiceCd());
                        currentScheduledCallStatus.setServiceDesc(servicesMst.getServiceDesc());
                    } else {
                        currentScheduledCallStatus.setServiceCd("N/A");
                        currentScheduledCallStatus.setServiceDesc("N/A");
                    }
                } else {
                    currentScheduledCallStatus.setServiceCd("N/A");
                    currentScheduledCallStatus.setServiceDesc("N/A");
                }
                if (singleScheduledCall.getLanguage() != null && singleScheduledCall.getLanguage() != (long) 0) {
                    languageMst = languageMstService.findAllLanguageMstById(singleScheduledCall.getLanguage());
                    if (languageMst != null) {
                        currentScheduledCallStatus.setLanguageCd(languageMst.getLanguageCd());
                        currentScheduledCallStatus.setLanguageDesc(languageMst.getLanguageDesc());
                    }
                } else {
                    currentScheduledCallStatus.setLanguageCd("N/A");
                    currentScheduledCallStatus.setLanguageDesc("N/A");
                }
                if (singleScheduledCall.getCategory() != null && singleScheduledCall.getCategory() != (long) 0) {
                    categoryMst = categoryMstService.findCategoryMstById(singleScheduledCall.getCategory());
                    if (categoryMst != null) {
                        currentScheduledCallStatus.setSegmentCd(categoryMst.getCatgCd());
                        currentScheduledCallStatus.setSegmentDesc(categoryMst.getCatgDesc());
                    }
                } else {
                    currentScheduledCallStatus.setSegmentCd("N/A");
                    currentScheduledCallStatus.setSegmentDesc("N/A");
                }
                logger.info("333333333333333");
                currentScheduledCallStatusList.add(currentScheduledCallStatus);

            }

        }
        logger.info("44444444444444444444");
        if (!currentScheduledCallStatusList.isEmpty()) {
            logger.info("555555555555555");
            setCsvButtonStatus(true);
        } else {
            logger.info("6666666666666666");
            setCsvButtonStatus(false);
        }
        this.setFilteredcurrentScheduledCallStatusList(currentScheduledCallStatusList);
        logger.info("77777777777777777");
        return currentScheduledCallStatusList;

    }

    public List<CurrentScheduledCallStatus> currentSchedulCallStatusByDateFromEdit() {
        setCsvButtonStatus(false);
        Timestamp fromtime = null;
        Timestamp totime = null;
        selectedScheduledCallStatus = new ArrayList<>();
        try {
            if (this.startTime != null && this.endTime != null) {
                fromtime = CustomConvert.javaDateToTimeStamp(this.startTime);
                totime = CustomConvert.javaDateToTimeStamp(this.endTime);
            } else {
                Date dt = new Date();
                fromtime = CustomConvert.javaDateToTimeStamp(dt);
                String date1 = fromtime.toString().split(" ")[0];
                String time1 = "00:00";
                ThreadSafeSimpleDateFormat sdf = new ThreadSafeSimpleDateFormat("yyyy-MM-dd HH:mm");
                java.util.Date date2 = sdf.parse(date1 + " " + time1);
                Timestamp datetime = CustomConvert.javaDateToTimeStamp(date2);
                fromtime = datetime;
            }
        } catch (ParseException ex) {

            Logger.getLogger(CallSchedulerComponent.class.getName()).log(Level.SEVERE, null, ex);
        }
        listScheduleCall = new ArrayList<>();
        currentScheduledCallStatusList = new ArrayList<>();
        if (totime == null) {
            setListScheduleCall(callSchedulerService.findAllNonTakenScheduleCallsByDate(fromtime));
        } else {
            setListScheduleCall(callSchedulerService.findAllNonTakenScheduleCallsByDateRange(fromtime, totime));
        }

        if (!listScheduleCall.isEmpty()) {

            for (ScheduleCall singleScheduledCall : listScheduleCall) {

                currentScheduledCallStatus = new CurrentScheduledCallStatus();

                customerMst = customerMstService.findCustomerMstById(singleScheduledCall.getCustomerId().getId());

                String cFname;
                String mName;
                String lName;
                if (customerMst.getFirstName() == null) {
                    cFname = "";
                } else {
                    cFname = customerMst.getFirstName();
                }
                if (customerMst.getMidName() == null) {
                    mName = "";
                } else {
                    mName = customerMst.getMidName();
                }
                if (customerMst.getLastName() == null) {
                    lName = "";
                } else {
                    lName = customerMst.getLastName();
                }
                currentScheduledCallStatus.setCustomerName(cFname + mName + lName);

                currentScheduledCallStatus.setCustomerMobile(customerMst.getCellPhone().toString());

                currentScheduledCallStatus.setCustomerEmail(customerMst.getEmail());

                currentScheduledCallStatus.setCustomerAccount(customerMst.getAccountNo());

                currentScheduledCallStatus.setCustomerSeg(customerMst.getCustSeg());

                ThreadSafeSimpleDateFormat sdf = new ThreadSafeSimpleDateFormat("dd-MM-yyyy HH:mm");

                //java.sql.Timestamp timestamp = singleScheduledCall.getScheduleDate();
                java.util.Date date2 = singleScheduledCall.getScheduleDate();
                String date1 = sdf.format(date2);

                String[] date = date1.split(" ");

                java.util.Date datepresent = new java.util.Date();
                java.sql.Timestamp timestamp1 = new Timestamp(datepresent.getTime());
                java.util.Date date3 = new java.sql.Date(timestamp1.getTime());
                currentScheduledCallStatus.setPresentDate(date3);

                currentScheduledCallStatus.setScheduleDate(date[0]);
                currentScheduledCallStatus.setScheduleTime(date[1]);
                currentScheduledCallStatus.setScheduleDateTimestamp(date2);
                currentScheduledCallStatus.setScheduleTimeTimestamp(date2);
                currentScheduledCallStatus.setAgentStatus(singleScheduledCall.getExecuteStatus());

                currentScheduledCallStatus.setId(singleScheduledCall.getId());
                employeeMst = null;
                employeeMst = employeeMstService.findEmployeeMstById(singleScheduledCall.getSchedulerId());

                if (null != employeeMst) {
                    currentScheduledCallStatus.setAgentId(employeeMst.getId());
                    currentScheduledCallStatus.setAgentUserId(employeeMst.getLoginId());
                }
                if (singleScheduledCall.getService() != null && singleScheduledCall.getService() != (long) 0) {
                    servicesMst = serviceMstService.findAllServiceMstById(singleScheduledCall.getService());
                    if (servicesMst != null) {
                        currentScheduledCallStatus.setServiceCd(servicesMst.getServiceCd());
                        currentScheduledCallStatus.setServiceDesc(servicesMst.getServiceDesc());
                    } else {
                        currentScheduledCallStatus.setServiceCd("N/A");
                        currentScheduledCallStatus.setServiceDesc("N/A");
                    }
                } else {
                    currentScheduledCallStatus.setServiceCd("N/A");
                    currentScheduledCallStatus.setServiceDesc("N/A");
                }
                if (singleScheduledCall.getLanguage() != null && singleScheduledCall.getLanguage() != (long) 0) {
                    languageMst = languageMstService.findAllLanguageMstById(singleScheduledCall.getLanguage());
                    if (languageMst != null) {
                        currentScheduledCallStatus.setLanguageCd(languageMst.getLanguageCd());
                        currentScheduledCallStatus.setLanguageDesc(languageMst.getLanguageDesc());
                    }
                } else {
                    currentScheduledCallStatus.setLanguageCd("N/A");
                    currentScheduledCallStatus.setLanguageDesc("N/A");
                }
                if (singleScheduledCall.getCategory() != null && singleScheduledCall.getCategory() != (long) 0) {
                    categoryMst = categoryMstService.findCategoryMstById(singleScheduledCall.getCategory());
                    if (categoryMst != null) {
                        currentScheduledCallStatus.setSegmentCd(categoryMst.getCatgCd());
                        currentScheduledCallStatus.setSegmentDesc(categoryMst.getCatgDesc());
                    }
                } else {
                    currentScheduledCallStatus.setSegmentCd("N/A");
                    currentScheduledCallStatus.setSegmentDesc("N/A");
                }
                currentScheduledCallStatusList.add(currentScheduledCallStatus);

            }

        }

        if (!currentScheduledCallStatusList.isEmpty()) {
            setCsvButtonStatus(true);
        } else {
            setCsvButtonStatus(false);
        }
        this.setFilteredcurrentScheduledCallStatusList(currentScheduledCallStatusList);
        return currentScheduledCallStatusList;

    }

    public List<String> loadAgentStatus() throws IOException {
        agentStatusList = new ArrayList<>();
        List<String> agentList = new ArrayList<>();
        Date now = new Date();
        if (currentScheduledCallStatus.getScheduleDateTimestamp().before(now)) {
            if (currentScheduledCallStatus.getAgentStatus().equalsIgnoreCase("Completed")) {
                agentList.add("Completed");
            } else if (currentScheduledCallStatus.getAgentStatus().equalsIgnoreCase("Reject")) {
                agentList.add("Reject");
            } else if (currentScheduledCallStatus.getAgentStatus().equalsIgnoreCase("Request")) {
                agentList.add("Request");
                agentList.add("Reject");
            } else if (currentScheduledCallStatus.getAgentStatus().equalsIgnoreCase("Scheduled")) {
                agentList.add("Scheduled");
                agentList.add("Completed");
                agentList.add("Reject");
            }
        } else if (currentScheduledCallStatus.getAgentStatus().equalsIgnoreCase("Completed")) {
            agentList.add("Completed");
        } else if (currentScheduledCallStatus.getAgentStatus().equalsIgnoreCase("Reject")) {
            agentList.add("Reject");
        } else if (currentScheduledCallStatus.getAgentStatus().equalsIgnoreCase("Request")) {
            agentList.add("Request");
            agentList.add("Scheduled");
            agentList.add("Reject");
        } else if (currentScheduledCallStatus.getAgentStatus().equalsIgnoreCase("Scheduled")) {
            agentList.add("Scheduled");
            agentList.add("Completed");
            agentList.add("Reject");
        }

        return agentList;
    }

    public HashSet<EmployeeMst> loadScheduledAgents() {
        listOfScheduledAgents = new HashSet<>();
//         List<EmployeeCallProficiency> listEmployeeCallProficiencyByPrimarySegment=employeeCallProficiencyService.findEmployeeCallProficiencyByPrimarySegment("SCH");
//         List<EmployeeCallProficiency> listEmployeeCallProficiencyBySecondarySegment=employeeCallProficiencyService.findEmployeeCallProficiencyBySecondarySegment("SCH");
//         List<EmployeeCallProficiency> listEmployeeCallProficiencyForSch=ListUtils.union(listEmployeeCallProficiencyByPrimarySegment, listEmployeeCallProficiencyBySecondarySegment);
//         if(!listEmployeeCallProficiencyForSch.isEmpty()){
//             for(EmployeeCallProficiency singleEmployeeCallProficiency:listEmployeeCallProficiencyForSch){
//                 employeeMst=employeeMstService.findEmployeeMstById(singleEmployeeCallProficiency.getEmpId().getId());
//                 if(employeeMst!=null){
//                 listOfScheduledAgents.add(employeeMst);
//                 }
//             }
//         }
        List<EmployeeMst> lEmpMst = new ArrayList<>();
        EmployeeTypeMst localEmpType = employeeTypeMstService.findEmployeeTypeByEmployeeTypeName("ScheduleAgent");
        if (localEmpType != null) {
            lEmpMst = employeeMstService.findEmployeeByEmpTypeId(localEmpType);
        }
        if (!lEmpMst.isEmpty()) {
            for (EmployeeMst eMst : lEmpMst) {
                listOfScheduledAgents.add(eMst);
            }
        }
        return listOfScheduledAgents;
    }

//    public String editScheduleCall() throws ParseException {
//
//        boolean save = true;
//        Long agentid = (long) 0;
//        if (this.currentScheduledCallStatus.getAgentId() != null) {
//            agentid = this.currentScheduledCallStatus.getAgentId();
//        }
//
//        if ((this.currentScheduledCallStatus.getAgentStatus().trim().equalsIgnoreCase("Scheduled")
//                || this.currentScheduledCallStatus.getAgentStatus().trim().equalsIgnoreCase("Completed"))
//                && (agentid == 0)) {
//            save = false;
//        }
//        if (this.pastRender) {
//            save = true;
//        }
//
//        if (save) {
//            ThreadSafeSimpleDateFormat sdf = new ThreadSafeSimpleDateFormat("dd-MMM-yyyy HH:mm");
//            HttpSession session = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession(false);
//            CurrentScheduledCallStatus dto = getCurrentScheduledCallStatus();
//            EmployeeMst emp = (EmployeeMst) session.getAttribute("ormUserMaster");
//            ScheduleCall scCall;
//            scCall = callSchedulerService.findNonDeletedScheduleCallById(dto.getId());
//            scCall.setExecuteStatus(dto.getAgentStatus());
//
//            if (dto.getScheduleDateTimestamp() != null) {
//                Date scheduledDate = dto.getScheduleDateTimestamp();
//                Date scheduledTime = dto.getScheduleTimeTimestamp();
//
//                String dateSchDate1 = sdf.format(scheduledDate);
//
//                String[] datedateSchDate = dateSchDate1.split(" ");
//
//                String dateSchTime1 = sdf.format(scheduledTime);
//
//                String[] datedateSchTime = dateSchTime1.split(" ");
//                String schDate = datedateSchDate[0];
//                String schTime = datedateSchTime[1];
//                java.util.Date ScheduleDateTime = sdf.parse(schDate + " " + schTime);
//                Timestamp dt_time1 = new Timestamp(ScheduleDateTime.getTime());
//                scCall.setScheduleDate(dt_time1);
//            }
//
//            CustomerMst custMst = customerMstService.findCustomerMstById(scCall.getCustomerId().getId());
//            custMst.setEmail(dto.getCustomerEmail());
//            custMst.setCellPhone(Long.parseLong(dto.getCustomerMobile()));
//            customerMstService.saveCustomerMst(custMst, emp);
//            if (dto.getAgentId() != null) {
//                scCall.setSchedulerId(dto.getAgentId());
//            }
//
//            employeeMst = new EmployeeMst();
//
//            if (null != dto.getAgentUserId()) {
//                employeeMst = employeeMstService.findEmployeeByUserId(dto.getAgentUserId());
//
//            }
//            scCall = callSchedulerService.saveScheduleCall(scCall, emp);
//            if (scCall == null) {
//                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed to Update Schedule id ", "Please try again!");
//                FacesContext.getCurrentInstance().addMessage(null, msg);
//            } else {
//                try {
//                    if (scCall.getExecuteStatus().equalsIgnoreCase("Scheduled")) {
//                        // CallScheduler callScheduler = new CallScheduler();
//
//                        EmployeeMst employeeMstLocal = employeeMstService.findEmployeeMstById(scCall.getSchedulerId());
//                        String custEmail = custMst.getEmail();
//                        if (null != custEmail) {
//                            StringBuffer sb = new StringBuffer(255);
//                            sb.append("Dear Customer ").append(custMst.getFirstName());
//                            sb.append(", \nYour Schedule Call with our Schedule Agent ");
//                            sb.append(employeeMstLocal.getFirstName());
//                            sb.append(" On ").append(scCall.getScheduleDate());
//                            sb.append(SendingMailUtil.TELE_THX);
//                            try {
//                                boolean mailSend = SendingMailUtil.sendMail(custEmail, sb, SendingMailUtil.SCHEDULE_CALL);
//                                if (mailSend) {
//                                    logger.info("mail sending was successfull... to Customer" + custEmail);
//                                }
//                            } catch (Exception ex) {
//                                logger.error("Sending Email Error:" + ex.getMessage());
//                            }
//                        }
//                        //        callScheduler.loadCustomerMail(custMst.getEmail(), scCall.getScheduleDate());
//                        if (null != employeeMstLocal) {
//                            String emailTo = employeeMstLocal.getEmail();
//                            if (null != emailTo) {
//                                StringBuffer sb = new StringBuffer(255);
//                                sb.append("Dear ").append(employeeMstLocal.getFirstName());
//                                sb.append(" , \nYour Schedule Call with our Customer ");
//                                sb.append(custMst.getFirstName());
//                                sb.append(" On ").append(scCall.getScheduleDate());
//                                sb.append(SendingMailUtil.TELE_THX);
//                                try {
//                                    boolean mailSend = SendingMailUtil.sendMail(emailTo, sb, SendingMailUtil.SCHEDULE_CALL);
//                                    if (mailSend) {
//                                        logger.info("mail sending was successfull... to Agent" + emailTo);
//                                    }
//                                } catch (Exception ex) {
//                                    logger.error("Sending Email Error:" + ex.getMessage());
//                                }
//                            }
//                        }
//                    }
//                } catch (Exception ex) {
//                    logger.error("  Error:" + ex.getMessage());
//                }
//                switch (dto.getAgentStatus()) {
//                    case "Scheduled":
//
//                        break;
//                    case "Reject":
//                        break;
//                }
//
//                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Schedule Request Updated Successfully", "");
//
//                FacesContext.getCurrentInstance().addMessage(null, msg);
//
//            }
//            currentSchedulCallStatusByDateFromEdit();
//            return "/pages/schedule/currentScheduleCallStatus.xhtml";
//        } else {
//            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please select Agent Id as you have selected Scheduled status as Scheduled or Completed", "Please Try Again");
//            FacesContext.getCurrentInstance().addMessage(null, msg);
//            return "/pages/schedule/editScheduledCallStatus.xhtml";
//        }
//    }
    public String editScheduleCall() throws ParseException {

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String websiteURL = request.getRequestURL().substring(0, request.getRequestURL().indexOf("/", 9));
        RequestContext.getCurrentInstance().execute("$('#spinner').show();");
        boolean save = true;
        Long agentid = (long) 0;
        if (this.currentScheduledCallStatus.getAgentId() != null) {
            agentid = this.currentScheduledCallStatus.getAgentId();
        }

        if ((this.currentScheduledCallStatus.getAgentStatus().trim().equalsIgnoreCase("Scheduled")
                || this.currentScheduledCallStatus.getAgentStatus().trim().equalsIgnoreCase("Completed"))
                && (agentid == 0)) {
            save = false;
        }
        if (this.pastRender) {
            save = true;
        }

        if (save) {
            ThreadSafeSimpleDateFormat sdf = new ThreadSafeSimpleDateFormat("dd-MMM-yyyy HH:mm");
            HttpSession session = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession(false);
            CurrentScheduledCallStatus dto = getCurrentScheduledCallStatus();
            EmployeeMst emp = (EmployeeMst) session.getAttribute("ormUserMaster");
            ScheduleCall scCall;
            scCall = callSchedulerService.findNonDeletedScheduleCallById(dto.getId());
            scCall.setExecuteStatus(dto.getAgentStatus());

            if (dto.getScheduleDateTimestamp() != null) {
                Date scheduledDate = dto.getScheduleDateTimestamp();
                Date scheduledTime = dto.getScheduleTimeTimestamp();

                String dateSchDate1 = sdf.format(scheduledDate);
                String[] datedateSchDate = dateSchDate1.split(" ");
                String dateSchTime1 = sdf.format(scheduledTime);
                String[] datedateSchTime = dateSchTime1.split(" ");
                String schDate = datedateSchDate[0];
                String schTime = datedateSchTime[1];
                java.util.Date ScheduleDateTime = sdf.parse(schDate + " " + schTime);
                Timestamp dt_time1 = new Timestamp(ScheduleDateTime.getTime());
                scCall.setScheduleDate(dt_time1);
            }

            CustomerMst custMst = customerMstService.findCustomerMstById(scCall.getCustomerId().getId());
            custMst.setEmail(dto.getCustomerEmail());
            custMst.setCellPhone(Long.parseLong(dto.getCustomerMobile()));
            customerMstService.saveCustomerMst(custMst, emp);
            if (dto.getAgentId() != null) {
                scCall.setSchedulerId(dto.getAgentId());
            }

            employeeMst = new EmployeeMst();
            if (null != dto.getAgentUserId()) {
                employeeMst = employeeMstService.findEmployeeByUserId(dto.getAgentUserId());
            }
            scCall = callSchedulerService.saveScheduleCall(scCall, emp);
            if (scCall == null) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed to Update Schedule id ", "Please try again!");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                try {

                    if (scCall.getExecuteStatus().equalsIgnoreCase("Scheduled")) {
                        String messageBody;
                        //CallScheduler callScheduler = new CallScheduler();
                        EmployeeMst employeeMstLocal = employeeMstService.findEmployeeMstById(scCall.getSchedulerId());
                        String custEmail = custMst.getEmail();
                        if (null != custEmail) {
                            messageBody = "<html><body>Dear &nbsp;" + custMst.getFirstName();
                            messageBody += ",&nbsp;<br></br><br></br>We have confirmed a schedule call from CCMS with our Service Agent at ";
                            messageBody += sdf.format(scCall.getScheduleDate());
                            messageBody += "&nbsp;<br></br><br></br>Kindly click the following link, preferably 10 minutes before call time.";
                            messageBody += "<br></br><br></br>";
                            messageBody += "<a href=";
                            //messageBody+= websiteURL+"/faces/pages/customer/customerScheduleCall.xhtml?scheduleCallId="+scCall.getId();
                            if (custMst.getAccountNo() != null && custMst.getAccountNo().length() > 0) {
                                messageBody += websiteURL + request.getContextPath() + "/custScheduleCheck?scheduleCallId=" + scCall.getId();
                            } else {
                                messageBody += websiteURL + request.getContextPath() + "/custScheduleCheck?scheduleCallId=" + scCall.getId() + "&type=guest";
                            }

                            messageBody += ">Meeting Link</a>";
                            messageBody += "<br></br><br></br><br></br>";
                            messageBody += "<b>Note:</b>Please do not refresh the browser during the call.";
                            messageBody += "<br></br>";
                            messageBody += SendingMailUtil.TELE_THX_HTML;
                            messageBody += "</body><html/>";

                            try {
                                boolean mailSend = SendingMailUtil.sendEMail(custEmail, messageBody, SendingMailUtil.SCHEDULE_CALL);
                                if (mailSend) {
                                    logger.info("mail sending was successfull... to Customer" + custEmail);
                                }
                            } catch (Exception ex) {
                                logger.error("Sending Email Error:" + ex.getMessage());
                            }

                        }
                        //callScheduler.loadCustomerMail(custMst.getEmail(), scCall.getScheduleDate());
                        if (null != employeeMstLocal) {
                            String emailTo = employeeMstLocal.getEmail();
                            if (null != emailTo) {
                                messageBody = "<html><body>Dear &nbsp;" + employeeMstLocal.getFirstName();
                                messageBody += ",&nbsp;<br></br>you have a schedule call with our Customer ";
                                messageBody += custMst.getFirstName();
                                messageBody += " &nbsp;at&nbsp;";
                                messageBody += sdf.format(scCall.getScheduleDate());
                                messageBody += "&nbsp;<br></br><br></br>Please login and available 10 minutes before the schedule time.";
//                                messageBody += "<br></br><br></br>";
                                // messageBody+= websiteURL+"/faces/pages/customer/customerScheduleCall.xhtml?scheduleCallId="+scCall.getId();
//                                messageBody += websiteURL + "/videoScheduleCallCustomer?scheduleCallId=" + scCall.getId();
                                messageBody += "<br></br><br></br><br></br>";
                                messageBody += SendingMailUtil.TELE_THX;
                                messageBody += "</body><html/>";

                                try {
                                    boolean mailSend = SendingMailUtil.sendEMail(emailTo, messageBody, SendingMailUtil.SCHEDULE_CALL);
                                    if (mailSend) {
                                        logger.info("mail sending was successfull... to Agent" + emailTo);
                                    }
                                } catch (Exception ex) {
                                    logger.error("Sending Email Error:" + ex.getMessage());
                                }
                            }
                        }
                    }

                    // FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Schedule Request Updated Successfully", "");
                    //FacesContext.getCurrentInstance().addMessage(null, msg);
                } catch (Exception ex) {
                    logger.error("  Error:" + ex.getMessage());
                }

                RequestContext.getCurrentInstance().execute("PF('scheduleSuccess').show();");
//                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Schedule Request Updated Successfully", "");
//                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
            return "";

        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please select Agent Id as you have selected Scheduled status as Scheduled or Completed", "Please Try Again");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "";
        }
    }

    public String back() {
        selectedScheduledCallStatus.clear();
        currentSchedulCallStatusByDateFromEdit();
        return "/pages/schedule/currentScheduleCallStatus.xhtml";
    }

    public String checkEdit() {

        if (this.selectedScheduledCallStatus.size() == 1) {

            return "/pages/schedule/editScheduledCallStatus";
        } else {

            return null;
        }
    }

    public void selectScheduleCallById(Long id) {
        setScheduleCall(callSchedulerService.findAllNonTakenScheduleCallById(id));

    }

    public Date getPresentDate() {
        return presentDate;
    }

    public void setPresentDate(Date presentDate) {
        this.presentDate = presentDate;
    }

    public Integer getCallPrefValue() {
        return callPrefValue;
    }

    public void setCallPrefValue(Integer callPrefValue) {
        this.callPrefValue = callPrefValue;
    }

    public String getCallPref() {
        return callPref;
    }

    public void setCallPref(String callPref) {
        this.callPref = callPref;
    }

    public Long getCustomerFound() {
        return customerFound;
    }

    public void setCustomerFound(Long customerFound) {
        this.customerFound = customerFound;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getC5Code() {
        return c5Code;
    }

    public void setC5Code(String c5Code) {
        this.c5Code = c5Code;
    }

    public String getCustDesc() {
        return custDesc;
    }

    public void setCustDesc(String custDesc) {
        this.custDesc = custDesc;
    }

    public String getCustSeg() {
        return custSeg;
    }

    public void setCustSeg(String custSeg) {
        this.custSeg = custSeg;
    }

    public String getAddrsLine1() {
        return addrsLine1;
    }

    public void setAddrsLine1(String addrsLine1) {
        this.addrsLine1 = addrsLine1;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(Long cellPhone) {
        this.cellPhone = cellPhone;
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

    public LanguageMst getLanguageMstById(Long id) {
        setLanguageMst(languageMstService.findNonDeletedLanguageMstById(id));
        return this.languageMst;

    }

    public ServiceMst getServicesMst() {
        return servicesMst;
    }

    public void setServicesMst(ServiceMst servicesMst) {
        this.servicesMst = servicesMst;
    }

    public LanguageMst getLanguageMst() {
        return languageMst;
    }

    public void setLanguageMst(LanguageMst languageMst) {
        this.languageMst = languageMst;
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

    public ScheduleCall getScheduleCall() {
        return scheduleCall;
    }

    public void setScheduleCall(ScheduleCall scheduleCall) {
        this.scheduleCall = scheduleCall;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Date getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(Date scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public String getExecuteStatus() {
        return executeStatus;
    }

    public void setExecuteStatus(String executeStatus) {
        this.executeStatus = executeStatus;
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

    public Long getLanguage_id() {
        return language_id;
    }

    public void setLanguage_id(Long language_id) {
        this.language_id = language_id;
    }

    public List<ScheduleCall> getListScheduleCall() {
        return listScheduleCall;
    }

    public void setListScheduleCall(List<ScheduleCall> listScheduleCall) {
        this.listScheduleCall = listScheduleCall;
    }

    public List<ScheduleCall> getSelectScheduleCall() {
        return selectScheduleCall;
    }

    public void setSelectScheduleCall(List<ScheduleCall> selectScheduleCall) {
        this.selectScheduleCall = selectScheduleCall;
    }

    public List<ServiceMst> getSelectService() {
        return selectService;
    }

    public void setSelectService(List<ServiceMst> selectService) {
        this.selectService = selectService;
    }

    public List<LanguageMst> getSelectLanguage() {
        return selectLanguage;
    }

    public void setSelectLanguage(List<LanguageMst> selectLanguage) {
        this.selectLanguage = selectLanguage;
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

    public List<CurrentScheduledCallStatus> getCurrentScheduledCallStatusList() {
        return currentScheduledCallStatusList;
    }

    public void setCurrentScheduledCallStatusList(List<CurrentScheduledCallStatus> currentScheduledCallStatusList) {
        this.currentScheduledCallStatusList = currentScheduledCallStatusList;
    }

    public CurrentScheduledCallStatus getCurrentScheduledCallStatus() {
        return currentScheduledCallStatus;
    }

    public void setCurrentScheduledCallStatus(CurrentScheduledCallStatus currentScheduledCallStatus) {
        this.currentScheduledCallStatus = currentScheduledCallStatus;
    }

    public EmployeeMst getEmployeeMst() {
        return employeeMst;
    }

    public void setEmployeeMst(EmployeeMst employeeMst) {
        this.employeeMst = employeeMst;
    }

    public HashSet<EmployeeMst> getListOfScheduledAgents() {
        return listOfScheduledAgents;
    }

    public void setListOfScheduledAgents(HashSet<EmployeeMst> listOfScheduledAgents) {
        this.listOfScheduledAgents = listOfScheduledAgents;
    }

    public EmployeeCallProficiency getEmployeeCallProficiency() {
        return employeeCallProficiency;
    }

    public void setEmployeeCallProficiency(EmployeeCallProficiency employeeCallProficiency) {
        this.employeeCallProficiency = employeeCallProficiency;
    }

    public List<String> getAdditinalStatusList() {
        return additinalStatusList;
    }

    public void setAdditinalStatusList(List<String> additinalStatusList) {
        this.additinalStatusList = additinalStatusList;
    }

    public List<String> getAgentStatusList() {
        return agentStatusList;
    }

    public void setAgentStatusList(List<String> agentStatusList) {
        this.agentStatusList = agentStatusList;
    }

    public List<CurrentScheduledCallStatus> getSelectedScheduledCallStatus() {
        if (selectedScheduledCallStatus.size() == 1) {
            currentScheduledCallStatus = selectedScheduledCallStatus.get(0);

            Date now = new Date();
            if (currentScheduledCallStatus.getScheduleDateTimestamp().before(now)) {
                this.pastRender = true;
                this.currRender = false;
            } else {
                this.pastRender = false;
                this.currRender = true;
            }
        }
        return selectedScheduledCallStatus;

    }

    public void setSelectedScheduledCallStatus(List<CurrentScheduledCallStatus> selectedScheduledCallStatus) {
        this.selectedScheduledCallStatus = selectedScheduledCallStatus;
    }

    public Boolean getPastRender() {
        return pastRender;
    }

    public void setPastRender(Boolean pastRender) {
        this.pastRender = pastRender;
    }

    public Boolean getCurrRender() {
        return currRender;
    }

    public void setCurrRender(Boolean currRender) {
        this.currRender = currRender;
    }

    public boolean isCsvButtonStatus() {
        return csvButtonStatus;
    }

    public void setCsvButtonStatus(boolean csvButtonStatus) {
        this.csvButtonStatus = csvButtonStatus;
    }

    public List<CurrentScheduledCallStatus> getFilteredcurrentScheduledCallStatusList() {
        return filteredcurrentScheduledCallStatusList;
    }

    public void setFilteredcurrentScheduledCallStatusList(List<CurrentScheduledCallStatus> filteredcurrentScheduledCallStatusList) {
        this.filteredcurrentScheduledCallStatusList = filteredcurrentScheduledCallStatusList;
    }

    public String getOutputTextToExport() {
        return outputTextToExport;
    }

    public void setOutputTextToExport(String outputTextToExport) {
        this.outputTextToExport = outputTextToExport;
    }

}
