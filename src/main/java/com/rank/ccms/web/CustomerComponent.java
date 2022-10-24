package com.rank.ccms.web;

import com.rank.ccms.dto.CallEmployeeMap;
import com.rank.ccms.dto.ConferenceAgentsDto;
import com.rank.ccms.dto.CustomerCallDto;
import com.rank.ccms.dto.CustomerDetails;
import com.rank.ccms.dto.FileDownloadDto;
import com.rank.ccms.dto.ScheduleCallDto;
import com.rank.ccms.entities.CallDtl;
import com.rank.ccms.entities.CallFileUploadDtls;
import com.rank.ccms.entities.CallMst;
import com.rank.ccms.entities.CallSettings;
import com.rank.ccms.entities.CategoryMst;
import com.rank.ccms.entities.CustomerDeviceDtl;
import com.rank.ccms.entities.CustomerMst;
import com.rank.ccms.entities.CustomerRmMap;
import com.rank.ccms.entities.DownTime;
import com.rank.ccms.entities.EmployeeCallStatus;
import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.entities.FeedbackDtl;
import com.rank.ccms.entities.FeedbackQueryMst;
import com.rank.ccms.entities.LanguageMst;
import com.rank.ccms.entities.PromotionalVideoMst;
import com.rank.ccms.entities.RmSrmMap;
import com.rank.ccms.entities.ScheduleCall;
import com.rank.ccms.entities.ServiceMst;
import com.rank.ccms.entities.SrmBmMap;
import com.rank.ccms.entities.TenancyEmployeeMap;
import com.rank.ccms.service.AgentFindingService;
import com.rank.ccms.service.CallDtlService;
import com.rank.ccms.service.CallFileUploadDtlsService;
import com.rank.ccms.service.CallMstService;
import com.rank.ccms.service.CallSchedulerService;
import com.rank.ccms.service.CallSettingsService;
import com.rank.ccms.service.CategoryMstService;
import com.rank.ccms.service.CustomerDeviceDtlService;
import com.rank.ccms.service.CustomerMstService;
import com.rank.ccms.service.CustomerRMMapService;
import com.rank.ccms.service.DownTimeService;
import com.rank.ccms.service.EmployeeCallStatusService;
import com.rank.ccms.service.EmployeeMstService;
import com.rank.ccms.service.EmployeeTypeMstService;
import com.rank.ccms.service.FeedbackDtlService;
import com.rank.ccms.service.FeedbackQueryMstService;
import com.rank.ccms.service.LanguageMstService;
import com.rank.ccms.service.PromotionalVideoMstService;
import com.rank.ccms.service.RmSrmMapService;
import com.rank.ccms.service.ServiceMstService;
import com.rank.ccms.service.SrmBmMapService;
import com.rank.ccms.service.TenancyEmployeeMapService;
import com.rank.ccms.util.CallScheduler;
import com.rank.ccms.util.Constants;
import com.rank.ccms.util.CreateJWTToken;
import com.rank.ccms.util.CustomConvert;
import com.rank.ccms.util.CustomerConstant;
import com.rank.ccms.util.DateValidatorRangeCheck;
import com.rank.ccms.util.GenerateCustId;
import com.rank.ccms.util.SendingMailUtil;
import com.rank.ccms.util.SocketMessage;
import com.rank.ccms.util.ThreadSafeSimpleDateFormat;
import com.rank.ccms.rest.response.IncomingCallResponse;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.joda.time.LocalTime;
import org.json.JSONObject;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RateEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;

@Component
@Scope("session")
public class CustomerComponent implements Serializable {

    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CustomerComponent.class);
    private Date startDate;
    private Date endDate;
    private int pollinterval = 1;
    private File file;
    private String downloadedFileName;
    private String downloadCSS;
    private Date presentDate;
    private boolean exportToCsvBtnStatus;
    private String agentName;
    private String custid;
    private String mobileNo;
    private String accountNo;
    private String customerName;
    private String nationality;
    private String nationalId;
    private String outputTextToExport = "";
    private String customerSocket;
    private String videoFileUrl = "";
    private String docTitle = "";
    private String callType = "";

    private List<CategoryMst> categoryMstList;
    private List<ServiceMst> serviceMstList = new ArrayList<>();
    private List<LanguageMst> languageMstList;

    private Long selectedCategoryName;
    private Long selectedServiceName;
    private Long selectedLanguageName;
    private CallMst callMst;
    private List<CustomerDetails> listCustomerDetails;
    private List<CustomerDetails> selectRecords;
    private List<CustomerCallDto> listDroppedCall;
    private List<CustomerCallDto> listAbandonCall;
    private List<CustomerCallDto> listSuccessfulCall;
    private String roomUrl;
    private String custName;

    private ConferenceAgentsDto conferenceAgentsDto;
    private List<ConferenceAgentsDto> conferenceAgentsDtoList;
    private String fileUploadMsg = "";
    private Integer idAgent;
    private UploadedFile upFile;
    private Boolean upload = false;
    private Boolean fileDialogdisplay = false;
    private Boolean fileuploadcompleted = false;
    private Date schDateTime = new Date();
    private Date schEndDateTime = new Date();
    private Date serviceStartTime = new Date();
    private Date serviceEndTime = new Date();
    private Date scheduleStartTimeeee = new Date();
    private Date scheduleEndTimeeee = new Date();
    private Boolean diasableEndCallButton = true;
    private Boolean disableUploadFileButton = true;
    private Boolean disableCallButton = false;
    private Boolean statusOfEndCall = false;
    private Long rating = (long) 0;
    private String feedback;
    private String scheduleText;
    private String serviceTimeText;
    private Boolean showDialogs = true;
    private int retryCallIntValue = 0;
    private Boolean imageuploadrenderer = false;
    private Boolean docuploadrenderer = false;
    private Boolean idcarduploadrenderer = false;
    private Boolean addproofuploadrenderer = false;
    private String roomId;
    private String roomName;
    private String portal;
    List<String> files = new ArrayList<>();
    private boolean showLink = false;
    private String userNameText;
    private boolean iworkImage = false;
    private String filemessage = "";
    private String fileLocationCustomerText;
    private boolean multipleParticipant = false;
    private List<ConferenceAgentsDto> conferenceAgentsDtoListForParticipantCallDtls = new ArrayList<>();
    private List<ConferenceAgentsDto> conferenceAgentsDtoListForParticipantCallDtlsSelection = new ArrayList<>();
    private String timeZone;
    private String customerEmail;
    private String customerPhoneNumber;
    private String scheduleCallId;
    private boolean scheduleStatusChange = false;
    private String promoURL;
    private String uploadedFilePath = "";
    private String fileName = "";
    private String newFileMessage;
    private String latitude;
    private String longitude;
    private String area;
    private Boolean promotionalVideoLink = false;
    private String qualityQuery;
    private String abilityQuery;
    private String recommendQuery;
    private String quality;
    private String ability;
    private String recommend;
    private String downMessage = "";
    private String serviceTimeStart = "";
    private String serviceTimeEnd = "";
    private String downTimeStart = "";
    private String downTimeEnd = "";
    private Boolean fileCaptionRenderer = false;
    private List<FileDownloadDto> fileDownloadDtoList = new ArrayList<>();
    private Boolean existingCustomer = true;
    private String binaryImage;
    private String jwtToken;
    private String customerId;
    private String guestEmail;
    private String urlLink;
    private String uinNo;
    private String selectedCallType = "1";
    private ScheduleModel eventModel = new DefaultScheduleModel();
    private ScheduleEvent event = new DefaultScheduleEvent();
    private Long scheduledCallMstId;
    private List<ScheduleCallDto> scheduledCallDtlDtoList = new ArrayList<>();
    private Boolean renderedCallBtn;
    private Boolean renderedCancelSchdlBtn;
    private Boolean renderedCallBtnForCustomer;
    private Boolean renderedResendLinkBtn;
    private String docTobeOpen;
    private String rmOption = "";

    private String countryLocation = "";
    private String stateLocation = "";
    private String cityLocation = "";

    @Autowired
    private CustomerMstService customerMstService;

    @Autowired
    private CallMstService callMstService;

    @Autowired
    private ServiceMstService serviceMstService;

    @Autowired
    private CategoryMstService categoryMstService;

    @Autowired
    CustomerDeviceDtlService customerDeviceDtlService;

    @Autowired
    private AgentFindingService agentFindingService;

    @Autowired
    private LanguageMstService langMstService;

    @Autowired
    private CallDtlService callDtlService;

    @Autowired
    private EmployeeMstService employeeMstService;

    @Autowired
    private EmployeeTypeMstService employeeTypeMstService;

    @Autowired
    private EmployeeCallStatusService employeeCallStatusService;

    @Autowired
    private TenancyEmployeeMapService tenancyEmployeeMapService;

    @Autowired
    CallSchedulerService callSchedulerService;

    @Autowired
    private FeedbackDtlService feedbackDtlService;

    @Autowired
    CallScheduler callScheduler;

    @Autowired
    FeedbackQueryMstService feedbackQueryMstService;

    @Autowired
    CallSettingsService callSettingsService;

    @Autowired
    DownTimeService downTimeService;

    @Autowired
    PromotionalVideoMstService promotionalVideoMstService;

    @Autowired
    CallFileUploadDtlsService callFileUploadDtlsService;

    @Autowired
    CustomerAccountComponent customerAccountComponent;

    @Autowired
    CustomerRMMapService customerRMMapService;

    @Autowired
    RmSrmMapService rmSrmMapService;

    @Autowired
    SrmBmMapService srmBmMapService;

    public void newCustomerComponent() {

        this.downloadCSS = "display:none";
        this.downloadedFileName = "";
        this.agentName = "";
        this.custid = "";
        this.mobileNo = "";
        this.accountNo = "";
        this.customerName = "";

        setListCustomerDetails(null);
        setExportToCsvBtnStatus(false);
        this.startDate = null;
        this.endDate = null;
        java.util.Date datepresent = new java.util.Date();
        java.sql.Timestamp timestamp1 = new Timestamp(datepresent.getTime());
        java.util.Date date3 = new java.sql.Date(timestamp1.getTime());
        setPresentDate(date3);
    }

    public void clear() {

        this.downloadCSS = "display:none";
        this.downloadedFileName = "";
        this.startDate = null;
        this.endDate = null;
        this.agentName = "";
        this.custid = "";
        this.mobileNo = "";
        this.accountNo = "";
        this.customerName = "";
        if (listCustomerDetails != null) {
            if (!listCustomerDetails.isEmpty()) {
                listCustomerDetails.clear();
            }
        }
        setExportToCsvBtnStatus(false);

    }

    public void loadCustomerRecords(HttpServletRequest request) {
        this.exportToCsvBtnStatus = false;
        String startTimeOutput = "";
        String endTimeOutput = "";
        String agentOutput = "";
        String custIdOutput = "";
        String customerMobileOutput = "";
        String customerAccountOutput = "";
        String customerNameOutput = "";

        SimpleDateFormat dateFormat1 = new SimpleDateFormat("dd.MM.yyyy HH:mm");

        if (startDate != null) {

            startTimeOutput = "Start Date Time : " + dateFormat1.format(startDate);

        }
        if (endDate != null) {

            endTimeOutput = "End Date Time : " + dateFormat1.format(endDate);
        }

        if (!(agentName == null) && !(agentName.trim().equals(""))) {

            agentOutput = "Agent Name : " + agentName;
        }
        if (!(custid == null) && !(custid.trim().equals(""))) {

            custIdOutput = "Customer ID : " + custid;
        }
        if (!(mobileNo == null) && !(mobileNo.trim().equals(""))) {

            customerMobileOutput = "Mobile No : " + mobileNo;
        }
        if (!(accountNo == null) && !(accountNo.trim().equals(""))) {

            customerAccountOutput = "Account No : " + accountNo;
        }
        if (!(customerName == null) && !(customerName.trim().equals(""))) {

            customerNameOutput = "Customer Name : " + customerName;
        }

        boolean valid = true;
        if ((agentName == null || agentName.trim().equals("")) && (custid == null || custid.trim().equals("")) && (mobileNo == null || mobileNo.trim().equals("")) && (accountNo == null || accountNo.trim().equals("")) && (customerName == null || customerName.trim().equals("")) && startDate == null && startDate == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "No search input !", "Search Unsuccessful!!"));
            valid = false;

        }
        if (startDate != null | endDate != null) {
            if (startDate == null && endDate != null || startDate != null && endDate == null || startDate == null && endDate == null) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please select Start Date and End Date,both!", "Search Unsuccessfull!!"));
                valid = false;

                if (listCustomerDetails != null) {
                    if (!listCustomerDetails.isEmpty()) {
                        listCustomerDetails.clear();
                    }
                }
                setExportToCsvBtnStatus(false);
            }
        }
        if (startDate != null && endDate != null) {
            if (startDate.after(endDate)) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Start Date Time is greater than End Date Time!", "Search Unsuccessful!!"));
                valid = false;
            }

        }

        if (valid) {

            try {
                this.downloadCSS = "display:none";
                this.downloadedFileName = "";
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Timestamp startTime2 = null;
                Timestamp endTime2 = null;
                Date endDate1;
                CustomerMst customerMst;
                CallMst callMstLocal;

                CustomerDetails customerDetails1;

                List<CustomerDetails> customerList;
                int count;

                if (this.startDate != null) {
                    startTime2 = new Timestamp(dateFormat.parse(dateFormat.format(this.startDate)).getTime());
                }
                if (this.endDate != null) {
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(this.endDate);
                    cal.add(Calendar.DATE, 1);
                    endDate1 = cal.getTime();
                    endTime2 = new Timestamp(dateFormat.parse(dateFormat.format(endDate1)).getTime());
                }

                List<CallMst> callList;
                if (this.startDate != null && this.endDate != null) {
                    callList = callMstService.findNonDeletedCallByDate(startTime2, endTime2);

                } else {
                    callList = callMstService.findAllNonDeletedCallMsts();

                }

                customerList = new ArrayList();

                if (!callList.isEmpty()) {
                    for (CallMst callList2 : callList) {
                        customerDetails1 = new CustomerDetails();
                        callMstLocal = callList2;
                        String custID = callMstLocal.getCustId();
                        String deviceId = callMstLocal.getDeviceId();
                        Long serviceID = callMstLocal.getServiceId();
                        List<CallMst> callList1;
                        customerMst = customerMstService.findCustomerMstByCustomerId(custID);
                        if (customerMst != null) {
                            customerDetails1.setId(customerMst.getId());
                            customerDetails1.setAccountNo(customerMst.getAccountNo());
                            CallDtl calDtl = findCallDtlfromCallMst(callMstLocal);
                            if (calDtl != null) {
                                EmployeeMst emMst = calDtl.getHandeledById();
                                if (emMst == null) {
                                    customerDetails1.setAgentName("");
                                    customerDetails1.setAgentEcn("");

                                } else {
                                    customerDetails1.setAgentName(emMst.getFirstName());
                                    customerDetails1.setAgentEcn(emMst.getLoginId());

                                }
                            } else {
                                customerDetails1.setAgentName("");
                                customerDetails1.setAgentEcn("");
                            }
                            if (customerMst.getMidName() != null) {
                                customerDetails1.setCustomerName(customerMst.getFirstName() + " " + customerMst.getMidName() + " " + customerMst.getLastName());
                            } else {
                                customerDetails1.setCustomerName(customerMst.getFirstName() + " " + customerMst.getLastName());
                            }
                            if (customerMst.getCustId() != null) {
                                customerDetails1.setCustid(customerMst.getCustId());
                            } else {
                                customerDetails1.setCustid("");
                            }
                            if (customerMst.getCellPhone() != null) {
                                customerDetails1.setCellPhone(customerMst.getCellPhone() + "");
                            } else {
                                customerDetails1.setCellPhone("");
                            }
                            customerDetails1.setCustomerEmail(customerMst.getEmail());

                            try {
                                CategoryMst catgMst = categoryMstService.findCategoryMstById(callMstLocal.getCategoryId());
                                if (catgMst == null) {
                                    customerDetails1.setCustomerSegmentCode(" ");
                                    customerDetails1.setCustomerSegmentDescription(" ");
                                } else {
                                    customerDetails1.setCustomerSegmentCode(catgMst.getCatgCd());
                                    customerDetails1.setCustomerSegmentDescription(catgMst.getCatgName());
                                }
                            } catch (Exception ex) {
                                customerDetails1.setCustomerSegmentCode(" ");
                                customerDetails1.setCustomerSegmentDescription(" ");
                            }
                            customerDetails1.setCustomerLangauage(customerMst.getCustLang1());
                            customerDetails1.setCallMedium(callMstLocal.getCallMedium());

                            if (callMstLocal.getDeviceId() != null && !"".equals(callMstLocal.getDeviceId())) {
                                customerDetails1.setDeviceId(callMst.getDeviceId());
                            } else {
                                customerDetails1.setDeviceId("");
                            }
                            if (callMstLocal.getDeviceName() != null && !"".equals(callMstLocal.getDeviceName())) {
                                customerDetails1.setDeviceName(callMstLocal.getDeviceName());
                            } else {
                                customerDetails1.setDeviceName("");
                            }

                            if (callMstLocal.getDeviceOs() != null && !"".equals(callMstLocal.getDeviceOs())) {
                                customerDetails1.setDeviceOs(callMstLocal.getDeviceOs());
                            } else {
                                customerDetails1.setDeviceOs("");
                            }
                            if (callMstLocal.getDeviceIp() != null && !"".equals(callMstLocal.getDeviceIp())) {
                                customerDetails1.setDeviceIp(callMstLocal.getDeviceIp());
                            } else {
                                customerDetails1.setDeviceIp("");
                            }

                            try {
                                LanguageMst skilMst = langMstService.findAllLanguageMstById(callMstLocal.getLanguageId());
                                if (skilMst == null) {
                                    customerDetails1.setLangCd(" ");
                                    customerDetails1.setLangName(" ");
                                } else {
                                    customerDetails1.setLangCd(skilMst.getLanguageCd());
                                    customerDetails1.setLangName(skilMst.getLanguageName());
                                }
                            } catch (Exception ex) {
                                customerDetails1.setLangCd(" ");
                                customerDetails1.setLangName(" ");
                            }

                            try {
                                ServiceMst servcMst = serviceMstService.findAllServiceMstById(callMstLocal.getServiceId());
                                if (null == servcMst) {
                                    customerDetails1.setServiceCd(" ");
                                    customerDetails1.setServiceName(" ");
                                } else {
                                    customerDetails1.setServiceCd(servcMst.getServiceCd());
                                    customerDetails1.setServiceName(servcMst.getServiceName());
                                }
                            } catch (Exception ex) {
                                customerDetails1.setServiceCd(" ");
                                customerDetails1.setServiceName(" ");
                            }
                            count = 0;
                            callList1 = callMstService.numberOfCalls(startTime2, endTime2, custID, deviceId, serviceID);
                            int abandoncallcount = 0;
                            int droppedcallcount = 0;

                            for (CallMst callList11 : callList1) {
                                boolean succesfulCall = true;
                                if (callList11.getCallStatus().trim().equalsIgnoreCase("ABANDONED")) {
                                    abandoncallcount++;
                                    succesfulCall = false;
                                }
                                if (callList11.getCallStatus().trim().equalsIgnoreCase("No Agent Found")) {
                                    droppedcallcount++;
                                    succesfulCall = false;
                                }
                                if (succesfulCall) {
                                    count++;
                                }
                            }

                            customerDetails1.setNumberOfCalls(count);
                            customerDetails1.setNumberOfAbandonCalls(abandoncallcount);
                            customerDetails1.setNumberOfDroppedCalls(droppedcallcount);
                            boolean addRecord;
                            addRecord = this.agentName.trim().equals("") || this.agentName.trim().equals(customerDetails1.getAgentName());

                            if (addRecord) {
                                addRecord = this.custid.trim().equals("") || this.custid.trim().equals(customerDetails1.getCustid());
                            }
                            if (addRecord) {
                                addRecord = this.mobileNo.trim().equals("") || this.mobileNo.trim().equals(customerDetails1.getCellPhone());
                            }
                            if (addRecord) {
                                addRecord = this.customerName.trim().equals("") || this.customerName.trim().equalsIgnoreCase(customerDetails1.getCustomerName().trim());
                            }
                            if (addRecord) {
                                addRecord = this.accountNo.trim().equals("") || this.accountNo.trim().equals(customerDetails1.getAccountNo());
                            }

                            if (addRecord) {

                                customerList.add(customerDetails1);
                            }
                        }
                    }
                }

                HashMap<String, CustomerDetails> hm = new HashMap();
                for (CustomerDetails customerList1 : customerList) {
                    String serviceID = customerList1.getServiceId() + "";
                    String key = customerList1.getCustid().trim() + "," + customerList1.getDeviceId().trim() + "," + serviceID.trim();
                    hm.put(key, customerList1);
                }

                List<CustomerDetails> valueList = new ArrayList<>(hm.values());
                setListCustomerDetails(valueList);

                if (!customerList.isEmpty()) {
                    setExportToCsvBtnStatus(true);
                    outputTextToExport = startTimeOutput + "   " + endTimeOutput + "  " + agentOutput + "  " + custIdOutput + "  " + customerMobileOutput + "  " + customerAccountOutput + "  " + customerNameOutput;
                }

            } catch (ParseException e) {
                listCustomerDetails.clear();
                logger.error("Error:loadCustomerRecords" + e.getMessage());
            } finally {
                RequestContext.getCurrentInstance().execute("setLoadRender2();");
            }
        }

    }

    public String back(HttpServletRequest request) {
        loadCustomerRecords(request);
        return "/pages/customer/customerList.xhtml";

    }

    public CallDtl findCallDtlfromCallMst(CallMst callmst) {

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

    public void refreshOperations() {
        this.schDateTime = DateValidatorRangeCheck.addTimeInMin(new Date(), 5);
        schEndDateTime = DateValidatorRangeCheck.addTimeInMin(new Date(), 15);
        selectedCallType = "1";
        categoryMstList = new ArrayList<>();
        serviceMstList = new ArrayList<>();
        languageMstList = new ArrayList<>();
        callMst = new CallMst();
        selectedCategoryName = (long) 0;
        selectedLanguageName = (long) 0;
        selectedServiceName = (long) 0;
        this.rating = (long) 1;
        this.feedback = "";
        retryCallIntValue = 0;
        HttpSession session = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession(false);
        String customerIdL = (String) session.getAttribute("ormCustomerID");
        CustomerMst customerMst = customerMstService.findCustomerMstByCustId(customerIdL);
        CustomerDeviceDtl custiddeviceDtl = customerDeviceDtlService.findCustomerDeviceDtlByCustId(customerMst);
        custiddeviceDtl.setLoginInfo(2);
        customerDeviceDtlService.saveCustomerDeviceDtl(custiddeviceDtl, null);

    }

    public void setCustomerAvailable() {
        HttpSession session = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession(false);
        String customerIdL = (String) session.getAttribute("ormCustomerID");
        CustomerMst customerMst = customerMstService.findCustomerMstByCustId(customerIdL);
        CustomerDeviceDtl custiddeviceDtl = customerDeviceDtlService.findCustomerDeviceDtlByCustId(customerMst);
        custiddeviceDtl.setLoginInfo(1);
        customerDeviceDtlService.saveCustomerDeviceDtl(custiddeviceDtl, null);
    }

    public void setCallEndTime(HttpServletRequest request) {
        try {
            CallMst cm = (CallMst) request.getSession().getAttribute("callMstThroughWeb");

            if (cm != null) {
                cm = callMstService.findCallMstById(cm.getId());

                cm.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                callMstService.saveCallMst(cm);
                request.getSession().setAttribute("callMstThroughWeb", null);
            }
        } catch (ParseException ex) {
            logger.error(ex);
        }

    }

    public String setCallEndTimeRM(HttpServletRequest request) {
        try {
            CallMst cm = (CallMst) request.getSession().getAttribute("callMstThroughWeb");

            RequestContext.getCurrentInstance().execute("clearAllRM();");

            if (cm != null) {
                cm = callMstService.findCallMstById(cm.getId());

                cm.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                callMstService.saveCallMst(cm);
                request.getSession().setAttribute("callMstThroughWeb", null);

            }
            RequestContext.getCurrentInstance().execute("closeAllDialog();");

        } catch (ParseException ex) {
            logger.error(ex);
        }
        refreshValues();
        RequestContext.getCurrentInstance().execute("hideLoaderBeforeRefresh();");
        return "/pages/customer/customerDashboard.xhtml?faces-redirect=true";

    }

    public String setCallEndTimeBM(HttpServletRequest request) {
        CallMst cm = (CallMst) request.getSession().getAttribute("callMstThroughWeb");
        RequestContext.getCurrentInstance().execute("clearAllSRM();");
        try {
            if (cm != null) {
                cm = callMstService.findCallMstById(cm.getId());

                cm.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                callMstService.saveCallMst(cm);
                request.getSession().setAttribute("callMstThroughWeb", null);
            }
            RequestContext.getCurrentInstance().execute("closeAllDialog();");
        } catch (ParseException ex) {
            logger.error(ex);
        }
        refreshValues();
        RequestContext.getCurrentInstance().execute("hideLoaderBeforeRefresh();");
        return "/pages/customer/customerDashboard.xhtml?faces-redirect=true";
    }

    public String setCallEndTimeSRM(HttpServletRequest request) {
        CallMst cm = (CallMst) request.getSession().getAttribute("callMstThroughWeb");
        //isSRMend = true;
        RequestContext.getCurrentInstance().execute("clearAllBM();");
        try {
            //RequestContext.getCurrentInstance().execute("PF('initialCallCancelDlgRM').hide();");
            if (cm != null) {
                cm = callMstService.findCallMstById(cm.getId());

                cm.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                callMstService.saveCallMst(cm);
                request.getSession().setAttribute("callMstThroughWeb", null);
            }
            RequestContext.getCurrentInstance().execute("closeAllDialog();");
        } catch (ParseException ex) {
            logger.error(ex);
        }
        refreshValues();
        RequestContext.getCurrentInstance().execute("hideLoaderBeforeRefresh();");
        return "/pages/customer/customerDashboard.xhtml?faces-redirect=true";

    }

    public List<CategoryMst> loadCategoryMst() {
        categoryMstList = categoryMstService.findAllNonDeletedCategoryMsts();
        return categoryMstList;
    }

    public void retryCall(HttpServletRequest request) {

        retryCallIntValue = retryCallIntValue + 1;

        afterSelectingCategory(request);

    }

    public void retryCall1(HttpServletRequest request) {

        retryCallIntValue = retryCallIntValue + 1;

    }

    public void retryCallSRM(HttpServletRequest request) {

        afterSelectingSRMCall(request);

    }

    public void retryCallBM(HttpServletRequest request) {
        afterSelectingBMCall(request);
    }

    @SuppressWarnings("SleepWhileInLoop")
    public void unregisterCustomerCall(HttpServletRequest request) {
        logger.info("unregisterCustomerCall:Call "+selectedCategoryName+","+selectedLanguageName );
        logger.info("retryCallIntValue:"+retryCallIntValue);
        if (null == selectedCategoryName || 0 == selectedCategoryName || null == selectedLanguageName || 0 == selectedLanguageName || null == selectedServiceName || 0 == selectedServiceName) {

        } else {
           
            IncomingCallResponse incomingCallResponse = makeAgentRoutingCall(request);

            if (incomingCallResponse.getUrl().equals("")) {
                for (int i = 0; i < 2; i++) {
                    if (incomingCallResponse.getUrl().equals("")) {
                        incomingCallResponse = makeAgentRoutingCall(request);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                            logger.error(ex);
                        }
                    } else {
                        break;
                    }
                }
            }

            if (incomingCallResponse.getUrl().equals("") && incomingCallResponse.getStatus().equalsIgnoreCase("serverIsOnDownTime")) {
                RequestContext.getCurrentInstance().execute("PF('initialCallCancelDlg').hide();");
                RequestContext.getCurrentInstance().update("downtimedialogidform");
                RequestContext.getCurrentInstance().execute("hideSpinner();PF('downtimeDlg').show();");
            } else if (!incomingCallResponse.getUrl().trim().equals("")) {
                loadFeedbackQuery();
                this.portal = Constants.vidyoportal;
                this.roomUrl = incomingCallResponse.getUrl().substring(incomingCallResponse.getUrl().lastIndexOf("/") + 1);
                this.roomName = incomingCallResponse.getRoomName();
                this.custName = (String) request.getSession().getAttribute("ormCustomerNameSocket");

                request.getSession().setAttribute("onlineAgentFound", incomingCallResponse.getAgentId());
                EmployeeMst emp = employeeMstService.findAllEmployeeByUserId(incomingCallResponse.getAgentId());
                String midName = "";
                if (null != emp.getMidName()) {
                    midName = emp.getMidName();
                }

                this.agentName = emp.getFirstName() + " " + midName + " " + emp.getLastName();
                this.roomId = incomingCallResponse.getUrl().substring(incomingCallResponse.getUrl().lastIndexOf("/"));

                RequestContext.getCurrentInstance().execute("afterJoincall();");

            } else if (retryCallIntValue == 5) {

                RequestContext.getCurrentInstance().execute("checkDialog1();");
                

            } else if (retryCallIntValue < 5) {

                RequestContext.getCurrentInstance().execute("checkDialog();");

                this.schDateTime = new Date();

            }

        }
    }

    public void loadFeedbackQuery() {
        qualityQuery = "How would you rate the quality of the sevice provided by our representative?";
        abilityQuery = "How would you rate the the satisfaction with the resolution provided by our representative?";
        recommendQuery = "Are all your queries get resolved?";
        List<FeedbackQueryMst> listFeedbackQueryMst = feedbackQueryMstService.findAllActiveFeedbackQueryMst();
        int index = 0;
        for (FeedbackQueryMst lQueryMst : listFeedbackQueryMst) {
            switch (index) {
                case 0:
                    qualityQuery = lQueryMst.getFeedbackQuery();
                    break;
                case 1:
                    abilityQuery = lQueryMst.getFeedbackQuery();
                    break;
                case 2:
                    recommendQuery = lQueryMst.getFeedbackQuery();
                    break;
                default:
                    break;
            }
            index++;
        }

    }

    public List<ServiceMst> loadServiceMst() {
        serviceMstList = serviceMstService.findAllNonDeletedServiceMsts();
        return serviceMstList;
    }

    public List<ServiceMst> loadServiceMstForExistingCust() {
        List<ServiceMst> servMstList = serviceMstService.findAllNonDeletedServiceMsts();
        serviceMstList.clear();
        for (ServiceMst svm : servMstList) {
            if (!svm.getServiceCd().equals("CASA")) {
                serviceMstList.add(svm);
            }
        }
        return serviceMstList;
    }

    public List<LanguageMst> loadLanguageMst() {
        languageMstList = langMstService.findAllNonDeletedLanguageMsts();
        return languageMstList;
    }

    public void submitCatagory() {
        if (rmOption.equalsIgnoreCase("yes")) {
            RequestContext.getCurrentInstance().execute("checkRM()");
        } else {
            RequestContext.getCurrentInstance().execute("check()");
        }
    }

    public void afterSelectingCategory(HttpServletRequest request) {
        this.setExistingCustomer(true);

        CategoryMst cat = categoryMstService.findCategoryByCategoryCode("UGR");
        selectedCategoryName = cat.getId();
        LanguageMst langMst = langMstService.findLanguageMstByLanguageCode("ENG");
        selectedLanguageName = langMst.getId();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            java.util.logging.Logger.getLogger(CustomerComponent.class.getName()).log(Level.SEVERE, null, ex);
        }

        logger.info("In afterSelectingCategory" + rmOption);
        IncomingCallResponse incomingCallResponse;
        if (rmOption.equalsIgnoreCase("yes")) {
            incomingCallResponse = makeRMCall(request);
            if (incomingCallResponse.getUrl().equals("")) {
                for (int i = 0; i < 6; i++) {
                    if (incomingCallResponse.getUrl().equals("")) {
                        incomingCallResponse = makeRMCall(request);
                    } else {
                        break;
                    }
                }
            }
        } else {
            incomingCallResponse = makeAgentRoutingCall(request);
            if (incomingCallResponse.getUrl().equals("")) {
                for (int i = 0; i < 6; i++) {
                    if (incomingCallResponse.getUrl().equals("")) {
                        incomingCallResponse = makeAgentRoutingCall(request);
                    } else {
                        break;
                    }
                }
            }
        }

        logger.info("communication mode" + callType);

        try {

            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            java.util.logging.Logger.getLogger(CustomerComponent.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (incomingCallResponse.getUrl().equals("") && incomingCallResponse.getStatus().contains("serverIsOnDownTime")) {
            RequestContext.getCurrentInstance().execute("PF('initialCallCancelDlgRM').hide();PF('initialCallCancelDlg').hide();");
            RequestContext.getCurrentInstance().execute("PF('downtimeDlg').show();");
        } else if (incomingCallResponse.getUrl().equals("") && incomingCallResponse.getStatus().contains("serverIsOnServiceTime")) {
            RequestContext.getCurrentInstance().execute("PF('initialCallCancelDlgRM').hide();PF('initialCallCancelDlg').hide();");
            RequestContext.getCurrentInstance().execute("PF('downtimeDlg').show();");
        } else if (!incomingCallResponse.getUrl().equals("")) {
            RequestContext.getCurrentInstance().execute("PF('initialCallCancelDlgRM').hide();PF('initialCallCancelDlg').hide();");
            this.roomId = incomingCallResponse.getUrl();
            this.portal = Constants.vidyoportal;
            this.roomUrl = incomingCallResponse.getUrl().substring(incomingCallResponse.getUrl().lastIndexOf("/") + 1);
            this.roomName = incomingCallResponse.getRoomName();

            this.custName = (String) request.getSession().getAttribute("ormCustomerName") + "~" + (String) request.getSession().getAttribute("ormCustomerID");
            request.getSession().setAttribute("onlineAgentFound", incomingCallResponse.getAgentId());
            EmployeeMst emp = employeeMstService.findAllEmployeeByUserId(incomingCallResponse.getAgentId());
            String midName = "";
            if (null != emp.getMidName()) {
                midName = emp.getMidName();
            }

            this.agentName = emp.getFirstName() + " " + midName + " " + emp.getLastName();
            Long CallMstId = Long.parseLong(incomingCallResponse.getCallId());
            CallMst cm = callMstService.findCallMstById(CallMstId);
            if (null != cm) {
                request.getSession().setAttribute("callMstThroughWeb", cm);

                if (rmOption.equalsIgnoreCase("yes")) {
                 
                    RequestContext.getCurrentInstance().execute("openRMCloseDialog();");
                } else {
                
                    RequestContext.getCurrentInstance().execute("openCloseDialog();");
                }

            }

            statusOfEndCall = false;

        } else if (retryCallIntValue == 5) {

            RequestContext.getCurrentInstance().execute("checkDialog1();");

        } else if (retryCallIntValue < 5) {
            if (rmOption.equalsIgnoreCase("yes")) {
                RequestContext.getCurrentInstance().execute("checkDialogRM();");

            } else {
                RequestContext.getCurrentInstance().execute("checkDialog();");
            }

            this.schDateTime = new Date();

        }

    }

    public void afterRoutingToAgentInScheduleCall(HttpServletRequest request) {

        logger.info("afterRoutingToAgentInScheduleCall..........");
        this.setExistingCustomer(true);

        CategoryMst cat = categoryMstService.findCategoryByCategoryCode("UGR");
        selectedCategoryName = cat.getId();
        LanguageMst langMst = langMstService.findLanguageMstByLanguageCode("ENG");
        selectedLanguageName = langMst.getId();

        IncomingCallResponse incomingCallResponse = makeAgentRoutingCall(request);

        if (incomingCallResponse.getUrl().equals("")) {
            for (int i = 0; i < 6; i++) {
                if (incomingCallResponse.getUrl().equals("")) {
                    incomingCallResponse = makeAgentRoutingCall(request);
                } else {
                    break;
                }
            }
        }

        if (incomingCallResponse.getUrl().equals("") && incomingCallResponse.getStatus().contains("serverIsOnDownTime")) {
            RequestContext.getCurrentInstance().execute("PF('initialCallCancelDlg').hide();");
            RequestContext.getCurrentInstance().execute("PF('downtimeDlg').show();");
        } else if (incomingCallResponse.getUrl().equals("") && incomingCallResponse.getStatus().contains("serverIsOnServiceTime")) {
            RequestContext.getCurrentInstance().execute("PF('initialCallCancelDlg').hide();");
            RequestContext.getCurrentInstance().execute("PF('downtimeDlg').show();");
        } else if (!incomingCallResponse.getUrl().equals("")) {
            RequestContext.getCurrentInstance().execute("PF('initialCallCancelDlg').hide();");
            this.roomId = incomingCallResponse.getUrl().substring(incomingCallResponse.getUrl().lastIndexOf("/") + 1);
            this.portal = Constants.vidyoportal;
            this.roomUrl = incomingCallResponse.getUrl().substring(incomingCallResponse.getUrl().lastIndexOf("/") + 1);
            this.roomName = incomingCallResponse.getRoomName();

            this.custName = (String) request.getSession().getAttribute("ormCustomerName") + "~" + (String) request.getSession().getAttribute("ormCustomerID");
            request.getSession().setAttribute("onlineAgentFound", incomingCallResponse.getAgentId());
            EmployeeMst emp = employeeMstService.findAllEmployeeByUserId(incomingCallResponse.getAgentId());
            String midName = "";
            if (null != emp.getMidName()) {
                midName = emp.getMidName();
            }

            this.agentName = emp.getFirstName() + " " + midName + " " + emp.getLastName();
            Long CallMstId = Long.parseLong(incomingCallResponse.getCallId());
            CallMst cm = callMstService.findCallMstById(CallMstId);
            if (null != cm) {
                request.getSession().setAttribute("callMstThroughWeb", cm);

            }

            statusOfEndCall = false;
           
            RequestContext.getCurrentInstance().execute("PF('initialCallCancelDlg').hide();PF('exitSysVar').show();hideSpinner();");
            RequestContext.getCurrentInstance().execute("document.getElementById('burgeInForm').style.display='block'");
        } else if (retryCallIntValue == 5) {

            RequestContext.getCurrentInstance().execute("checkDialog1();");

        } else if (retryCallIntValue < 5) {
            RequestContext.getCurrentInstance().execute("checkDialog();");

            this.schDateTime = new Date();

        }

    }

    public void afterSelectingCallType(HttpServletRequest request) {
        logger.info("afterSelectingCallType.........." + selectedCallType);
        //request.getSession().setAttribute("scheduleCallLocal", null);
        CustomerMst cm = (CustomerMst) request.getSession().getAttribute("ormCustomerMaster");
        if (cm.getIsRegistered()) {
            serviceTimeText = "Please Schedule between " + Constants.RM1_Service_Start_Time + "-" + Constants.RM1_Service_End_Time;
        } 
        callType = "";

        this.schDateTime = DateValidatorRangeCheck.addTimeInMin(new Date(), 5);
        schEndDateTime = DateValidatorRangeCheck.addTimeInMin(new Date(), 15);
        logger.info("schDateTime:" + schDateTime + "schEndDateTime:" + schEndDateTime);
    }

    public void onReturnDashBoard() {
        logger.info("onReturnDashBoard.........." + selectedCallType);
        this.schDateTime = DateValidatorRangeCheck.addTimeInMin(new Date(), 5);
        schEndDateTime = DateValidatorRangeCheck.addTimeInMin(new Date(), 15);
        selectedCallType = "1";
    }

    public void afterSelectingSRMCall(HttpServletRequest request) {
        this.setExistingCustomer(true);

        CategoryMst cat = categoryMstService.findCategoryByCategoryCode("UGR");
        selectedCategoryName = cat.getId();
        LanguageMst langMst = langMstService.findLanguageMstByLanguageCode("ENG");
        selectedLanguageName = langMst.getId();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            java.util.logging.Logger.getLogger(CustomerComponent.class.getName()).log(Level.SEVERE, null, ex);
        }
        logger.info("In afterSelectingSRMCall");
        IncomingCallResponse incomingCallResponse = makeSRMCall(request);
        //IncomingCallResponse incomingCallResponse = makeAgentRoutingCall(request);

        if (incomingCallResponse.getUrl().equals("")) {
            for (int i = 0; i < 6; i++) {
                if (incomingCallResponse.getUrl().equals("")) {
                    incomingCallResponse = makeSRMCall(request);
                } else {
                    break;
                }
            }
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            java.util.logging.Logger.getLogger(CustomerComponent.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (incomingCallResponse.getUrl().equals("") && incomingCallResponse.getStatus().contains("serverIsOnDownTime")) {
            RequestContext.getCurrentInstance().execute("PF('initialCallCancelDlgSRM').hide();");
            RequestContext.getCurrentInstance().execute("PF('downtimeDlg').show();");
        } else if (incomingCallResponse.getUrl().equals("") && incomingCallResponse.getStatus().contains("serverIsOnServiceTime")) {
            RequestContext.getCurrentInstance().execute("PF('initialCallCancelDlgSRM').hide();");
            RequestContext.getCurrentInstance().execute("PF('downtimeDlg').show();");
        } else if (!incomingCallResponse.getUrl().equals("")) {

            RequestContext.getCurrentInstance().execute("PF('initialCallCancelDlgSRM').hide();");
           
            this.roomId = incomingCallResponse.getUrl().substring(incomingCallResponse.getUrl().lastIndexOf("/") + 1);
            this.portal = Constants.vidyoportal;
            this.roomUrl = incomingCallResponse.getUrl().substring(incomingCallResponse.getUrl().lastIndexOf("/") + 1);
            this.roomName = incomingCallResponse.getRoomName();

            logger.info("roomUrl..................... : " + roomUrl);

            this.custName = (String) request.getSession().getAttribute("ormCustomerName") + "~" + (String) request.getSession().getAttribute("ormCustomerID");
            request.getSession().setAttribute("onlineAgentFound", incomingCallResponse.getAgentId());
            EmployeeMst emp = employeeMstService.findAllEmployeeByUserId(incomingCallResponse.getAgentId());
            String midName = "";
            if (null != emp.getMidName()) {
                midName = emp.getMidName();
            }

            this.agentName = emp.getFirstName() + " " + midName + " " + emp.getLastName();
            Long CallMstId = Long.parseLong(incomingCallResponse.getCallId());
            CallMst cm = callMstService.findCallMstById(CallMstId);
            if (null != cm) {
                request.getSession().setAttribute("callMstThroughWeb", cm);
             
                RequestContext.getCurrentInstance().execute("openSRMCloseDialog();");

            }
            statusOfEndCall = false;

        } else if (retryCallIntValue == 5) {

            RequestContext.getCurrentInstance().execute("checkDialog1();");

        } else if (retryCallIntValue < 5) {
            RequestContext.getCurrentInstance().execute("checkDialogSRM();");

            this.schDateTime = new Date();

        }

    }

    public void afterSelectingBMCall(HttpServletRequest request) {
        this.setExistingCustomer(true);

        CategoryMst cat = categoryMstService.findCategoryByCategoryCode("UGR");
        selectedCategoryName = cat.getId();
        LanguageMst langMst = langMstService.findLanguageMstByLanguageCode("ENG");
        selectedLanguageName = langMst.getId();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            java.util.logging.Logger.getLogger(CustomerComponent.class.getName()).log(Level.SEVERE, null, ex);
        }
        logger.info("In afterSelectingBMCall");
        IncomingCallResponse incomingCallResponse = makeBMCall(request);
        //IncomingCallResponse incomingCallResponse = makeAgentRoutingCall(request);

        if (incomingCallResponse.getUrl().equals("")) {
            for (int i = 0; i < 6; i++) {
                if (incomingCallResponse.getUrl().equals("")) {
                    incomingCallResponse = makeBMCall(request);
                } else {
                    break;
                }
            }
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            java.util.logging.Logger.getLogger(CustomerComponent.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (incomingCallResponse.getUrl().equals("") && incomingCallResponse.getStatus().contains("serverIsOnDownTime")) {
            RequestContext.getCurrentInstance().execute("PF('initialCallCancelDlgBM').hide();");
            RequestContext.getCurrentInstance().execute("PF('downtimeDlg').show();");
        } else if (incomingCallResponse.getUrl().equals("") && incomingCallResponse.getStatus().contains("serverIsOnServiceTime")) {
            RequestContext.getCurrentInstance().execute("PF('initialCallCancelDlgBM').hide();");
            RequestContext.getCurrentInstance().execute("PF('downtimeDlg').show();");
        } else if (!incomingCallResponse.getUrl().equals("")) {

            RequestContext.getCurrentInstance().execute("PF('initialCallCancelDlgBM').hide();");
            this.roomId = incomingCallResponse.getUrl().substring(incomingCallResponse.getUrl().lastIndexOf("/") + 1);
            this.portal = Constants.vidyoportal;
            this.roomUrl = incomingCallResponse.getUrl().substring(incomingCallResponse.getUrl().lastIndexOf("/") + 1);
            this.roomName = incomingCallResponse.getRoomName();

            logger.info("roomUrl..................... : " + roomUrl);

            this.custName = (String) request.getSession().getAttribute("ormCustomerName") + "~" + (String) request.getSession().getAttribute("ormCustomerID");
            request.getSession().setAttribute("onlineAgentFound", incomingCallResponse.getAgentId());
            EmployeeMst emp = employeeMstService.findAllEmployeeByUserId(incomingCallResponse.getAgentId());
            String midName = "";
            if (null != emp.getMidName()) {
                midName = emp.getMidName();
            }

            this.agentName = emp.getFirstName() + " " + midName + " " + emp.getLastName();
            Long CallMstId = Long.parseLong(incomingCallResponse.getCallId());
            CallMst cm = callMstService.findCallMstById(CallMstId);
            if (null != cm) {
                request.getSession().setAttribute("callMstThroughWeb", cm);
               
                RequestContext.getCurrentInstance().execute("openBMCloseDialog();");

            }
            statusOfEndCall = false;

        } else if (retryCallIntValue == 5) {

            RequestContext.getCurrentInstance().execute("checkDialog1();");

        } else if (retryCallIntValue < 5) {
            RequestContext.getCurrentInstance().execute("checkDialogBM();");

            this.schDateTime = new Date();

        }

    }

    public IncomingCallResponse makeAgentRoutingCall(HttpServletRequest request) {

        IncomingCallResponse incomingCallResponse = null;

        CustomerMst customerMst_local = (CustomerMst) request.getSession().getAttribute("ormCustomerMaster");
        boolean newCall = true;
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        String weekDay = "";
        switch (day) {
            case Calendar.SUNDAY:
                weekDay = "Sunday";
                break;
            case Calendar.MONDAY:
                weekDay = "Monday";
                break;
            case Calendar.TUESDAY:
                weekDay = "Tuesday";
                break;
            case Calendar.WEDNESDAY:
                weekDay = "Wednesday";
                break;
            case Calendar.THURSDAY:
                weekDay = "Thursday";
                break;
            case Calendar.FRIDAY:
                weekDay = "Friday";
                break;
            case Calendar.SATURDAY:
                weekDay = "Saturday";
                break;

        }

        boolean indownTime;
        boolean inservicedownTime = false;

        indownTime = whetherInDownTime();

        if (!indownTime) {
            inservicedownTime = whetherInServiceDownTime();
        }
        if (indownTime) {
            downMessage = "Currently System is in downtime from " + this.downTimeStart + " to " + this.downTimeEnd;
        } else if (inservicedownTime) {
            if ((this.getServiceTimeStart() != null && !this.getServiceTimeStart().trim().equals("")) && (this.getServiceTimeEnd() != null && !this.getServiceTimeEnd().trim().equals(""))) {
                downMessage = "We are unavailable to attend to you via this channel at this time. We are only available on " + weekDay + " between " + this.getServiceTimeStart() + " to " + this.getServiceTimeEnd();

            } else {

                downMessage = "We are unavailable to attend to you via this channel at this time on this week day ";
            }
        }

        try {
            if (customerMst_local != null && null != customerMst_local.getCustId()) {

                if (!indownTime && !inservicedownTime) {

                    CustomerDeviceDtl cdd = customerDeviceDtlService.findCustomerDeviceDtlByCustId(customerMst_local);

                    if (null != cdd) {
                        if (null != cdd.getId()) {
                            cdd.setAudioVideo(1);

                            customerDeviceDtlService.saveCustomerDeviceDtl(cdd, null);
                        }
                    }

                    CallMst callMstForRepeatcall = null;
                    List<CallMst> callMstForRepeatcallList = callMstService.findAllNotRecevedCallsByCust(customerMst_local.getCustId());
                    if (!callMstForRepeatcallList.isEmpty()) {
                        callMstForRepeatcall = callMstForRepeatcallList.get(0);
                        newCall = false;
                    }

                    synchronized (this) {
                        CategoryMst categoryMst = categoryMstService.findCategoryByCategoryCode("UGR");
                        selectedCategoryName = categoryMst.getId();
                        LanguageMst languageMst = langMstService.findLanguageMstByLanguageCode("ENG");
                        selectedLanguageName = languageMst.getId();

                        ServiceMst serviceMst = serviceMstService.findNonDeletedServiceMstById(selectedServiceName);
                        if (newCall) {
                            List<CallMst> listOpenCalls = callMstService.findAllOnGoingCallsByCust(customerMst_local.getCustId());

                            if (!listOpenCalls.isEmpty()) {
                                for (CallMst cmst : listOpenCalls) {
                                    cmst.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                                    cmst = callMstService.saveCallMst(cmst);
                                }
                            }

                            CallMst local_callMst = new CallMst();
                            local_callMst.setCallStatus("Initialize");
                            local_callMst.setCallType("Incoming Call");
                            local_callMst.setDeviceBrand(null);
                            local_callMst.setDeviceOs(null);
                            local_callMst.setiMEIno(null);
                            local_callMst.setDeviceId(null);
                            local_callMst.setDeviceIp(null);
                            local_callMst.setStaticIp(null);
                            local_callMst.setDeviceName(null);
                            local_callMst.setCustomerId(customerMst_local);
                            local_callMst.setCustId(customerMst_local.getCustId());

                            local_callMst.setCallLocation(getCityLocation() + "," + getCountryLocation());
                            local_callMst.setCallLatitude(getLatitude());
                            local_callMst.setCallLongitude(getLongitude());

                            local_callMst.setServiceId(serviceMst.getId());
                            local_callMst.setCategoryId(categoryMst.getId());
                            local_callMst.setLanguageId(languageMst.getId());
                            local_callMst.setCallOption(callType);
                            local_callMst.setCallMedium("WEB");
                            local_callMst.setActiveFlg(true);
                            local_callMst.setDeleteFlg(false);
                            local_callMst.setStartTime(CustomConvert.javaDateToTimeStamp(new Date(System.currentTimeMillis())));
                            local_callMst.setCustomerRequestTime(CustomConvert.javaDateToTimeStamp(new Date()));

                            local_callMst = callMstService.saveCallMst(local_callMst);
                            callMst = callMstService.findCallMstById(local_callMst.getId());
                        } else if (callMstForRepeatcall != null) {
                            callMstForRepeatcall.setServiceId(serviceMst.getId());
                            callMstForRepeatcall.setCategoryId(categoryMst.getId());
                            callMstForRepeatcall.setLanguageId(languageMst.getId());
                            callMstForRepeatcall.setStartTime(CustomConvert.javaDateToTimeStamp(new Date()));
                            callMst = callMstService.saveCallMst(callMstForRepeatcall);
                        }

                        if (!employeeCallStatusService.findFreeOnlineAgents().isEmpty()) {
                            Long callId;
                            callId = callMst.getId();
                            incomingCallResponse = new IncomingCallResponse("", "", callId.toString(), "Not Initiated", categoryMst.getCatgName(), languageMst.getLanguageName(), serviceMst.getServiceName(), customerMst_local.getCustId());

                            CallMst callMaster;

                            CategoryMst categoryMstLocal = categoryMstService.findCategoryByCategoryName(incomingCallResponse.getCategory());
                            LanguageMst languageMstLocal = langMstService.findLanguageMstByLanguageName(incomingCallResponse.getLanguage());
                            ServiceMst serviceMstLocal = serviceMstService.findServiceByServiceName(incomingCallResponse.getService());
                            callMaster = callMstService.findNonDeletedCallMstById(Long.parseLong(incomingCallResponse.getCallId()));

                            List<Object> L_E_Mst = Arrays.asList(agentFindingService.findAgents(categoryMstLocal.getCatgCd(), serviceMstLocal.getServiceCd(), languageMstLocal.getLanguageCd()).toArray());
                            EmployeeMst em;

                            if (!L_E_Mst.isEmpty()) {
                                em = (EmployeeMst) L_E_Mst.get(0);

                                EmployeeCallStatus empCallStatus = new EmployeeCallStatus();
                                List<EmployeeCallStatus> empClStatusList = employeeCallStatusService.findEmployeeCallStatusByEmpId(em);

                                if (!empClStatusList.isEmpty()) {
                                    for (EmployeeCallStatus empstatus : empClStatusList) {
                                        empCallStatus = empstatus;
                                    }
                                    int count = empCallStatus.getCallCount();
                                    empCallStatus.setCallCount(count + 1);
                                    empCallStatus.setStatus(false);
                                    employeeCallStatusService.saveEmployeeCallStatus(empCallStatus);
                                } else {
                                    empCallStatus.setEmpId(em);
                                    empCallStatus.setCallCount(1);
                                    empCallStatus.setStatus(false);
                                    employeeCallStatusService.saveEmployeeCallStatus(empCallStatus);
                                }
                                // status busy set

                                List<TenancyEmployeeMap> tenancyEmployeeMaplist = tenancyEmployeeMapService.findVidyoTenantUrlByEmpId(em.getId());
                                List<Long> unsortList = new ArrayList<>();
                                for (TenancyEmployeeMap tenancyEmployeeMaplist1 : tenancyEmployeeMaplist) {
                                    unsortList.add(tenancyEmployeeMaplist1.getId());
                                }
                                Collections.sort(unsortList);

                                TenancyEmployeeMap tenancyEmployeeMap = tenancyEmployeeMapService.findById(unsortList.get(unsortList.size() - 1));

                                String roomLink = tenancyEmployeeMap.getRoomLink();
                                incomingCallResponse.setUrl(roomLink);
                                incomingCallResponse.setRoomName(tenancyEmployeeMap.getRoomName());
                                incomingCallResponse.setAgentId(em.getLoginId());
                                incomingCallResponse.setStatus("Initiated" + "||" + em.getFirstName() + " " + em.getLastName());
                                incomingCallResponse.setCustomerId(customerMst_local.getCustId());

                                Set<String> custIdSet = new TreeSet<>();
                                custIdSet.add(customerMst_local.getCustId());
                                CallScheduler.customerCallDetailsMap.put(callId.toString(), custIdSet);

                                CallEmployeeMap l_CallEmpMap = new CallEmployeeMap();
                                l_CallEmpMap.setCallId(callMaster.getId());
                                l_CallEmpMap.setEmployeeId(em.getId());
                                l_CallEmpMap.setCallType("Normal");
                                l_CallEmpMap.setCustId(callMaster.getCustId());
                                l_CallEmpMap.setRoomLink(roomLink);

                                callMaster.setRoutingCallTime(CustomConvert.javaDateToTimeStamp(new Date()));
                                callMaster.setRoomName(roomLink);
                                callMstService.saveCallMst(callMaster);
                                try {
                                    SocketMessage.send(callScheduler.getAdminSocket(), em.getLoginId(), "incomingCall#" + callMaster.getId() + "#" + callMaster.getCustId() + "#" + callMaster.getCallOption());
                                } catch (Exception ex) {
                                    logger.error(ex);
                                }
                                CallScheduler.listCallEmp.add(l_CallEmpMap);

                                selectPromoVideo();

                            } else {
                                logger.info("No Agent Found..........");
                                callMaster = callMstService.findNonDeletedCallMstById(Long.parseLong(incomingCallResponse.getCallId()));
                                callMaster.setCallStatus("No Agent Found");
                                callMaster.setStartTime(CustomConvert.javaDateToTimeStamp(new Date()));
                                callMstService.saveCallMst(callMaster);

                            }

                        } else {
                            logger.info("All Agents are busy or no agent is available..........");
                            Long callId = callMst.getId();
                            CallMst callMaster = callMstService.findNonDeletedCallMstById(callId);
                            callMaster.setCallStatus("No Agent Found");
                            callMaster.setStartTime(CustomConvert.javaDateToTimeStamp(new Date()));
                            //callMaster.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                            callMstService.saveCallMst(callMaster);
                            incomingCallResponse = new IncomingCallResponse("", "", callId.toString(), "Not Initiated", categoryMst.getCatgDesc(), languageMst.getLanguageName(), serviceMst.getServiceName(), customerMst_local.getCustId());

                        }
                    }
                } else {
                    logger.info("Else...............serverIsOnDownTime....");
                    incomingCallResponse = new IncomingCallResponse("", "", "", "serverIsOnDownTime", "", "", "", "");

                }
            }

        } catch (ParseException | NumberFormatException e) {
            e.printStackTrace(System.err);
        }
        if (null != incomingCallResponse && !incomingCallResponse.getCallId().equals("")) {
            Long CallMstId = Long.parseLong(incomingCallResponse.getCallId());
            CallMst cm = callMstService.findCallMstById(CallMstId);
            if (null != cm) {
                request.getSession().setAttribute("callMstThroughWeb", cm);
            }
        }
        return incomingCallResponse;

    }

    public void resetOnLoad() {
        this.customerName = "";
        this.customerEmail = "";
        this.customerPhoneNumber = "";
        this.nationality = "";
    }

    public void selectPromoVideo() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        try {
             PromotionalVideoMst lPromotionalVideoMst = promotionalVideoMstService.findSelectedPromotionalVideo();
           
            if (lPromotionalVideoMst == null) {
                String websiteURL = request.getRequestURL().substring(0, request.getRequestURL().indexOf("/", 9));
                String videoURL = websiteURL + request.getContextPath() + "/promotional/promo.mp4";
                
                videoFileUrl = videoURL;
            } else {
                ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
                videoFileUrl = promotionalVideoMstService.getVideoFileUrl(lPromotionalVideoMst.getFileUrl(), request, ctx);
            }
            logger.info("Promo video Url " + videoFileUrl);

        } catch (Exception e) {
            String websiteURL = request.getRequestURL().substring(0, request.getRequestURL().indexOf("/", 9));
            String videoURL = websiteURL + request.getContextPath() + "/promotional/promo.mp4";
            videoFileUrl = videoURL;
            logger.info("error " + e.getMessage());
        }
    }

    public boolean whetherInDownTime() {
        boolean check = false;
        this.setDownTimeStart("");
        this.setDownTimeEnd("");
        ThreadSafeSimpleDateFormat sdf = new ThreadSafeSimpleDateFormat("dd-MM-yyyy HH:mm");
        List<DownTime> downTimeList = downTimeService.findAllNonDeletedDownTime();
        for (DownTime downTime : downTimeList) {

            if (new Date().before(downTime.getEndTime()) && new Date().after(downTime.getStartTime())) {
                logger.info("downtime in between.....");
                String stringStart = sdf.format(downTime.getStartTime());
                int startColonPos = stringStart.indexOf(":");
                int startBlankPos = stringStart.indexOf(" ");
                String dateStart = stringStart.substring(0, startBlankPos);
                int startHour = Integer.parseInt(stringStart.substring(startBlankPos + 1, startColonPos));
                int startMin = Integer.parseInt(stringStart.substring(startColonPos + 1));
                if (startHour > 12 && startHour < 24) {
                    if ((startHour - 12) < 10) {
                        if (startMin < 10) {
                            stringStart = "0" + String.valueOf(startHour - 12) + ":" + "0" + startMin;
                        } else {
                            stringStart = String.valueOf(startHour - 12) + ":" + startMin;
                        }
                    } else if (startMin < 10) {
                        stringStart = String.valueOf(startHour - 12) + ":" + "0" + startMin;
                    } else {
                        stringStart = String.valueOf(startHour - 12) + ":" + startMin;
                    }
                    this.setDownTimeStart(dateStart + " " + stringStart + " PM");
                } else {
                    this.setDownTimeStart(stringStart + " AM");
                }

                String stringEnd = sdf.format(downTime.getEndTime());
                int endColonPos = stringEnd.indexOf(":");
                int endBlankPos = stringEnd.indexOf(" ");
                String dateEnd = stringEnd.substring(0, endBlankPos);
                int endHour = Integer.parseInt(stringEnd.substring(endBlankPos + 1, endColonPos));
                int endMin = Integer.parseInt(stringEnd.substring(endColonPos + 1));
                if (endHour > 12 && endHour < 24) {
                    if ((endHour - 12) < 10) {
                        if (endMin < 10) {
                            stringEnd = "0" + String.valueOf(endHour - 12) + ":" + "0" + endMin;
                        } else {
                            stringEnd = String.valueOf(endHour - 12) + ":" + endMin;
                        }
                    } else if (endMin < 10) {
                        stringEnd = String.valueOf(endHour - 12) + ":" + "0" + endMin;
                    } else {
                        stringEnd = String.valueOf(endHour - 12) + ":" + endMin;
                    }
                    this.setDownTimeEnd(dateEnd + " " + stringEnd + " PM");
                } else {
                    this.setDownTimeEnd(stringEnd + " AM");
                }
                check = true;
                break;
            }
        }
        return check;
    }

    public boolean whetherInServiceDownTime() {

        boolean check = false;
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        String weekDay = "";
        switch (day) {
            case Calendar.SUNDAY:
                weekDay = "Sunday";
                break;
            case Calendar.MONDAY:
                weekDay = "Monday";
                break;
            case Calendar.TUESDAY:
                weekDay = "Tuesday";
                break;
            case Calendar.WEDNESDAY:
                weekDay = "Wednesday";
                break;
            case Calendar.THURSDAY:
                weekDay = "Thursday";
                break;
            case Calendar.FRIDAY:
                weekDay = "Friday";
                break;
            case Calendar.SATURDAY:
                weekDay = "Saturday";

        }

        CallSettings callSettings = callSettingsService.findCallSettingsByWeekDay(weekDay);
        if (null != callSettings) {

            ThreadSafeSimpleDateFormat sdf = new ThreadSafeSimpleDateFormat("HH:mm");
            LocalTime now = LocalTime.now();

            if (callSettings.getServiceStartTime() != null && callSettings.getServiceEndTime() != null) {
                String stringStart = sdf.format(callSettings.getServiceStartTime());
                int startColonPos = stringStart.indexOf(":");
                int startHour = Integer.parseInt(stringStart.substring(0, startColonPos));
                int startMin = Integer.parseInt(stringStart.substring(startColonPos + 1));

                String stringEnd = sdf.format(callSettings.getServiceEndTime());
                int endColonPos = stringEnd.indexOf(":");
                int endHour = Integer.parseInt(stringEnd.substring(0, endColonPos));
                int endMin = Integer.parseInt(stringEnd.substring(endColonPos + 1));

                if (now.isAfter(LocalTime.parse(endHour + ":" + endMin)) || now.isBefore(LocalTime.parse(startHour + ":" + startMin))) {
                    logger.info("In exclude");

                    if (startHour > 12 && startHour < 24) {
                        if ((startHour - 12) < 10) {
                            if (startMin < 10) {
                                stringStart = "0" + String.valueOf(startHour - 12) + ":" + "0" + startMin;
                            } else {
                                stringStart = String.valueOf(startHour - 12) + ":" + startMin;
                            }
                        } else if (startMin < 10) {
                            stringStart = String.valueOf(startHour - 12) + ":" + "0" + startMin;
                        } else {
                            stringStart = String.valueOf(startHour - 12) + ":" + startMin;
                        }
                        this.setServiceTimeStart(stringStart + " PM");
                    } else {
                        this.setServiceTimeStart(stringStart + " AM");
                    }

                    if (endHour > 12 && endHour < 24) {
                        if ((endHour - 12) < 10) {
                            if (endMin < 10) {
                                stringEnd = "0" + String.valueOf(endHour - 12) + ":" + "0" + endMin;
                            } else {
                                stringEnd = String.valueOf(endHour - 12) + ":" + endMin;
                            }
                        } else if (endMin < 10) {
                            stringEnd = String.valueOf(endHour - 12) + ":" + "0" + endMin;
                        } else {
                            stringEnd = String.valueOf(endHour - 12) + ":" + endMin;
                        }
                        this.setServiceTimeEnd(stringEnd + " PM");
                    } else {
                        this.setServiceTimeEnd(stringEnd + " AM");
                    }
                    check = true;
                }
            } else {
                check = false;
            }
        } else {
            check = true;
        }
        return check;
    }

    public void executeHold(HttpServletRequest request) {

        CallMst cm = (CallMst) request.getSession().getAttribute("callMstThroughWeb");
        if (cm != null) {
            RequestContext.getCurrentInstance().execute("muteAll();$('#inCallVideoReplaceButton').click();");
        }
        iworkImage = false;
    }

    public void executeUnhold(HttpServletRequest request) {
        CallMst cm = (CallMst) request.getSession().getAttribute("callMstThroughWeb");
        if (cm != null) {
            RequestContext.getCurrentInstance().execute("unholdWithPersistantingStateCustomer();$('#restoreVideoBtn').click();");
        }
        iworkImage = true;
    }

    public void setFileTitle(HttpServletRequest request) {
        logger.info("In setFileTitle");
        if (docTitle == null || docTitle.equals("")) {
            FacesContext.getCurrentInstance().addMessage("fileCaption", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Provide a document title"));
        } else {
            this.fileCaptionRenderer = true;
        }
    }

    public void setbuttons1() {
        this.imageuploadrenderer = true;
        this.docuploadrenderer = false;
        this.idcarduploadrenderer = false;
        this.addproofuploadrenderer = false;
    }

    public void setbuttons2() {
        this.imageuploadrenderer = false;
        this.docuploadrenderer = true;
        this.idcarduploadrenderer = false;
        this.addproofuploadrenderer = false;
    }

    public void setbuttons3() {
        this.imageuploadrenderer = false;
        this.docuploadrenderer = false;
        this.idcarduploadrenderer = true;
        this.addproofuploadrenderer = false;
    }

    public void setbuttons4() {
        this.imageuploadrenderer = false;
        this.docuploadrenderer = false;
        this.idcarduploadrenderer = false;
        this.addproofuploadrenderer = true;
    }

    public void setbuttons() {
        this.imageuploadrenderer = false;
        this.docuploadrenderer = false;
    }

    public void fileuploadview() {
        this.fileUploadMsg = "";
    }

    public void promotinalVideoSet() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        PromotionalVideoMst pvm = promotionalVideoMstService.findSelectedPromotionalVideo();
        if (null != pvm) {
            promoURL = promotionalVideoMstService.getVideoFileUrl(pvm.getFileUrl(), request, ctx);
        } else {
            promoURL = request.getContextPath() + "/promotional/promo.mp4";
        }
    }

    public void viewScheduleCalendarRM(HttpServletRequest request) {
        logger.info("Inside viewScheduleCalendarRM");
        try {

            CustomerMst cm = (CustomerMst) request.getSession().getAttribute("ormCustomerMaster");

            List<CustomerRmMap> mappedRMList = customerRMMapService.getRMMappedWithCustomer(cm.getId());
            CustomerRmMap customerRmMap = null;
            if (!mappedRMList.isEmpty()) {
                customerRmMap = mappedRMList.get(0);
            }

            eventModel = new DefaultScheduleModel();
            if (null != customerRmMap) {

                List<ScheduleCallDto> scheduleCallDtlsDtos = callSchedulerService.getScheduledCallDtlsByEmployeeMstID(customerRmMap.getRmId().getId());

                if (scheduleCallDtlsDtos != null && scheduleCallDtlsDtos.size() > 0 && !scheduleCallDtlsDtos.isEmpty()) {
                    for (ScheduleCallDto obj : scheduleCallDtlsDtos) {
                        eventModel.addEvent(new DefaultScheduleEvent("Schedule", obj.getScheduleStartDateTime(), obj.getScheduleEndDateTime(), obj.getScheduleCallMstId()));
                    }
                }
            }
        } catch (Exception e) {
            logger.error("ERROR : ", e);
        }
    }

    public void viewScheduleCallRM(HttpServletRequest request) {
        logger.info("viewScheduleCallRM...........................");
        try {
            viewScheduleCalendarRM(request);

            RequestContext.getCurrentInstance().execute("PF('rmscvar').show();");

        } catch (Exception e) {
            logger.error("ERROR viewScheduleCallRM: ", e);
        }
    }

    public void viewScheduleCalendarCustomer(HttpServletRequest request) {
        logger.info("Inside viewScheduleCalendarCustomer");
        try {
            if (scheduledCallDtlDtoList != null) {
                scheduledCallDtlDtoList.clear();
            }

            renderedCallBtn = false;
            renderedCancelSchdlBtn = false;

            CustomerMst cm = (CustomerMst) request.getSession().getAttribute("ormCustomerMaster");

            eventModel = new DefaultScheduleModel();

            List<ScheduleCallDto> scheduleCallDtlsDtos = callSchedulerService.getScheduledCallDtlsByCustomerMstID(cm.getId());

            if (scheduleCallDtlsDtos != null && scheduleCallDtlsDtos.size() > 0 && !scheduleCallDtlsDtos.isEmpty()) {
                for (ScheduleCallDto obj : scheduleCallDtlsDtos) {
                    eventModel.addEvent(new DefaultScheduleEvent("Schedule", obj.getScheduleStartDateTime(), obj.getScheduleEndDateTime(), obj.getScheduleCallMstId()));
                }
            }
        } catch (Exception e) {
            logger.error("ERROR : ", e);
        }
    }

    public void viewScheduleCallCustomer(HttpServletRequest request) {
        logger.info("viewScheduleCallCustomer...........................");
        try {
            viewScheduleCalendarCustomer(request);
            RequestContext.getCurrentInstance().execute("PF('pscvar').show();");
        } catch (Exception e) {
            logger.error("ERROR viewScheduleCallCustomer: ", e);
        }
    }

    public void onEventSelect(SelectEvent selectEvent) {
        try {
            if (selectEvent != null) {
                event = (ScheduleEvent) selectEvent.getObject();

                Date now = new Date();
                Calendar beforeCal = Calendar.getInstance();
                beforeCal.setTime(event.getStartDate());
                beforeCal.add(Calendar.MINUTE, -5);
                Calendar afterCal = Calendar.getInstance();
                afterCal.setTime(event.getStartDate());
                afterCal.add(Calendar.MINUTE, 6);
                logger.info("CurrentTime : " + now + "beforeCal : " + beforeCal.getTime());

                renderedCallBtn = now.after(beforeCal.getTime()) && now.before(afterCal.getTime());

                renderedCancelSchdlBtn = now.before(beforeCal.getTime());

                scheduledCallMstId = (Long) event.getData();
                logger.info("scheduledCallMstId : " + scheduledCallMstId);

                if (scheduledCallDtlDtoList != null) {
                    scheduledCallDtlDtoList.clear();
                }

                List<ScheduleCallDto> tmpScheduledCallDtlDtoList = callSchedulerService.findScheduledCallDtlsByScheduledMstID(scheduledCallMstId);

                if (tmpScheduledCallDtlDtoList == null || tmpScheduledCallDtlDtoList.isEmpty() || tmpScheduledCallDtlDtoList.isEmpty()) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "ERROR : ", "Schedule Details Not Found"));

                } else {
                    for (ScheduleCallDto obj : tmpScheduledCallDtlDtoList) {
                        ScheduleCallDto scheduleCallDtlsDto = obj;
                        if (scheduleCallDtlsDto.getService() != null) {
                            obj.setServiceName((serviceMstService.findAllServiceMstById(scheduleCallDtlsDto.getService())).getServiceName());
                        }

                        scheduledCallDtlDtoList.add(obj);
                    }

                    RequestContext.getCurrentInstance().execute("PF('eventDialogVar').show();");
                }
            }
        } catch (Exception e) {
            logger.error("ERROR : ", e);
        }
    }

    public void onDialScheduleCall() {

        RequestContext.getCurrentInstance().execute("PF('eventDialogVar').hide();");
        RequestContext.getCurrentInstance().execute("showSpineerCallForScheduleCall();");

    }

    public void onCancelScheduleCall(HttpServletRequest request) {

        logger.info("inside onCancelScheduleCall");
        try {
            ScheduleCall scheduleCallDtl = callSchedulerService.findAllNonTakenScheduleCallById(scheduledCallMstId);
            ScheduleCall schCl;
            scheduleCallDtl.setActiveFlg(false);
            scheduleCallDtl.setDeleteFlg(true);
            scheduleCallDtl.setExecuteStatus("Cancelled");

            schCl = callSchedulerService.saveScheduleCall(scheduleCallDtl, null);

            if (schCl != null) {
                logger.info("success onCancelScheduleCall");
                renderedCancelSchdlBtn = false;
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "SUCCESS! : ", "Schedule Call Cancelled Successfully"));
                RequestContext.getCurrentInstance().execute("PF('pscvar').hide();");
            }

        } catch (Exception e) {
            logger.error("error :" + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR! : ", "Schedule Call not Cancelled"));
        }

    }

    public void scheduleCallCallDataFromCustomer(HttpServletRequest request) {
        logger.info("scheduleCallCallDataFromCustomer....." + scheduledCallMstId);
        CustomerMst customerMst_local = (CustomerMst) request.getSession().getAttribute("ormCustomerMaster");
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        String weekDay = "";
        switch (day) {
            case Calendar.SUNDAY:
                weekDay = "Sunday";
                break;
            case Calendar.MONDAY:
                weekDay = "Monday";
                break;
            case Calendar.TUESDAY:
                weekDay = "Tuesday";
                break;
            case Calendar.WEDNESDAY:
                weekDay = "Wednesday";
                break;
            case Calendar.THURSDAY:
                weekDay = "Thursday";
                break;
            case Calendar.FRIDAY:
                weekDay = "Friday";
                break;
            case Calendar.SATURDAY:
                weekDay = "Saturday";
                break;

        }

        boolean indownTime;
        boolean inservicedownTime = false;

        indownTime = whetherInDownTime();

        if (!indownTime) {
            inservicedownTime = whetherInServiceDownTime();
        }
        if (indownTime) {
            downMessage = "Currently System is in downtime from " + this.downTimeStart + " to " + this.downTimeEnd;
        } else if (inservicedownTime) {
            if ((this.getServiceTimeStart() != null && !this.getServiceTimeStart().trim().equals("")) && (this.getServiceTimeEnd() != null && !this.getServiceTimeEnd().trim().equals(""))) {
                downMessage = "We are unavailable to attend to you via this channel at this time. We are only available on " + weekDay + " between " + this.getServiceTimeStart() + " to " + this.getServiceTimeEnd();

            } else {

                downMessage = "We are unavailable to attend to you via this channel at this time on this week day ";
            }
        }
        try {
            if (customerMst_local != null && null != customerMst_local.getCustId() && null != scheduledCallMstId && scheduledCallMstId != 0) {
                if (!indownTime && !inservicedownTime) {
                    CustomerDeviceDtl cdd = customerDeviceDtlService.findCustomerDeviceDtlByCustId(customerMst_local);
                    if (null != cdd) {
                        if (null != cdd.getId()) {
                            cdd.setAudioVideo(1);

                            customerDeviceDtlService.saveCustomerDeviceDtl(cdd, null);
                        }
                    }
                    ScheduleCall scheduleCallLocal = callSchedulerService.findAllNonTakenScheduleCallById(scheduledCallMstId);
                    if (null != scheduleCallLocal) {
                        try {
                            scheduleCallLocal.setCustomerTime(CustomConvert.javaDateToTimeStamp(new Date()));
                            scheduleCallLocal = callSchedulerService.saveScheduleCall(scheduleCallLocal, null);
                            request.getSession().setAttribute("scheduleCallLocal", scheduleCallLocal);
                        } catch (ParseException ex) {
                            logger.error(ex);
                        }
                    }
                    logger.info("scheduleCallLocal:" + scheduleCallLocal);
                    CategoryMst categoryMst = categoryMstService.findCategoryByCategoryCode("UGR");
                    selectedCategoryName = categoryMst.getId();
                    LanguageMst languageMst = langMstService.findLanguageMstByLanguageCode("ENG");
                    selectedLanguageName = languageMst.getId();
                    CallMst local_callMst = new CallMst();
                    ServiceMst serviceMst = null;
                    if (null != scheduleCallLocal) {
                        serviceMst = serviceMstService.findNonDeletedServiceMstById(scheduleCallLocal.getService());
                        local_callMst.setCallOption(scheduleCallLocal.getCallType());
                    }

                    local_callMst.setCallStatus("Initialize");
                    local_callMst.setCallType("Schedule Call");
                    local_callMst.setDeviceBrand(null);
                    local_callMst.setDeviceOs(null);
                    local_callMst.setiMEIno(null);
                    local_callMst.setDeviceId(null);
                    local_callMst.setDeviceIp(null);
                    local_callMst.setStaticIp(null);
                    local_callMst.setDeviceName(null);
                    local_callMst.setCustomerId(customerMst_local);
                    local_callMst.setCustId(customerMst_local.getCustId());
                    if (null != serviceMst) {
                        local_callMst.setServiceId(serviceMst.getId());
                    }
                    local_callMst.setCategoryId(categoryMst.getId());
                    local_callMst.setLanguageId(languageMst.getId());
                    local_callMst.setCallMedium("WEB");
                    local_callMst.setActiveFlg(true);
                    local_callMst.setDeleteFlg(false);
                    local_callMst.setStartTime(CustomConvert.javaDateToTimeStamp(new Date(System.currentTimeMillis())));
                    local_callMst.setCustomerRequestTime(CustomConvert.javaDateToTimeStamp(new Date()));
                    local_callMst.setCallLocation(getCityLocation() + "," + getCountryLocation());
                    local_callMst.setCallLatitude(getLatitude());
                    local_callMst.setCallLongitude(getLongitude());
                    local_callMst = callMstService.saveCallMst(local_callMst);
                    callMst = callMstService.findCallMstById(local_callMst.getId());

                    request.getSession().setAttribute("callMstThroughWeb", callMst);

                    if (null != scheduleCallLocal) {
                        scheduleCallLocal.setCallmstid(callMst.getId());
                    }
                    scheduleCallLocal = callSchedulerService.saveScheduleCall(scheduleCallLocal, null);

                    this.portal = Constants.vidyoportal;
                    this.custName = (String) request.getSession().getAttribute("ormCustomerName") + "~" + (String) request.getSession().getAttribute("ormCustomerID");

                    RequestContext.getCurrentInstance().update("vidyowebrtcform");

                    List<CustomerRmMap> mappedRMList = customerRMMapService.getRMMappedWithCustomer(customerMst_local.getId());
                    CustomerRmMap customerRmMap = null;
                    if (!mappedRMList.isEmpty()) {
                        customerRmMap = mappedRMList.get(0);
                    }
                    if (null != customerRmMap) {
                        EmployeeMst employeeMstLocal = employeeMstService.findEmployeeMstById(customerRmMap.getRmId().getId());

                        if (null != employeeMstLocal) {
                            CallEmployeeMap l_CallEmpMap = new CallEmployeeMap();
                            l_CallEmpMap.setCallId(callMst.getId());
                            l_CallEmpMap.setEmployeeId(employeeMstLocal.getId());
                            l_CallEmpMap.setCallType("ScheduleCall");
                            l_CallEmpMap.setScheduleCallId(scheduleCallLocal.getId());
                            l_CallEmpMap.setCustId(callMst.getCustId());

                            CallScheduler.listScheduleCallForEmp.add(l_CallEmpMap);
                            try {
                                DateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                Date d = f.parse(scheduleCallLocal.getScheduleDate() + "");
                                DateFormat time = new SimpleDateFormat("HH:mm:ss");
                                String timeText = time.format(d);
                                logger.info("Date: " + d);
                                logger.info("Time: " + timeText);
                                logger.info(employeeMstLocal.getLoginId() + "schheduleCallInitiatedByCust#" + timeText + "#" + scheduleCallLocal.getId());
                                SocketMessage.send(callScheduler.getAdminSocket(), employeeMstLocal.getLoginId(), "schheduleCallInitiatedByCust#" + timeText + "#" + scheduleCallLocal.getId());
                            } catch (Exception ex) {
                                logger.error(ex);
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            logger.error(e);
        }

    }

    public void makeScheduleCallFromWeb(HttpServletRequest request) {
        final TimeZone timeZoneL = TimeZone.getDefault();

        CustomerMst cm = (CustomerMst) request.getSession().getAttribute("ormCustomerMaster");
        if (null != cm) {
            EmployeeMst employeeMstlocal;
            List<CustomerRmMap> mappedRMList = customerRMMapService.getRMMappedWithCustomer(cm.getId());
            CustomerRmMap customerRmMap = null;
            if (!mappedRMList.isEmpty()) {
                customerRmMap = mappedRMList.get(0);
            }
            if (null != customerRmMap) {
                employeeMstlocal = employeeMstService.findEmployeeMstById(customerRmMap.getRmId().getId());

                String serviceStrtTimeRM1 = Constants.RM1_Service_Start_Time;
                String serviceendTimeRM1 = Constants.RM1_Service_End_Time;

                SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
                try {
                    String scheduleStartTime = sdf1.format(schDateTime);
                    String scheduleEndTime = sdf1.format(schEndDateTime);

                    serviceStartTime = sdf1.parse(serviceStrtTimeRM1);
                    serviceEndTime = sdf1.parse(serviceendTimeRM1);

                    scheduleStartTimeeee = sdf1.parse(scheduleStartTime);
                    scheduleEndTimeeee = sdf1.parse(scheduleEndTime);
                } catch (Exception e) {

                }
                if (null != employeeMstlocal) {
                    List<ScheduleCallDto> scheduleCallDtlsDtos = callSchedulerService.getScheduledCallDtlsByEmployeeMstID(employeeMstlocal.getId());

                    ScheduleCall scheduleCall = new ScheduleCall();
                    ScheduleCall schCall;

                    try {
                        logger.info("selectedServiceName:" + selectedServiceName);
                        logger.info("schDateTime:" + schDateTime);
                        logger.info("callType:" + callType + "CM:" + cm + "schEndDateTime:" + schEndDateTime);
                        ThreadSafeSimpleDateFormat sdf = new ThreadSafeSimpleDateFormat("dd-MMM-yyyy HH:mm");
                        sdf.setTimeZone(timeZoneL);
                        this.schDateTime = sdf.parse(sdf.format(schDateTime));
                        this.schEndDateTime = sdf.parse(sdf.format(schEndDateTime));
                        java.sql.Timestamp timestamp = new java.sql.Timestamp(schDateTime.getTime());

                        java.sql.Timestamp timestampEndDate = new java.sql.Timestamp(schEndDateTime.getTime());
                        if ((schDateTime != null && schEndDateTime == null) || (schDateTime == null && schEndDateTime != null)) {
                            this.scheduleText = "Please Select Start Date & End Date Properly";
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR : ", "Please Select Start Date & End Date Properly"));
                            RequestContext.getCurrentInstance().update("confirmscheduleform2");
                            RequestContext.getCurrentInstance().execute("closeAllDialog();PF('confirmscheduleDlg2').show();hideLoaderSpinner();");
                            return;
                        }
                        if (scheduleCallDtlsDtos != null && scheduleCallDtlsDtos.size() > 0 && !scheduleCallDtlsDtos.isEmpty()) {
                            if (schDateTime != null && schEndDateTime != null) {
                                for (ScheduleCallDto obj : scheduleCallDtlsDtos) {
                                    if ((schDateTime.after(obj.getScheduleStartDateTime()) && schDateTime.before(obj.getScheduleEndDateTime())) || schDateTime.equals(obj.getScheduleStartDateTime()) || schDateTime.equals(obj.getScheduleEndDateTime())) {
                                        this.scheduleText = "Start Time overlapping with existing Schedule Call..!Please Select another Start Time!";
                                        RequestContext.getCurrentInstance().update("confirmscheduleform2");
                                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR : Please Select Proper End Date Time..!! ", "Please Select Proper Start Date Time..!!"));
                                        RequestContext.getCurrentInstance().execute("closeAllDialog();PF('confirmscheduleDlg2').show();hideLoaderSpinner();");
                                        return;

                                    }
                                    if ((schEndDateTime.after(obj.getScheduleStartDateTime()) && schEndDateTime.before(obj.getScheduleEndDateTime())) || schEndDateTime.equals(obj.getScheduleStartDateTime()) || schEndDateTime.equals(obj.getScheduleEndDateTime())) {
                                        this.scheduleText = "End Time overlapping with existing Schedule Call..!Please Select another End Time!";
                                        RequestContext.getCurrentInstance().update("confirmscheduleform2");
                                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR : Please Select Proper End Date Time..!!", "Please Select Proper End Date Time..!!"));
                                        RequestContext.getCurrentInstance().execute("closeAllDialog();PF('confirmscheduleDlg2').show();hideLoaderSpinner();");
                                        return;

                                    }

                                }
                            }

                        }

                        if (schDateTime != null && schEndDateTime != null) {
                            if (schDateTime.compareTo(schEndDateTime) == 1) {
                                this.scheduleText = "Start Date Time Should Not be Greater Than End Date Time..!!";
                                RequestContext.getCurrentInstance().update("confirmscheduleform2");
                                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR : ", "Start Date Time Should Not be Greater Than End Date Time..!!"));
                                RequestContext.getCurrentInstance().execute("closeAllDialog();PF('confirmscheduleDlg2').show();hideLoaderSpinner();");
                                return;
                            }
                        }

                        if (schDateTime != null && schEndDateTime != null) {
                            if (scheduleStartTimeeee.before(serviceStartTime) || scheduleStartTimeeee.after(serviceEndTime) || scheduleStartTimeeee.equals(serviceEndTime) || scheduleEndTimeeee.after(serviceEndTime)) {
                                this.scheduleText = "Please Schdeule in between Service Time!";
                                RequestContext.getCurrentInstance().update("confirmscheduleform2");
                                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR : Please Schdeule in between Service Time.!! ", "Please Schdeule in between Service Time..!!"));
                                RequestContext.getCurrentInstance().execute("closeAllDialog();PF('confirmscheduleDlg2').show();hideLoaderSpinner();");
                                return;
                            }
                        }

                        if (schDateTime.after(new Date())) {

                            //TO-DO validate If already there some schedule with same time slot with particular RM
                            CategoryMst categoryMst = categoryMstService.findCategoryMstById(this.selectedCategoryName);
                            LanguageMst languageMst = langMstService.findAllLanguageMstById(this.selectedLanguageName);
                            ServiceMst serviceMst = serviceMstService.findAllServiceMstById(this.selectedServiceName);

                            scheduleCall.setCreationDatetime(CustomConvert.javaDateToTimeStamp(new Date()));
                            scheduleCall.setExecuteStatus("Scheduled");
                            scheduleCall.setScheduledBy("Customer");
                            scheduleCall.setSchedulerId((long) 0);
                            scheduleCall.setScheduleDate(timestamp);
                            scheduleCall.setCustomerId(cm);
                            scheduleCall.setCallMedium("WEB");

                            scheduleCall.setCallType(callType);
                            scheduleCall.setEmployeeId(employeeMstlocal);
                            scheduleCall.setScheduleEndDate(timestampEndDate);

                            if (serviceMst != null) {
                                scheduleCall.setService(serviceMst.getId());
                            }
                            if (languageMst != null) {
                                scheduleCall.setLanguage(languageMst.getId());
                            }
                            if (categoryMst != null) {
                                scheduleCall.setCategory(categoryMst.getId());
                            }

                            scheduleCall.setActiveFlg(true);
                            scheduleCall.setDeleteFlg(false);
                            schCall = callSchedulerService.saveScheduleCall(scheduleCall, null);
                            if (null != schCall) {
                                //TO-DO EMAIl CODE.
                                String messageBody;
                                synchronized (schCall) {
                                    //Mail Send TO RM
                                    messageBody = "<html><body>Dear &nbsp; " + employeeMstlocal.getFirstName() + " " + employeeMstlocal.getLastName();
                                    messageBody += " ,&nbsp;<br></br><br></br>You have a scheduled meeting with customer " + cm.getFirstName() + " " + cm.getLastName() + " at " + schDateTime + " .";
                                    messageBody += "<br>";
                                    messageBody += "<b>Note:</b>Please do not refresh the browser during the call.";
                                    messageBody += "<br></br>";
                                    messageBody += SendingMailUtil.TELE_THX_HTML;
                                    messageBody += "</body><html/>";
                                    logger.info("Before Send Email...");
                                    boolean mailSend = SendingMailUtil.sendEMail(employeeMstlocal.getEmail(), messageBody, SendingMailUtil.SCHEDULE_CALL);
                                    logger.info("After Send Email...");
                                    if (mailSend) {
                                        logger.info("mail sending was successfull... to RM:" + employeeMstlocal.getEmail());
                                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Email Send Was Successful", "Send Successful!!"));
                                    }

                                    messageBody = "<html><body>Dear &nbsp; Customer ";
                                    messageBody += ",&nbsp;<br>You have a scheduled meeting with your Relationship Manager at " + schDateTime + " .";
                                    messageBody += "<br>";
                                    messageBody += "<b>Note:</b>Please do not refresh the browser during the call.";
                                    messageBody += "<br></br>";
                                    messageBody += SendingMailUtil.TELE_THX_HTML;
                                    messageBody += "</body><html/>";
                                    logger.info("Before Send Email...");
                                    mailSend = SendingMailUtil.sendEMail(cm.getEmail(), messageBody, SendingMailUtil.SCHEDULE_CALL);
                                    logger.info("After Send Email...");
                                    if (mailSend) {
                                        logger.info("mail sending was successfull... to Customer:" + cm.getEmail());
                                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Email Send Was Successful", "Send Successful!!"));
                                    }
                                }
                                this.scheduleText = "Your call has been scheduled successfully";
                                RequestContext.getCurrentInstance().update("confirmscheduleform");
                                RequestContext.getCurrentInstance().execute("PF('doScheduleDialog').hide();");
                                RequestContext.getCurrentInstance().execute("closeAllDialog();PF('confirmscheduleDlg').show();");
                                RequestContext.getCurrentInstance().execute("hideLoaderSpinner();");

                            } else {

                                this.scheduleText = "Call Scheduleding Failed";
                                RequestContext.getCurrentInstance().execute("PF('confirmscheduleDlg').show();hideLoaderSpinner();");
                            }

                        } else {

                            this.scheduleText = "Select valid Date and time";
                            RequestContext.getCurrentInstance().update("confirmscheduleform2");
                            RequestContext.getCurrentInstance().execute("closeAllDialog();PF('confirmscheduleDlg2').show();hideLoaderSpinner();");
                        }

                    } catch (Exception e) {
                        logger.error("Eroor:" + e);
                        this.scheduleText = "Some Error Occured, Please try again";
                        RequestContext.getCurrentInstance().update("confirmscheduleform2");
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR : ", scheduleText));
                        RequestContext.getCurrentInstance().execute("closeAllDialog();PF('confirmscheduleDlg2').show();hideLoaderSpinner();");

                    }
                }
            }
        }
    }

    public void doScheduleCancel() {

    }

    public void sendScheduledCallNotiToCustomer(HttpServletRequest request) {

        CustomerMst customerInSession = (CustomerMst) request.getSession().getAttribute("ormCustomerMaster");
        if (null != customerInSession) {
            for (Iterator<ScheduleCallDto> scheduledCallItr = CustomerConstant.scheduleCallList.iterator(); scheduledCallItr.hasNext();) {
                ScheduleCallDto scheduleCallDto = scheduledCallItr.next();

                if (customerInSession.getId().longValue() == scheduleCallDto.getCustomerPkId()) {
                    this.portal = Constants.vidyoportal;
                    this.roomUrl = scheduleCallDto.getRoomLink();
                    this.custName = (String) request.getSession().getAttribute("ormCustomerNameSocket");
                    CallMst callMst_l = callMstService.findCallMstById(scheduleCallDto.getCallMstPkId());
                    if (null != latitude && latitude.equals("")) {
                        callMst_l.setCallLatitude(latitude);
                        callMst_l.setCallLongitude(longitude);
                        callMst_l.setCallLocation(area);
                        callMst_l = callMstService.saveCallMst(callMst_l);
                    }
                    EmployeeMst emp = employeeMstService.findEmployeeMstById(scheduleCallDto.getRefAgentSchId());
                    if (null != emp) {
                        request.getSession().setAttribute("onlineAgentFound", emp.getLoginId());
                        List<TenancyEmployeeMap> tenancyEmployeeMapList = tenancyEmployeeMapService.findVidyoTenantUrlByEmpId(emp.getId());
                        if (!tenancyEmployeeMapList.isEmpty()) {
                            String roomLink = tenancyEmployeeMapList.get(0).getRoomLink();
                            String roomNameL = tenancyEmployeeMapList.get(0).getRoomName();
                            this.portal = Constants.vidyoportal;
                            this.roomUrl = roomLink;
                            this.roomName = roomNameL;
                            this.custName = (String) request.getSession().getAttribute("ormCustomerNameSocket");
                            RequestContext.getCurrentInstance().update("vidyowebrtcform");

                        }

                    }
                    request.getSession().setAttribute("callMstThroughWeb", callMst_l);
                    RequestContext.getCurrentInstance().execute("closeAllDialog();PF('scheduledCallDlg').show();");
                    scheduledCallItr.remove();
                }
            }
        }

    }

    public void rejectScheduledCall(HttpServletRequest request) {
        CallMst callMstFromSession = (CallMst) request.getSession().getAttribute("callMstThroughWeb");
        if (null != callMstFromSession) {
            CallMst cm = callMstService.findCallMstById(callMstFromSession.getId());
            CustomerMst cust = customerMstService.findCustomerMstByCustomerId(callMstFromSession.getCustId());
            List<ScheduleCall> scallList = callSchedulerService.findScheduleCallByCustomerIdCallMstId(callMstFromSession.getId(), cust.getId());
            if (!scallList.isEmpty()) {
                scallList.get(0).setExecuteStatus("Refuse");
                callSchedulerService.saveScheduleCall(scallList.get(0), null);
            }

            try {
                cm.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                cm.setCallStatus("Customer Rejected");
                callMstService.saveCallMst(cm);
                CallDtl cd = callDtlService.findCallDtlByCallMstIdForSchduledCall(cm.getId());
                cd.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                callDtlService.saveCallDtl(cd);
            } catch (ParseException ex) {
                java.util.logging.Logger.getLogger(CustomerComponent.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                logger.info("call null 1");
                request.getSession().setAttribute("callMstThroughWeb", null);
            }
        }
    }

    public void enableCustometForTakeCall(HttpServletRequest request) {
        logger.info("In enableCustometForTakeCall");
        try {
            CustomerMst customerMst = (CustomerMst) request.getSession().getAttribute("ormCustomerMaster");
            CustomerDeviceDtl cdd = customerDeviceDtlService.findCustomerDeviceDtlByCustId(customerMst);

            cdd.setLoginInfo(1);

            customerDeviceDtlService.saveCustomerDeviceDtl(cdd, null);
        } catch (Exception e) {
            logger.error(" Error in enableCustometForTakeCall " + e);
        }
    }

    public void missedScheduledCall(HttpServletRequest request) {
        CallMst callMstFromSession = (CallMst) request.getSession().getAttribute("callMstThroughWeb");

        if (null != callMstFromSession) {
            CallMst cm = callMstService.findCallMstById(callMstFromSession.getId());

            try {
                cm.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                cm.setCallStatus("Customer missed");
                callMstService.saveCallMst(cm);
                CallDtl cd = callDtlService.findCallDtlByCallMstIdForSchduledCall(cm.getId());
                cd.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                callDtlService.saveCallDtl(cd);
            } catch (ParseException ex) {
                java.util.logging.Logger.getLogger(CustomerComponent.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                logger.info("call null 2");
                request.getSession().setAttribute("callMstThroughWeb", null);
            }
        }

    }

    public void rejectDirectCall(HttpServletRequest request) {
        CallMst callMstFromSession = (CallMst) request.getSession().getAttribute("callMstThroughWeb");
        if (null != callMstFromSession) {
            CallMst cm = callMstService.findCallMstById(callMstFromSession.getId());
            try {
                cm.setCallStatus("Customer Rejected");
                cm.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                cm.setCustomerHangupTime(CustomConvert.javaDateToTimeStamp(new Date()));
                callMstService.saveCallMst(cm);

            } catch (ParseException ex) {
                java.util.logging.Logger.getLogger(CustomerComponent.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                logger.info("call null 3");
                request.getSession().setAttribute("callMstThroughWeb", null);
            }
        }
    }

    public void rejectDirectCall1(HttpServletRequest request) {
        CallMst callMstFromSession = (CallMst) request.getSession().getAttribute("callMstThroughWeb");
        if (null != callMstFromSession) {
            CallMst cm = callMstService.findCallMstById(callMstFromSession.getId());
            cm.setCallStatus("Customer Missed");
            try {
                cm.setCustomerHangupTime(CustomConvert.javaDateToTimeStamp(new Date()));
                cm.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                callMstService.saveCallMst(cm);

            } catch (ParseException ex) {
                java.util.logging.Logger.getLogger(CustomerComponent.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                RequestContext.getCurrentInstance().execute("closeAllDialog();PF('missagent').show();");
                logger.info("call null 4");
                request.getSession().setAttribute("callMstThroughWeb", null);
            }
        }
    }

    public void refreshWhenCallStarts() {
        logger.info("===========refreshWhenCallStarts has been called=============");
        HttpSession session = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession(false);
        CallMst callMstFromSession = (CallMst) session.getAttribute("callMstThroughWeb");
        CustomerMst cm = (CustomerMst) session.getAttribute("ormCustomerMaster");
        disableCallButton = true;
        disableUploadFileButton = false;

        iworkImage = true;
        diasableEndCallButton = false;
        statusOfEndCall = false;
        this.setCustName(cm.getFirstName() + "~" + cm.getCustId());
        logger.info("Display Name is====" + this.custName);
        if (callMstFromSession != null) {
            try {

                callMstFromSession = callMstService.findCallMstById(callMstFromSession.getId());

                if (callMstFromSession.getCallOption().equalsIgnoreCase("chat")) {
                    List<CallDtl> cList = callDtlService.findCallDtlByCallMasterId(callMstFromSession.getId());
                    Set<String> hash_Set = new HashSet<>();
                    for (int i = 0; i < cList.size(); i++) {
                        if (cList.get(i).getEndTime() == null) {
                            EmployeeMst em = employeeMstService.findEmployeeMstById(cList.get(i).getHandeledById().getId());
                            hash_Set.add(em.getLoginId());
                        }
                    }

                    for (String temp : hash_Set) {
                        RequestContext.getCurrentInstance().execute("insertParticipants('" + temp + "');");
                    }
                    RequestContext.getCurrentInstance().execute("joinConferenceOperationchat();");

                } else if (callMstFromSession.getCallOption().equalsIgnoreCase("audio")) {
                    RequestContext.getCurrentInstance().execute("joinConferenceOperationAudio();");

                } else {
                    RequestContext.getCurrentInstance().execute("joinConferenceOperation();");
                }
                callMstFromSession.setCustomerPickupTime(CustomConvert.javaDateToTimeStamp(new Date()));

                callMstService.saveCallMst(callMstFromSession);
                if (cm.getAccountNo() == null || "".equals(cm.getAccountNo().trim())) {

                    CustomerDeviceDtl customerDeviceDtl = new CustomerDeviceDtl();
                    customerDeviceDtl.setLogout(0);

                    customerDeviceDtl.setDeviceBrand(null);
                    customerDeviceDtl.setDeviceId(null);
                    customerDeviceDtl.setDeviceIp(null);
                    customerDeviceDtl.setImeiNo(null);

                    customerDeviceDtl.setMobileOsType("WEB");
                    customerDeviceDtl.setStaticIp(null);
                    customerDeviceDtl.setLoginInfo(1);
                    customerDeviceDtl.setCustomerId(cm);
                    customerDeviceDtl.setCustomerOtp(null);
                    customerDeviceDtl.setTimezone("Asia/Kolkata");
                    customerDeviceDtlService.saveCustomerDeviceDtl(customerDeviceDtl, null);
                } else if (cm.getAccountNo() != null && cm.getAccountNo().trim().length() > 0) {
                    logger.info("Existing Customer call end!");
                    CustomerDeviceDtl customerDeviceDtl = new CustomerDeviceDtl();
                    customerDeviceDtl.setLogout(0);

                    customerDeviceDtl.setDeviceBrand(null);
                    customerDeviceDtl.setDeviceId(null);
                    customerDeviceDtl.setDeviceIp(null);
                    customerDeviceDtl.setImeiNo(null);

                    customerDeviceDtl.setMobileOsType("WEB");
                    customerDeviceDtl.setStaticIp(null);
                    customerDeviceDtl.setLoginInfo(2);
                    customerDeviceDtl.setCustomerId(cm);
                    customerDeviceDtl.setCustomerOtp(null);
                    customerDeviceDtl.setTimezone("Asia/Kolkata");
                    customerDeviceDtlService.saveCustomerDeviceDtl(customerDeviceDtl, null);
                }
            } catch (ParseException ex) {
                Logger.getLogger(CustomerComponent.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void refreshValues() {
        disableCallButton = false;
        disableUploadFileButton = true;
        diasableEndCallButton = true;
        statusOfEndCall = true;
    }

    public void refreshWhenCallEnds(HttpServletRequest request) {
        customerAccountComponent.setCustomerAccountDto(null);
        customerAccountComponent.setCustomerLoanDto(null);
        customerAccountComponent.setSnapImage("");

        logger.info("===========refreshWhenCallEnds has been called=============");
        RequestContext.getCurrentInstance().update("accViewFrmId");
        request.getSession().setAttribute("imagePath", null);
        request.getSession().setAttribute("imageCount", null);
        this.rating = (long) 1;
        this.feedback = "";
        disableCallButton = false;
        disableUploadFileButton = true;
        diasableEndCallButton = true;
        statusOfEndCall = true;
        fileUploadMsg = "";
        iworkImage = false;
        fileDownloadDtoList.clear();
        CallMst callSession = (CallMst) request.getSession().getAttribute("callMstThroughWeb");
        CallMst cm = null;
        if (null != callSession) {
            cm = callMstService.findCallMstById(callSession.getId());
            if (null != cm) {
                try {
                    List<CallDtl> cList = callDtlService.findCallDtlByCallMasterId(cm.getId());
                    Set<String> hash_Set = new HashSet<>();

                    for (int i = 0; i < cList.size(); i++) {
                        if (cList.get(i).getEndTime() == null) {
                            EmployeeMst em = employeeMstService.findEmployeeMstById(cList.get(i).getHandeledById().getId());
                            hash_Set.add(em.getLoginId());

                        }
                    }
                    for (String temp : hash_Set) {
                        SocketMessage.send(callScheduler.getAdminSocket(), temp, "hangUp#" + cm.getId());
                    }

                    CustomerMst l_cm = customerMstService.findCustomerMstByCustId(cm.getCustId());
                    cm.setCallStatus("Ended");
                    cm.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                    cm.setCustomerHangupTime(CustomConvert.javaDateToTimeStamp(new Date()));
                    callMstService.saveCallMst(cm);
                    this.setCustomerId(l_cm.getCustId());
                    if (l_cm.getAccountNo() == null || "".equals(l_cm.getAccountNo().trim())) {

                        CustomerDeviceDtl customerDeviceDtl = new CustomerDeviceDtl();
                        customerDeviceDtl.setLogout(1);

                        customerDeviceDtl.setDeviceBrand(null);
                        customerDeviceDtl.setDeviceId(null);
                        customerDeviceDtl.setDeviceIp(null);
                        customerDeviceDtl.setImeiNo(null);

                        customerDeviceDtl.setMobileOsType("WEB");
                        customerDeviceDtl.setStaticIp(null);
                        customerDeviceDtl.setLoginInfo(0);
                        customerDeviceDtl.setCustomerId(l_cm);
                        customerDeviceDtl.setCustomerOtp(null);
                        customerDeviceDtl.setTimezone("Asia/Kolkata");
                        customerDeviceDtlService.saveCustomerDeviceDtl(customerDeviceDtl, null);
                    } else if (l_cm.getAccountNo() != null && l_cm.getAccountNo().trim().length() > 0) {
                        logger.info("Existing Customer call end!");
                        CustomerDeviceDtl customerDeviceDtl = new CustomerDeviceDtl();
                        customerDeviceDtl.setLogout(0);

                        customerDeviceDtl.setDeviceBrand(null);
                        customerDeviceDtl.setDeviceId(null);
                        customerDeviceDtl.setDeviceIp(null);
                        customerDeviceDtl.setImeiNo(null);

                        customerDeviceDtl.setMobileOsType("WEB");
                        customerDeviceDtl.setStaticIp(null);
                        customerDeviceDtl.setLoginInfo(1);
                        customerDeviceDtl.setCustomerId(l_cm);
                        customerDeviceDtl.setCustomerOtp(null);
                        customerDeviceDtl.setTimezone("Asia/Kolkata");
                        customerDeviceDtlService.saveCustomerDeviceDtl(customerDeviceDtl, null);
                    }
                } catch (ParseException ex) {
                    java.util.logging.Logger.getLogger(CustomerComponent.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(CustomerComponent.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        if (null != cm && cm.getCustomerPickupTime() != null && cm.getAgentPickupTime() != null) {
            RequestContext.getCurrentInstance().update("feedbackIdForm");
            RequestContext.getCurrentInstance().execute("closeAllDialog();PF('feedbackdialog').show();");
        }

    }

    public void refreshWhenCallEnds1(HttpServletRequest request) {
        this.rating = (long) 1;
        this.feedback = "";
        disableCallButton = false;
        disableUploadFileButton = true;
        diasableEndCallButton = true;
        statusOfEndCall = true;
        fileUploadMsg = "";
        iworkImage = false;
        fileDownloadDtoList.clear();
        CallMst callSession = (CallMst) request.getSession().getAttribute("callMstThroughWeb");

        if (null != callSession) {
            CallMst cm = callMstService.findCallMstById(callSession.getId());
            if (null != cm) {
                try {
                    cm.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                    callMstService.saveCallMst(cm);
                } catch (ParseException ex) {
                    logger.error("Error:" + ex);
                }
            }
        }

    }

    public String redirectToCustomerDashboard() {

        return "/pages/customer/customerDashboard.xhtml?faces-redirect=true";
    }

    public String giveFeedBackOfCall(HttpServletRequest request) {
        try {
            boolean validFeedback = true;
            agentName = "";
            logger.info("" + quality + "," + ability + "," + recommend);
            if (quality == null || quality.trim().equals("")) {
                validFeedback = false;
            }
            if (ability == null || ability.trim().equals("")) {
                validFeedback = false;
            }
            if (recommend == null || recommend.trim().equals("")) {
                validFeedback = false;
            }

            if (validFeedback) {
                CallMst localCallMaster = (CallMst) request.getSession().getAttribute("callMstThroughWeb");
                if (null != localCallMaster) {
                    localCallMaster = callMstService.findCallMstById(localCallMaster.getId());
                    FeedbackDtl feedbackdtl = new FeedbackDtl();
                    feedbackdtl.setCallMstId(localCallMaster);
                    feedbackdtl.setActiveFlg(true);
                    feedbackdtl.setCustomerId(localCallMaster.getCustomerId());
                    feedbackdtl.setDeleteFlg(false);
                    feedbackdtl.setFeedbackParamId((long) 0);
                    feedbackdtl.setComments(this.feedback);
                    feedbackdtl.setStarRating(this.quality + "~" + this.ability + "~" + this.recommend);
                    feedbackdtl.setFeedbackQuery(this.qualityQuery + "~" + this.abilityQuery + "~" + this.recommendQuery);
                    feedbackdtl.setFeedbackDate(CustomConvert.javaDateToTimeStamp(new Date()));
                    feedbackdtl = feedbackDtlService.saveFeedbackDtl(feedbackdtl, null);

                    if (feedbackdtl != null) {
                        //request.getSession().setAttribute("callMstThroughWeb", null);
                        request.getSession().setAttribute("ormCustomerName", null);
                        this.setCustomerName("");
                        this.setCustomerEmail("");
                        this.setCustomerPhoneNumber("");

                        this.quality = "";
                        this.ability = "";
                        this.recommend = "";
                        resetData();
                        FacesContext.getCurrentInstance().addMessage(null,
                                new FacesMessage(FacesMessage.SEVERITY_INFO, "Thank you for your interest. It was our pleasure to assist you !", "Thank you for your interest. It was our pleasure to assist you!"));
                        logger.info("call null 5");
                        request.getSession().setAttribute("callMstThroughWeb", null);
                        RequestContext.getCurrentInstance().execute("PF('feedbackdialog').hide();setTimeout(function(){ redirectHome(); }, 5000);");

                    } else {
                        FacesContext.getCurrentInstance().addMessage(null,
                                new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR : Please Try Again", "Please Try Again."));

                    }
                } else {
                    RequestContext.getCurrentInstance().execute("PF('feedbackdialog').hide();");
                    logger.info("call null 6");
                    request.getSession().setAttribute("callMstThroughWeb", null);
                    agentName = "";
                }
            } else {

                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR : Please rate all star marked feedback questions", "Please rate all star marked feedback questions"));

            }
        } catch (ParseException e) {
            logger.info(" Error " + e.getMessage());
        }

        return "faces/customerHome.xtml";
    }

    public BufferedImage getImageFromSnapShot(String binaryImages) {
        String imageString[] = binaryImages.split(",");
        logger.info("imageString===" + imageString[0]);
        BufferedImage image = null;
        byte[] imageByte;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            imageByte = decoder.decodeBuffer(imageString[1]);

            try (ByteArrayInputStream bis = new ByteArrayInputStream(imageByte)) {
                image = ImageIO.read(bis);
            }
        } catch (IOException ex) {

        }
        return image;
    }

    //TODO
    public void handleSaveFromSnap() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();
        ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String deploymentDirectoryPath = ctx.getRealPath("/");
        try {
            CallMst callMster = (CallMst) session.getAttribute("callMstThroughWeb");
            if (null != callMster) {
                Random rand = new Random();
                int no = rand.nextInt(1000) + 1;
                customerMstService.findCustomerMstByCustId(callMster.getCustId());
                BufferedImage image = this.getImageFromSnapShot(this.binaryImage);

// write the image to a file
                String imagePath = no + "snapshot.jpg";

                String tempFilePath = File.separator + "File_Upload" + File.separator + "Call" + File.separator
                        + callMster.getId() + File.separator + imagePath;

                File ff = new File(deploymentDirectoryPath + "/resources" + tempFilePath);
                File n = new File(deploymentDirectoryPath + "/resources" + File.separator + "File_Upload" + File.separator + "Call" + File.separator
                        + callMster.getId());
                if (!n.exists()) {
                    n.mkdirs();
                }
                //File outputfile = new File("E:\\Learning\\image3.jpg");
                ImageIO.write(image, "png", ff);
                request.getRequestURL().substring(0, request.getRequestURL().indexOf("/", 9));

                if (null != callMster.getCustId() && !"".equals(callMster.getCustId())) {
                    List<CallDtl> cdtlist = callDtlService.findCallDtlByCallMasterId(callMster.getId());
                    if (!cdtlist.isEmpty()) {
                        EmployeeMst em = employeeMstService.findEmployeeMstById(cdtlist.get(0).getHandeledById().getId());
                        try {
                            //logger.info("Before Send Natinal ID===" + no + upFile.getFileName() + " SEND TO===" + em.getLoginId());
                            SocketMessage.send(callScheduler.getAdminSocket(), em.getLoginId(), "selfImageSend#" + imagePath);
                        } catch (Exception ex) {
                            logger.error(ex);
                        }
                    }
                }

                String jbossHome = System.getenv("JBOSS_HOME");

                String path = jbossHome + File.separator + "standalone" + tempFilePath;
                n = new File(jbossHome + File.separator + "standalone" + File.separator + "File_Upload" + File.separator + "Call" + File.separator
                        + callMster.getId());
                if (!n.exists()) {
                    n.mkdirs();
                }
                File ff2 = new File(path);
                ImageIO.write(image, "png", ff2);

            }

        } catch (IOException ex) {
            Logger.getLogger(VidyoAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String giveFeedBackOfCallRegistered(HttpServletRequest request) {
        try {
            boolean validFeedback = true;
            agentName = "";
            logger.info("" + quality + "," + ability + "," + recommend);
            if (quality == null || quality.trim().equals("")) {
                validFeedback = false;
            }
            if (ability == null || ability.trim().equals("")) {
                validFeedback = false;
            }
            if (recommend == null || recommend.trim().equals("")) {
                validFeedback = false;
            }

            if (validFeedback) {
                CallMst localCallMaster = (CallMst) request.getSession().getAttribute("callMstThroughWeb");
                if (null != localCallMaster) {
                    localCallMaster = callMstService.findCallMstById(localCallMaster.getId());
                    FeedbackDtl feedbackdtl = new FeedbackDtl();
                    feedbackdtl.setCallMstId(localCallMaster);
                    feedbackdtl.setActiveFlg(true);
                    feedbackdtl.setCustomerId(localCallMaster.getCustomerId());
                    feedbackdtl.setDeleteFlg(false);
                    feedbackdtl.setFeedbackParamId((long) 0);
                    feedbackdtl.setComments(this.feedback);
                    feedbackdtl.setStarRating(this.quality + "~" + this.ability + "~" + this.recommend);
                    feedbackdtl.setFeedbackQuery(this.qualityQuery + "~" + this.abilityQuery + "~" + this.recommendQuery);
                    feedbackdtl.setFeedbackDate(CustomConvert.javaDateToTimeStamp(new Date()));
                    feedbackdtl = feedbackDtlService.saveFeedbackDtl(feedbackdtl, null);

                    if (feedbackdtl != null) {
                        //request.getSession().setAttribute("callMstThroughWeb", null);

                        this.setCustomerName("");
                        this.setCustomerEmail("");
                        this.setCustomerPhoneNumber("");

                        this.quality = "";
                        this.ability = "";
                        this.recommend = "";
                        resetData();
                        FacesContext.getCurrentInstance().addMessage(null,
                                new FacesMessage(FacesMessage.SEVERITY_INFO, "Thank you for your interest. It was our pleasure to assist you !", "Thank you for your interest. It was our pleasure to assist you!"));
                        logger.info("call null 7");
                        request.getSession().setAttribute("callMstThroughWeb", null);
                        request.getSession().setAttribute("scheduleCallLocal", null);
                        RequestContext.getCurrentInstance().execute("PF('feedbackdialog').hide();redirectHome()");

                    } else {
                        FacesContext.getCurrentInstance().addMessage(null,
                                new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR : Please Try Again", "Please Try Again."));

                    }
                } else {
                    RequestContext.getCurrentInstance().execute("PF('feedbackdialog').hide();");
                    logger.info("call null 8");
                    request.getSession().setAttribute("callMstThroughWeb", null);
                    agentName = "";
                }
            } else {

                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR : Please rate all star marked feedback questions", "Please rate all star marked feedback questions"));

            }
        } catch (ParseException e) {
            logger.info(" Error " + e.getMessage());
        } finally {
            RequestContext.getCurrentInstance().execute("refreshEssentials()");
        }
        return "faces/pages/customer/customerVideoCall.xhtml";

    }

    public void onrate(RateEvent rateEvent) {
        setRating((Long) rateEvent.getRating());

    }

    public void presentDate() {
        this.schDateTime = new Date();

    }

    public void oncancel() {

        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cancel Event", "Rate Reset");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public List<CustomerCallDto> getListDroppedCall() {
        return listDroppedCall;
    }

    public void setListDroppedCall(List<CustomerCallDto> listDroppedCall) {
        this.listDroppedCall = listDroppedCall;
    }

    public List<CustomerCallDto> getListAbandonCall() {
        return listAbandonCall;
    }

    public void setListAbandonCall(List<CustomerCallDto> listAbandonCall) {
        this.listAbandonCall = listAbandonCall;
    }

    public List<CustomerCallDto> getListSuccessfulCall() {
        return listSuccessfulCall;
    }

    public void setListSuccessfulCall(List<CustomerCallDto> listSuccessfulCall) {
        this.listSuccessfulCall = listSuccessfulCall;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<CustomerDetails> getListCustomerDetails() {
        return listCustomerDetails;
    }

    public void setListCustomerDetails(List<CustomerDetails> listCustomerDetails) {
        this.listCustomerDetails = listCustomerDetails;
    }

    public List<CustomerDetails> getSelectRecords() {
        return selectRecords;
    }

    public void setSelectRecords(List<CustomerDetails> selectRecords) {
        this.selectRecords = selectRecords;
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

    public boolean isExportToCsvBtnStatus() {
        return exportToCsvBtnStatus;
    }

    public void setExportToCsvBtnStatus(boolean exportToCsvBtnStatus) {
        this.exportToCsvBtnStatus = exportToCsvBtnStatus;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getOutputTextToExport() {
        return outputTextToExport;
    }

    public void setOutputTextToExport(String outputTextToExport) {
        this.outputTextToExport = outputTextToExport;
    }

    public List<CategoryMst> getCategoryMstList() {
        return categoryMstList;
    }

    public void setCategoryMstList(List<CategoryMst> categoryMstList) {
        this.categoryMstList = categoryMstList;
    }

    public List<ServiceMst> getServiceMstList() {
        return serviceMstList;
    }

    public void setServiceMstList(List<ServiceMst> serviceMstList) {
        this.serviceMstList = serviceMstList;
    }

    public List<LanguageMst> getLanguageMstList() {
        return languageMstList;
    }

    public void setLanguageMstList(List<LanguageMst> languageMstList) {
        this.languageMstList = languageMstList;
    }

    public Long getSelectedCategoryName() {
        return selectedCategoryName;
    }

    public void setSelectedCategoryName(Long selectedCategoryName) {
        this.selectedCategoryName = selectedCategoryName;

    }

    public Long getSelectedServiceName() {
        return selectedServiceName;
    }

    public void setSelectedServiceName(Long selectedServiceName) {
        this.selectedServiceName = selectedServiceName;
    }

    public Long getSelectedLanguageName() {
        return selectedLanguageName;
    }

    public void setSelectedLanguageName(Long selectedLanguageName) {
        this.selectedLanguageName = selectedLanguageName;
    }

    public CallMst getCallMst() {
        return callMst;
    }

    public void setCallMst(CallMst callMst) {
        this.callMst = callMst;
    }

    public String getRoomUrl() {
        return roomUrl;
    }

    public void setRoomUrl(String roomUrl) {
        this.roomUrl = roomUrl;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public ConferenceAgentsDto getConferenceAgentsDto() {
        return conferenceAgentsDto;
    }

    public void setConferenceAgentsDto(ConferenceAgentsDto conferenceAgentsDto) {
        this.conferenceAgentsDto = conferenceAgentsDto;
    }

    public List<ConferenceAgentsDto> getConferenceAgentsDtoList() {
        return conferenceAgentsDtoList;
    }

    public void setConferenceAgentsDtoList(List<ConferenceAgentsDto> conferenceAgentsDtoList) {
        this.conferenceAgentsDtoList = conferenceAgentsDtoList;
    }

    public String getFileUploadMsg() {
        return fileUploadMsg;
    }

    public void setFileUploadMsg(String fileUploadMsg) {
        this.fileUploadMsg = fileUploadMsg;
    }

    public Integer getIdAgent() {
        return idAgent;
    }

    public void setIdAgent(Integer idAgent) {
        this.idAgent = idAgent;
    }

    public UploadedFile getUpFile() {
        return upFile;
    }

    public void setUpFile(UploadedFile upFile) {
        this.upFile = upFile;
    }

    public Boolean getUpload() {
        return upload;
    }

    public void setUpload(Boolean upload) {
        this.upload = upload;
    }

    public Boolean getFileDialogdisplay() {
        return fileDialogdisplay;
    }

    public void setFileDialogdisplay(Boolean fileDialogdisplay) {
        this.fileDialogdisplay = fileDialogdisplay;
    }

    public Boolean getFileuploadcompleted() {
        return fileuploadcompleted;
    }

    public void setFileuploadcompleted(Boolean fileuploadcompleted) {
        this.fileuploadcompleted = fileuploadcompleted;
    }

    public int getPollinterval() {
        return pollinterval;
    }

    public void setPollinterval(int pollinterval) {
        this.pollinterval = pollinterval;
    }

    public Date getSchDateTime() {
        return schDateTime;
    }

    public void setSchDateTime(Date schDateTime) {
        this.schDateTime = schDateTime;
    }

    public Boolean getDiasableEndCallButton() {
        return diasableEndCallButton;
    }

    public void setDiasableEndCallButton(Boolean diasableEndCallButton) {
        this.diasableEndCallButton = diasableEndCallButton;
    }

    public Boolean getDisableUploadFileButton() {
        return disableUploadFileButton;
    }

    public void setDisableUploadFileButton(Boolean disableUploadFileButton) {
        this.disableUploadFileButton = disableUploadFileButton;
    }

    public Boolean getDisableCallButton() {
        return disableCallButton;
    }

    public void setDisableCallButton(Boolean disableCallButton) {
        this.disableCallButton = disableCallButton;
    }

    public Boolean getStatusOfEndCall() {
        return statusOfEndCall;
    }

    public void setStatusOfEndCall(Boolean statusOfEndCall) {
        this.statusOfEndCall = statusOfEndCall;
    }

    public Long getRating() {
        return rating;
    }

    public void setRating(Long rating) {
        this.rating = rating;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getScheduleText() {
        return scheduleText;
    }

    public void setScheduleText(String scheduleText) {
        this.scheduleText = scheduleText;
    }

    public int getRetryCallIntValue() {
        return retryCallIntValue;
    }

    public void setRetryCallIntValue(int retryCallIntValue) {
        this.retryCallIntValue = retryCallIntValue;
    }

    public Boolean getShowDialogs() {
        return showDialogs;
    }

    public void setShowDialogs(Boolean showDialogs) {
        this.showDialogs = showDialogs;
    }

    public Boolean getImageuploadrenderer() {
        return imageuploadrenderer;
    }

    public void setImageuploadrenderer(Boolean imageuploadrenderer) {
        this.imageuploadrenderer = imageuploadrenderer;
    }

    public Boolean getDocuploadrenderer() {
        return docuploadrenderer;
    }

    public void setDocuploadrenderer(Boolean docuploadrenderer) {
        this.docuploadrenderer = docuploadrenderer;
    }

    public Boolean getIdcarduploadrenderer() {
        return idcarduploadrenderer;
    }

    public void setIdcarduploadrenderer(Boolean idcarduploadrenderer) {
        this.idcarduploadrenderer = idcarduploadrenderer;
    }

    public Boolean getAddproofuploadrenderer() {
        return addproofuploadrenderer;
    }

    public void setAddproofuploadrenderer(Boolean addproofuploadrenderer) {
        this.addproofuploadrenderer = addproofuploadrenderer;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
//TODO

    public void handleNationalIdUpload(FileUploadEvent event) {
        HttpSession session = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession(false);
        boolean valid = true;
        UploadedFile uploadedfile = event.getFile();
        BufferedOutputStream outputStream;
        FileOutputStream fos;
        BufferedOutputStream outputStream2;
        FileOutputStream fos2;
        boolean flag = false;
        boolean pdfflag = false;

        CallMst callMster = (CallMst) session.getAttribute("callMstThroughWeb");

        if (uploadedfile == null || uploadedfile.getSize() == 0) {
            valid = false;
            fileUploadMsg = "Please select file";
            FacesMessage message = new FacesMessage(fileUploadMsg);
            FacesContext.getCurrentInstance().addMessage(null, message);

        } else if (uploadedfile.getFileName() == null || uploadedfile.getFileName().equalsIgnoreCase("") || uploadedfile.getFileName().isEmpty()) {
            valid = false;
            fileUploadMsg = "File Type Not Supported. Please select correct file.";
            FacesMessage message = new FacesMessage(fileUploadMsg);
            FacesContext.getCurrentInstance().addMessage(null, message);

        } else {

            String fn = uploadedfile.getFileName();
            String parts[] = fn.split(Pattern.quote("."));
            String ext = parts[1];

            if (ext.equalsIgnoreCase("jpeg") || ext.equalsIgnoreCase("exif") || ext.equalsIgnoreCase("tiff") || ext.equalsIgnoreCase("rif") || ext.equalsIgnoreCase("bmp") || ext.equalsIgnoreCase("png") || ext.equalsIgnoreCase("webp") || ext.equalsIgnoreCase("jpg")) {

                if (uploadedfile.getSize() > 2097152) {
                    valid = false;
                    fileUploadMsg = "Please upload image of size less than 2MB.";

                    FacesMessage message = new FacesMessage(fileUploadMsg);
                    FacesContext.getCurrentInstance().addMessage(null, message);
                }
            } else if (ext.equalsIgnoreCase("pdf")) {

                if (uploadedfile.getSize() > 2097152) {
                    valid = false;
                    fileUploadMsg = "Please upload image of size less than 2MB.";

                    FacesMessage message = new FacesMessage(fileUploadMsg);
                    FacesContext.getCurrentInstance().addMessage(null, message);
                }
                pdfflag = true;
            } else {
                valid = false;
                fileUploadMsg = "File extension not supported";
                FacesMessage message = new FacesMessage(fileUploadMsg);
                FacesContext.getCurrentInstance().addMessage(null, message);
            }

        }
        if (valid) {
            upFile = uploadedfile;
            byte[] fileContentInByte;
            fileContentInByte = upFile.getContents();
            if (null != callMster.getCustId() && !"".equals(callMster.getCustId())) {
                ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
                String deploymentDirectoryPath = ctx.getRealPath("/");
                String jbossHome = System.getenv("JBOSS_HOME");
                Random rand = new Random();
                int no = rand.nextInt(1000) + 1;
                String tempFilePath = File.separator + "File_Upload" + File.separator + "Call" + File.separator
                        + callMster.getId() + File.separator + no + upFile.getFileName();

                String path = jbossHome + File.separator + "standalone" + tempFilePath;
                File n = new File(jbossHome + File.separator + "standalone" + File.separator + "File_Upload" + File.separator + "Call" + File.separator
                        + callMster.getId());
                if (!n.exists()) {
                    n.mkdirs();
                }
                try {
                    n = new File(deploymentDirectoryPath + "/resources" + File.separator + "File_Upload" + File.separator + "Call" + File.separator
                            + callMster.getId());
                    if (!n.exists()) {
                        n.mkdirs();
                    }
                    fos = new FileOutputStream(deploymentDirectoryPath + "/resources" + tempFilePath);
                    outputStream = new BufferedOutputStream(fos);
                    outputStream.write(fileContentInByte);
                    outputStream.close();
                    if (!pdfflag) {
                        if (null != upFile.getFileName()) {
                            List<CallDtl> cdtlist = callDtlService.findCallDtlByCallMasterId(callMster.getId());
                            if (!cdtlist.isEmpty()) {
                                EmployeeMst em = employeeMstService.findEmployeeMstById(cdtlist.get(0).getHandeledById().getId());
                                try {
                                    String uploadedPan=deploymentDirectoryPath + "/resources" + File.separator + "File_Upload" + File.separator + "Call" + File.separator
                                            + callMster.getId() + File.separator + no + upFile.getFileName();
//                                    File ff = new File(uploadedPan);
//                                    HttpClient httpclient = new DefaultHttpClient();
//                                    HttpPost httpPost = new HttpPost(Constants.ocrUrl + "task/detect");
//                                    //httpPost.addHeader("Content-type", "multipart/form-data");
//                                    //StringBody langBody = new StringBody(LANG , ContentType.TEXT_PLAIN);
//                                    FileBody uploadFilePart = new FileBody(ff);
//                                    MultipartEntity reqEntity = new MultipartEntity();
//                                    reqEntity.addPart("docImg", uploadFilePart);
//                                    reqEntity.addPart("app_id", new StringBody(Constants.appId));
//                                    reqEntity.addPart("extract", new StringBody("true"));
//                                    reqEntity.addPart("type", new StringBody("pan"));
//                                    httpPost.setEntity(reqEntity);
//
//                                    HttpResponse response1 = httpclient.execute(httpPost);
//                                    HttpEntity responseEntity = response1.getEntity();
//                                    String response = "Failure";
//                                    if (responseEntity != null) {
//                                        response = EntityUtils.toString(responseEntity);
//                                    }
//                                    logger.info("Response==========" + response);
//
//                                    JSONObject jsonObject;
//                                    //try {
//                                    jsonObject = new JSONObject(response);
//                                    if (jsonObject.getBoolean("success")) {
//                                        JSONObject jsObject = new JSONObject(jsonObject.getString("data"));
                                        SocketMessage.send(callScheduler.getAdminSocket(), em.getLoginId(), "idCardSend#" + no + upFile.getFileName());
//                                    }

                                    //logger.info("Before Send Natinal ID===" + no + upFile.getFileName() + " SEND TO===" + em.getLoginId());
                                    flag = true;
                                } catch (Exception ex) {
                                    logger.error(ex);
                                }
                            }

                        }
                    } else {
                        String sourceDir = deploymentDirectoryPath + "/resources" + tempFilePath; // Pdf files are read from this folder
                        String destinationDir = deploymentDirectoryPath + "/resources" + File.separator + "File_Upload" + File.separator + "Call" + File.separator
                                + callMster.getId(); // converted images from pdf document are saved here

                        File sourceFile = new File(sourceDir);
                        File destinationFile = new File(destinationDir);
                        if (!destinationFile.exists()) {
                            destinationFile.mkdir();
                            //logger.info("Folder Created -> "+ destinationFile.getAbsolutePath());
                        }
                        if (sourceFile.exists()) {
                            //logger.info("Images copied to Folder: "+ destinationFile.getName());             
                            PDDocument document = PDDocument.load(sourceDir);
                            List<PDPage> list = document.getDocumentCatalog().getAllPages();
                            //logger.info("Total files to be converted -> "+ list.size());

                            String fileNameL = sourceFile.getName().replace(".pdf", "");
                            for (PDPage page : list) {
                                BufferedImage image = page.convertToImage();
                                File outputfile = new File(destinationDir + File.separator + no + fileNameL + ".jpg");
                                n = new File(destinationDir);
                                if (!n.exists()) {
                                    n.mkdirs();
                                }
                                //logger.info("Image Created -> "+ outputfile.getName());
                                ImageIO.write(image, "png", outputfile);

                                String path1 = jbossHome + File.separator + "standalone" + File.separator + "File_Upload" + File.separator + "Call" + File.separator
                                        + callMster.getId() + File.separator + no + fileNameL + ".jpg";
                                n = new File(jbossHome + File.separator + "standalone" + File.separator + "File_Upload" + File.separator + "Call" + File.separator
                                        + callMster.getId());
                                if (!n.exists()) {
                                    n.mkdirs();
                                }
                                File ff2 = new File(path1);
                                ImageIO.write(image, "png", ff2);
                            }
                            document.close();

                            if (null != upFile.getFileName()) {
                                List<CallDtl> cdtlist = callDtlService.findCallDtlByCallMasterId(callMster.getId());
                                if (!cdtlist.isEmpty()) {
                                    EmployeeMst em = employeeMstService.findEmployeeMstById(cdtlist.get(0).getHandeledById().getId());
                                    try {
                                        String uploadedPan=deploymentDirectoryPath + "/resources" + File.separator + "File_Upload" + File.separator + "Call" + File.separator
                                               + callMster.getId() + File.separator + no + upFile.getFileName();
//                                        File ff = new File(deploymentDirectoryPath + "/resources" + File.separator + "File_Upload" + File.separator + "Call" + File.separator
//                                                + callMster.getId() + File.separator + no + upFile.getFileName());
//                                        HttpClient httpclient = new DefaultHttpClient();
//                                        HttpPost httpPost = new HttpPost(Constants.ocrUrl + "task/detect");
//                                        //httpPost.addHeader("Content-type", "multipart/form-data");
//                                        //StringBody langBody = new StringBody(LANG , ContentType.TEXT_PLAIN);
//                                        FileBody uploadFilePart = new FileBody(ff);
//                                        MultipartEntity reqEntity = new MultipartEntity();
//                                        reqEntity.addPart("docImg", uploadFilePart);
//                                        reqEntity.addPart("app_id", new StringBody(Constants.appId));
//                                        reqEntity.addPart("extract", new StringBody("true"));
//                                        reqEntity.addPart("type", new StringBody("pan"));
//                                        httpPost.setEntity(reqEntity);
//
//                                        HttpResponse response1 = httpclient.execute(httpPost);
//                                        HttpEntity responseEntity = response1.getEntity();
//                                        String response = "Failure";
//                                        if (responseEntity != null) {
//                                            response = EntityUtils.toString(responseEntity);
//                                        }
//                                        logger.info("Response==========" + response);
//
//                                        JSONObject jsonObject;
//                                        //try {
//                                        jsonObject = new JSONObject(response);
//                                        if (jsonObject.getBoolean("success")) {
//                                            JSONObject jsObject = new JSONObject(jsonObject.getString("data"));
                                            SocketMessage.send(callScheduler.getAdminSocket(), em.getLoginId(), "idCardSend#" + no + upFile.getFileName() + "@" + uploadedPan);
//                                        }

                                        //logger.info("Before Send Natinal ID===" + no + upFile.getFileName() + " SEND TO===" + em.getLoginId());
                                        flag = true;
                                    } catch (Exception ex) {
                                        logger.error(ex);
                                    }
                                }
                            }
                            //logger.info("Converted Images are saved at -> "+ destinationFile.getAbsolutePath());
                        } else {
                            System.err.println(sourceFile.getName() + " File not exists");
                        }
                    }

                    fos2 = new FileOutputStream(path);
                    outputStream2 = new BufferedOutputStream(fos2);
                    outputStream2.write(fileContentInByte);
                    outputStream2.close();

                    if (flag) {
                        fileUploadMsg = "Identity Card  Sent SuccessFully";
                        RequestContext.getCurrentInstance().execute("closeAllDialog();PF('fileuploadsuccess').show();setTimeout(function(){PF('fileuploadsuccess').hide() }, 4000);");
                    } else {
                        fileUploadMsg = " Identity Card  Not Sent SuccessFully";
                    }

                } catch (IOException ex) {
                    fileUploadMsg = " Not Sent SuccessFully";
                    System.err.println(ex);

                } catch (Exception e) {
                    fileUploadMsg = " Not Sent SuccessFully";

                } finally {
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO, fileUploadMsg, ""));

                }
            }
        }

    }

    //TODO
    public void handleAddressProofUpload(FileUploadEvent event) {
        HttpSession session = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession(false);
        boolean valid = true;
        UploadedFile uploadedfile = event.getFile();
        BufferedOutputStream outputStream;
        FileOutputStream fos;
        BufferedOutputStream outputStream2;
        FileOutputStream fos2;
        boolean flag = false;
        boolean oldflag = false;
        boolean pdfflag = false;

        CallMst callMster = (CallMst) session.getAttribute("callMstThroughWeb");

        if (uploadedfile == null || uploadedfile.getSize() == 0) {
            valid = false;
            fileUploadMsg = "Please select file";
            FacesMessage message = new FacesMessage(fileUploadMsg);
            FacesContext.getCurrentInstance().addMessage(null, message);

        } else if (uploadedfile.getFileName() == null || uploadedfile.getFileName().equalsIgnoreCase("") || uploadedfile.getFileName().isEmpty()) {
            valid = false;
            fileUploadMsg = "File Type Not Supported. Please select correct file.";
            FacesMessage message = new FacesMessage(fileUploadMsg);
            FacesContext.getCurrentInstance().addMessage(null, message);

        } else {

            String fn = uploadedfile.getFileName();
            String parts[] = fn.split(Pattern.quote("."));
            String ext = parts[1];

            if (ext.equalsIgnoreCase("xml") || ext.equalsIgnoreCase("jpeg") || ext.equalsIgnoreCase("exif") || ext.equalsIgnoreCase("tiff") || ext.equalsIgnoreCase("rif") || ext.equalsIgnoreCase("bmp") || ext.equalsIgnoreCase("png") || ext.equalsIgnoreCase("webp") || ext.equalsIgnoreCase("jpg")) {

                if (uploadedfile.getSize() > 2097152) {
                    valid = false;
                    fileUploadMsg = "Please upload image of size less than 2MB.";

                    FacesMessage message = new FacesMessage(fileUploadMsg);
                    FacesContext.getCurrentInstance().addMessage(null, message);
                }
            } else if (ext.equalsIgnoreCase("pdf")) {

                if (uploadedfile.getSize() > 2097152) {
                    valid = false;
                    fileUploadMsg = "Please upload image of size less than 2MB.";

                    FacesMessage message = new FacesMessage(fileUploadMsg);
                    FacesContext.getCurrentInstance().addMessage(null, message);
                }
                pdfflag = true;
            } else {
                valid = false;
                fileUploadMsg = "File extension not supported";
                FacesMessage message = new FacesMessage(fileUploadMsg);
                FacesContext.getCurrentInstance().addMessage(null, message);
            }

        }
        if (valid) {
            upFile = uploadedfile;
            byte[] fileContentInByte;
            fileContentInByte = upFile.getContents();
            if (null != callMster.getCustId() && !"".equals(callMster.getCustId())) {
                ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
                String deploymentDirectoryPath = ctx.getRealPath("/");
                String jbossHome = System.getenv("JBOSS_HOME");
                Random rand = new Random();
                int no = rand.nextInt(1000) + 1;
                String tempFilePath = File.separator + "File_Upload" + File.separator + "Call" + File.separator
                        + callMster.getId() + File.separator + no + upFile.getFileName();

                String path = jbossHome + File.separator + "standalone" + tempFilePath;
                File n = new File(jbossHome + File.separator + "standalone" + File.separator + "File_Upload" + File.separator + "Call" + File.separator
                        + callMster.getId());
                if (!n.exists()) {
                    n.mkdirs();
                }
                try {
                    n = new File(deploymentDirectoryPath + "/resources" + File.separator + "File_Upload" + File.separator + "Call" + File.separator
                            + callMster.getId());
                    if (!n.exists()) {
                        n.mkdirs();
                    }
                    fos = new FileOutputStream(deploymentDirectoryPath + "/resources" + tempFilePath);
                    outputStream = new BufferedOutputStream(fos);
                    outputStream.write(fileContentInByte);
                    outputStream.close();
                    if (!pdfflag) {
                        if (null != upFile.getFileName()) {
                            List<CallDtl> cdtlist = callDtlService.findCallDtlByCallMasterId(callMster.getId());
                            if (!cdtlist.isEmpty()) {
                                String Gender = "";
                                String Address = "";
                                String Country = "";
                                EmployeeMst em = employeeMstService.findEmployeeMstById(cdtlist.get(0).getHandeledById().getId());
                                try {
                                    File ff = new File(deploymentDirectoryPath + "/resources" + File.separator + "File_Upload" + File.separator + "Call" + File.separator
                                            + callMster.getId() + File.separator + no + upFile.getFileName());
                                    //File xmlFile = new File(ff);
                                    ThreadSafeSimpleDateFormat dateFormat = new ThreadSafeSimpleDateFormat("yyyyMMdd");
                                    try {
                                        SAXBuilder builder = new SAXBuilder();
                                        Document anotherDocument = builder.build(ff);
                                        Element rootNode = anotherDocument.getRootElement();

                                       
                                        Date createDate = dateFormat.parse(rootNode.getAttributeValue("referenceId").substring(4, 12));
                                        Date currentDate = new Date();
                                        long diff = currentDate.getTime() - createDate.getTime();
                                        long diference = diff / (1000 * 60 * 60 * 24);
                                        
                                        if (diference <= 3) {
                                            Element UidDataNode = rootNode.getChild("UidData");
                                            System.out.println("UidData element :" + UidDataNode.getChild("Poi").getAttributeValue("dob"));
                                            Gender = UidDataNode.getChild("Poi").getAttributeValue("gender");
                                            Address = UidDataNode.getChild("Poa").getAttributeValue("careof") + " "
                                                    + UidDataNode.getChild("Poa").getAttributeValue("house") + " "
                                                    + UidDataNode.getChild("Poa").getAttributeValue("street") + " "
                                                    + UidDataNode.getChild("Poa").getAttributeValue("landmark") + " "
                                                    + UidDataNode.getChild("Poa").getAttributeValue("po") + " "
                                                    + UidDataNode.getChild("Poa").getAttributeValue("vtc") + " "
                                                    + UidDataNode.getChild("Poa").getAttributeValue("subdist") + " "
                                                    + UidDataNode.getChild("Poa").getAttributeValue("dist") + " "
                                                    + UidDataNode.getChild("Poa").getAttributeValue("pc");
                                            Country = UidDataNode.getChild("Poa").getAttributeValue("country");
                                            flag = true;
                                            oldflag = false;
                                        } else {
                                            fileUploadMsg = " File is not current";
                                            flag = false;
                                            oldflag = true;
                                        }

                                    } catch (IOException e1) {
                                    } catch (JDOMException | ParseException ex) {
                                        Logger.getLogger(CustomerComponent.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    if (flag) {
                                        SocketMessage.send(callScheduler.getAdminSocket(), em.getLoginId(), "addProofSend#" + Gender + "@" + Address + "@" + Country);
                                        flag = true;
                                    }
                                } catch (Exception ex) {
                                    logger.error(ex);
                                }
                            }
                        }
                    } else {
                        String sourceDir = deploymentDirectoryPath + "/resources" + tempFilePath; // Pdf files are read from this folder
                        String destinationDir = deploymentDirectoryPath + "/resources" + File.separator + "File_Upload" + File.separator + "Call" + File.separator
                                + callMster.getId(); // converted images from pdf document are saved here

                        File sourceFile = new File(sourceDir);
                        File destinationFile = new File(destinationDir);
                        if (!destinationFile.exists()) {
                            destinationFile.mkdir();
                            //logger.info("Folder Created -> "+ destinationFile.getAbsolutePath());
                        }
                        if (sourceFile.exists()) {
                            //logger.info("Images copied to Folder: "+ destinationFile.getName());             
                            PDDocument document = PDDocument.load(sourceDir);
                            List<PDPage> list = document.getDocumentCatalog().getAllPages();
                            //logger.info("Total files to be converted -> "+ list.size());

                            String fileNameL = sourceFile.getName().replace(".pdf", "");
                            for (PDPage page : list) {
                                BufferedImage image = page.convertToImage();
                                File outputfile = new File(destinationDir + File.separator + no + fileNameL + ".jpg");
                                n = new File(destinationDir);
                                if (!n.exists()) {
                                    n.mkdirs();
                                }
                                //logger.info("Image Created -> "+ outputfile.getName());
                                ImageIO.write(image, "png", outputfile);

                                String path1 = jbossHome + File.separator + "standalone" + File.separator + "File_Upload" + File.separator + "Call" + File.separator
                                        + callMster.getId() + File.separator + no + fileNameL + ".jpg";
                                n = new File(jbossHome + File.separator + "standalone" + File.separator + "File_Upload" + File.separator + "Call" + File.separator
                                        + callMster.getId());
                                if (!n.exists()) {
                                    n.mkdirs();
                                }
                                File ff2 = new File(path1);
                                ImageIO.write(image, "png", ff2);
                            }
                            document.close();

                            if (null != upFile.getFileName()) {
                                List<CallDtl> cdtlist = callDtlService.findCallDtlByCallMasterId(callMster.getId());
                                if (!cdtlist.isEmpty()) {
                                    EmployeeMst em = employeeMstService.findEmployeeMstById(cdtlist.get(0).getHandeledById().getId());
                                    try {
                                        SocketMessage.send(callScheduler.getAdminSocket(), em.getLoginId(), "addProofSend#" + no + fileNameL + ".jpg");
                                        flag = true;
                                    } catch (Exception ex) {
                                        logger.error(ex);
                                    }
                                }
                            }
                            //logger.info("Converted Images are saved at -> "+ destinationFile.getAbsolutePath());
                        } else {
                            System.err.println(sourceFile.getName() + " File not exists");
                        }
                    }

                    fos2 = new FileOutputStream(path);
                    outputStream2 = new BufferedOutputStream(fos2);
                    outputStream2.write(fileContentInByte);
                    outputStream2.close();

                    if (flag) {
                        fileUploadMsg = "File Sent SuccessFully";
                        RequestContext.getCurrentInstance().execute("closeAllDialog();PF('fileuploadsuccess').show();setTimeout(function(){PF('fileuploadsuccess').hide() }, 4000);");
                    } else if (oldflag) {
                        fileUploadMsg = " File is not current";
                    } else {
                        fileUploadMsg = " File Not Sent SuccessFully";
                    }

                } catch (IOException ex) {
                    fileUploadMsg = " Not Sent SuccessFully";
                    System.err.println(ex);

                } catch (Exception e) {
                    fileUploadMsg = " Not Sent SuccessFully";

                } finally {
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO, fileUploadMsg, ""));

                }
            }
        }

    }

    public void fileUploadListener(FileUploadEvent event) {
        try {

            if (event != null) {
                HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                CallMst callMster = (CallMst) request.getSession().getAttribute("callMstThroughWeb");
                String websiteURL = request.getRequestURL().substring(0, request.getRequestURL().indexOf("/", 9));
                boolean flag = false;
                Set<ConferenceAgentsDto> conferenceAgentsDtoListForParticipantCallDtlsSelectionSet = new HashSet(conferenceAgentsDtoListForParticipantCallDtlsSelection);
                String filePathForDatabase;
                Long result;
                String filePathToSend;

                String jbossHome = System.getenv("JBOSS_HOME");
                if (conferenceAgentsDtoListForParticipantCallDtls.size() > 1 && conferenceAgentsDtoListForParticipantCallDtlsSelectionSet.isEmpty()) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "ERROR : ", "Please Select Participant(s)"));
                    //RequestContext.getCurrentInstance().execute("PF('fileuploadvar').reset();");
                    return;
                }
                if (docTitle != null && !docTitle.isEmpty()) {

                    String fName;
                    UploadedFile upfile = event.getFile();

                    if (upfile != null && upfile.getSize() > 0) {
                        fName = upfile.getFileName();

                        String[] matches = new String[]{"~", "#", "%", "&", "*", "{", "}", "\\", ":", "<", ">", "?", "/", "+", "|"};

                        int checkFilename = 0;
                        for (String s : matches) {
                            if (fName.contains(s)) {
                                checkFilename = 1;
                                break;
                            }
                        }

                        if (checkFilename > 0) {
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                    "ERROR : ", "Filename Is Not Correct, Contains Special Character like \"~\", \"#\", \"%\", \"&\", \"*\", \"{\", \"}\", \"\\\\\", \":\", \"<\", \">\", \"?\", \"/\", \"+\", \"|\""));
                            return;
                        }

                        Random rand = new Random();
                        int no = rand.nextInt(1000) + 1;
                        /*FILE BACKUP*/
                        String backupFileLocation = jbossHome + File.separator + "standalone" + File.separator + "File_Upload" + File.separator + "Call" + File.separator + callMster.getId() + File.separator + no + fName;

                        File backupFile = new File(backupFileLocation);
                        FileUtils.copyInputStreamToFile(event.getFile().getInputstream(), backupFile);
                        /*FILE COPY*/
                        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
                        String deploymentDirectoryPath = servletContext.getRealPath(File.separator);

                        String finalFilePath = deploymentDirectoryPath + "/resources" + File.separator + "File_Upload" + File.separator + "Call" + File.separator + callMster.getId() + File.separator + no + fName;

                        File finalFile = new File(finalFilePath);
                        FileUtils.copyFile(backupFile, finalFile);

                        filePathForDatabase = "/resources/File_Upload/Call/" + callMster.getId() + "/" + no + fName;

                        filePathToSend = websiteURL + request.getContextPath() + filePathForDatabase;

                        if (!conferenceAgentsDtoListForParticipantCallDtlsSelectionSet.isEmpty() && conferenceAgentsDtoListForParticipantCallDtlsSelectionSet.size() > 0) {
                            for (ConferenceAgentsDto obj : conferenceAgentsDtoListForParticipantCallDtlsSelectionSet) {
                                CallFileUploadDtls callFileUploadDtls = new CallFileUploadDtls();
                                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                                callFileUploadDtls.setCreatedDateTime(timestamp);
                                callFileUploadDtls.setFileReceivedBy(obj.getEmpId());
                                callFileUploadDtls.setCallMstId(callMster);
                                callFileUploadDtls.setFileSentBy(callMster.getCustomerId().getId());
                                callFileUploadDtls.setFilePath(filePathForDatabase);
                                callFileUploadDtls.setCreatedBy(callMster.getCustomerId().getId());
                                callFileUploadDtls.setFileSentbyType("CUSTOMER");
                                callFileUploadDtls.setFileReceivedbyType("EMPLOYEE");
                                callFileUploadDtls.setDocTitle(docTitle);

                                result = callFileUploadDtlsService.saveCallFileDetails(callFileUploadDtls);
                                logger.info("============>file sent to:" + obj.getEmpLoginId());

                                CustomerMst customerMst = customerMstService.findCustomerMstByCustId(callMster.getCustId());
                                logger.info("customerMst:" + customerMst);
                                logger.info("sendFileReceiveRequest('" + obj.getEmpLoginId() + "','fileSentByCust#" + filePathToSend + "#" + docTitle + "#" + customerMst.getId() + "')");
                                RequestContext.getCurrentInstance().execute("sendFileReceiveRequest('" + obj.getEmpLoginId() + "','fileSentByCust#" + filePathToSend + "#" + docTitle + "#" + customerMst.getId() + "')");
                                //  RequestContext.getCurrentInstance().execute("sendFileReceiveRequest('" + obj.getEmpLoginId() + "','fileSent#" + filePathToSend + "#" + docTitle + "')");
//                                SocketMessage.send(callScheduler.getAdminSocket(), obj.getEmpLoginId(), "fileSent#" + filePathToSend + "#" + docTitle);
                                if (result > 0) {
                                    flag = true;
                                }
                            }
                        } else {
                            CallFileUploadDtls callFileUploadDtls = new CallFileUploadDtls();
                            CallDtl calldtllatestagent = callDtlService.findCurrentCallAgent(callMster.getId());
                            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                            callFileUploadDtls.setCreatedDateTime(timestamp);
                            callFileUploadDtls.setFileReceivedBy(calldtllatestagent.getHandeledById().getId());
                            callFileUploadDtls.setCallMstId(callMster);
                            callFileUploadDtls.setFileSentBy(callMster.getCustomerId().getId());
                            callFileUploadDtls.setFilePath(filePathForDatabase);
                            callFileUploadDtls.setCreatedBy(callMster.getCustomerId().getId());
                            callFileUploadDtls.setDocTitle(docTitle);
                            callFileUploadDtls.setFileSentbyType("CUSTOMER");
                            callFileUploadDtls.setFileReceivedbyType("EMPLOYEE");

                            result = callFileUploadDtlsService.saveCallFileDetails(callFileUploadDtls);
//                          /  EmployeeMst emp = employeeMstService.findEmployeeMstById(calldtllatestagent.getHandeledById().getId());
                            /**
                             * Modified By VD on 04-11-2020 add customer id and
                             * change fileSent to fileSentByCust
                             */
                            CustomerMst customerMst = customerMstService.findCustomerMstByCustId(callMster.getCustId());
                            logger.info("customerMst:" + customerMst);
                            logger.info("sendFileReceiveRequest('" + conferenceAgentsDtoListForParticipantCallDtls.get(0).getEmpLoginId() + "','fileSentByCust#" + filePathToSend + "#" + docTitle + "#" + customerMst.getId() + "')");
                            RequestContext.getCurrentInstance().execute("sendFileReceiveRequest('" + conferenceAgentsDtoListForParticipantCallDtls.get(0).getEmpLoginId() + "','fileSentByCust#" + filePathToSend + "#" + docTitle + "#" + customerMst.getId() + "')");
                         
                            if (result > 0) {
                                flag = true;
                            }
                        }
                    }
                    if (flag) {
//                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
//                                "SUCCESS : ", "Document Uploaded Successfully."));
                        RequestContext.getCurrentInstance().execute("PF('fileuploadvar').hide();");
                        RequestContext.getCurrentInstance().execute("PF('fileuploadsuccess').show();setTimeout(function(){PF('fileuploadsuccess').hide() }, 4000);");

                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "ERROR : ", "Document Uploading UnSuccessful."));
                    }

                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "ERROR : ", "Please Enter Document Title"));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "ERROR : ", "Document Uploading UnSuccessful, Please Try Again"));
            }

        } catch (Exception e) {
            logger.error("ERROR : ", e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "ERROR : ", "Document Uploading UnSuccessful, Please Try Again"));
        }

    }

    public void convertTimeZone() {
        try {
            HttpSession session = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession(false);
            DateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
            String timeZoneL = (String) session.getAttribute("timeZone");
            formatter.setTimeZone(TimeZone.getTimeZone(timeZoneL));
            this.schDateTime = formatter.parse(formatter.format(new Date()));
        } catch (ParseException ex) {
            Logger.getLogger(CustomerComponent.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<String> getFiles() {
        return files;
    }

    public void setFiles(List<String> files) {
        this.files = files;
    }

    public void uploadKyc(String filename, String custid) throws IOException {
        String jbossHome = System.getenv("JBOSS_HOME");
        if (null == jbossHome) {

        } else {
            String filePath = jbossHome + File.separator + "standalone" + File.separator + "tmp" + File.separator + "vfs" + File.separator + "temp" + File.separator + custid + "kyc.zip";
            HttpSession session = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession(false);
            session.setAttribute("imagePath", filePath);

        }

    }

    public void resetData() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        this.customerName = "";
        this.customerEmail = "";
        this.customerPhoneNumber = "";
        this.nationality = "";
        this.selectedServiceName = null;
        this.callType = "";
        this.fileDownloadDtoList.clear();
        CallMst callMstL = (CallMst) request.getSession().getAttribute("callMstThroughWeb");
        CallMst cm = callMstService.findNonDeletedCallMstById(callMstL.getId());
        if (cm != null) {
            try {
                if (cm.getEndTime() == null) {
                    cm.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                    callMstService.saveCallMst(cm);
                }
            } catch (ParseException ex) {
                Logger.getLogger(CustomerComponent.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        logger.info("call null 9");
        request.getSession().setAttribute("callMstThroughWeb", null);
        request.getSession().setAttribute("scheduleCallLocal", null);
        RequestContext.getCurrentInstance().execute("refreshEssentials()");

    }

    public void resetAndRedirect() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        this.customerName = "";
        this.customerEmail = "";
        this.customerPhoneNumber = "";
        this.nationality = "";
        this.selectedServiceName = null;
        this.callType = "";
        this.fileDownloadDtoList.clear();
        this.retryCallIntValue=0;
        CallMst callMstL = (CallMst) request.getSession().getAttribute("callMstThroughWeb");
        CallMst cm = callMstService.findNonDeletedCallMstById(callMstL.getId());
        if (cm != null) {
            try {
                if (cm.getEndTime() == null) {
                    cm.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                    callMstService.saveCallMst(cm);
                }
            } catch (ParseException ex) {
                Logger.getLogger(CustomerComponent.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        logger.info("resetAndRedirect");
        request.getSession().setAttribute("callMstThroughWeb", null);
       
        RequestContext.getCurrentInstance().execute("window.location.reload()");
        

    }

    public void resetDataCancelRedirect() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
                .getRequest();
        logger.info("inside resetDataCancelRedirect");
        this.customerName = "";
        this.customerEmail = "";
        this.customerPhoneNumber = "";
        this.nationality = "";
        this.selectedServiceName = null;
        this.callType = "";
        this.fileDownloadDtoList.clear();
        CallMst callMstL = (CallMst) request.getSession().getAttribute("callMstThroughWeb");
        logger.info("callMstL=================" + callMstL);
        CallMst cm = callMstService.findNonDeletedCallMstById(callMstL.getId());
        if (cm != null) {
            try {
                if (cm.getEndTime() == null) {
                    cm.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                    callMstService.saveCallMst(cm);
                }
            } catch (ParseException ex) {
                Logger.getLogger(CustomerComponent.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        logger.info("call null 10");
        request.getSession().setAttribute("callMstThroughWeb", null);
        RequestContext.getCurrentInstance().execute("window.location.reload()");
    }

    public String resetDataRedirect() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        this.customerName = "";
        this.customerEmail = "";
        this.customerPhoneNumber = "";
        this.nationality = "";
        this.selectedServiceName = null;
        this.callType = "";
        this.fileDownloadDtoList.clear();
        logger.info("call null 11");
        request.getSession().setAttribute("callMstThroughWeb", null);
        RequestContext.getCurrentInstance().execute("refreshEssentials()");
        return "faces/customerHome.xtml";
    }

    public void initiateNextCall() {
        callType = "";
        selectedServiceName = null;
        RequestContext.getCurrentInstance().update("selform");
        RequestContext.getCurrentInstance().update("calltypeform");
        RequestContext.getCurrentInstance().execute("valueassign();$( \".sec_one\" ).hide();$( \".sec_two\" ).show();$( \".sec_three\" ).hide();$( \".sec_four\" ).hide();");
    }

    public void afterInitiateCall() {
       
        RequestContext.getCurrentInstance().execute("$( \".sec_one\" ).hide();$( \".sec_two\" ).hide();$( \".sec_three\" ).hide();$( \".sec_four\" ).show();");
    }

    public void initiateCall() {

    }

    public void onSelectCallType(ValueChangeEvent e) {
        logger.info("onSelectCallType:" + selectedCallType);
        selectedCallType = e.getNewValue().toString();
        logger.info("onSelectCallType--Case2:" + selectedCallType);
    }

    public void callCustomer(HttpServletRequest request) {
        this.setExistingCustomer(false);
        FacesContext context = FacesContext.getCurrentInstance();
        Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
        String projectId = paramMap.get("param");
        CategoryMst cat = categoryMstService.findCategoryByCategoryCode("UGR");
        selectedCategoryName = cat.getId();
        LanguageMst langMst = langMstService.findLanguageMstByLanguageCode("ENG");
        selectedLanguageName = langMst.getId();
        logger.info("GET URL PARAMETER=====" + projectId);
        logger.info("selectedCategoryName==" + selectedCategoryName + "?==");
        logger.info("selectedLanguageName==" + selectedLanguageName + "?==");
        logger.info("selectedServiceName==" + selectedServiceName + "?==");
        logger.info("Latitude==" + latitude + "?==");
        logger.info("Longitude==" + longitude + "?==");
        logger.info("Location Area==" + area + "?==");
        logger.info("this.getCustomerEmail()==" + this.getCustomerEmail() + "?==");
        logger.info("this.customerName==" + this.customerName + "?==");
        logger.info("this.getCustomerPhoneNumber()==" + this.getCustomerPhoneNumber() + "?==");
        logger.info("callType==" + this.callType + "?==");
        if (null == selectedCategoryName || 0 == selectedCategoryName || null == selectedLanguageName || 0 == selectedLanguageName || null == selectedServiceName || 0 == selectedServiceName || null == this.getCustomerEmail() || null == this.customerName || null == this.getCustomerPhoneNumber()) {
            logger.info("Failed");
            RequestContext.getCurrentInstance().execute("$('#spinner').hide();PF('initialCallCancelDlg').hide();hideSpinner();");
        } else {
            logger.info("Success");
            if (callType.equalsIgnoreCase("chat")) {
                RequestContext.getCurrentInstance().execute(" showSpinner();PF('initialCallCancelDlg').show();$('#spinner').hide(); document.getElementById('go').click();");
            } else {
                RequestContext.getCurrentInstance().execute("$('#spinner').hide();handleComplete();");
            }
        }
    }

    public void registerCustomer(HttpServletRequest request) {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            Map<String, String> paramMap = context.getExternalContext().getRequestParameterMap();
            String projectId = paramMap.get("param");
            logger.info("GET URL PARAMETER===register==" + projectId);
            logger.info("startCallVerified called.....");
            logger.info("Customer Name:" + this.customerName);
            logger.info("Customer Email:" + this.getCustomerEmail());
            logger.info("serviceName:" + this.getSelectedServiceName());
            logger.info("phoneNumber:" + this.getCustomerPhoneNumber());
            retryCallIntValue=0;
            Thread.sleep(2000);

            if (null == selectedCategoryName || 0 == selectedCategoryName || null == selectedLanguageName || 0 == selectedLanguageName || null == selectedServiceName || 0 == selectedServiceName || null == this.getCustomerEmail() || null == this.customerName || null == this.getCustomerPhoneNumber()) {
                RequestContext.getCurrentInstance().execute("$('#spinner').hide();PF('initialCallCancelDlg').hide();hideSpinner();");
            } else {
                RequestContext.getCurrentInstance().execute(" $('#spinner').hide();PF('initialCallCancelDlg').show();");
                String custSocket = "Cust_" + GenerateCustId.generateCustId();
                this.setCustName(custSocket);
                this.setCustomerSocket(custSocket);
                this.setCustid(this.getCustomerName() + "~" + custSocket);
                com.rank.ccms.entities.CustomerMst cust = new com.rank.ccms.entities.CustomerMst();
                cust.setFirstName(this.getCustomerName());
                cust.setLastName("");
                cust.setEmail(this.getCustomerEmail());
                cust.setCellPhone(Long.parseLong(this.getCustomerPhoneNumber()));
                cust.setCustId(custSocket);
                cust.setCustPwd("123");
                cust.setNatinality(this.getNationality());
                cust.setActiveFlg(true);
                cust.setDeleteFlg(false);
                cust.setPolicyCount(0);
                cust.setSalute("");

                cust = customerMstService.saveCustomerMst(cust, null);
                logger.info(cust);

                HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                session.setAttribute("timeZone", this.timeZone);

                session.setAttribute("socketHostPublic", Constants.socketHostPublic);
                session.setAttribute("ormCustomerMaster", cust);

                session.setAttribute("ormCustomerName", this.getCustomerName());
                session.setAttribute("ormCustomerID", this.getCustomerName());
                request.getSession().setAttribute("ormCustomerNameSocket", this.getCustomerName() + "~" + custSocket);
                logger.info("call null 12");
                request.getSession().setAttribute("callMstThroughWeb", null);

                CustomerDeviceDtl customerDeviceDtl = new CustomerDeviceDtl();
                customerDeviceDtl.setLogout(0);

                customerDeviceDtl.setDeviceBrand(null);
                customerDeviceDtl.setDeviceId(null);
                customerDeviceDtl.setDeviceIp(null);
                customerDeviceDtl.setImeiNo(null);

                customerDeviceDtl.setMobileOsType("WEB");
                customerDeviceDtl.setStaticIp(null);
                customerDeviceDtl.setLoginInfo(1);
                customerDeviceDtl.setCustomerId(cust);
                customerDeviceDtl.setCustomerOtp(null);
                customerDeviceDtl.setTimezone(this.getTimeZone());
                customerDeviceDtlService.saveCustomerDeviceDtl(customerDeviceDtl, null);
            }

            RequestContext.getCurrentInstance().execute("$('#spinner').hide();document.getElementById('start').click();");
        } catch (InterruptedException ex) {
            logger.error("ERROR: ", ex);
        }
    }

    public void reScheduleRequest(HttpServletRequest request) {
        logger.info("INSIDE RE SCHEDULE ID===" + this.getScheduleCallId());
        if (this.getScheduleCallId() != null) {
            ScheduleCall sc = callSchedulerService.findAllNonTakenScheduleCallById(Long.parseLong(this.scheduleCallId));
            if (sc != null) {
                Date currentDate = new Date();
                if (sc.getScheduleDate().getTime() > currentDate.getTime()) {
                    long diff = sc.getScheduleDate().getTime() - currentDate.getTime();

                    long tenMinutes = 10 * 60 * 1000;

                    if (diff <= tenMinutes) {
                        RequestContext.getCurrentInstance().execute("PF('waitForCannotReschedule').show();");

                    } else {
                        this.setSchDateTime(new Date());
                        scheduleStatusChange = true;
                        RequestContext.getCurrentInstance().execute("PF('doScheduleDialog').show();");
                        //RequestContext.getCurrentInstance().execute("checkScheduleWithInTime();");
                    }
                }
            }
        }
    }

    public void makeReScheduleCallFromWeb(HttpServletRequest request) {
        final TimeZone timeZoneL = TimeZone.getDefault();

        ScheduleCall scheduleCall = callSchedulerService.findAllNonTakenScheduleCallById(Long.parseLong(this.scheduleCallId));
        ScheduleCall schCall;

        try {
            ThreadSafeSimpleDateFormat sdf = new ThreadSafeSimpleDateFormat("dd-MMM-yyyy HH:mm");
            sdf.setTimeZone(timeZoneL);
            this.schDateTime = sdf.parse(sdf.format(schDateTime));
            java.sql.Timestamp timestamp = new java.sql.Timestamp(schDateTime.getTime());

            if (schDateTime.after(new Date())) {
                scheduleCall.setCreationDatetime(CustomConvert.javaDateToTimeStamp(new Date()));
                scheduleCall.setExecuteStatus("Request");
                scheduleCall.setScheduleDate(timestamp);

                schCall = callSchedulerService.saveScheduleCall(scheduleCall, null);
                if (null != schCall) {
                    CustomerMst custMst = customerMstService.findCustomerMstById(schCall.getCustomerId().getId());
                    String messageBody;
                    employeeMstService.findEmployeeMstById(schCall.getSchedulerId());
                    String custEmail = custMst.getEmail();
                    if (null != custEmail) {
                        messageBody = "<html><body>Dear &nbsp;" + custMst.getFirstName();
                        messageBody += ",&nbsp;<br></br>We receive your request of new time of schedule for verification call from CCMS with our Service Agent at ";
                        messageBody += schCall.getScheduleDate();
                        messageBody += "&nbsp;<br></br>Our verification manager will confirm it shortly.";
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

                    List<EmployeeMst> employeeMstList = employeeMstService.findEmployeeByEmpTypeId(employeeTypeMstService.findEmployeeTypeByEmployeeTypeName("VerificationManager"));
                    for (EmployeeMst em : employeeMstList) {
                        StringBuffer sb = new StringBuffer(255);
                        sb.append("Dear ").append(em.getFirstName());
                        sb.append(",\n\nMr. ").append(custMst.getFirstName()).append(" just requested for re schedule the call. Requesting you to confirm the time or give some time to your convenient to verify the requested account. \n\n");
                        sb.append(SendingMailUtil.TELE_THX);
                        try {
                            boolean mailSend = SendingMailUtil.sendMail(em.getEmail(), sb, SendingMailUtil.NEW_CUSTOMER);
                            if (mailSend) {
                                logger.info("mail sending was successfull... to Manager " + em.getEmail());
                            }
                        } catch (Exception ex) {
                            logger.error("Sending Email Error:" + ex.getMessage());
                        }

                    }

                    this.scheduleText = "You have been requested successfully for reschedule, Our Verification manager will confirm it shortly.";
                    RequestContext.getCurrentInstance().execute("PF('doScheduleDialog').hide();");
                    RequestContext.getCurrentInstance().execute("$('#spinner').hide();PF('confirmscheduleDlg').show();");

                } else {

                    this.scheduleText = "Call Scheduleding Failed";
                    RequestContext.getCurrentInstance().execute("PF('confirmscheduleDlg').show();");
                }
//                    }
//                }
            } else {

                this.scheduleText = "Select valid Date and time";
                RequestContext.getCurrentInstance().execute("closeAllDialog();PF('confirmscheduleDlg').show();");
            }

        } catch (ParseException | NumberFormatException e) {

        }

    }

    public void checkScheduleStatus() {
        logger.info("hi");
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        session.setAttribute("socketHostPublic", Constants.socketHostPublic);

        try {
            if (this.getScheduleCallId() != null) {
                boolean isNum = CustomerConstant.isNumericCustom(this.scheduleCallId);
                logger.info("isNum:" + isNum);
                ScheduleCall sc = null;
                if (isNum) {
                    sc = callSchedulerService.findAllNonTakenScheduleCallById(Long.parseLong(this.scheduleCallId));
                }
                if (null != sc) {
                    logger.info(sc + "this.scheduleCallId:" + this.scheduleCallId);
                    sc.setCustomerTime(CustomConvert.javaDateToTimeStamp(new Date()));
                    sc = callSchedulerService.saveScheduleCall(sc, null);
                }
                if (sc != null) {
                    if (sc.getExecuteStatus().equalsIgnoreCase("Scheduled")) {
                        CustomerMst cmLoc = customerMstService.findCustomerMstById(sc.getCustomerId().getId());
                        if (null != cmLoc.getMidName()) {
                            this.setCustName(cmLoc.getFirstName() + " " + cmLoc.getMidName() + "~" + cmLoc.getCustId());

                        } else {
                            this.setCustName(cmLoc.getFirstName() + "~" + cmLoc.getCustId());
                        }
                        session.setAttribute("scheduleCallId", sc.getId());
                        this.setCustid(cmLoc.getCustId());
                        logger.info(cmLoc.getCustId() + "custName:" + custName);
                        this.setCustomerSocket(cmLoc.getCustId());

//                    RequestContext.getCurrentInstance().execute("setCustomerUserName('" + sc.getCustomerId().getCustId() + "','" + custName + "')");
                        Date currentDate = new Date();
                        logger.info("sc.getExecuteStatus():" + sc.getExecuteStatus() + "sc.getScheduleDate().getTime():" + sc.getScheduleDate().getTime());
                        if (sc.getScheduleDate().getTime() > currentDate.getTime()) {
                            sc.getScheduleDate().getTime();
                            currentDate.getTime();

                            //if (diff <= tenMinutes) {
                            if (scheduleStatusChange) {
                                RequestContext.getCurrentInstance().execute("PF('tenSchedule').hide();");
                                scheduleStatusChange = false;
                            }
                            CustomerMst l_customerMst = customerMstService.findCustomerMstById(cmLoc.getId());
                            session.setAttribute("ormCustomerMaster", l_customerMst);
                            session.setAttribute("ormCustomerName", l_customerMst.getFirstName());

                            RequestContext.getCurrentInstance().execute("PF('waitForSchedule').show();");

                        } else if (sc.getExecuteStatus().trim().equalsIgnoreCase("Completed")) {
                            RequestContext.getCurrentInstance().execute("PF('successSchedule').show();");

                        } else {
                            long diff = currentDate.getTime() - sc.getScheduleDate().getTime();
                            long fifteenMinutes = 15 * 60 * 1000;
                            if (diff >= fifteenMinutes) {
                                RequestContext.getCurrentInstance().execute("PF('passedSchedule').show();");
                            } else {
                                CustomerMst l_customerMst = customerMstService.findCustomerMstById(cmLoc.getId());
                                session.setAttribute("ormCustomerMaster", l_customerMst);
                                session.setAttribute("ormCustomerName", l_customerMst.getFirstName());
                                RequestContext.getCurrentInstance().execute("PF('waitForSchedule').show();");
                            }
                        }
                    } else {
                        RequestContext.getCurrentInstance().execute("PF('noSchedule').show();");
                    }
                } else {
                    logger.info("noSchedule.......1...................................");
                    if (this.scheduleCallId == null || "".equals(this.scheduleCallId) || !isNum) {
                        RequestContext.getCurrentInstance().execute("PF('someWentWrongWid').show();");
                    } else {
                        RequestContext.getCurrentInstance().execute("PF('noSchedule').show();");
                    }
                }
            } else {
                logger.info("noSchedule.......2...................................");
                RequestContext.getCurrentInstance().execute("PF('noSchedule').show();");
            }
        } catch (NumberFormatException | ParseException e) {
            logger.error(e);
        }
    }

    public void reset() {
        this.filemessage = "  ";
    }

    public boolean getShowLink() {
        return showLink;
    }

    public void setShowLink(boolean showLink) {
        this.showLink = showLink;
    }

    public String getUserNameText() {
        return userNameText;
    }

    public void setUserNameText(String userNameText) {
        this.userNameText = userNameText;
    }

    public String getFileLocationCustomerText() {
        return fileLocationCustomerText;
    }

    public void setFileLocationCustomerText(String fileLocationCustomerText) {
        this.fileLocationCustomerText = fileLocationCustomerText;
    }

    public boolean getIworkImage() {
        return iworkImage;
    }

    public void setIworkImage(boolean iworkImage) {
        this.iworkImage = iworkImage;
    }

    public String getFilemessage() {
        return filemessage;
    }

    public void setFilemessage(String filemessage) {
        this.filemessage = filemessage;
    }

    public void fileUploadOptions(HttpServletRequest request) {
        RequestContext.getCurrentInstance().execute("closeAllDialog();PF('fileuploadOptionDlg').show();");
    }

    public void searchParticipant(HttpServletRequest request) {
        logger.info("searchParticipant.....");
        try {
            docTitle = null;
            if (conferenceAgentsDtoListForParticipantCallDtlsSelection != null) {
                conferenceAgentsDtoListForParticipantCallDtlsSelection.clear();
            }
            if (conferenceAgentsDtoListForParticipantCallDtls != null) {
                conferenceAgentsDtoListForParticipantCallDtls.clear();
            }
            CallMst callMst1 = (CallMst) request.getSession().getAttribute("callMstThroughWeb");
            logger.info("callMst:" + callMst1);
            conferenceAgentsDtoListForParticipantCallDtls = callDtlService.findParticipantCallDtlsByCallMstId(callMst1);

            if (conferenceAgentsDtoListForParticipantCallDtlsSelection != null && !conferenceAgentsDtoListForParticipantCallDtls.isEmpty() && conferenceAgentsDtoListForParticipantCallDtls.size() == 1) {
                multipleParticipant = false;
            } else {
                multipleParticipant = conferenceAgentsDtoListForParticipantCallDtls.size() > 1;
            }
        } catch (Exception e) {
            logger.error("ERROR : ", e);
        }
        RequestContext.getCurrentInstance().execute("closeAllDialog();PF('fileuploadvar').show();");
        RequestContext.getCurrentInstance().update("fileUploadVarForm");
    }

    public void resetbuttons() {
        this.docTitle = "";
        this.fileCaptionRenderer = false;
    }

    public void updateLink() {
        logger.info("updateLink------------");
        try {
            newFileMessage = "New File Arrived";
            FileDownloadDto fileDownloadDto = new FileDownloadDto();
            fileDownloadDto.setUploadedFilePath(uploadedFilePath);
            fileDownloadDto.setFileName(fileName);
            fileDownloadDto.setDownloadCss("color:red");
            fileDownloadDtoList.add(fileDownloadDto);
            Collections.reverse(fileDownloadDtoList);
            this.iworkImage = true;
            RequestContext.getCurrentInstance().update("imgIworksForm");
            RequestContext.getCurrentInstance().execute("showRightSidePanelCust()");
        } catch (Exception e) {
            logger.error("ERROR: ", e);
        }
    }

    public void downloadFile(FileDownloadDto fileDownloadDto) {
        try {

            if (fileDownloadDto.getUploadedFilePath() != null && fileDownloadDto.getFileName() != null) {
                newFileMessage = "";
                fileDownloadDto.setDownloadCss("color:green");
                RequestContext.getCurrentInstance().update("imgIworksForm");
//                InputStream inputStream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream(fileDownloadDto.getUploadedFilePath());
//                downloadFile = new DefaultStreamedContent(inputStream, new MimetypesFileTypeMap().getContentType(fileDownloadDto.getUploadedFilePath()), fileDownloadDto.getFileName());
                RequestContext.getCurrentInstance().execute("openDocInTab('" + fileDownloadDto.getUploadedFilePath() + "');");

            }
        } catch (Exception e) {
            logger.error("ERROR : ", e);
        }
    }

    public void openCobrowseWindow() {
        try {
            HttpSession session = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession();
            CallMst cm = (CallMst) session.getAttribute("callMstThroughWeb");
            Map<String, Object> options = new HashMap<>();
            options.put("modal", true);
            options.put("height", 550);
            options.put("width", 1000);
            this.setCustomerId(cm.getCustId());
            this.setJwtToken(CreateJWTToken.createTokenWithPolicy(cm.getCustId()));
            RequestContext.getCurrentInstance().openDialog("/pages/customer/cobrowse", options, null);

        } catch (Exception e) {
            logger.error("ERROR : ", e);
        }
    }

    public IncomingCallResponse makeRMCall(HttpServletRequest request) {

        IncomingCallResponse incomingCallResponse = null;

        CustomerMst customerMst_local = (CustomerMst) request.getSession().getAttribute("ormCustomerMaster");

        boolean newCall = true;
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        String weekDay = "";
        switch (day) {
            case Calendar.SUNDAY:
                weekDay = "Sunday";
                break;
            case Calendar.MONDAY:
                weekDay = "Monday";
                break;
            case Calendar.TUESDAY:
                weekDay = "Tuesday";
                break;
            case Calendar.WEDNESDAY:
                weekDay = "Wednesday";
                break;
            case Calendar.THURSDAY:
                weekDay = "Thursday";
                break;
            case Calendar.FRIDAY:
                weekDay = "Friday";
                break;
            case Calendar.SATURDAY:
                weekDay = "Saturday";
                break;

        }

        boolean indownTime;
        boolean inservicedownTime = false;

        indownTime = whetherInDownTime();

        if (!indownTime) {
            inservicedownTime = whetherInServiceDownTime();
        }
        if (indownTime) {
            downMessage = "Currently System is in downtime from " + this.downTimeStart + " to " + this.downTimeEnd;
        } else if (inservicedownTime) {
            if ((this.getServiceTimeStart() != null && !this.getServiceTimeStart().trim().equals("")) && (this.getServiceTimeEnd() != null && !this.getServiceTimeEnd().trim().equals(""))) {
                downMessage = "We are unavailable to attend to you via this channel at this time. We are only available on " + weekDay + " between " + this.getServiceTimeStart() + " to " + this.getServiceTimeEnd();

            } else {

                downMessage = "We are unavailable to attend to you via this channel at this time on this week day ";
            }
        }

        try {
            if (customerMst_local != null && null != customerMst_local.getCustId()) {

                if (!indownTime && !inservicedownTime) {

                    CustomerDeviceDtl cdd = customerDeviceDtlService.findCustomerDeviceDtlByCustId(customerMst_local);

                    if (null != cdd) {
                        if (null != cdd.getId()) {
                            cdd.setAudioVideo(1);

                            customerDeviceDtlService.saveCustomerDeviceDtl(cdd, null);
                        }
                    }

                    CallMst callMstForRepeatcall = null;
                    List<CallMst> callMstForRepeatcallList = callMstService.findAllNotRecevedCallsByCust(customerMst_local.getCustId());
                    if (!callMstForRepeatcallList.isEmpty()) {
                        callMstForRepeatcall = callMstForRepeatcallList.get(0);
                        newCall = false;
                    }

                    synchronized (this) {
                        CategoryMst categoryMst = categoryMstService.findNonDeletedCategoryMstByCategoryMstId(selectedCategoryName);

                        LanguageMst languageMst = langMstService.findValidLanguageMstById(selectedLanguageName);

                        ServiceMst serviceMst = serviceMstService.findNonDeletedServiceMstById(selectedServiceName);
                        if (newCall) {
                            List<CallMst> listOpenCalls = callMstService.findAllOnGoingCallsByCust(customerMst_local.getCustId());

                            if (!listOpenCalls.isEmpty()) {
                                for (CallMst cmst : listOpenCalls) {
                                    cmst.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                                    cmst = callMstService.saveCallMst(cmst);
                                }
                            }

                            CallMst local_callMst = new CallMst();
                            local_callMst.setCallStatus("Initialize");
                            local_callMst.setCallType("Incoming Call");
                            local_callMst.setDeviceBrand(null);
                            local_callMst.setDeviceOs(null);
                            local_callMst.setiMEIno(null);
                            local_callMst.setDeviceId(null);
                            local_callMst.setDeviceIp(null);
                            local_callMst.setStaticIp(null);
                            local_callMst.setDeviceName(null);
                            local_callMst.setCustomerId(customerMst_local);
                            local_callMst.setCustId(customerMst_local.getCustId());

                            local_callMst.setServiceId(serviceMst.getId());
                            local_callMst.setCategoryId(categoryMst.getId());
                            local_callMst.setLanguageId(languageMst.getId());
                            local_callMst.setCallOption(callType);
                            local_callMst.setCallMedium("WEB");
                            local_callMst.setActiveFlg(true);
                            local_callMst.setDeleteFlg(false);
                            local_callMst.setStartTime(CustomConvert.javaDateToTimeStamp(new Date(System.currentTimeMillis())));
                            local_callMst.setCustomerRequestTime(CustomConvert.javaDateToTimeStamp(new Date()));

                            local_callMst.setCallLocation(getCityLocation() + "," + getCountryLocation());
                            local_callMst.setCallLatitude(getLatitude());
                            local_callMst.setCallLongitude(getLongitude());

                            local_callMst = callMstService.saveCallMst(local_callMst);
                            callMst = callMstService.findCallMstById(local_callMst.getId());
                        } else if (callMstForRepeatcall != null) {
                            callMstForRepeatcall.setServiceId(serviceMst.getId());
                            callMstForRepeatcall.setCategoryId(categoryMst.getId());
                            callMstForRepeatcall.setLanguageId(languageMst.getId());
                            callMstForRepeatcall.setStartTime(CustomConvert.javaDateToTimeStamp(new Date()));
                            callMst = callMstService.saveCallMst(callMstForRepeatcall);
                        }

                        List<CustomerRmMap> mappedRMList = customerRMMapService.getRMMappedWithCustomer(customerMst_local.getId());
                        CustomerRmMap customerRmMap = null;
                        if (!mappedRMList.isEmpty()) {
                            customerRmMap = mappedRMList.get(0);
                        }

                        if (null != customerRmMap) {
                            EmployeeMst emp = employeeMstService.findEmployeeMstById(customerRmMap.getRmId().getId());
                            String loginId = emp.getLoginId();
                            if (!employeeCallStatusService.findFreeOnlineRMs(loginId).isEmpty()) {
                                Long callId;
                                callId = callMst.getId();
                                incomingCallResponse = new IncomingCallResponse("", "", callId.toString(), "Not Initiated", categoryMst.getCatgName(), languageMst.getLanguageName(), serviceMst.getServiceName(), customerMst_local.getCustId());

                                CallMst callMaster;

                                callMaster = callMstService.findNonDeletedCallMstById(Long.parseLong(incomingCallResponse.getCallId()));

                                //List<Object> L_E_Mst = Arrays.asList(agentFindingService.findAgents(categoryMstLocal.getCatgCd(), serviceMstLocal.getServiceCd(), languageMstLocal.getLanguageCd()).toArray());
                                EmployeeMst em = employeeMstService.findEmployeeByUserId(loginId);

                                if (em != null) {

                                    EmployeeCallStatus empCallStatus = new EmployeeCallStatus();
                                    List<EmployeeCallStatus> empClStatusList = employeeCallStatusService.findEmployeeCallStatusByEmpId(em);

                                    if (!empClStatusList.isEmpty()) {
                                        for (EmployeeCallStatus empstatus : empClStatusList) {
                                            empCallStatus = empstatus;
                                        }
                                        int count = empCallStatus.getCallCount();
                                        empCallStatus.setCallCount(count + 1);
                                        empCallStatus.setStatus(false);
                                        employeeCallStatusService.saveEmployeeCallStatus(empCallStatus);
                                    } else {
                                        empCallStatus.setEmpId(em);
                                        empCallStatus.setCallCount(1);
                                        empCallStatus.setStatus(false);
                                        employeeCallStatusService.saveEmployeeCallStatus(empCallStatus);
                                    }
                                    // status busy set

                                    List<TenancyEmployeeMap> tenancyEmployeeMaplist = tenancyEmployeeMapService.findVidyoTenantUrlByEmpId(em.getId());
                                    logger.info("tenancyEmployeeMaplist size : " + tenancyEmployeeMaplist.size());
                                    List<Long> unsortList = new ArrayList<>();
                                    for (TenancyEmployeeMap tenancyEmployeeMaplist1 : tenancyEmployeeMaplist) {
                                        logger.info("tenancyEmployeeMaplist1 id :" + tenancyEmployeeMaplist1.getId());
                                        unsortList.add(tenancyEmployeeMaplist1.getId());
                                    }
                                    logger.info("unsortList size :" + unsortList.size());
                                    Collections.sort(unsortList);
                                    logger.info("Error ID : " + unsortList.get(unsortList.size() - 1));
                                    TenancyEmployeeMap tenancyEmployeeMap = tenancyEmployeeMapService.findById(unsortList.get(unsortList.size() - 1));

                                    String roomLink = tenancyEmployeeMap.getRoomLink();
                                    incomingCallResponse.setUrl(roomLink);
                                    incomingCallResponse.setRoomName(tenancyEmployeeMap.getRoomName());
                                    incomingCallResponse.setAgentId(em.getLoginId());
                                    incomingCallResponse.setStatus("Initiated" + "||" + em.getFirstName() + " " + em.getLastName());
                                    incomingCallResponse.setCustomerId(customerMst_local.getCustId());
                                    Set<String> custIdSet = new TreeSet<>();
                                    custIdSet.add(customerMst_local.getCustId());
                                    CallScheduler.customerCallDetailsMap.put(callId.toString(), custIdSet);

                                    CallEmployeeMap l_CallEmpMap = new CallEmployeeMap();
                                    l_CallEmpMap.setCallId(callMaster.getId());
                                    l_CallEmpMap.setEmployeeId(em.getId());
                                    l_CallEmpMap.setCallType("Normal");
                                    l_CallEmpMap.setCustId(callMaster.getCustId());
                                    l_CallEmpMap.setRoomLink(roomLink);
                                    callMaster.setRoutingCallTime(CustomConvert.javaDateToTimeStamp(new Date()));
                                    callMaster.setRoomName(roomLink);
                                    callMstService.saveCallMst(callMaster);
                                    try {
                                        SocketMessage.send(callScheduler.getAdminSocket(), em.getLoginId(), "incomingCall#" + callMaster.getId() + "#" + callMaster.getCustId() + "#" + callMaster.getCallOption());
                                    } catch (Exception ex) {
                                        logger.error(ex);
                                    }
                                    CallScheduler.listCallEmp.add(l_CallEmpMap);

                                    selectPromoVideo();

                                } else {
                                    logger.info("No RM Found..........");
                                    callMaster = callMstService.findNonDeletedCallMstById(Long.parseLong(incomingCallResponse.getCallId()));
                                    callMaster.setCallStatus("No Agent Found");
                                    callMaster.setStartTime(CustomConvert.javaDateToTimeStamp(new Date()));
                                    //callMaster.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                                    callMstService.saveCallMst(callMaster);

                                }

                            } else {
                                logger.info("All RM's are busy or no RM's is available..........");
                                Long callId = callMst.getId();
                                CallMst callMaster = callMstService.findNonDeletedCallMstById(callId);
                                callMaster.setCallStatus("No Agent Found");
                                callMaster.setStartTime(CustomConvert.javaDateToTimeStamp(new Date()));
                                //callMaster.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                                callMstService.saveCallMst(callMaster);
                                incomingCallResponse = new IncomingCallResponse("", "", callId.toString(), "Not Initiated", categoryMst.getCatgDesc(), languageMst.getLanguageName(), serviceMst.getServiceName(), customerMst_local.getCustId());

                            }
                        }

                    }
                } else {
                    logger.info("Else...............serverIsOnDownTime....");
                    incomingCallResponse = new IncomingCallResponse("", "", "", "serverIsOnDownTime", "", "", "", "");

                }
            }

        } catch (ParseException | NumberFormatException e) {
            e.printStackTrace(System.err);
        }
        if (null != incomingCallResponse && !incomingCallResponse.getCallId().equals("")) {
            Long CallMstId = Long.parseLong(incomingCallResponse.getCallId());
            CallMst cm = callMstService.findCallMstById(CallMstId);
            if (null != cm) {
                request.getSession().setAttribute("callMstThroughWeb", cm);
            }
        }
        return incomingCallResponse;

    }

    public IncomingCallResponse makeSRMCall(HttpServletRequest request) {

        IncomingCallResponse incomingCallResponse = null;

        CustomerMst customerMst_local = (CustomerMst) request.getSession().getAttribute("ormCustomerMaster");
        boolean newCall = true;
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        String weekDay = "";
        switch (day) {
            case Calendar.SUNDAY:
                weekDay = "Sunday";
                break;
            case Calendar.MONDAY:
                weekDay = "Monday";
                break;
            case Calendar.TUESDAY:
                weekDay = "Tuesday";
                break;
            case Calendar.WEDNESDAY:
                weekDay = "Wednesday";
                break;
            case Calendar.THURSDAY:
                weekDay = "Thursday";
                break;
            case Calendar.FRIDAY:
                weekDay = "Friday";
                break;
            case Calendar.SATURDAY:
                weekDay = "Saturday";
                break;

        }

        boolean indownTime;
        boolean inservicedownTime = false;

        indownTime = whetherInDownTime();

        if (!indownTime) {
            inservicedownTime = whetherInServiceDownTime();
        }
        if (indownTime) {
            downMessage = "Currently System is in downtime from " + this.downTimeStart + " to " + this.downTimeEnd;
        } else if (inservicedownTime) {
            if ((this.getServiceTimeStart() != null && !this.getServiceTimeStart().trim().equals("")) && (this.getServiceTimeEnd() != null && !this.getServiceTimeEnd().trim().equals(""))) {
                downMessage = "We are unavailable to attend to you via this channel at this time. We are only available on " + weekDay + " between " + this.getServiceTimeStart() + " to " + this.getServiceTimeEnd();

            } else {

                downMessage = "We are unavailable to attend to you via this channel at this time on this week day ";
            }
        }

        try {
            if (customerMst_local != null && null != customerMst_local.getCustId()) {

                if (!indownTime && !inservicedownTime) {

                    CustomerDeviceDtl cdd = customerDeviceDtlService.findCustomerDeviceDtlByCustId(customerMst_local);

                    if (null != cdd) {
                        if (null != cdd.getId()) {
                            cdd.setAudioVideo(1);

                            customerDeviceDtlService.saveCustomerDeviceDtl(cdd, null);
                        }
                    }

                    CallMst callMstForRepeatcall = null;
                    List<CallMst> callMstForRepeatcallList = callMstService.findAllNotRecevedCallsByCust(customerMst_local.getCustId());
                    if (!callMstForRepeatcallList.isEmpty()) {
                        callMstForRepeatcall = callMstForRepeatcallList.get(0);
                        newCall = false;
                    }

                    synchronized (this) {
                        CategoryMst categoryMst = categoryMstService.findNonDeletedCategoryMstByCategoryMstId(selectedCategoryName);

                        LanguageMst languageMst = langMstService.findValidLanguageMstById(selectedLanguageName);

                        ServiceMst serviceMst = serviceMstService.findNonDeletedServiceMstById(selectedServiceName);
                        if (newCall) {
                            List<CallMst> listOpenCalls = callMstService.findAllOnGoingCallsByCust(customerMst_local.getCustId());

                            if (!listOpenCalls.isEmpty()) {
                                for (CallMst cmst : listOpenCalls) {
                                    cmst.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                                    cmst = callMstService.saveCallMst(cmst);
                                }
                            }

                            CallMst local_callMst = new CallMst();
                            local_callMst.setCallStatus("Initialize");
                            local_callMst.setCallType("Incoming Call");
                            local_callMst.setDeviceBrand(null);
                            local_callMst.setDeviceOs(null);
                            local_callMst.setiMEIno(null);
                            local_callMst.setDeviceId(null);
                            local_callMst.setDeviceIp(null);
                            local_callMst.setStaticIp(null);
                            local_callMst.setDeviceName(null);
                            local_callMst.setCustomerId(customerMst_local);
                            local_callMst.setCustId(customerMst_local.getCustId());

                            local_callMst.setServiceId(serviceMst.getId());
                            local_callMst.setCategoryId(categoryMst.getId());
                            local_callMst.setLanguageId(languageMst.getId());
                            local_callMst.setCallOption(callType);
                            local_callMst.setCallMedium("WEB");
                            local_callMst.setActiveFlg(true);
                            local_callMst.setDeleteFlg(false);
                            local_callMst.setStartTime(CustomConvert.javaDateToTimeStamp(new Date(System.currentTimeMillis())));
                            local_callMst.setCustomerRequestTime(CustomConvert.javaDateToTimeStamp(new Date()));

                            local_callMst.setCallLocation(getCityLocation() + "," + getCountryLocation());
                            local_callMst.setCallLatitude(getLatitude());
                            local_callMst.setCallLongitude(getLongitude());

                            local_callMst = callMstService.saveCallMst(local_callMst);
                            callMst = callMstService.findCallMstById(local_callMst.getId());
                        } else if (callMstForRepeatcall != null) {
                            callMstForRepeatcall.setServiceId(serviceMst.getId());
                            callMstForRepeatcall.setCategoryId(categoryMst.getId());
                            callMstForRepeatcall.setLanguageId(languageMst.getId());
                            callMstForRepeatcall.setStartTime(CustomConvert.javaDateToTimeStamp(new Date()));
                            callMst = callMstService.saveCallMst(callMstForRepeatcall);
                        }

                        List<CustomerRmMap> mappedRMList = customerRMMapService.getRMMappedWithCustomer(customerMst_local.getId());
                        CustomerRmMap customerRmMap = null;
                        if (!mappedRMList.isEmpty()) {
                            customerRmMap = mappedRMList.get(0);
                        }
                        if (null != customerRmMap) {
                            EmployeeMst rm = employeeMstService.findEmployeeMstById(customerRmMap.getRmId().getId());
                            RmSrmMap rmSrmMap = rmSrmMapService.getSRMMappedWithRM(rm.getId());
                            if (null != rmSrmMap) {
                                if (!employeeCallStatusService.findFreeOnlineSRMs(rmSrmMap.getSrmId().getId()).isEmpty()) {
                                    Long callId;
                                    callId = callMst.getId();
                                    incomingCallResponse = new IncomingCallResponse("", "", callId.toString(), "Not Initiated", categoryMst.getCatgName(), languageMst.getLanguageName(), serviceMst.getServiceName(), customerMst_local.getCustId());

                                    CallMst callMaster;

                                    callMaster = callMstService.findNonDeletedCallMstById(Long.parseLong(incomingCallResponse.getCallId()));

                                    //List<Object> L_E_Mst = Arrays.asList(agentFindingService.findAgents(categoryMstLocal.getCatgCd(), serviceMstLocal.getServiceCd(), languageMstLocal.getLanguageCd()).toArray());
                                    EmployeeMst em = employeeMstService.findEmployeeMstById(rmSrmMap.getSrmId().getId());

                                    if (em != null) {
                                        //em = (EmployeeMst) L_E_Mst.get(0);

                                        EmployeeCallStatus empCallStatus = new EmployeeCallStatus();
                                        List<EmployeeCallStatus> empClStatusList = employeeCallStatusService.findEmployeeCallStatusByEmpId(em);

                                        if (!empClStatusList.isEmpty()) {
                                            for (EmployeeCallStatus empstatus : empClStatusList) {
                                                empCallStatus = empstatus;
                                            }
                                            int count = empCallStatus.getCallCount();
                                            empCallStatus.setCallCount(count + 1);
                                            empCallStatus.setStatus(false);
                                            employeeCallStatusService.saveEmployeeCallStatus(empCallStatus);
                                        } else {
                                            empCallStatus.setEmpId(em);
                                            empCallStatus.setCallCount(1);
                                            empCallStatus.setStatus(false);
                                            employeeCallStatusService.saveEmployeeCallStatus(empCallStatus);
                                        }
                                        // status busy set

                                        List<TenancyEmployeeMap> tenancyEmployeeMaplist = tenancyEmployeeMapService.findVidyoTenantUrlByEmpId(em.getId());
                                        List<Long> unsortList = new ArrayList<>();
                                        for (TenancyEmployeeMap tenancyEmployeeMaplist1 : tenancyEmployeeMaplist) {
                                            unsortList.add(tenancyEmployeeMaplist1.getId());
                                        }
                                        Collections.sort(unsortList);

                                        TenancyEmployeeMap tenancyEmployeeMap = tenancyEmployeeMapService.findById(unsortList.get(unsortList.size() - 1));

                                        String roomLink = tenancyEmployeeMap.getRoomLink();
                                        incomingCallResponse.setUrl(roomLink);
                                        incomingCallResponse.setRoomName(tenancyEmployeeMap.getRoomName());
                                        incomingCallResponse.setAgentId(em.getLoginId());
                                        incomingCallResponse.setStatus("Initiated" + "||" + em.getFirstName() + " " + em.getLastName());
                                        incomingCallResponse.setCustomerId(customerMst_local.getCustId());
                                        Set<String> custIdSet = new TreeSet<>();
                                        custIdSet.add(customerMst_local.getCustId());
                                        CallScheduler.customerCallDetailsMap.put(callId.toString(), custIdSet);

                                        CallEmployeeMap l_CallEmpMap = new CallEmployeeMap();
                                        l_CallEmpMap.setCallId(callMaster.getId());
                                        l_CallEmpMap.setEmployeeId(em.getId());
                                        l_CallEmpMap.setCallType("Normal");
                                        l_CallEmpMap.setCustId(callMaster.getCustId());
                                        l_CallEmpMap.setRoomLink(roomLink);
                                        callMaster.setRoutingCallTime(CustomConvert.javaDateToTimeStamp(new Date()));
                                        callMaster.setRoomName(roomLink);
                                        callMstService.saveCallMst(callMaster);
                                        try {
                                            SocketMessage.send(callScheduler.getAdminSocket(), em.getLoginId(), "incomingCall#" + callMaster.getId() + "#" + callMaster.getCustId() + "#" + callMaster.getCallOption());
                                        } catch (Exception ex) {
                                            logger.error(ex);
                                        }
                                        CallScheduler.listCallEmp.add(l_CallEmpMap);

                                        selectPromoVideo();

                                    } else {
                                        logger.info("No SRM Found..........");
                                        callMaster = callMstService.findNonDeletedCallMstById(Long.parseLong(incomingCallResponse.getCallId()));
                                        callMaster.setCallStatus("No Agent Found");
                                        callMaster.setStartTime(CustomConvert.javaDateToTimeStamp(new Date()));
                                        //callMaster.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                                        callMstService.saveCallMst(callMaster);

                                    }

                                } else {
                                    logger.info("All SRM's are busy or no RM's is available..........");
                                    Long callId = callMst.getId();
                                    CallMst callMaster = callMstService.findNonDeletedCallMstById(callId);
                                    callMaster.setCallStatus("No Agent Found");
                                    callMaster.setStartTime(CustomConvert.javaDateToTimeStamp(new Date()));
                                    //callMaster.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                                    callMstService.saveCallMst(callMaster);
                                    incomingCallResponse = new IncomingCallResponse("", "", callId.toString(), "Not Initiated", categoryMst.getCatgDesc(), languageMst.getLanguageName(), serviceMst.getServiceName(), customerMst_local.getCustId());

                                }
                            }
                        }
                    }
                } else {
                    logger.info("Else...............serverIsOnDownTime....");
                    incomingCallResponse = new IncomingCallResponse("", "", "", "serverIsOnDownTime", "", "", "", "");

                }
            }

        } catch (ParseException | NumberFormatException e) {
            e.printStackTrace(System.err);
        }
        if (null != incomingCallResponse && !incomingCallResponse.getCallId().equals("")) {
            Long CallMstId = Long.parseLong(incomingCallResponse.getCallId());
            CallMst cm = callMstService.findCallMstById(CallMstId);
            if (null != cm) {
                request.getSession().setAttribute("callMstThroughWeb", cm);
            }
        }
        return incomingCallResponse;

    }

    public IncomingCallResponse makeBMCall(HttpServletRequest request) {

        IncomingCallResponse incomingCallResponse = null;

        CustomerMst customerMst_local = (CustomerMst) request.getSession().getAttribute("ormCustomerMaster");
        boolean newCall = true;
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        String weekDay = "";
        switch (day) {
            case Calendar.SUNDAY:
                weekDay = "Sunday";
                break;
            case Calendar.MONDAY:
                weekDay = "Monday";
                break;
            case Calendar.TUESDAY:
                weekDay = "Tuesday";
                break;
            case Calendar.WEDNESDAY:
                weekDay = "Wednesday";
                break;
            case Calendar.THURSDAY:
                weekDay = "Thursday";
                break;
            case Calendar.FRIDAY:
                weekDay = "Friday";
                break;
            case Calendar.SATURDAY:
                weekDay = "Saturday";
                break;

        }

        boolean indownTime;
        boolean inservicedownTime = false;

        indownTime = whetherInDownTime();

        if (!indownTime) {
            inservicedownTime = whetherInServiceDownTime();
        }
        if (indownTime) {
            downMessage = "Currently System is in downtime from " + this.downTimeStart + " to " + this.downTimeEnd;
        } else if (inservicedownTime) {
            if ((this.getServiceTimeStart() != null && !this.getServiceTimeStart().trim().equals("")) && (this.getServiceTimeEnd() != null && !this.getServiceTimeEnd().trim().equals(""))) {
                downMessage = "We are unavailable to attend to you via this channel at this time. We are only available on " + weekDay + " between " + this.getServiceTimeStart() + " to " + this.getServiceTimeEnd();

            } else {

                downMessage = "We are unavailable to attend to you via this channel at this time on this week day ";
            }
        }

        try {
            if (customerMst_local != null && null != customerMst_local.getCustId()) {

                if (!indownTime && !inservicedownTime) {

                    CustomerDeviceDtl cdd = customerDeviceDtlService.findCustomerDeviceDtlByCustId(customerMst_local);

                    if (null != cdd) {
                        if (null != cdd.getId()) {
                            cdd.setAudioVideo(1);

                            customerDeviceDtlService.saveCustomerDeviceDtl(cdd, null);
                        }
                    }

                    CallMst callMstForRepeatcall = null;
                    List<CallMst> callMstForRepeatcallList = callMstService.findAllNotRecevedCallsByCust(customerMst_local.getCustId());
                    if (!callMstForRepeatcallList.isEmpty()) {
                        callMstForRepeatcall = callMstForRepeatcallList.get(0);
                        newCall = false;
                    }

                    synchronized (this) {
                        CategoryMst categoryMst = categoryMstService.findNonDeletedCategoryMstByCategoryMstId(selectedCategoryName);

                        LanguageMst languageMst = langMstService.findValidLanguageMstById(selectedLanguageName);

                        ServiceMst serviceMst = serviceMstService.findNonDeletedServiceMstById(selectedServiceName);
                        if (newCall) {
                            List<CallMst> listOpenCalls = callMstService.findAllOnGoingCallsByCust(customerMst_local.getCustId());

                            if (!listOpenCalls.isEmpty()) {
                                for (CallMst cmst : listOpenCalls) {
                                    cmst.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                                    cmst = callMstService.saveCallMst(cmst);
                                }
                            }

                            CallMst local_callMst = new CallMst();
                            local_callMst.setCallStatus("Initialize");
                            local_callMst.setCallType("Incoming Call");
                            local_callMst.setDeviceBrand(null);
                            local_callMst.setDeviceOs(null);
                            local_callMst.setiMEIno(null);
                            local_callMst.setDeviceId(null);
                            local_callMst.setDeviceIp(null);
                            local_callMst.setStaticIp(null);
                            local_callMst.setDeviceName(null);
                            local_callMst.setCustomerId(customerMst_local);
                            local_callMst.setCustId(customerMst_local.getCustId());

                            local_callMst.setServiceId(serviceMst.getId());
                            local_callMst.setCategoryId(categoryMst.getId());
                            local_callMst.setLanguageId(languageMst.getId());
                            local_callMst.setCallOption(callType);
                            local_callMst.setCallMedium("WEB");
                            local_callMst.setActiveFlg(true);
                            local_callMst.setDeleteFlg(false);
                            local_callMst.setStartTime(CustomConvert.javaDateToTimeStamp(new Date(System.currentTimeMillis())));
                            local_callMst.setCustomerRequestTime(CustomConvert.javaDateToTimeStamp(new Date()));

                            local_callMst.setCallLocation(getCityLocation() + "," + getCountryLocation());
                            local_callMst.setCallLatitude(getLatitude());
                            local_callMst.setCallLongitude(getLongitude());

                            local_callMst = callMstService.saveCallMst(local_callMst);
                            callMst = callMstService.findCallMstById(local_callMst.getId());
                        } else if (callMstForRepeatcall != null) {
                            callMstForRepeatcall.setServiceId(serviceMst.getId());
                            callMstForRepeatcall.setCategoryId(categoryMst.getId());
                            callMstForRepeatcall.setLanguageId(languageMst.getId());
                            callMstForRepeatcall.setStartTime(CustomConvert.javaDateToTimeStamp(new Date()));
                            callMst = callMstService.saveCallMst(callMstForRepeatcall);
                        }

                        List<CustomerRmMap> mappedRMList = customerRMMapService.getRMMappedWithCustomer(customerMst_local.getId());
                        CustomerRmMap customerRmMap = null;
                        if (!mappedRMList.isEmpty()) {
                            customerRmMap = mappedRMList.get(0);
                        }
                        if (null != customerRmMap) {
                            EmployeeMst rm = employeeMstService.findEmployeeMstById(customerRmMap.getRmId().getId());
                            RmSrmMap rmSrmMap = rmSrmMapService.getSRMMappedWithRM(rm.getId());
                            EmployeeMst srm = employeeMstService.findEmployeeMstById(rmSrmMap.getSrmId().getId());
                            SrmBmMap srmBmMap = srmBmMapService.getBMMappedWithSRM(srm.getId());

                            if (!employeeCallStatusService.findFreeOnlineBMs(srmBmMap.getBmId().getId()).isEmpty()) {
                                Long callId;
                                callId = callMst.getId();
                                incomingCallResponse = new IncomingCallResponse("", "", callId.toString(), "Not Initiated", categoryMst.getCatgName(), languageMst.getLanguageName(), serviceMst.getServiceName(), customerMst_local.getCustId());

                                CallMst callMaster;

                                callMaster = callMstService.findNonDeletedCallMstById(Long.parseLong(incomingCallResponse.getCallId()));

                                //List<Object> L_E_Mst = Arrays.asList(agentFindingService.findAgents(categoryMstLocal.getCatgCd(), serviceMstLocal.getServiceCd(), languageMstLocal.getLanguageCd()).toArray());
                                EmployeeMst em = employeeMstService.findEmployeeMstById(srmBmMap.getBmId().getId());

                                if (em != null) {
                                    //em = (EmployeeMst) L_E_Mst.get(0);

                                    EmployeeCallStatus empCallStatus = new EmployeeCallStatus();
                                    List<EmployeeCallStatus> empClStatusList = employeeCallStatusService.findEmployeeCallStatusByEmpId(em);

                                    if (!empClStatusList.isEmpty()) {
                                        for (EmployeeCallStatus empstatus : empClStatusList) {
                                            empCallStatus = empstatus;
                                        }
                                        int count = empCallStatus.getCallCount();
                                        empCallStatus.setCallCount(count + 1);
                                        empCallStatus.setStatus(false);
                                        employeeCallStatusService.saveEmployeeCallStatus(empCallStatus);
                                    } else {
                                        empCallStatus.setEmpId(em);
                                        empCallStatus.setCallCount(1);
                                        empCallStatus.setStatus(false);
                                        employeeCallStatusService.saveEmployeeCallStatus(empCallStatus);
                                    }
                                    // status busy set

                                    List<TenancyEmployeeMap> tenancyEmployeeMaplist = tenancyEmployeeMapService.findVidyoTenantUrlByEmpId(em.getId());
                                    List<Long> unsortList = new ArrayList<>();
                                    for (TenancyEmployeeMap tenancyEmployeeMaplist1 : tenancyEmployeeMaplist) {
                                        unsortList.add(tenancyEmployeeMaplist1.getId());
                                    }
                                    Collections.sort(unsortList);

                                    TenancyEmployeeMap tenancyEmployeeMap = tenancyEmployeeMapService.findById(unsortList.get(unsortList.size() - 1));

                                    String roomLink = tenancyEmployeeMap.getRoomLink();
                                    incomingCallResponse.setUrl(roomLink);
                                    incomingCallResponse.setRoomName(tenancyEmployeeMap.getRoomName());
                                    incomingCallResponse.setAgentId(em.getLoginId());
                                    incomingCallResponse.setStatus("Initiated" + "||" + em.getFirstName() + " " + em.getLastName());
                                    incomingCallResponse.setCustomerId(customerMst_local.getCustId());
                                    Set<String> custIdSet = new TreeSet<>();
                                    custIdSet.add(customerMst_local.getCustId());
                                    CallScheduler.customerCallDetailsMap.put(callId.toString(), custIdSet);

                                    CallEmployeeMap l_CallEmpMap = new CallEmployeeMap();
                                    l_CallEmpMap.setCallId(callMaster.getId());
                                    l_CallEmpMap.setEmployeeId(em.getId());
                                    l_CallEmpMap.setCallType("Normal");
                                    l_CallEmpMap.setCustId(callMaster.getCustId());
                                    l_CallEmpMap.setRoomLink(roomLink);
                                    callMaster.setRoutingCallTime(CustomConvert.javaDateToTimeStamp(new Date()));
                                    callMaster.setRoomName(roomLink);
                                    callMstService.saveCallMst(callMaster);
                                    try {
                                        SocketMessage.send(callScheduler.getAdminSocket(), em.getLoginId(), "incomingCall#" + callMaster.getId() + "#" + callMaster.getCustId() + "#" + callMaster.getCallOption());
                                    } catch (Exception ex) {
                                        logger.error(ex);
                                    }
                                    CallScheduler.listCallEmp.add(l_CallEmpMap);

                                    selectPromoVideo();

                                } else {
                                    logger.info("No BM Found..........");
                                    callMaster = callMstService.findNonDeletedCallMstById(Long.parseLong(incomingCallResponse.getCallId()));
                                    callMaster.setCallStatus("No Agent Found");
                                    callMaster.setStartTime(CustomConvert.javaDateToTimeStamp(new Date()));
                                    //callMaster.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                                    callMstService.saveCallMst(callMaster);

                                }

                            } else {
                                logger.info("All BM's are busy or no RM's is available..........");
                                Long callId = callMst.getId();
                                CallMst callMaster = callMstService.findNonDeletedCallMstById(callId);
                                callMaster.setCallStatus("No Agent Found");
                                callMaster.setStartTime(CustomConvert.javaDateToTimeStamp(new Date()));
                                //callMaster.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                                callMstService.saveCallMst(callMaster);
                                incomingCallResponse = new IncomingCallResponse("", "", callId.toString(), "Not Initiated", categoryMst.getCatgDesc(), languageMst.getLanguageName(), serviceMst.getServiceName(), customerMst_local.getCustId());

                            }
                        }
                    }
                } else {
                    logger.info("Else...............serverIsOnDownTime....");
                    incomingCallResponse = new IncomingCallResponse("", "", "", "serverIsOnDownTime", "", "", "", "");

                }
            }

        } catch (ParseException | NumberFormatException e) {
            e.printStackTrace(System.err);
        }
        if (null != incomingCallResponse && !incomingCallResponse.getCallId().equals("")) {
            Long CallMstId = Long.parseLong(incomingCallResponse.getCallId());
            CallMst cm = callMstService.findCallMstById(CallMstId);
            if (null != cm) {
                request.getSession().setAttribute("callMstThroughWeb", cm);
            }
        }
        return incomingCallResponse;

    }

    public String getPromoURL() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        PromotionalVideoMst pvm = promotionalVideoMstService.findSelectedPromotionalVideo();
        if (null != pvm) {
            promoURL = promotionalVideoMstService.getVideoFileUrl(pvm.getFileUrl(), request, ctx);
        } else {
            promoURL = request.getContextPath() + "/promotional/promo.mp4";
        }

        return promoURL;
    }

    public void openSendLink() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        CallMst callMstLocal = (CallMst) request.getSession().getAttribute("callMstThroughWeb");
        if (!callMstLocal.getCallOption().equalsIgnoreCase("chat")) {
            try {
                logger.info("openSendLink....");
                guestEmail = null;

                RequestContext.getCurrentInstance().execute("closeAllDialog();PF('showSendLinkWidId').show();");
                RequestContext.getCurrentInstance().update("showSendLinkFormId");

            } catch (Exception e) {
                logger.error(e);
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "This facility not allowed on chat mode", "This facility not allowed on chat mode"));
        }
    }

    public void sendMultiWayCallEmailToGuest() {
        try {
            logger.info("sendMultiWayCallEmailToGuest....");
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            CallMst callMstLocal = (CallMst) request.getSession().getAttribute("callMstThroughWeb");
            String messageBody;
            CustomerMst customerMst_local = (CustomerMst) request.getSession().getAttribute("ormCustomerMaster");
            try {
                logger.info("customerMst_local:" + customerMst_local + "callMstLocal:" + callMstLocal + "guestEmail:" + guestEmail);
                if (null != guestEmail && !"".equals(guestEmail) && null != callMstLocal && null != customerMst_local) {
                    String toBeEncode = "callId=" + callMstLocal.getId() + "&resourceId=" + roomName;
                    logger.info("toBeEncode:" + toBeEncode);
                    String base64encodedString = Base64.getEncoder().encodeToString(toBeEncode.getBytes("utf-8"));
                    String websiteURL = Constants.WEB_PATH_URL;
                    messageBody = "<html><body>Dear &nbsp; Guest";
                    messageBody += ",&nbsp;<br></br><br></br>You have MultiWay call from " + customerMst_local.getFirstName() + " with our Service Agent ";

                    messageBody += "<br></br>";
                    messageBody += "<a href=";
                    messageBody += websiteURL + "/guestCallCheck?param=" + base64encodedString;

                    messageBody += ">Meeting Link</a>";
                    messageBody += "<br></br>";
                    messageBody += "<b>Note:</b>Please do not refresh the browser during the call.";
                    messageBody += "<br></br>";
                    messageBody += SendingMailUtil.TELE_THX_HTML;
                    messageBody += "</body><html/>";
                    logger.info("Before Send Email...");
                    boolean mailSend = SendingMailUtil.sendEMail(guestEmail, messageBody, SendingMailUtil.MULTI_WAY_CALL);
                    logger.info("After Send Email...");
                    if (mailSend) {
                        logger.info("mail sending was successfull... to Guest:" + guestEmail);
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Email Send Was Successful", "Send Successful!!"));
                    }
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please Provide Correct Email", "Please Provide Correct Email"));
                    RequestContext.getCurrentInstance().execute("PF('showSendLinkWidId').show();");
                }

                //RequestContext.getCurrentInstance().execute("PF('showSendLinkWidId').hide();");
                Thread.sleep(3000);
            } catch (Exception ex) {
                logger.error("Sending Email Error:" + ex.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Email Send Was UnSuccessful", "Send UnSuccessful!!"));
            }
        } catch (Exception e) {
            logger.error(e);
        }

    }

    public void setUrlForCustomerHome() {
        logger.info("setUrlForCustomer...............");

        try {
            logger.info("urlLink:" + urlLink);
            uinNo = urlLink.substring(urlLink.lastIndexOf("param=") + 6);

            logger.info("uinNo:" + uinNo);
            String callId = null;
            if (uinNo != null && !uinNo.equalsIgnoreCase("")) {
                // Decode
                byte[] base64decodedBytes = Base64.getDecoder().decode(uinNo);

                Map<String, String> getQueryMap = getQueryMap(new String(base64decodedBytes, "utf-8"));
                for (Map.Entry<String, String> entry : getQueryMap.entrySet()) {
                    String key = entry.getKey();
                    String val = entry.getValue();
                    if ("resourceId".equalsIgnoreCase(key)) {
                        roomUrl = val;
                    }
                    if ("callId".equalsIgnoreCase(key)) {
                        callId = val;
                    }
                }
                portal = Constants.vidyoportal;
                logger.info("roomUrl:" + roomUrl + "callId:" + callId);

                CallMst callMstLocal = callMstService.findCallMstById(Long.parseLong(callId));
                logger.info("callMstLocal:" + callMstLocal);
                if (null != callMstLocal) {
                    logger.info("Correct Call Mst-----------------------------");
                    if (null == callMstLocal.getEndTime()) {
                        customerName = "";
                        RequestContext.getCurrentInstance().execute("PF('redirectToCallIdVar').show();");
                        return;
                    }
                }

            } else {
                RequestContext.getCurrentInstance().execute("PF('invalidUrlLinkDialogVar').show();");
                return;
            }
            RequestContext.getCurrentInstance().execute("PF('invalidUrlLinkDialogVar').show();");

        } catch (UnsupportedEncodingException | NumberFormatException e) {
            logger.error("ERROR: setUrlForCustomer ", e);

            RequestContext.getCurrentInstance().execute("PF('invalidUrlLinkDialogVar').show();");

        }
    }

    public void guestURLValidationOnVC() {
        logger.info("guestURLValidationOnVC...............");

        try {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            logger.info("urlLink:" + urlLink);

            uinNo = urlLink.substring(urlLink.lastIndexOf("param=") + 6);
            logger.info("uinNo:" + uinNo);
            String callId = null;
            if (uinNo != null && !uinNo.equalsIgnoreCase("")) {
                // Decode
                byte[] base64decodedBytes = Base64.getDecoder().decode(uinNo);

                System.out.println("Original String: " + new String(base64decodedBytes, "utf-8"));
                Map<String, String> getQueryMap = getQueryMap(new String(base64decodedBytes, "utf-8"));
                for (Map.Entry<String, String> entry : getQueryMap.entrySet()) {
                    String key = entry.getKey();
                    String val = entry.getValue();
                    logger.info("Key:" + key + "VALUE:" + val);
                    if ("resourceId".equalsIgnoreCase(key)) {
                        roomName = val;
                    }
                    if ("callId".equalsIgnoreCase(key)) {
                        callId = val;
                    }
                }
                logger.info("roomName:" + roomName + "callId:" + callId);
                logger.info("CAse------setUrlForCustomerHome---------1-----------------------------------");
                CallMst callMstLocal = callMstService.findCallMstById(Long.parseLong(callId));
                logger.info("callMstLocal:" + callMstLocal);
                if (null != callMstLocal) {
                    logger.info("Correct Call Mst-----------------------------");
                    if (null == callMstLocal.getEndTime()) {
                        logger.info("Correct Call Mst AND CALL IS ONGOING-----------------------------");
//                        roomId = GenerateToken.getToken();
                        logger.info("roomId:" + roomId);
                        logger.info("joinViaBrowser Called-----------------------------");
                        request.getSession().setAttribute("callMstThroughWeb", callMstLocal);
                        if (callMstLocal.getCallOption().equalsIgnoreCase("audio")) {
                            RequestContext.getCurrentInstance().execute("joinViaBrowser();joinAudio()");
                        } else {
                            RequestContext.getCurrentInstance().execute("joinViaBrowser();");
                        }
                        return;
                    }
                }

            } else {
                RequestContext.getCurrentInstance().execute("PF('invalidUrlLinkDialogVar').show();");
                return;
            }
            RequestContext.getCurrentInstance().execute("PF('invalidUrlLinkDialogVar').show();");

        } catch (UnsupportedEncodingException | NumberFormatException e) {
            logger.error("ERROR: setUrlForCustomer ", e);

            RequestContext.getCurrentInstance().execute("PF('invalidUrlLinkDialogVar').show();");

        }
    }

    public void redirectToGuestCall() {
       
        logger.info( "uinNo:" + uinNo);
         HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        session.setAttribute("socketHostPublic", Constants.socketHostPublic);

        customerSocket = "Guest_" + GenerateCustId.generateCustId();
        custName = customerName + "~" + customerSocket;

        session.setAttribute("ormCustomerName", customerName);
        
        System.out.println("===============xxx"+customerName);
        RequestContext.getCurrentInstance().execute("location.href='"+request.getContextPath()+"/guestCall?param=" + uinNo+"'");
        

        String redirectURl = "location.href="+request.getContextPath()+"/guestCall?&param=" + uinNo;
        logger.info("redirectURl:" + redirectURl);
        
    }

    public void redirectToGuestHome() {
        logger.info("roomId:" + roomId);
        logger.info("roomName:" + roomName + "uinNo:" + uinNo);
        RequestContext.getCurrentInstance().execute("PF('agenthangup').show();");

    }

    public String redirectToGuestHome1() {
        String redirectURl = "/endGuestCall?faces-redirect=true";
        logger.info("redirectURl:" + redirectURl);
        return redirectURl;

    }

    public void openSelected(HttpServletRequest request) {
        logger.info("openSelected............................" + rmOption);

        RequestContext requestContext = RequestContext.getCurrentInstance();

        if (null != rmOption && !"".equals(rmOption)) {

            if (rmOption.equalsIgnoreCase("yes")) {
                requestContext.execute("PF('rmcallDlgWid').hide();PF('directOrScheduleCallWid').show();");

            } else {
                requestContext.execute("PF('rmcallDlgWid').hide();PF('serviceDialog').show()");

            }

        }
    }

    public void openDocDtl(HttpServletRequest request) {
        logger.info("openDocDtl............................" + docTobeOpen);
        String link;
        String linkName = "Open Document";

        String website;
        CallMst callMster = (CallMst) request.getSession().getAttribute("callMstThroughWeb");
        this.setCustomerId(callMster.getCustId());
        website = request.getRequestURL().substring(0, request.getRequestURL().indexOf("/", 9));

        RequestContext requestContext = RequestContext.getCurrentInstance();

        if (null != docTobeOpen && !"".equals(docTobeOpen)) {

            if (docTobeOpen.equalsIgnoreCase("fd")) {
                link = website + request.getContextPath() + "/faces/pages/forms/viewCustomerFormFd.xhtml";
                requestContext.execute("openDocumentsCustom('" + link + "','" + linkName + "')");
            } else if (docTobeOpen.equalsIgnoreCase("acc")) {
                link = website + request.getContextPath() + "/formCustomer";
                requestContext.execute("openDocumentsCustom('" + link + "','" + linkName + "')");
            } else if (docTobeOpen.equalsIgnoreCase("loan")) {
                link = website + request.getContextPath() + "/formLoan";
                requestContext.execute("openDocumentsCustom('" + link + "','" + linkName + "')");
            } else if (docTobeOpen.equalsIgnoreCase("cc")) {
                link = website + request.getContextPath() + "/formCustomer";
                requestContext.execute("openDocumentsCustom('" + link + "','" + linkName + "')");
            }

        }
    }

    public void resetOptions() {
        docTobeOpen = "";
        rmOption = "";
    }

    public void checkGuestCallEnded(HttpServletRequest request) {
        // String redirectURl = null;
        CallMst callMstLocal = (CallMst) request.getSession().getAttribute("callMstThroughWeb");
        if (null != callMstLocal) {
            callMstLocal = callMstService.findCallMstById(callMstLocal.getId());
            if (null != callMstLocal.getEndTime()) {
                logger.info("---->>>customer Component agenthangup Dialog showing From Here." + callMstLocal.getId());
                RequestContext.getCurrentInstance().execute("PF('agenthangup').show();disconnectCall();");
                request.getSession().setAttribute("callMstThroughWeb", null);

            }

        }

    }

    public void showInvalidUrl() {
        try {
            logger.info("showInvalidUrl......................." + urlLink);

            RequestContext.getCurrentInstance().execute("PF('invalidUrlLinkDialogVar').show();");
        } catch (Exception e) {
            logger.error("ERROR: showInvalidUrl ", e);
        }
    }

    private static Map<String, String> getQueryMap(String query) {
        String[] params = query.split("&");
        Map<String, String> map = new HashMap<>();

        for (String param : params) {
            String name = param.split("=")[0];
            String value = param.split("=")[1];
            map.put(name, value);
        }
        return map;
    }

    public void checkTimeOverForSchdeuleCall(HttpServletRequest request) {
        try {
            logger.info("In checkTimeOverForSchdeuleCall scheduledCallMstId in poll : " + scheduledCallMstId);
            if (scheduledCallMstId != null) {
                ScheduleCall scheduleCallDtl = callSchedulerService.findAllNonTakenScheduleCallById(scheduledCallMstId);

                Date scheduleStartDate = scheduleCallDtl.getScheduleDate();
                Date now = new Date();
                Calendar toleranceDate = Calendar.getInstance();
                toleranceDate.setTime(scheduleStartDate);
                toleranceDate.add(Calendar.MINUTE, +5);

                logger.info("scheduleStartDate : " + scheduleStartDate + "............" + "toleranceDate : " + toleranceDate);

                CallMst callMstL = callMstService.findCallMstById(scheduleCallDtl.getCallmstid());
                if (callMstL.getAgentPickupTime() == null) {
                    if (now.after(toleranceDate.getTime())) {
                        logger.info("Agent did not attend the schedule call");
                        request.getSession().setAttribute("callMstThroughWeb", null);

                        scheduleCallDtl.setExecuteStatus("Abandoned");
                        callSchedulerService.saveScheduleCall(scheduleCallDtl, null);
                        scheduledCallMstId = null;
                        RequestContext.getCurrentInstance().execute("closeAllDialog();hideSpinner();PF('routeToAgentOnScheduleWidget').show();");
                    }
                }

            }

        } catch (Exception e) {
            logger.info("ERROR : " + e.getMessage());
        }
    }

    public void checkErrors() {

    }

    public void setLocation() {
      
    }

    public List<ConferenceAgentsDto> getConferenceAgentsDtoListForParticipantCallDtls() {
        return conferenceAgentsDtoListForParticipantCallDtls;
    }

    public void setConferenceAgentsDtoListForParticipantCallDtls(List<ConferenceAgentsDto> conferenceAgentsDtoListForParticipantCallDtls) {
        this.conferenceAgentsDtoListForParticipantCallDtls = conferenceAgentsDtoListForParticipantCallDtls;
    }

    public List<ConferenceAgentsDto> getConferenceAgentsDtoListForParticipantCallDtlsSelection() {
        return conferenceAgentsDtoListForParticipantCallDtlsSelection;
    }

    public void setConferenceAgentsDtoListForParticipantCallDtlsSelection(List<ConferenceAgentsDto> conferenceAgentsDtoListForParticipantCallDtlsSelection) {
        this.conferenceAgentsDtoListForParticipantCallDtlsSelection = conferenceAgentsDtoListForParticipantCallDtlsSelection;
    }

    public boolean isMultipleParticipant() {
        return multipleParticipant;
    }

    public void setMultipleParticipant(boolean multipleParticipant) {
        this.multipleParticipant = multipleParticipant;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public String getCustomerSocket() {
        return customerSocket;
    }

    public void setCustomerSocket(String customerSocket) {
        this.customerSocket = customerSocket;
    }

    public String getScheduleCallId() {
        return scheduleCallId;
    }

    public void setScheduleCallId(String scheduleCallId) {
        this.scheduleCallId = scheduleCallId;
    }

    public boolean getScheduleStatusChange() {
        return scheduleStatusChange;
    }

    public void setScheduleStatusChange(boolean scheduleStatusChange) {
        this.scheduleStatusChange = scheduleStatusChange;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setPromoURL(String promoURL) {
        this.promoURL = promoURL;
    }

    public String getUploadedFilePath() {
        return uploadedFilePath;
    }

    public void setUploadedFilePath(String uploadedFilePath) {
        this.uploadedFilePath = uploadedFilePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getNewFileMessage() {
        return newFileMessage;
    }

    public void setNewFileMessage(String newFileMessage) {
        this.newFileMessage = newFileMessage;
    }

    public Boolean getPromotionalVideoLink() {
        return promotionalVideoLink;
    }

    public void setPromotionalVideoLink(Boolean promotionalVideoLink) {
        this.promotionalVideoLink = promotionalVideoLink;
    }

    public String getQualityQuery() {
        return qualityQuery;
    }

    public void setQualityQuery(String qualityQuery) {
        this.qualityQuery = qualityQuery;
    }

    public String getAbilityQuery() {
        return abilityQuery;
    }

    public void setAbilityQuery(String abilityQuery) {
        this.abilityQuery = abilityQuery;
    }

    public String getRecommendQuery() {
        return recommendQuery;
    }

    public void setRecommendQuery(String recommendQuery) {
        this.recommendQuery = recommendQuery;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    public String getDownMessage() {
        return downMessage;
    }

    public void setDownMessage(String downMessage) {
        this.downMessage = downMessage;
    }

    public String getServiceTimeStart() {
        return serviceTimeStart;
    }

    public void setServiceTimeStart(String serviceTimeStart) {
        this.serviceTimeStart = serviceTimeStart;
    }

    public String getServiceTimeEnd() {
        return serviceTimeEnd;
    }

    public void setServiceTimeEnd(String serviceTimeEnd) {
        this.serviceTimeEnd = serviceTimeEnd;
    }

    public String getBinaryImage() {
        return binaryImage;
    }

    public void setBinaryImage(String binaryImage) {
        this.binaryImage = binaryImage;
    }

    public String getDownTimeStart() {
        return downTimeStart;
    }

    public void setDownTimeStart(String downTimeStart) {
        this.downTimeStart = downTimeStart;
    }

    public String getDownTimeEnd() {
        return downTimeEnd;
    }

    public void setDownTimeEnd(String downTimeEnd) {
        this.downTimeEnd = downTimeEnd;
    }

    public String getVideoFileUrl() {
        return videoFileUrl;
    }

    public void setVideoFileUrl(String videoFileUrl) {
        this.videoFileUrl = videoFileUrl;
    }

    public String getDocTitle() {
        return docTitle;
    }

    public void setDocTitle(String docTitle) {
        this.docTitle = docTitle;
    }

    public Boolean getFileCaptionRenderer() {
        return fileCaptionRenderer;
    }

    public void setFileCaptionRenderer(Boolean fileCaptionRenderer) {
        this.fileCaptionRenderer = fileCaptionRenderer;
    }

    public List<FileDownloadDto> getFileDownloadDtoList() {
        return fileDownloadDtoList;
    }

    public void setFileDownloadDtoList(List<FileDownloadDto> fileDownloadDtoList) {
        this.fileDownloadDtoList = fileDownloadDtoList;
    }

    public Boolean getExistingCustomer() {
        return existingCustomer;
    }

    public void setExistingCustomer(Boolean existingCustomer) {
        this.existingCustomer = existingCustomer;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getGuestEmail() {
        return guestEmail;
    }

    public void setGuestEmail(String guestEmail) {
        this.guestEmail = guestEmail;
    }

    public String getUrlLink() {
        return urlLink;
    }

    public void setUrlLink(String urlLink) {
        this.urlLink = urlLink;
    }

    public String getUinNo() {
        return uinNo;
    }

    public void setUinNo(String uinNo) {
        this.uinNo = uinNo;
    }

    public String getSelectedCallType() {
        return selectedCallType;
    }

    public void setSelectedCallType(String selectedCallType) {
        this.selectedCallType = selectedCallType;
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public void setEventModel(ScheduleModel eventModel) {
        this.eventModel = eventModel;
    }

    public ScheduleEvent getEvent() {
        return event;
    }

    public void setEvent(ScheduleEvent event) {
        this.event = event;
    }

    public Long getScheduledCallMstId() {
        return scheduledCallMstId;
    }

    public void setScheduledCallMstId(Long scheduledCallMstId) {
        this.scheduledCallMstId = scheduledCallMstId;
    }

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    public Date getSchEndDateTime() {
        return schEndDateTime;
    }

    public void setSchEndDateTime(Date schEndDateTime) {
        this.schEndDateTime = schEndDateTime;
    }

    public List<ScheduleCallDto> getScheduledCallDtlDtoList() {
        return scheduledCallDtlDtoList;
    }

    public void setScheduledCallDtlDtoList(List<ScheduleCallDto> scheduledCallDtlDtoList) {
        this.scheduledCallDtlDtoList = scheduledCallDtlDtoList;
    }

    public Boolean getRenderedCallBtn() {
        return renderedCallBtn;
    }

    public void setRenderedCallBtn(Boolean renderedCallBtn) {
        this.renderedCallBtn = renderedCallBtn;
    }

    public Boolean getRenderedCallBtnForCustomer() {
        return renderedCallBtnForCustomer;
    }

    public void setRenderedCallBtnForCustomer(Boolean renderedCallBtnForCustomer) {
        this.renderedCallBtnForCustomer = renderedCallBtnForCustomer;
    }

    public Boolean getRenderedResendLinkBtn() {
        return renderedResendLinkBtn;
    }

    public void setRenderedResendLinkBtn(Boolean renderedResendLinkBtn) {
        this.renderedResendLinkBtn = renderedResendLinkBtn;
    }

    public Boolean getRenderedCancelSchdlBtn() {
        return renderedCancelSchdlBtn;
    }

    public void setRenderedCancelSchdlBtn(Boolean renderedCancelSchdlBtn) {
        this.renderedCancelSchdlBtn = renderedCancelSchdlBtn;
    }

    public String getDocTobeOpen() {
        return docTobeOpen;
    }

    public void setDocTobeOpen(String docTobeOpen) {
        this.docTobeOpen = docTobeOpen;
    }

    public String getServiceTimeText() {
        return serviceTimeText;
    }

    public void setServiceTimeText(String serviceTimeText) {
        this.serviceTimeText = serviceTimeText;
    }

    public Date getServiceStartTime() {
        return serviceStartTime;
    }

    public void setServiceStartTime(Date serviceStartTime) {
        this.serviceStartTime = serviceStartTime;
    }

    public Date getServiceEndTime() {
        return serviceEndTime;
    }

    public void setServiceEndTime(Date serviceEndTime) {
        this.serviceEndTime = serviceEndTime;
    }

    public Date getScheduleStartTimeeee() {
        return scheduleStartTimeeee;
    }

    public void setScheduleStartTimeeee(Date scheduleStartTimeeee) {
        this.scheduleStartTimeeee = scheduleStartTimeeee;
    }

    public Date getScheduleEndTimeeee() {
        return scheduleEndTimeeee;
    }

    public void setScheduleEndTimeeee(Date scheduleEndTimeeee) {
        this.scheduleEndTimeeee = scheduleEndTimeeee;
    }

    public String getPortal() {
        return portal;
    }

    public void setPortal(String portal) {
        this.portal = portal;
    }

    public String getCountryLocation() {
        return countryLocation;
    }

    public void setCountryLocation(String countryLocation) {
        this.countryLocation = countryLocation;
    }

    public String getStateLocation() {
        return stateLocation;
    }

    public void setStateLocation(String stateLocation) {
        this.stateLocation = stateLocation;
    }

    public String getCityLocation() {
        return cityLocation;
    }

    public void setCityLocation(String cityLocation) {
        this.cityLocation = cityLocation;
    }

    public String getRmOption() {
        return rmOption;
    }

    public void setRmOption(String rmOption) {
        this.rmOption = rmOption;
    }

}
