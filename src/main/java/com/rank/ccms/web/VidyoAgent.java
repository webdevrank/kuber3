package com.rank.ccms.web;

import com.rank.ccms.dto.CallEmployeeMap;
import com.rank.ccms.dto.CustomerAccountDto;
import com.rank.ccms.dto.CustomerLoanDto;
import com.rank.ccms.dto.EmployeeActivityDto;
import com.rank.ccms.dto.FileDownloadDto;
import com.rank.ccms.dto.FileReportDto;
import com.rank.ccms.dto.PerticipentDto;
import com.rank.ccms.dto.PromotionalVideoDto;
import com.rank.ccms.dto.ScheduleCallDto;
import com.rank.ccms.entities.CallDtl;
import com.rank.ccms.entities.CallFileUploadDtls;
import com.rank.ccms.entities.CallMst;
import com.rank.ccms.entities.CallRecords;
import com.rank.ccms.entities.CategoryMst;
import com.rank.ccms.entities.CustomerAccDtl;
import com.rank.ccms.entities.CustomerDeviceDtl;
import com.rank.ccms.entities.CustomerDtl;
import com.rank.ccms.entities.CustomerLoanDtl;
import com.rank.ccms.entities.CustomerMst;
import com.rank.ccms.entities.CustomerRmMap;
import com.rank.ccms.entities.EmployeeActivityDtl;
import com.rank.ccms.entities.EmployeeCallStatus;
import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.entities.EmployeeTypeMst;
import com.rank.ccms.entities.ForwardedCall;
import com.rank.ccms.entities.LanguageMst;
import com.rank.ccms.entities.PromotionalVideoMst;
import com.rank.ccms.entities.ReasonMst;
import com.rank.ccms.entities.ScheduleCall;
import com.rank.ccms.entities.ServiceMst;
import com.rank.ccms.entities.TenancyEmployeeMap;
import com.rank.ccms.service.CallDtlService;
import com.rank.ccms.service.CallFileUploadDtlsService;
import com.rank.ccms.service.CallMstService;
import com.rank.ccms.service.CallRecordsService;
import com.rank.ccms.service.CallSchedulerService;
import com.rank.ccms.service.CategoryMstService;
import com.rank.ccms.service.CustomerAccDtlService;
import com.rank.ccms.service.CustomerDeviceDtlService;
import com.rank.ccms.service.CustomerDtlService;
import com.rank.ccms.service.CustomerLoanDtlService;
import com.rank.ccms.service.CustomerMstService;
import com.rank.ccms.service.CustomerRMMapService;
import com.rank.ccms.service.EmployeeActivityDtlService;
import com.rank.ccms.service.EmployeeCallStatusService;
import com.rank.ccms.service.EmployeeMstService;
import com.rank.ccms.service.EmployeeTypeMstService;
import com.rank.ccms.service.ForwardedCallService;
import com.rank.ccms.service.LanguageMstService;
import com.rank.ccms.service.PromotionalVideoMstService;
import com.rank.ccms.service.ReasonMstService;
import com.rank.ccms.service.ServiceMstService;
import com.rank.ccms.service.TenancyEmployeeMapService;
import com.rank.ccms.util.CallScheduler;
import com.rank.ccms.util.Constants;
import com.rank.ccms.util.CreateJWTToken;
import com.rank.ccms.util.CustomConvert;
import com.rank.ccms.util.DateValidatorRangeCheck;
import com.rank.ccms.util.SendingMailUtil;
import com.rank.ccms.util.SocketMessage;
import com.rank.ccms.util.ThreadSafeSimpleDateFormat;
import com.rank.ccms.vidyo.util.VidyoAccessUser;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.jdom2.JDOMException;
import org.json.JSONException;
import org.json.JSONObject;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Decoder;

@Component
@Scope("session")
public class VidyoAgent implements Serializable {

    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(VidyoAgent.class);

    private String customerId = "";
    private String statusMessage = "Waiting for response...";
    private String message;
    private boolean enabled;
    private String unholdMessage;
    private Long agentComment;
    private Long rsnId;
    private boolean reasonsRenderer;
    private boolean customerRenderer;
    private boolean notReadyRenderer;
    private String calltype;
    private List<ServiceMst> serviceMstList = new ArrayList<>();
    private List<CustomerMst> customerMstList = new ArrayList<>();
    private String serviceSelected;
    private boolean serviceRenderer;
    private String agentStatus = "Ready";
    private String agentColor = "";
    private boolean hangupenabled = true;
    private boolean availableenabled = true;
    private boolean dialenabled = true;
    private boolean holdenabled = true;
    private boolean threewayenabled = true;
    private boolean forwardenabled = true;
    private boolean notreadyenabled = true;
    private boolean logoutenabled = false;
    private boolean selfviewenabled = false;
    private boolean gotoDashoardenabled = false;
    private Long agentHangUpComment;
    private Long agentNotreadyComment;
    private List<ReasonMst> reasonMstList = new ArrayList<>();
    private List<ReasonMst> hangupReasonMstList = new ArrayList<>();
    private List<ReasonMst> notreadyreasonMstList = new ArrayList<>();
    private String recordingStatus = "";
    private String entityId = "";
    private boolean forwardedcall = false;
    private boolean iworkImage = false;
    private String loginMessage = "";
    private boolean setupCheck = true;
    private boolean dashboardCheck = false;
    private String callTimeOut = "30000";
    private String autoPollingStart = "false";
    private String chatText = "";
    private String chatText1 = "";
    private String chatText2 = "";
    private String specialistId = "";
    private boolean hangupcalled = false;
    private String spcialistmessage;
    private String roomName = "";
    private String roomId = "";
    private String userName = "";
    private String scheduleRoomId = "";
    private String scheduleRoomName = "";
    private String scheduleAgentName = "";
    private String agentRoomName = "";
    private String agentName = "";
    private Integer lastdelay = 1000;
    private String socketHost = "";
    private String fileUploadMsg = "";
    private UploadedFile upFile;
    private boolean imageuploadrenderer = false;
    private boolean docuploadrenderer = false;
    private boolean disableUploadFileButton = true;
    public String msgToSend = "";
    private List<PromotionalVideoDto> listPromotionalVideoDto = new ArrayList<>();
    private Boolean renderedPromoVideoPanel = false;
    private Boolean renderedPromoVideoUserListPanel = false;
    private Boolean scanResultRender = false;
    private String binaryImage;
    private String docTobeOpen;
    private String customerName;
    private String newFileMessage = "";
    private List<PerticipentDto> fileUploadDtoList = new ArrayList<>();
    private String idCardFromCust;
    private String addProofFromCust;
    private String selfImageFromCust;
    private boolean existingCustomer = false;
    private boolean loanCustomer = false;
    private Long loanCustomerId;
    private String nameFromCRM;
    private String emailFromCRM;
    private String phoneFromCRM;
    private String accFromCRM;
    private boolean exportToCsvBtnStatus = false;
    private String matchResultText = "";
    private String videoFileUrl = "";
    private List<PerticipentDto> holdUnHoldDtoList = new ArrayList<>();
    private List<PerticipentDto> selectedHoldUnHoldDtoList = new ArrayList<>();
    private String promoURL;
    private String fileName = "";
    private String uploadedFilePath = "";
    private List<PerticipentDto> selectedFileuploadDtoList = new ArrayList<>();
    private List<FileDownloadDto> fileDownloadDtoList = new ArrayList<>();
    private boolean multipleParticipant = false;
    private String docTitle = "";
    private Boolean fileCaptionRenderer = false;
    private List<CustomerAccountDto> customerFileDetailsList;
    private List<FileReportDto> userReceivedFiles;
    private List<FileReportDto> userSendFiles;
    private List<CustomerAccountDto> filteredCustomerFileDetailsList;
    private List<FileReportDto> filteredUserReceivedFiles;
    private String guestEmail;
    private String jwtToken;
    private String serviceTimeText;
    private String schCustomerName;
    private Date schDateTime = new Date();
    private Date schEndDateTime = new Date();
    private Date serviceStartTime = new Date();
    private Date serviceEndTime = new Date();
    private Boolean renderedMakeScheduleButton = false;
    private String callType = "";
    private Long selectedServiceName;
    private Long selectedCategoryName;
    private Long selectedLanguageName;
    private Date scheduleStartTimeeee = new Date();
    private Date scheduleEndTimeeee = new Date();
    private String scheduleText;
    private Long selectedCustomerId;
    private String displayName;
    private String roomUrl;
    private String portal;
    private String vidyoUserid;
    private String password;
    private boolean isRm = false;

    @Autowired
    private CustomerDtlService customerDtlService;
    @Autowired
    private CustomerAccDtlService customerAccDtlService;
    @Autowired
    private EmployeeActivityDtlService employeeActivityDtlService;
    @Autowired
    private TenancyEmployeeMapService tenancyEmployeeMapService;
    @Autowired
    private ReasonMstService reasonMstService;
    @Autowired
    private EmployeeCallStatusService employeeCallStatusService;
    @Autowired
    private CallDtlService callDtlService;
    @Autowired
    private CallMstService callMasterService;
    @Autowired
    private CallRecordsComponent callRecordsComponent;
    @Autowired
    private CustomerMstService customerMstService;

    @Autowired
    private ServiceMstService serviceMstService;

    @Autowired
    CustomerRMMapService customerRMMapService;

    @Autowired
    private CategoryMstService categoryMstService;
    @Autowired
    private LanguageMstService langMstService;
    @Autowired
    private ImageComponent imageComponent;
    @Autowired
    public ForwardedCallService forwardedCallService;
    @Autowired
    public CustomerDeviceDtlService customerDeviceDtlService;
    @Autowired
    private CallRecordsService callRecordsService;
    @Autowired
    private EmployeeMstService employeeMstService;
    @Autowired
    private EmployeeTypeMstService employeeTypeMstService;
    @Autowired
    CallScheduler callScheduler;
    @Autowired
    private CustomerAccountComponent customerAccountComponent;
    @Autowired
    PromotionalVideoMstService promotionalVideoMstService;
    @Autowired
    CallFileUploadDtlsService callFileUploadDtlsService;
    @Autowired
    private CallSchedulerService scheduleCallService;
    @Autowired
    private CustomerLoanDtlService customerLoanDtlService;
    @Autowired
    CallSchedulerService callSchedulerService;

    public String getMatchResultText() {
        return matchResultText;
    }

    public void setMatchResultText(String matchResultText) {
        this.matchResultText = matchResultText;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getUnholdMessage() {
        return unholdMessage;
    }

    public void setUnholdMessage(String unholdMessage) {
        this.unholdMessage = unholdMessage;
    }

    public Long getAgentComment() {
        return agentComment;
    }

    public void setAgentComment(Long agentComment) {
        this.agentComment = agentComment;
    }

    public Long getRsnId() {
        return rsnId;
    }

    public void setRsnId(Long rsnId) {
        this.rsnId = rsnId;
    }

    public boolean getReasonsRenderer() {
        return reasonsRenderer;
    }

    public void setReasonsRenderer(boolean reasonsRenderer) {
        this.reasonsRenderer = reasonsRenderer;
    }

    public boolean getCustomerRenderer() {
        return customerRenderer;
    }

    public void setCustomerRenderer(boolean customerRenderer) {
        this.customerRenderer = customerRenderer;
    }

    public boolean getNotReadyRenderer() {
        return notReadyRenderer;
    }

    public void setNotReadyRenderer(boolean notReadyRenderer) {
        this.notReadyRenderer = notReadyRenderer;
    }

    public String getCalltype() {
        return calltype;
    }

    public void setCalltype(String calltype) {
        this.calltype = calltype;
    }

    public String getServiceSelected() {
        return serviceSelected;
    }

    public void setServiceSelected(String serviceSelected) {
        this.serviceSelected = serviceSelected;
    }

    public boolean isServiceRenderer() {
        return serviceRenderer;
    }

    public void setServiceRenderer(boolean serviceRenderer) {
        this.serviceRenderer = serviceRenderer;
    }

    public String getAgentStatus() {
        return agentStatus;
    }

    public void setAgentStatus(String agentStatus) {
        this.agentStatus = agentStatus;
    }

    public String getAgentColor() {
        return agentColor;
    }

    public void setAgentColor(String agentColor) {
        this.agentColor = agentColor;
    }

    public Long getAgentHangUpComment() {
        return agentHangUpComment;
    }

    public void setAgentHangUpComment(Long agentHangUpComment) {
        this.agentHangUpComment = agentHangUpComment;
    }

    public Long getAgentNotreadyComment() {
        return agentNotreadyComment;
    }

    public void setAgentNotreadyComment(Long agentNotreadyComment) {
        this.agentNotreadyComment = agentNotreadyComment;
    }

    public String getRecordingStatus() {
        return recordingStatus;
    }

    public void setRecordingStatus(String recordingStatus) {
        this.recordingStatus = recordingStatus;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public boolean isForwardedcall() {
        return forwardedcall;
    }

    public void setForwardedcall(boolean forwardedcall) {
        this.forwardedcall = forwardedcall;
    }

    public boolean isIworkImage() {
        return iworkImage;
    }

    public void setIworkImage(boolean iworkImage) {
        this.iworkImage = iworkImage;
    }

    public String getLoginMessage() {
        return loginMessage;
    }

    public void setLoginMessage(String loginMessage) {
        this.loginMessage = loginMessage;
    }

    public boolean isSetupCheck() {
        return setupCheck;
    }

    public void setSetupCheck(boolean setupCheck) {
        this.setupCheck = setupCheck;
    }

    public boolean getDashboardCheck() {
        return dashboardCheck;
    }

    public void setDashboardCheck(boolean dashboardCheck) {
        this.dashboardCheck = dashboardCheck;
    }

    public String getCallTimeOut() {
        return callTimeOut;
    }

    public void setCallTimeOut(String callTimeOut) {
        this.callTimeOut = callTimeOut;
    }

    public boolean getHangupenabled() {
        return hangupenabled;
    }

    public void setHangupenabled(boolean hangupenabled) {
        this.hangupenabled = hangupenabled;
    }

    public boolean getAvailableenabled() {
        return availableenabled;
    }

    public void setAvailableenabled(boolean availableenabled) {
        this.availableenabled = availableenabled;
    }

    public boolean getDialenabled() {
        return dialenabled;
    }

    public void setDialenabled(boolean dialenabled) {
        this.dialenabled = dialenabled;
    }

    public boolean getHoldenabled() {
        return holdenabled;
    }

    public void setHoldenabled(boolean holdenabled) {
        this.holdenabled = holdenabled;
    }

    public boolean getThreewayenabled() {
        return threewayenabled;
    }

    public void setThreewayenabled(boolean threewayenabled) {
        this.threewayenabled = threewayenabled;
    }

    public boolean getForwardenabled() {
        return forwardenabled;
    }

    public void setForwardenabled(boolean forwardenabled) {
        this.forwardenabled = forwardenabled;
    }

    public boolean getNotreadyenabled() {
        return notreadyenabled;
    }

    public void setNotreadyenabled(boolean notreadyenabled) {
        this.notreadyenabled = notreadyenabled;
    }

    public boolean getLogoutenabled() {
        return logoutenabled;
    }

    public void setLogoutenabled(boolean logoutenabled) {
        this.logoutenabled = logoutenabled;
    }

    public boolean getSelfviewenabled() {
        return selfviewenabled;
    }

    public void setSelfviewenabled(boolean selfviewenabled) {
        this.selfviewenabled = selfviewenabled;
    }

    public boolean getGotoDashoardenabled() {
        return gotoDashoardenabled;
    }

    public void setGotoDashoardenabled(boolean gotoDashoardenabled) {
        this.gotoDashoardenabled = gotoDashoardenabled;
    }

    public List<ServiceMst> getServiceMstList() {
        return serviceMstList;
    }

    public void setServiceMstList(List<ServiceMst> serviceMstList) {
        this.serviceMstList = serviceMstList;
    }

    public List<ReasonMst> getHangupReasonMstList() {
        return hangupReasonMstList;
    }

    public void setHangupReasonMstList(List<ReasonMst> hangupReasonMstList) {
        this.hangupReasonMstList = hangupReasonMstList;
    }

    public List<ReasonMst> getNotreadyreasonMstList() {
        return notreadyreasonMstList;
    }

    public void setNotreadyreasonMstList(List<ReasonMst> notreadyreasonMstList) {
        this.notreadyreasonMstList = notreadyreasonMstList;
    }

    public List<ReasonMst> getReasonMstList() {
        return reasonMstList;
    }

    public void setReasonMstList(List<ReasonMst> reasonMstList) {
        this.reasonMstList = reasonMstList;
    }

    public String getAutoPollingStart() {
        return autoPollingStart;
    }

    public void setAutoPollingStart(String autoPollingStart) {
        this.autoPollingStart = autoPollingStart;
    }

    public String getChatText() {
        return chatText;
    }

    public void setChatText(String chatText) {
        this.chatText = chatText;
    }

    public String getChatText1() {
        return chatText1;
    }

    public void setChatText1(String chatText1) {
        this.chatText1 = chatText1;
    }

    public String getChatText2() {
        return chatText2;
    }

    public void setChatText2(String chatText2) {
        this.chatText2 = chatText2;
    }

    public String getSpecialistId() {
        return specialistId;
    }

    public void setSpecialistId(String specialistId) {
        this.specialistId = specialistId;
    }

    public boolean getHangupcalled() {
        return hangupcalled;
    }

    public void setHangupcalled(boolean hangupcalled) {
        this.hangupcalled = hangupcalled;
    }

    public String getSpcialistmessage() {
        return spcialistmessage;
    }

    public void setSpcialistmessage(String spcialistmessage) {
        this.spcialistmessage = spcialistmessage;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getScheduleRoomId() {
        return scheduleRoomId;
    }

    public void setScheduleRoomId(String scheduleRoomId) {
        this.scheduleRoomId = scheduleRoomId;
    }

    public String getScheduleRoomName() {
        return scheduleRoomName;
    }

    public void setScheduleRoomName(String scheduleRoomName) {
        this.scheduleRoomName = scheduleRoomName;
    }

    public String getScheduleAgentName() {
        return scheduleAgentName;
    }

    public void setScheduleAgentName(String scheduleAgentName) {
        this.scheduleAgentName = scheduleAgentName;
    }

    public String getAgentRoomName() {
        return agentRoomName;
    }

    public void setAgentRoomName(String agentRoomName) {
        this.agentRoomName = agentRoomName;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getSocketHost() {
        return socketHost;
    }

    public void setSocketHost(String socketHost) {
        this.socketHost = socketHost;
    }

    public String getFileUploadMsg() {
        return fileUploadMsg;
    }

    public void setFileUploadMsg(String fileUploadMsg) {
        this.fileUploadMsg = fileUploadMsg;
    }

    public List<PromotionalVideoDto> getListPromotionalVideoDto() {
        return listPromotionalVideoDto;
    }

    public void setListPromotionalVideoDto(List<PromotionalVideoDto> listPromotionalVideoDto) {
        this.listPromotionalVideoDto = listPromotionalVideoDto;
    }

    public Boolean getRenderedPromoVideoPanel() {
        return renderedPromoVideoPanel;
    }

    public void getRenderedPromoVideoPanel(Boolean renderedPromoVideoPanel) {
        this.renderedPromoVideoPanel = renderedPromoVideoPanel;
    }

    public Boolean getRenderedPromoVideoUserListPanel() {
        return renderedPromoVideoUserListPanel;
    }

    public void setRenderedPromoVideoUserListPanel(Boolean renderedPromoVideoUserListPanel) {
        this.renderedPromoVideoUserListPanel = renderedPromoVideoUserListPanel;
    }

    public Boolean getScanResultRender() {
        return scanResultRender;
    }

    public void setScanResultRender(Boolean scanResultRender) {
        this.scanResultRender = scanResultRender;
    }

    public String getBinaryImage() {
        return binaryImage;
    }

    public void setBinaryImage(String binaryImage) {
        this.binaryImage = binaryImage;
    }

    public UploadedFile getUpFile() {
        return upFile;
    }

    public void setUpFile(UploadedFile upFile) {
        this.upFile = upFile;
    }

    public boolean isImageuploadrenderer() {
        return imageuploadrenderer;
    }

    public void setImageuploadrenderer(boolean imageuploadrenderer) {
        this.imageuploadrenderer = imageuploadrenderer;
    }

    public boolean isDocuploadrenderer() {
        return docuploadrenderer;
    }

    public void setDocuploadrenderer(boolean docuploadrenderer) {
        this.docuploadrenderer = docuploadrenderer;
    }

    public boolean isDisableUploadFileButton() {
        return disableUploadFileButton;
    }

    public void setDisableUploadFileButton(boolean disableUploadFileButton) {
        this.disableUploadFileButton = disableUploadFileButton;
    }

    public void setbuttons1() {
        this.imageuploadrenderer = true;
        this.docuploadrenderer = false;
    }

    public void setbuttons2() {
        this.imageuploadrenderer = false;
        this.docuploadrenderer = true;
    }

    public void specialistGetNotification() {
        logger.info("Specialist GET NOTIFICATION.....");

        RequestContext.getCurrentInstance().update("receiveForwardSpecalist");
        RequestContext.getCurrentInstance().update("receiveForwardSpecalist:receiveForwardTextSpecalist");
        RequestContext.getCurrentInstance().execute("PF('reasonHangupSelectDlg').hide();PF('notReadyReasonDlg').hide();PF('reasonSelectDlg').hide();");
        RequestContext.getCurrentInstance().execute("closeAllDialog();PF('forwardedNotificationSplecalist').show();");
        RequestContext.getCurrentInstance().execute("playAudio();");

    }

    public void specialistGetEndNotification() {
        logger.info("END OF CALL FOR SPECIALIST......");
        RequestContext.getCurrentInstance().execute("stopIntervalForNoti();stopAudio();PF('forwardedNotificationSplecalist').hide();");
        RequestContext.getCurrentInstance().execute("PF('reasonHangupSelectDlg').hide();PF('notReadyReasonDlg').hide();PF('reasonSelectDlg').hide();");
        RequestContext.getCurrentInstance().execute("disconnect();disconnectCall();closeAllDialog();PF('callEndNotification').show();");
        RequestContext.getCurrentInstance().execute("setTimeout(function(){ PF('forwardedNotificationSplecalist').hide(); }, 3000)");

    }

    private boolean searchSpecialistOnBrowser(String spId) throws RemoteException, UnsupportedEncodingException, JDOMException, IOException, InterruptedException {
        logger.info("..............searchSpecialistOnBrowser..........................." + spId);
        message = null;
        boolean flag = false;
        HttpSession session = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession();
        CallMst cm = (CallMst) session.getAttribute("callMst");
        cm = callMasterService.findCallMstById(cm.getId());

        EmployeeMst employeeMstLocal = (EmployeeMst) session.getAttribute("ormUserMaster");

        EmployeeMst local_em = employeeMstService.findEmployeeByUserId(spId);
        TenancyEmployeeMap tenancyEmployeeMap;
        if (null != local_em) {

            EmployeeTypeMst etm = employeeTypeMstService.findEmployeeTypeMstById(local_em.getEmpTypId().getId());
            if (etm.getTypeName().equalsIgnoreCase("Specialist")) {
                EmployeeCallStatus employeeCallStatus = null;
                if (!employeeCallStatusService.findEmployeeCallStatusByEmpId(local_em).isEmpty()) {
                    employeeCallStatus = employeeCallStatusService.findEmployeeCallStatusByEmpId(local_em).get(0);
                }
                if (null != employeeCallStatus && employeeCallStatus.getStatus()) {
                    List<TenancyEmployeeMap> tenancyEmployeeMaplist = tenancyEmployeeMapService.findVidyoTenantUrlByEmpId(employeeMstLocal.getId());
                    List<Long> unsortList = new ArrayList<>();
                    for (TenancyEmployeeMap tenancyEmployeeMaplist1 : tenancyEmployeeMaplist) {
                        unsortList.add(tenancyEmployeeMaplist1.getId());
                    }
                    Collections.sort(unsortList);
                    long val = unsortList.get(unsortList.size() - 1);
                    tenancyEmployeeMap = tenancyEmployeeMapService.findById(val);

                    CallEmployeeMap l_CallEmpMap = new CallEmployeeMap();
                    l_CallEmpMap.setCallId(cm.getId());
                    l_CallEmpMap.setEmployeeId(local_em.getId());
                    l_CallEmpMap.setCallType("ThreeWay");
                    l_CallEmpMap.setRoomLink(tenancyEmployeeMap.getRoomLink());

                    CallScheduler.listCallEmp.add(l_CallEmpMap);
                    try {
                        logger.info(local_em.getLoginId() + "ThreeWay#" + cm.getId() + "#" + tenancyEmployeeMap.getRoomLink() + "#" + tenancyEmployeeMap.getRoomName());
                        SocketMessage.send(callScheduler.getAdminSocket(), local_em.getLoginId(), "ThreeWay#" + cm.getId() + "#" + tenancyEmployeeMap.getRoomLink() + "#" + tenancyEmployeeMap.getRoomName() + "#" + cm.getCallOption());
                    } catch (Exception ex) {
                        logger.error(ex.getMessage());
                    }
                    flag = true;

                    Timestamp dt_time = null;
                    try {
                        dt_time = CustomConvert.javaDateToTimeStamp(new Date());
                    } catch (ParseException ex) {
                        logger.error(ex.getMessage());
                    }

                    CallDtl callDtl = new CallDtl();
                    callDtl.setCallMstId(cm);
                    callDtl.setCallTypeInfo("Threeway Specialist");
                    callDtl.setHandeledById(local_em);
                    callDtl.setActiveFlg(true);
                    callDtl.setDeleteFlg(false);
                    callDtl.setStartTime(dt_time);
                    callDtl = callDtlService.saveCallDtl(callDtl);

                    ForwardedCall forwardedCall = new ForwardedCall();
                    forwardedCall.setEmpId(local_em);
                    forwardedCall.setRoomLink(tenancyEmployeeMap.getRoomLink());
                    forwardedCall.setRoomName(tenancyEmployeeMap.getRoomName());
                    forwardedCall.setEntityId(tenancyEmployeeMap.getEntityId());
                    forwardedCall.setCallId(cm);
                    forwardedCall.setForwardedDatetime(dt_time);
                    forwardedCall.setActiveFlg(true);
                    forwardedCall.setDeleteFlg(false);
                    forwardedCall.setPrevEmpId(employeeMstLocal.getId());
                    forwardedCall.setSelectedServiceId(cm.getServiceId());
                    forwardedCall.setFromServiceId(cm.getServiceId());
                    forwardedCall.setCallDtlId(callDtl.getId());
                    forwardedCallService.saveForwardedCalls(forwardedCall, employeeMstLocal);

                    this.spcialistmessage = spId + " is online and waiting for joining";
                    employeeCallStatus.setStatus(false);
                    employeeCallStatusService.saveEmployeeCallStatus(employeeCallStatus);

                    RequestContext.getCurrentInstance().execute("$('#spinner').hide();PF('specilistInfoDia').show();setTimeout(function(){PF('specilistInfoDia').hide(); }, 3000);");
                } else {
                    Long lastActivity = employeeActivityDtlService.findMaxIdByEmpId(local_em.getId());
                    EmployeeActivityDtl login_activityDtl = employeeActivityDtlService.findByActivityId(lastActivity);
                    if (login_activityDtl != null) {
                        if (login_activityDtl.getActivity().equalsIgnoreCase("login")) {
                            flag = false;
                            this.spcialistmessage = "Sorry! Specialist Logged in but is not ready";
                            RequestContext.getCurrentInstance().execute("$('#spinner').hide();PF('specilistInfoDia5').show();setTimeout(function(){PF('specilistInfoDia5').hide(); }, 3000);");
                        } else if (login_activityDtl.getActivity().equalsIgnoreCase("Hang Up")) {
                            flag = false;
                            this.spcialistmessage = "Sorry! Specialist Logged in but is not ready";
                            RequestContext.getCurrentInstance().execute("$('#spinner').hide();PF('specilistInfoDia5').show();setTimeout(function(){PF('specilistInfoDia5').hide(); }, 3000);");
                        } else if (login_activityDtl.getActivity().equalsIgnoreCase("logout")) {
                            flag = false;
                            this.spcialistmessage = "Sorry! Specialist not Logged in";
                            RequestContext.getCurrentInstance().execute("$('#spinner').hide();PF('specilistInfoDia3').show();setTimeout(function(){PF('specilistInfoDia3').hide(); }, 3000);");
                        } else if (login_activityDtl.getActivity().equalsIgnoreCase("Call Started")) {
                            flag = false;
                            this.spcialistmessage = "Sorry! Specialist is Busy";
                            RequestContext.getCurrentInstance().execute("$('#spinner').hide();PF('specilistInfoDia2').show();setTimeout(function(){PF('specilistInfoDia2').hide(); }, 3000);");

                        } else if (login_activityDtl.getActivity().equalsIgnoreCase("Threeway Specialist")) {
                            flag = false;
                            this.spcialistmessage = "Sorry! Specialist is Busy";
                            RequestContext.getCurrentInstance().execute("$('#spinner').hide();PF('specilistInfoDia2').show();setTimeout(function(){PF('specilistInfoDia2').hide(); }, 3000);");
                        } else {
                            flag = false;
                            this.spcialistmessage = "Sorry! Specialist Logged in but is not ready";
                            RequestContext.getCurrentInstance().execute("$('#spinner').hide();PF('specilistInfoDia5').show();setTimeout(function(){PF('specilistInfoDia5').hide(); }, 3000);");
                        }
                    } else {
                        flag = false;
                        this.spcialistmessage = "Sorry! Specialist not Logged in";
                        RequestContext.getCurrentInstance().execute("$('#spinner').hide();PF('specilistInfoDia3').show();setTimeout(function(){PF('specilistInfoDia3').hide(); }, 3000);");
                    }
                }

            } else {

                this.spcialistmessage = "Sorry! Specialist not found";
                RequestContext.getCurrentInstance().execute("$('#spinner').hide();PF('specilistInfoDia1').show();setTimeout(function(){PF('specilistInfoDia1').hide(); }, 3000);");
                flag = false;

            }
        }
        return flag;
    }

    public void checkCustomer(String custId) {
        if (null != custId && !"".equals(custId)) {
            RequestContext.getCurrentInstance().execute("document.getElementById('checkcustomer').click();");
        }
    }

    public void checkSpecialist(String splId) {
        if (null != splId && !"".equals(splId)) {
            RequestContext.getCurrentInstance().execute("document.getElementById('searchspecialist').click();");
        }
    }

    public void searchSpecialist(String spId) throws RemoteException, UnsupportedEncodingException, JDOMException, IOException, InterruptedException {
        logger.info("VideoAgent-----searchSpecialist..........................................");
        message = null;
        this.spcialistmessage = "";
        boolean flag;
        EmployeeMst em = employeeMstService.findEmployeeByUserId(spId);

        if (em != null) {
            flag = searchSpecialistOnBrowser(spId);
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            if (flag) {

                request.getSession().setAttribute("specialist", em);
            }
        }

    }

    public void checkReceive(HttpServletRequest request) {
        CallMst callMstLocal = (CallMst) request.getSession().getAttribute("callMst");
        callMstLocal = callMasterService.findCallMstById(callMstLocal.getId());
        if (callMstLocal.getCustomerPickupTime() != null) {

            this.iworkImage = true;
            RequestContext.getCurrentInstance().execute("startCounterTimer();PF('cust').hide();stopDialTimer();sendRoomUrlFn();");
        }
    }

    public void pickRMScheduleCall(HttpServletRequest request) {
        try {
            CallMst callMstLocal = (CallMst) request.getSession().getAttribute("callMst");
            callMstLocal = callMasterService.findCallMstById(callMstLocal.getId());
            callMstLocal.setAgentPickupTime(CustomConvert.javaDateToTimeStamp(new Date()));
            callMstLocal = callMasterService.saveCallMst(callMstLocal);
            logger.info("ccallType()" + callMstLocal.getCallType());
            if (callMstLocal.getCallType().equalsIgnoreCase("Schedule Call") || callMstLocal.getCallType().equalsIgnoreCase("ScheduleCall")) {
                if (null == callMstLocal.getEndTime()) {
                    CustomerMst cmm = customerMstService.findCustomerMstByCustId(callMstLocal.getCustId());
                    // String custName = cmm.getFirstName() + "~" + cmm.getCustId();/
                    logger.info("schedule_call#received#'" + cmm.getCustId() + "')");
                    SocketMessage.send(callScheduler.getAdminSocket(), cmm.getCustId(), "schedule_call#received");

                }
            }
            logger.info("Schedule call Pick By Agent-CallMst:" + callMstLocal);

        } catch (Exception e) {
            logger.error(e);
        }
    }

    public void checkReceiveSchedule(HttpServletRequest request) {

        CallMst callMstLocal = (CallMst) request.getSession().getAttribute("callMst");
        if (null != callMstLocal) {
            callMstLocal = callMasterService.findCallMstById(callMstLocal.getId());

            if (callMstLocal.getCustomerPickupTime() != null) {

                this.iworkImage = true;
                RequestContext.getCurrentInstance().execute("stopReceiveInterval();closeAllDialog();call();startCounterTimer()");
            }
        }
    }

    public void checkScheduleReceive(HttpServletRequest request) {
        logger.info("checkScheduleReceive.....");
        CallMst callMstLocal = (CallMst) request.getSession().getAttribute("callMst");
        callMstLocal = callMasterService.findCallMstById(callMstLocal.getId());
        if (callMstLocal.getAgentPickupTime() == null) {
            try {
                RequestContext.getCurrentInstance().execute("sendScheduleAcknowledgement('" + callMstLocal.getCustId() + "')");
                callMstLocal.setAgentPickupTime(CustomConvert.javaDateToTimeStamp(new Date(System.currentTimeMillis())));
                callMasterService.saveCallMst(callMstLocal);
            } catch (ParseException ex) {
                Logger.getLogger(VidyoAgent.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (callMstLocal.getCustomerPickupTime() != null) {

            this.iworkImage = true;
            RequestContext.getCurrentInstance().execute("stopReceiveInterval();closeAllDialog();call();startCounterTimer()");
        } else {

            RequestContext.getCurrentInstance().execute("startCustomerReceiveCheck();");

        }
    }

    public void checkMissed(HttpServletRequest request) {
        this.setHangupenabled(true);
        request.getSession().setAttribute("callMst", null);
    }

    public void clearPromoVideoList() {

        listPromotionalVideoDto = new ArrayList<>();
        renderedPromoVideoPanel = false;
        renderedPromoVideoUserListPanel = false;
    }

    public void loadPromotionVideo(HttpServletRequest request) {

        try {

            renderedPromoVideoUserListPanel = false;
            holdUnHoldDtoList = new ArrayList<>();
            selectedHoldUnHoldDtoList = new ArrayList<>();

            getPerticipentListForHold(request);
            if (holdUnHoldDtoList.size() > 1) {

                renderedPromoVideoUserListPanel = true;
                RequestContext.getCurrentInstance().update("promoVideoFormId");
                RequestContext.getCurrentInstance().execute("PF('promoVideoWid').show();");
            } else {

                renderedPromoVideoUserListPanel = false;
                RequestContext.getCurrentInstance().update("promoVideoFormId");
                this.setSelectedHoldUnHoldDtoList(this.getHoldUnHoldDtoList());
                holdCustomer(request);

            }

        } catch (Exception e) {
            logger.error(" Error " + e.getMessage());
        }
    }

    public void getPerticipentListForHold(HttpServletRequest request) {

        try {
            CallMst callMster = (CallMst) request.getSession().getAttribute("callMst");
            EmployeeMst em = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
            holdUnHoldDtoList = new ArrayList<>();
            selectedHoldUnHoldDtoList = new ArrayList<>();

            if (null != callMster) {
                callMster = callMasterService.findCallMstById(callMster.getId());

                //This is for add customer........
                PerticipentDto lPerticipentDto = new PerticipentDto();
                if (!callMster.getCallType().equalsIgnoreCase("Dial Call")) {
                    CustomerMst lCustomerMst = customerMstService.findCustomerMstByCustId(callMster.getCustId());
                    lPerticipentDto.setId((long) 0);
                    lPerticipentDto.setLoginId(callMster.getCustId());
                    lPerticipentDto.setNameAndLoginId(lCustomerMst.getFirstName() + "(" + lCustomerMst.getEmail() + ")");
                    holdUnHoldDtoList.add(lPerticipentDto);
                } else {
                    EmployeeMst lEmployeeMst;
                    List<CallDtl> dtlList = callDtlService.findCallDtlByCallMasterIdAndCallTypeInfo(callMster.getId(), "Dial");
                    if (!dtlList.isEmpty()) {

                        lEmployeeMst = employeeMstService.findEmployeeMstById(dtlList.get(0).getHandeledById().getId());
                    } else {
                        lEmployeeMst = employeeMstService.findAllEmployeeByUserId(callMster.getCustId());

                    }

                    if (em.getId() != (long) lEmployeeMst.getId()) {

                        lPerticipentDto.setId(lEmployeeMst.getId());

                        lPerticipentDto.setLoginId(lEmployeeMst.getLoginId());
                        String name;
                        if (lEmployeeMst.getMidName() != null && !lEmployeeMst.getMidName().equals("")) {
                            name = lEmployeeMst.getFirstName() + " " + lEmployeeMst.getMidName() + " " + lEmployeeMst.getLastName();
                        } else {
                            name = lEmployeeMst.getFirstName() + " " + lEmployeeMst.getLastName();
                        }
                        lPerticipentDto.setNameAndLoginId(name + "(" + lEmployeeMst.getEmail() + ")");
                        holdUnHoldDtoList.add(lPerticipentDto);
                    }

                }

                List<CallDtl> localCallDtlList = callDtlService.findNonEndedCallDtlByCallMasterIdAndExcludeCurrentEmployee(callMster.getId(), em.getId());

                for (CallDtl lCallDtl : localCallDtlList) {

                    lPerticipentDto = new PerticipentDto();

                    EmployeeMst lEmployeeMst = employeeMstService.findEmployeeMstById(lCallDtl.getHandeledById().getId());
                    lPerticipentDto.setId(lEmployeeMst.getId());

                    lPerticipentDto.setLoginId(lEmployeeMst.getLoginId());
                    String name;
                    if (lEmployeeMst.getMidName() != null && !lEmployeeMst.getMidName().equals("")) {
                        name = lEmployeeMst.getFirstName() + " " + lEmployeeMst.getMidName() + " " + lEmployeeMst.getLastName();
                    } else {
                        name = lEmployeeMst.getFirstName() + " " + lEmployeeMst.getLastName();
                    }
                    lPerticipentDto.setNameAndLoginId(name + "(" + lEmployeeMst.getEmail() + ")");

                    holdUnHoldDtoList.add(lPerticipentDto);

                }
                Set<PerticipentDto> set = new HashSet<>(holdUnHoldDtoList);
                holdUnHoldDtoList = new ArrayList<>(set);

            }
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }

    public void holdCustomer(HttpServletRequest request) {
        CallMst callMstLocal = (CallMst) request.getSession().getAttribute("callMst");
        if (holdUnHoldDtoList.size() > 1 && selectedHoldUnHoldDtoList.isEmpty()) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please select atleast one participant", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        } else {
            try {
                EmployeeMst empMst = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
                RequestContext.getCurrentInstance().execute("closeAllDialog();holdDisplay();PF('timerDivW').show();");
                ReasonMst reasonMst = reasonMstService.findReasonMstByReasonCode("HLD01");
                if (null == reasonMst) {
                    reasonMst = new ReasonMst();
                    reasonMst.setType("Hold");
                    reasonMst.setReasonCd("HLD01");
                    reasonMst.setReasonDesc("Call hold by agent");
                    reasonMst.setActiveFlg(true);
                    reasonMst.setDeleteFlg(false);
                    reasonMst = reasonMstService.save(reasonMst, empMst);
                }

                EmployeeActivityDtl employeeActivityDtl = new EmployeeActivityDtl();
                employeeActivityDtl.setActivity("HOLD CALL");
                employeeActivityDtl.setEmpId(empMst);
                employeeActivityDtl.setStartTime(CustomConvert.javaDateToTimeStamp(new Date(System.currentTimeMillis())));
                employeeActivityDtl.setReasonId(reasonMst);
                employeeActivityDtlService.save(employeeActivityDtl);

                for (PerticipentDto lPerticipentDto : selectedHoldUnHoldDtoList) {
                    if (null != lPerticipentDto.getLoginId() && !"".equals(lPerticipentDto.getLoginId())) {

                        try {
                            logger.info("sendHold('" + lPerticipentDto.getLoginId() + "','" + "hold#" + callMstLocal.getId() + "#" + videoFileUrl + "')");
                            RequestContext.getCurrentInstance().execute("sendHold('" + lPerticipentDto.getLoginId() + "','" + "hold#" + callMstLocal.getId() + "#" + videoFileUrl + "')");

                        } catch (Exception ex) {
                            Logger.getLogger(VidyoAgent.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                }
                dashboardCheck = true;
                forwardenabled = true;
                threewayenabled = true;
                hangupenabled = true;
                logoutenabled = true;
                holdenabled = true;
                renderedMakeScheduleButton = true;

            } catch (ParseException e) {
                logger.info("Error:holdCustomer-->" + e.getMessage());
            }
        }

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

    public void unholdCustomer(HttpServletRequest request) {
        logger.info("UNHOLD..... ");
        CallMst callMstLocal = (CallMst) request.getSession().getAttribute("callMst");

        try {

            if (callMstLocal != null) {
                for (PerticipentDto lDto : selectedHoldUnHoldDtoList) {

                    if (null != lDto.getLoginId() && !"".equals(lDto.getLoginId())) {
                        logger.info("sendUnhold('" + lDto.getLoginId() + "','" + "unhold#" + callMstLocal.getId() + "')");
                        RequestContext.getCurrentInstance().execute("sendUnhold('" + lDto.getLoginId() + "','" + "unhold#" + callMstLocal.getId() + "')");

                    }
                }
                EmployeeMst empMst = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
                EmployeeActivityDtl employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(empMst.getId(), "HOLD CALL");
                if (employeeActivityDtl != null) {
                    employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date(System.currentTimeMillis())));
                    employeeActivityDtlService.save(employeeActivityDtl);
                }
            }

        } catch (ParseException e) {
            logger.info("Error:" + e.getMessage());
        } finally {
            dashboardCheck = true;
            forwardenabled = false;
            threewayenabled = false;
            hangupenabled = false;
            logoutenabled = true;
            holdenabled = false;
            renderedMakeScheduleButton = false;

        }
        request.getSession();
    }

    public void executeHold(HttpServletRequest request) {
        try {
            CallMst cm = (CallMst) request.getSession().getAttribute("callMst");
            if (cm != null) {
                promotinalVideoSet();
                RequestContext.getCurrentInstance().update("spinnerReplace");
                RequestContext.getCurrentInstance().execute("muteAll();replaceVideoStrm();");

            }
        } catch (Exception e) {
            logger.error("ERROR: ", e);
        } finally {
            this.setSetupCheck(false);
            this.dashboardCheck = true;
            this.hangupenabled = true;
            this.holdenabled = true;
            this.threewayenabled = true;
            this.forwardenabled = true;
            this.logoutenabled = true;
            this.renderedMakeScheduleButton = true;

        }
    }

    public void executeUnhold(HttpServletRequest request) {
        try {
            CallMst cm = (CallMst) request.getSession().getAttribute("callMst");
            if (cm != null) {
                RequestContext.getCurrentInstance().execute("unholdWithPersistantingStateCustomer();restoreVideoConfrnce();");
            }
        } catch (Exception e) {
            logger.error("ERROR: ", e);
        } finally {
            this.setSetupCheck(false);
            this.dashboardCheck = true;
            this.setAvailableenabled(true);
            this.setNotreadyenabled(true);
            this.setHangupenabled(true);
            this.setDialenabled(true);
            this.setHoldenabled(true);
            this.setThreewayenabled(true);
            this.setForwardenabled(true);
            this.setLogoutenabled(true);

        }

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
            RequestContext.getCurrentInstance().update("imgIworksForm");
        } catch (Exception e) {
            logger.error("ERROR: ", e);
        }
    }

    public void sendHangUpMessage(HttpServletRequest request) {
        CallMst callMstLocal = (CallMst) request.getSession().getAttribute("callMst");
        callMstLocal = callMasterService.findCallMstById(callMstLocal.getId());
        Set<String> habgUpCustIdSet = (Set<String>) CallScheduler.customerCallDetailsMap.get(callMstLocal.getId().toString());
        if (habgUpCustIdSet != null && !this.hangupcalled) {
            for (String cId : habgUpCustIdSet) {
                logger.info("Customer Id at HangUp:" + cId);
                if (null != cId && !"".equals(cId)) {
                    CustomerMst cm = customerMstService.findCustomerMstByCustId(cId);
                    CustomerDeviceDtl deviceDtl = customerDeviceDtlService.findCustomerDeviceDtlByCustId(cm);
                    if (null != deviceDtl) {
                        deviceDtl.setLoginInfo(1);
                        customerDeviceDtlService.saveCustomerDeviceDtl(deviceDtl, null);
                        String osType = deviceDtl.getMobileOsType();

                        if (null != osType && !"WEB".equalsIgnoreCase(osType)) {
                            try {
                                SocketMessage.send(callScheduler.getAdminSocket(), cId, "hangUp#" + callMstLocal.getId());

                            } catch (Exception ex) {
                                Logger.getLogger(VidyoAgent.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
            }
        }

    }

    public void sendMissedCallMessage(HttpServletRequest request) {
        CallMst callMstLocal = (CallMst) request.getSession().getAttribute("callMst");
        callMstLocal = callMasterService.findCallMstById(callMstLocal.getId());
        Set<String> habgUpCustIdSet = (Set<String>) CallScheduler.customerCallDetailsMap.get(callMstLocal.getId().toString());

        if (habgUpCustIdSet != null && !this.hangupcalled) {
            for (String cId : habgUpCustIdSet) {
                logger.info("Customer Id at HangUp:" + cId);
                if (null != cId && !"".equals(cId)) {
                    CustomerMst cm = customerMstService.findCustomerMstByCustId(cId);
                    CustomerDeviceDtl deviceDtl = customerDeviceDtlService.findCustomerDeviceDtlByCustId(cm);
                    if (null != deviceDtl) {
                        String osType = deviceDtl.getMobileOsType();
                        if (null != osType) {
                            try {
                                SocketMessage.send(callScheduler.getAdminSocket(), cId, "missedCall#" + callMstLocal.getId());
                            } catch (Exception ex) {
                                Logger.getLogger(VidyoAgent.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }
                    }
                }
            }
        }

    }

    public void specialistHangUp(HttpServletRequest request) {

        logger.info("SPECIALIST HANG UP CALL.....");
        this.fileUploadDtoList.clear();
        this.fileDownloadDtoList.clear();
        String inCallType = "";
        try {
            CallMst callMstLocal = (CallMst) request.getSession().getAttribute("callMst");
            if (callMstLocal != null) {
                callMstLocal = callMasterService.findCallMstById(callMstLocal.getId());
                if (request.getSession().getAttribute("incomingCallType") != null) {

                    inCallType = request.getSession().getAttribute("incomingCallType").toString();
                }

                this.iworkImage = false;
                RequestContext.getCurrentInstance().update("imgIworksForm");
                imageComponent.setForwardChatHistory(false);
                RequestContext.getCurrentInstance().update("forwardchattext");

                ReasonMst reasonMst = null;
                if (null != this.getAgentHangUpComment()) {
                    reasonMst = reasonMstService.findNonDeletedById(this.getAgentHangUpComment());
                }
                if (null == reasonMst) {
                    reasonMst = reasonMstService.findReasonMstByReasonCode("HUP01");
                }

                EmployeeMst empMst = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");

                String ss = empMst.getLoginId() + "~" + empMst.getFirstName() + " " + empMst.getLastName();
                RequestContext.getCurrentInstance().execute("sendForwardSuccessAcknowledgement('" + callMstLocal.getCustId() + "','" + ss + "')");

                CallDtl clDtl = callDtlService.findCallDtlByCallMasterIdAndAgentNotForwarded(callMstLocal.getId(), empMst.getId());

                clDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                clDtl.setDeleteFlg(false);
                clDtl.setAgentComments(reasonMst.getReasonDesc());
                clDtl.setActiveFlg(true);
                callDtlService.saveCallDtl(clDtl);

                EmployeeActivityDtl employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(empMst.getId(), "Call Started");

                if (employeeActivityDtl != null) {
                    employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                    employeeActivityDtlService.save(employeeActivityDtl);

                }

                employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(empMst.getId(), "Incoming Call");

                if (employeeActivityDtl != null) {
                    employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                    employeeActivityDtlService.save(employeeActivityDtl);

                }

                employeeActivityDtl = new EmployeeActivityDtl();
                employeeActivityDtl.setActivity("Hang Up");
                employeeActivityDtl.setEmpId(empMst);
                employeeActivityDtl.setStartTime(CustomConvert.javaDateToTimeStamp(new Date()));
                ReasonMst reason = reasonMstService.findReasonMstByReasonType("Hang Up");
                employeeActivityDtl.setReasonId(reason);
                employeeActivityDtl.setReasonCd(reason.getReasonCd());
                employeeActivityDtl.setReasonDesc(reason.getReasonDesc());
                employeeActivityDtl.setCallMstId(callMstLocal.getId());
                employeeActivityDtlService.save(employeeActivityDtl);

                if (!inCallType.equals("Scheduled")) {

                    EmployeeCallStatus empCallStatus = new EmployeeCallStatus();
                    List<EmployeeCallStatus> empClStatusList = employeeCallStatusService.findEmployeeCallStatusByEmpId(empMst);

                    if (!empClStatusList.isEmpty()) {
                        for (EmployeeCallStatus empstatus : empClStatusList) {
                            empCallStatus = empstatus;
                        }

                        empCallStatus.setStatus(false);
                        empCallStatus.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                        employeeCallStatusService.saveEmployeeCallStatus(empCallStatus);
                    } else {
                        empCallStatus.setEmpId(empMst);
                        empCallStatus.setCallCount(1);
                        empCallStatus.setStatus(false);
                        empCallStatus.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                        employeeCallStatusService.saveEmployeeCallStatus(empCallStatus);
                    }
                    request.getSession().setAttribute("callMst", null);
                    request.getSession().setAttribute("incomingCallType", null);
                } else {

                    employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(empMst.getId(), "Blocked");

                    if (employeeActivityDtl != null) {
                        employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                        employeeActivityDtlService.save(employeeActivityDtl);
                    }
                    request.getSession().setAttribute("incomingCallType", null);
                    request.getSession().setAttribute("callMst", null);
                }

            }
        } catch (ParseException ex) {

            logger.error("Error:specialistHangUp--->" + ex.getMessage());

        } finally {

            this.hangupcalled = true;
            imageComponent.filemessage = "";
            RequestContext.getCurrentInstance().update("imgIworksForm");
            this.setReasonsRenderer(true);
            this.setAgentStatus("Ready");
            this.setAgentColor("");
            this.setAvailableenabled(false);
            this.setDialenabled(true);
            this.setHoldenabled(true);
            this.setThreewayenabled(true);
            this.setForwardenabled(true);
            this.setNotreadyenabled(true);
            this.setLogoutenabled(false);
            this.setRecordingStatus("");
            this.setIworkImage(false);
            this.setSelfviewenabled(false);
            this.setHangupenabled(true);

            RequestContext.getCurrentInstance().execute(" $('#spinner').hide();");

        }

    }

    public void refreshCustomerAccount() {
        this.customerAccountComponent.newCustomerAccount();
    }

    public void refreshCustomerLoanAccount() {
        this.customerAccountComponent.existingCustomerLoanAccount();
    }

    public void hangUp(HttpServletRequest request) {

        logger.info("HANG UP CALL.....");
        String inCallType = "";
        this.fileUploadDtoList.clear();
        try {
            CallMst callMstLocal = (CallMst) request.getSession().getAttribute("callMst");
            if (callMstLocal != null) {
                callMstLocal = callMasterService.findCallMstById(callMstLocal.getId());
                request.getSession().setAttribute("callMstChat", callMstLocal);
                Set<String> habgUpCustIdSet = (Set<String>) CallScheduler.customerCallDetailsMap.get(callMstLocal.getId().toString());
                if (request.getSession().getAttribute("incomingCallType") == null) {

                } else {
                    inCallType = request.getSession().getAttribute("incomingCallType").toString();
                }

                this.iworkImage = false;
                imageComponent.setForwardChatHistory(false);
                RequestContext.getCurrentInstance().update("forwardchattext");
                ReasonMst reasonMst = null;
                if (null != this.getAgentHangUpComment()) {
                    reasonMst = reasonMstService.findNonDeletedById(this.getAgentHangUpComment());
                }
                if (null == reasonMst) {

                    reasonMst = reasonMstService.findReasonMstByReasonCode("HUP01");

                }

                EmployeeMst empMst = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");

                if (habgUpCustIdSet != null && !this.hangupcalled) {
                    for (String cId : habgUpCustIdSet) {
                        logger.info("Customer Id at HangUp:" + cId);
                        if (null != cId && !"".equals(cId)) {

                            CustomerMst cm = customerMstService.findCustomerMstByCustId(cId);
                            CustomerDeviceDtl deviceDtl = customerDeviceDtlService.findCustomerDeviceDtlByCustId(cm);
                            if (null != deviceDtl) {
                                deviceDtl.setLoginInfo(1);
                                customerDeviceDtlService.saveCustomerDeviceDtl(deviceDtl, null);
                                String osType = deviceDtl.getMobileOsType();
                                if (null != osType && !"WEB".equalsIgnoreCase(osType)) {
                                    try {
                                        SocketMessage.send(callScheduler.getAdminSocket(), cId, "hangUp#" + callMstLocal.getId());
                                    } catch (Exception ex) {
                                        Logger.getLogger(VidyoAgent.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            }
                        }
                    }
                }
                CallScheduler.customerCallDetailsMap.remove(callMstLocal.getId().toString());
                CallDtl clDtl = callDtlService.findCallDtlByCallMasterIdAndAgentNotForwarded(callMstLocal.getId(), empMst.getId());

                clDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                clDtl.setDeleteFlg(false);
                clDtl.setAgentComments(reasonMst.getReasonDesc());
                clDtl.setActiveFlg(true);
                callDtlService.saveCallDtl(clDtl);

                CallMst clMst = callMasterService.findNonDeletedCallMstById(clDtl.getCallMstId().getId());
                List<CallDtl> findOtherNonEndedCallDetailFromSameCallMst = callDtlService.findCallDtlByCallMasterIdAndAgent(clMst.getId(), empMst.getId());
                clMst.setCallStatus("Ended");
                clMst.setEndTime(clDtl.getEndTime());

                callMasterService.saveCallMst(clMst);
                customerAccountComponent.setCustomerAccountDto(new CustomerAccountDto());

                EmployeeActivityDtl employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(empMst.getId(), "Call Started");

                if (employeeActivityDtl != null) {
                    employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                    employeeActivityDtlService.save(employeeActivityDtl);

                }

                employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(empMst.getId(), "Incoming Call");

                if (employeeActivityDtl != null) {
                    employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                    employeeActivityDtlService.save(employeeActivityDtl);

                }

                employeeActivityDtl = new EmployeeActivityDtl();
                employeeActivityDtl.setActivity("Hang Up");
                employeeActivityDtl.setEmpId(empMst);
                employeeActivityDtl.setStartTime(CustomConvert.javaDateToTimeStamp(new Date()));
                ReasonMst reason = reasonMstService.findReasonMstByReasonType("Hang Up");
                employeeActivityDtl.setReasonId(reason);
                employeeActivityDtl.setReasonCd(reason.getReasonCd());
                employeeActivityDtl.setReasonDesc(reason.getReasonDesc());
                employeeActivityDtl.setCallMstId(callMstLocal.getId());
                employeeActivityDtlService.save(employeeActivityDtl);

                if (!inCallType.equals("Scheduled")) {

                    EmployeeCallStatus empCallStatus = new EmployeeCallStatus();
                    List<EmployeeCallStatus> empClStatusList = employeeCallStatusService.findEmployeeCallStatusByEmpId(empMst);

                    if (!empClStatusList.isEmpty()) {
                        for (EmployeeCallStatus empstatus : empClStatusList) {
                            empCallStatus = empstatus;
                        }

                        empCallStatus.setStatus(false);
                        empCallStatus.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                        employeeCallStatusService.saveEmployeeCallStatus(empCallStatus);
                    } else {
                        empCallStatus.setEmpId(empMst);
                        empCallStatus.setCallCount(1);
                        empCallStatus.setStatus(false);
                        empCallStatus.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                        employeeCallStatusService.saveEmployeeCallStatus(empCallStatus);
                    }

                } else {

                    employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(empMst.getId(), "Blocked");

                    if (employeeActivityDtl != null) {
                        employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                        employeeActivityDtlService.save(employeeActivityDtl);
                    }

                }
                request.getSession().setAttribute("incomingCallType", null);
                if (!findOtherNonEndedCallDetailFromSameCallMst.isEmpty()) {
                    for (CallDtl calldtl : findOtherNonEndedCallDetailFromSameCallMst) {
                        if (calldtl.getEndTime() == null) {
                            calldtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                            calldtl.setDeleteFlg(false);
                            calldtl.setAgentComments(reasonMst.getReasonDesc());
                            calldtl.setActiveFlg(true);
                            calldtl = callDtlService.saveCallDtl(calldtl);
                        }
                    }
                    findOtherNonEndedCallDetailFromSameCallMst.clear();
                }
                try {
                    callRecordsComponent.getStopRecording(callMstLocal.getId());

                } catch (Exception ex) {
                    logger.error(ex.getMessage());
                }

                RequestContext.getCurrentInstance().execute("chatSave();");

                request.getSession().setAttribute("callMst", null);
                this.chatText = "";

            }
        } catch (ParseException ex) {

            logger.info("Got Some Error at HangUp....." + ex.getMessage());

        } finally {
            customerAccountComponent.setCustomerAccountDto(null);
            customerAccountComponent.setCustomerLoanDto(null);
            customerAccountComponent.setSnapImage(request.getContextPath() + "/resources/images/no_img.jpg");
            RequestContext.getCurrentInstance().execute(" $('#spinner').hide();");
            RequestContext.getCurrentInstance().execute("closeChildWindow();");
        }

    }

    public void callAbondaned(HttpServletRequest request) {
        logger.info("Inside callAbondaned.....");
        imageComponent.setCategory("");
        imageComponent.setCustAcctNo("");
        imageComponent.setCustNm("");
        imageComponent.setFileLocation("");
        imageComponent.setService("");
        imageComponent.setRealPath("");
        imageComponent.setLanguage("");

        this.setHangupenabled(true);
        this.setAvailableenabled(false);
        this.setDialenabled(false);
        this.setHoldenabled(true);
        this.setThreewayenabled(true);
        this.setForwardenabled(true);
        this.setNotreadyenabled(false);
        this.setAgentStatus("Ready");
        this.setLogoutenabled(false);
        this.setAgentColor("");
        this.setRecordingStatus("");
        this.setIworkImage(false);
        this.setSelfviewenabled(false);

        try {

            EmployeeMst empMst = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
            CallMst callMstLocal = (CallMst) request.getSession().getAttribute("callMst");

            if (callMstLocal != null) {
                callMstLocal = callMasterService.findCallMstById(callMstLocal.getId());
                callMstLocal.setCallStatus("ABANDONED");
                callMstLocal.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                callMstLocal.setAgentPickupTime(CustomConvert.javaDateToTimeStamp(new Date()));
                callMasterService.saveCallMst(callMstLocal);
            }
            sendHangUpMessage(request);
            EmployeeCallStatus empCallStatus = new EmployeeCallStatus();
            List<EmployeeCallStatus> empClStatusList = employeeCallStatusService.findEmployeeCallStatusByEmpId(empMst);

            if (!empClStatusList.isEmpty()) {
                for (EmployeeCallStatus empstatus : empClStatusList) {
                    empCallStatus = empstatus;
                }

                empCallStatus.setStatus(false);
                employeeCallStatusService.saveEmployeeCallStatus(empCallStatus);
            } else {
                empCallStatus.setEmpId(empMst);

                empCallStatus.setStatus(false);
                employeeCallStatusService.saveEmployeeCallStatus(empCallStatus);
            }

            EmployeeActivityDtl employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(empMst.getId(), "NEXT CALL");
            if (employeeActivityDtl != null) {
                employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                employeeActivityDtlService.save(employeeActivityDtl);

            }

            employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(empMst.getId(), "Call Started");
            if (employeeActivityDtl != null) {
                employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                employeeActivityDtlService.save(employeeActivityDtl);

            }

            employeeActivityDtl = new EmployeeActivityDtl();
            employeeActivityDtl.setActivity("Hang Up");
            employeeActivityDtl.setEmpId(empMst);
            employeeActivityDtl.setStartTime(CustomConvert.javaDateToTimeStamp(new Date()));
            ReasonMst reason = reasonMstService.findReasonMstByReasonCode("ABN01");
            employeeActivityDtl.setReasonId(reason);
            employeeActivityDtl.setReasonCd(reason.getReasonCd());
            employeeActivityDtl.setReasonDesc(reason.getReasonDesc());
            if (callMstLocal != null) {
                employeeActivityDtl.setCallMstId(callMstLocal.getId());
            }
            employeeActivityDtlService.save(employeeActivityDtl);

            List<CallDtl> calldtlList = new ArrayList<>();
            if (callMstLocal != null) {
                calldtlList = callDtlService.findCallDtlByCallMasterId(callMstLocal.getId());
            }

            if (!(calldtlList.isEmpty())) {

                CallDtl callDetail = calldtlList.get(0);
                callDetail.setAgentComments("Not Recieved By Agent");
                callDetail.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                callDtlService.saveCallDtl(callDetail);
            }
            request.getSession().setAttribute("callMst", null);

        } catch (ParseException e) {
            logger.info("callAbondaned:Error:" + e.getMessage());
        }

    }

    public void callAbondaned1(HttpServletRequest request) {
        logger.info("Inside callAbondaned1.....");
        imageComponent.setCategory("");
        imageComponent.setCustAcctNo("");
        imageComponent.setCustNm("");
        imageComponent.setFileLocation("");
        imageComponent.setService("");
        imageComponent.setRealPath("");
        imageComponent.setLanguage("");

        this.setHangupenabled(true);
        this.setAvailableenabled(false);
        this.setDialenabled(true);
        this.setHoldenabled(true);
        this.setThreewayenabled(true);
        this.setForwardenabled(true);
        this.setNotreadyenabled(true);
        this.setAgentStatus("Ready");
        this.setLogoutenabled(false);
        this.setAgentColor("");
        this.setRecordingStatus("");
        this.setIworkImage(false);
        this.setSelfviewenabled(false);
        String usertext = "";

        try {

            EmployeeMst empMst = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
            CallMst callMstLocal = (CallMst) request.getSession().getAttribute("callMst");

            if (callMstLocal != null) {
                callMstLocal = callMasterService.findCallMstById(callMstLocal.getId());
                callMstLocal.setCallStatus("Missed");
                callMstLocal.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
//                callMstLocal.setAgentPickupTime(CustomConvert.javaDateToTimeStamp(new Date()));
                callMasterService.saveCallMst(callMstLocal);
                CustomerMst cm = customerMstService.findCustomerMstByCustId(callMstLocal.getCustId());
                if (null != cm) {
                    usertext = cm.getCustId();
                }
            }
            /**
             * two socket is sending to same recipient 1. Hangup and 2. Missed
             */
            //      sendHangUpMessage(request);
            EmployeeCallStatus empCallStatus = new EmployeeCallStatus();
            List<EmployeeCallStatus> empClStatusList = employeeCallStatusService.findEmployeeCallStatusByEmpId(empMst);

            if (!empClStatusList.isEmpty()) {
                for (EmployeeCallStatus empstatus : empClStatusList) {
                    empCallStatus = empstatus;
                }

                empCallStatus.setStatus(false);
                employeeCallStatusService.saveEmployeeCallStatus(empCallStatus);
            } else {
                empCallStatus.setEmpId(empMst);

                empCallStatus.setStatus(false);
                employeeCallStatusService.saveEmployeeCallStatus(empCallStatus);
            }

            EmployeeActivityDtl employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(empMst.getId(), "NEXT CALL");
            if (employeeActivityDtl != null) {
                employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                employeeActivityDtlService.save(employeeActivityDtl);

            }

            employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(empMst.getId(), "Call Started");
            if (employeeActivityDtl != null) {
                employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                employeeActivityDtlService.save(employeeActivityDtl);

            }

            employeeActivityDtl = new EmployeeActivityDtl();
            employeeActivityDtl.setActivity("Hang Up");
            employeeActivityDtl.setEmpId(empMst);
            employeeActivityDtl.setStartTime(CustomConvert.javaDateToTimeStamp(new Date()));
            ReasonMst reason = reasonMstService.findReasonMstByReasonCode("ABN01");
            employeeActivityDtl.setReasonId(reason);
            employeeActivityDtl.setReasonCd(reason.getReasonCd());
            employeeActivityDtl.setReasonDesc(reason.getReasonDesc());
            if (callMstLocal != null) {
                employeeActivityDtl.setCallMstId(callMstLocal.getId());
            }
            employeeActivityDtlService.save(employeeActivityDtl);

            List<CallDtl> calldtlList = new ArrayList<>();
            if (callMstLocal != null) {
                calldtlList = callDtlService.findCallDtlByCallMasterId(callMstLocal.getId());
            }

            if (!(calldtlList.isEmpty())) {

                CallDtl callDetail = calldtlList.get(0);
                callDetail.setAgentComments("Not Recieved By Agent");
                callDetail.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                callDtlService.saveCallDtl(callDetail);
            }

            if (callMstLocal != null) {
                try {
                    if (callMstLocal.getCallType().equalsIgnoreCase("Schedule")) {
                        SocketMessage.send(callScheduler.getAdminSocket(), usertext,
                                "schmissed#" + callMstLocal.getId());
                    } else {
                        SocketMessage.send(callScheduler.getAdminSocket(), usertext, "missed#" + callMstLocal.getId());
                    }
                } catch (Exception e) {
                    logger.error("callAbondaned1:SocketError:" + e.getMessage());
                }
            }

            request.getSession().setAttribute("callMst", null);

        } catch (ParseException e) {
            logger.error("callAbondaned1:Error:" + e.getMessage());
        }

    }

    public void nextScheduledCall(HttpServletRequest request) {
        RequestContext.getCurrentInstance().execute("$('#spinner').hide();");
        this.setSelfviewenabled(false);

    }

    public void nextCallDisable(HttpServletRequest request) {
        EmployeeMst empMst = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
        if (null != empMst) {
            try {
                Long number1 = (long) Math.floor(Math.random() * 9000000L) + 1000000L;
                String room1 = empMst.getLoginId() + number1;
                VidyoAccessUser vidyoAccessUser = new VidyoAccessUser(Constants.vidyoportalUserServiceWSDL);
                String ret = vidyoAccessUser.createRoom(Constants.adminUserId, Constants.adminPwd, room1, Constants.vidyoportalUserServiceWSDL);
                String roomlink = ret.split(",")[0];
                String entity = ret.split(",")[1];
//                String roomNameL = ret.split(",")[2];
                request.getSession().setAttribute("entityId", entity);
                logger.info("New Agent roomlink=====>" + roomlink + " created for agent:" + empMst.getFirstName() + "(" + empMst.getId() + ")");

                this.setRoomUrl(roomlink.substring(roomlink.lastIndexOf("/") + 1));

                List<TenancyEmployeeMap> tenancyEmployeeMaplist = tenancyEmployeeMapService.findVidyoTenantUrlByEmpId(empMst.getId());
                List<Long> unsortList = new ArrayList<>();
                for (TenancyEmployeeMap tenancyEmployeeMaplist1 : tenancyEmployeeMaplist) {
                    unsortList.add(tenancyEmployeeMaplist1.getId());
                }
                Collections.sort(unsortList);

                this.roomName = room1;

                List<TenancyEmployeeMap> currentAgent = tenancyEmployeeMapService.findVidyoTenantUrlByEmpId(empMst.getId());

                for (TenancyEmployeeMap currentAgent1 : currentAgent) {
                    currentAgent1.setRoomName(this.getRoomName());
                    currentAgent1.setRoomLink(roomlink);
                    currentAgent1.setEntityId(entity);
                    tenancyEmployeeMapService.saveTenancyEmployeeMap(currentAgent1);
                }

                this.availableenabled = true;
                RequestContext.getCurrentInstance().execute("PF('nextcallrecreate').hide();");
            } catch (Exception ex) {
                logger.error(ex.getMessage());
                RequestContext.getCurrentInstance().execute("PF('nextcallrecreate').show();");

                Logger.getLogger(VidyoAgent.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void nextCalEnable(HttpServletRequest request) {
        this.availableenabled = false;
    }

    public void nextCall(HttpServletRequest request) {
        EmployeeMst empMst = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
        request.getSession().setAttribute("callMst", null);
        request.getSession().setAttribute("forwardedsavedchat", null);
        request.getSession().setAttribute("savedchat", null);
        this.chatText = "";
        this.chatText1 = "";
        this.chatText2 = "";
        imageComponent.setForwardChatHistory(false);
        RequestContext.getCurrentInstance().update("forwardchattext");
        fileUploadDtoList.clear();
        this.hangupcalled = false;

        EmployeeCallStatus empCallStatus = new EmployeeCallStatus();
        List<EmployeeCallStatus> empClStatusList = employeeCallStatusService.findEmployeeCallStatusByEmpId(empMst);
        try {

            if (agentStatus.equals("Ready")) {
                agentStatus = "Available";
                this.agentColor = "background:#51A351;opacity: 1 !important";
                hangupenabled = true;
                availableenabled = true;
                dialenabled = false;
                holdenabled = true;
                threewayenabled = true;
                forwardenabled = true;
                notreadyenabled = false;
                logoutenabled = false;
                this.selfviewenabled = true;
                this.renderedMakeScheduleButton = false;

                if (!empClStatusList.isEmpty()) {
                    for (EmployeeCallStatus empstatus : empClStatusList) {
                        empCallStatus = empstatus;
                    }

                    empCallStatus.setStatus(true);

                    employeeCallStatusService.saveEmployeeCallStatus(empCallStatus);
                } else {
                    empCallStatus.setEmpId(empMst);
                    empCallStatus.setStatus(true);
                    employeeCallStatusService.saveEmployeeCallStatus(empCallStatus);
                }

                EmployeeActivityDtl employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(empMst.getId(), "not ready");
                if (employeeActivityDtl != null) {
                    employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                    employeeActivityDtlService.save(employeeActivityDtl);

                }

                employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(empMst.getId(), "Hang Up");
                if (employeeActivityDtl != null) {
                    employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                    employeeActivityDtlService.save(employeeActivityDtl);

                }

                employeeActivityDtl = new EmployeeActivityDtl();
                employeeActivityDtl.setActivity("NEXT CALL");
                employeeActivityDtl.setEmpId(empMst);

                ReasonMst reasonmst = reasonMstService.findReasonMstByReasonType("Not Ready");
                employeeActivityDtl.setReasonId(reasonmst);
                employeeActivityDtl.setReasonCd(reasonmst.getReasonCd());
                employeeActivityDtl.setReasonDesc(reasonmst.getReasonDesc());
                employeeActivityDtl.setStartTime(CustomConvert.javaDateToTimeStamp(new Date()));
                employeeActivityDtlService.save(employeeActivityDtl);

                employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(empMst.getId(), "Self View");
                if (employeeActivityDtl != null) {
                    employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                    employeeActivityDtlService.save(employeeActivityDtl);
                }
            }

        } catch (Exception e) {

        } finally {
            RequestContext.getCurrentInstance().execute(" $('#spinner').hide();");
            RequestContext.getCurrentInstance().update("vidyowebrtcform");
        }

    }

    public void revokenotReady() {
        agentStatus = "Available";
        this.agentColor = "background:#51A351;opacity: 1 !important";

        hangupenabled = true;
        availableenabled = true;
        dialenabled = false;
        holdenabled = true;
        threewayenabled = true;
        forwardenabled = true;
        notreadyenabled = false;
        logoutenabled = false;
        this.selfviewenabled = true;
        this.renderedMakeScheduleButton = false;
    }

    public void notReady(HttpServletRequest request) {
        logger.info("Not Ready......");
        EmployeeMst empMst = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
        EmployeeCallStatus empCallStatus = new EmployeeCallStatus();
        List<EmployeeCallStatus> empClStatusList = employeeCallStatusService.findEmployeeCallStatusByEmpId(empMst);
        this.setAvailableenabled(false);
        try {
            if (empClStatusList != null) {
                for (EmployeeCallStatus empstatus : empClStatusList) {
                    empCallStatus = empstatus;
                }
                empCallStatus.setStatus(false);
                employeeCallStatusService.saveEmployeeCallStatus(empCallStatus);
            } else {
                empCallStatus.setEmpId(empMst);
                empCallStatus.setStatus(false);
                employeeCallStatusService.saveEmployeeCallStatus(empCallStatus);
            }

            EmployeeActivityDtl employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(empMst.getId(), "NEXT CALL");
            if (employeeActivityDtl != null) {
                employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                employeeActivityDtlService.save(employeeActivityDtl);

            }

            ReasonMst reasonMst = reasonMstService.findNonDeletedById(this.getAgentNotreadyComment());
            if (reasonMst != null) {
                employeeActivityDtl = new EmployeeActivityDtl();
                employeeActivityDtl.setActivity("not ready");
                employeeActivityDtl.setEmpId(empMst);
                employeeActivityDtl.setStartTime(CustomConvert.javaDateToTimeStamp(new Date()));
                employeeActivityDtl.setReasonId(reasonMst);
                employeeActivityDtl.setReasonCd(reasonMst.getReasonCd());
                employeeActivityDtl.setReasonDesc(reasonMst.getReasonDesc());
                employeeActivityDtlService.save(employeeActivityDtl);
            }

        } catch (ParseException e) {
            logger.error("notReady:Error:" + e.getMessage());
        }

    }

    public void showhangUpReasons(HttpServletRequest request) {
        logger.info("Inside showhangUpReasons........");
        List<ReasonMst> reasonList = reasonMstService.findAllActivenNonDeletedReasonMsts("Hang Up");
        hangupReasonMstList = reasonList;
        this.agentHangUpComment = reasonList.get(0).getId();
        reasonsRenderer = true;
        agentStatus = "Ready";
        this.agentColor = "";
        this.hangupenabled = true;
        this.setAvailableenabled(false);
        this.dialenabled = true;
        this.holdenabled = true;
        this.threewayenabled = true;
        this.forwardenabled = true;
        this.notreadyenabled = true;
        this.logoutenabled = false;
        this.renderedMakeScheduleButton = false;
        this.recordingStatus = "";
        this.setIworkImage(false);
        callRecordsComponent.setRecordingStatus("");
        this.selfviewenabled = false;
        hangupcalled = true;
        request.getSession();
        imageComponent.setIdCardName("");
        RequestContext.getCurrentInstance().update("scanresult");
    }

    public void revokehangUpReasons(HttpServletRequest request) {

        agentStatus = "Busy";
        this.agentColor = "background:red;opacity : 1 !important;";
        hangupenabled = false;
        availableenabled = true;
        dialenabled = true;
        holdenabled = false;
        threewayenabled = false;
        forwardenabled = false;
        notreadyenabled = true;
        this.logoutenabled = true;
        this.renderedMakeScheduleButton = true;
        this.iworkImage = true;
        this.selfviewenabled = true;

        logger.info("revoke hang up reasons.......");
        request.getSession();

    }

    public void showNotReadyReasons(HttpServletRequest request) {
        reasonMstList = reasonMstService.findAllActivenNonDeletedReasonMsts("Not Ready");
        this.setNotreadyreasonMstList(reasonMstList);
        agentNotreadyComment = reasonMstList.get(0).getId();
        agentStatus = "Ready";
        this.agentColor = "";
        this.hangupenabled = true;
        this.dialenabled = true;
        this.holdenabled = true;
        this.threewayenabled = true;
        this.forwardenabled = true;
        this.notreadyenabled = true;
        this.selfviewenabled = false;
        request.getSession();

    }

    public void showNotMockReadyReasons(HttpServletRequest request) {
        EmployeeMst localEmpMst = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
        agentStatus = "Ready";
        this.agentColor = "";
        this.hangupenabled = true;
        this.dialenabled = true;
        this.holdenabled = true;
        this.threewayenabled = true;
        this.forwardenabled = true;
        this.notreadyenabled = true;
        this.selfviewenabled = false;
        this.availableenabled = false;
        this.logoutenabled = false;
        this.renderedMakeScheduleButton = false;
        logger.info("showNotMockReadyReasons............");
        request.getSession();
        CallMst callMst = (CallMst) request.getSession().getAttribute("callMst");

        if (null != callMst) {
            callMst = callMasterService.findCallMstById(callMst.getId());
            try {
                callMst = callMasterService.findCallMstById(callMst.getId());
                callMst.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));

                callMasterService.saveCallMst(callMst);
                EmployeeActivityDtl empactdtl = employeeActivityDtlService.findLastNonEndedActivityByType(localEmpMst.getId(), "Call Started");
                if (empactdtl != null) {
                    empactdtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                    employeeActivityDtlService.save(empactdtl);
                }
            } catch (ParseException ex) {
                Logger.getLogger(VidyoAgent.class.getName()).log(Level.SEVERE, null, ex);
            }

            request.getSession().setAttribute("callMst", null);

            ForwardedCall forwardedCall = forwardedCallService.findActiveForwardedCallByEmployeeMst(localEmpMst);
            if (forwardedCall != null) {
                forwardedCall.setActiveFlg(false);
                forwardedCall.setForwardStatus("missed");
                forwardedCallService.saveForwardedCalls(forwardedCall, localEmpMst);
            }
        }

    }

    public void openSendLink() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        CallMst callMstLocal = (CallMst) request.getSession().getAttribute("callMst");
        if (!callMstLocal.getCallOption().equalsIgnoreCase("chat")) {
            try {
                logger.info("openSendLink....");
                guestEmail = "";

                RequestContext.getCurrentInstance().execute("closeAllDialog();PF('showSendLinkWidId').show();");
                RequestContext.getCurrentInstance().update("showSendLinkFormId");

            } catch (Exception e) {
                logger.error(e);
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "This facility not allowed on chat mode", "This facility not allowed on chat mode"));
        }
    }

    public void checkErrors() {

    }

    public void sendLink(String email) {
        if (null != email && !"".equals(email)) {
            RequestContext.getCurrentInstance().execute("document.getElementById('slink').click();");
        }
    }

    public void sendMultiWayCallEmailToGuest() {
        try {
            logger.info("sendMultiWayCallEmailToGuest....");
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            CallMst callMstLocal = (CallMst) request.getSession().getAttribute("callMst");
            String messageBody;
            CustomerMst customerMst_local = null;
            if (null != callMstLocal) {
                customerMst_local = customerMstService.findCustomerMstByCustId(callMstLocal.getCustId());
            }
            try {
                logger.info("customerMst_local:" + customerMst_local + "callMstLocal:" + callMstLocal + "guestEmail:" + guestEmail);
                if (null != guestEmail && !"".equals(guestEmail) && null != callMstLocal && null != customerMst_local) {
                    String toBeEncode = "callId=" + callMstLocal.getId() + "&resourceId=" + roomUrl;
                    logger.info("toBeEncode:" + toBeEncode);
                    String base64encodedString = Base64.getEncoder().encodeToString(toBeEncode.getBytes("utf-8"));
                    String websiteURL = Constants.WEB_PATH_URL;
                    messageBody = "<html><body>Dear &nbsp; Customer";
                    messageBody += ",&nbsp;<br>You are invited by " + customerMst_local.getFirstName() + " " + customerMst_local.getLastName() + " to join the meeting with his/her Relationship Manager.";
                    messageBody += "&nbsp;<br>Kindly click the following link, to join the call.";
                    messageBody += "<br>";
                    messageBody += "<a href=";
                    messageBody += websiteURL + "/guestCallCheck?param=" + base64encodedString;

                    messageBody += ">Meeting Link</a>";
                    messageBody += "<br>";
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

                Thread.sleep(3000);
            } catch (Exception ex) {
                logger.error("Sending Email Error:" + ex.getMessage());
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Email Send Was UnSuccessful", "Send UnSuccessful!!"));
            }
        } catch (Exception e) {
            logger.error(e);
        }

    }

    public void customerCallingNotification() {
        logger.info("CUSTOMER GET NOTIFICATION.....");
        RequestContext.getCurrentInstance().execute("disconnect();");
        RequestContext.getCurrentInstance().execute("deskNotification('Customer is waiting for your response.Please join the conference');");
        RequestContext.getCurrentInstance().execute("closeAllDialog();PF('notDialog').show();");
        RequestContext.getCurrentInstance().execute("playAudio();");

    }

    public void checkCallOption(HttpServletRequest request) {
        try {
            CallMst callMstL = (CallMst) request.getSession().getAttribute("callMst");
            logger.info("callMstL:" + callMstL);
            EmployeeMst employeeMaster = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
            if (null != callMstL) {
                callMstL = callMasterService.findCallMstById(callMstL.getId());
                logger.info("ccallType()" + callMstL.getCallType());
                if (callMstL.getCallType().equalsIgnoreCase("Schedule Call") || callMstL.getCallType().equalsIgnoreCase("ScheduleCall")) {
                    RequestContext.getCurrentInstance().execute("stopIntervalForScheduleNoti()");
                    if (null == callMstL.getEndTime()) {
                        CustomerMst cmm = customerMstService.findCustomerMstByCustId(callMstL.getCustId());
                        logger.info("schedule_call#received#'" + cmm.getCustId() + "')");
                        SocketMessage.send(callScheduler.getAdminSocket(), cmm.getCustId(), "schedule_call#received");

                    }
                }
                logger.info("Schedule call Pick By Agent-CallMst:" + callMstL);
                logger.info("callMstL-Option:" + callMstL.getCallOption());
                if (callMstL.getCallOption().equalsIgnoreCase("chat")) {
                    List<CallDtl> cList = callDtlService.findCallDtlByCallMasterId(callMstL.getId());
                    Set<String> hash_Set = new HashSet<>();
                    if (callMstL.getEndTime() == null) {
                        hash_Set.add(callMstL.getCustId());
                    }
                    for (int i = 0; i < cList.size(); i++) {
                        EmployeeMst em = employeeMstService.findEmployeeMstById(cList.get(i).getHandeledById().getId());
                        if (!em.getLoginId().equals(employeeMaster.getLoginId())) {
                            hash_Set.add(em.getLoginId());
                        }
                    }

                    for (String temp : hash_Set) {
                        RequestContext.getCurrentInstance().execute("insertParticipants('" + temp + "');");
                    }
                    RequestContext.getCurrentInstance().execute("callchat();");

                } else if (callMstL.getCallOption().equalsIgnoreCase("audio")) {
                    RequestContext.getCurrentInstance().execute("callaudio();");
                } else {
                    RequestContext.getCurrentInstance().execute("call();");
                }
            }
        } catch (Exception e) {
            logger.error("error:" + e);

        }
    }

    public void refreshOperations(HttpServletRequest request) throws ParseException {
        logger.info("Inside refreshOperations....");

        CallMst callMstL = (CallMst) request.getSession().getAttribute("callMst");
        fileDownloadDtoList.clear();
        String userNameText = (String) request.getSession().getAttribute("userNameText");
        CustomerMst cm;
        if (null != userNameText && !"".equals(userNameText)) {
            if (null != callMstL) {
                try {
                    callMstL = callMasterService.findCallMstById(callMstL.getId());
                    cm = customerMstService.findCustomerMstByCustId(callMstL.getCustId());

                    imageComponent.setCustNm(cm.getFirstName() + " " + cm.getLastName());

                    imageComponent.setCustAcctNo(cm.getAccountNo());
                    imageComponent.setCustSegment(cm.getCustSeg());
                    imageComponent.setCustEmail(cm.getEmail());

                    imageComponent.setCustPhone(cm.getCellPhone() + "");

                    imageComponent.setLanguage(cm.getCustLang1());
                    imageComponent.setCustId(cm.getCustId());
                    imageComponent.setCustNationality(cm.getNatinality());

                    imageComponent.setCustomerLocation(callMstL.getCallLocation());
                    imageComponent.setLatitude(callMstL.getCallLatitude());
                    imageComponent.setLongitude(callMstL.getCallLongitude());
                    logger.info("Location:" + callMstL.getCallLocation());
                    CategoryMst categoryMstL = null;
                    ServiceMst serviceMst = null;
                    if (callMstL.getServiceId() > 0) {
                        serviceMst = serviceMstService.findNonDeletedServiceMstById(callMstL.getServiceId());
                    }
                    if (callMstL.getCategoryId() > 0) {
                        categoryMstL = categoryMstService.findCategoryMstById(callMstL.getCategoryId());
                    }
                    if (null != categoryMstL) {
                        imageComponent.setCategory(categoryMstL.getCatgName());
                    }
                    if (null != serviceMst) {
                        imageComponent.setService(serviceMst.getServiceName());
                    }
                } catch (Exception ex) {
                    Logger.getLogger(VidyoAgent.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (callMstL.getCallType().equalsIgnoreCase("Incoming Call")) {
                    callMstL = callMasterService.findCallMstById(callMstL.getId());
                    if (null != callMstL && null == callMstL.getEndTime()) {
                        logger.info("callMstL:" + callMstL + "sendAcknowledgement('" + callMstL.getCustId() + "')");
                        RequestContext.getCurrentInstance().execute("sendAcknowledgement('" + callMstL.getCustId() + "')");

                    }
                } else if (callMstL.getCallType().equalsIgnoreCase("Schedule")) {
                    callMstL = callMasterService.findCallMstById(callMstL.getId());
                    if (null != callMstL && null == callMstL.getEndTime()) {

                        try {
                            CustomerMst cmm = customerMstService.findCustomerMstByCustId(callMstL.getCustId());
                            ScheduleCall sc = scheduleCallService.findScheduleCallByCustomerIdCallMstId(callMstL.getId(), cmm.getId()).get(0);

                            if (cmm.getAccountNo() != null) {
                                if (null != sc) {
                                    CustomerLoanDtl cld = customerLoanDtlService.findIDByPhoneNo(String.valueOf(cmm.getCellPhone()));
                                    if (null != cld) {
                                        this.setLoanCustomer(true);
                                        this.setLoanCustomerId(cld.getId());
                                    }
                                }
                                this.setExistingCustomer(true);
                            }
                            String custName = cmm.getFirstName() + "~" + cmm.getCustId();
//                         
                            RequestContext.getCurrentInstance().execute("sendScheduleAcknowledgementNew('" + callMstL.getCustId() + "','" + this.roomUrl + "','" + this.roomName + "','" + custName + "','" + callMstL.getId() + "')");
                        } catch (Exception ex) {
                            logger.error(ex);
                        }

                    }
                }

            }
        }
        this.forwardedcall = false;
        hangupenabled = false;
        dialenabled = true;
        holdenabled = false;
        threewayenabled = false;
        forwardenabled = false;
        notreadyenabled = true;
        this.logoutenabled = true;
        this.renderedMakeScheduleButton = true;
        agentStatus = "Busy";
        availableenabled = true;
        this.agentColor = "background:red;opacity : 1 !important;";
        this.recordingStatus = "";
        this.iworkImage = true;
        this.selfviewenabled = true;

        RequestContext.getCurrentInstance().execute("PF('calljoin').hide();");

        joinAgent(request);

    }

    public void joinAgent(HttpServletRequest request) {
        logger.info("Inside joinAgent......");
        EmployeeMst employeeMaster = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
        CallMst callMstL = (CallMst) request.getSession().getAttribute("callMst");
        callMstL = callMasterService.findCallMstById(callMstL.getId());
        try {
            callMstL.setAgentPickupTime(CustomConvert.javaDateToTimeStamp(new Date()));
        } catch (ParseException ex) {
            Logger.getLogger(VidyoAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
        callMasterService.saveCallMst(callMstL);

        String calltyp = (String) request.getSession().getAttribute("incomingCallType");

        if (!calltyp.equals("Scheduled")) {

            try {

                hangupenabled = false;
                dialenabled = true;
                holdenabled = false;
                threewayenabled = false;
                forwardenabled = false;
                notreadyenabled = true;
                agentStatus = "Busy";
                availableenabled = true;
                this.agentColor = "background:red";
                this.selfviewenabled = true;

                EmployeeActivityDtl employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(employeeMaster.getId(), "NEXT CALL");
                if (employeeActivityDtl != null) {
                    employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                    employeeActivityDtlService.save(employeeActivityDtl);
                }

            } catch (ParseException e) {
                logger.error("Error:" + e.getMessage());
            }
        }

        logger.info("End joinAgent......");

    }

    public void getReadyEnabled() {
        this.setAvailableenabled(false);
        this.setLogoutenabled(false);
        this.setLoginMessage("");
        this.setGotoDashoardenabled(false);
        this.setSelfviewenabled(false);
    }

    public void AfterForwardHangUp(HttpServletRequest request) {
        logger.info("AFTER FORWARD HANG UP CALL....");
        String inCallType = "";
        if (request.getSession().getAttribute("incomingCallType") == null) {

        } else {
            inCallType = request.getSession().getAttribute("incomingCallType").toString();
        }

        imageComponent.setCategory("");
        imageComponent.setCustAcctNo("");
        imageComponent.setCustNm("");
        imageComponent.setFileLocation("");
        imageComponent.setService("");
        imageComponent.setRealPath("");
        imageComponent.setLanguage("");
        imageComponent.setIdCardName("");
        this.fileDownloadDtoList.clear();
        this.iworkImage = false;
        imageComponent.setForwardChatHistory(false);
        RequestContext.getCurrentInstance().update("forwardchattext");

        try {

            EmployeeMst empMst = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
            CallMst callMstLocal = (CallMst) request.getSession().getAttribute("callMst");
            callMstLocal = callMasterService.findCallMstById(callMstLocal.getId());
            request.getSession().setAttribute("callMstChat", callMstLocal);

            CallDtl clDtl = callDtlService.findCallDtlByCallMasterIdAndAgentNotForwarded(callMstLocal.getId(), empMst.getId());
            clDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
            clDtl.setDeleteFlg(false);
            clDtl.setAgentComments("Call forwarded Successfully");
            clDtl.setActiveFlg(true);
            callDtlService.saveCallDtl(clDtl);
            CallMst clMst = callMasterService.findNonDeletedCallMstById(clDtl.getCallMstId().getId());
            List<CallDtl> findOtherNonEndedCallDetailFromSameCallMst = callDtlService.findCallDtlByCallMasterIdAndAgent(clMst.getId(), empMst.getId());

            EmployeeActivityDtl employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(empMst.getId(), "Call Started");

            try {
                if (employeeActivityDtl != null) {
                    employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                    employeeActivityDtlService.save(employeeActivityDtl);

                }

                employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(empMst.getId(), "Incoming Call");

                if (employeeActivityDtl != null) {

                    employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                    employeeActivityDtlService.save(employeeActivityDtl);

                }

                employeeActivityDtl = new EmployeeActivityDtl();
                employeeActivityDtl.setActivity("Hang Up");
                employeeActivityDtl.setEmpId(empMst);
                employeeActivityDtl.setStartTime(CustomConvert.javaDateToTimeStamp(new Date()));

                ReasonMst reason = reasonMstService.findReasonMstByReasonCode("HUP01");
                employeeActivityDtl.setReasonId(reason);
                employeeActivityDtl.setReasonCd(reason.getReasonCd());
                employeeActivityDtl.setReasonDesc(reason.getReasonDesc());
                employeeActivityDtl.setCallMstId(callMstLocal.getId());
                employeeActivityDtlService.save(employeeActivityDtl);
                if (!inCallType.equals("Scheduled")) {

                    EmployeeCallStatus empCallStatus = new EmployeeCallStatus();
                    List<EmployeeCallStatus> empClStatusList = employeeCallStatusService.findEmployeeCallStatusByEmpId(empMst);

                    if (!empClStatusList.isEmpty()) {

                        for (EmployeeCallStatus empstatus : empClStatusList) {
                            empCallStatus = empstatus;
                        }

                        empCallStatus.setStatus(false);
                        empCallStatus.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                        employeeCallStatusService.saveEmployeeCallStatus(empCallStatus);
                    } else {
                        empCallStatus.setEmpId(empMst);
                        empCallStatus.setCallCount(1);
                        empCallStatus.setStatus(false);
                        empCallStatus.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                        employeeCallStatusService.saveEmployeeCallStatus(empCallStatus);
                    }

                } else {

                    employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(empMst.getId(), "Blocked");

                    if (employeeActivityDtl != null) {

                        employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                        employeeActivityDtlService.save(employeeActivityDtl);
                    }
                    request.getSession().setAttribute("incomingCallType", null);
                }
                if (!findOtherNonEndedCallDetailFromSameCallMst.isEmpty()) {

                    for (CallDtl calldtl : findOtherNonEndedCallDetailFromSameCallMst) {
                        if (calldtl.getEndTime() == null) {
                            calldtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                            calldtl.setDeleteFlg(false);
                            calldtl.setAgentComments("Call forwarded Successfully");
                            calldtl.setActiveFlg(true);
                            calldtl = callDtlService.saveCallDtl(calldtl);
                        }
                    }

                    findOtherNonEndedCallDetailFromSameCallMst.clear();
                }

            } catch (ParseException e) {
                logger.error("AfterForwardHangUp1:Error:" + e.getMessage());
            }

            RequestContext.getCurrentInstance().execute("chatSave();");
            request.getSession().setAttribute("callMst", null);

        } catch (ParseException ex) {
            logger.error("AfterForwardHangUp2:Error:" + ex.getMessage());

        }

    }

    public void testJoin() {
        try {
            this.setSelfviewenabled(true);
            this.setGotoDashoardenabled(false);
            RequestContext.getCurrentInstance().execute("joinViaBrowser();");
            RequestContext.getCurrentInstance().execute("document.getElementById('incallcontainer').style.display='block';");
            HttpSession session = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession(false);
            EmployeeMst employeeMaster = (EmployeeMst) session.getAttribute("ormUserMaster");
            RequestContext.getCurrentInstance().execute("$('#spinner').hide();");
            EmployeeActivityDtl employeeActivityDtl = new EmployeeActivityDtl();
            employeeActivityDtl.setActivity("Self View");
            employeeActivityDtl.setEmpId(employeeMaster);
            employeeActivityDtl.setStartTime(CustomConvert.javaDateToTimeStamp(new Date()));
            ReasonMst reasonmst = reasonMstService.findReasonMstByReasonType("SelfView");
            employeeActivityDtl.setReasonId(reasonmst);
            employeeActivityDtl.setReasonCd(reasonmst.getReasonCd());
            employeeActivityDtl.setReasonDesc(reasonmst.getReasonDesc());
            employeeActivityDtlService.save(employeeActivityDtl);
        } catch (Exception e) {
            RequestContext.getCurrentInstance().execute("$('#spinner').hide();");
            RequestContext.getCurrentInstance().execute("closeAllDialog();");
            logger.error("testJoin:Error:" + e.getMessage());
        }

    }

    public void goToDashboard() throws ParseException {
        HttpSession session = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession(false);
        EmployeeMst empMst = (EmployeeMst) session.getAttribute("ormUserMaster");
        agentStatus = "Ready";
        this.setSelfviewenabled(false);
        this.setSetupCheck(false);

        this.setDashboardCheck(true);
        this.agentColor = "";
        this.hangupenabled = true;
        this.dialenabled = true;
        this.holdenabled = true;
        this.threewayenabled = true;
        this.forwardenabled = true;
        this.notreadyenabled = true;
        this.logoutenabled = false;
        this.renderedMakeScheduleButton = false;
        this.recordingStatus = "";
        this.availableenabled = false;
        this.setIworkImage(false);

        EmployeeActivityDtl employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(empMst.getId(), "Self View");

        if (employeeActivityDtl != null) {
            employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
            employeeActivityDtlService.save(employeeActivityDtl);

        }
        ReasonMst reasonMst = reasonMstService.findReasonMstByReasonCode("NRDFT");
        if (reasonMst == null) {
            reasonMst = new ReasonMst();
            reasonMst.setReasonCd("NRDFT");
            reasonMst.setType("Not Ready");
            reasonMst.setReasonDesc("Not Ready");
            reasonMst.setActiveFlg(true);
            reasonMst.setDeleteFlg(false);
            reasonMst = reasonMstService.save(reasonMst, empMst);
        }
        if (reasonMst != null) {
            employeeActivityDtl = new EmployeeActivityDtl();
            employeeActivityDtl.setActivity("not ready");
            employeeActivityDtl.setEmpId(empMst);
            employeeActivityDtl.setStartTime(CustomConvert.javaDateToTimeStamp(new Date()));
            employeeActivityDtl.setReasonId(reasonMst);
            employeeActivityDtl.setReasonCd(reasonMst.getReasonCd());
            employeeActivityDtl.setReasonDesc(reasonMst.getReasonDesc());
            employeeActivityDtlService.save(employeeActivityDtl);
        }
        this.setAutoPollingStart("true");

    }

    public void refreshCheck() {
        logger.info("Inside refreshCheck");
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        EmployeeMst CurrEmp = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
        request.getSession().setAttribute("ormAgentMaster", CurrEmp);
        logger.info(" CurrEmp in refreshCheck " + CurrEmp);
        EmployeeActivityDtl employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(CurrEmp.getId(), "login");
        if (employeeActivityDtl == null) {
            agentStatus = "Ready";
            this.agentColor = "";

            this.hangupenabled = true;
            this.holdenabled = true;

            this.dialenabled = false;
            this.threewayenabled = true;
            this.forwardenabled = true;
            this.logoutenabled = false;
            this.renderedMakeScheduleButton = false;

            ReasonMst reasonMst = reasonMstService.findReasonMstByReasonCode("LN001");
            if (null == reasonMst) {
                reasonMst = new ReasonMst();
                reasonMst.setType("login");
                reasonMst.setReasonCd("LN001");
                reasonMst.setReasonDesc("Login into application");
                reasonMst.setActiveFlg(true);
                reasonMst.setDeleteFlg(false);
                reasonMst = reasonMstService.save(reasonMst, CurrEmp);

            }
            employeeActivityDtl = new EmployeeActivityDtl();
            employeeActivityDtl.setEmpId(CurrEmp);
            employeeActivityDtl.setActivity("login");
            try {
                employeeActivityDtl.setStartTime(CustomConvert.javaDateToTimeStamp(new Date()));
            } catch (ParseException e) {
                logger.info("Error:" + e.getMessage());
            }
            employeeActivityDtl.setReasonId(reasonMst);
            employeeActivityDtl.setReasonCd(reasonMst.getReasonCd());
            employeeActivityDtl.setReasonDesc(reasonMst.getReasonDesc());
            employeeActivityDtlService.save(employeeActivityDtl, CurrEmp);
            if (!this.setupCheck) {
                this.availableenabled = false;
                this.notreadyenabled = true;
                reasonMst = reasonMstService.findReasonMstByReasonCode("NRDFT");
                if (reasonMst == null) {
                    reasonMst = new ReasonMst();
                    reasonMst.setReasonCd("NRDFT");
                    reasonMst.setType("Not Ready");
                    reasonMst.setReasonDesc("Not Ready");
                    reasonMst.setActiveFlg(true);
                    reasonMst.setDeleteFlg(false);
                    reasonMst = reasonMstService.save(reasonMst, CurrEmp);
                }
                if (reasonMst != null) {
                    employeeActivityDtl = new EmployeeActivityDtl();
                    employeeActivityDtl.setActivity("not ready");
                    employeeActivityDtl.setEmpId(CurrEmp);
                    try {
                        employeeActivityDtl.setStartTime(CustomConvert.javaDateToTimeStamp(new Date()));
                    } catch (ParseException ex) {
                        Logger.getLogger(VidyoAgent.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    employeeActivityDtl.setReasonId(reasonMst);
                    employeeActivityDtl.setReasonCd(reasonMst.getReasonCd());
                    employeeActivityDtl.setReasonDesc(reasonMst.getReasonDesc());
                    employeeActivityDtlService.save(employeeActivityDtl);
                }
            } else {
                this.availableenabled = true;
                this.notreadyenabled = true;
            }
        } else {
            this.setSetupCheck(true);
            this.availableenabled = true;

        }

        request.getSession().setAttribute("callMst", null);
    }

    public void checkIncomingReceive(HttpServletRequest request) {
        CallMst callMstLocal = (CallMst) request.getSession().getAttribute("callMst");
        if (null != callMstLocal) {
            callMstLocal = callMasterService.findCallMstById(callMstLocal.getId());
            if (callMstLocal.getCustomerPickupTime() == null) {
                logger.info("callMstLocal:" + callMstLocal + "sendAcknowledgement('" + callMstLocal.getCustId() + "')");
                RequestContext.getCurrentInstance().execute("sendAcknowledgement('" + callMstLocal.getCustId() + "')");
            }
        }
    }

    public void saveChat() {
        logger.info("Save Chat Called................#########################");

        HttpSession session = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession(false);
        try {

            CallMst callMstLocal = (CallMst) session.getAttribute("callMstChat");

            if (callMstLocal != null) {
                callMstLocal = callMasterService.findCallMstById(callMstLocal.getId());
                CallRecords callRecords = callRecordsService.findCallRecordsByCallId(callMstLocal.getId(), (long) 0);
                if (callRecords != null) {
                    if (null == callRecords.getChatText() || "".equals(callRecords.getChatText().trim())) {

                        logger.info("save chat called for not forwrded call");

                        String tabs = "<ul class=\"tabs\" id=\"addstatictab\">";
                        tabs = tabs + this.chatText;
                        tabs = tabs + "</ul><div class=\"tabcontents\" id=\"participant\">";
                        tabs = tabs + this.chatText1;
                        tabs = tabs + "</div>";
                        callRecords.setChatText(tabs);
                        callRecordsService.saveCallRecord(callRecords);
                    } else {

                        logger.info("+++++++++++++save chat called for FORWARDED call");
                        String previousSavedChat = callRecords.getChatText();
                        String previoustabs = previousSavedChat.substring(0, previousSavedChat.lastIndexOf("</ul>"));
                        Random rn = new Random();
                        int idx = rn.nextInt(100) + 1;
                        String appendtabs = this.chatText.replaceAll("Everyone", "Everyone" + idx).replaceAll("participant", "participant" + idx);
                        String finalTabs = previoustabs + appendtabs + "</ul>";
                        String previousContent = previousSavedChat.substring(previousSavedChat.lastIndexOf("</ul>") + 5, previousSavedChat.lastIndexOf("</div>"));
                        String appendContent = this.chatText1.replaceAll("Everyone", "Everyone" + idx).replaceAll("participant", "participant" + idx);
                        appendContent = appendContent.replaceAll(">Everyone" + idx + "<", ">Everyone<");
                        String finalContent = previousContent + appendContent + "</div>";
                        callRecords.setChatText(finalTabs + finalContent);
                        callRecordsService.saveCallRecord(callRecords);

                    }

                }
                session.setAttribute("callMstChat", null);
            }
        } catch (NumberFormatException e) {

            logger.error("Exception: saveChat" + e.getMessage());
        }
        // session.setAttribute("callMst", null);
    }

    public void mailReset() {
        guestEmail = null;
    }

    public void threeway() {
        this.threewayenabled = false;
    }

    public void nothreeway() {
        this.threewayenabled = true;
    }

    public Integer getLastdelay() {
        return lastdelay;
    }

    public void setLastdelay(Integer lastdelay) {
        this.lastdelay = lastdelay;
    }

    public void setFileTitle(HttpServletRequest request) {
        logger.info("In setFileTitle");
        if (docTitle == null || docTitle.equals("")) {
            FacesContext.getCurrentInstance().addMessage("fileCaption", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Please provide a document title"));
        } else {
            this.fileCaptionRenderer = true;
        }
    }

    public void fileUploadListener(FileUploadEvent event) {
        try {

            if (event != null) {
                HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
                CallMst callMster = (CallMst) request.getSession().getAttribute("callMst");
                EmployeeMst CurrEmp = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
                String websiteURL = request.getRequestURL().substring(0, request.getRequestURL().indexOf("/", 9));
                boolean flag = false;
                String filePathForDatabase;
                Long result;
                String filePathToSend;

                String jbossHome = System.getenv("JBOSS_HOME");
                if (fileUploadDtoList.size() > 1 && selectedFileuploadDtoList.isEmpty()) {
                    FacesContext.getCurrentInstance().addMessage("fileuploadbutton", new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "ERROR : ", "Please Select Participant(s)"));
                    //RequestContext.getCurrentInstance().execute("PF('fileuploadvar').reset();");
                    return;
                }

                if (docTitle != null && !docTitle.isEmpty()) {

                    String fName;

                    UploadedFile file = event.getFile();

                    if (file != null && file.getSize() > 0) {
                        fName = file.getFileName();

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

//                        CustomerMst cm = customerMstService.findCustomerByUserID(callMster.getCustId());
                        if (!selectedFileuploadDtoList.isEmpty() && selectedFileuploadDtoList.size() > 0) {
                            for (PerticipentDto obj : selectedFileuploadDtoList) {
                                CallFileUploadDtls callFileUploadDtls = new CallFileUploadDtls();
                                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                                callFileUploadDtls.setCreatedDateTime(timestamp);
                                callFileUploadDtls.setFileReceivedBy(obj.getId());
                                callFileUploadDtls.setCallMstId(callMster);
                                callFileUploadDtls.setFileSentBy(CurrEmp.getId());
                                callFileUploadDtls.setFilePath(filePathForDatabase);
                                callFileUploadDtls.setCreatedBy(CurrEmp.getId());
                                callFileUploadDtls.setDocTitle(docTitle);
                                callFileUploadDtls.setFileSentbyType("EMPLOYEE");
                                if (obj.getParticipantType() != null && obj.getParticipantType().equals("Customer")) {
                                    callFileUploadDtls.setFileReceivedbyType("CUSTOMER");
                                } else {
                                    callFileUploadDtls.setFileReceivedbyType("EMPLOYEE");
                                }

                                result = callFileUploadDtlsService.saveCallFileDetails(callFileUploadDtls);
                                /**
                                 * Modified By VD on 04-11-2020 add agent PK id
                                 *
                                 */
                                logger.info("sendFileReceiveRequest('" + obj.getLoginId() + "','fileSent#" + filePathToSend + "#" + docTitle + "#" + CurrEmp.getId() + "')");
                                RequestContext.getCurrentInstance().execute("sendFileReceiveRequest('" + obj.getLoginId() + "','fileSent#" + filePathToSend + "#" + docTitle + "#" + CurrEmp.getId() + "')");
//                             
                                if (result > 0) {
                                    flag = true;
                                }
                            }
                        } else {
                            CallFileUploadDtls callFileUploadDtls = new CallFileUploadDtls();
                            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                            callFileUploadDtls.setCreatedDateTime(timestamp);
                            callFileUploadDtls.setFileReceivedBy(callMster.getCustomerId().getId());
                            callFileUploadDtls.setCallMstId(callMster);
                            callFileUploadDtls.setFileSentBy(CurrEmp.getId());
                            callFileUploadDtls.setFilePath(filePathForDatabase);
                            callFileUploadDtls.setCreatedBy(CurrEmp.getId());
                            callFileUploadDtls.setDocTitle(docTitle);
                            callFileUploadDtls.setFileSentbyType("EMPLOYEE");
                            callFileUploadDtls.setFileReceivedbyType("CUSTOMER");

                            result = callFileUploadDtlsService.saveCallFileDetails(callFileUploadDtls);
                            /**
                             * Modified By VD on 04-11-2020 add agent PK id
                             *
                             */
                            logger.info("sendFileReceiveRequest('" + callMster.getCustId() + "','fileSent#" + filePathToSend + "#" + docTitle + "#" + CurrEmp.getId() + "')");
                            RequestContext.getCurrentInstance().execute("sendFileReceiveRequest('" + callMster.getCustId() + "','fileSent#" + filePathToSend + "#" + docTitle + "#" + CurrEmp.getId() + "')");

                            if (result > 0) {
                                flag = true;
                            }
                        }
                    }
                    if (flag) {

                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                                "SUCCESS : ", "Document Uploaded Successfully."));
                        RequestContext.getCurrentInstance().execute("PF('fileUploadVar').hide();");

                    } else {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "ERROR : ", "Document Uploading Unsuccessful."));
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

    public void conferenceParticipants(HttpServletRequest request) {

        RequestContext.getCurrentInstance().execute("closeAllDialog();PF('fileuploadvar').show();");
    }

    public void handleImageFileUpload(FileUploadEvent event) {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();
        boolean valid = true;
        UploadedFile uploadedfile = event.getFile();
        BufferedOutputStream outputStream;
        FileOutputStream fos;
        BufferedOutputStream outputStream2;
        FileOutputStream fos2;
        boolean flag = false;

        CallMst callMster = (CallMst) session.getAttribute("callMst");
        EmployeeMst CurrEmp = (EmployeeMst) session.getAttribute("ormUserMaster");

        if (uploadedfile == null || uploadedfile.getSize() == 0) {

            valid = false;
            fileUploadMsg = "Please select file";

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(fileUploadMsg));

        } else if (uploadedfile.getFileName() == null || uploadedfile.getFileName().equalsIgnoreCase("") || uploadedfile.getFileName().isEmpty()) {

            valid = false;
            fileUploadMsg = "File Type Not Supported. Please select correct file.";

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(fileUploadMsg));

        } else {

            String fn = uploadedfile.getFileName();

            String parts[] = fn.split(Pattern.quote("."));
            String ext = parts[1];

            if (ext.equalsIgnoreCase("jpeg") || ext.equalsIgnoreCase("exif") || ext.equalsIgnoreCase("tiff") || ext.equalsIgnoreCase("rif") || ext.equalsIgnoreCase("bmp") || ext.equalsIgnoreCase("png") || ext.equalsIgnoreCase("webp") || ext.equalsIgnoreCase("jpg")) {

                if (uploadedfile.getSize() > 2097152) {
                    valid = false;
                    fileUploadMsg = "Please upload image of size less than 2MB.";

                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(fileUploadMsg));
                }
            } else {
                valid = false;
                fileUploadMsg = "File extension not supported";

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(fileUploadMsg));
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

                String path = jbossHome + File.separator + "standalone" + File.separator + "tmp" + File.separator + "vfs" + File.separator + no + upFile.getFileName();

                try {
                    fos = new FileOutputStream(deploymentDirectoryPath + File.separator + no + upFile.getFileName());
                    outputStream = new BufferedOutputStream(fos);
                    outputStream.write(fileContentInByte);
                    outputStream.close();

                    fos2 = new FileOutputStream(path);
                    outputStream2 = new BufferedOutputStream(fos2);
                    outputStream2.write(fileContentInByte);
                    outputStream2.close();

                    if (flag) {

                        fileUploadMsg = "File Sent SuccessFully";

                        String websiteURL = request.getRequestURL().substring(0, request.getRequestURL().indexOf("/", 9));
                        SocketMessage.send(callScheduler.getAdminSocket(), callMster.getCustId(), "fileUpload#" + callMster.getId() + "#" + websiteURL + File.separator + no + upFile.getFileName());
                        logger.info("===>" + "fileUpload#" + callMster.getId() + "#" + websiteURL + request.getContextPath() + File.separator + no + upFile.getFileName());
                    } else {
                        fileUploadMsg = " File Not Sent SuccessFully";
                    }

                    RequestContext.getCurrentInstance().execute("document.getElementById('uploadpanel').style.display = 'block'");

                } catch (IOException ex) {
                    fileUploadMsg = " Not Sent SuccessFully" + ex.getMessage();
                    logger.info("Error in file upload " + ex);

                } catch (Exception e) {
                    fileUploadMsg = " Not Sent SuccessFully" + e.getMessage();
                    logger.info("Error in file upload " + e);
                } finally {
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO, fileUploadMsg, ""));

                }
            }
        }

    }

    public void handleDirectScanFromSnap() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();
        ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String deploymentDirectoryPath = ctx.getRealPath("/");
        try {
            CallMst callMster = (CallMst) session.getAttribute("callMst");
            if (null != callMster) {
                CustomerMst cm = customerMstService.findCustomerMstByCustId(callMster.getCustId());
                BufferedImage image = this.getImageFromSnapShot(this.binaryImage);

// write the image to a file
                Random rand = new Random();
                int no = rand.nextInt(1000) + 1;
                String imagePath = no + "snapshot.jpg";
                File ff = new File(deploymentDirectoryPath + "/resources" + File.separator + "File_Upload" + File.separator + "Call" + File.separator
                        + callMster.getId() + File.separator + imagePath);
                File n = new File(deploymentDirectoryPath + "/resources" + File.separator + "File_Upload" + File.separator + "Call" + File.separator
                        + callMster.getId());
                if (!n.exists()) {
                    n.mkdirs();
                }
                //File ff = new File(deploymentDirectoryPath + "/snapshot.jpg");
                //File outputfile = new File("E:\\Learning\\image3.jpg");
                ImageIO.write(image, "png", ff);

                String jbossHome = System.getenv("JBOSS_HOME");

                String path = jbossHome + File.separator + "standalone" + File.separator + "File_Upload" + File.separator + "Call" + File.separator
                        + callMster.getId() + File.separator + imagePath;
                n = new File(jbossHome + File.separator + "standalone" + File.separator + "File_Upload" + File.separator + "Call" + File.separator
                        + callMster.getId());
                if (!n.exists()) {
                    n.mkdirs();
                }
                File ff2 = new File(path);
                ImageIO.write(image, "png", ff2);
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(Constants.ocrUrl + "getText");
                //httpPost.addHeader("Content-type", "multipart/form-data");

                FileBody uploadFilePart = new FileBody(ff);
                MultipartEntity reqEntity = new MultipartEntity();
                reqEntity.addPart("file", uploadFilePart);
                httpPost.setEntity(reqEntity);

                HttpResponse response1 = httpclient.execute(httpPost);
                HttpEntity responseEntity = response1.getEntity();
                String response = "Failure";
                if (responseEntity != null) {
                    response = EntityUtils.toString(responseEntity);
                }
                logger.info("Response==========" + response);

                JSONObject jsonObject;
                try {
                    jsonObject = new JSONObject(response);
                    logger.info("NAME IS====" + jsonObject.getString("name"));
                    //ERROR: Can not open input file snapshot_name.jpg Error during processing
                    if (!jsonObject.getString("name").contains("ERROR:")) {
                        imageComponent.setIdCardName(jsonObject.getString("name"));
                        cm.setCustNameAsIdCard(jsonObject.getString("name"));
                    } else {
                        imageComponent.setIdCardName("Can't Read The Name");
                    }
                    if (!jsonObject.getString("nationality").contains("ERROR:")) {
                        imageComponent.setIdCardNationality(jsonObject.getString("nationality"));
                        cm.setCustNationalityAsIdCard(jsonObject.getString("nationality"));
                    } else {
                        imageComponent.setIdCardNationality("Can't Read The Nationality");
                    }
                    if (!jsonObject.getString("id").contains("ERROR:")) {
                        imageComponent.setIdCardNumber(jsonObject.getString("id"));
                        cm.setCustIdNo(jsonObject.getString("id"));
                    } else {
                        imageComponent.setIdCardNumber("Can't Read The Id Card Number");
                    }

                    cm = customerMstService.saveCustomerMst(cm, null);
                    //this.scanResultRender=true;
                    Boolean match = false;
                    if (null != cm.getCustNameAsIdCard() && null != cm.getCustNationalityAsIdCard() && null != cm.getCustIdNo()) {
                        if (cm.getCustNationalityAsIdCard().equalsIgnoreCase(cm.getNatinality()) && cm.getCustIdNo().equals(cm.getNationalId())) {
                            match = true;
                        }
                        if (match) {
                            String[] name = cm.getFirstName().split(" ");
                            if (name.length > 1) {
                                for (String name1 : name) {
                                    Pattern p = Pattern.compile(" " + name1 + " ", Pattern.CASE_INSENSITIVE);
                                    Matcher m = p.matcher(" " + cm.getCustNameAsIdCard() + " ");
                                    if (m.find()) {
                                        match = true;
                                    } else {
                                        match = false;
                                        break;
                                    }
                                }
                            }
                        }
                    } else {
                        String[] jsonarray = jsonObject.getString("others").split(",");
                        List<String> outputs = new ArrayList<>();
                        for (String jsonarray1 : jsonarray) {
                            String val = jsonarray1.trim();
                            if (!val.equals("") && !val.equals("others")) {
                                Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
                                Matcher m = p.matcher(val);
                                if (!m.find()) {
                                    outputs.add(val.toUpperCase());
                                }
                            }
                        }
                        logger.info("Others========" + outputs);

                        logger.info("Contains Nationality ==>" + outputs.contains(cm.getNatinality().toUpperCase()));
                        logger.info("Contains National ID ==>" + outputs.contains(cm.getNationalId()));
                        if (outputs.contains(cm.getNatinality().toUpperCase()) && outputs.contains(cm.getNationalId())) {
                            match = true;
                        }
                        if (match) {
                            String[] name = cm.getFirstName().split(" ");
                            if (name.length > 1) {
                                for (String output : outputs) {
                                    logger.info("OUTPUT==>" + output);
                                    for (String name1 : name) {
                                        logger.info("Name==>" + name1);
                                        Pattern p = Pattern.compile(" " + name1 + " ", Pattern.CASE_INSENSITIVE);
                                        Matcher m = p.matcher(" " + output + " ");
                                        if (m.find()) {
                                            logger.info("matched");
                                            match = true;
                                        } else {
                                            logger.info("Not matched");
                                            match = false;
                                            break;
                                        }
                                    }
                                    if (match) {
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    RequestContext.getCurrentInstance().execute("$('#spinner').hide()");
                    if (match) {
                        RequestContext.getCurrentInstance().execute("PF('verifyFacevar').show();");
                    } else {
                        RequestContext.getCurrentInstance().execute("PF('verifyFacenotvar').show();");
                    }

                } catch (JSONException ex) {
                    Logger.getLogger(VidyoAgent.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(VidyoAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void handleFaceVerificationFromSnap() {
        logger.info("In Side handleFaceVerificationFromSnap");
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();
        String website = request.getRequestURL().substring(0, request.getRequestURL().indexOf("/", 9));
        ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String deploymentDirectoryPath = ctx.getRealPath("/");
        try {
            CallMst callMster = (CallMst) session.getAttribute("callMst");
            if (null != callMster) {
                Random rand = new Random();
                int no = rand.nextInt(1000) + 1;
                CustomerMst cm = customerMstService.findCustomerMstByCustId(callMster.getCustId());
                if (null == cm.getAccountNo()) {
                    CustomerDtl cdtl = customerDtlService.findIDByPhoneNo(cm.getCellPhone().toString());
                    if (null != cdtl) {
                        customerAccountComponent.setNewSnapImage(website + request.getContextPath() + cdtl.getCustomerImage());
                    }
                } else {
                    CustomerLoanDtl cldtl = customerLoanDtlService.findIDByPhoneNo(cm.getCellPhone().toString());
                    if (null != cldtl) {
                        customerAccountComponent.setNewSnapImage(website + request.getContextPath() + cldtl.getCustomerImage());
                    }
                }
                BufferedImage image = this.getImageFromSnapShot(this.binaryImage);

                // write the image to a file
                String imagePath = no + "snapshot.jpg";
                File ff = new File(deploymentDirectoryPath + "/resources" + File.separator + "File_Upload" + File.separator + "Call" + File.separator
                        + callMster.getId() + File.separator + imagePath);
                File n = new File(deploymentDirectoryPath + "/resources" + File.separator + "File_Upload" + File.separator + "Call" + File.separator
                        + callMster.getId());
                if (!n.exists()) {
                    n.mkdirs();
                }
                //File outputfile = new File("E:\\Learning\\image3.jpg");
                ImageIO.write(image, "png", ff);
                String jbossHome = System.getenv("JBOSS_HOME");

                String path = jbossHome + File.separator + "standalone" + File.separator + "File_Upload" + File.separator + "Call" + File.separator
                        + callMster.getId() + File.separator + imagePath;
                n = new File(jbossHome + File.separator + "standalone" + File.separator + "File_Upload" + File.separator + "Call" + File.separator
                        + callMster.getId());
                if (!n.exists()) {
                    n.mkdirs();
                }
                File ff2 = new File(path);
                ImageIO.write(image, "png", ff2);
                customerAccountComponent.setSnapImage(website + request.getContextPath() + "/resources/File_Upload/Call/" + callMster.getId() + "/" + imagePath);

                RequestContext.getCurrentInstance().execute("$('#spinner').hide()");
                RequestContext.getCurrentInstance().update("custimageverifyform");

            }

        } catch (IOException ex) {
            Logger.getLogger(VidyoAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void handleAddressProofVerificationFromSnap() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();
        ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String deploymentDirectoryPath = ctx.getRealPath("/");
        String website = request.getRequestURL().substring(0, request.getRequestURL().indexOf("/", 9));
        try {
            CallMst callMster = (CallMst) session.getAttribute("callMst");
            if (null != callMster) {
                Random rand = new Random();
                int no = rand.nextInt(1000) + 1;
                CustomerMst cm = customerMstService.findCustomerMstByCustId(callMster.getCustId());
                if (null == cm.getAccountNo()) {
                    CustomerDtl cdtl = customerDtlService.findIDByPhoneNo(cm.getCellPhone().toString());
                    if (null != cdtl) {
                        customerAccountComponent.setNewUploadedAddProof(website + request.getContextPath() + cdtl.getUtilityBill());
                    }
                } else {
                    CustomerLoanDtl cldtl = customerLoanDtlService.findIDByPhoneNo(cm.getCellPhone().toString());
                    if (null != cldtl) {
                        customerAccountComponent.setNewUploadedAddProof(website + request.getContextPath() + cldtl.getUtilityBill());
                    }
                }
                BufferedImage image = this.getImageFromSnapShot(this.binaryImage);

                // write the image to a file
                String imagePath = no + "snapshot.jpg";
                File ff = new File(deploymentDirectoryPath + "/resources" + File.separator + "File_Upload" + File.separator + "Call" + File.separator
                        + callMster.getId() + File.separator + imagePath);
                File n = new File(deploymentDirectoryPath + "/resources" + File.separator + "File_Upload" + File.separator + "Call" + File.separator
                        + callMster.getId());
                if (!n.exists()) {
                    n.mkdirs();
                }
                //File outputfile = new File("E:\\Learning\\image3.jpg");
                ImageIO.write(image, "png", ff);

                String jbossHome = System.getenv("JBOSS_HOME");

                String path = jbossHome + File.separator + "standalone" + File.separator + "File_Upload" + File.separator + "Call" + File.separator
                        + callMster.getId() + File.separator + imagePath;
                n = new File(jbossHome + File.separator + "standalone" + File.separator + "File_Upload" + File.separator + "Call" + File.separator
                        + callMster.getId());
                if (!n.exists()) {
                    n.mkdirs();
                }
                File ff2 = new File(path);
                ImageIO.write(image, "png", ff2);
                customerAccountComponent.setUploadedAddProof(website + request.getContextPath() + "/resources/File_Upload/Call/" + callMster.getId() + "/" + imagePath);

                RequestContext.getCurrentInstance().execute("$('#spinner').hide()");
                RequestContext.getCurrentInstance().update("custaddproofverifyform");

            }

        } catch (IOException ex) {
            Logger.getLogger(VidyoAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void handleIdCardVerificationFromSnap() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();
        ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String deploymentDirectoryPath = ctx.getRealPath("/");
        String website = request.getRequestURL().substring(0, request.getRequestURL().indexOf("/", 9));
        try {
            CallMst callMster = (CallMst) session.getAttribute("callMst");
            if (null != callMster) {
                Random rand = new Random();
                int no = rand.nextInt(1000) + 1;
                CustomerMst cm = customerMstService.findCustomerMstByCustId(callMster.getCustId());
                if (null == cm.getAccountNo()) {
                    CustomerDtl cdtl = customerDtlService.findIDByPhoneNo(cm.getCellPhone().toString());
                    if (null != cdtl) {
                        customerAccountComponent.setNewUploadedNationalId(website + request.getContextPath() + cdtl.getNationalId());
                    }
                } else {
                    CustomerLoanDtl cldtl = customerLoanDtlService.findIDByPhoneNo(cm.getCellPhone().toString());
                    if (null != cldtl) {
                        customerAccountComponent.setNewUploadedNationalId(website + request.getContextPath() + cldtl.getNationalId());
                    }
                }
                BufferedImage image = this.getImageFromSnapShot(this.binaryImage);

                // write the image to a file
                String imagePath = no + "snapshot.jpg";
                File ff = new File(deploymentDirectoryPath + "/resources" + File.separator + "File_Upload" + File.separator + "Call" + File.separator
                        + callMster.getId() + File.separator + imagePath);
                File n = new File(deploymentDirectoryPath + "/resources" + File.separator + "File_Upload" + File.separator + "Call" + File.separator
                        + callMster.getId());
                if (!n.exists()) {
                    n.mkdirs();
                }
                //File outputfile = new File("E:\\Learning\\image3.jpg");
                ImageIO.write(image, "png", ff);

                String jbossHome = System.getenv("JBOSS_HOME");

                String path = jbossHome + File.separator + "standalone" + File.separator + "File_Upload" + File.separator + "Call" + File.separator
                        + callMster.getId() + File.separator + imagePath;
                n = new File(jbossHome + File.separator + "standalone" + File.separator + "File_Upload" + File.separator + "Call" + File.separator
                        + callMster.getId());
                if (!n.exists()) {
                    n.mkdirs();
                }
                File ff2 = new File(path);
                ImageIO.write(image, "png", ff2);

                customerAccountComponent.setUploadedNationalId(website + request.getContextPath() + "/resources/File_Upload/Call/" + callMster.getId() + "/" + imagePath);

                RequestContext.getCurrentInstance().execute("$('#spinner').hide()");
                RequestContext.getCurrentInstance().update("custidcardverifyform");

            }

        } catch (IOException ex) {
            Logger.getLogger(VidyoAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void handleSaveFromSnap() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();
        ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String deploymentDirectoryPath = ctx.getRealPath("/");
        try {
            CallMst callMster = (CallMst) session.getAttribute("callMst");
            if (null != callMster) {
                Random rand = new Random();
                int no = rand.nextInt(1000) + 1;
                CustomerMst cm = customerMstService.findCustomerMstByCustId(callMster.getCustId());
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
                String website = request.getRequestURL().substring(0, request.getRequestURL().indexOf("/", 9));
                logger.info("::::::::IMAGE PATH::::::::::::" + website + request.getContextPath() + File.separator + "resources" + tempFilePath);
                customerAccountComponent.setSnapImage(website + request.getContextPath() + "/resources/File_Upload/Call/" + callMster.getId() + "/" + imagePath);
                logger.info("::::::::IMAGE PATH AFTER SUBSTRING::::::::::::" + customerAccountComponent.getSnapImage().substring(customerAccountComponent.getSnapImage().indexOf("/") + 1));

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

    public void handleSaveIdCardFromSnap() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();
        ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String deploymentDirectoryPath = ctx.getRealPath("/");
        try {
            CallMst callMster = (CallMst) session.getAttribute("callMst");
            if (null != callMster) {
                Random rand = new Random();
                int no = rand.nextInt(1000) + 1;
                CustomerMst cm = customerMstService.findCustomerMstByCustId(callMster.getCustId());
                BufferedImage image;
                image = this.getImageFromSnapShot(this.binaryImage);

// write the image to a file
                String imagePath = no + "snapshot.jpg";
                String tempFilePath = File.separator + "File_Upload" + File.separator + "Call" + File.separator
                        + callMster.getId() + File.separator + imagePath;

                File ff1 = new File(deploymentDirectoryPath + "/resources" + tempFilePath);
                File n = new File(deploymentDirectoryPath + "/resources" + File.separator + "File_Upload" + File.separator + "Call" + File.separator
                        + callMster.getId());
                if (!n.exists()) {
                    n.mkdirs();
                }
                //File outputfile = new File("E:\\Learning\\image3.jpg");
                ImageIO.write(image, "png", ff1);
                String website = request.getRequestURL().substring(0, request.getRequestURL().indexOf("/", 9));
                logger.info("::::::::IMAGE PATH::::::::::::" + website + request.getContextPath() + "resources" + tempFilePath);
                customerAccountComponent.setUploadedNationalId(website + request.getContextPath() + File.separator + "resources" + tempFilePath);
                logger.info("::::::::IMAGE PATH AFTER SUBSTRING::::::::::::" + customerAccountComponent.getUploadedNationalId().substring(customerAccountComponent.getUploadedNationalId().indexOf(File.separator)));

                String jbossHome = System.getenv("JBOSS_HOME");

                String path = jbossHome + File.separator + "standalone" + tempFilePath;
                n = new File(jbossHome + File.separator + "standalone" + File.separator + "File_Upload" + File.separator + "Call" + File.separator
                        + callMster.getId());
                if (!n.exists()) {
                    n.mkdirs();
                }
                File ff2 = new File(path);
                ImageIO.write(image, "png", ff2);

                ThreadSafeSimpleDateFormat dateFormat = new ThreadSafeSimpleDateFormat("yyMMdd");
                Date dob = new Date();
                String fullName = "";
                String gender = "";
                String nationality = "";

                logger.info("Inside file upload for scan");
                boolean valid = true;

                if (valid) {

                    try {
                        File ff = new File(deploymentDirectoryPath + "/resources" + tempFilePath);

                        HttpClient httpclient = new DefaultHttpClient();
                        HttpPost httpPost = new HttpPost(Constants.ocrUrl + "getTextSnapshot");
                        //httpPost.addHeader("Content-type", "multipart/form-data");

                        FileBody uploadFilePart = new FileBody(ff);
                        MultipartEntity reqEntity = new MultipartEntity();
                        reqEntity.addPart("file", uploadFilePart);
                        httpPost.setEntity(reqEntity);

                        HttpResponse response1 = httpclient.execute(httpPost);
                        HttpEntity responseEntity = response1.getEntity();
                        String response = "Failure";
                        if (responseEntity != null) {
                            response = EntityUtils.toString(responseEntity);
                        }
                        logger.info("Response==========" + response);

                        JSONObject jsonObject;
                        try {
                            jsonObject = new JSONObject(response);
                            logger.info("NAME IS====" + jsonObject.getString("name"));

                            String[] jsonarray = jsonObject.getString("others").split(",");
                            Map<String, String> outputss = new HashMap<>();
                            for (String jsonarray1 : jsonarray) {
                                String[] othersval = jsonarray1.trim().split(":");
                                String val = "";
                                if (othersval.length > 1) {
                                    val = othersval[1];
                                }
                                if (!val.equals("") && !val.equals("others")) {
                                    Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
                                    Matcher m = p.matcher(val);
                                    if (!m.find()) {
                                        outputss.put(othersval[0], val);
                                        if (othersval[0].equalsIgnoreCase("Name")) {
                                            logger.info("Value is==" + val);
                                            fullName = val;
                                        } else if (othersval[0].equalsIgnoreCase("Nationality")) {
                                            logger.info("Value is==" + val);
                                            nationality = val;
                                        } else if (othersval[0].equalsIgnoreCase("Sex")) {
                                            logger.info("Value is==" + val);
                                            if (val.equals("Male")) {
                                                gender = "M";
                                            } else {
                                                gender = "F";
                                            }
                                        } else if (othersval[0].equalsIgnoreCase("DOB")) {
                                            logger.info("Value is==" + dateFormat.parse(val));
                                            dob = dateFormat.parse(val);
                                        }

                                    }
                                }
                            }
                            logger.info("Others========" + outputss);

                        } catch (JSONException | ParseException ex) {
                            Logger.getLogger(VidyoAgent.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    } catch (IOException e) {
                        logger.error("ERROR ISSSSSSSSSSSSSSSSSSS====" + e);
                    }

                }
                if (null != cm.getAccountNo()) {
                    CustomerLoanDto customerLoanDto = customerAccountComponent.getCustomerLoanDto();
                    logger.info("DOB=======" + dob);
                    customerLoanDto.setDob(dob);
                    logger.info("Name is ============" + fullName);
                    customerLoanDto.setFullName(fullName);
                    customerLoanDto.setGender(gender);
                    customerLoanDto.setNationality(nationality);
                    customerAccountComponent.setCustomerLoanDto(customerLoanDto);
                    logger.info("Name from acc dtl is=======" + customerAccountComponent.getCustomerLoanDto().getFullName());
                } else {
                    CustomerAccountDto customerAccountDto = customerAccountComponent.getCustomerAccountDto();
                    logger.info("DOB=======" + dob);
                    customerAccountDto.setDob(dob);
                    logger.info("Name is ============" + fullName);
                    customerAccountDto.setFullName(fullName);
                    customerAccountDto.setGender(gender);
                    customerAccountDto.setNationality(nationality);
                    customerAccountComponent.setCustomerAccountDto(customerAccountDto);
                    logger.info("Name from acc dtl is=======" + customerAccountComponent.getCustomerAccountDto().getFullName());
                }

            }

        } catch (IOException ex) {
            Logger.getLogger(VidyoAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void handleSaveAddProofFromSnap() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();
        ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String deploymentDirectoryPath = ctx.getRealPath("/");
        try {
            CallMst callMster = (CallMst) session.getAttribute("callMst");
            if (null != callMster) {
                Random rand = new Random();
                int no = rand.nextInt(1000) + 1;
                CustomerMst cm = customerMstService.findCustomerMstByCustId(callMster.getCustId());
                BufferedImage image;
                image = this.getImageFromSnapShot(this.binaryImage);

// write the image to a file
                String imagePath = no + "snapshot.jpg";
                String tempFilePath = File.separator + "File_Upload" + File.separator + "Call" + File.separator
                        + callMster.getId() + File.separator + imagePath;

                File ff1 = new File(deploymentDirectoryPath + "/resources" + tempFilePath);
                File n = new File(deploymentDirectoryPath + "/resources" + File.separator + "File_Upload" + File.separator + "Call" + File.separator
                        + callMster.getId());
                if (!n.exists()) {
                    n.mkdirs();
                }
                //File outputfile = new File("E:\\Learning\\image3.jpg");
                ImageIO.write(image, "png", ff1);
                String website = request.getRequestURL().substring(0, request.getRequestURL().indexOf("/", 9));
                logger.info("::::::::IMAGE PATH::::::::::::" + website + request.getContextPath() + "resources" + tempFilePath);
                customerAccountComponent.setUploadedAddProof(website + request.getContextPath() + "/resources/File_Upload/Call/" + callMster.getId() + "/" + imagePath);
                logger.info("::::::::IMAGE PATH AFTER SUBSTRING::::::::::::" + customerAccountComponent.getUploadedAddProof().substring(customerAccountComponent.getUploadedAddProof().indexOf(File.separator)));

                String jbossHome = System.getenv("JBOSS_HOME");

                String path = jbossHome + File.separator + "standalone" + tempFilePath;
                n = new File(jbossHome + File.separator + "standalone" + File.separator + "File_Upload" + File.separator + "Call" + File.separator
                        + callMster.getId());
                if (!n.exists()) {
                    n.mkdirs();
                }
                File ff2 = new File(path);
                ImageIO.write(image, "png", ff2);

                String address = "";

                logger.info("Inside file upload for scan");
                boolean valid = true;

                if (valid) {

                    try {
                        File ff = new File(deploymentDirectoryPath + "/resources" + tempFilePath);

                        HttpClient httpclient = new DefaultHttpClient();
                        HttpPost httpPost = new HttpPost(Constants.ocrUrl + "getAddressSnapshot");
                        //httpPost.addHeader("Content-type", "multipart/form-data");

                        FileBody uploadFilePart = new FileBody(ff);
                        MultipartEntity reqEntity = new MultipartEntity();
                        reqEntity.addPart("file", uploadFilePart);
                        httpPost.setEntity(reqEntity);

                        HttpResponse response1 = httpclient.execute(httpPost);
                        HttpEntity responseEntity = response1.getEntity();
                        String response = "Failure";
                        if (responseEntity != null) {
                            response = EntityUtils.toString(responseEntity);
                        }
                        logger.info("Response==========" + response);

                        JSONObject jsonObject;
                        try {
                            jsonObject = new JSONObject(response);
                            logger.info("Address IS====" + jsonObject.getString("Address").replace("\n", " "));

                            address = jsonObject.getString("Address").replace("\n", " ");

                        } catch (JSONException ex) {
                            Logger.getLogger(VidyoAgent.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    } catch (IOException e) {
                        logger.error("ERROR ISSSSSSSSSSSSSSSSSSS====" + e);
                    }

                }

                if (null != cm.getAccountNo()) {
                    CustomerLoanDto customerLoanDto = customerAccountComponent.getCustomerLoanDto();
                    customerLoanDto.setAddress(address);
                    customerAccountComponent.setCustomerLoanDto(customerLoanDto);
                    logger.info("Address from acc dtl is=======" + customerAccountComponent.getCustomerLoanDto().getAddress());
                } else {
                    CustomerAccountDto customerAccountDto = customerAccountComponent.getCustomerAccountDto();
                    customerAccountDto.setAddress(address);
                    customerAccountComponent.setCustomerAccountDto(customerAccountDto);
                    logger.info("Address from acc dtl is=======" + customerAccountComponent.getCustomerAccountDto().getAddress());
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(VidyoAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("SleepWhileInLoop")
    public void checkReceiveIdCardFromCust(HttpServletRequest request) {
        HttpSession session = request.getSession();
        boolean valid = true;
        try {
            Thread.sleep(8000);
        } catch (InterruptedException ex) {
            Logger.getLogger(VidyoAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
        CallMst callMster = (CallMst) session.getAttribute("callMst");
        ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String deploymentDirectoryPath = ctx.getRealPath("/");
        
        String uploadedPan=deploymentDirectoryPath + "/resources" + File.separator + "File_Upload" + File.separator + "Call" + File.separator
                                            + callMster.getId() + File.separator + this.idCardFromCust;

        //String[] imgpath = this.idCardFromCust.split("@");
        //String imagePath = imgpath[1];
        String website = request.getRequestURL().substring(0, request.getRequestURL().indexOf("/", 9));

        ThreadSafeSimpleDateFormat dateFormat = new ThreadSafeSimpleDateFormat("dd/MM/yyyy");
        Date dob = new Date();
        String fullName = "";

        String father = "";
        String panNumber = "";
        String imageFromPan = "";
        String signatureFromPan = "";

        logger.info("Inside file upload for scan");

        if (valid) {
            if (null != callMster) {
                customerAccountComponent.setUploadedNationalId(website + request.getContextPath() + "/resources/File_Upload/Call/" + callMster.getId() + "/" + this.idCardFromCust);
                CustomerMst cm = customerMstService.findCustomerMstByCustId(callMster.getCustId());

                //for (int i = 0; i <= 5; i++) {
                    try {
                        //String uploadedPan=website + request.getContextPath() + "/resources/File_Upload/Call/" + callMster.getId() + "/" + this.idCardFromCust;
                        logger.info("imagePath===="+this.idCardFromCust);
                        File ff = new File(uploadedPan);
                        HttpClient httpclient = new DefaultHttpClient();
                        HttpPost httpPost = new HttpPost(Constants.ocrUrl + "task/detect");
//                        httpPost.addHeader("Content-type", "multipart/form-data");
                        //StringBody langBody = new StringBody(LANG , ContentType.TEXT_PLAIN);
                        FileBody uploadFilePart = new FileBody(ff);
                        logger.info("imagePath==11=="+uploadedPan);
                        logger.info("Api Url=="+Constants.ocrUrl+ "task/detect");
                        logger.info("Api ID=="+Constants.appId);
                        MultipartEntity reqEntity = new MultipartEntity();
                        reqEntity.addPart("docImg", uploadFilePart);
                        reqEntity.addPart("app_id", new StringBody(Constants.appId));
                        reqEntity.addPart("extract", new StringBody("true"));
                        reqEntity.addPart("type", new StringBody("pan"));
                        
                        httpPost.setEntity(reqEntity);
                        logger.info("imagePath==22=="+uploadedPan);

                        HttpResponse response1 = httpclient.execute(httpPost);
                        HttpEntity responseEntity = response1.getEntity();
                        logger.info("imagePath=33==="+uploadedPan);
                        String response = "Failure";
                        if (responseEntity != null) {
                            logger.info("imagePath==44=="+uploadedPan);
                            response = EntityUtils.toString(responseEntity);
                        }
                        logger.info("Response==========" + response);

                        JSONObject jsonObject;
                        try {
                            jsonObject = new JSONObject(response);
                            if (jsonObject.getBoolean("success")) {
                                JSONObject jsObject = new JSONObject(jsonObject.getString("data"));
                                JSONObject resultObject = new JSONObject(jsObject.getString("result"));
                                JSONObject dobObject = new JSONObject(resultObject.getString("dob"));

                                try {
                                    logger.info("Value is==" + dateFormat.parse(dobObject.getString("value")));
                                    if (dobObject.getString("value").contains("\\n\\f")) {
                                        dob = dateFormat.parse(dobObject.getString("value").replace("\\n\\f", ""));
                                    } else {
                                        dob = dateFormat.parse(dobObject.getString("value"));
                                    }
                                } catch (ParseException | JSONException e) {
                                    logger.error("Error==" + e.getMessage());
                                }

                                JSONObject nameObject = new JSONObject(resultObject.getString("name"));

                                if (nameObject.getString("value").contains("\\n\\f")) {
                                    fullName = nameObject.getString("value").replace("\\n\\f", "");
                                } else {
                                    fullName = nameObject.getString("value");
                                }

                                JSONObject panObject = new JSONObject(resultObject.getString("pan"));

                                if (panObject.getString("value").contains("\\n\\f")) {
                                    panNumber = panObject.getString("value").replace("\\n\\f", "");
                                } else {
                                    panNumber = panObject.getString("value");
                                }

                                JSONObject fatherObject = new JSONObject(resultObject.getString("father"));

                                if (fatherObject.getString("value").contains("\\n\\f")) {
                                    father = fatherObject.getString("value").replace("\\n\\f", "");
                                } else {
                                    father = fatherObject.getString("value");
                                }

                                JSONObject imageObject = new JSONObject(resultObject.getString("photo"));

                                imageFromPan = Constants.ocrUrl + imageObject.getString("source").substring(1);

                                JSONObject signObject = new JSONObject(resultObject.getString("sign"));

                                signatureFromPan = Constants.ocrUrl + signObject.getString("source").substring(1);

                            }

                        } catch (JSONException ex) {
                            Logger.getLogger(VidyoAgent.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    } catch (IOException e) {
                        logger.error("ERROR ISSSSSSSSSSSSSSSSSSS====" + e);
                    }
//                    if (!fullName.equals("")) {
//                        break;
//                    }
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(VidyoAgent.class.getName()).log(Level.SEVERE, null, ex);
                    }
                //}
                if (null != cm.getAccountNo()) {
                    CustomerLoanDto customerLoanDto = customerAccountComponent.getCustomerLoanDto();
                    logger.info("LOAN DOB=======" + dob);
                    customerLoanDto.setDob(dob);
                    logger.info("LOAN Name is ============" + fullName);
                    customerLoanDto.setFullName(fullName);
                    customerAccountComponent.setCustomerLoanDto(customerLoanDto);
                    logger.info("LOAN Name from acc dtl is=======" + customerAccountComponent.getCustomerLoanDto().getFullName());
                } else {
                    CustomerAccountDto customerAccountDto = customerAccountComponent.getCustomerAccountDto();
                    logger.info("DOB=======" + dob);
                    customerAccountDto.setDob(dob);
                    logger.info("Name is ============" + fullName);
                    customerAccountDto.setFullName(fullName);
                    logger.info("father is ============" + father);
                    customerAccountDto.setFather(father);
                    logger.info("pan is ============" + panNumber);
                    customerAccountDto.setPanNumber(panNumber);
                    logger.info("image is ============" + imageFromPan);
                    customerAccountDto.setImageFromPan(imageFromPan);
                    customerAccountComponent.setFaceFromPanCard(imageFromPan);
                    logger.info("Sign is ============" + signatureFromPan);
                    customerAccountDto.setSignatureFromPan(signatureFromPan);
                    customerAccountComponent.setSignFromPanCard(signatureFromPan);
                    customerAccountComponent.setCustomerAccountDto(customerAccountDto);
                    logger.info("Name from acc dtl is=======" + customerAccountComponent.getCustomerAccountDto().getFullName());
                }
            }
        }

    }

    public void handleFaceVerifyFromPan() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();
        ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String deploymentDirectoryPath = ctx.getRealPath("/")+"/";
        try {
            CallMst callMster = (CallMst) session.getAttribute("callMst");
            if (null != callMster) {
                CustomerMst cm = customerMstService.findCustomerMstByCustId(callMster.getCustId());
                BufferedImage image = this.getImageFromSnapShot(this.binaryImage);

                String file11 = customerAccountComponent.getSnapImage();
                String file22 = customerAccountComponent.getUploadedNationalId();
                String file1 = file11.substring(file11.indexOf(request.getContextPath()) + request.getContextPath().length() + 1);
                String file2 = file22.substring(file22.indexOf(request.getContextPath()) + request.getContextPath().length() + 1);
                logger.info("handleFaceVerify:::::::::::::file1" + file1);
                logger.info("handleFaceVerify:::::::::::::file2" + file2);
// write the image to a file
                File ff = new File(deploymentDirectoryPath + file1.replace("/", File.separator));
                File ff1 = new File(deploymentDirectoryPath + file2.replace("/", File.separator));
                logger.info("handleFaceVerify:::::::::::::file1" + file1);
                logger.info("handleFaceVerify:::::::::::::file2" + file2);
                ImageIO.write(image, "png", ff);

                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(Constants.ocrUrl + "verify/face");
                //httpPost.addHeader("Content-type", "multipart/form-data");

                FileBody uploadFilePart = new FileBody(ff);
                FileBody uploadFilePart1 = new FileBody(ff1);
                MultipartEntity reqEntity = new MultipartEntity();
                reqEntity.addPart("image1", uploadFilePart);
                reqEntity.addPart("image2", uploadFilePart1);
                httpPost.setEntity(reqEntity);

                HttpResponse response1 = httpclient.execute(httpPost);
                HttpEntity responseEntity = response1.getEntity();
                String response = "Failure";
                if (responseEntity != null) {
                    response = EntityUtils.toString(responseEntity);
                }
                logger.info("Response==========" + response);

                JSONObject jsonObject;
                try {
                    jsonObject = new JSONObject(response);
                    if (jsonObject.getBoolean("success")) {
                        JSONObject jsObject = new JSONObject(jsonObject.getString("data"));

                        String result = jsObject.getString("confidence");
                        logger.info("Match Result IS====" + jsObject.getString("confidence"));
                        if (!result.contains("IndexError:")) {
                            customerAccountComponent.setMatchresult(result + "%");
                        } else {
                            customerAccountComponent.setMatchresult("Unable to match");
                        }

                    } else {
                        customerAccountComponent.setMatchresult("Unable to match");
                    }
                    RequestContext.getCurrentInstance().execute("window.location.reload()");
                } catch (JSONException ex) {
                    customerAccountComponent.setMatchresult("Unable to match");
                }
            }
        } catch (IOException ex) {
            customerAccountComponent.setMatchresult("Unable to match");
            Logger.getLogger(VidyoAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void checkReceiveAddProofFromCust(HttpServletRequest request) {
        HttpSession session = request.getSession();
        boolean valid = true;

        CallMst callMster = (CallMst) session.getAttribute("callMst");
        String imagePath = this.addProofFromCust;
        String website = request.getRequestURL().substring(0, request.getRequestURL().indexOf("/", 9));
        ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();

        String address;
        String gender;
        String nation;

        logger.info("Inside file upload for scan");

        if (valid) {

            if (null != callMster) {
                customerAccountComponent.setUploadedAddProof(website + request.getContextPath() + "/resources/File_Upload/Call/" + callMster.getId() + "/" + imagePath);
                CustomerMst cm = customerMstService.findCustomerMstByCustId(callMster.getCustId());
                logger.info("address===" + imagePath);
                String[] resp = imagePath.split("@");
                address = resp[1];
                gender = resp[0];
                nation = resp[2];

                if (null != cm.getAccountNo()) {
                    CustomerLoanDto customerLoanDto = customerAccountComponent.getCustomerLoanDto();
                    customerLoanDto.setAddress(address);
                    customerAccountComponent.setCustomerLoanDto(customerLoanDto);
                    logger.info("Address from acc dtl is=======" + customerAccountComponent.getCustomerLoanDto().getAddress());
                } else {
                    CustomerAccountDto customerAccountDto = customerAccountComponent.getCustomerAccountDto();
                    customerAccountDto.setAddress(address);
                    customerAccountDto.setGender(gender);
                    customerAccountDto.setNationality(nation);
                    customerAccountComponent.setCustomerAccountDto(customerAccountDto);
                    logger.info("Address from acc dtl is=======" + customerAccountComponent.getCustomerAccountDto().getAddress());
                }
            }
        }

    }

    public void checkReceiveSelfImageFromCust(HttpServletRequest request) {
        HttpSession session = request.getSession();

        CallMst callMster = (CallMst) session.getAttribute("callMst");
        ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String deploymentDirectoryPath = ctx.getRealPath("/");
        String imagePath = this.selfImageFromCust;
        String website = request.getRequestURL().substring(0, request.getRequestURL().indexOf("/", 9));
        customerAccountComponent.setSnapImage(website + request.getContextPath() + "/resources/File_Upload/Call/" + callMster.getId() + "/" + imagePath);

        String address = "";

        logger.info("Inside file upload for scan");

    }

    public void getCustomerFiles(HttpServletRequest request) {

        HttpSession session = request.getSession();
        String websiteURL = request.getRequestURL().substring(0, request.getRequestURL().indexOf("/", 9));
        customerFileDetailsList = new ArrayList<>();
        userReceivedFiles = new ArrayList<>();
        userSendFiles = new ArrayList<>();
        try {
            CallMst callMster = (CallMst) session.getAttribute("callMst");
            if (null != callMster) {
                CustomerMst cm = customerMstService.findCustomerMstByCustId(callMster.getCustId());
                CustomerDtl cdtl = null;
                if (cm != null && cm.getId() > 0) {
                    List<CallFileUploadDtls> userFileDtsLst = callFileUploadDtlsService.findAllFileByFileReceivedTypeAndId(Constants.USER_TYPE_CUST, cm.getId());
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                    for (CallFileUploadDtls lCallFileDtl : userFileDtsLst) {
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
                            userReceivedFiles.add(lFileReportDto);

                        }

                        if (lCallFileDtl.getFileReceivedbyType().equalsIgnoreCase("Employee")) {
                            EmployeeMst lEmpMst = employeeMstService.findEmployeeMstById(lCallFileDtl.getFileReceivedBy());
                            if (lEmpMst.getMidName() != null && !lEmpMst.getMidName().equals("")) {
                                lFileReportDto.setFileSendTo(lEmpMst.getFirstName() + " " + lEmpMst.getMidName() + " " + lEmpMst.getLastName());
                            } else {
                                lFileReportDto.setFileSendTo(lEmpMst.getFirstName() + " " + lEmpMst.getLastName());
                            }
                            lFileReportDto.setFileSendToType("Employee");
                            userSendFiles.add(lFileReportDto);
                        }

                    }
                    cdtl = customerDtlService.findIDByPhoneNo(cm.getCellPhone().toString());
                }

                if (cdtl != null && cdtl.getId() > 0) {
                    CustomerAccountDto l_customerAccountDto = new CustomerAccountDto();
                    l_customerAccountDto.setCustImage(websiteURL + request.getContextPath() + cdtl.getCustomerImage());
                    l_customerAccountDto.setAddressProof(websiteURL + request.getContextPath() + cdtl.getUtilityBill());
                    l_customerAccountDto.setIdCard(websiteURL + request.getContextPath() + cdtl.getNationalId());
                    customerFileDetailsList.add(l_customerAccountDto);
                }
                this.setFilteredCustomerFileDetailsList(this.customerFileDetailsList);
                RequestContext.getCurrentInstance().execute("$('#spinner').hide()");
                RequestContext.getCurrentInstance().execute("PF('custFileDtlVar').show()");
            }

        } catch (Exception ex) {
            RequestContext.getCurrentInstance().execute("$('#spinner').hide()");
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Not Verified!", ""));
            Logger.getLogger(VidyoAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void customerverification(HttpServletRequest request) {
        HttpSession session = request.getSession();
        EmployeeMst employeeMstLocal = (EmployeeMst) session.getAttribute("ormUserMaster");
        String websiteURL = request.getRequestURL().substring(0, request.getRequestURL().indexOf("/", 9));
        try {
            CallMst callMster = (CallMst) session.getAttribute("callMst");
            if (null != callMster) {
                Random rand = new Random();
                int no = rand.nextInt(1000) + 1;
                CustomerMst cm = customerMstService.findCustomerMstByCustId(callMster.getCustId());
                CustomerDtl cdtl = customerDtlService.findIDByPhoneNo(cm.getCellPhone().toString());
                if (null != cdtl) {
                    CustomerAccDtl accDtl = customerAccDtlService.getAccDtlByCustomerDtl(cdtl);
                    if (null != accDtl) {
                        cm.setAccountNo(accDtl.getAccNo());
                        cm = customerMstService.saveCustomerMst(cm, employeeMstLocal);
                        if (null != cm.getAccountNo()) {
                            String custEmail = cdtl.getEmail();
                            if (null != custEmail) {
                                StringBuffer sb = new StringBuffer(255);
                                sb.append("Dear ").append(cdtl.getFullName());
                                sb.append(", \n\nYour Account has been created successfully ");
                                sb.append("\nFollowing is the detail information of your account\n");
                                sb.append("\nCustomer Id: ").append(cm.getCustId());
                                sb.append("\nPassword: 123");
                                sb.append("\nAccount Number: ").append(accDtl.getAccNo());
                                sb.append("\nAccount Name: ").append(accDtl.getCustomerDtlId().getFullName());
                                sb.append("\nBank Name: ").append(accDtl.getCustomerDtlId().getBankMstId().getBankName());
                                sb.append("\nBank Branch Code: ").append(accDtl.getCustomerDtlId().getBankMstId().getBranchCode());
                                sb.append("\nBank IFSC: ").append(accDtl.getCustomerDtlId().getBankMstId().getIfscCode());
                                sb.append("\nBank Address: ").append(accDtl.getCustomerDtlId().getBankMstId().getAddress());
                                sb.append("\nCreated On: ").append(accDtl.getEffectiveDate().toString());

                                sb.append(SendingMailUtil.TELE_THX);
                                try {
                                    boolean mailSend = SendingMailUtil.sendMail(custEmail, sb, SendingMailUtil.NEW_CUSTOMER);
                                    if (mailSend) {
                                        logger.info("mail sending was successfull... to Customer" + custEmail);
                                    }
                                } catch (Exception ex) {
                                    logger.error("Sending Email Error:" + ex.getMessage());
                                }
                                List<EmployeeMst> employeeMstList = employeeMstService.findEmployeeByEmpTypeId(employeeTypeMstService.findEmployeeTypeByEmployeeTypeName("ChequeBookManager"));
                                for (EmployeeMst em : employeeMstList) {
                                    sb = new StringBuffer(255);
                                    sb.append("Dear ").append(em.getFirstName());
                                    sb.append(",\n\nMr. ").append(cdtl.getFullName()).append(" have successfully open an account with CCMS. Requesting you to start the cheque book printing and dispatch procedure. \n");
                                    sb.append("\nPlease click to the following link to view full details of the customer for cheque book printing and dispatch procedure.\n");
                                    sb.append(websiteURL).append(request.getContextPath()).append("/faces/customerList.xhtml?id=").append(em.getId()).append("\n\n");
                                    sb.append(SendingMailUtil.TELE_THX);

                                    boolean mailSend = SendingMailUtil.sendMail(em.getEmail(), sb, SendingMailUtil.NEW_CUSTOMER);
                                    if (mailSend) {
                                        logger.info("mail sending was successfull... to Cheque Book Manager" + em.getEmail());
                                    }
                                }
                                employeeMstList = employeeMstService.findEmployeeByEmpTypeId(employeeTypeMstService.findEmployeeTypeByEmployeeTypeName("DebitCardManager"));
                                for (EmployeeMst em : employeeMstList) {
                                    sb = new StringBuffer(255);
                                    sb.append("Dear ").append(em.getFirstName());
                                    sb.append(",\n\nMr. ").append(cdtl.getFullName()).append(" have successfully open an account with CCMS. Requesting you to start the debit card printing and dispatch procedure. \n");
                                    sb.append("\nPlease click to the following link to view full details of the customer for cheque book printing and dispatch procedure.\n");
                                    sb.append(websiteURL).append(request.getContextPath()).append("/faces/customerList.xhtml?id=").append(em.getId()).append("\n\n");
                                    sb.append(SendingMailUtil.TELE_THX);

                                    try {
                                        boolean mailSend = SendingMailUtil.sendMail(em.getEmail(), sb, SendingMailUtil.NEW_CUSTOMER);
                                        if (mailSend) {
                                            logger.info("mail sending was successfull... to Cheque Book Manager" + em.getEmail());
                                        }
                                    } catch (Exception ex) {
                                        logger.error("Sending Email Error:" + ex.getMessage());
                                        FacesContext.getCurrentInstance().addMessage(null,
                                                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error in sending mail!", ""));
                                    }
                                }
                                SocketMessage.send(callScheduler.getAdminSocket(), callMster.getCustId(), "accountOpen#success");
                                RequestContext.getCurrentInstance().execute("$('#spinner').hide()");
                                RequestContext.getCurrentInstance().execute("PF('verifyCustvar').show();");
                            } else {
                                RequestContext.getCurrentInstance().execute("$('#spinner').hide()");
                                FacesContext.getCurrentInstance().addMessage(null,
                                        new FacesMessage(FacesMessage.SEVERITY_WARN, "No mail address has found", ""));
                            }

                        }
                    }
                }

            }

        } catch (Exception ex) {
            RequestContext.getCurrentInstance().execute("$('#spinner').hide()");
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Not Verified!", ""));
            Logger.getLogger(VidyoAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void loanCustomerverification(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String websiteURL = request.getRequestURL().substring(0, request.getRequestURL().indexOf("/", 9));
        try {
            CallMst callMster = (CallMst) session.getAttribute("callMst");
            if (null != callMster) {
                Random rand = new Random();
                int no = rand.nextInt(1000) + 1;
                CustomerMst cm = customerMstService.findCustomerMstByCustId(callMster.getCustId());
                CustomerLoanDtl cdtl = customerLoanDtlService.findByID(this.getLoanCustomerId());
                if (null != cdtl) {
                    cdtl.setLoanStatus("Verification Done");
                    cdtl.setLoanNo("loan" + no);
                    cdtl = customerLoanDtlService.saveCustomerLoanDtl(cdtl);
                    if (null != cm.getAccountNo()) {
                        String custEmail = cdtl.getEmail();
                        if (null != custEmail) {
                            StringBuffer sb = new StringBuffer(255);
                            sb.append("Dear ").append(cdtl.getFullName());
                            sb.append(", \n\nYour Loan is approved successfully ");
                            sb.append("\nFollowing is the detail information of your loan\n");
                            sb.append("\nCustomer Id: ").append(cm.getCustId());
                            sb.append("\nAccount Number: ").append(cdtl.getAccountNumber());
                            sb.append("\nLoan Number: ").append(cdtl.getLoanNo());

                            sb.append(SendingMailUtil.TELE_THX);
                            try {
                                boolean mailSend = SendingMailUtil.sendMail(custEmail, sb, SendingMailUtil.LOAN_APPROVE);
                                if (mailSend) {
                                    logger.info("mail sending was successfull... to Customer" + custEmail);
                                }
                            } catch (Exception ex) {
                                logger.error("Sending Email Error:" + ex.getMessage());
                                FacesContext.getCurrentInstance().addMessage(null,
                                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error in sending mail!", ""));
                            }

                            SocketMessage.send(callScheduler.getAdminSocket(), callMster.getCustId(), "loanApproved#success");
                            RequestContext.getCurrentInstance().execute("$('#spinner').hide()");
                            RequestContext.getCurrentInstance().execute("PF('verifyLoanvar').show();");
                        } else {
                            RequestContext.getCurrentInstance().execute("$('#spinner').hide()");
                            FacesContext.getCurrentInstance().addMessage(null,
                                    new FacesMessage(FacesMessage.SEVERITY_WARN, "No mail address has found", ""));
                        }
                    }
                }
            }

        } catch (Exception ex) {
            RequestContext.getCurrentInstance().execute("$('#spinner').hide()");
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Not Verified!", ""));
            Logger.getLogger(VidyoAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void handleFaceVerify() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();
        ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String deploymentDirectoryPath = ctx.getRealPath("/");
        try {
            CallMst callMster = (CallMst) session.getAttribute("callMst");
            if (null != callMster) {
                CustomerMst cm = customerMstService.findCustomerMstByCustId(callMster.getCustId());
                BufferedImage image = this.getImageFromSnapShot(this.binaryImage);

                String file11 = customerAccountComponent.getSnapImage();
                String file22 = customerAccountComponent.getNewSnapImage();
                String file1 = file11.substring(file11.indexOf(request.getContextPath()) + request.getContextPath().length() + 1);
                String file2 = file22.substring(file22.indexOf(request.getContextPath()) + request.getContextPath().length() + 1);
                logger.info("handleFaceVerify:::::::::::::file1" + file1);
                logger.info("handleFaceVerify:::::::::::::file2" + file2);
// write the image to a file
                File ff = new File(deploymentDirectoryPath + file1.replace("/", File.separator));
                File ff1 = new File(deploymentDirectoryPath + file2.replace("/", File.separator));
                logger.info("handleFaceVerify:::::::::::::file1" + file1);
                logger.info("handleFaceVerify:::::::::::::file2" + file2);
                ImageIO.write(image, "png", ff);

                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(Constants.ocrUrl + "getFaceResult");
                //httpPost.addHeader("Content-type", "multipart/form-data");

                FileBody uploadFilePart = new FileBody(ff);
                FileBody uploadFilePart1 = new FileBody(ff1);
                MultipartEntity reqEntity = new MultipartEntity();
                reqEntity.addPart("image1", uploadFilePart);
                reqEntity.addPart("image2", uploadFilePart1);
                httpPost.setEntity(reqEntity);

                HttpResponse response1 = httpclient.execute(httpPost);
                HttpEntity responseEntity = response1.getEntity();
                String response = "Failure";
                if (responseEntity != null) {
                    response = EntityUtils.toString(responseEntity);
                }
                logger.info("Response==========" + response);

                JSONObject jsonObject;
                try {
                    jsonObject = new JSONObject(response);
                    logger.info("Match Result IS====" + jsonObject.getString("result"));
                    String result = jsonObject.getString("result");
                    RequestContext.getCurrentInstance().execute("$('#spinner').hide()");
                    if (!result.contains("IndexError:")) {
                        if (Integer.parseInt(result) > 50) {
                            RequestContext.getCurrentInstance().execute("PF('verifyFacevar').show();");
                        } else {
                            RequestContext.getCurrentInstance().execute("PF('verifyFacenotvar').show();");
                        }
                    } else {
                        RequestContext.getCurrentInstance().execute("PF('verifyFacenotvar').show();");
                    }

                } catch (JSONException ex) {
                    RequestContext.getCurrentInstance().execute("$('#spinner').hide()");
                    RequestContext.getCurrentInstance().execute("PF('verifyFacenotvar').show();");
                    Logger.getLogger(VidyoAgent.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (IOException ex) {
            RequestContext.getCurrentInstance().execute("$('#spinner').hide()");
            Logger.getLogger(VidyoAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void handleIDCardVerify() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();
        ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String deploymentDirectoryPath = ctx.getRealPath("/");
        ThreadSafeSimpleDateFormat dateFormat = new ThreadSafeSimpleDateFormat("yyMMdd");
        Date dob = new Date();
        String fullName = "";
        String gender = "";
        String nationality = "";
        try {
            CallMst callMster = (CallMst) session.getAttribute("callMst");
            if (null != callMster) {
                CustomerMst cm = customerMstService.findCustomerMstByCustId(callMster.getCustId());
                CustomerDtl customerDtl = customerDtlService.findIDByPhoneNo(cm.getCellPhone().toString());
                if (null != customerDtl) {
                    BufferedImage image = this.getImageFromSnapShot(this.binaryImage);
                    //String file1 = customerAccountComponent.getSnapImage();
                    String file22 = customerAccountComponent.getUploadedNationalId();
                    String file2 = file22.substring(file22.indexOf(request.getContextPath()) + request.getContextPath().length() + 1);

// write the image to a file
                    try {
                        //File ff = new File(deploymentDirectoryPath + file1.substring(file1.lastIndexOf("/")));
                        logger.info("handleIDCardVerify:::::::::::::file2" + file2);
                        // write the image to a file
                        File ff = new File(deploymentDirectoryPath + file2.replace("/", File.separator));
                        //ImageIO.write(image, "png", ff);

                        HttpClient httpclient = new DefaultHttpClient();
                        HttpPost httpPost = new HttpPost(Constants.ocrUrl + "getTextSnapshot");
                        //httpPost.addHeader("Content-type", "multipart/form-data");

                        FileBody uploadFilePart = new FileBody(ff);
                        MultipartEntity reqEntity = new MultipartEntity();
                        reqEntity.addPart("file", uploadFilePart);
                        httpPost.setEntity(reqEntity);

                        HttpResponse response1 = httpclient.execute(httpPost);
                        HttpEntity responseEntity = response1.getEntity();
                        String response = "Failure";
                        if (responseEntity != null) {
                            response = EntityUtils.toString(responseEntity);
                        }
                        logger.info("Response==========" + response);

                        JSONObject jsonObject;
                        try {
                            jsonObject = new JSONObject(response);
                            logger.info("NAME IS====" + jsonObject.getString("name"));

                            String[] jsonarray = jsonObject.getString("others").split(",");
                            Map<String, String> outputss = new HashMap<>();
                            for (String jsonarray1 : jsonarray) {
                                String[] othersval = jsonarray1.trim().split(":");
                                String val = "";
                                if (othersval.length > 1) {
                                    val = othersval[1];
                                }
                                if (!val.equals("") && !val.equals("others")) {
                                    Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
                                    Matcher m = p.matcher(val);
                                    if (!m.find()) {
                                        outputss.put(othersval[0], val);
                                        if (othersval[0].equalsIgnoreCase("Name")) {
                                            logger.info("Value is==" + val);
                                            fullName = val;
                                        } else if (othersval[0].equalsIgnoreCase("Nationality")) {
                                            logger.info("Value is==" + val);
                                            nationality = val;
                                        } else if (othersval[0].equalsIgnoreCase("Sex")) {
                                            logger.info("Value is==" + val);
                                            if (val.equals("Male")) {
                                                gender = "M";
                                            } else {
                                                gender = "F";
                                            }
                                        } else if (othersval[0].equalsIgnoreCase("DOB")) {
                                            if (!val.equals("Not Available")) {
                                                logger.info("Value is==" + dateFormat.parse(val));
                                                dob = dateFormat.parse(val);
                                            }
                                        }

                                    }
                                }
                            }
                            logger.info("Others========" + outputss);

                        } catch (JSONException | ParseException ex) {
                            Logger.getLogger(VidyoAgent.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    } catch (IOException e) {
                        RequestContext.getCurrentInstance().execute("$('#spinner').hide()");
                        logger.error("ERROR ISSSSSSSSSSSSSSSSSSS====" + e);
                    }
                    Integer addMatch = 0;
                    if (!fullName.trim().equals("")) {
                        addMatch = lock_match(fullName, customerDtl.getAddress());
                    }
                    if (addMatch > 80) {
                        matchResultText = "The documents matched with each Other.";
                    } else if (addMatch < 80 && addMatch > 50) {
                        matchResultText = "The documents doesn't matched with each Other fully You may manually verify it.";
                    } else {
                        matchResultText = "Require Manual Verification.";
                    }
                    RequestContext.getCurrentInstance().execute("$('#spinner').hide();");
                    RequestContext.getCurrentInstance().update("verifyIdCardForm");
                    RequestContext.getCurrentInstance().execute("PF('verifyIdCardvar').show();");
                }
            }
        } catch (Exception ex) {
            RequestContext.getCurrentInstance().execute("$('#spinner').hide()");
            Logger.getLogger(VidyoAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void handleAddProofVerify() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();
        ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String deploymentDirectoryPath = ctx.getRealPath("/");

        String address = "";
        try {
            CallMst callMster = (CallMst) session.getAttribute("callMst");
            if (null != callMster) {
                CustomerMst cm = customerMstService.findCustomerMstByCustId(callMster.getCustId());
                CustomerDtl customerDtl = customerDtlService.findIDByPhoneNo(cm.getCellPhone().toString());
                if (null != customerDtl) {
                    BufferedImage image = this.getImageFromSnapShot(this.binaryImage);

                    String file22 = customerAccountComponent.getUploadedAddProof();
                    String file2 = file22.substring(file22.indexOf(request.getContextPath()) + request.getContextPath().length() + 1);

// write the image to a file
                    //File ff = new File(deploymentDirectoryPath + file1.substring(file1.lastIndexOf("/")));
                    //ImageIO.write(image, "png", ff);
                    try {
                        logger.info("handleAddProofVerify:::::::::::::file2" + file2);
                        // write the image to a file
                        File ff = new File(deploymentDirectoryPath + file2.replace("/", File.separator));

                        HttpClient httpclient = new DefaultHttpClient();
                        HttpPost httpPost = new HttpPost(Constants.ocrUrl + "getAddressSnapshot");
                        //httpPost.addHeader("Content-type", "multipart/form-data");

                        FileBody uploadFilePart = new FileBody(ff);
                        MultipartEntity reqEntity = new MultipartEntity();
                        reqEntity.addPart("file", uploadFilePart);
                        httpPost.setEntity(reqEntity);

                        HttpResponse response1 = httpclient.execute(httpPost);
                        HttpEntity responseEntity = response1.getEntity();
                        String response = "Failure";
                        if (responseEntity != null) {
                            response = EntityUtils.toString(responseEntity);
                        }
                        logger.info("Response==========" + response);

                        JSONObject jsonObject;
                        try {
                            jsonObject = new JSONObject(response);
                            logger.info("Address IS====" + jsonObject.getString("Address").replace("\n", " "));

                            address = jsonObject.getString("Address").replace("\n", " ");

                        } catch (JSONException ex) {
                            Logger.getLogger(VidyoAgent.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    } catch (IOException e) {
                        RequestContext.getCurrentInstance().execute("$('#spinner').hide()");
                        logger.error("ERROR ISSSSSSSSSSSSSSSSSSS====" + e);
                    }
                    Integer addMatch = 0;
                    if (!address.trim().equals("")) {
                        addMatch = lock_match(address, customerDtl.getAddress());

                    }
                    logger.info("Address Mathe result====" + addMatch);
                    if (addMatch > 50) {
                        matchResultText = "Documents matched.";
                    } else if (addMatch < 50 && addMatch > 30) {
                        matchResultText = "Documents doesn't matched fully, You may manually verify it.";
                    } else {
                        matchResultText = "Require Manual Verification.";
                    }
                    RequestContext.getCurrentInstance().execute("$('#spinner').hide();");
                    RequestContext.getCurrentInstance().update("verifyAddForm");
                    RequestContext.getCurrentInstance().execute("PF('verifyAddvar').show();");
                }
            }
        } catch (Exception ex) {
            RequestContext.getCurrentInstance().execute("$('#spinner').hide()");
            Logger.getLogger(VidyoAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void refrashOnBoardForm() {

    }

    public void getCustomerForm(HttpServletRequest request) {
        logger.info("aaaaaaaaaaa");
        CustomerDtl customerDtl;
        //customerAccountDto = getCustomerAccountDto();
        try {
            HttpSession session = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession();
            //Get Customer master from session
            logger.info("bbbbbbbbbbbbbbbb");
            CallMst callMst = (CallMst) session.getAttribute("callMst");
            CustomerMst customerMstL = customerMstService.findCustomerMstById(callMst.getCustomerId().getId());
            logger.info("ccccccccccc");
            if (null != customerMstL.getAccountNo() && !customerMstL.getAccountNo().equals("")) {
                logger.info("dddddddddddddddd");
                //this.customerFormPdfPath = customerMstL.getAccountNo() + ".pdf";
                request.getSession().setAttribute("formPdfPath", customerMstL.getAccountNo() + ".pdf");
                RequestContext.getCurrentInstance().update("downloadPDFId");
            } else {
                logger.info("eeeeeeeeeeeeeee customerMst==" + customerMstL);
                //Get Customer Detail by email id of Customer assuming they are same.
                customerDtl = customerDtlService.findIDByPhoneNo(customerMstL.getCellPhone().toString());

                logger.info("eeeeeeeeeeeeeee customerDtl==" + customerDtl);
                CustomerAccDtl customerAccDtl = customerAccDtlService.getAccDtlByCustomerDtl(customerDtl);
                if (null != customerAccDtl) {
                    //this.customerFormPdfPath = customerAccDtl.getAccNo() + ".pdf";
                    logger.info("eeeeeeeeeeeeeee customerAccDtl==" + customerAccDtl);
                    request.getSession().setAttribute("formPdfPath", customerAccDtl.getAccNo() + ".pdf");
                    RequestContext.getCurrentInstance().update("downloadPDFId");
                }

            }
        } catch (Exception e) {
            logger.error(e);
        }
    }

    public void handleDocFileUpload(FileUploadEvent event) {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession();
        boolean valid = true;
        UploadedFile uploadedfile = event.getFile();
        BufferedOutputStream outputStream;
        FileOutputStream fos;
        BufferedOutputStream outputStream2;
        FileOutputStream fos2;
        boolean flag = false;

        CallMst callMster = (CallMst) session.getAttribute("callMst");
        EmployeeMst CurrEmp = (EmployeeMst) session.getAttribute("ormUserMaster");
        if (null != callMster) {
            callMster = callMasterService.findCallMstById(callMster.getId());
        }
        if (uploadedfile == null || uploadedfile.getSize() == 0) {
            valid = false;
            fileUploadMsg = "Please select file";

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(fileUploadMsg));

        } else if (uploadedfile.getFileName() == null || uploadedfile.getFileName().equalsIgnoreCase("") || uploadedfile.getFileName().isEmpty()) {
            valid = false;
            fileUploadMsg = "File Type Not Supported. Please select correct file.";

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(fileUploadMsg));

        } else {

            String fn = uploadedfile.getFileName();
            String parts[] = fn.split(Pattern.quote("."));
            String ext = parts[1];

            if (ext.equalsIgnoreCase("doc") || ext.equalsIgnoreCase("docx") || ext.equalsIgnoreCase("pdf") || ext.equalsIgnoreCase("txt") || ext.equalsIgnoreCase("xls") || ext.equalsIgnoreCase("xlsx") || ext.equalsIgnoreCase("rtf") || ext.equalsIgnoreCase("ppt") || ext.equalsIgnoreCase("pptx")) {

                if (uploadedfile.getSize() > 5242880) {
                    valid = false;
                    fileUploadMsg = "Please upload document of size less than 5MB.";

                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(fileUploadMsg));
                }
            } else {
                valid = false;
                fileUploadMsg = "File extension not supported";

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(fileUploadMsg));
            }

        }
        if (valid) {
            upFile = uploadedfile;
            byte[] fileContentInByte;
            fileContentInByte = upFile.getContents();
            if (null != callMster && null != callMster.getCustId() && !"".equals(callMster.getCustId())) {
                ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
                String deploymentDirectoryPath = ctx.getRealPath("/");
                String jbossHome = System.getenv("JBOSS_HOME");
                Random rand = new Random();
                int no = rand.nextInt(1000) + 1;
                String path = jbossHome + File.separator + "standalone" + File.separator + "tmp" + File.separator + "vfs" + File.separator + no + upFile.getFileName();

                try {
                    fos = new FileOutputStream(deploymentDirectoryPath + File.separator + no + upFile.getFileName());
                    outputStream = new BufferedOutputStream(fos);
                    outputStream.write(fileContentInByte);
                    outputStream.close();

                    fos2 = new FileOutputStream(path);
                    outputStream2 = new BufferedOutputStream(fos2);
                    outputStream2.write(fileContentInByte);
                    outputStream2.close();

                    if (flag) {
                        fileUploadMsg = "File Sent SuccessFully";

                        String websiteURL = request.getRequestURL().substring(0, request.getRequestURL().indexOf("/", 9));
                        SocketMessage.send(callScheduler.getAdminSocket(), callMster.getCustId(), "fileUpload#" + callMster.getId() + "#" + websiteURL + request.getContextPath() + File.separator + no + upFile.getFileName());
                        logger.info("===>" + "fileUpload#" + callMster.getId() + "#" + websiteURL + request.getContextPath() + File.separator + no + upFile.getFileName());
                    } else {
                        fileUploadMsg = " File Not Sent SuccessFully";
                    }

                    RequestContext.getCurrentInstance().execute("document.getElementById('uploadpanel').style.display = 'block'");
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

    public void downloadFile(FileDownloadDto fileDownloadDto) {
        try {

            if (fileDownloadDto.getUploadedFilePath() != null && fileDownloadDto.getFileName() != null) {
                newFileMessage = null;
                fileDownloadDto.setDownloadCss("color:green");
                RequestContext.getCurrentInstance().update("imgIworksForm");

                InputStream inputStream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream(fileDownloadDto.getUploadedFilePath());
//                downloadFile = new DefaultStreamedContent(inputStream, new MimetypesFileTypeMap().getContentType(fileDownloadDto.getUploadedFilePath()), fileDownloadDto.getFileName());
                RequestContext.getCurrentInstance().execute("openDocInTab('" + fileDownloadDto.getUploadedFilePath() + "');");

            }
        } catch (Exception e) {
            logger.error("ERROR : ", e);
        }
    }

    public void getPerticipentList(HttpServletRequest request) {

        try {
            CallMst callMster = (CallMst) request.getSession().getAttribute("callMst");
            EmployeeMst em = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
            fileUploadDtoList = new ArrayList<>();
            selectedFileuploadDtoList = new ArrayList<>();
            docTitle = null;

            if (null != callMster) {
                callMster = callMasterService.findCallMstById(callMster.getId());

                PerticipentDto lPerticipentDto = new PerticipentDto();
                if (!callMster.getCallType().equalsIgnoreCase("Dial Call")) {
                    CustomerMst lCustomerMst = customerMstService.findCustomerMstByCustomerId(callMster.getCustId());
                    lPerticipentDto.setId(callMster.getCustomerId().getId());
                    lPerticipentDto.setLoginId(callMster.getCustId());
                    lPerticipentDto.setName(lCustomerMst.getFirstName() + lCustomerMst.getLastName());
                    lPerticipentDto.setParticipantType("Customer");
                    fileUploadDtoList.add(lPerticipentDto);
                } else {
                    EmployeeMst lEmployeeMst;
                    List<CallDtl> dtlList = callDtlService.findCallDtlByCallMasterIdAndCallTypeInfo(callMster.getId(), "Dial");
                    if (!dtlList.isEmpty()) {

                        lEmployeeMst = employeeMstService.findEmployeeMstById(dtlList.get(0).getHandeledById().getId());
                    } else {
                        lEmployeeMst = employeeMstService.findAllEmployeeByUserId(callMster.getCustId());

                    }

                    if (em.getId() != (long) lEmployeeMst.getId()) {

                        lPerticipentDto.setId(lEmployeeMst.getId());

                        lPerticipentDto.setLoginId(lEmployeeMst.getLoginId());
                        String name;
                        if (lEmployeeMst.getMidName() != null && !lEmployeeMst.getMidName().equals("")) {
                            name = lEmployeeMst.getFirstName() + " " + lEmployeeMst.getMidName() + " " + lEmployeeMst.getLastName();
                        } else {
                            name = lEmployeeMst.getFirstName() + " " + lEmployeeMst.getLastName();
                        }
                        lPerticipentDto.setNameAndLoginId(name + "(" + lEmployeeMst.getEmail() + ")");
                        fileUploadDtoList.add(lPerticipentDto);
                    }
                }
                List<CallDtl> findOtherNonEndedCallDetailFromSameCallMst = callDtlService.findNonEndedCallDtlByCallMasterIdAndExcludeCurrentEmployee(callMster.getId(), em.getId());
                for (CallDtl lCallDtl : findOtherNonEndedCallDetailFromSameCallMst) {

                    lPerticipentDto = new PerticipentDto();

                    EmployeeMst lEmployeeMst = employeeMstService.findEmployeeMstById(lCallDtl.getHandeledById().getId());
                    lPerticipentDto.setId(lEmployeeMst.getId());

                    lPerticipentDto.setLoginId(lEmployeeMst.getLoginId());
                    String name;
                    if (lEmployeeMst.getMidName() != null && !lEmployeeMst.getMidName().equals("")) {
                        name = lEmployeeMst.getFirstName() + " " + lEmployeeMst.getMidName() + " " + lEmployeeMst.getLastName();
                    } else {
                        name = lEmployeeMst.getFirstName() + " " + lEmployeeMst.getLastName();
                    }
                    lPerticipentDto.setName(name);
                    lPerticipentDto.setParticipantType(lEmployeeMst.getEmpTypId().getTypeName());
                    fileUploadDtoList.add(lPerticipentDto);
                }
                Set<PerticipentDto> set = new HashSet<>(fileUploadDtoList);
                fileUploadDtoList = new ArrayList<>(set);
                multipleParticipant = !(!fileUploadDtoList.isEmpty() && fileUploadDtoList.size() == 1);
                RequestContext.getCurrentInstance().execute("closeAllDialog();PF('fileUploadVar').show();");
                RequestContext.getCurrentInstance().update("fileUploadVarForm");
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
    }

    public void makeScheduleCall(HttpServletRequest request) {
        logger.info("makeScheduleCall..........");
        try {
            this.renderedMakeScheduleButton = true;
            setServiceTimeText("Please Schedule between " + Constants.RM1_Service_Start_Time + "-" + Constants.RM1_Service_End_Time);

            this.schDateTime = DateValidatorRangeCheck.addTimeInMin(new Date(), 5);
            schEndDateTime = DateValidatorRangeCheck.addTimeInMin(new Date(), 15);
            logger.info("schDateTime:" + schDateTime + "schEndDateTime:" + schEndDateTime);
            RequestContext.getCurrentInstance().execute("PF('doScheduleDialog').show()");
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }

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

    public List<CustomerMst> loadCustomerMstForRM() {

        customerMstList.clear();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        EmployeeMst CurrEmp = (EmployeeMst) request.getSession().getAttribute("ormAgentMaster");
        if (null != CurrEmp) {
            List<CustomerRmMap> mapList = customerRMMapService.getCustomersMappedWithEmployee(CurrEmp.getId());
            logger.info("mapList size : " + mapList.size());
            for (CustomerRmMap map : mapList) {
                CustomerMst cust = customerMstService.findCustomerMstById(map.getCustId().getId());
                customerMstList.add(cust);
            }
        }
        return customerMstList;

    }

    public void makeScheduleCallFromWeb(HttpServletRequest request) {
        final TimeZone timeZoneL = TimeZone.getDefault();

        EmployeeMst CurrEmp = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
        CustomerMst customerMstlocal;
        CategoryMst cat = categoryMstService.findCategoryByCategoryCode("UGR");
        selectedCategoryName = cat.getId();
        LanguageMst langMst = langMstService.findLanguageMstByLanguageCode("ENG");
        selectedLanguageName = langMst.getId();
        this.renderedMakeScheduleButton = false;

        logger.info("selectedCustomerId :" + selectedCustomerId);
        customerMstlocal = customerMstService.findCustomerMstById(selectedCustomerId);

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
            logger.info("DateFormat Exception");
        }

        List<ScheduleCallDto> scheduleCallDtlsDtos = null;
        if (null != CurrEmp) {
            scheduleCallDtlsDtos = callSchedulerService.getScheduledCallDtlsByEmployeeMstID(CurrEmp.getId());
        }

        ScheduleCall scheduleCall = new ScheduleCall();
        ScheduleCall schCall;

        try {

            logger.info("selectedServiceName:" + selectedServiceName);
            logger.info("schDateTime:" + schDateTime);
            logger.info("callType:" + callType + "CM:" + customerMstlocal + "schEndDateTime:" + schEndDateTime);
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
                RequestContext.getCurrentInstance().execute("closeAllDialog();PF('confirmscheduleDlg2').show();");
                return;
            }
            if (scheduleCallDtlsDtos != null && scheduleCallDtlsDtos.size() > 0 && !scheduleCallDtlsDtos.isEmpty()) {
                if (schDateTime != null && schEndDateTime != null) {
                    for (ScheduleCallDto obj : scheduleCallDtlsDtos) {
                        if ((schDateTime.after(obj.getScheduleStartDateTime()) && schDateTime.before(obj.getScheduleEndDateTime())) || schDateTime.equals(obj.getScheduleStartDateTime()) || schDateTime.equals(obj.getScheduleEndDateTime())) {
                            this.scheduleText = "Start Time overlapping with existing Schedule Call..!Please Select another Start Time!";
                            RequestContext.getCurrentInstance().update("confirmscheduleform2");
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR : Please Select Proper End Date Time..!! ", "Please Select Proper Start Date Time..!!"));
                            RequestContext.getCurrentInstance().execute("closeAllDialog();PF('confirmscheduleDlg2').show();");
                            return;

                        }
                        if ((schEndDateTime.after(obj.getScheduleStartDateTime()) && schEndDateTime.before(obj.getScheduleEndDateTime())) || schEndDateTime.equals(obj.getScheduleStartDateTime()) || schEndDateTime.equals(obj.getScheduleEndDateTime())) {
                            this.scheduleText = "End Time overlapping with existing Schedule Call..!Please Select another End Time!";
                            RequestContext.getCurrentInstance().update("confirmscheduleform2");
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR : Please Select Proper End Date Time..!!", "Please Select Proper End Date Time..!!"));
                            RequestContext.getCurrentInstance().execute("closeAllDialog();PF('confirmscheduleDlg2').show();");
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
                    RequestContext.getCurrentInstance().execute("closeAllDialog();PF('confirmscheduleDlg2').show();");
                    return;
                }
            }

            if (schDateTime != null && schEndDateTime != null) {
                if (scheduleStartTimeeee.before(serviceStartTime) || scheduleStartTimeeee.after(serviceEndTime) || scheduleStartTimeeee.equals(serviceEndTime) || scheduleEndTimeeee.after(serviceEndTime)) {
                    this.scheduleText = "Please Schdeule in between Service Time!";
                    RequestContext.getCurrentInstance().update("confirmscheduleform2");
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR : Please Schdeule in between Service Time.!! ", "Please Schdeule in between Service Time..!!"));
                    RequestContext.getCurrentInstance().execute("closeAllDialog();PF('confirmscheduleDlg2').show();");
                    return;

                }

            }

            if (schDateTime.after(new Date())) {
                if (customerMstlocal != null) {

                    //TO-DO validate If already there some schedule with same time slot with particular RM
                    CategoryMst categoryMst = categoryMstService.findCategoryMstById(this.selectedCategoryName);
                    LanguageMst languageMst = langMstService.findAllLanguageMstById(this.selectedLanguageName);
                    ServiceMst serviceMst = serviceMstService.findAllServiceMstById(this.selectedServiceName);

                    scheduleCall.setCreationDatetime(CustomConvert.javaDateToTimeStamp(new Date()));
                    scheduleCall.setExecuteStatus("Scheduled");
                    scheduleCall.setScheduledBy("RM");
                    scheduleCall.setSchedulerId((long) 0);
                    scheduleCall.setScheduleDate(timestamp);
                    scheduleCall.setCustomerId(customerMstlocal);
                    scheduleCall.setCallMedium("WEB");

                    scheduleCall.setCallType(callType);
                    scheduleCall.setEmployeeId(CurrEmp);
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
                    if (null != schCall && null != CurrEmp) {
                        //TO-DO EMAIl CODE.
                        String messageBody;
                        synchronized (schCall) {
                            //Mail Send TO RM
                            messageBody = "<html><body>Dear &nbsp; " + CurrEmp.getFirstName() + " " + CurrEmp.getLastName();
                            messageBody += " ,&nbsp;<br></br><br></br>You have a scheduled meeting with customer " + customerMstlocal.getFirstName() + " " + customerMstlocal.getLastName() + " at " + schDateTime + " .";
                            messageBody += "<br>";
                            messageBody += "<b>Note:</b>Please do not refresh the browser during the call.";
                            messageBody += "<br></br>";
                            messageBody += SendingMailUtil.TELE_THX_HTML;
                            messageBody += "</body><html/>";
                            logger.info("Before Send Email...");
                            boolean mailSend = SendingMailUtil.sendEMail(CurrEmp.getEmail(), messageBody, SendingMailUtil.SCHEDULE_CALL);
                            logger.info("After Send Email...");
                            if (mailSend) {
                                logger.info("mail sending was successfull... to RM:" + CurrEmp.getEmail());
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
                            mailSend = SendingMailUtil.sendEMail(customerMstlocal.getEmail(), messageBody, SendingMailUtil.SCHEDULE_CALL);
                            logger.info("After Send Email...");
                            if (mailSend) {
                                logger.info("mail sending was successfull... to Customer:" + customerMstlocal.getEmail());
                                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Email Send Was Successful", "Send Successful!!"));
                            }
                        }
                        this.scheduleText = "Your call has been scheduled successfully";
                        RequestContext.getCurrentInstance().update("confirmscheduleform");
                        RequestContext.getCurrentInstance().execute("PF('doScheduleDialog').hide();");
                        RequestContext.getCurrentInstance().execute("closeAllDialog();PF('confirmscheduleDlg').show();");

                    } else {

                        this.scheduleText = "Call Scheduleding Failed";
                        RequestContext.getCurrentInstance().execute("PF('confirmscheduleDlg').show();hideLoaderSpinner();");
                    }
                }
            } else {

                this.scheduleText = "Select valid Date and time";
                RequestContext.getCurrentInstance().update("confirmscheduleform2");
                RequestContext.getCurrentInstance().execute("closeAllDialog();PF('confirmscheduleDlg2').show();");
            }

        } catch (ParseException | NumberFormatException e) {

        } catch (Exception e) {
            logger.error("Eroor:" + e);
            this.scheduleText = "Some Error Occured, Please try again";
            RequestContext.getCurrentInstance().update("confirmscheduleform2");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR : ", scheduleText));
            RequestContext.getCurrentInstance().execute("closeAllDialog();PF('confirmscheduleDlg2').show();");

        }

    }

    public void doScheduleCancel() {

        this.renderedMakeScheduleButton = false;
    }

    public void saveCustomerAccDtl(HttpServletRequest request) {
        logger.info("saveCustomerAccDtl............................" + customerName);
    }

    public void resetOptions() {
        docTobeOpen = "";
    }

    public void openDocDtl(HttpServletRequest request) {
        logger.info("openDocDtl............................" + docTobeOpen);
        String link;
        String linkName = "Open Document";

        String website;
        CallMst callMster = (CallMst) request.getSession().getAttribute("callMst");
        this.setCustomerId(callMster.getCustId());
        website = request.getRequestURL().substring(0, request.getRequestURL().indexOf("/", 9));

        RequestContext requestContext = RequestContext.getCurrentInstance();

        if (null != docTobeOpen && !"".equals(docTobeOpen)) {

            if (docTobeOpen.equalsIgnoreCase("fd")) {
                link = website + request.getContextPath() + "/faces/pages/agent/viewCustomerFormFd.xhtml";
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

    public void openCobrowseWindow() {
        try {
            HttpSession session = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession();
            CallMst cm = (CallMst) session.getAttribute("callMst");
            Map<String, Object> options = new HashMap<>();
            options.put("modal", true);
            options.put("height", 500);
            options.put("width", 800);
            this.setCustomerId(cm.getCustId());
            this.setJwtToken(CreateJWTToken.createTokenWithPolicy(cm.getCustId()));
            RequestContext.getCurrentInstance().openDialog("/pages/agent/cobrowse", options, null);

        } catch (Exception e) {
            logger.error("ERROR : ", e);
        }
    }

    public void showhangUpReasonsFailedRecording(HttpServletRequest request) {
        logger.info("calling showhangUpReasonsFailedRecording....");
        reasonsRenderer = true;
        agentStatus = "Ready";
        this.agentColor = "";
        this.hangupenabled = true;
        this.setAvailableenabled(false);
        this.dialenabled = true;
        this.holdenabled = true;
        this.threewayenabled = true;
        this.forwardenabled = true;
        this.notreadyenabled = false;
        this.logoutenabled = false;
        this.recordingStatus = "";
        this.setIworkImage(false);
        callRecordsComponent.setRecordingStatus("");
        this.selfviewenabled = false;

        this.agentComment = null;
        Boolean forwardFlag = false;
        EmployeeMst empMst = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
        CallMst callMstLocal = (CallMst) request.getSession().getAttribute("callMst");

        if (null != callMstLocal && null != empMst) {
            CallDtl clDtl = null;
            try {

                clDtl = callDtlService.findCallDtlByCallMasterIdAndAgentNotForwarded(callMstLocal.getId(), empMst.getId());
                if (null != clDtl) {
                    forwardFlag = clDtl.getCallTypeInfo().equalsIgnoreCase("Forward");
                    clDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                    clDtl.setDeleteFlg(false);
                    clDtl.setActiveFlg(true);
                    callDtlService.saveCallDtl(clDtl);
                }

            } catch (Exception ex) {
                logger.error("showhangUpReasons1" + ex.getMessage());
            }
            List<CallDtl> findOtherNonEndedCallDetailFromSameCallMst = new ArrayList<>();
            try {
                if (null != clDtl) {
                    CallMst clMst = callMasterService.findNonDeletedCallMstById(clDtl.getCallMstId().getId());
                    findOtherNonEndedCallDetailFromSameCallMst = callDtlService.findCallDtlByCallMasterIdAndAgent(clMst.getId(), empMst.getId());
                    clMst.setEndTime(clDtl.getEndTime());
                    callMasterService.saveCallMst(clMst);
                }
            } catch (Exception ex) {
                logger.error("showhangUpReasons2" + ex.getMessage());
            }

            ReasonMst reasonMst = reasonMstService.findReasonMstByReasonCode("ZZZ00");

            if (reasonMst == null) {
                reasonMst = new ReasonMst();
                reasonMst.setReasonCd("ZZZ00");
                reasonMst.setType("hang up");
                reasonMst.setReasonDesc("Conference Successfully Done");
                reasonMst.setActiveFlg(true);
                reasonMst.setDeleteFlg(false);
                reasonMst = reasonMstService.save(reasonMst, empMst);
            }
            try {

                EmployeeActivityDtl employeeActivityDtl = new EmployeeActivityDtl();
                employeeActivityDtl.setActivity("Hang Up");
                employeeActivityDtl.setEmpId(empMst);
                employeeActivityDtl.setStartTime(CustomConvert.javaDateToTimeStamp(new Date()));
                employeeActivityDtl.setReasonId(reasonMst);
                employeeActivityDtl.setCallMstId(callMstLocal.getId());
                employeeActivityDtlService.save(employeeActivityDtl);
                logger.info("saved hang-up in Employee activity detail for Agent......." + empMst.getId());
            } catch (Exception ex) {
                logger.error("Exception in saving employeeActivityDtl" + ex.getMessage());
            }

            try {

                EmployeeActivityDtl employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(empMst.getId(), "Call Started");

                if (employeeActivityDtl != null) {
                    employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                    employeeActivityDtlService.save(employeeActivityDtl);

                }
            } catch (Exception ex) {
                logger.error(ex.getMessage());
            }

            try {
                if (!findOtherNonEndedCallDetailFromSameCallMst.isEmpty()) {
                    for (CallDtl calldtl : findOtherNonEndedCallDetailFromSameCallMst) {
                        if (calldtl.getEndTime() == null) {
                            calldtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                            calldtl.setDeleteFlg(false);
                            calldtl.setActiveFlg(true);
                            calldtl = callDtlService.saveCallDtl(calldtl);
                        }
                    }
                    findOtherNonEndedCallDetailFromSameCallMst.clear();
                }
            } catch (Exception ex) {
                logger.error(ex.getMessage());
            }

            if (forwardFlag) {
                logger.info("calling getOnlyStopRecording when forwarded....");
                callRecordsComponent.getOnlyStopRecording();

            }
        }

    }

    public void resetMsgToSend() {
        this.msgToSend = "";
    }

    public Boolean getImageuploadrenderer() {
        return imageuploadrenderer;
    }

    public void setImageuploadrenderer(Boolean imageuploadrenderer) {
        this.imageuploadrenderer = imageuploadrenderer;
    }

    public void setbuttons() {
        this.imageuploadrenderer = false;
        this.docuploadrenderer = false;

    }

    public Boolean getDocuploadrenderer() {
        return docuploadrenderer;
    }

    public void setDocuploadrenderer(Boolean docuploadrenderer) {
        this.docuploadrenderer = docuploadrenderer;
    }

    public boolean getDisableUploadFileButton() {
        return disableUploadFileButton;
    }

    public String getMsgToSend() {
        return msgToSend;
    }

    public void setMsgToSend(String msgToSend) {
        this.msgToSend = msgToSend;
    }

    public String getDocTobeOpen() {
        return docTobeOpen;
    }

    public void setDocTobeOpen(String docTobeOpen) {
        this.docTobeOpen = docTobeOpen;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getIdCardFromCust() {
        return idCardFromCust;
    }

    public void setIdCardFromCust(String idCardFromCust) {
        this.idCardFromCust = idCardFromCust;
    }

    public String getAddProofFromCust() {
        return addProofFromCust;
    }

    public void setAddProofFromCust(String addProofFromCust) {
        this.addProofFromCust = addProofFromCust;
    }

    public String getSelfImageFromCust() {
        return selfImageFromCust;
    }

    public void setSelfImageFromCust(String selfImageFromCust) {
        this.selfImageFromCust = selfImageFromCust;
    }

    public boolean getExistingCustomer() {
        return existingCustomer;
    }

    public void setExistingCustomer(boolean existingCustomer) {
        this.existingCustomer = existingCustomer;
    }

    public boolean getLoanCustomer() {
        return loanCustomer;
    }

    public void setLoanCustomer(boolean loanCustomer) {
        this.loanCustomer = loanCustomer;
    }

    public Long getLoanCustomerId() {
        return loanCustomerId;
    }

    public void setLoanCustomerId(Long loanCustomerId) {
        this.loanCustomerId = loanCustomerId;
    }

    public String getNameFromCRM() {
        return nameFromCRM;
    }

    public void setNameFromCRM(String nameFromCRM) {
        this.nameFromCRM = nameFromCRM;
    }

    public String getEmailFromCRM() {
        return emailFromCRM;
    }

    public void setEmailFromCRM(String emailFromCRM) {
        this.emailFromCRM = emailFromCRM;
    }

    public String getPhoneFromCRM() {
        return phoneFromCRM;
    }

    public void setPhoneFromCRM(String phoneFromCRM) {
        this.phoneFromCRM = phoneFromCRM;
    }

    public String getAccFromCRM() {
        return accFromCRM;
    }

    public void setAccFromCRM(String accFromCRM) {
        this.accFromCRM = accFromCRM;
    }

    public boolean getExportToCsvBtnStatus() {
        return exportToCsvBtnStatus;
    }

    public void setExportToCsvBtnStatus(boolean exportToCsvBtnStatus) {
        this.exportToCsvBtnStatus = exportToCsvBtnStatus;
    }

    public List<PerticipentDto> getHoldUnHoldDtoList() {
        return holdUnHoldDtoList;
    }

    public void setHoldUnHoldDtoList(List<PerticipentDto> holdUnHoldDtoList) {
        this.holdUnHoldDtoList = holdUnHoldDtoList;
    }

    public List<PerticipentDto> getSelectedHoldUnHoldDtoList() {
        return selectedHoldUnHoldDtoList;
    }

    public void setSelectedHoldUnHoldDtoList(List<PerticipentDto> selectedHoldUnHoldDtoList) {
        this.selectedHoldUnHoldDtoList = selectedHoldUnHoldDtoList;
    }

    public String getVideoFileUrl() {
        return videoFileUrl;
    }

    public void setVideoFileUrl(String videoFileUrl) {
        this.videoFileUrl = videoFileUrl;
    }

    public String getPromoURL() {
        return promoURL;
    }

    public void setPromoURL(String promoURL) {
        this.promoURL = promoURL;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUploadedFilePath() {
        return uploadedFilePath;
    }

    public void setUploadedFilePath(String uploadedFilePath) {
        this.uploadedFilePath = uploadedFilePath;
    }

    public String getNewFileMessage() {
        return newFileMessage;
    }

    public void setNewFileMessage(String newFileMessage) {
        this.newFileMessage = newFileMessage;
    }

    public List<PerticipentDto> getFileUploadDtoList() {
        return fileUploadDtoList;
    }

    public void setFileUploadDtoList(List<PerticipentDto> fileUploadDtoList) {
        this.fileUploadDtoList = fileUploadDtoList;
    }

    public List<PerticipentDto> getSelectedFileuploadDtoList() {
        return selectedFileuploadDtoList;
    }

    public void setSelectedFileuploadDtoList(List<PerticipentDto> selectedFileuploadDtoList) {
        this.selectedFileuploadDtoList = selectedFileuploadDtoList;
    }

    public List<FileDownloadDto> getFileDownloadDtoList() {
        return fileDownloadDtoList;
    }

    public void setFileDownloadDtoList(List<FileDownloadDto> fileDownloadDtoList) {
        this.fileDownloadDtoList = fileDownloadDtoList;
    }

    public boolean isMultipleParticipant() {
        return multipleParticipant;
    }

    public void setMultipleParticipant(boolean multipleParticipant) {
        this.multipleParticipant = multipleParticipant;
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

    //================== Methods For Getting match Percentage ==============================
    public static int lock_match(String s, String t) {
        logger.info("New String =====" + s);
        logger.info("Old String =====" + t);
        int totalw = word_count(s);
        int total = 100;
        int perw = 0;
        if (totalw > 0) {
            perw = total / totalw;
        }
        int gotperw = 0;

        if (!s.equals(t)) {

            for (int i = 1; i <= totalw; i++) {
                if (simple_match(split_string(s, i), t) == 1) {
                    gotperw = ((perw * (total - 10)) / total) + gotperw;
                } else if (front_full_match(split_string(s, i), t) == 1) {
                    gotperw = ((perw * (total - 20)) / total) + gotperw;
                } else if (anywhere_match(split_string(s, i), t) == 1) {
                    gotperw = ((perw * (total - 30)) / total) + gotperw;
                } else {
                    gotperw = ((perw * smart_match(split_string(s, i), t)) / total) + gotperw;
                }
            }
        } else {
            gotperw = 100;
        }
        return gotperw;
    }

    public static int anywhere_match(String s, String t) {
        int x = 0;
        if (t.contains(s)) {
            x = 1;
        }
        return x;
    }

    public static int front_full_match(String s, String t) {
        int x = 0;
        String tempt;
        int len = s.length();

        //----------Work Body----------//
        for (int i = 1; i <= word_count(t); i++) {
            tempt = split_string(t, i);
            if (tempt.length() >= s.length()) {
                tempt = tempt.substring(0, len);
                if (s.contains(tempt)) {
                    x = 1;
                    break;
                }
            }
        }
        //---------END---------------//
        if (len == 0) {
            x = 0;
        }
        return x;
    }

    public static int simple_match(String s, String t) {
        int x = 0;
        String tempt;
        int len = s.length();

        //----------Work Body----------//
        for (int i = 1; i <= word_count(t); i++) {
            tempt = split_string(t, i);
            if (tempt.length() == s.length()) {
                if (s.contains(tempt)) {
                    x = 1;
                    break;
                }
            }
        }
        //---------END---------------//
        if (len == 0) {
            x = 0;
        }
        return x;
    }

    public static int smart_match(String ts, String tt) {

        char[] s;
        s = ts.toCharArray();
        char[] t;
        t = tt.toCharArray();

        int slen = s.length;
        //number of 3 combinations per word//
        int combs = (slen - 3) + 1;
        //percentage per combination of 3 characters//
        int ppc = 0;
        if (slen >= 3) {
            ppc = 100 / combs;
        }
        //initialising an integer to store the total % this class genrate//
        int x = 0;
        //declaring a temporary new source char array
        char[] ns = new char[3];
        //check if source char array has more then 3 characters//
        if (slen < 3) {
        } else {
            for (int i = 0; i < combs; i++) {
                for (int j = 0; j < 3; j++) {
                    ns[j] = s[j + i];
                }
                if (cross_full_match(ns, t) == 1) {
                    x = x + 1;
                }
            }
        }
        x = ppc * x;
        return x;
    }

    /**
     *
     * @param s
     * @param t
     * @return
     */
    public static int cross_full_match(char[] s, char[] t) {
        int z = t.length - s.length;
        int x = 0;
        if (s.length > t.length) {
            return x;
        } else {
            for (int i = 0; i <= z; i++) {
                for (int j = 0; j <= (s.length - 1); j++) {
                    if (s[j] == t[j + i]) {
                        // x=1 if any charecer matches
                        x = 1;
                    } else {
                        // if x=0 mean an character do not matches and loop break out
                        x = 0;
                        break;
                    }
                }
                if (x == 1) {
                    break;
                }
            }
        }
        return x;
    }

    public static String split_string(String s, int n) {

        int index;
        String temp;
        temp = s;
        String temp2 = null;

        int temp3 = 0;

        for (int i = 0; i < n; i++) {
            int strlen = temp.length();
            index = temp.indexOf(" ");
            if (index < 0) {
                index = strlen;
            }
            temp2 = temp.substring(temp3, index);
            temp = temp.substring(index, strlen);
            temp = temp.trim();

        }
        return temp2;
    }

    public static int word_count(String s) {
        int x = 1;
        int c;
        s = s.trim();
        if (s.isEmpty()) {
            x = 0;
        } else if (s.contains(" ")) {
            for (;;) {
                x++;
                c = s.indexOf(" ");
                s = s.substring(c);
                s = s.trim();
                if (s.contains(" ")) {
                } else {
                    break;
                }
            }
        }
        return x;
    }
    //=======================End Methods for % matched =====================================

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

    public void checkSelfView() throws ParseException {
        HttpSession session = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession();
        EmployeeMst empMst = (EmployeeMst) session.getAttribute("ormUserMaster");
        if (null != empMst) {
            String[] activityArray = {"NEXT CALL", "Self View"};
            List<EmployeeActivityDto> employeeActivityDtlList;

            employeeActivityDtlList = employeeActivityDtlService.findLastNonEndedActivityByAllType(empMst.getId(), activityArray);

            if (!employeeActivityDtlList.isEmpty() && employeeActivityDtlList.size() > 0) {

                try {
                    EmployeeActivityDtl employeeActivityDtl = employeeActivityDtlService.updateEndTimeByEmpId(empMst.getId(), employeeActivityDtlList, CustomConvert.javaDateToTimeStamp(new Date()));
                    if (employeeActivityDtl != null) {

                        this.agentStatus = "Ready";
                        this.agentColor = "";
                        this.notreadyenabled = true;
                        this.availableenabled = false;

                    }
                } catch (Exception ex) {
                    Logger.getLogger(VidyoAgent.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } else {
            RequestContext.getCurrentInstance().execute("redirectHome();");
        }

    }

    public List<CustomerAccountDto> getCustomerFileDetailsList() {
        return customerFileDetailsList;
    }

    public void setCustomerFileDetailsList(List<CustomerAccountDto> customerFileDetailsList) {
        this.customerFileDetailsList = customerFileDetailsList;
    }

    public List<FileReportDto> getUserReceivedFiles() {
        return userReceivedFiles;
    }

    public void setUserReceivedFiles(List<FileReportDto> userReceivedFiles) {
        this.userReceivedFiles = userReceivedFiles;
    }

    public List<CustomerAccountDto> getFilteredCustomerFileDetailsList() {
        return filteredCustomerFileDetailsList;
    }

    public void setFilteredCustomerFileDetailsList(List<CustomerAccountDto> filteredCustomerFileDetailsList) {
        this.filteredCustomerFileDetailsList = filteredCustomerFileDetailsList;
    }

    public List<FileReportDto> getFilteredUserReceivedFiles() {
        return filteredUserReceivedFiles;
    }

    public void setFilteredUserReceivedFiles(List<FileReportDto> filteredUserReceivedFiles) {
        this.filteredUserReceivedFiles = filteredUserReceivedFiles;
    }

    public List<FileReportDto> getUserSendFiles() {
        return userSendFiles;
    }

    public void setUserSendFiles(List<FileReportDto> userSendFiles) {
        this.userSendFiles = userSendFiles;
    }

    public String getGuestEmail() {
        return guestEmail;
    }

    public void setGuestEmail(String guestEmail) {
        this.guestEmail = guestEmail;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getServiceTimeText() {
        return serviceTimeText;
    }

    public void setServiceTimeText(String serviceTimeText) {
        this.serviceTimeText = serviceTimeText;
    }

    public String getSchCustomerName() {
        return schCustomerName;
    }

    public void setSchCustomerName(String schCustomerName) {
        this.schCustomerName = schCustomerName;
    }

    public Date getSchDateTime() {
        return schDateTime;
    }

    public void setSchDateTime(Date schDateTime) {
        this.schDateTime = schDateTime;
    }

    public Date getSchEndDateTime() {
        return schEndDateTime;
    }

    public void setSchEndDateTime(Date schEndDateTime) {
        this.schEndDateTime = schEndDateTime;
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

    public Boolean getRenderedMakeScheduleButton() {
        return renderedMakeScheduleButton;
    }

    public void setRenderedMakeScheduleButton(Boolean renderedMakeScheduleButton) {
        this.renderedMakeScheduleButton = renderedMakeScheduleButton;
    }

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    public Long getSelectedServiceName() {
        return selectedServiceName;
    }

    public void setSelectedServiceName(Long selectedServiceName) {
        this.selectedServiceName = selectedServiceName;
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

    public String getScheduleText() {
        return scheduleText;
    }

    public void setScheduleText(String scheduleText) {
        this.scheduleText = scheduleText;
    }

    public Long getSelectedCategoryName() {
        return selectedCategoryName;
    }

    public void setSelectedCategoryName(Long selectedCategoryName) {
        this.selectedCategoryName = selectedCategoryName;
    }

    public Long getSelectedLanguageName() {
        return selectedLanguageName;
    }

    public void setSelectedLanguageName(Long selectedLanguageName) {
        this.selectedLanguageName = selectedLanguageName;
    }

    public List<CustomerMst> getCustomerMstList() {
        return customerMstList;
    }

    public void setCustomerMstList(List<CustomerMst> customerMstList) {
        this.customerMstList = customerMstList;
    }

    public Long getSelectedCustomerId() {
        return selectedCustomerId;
    }

    public void setSelectedCustomerId(Long selectedCustomerId) {
        this.selectedCustomerId = selectedCustomerId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getRoomUrl() {
        return roomUrl;
    }

    public void setRoomUrl(String roomUrl) {
        this.roomUrl = roomUrl;
    }

    public String getPortal() {
        return portal;
    }

    public void setPortal(String portal) {
        this.portal = portal;
    }

    public String getVidyoUserid() {
        return vidyoUserid;
    }

    public void setVidyoUserid(String vidyoUserid) {
        this.vidyoUserid = vidyoUserid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isIsRm() {
        return isRm;
    }

    public void setIsRm(boolean isRm) {
        this.isRm = isRm;
    }

}
