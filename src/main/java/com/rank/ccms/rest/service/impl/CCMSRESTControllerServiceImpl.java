package com.rank.ccms.rest.service.impl;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.rank.ccms.dao.CategoryMstDao;
import com.rank.ccms.dao.LanguageMstDao;
import com.rank.ccms.dao.ReasonMstDao;
import com.rank.ccms.dao.ServiceMstDao;
import com.rank.ccms.dto.CallEmployeeMap;
import com.rank.ccms.dto.PerticipentDto;
import com.rank.ccms.dto.ScheduleCallDto;
import com.rank.ccms.dto.SpecialistDto;
import com.rank.ccms.entities.CallDtl;
import com.rank.ccms.entities.CallFileUploadDtls;
import com.rank.ccms.entities.CallMst;
import com.rank.ccms.entities.CallRecords;
import com.rank.ccms.entities.CallSettings;
import com.rank.ccms.entities.CategoryMst;
import com.rank.ccms.entities.CustomerDeviceDtl;
import com.rank.ccms.entities.CustomerDtl;
import com.rank.ccms.entities.CustomerLoanDtl;
import com.rank.ccms.entities.CustomerMst;
import com.rank.ccms.entities.CustomerRmMap;
import com.rank.ccms.entities.DownTime;
import com.rank.ccms.entities.EmployeeActivityDtl;
import com.rank.ccms.entities.EmployeeCallStatus;
import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.entities.EmployeeTypeMst;
import com.rank.ccms.entities.FeedbackDtl;
import com.rank.ccms.entities.FeedbackQueryMst;
import com.rank.ccms.entities.ForwardedCall;
import com.rank.ccms.entities.LanguageMst;
import com.rank.ccms.entities.PromotionalVideoMst;
import com.rank.ccms.entities.ReasonMst;
import com.rank.ccms.entities.RmSrmMap;
import com.rank.ccms.entities.ScheduleCall;
import com.rank.ccms.entities.ServiceMst;
import com.rank.ccms.entities.SrmBmMap;
import com.rank.ccms.entities.TenancyEmployeeMap;
import com.rank.ccms.rest.exception.CCMSRestException;
import com.rank.ccms.rest.response.AgentLoginResponse;
import com.rank.ccms.rest.response.CategoryDto;
import com.rank.ccms.rest.response.CustomerAccountInformationDetailsDto;
import com.rank.ccms.rest.response.CustomerDto;
import com.rank.ccms.rest.response.FeedbackDto;
import com.rank.ccms.rest.response.FeedbackQuestionsResponse;
import com.rank.ccms.rest.response.FeedbackResponse;
import com.rank.ccms.rest.response.FileHandleDto;
import com.rank.ccms.rest.response.IncomingCallResponse;
import com.rank.ccms.rest.response.LanguageDto;
import com.rank.ccms.rest.response.ParticipantDto;
import com.rank.ccms.rest.response.ReasonDto;
import com.rank.ccms.rest.response.ScheduleDetailsResponse;
import com.rank.ccms.rest.response.ServiceDto;
import com.rank.ccms.rest.service.CCMSRESTControllerService;
import com.rank.ccms.service.AgentFindingService;
import com.rank.ccms.service.CallDtlService;
import com.rank.ccms.service.CallFileUploadDtlsService;
import com.rank.ccms.service.CallMstService;
import com.rank.ccms.service.CallRecordsService;
import com.rank.ccms.service.CallSchedulerService;
import com.rank.ccms.service.CallSettingsService;
import com.rank.ccms.service.CategoryMstService;
import com.rank.ccms.service.CustomerDeviceDtlService;
import com.rank.ccms.service.CustomerDtlService;
import com.rank.ccms.service.CustomerLoanDtlService;
import com.rank.ccms.service.CustomerMstService;
import com.rank.ccms.service.CustomerRMMapService;
import com.rank.ccms.service.DownTimeService;
import com.rank.ccms.service.EmployeeActivityDtlService;
import com.rank.ccms.service.EmployeeCallStatusService;
import com.rank.ccms.service.EmployeeLoginService;
import com.rank.ccms.service.EmployeeMstService;
import com.rank.ccms.service.EmployeeTypeMstService;
import com.rank.ccms.service.FeedbackDtlService;
import com.rank.ccms.service.FeedbackQueryMstService;
import com.rank.ccms.service.ForwardedCallService;
import com.rank.ccms.service.LanguageMstService;
import com.rank.ccms.service.PromotionalVideoMstService;
import com.rank.ccms.service.ReasonMstService;
import com.rank.ccms.service.RmSrmMapService;
import com.rank.ccms.service.ServiceMstService;
import com.rank.ccms.service.SrmBmMapService;
import com.rank.ccms.service.TenancyEmployeeMapService;
import com.rank.ccms.util.CallScheduler;
import com.rank.ccms.util.Constants;
import com.rank.ccms.util.CustomConvert;
import com.rank.ccms.util.CustomerConstant;
import com.rank.ccms.util.GenerateCustId;
import com.rank.ccms.util.SendingMailUtil;
import com.rank.ccms.util.SocketMessage;
import com.rank.ccms.util.ThreadSafeSimpleDateFormat;
import com.rank.ccms.vidyo.util.VidyoAccessUser;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeSet;
import static java.util.concurrent.TimeUnit.*;
import java.util.logging.Level;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.joda.time.LocalTime;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("ccmsRESTControllerServiceImpl")

public class CCMSRESTControllerServiceImpl implements CCMSRESTControllerService {

    private static final Logger logger = Logger.getLogger(CCMSRESTControllerService.class);

    private final boolean FALSE_STATUS = false;
    private final String MSG_SUCCESS = "SUCCESS";
    private final String MSG_FAILED = "FAILED";
    private final String PARTICIPANT_EMP = "Employee";
    private final String PARTICIPANT_CUST = "Customer";
    private final boolean TRUE_STATUS = true;
    private final String CUSTOMER_NEW = "new_customer";
    private final String CUSTOMER_REGISTERED = "registered_customer";

    private String downTimeStart;

    private String downTimeEnd;

    private String serviceTimeStart;

    private String serviceTimeEnd;

    private String downMessage;

    @Autowired
    private HttpServletRequest requests;

    @Autowired
    private ServletContext context;

    @Autowired
    private LanguageMstDao languageMstDao;

    @Autowired
    private ServiceMstDao servicesMstDao;

    @Autowired
    private CategoryMstDao categoryMstDao;

    @Autowired
    private EmployeeLoginService employeeLoginService;

    @Autowired
    private ReasonMstService reasonMstService;

    @Autowired
    private EmployeeActivityDtlService employeeActivityDtlService;

    @Autowired
    private EmployeeCallStatusService employeeCallStatusService;

    @Autowired
    private ForwardedCallService forwardedCallService;

    @Autowired
    private TenancyEmployeeMapService tenancyEmployeeMapService;

    @Autowired
    private EmployeeMstService employeeMstService;

    @Autowired
    private CustomerMstService customerMstService;

    @Autowired
    private CustomerDeviceDtlService customerDeviceDtlService;

    @Autowired
    private PromotionalVideoMstService promotionalVideoMstService;

    @Autowired
    private CategoryMstService categoryMstService;

    @Autowired
    private LanguageMstService langMstService;

    @Autowired
    private ReasonMstDao reasonMstDao;

    @Autowired
    private CallMstService callMstService;

    @Autowired
    private ServiceMstService serviceMstService;

    @Autowired
    private CallRecordsService callRecordsService;

    @Autowired
    private AgentFindingService agentFindingService;

    @Autowired
    private CallDtlService callDtlService;

    @Autowired
    private CallScheduler callScheduler;

    @Autowired
    private CallFileUploadDtlsService callFileUploadDtlsService;

    @Autowired
    private EmployeeTypeMstService employeeTypeMstService;

    @Autowired
    private CallSchedulerService callSchedulerService;

    @Autowired
    private FeedbackQueryMstService feedbackQueryMstService;

    @Autowired
    private FeedbackDtlService feedbackDtlService;

    @Autowired
    private CustomerDtlService customerDtlService;

    @Autowired
    private DownTimeService downTimeService;

    @Autowired
    private CallSettingsService callSettingsService;

    @Autowired
    private CustomerLoanDtlService customerLoanDtlService;

    @Autowired
    CustomerRMMapService customerRMMapService;

    @Autowired
    RmSrmMapService rmSrmMapService;

    @Autowired
    SrmBmMapService srmBmMapService;

    @Override
    public AgentLoginResponse getAgentLoginResponse(String credential) throws Exception {
        EmployeeMst employeeMst;
        AgentLoginResponse agentLoginResponse;

        /**
         * @Purpose To check whether method parameter credential is null or not
         */
        if (credential != null && credential.length() > 0) {
            JSONObject cred = new JSONObject(credential);
            String loginId = cred.getString("loginId");
            String loginPassword = cred.getString("password");

            if (loginId == null || loginId.isEmpty() || "".equals(loginId)) {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Please Provide loginId!");
                throw ccmsRestException;

            }

            if (loginPassword == null || loginPassword.isEmpty() || "".equals(loginPassword)) {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Please Provide password!");
                throw ccmsRestException;
            }

            employeeMst = employeeLoginService.checkLogin(loginId, loginPassword, "");

            /**
             * check if login credential is valid or not
             */
            if (employeeMst != null) {

                Date date = new Date();

                /**
                 * @purpose: Find in employee_activity_dtl table if any previous
                 * activity made by this agent is ended or not. Consider only
                 * the last most activity.If not ended, then give a end time and
                 * save it in the employee_activity_dtl table. If login is not
                 * ended then Logout must not be ended. Because every successful
                 * logout associated with a login end time in the table.
                 */
                /**
                 * Start of login activity check and save
                 */
                EmployeeActivityDtl employeeActivityDtl = employeeActivityDtlService
                        .findLastNonEndedActivityByType(employeeMst.getId(), "login");
                Long resLoginId;
                ReasonMst reasonMst = reasonMstService.findReasonMstByReasonCode("SOUT");
                if (null != employeeActivityDtl) {
                    resLoginId = employeeActivityDtl.getId();
                    employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(date));
                    employeeActivityDtlService.save(employeeActivityDtl, employeeMst);

                    /**
                     * Start of logout activity against the login activity
                     */
                    employeeActivityDtl = new EmployeeActivityDtl();
                    employeeActivityDtl.setEmpId(employeeMst);
                    employeeActivityDtl.setActivity("logout");
                    employeeActivityDtl.setStartTime(CustomConvert.javaDateToTimeStamp(date));
                    employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(date));
                    employeeActivityDtl.setRespectiveLoginId(resLoginId);
                    employeeActivityDtl.setReasonId(reasonMst);
                    employeeActivityDtl.setReasonCd(reasonMst.getReasonCd());
                    employeeActivityDtl.setReasonDesc(reasonMst.getReasonDesc());

                    employeeActivityDtlService.save(employeeActivityDtl, employeeMst);
                    /**
                     * End of logout activity
                     */

                }
                /**
                 * End of login activity check and save
                 */

                /**
                 * Start of Self View activity check and save
                 */
                employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(employeeMst.getId(),
                        "Self View");
                if (employeeActivityDtl != null) {
                    employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                    employeeActivityDtlService.save(employeeActivityDtl);
                }
                /**
                 * End of Self View activity check and save
                 */

                /**
                 * Start of Not Ready activity check and save
                 */
                employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(employeeMst.getId(),
                        "not ready");
                if (employeeActivityDtl != null) {
                    employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(date));
                    employeeActivityDtlService.save(employeeActivityDtl);
                }
                /**
                 * End of Not Ready activity check and save
                 */

                /**
                 * Start of Next Call activity check and save
                 */
                employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(employeeMst.getId(),
                        "NEXT CALL");
                if (employeeActivityDtl != null) {
                    employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(date));
                    employeeActivityDtlService.save(employeeActivityDtl);

                }
                /**
                 * End of Next Call activity check and save
                 */

                /**
                 * Start of Call Started activity check and save
                 */
                employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(employeeMst.getId(),
                        "Call Started");
                if (employeeActivityDtl != null) {
                    employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(date));
                    employeeActivityDtlService.save(employeeActivityDtl);

                }
                /**
                 * End of Call Started activity check and save
                 */

                /**
                 * Start of Hang Up activity check and save
                 */
                employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(employeeMst.getId(),
                        "Hang Up");
                if (employeeActivityDtl != null) {
                    employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(date));
                    employeeActivityDtlService.save(employeeActivityDtl);

                }
                /**
                 * End of Hang Up activity check and save
                 */

                /**
                 * @purpose: Update the Call Status of this employee to false.
                 * Since not available for this employee, as not ready.
                 */
                List<EmployeeCallStatus> empCallStatusList = employeeCallStatusService
                        .findEmployeeCallStatusByEmpId(employeeMst);
                for (EmployeeCallStatus callStatus : empCallStatusList) {
                    // callStatus.setCallCount(0);
                    callStatus.setStatus(false);
                    employeeCallStatusService.saveEmployeeCallStatus(callStatus);
                }

                /**
                 * @purpose: Update the Forward Call Active Flag of this
                 * employee to false. Since, for fresh login this is a certain.
                 */
                List<ForwardedCall> forwardedCallList = forwardedCallService
                        .findActiveForwardedCallByEmployeeMstList(employeeMst);
                for (ForwardedCall forwardedCall : forwardedCallList) {
                    forwardedCall.setForwardStatus("NotPicked");
                    forwardedCall.setActiveFlg(false);
                    forwardedCall.setDeleteFlg(true);
                    forwardedCallService.saveForwardedCalls(forwardedCall, employeeMst);
                }

                logger.info(">>>>Logout Successfully>>> Employee UserId: " + employeeMst.getLoginId()
                        + " Employee Name:" + employeeMst.getFirstName() + " " + employeeMst.getMidName() + " "
                        + employeeMst.getLastName() + ".");

                /**
                 * @purpose: Remove the non existent employee from the list
                 * which is set through the scheduler.
                 */
                if (!CallScheduler.listCallEmp.isEmpty()) {
                    for (CallEmployeeMap cem : CallScheduler.listCallEmp) {
                        if (employeeMst.getId() == cem.getEmployeeId().longValue()) {
                            CallScheduler.listCallEmp.remove(cem);
                            break;
                        }
                    }
                }

                /**
                 * @purpose: Search in reason_mst table for type Login. If any
                 * record found then fetch the first record if no record found,
                 * then create a new ReasonMst entity and assign as type Login
                 * and set required fields. ReasonMst object will be used to
                 * store value in employee activity detail table.
                 */
                List<ReasonMst> lReasonMstList = reasonMstService.findAllActivenNonDeletedReasonMsts("login");
                if (null == lReasonMstList || lReasonMstList.isEmpty()) {
                    reasonMst = new ReasonMst((long) 0, "login", true, false);
                    reasonMst.setReasonCd("LN001");
                    reasonMst.setReasonDesc("Login First Time - Day Start.");
                } else {
                    reasonMst = lReasonMstList.get(0);
                }

                /**
                 * @purpose: Create a new activity with fresh login and start
                 * time of the employee.
                 */
                employeeActivityDtl = new EmployeeActivityDtl();
                employeeActivityDtl.setEmpId(employeeMst);
                employeeActivityDtl.setActivity("login");
                try {
                    employeeActivityDtl.setStartTime(CustomConvert.javaDateToTimeStamp(new Date()));
                } catch (ParseException e) {
                    logger.info("Error:" + e.getMessage());
                }
                employeeActivityDtl.setReasonId(reasonMst);
                employeeActivityDtl.setReasonCd(reasonMst.getReasonCd());
                employeeActivityDtl.setReasonDesc(reasonMst.getReasonDesc());
                employeeActivityDtlService.save(employeeActivityDtl, employeeMst);
                /**
                 * End of Login activity set
                 */

                /**
                 * @purpose: The following code is used to create the user id
                 * and token, for self view.
                 */
                Long number = (long) Math.floor(Math.random() * 9000000L) + 1000000L;
                String room = employeeMst.getLoginId() + number;
//                String token = GenerateToken.getToken();

                TenancyEmployeeMap tenancyEmployeeMap = new TenancyEmployeeMap();
                tenancyEmployeeMap.setEmpId(employeeMst);
//                tenancyEmployeeMap.setRoomLink(token);
                tenancyEmployeeMap.setRoomName(room);

                tenancyEmployeeMapService.saveTenancyEmployeeMap(tenancyEmployeeMap);
                /**
                 * End of self view set.
                 */

                /**
                 * @purpose: Create a new AgentLoginResponse object in order to
                 * send the response.
                 */
                agentLoginResponse = new AgentLoginResponse();
                agentLoginResponse.setSuccMsg("Success");
                agentLoginResponse.setValidateMsg("");

                agentLoginResponse.setEmpId(employeeMst.getId());
                agentLoginResponse.setFirstname(employeeMst.getFirstName());
                agentLoginResponse.setLastName(employeeMst.getLastName());
                agentLoginResponse.setLoginId(employeeMst.getLoginId());
                agentLoginResponse.setEmpType(employeeMst.getEmpTypId().getTypeName());
                agentLoginResponse.setSocketHostPublic(Constants.socketHostPublic);
                if ("RM1".equalsIgnoreCase(employeeMst.getLoginId())) {
                    agentLoginResponse.setServiceTimeText(Constants.RM1_Service_Start_Time + "-" + Constants.RM1_Service_End_Time);
                } else if ("RM2".equalsIgnoreCase(employeeMst.getLoginId())) {
                    agentLoginResponse.setServiceTimeText(Constants.RM2_Service_Start_Time + "-" + Constants.RM2_Service_End_Time);
                }
                /**
                 * return from the method, if success
                 */
                return agentLoginResponse;
            } else {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Please Provide valid login credential!");
                throw ccmsRestException;
            }

        } else {
            CCMSRestException ccmsRestException = new CCMSRestException();
            ccmsRestException.setErrorCode("418");
            ccmsRestException.setErrorMessage("Please Provide valid login credential!");
            throw ccmsRestException;
        }
    }

    @Override
    public List<CategoryDto> getCategoryList() {
        return categoryMstDao.findAllNonDeletedCategoriesAsCategoryDtoList();
    }

    @Override
    public List<ServiceDto> getServiceList() {
        return servicesMstDao.findAllNonDeletedServicesAsServiceDtoList();
    }

    @Override
    public List<LanguageDto> getLanguageList() {
        return languageMstDao.findAllNonDeletedLanguagesAsLanguageDtoList();
    }

    @Override
    public List<ReasonDto> getReasonList() {
        return reasonMstDao.findAllNonDeletedReasonsAsReasonDtoList();
    }

    @Override
    public AgentLoginResponse getAgentReadyResponse(String credential) throws Exception {

        AgentLoginResponse agentLoginResponse;
        String roomlink = null;
        String roomname = null;
        String entityId = null;

        /**
         * @Purpose To check whether this method parameter credential is null or
         * not
         */
        if (credential != null && credential.length() > 0) {
            JSONObject cred = new JSONObject(credential);
            String employeeId = cred.getString("empId");

            if (employeeId != null && employeeId.length() > 0) {
                Long empId = Long.parseLong(employeeId);
                EmployeeMst empMst = employeeMstService.findEmployeeMstById(empId);
                EmployeeCallStatus empCallStatus = null;

                List<EmployeeCallStatus> empClStatusList = null;
                List<TenancyEmployeeMap> currentAgent = null;

                if (null != empMst) {
                    /**
                     * @purpose empClStatusList object is used to find if this
                     * employee's has any entry in the employee_call_status
                     * table or not. If found the update the existing to true
                     * else create a new callStatus entity and set the status to
                     * true and save it to the employee_call_status table.
                     */
                    empClStatusList = employeeCallStatusService.findEmployeeCallStatusByEmpId(empMst);

                    /**
                     * @purpose currentAgent is list of tenancy_employee_map
                     * table objects. It is in general single entry against each
                     * logged in employee is valid. Based on this the multiple
                     * employee with same credential is detected.
                     */
                    currentAgent = tenancyEmployeeMapService.findVidyoTenantUrlByEmpId(empMst.getId());

                    Long number = (long) Math.floor(Math.random() * 9000000L) + 1000000L;
                    roomname = empMst.getLoginId() + number;

                    VidyoAccessUser vidyoAccessUser = new VidyoAccessUser(Constants.vidyoportalUserServiceWSDL);
                    String ret = vidyoAccessUser.createRoom(Constants.adminUserId, Constants.adminPwd, roomname, Constants.vidyoportalUserServiceWSDL);
                    roomlink = ret.split(",")[0];
                    entityId = ret.split(",")[1];

                } else {
                    CCMSRestException ccmsRestException = new CCMSRestException();
                    ccmsRestException.setErrorCode("418");
                    ccmsRestException.setErrorMessage("Please Provide valid credential!");
                    throw ccmsRestException;
                }

                if (!currentAgent.isEmpty() && currentAgent.size() > 0) {
                    for (TenancyEmployeeMap currentAgent1 : currentAgent) {
                        currentAgent1.setRoomName(roomname);
                        currentAgent1.setRoomLink(roomlink);
                        currentAgent1.setEntityId(entityId);
                        tenancyEmployeeMapService.saveTenancyEmployeeMap(currentAgent1);
                    }
                } else {
                    TenancyEmployeeMap tenancyEmployeeMap = new TenancyEmployeeMap();
                    tenancyEmployeeMap.setEmpId(empMst);
                    tenancyEmployeeMap.setRoomLink(roomlink);
                    tenancyEmployeeMap.setRoomName(roomname);
                    tenancyEmployeeMap.setEntityId(entityId);
                    tenancyEmployeeMapService.saveTenancyEmployeeMap(tenancyEmployeeMap);
                }

                /**
                 * @purpose: Create a new AgentLoginResponse object in order to
                 * send the response.
                 */
                agentLoginResponse = new AgentLoginResponse();
                agentLoginResponse.setRoomLink(roomlink);
                agentLoginResponse.setRoomName(roomname);
                agentLoginResponse.setEntityId(entityId);

                /**
                 * @purpose To check whether there exist an entry corresponding
                 * to this employee in employee_call_status table or not. If
                 * found then update with the status as true and if not found,
                 * then create a call status object and set it true and save it
                 * in the employee_call_status.
                 */
                if (!empClStatusList.isEmpty()) {
                    for (EmployeeCallStatus empstatus : empClStatusList) {
                        empCallStatus = empstatus;
                    }
                    if (null != empCallStatus) {
                        empCallStatus.setStatus(true);

                        employeeCallStatusService.saveEmployeeCallStatus(empCallStatus);
                    }
                } else {
                    empCallStatus = new EmployeeCallStatus();
                    empCallStatus.setEmpId(empMst);
                    empCallStatus.setStatus(true);
                    employeeCallStatusService.saveEmployeeCallStatus(empCallStatus);
                }

                /**
                 * @purpose To find out the last non ended activity of this
                 * employee by type "not ready". If found then update the record
                 * with the end time.
                 */
                EmployeeActivityDtl employeeActivityDtl = employeeActivityDtlService
                        .findLastNonEndedActivityByType(empMst.getId(), "not ready");
                if (employeeActivityDtl != null) {
                    employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                    employeeActivityDtlService.save(employeeActivityDtl);
                }

                /**
                 * @purpose To find out the last non ended activity of this
                 * employee by type "Hang Up". If found then update the record
                 * with the end time.
                 */
                employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(empMst.getId(),
                        "Hang Up");
                if (employeeActivityDtl != null) {
                    employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                    employeeActivityDtlService.save(employeeActivityDtl);
                }

                /**
                 * @purpose To find out the last non ended activity of this
                 * employee by type "Self View". If found then update the record
                 * with the end time.
                 */
                employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(empMst.getId(),
                        "Self View");
                if (employeeActivityDtl != null) {
                    employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                    employeeActivityDtlService.save(employeeActivityDtl);
                }

                /**
                 * @purpose To create a new EmployeeActivityDtl object to add a
                 * row in the employee_activity_table as "NEXT CALL" activity
                 * and reason as "Not Ready" with reason code as "NRDFT"
                 */
                employeeActivityDtl = new EmployeeActivityDtl();
                employeeActivityDtl.setActivity("NEXT CALL");
                employeeActivityDtl.setEmpId(empMst);

                ReasonMst reasonmst = reasonMstService.findReasonMstByReasonType("Not Ready");
                employeeActivityDtl.setReasonId(reasonmst);
                employeeActivityDtl.setReasonCd(reasonmst.getReasonCd());
                employeeActivityDtl.setReasonDesc(reasonmst.getReasonDesc());
                employeeActivityDtl.setStartTime(CustomConvert.javaDateToTimeStamp(new Date()));
                employeeActivityDtlService.save(employeeActivityDtl);

                /**
                 * return from the method, if success
                 */
                return agentLoginResponse;
            } else {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Please Provide valid credential!");
                throw ccmsRestException;
            }

        } else {
            CCMSRestException ccmsRestException = new CCMSRestException();
            ccmsRestException.setErrorCode("418");
            ccmsRestException.setErrorMessage("Please Provide valid credential!");
            throw ccmsRestException;
        }

    }

    @Override
    public AgentLoginResponse getAgentLogoutResponse(String callInfo) throws Exception {

        String callId;
        String agentId = null;
        AgentLoginResponse agentLoginResponse;
        /**
         * @Purpose To check whether method parameter callInfo is null or not
         */
        if (callInfo != null && callInfo.length() > 0) {
            JSONObject callInformation = new JSONObject(callInfo);
            if (callInformation.has("callId") && callInformation.has("empId")) {
                callId = callInformation.getString("callId");
                logger.info("with callid=" + callId + "    Called");
                this.getAgentCallEndResponse(callInfo);
            } else if (callInformation.has("empId")) {
                logger.info("Agent id found");
            } else {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Please Provide valid information!");
                throw ccmsRestException;
            }
            agentId = callInformation.getString("empId");

        } else {
            CCMSRestException ccmsRestException = new CCMSRestException();
            ccmsRestException.setErrorCode("418");
            ccmsRestException.setErrorMessage("Please Provide valid information!");
            throw ccmsRestException;
        }

        if (agentId != null && agentId.length() > 0) {

            EmployeeMst employeeMaster = employeeMstService.findEmployeeMstById(Long.parseLong(agentId));
            List<ReasonMst> lReasonMstList = reasonMstService.findAllActivenNonDeletedReasonMsts("Logout");
            agentLoginResponse = new AgentLoginResponse();
            ReasonMst reasonMst;

            if (null == lReasonMstList || lReasonMstList.isEmpty()) {
                reasonMst = new ReasonMst((long) 0, "login", true, false);
                reasonMst.setReasonCd("LN001");
                reasonMst.setReasonDesc("Login First Time - Day Start.");
            } else {
                reasonMst = lReasonMstList.get(0);
            }
            if (employeeMaster != null) {
                List<EmployeeCallStatus> employeeCallStatusList = employeeCallStatusService
                        .findEmployeeCallStatusByEmpId(employeeMaster);
                for (EmployeeCallStatus employeeCallStatusList1 : employeeCallStatusList) {
                    employeeCallStatusList1.setStatus(false);
                    employeeCallStatusService.saveEmployeeCallStatus(employeeCallStatusList1);
                }
                Date date = new Date();
                EmployeeActivityDtl employeeActivityDtl = employeeActivityDtlService
                        .findLastNonEndedActivityByType(employeeMaster.getId(), "login");
                Long resLoginId;
                if (null != employeeActivityDtl) {

                    employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(date));
                    employeeActivityDtl = employeeActivityDtlService.save(employeeActivityDtl, employeeMaster);
                    resLoginId = employeeActivityDtl.getId();

                    employeeActivityDtl = new EmployeeActivityDtl();
                    employeeActivityDtl.setEmpId(employeeMaster);
                    employeeActivityDtl.setActivity("logout");
                    employeeActivityDtl.setStartTime(CustomConvert.javaDateToTimeStamp(date));
                    employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(date));
                    employeeActivityDtl.setRespectiveLoginId(resLoginId);
                    employeeActivityDtl.setReasonId(reasonMst);
                    employeeActivityDtl.setReasonCd(reasonMst.getReasonCd());
                    employeeActivityDtl.setReasonDesc(reasonMst.getReasonDesc());
                    employeeActivityDtlService.save(employeeActivityDtl, employeeMaster);
                }
                List<TenancyEmployeeMap> tenancyEmployeeMaplst = tenancyEmployeeMapService
                        .findVidyoTenantUrlByEmpId(employeeMaster.getId());
                for (TenancyEmployeeMap tenancyEmployeeMaplst1 : tenancyEmployeeMaplst) {
                    tenancyEmployeeMapService.deleteTenancyEmployeeMap(tenancyEmployeeMaplst1);
                }

                agentLoginResponse.setSuccMsg(this.MSG_SUCCESS);
                agentLoginResponse.setValidateMsg("");
                logger.info(">>>>Logout Successfully>>> Employee UserId: " + employeeMaster.getLoginId()
                        + " Employee Name:" + employeeMaster.getFirstName() + " " + employeeMaster.getMidName() + " "
                        + employeeMaster.getLastName() + ".");
            } else {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Please Provide valid information!");
                logger.info(
                        ">>>>Logout Successfully, but NO User Details found in this session, OR is requesting again after Logout.");
                throw ccmsRestException;

            }
            return agentLoginResponse;
        } else {
            CCMSRestException ccmsRestException = new CCMSRestException();
            ccmsRestException.setErrorCode("418");
            ccmsRestException.setErrorMessage("Please Provide valid information!");
            throw ccmsRestException;
        }
    }

    @Override
    public AgentLoginResponse getAgentSelfView(String credential) throws Exception {
        AgentLoginResponse agentLoginResponse;
        String roomlink = null;
        String roomname = null;
        String entityId = null;

        /**
         * @Purpose To check whether this method parameter credential is null or
         * not
         */
        if (credential != null && credential.length() > 0) {
            JSONObject cred = new JSONObject(credential);
            String employeeId = cred.getString("empId");

            if (employeeId != null && employeeId.length() > 0) {
                Long empId = Long.parseLong(employeeId);
                EmployeeMst empMst = employeeMstService.findEmployeeMstById(empId);

                //List<EmployeeCallStatus> empClStatusList = null;
                List<TenancyEmployeeMap> currentAgent = null;

                /**
                 * @purpose: Check whether return object as empMst is null or
                 * not, if not null then generate a random token and a roomId
                 * for this employee and make this employee ready to take the
                 * call.
                 */
                if (null != empMst) {
                    /**
                     * @purpose empClStatusList object is used to find if this
                     * employee's has any entry in the employee_call_status
                     * table or not. If found the update the existing to true
                     * else create a new callStatus entity and set the status to
                     * true and save it to the employee_call_status table.
                     */
                    //empClStatusList = employeeCallStatusService.findEmployeeCallStatusByEmpId(empMst);

                    /**
                     * @purpose currentAgent is list of tenancy_employee_map
                     * table objects. It is in general single entry against each
                     * logged in employee is valid. Based on this the multiple
                     * employee with same credential is detected.
                     */
                    currentAgent = tenancyEmployeeMapService.findVidyoTenantUrlByEmpId(empMst.getId());

                    /**
                     * @purpose To generate a resourceId as room and token as
                     * token as follows
                     */
                    Long number = (long) Math.floor(Math.random() * 9000000L) + 1000000L;
                    roomname = empMst.getLoginId() + number;

                    VidyoAccessUser vidyoAccessUser = new VidyoAccessUser(Constants.vidyoportalUserServiceWSDL);
                    String ret = vidyoAccessUser.createRoom(Constants.adminUserId, Constants.adminPwd, roomname, Constants.vidyoportalUserServiceWSDL);
                    roomlink = ret.split(",")[0];
                    entityId = ret.split(",")[1];

                    /**
                     * End of token generation
                     */
                } else {
                    CCMSRestException ccmsRestException = new CCMSRestException();
                    ccmsRestException.setErrorCode("418");
                    ccmsRestException.setErrorMessage("Please Provide valid credential!");
                    throw ccmsRestException;
                }

                /**
                 * @purpose To check whether there exist an entry corresponding
                 * to this employee in tenancy_employee_map table or not. If
                 * found then update with the above generated resourceId and
                 * token and if not found, then create a tenancyEmployeeMap
                 * object and set it true and save it in the
                 * tenancy_employee_map table.
                 */
                if (!currentAgent.isEmpty() && currentAgent.size() > 0) {
                    for (TenancyEmployeeMap currentAgent1 : currentAgent) {
                        currentAgent1.setRoomName(roomname);
                        currentAgent1.setRoomLink(roomlink);
                        currentAgent1.setEntityId(entityId);
                        tenancyEmployeeMapService.saveTenancyEmployeeMap(currentAgent1);
                    }
                } else {
                    TenancyEmployeeMap tenancyEmployeeMap = new TenancyEmployeeMap();
                    tenancyEmployeeMap.setEmpId(empMst);
                    tenancyEmployeeMap.setRoomLink(roomlink);
                    tenancyEmployeeMap.setRoomName(roomname);
                    tenancyEmployeeMap.setEntityId(entityId);

                    tenancyEmployeeMapService.saveTenancyEmployeeMap(tenancyEmployeeMap);
                }

                /**
                 * @purpose: Create a new AgentLoginResponse object in order to
                 * send the response.
                 */
                agentLoginResponse = new AgentLoginResponse();
                agentLoginResponse.setRoomLink(roomlink);
                agentLoginResponse.setRoomName(roomname);
                agentLoginResponse.setEntityId(entityId);

                EmployeeActivityDtl employeeActivityDtl = new EmployeeActivityDtl();
                employeeActivityDtl.setActivity("Self View");
                employeeActivityDtl.setEmpId(empMst);
                employeeActivityDtl.setStartTime(CustomConvert.javaDateToTimeStamp(new Date()));
                ReasonMst reasonmst = reasonMstService.findReasonMstByReasonType("SelfView");
                employeeActivityDtl.setReasonId(reasonmst);
                employeeActivityDtl.setReasonCd(reasonmst.getReasonCd());
                employeeActivityDtl.setReasonDesc(reasonmst.getReasonDesc());
                employeeActivityDtlService.save(employeeActivityDtl);

                /**
                 * @purpose To find out the last non ended activity of this
                 * employee by type "not ready". If found then update the record
                 * with the end time.
                 */
                employeeActivityDtl = employeeActivityDtlService
                        .findLastNonEndedActivityByType(empMst.getId(), "not ready");
                if (employeeActivityDtl != null) {
                    employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                    employeeActivityDtlService.save(employeeActivityDtl);
                }

                /**
                 * @purpose To find out the last non ended activity of this
                 * employee by type "Hang Up". If found then update the record
                 * with the end time.
                 */
                employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(empMst.getId(),
                        "Hang Up");
                if (employeeActivityDtl != null) {
                    employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                    employeeActivityDtlService.save(employeeActivityDtl);
                }

                /**
                 * @purpose To find out the last non ended activity of this
                 * employee by type "Self View". If found then update the record
                 * with the end time.
                 */
                /**
                 * return from the method, if success
                 */
                return agentLoginResponse;
            } else {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Please Provide valid credential!");
                throw ccmsRestException;
            }

        } else {
            CCMSRestException ccmsRestException = new CCMSRestException();
            ccmsRestException.setErrorCode("418");
            ccmsRestException.setErrorMessage("Please Provide valid credential!");
            throw ccmsRestException;
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.rank.ccms.rest.service.CCMSRESTControllerService#
     * getCustomerInfo( String credential)
     */
    @Override
    public CustomerDto getCustomerInfo(String credential) throws Exception {
        String callId = null;
        CustomerDto customerDto = new CustomerDto();

        /**
         * @Purpose To check whether method parameter credential is null or not
         */
        if (credential != null && credential.length() > 0) {
            JSONObject callInformation = new JSONObject(credential);
            if (callInformation.has("callId")) {
                callId = callInformation.getString("callId");
            } else {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Please Provide valid information!");
                throw ccmsRestException;
            }

        } else {
            CCMSRestException ccmsRestException = new CCMSRestException();
            ccmsRestException.setErrorCode("418");
            ccmsRestException.setErrorMessage("Please Provide valid information!");
            throw ccmsRestException;
        }
        if (callId != null) {
            CallMst cm = callMstService.findCallMstById(Long.parseLong(callId));
            if (cm != null) {
                CustomerMst custMst = customerMstService.findCustomerMstByCustId(cm.getCustId());
                if (custMst != null) {
                    customerDto.setCustomerName(custMst.getFirstName() + " " + custMst.getLastName());
                    customerDto.setCellPhone(custMst.getCellPhone().toString());
                    customerDto.setEmail(custMst.getEmail());
                    customerDto.setNationality(custMst.getNatinality());
                    ServiceMst service = serviceMstService.findAllServiceMstById(cm.getServiceId());
                    if (service != null) {
                        customerDto.setService(service.getServiceName());
                    }
                    CategoryMst category = categoryMstService.findCategoryMstById(cm.getCategoryId());
                    if (category != null) {
                        customerDto.setSegment(category.getCatgName());
                    }
                    LanguageMst languageMst = langMstService.findAllLanguageMstById(cm.getLanguageId());
                    if (languageMst != null) {
                        customerDto.setLanguage(languageMst.getLanguageName());
                    }
                    //customerDto.setService(custMst.get);
                }
            }
        }

        return customerDto;
    }

    @Override
    public IncomingCallResponse agentReceiveCall(String credential) throws Exception {
        String callId = null;
        String empId = null;
        String userIdText = null;
        CustomerDeviceDtl cdd = null;
        String custStatus = "No";
        String roomLink;
        IncomingCallResponse callResponse = new IncomingCallResponse();

        /**
         * @Purpose To check whether method parameter credential is null or not
         */
        if (credential != null && credential.length() > 0) {
            JSONObject callInformation = new JSONObject(credential);
            if (callInformation.has("callId") && callInformation.has("empId")) {
                callId = callInformation.getString("callId");
                empId = callInformation.getString("empId");
            } else {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Please Provide valid information!");
                throw ccmsRestException;
            }

        } else {
            CCMSRestException ccmsRestException = new CCMSRestException();
            ccmsRestException.setErrorCode("418");
            ccmsRestException.setErrorMessage("Please Provide valid information!");
            throw ccmsRestException;
        }
        if (callId != null) {
            CallMst cm = callMstService.findCallMstById(Long.parseLong(callId));
            if (cm != null) {
                CustomerMst customerMst = customerMstService.findCustomerMstByCustId(cm.getCustId());
                EmployeeMst empMst = employeeMstService.findEmployeeMstById(Long.parseLong(empId));
                if (empMst != null) {
                    Timestamp fromtime = CustomConvert.javaDateToTimeStamp(new Date());

                    if (!cm.getCallType().equalsIgnoreCase("Scheduled")) {

                        ReasonMst reasonMst = reasonMstService.findReasonMstByReasonCode("CLS01");
                        EmployeeActivityDtl employeeActDtl = new EmployeeActivityDtl();
                        employeeActDtl.setEmpId(empMst);
                        employeeActDtl.setActivity("Call Started");
                        employeeActDtl.setStartTime(fromtime);
                        employeeActDtl.setReasonId(reasonMst);
                        employeeActDtl.setReasonCd(reasonMst.getReasonCd());
                        employeeActDtl.setReasonDesc(reasonMst.getReasonDesc());
                        employeeActDtl.setCallMstId(cm.getId());
                        employeeActivityDtlService.save(employeeActDtl, empMst);
                    }
                    EmployeeActivityDtl employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(empMst.getId(), "NEXT CALL");
                    if (employeeActivityDtl != null) {
                        employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                        employeeActivityDtlService.save(employeeActivityDtl);

                    }

                    cm.setStartTime(fromtime);
                    cm.setCallStatus("Active");
                    CallMst callMstLocal = callMstService.saveCallMst(cm);

                    CallRecords callRecords = null;
                    if (null != callMstLocal) {
                        callRecords = callRecordsService.findCallRecordsByCallId(callMstLocal.getId(), (long) 0);
                    }
                    if (null == callRecords) {
                        callRecords = new CallRecords();
                    }

                    List<TenancyEmployeeMap> tenancyEmployeeMaplist = tenancyEmployeeMapService.findVidyoTenantUrlByEmpId(empMst.getId());
                    List<Long> unsortList = new ArrayList<>();
                    for (TenancyEmployeeMap tenancyEmployeeMaplist1 : tenancyEmployeeMaplist) {
                        unsortList.add(tenancyEmployeeMaplist1.getId());
                    }
                    Collections.sort(unsortList);

                    TenancyEmployeeMap tenancyEmployeeMap = tenancyEmployeeMapService.findById(unsortList.get(unsortList.size() - 1));
                    roomLink = tenancyEmployeeMap.getRoomLink();
                    String customerID = cm.getCustId();
                    callRecords.setCallId(cm);
                    callRecords.setEmpId(empMst);
                    callRecords.setCustomerId(customerID);
                    String roomName = tenancyEmployeeMap.getRoomName();
                    callRecords.setRoomName(roomName);
                    callRecords.setExternalPlaybackLink("Not Saved");
                    callRecords.setRecorderId("");

                    callRecordsService.saveCallRecord(callRecords);

                    if (null != callMstLocal) {
                        if (null != callMstLocal.getCustId() && !"".equals(callMstLocal.getCustId())) {
                            //request.getSession().setAttribute("userNameText", callMstLocal.getCustId());
                            userIdText = callMstLocal.getCustId();
                            Set<String> custIdSet = new TreeSet<>();

                            if (!CallScheduler.customerCallDetailsMap.isEmpty()) {
                                custIdSet = (Set<String>) CallScheduler.customerCallDetailsMap.get(callMstLocal.getCustId());
                            }

                            if (null == custIdSet) {
                                custIdSet = new TreeSet<>();
                            }

                            custIdSet.add(callMstLocal.getCustId() + "");
                            CallScheduler.customerCallDetailsMap.put(callMstLocal.getId().toString(), custIdSet);
                            //request.getSession().setAttribute("callMst", callMstLocal);
                        }
                    }
                    CallDtl callDtl = new CallDtl();
                    //request.getSession().setAttribute("incomingCallType", cem.getCallType());
                    callDtl.setCallMstId(callMstLocal);
                    callDtl.setActiveFlg(true);
                    callDtl.setDeleteFlg(false);
                    callDtl.setHandeledById(empMst);
                    callDtl.setStartTime(fromtime);
                    callDtl.setCallTypeInfo(cm.getCallType());
                    callDtlService.saveCallDtl(callDtl);

                    if (null != userIdText && !"".equals(userIdText) && cm.getCallType().equalsIgnoreCase("Scheduled")) {
                        CustomerMst custm = customerMstService.findCustomerMstByCustId(userIdText);
                        logger.info("userIdText:::" + userIdText);
                        cdd = customerDeviceDtlService.findCustomerDeviceDtlByCustId(custm);

                        if (null != cdd) {
                            if (0 == cdd.getLoginInfo() && custm.getAccountNo() != null && custm.getAccountNo().length() > 0) {
                                custStatus = "No";
                            } else if (0 == cdd.getLoginInfo()) {
                                custStatus = "Yes";
                            }
                            if (2 == cdd.getLoginInfo()) {
                                custStatus = "Busy";
                            }
                            if (1 == cdd.getLoginInfo() && custm.getAccountNo() != null && custm.getAccountNo().length() > 0) {
                                custStatus = "Yes";
                            } else if (1 == cdd.getLoginInfo()) {
                                custStatus = "Busy";
                            }

                        }
                    }

                    String room = roomLink;
                    if (custStatus.equalsIgnoreCase("Yes")) {
                        if (null != cdd && null != callMstLocal) {
                            String osType = cdd.getMobileOsType();
//                                                roomLink = Constants.conferenceUrl + "?key=" + roomLink;
                            if (null != osType) {
                                if (!osType.equalsIgnoreCase("WEB")) {
                                    CustomerMst custm = customerMstService.findCustomerMstByCustId(userIdText);
                                    try {
                                        logger.info("Before Sending SOcket to Customer for Schedule#########");

                                    } catch (Exception ex) {
                                        java.util.logging.Logger.getLogger(CCMSRESTControllerServiceImpl.class
                                                .getName()).log(Level.SEVERE, null, ex);
                                    }
                                }

                                Set<String> custIdSet = new TreeSet<>();
                                if (!CallScheduler.customerCallDetailsMap.isEmpty()) {
                                    custIdSet = (Set<String>) CallScheduler.customerCallDetailsMap.get(callMstLocal.getId().toString());
                                }
                                if (null == custIdSet) {
                                    custIdSet = new TreeSet<>();
                                }
                                custIdSet.add(userIdText);
                                CallScheduler.customerCallDetailsMap.put(callMstLocal.getId().toString(), custIdSet);
                                if (customerMst != null && customerMst.getAccountNo() != null && customerMst.getAccountNo().length() > 0) {
                                    cdd.setLoginInfo(2);

                                } else {
                                    cdd.setLoginInfo(1);
                                }
                                customerDeviceDtlService.saveCustomerDeviceDtl(cdd, null);
                                CallMst callMst_L = cm;

                                callMst_L = callMstService.findCallMstById(callMst_L.getId());

                                callMst_L.setCallMedium("Mobile");
                                callMst_L.setRoomName(room);
                                callMstService.saveCallMst(callMst_L);

                            }

                        }

                    }

                }
            }
        }

        return callResponse;
    }

    @Override
    public IncomingCallResponse agentHangupForwardCall(String credential) throws Exception {
        String callId = null;
        String empId = null;
        IncomingCallResponse callResponse = new IncomingCallResponse();

        /**
         * @Purpose To check whether method parameter credential is null or not
         */
        if (credential != null && credential.length() > 0) {
            JSONObject callInformation = new JSONObject(credential);
            if (callInformation.has("callId") && callInformation.has("empId")) {
                callId = callInformation.getString("callId");
                empId = callInformation.getString("empId");
            } else {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Please Provide valid information!");
                throw ccmsRestException;
            }

        } else {
            CCMSRestException ccmsRestException = new CCMSRestException();
            ccmsRestException.setErrorCode("418");
            ccmsRestException.setErrorMessage("Please Provide valid information!");
            throw ccmsRestException;
        }
        if (callId != null) {
            CallMst cm = callMstService.findCallMstById(Long.parseLong(callId));
            if (cm != null) {
                EmployeeMst empMst = employeeMstService.findEmployeeMstById(Long.parseLong(empId));
                if (empMst != null) {
                    logger.info("AFTER FORWARD HANG UP CALL....");

                    try {

                        CallDtl clDtl = callDtlService.findCallDtlByCallMasterIdAndAgentNotForwarded(cm.getId(), empMst.getId());
                        clDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                        clDtl.setDeleteFlg(false);
                        clDtl.setAgentComments("Call forwarded Successfully");
                        clDtl.setActiveFlg(true);
                        callDtlService.saveCallDtl(clDtl);
                        CallMst clMst = callMstService.findNonDeletedCallMstById(clDtl.getCallMstId().getId());
                        List<CallDtl> findOtherNonEndedCallDetailFromSameCallMst = callDtlService.findCallDtlByCallMasterIdAndAgent(clMst.getId(), empMst.getId());

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

                        ReasonMst reason = reasonMstService.findReasonMstByReasonCode("HUP01");
                        employeeActivityDtl.setReasonId(reason);
                        employeeActivityDtl.setReasonCd(reason.getReasonCd());
                        employeeActivityDtl.setReasonDesc(reason.getReasonDesc());
                        employeeActivityDtl.setCallMstId(cm.getId());
                        employeeActivityDtlService.save(employeeActivityDtl);
                        if (!cm.getCallType().equalsIgnoreCase("Scheduled")) {

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

                    } catch (ParseException ex) {
                        logger.error("AfterForwardHangUp2:Error:" + ex.getMessage());

                    }
                    callResponse.setStatus("Success");

                }
            }
        }

        return callResponse;
    }

    @Override
    public IncomingCallResponse agentReceiveForwardCall(String credential) throws Exception {
        String callId = null;
        String empId = null;
        IncomingCallResponse callResponse = new IncomingCallResponse();

        /**
         * @Purpose To check whether method parameter credential is null or not
         */
        if (credential != null && credential.length() > 0) {
            JSONObject callInformation = new JSONObject(credential);
            if (callInformation.has("callId") && callInformation.has("empId")) {
                callId = callInformation.getString("callId");
                empId = callInformation.getString("empId");
            } else {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Please Provide valid information!");
                throw ccmsRestException;
            }

        } else {
            CCMSRestException ccmsRestException = new CCMSRestException();
            ccmsRestException.setErrorCode("418");
            ccmsRestException.setErrorMessage("Please Provide valid information!");
            throw ccmsRestException;
        }
        if (callId != null) {
            CallMst cm = callMstService.findCallMstById(Long.parseLong(callId));
            if (cm != null) {
//                CustomerMst customerMst = customerMstService.findCustomerByUserID(cm.getCustId());
                EmployeeMst empMst = employeeMstService.findEmployeeMstById(Long.parseLong(empId));
                if (empMst != null) {
                    Timestamp fromtime = CustomConvert.javaDateToTimeStamp(new Date());

                    if (!cm.getCallType().equalsIgnoreCase("Scheduled")) {

                        ReasonMst reasonMst = reasonMstService.findReasonMstByReasonCode("CLS01");
                        EmployeeActivityDtl employeeActDtl = new EmployeeActivityDtl();
                        employeeActDtl.setEmpId(empMst);
                        employeeActDtl.setActivity("Call Started");
                        employeeActDtl.setStartTime(fromtime);
                        employeeActDtl.setReasonId(reasonMst);
                        employeeActDtl.setReasonCd(reasonMst.getReasonCd());
                        employeeActDtl.setReasonDesc(reasonMst.getReasonDesc());
                        employeeActDtl.setCallMstId(cm.getId());
                        employeeActivityDtlService.save(employeeActDtl, empMst);
                    }
                    EmployeeActivityDtl employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(empMst.getId(), "NEXT CALL");
                    if (employeeActivityDtl != null) {
                        employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                        employeeActivityDtlService.save(employeeActivityDtl);

                    }
                    callResponse.setStatus("Success");

                }
            }
        }

        return callResponse;
    }

    @Override
    public IncomingCallResponse agentMissedCall(String credential) throws Exception {
        String callId = null;
        String empId = null;
        String usertext = null;
        CustomerDeviceDtl cdd = null;
        CallDtl callDetail;

        IncomingCallResponse callResponse = new IncomingCallResponse();

        if (credential != null && credential.length() > 0) {
            JSONObject callInformation = new JSONObject(credential);
            if (callInformation.has("callId") && callInformation.has("empId")) {
                callId = callInformation.getString("callId");
                empId = callInformation.getString("empId");
            } else {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Please Provide valid information!");
                throw ccmsRestException;
            }

        } else {
            CCMSRestException ccmsRestException = new CCMSRestException();
            ccmsRestException.setErrorCode("418");
            ccmsRestException.setErrorMessage("Please Provide valid information!");
            throw ccmsRestException;
        }

        if (callId != null) {
            CallMst callMstLocal = callMstService.findCallMstById(Long.parseLong(callId));
            if (callMstLocal != null) {

                CustomerMst cm = customerMstService.findCustomerMstByCustId(callMstLocal.getCustId());
                EmployeeMst empMst = employeeMstService.findEmployeeMstById(Long.parseLong(empId));

                callMstLocal = callMstService.findCallMstById(callMstLocal.getId());
                callMstLocal.setCallStatus("Missed");
                callMstLocal.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                callMstService.saveCallMst(callMstLocal);
                if (null != cm) {
                    usertext = cm.getCustId();
                }

                /**
                 * two socket is sending to same recipient 1. Hangup and 2.
                 * Missed
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

                employeeActivityDtl.setCallMstId(callMstLocal.getId());

                employeeActivityDtlService.save(employeeActivityDtl);

                List<CallDtl> calldtlList = callDtlService.findCallDtlByCallMasterId(callMstLocal.getId());

                if (!(calldtlList.isEmpty())) {

                    callDetail = calldtlList.get(0);
                    callDetail.setAgentComments("Not Recieved By Agent");
                    callDetail.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                    callDtlService.saveCallDtl(callDetail);
                }

                try {
                    if (callMstLocal.getCallType().equalsIgnoreCase("Schedule")) {
                        SocketMessage.send(callScheduler.getAdminSocket(), usertext,
                                "schmissed#" + callMstLocal.getId());
                    } else {
                        SocketMessage.send(callScheduler.getAdminSocket(), usertext, "missed#" + callMstLocal.getId());
                    }
                    callResponse.setStatus("Success");
                } catch (Exception e) {
                    logger.error("callAbondaned1:SocketError:" + e.getMessage());
                }

            }
        }

        return callResponse;
    }

    @Override
    public IncomingCallResponse agentMissedForwardCall(String credential) throws Exception {
        String callId = null;
        String empId = null;
        IncomingCallResponse callResponse = new IncomingCallResponse();

        if (credential != null && credential.length() > 0) {
            JSONObject callInformation = new JSONObject(credential);
            if (callInformation.has("callId") && callInformation.has("empId")) {
                callId = callInformation.getString("callId");
                empId = callInformation.getString("empId");
            } else {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Please Provide valid information!");
                throw ccmsRestException;
            }

        } else {
            CCMSRestException ccmsRestException = new CCMSRestException();
            ccmsRestException.setErrorCode("418");
            ccmsRestException.setErrorMessage("Please Provide valid information!");
            throw ccmsRestException;
        }

        if (callId != null) {
            CallMst callMstLocal = callMstService.findCallMstById(Long.parseLong(callId));
            if (callMstLocal != null) {

                CustomerMst cm = customerMstService.findCustomerMstByCustId(callMstLocal.getCustId());
                EmployeeMst empMst = employeeMstService.findEmployeeMstById(Long.parseLong(empId));

                ForwardedCall forwardedCalllocal = forwardedCallService.findActiveForwardedCallByEmployeeMst(empMst);
                if (forwardedCalllocal != null) {
                    EmployeeMst oldEmpMst = employeeMstService.findEmployeeMstById(forwardedCalllocal.getPrevEmpId());

                    callResponse.setStatus("Missed forward call");
                    forwardedCalllocal.setActiveFlg(false);
                    forwardedCalllocal.setForwardStatus("missed");
                    forwardedCalllocal = forwardedCallService.saveForwardedCalls(forwardedCalllocal, empMst);

                    EmployeeMst em = forwardedCalllocal.getEmpId();
                    EmployeeActivityDtl activityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(em.getId(), "NEXT CALL");
                    if (activityDtl != null) {
                        try {
                            activityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                            employeeActivityDtlService.save(activityDtl);

                            EmployeeActivityDtl empActDtl = new EmployeeActivityDtl();
                            empActDtl.setActivity("not ready");
                            empActDtl.setEmpId(em);
                            ReasonMst reasonmst = reasonMstService.findReasonMstByReasonType("Not Ready");
                            empActDtl.setReasonId(reasonmst);
                            empActDtl.setReasonCd(reasonmst.getReasonCd());
                            empActDtl.setReasonDesc(reasonmst.getReasonDesc());
                            empActDtl.setStartTime(CustomConvert.javaDateToTimeStamp(new Date()));
                            employeeActivityDtlService.save(empActDtl);
                        } catch (ParseException ex) {
                            CCMSRestException ccmsRestException = new CCMSRestException();
                            ccmsRestException.setErrorCode("418");
                            ccmsRestException.setErrorMessage("Please Provide valid information!");
                            throw ccmsRestException;
                        }
                    }
                    List<EmployeeCallStatus> empClStatusList = employeeCallStatusService.findEmployeeCallStatusByEmpId(empMst);

                    EmployeeCallStatus empCallStatus = null;
                    if (!empClStatusList.isEmpty()) {
                        for (EmployeeCallStatus empstatus : empClStatusList) {
                            empCallStatus = empstatus;
                        }
                        if (null != empCallStatus) {
                            empCallStatus.setStatus(false);
                            try {
                                empCallStatus.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                            } catch (ParseException ex) {
                                logger.error(ex.getMessage());
                            }
                            employeeCallStatusService.saveEmployeeCallStatus(empCallStatus);
                        }
                    } else {
                        empCallStatus = new EmployeeCallStatus();
                        empCallStatus.setEmpId(empMst);
                        empCallStatus.setCallCount(1);
                        empCallStatus.setStatus(false);
                        try {
                            empCallStatus.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                        } catch (ParseException ex) {
                            logger.error(ex.getMessage());
                        }
                        employeeCallStatusService.saveEmployeeCallStatus(empCallStatus);
                    }
                    try {

                        SocketMessage.send(callScheduler.getAdminSocket(), oldEmpMst.getLoginId(), "missedforward#" + callMstLocal.getId());

                        callResponse.setStatus("Success");
                    } catch (Exception e) {
                        logger.error("callAbondaned1:SocketError:" + e.getMessage());
                    }

                }
                //------------------------------------------------------------------------     

            }
        }

        return callResponse;
    }

    @Override
    public IncomingCallResponse agentPickUpCall(String credential) throws Exception {
        String callId = null;

        IncomingCallResponse callResponse = new IncomingCallResponse();

        if (credential != null && credential.length() > 0) {
            JSONObject callInformation = new JSONObject(credential);
            if (callInformation.has("callId") && callInformation.has("empId")) {
                callId = callInformation.getString("callId");

            } else {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Please Provide valid information!");
                throw ccmsRestException;
            }

        } else {
            CCMSRestException ccmsRestException = new CCMSRestException();
            ccmsRestException.setErrorCode("418");
            ccmsRestException.setErrorMessage("Please Provide valid information!");
            throw ccmsRestException;
        }

        if (callId != null) {
            CallMst callMstLocal = callMstService.findCallMstById(Long.parseLong(callId));
            if (callMstLocal != null) {

                try {
                    callMstLocal.setAgentPickupTime(CustomConvert.javaDateToTimeStamp(new Date()));

                } catch (ParseException ex) {
                    logger.error("Error:" + ex.getMessage());
                }
                callMstService.saveCallMst(callMstLocal);
                callResponse.setStatus("Success");

            }
        }

        return callResponse;
    }

    @Override
    public IncomingCallResponse agentPickUpForwardCall(String credential) throws Exception {
        String callId = null;
        String empId = null;

        IncomingCallResponse callResponse = new IncomingCallResponse();

        if (credential != null && credential.length() > 0) {
            JSONObject callInformation = new JSONObject(credential);
            if (callInformation.has("callId") && callInformation.has("empId")) {
                callId = callInformation.getString("callId");
                empId = callInformation.getString("empId");
            } else {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Please Provide valid information!");
                throw ccmsRestException;
            }

        } else {
            CCMSRestException ccmsRestException = new CCMSRestException();
            ccmsRestException.setErrorCode("418");
            ccmsRestException.setErrorMessage("Please Provide valid information!");
            throw ccmsRestException;
        }

        if (callId != null) {
            CallMst callMstLocal = callMstService.findCallMstById(Long.parseLong(callId));
            if (callMstLocal != null) {

                EmployeeMst empMst = employeeMstService.findEmployeeMstById(Long.parseLong(empId));

                ForwardedCall forwardedCall = forwardedCallService.findActiveForwardedCallByEmployeeMst(empMst);
                if (forwardedCall != null) {
                    forwardedCall.setActiveFlg(false);
                    try {
                        forwardedCall.setCallPickupTime(CustomConvert.javaDateToTimeStamp(new Date()));
                    } catch (ParseException ex) {
                        logger.error(ex.getMessage());
                    }
                    forwardedCall = forwardedCallService.saveForwardedCalls(forwardedCall, empMst);
                    //request.getSession().setAttribute("ForwardedCall", forwardedCall);

                    String roomUrl = forwardedCall.getRoomLink();
                    String roomName = forwardedCall.getRoomName();

                    List<TenancyEmployeeMap> currentAgent = tenancyEmployeeMapService.findVidyoTenantUrlByEmpId(empMst.getId());
                    for (TenancyEmployeeMap currentAgent1 : currentAgent) {
                        currentAgent1.setRoomLink(roomUrl);
                        currentAgent1.setRoomName(roomName);

                        tenancyEmployeeMapService.saveTenancyEmployeeMap(currentAgent1);
                    }
                }
                EmployeeCallStatus employeeCallStatus = new EmployeeCallStatus();

                List<EmployeeCallStatus> employeeCallStatusList = employeeCallStatusService.findEmployeeCallStatusByEmpId(empMst);

                if (!employeeCallStatusList.isEmpty()) {
                    for (EmployeeCallStatus empstatus : employeeCallStatusList) {
                        employeeCallStatus = empstatus;
                    }
                    Integer count = employeeCallStatus.getCallCount() + 1;
                    employeeCallStatus.setCallCount(count);
                    employeeCallStatus.setStatus(false);
                    employeeCallStatus = employeeCallStatusService.saveEmployeeCallStatus(employeeCallStatus);
                } else {
                    employeeCallStatus.setEmpId(empMst);
                    employeeCallStatus.setCallCount(1);
                    employeeCallStatus.setStatus(false);
                    employeeCallStatus = employeeCallStatusService.saveEmployeeCallStatus(employeeCallStatus);
                }
                if (null != forwardedCall) {

                    CallMst localcallMst = callMstService.findCallMstById(forwardedCall.getCallId().getId());
                    CallDtl callDtl = new CallDtl();
                    callDtl.setCallMstId(localcallMst);
                    callDtl.setActiveFlg(true);
                    callDtl.setDeleteFlg(false);
                    callDtl.setHandeledById(empMst);
                    try {
                        callDtl.setStartTime(CustomConvert.javaDateToTimeStamp(new Date()));

                    } catch (ParseException ex) {
                        logger.error(ex.getMessage());
                    }
                    callDtl.setCallTypeInfo("Forward");
                    callDtl = callDtlService.saveCallDtl(callDtl);

                    EmployeeActivityDtl employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(empMst.getId(), "NEXT CALL");
                    if (employeeActivityDtl != null) {
                        try {
                            employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));

                        } catch (ParseException ex) {
                            logger.error(ex.getMessage());
                        }
                        employeeActivityDtlService.save(employeeActivityDtl);

                    }
                    employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(empMst.getId(), "HOLD CALL");
                    if (employeeActivityDtl != null) {
                        try {
                            employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));

                        } catch (ParseException ex) {
                            logger.error(ex.getMessage());
                        }
                        employeeActivityDtlService.save(employeeActivityDtl);

                    }

                    forwardedCall.setCallDtlId(callDtl.getId());
                    forwardedCall = forwardedCallService.saveForwardedCalls(forwardedCall, empMst);

                    EmployeeMst forwrdingagent = employeeMstService.findEmployeeMstById(forwardedCall.getPrevEmpId());

                    List<EmployeeCallStatus> employeeCallStatusList1 = employeeCallStatusService.findEmployeeCallStatusByEmpId(forwrdingagent);

                    if (!employeeCallStatusList1.isEmpty()) {
                        for (EmployeeCallStatus empstatus : employeeCallStatusList1) {
                            employeeCallStatus = empstatus;
                        }
                        Integer count = employeeCallStatus.getCallCount() + 1;
                        employeeCallStatus.setCallCount(count);
                        employeeCallStatus.setStatus(false);
                        employeeCallStatusService.saveEmployeeCallStatus(employeeCallStatus);
                    } else {
                        employeeCallStatus.setEmpId(forwrdingagent);
                        employeeCallStatus.setCallCount(1);
                        employeeCallStatus.setStatus(false);
                        employeeCallStatusService.saveEmployeeCallStatus(employeeCallStatus);
                    }

                    employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(forwrdingagent.getId(), "Call Started");

                    if (employeeActivityDtl != null) {
                        employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                        employeeActivityDtlService.save(employeeActivityDtl);
                    }

                    employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(forwrdingagent.getId(), "Incoming Call");

                    if (employeeActivityDtl != null) {
                        try {
                            employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));

                        } catch (ParseException ex) {
                            logger.error(ex.getMessage());
                        }
                        employeeActivityDtlService.save(employeeActivityDtl);
                    }

                    employeeActivityDtl = new EmployeeActivityDtl();
                    employeeActivityDtl.setActivity("Call forwarded");
                    employeeActivityDtl.setCallMstId(callMstLocal.getId());
                    employeeActivityDtl.setEmpId(forwrdingagent);

                    employeeActivityDtl.setStartTime(CustomConvert.javaDateToTimeStamp(new Date()));
                    employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                    ReasonMst reason = reasonMstService.findReasonMstByReasonCode("FWD01");
                    employeeActivityDtl.setReasonId(reason);
                    employeeActivityDtl.setReasonCd(reason.getReasonCd());
                    employeeActivityDtl.setReasonDesc(reason.getReasonDesc());
                    employeeActivityDtlService.save(employeeActivityDtl);
                    try {
                        SocketMessage.send(callScheduler.getAdminSocket(), forwrdingagent.getLoginId(), "pickedforward#" + callMstLocal.getId());
                        callResponse.setStatus("Success");

                    } catch (Exception e) {
                        logger.error("callAbondaned1:SocketError:" + e.getMessage());
                    }
                }
            }
        }

        return callResponse;
    }

    @Override
    public IncomingCallResponse agentHoldList(String credential) throws Exception {
        String callId = null;
        String empId = null;
        List<PerticipentDto> holdUnHoldDtoList;
        IncomingCallResponse callResponse = new IncomingCallResponse();

        if (credential != null && credential.length() > 0) {
            JSONObject callInformation = new JSONObject(credential);
            if (callInformation.has("callId") && callInformation.has("empId")) {
                callId = callInformation.getString("callId");
                empId = callInformation.getString("empId");
            } else {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Please Provide valid information!");
                throw ccmsRestException;
            }

        } else {
            CCMSRestException ccmsRestException = new CCMSRestException();
            ccmsRestException.setErrorCode("418");
            ccmsRestException.setErrorMessage("Please Provide valid information!");
            throw ccmsRestException;
        }

        if (callId != null) {
            CallMst callMstLocal = callMstService.findCallMstById(Long.parseLong(callId));
            if (callMstLocal != null) {

                CustomerMst cm = customerMstService.findCustomerMstByCustId(callMstLocal.getCustId());
                EmployeeMst empMst = employeeMstService.findEmployeeMstById(Long.parseLong(empId));

                holdUnHoldDtoList = new ArrayList<>();

                //callMster = callMasterService.findCallMstById(callMster.getId());
                //This is for add customer........
                PerticipentDto lPerticipentDto = new PerticipentDto();
                if (!callMstLocal.getCallType().equalsIgnoreCase("Dial Call")) {
                    CustomerMst lCustomerMst = customerMstService.findCustomerMstByCustId(callMstLocal.getCustId());
                    lPerticipentDto.setId((long) 0);
                    lPerticipentDto.setLoginId(callMstLocal.getCustId());
                    lPerticipentDto.setName(lCustomerMst.getFirstName());
                    lPerticipentDto.setNameAndLoginId(lCustomerMst.getFirstName() + "(" + lCustomerMst.getEmail() + ")");
                    holdUnHoldDtoList.add(lPerticipentDto);
                } else {
                    EmployeeMst lEmployeeMst;
                    List<CallDtl> dtlList = callDtlService.findCallDtlByCallMasterIdAndCallTypeInfo(callMstLocal.getId(), "Dial");
                    if (!dtlList.isEmpty()) {

                        lEmployeeMst = employeeMstService.findEmployeeMstById(dtlList.get(0).getHandeledById().getId());
                    } else {
                        lEmployeeMst = employeeMstService.findAllEmployeeByUserId(callMstLocal.getCustId());

                    }

                    if (empMst.getId() != (long) lEmployeeMst.getId()) {

                        lPerticipentDto.setId(lEmployeeMst.getId());

                        lPerticipentDto.setLoginId(lEmployeeMst.getLoginId());
                        String name;
                        if (lEmployeeMst.getMidName() != null && !lEmployeeMst.getMidName().equals("")) {
                            name = lEmployeeMst.getFirstName() + " " + lEmployeeMst.getMidName() + " " + lEmployeeMst.getLastName();
                        } else {
                            name = lEmployeeMst.getFirstName() + " " + lEmployeeMst.getLastName();
                        }
                        lPerticipentDto.setName(name);
                        lPerticipentDto.setNameAndLoginId(name + "(" + lEmployeeMst.getEmail() + ")");
                        holdUnHoldDtoList.add(lPerticipentDto);
                    }

                }

                List<CallDtl> localCallDtlList = callDtlService.findNonEndedCallDtlByCallMasterIdAndExcludeCurrentEmployee(callMstLocal.getId(), empMst.getId());

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
                    lPerticipentDto.setName(name);
                    lPerticipentDto.setNameAndLoginId(name + "(" + lEmployeeMst.getEmail() + ")");

                    holdUnHoldDtoList.add(lPerticipentDto);

                }
                callResponse.setStatus("Success");
                callResponse.setHoldUnHoldDtoList(holdUnHoldDtoList);
            }
        }

        return callResponse;
    }

    @Override
    public IncomingCallResponse agentHold(String credential) throws Exception {
        String callId = null;
        String empId = null;
        String name = null;
        String uid = null;
        //List<PerticipentDto> holdUnHoldDtoList = new ArrayList<>();
        IncomingCallResponse callResponse = new IncomingCallResponse();

        if (credential != null && credential.length() > 0) {
            JSONObject callInformation = new JSONObject(credential);
            if (callInformation.has("callId") && callInformation.has("empId")) {
                callId = callInformation.getString("callId");
                empId = callInformation.getString("empId");
                String hold = callInformation.getString("holdList");
                JSONObject holdInformation = new JSONObject(hold);
                name = holdInformation.getString("name");
                uid = holdInformation.getString("loginId");
            } else {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Please Provide valid information!");
                throw ccmsRestException;
            }

        } else {
            CCMSRestException ccmsRestException = new CCMSRestException();
            ccmsRestException.setErrorCode("418");
            ccmsRestException.setErrorMessage("Please Provide valid information!");
            throw ccmsRestException;
        }

        if (callId != null) {
            CallMst callMstLocal = callMstService.findCallMstById(Long.parseLong(callId));
            if (callMstLocal != null) {

                EmployeeMst empMst = employeeMstService.findEmployeeMstById(Long.parseLong(empId));

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

                if (null != uid && !"".equals(uid)) {
                    try {

                        logger.info("sendHold('" + uid + "','" + "hold#" + callMstLocal.getId() + "')");
                        SocketMessage.send(callScheduler.getAdminSocket(), uid, "hold#" + callMstLocal.getId());

                        callResponse.setStatus("Success");
                    } catch (Exception ex) {
                        logger.error(ex.getMessage());

                    }

                }

            }
        }
        return callResponse;
    }

    @Override
    public IncomingCallResponse agentUnHold(String credential) throws Exception {
        String callId = null;
        String empId = null;
        String name = null;
        String uid = null;
        //List<PerticipentDto> holdUnHoldDtoList = new ArrayList<>();
        IncomingCallResponse callResponse = new IncomingCallResponse();

        if (credential != null && credential.length() > 0) {
            JSONObject callInformation = new JSONObject(credential);
            if (callInformation.has("callId") && callInformation.has("empId")) {
                callId = callInformation.getString("callId");
                empId = callInformation.getString("empId");
                String hold = callInformation.getString("holdList");
                JSONObject holdInformation = new JSONObject(hold);
                name = holdInformation.getString("name");
                uid = holdInformation.getString("loginId");
            } else {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Please Provide valid information!");
                throw ccmsRestException;
            }

        } else {
            CCMSRestException ccmsRestException = new CCMSRestException();
            ccmsRestException.setErrorCode("418");
            ccmsRestException.setErrorMessage("Please Provide valid information!");
            throw ccmsRestException;
        }

        if (callId != null) {
            CallMst callMstLocal = callMstService.findCallMstById(Long.parseLong(callId));
            if (callMstLocal != null) {

                //CustomerMst cm = customerMstService.findCustomerByUserID(callMstLocal.getCustId());
                EmployeeMst empMst = employeeMstService.findEmployeeMstById(Long.parseLong(empId));

//                for (PerticipentDto lDto : holdUnHoldDtoList) {
//
//                    if (null != lDto.getLoginId() && !"".equals(lDto.getLoginId())) {
                if (null != uid && !"".equals(uid)) {
                    logger.info("sendUnhold('" + uid + "','" + "unhold#" + callMstLocal.getId() + "')");
                    //RequestContext.getCurrentInstance().execute("sendUnhold('" + lDto.getLoginId() + "','" + "unhold#" + callMstLocal.getId() + "')");
                    SocketMessage.send(callScheduler.getAdminSocket(), uid, "unhold#" + callMstLocal.getId());
                }
                //}
                EmployeeActivityDtl employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(empMst.getId(), "HOLD CALL");
                if (employeeActivityDtl != null) {
                    employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date(System.currentTimeMillis())));
                    employeeActivityDtlService.save(employeeActivityDtl);
                }
                callResponse.setStatus("Success");
            }
        }
        return callResponse;
    }

    @Override
    public IncomingCallResponse getCustomerLoginResponse(HttpServletRequest request, String credential)
            throws Exception {
        IncomingCallResponse incomingCallResPonse = new IncomingCallResponse();
        CustomerMst cm;

        /**
         * @Purpose To check whether method parameter credential is null or not
         */
        if (credential != null && credential.length() > 0) {
            JSONObject cred = new JSONObject(credential);
            String loginId = cred.getString("loginId");
            String loginPassword = cred.getString("password");

            if (loginId == null || loginId.isEmpty() || "".equals(loginId)) {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Please Provide loginId!");
                throw ccmsRestException;

            }

            if (loginPassword == null || loginPassword.isEmpty() || "".equals(loginPassword)) {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Please Provide password!");
                throw ccmsRestException;
            }

            cm = customerMstService.findCustomerMstByCustIdPassword(loginId, loginPassword);
            if (cm != null) {

                incomingCallResPonse.setCustFname(cm.getFirstName());
                incomingCallResPonse.setCustLname(cm.getLastName());
                incomingCallResPonse.setSocketHostPublic(Constants.socketHostPublic);
                incomingCallResPonse.setCustomerId(cm.getCustId());
                incomingCallResPonse.setCustMstId(cm.getId());
                incomingCallResPonse.setMobileNo("" + cm.getCellPhone());
                incomingCallResPonse.setCustomerType(this.CUSTOMER_REGISTERED);
                CustomerDeviceDtl customerDeviceDtl = new CustomerDeviceDtl();
                customerDeviceDtl.setLogout(0);

                customerDeviceDtl.setDeviceBrand(null);
                customerDeviceDtl.setDeviceId(null);
                customerDeviceDtl.setDeviceIp(null);
                customerDeviceDtl.setImeiNo(null);

                customerDeviceDtl.setMobileOsType("android");
                customerDeviceDtl.setStaticIp(null);
                customerDeviceDtl.setLoginInfo(1);
                customerDeviceDtl.setCustomerId(cm);
                customerDeviceDtl.setCustomerOtp(null);
                customerDeviceDtl.setTimezone("Asia/Kolkata");
                customerDeviceDtlService.saveCustomerDeviceDtl(customerDeviceDtl, null);
                CustomerConstant.customerLoginFlag.put(cm.getCustId(), 0);
                CustomerConstant.customerMediumFlag.put(cm.getCustId(), "android");
                PromotionalVideoMst lVideoMst = promotionalVideoMstService.findSelectedPromotionalVideo();
                if (lVideoMst == null) {
                    String websiteURL = request.getRequestURL().substring(0, request.getRequestURL().indexOf("/", 9));
                    String promoVideo = websiteURL + request.getContextPath() + "/promotional/promo.mp4";
                    incomingCallResPonse.setPromotionalVideo(promoVideo);
                } else {
                    String videoUrl = promotionalVideoMstService.getVideoFileUrl(lVideoMst.getFileUrl(), request,
                            context);
                    incomingCallResPonse.setPromotionalVideo(videoUrl);
                }

                return incomingCallResPonse;
            } else {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Please Provide valid credential!");
                throw ccmsRestException;
            }
        } else {
            CCMSRestException ccmsRestException = new CCMSRestException();
            ccmsRestException.setErrorCode("418");
            ccmsRestException.setErrorMessage("Please Provide valid credential!");
            throw ccmsRestException;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.rank.ccms.rest.service.CCMSRESTControllerService#
     * getCustomerToAvailabeAgentCallResponse( String credential)
     */
    @Override
    public IncomingCallResponse getCustomerToAvailabeAgentCallResponse(String credential) throws Exception {
        logger.info("INSIDE WEBSERVICE getNewCustomerIncomingCallInfo");

        CustomerMst cm;
        boolean newCall = true;

        IncomingCallResponse incomingCallResPonse = new IncomingCallResponse();
        CallMst callMstForRepeatcall = null;
        String location;
        String latitude;
        String longitude;

        /**
         * @Purpose To check whether method parameter credential is null or not
         */
        if (credential != null && credential.length() > 0) {
            JSONObject cred = new JSONObject(credential);
            String loginId = cred.getString("loginId");

            String category = cred.getString("category");
            String language = cred.getString("language");
            String service = cred.getString("service");
            String callOption = cred.getString("callOption");
            location = cred.getString("location");
            latitude = cred.getString("latitude");
            longitude = cred.getString("longitude");

            if (loginId == null || loginId.isEmpty() || "".equals(loginId)) {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Please Provide loginId!");
                throw ccmsRestException;

            }

            if (category == null
                    || "".equals(language) || category.isEmpty() || "".equals(category) || language == null || language.isEmpty() || service == null || service.isEmpty() || "".equals(service)) {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Please Provide valid information!");
                throw ccmsRestException;
            }

            cm = customerMstService.findCustomerMstByCustId(loginId);
            if (cm != null) {

                incomingCallResPonse.setCustFname(cm.getFirstName());
                incomingCallResPonse.setCustLname(cm.getLastName());
                incomingCallResPonse.setSocketHostPublic(Constants.socketHostPublic);
                List<CallMst> callMstForRepeatcallList = callMstService.findAllNotRecevedCallsByCust(cm.getCustId());
                if (!callMstForRepeatcallList.isEmpty()) {

                    callMstForRepeatcall = callMstForRepeatcallList.get(0);
                    newCall = false;
                }

                /**
                 * the following portion need to be changed in CustomerMst
                 * Relation and Entity as well
                 */
                CategoryMst cat = categoryMstService.findCategoryByCategoryName(category);
                Long selectedCategoryName = cat.getId();
                LanguageMst langMst = langMstService.findLanguageMstByLanguageName(language);
                Long selectedLanguageName = langMst.getId();
                ServiceMst serviceMst = serviceMstService.findServiceByServiceName(service);
                Long selectedServiceName = serviceMst.getId();

                synchronized (this) {

                    if (newCall) {

                        List<CallMst> listOpenCalls = callMstService.findAllOnGoingCallsByCust(cm.getCustId());

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
                        local_callMst.setCallLocation(location);
                        local_callMst.setCallLatitude(latitude);
                        local_callMst.setCallLongitude(longitude);
                        local_callMst.setCustomerId(cm);
                        local_callMst.setCustId(cm.getCustId());
                        local_callMst.setCallOption(callOption);

                        local_callMst.setServiceId(serviceMst.getId());
                        local_callMst.setServiceId(selectedServiceName);
                        local_callMst.setCategoryId(selectedCategoryName);
                        local_callMst.setLanguageId(selectedLanguageName);
                        local_callMst.setCallMedium("");
                        local_callMst.setActiveFlg(true);
                        local_callMst.setDeleteFlg(false);
                        local_callMst.setStartTime(CustomConvert.javaDateToTimeStamp(new Date()));
                        local_callMst.setCustomerRequestTime(CustomConvert.javaDateToTimeStamp(new Date()));

                        local_callMst = callMstService.saveCallMst(local_callMst);
                        callMstForRepeatcall = callMstService.findCallMstById(local_callMst.getId());
                    } else if (callMstForRepeatcall != null) {
                        callMstForRepeatcall.setServiceId(selectedServiceName);
                        callMstForRepeatcall.setCategoryId(selectedCategoryName);
                        callMstForRepeatcall.setLanguageId(selectedLanguageName);
                        callMstForRepeatcall.setStartTime(CustomConvert.javaDateToTimeStamp(new Date()));
                        callMstForRepeatcall = callMstService.saveCallMst(callMstForRepeatcall);
                    }
                    if (null != callMstForRepeatcall) {
                        Long callId = callMstForRepeatcall.getId();
                        CallMst callMaster = callMstService.findNonDeletedCallMstById(callId);
                        incomingCallResPonse.setCallId(callId + "");
                        incomingCallResPonse.setStatus("Not Initiated");
                        if (!employeeCallStatusService.findFreeOnlineAgents().isEmpty()) {

                            List<Object> L_E_Mst = Arrays.asList(agentFindingService
                                    .findAgents(cat.getCatgCd(), serviceMst.getServiceCd(), langMst.getLanguageCd())
                                    .toArray());
                            EmployeeMst em;

                            if (!L_E_Mst.isEmpty()) {
                                em = (EmployeeMst) L_E_Mst.get(0);

                                EmployeeCallStatus empCallStatus = new EmployeeCallStatus();
                                List<EmployeeCallStatus> empClStatusList = employeeCallStatusService
                                        .findEmployeeCallStatusByEmpId(em);

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

                                List<TenancyEmployeeMap> tenancyEmployeeMaplist = tenancyEmployeeMapService
                                        .findVidyoTenantUrlByEmpId(em.getId());
                                List<Long> unsortList = new ArrayList<>();
                                for (TenancyEmployeeMap tenancyEmployeeMaplist1 : tenancyEmployeeMaplist) {
                                    unsortList.add(tenancyEmployeeMaplist1.getId());
                                }
                                Collections.sort(unsortList);

                                TenancyEmployeeMap tenancyEmployeeMap = tenancyEmployeeMapService
                                        .findById(unsortList.get(unsortList.size() - 1));

                                String roomLink = tenancyEmployeeMap.getRoomLink();
                                String roomName = tenancyEmployeeMap.getRoomName();
                                String entityId = tenancyEmployeeMap.getEntityId();
                                String custSocket = "Cust_" + GenerateCustId.generateCustId();
                                incomingCallResPonse.setRoomName(roomName);
                                incomingCallResPonse.setUrl(roomLink);
                                incomingCallResPonse.setEntityId(entityId);
                                incomingCallResPonse.setAgentId(em.getId().toString());
                                incomingCallResPonse.setStatus("Initiated" + "||" + em.getFirstName() + " " + em.getLastName());
                                incomingCallResPonse.setCustomerId(cm.getCustId());
                                incomingCallResPonse.setSocketHostPublic(custSocket);

                                Set<String> custIdSet = new TreeSet<>();
                                custIdSet.add(cm.getCustId());
                                CallScheduler.customerCallDetailsMap.put(callId.toString(), custIdSet);

                                CallEmployeeMap l_CallEmpMap = new CallEmployeeMap();
                                l_CallEmpMap.setCallId(callMaster.getId());
                                l_CallEmpMap.setEmployeeId(em.getId());
                                l_CallEmpMap.setCallType("Normal");
                                l_CallEmpMap.setCustId(cm.getCustId());
                                callMaster.setRoutingCallTime(CustomConvert.javaDateToTimeStamp(new Date()));
                                callMaster.setRoomName(roomLink);
                                callMaster = callMstService.saveCallMst(callMaster);

                                CallRecords callRecords = callRecordsService.findCallRecordsByCallId(callMaster.getId(),
                                        (long) 0);
                                if (null == callRecords) {
                                    callRecords = new CallRecords();
                                }

                                callRecords.setCallId(callMaster);
                                callRecords.setEmpId(em);
                                callRecords.setRecorderId("");
                                callRecords.setRoomName(roomName);
                                callRecords.setCustomerId(incomingCallResPonse.getCustomerId());

                                callRecordsService.saveCallRecord(callRecords);
                                try {
                                    SocketMessage.send(callScheduler.getAdminSocket(), em.getLoginId(),
                                            "incomingCall#" + callMaster.getId() + "#" + cm.getCustId() + "#" + callMaster.getCallOption());
                                } catch (Exception ex) {
                                    logger.error(ex);
                                }

                                CallScheduler.listCallEmp.add(l_CallEmpMap);

                                CustomerDeviceDtl customerDeviceDtl = new CustomerDeviceDtl();
                                customerDeviceDtl.setLogout(0);

                                customerDeviceDtl.setDeviceBrand(null);
                                customerDeviceDtl.setDeviceId(null);
                                customerDeviceDtl.setDeviceIp(null);
                                customerDeviceDtl.setImeiNo(null);

                                customerDeviceDtl.setMobileOsType("android");
                                customerDeviceDtl.setStaticIp(null);
                                if (cm.getAccountNo() != null && cm.getAccountNo().trim().length() > 0) {
                                    customerDeviceDtl.setLoginInfo(2);
                                } else {
                                    customerDeviceDtl.setLoginInfo(1);
                                }

                                customerDeviceDtl.setCustomerId(cm);
                                customerDeviceDtl.setCustomerOtp(null);
                                customerDeviceDtl.setTimezone("Asia/Kolkata");
                                customerDeviceDtlService.saveCustomerDeviceDtl(customerDeviceDtl, null);

                            } else {
                                logger.info("No Agent Found..........");
                                callMaster = callMstService.findNonDeletedCallMstById(Long.parseLong(incomingCallResPonse.getCallId()));
                                callMaster.setCallStatus("No Agent Found");
                                callMaster.setStartTime(CustomConvert.javaDateToTimeStamp(new Date()));
                                callMstService.saveCallMst(callMaster);

                            }

                        } else {
                            logger.info("All Agents are busy or no agent is available..........");
                            if (incomingCallResPonse.getCallId() != null && incomingCallResPonse.getCallId().trim().length() > 0) {
                                callId = Long.parseLong(incomingCallResPonse.getCallId());
                                callMaster.setCallStatus("No Agent Found");
                                callMaster.setStartTime(CustomConvert.javaDateToTimeStamp(new Date()));
                                callMstService.saveCallMst(callMaster);
                                incomingCallResPonse.setCallId("" + callId);
                            }
                        }
                    }

                    return incomingCallResPonse;
                }

            } else {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Please Provide valid credential!");
                throw ccmsRestException;
            }
        } else {
            CCMSRestException ccmsRestException = new CCMSRestException();
            ccmsRestException.setErrorCode("418");
            ccmsRestException.setErrorMessage("Please Provide valid credential!");
            throw ccmsRestException;
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.rank.ccms.rest.service.CCMSRESTControllerService#
     * getCustomerRegisterResponse( CustomerDto customerDto)
     */
    @Override
    public IncomingCallResponse getCustomerRegisterResponse(CustomerDto customerDto) throws Exception {

        if (customerDto != null && customerDto.getCategory() != null && customerDto.getCategory().length() > 0
                && customerDto.getLanguage() != null && customerDto.getLanguage().length() > 0
                && customerDto.getService() != null && customerDto.getService().length() > 0) {
            logger.info("startCallVerified called.....");
            CategoryMst cat = categoryMstService.findCategoryByCategoryName(customerDto.getCategory());
            Long selectedCategoryName;
            selectedCategoryName = cat.getId();
            LanguageMst langMst = langMstService.findLanguageMstByLanguageName(customerDto.getLanguage());
            Long selectedLanguageName;
            selectedLanguageName = langMst.getId();
            ServiceMst serviceMst = serviceMstService.findServiceByServiceName(customerDto.getService());
            Long selectedServiceName;
            selectedServiceName = serviceMst.getId();
            if (null == selectedCategoryName || 0 == selectedCategoryName || null == selectedLanguageName
                    || 0 == selectedLanguageName || null == selectedServiceName || 0 == selectedServiceName
                    || null == customerDto.getEmail() || null == customerDto.getCustomerName()
                    || null == customerDto.getCellPhone()) {

                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Please Provide valid information!");
                throw ccmsRestException;

            } else {
                IncomingCallResponse incomingCallResPonse = new IncomingCallResponse();
                CustomerMst cust = new com.rank.ccms.entities.CustomerMst();
                String custSocket = "Cust_" + GenerateCustId.generateCustId();
                cust.setFirstName(customerDto.getCustomerName());
                cust.setLastName("");
                cust.setEmail(customerDto.getEmail());
                cust.setCellPhone(Long.parseLong(customerDto.getCellPhone()));
                cust.setCustId(custSocket);
                cust.setCustPwd("123");
                cust.setActiveFlg(true);
                cust.setDeleteFlg(false);
                cust.setPolicyCount(0);
                cust.setSalute("");
                cust.setNatinality(customerDto.getNationality());

                cust = customerMstService.saveCustomerMst(cust, null);
                customerDto.setCustomerMstId(String.valueOf(cust.getId()));
                customerDto.setCustId(cust.getCustId());
                customerDto.setPassword(cust.getCustPwd());
                customerDto.setSocketHostPublic(Constants.socketHostPublic);
                logger.info(cust.getCustId());

                CustomerDeviceDtl customerDeviceDtl = new CustomerDeviceDtl();
                customerDeviceDtl.setLogout(0);

                customerDeviceDtl.setDeviceBrand(null);
                customerDeviceDtl.setDeviceId(null);
                customerDeviceDtl.setDeviceIp(null);
                customerDeviceDtl.setImeiNo(null);

                customerDeviceDtl.setMobileOsType("Android");
                customerDeviceDtl.setStaticIp(null);
                customerDeviceDtl.setLoginInfo(0);
                customerDeviceDtl.setCustomerId(cust);
                customerDeviceDtl.setCustomerOtp(null);
                customerDeviceDtl.setTimezone("Asia/Kolkata");
                customerDeviceDtlService.saveCustomerDeviceDtl(customerDeviceDtl, null);
                CustomerConstant.customerLoginFlag.put(cust.getCustId(), 0);
                CustomerConstant.customerMediumFlag.put(cust.getCustId(), "android");
                incomingCallResPonse.setCustFname(cust.getFirstName());
                incomingCallResPonse.setCustLname(cust.getLastName());
                incomingCallResPonse.setSocketHostPublic(Constants.socketHostPublic);
                incomingCallResPonse.setCustomerId(cust.getCustId());
                incomingCallResPonse.setCustMstId(cust.getId());
                incomingCallResPonse.setMobileNo("" + cust.getCellPhone());
                incomingCallResPonse.setCustomerType(this.CUSTOMER_NEW);
                PromotionalVideoMst lVideoMst = promotionalVideoMstService.findSelectedPromotionalVideo();
                if (lVideoMst == null) {
                    String websiteURL = requests.getRequestURL().substring(0, requests.getRequestURL().indexOf("/", 9));
                    String promoVideo = websiteURL + requests.getContextPath() + "/promotional/promo.mp4";
                    incomingCallResPonse.setPromotionalVideo(promoVideo);
                } else {
                    String videoUrl = promotionalVideoMstService.getVideoFileUrl(lVideoMst.getFileUrl(), requests,
                            context);
                    incomingCallResPonse.setPromotionalVideo(videoUrl);
                }

                return incomingCallResPonse;

            }

        } else {
            CCMSRestException ccmsRestException = new CCMSRestException();
            ccmsRestException.setErrorCode("418");
            ccmsRestException.setErrorMessage("Please Provide valid information!");
            throw ccmsRestException;
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.rank.ccms.rest.service.CCMSRESTControllerService#
     * getCustomerCallEndResponse( CustomerDto customerDto)
     */
    @Override
    public CustomerDto getCustomerCallEndResponse(String callInfo) throws Exception {
        boolean flag = false;
        String inCallType;
        String callId = null;
        String custId = null;
        CustomerDto customerDto;

        /**
         * @Purpose To check whether method parameter credential is null or not
         */
        if (callInfo != null && callInfo.length() > 0) {
            JSONObject callInformation = new JSONObject(callInfo);
            if (callInformation.has("callId") && callInformation.has("custId")) {
                callId = callInformation.getString("callId");
                custId = callInformation.getString("custId");
            } else {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Please Provide valid information!");
                throw ccmsRestException;
            }

        } else {
            CCMSRestException ccmsRestException = new CCMSRestException();
            ccmsRestException.setErrorCode("418");
            ccmsRestException.setErrorMessage("Please Provide valid information!");
            throw ccmsRestException;
        }

        if (callId != null && callId.length() > 0 && custId != null && custId.length() > 0) {

            CallMst callMst;
            callMst = callMstService.findNonEndedCustomerCallByCallId(Long.parseLong(callId), custId);

            EmployeeMst empMst;
            logger.info(" callMst " + callMst);
            if (null != callMst) {
                inCallType = callMst.getCallType();
                CustomerMst cm;
                callMst.setCallStatus("Ended");
                callMst.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                CallMst cmLocal = callMstService.saveCallMst(callMst);
                CallRecords l_callReords = callRecordsService.findCallRecordsByCallIdOnly(cmLocal.getId());
                try {
//                    stopRecording(l_callReords.getRoomName());
                } catch (Exception e) {
                    logger.error("Error in stop Recording in endCall rest" + e.getMessage());
                }

                flag = true;
                cm = customerMstService.findCustomerMstByCustId(custId);
                if (cm.getAccountNo() == null || "".equals(cm.getAccountNo().trim())) {
                    logger.info("New Customer call end!");
                    CustomerDeviceDtl customerDeviceDtl = new CustomerDeviceDtl();
                    customerDeviceDtl.setLogout(1);

                    customerDeviceDtl.setDeviceBrand(null);
                    customerDeviceDtl.setDeviceId(null);
                    customerDeviceDtl.setDeviceIp(null);
                    customerDeviceDtl.setImeiNo(null);

                    customerDeviceDtl.setMobileOsType("android");
                    customerDeviceDtl.setStaticIp(null);
                    customerDeviceDtl.setLoginInfo(0);
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

                    customerDeviceDtl.setMobileOsType("android");
                    customerDeviceDtl.setStaticIp(null);
                    customerDeviceDtl.setLoginInfo(1);
                    customerDeviceDtl.setCustomerId(cm);
                    customerDeviceDtl.setCustomerOtp(null);
                    customerDeviceDtl.setTimezone("Asia/Kolkata");
                    customerDeviceDtlService.saveCustomerDeviceDtl(customerDeviceDtl, null);
                }

                CallMst callMstLocal = cmLocal;
                if (callMstLocal.getId() > 0) {

                    List<CallDtl> cList = callDtlService.findCallDtlByCallMasterId(callMstLocal.getId());
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

                    CallScheduler.customerCallDetailsMap.remove(callMstLocal.getId().toString());
                    ReasonMst reasonMst;
                    reasonMst = reasonMstService.findReasonMstByReasonCode("HUP01");
                    List<CallDtl> listCallDtl = callDtlService.findCallDtlByCallMasterId(callMst.getId());
                    if (!listCallDtl.isEmpty()) {
                        for (CallDtl l_callDtl : listCallDtl) {

                            empMst = employeeMstService.findEmployeeMstById(l_callDtl.getHandeledById().getId());

                            CallDtl clDtl = callDtlService.findCallDtlByCallMasterIdAndAgentNotForwarded(
                                    callMstLocal.getId(), empMst.getId());

                            clDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                            clDtl.setDeleteFlg(false);
                            clDtl.setAgentComments(reasonMst.getReasonDesc());
                            clDtl.setActiveFlg(true);
                            callDtlService.saveCallDtl(clDtl);

                            List<CallDtl> findOtherNonEndedCallDetailFromSameCallMst = callDtlService
                                    .findCallDtlByCallMasterIdAndAgent(callMstLocal.getId(), empMst.getId());
                            EmployeeActivityDtl employeeActivityDtl = employeeActivityDtlService
                                    .findLastNonEndedActivityByType(empMst.getId(), "Call Started");

                            if (employeeActivityDtl != null) {
                                employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                                employeeActivityDtlService.save(employeeActivityDtl);

                            }

                            employeeActivityDtl = employeeActivityDtlService
                                    .findLastNonEndedActivityByType(empMst.getId(), "Incoming Call");

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
                                List<EmployeeCallStatus> empClStatusList = employeeCallStatusService
                                        .findEmployeeCallStatusByEmpId(empMst);

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

                                employeeActivityDtl = employeeActivityDtlService
                                        .findLastNonEndedActivityByType(empMst.getId(), "Blocked");

                                if (employeeActivityDtl != null) {
                                    employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                                    employeeActivityDtlService.save(employeeActivityDtl);
                                }

                            }
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
                        }
                    }
                }

            }

            customerDto = new CustomerDto();
            if (flag) {
                customerDto.setResMessage(MSG_SUCCESS);
                customerDto.setResStatus(TRUE_STATUS);
                return customerDto;
            } else {
                customerDto.setResMessage(MSG_FAILED);
                customerDto.setResStatus(FALSE_STATUS);
                return customerDto;
            }
        } else {
            CCMSRestException ccmsRestException = new CCMSRestException();
            ccmsRestException.setErrorCode("418");
            ccmsRestException.setErrorMessage("Please Provide valid information!");
            throw ccmsRestException;
        }
    }

    @Override
    public CustomerDto getAgentCallEndResponse(String callInfo) throws Exception {

        logger.info("Employee end call!");
        String inCallType;
        EmployeeMst empMst;
        String callId = null;
        String agentId = null;
        String reasonCode = null;
        CustomerDto customerDto = null;
        logger.info("HANG UP CALL.....");
        /**
         * @Purpose To check whether method parameter callInfo is null or not
         */
        if (callInfo != null && callInfo.length() > 0) {
            JSONObject callInformation = new JSONObject(callInfo);
            if (callInformation.has("callId") && callInformation.has("empId")) {
                callId = callInformation.getString("callId");
                agentId = callInformation.getString("empId");
                reasonCode = callInformation.getString("reasonCode");
            } else {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Please Provide valid information!");
                throw ccmsRestException;
            }

        } else {
            CCMSRestException ccmsRestException = new CCMSRestException();
            ccmsRestException.setErrorCode("418");
            ccmsRestException.setErrorMessage("Please Provide valid information!");
            throw ccmsRestException;
        }

        if (callId != null && callId.length() > 0 && agentId != null && agentId.length() > 0) {

            CallMst callMst = callMstService.findCallMstById(Long.parseLong(callId));
            empMst = employeeMstService.findEmployeeMstById(Long.parseLong(agentId));

            if (null != callMst) {
                inCallType = callMst.getCallType();
                logger.info(" callMst " + callMst);
                CustomerMst cm;
                callMst.setCallStatus("Ended");
                callMst.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                CallMst cmLocal = callMstService.saveCallMst(callMst);
                List<CallDtl> callDtls = callDtlService.findCallDtlByCallMasterId(cmLocal.getId());
                if (callDtls != null) {
                    if (!callDtls.isEmpty()) {
                        for (CallDtl cd : callDtls) {
                            if (cd.getEndTime() == null) {
                                cd.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                                callDtlService.saveCallDtl(cd);
                            }
                        }
                    }
                }
                //CallRecords l_callReords = callRecordsService.findCallRecordsByCallIdOnly(cmLocal.getId());
                try {
                    //stopRecording(l_callReords.getRoomName());
                } catch (Exception e) {
                    logger.error("Error in stop Recording in endCall rest" + e.getMessage());
                }
                CallMst callMstLocal = cmLocal;

                cm = customerMstService.findCustomerMstByCustId(cmLocal.getCustId());
                CustomerDeviceDtl deviceDtl = customerDeviceDtlService.findCustomerDeviceDtlByCustId(cm);
                if (null != deviceDtl) {
                    deviceDtl.setLoginInfo(1);
                    customerDeviceDtlService.saveCustomerDeviceDtl(deviceDtl, null);
                    String osType = deviceDtl.getMobileOsType();
                    if (null != osType && !"WEB".equalsIgnoreCase(osType)) {
                        try {
                            SocketMessage.send(callScheduler.getAdminSocket(), cm.getCustId(),
                                    "hangUp#" + callMstLocal.getId());
                        } catch (Exception ex) {
                            logger.error(ex);
                        }
                    }
                }

                CallScheduler.customerCallDetailsMap.remove(callMstLocal.getId().toString());

                if (empMst != null) {
                    EmployeeActivityDtl employeeActivityDtl = employeeActivityDtlService
                            .findLastNonEndedActivityByType(empMst.getId(), "Call Started");

                    if (employeeActivityDtl != null) {
                        employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                        employeeActivityDtlService.save(employeeActivityDtl);

                    }

                    employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(empMst.getId(),
                            "Call Started");

                    if (employeeActivityDtl != null) {
                        employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                        employeeActivityDtlService.save(employeeActivityDtl);

                    }

                    employeeActivityDtl = new EmployeeActivityDtl();
                    employeeActivityDtl.setActivity("Hang Up");
                    employeeActivityDtl.setEmpId(empMst);
                    employeeActivityDtl.setStartTime(CustomConvert.javaDateToTimeStamp(new Date()));
                    ReasonMst reason = reasonMstService.findReasonMstByReasonCode(reasonCode);
                    employeeActivityDtl.setReasonId(reason);
                    employeeActivityDtl.setReasonCd(reason.getReasonCd());
                    employeeActivityDtl.setReasonDesc(reason.getReasonDesc());
                    employeeActivityDtl.setCallMstId(callMstLocal.getId());
                    employeeActivityDtlService.save(employeeActivityDtl);

                    if (!inCallType.equals("Scheduled")) {

                        EmployeeCallStatus empCallStatus = new EmployeeCallStatus();
                        List<EmployeeCallStatus> empClStatusList = employeeCallStatusService
                                .findEmployeeCallStatusByEmpId(empMst);

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

                        employeeActivityDtl = employeeActivityDtlService
                                .findLastNonEndedActivityByType(empMst.getId(), "Blocked");

                        if (employeeActivityDtl != null) {
                            employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                            employeeActivityDtlService.save(employeeActivityDtl);
                        }

                    }
                }

                customerDto = new CustomerDto();
                customerDto.setResMessage(MSG_SUCCESS);
                customerDto.setResStatus(TRUE_STATUS);
            } else {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Please Provide valid information!");
                throw ccmsRestException;
            }
            return customerDto;
        } else {
            CCMSRestException ccmsRestException = new CCMSRestException();
            ccmsRestException.setErrorCode("418");
            ccmsRestException.setErrorMessage("Please Provide valid information!");
            throw ccmsRestException;
        }
        /**
         * The other parties in the same call is here apart from the main call
         * initiator employee
         */

    }

    @Override
    public CustomerDto getCustomerLogoutResponse(String callInfo) throws Exception {
        logger.info("customerLogout...........");
        CustomerMst cm;
        String callId;
        String custId = null;
        // String password = null;
        CustomerDto customerDto = null;

        /**
         * @Purpose To check whether method parameter callInfo is null or not
         */
        if (callInfo != null && callInfo.length() > 0) {
            JSONObject callInformation = new JSONObject(callInfo);
            if (callInformation.has("callId") && callInformation.has("custId") && callInformation.has("password")) {
                callId = callInformation.getString("callId");
                this.getCustomerCallEndResponse(callInfo);
                logger.info("with callid=" + callId + "    Called");
            } else if (callInformation.has("custId")/* && callInformation.has("password") */) {
                logger.info("custId and password found");

            } else {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Please Provide valid information!");
                throw ccmsRestException;
            }

            custId = callInformation.getString("custId");
            // password = callInformation.getString("password");

        } else {
            CCMSRestException ccmsRestException = new CCMSRestException();
            ccmsRestException.setErrorCode("418");
            ccmsRestException.setErrorMessage("Please Provide valid information!");
            throw ccmsRestException;
        }
        if (custId != null && custId.length() > 0 /* && password != null && password.length() > 0 */) {

            cm = customerMstService.findCustomerMstByCustId(custId);
            if (cm != null) {

                if (CustomerConstant.customerMediumFlag.containsKey(cm.getCustId())) {
                    CustomerConstant.customerMediumFlag.remove(cm.getCustId());
                }
                CustomerDeviceDtl customerDeviceDtl = new CustomerDeviceDtl();
                customerDeviceDtl.setLogout(1);

                customerDeviceDtl.setDeviceBrand(null);
                customerDeviceDtl.setDeviceId(null);
                customerDeviceDtl.setDeviceIp(null);
                customerDeviceDtl.setImeiNo(null);

                customerDeviceDtl.setMobileOsType("android");
                customerDeviceDtl.setStaticIp(null);
                customerDeviceDtl.setLoginInfo(0);
                customerDeviceDtl.setCustomerId(cm);
                customerDeviceDtl.setCustomerOtp(null);
                customerDeviceDtl.setTimezone("Asia/Kolkata");
                customerDeviceDtlService.saveCustomerDeviceDtl(customerDeviceDtl, null);
                customerDto = new CustomerDto();
                customerDto.setResMessage(MSG_SUCCESS);
                customerDto.setResStatus(TRUE_STATUS);

            } else {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Please Provide valid information!");
                throw ccmsRestException;
            }
            return customerDto;

        } else {
            CCMSRestException ccmsRestException = new CCMSRestException();
            ccmsRestException.setErrorCode("418");
            ccmsRestException.setErrorMessage("Please Provide valid credential!");
            throw ccmsRestException;
        }

    }

    @Override
    public AgentLoginResponse agentNotReady(String callInfo) throws Exception {

        String agentId = null;
        String reasonCode = null;
        AgentLoginResponse agentLoginResponse;
        /**
         * @Purpose To check whether method parameter callInfo is null or not
         */
        if (callInfo != null && callInfo.length() > 0) {
            JSONObject callInformation = new JSONObject(callInfo);
            if (callInformation.has("empId")) {
                agentId = callInformation.getString("empId");

            } else {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Please Provide valid information!");
                throw ccmsRestException;
            }

            reasonCode = callInformation.getString("reasonCode");
        } else {
            CCMSRestException ccmsRestException = new CCMSRestException();
            ccmsRestException.setErrorCode("418");
            ccmsRestException.setErrorMessage("Please Provide valid information!");
            throw ccmsRestException;
        }

        if (agentId != null && agentId.length() > 0) {

            EmployeeMst empMst = employeeMstService.findEmployeeMstById(Long.parseLong(agentId));
            agentLoginResponse = new AgentLoginResponse();
            ReasonMst reasonMst = reasonMstService.findReasonMstByReasonCode(reasonCode);

            if (empMst != null) {
                EmployeeCallStatus empCallStatus = new EmployeeCallStatus();
                List<EmployeeCallStatus> empClStatusList = employeeCallStatusService.findEmployeeCallStatusByEmpId(empMst);
                //this.setAvailableenabled(false);
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

                    //ReasonMst reasonMst = reasonMstService.findNonDeletedById(this.getAgentNotreadyComment());
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

                    agentLoginResponse.setSuccMsg("Success");
                } catch (Exception ex) {
                    CCMSRestException ccmsRestException = new CCMSRestException();
                    ccmsRestException.setErrorCode("418");
                    ccmsRestException.setErrorMessage("Please Provide valid information!");
                    logger.error(ex.getMessage());
                    throw ccmsRestException;
                }
            } else {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Please Provide valid information!");
                throw ccmsRestException;

            }
            return agentLoginResponse;
        } else {
            CCMSRestException ccmsRestException = new CCMSRestException();
            ccmsRestException.setErrorCode("418");
            ccmsRestException.setErrorMessage("Please Provide valid information!");
            throw ccmsRestException;
        }
    }

    @Override
    public AgentLoginResponse getCustomerCallPickedResponse(CustomerDto customerDto) throws Exception {
        logger.info("===========getCustomerCallPickedResponse has been called=============");
        AgentLoginResponse agentLoginResponse;
        if (customerDto != null && customerDto.getCallId() != null && customerDto.getCallId().length() > 0
                && customerDto.getCustId() != null && customerDto.getCustId().length() > 0) {
//			String agentLoginId = customerDto.getAgentId();
            CallMst callMstFromSession = callMstService.findCallMstById(Long.parseLong(customerDto.getCallId()));
            CustomerMst cm = customerMstService.findCustomerMstByCustId(customerDto.getCustId());
            if (callMstFromSession != null) {
                callMstFromSession.setCustomerPickupTime(CustomConvert.javaDateToTimeStamp(new Date()));
                callMstService.saveCallMst(callMstFromSession);
                if (cm != null) {
                    if (cm.getAccountNo() == null || "".equals(cm.getAccountNo().trim())) {

                        CustomerDeviceDtl customerDeviceDtl = new CustomerDeviceDtl();
                        customerDeviceDtl.setLogout(0);

                        customerDeviceDtl.setDeviceBrand(null);
                        customerDeviceDtl.setDeviceId(null);
                        customerDeviceDtl.setDeviceIp(null);
                        customerDeviceDtl.setImeiNo(null);

                        customerDeviceDtl.setMobileOsType("Android");
                        customerDeviceDtl.setStaticIp(null);
                        customerDeviceDtl.setLoginInfo(1);
                        customerDeviceDtl.setCustomerId(cm);
                        customerDeviceDtl.setCustomerOtp(null);
                        customerDeviceDtl.setTimezone("Asia/Kolkata");
                        customerDeviceDtlService.saveCustomerDeviceDtl(customerDeviceDtl, null);
                    } else if (cm.getAccountNo() != null && cm.getAccountNo().trim().length() > 0) {
                        logger.info("Existing Customer call start!");
                        CustomerDeviceDtl customerDeviceDtl = new CustomerDeviceDtl();
                        customerDeviceDtl.setLogout(0);

                        customerDeviceDtl.setDeviceBrand(null);
                        customerDeviceDtl.setDeviceId(null);
                        customerDeviceDtl.setDeviceIp(null);
                        customerDeviceDtl.setImeiNo(null);

                        customerDeviceDtl.setMobileOsType("Android");
                        customerDeviceDtl.setStaticIp(null);
                        customerDeviceDtl.setLoginInfo(2);
                        customerDeviceDtl.setCustomerId(cm);
                        customerDeviceDtl.setCustomerOtp(null);
                        customerDeviceDtl.setTimezone("Asia/Kolkata");
                        customerDeviceDtlService.saveCustomerDeviceDtl(customerDeviceDtl, null);
                    }

                    agentLoginResponse = new AgentLoginResponse();
                    agentLoginResponse.setSuccMsg(MSG_SUCCESS);
                    return agentLoginResponse;
                } else {
                    CCMSRestException ccmsRestException = new CCMSRestException();
                    ccmsRestException.setErrorCode("418");
                    ccmsRestException.setErrorMessage("Please Provide valid information!");
                    throw ccmsRestException;
                }
            } else {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Please Provide valid information!");
                throw ccmsRestException;
            }
        } else {
            CCMSRestException ccmsRestException = new CCMSRestException();
            ccmsRestException.setErrorCode("418");
            ccmsRestException.setErrorMessage("Please Provide required information!");
            throw ccmsRestException;
        }
    }

    @Override
    public AgentLoginResponse getAgentCallMissedResponse(String callInfo) throws Exception {

        String empId = null;
        String callId = null;
        AgentLoginResponse agentLoginResponse;
        /**
         * @Purpose To check whether method parameter callInfo is null or not
         */
        if (callInfo != null && callInfo.length() > 0) {
            JSONObject callInformation = new JSONObject(callInfo);
            if (callInformation.has("callId") && callInformation.has("empId")) {
                empId = callInformation.getString("empId");
                callId = callInformation.getString("callId");
                logger.info("with callid=" + callId + "    Called");
                this.getAgentCallEndResponse(callInfo);
            } else {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Please Provide valid information!");
                throw ccmsRestException;
            }
        } else {
            CCMSRestException ccmsRestException = new CCMSRestException();
            ccmsRestException.setErrorCode("418");
            ccmsRestException.setErrorMessage("Please Provide valid information!");
            throw ccmsRestException;
        }

        logger.info("Inside callAbondaned1.....");
        String usertext = null;
        if (empId != null && empId.length() > 0 && callId != null && callId.length() > 0) {
            EmployeeMst empMst = employeeMstService.findEmployeeMstById(Long.parseLong(empId));
            CallMst callMstLocal = callMstService.findCallMstById(Long.parseLong(callId));

            if (empMst != null && empMst.getId() > 0 && callMstLocal != null && callMstLocal.getId() > 0) {
                callMstLocal.setCallStatus("Missed");
                callMstLocal.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                CustomerMst cm = customerMstService.findCustomerMstByCustId(callMstLocal.getCustId());
                if (null != cm) {
                    usertext = cm.getCustId();

                    callMstService.saveCallMst(callMstLocal);
                }

                EmployeeCallStatus empCallStatus = new EmployeeCallStatus();
                List<EmployeeCallStatus> empClStatusList = employeeCallStatusService
                        .findEmployeeCallStatusByEmpId(empMst);

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

                EmployeeActivityDtl employeeActivityDtl = employeeActivityDtlService
                        .findLastNonEndedActivityByType(empMst.getId(), "NEXT CALL");
                if (employeeActivityDtl != null) {
                    employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                    employeeActivityDtlService.save(employeeActivityDtl);

                }

                employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(empMst.getId(),
                        "Call Started");
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

                employeeActivityDtl.setCallMstId(callMstLocal.getId());

                employeeActivityDtlService.save(employeeActivityDtl);

                List<CallDtl> calldtlList = callDtlService.findCallDtlByCallMasterId(callMstLocal.getId());

                CallDtl callDetail;
                if (!(calldtlList.isEmpty())) {

                    callDetail = calldtlList.get(0);
                    callDetail.setAgentComments("Not Recieved By Agent");
                    callDetail.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                    callDtlService.saveCallDtl(callDetail);
                }

                SocketMessage.send(callScheduler.getAdminSocket(), usertext, "missed#" + callMstLocal.getId());
                agentLoginResponse = new AgentLoginResponse();
                agentLoginResponse.setSuccMsg(MSG_SUCCESS);

                return agentLoginResponse;
            } else {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Please Provide valid information!");
                throw ccmsRestException;
            }
        } else {
            CCMSRestException ccmsRestException = new CCMSRestException();
            ccmsRestException.setErrorCode("418");
            ccmsRestException.setErrorMessage("Please Provide valid information!");
            throw ccmsRestException;
        }

    }

    @Override
    public AgentLoginResponse getCustomerCallMissedResponse(String callInfo) throws Exception {
        String loginId = null;
        String password = null;
        String callId = null;
        AgentLoginResponse agentLoginResponse;
        /**
         * @Purpose To check whether method parameter callInfo is null or not
         */
        if (callInfo != null && callInfo.length() > 0) {
            JSONObject callInformation = new JSONObject(callInfo);
            if (callInformation.has("custId") && callInformation.has("password") && callInformation.has("callId")) {
                loginId = callInformation.getString("custId");
                password = callInformation.getString("password");
                callId = callInformation.getString("callId");
                logger.info("with callid=" + callId + "    Called");
            } else {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Please Provide valid information!");
                throw ccmsRestException;
            }

        } else {
            CCMSRestException ccmsRestException = new CCMSRestException();
            ccmsRestException.setErrorCode("418");
            ccmsRestException.setErrorMessage("Please Provide valid information!");
            throw ccmsRestException;
        }
        if (loginId != null && loginId.length() > 0 && password != null && password.length() > 0 && callId != null
                && callId.length() > 0) {
            CustomerMst cm = customerMstService.findCustomerMstByCustIdPassword(loginId, password);
            CallMst callMstLocal = callMstService.findCallMstById(Long.parseLong(callId));
            if (cm != null && cm.getId() > 0 && callMstLocal != null && callMstLocal.getId() > 0) {
                String usertext;
                String inCallType;
                inCallType = callMstLocal.getCallType();
                callMstLocal.setCallStatus("Missed");
                callMstLocal.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                callMstLocal = callMstService.saveCallMst(callMstLocal);

                if (cm.getAccountNo() == null || "".equals(cm.getAccountNo().trim())) {
                    logger.info("New Customer call end!");
                    CustomerDeviceDtl customerDeviceDtl = new CustomerDeviceDtl();
                    customerDeviceDtl.setLogout(1);

                    customerDeviceDtl.setDeviceBrand(null);
                    customerDeviceDtl.setDeviceId(null);
                    customerDeviceDtl.setDeviceIp(null);
                    customerDeviceDtl.setImeiNo(null);

                    customerDeviceDtl.setMobileOsType("android");
                    customerDeviceDtl.setStaticIp(null);
                    customerDeviceDtl.setLoginInfo(0);
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

                    customerDeviceDtl.setMobileOsType("android");
                    customerDeviceDtl.setStaticIp(null);
                    customerDeviceDtl.setLoginInfo(1);
                    customerDeviceDtl.setCustomerId(cm);
                    customerDeviceDtl.setCustomerOtp(null);
                    customerDeviceDtl.setTimezone("Asia/Kolkata");
                    customerDeviceDtlService.saveCustomerDeviceDtl(customerDeviceDtl, null);
                }
                if (callMstLocal != null) {
                    List<CallDtl> callDtlList = callDtlService.findCallDtlByCallMasterId(callMstLocal.getId());
                    ReasonMst reasonMst;
                    reasonMst = reasonMstService.findReasonMstByReasonCode("ABN01");
                    for (CallDtl localCallDtl : callDtlList) {
                        if (localCallDtl != null && localCallDtl.getHandeledById() != null
                                && localCallDtl.getHandeledById().getId() != null) {

                            EmployeeMst localEmpMst = employeeMstService
                                    .findEmployeeMstById(localCallDtl.getHandeledById().getId());
                            usertext = localEmpMst.getLoginId();

                            CallDtl clDtl = callDtlService.findCallDtlByCallMasterIdAndAgentNotForwarded(
                                    callMstLocal.getId(), localEmpMst.getId());

                            clDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                            clDtl.setDeleteFlg(false);
                            clDtl.setAgentComments(reasonMst.getReasonDesc());
                            clDtl.setActiveFlg(true);
                            callDtlService.saveCallDtl(clDtl);
                            List<CallDtl> findOtherNonEndedCallDetailFromSameCallMst = callDtlService
                                    .findCallDtlByCallMasterIdAndAgent(callMstLocal.getId(), localEmpMst.getId());
                            EmployeeActivityDtl employeeActivityDtl = employeeActivityDtlService
                                    .findLastNonEndedActivityByType(localEmpMst.getId(), "Call Started");

                            if (employeeActivityDtl != null) {
                                employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                                employeeActivityDtlService.save(employeeActivityDtl);

                            }

                            employeeActivityDtl = employeeActivityDtlService
                                    .findLastNonEndedActivityByType(localEmpMst.getId(), "Incoming Call");

                            if (employeeActivityDtl != null) {
                                employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                                employeeActivityDtlService.save(employeeActivityDtl);

                            }

                            employeeActivityDtl = new EmployeeActivityDtl();
                            employeeActivityDtl.setActivity("Hang Up");
                            employeeActivityDtl.setEmpId(localEmpMst);
                            employeeActivityDtl.setStartTime(CustomConvert.javaDateToTimeStamp(new Date()));
                            ReasonMst reason = reasonMst;
                            employeeActivityDtl.setReasonId(reason);
                            employeeActivityDtl.setReasonCd(reason.getReasonCd());
                            employeeActivityDtl.setReasonDesc(reason.getReasonDesc());
                            employeeActivityDtl.setCallMstId(callMstLocal.getId());
                            employeeActivityDtlService.save(employeeActivityDtl);
                            if (!inCallType.equals("Scheduled")) {

                                EmployeeCallStatus empCallStatus = new EmployeeCallStatus();
                                List<EmployeeCallStatus> empClStatusList = employeeCallStatusService
                                        .findEmployeeCallStatusByEmpId(localEmpMst);

                                if (!empClStatusList.isEmpty()) {
                                    for (EmployeeCallStatus empstatus : empClStatusList) {
                                        empCallStatus = empstatus;
                                    }

                                    empCallStatus.setStatus(false);
                                    empCallStatus.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                                    employeeCallStatusService.saveEmployeeCallStatus(empCallStatus);
                                } else {
                                    empCallStatus.setEmpId(localEmpMst);
                                    empCallStatus.setCallCount(1);
                                    empCallStatus.setStatus(false);
                                    empCallStatus.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                                    employeeCallStatusService.saveEmployeeCallStatus(empCallStatus);
                                }

                            } else {

                                employeeActivityDtl = employeeActivityDtlService
                                        .findLastNonEndedActivityByType(localEmpMst.getId(), "Blocked");

                                if (employeeActivityDtl != null) {
                                    employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                                    employeeActivityDtlService.save(employeeActivityDtl);
                                }

                            }

                            if (!findOtherNonEndedCallDetailFromSameCallMst.isEmpty()) {
                                for (CallDtl calldtl : findOtherNonEndedCallDetailFromSameCallMst) {
                                    if (calldtl.getEndTime() == null) {
                                        calldtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                                        calldtl.setDeleteFlg(false);
                                        calldtl.setAgentComments("Not Recieved By Customer");
                                        calldtl.setActiveFlg(true);
                                        calldtl = callDtlService.saveCallDtl(calldtl);
                                    }
                                }
                                findOtherNonEndedCallDetailFromSameCallMst.clear();
                            }

                            SocketMessage.send(callScheduler.getAdminSocket(), usertext,
                                    "missed#" + callMstLocal.getId());
                        }
                    }
                }
                agentLoginResponse = new AgentLoginResponse();
                agentLoginResponse.setSuccMsg(MSG_SUCCESS);
                return agentLoginResponse;
            } else {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Please Provide valid information!");
                throw ccmsRestException;
            }

        } else {
            CCMSRestException ccmsRestException = new CCMSRestException();
            ccmsRestException.setErrorCode("418");
            ccmsRestException.setErrorMessage("Please Provide valid information!");
            throw ccmsRestException;
        }
    }

    @Override
    public List<ParticipantDto> getInCallEmployeeListToCustomerForFileUploadResponse(String callInfo) throws Exception {

        String callId = null;
        List<ParticipantDto> fileUploadDtoList = new ArrayList<>();
        CallMst callMster;
        /**
         * @Purpose To check whether method parameter callInfo is null or not
         */
        if (callInfo != null && callInfo.length() > 0) {
            JSONObject callInformation = new JSONObject(callInfo);
            if (callInformation.has("callId")) {
                callId = callInformation.getString("callId");
                logger.info("with callid=" + callId + "    Called");
            } else {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Please Provide valid information!");
                throw ccmsRestException;
            }

        } else {
            CCMSRestException ccmsRestException = new CCMSRestException();
            ccmsRestException.setErrorCode("418");
            ccmsRestException.setErrorMessage("Please Provide valid information!");
            throw ccmsRestException;
        }

        if (callId != null && callId.length() > 0) {
            callMster = callMstService.findCallMstById(Long.parseLong(callId));
            if (callMster != null && callMster.getId() > 0) {
                logger.info(":::Call from Customer End::::::");
                List<CallDtl> findOtherNonEndedCallDetailFromSameCallMst = callDtlService
                        .findNonEndedCallDtlByCallMasterId(callMster.getId());
                for (CallDtl lCallDtl : findOtherNonEndedCallDetailFromSameCallMst) {
                    ParticipantDto lParticipantDto = new ParticipantDto();
                    logger.info("lCallDtl:" + lCallDtl);
                    EmployeeMst lEmployeeMst = employeeMstService
                            .findEmployeeMstById(lCallDtl.getHandeledById().getId());
                    lParticipantDto.setId(lEmployeeMst.getId());
                    logger.info("lEmployeeMst:" + lEmployeeMst + "lEmployeeMst.getEmpTypId().getTypeName():"
                            + lEmployeeMst.getEmpTypId().getTypeName() + "lEmployeeMst.getLoginId():"
                            + lEmployeeMst.getLoginId());
                    lParticipantDto.setLoginId(lEmployeeMst.getLoginId());
                    String name;
                    if (lEmployeeMst.getMidName() != null && !lEmployeeMst.getMidName().equals("")) {
                        name = lEmployeeMst.getFirstName() + " " + lEmployeeMst.getMidName() + " "
                                + lEmployeeMst.getLastName();
                    } else {
                        name = lEmployeeMst.getFirstName() + " " + lEmployeeMst.getLastName();
                    }
                    lParticipantDto.setName(name);
                    lParticipantDto.setParticipantType(this.PARTICIPANT_EMP);
                    if (!fileUploadDtoList.contains(lParticipantDto)) {
                        fileUploadDtoList.add(lParticipantDto);
                    }
                }
                if (fileUploadDtoList.size() > 0) {
                    return fileUploadDtoList;
                } else {
                    CCMSRestException ccmsRestException = new CCMSRestException();
                    ccmsRestException.setErrorCode("418");
                    ccmsRestException.setErrorMessage("Error in finding participants");
                    throw ccmsRestException;
                }

            } else {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Please Provide valid information!");
                throw ccmsRestException;
            }
        } else {
            CCMSRestException ccmsRestException = new CCMSRestException();
            ccmsRestException.setErrorCode("418");
            ccmsRestException.setErrorMessage("Please Provide valid information!");
            throw ccmsRestException;
        }

    }

    @Override
    public List<ParticipantDto> getInCallUserListToAgentforFileUploadResponse(String callInfo) throws Exception {
        String callId = null;
        String empId = null;
        CallMst callMster;
        EmployeeMst em;
        /**
         * @Purpose To check whether method parameter callInfo is null or not
         */
        List<ParticipantDto> fileUploadDtoList = new ArrayList<>();
        if (callInfo != null && callInfo.length() > 0) {
            JSONObject callInformation = new JSONObject(callInfo);
            if (callInformation.has("callId") && callInformation.has("empId")) {
                callId = callInformation.getString("callId");
                empId = callInformation.getString("empId");
                logger.info("with callid=" + callId + "    Called");
            } else {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Please Provide valid information!");
                throw ccmsRestException;
            }

        } else {
            CCMSRestException ccmsRestException = new CCMSRestException();
            ccmsRestException.setErrorCode("418");
            ccmsRestException.setErrorMessage("Please Provide valid information!");
            throw ccmsRestException;
        }

        if (callId != null && callId.length() > 0 && empId != null && empId.length() > 0) {
            logger.info(":::Call from Employee End::::::");
            callMster = callMstService.findCallMstById(Long.parseLong(callId));
            em = employeeMstService.findEmployeeMstById(Long.parseLong(empId));
            if (callMster != null && callMster.getId() > 0 && em != null && em.getId() > 0) {

                logger.info("em-getPerticipentList:" + em);
                // add customer........
                ParticipantDto lParticipantDto = new ParticipantDto();
                if (!callMster.getCallType().equalsIgnoreCase("Dial Call")) {
                    CustomerMst lCustomerMst = customerMstService.findCustomerMstByCustId(callMster.getCustId());
                    lParticipantDto.setId(lCustomerMst.getId());
                    lParticipantDto.setLoginId(lCustomerMst.getCustId());
                    lParticipantDto.setName(lCustomerMst.getFirstName() + lCustomerMst.getLastName());
                    lParticipantDto.setParticipantType(this.PARTICIPANT_CUST);
                    if (!fileUploadDtoList.contains(lParticipantDto)) {
                        fileUploadDtoList.add(lParticipantDto);
                    }

                } else {
                    EmployeeMst lEmployeeMst = null;
                    List<CallDtl> dtlList = callDtlService.findCallDtlByCallMasterIdAndCallTypeInfo(callMster.getId(),
                            "Dial");
                    if (!dtlList.isEmpty()) {

                        lEmployeeMst = employeeMstService.findEmployeeMstById(dtlList.get(0).getHandeledById().getId());
                    }
                    if (null != lEmployeeMst) {
                        if (em.getId() != (long) lEmployeeMst.getId()) {

                            lParticipantDto.setId(lEmployeeMst.getId());

                            lParticipantDto.setLoginId(lEmployeeMst.getLoginId());
                            String name;
                            if (lEmployeeMst.getMidName() != null && !lEmployeeMst.getMidName().equals("")) {
                                name = lEmployeeMst.getFirstName() + " " + lEmployeeMst.getMidName() + " "
                                        + lEmployeeMst.getLastName();
                            } else {
                                name = lEmployeeMst.getFirstName() + " " + lEmployeeMst.getLastName();
                            }
                            lParticipantDto.setNameAndLoginId(name + "(" + lEmployeeMst.getEmail() + ")");
                            if (!fileUploadDtoList.contains(lParticipantDto)) {
                                fileUploadDtoList.add(lParticipantDto);
                            }
                        }
                    }
                }

                List<CallDtl> findOtherNonEndedCallDetailFromSameCallMst = callDtlService
                        .findNonEndedCallDtlByCallMasterIdAndExcludeCurrentEmployee(callMster.getId(), em.getId());
                for (CallDtl lCallDtl : findOtherNonEndedCallDetailFromSameCallMst) {
                    lParticipantDto = new ParticipantDto();
                    logger.info("lCallDtl:" + lCallDtl);
                    EmployeeMst lEmployeeMst = employeeMstService
                            .findEmployeeMstById(lCallDtl.getHandeledById().getId());
                    lParticipantDto.setId(lEmployeeMst.getId());
                    logger.info("lEmployeeMst:" + lEmployeeMst + "lEmployeeMst.getEmpTypId().getTypeName():"
                            + lEmployeeMst.getEmpTypId().getTypeName() + "lEmployeeMst.getLoginId():"
                            + lEmployeeMst.getLoginId());
                    lParticipantDto.setLoginId(lEmployeeMst.getLoginId());
                    String name;
                    if (lEmployeeMst.getMidName() != null && !lEmployeeMst.getMidName().equals("")) {
                        name = lEmployeeMst.getFirstName() + " " + lEmployeeMst.getMidName() + " "
                                + lEmployeeMst.getLastName();
                    } else {
                        name = lEmployeeMst.getFirstName() + " " + lEmployeeMst.getLastName();
                    }
                    lParticipantDto.setName(name);
                    lParticipantDto.setParticipantType(this.PARTICIPANT_EMP);
                    if (!fileUploadDtoList.contains(lParticipantDto)) {
                        fileUploadDtoList.add(lParticipantDto);
                    }
                }

                return fileUploadDtoList;
            } else {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Please Provide valid information!");
                throw ccmsRestException;
            }
        } else {
            CCMSRestException ccmsRestException = new CCMSRestException();
            ccmsRestException.setErrorCode("418");
            ccmsRestException.setErrorMessage("Please Provide valid information!");
            throw ccmsRestException;
        }

    }

    @Override
    public AgentLoginResponse getFileUploadResponse(FileHandleDto model, HttpServletRequest request) throws Exception {
        List<ParticipantDto> fileUploadDtoList = new ArrayList<>();
        AgentLoginResponse agentLoginResponse;
        String jbossHome;
        String filePathToSend;
        if (model != null) {
            org.json.JSONArray jArray = model.getConferenceUsersDtoList();
            if (jArray != null) {
                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject localJSONObject = jArray.getJSONObject(i);
                    if (localJSONObject != null) {
                        ParticipantDto perticipentDto = new ParticipantDto();
                        perticipentDto.setId(localJSONObject.getLong("id"));
                        perticipentDto.setLoginId(localJSONObject.getString("loginId"));

                        if (perticipentDto.getLoginId() != null) {
                            EmployeeMst em = employeeMstService.findEmployeeByUserId(perticipentDto.getLoginId());
                            if (em == null) {
                                perticipentDto.setParticipantType("Customer");
                            } else {
                                perticipentDto.setParticipantType("Employee");

                            }
                        }

                        fileUploadDtoList.add(perticipentDto);
                    }

                }
                Long empId = null;
                String custId = null;
                EmployeeMst currEmp = null;
                CustomerMst currCust = null;

                if (model.getEmpId() != null && model.getEmpId() > 0) {
                    empId = model.getEmpId();
                    currEmp = employeeMstService.findEmployeeMstById(empId);
                } else if (model.getCustId() != null && model.getCustId().length() > 0) {
                    custId = model.getCustId();
                    currCust = customerMstService.findCustomerMstByCustId(custId);
                } else {
                    CCMSRestException ccmsRestException = new CCMSRestException();
                    ccmsRestException.setErrorCode("418");
                    ccmsRestException.setErrorMessage("Please Provide valid information1111!");
                    throw ccmsRestException;
                }
                Long callMstId = model.getCallId();
                String docTitle = model.getDocumentTitle().replaceAll("\"", "");

                MultipartFile mpf = model.getFile();
                String fileName;
                InputStream fileData = null;

                if (mpf != null && !mpf.isEmpty()) {
                    fileName = mpf.getOriginalFilename();
                    fileData = mpf.getInputStream();
                } else {
                    CCMSRestException ccmsRestException = new CCMSRestException();
                    ccmsRestException.setErrorCode("418");
                    ccmsRestException.setErrorMessage("Please Provide valid information22222!");
                    throw ccmsRestException;
                }
                String filePathForDatabase;
                CallMst callMster = null;
                if (callMstId != null && callMstId > 0) {
                    callMster = callMstService.findCallMstById(callMstId);
                } else {
                    CCMSRestException ccmsRestException = new CCMSRestException();
                    ccmsRestException.setErrorCode("418");
                    ccmsRestException.setErrorMessage("Please Provide valid informationdddddddd!");
                    throw ccmsRestException;
                }

                String websiteURL = request.getRequestURL().substring(0, request.getRequestURL().indexOf("/", 9));
                boolean flag = false;
                Long result;
                jbossHome = System.getenv("JBOSS_HOME");
                if (fileUploadDtoList.isEmpty()) {
                    logger.error("Please Select Participant(s)");
                    CCMSRestException ccmsRestException = new CCMSRestException();
                    ccmsRestException.setErrorCode("418");
                    ccmsRestException.setErrorMessage("Please Select Participant(s)!");
                    throw ccmsRestException;
                }

                if (docTitle != null && !docTitle.isEmpty()) {
                    logger.info("fileUploadListener-docTitle:" + docTitle);
                    String fName;

                    if (fileData != null) {
                        fName = fileName;

                        Random rand = new Random();
                        int no = rand.nextInt(1000) + 1;
                        /* FILE BACKUP */
                        String backupFileLocation = jbossHome + File.separator + "standalone" + File.separator
                                + "File_Upload" + File.separator + "Call" + File.separator + callMster.getId()
                                + File.separator + no + fName;

                        File backupFile = new File(backupFileLocation);

                        FileUtils.copyInputStreamToFile(fileData, backupFile);
                        /* FILE COPY */
                        String deploymentDirectoryPath = context.getRealPath(File.separator);

                        String finalFilePath = deploymentDirectoryPath + "/resources" + File.separator + "File_Upload"
                                + File.separator + "Call" + File.separator + callMster.getId() + File.separator + no
                                + fName;

                        File finalFile = new File(finalFilePath);
                        FileUtils.copyFile(backupFile, finalFile);

                        filePathForDatabase = "/resources/File_Upload/Call/" + callMster.getId() + "/" + no + fName;
                        filePathToSend = websiteURL + request.getContextPath() + filePathForDatabase;
                        if (!fileUploadDtoList.isEmpty() && fileUploadDtoList.size() > 0) {
                            for (ParticipantDto obj : fileUploadDtoList) {
                                CallFileUploadDtls callFileUploadDtls = new CallFileUploadDtls();
                                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                                callFileUploadDtls.setCreatedDateTime(timestamp);
                                callFileUploadDtls.setFileReceivedBy(obj.getId());
                                callFileUploadDtls.setCallMstId(callMster);
                                if (empId != null && null != currEmp && Objects.equals(currEmp.getId(), empId)) {
                                    callFileUploadDtls.setFileSentBy(currEmp.getId());
                                    callFileUploadDtls.setCreatedBy(currEmp.getId());
                                    callFileUploadDtls.setFileSentbyType(this.PARTICIPANT_EMP);
                                } else if (custId != null && null != currCust && custId.length() > 0 && custId.compareTo(currCust.getCustId()) == 0) {
                                    callFileUploadDtls.setFileSentBy(currCust.getId());
                                    callFileUploadDtls.setCreatedBy(currCust.getId());
                                    callFileUploadDtls.setFileSentbyType(this.PARTICIPANT_CUST);
                                }
                                callFileUploadDtls.setFilePath(filePathForDatabase);
                                callFileUploadDtls.setDocTitle(docTitle);
                                if (obj.getParticipantType() != null
                                        && obj.getParticipantType().equalsIgnoreCase(this.PARTICIPANT_CUST)) {
                                    callFileUploadDtls.setFileReceivedbyType(this.PARTICIPANT_CUST);
                                } else {
                                    callFileUploadDtls.setFileReceivedbyType(this.PARTICIPANT_EMP);
                                }

                                result = callFileUploadDtlsService.saveCallFileDetails(callFileUploadDtls);
                                if (obj.getParticipantType() != null && obj.getParticipantType().equalsIgnoreCase(this.PARTICIPANT_EMP)) {
                                    SocketMessage.send(callScheduler.getAdminSocket(), obj.getLoginId(), "fileSentByCust#" + filePathToSend + "#" + docTitle);
                                } else {
                                    SocketMessage.send(callScheduler.getAdminSocket(), obj.getLoginId(), "fileSent#" + filePathToSend + "#" + docTitle);
                                }
                                if (result > 0) {
                                    flag = true;
                                } else {
                                    flag = false;
                                    break;
                                }
                            }
                        }

                    }
                    if (flag) {
                        agentLoginResponse = new AgentLoginResponse();
                        agentLoginResponse.setSuccMsg(MSG_SUCCESS);
                        return agentLoginResponse;
                    } else {
                        CCMSRestException ccmsRestException = new CCMSRestException();
                        ccmsRestException.setErrorCode("418");
                        ccmsRestException.setErrorMessage("Please Provide valid information33333!");
                        throw ccmsRestException;
                    }
                } else {
                    CCMSRestException ccmsRestException = new CCMSRestException();
                    ccmsRestException.setErrorCode("418");
                    ccmsRestException.setErrorMessage("Please Provide valid information4444!");
                    throw ccmsRestException;
                }
            } else {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Please Provide valid information5555!");
                throw ccmsRestException;
            }
        } else {
            CCMSRestException ccmsRestException = new CCMSRestException();
            ccmsRestException.setErrorCode("418");
            ccmsRestException.setErrorMessage("Please Provide valid information666666!");
            throw ccmsRestException;
        }
    }

    @Override
    public List<SpecialistDto> getMultiwaySpecialistList() throws Exception {
        List<EmployeeCallStatus> availableSpecialists = employeeCallStatusService.findFreeOnlineSpecialists();
        List<SpecialistDto> specialistDtolists = new ArrayList<>();
        if (!availableSpecialists.isEmpty()) {
            for (int i = 0; i < availableSpecialists.size(); i++) {
                SpecialistDto sp = new SpecialistDto();
                sp.setEmpId(availableSpecialists.get(i).getEmpId().getId());
                sp.setLoginid(availableSpecialists.get(i).getEmpId().getLoginId());
                sp.setAgentName(availableSpecialists.get(i).getEmpId().getFirstName());
                specialistDtolists.add(sp);
            }
        }
        return specialistDtolists;
    }

    @Override
    public List<SpecialistDto> getForwardAgentList() throws Exception {
        List<EmployeeCallStatus> availableSpecialists = employeeCallStatusService.findFreeOnlineAgents();
        List<SpecialistDto> specialistDtolists = new ArrayList<>();
        if (!availableSpecialists.isEmpty()) {
            for (int i = 0; i < availableSpecialists.size(); i++) {
                SpecialistDto sp = new SpecialistDto();
                sp.setLoginid(availableSpecialists.get(i).getEmpId().getLoginId());
                sp.setAgentName(availableSpecialists.get(i).getEmpId().getFirstName());
                specialistDtolists.add(sp);
            }
        }
        return specialistDtolists;
    }

    @Override
    public AgentLoginResponse getMultiwaySpecialistCallResponse(String callInfo) throws Exception {
        logger.info("onMultiwayEmployeeCall----------");
        EmployeeMst currEmp;
        CallMst cm;
        String spcialistmessage;
        String callId = null;
        String agentId = null;
        CallMst callMstLocal;
        String agentLoginId = null;
        AgentLoginResponse agentLoginResponse = new AgentLoginResponse();
        if (callInfo != null && callInfo.length() > 0) {
            JSONObject callInformation = new JSONObject(callInfo);
            if (callInformation.has("callId") && callInformation.has("empId") && callInformation.has("empLoginId")) {
                callId = callInformation.getString("callId");
                agentId = callInformation.getString("empId");
                agentLoginId = callInformation.getString("empLoginId");
                logger.info("with callid=" + callId + "    Called");
            } else {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Please Provide valid information!");
                throw ccmsRestException;
            }

        } else {
            CCMSRestException ccmsRestException = new CCMSRestException();
            ccmsRestException.setErrorCode("418");
            ccmsRestException.setErrorMessage("Please Provide valid information!");
            throw ccmsRestException;
        }

        if (agentId != null && agentId.length() > 0 && callId != null && callId.length() > 0 && agentLoginId != null
                && agentLoginId.length() > 0) {
            callMstLocal = callMstService.findCallMstById(Long.parseLong(callId));
            currEmp = employeeMstService.findEmployeeMstById(Long.parseLong(agentId));
            if (callMstLocal != null && callMstLocal.getId() > 0 && currEmp != null && currEmp.getId() > 0) {

                EmployeeMst em = employeeMstService.findAllEmployeeByUserId(agentLoginId);
                if (null != em) {
                    List<EmployeeCallStatus> empClStatusList = employeeCallStatusService.findEmployeeCallStatusByEmpId(em);
                    if (!empClStatusList.isEmpty() && empClStatusList.get(0).getStatus() == false) {

                    } else {

                        String spId = agentLoginId;
                        logger.info("VideoAgent-----searchSpecialist.........................................." + spId);

                        cm = callMstLocal;
                        EmployeeMst employeeMstLocal = currEmp;

                        EmployeeMst selected_specialist = employeeMstService.findEmployeeByUserId(spId);
                        TenancyEmployeeMap tenancyEmployeeMap;
                        if (null != selected_specialist) {

                            EmployeeTypeMst etm = employeeTypeMstService.findEmployeeTypeMstById(selected_specialist.getEmpTypId().getId());
                            if (etm.getTypeName().equalsIgnoreCase("Specialist")) {
                                EmployeeCallStatus employeeCallStatus = null;
                                if (!employeeCallStatusService.findEmployeeCallStatusByEmpId(selected_specialist).isEmpty()) {
                                    employeeCallStatus = employeeCallStatusService.findEmployeeCallStatusByEmpId(selected_specialist).get(0);
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
                                    logger.info(selected_specialist.getLoginId() + "ThreeWay#" + cm.getId() + "#" + tenancyEmployeeMap.getRoomLink() + "#" + tenancyEmployeeMap.getRoomName());
                                    SocketMessage.send(callScheduler.getAdminSocket(), selected_specialist.getLoginId(), "ThreeWay#" + cm.getId() + "#" + tenancyEmployeeMap.getRoomLink() + "#" + tenancyEmployeeMap.getRoomName() + "#" + cm.getCallOption());

                                    /**
                                     * Added By VD on 09-11-2020
                                     */
                                    CallEmployeeMap l_CallEmpMap = new CallEmployeeMap();
                                    l_CallEmpMap.setCallId(cm.getId());
                                    l_CallEmpMap.setEmployeeId(selected_specialist.getId());
                                    l_CallEmpMap.setRoomLink(tenancyEmployeeMap.getRoomLink());
                                    l_CallEmpMap.setCallType("ThreeWay");
                                    CallScheduler.listCallEmp.add(l_CallEmpMap);

                                    Timestamp dt_time = null;
                                    try {
                                        dt_time = CustomConvert.javaDateToTimeStamp(new Date());
                                    } catch (ParseException ex) {
                                        logger.error(ex);
                                    }

                                    CallDtl callDtl = new CallDtl();
                                    callDtl.setCallMstId(cm);
                                    callDtl.setCallTypeInfo("Threeway Specialist");
                                    callDtl.setHandeledById(selected_specialist);
                                    callDtl.setActiveFlg(true);
                                    callDtl.setDeleteFlg(false);
                                    callDtl.setStartTime(dt_time);
                                    callDtlService.saveCallDtl(callDtl);

                                    ForwardedCall forwardedCall = new ForwardedCall();
                                    forwardedCall.setEmpId(selected_specialist);
                                    forwardedCall.setRoomLink(tenancyEmployeeMap.getRoomLink());
                                    forwardedCall.setEntityId(tenancyEmployeeMap.getEntityId());
                                    forwardedCall.setCallId(cm);
                                    forwardedCall.setForwardedDatetime(dt_time);
                                    forwardedCall.setActiveFlg(true);
                                    forwardedCall.setDeleteFlg(false);
                                    forwardedCall.setPrevEmpId(employeeMstLocal.getId());
                                    forwardedCall.setSelectedServiceId(cm.getServiceId());
                                    forwardedCall.setFromServiceId(cm.getServiceId());

                                    forwardedCallService.saveForwardedCalls(forwardedCall, employeeMstLocal);

                                    spcialistmessage = spId + " is online and waiting for joining";
                                    employeeCallStatus.setStatus(false);
                                    employeeCallStatusService.saveEmployeeCallStatus(employeeCallStatus);
                                    agentLoginResponse.setValidateMsg(spcialistmessage);

                                } else {
                                    Long lastActivity = employeeActivityDtlService.findMaxIdByEmpId(selected_specialist.getId());
                                    EmployeeActivityDtl login_activityDtl = employeeActivityDtlService
                                            .findByActivityId(lastActivity);
                                    if (login_activityDtl != null) {
                                        if (login_activityDtl.getActivity().equalsIgnoreCase("login")) {
                                            spcialistmessage = "Sorry! " + etm.getTypeName()
                                                    + " Logged in but is not ready";
                                            agentLoginResponse.setValidateMsg(spcialistmessage);
                                        } else if (login_activityDtl.getActivity().equalsIgnoreCase("Hang Up")) {
                                        } else if (login_activityDtl.getActivity().equalsIgnoreCase("logout")) {
                                            spcialistmessage = "Sorry! " + etm.getTypeName() + " not Logged in";
                                            agentLoginResponse.setValidateMsg(spcialistmessage);
                                        } else if (login_activityDtl.getActivity().equalsIgnoreCase("Call Started")) {
                                            spcialistmessage = "Sorry! " + etm.getTypeName() + " is Busy";
                                            agentLoginResponse.setValidateMsg(spcialistmessage);

                                        } else if (login_activityDtl.getActivity()
                                                .equalsIgnoreCase("Threeway Specialist")) {
                                            spcialistmessage = "Sorry! " + etm.getTypeName() + " is Busy";
                                            agentLoginResponse.setValidateMsg(spcialistmessage);
                                        } else {
                                            spcialistmessage = "Sorry! " + etm.getTypeName()
                                                    + " Logged in but is not ready";
                                            agentLoginResponse.setValidateMsg(spcialistmessage);
                                        }
                                    } else {
                                        spcialistmessage = "Sorry! " + etm.getTypeName() + " not Logged in";
                                        agentLoginResponse.setValidateMsg(spcialistmessage);
                                    }
                                }

                            } else {

                                spcialistmessage = "Sorry! " + etm.getTypeName() + " not found";
                                agentLoginResponse.setValidateMsg(spcialistmessage);

                            }
                        }

                        Set<String> custIdSet = new TreeSet<>();
                        if (!CallScheduler.customerCallDetailsMap.isEmpty()) {

                            custIdSet = (Set<String>) CallScheduler.customerCallDetailsMap.get(cm.getId().toString());

                        }

                        if (null == custIdSet) {
                            custIdSet = new TreeSet<>();
                        }
                        custIdSet.add(spId);

                        CallScheduler.customerCallDetailsMap.put(cm.getId().toString(), custIdSet);

                        spcialistmessage = spId + " is online and waiting joining";
                        agentLoginResponse.setValidateMsg(spcialistmessage);
                        ForwardedCall threeway = forwardedCallService.findForwardedCallByCallIdAndEmpId(cm);
                        if (threeway != null) {
                            threeway.setCallPickupTime(null);
                            forwardedCallService.saveForwardedCalls(threeway, currEmp);
                        }

                    }
                    return agentLoginResponse;
                } else {
                    CCMSRestException ccmsRestException = new CCMSRestException();
                    ccmsRestException.setErrorCode("418");
                    ccmsRestException.setErrorMessage("Please Provide valid information!");
                    throw ccmsRestException;
                }
            } else {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Please Provide valid information!");
                throw ccmsRestException;
            }
        } else {
            CCMSRestException ccmsRestException = new CCMSRestException();
            ccmsRestException.setErrorCode("418");
            ccmsRestException.setErrorMessage("Please Provide valid information!");
            throw ccmsRestException;
        }

    }

    @Override
    public AgentLoginResponse getScheduleCallResponse(CustomerDto customerDto) throws Exception {
        final TimeZone timeZoneL = TimeZone.getDefault();

        ScheduleCall scheduleCall = null;
        if (customerDto != null) {
            if (customerDto.getScheduleId() != null && customerDto.getScheduleId().length() > 0) {
                scheduleCall = callSchedulerService.findNonTakenById(Long.parseLong(customerDto.getScheduleId()));
            }
        } else {
            CCMSRestException ccmsRestException = new CCMSRestException();
            ccmsRestException.setErrorCode("418");
            ccmsRestException.setErrorMessage("Wrong Credential!");
            throw ccmsRestException;
        }
        if (scheduleCall == null || scheduleCall.getId() == null) {
            scheduleCall = new ScheduleCall();
        }

        ScheduleCall schCall;
        CustomerMst cm;
        Date localSchDateTime;
        AgentLoginResponse agentLoginResponse;
        CategoryMst cat = null;
        LanguageMst langMst = null;
        ServiceMst serviceMst = null;

        TimeZone tz = TimeZone.getTimeZone(customerDto.getTimeZone());
        ThreadSafeSimpleDateFormat sdf = new ThreadSafeSimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(tz);

        localSchDateTime = CustomConvert.convertScheduleTimeZone(customerDto.getSchDateTime(),
                customerDto.getTimeZone(), timeZoneL.getID());
        java.sql.Timestamp timestamp = new java.sql.Timestamp(localSchDateTime.getTime());

        if (localSchDateTime.after(new Date())) {
            if (customerDto.getCustId() != null && !"".equals(customerDto.getCustId())) {

                cm = customerMstService.findCustomerMstByCustId(customerDto.getCustId());
                if (cm != null) {

                    if (customerDto.getScheduleId() != null && customerDto.getScheduleId().length() > 0) {
                        //
                    } else {
                        cat = categoryMstService.findCategoryByCategoryName(customerDto.getCategory());
                        langMst = langMstService.findLanguageMstByLanguageName(customerDto.getLanguage());
                        serviceMst = serviceMstService.findServiceByServiceName(customerDto.getService());
                    }
                    scheduleCall.setCreationDatetime(CustomConvert.javaDateToTimeStamp(new Date()));
                    scheduleCall.setExecuteStatus("Request");
                    scheduleCall.setScheduledBy("Customer");
                    scheduleCall.setSchedulerId((long) 0);
                    scheduleCall.setScheduleDate(timestamp);
                    /**
                     * bcz not required for now
                     */
                    // scheduleCall.setCustomerId(cm);
                    scheduleCall.setCallMedium("MOB");

                    if (serviceMst != null) {
                        scheduleCall.setService(serviceMst.getId());
                    }
                    if (langMst != null) {
                        scheduleCall.setLanguage(langMst.getId());
                    }
                    if (cat != null) {
                        scheduleCall.setCategory(cat.getId());
                    }

                    scheduleCall.setActiveFlg(true);
                    scheduleCall.setDeleteFlg(false);
                    scheduleCall.setCustomerId(cm);
                    scheduleCall.setScheduledBy(this.PARTICIPANT_CUST);
                    scheduleCall.setCustomerTime(null);
                    schCall = callSchedulerService.saveScheduleCall(scheduleCall, null);
                    String messageBody;
                    String custEmail = cm.getEmail();
                    if (null != custEmail) {
                        messageBody = "<html><body>Dear &nbsp;" + cm.getFirstName();
                        messageBody += ",&nbsp;<br></br>We have scheduled your call with our Video Agent at ";
                        messageBody += sdf.format(schCall.getScheduleDate());
                        messageBody += "&nbsp;<br></br>Please sign in , preferably 10 minutes before the time.";
                        messageBody += "<br></br>";
                        messageBody += "<br></br>";
                        messageBody += "&nbsp;<br></br>You will receive a schedule confirmation mail shortly.";
                        // messageBody+=
                        // websiteURL+"/faces/pages/customer/customerScheduleCall.xhtml?scheduleCallId="+scCall.getId();
                        messageBody += "<br></br>";
                        messageBody += "<b>Note:</b>Please do not refresh the browser during the call.";
                        messageBody += "<br></br>";
                        messageBody += SendingMailUtil.TELE_THX_HTML;
                        messageBody += "</body><html/>";

                        try {
                            boolean mailSend = SendingMailUtil.sendEMail(custEmail, messageBody,
                                    SendingMailUtil.SCHEDULE_CALL);
                            if (mailSend) {
                                logger.info("mail sending was successfull... to Customer" + custEmail);
                            }
                        } catch (Exception ex) {
                            logger.error("Sending Email Error:" + ex.getMessage());
                            agentLoginResponse = new AgentLoginResponse();
                            agentLoginResponse.setSuccMsg(
                                    "Your call has been scheduled successfully but email send is unsuccessfull");
                            return agentLoginResponse;
                        }

                    }
                    if (null != schCall) {
                        agentLoginResponse = new AgentLoginResponse();
                        agentLoginResponse.setSuccMsg("Your call has been scheduled successfully");
                        return agentLoginResponse;

                    } else {
                        CCMSRestException ccmsRestException = new CCMSRestException();
                        ccmsRestException.setErrorCode("418");
                        ccmsRestException.setErrorMessage("Schedule call failed!");
                        throw ccmsRestException;

                    }
                } else {
                    CCMSRestException ccmsRestException = new CCMSRestException();
                    ccmsRestException.setErrorCode("418");
                    ccmsRestException.setErrorMessage("Schedule call failed!");
                    throw ccmsRestException;
                }
            } else {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Schedule call failed!");
                throw ccmsRestException;
            }
        } else {
            CCMSRestException ccmsRestException = new CCMSRestException();
            ccmsRestException.setErrorCode("418");
            ccmsRestException.setErrorMessage("Schedule call failed!");
            throw ccmsRestException;
        }

    }

    @Override
    public AgentLoginResponse getScheduleCallCheckResponse(String callInfo) throws Exception {

        logger.info("in side checkschedulecall");
        String custId = null;
        AgentLoginResponse agentLoginResponse;
        CustomerMst customerMst;
        if (callInfo != null && callInfo.length() > 0) {
            JSONObject callInformation = new JSONObject(callInfo);
            if (callInformation.has("custId")) {
                custId = callInformation.getString("custId");
                logger.info("with custId=" + custId);
            } else {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Please Provide valid information!");
                throw ccmsRestException;
            }

        } else {
            CCMSRestException ccmsRestException = new CCMSRestException();
            ccmsRestException.setErrorCode("418");
            ccmsRestException.setErrorMessage("Please Provide valid information!");
            throw ccmsRestException;
        }
        if (custId != null && custId.length() > 0) {
            customerMst = customerMstService.findCustomerMstByCustId(custId);
            if (customerMst != null && customerMst.getId() > 0) {
                agentLoginResponse = new AgentLoginResponse();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss:SS");
                List<ScheduleCall> listOfCall;

                Date date = new Date();
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                Calendar cal1 = Calendar.getInstance();
                cal1.setTime(date);
                cal1.add(Calendar.MINUTE, 10);

                Timestamp totime = new Timestamp(dateFormat.parse(dateFormat.format(cal.getTimeInMillis())).getTime());
                Timestamp totime1 = new Timestamp(
                        dateFormat.parse(dateFormat.format(cal1.getTimeInMillis())).getTime());

                listOfCall = callSchedulerService.findAllNonTakenScheduleCallByTimeRangeByCustId(totime, totime1,
                        customerMst);
                if (listOfCall != null && !listOfCall.isEmpty() && listOfCall.size() > 0) {
                    for (ScheduleCall localScheduleCall : listOfCall) {
                        localScheduleCall.setCustomerTime(CustomConvert.javaDateToTimeStamp(new Date()));
                        callSchedulerService.saveScheduleCall(localScheduleCall, null);
                    }

                    agentLoginResponse.setSuccMsg("You have a schedule call in next 10 minutes");
                } else {
                    agentLoginResponse.setSuccMsg("You have no schedule call in next 10 minutes");
                }
                return agentLoginResponse;
            } else {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Please Provide valid information!");
                throw ccmsRestException;
            }
        } else {
            CCMSRestException ccmsRestException = new CCMSRestException();
            ccmsRestException.setErrorCode("418");
            ccmsRestException.setErrorMessage("Please Provide valid information!");
            throw ccmsRestException;
        }
    }

    @Override
    public IncomingCallResponse getScheduleCallVerificationResponse(String callInfo) throws Exception {
        String scheduleId = null;
        final TimeZone timeZoneL = TimeZone.getDefault();
        IncomingCallResponse incomingCallResPonse = new IncomingCallResponse();
        ThreadSafeSimpleDateFormat sdf = new ThreadSafeSimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String custId = null;
        String mobileNo = null;
        ScheduleCall schedulCall;
        if (callInfo != null && callInfo.length() > 0) {
            JSONObject callInformation = new JSONObject(callInfo);
            if (callInformation.has("scheduleId")) {
                scheduleId = callInformation.getString("scheduleId");
                logger.info("with scheduleId=" + scheduleId);
                if (callInformation.has("mobileNo") && callInformation.getString("mobileNo") != null && callInformation.getString("mobileNo").length() > 0) {
                    mobileNo = callInformation.getString("mobileNo");
                    logger.info("New customer with mobileNo=" + scheduleId);
                } else if (callInformation.has("custId") && callInformation.getString("custId") != null && callInformation.getString("custId").length() > 0) {
                    custId = callInformation.getString("custId");
                    logger.info("Registered customer with scheduleId=" + scheduleId);
                } else {
                    CCMSRestException ccmsRestException = new CCMSRestException();
                    ccmsRestException.setErrorCode("418");
                    ccmsRestException.setErrorMessage("Wrong Credential!");
                    throw ccmsRestException;
                }
            } else {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Wrong Credential!");
                throw ccmsRestException;
            }

            if (scheduleId != null && scheduleId.length() > 0 && CustomerConstant.isNumericCustom(scheduleId)) {
                schedulCall = callSchedulerService.findNonTakenById(Long.parseLong(scheduleId));
                Date now = new Date();
                long MAX_DURATION = MILLISECONDS.convert(10, MINUTES);

                if (schedulCall != null && schedulCall.getId() > 0) {
                    long duration = now.getTime() - schedulCall.getScheduleDate().getTime();

                    if (duration <= MAX_DURATION) {
                        CustomerMst localCm = customerMstService.findCustomerMstById(schedulCall.getCustomerId().getId());
                        if (null != localCm) {
                            incomingCallResPonse.setCustFname(localCm.getFirstName());
                            incomingCallResPonse.setCustLname(localCm.getLastName());
                            incomingCallResPonse.setSocketHostPublic(Constants.socketHostPublic);
                            incomingCallResPonse.setCustomerId(localCm.getCustId());
                            incomingCallResPonse.setCustMstId(localCm.getId());
                            incomingCallResPonse.setMobileNo("" + localCm.getCellPhone());
                            Date date = new Date();
                            date.setTime(schedulCall.getScheduleDate().getTime());
                            incomingCallResPonse.setScheduleTime(sdf.format(date));
                            incomingCallResPonse.setTimeZone(timeZoneL.getID());
                            CustomerDeviceDtl customerDeviceDtl = new CustomerDeviceDtl();
                            customerDeviceDtl.setLogout(0);

                            customerDeviceDtl.setDeviceBrand(null);
                            customerDeviceDtl.setDeviceId(null);
                            customerDeviceDtl.setDeviceIp(null);
                            customerDeviceDtl.setImeiNo(null);

                            customerDeviceDtl.setMobileOsType("android");
                            customerDeviceDtl.setStaticIp(null);

                            customerDeviceDtl.setCustomerId(localCm);
                            customerDeviceDtl.setCustomerOtp(null);
                            customerDeviceDtl.setTimezone("Asia/Kolkata");
                            if (localCm.getAccountNo() != null && localCm.getAccountNo().trim().length() > 0) {
                                customerDeviceDtl.setLoginInfo(1);
                                incomingCallResPonse.setCustomerType(this.CUSTOMER_REGISTERED);

                            } else {
                                customerDeviceDtl.setLoginInfo(0);
                                incomingCallResPonse.setCustomerType(this.CUSTOMER_NEW);
                            }
                            customerDeviceDtlService.saveCustomerDeviceDtl(customerDeviceDtl, null);
                            CustomerConstant.customerLoginFlag.put(localCm.getCustId(), 0);
                            CustomerConstant.customerMediumFlag.put(localCm.getCustId(), "android");
                            PromotionalVideoMst lVideoMst = promotionalVideoMstService.findSelectedPromotionalVideo();
                            if (lVideoMst == null) {
                                String websiteURL = requests.getRequestURL().substring(0, requests.getRequestURL().indexOf("/", 9));
                                String promoVideo = websiteURL + requests.getContextPath() + "/promotional/promo.mp4";
                                incomingCallResPonse.setPromotionalVideo(promoVideo);
                            } else {
                                String videoUrl = promotionalVideoMstService.getVideoFileUrl(lVideoMst.getFileUrl(), requests,
                                        context);
                                incomingCallResPonse.setPromotionalVideo(videoUrl);
                            }
                            if (mobileNo != null && mobileNo.length() > 0) {
                                // for new customer
                                if (localCm.getId() > 0
                                        && localCm.getCellPhone() == Long.parseLong(mobileNo)) {
                                    return incomingCallResPonse;
                                } else {
                                    CCMSRestException ccmsRestException = new CCMSRestException();
                                    ccmsRestException.setErrorCode("418");
                                    ccmsRestException.setErrorMessage("Wrong Credential!");
                                    throw ccmsRestException;
                                }

                            } else if (custId != null && custId.length() > 0) {
                                // for registered customer
                                if (localCm.getId() > 0 && localCm.getCustId().equals(custId)) {
                                    return incomingCallResPonse;
                                } else {
                                    CCMSRestException ccmsRestException = new CCMSRestException();
                                    ccmsRestException.setErrorCode("418");
                                    ccmsRestException.setErrorMessage("Wrong Credential!");
                                    throw ccmsRestException;
                                }
                            } else {
                                CCMSRestException ccmsRestException = new CCMSRestException();
                                ccmsRestException.setErrorCode("418");
                                ccmsRestException.setErrorMessage("Wrong Credential!");
                                throw ccmsRestException;
                            }

                        } else {
                            CCMSRestException ccmsRestException = new CCMSRestException();
                            ccmsRestException.setErrorCode("418");
                            ccmsRestException.setErrorMessage("Schedule time is over!");
                            throw ccmsRestException;
                        }
                    } else {
                        CCMSRestException ccmsRestException = new CCMSRestException();
                        ccmsRestException.setErrorCode("418");
                        ccmsRestException.setErrorMessage("Wrong Credential!");
                        throw ccmsRestException;
                    }
                } else {
                    CCMSRestException ccmsRestException = new CCMSRestException();
                    ccmsRestException.setErrorCode("418");
                    ccmsRestException.setErrorMessage("Wrong Credential!");
                    throw ccmsRestException;
                }
            } else {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Wrong Credential!");
                throw ccmsRestException;
            }

        } else {
            CCMSRestException ccmsRestException = new CCMSRestException();
            ccmsRestException.setErrorCode("418");
            ccmsRestException.setErrorMessage("Wrong Credential!");
            throw ccmsRestException;
        }
    }

    @Override
    public AgentLoginResponse getScheduleCallRejectResponse(String callInfo) throws Exception {
        String callId;
        AgentLoginResponse agentLoginResponse = new AgentLoginResponse();
        if (callInfo != null && callInfo.length() > 0) {
            JSONObject callInformation = new JSONObject(callInfo);
            if (callInformation.has("callId")) {
                callId = callInformation.getString("callId");
                logger.info("with callId=" + callId);
                if (callId != null && callId.length() > 0) {
                    CallMst callMstFromSession = callMstService.findCallMstById(Long.parseLong(callId));
                    if (null != callMstFromSession) {
                        CallMst cm = callMstFromSession;
                        CustomerMst cust = customerMstService.findCustomerMstByCustomerId(callMstFromSession.getCustId());
                        List<ScheduleCall> scallList = callSchedulerService.findScheduleCallByCustomerIdCallMstId(callMstFromSession.getId(), cust.getId());
                        if (!scallList.isEmpty()) {
                            scallList.get(0).setExecuteStatus("Refuse");
                            callSchedulerService.saveScheduleCall(scallList.get(0), null);
                        }

                        cm.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                        cm.setCallStatus("Customer Rejected");
                        callMstService.saveCallMst(cm);
                        CallDtl cd = callDtlService.findCallDtlByCallMstIdForSchduledCall(cm.getId());
                        cd.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                        callDtlService.saveCallDtl(cd);
                        agentLoginResponse.setSuccMsg(this.MSG_SUCCESS);
                        return agentLoginResponse;
                    } else {
                        CCMSRestException ccmsRestException = new CCMSRestException();
                        ccmsRestException.setErrorCode("418");
                        ccmsRestException.setErrorMessage("Wrong Credential!");
                        throw ccmsRestException;
                    }
                } else {
                    CCMSRestException ccmsRestException = new CCMSRestException();
                    ccmsRestException.setErrorCode("418");
                    ccmsRestException.setErrorMessage("Wrong Credential!");
                    throw ccmsRestException;
                }
            } else {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Wrong Credential!");
                throw ccmsRestException;
            }
        } else {
            CCMSRestException ccmsRestException = new CCMSRestException();
            ccmsRestException.setErrorCode("418");
            ccmsRestException.setErrorMessage("Wrong Credential!");
            throw ccmsRestException;
        }
    }

    @Override
    public FeedbackQuestionsResponse getFeedbackResponse() throws Exception {
        FeedbackQuestionsResponse feedbackQuestionsResponse = new FeedbackQuestionsResponse();
        String qualityQuery = "How would you rate the quality of the sevice provided by our representative?";
        String abilityQuery = "How would you rate the the satisfaction with the resolution provided by our representative?";
        String recommendQuery = "Are all your queries get resolved?";
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
        feedbackQuestionsResponse.setQuestionOne(qualityQuery);
        feedbackQuestionsResponse.setQuestionTwo(abilityQuery);
        feedbackQuestionsResponse.setQuestionThree(recommendQuery);

        feedbackQuestionsResponse.setResMessage(MSG_SUCCESS);
        feedbackQuestionsResponse.setResStatus(TRUE_STATUS);
        return feedbackQuestionsResponse;

    }

    @Override
    public FeedbackResponse getFeedbackSaveResponse(FeedbackDto feedbackDto) throws Exception {
        CustomerMst customer;
        CallMst callmst;
        if (feedbackDto != null && feedbackDto.getCust_id() != null && feedbackDto.getCust_id().length() > 0
                && feedbackDto.getCallId() != null && feedbackDto.getCallId() > 0) {
            Long callId = feedbackDto.getCallId();
            String cust_id = feedbackDto.getCust_id();
            callmst = callMstService.findCallMstById(callId);
            customer = customerMstService.findCustomerMstByCustId(cust_id);
            if (callmst != null && callmst.getId() > 0 && customer != null && customer.getId() > 0) {
                String question1Val = feedbackDto.getQuestion1Val();
                String question2Val = feedbackDto.getQuestion2Val();
                String question3Val = feedbackDto.getQuestion3Val();
                String comment = feedbackDto.getComment();
                String qualityQuery = feedbackDto.getQualityQuery();
                String abilityQuery = feedbackDto.getAbilityQuery();
                String recommendQuery = feedbackDto.getRecommendQuery();
                FeedbackResponse feedbackResponse = new FeedbackResponse();
                FeedbackDtl feedbackdtl = new FeedbackDtl();
                feedbackdtl.setCallMstId(callmst);
                feedbackdtl.setActiveFlg(true);
                feedbackdtl.setCustomerId(customer);
                feedbackdtl.setDeleteFlg(false);
                feedbackdtl.setFeedbackParamId((long) 0);
                feedbackdtl.setComments(comment);
                feedbackdtl.setStarRating(question1Val + "~" + question2Val + "~" + question3Val);
                feedbackdtl.setFeedbackQuery(qualityQuery + "~" + abilityQuery + "~" + recommendQuery);
                feedbackdtl.setFeedbackDate(CustomConvert.javaDateToTimeStamp(new Date()));
                feedbackdtl = feedbackDtlService.saveFeedbackDtl(feedbackdtl, null);
                if (feedbackdtl != null && feedbackdtl.getId() > 0) {
                    feedbackResponse.setResMessage(MSG_SUCCESS);
                    feedbackResponse.setResStatus(TRUE_STATUS);
                    return feedbackResponse;
                } else {
                    CCMSRestException ccmsRestException = new CCMSRestException();
                    ccmsRestException.setErrorCode("418");
                    ccmsRestException.setErrorMessage("Please Provide valid information!");
                    throw ccmsRestException;
                }
            } else {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Please Provide valid information!");
                throw ccmsRestException;
            }
        } else {
            CCMSRestException ccmsRestException = new CCMSRestException();
            ccmsRestException.setErrorCode("418");
            ccmsRestException.setErrorMessage("Please Provide valid information!");
            throw ccmsRestException;
        }

    }

    @Override
    public CustomerAccountInformationDetailsDto getCustomerAccountDetailsbyPhoneNoResponse(String callInfo)
            throws Exception {
        CustomerDtl customerDtl;
        CustomerLoanDtl customerLoanDtl;
        CustomerAccountInformationDetailsDto customerAccountDto = null;
        String cellPhoneNo = null;
        String customerStatus = null;
        ThreadSafeSimpleDateFormat sdf = new ThreadSafeSimpleDateFormat("dd-MM-yyyy");
        String website = requests.getRequestURL().substring(0, requests.getRequestURL().indexOf("/", 9));
        if (callInfo != null && callInfo.length() > 0) {
            JSONObject callInformation = new JSONObject(callInfo);
            if (callInformation.has("cellPhoneNo") && callInformation.has("customerStatus")) {
                cellPhoneNo = callInformation.getString("cellPhoneNo");
                customerStatus = callInformation.getString("customerStatus");
                logger.info("with custId=" + cellPhoneNo);
            } else {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Please Provide valid information!");
                throw ccmsRestException;
            }

        } else {
            CCMSRestException ccmsRestException = new CCMSRestException();
            ccmsRestException.setErrorCode("418");
            ccmsRestException.setErrorMessage("Please Provide valid information!");
            throw ccmsRestException;
        }

        // Get Customer Detail by email id of Customer assuming they are same.
        if (customerStatus != null && customerStatus.length() > 0) {

            if (cellPhoneNo != null && cellPhoneNo.length() > 0) {
                if (customerStatus.equalsIgnoreCase(CUSTOMER_NEW)) {
                    customerDtl = customerDtlService.findIDByPhoneNo(cellPhoneNo);
                    if (customerDtl != null && customerDtl.getId() > 0) {
                        logger.info("::::::::::::::::cm:#CUST-PHNNO:::::::::::::::::::::::" + customerDtl.getPhoneNo());
                        customerAccountDto = new CustomerAccountInformationDetailsDto();
                        customerAccountDto.setCustDtlId(customerDtl.getId());
                        customerAccountDto.setAddress(customerDtl.getAddress());
                        customerAccountDto.setDob(sdf.format(customerDtl.getDob().getTime()));
                        customerAccountDto.setEducation(customerDtl.getEducation());
                        customerAccountDto.setEmail(customerDtl.getEmail());
                        customerAccountDto.setFullName(customerDtl.getFullName());
                        customerAccountDto.setGender(customerDtl.getGender());
                        customerAccountDto.setMaritialStatus(customerDtl.getMaritailStatus());
                        customerAccountDto.setNationality(customerDtl.getNationality());
                        customerAccountDto.setOccupation(customerDtl.getOccupation());
                        customerAccountDto.setPhone(customerDtl.getPhoneNo());
                        customerAccountDto.setSalary(customerDtl.getSalary());
                        customerAccountDto
                                .setCustImage(website + requests.getContextPath() + customerDtl.getCustomerImage());
                        customerAccountDto
                                .setAddressProof(website + requests.getContextPath() + customerDtl.getUtilityBill());
                        customerAccountDto.setIdCard(website + requests.getContextPath() + customerDtl.getNationalId());
                        customerAccountDto.setCustomerType(customerDtl.getCustomerType());
                        logger.info("customerDtl:" + customerAccountDto.toString());
                    } else {
                        CCMSRestException ccmsRestException = new CCMSRestException();
                        ccmsRestException.setErrorCode("418");
                        ccmsRestException.setErrorMessage("Please Provide valid information!");
                        throw ccmsRestException;
                    }
                } else if (customerStatus.equalsIgnoreCase(CUSTOMER_REGISTERED)) {
                    customerLoanDtl = customerLoanDtlService.findIDByPhoneNo(cellPhoneNo);
                    if (customerLoanDtl != null && customerLoanDtl.getId() > 0) {
                        logger.info(
                                "::::::::::::::::cm:#CUST-PHNNO:::::::::::::::::::::::" + customerLoanDtl.getPhoneNo());
                        customerAccountDto = new CustomerAccountInformationDetailsDto();
                        customerAccountDto.setCustDtlId(customerLoanDtl.getId());
                        customerAccountDto.setAddress(customerLoanDtl.getAddress());
                        customerAccountDto.setDob(sdf.format(customerLoanDtl.getDob().getTime()));
                        customerAccountDto.setEducation(customerLoanDtl.getEducation());
                        customerAccountDto.setEmail(customerLoanDtl.getEmail());
                        customerAccountDto.setFullName(customerLoanDtl.getFullName());
                        customerAccountDto.setGender(customerLoanDtl.getGender());
                        customerAccountDto.setMaritialStatus(customerLoanDtl.getMaritailStatus());
                        customerAccountDto.setNationality(customerLoanDtl.getNationality());
                        customerAccountDto.setOccupation(customerLoanDtl.getOccupation());
                        customerAccountDto.setPhone(customerLoanDtl.getPhoneNo());
                        customerAccountDto.setSalary(customerLoanDtl.getAnnualIncome());
                        customerAccountDto
                                .setCustImage(website + requests.getContextPath() + customerLoanDtl.getCustomerImage());
                        customerAccountDto.setAddressProof(
                                website + requests.getContextPath() + customerLoanDtl.getUtilityBill());
                        customerAccountDto
                                .setIdCard(website + requests.getContextPath() + customerLoanDtl.getNationalId());
                        customerAccountDto.setAccountNo(customerLoanDtl.getAccountNumber());
                        customerAccountDto.setLoanAmount(customerLoanDtl.getLoanAmount());
                        logger.info("customerLoanDtl:" + customerAccountDto.toString());
                    } else {
                        CCMSRestException ccmsRestException = new CCMSRestException();
                        ccmsRestException.setErrorCode("418");
                        ccmsRestException.setErrorMessage("Please Provide valid information!");
                        throw ccmsRestException;
                    }

                } else {
                    CCMSRestException ccmsRestException = new CCMSRestException();
                    ccmsRestException.setErrorCode("418");
                    ccmsRestException.setErrorMessage("Please Provide valid information!");
                    throw ccmsRestException;
                }
                return customerAccountDto;
            } else {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Please Provide valid information!");
                throw ccmsRestException;
            }
        } else {
            CCMSRestException ccmsRestException = new CCMSRestException();
            ccmsRestException.setErrorCode("418");
            ccmsRestException.setErrorMessage("Please Provide valid information!");
            throw ccmsRestException;
        }
    }

    public boolean pdfGeneration(String Path, CustomerLoanDtl customerLoanDtl) {
        boolean flag = false;
        try {
            ServletContext ctx = context;
            String deploymentDirectoryPath = ctx.getRealPath("/");
            ThreadSafeSimpleDateFormat dateFormat = new ThreadSafeSimpleDateFormat("yyyy-MM-dd");
            try (OutputStream file = new FileOutputStream(new File(Path))) {
                Document document = new Document(PageSize.A4, 50, 50, 50, 50);
                PdfWriter.getInstance(document, file);
                document.addAuthor("Kuber");
                document.addTitle("Terms Of Services");

                document.open();
                PdfPTable table = new PdfPTable(4);
                table.setTotalWidth(400);
                table.setLockedWidth(true);
                table.getDefaultCell().setBorder(0);
                PdfPCell cell;
                cell = new PdfPCell(new Phrase("Customer Information"));
                cell.setColspan(4);
                cell.setBorder(0);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("   "));
                cell.setColspan(4);
                cell.setBorder(0);
                table.addCell(cell);

                table.addCell("Customer Name : ");
                table.addCell(customerLoanDtl.getFullName() + " ");
                table.addCell("Customer Image : ");
                String ss = customerLoanDtl.getCustomerImage();
                // String custFace = ss.substring(ss.lastIndexOf("/") + 1);
                Image img = Image.getInstance(deploymentDirectoryPath + ss);
                PdfPCell cell1 = new PdfPCell(img, true);
                table.addCell(cell1);
                table.addCell("Customer DOB : ");
                table.addCell(dateFormat.format(customerLoanDtl.getDob()) + " ");
                table.addCell(" ");
                table.addCell(" ");
                table.addCell("Nationality : ");
                table.addCell(customerLoanDtl.getNationality() + " ");
                table.addCell("Id Card : ");
                ss = customerLoanDtl.getNationalId();
                // String custIdCard = ss.substring(ss.lastIndexOf("/") + 1);
                Image img1 = Image.getInstance(deploymentDirectoryPath + ss);
                PdfPCell cell11 = new PdfPCell(img1, true);
                table.addCell(cell11);
                table.addCell("Gender : ");
                table.addCell(customerLoanDtl.getGender().equals("M") ? "Male" : "Female" + " ");
                table.addCell("Maritial Status : ");
                table.addCell(customerLoanDtl.getMaritailStatus() + " ");
                table.addCell("Email : ");
                table.addCell(customerLoanDtl.getEmail() + " ");
                table.addCell("Phone No : ");
                table.addCell(customerLoanDtl.getPhoneNo() + " ");
                table.addCell("Address : ");
                table.addCell(customerLoanDtl.getAddress() + " ");
                table.addCell("Address Proof : ");
                ss = customerLoanDtl.getUtilityBill();
                // String custAddproof = ss.substring(ss.lastIndexOf("/") + 1);
                Image img2 = Image.getInstance(deploymentDirectoryPath + ss);
                PdfPCell cell12 = new PdfPCell(img2, true);
                table.addCell(cell12);
                table.addCell("Occupation : ");
                table.addCell(customerLoanDtl.getOccupation() + " ");
                table.addCell("Annual Income : ");
                table.addCell(customerLoanDtl.getAnnualIncome() + " ");
                table.addCell("Education : ");
                table.addCell(customerLoanDtl.getEducation() + " ");
                table.addCell("eSign : ");
                ss = customerLoanDtl.getCustomerSign();
                Image img3 = Image.getInstance(deploymentDirectoryPath + ss);
                PdfPCell cell13 = new PdfPCell(img3, true);

                table.addCell(cell13);

                document.add(table);

                document.close();
            }
            logger.info("PDF FILE CREATED SUCCESSFULLY ................");
            flag = true;
        } catch (IOException | DocumentException e) {

        }
        return flag;
    }

    @Override
    public AgentLoginResponse getCustomerNationalIdCardUploadResponse(FileHandleDto fileHandleDto,
            HttpServletRequest request) throws Exception {

        BufferedOutputStream outputStream;
        FileOutputStream fos;
        BufferedOutputStream outputStream2;
        FileOutputStream fos2;
        boolean flag = false;
        boolean pdfflag = false;
        CallMst callMster = null;
        byte[] fileContentInByte;
        AgentLoginResponse agentLoginResponse = new AgentLoginResponse();

        if (fileHandleDto != null) {
            if (fileHandleDto.getCallId() != null) {
                callMster = callMstService.findCallMstById(fileHandleDto.getCallId());
            } else {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Please Provide valid information!");
                throw ccmsRestException;
            }

            if (fileHandleDto.getFile() == null || fileHandleDto.getFile().isEmpty()) {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Please select file!");
                throw ccmsRestException;

            } else if (fileHandleDto.getFile().getOriginalFilename() == null
                    || fileHandleDto.getFile().getOriginalFilename().equalsIgnoreCase("")
                    || fileHandleDto.getFile().getOriginalFilename().isEmpty()) {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("File Type Not Supported. Please select correct file.");
                throw ccmsRestException;
            } else if (callMster != null && callMster.getId() > 0) {

                String fn = fileHandleDto.getFile().getOriginalFilename();
                String parts[] = fn.split(Pattern.quote("."));
                String ext = parts[1];

                if (ext.equalsIgnoreCase("jpeg") || ext.equalsIgnoreCase("exif") || ext.equalsIgnoreCase("tiff")
                        || ext.equalsIgnoreCase("rif") || ext.equalsIgnoreCase("bmp") || ext.equalsIgnoreCase("png")
                        || ext.equalsIgnoreCase("webp") || ext.equalsIgnoreCase("jpg")) {

                    if (fileHandleDto.getFile().getSize() > 2097152) {
                        CCMSRestException ccmsRestException = new CCMSRestException();
                        ccmsRestException.setErrorCode("418");
                        ccmsRestException.setErrorMessage("Please upload image of size less than 2MB.");
                        throw ccmsRestException;
                    }
                } else if (ext.equalsIgnoreCase("pdf")) {

                    if (fileHandleDto.getFile().getSize() > 2097152) {
                        CCMSRestException ccmsRestException = new CCMSRestException();
                        ccmsRestException.setErrorCode("418");
                        ccmsRestException.setErrorMessage("Please upload image of size less than 2MB.");
                        throw ccmsRestException;
                    }
                    pdfflag = true;
                } else {
                    CCMSRestException ccmsRestException = new CCMSRestException();
                    ccmsRestException.setErrorCode("418");
                    ccmsRestException.setErrorMessage("File extension not supported");
                    throw ccmsRestException;
                }

                fileContentInByte = fileHandleDto.getFile().getBytes();
                if (null != callMster.getCustId() && !"".equals(callMster.getCustId())) {
                    ServletContext ctx = this.context;
                    String deploymentDirectoryPath = ctx.getRealPath("/");
                    String jbossHome = System.getenv("JBOSS_HOME");
                    Random rand = new Random();
                    int no = rand.nextInt(1000) + 1;

                    String tempFilePath = File.separator + "File_Upload" + File.separator + "Call" + File.separator
                            + callMster.getId() + File.separator + no + fileHandleDto.getFile().getOriginalFilename();

                    String path = jbossHome + File.separator + "standalone" + tempFilePath;
                    File n = new File(jbossHome + File.separator + "standalone" + File.separator + "File_Upload"
                            + File.separator + "Call" + File.separator + callMster.getId());
                    if (!n.exists()) {
                        n.mkdirs();
                    }
                    try {
                        n = new File(deploymentDirectoryPath + "/resources" + File.separator + "File_Upload"
                                + File.separator + "Call" + File.separator + callMster.getId());
                        if (!n.exists()) {
                            n.mkdirs();
                        }
                        fos = new FileOutputStream(deploymentDirectoryPath + "/resources" + tempFilePath);
                        outputStream = new BufferedOutputStream(fos);
                        outputStream.write(fileContentInByte);
                        outputStream.close();
                        if (!pdfflag) {
                            if (null != fileHandleDto.getFile().getOriginalFilename()) {
                                List<CallDtl> cdtlist = callDtlService.findCallDtlByCallMasterId(callMster.getId());
                                if (!cdtlist.isEmpty()) {
                                    EmployeeMst em = employeeMstService
                                            .findEmployeeMstById(cdtlist.get(0).getHandeledById().getId());
                                    try {
                                        // logger.info("Before Send Natinal ID===" + no + upFile.getFileName() +
                                        // " SEND TO===" + em.getLoginId());
                                        SocketMessage.send(callScheduler.getAdminSocket(), em.getLoginId(),
                                                "idCardSend#" + no + fileHandleDto.getFile().getOriginalFilename());
                                        flag = true;
                                    } catch (Exception ex) {
                                        logger.error(ex);
                                    }
                                }

                            }
                        } else {
                            String sourceDir = deploymentDirectoryPath + "/resources" + tempFilePath; // Pdf files are
                            // read from
                            // this folder
                            String destinationDir = deploymentDirectoryPath + "/resources" + File.separator
                                    + "File_Upload" + File.separator + "Call" + File.separator + callMster.getId(); // converted
                            // images
                            // from
                            // pdf
                            // document
                            // are
                            // saved
                            // here

                            File sourceFile = new File(sourceDir);
                            File destinationFile = new File(destinationDir);
                            if (!destinationFile.exists()) {
                                destinationFile.mkdir();
                                // logger.info("Folder Created -> "+ destinationFile.getAbsolutePath());
                            }
                            if (sourceFile.exists()) {
                                // logger.info("Images copied to Folder: "+ destinationFile.getName());
                                PDDocument document = PDDocument.load(sourceDir);
                                List<PDPage> list = document.getDocumentCatalog().getAllPages();
                                // logger.info("Total files to be converted -> "+ list.size());

                                String fileNameL = sourceFile.getName().replace(".pdf", "");
                                for (PDPage page : list) {
                                    BufferedImage image = page.convertToImage();
                                    File outputfile = new File(
                                            destinationDir + File.separator + no + fileNameL + ".jpg");
                                    n = new File(destinationDir);
                                    if (!n.exists()) {
                                        n.mkdirs();
                                    }
                                    // logger.info("Image Created -> "+ outputfile.getName());
                                    ImageIO.write(image, "png", outputfile);

                                    String path1 = jbossHome + File.separator + "standalone" + File.separator
                                            + "File_Upload" + File.separator + "Call" + File.separator
                                            + callMster.getId() + File.separator + no + fileNameL + ".jpg";
                                    n = new File(
                                            jbossHome + File.separator + "standalone" + File.separator + "File_Upload"
                                            + File.separator + "Call" + File.separator + callMster.getId());
                                    if (!n.exists()) {
                                        n.mkdirs();
                                    }
                                    File ff2 = new File(path1);
                                    ImageIO.write(image, "png", ff2);
                                }
                                document.close();

                                if (null != fileHandleDto.getFile().getOriginalFilename()) {
                                    List<CallDtl> cdtlist = callDtlService.findCallDtlByCallMasterId(callMster.getId());
                                    if (!cdtlist.isEmpty()) {
                                        EmployeeMst em = employeeMstService
                                                .findEmployeeMstById(cdtlist.get(0).getHandeledById().getId());
                                        try {
                                            // logger.info("Before Send Natinal ID===" + no +
                                            // upFile.getFileName() + " SEND TO===" + em.getLoginId());
                                            SocketMessage.send(callScheduler.getAdminSocket(), em.getLoginId(),
                                                    "idCardSend#" + no + fileNameL + ".jpg");
                                            flag = true;
                                        } catch (Exception ex) {
                                            logger.error(ex);
                                        }
                                    }
                                }
                                // logger.info("Converted Images are saved at -> "+
                                // destinationFile.getAbsolutePath());
                            } else {
                                System.err.println(sourceFile.getName() + " File not exists");
                            }
                        }

                        fos2 = new FileOutputStream(path);
                        outputStream2 = new BufferedOutputStream(fos2);
                        outputStream2.write(fileContentInByte);
                        outputStream2.close();

                        if (flag) {
                            agentLoginResponse.setSuccMsg("Identity Card  Sent SuccessFully");
                        } else {
                            agentLoginResponse.setSuccMsg(" Identity Card  Not Sent SuccessFully");
                        }
                        return agentLoginResponse;

                    } catch (IOException ex) {
                        CCMSRestException ccmsRestException = new CCMSRestException();
                        ccmsRestException.setErrorCode("418");
                        ccmsRestException.setErrorMessage("File not sent successfully!");
                        throw ccmsRestException;

                    } catch (Exception e) {
                        CCMSRestException ccmsRestException = new CCMSRestException();
                        ccmsRestException.setErrorCode("418");
                        ccmsRestException.setErrorMessage("File not sent successfully!");
                        throw ccmsRestException;

                    }
                } else {
                    CCMSRestException ccmsRestException = new CCMSRestException();
                    ccmsRestException.setErrorCode("418");
                    ccmsRestException.setErrorMessage("Please Provide valid information!");
                    throw ccmsRestException;
                }
            } else {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Please Provide valid information!");
                throw ccmsRestException;
            }
        } else {
            CCMSRestException ccmsRestException = new CCMSRestException();
            ccmsRestException.setErrorCode("418");
            ccmsRestException.setErrorMessage("Please Provide valid information!");
            throw ccmsRestException;
        }

    }

    @Override
    public AgentLoginResponse getCustomerAddressProofUploadResponse(FileHandleDto fileHandleDto,
            HttpServletRequest request) throws Exception {

        BufferedOutputStream outputStream;
        FileOutputStream fos;
        BufferedOutputStream outputStream2;
        FileOutputStream fos2;
        boolean flag = false;
        boolean pdfflag = false;
        CallMst callMster = null;
        byte[] fileContentInByte;
        AgentLoginResponse agentLoginResponse = new AgentLoginResponse();

        if (fileHandleDto != null) {
            if (fileHandleDto.getCallId() != null) {
                callMster = callMstService.findCallMstById(fileHandleDto.getCallId());
            } else {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Please Provide valid information!");
                throw ccmsRestException;
            }

            if (fileHandleDto.getFile() == null || fileHandleDto.getFile().isEmpty()) {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Please select file!");
                throw ccmsRestException;

            } else if (fileHandleDto.getFile().getOriginalFilename() == null
                    || fileHandleDto.getFile().getOriginalFilename().equalsIgnoreCase("")
                    || fileHandleDto.getFile().getOriginalFilename().isEmpty()) {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("File Type Not Supported. Please select correct file.");
                throw ccmsRestException;
            } else if (callMster != null && callMster.getId() > 0) {

                String fn = fileHandleDto.getFile().getOriginalFilename();
                String parts[] = fn.split(Pattern.quote("."));
                String ext = parts[1];

                if (ext.equalsIgnoreCase("jpeg") || ext.equalsIgnoreCase("exif") || ext.equalsIgnoreCase("tiff")
                        || ext.equalsIgnoreCase("rif") || ext.equalsIgnoreCase("bmp") || ext.equalsIgnoreCase("png")
                        || ext.equalsIgnoreCase("webp") || ext.equalsIgnoreCase("jpg")) {

                    if (fileHandleDto.getFile().getSize() > 2097152) {
                        CCMSRestException ccmsRestException = new CCMSRestException();
                        ccmsRestException.setErrorCode("418");
                        ccmsRestException.setErrorMessage("Please upload image of size less than 2MB.");
                        throw ccmsRestException;
                    }
                } else if (ext.equalsIgnoreCase("pdf")) {

                    if (fileHandleDto.getFile().getSize() > 2097152) {
                        CCMSRestException ccmsRestException = new CCMSRestException();
                        ccmsRestException.setErrorCode("418");
                        ccmsRestException.setErrorMessage("Please upload image of size less than 2MB.");
                        throw ccmsRestException;
                    }
                    pdfflag = true;
                } else {
                    CCMSRestException ccmsRestException = new CCMSRestException();
                    ccmsRestException.setErrorCode("418");
                    ccmsRestException.setErrorMessage("File extension not supported");
                    throw ccmsRestException;
                }

                fileContentInByte = fileHandleDto.getFile().getBytes();
                if (null != callMster.getCustId() && !"".equals(callMster.getCustId())) {
                    ServletContext ctx = this.context;
                    String deploymentDirectoryPath = ctx.getRealPath("/");
                    String jbossHome = System.getenv("JBOSS_HOME");
                    Random rand = new Random();
                    int no = rand.nextInt(1000) + 1;

                    String tempFilePath = File.separator + "File_Upload" + File.separator + "Call" + File.separator
                            + callMster.getId() + File.separator + no + fileHandleDto.getFile().getOriginalFilename();

                    String path = jbossHome + File.separator + "standalone" + tempFilePath;
                    File n = new File(jbossHome + File.separator + "standalone" + File.separator + "File_Upload"
                            + File.separator + "Call" + File.separator + callMster.getId());
                    if (!n.exists()) {
                        n.mkdirs();
                    }
                    try {
                        n = new File(deploymentDirectoryPath + "/resources" + File.separator + "File_Upload"
                                + File.separator + "Call" + File.separator + callMster.getId());
                        if (!n.exists()) {
                            n.mkdirs();
                        }
                        fos = new FileOutputStream(deploymentDirectoryPath + "/resources" + tempFilePath);
                        outputStream = new BufferedOutputStream(fos);
                        outputStream.write(fileContentInByte);
                        outputStream.close();
                        if (!pdfflag) {
                            if (null != fileHandleDto.getFile().getOriginalFilename()) {
                                List<CallDtl> cdtlist = callDtlService.findCallDtlByCallMasterId(callMster.getId());
                                if (!cdtlist.isEmpty()) {
                                    EmployeeMst em = employeeMstService
                                            .findEmployeeMstById(cdtlist.get(0).getHandeledById().getId());
                                    try {
                                        SocketMessage.send(callScheduler.getAdminSocket(), em.getLoginId(),
                                                "addProofSend#" + no + fileHandleDto.getFile().getOriginalFilename());
                                        flag = true;
                                    } catch (Exception ex) {
                                        logger.error(ex);
                                    }
                                }
                            }
                        } else {
                            String sourceDir = deploymentDirectoryPath + "/resources" + tempFilePath; // Pdf files are
                            // read from
                            // this folder
                            String destinationDir = deploymentDirectoryPath + "/resources" + File.separator
                                    + "File_Upload" + File.separator + "Call" + File.separator + callMster.getId(); // converted
                            // images
                            // from
                            // pdf
                            // document
                            // are
                            // saved
                            // here

                            File sourceFile = new File(sourceDir);
                            File destinationFile = new File(destinationDir);
                            if (!destinationFile.exists()) {
                                destinationFile.mkdir();
                                // logger.info("Folder Created -> "+ destinationFile.getAbsolutePath());
                            }
                            if (sourceFile.exists()) {
                                // logger.info("Images copied to Folder: "+ destinationFile.getName());
                                PDDocument document = PDDocument.load(sourceDir);
                                List<PDPage> list = document.getDocumentCatalog().getAllPages();
                                // logger.info("Total files to be converted -> "+ list.size());

                                String fileNameL = sourceFile.getName().replace(".pdf", "");
                                for (PDPage page : list) {
                                    BufferedImage image = page.convertToImage();
                                    File outputfile = new File(
                                            destinationDir + File.separator + no + fileNameL + ".jpg");
                                    n = new File(destinationDir);
                                    if (!n.exists()) {
                                        n.mkdirs();
                                    }
                                    // logger.info("Image Created -> "+ outputfile.getName());
                                    ImageIO.write(image, "png", outputfile);

                                    String path1 = jbossHome + File.separator + "standalone" + File.separator
                                            + "File_Upload" + File.separator + "Call" + File.separator
                                            + callMster.getId() + File.separator + no + fileNameL + ".jpg";
                                    n = new File(
                                            jbossHome + File.separator + "standalone" + File.separator + "File_Upload"
                                            + File.separator + "Call" + File.separator + callMster.getId());
                                    if (!n.exists()) {
                                        n.mkdirs();
                                    }
                                    File ff2 = new File(path1);
                                    ImageIO.write(image, "png", ff2);
                                }
                                document.close();

                                if (null != fileHandleDto.getFile().getOriginalFilename()) {
                                    List<CallDtl> cdtlist = callDtlService.findCallDtlByCallMasterId(callMster.getId());
                                    if (!cdtlist.isEmpty()) {
                                        EmployeeMst em = employeeMstService
                                                .findEmployeeMstById(cdtlist.get(0).getHandeledById().getId());
                                        try {
                                            SocketMessage.send(callScheduler.getAdminSocket(), em.getLoginId(),
                                                    "addProofSend#" + no + fileNameL + ".jpg");
                                            flag = true;
                                        } catch (Exception ex) {
                                            logger.error(ex);
                                        }
                                    }
                                }
                                // logger.info("Converted Images are saved at -> "+
                                // destinationFile.getAbsolutePath());
                            } else {
                                System.err.println(sourceFile.getName() + " File not exists");
                            }
                        }

                        fos2 = new FileOutputStream(path);
                        outputStream2 = new BufferedOutputStream(fos2);
                        outputStream2.write(fileContentInByte);
                        outputStream2.close();

                        if (flag) {
                            agentLoginResponse.setSuccMsg("Address Proof sent SuccessFully");
                        } else {
                            agentLoginResponse.setSuccMsg("Address Proof Not Sent SuccessFully");
                        }
                        return agentLoginResponse;

                    } catch (IOException ex) {
                        CCMSRestException ccmsRestException = new CCMSRestException();
                        ccmsRestException.setErrorCode("418");
                        ccmsRestException.setErrorMessage("File not sent successfully!");
                        throw ccmsRestException;

                    } catch (Exception e) {
                        CCMSRestException ccmsRestException = new CCMSRestException();
                        ccmsRestException.setErrorCode("418");
                        ccmsRestException.setErrorMessage("File not sent successfully!");
                        throw ccmsRestException;

                    }
                } else {
                    CCMSRestException ccmsRestException = new CCMSRestException();
                    ccmsRestException.setErrorCode("418");
                    ccmsRestException.setErrorMessage("Please Provide valid information!");
                    throw ccmsRestException;
                }
            } else {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Please Provide valid information!");
                throw ccmsRestException;
            }
        } else {
            CCMSRestException ccmsRestException = new CCMSRestException();
            ccmsRestException.setErrorCode("418");
            ccmsRestException.setErrorMessage("Please Provide valid information!");
            throw ccmsRestException;
        }

    }

    @Override
    public AgentLoginResponse getCustomerProfileImageUploadResponse(FileHandleDto fileHandleDto,
            HttpServletRequest request) throws Exception {
        ServletContext ctx = this.context;
        String deploymentDirectoryPath = ctx.getRealPath("/");
        AgentLoginResponse agentLoginResponse = new AgentLoginResponse();
        boolean flag = false;
        try {
            CallMst callMster = callMstService.findCallMstById(fileHandleDto.getCallId());
            if (null != callMster) {
                Random rand = new Random();
                int no = rand.nextInt(1000) + 1;
                BufferedImage image = ImageIO.read(fileHandleDto.getFile().getInputStream());

                // write the image to a file
                String imagePath = no + "snapshot.jpg";

                String tempFilePath = File.separator + "File_Upload" + File.separator + "Call" + File.separator
                        + callMster.getId() + File.separator + imagePath;

                File ff = new File(deploymentDirectoryPath + "/resources" + tempFilePath);
                File n = new File(deploymentDirectoryPath + "/resources" + File.separator + "File_Upload"
                        + File.separator + "Call" + File.separator + callMster.getId());
                if (!n.exists()) {
                    n.mkdirs();
                }
                // File outputfile = new File("E:\\Learning\\image3.jpg");
                ImageIO.write(image, "png", ff);
                request.getRequestURL().substring(0, request.getRequestURL().indexOf("/", 9));

                if (null != callMster.getCustId() && !"".equals(callMster.getCustId())) {
                    List<CallDtl> cdtlist = callDtlService.findCallDtlByCallMasterId(callMster.getId());
                    if (!cdtlist.isEmpty()) {
                        EmployeeMst em = employeeMstService
                                .findEmployeeMstById(cdtlist.get(0).getHandeledById().getId());
                        try {
                            // logger.info("Before Send Natinal ID===" + no + upFile.getFileName() +
                            // " SEND TO===" + em.getLoginId());
                            SocketMessage.send(callScheduler.getAdminSocket(), em.getLoginId(),
                                    "selfImageSend#" + imagePath);
                            flag = true;
                        } catch (Exception ex) {
                            logger.error(ex);
                        }
                    }

                }

                String jbossHome = System.getenv("JBOSS_HOME");

                String path = jbossHome + File.separator + "standalone" + tempFilePath;
                n = new File(jbossHome + File.separator + "standalone" + File.separator + "File_Upload" + File.separator
                        + "Call" + File.separator + callMster.getId());
                if (!n.exists()) {
                    n.mkdirs();
                }
                File ff2 = new File(path);
                ImageIO.write(image, "png", ff2);

            }

            if (flag) {
                agentLoginResponse.setSuccMsg("Snap Image sent SuccessFully");
            } else {
                agentLoginResponse.setSuccMsg("Snap Image Not Sent SuccessFully");
            }
        } catch (IOException ex) {
            logger.info("Exception found" + ex);
        }
        return agentLoginResponse;
    }

    @Override
    public AgentLoginResponse getServerDownTimeResponse() throws Exception {
        logger.info("downtime");
        Calendar calendar = Calendar.getInstance();
        AgentLoginResponse agentLoginResponse = new AgentLoginResponse();
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
            CCMSRestException ccmsRestException = new CCMSRestException();
            ccmsRestException.setErrorCode("418");
            ccmsRestException.setErrorMessage(
                    "Currently System is in downtime from " + this.downTimeStart + " to " + this.downTimeEnd);
            throw ccmsRestException;
        } else if (inservicedownTime) {
            CCMSRestException ccmsRestException = new CCMSRestException();
            ccmsRestException.setErrorCode("418");
            if ((this.getServiceTimeStart() != null && !this.getServiceTimeStart().trim().equals(""))
                    && (this.getServiceTimeEnd() != null && !this.getServiceTimeEnd().trim().equals(""))) {
                ccmsRestException.setErrorMessage(
                        "We are unavailable to attend to you via this channel at this time. We are only available on "
                        + weekDay + " between " + this.getServiceTimeStart() + " to "
                        + this.getServiceTimeEnd());
            } else {
                ccmsRestException.setErrorMessage(
                        "We are unavailable to attend to you via this channel at this time on this week day ");
            }
            throw ccmsRestException;
        } else {
            agentLoginResponse.setDownTimeCheck("false");
        }

        return agentLoginResponse;
    }

    private boolean whetherInDownTime() {
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

                if (now.isAfter(LocalTime.parse(endHour + ":" + endMin))
                        || now.isBefore(LocalTime.parse(startHour + ":" + startMin))) {
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

    @Override
    public AgentLoginResponse getForwardResponse(String callInfo) throws Exception {
        logger.info("onForwardEmployeeCall----------" + callInfo);
        EmployeeMst currEmp;

        String callId = null;
        String agentId = null;
        CallMst callMstLocal;
        String serviceId = null;
        String forwardagentId = null;
        AgentLoginResponse agentLoginResponse = new AgentLoginResponse();
        if (callInfo != null && callInfo.length() > 0) {
            JSONObject callInformation = new JSONObject(callInfo);
            if (callInformation.has("callId") && callInformation.has("empId") && (callInformation.has("serviceId") || callInformation.has("forwardagentId"))) {
                callId = callInformation.getString("callId");
                agentId = callInformation.getString("empId");
                serviceId = callInformation.getString("serviceId");
                forwardagentId = callInformation.getString("forwardagentId");
                logger.info("with callid=" + callId + "    Called" + "agentId:" + agentId + "serviceId:" + serviceId + "forwardagentId:" + forwardagentId);
            } else {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Please Provide valid information!");
                throw ccmsRestException;
            }

        } else {
            CCMSRestException ccmsRestException = new CCMSRestException();
            ccmsRestException.setErrorCode("418");
            ccmsRestException.setErrorMessage("Please Provide valid information!");
            throw ccmsRestException;
        }

        if (agentId != null && agentId.length() > 0 && callId != null && callId.length() > 0) {
            callMstLocal = callMstService.findCallMstById(Long.parseLong(callId));
            currEmp = employeeMstService.findEmployeeMstById(Long.parseLong(agentId));
            logger.info("callMstLocal:" + callMstLocal + "currEmp:" + currEmp);
            if (callMstLocal != null && callMstLocal.getId() > 0 && currEmp != null && currEmp.getId() > 0) {
                if (serviceId != null && !serviceId.trim().equals("")) {
                    Timestamp dt_time = CustomConvert.javaDateToTimeStamp(new Date());
                    CallMst callmst = callMstLocal;
                    CategoryMst categoryMst = categoryMstService.findCategoryMstById(callmst.getCategoryId());
                    ServiceMst serviceMst = serviceMstService.findAllServiceMstById(Long.parseLong(serviceId));
                    LanguageMst languageMst = langMstService.findAllLanguageMstById(callmst.getLanguageId());
                    Set set = agentFindingService.findAgents(categoryMst.getCatgCd(), serviceMst.getServiceCd(), languageMst.getLanguageCd());

                    List<Object> L_E_Mst = new ArrayList<>(set);
                    EmployeeCallStatus empCallStatus = null;
                    if (L_E_Mst.size() > 0) {
                        EmployeeMst em = (EmployeeMst) L_E_Mst.get(0);

                        logger.info("serviceId Fwd Agent:" + em);
                        List<EmployeeCallStatus> empClStatusList = employeeCallStatusService.findEmployeeCallStatusByEmpId(em);

                        if (!empClStatusList.isEmpty()) {
                            for (EmployeeCallStatus empstatus : empClStatusList) {
                                empCallStatus = empstatus;
                            }
                            if (null != empCallStatus) {
                                empCallStatus.setCallCount(0);
                                empCallStatus.setStatus(false);
                                employeeCallStatusService.saveEmployeeCallStatus(empCallStatus);
                            }
                        } else if (null != empCallStatus) {
                            empCallStatus.setEmpId(em);
                            empCallStatus.setCallCount(0);
                            empCallStatus.setStatus(false);
                            employeeCallStatusService.saveEmployeeCallStatus(empCallStatus);
                        }

                        EmployeeMst CurrEmp = currEmp;
                        List<TenancyEmployeeMap> tenancyEmployeeMaplist = tenancyEmployeeMapService.findVidyoTenantUrlByEmpId(CurrEmp.getId());
                        List<Long> unsortList = new ArrayList<>();
                        for (TenancyEmployeeMap tenancyEmployeeMaplist1 : tenancyEmployeeMaplist) {
                            unsortList.add(tenancyEmployeeMaplist1.getId());
                        }
                        Collections.sort(unsortList);
                        TenancyEmployeeMap tenancyEmployeeMap = tenancyEmployeeMapService.findById(unsortList.get(unsortList.size() - 1));
                        String roomLink = tenancyEmployeeMap.getRoomLink();
                        String roomName = tenancyEmployeeMap.getRoomName();
                        CallMst callMst = callMstService.findCallMstById(callmst.getId());

                        //Call_id = callMst.getId();
                        ForwardedCall forwardedCall = new ForwardedCall();
                        forwardedCall.setEmpId(em);
                        forwardedCall.setRoomLink(roomLink);
                        forwardedCall.setRoomName(roomName);
                        forwardedCall.setEntityId(tenancyEmployeeMap.getEntityId());
                        forwardedCall.setCallId(callMst);
                        forwardedCall.setForwardedDatetime(dt_time);
                        forwardedCall.setActiveFlg(true);
                        forwardedCall.setDeleteFlg(false);
                        forwardedCall.setPrevEmpId(CurrEmp.getId());
                        forwardedCall.setSelectedServiceId(serviceMst.getId());

                        ForwardedCall currForwardedCall = forwardedCallService.findForwardedCallByCallIdAndEmpId(callMst);
                        if (currForwardedCall != null) {
                            forwardedCall.setFromServiceId(currForwardedCall.getSelectedServiceId());
                        } else {

                            forwardedCall.setFromServiceId(callMst.getServiceId());
                        }
                        forwardedCallService.saveForwardedCalls(forwardedCall, CurrEmp);
                        try {
                            /**
                             * Modified By VD on 04-11-2020
                             */
                            logger.info("em.getLoginId():" + em.getLoginId() + "forwardcallnotification#" + callMstLocal.getId() + "#" + CurrEmp.getFirstName() + " " + CurrEmp.getLastName() + "#" + roomLink + "#" + callMstLocal.getCallOption());
                            SocketMessage.send(callScheduler.getAdminSocket(), em.getLoginId(), "forwardcallnotification#" + callMstLocal.getId() + "#" + CurrEmp.getFirstName() + " " + CurrEmp.getLastName() + "#" + roomLink + "#" + callMstLocal.getCallOption());

                        } catch (Exception ex) {
                            logger.error(ex.getMessage());
                        }
                        agentLoginResponse.setValidateMsg("Waiting for Forwarding agent to take the call");

                    } else {
                        agentLoginResponse.setValidateMsg("No agent found to forward");

                    }
                } else if (forwardagentId != null && !forwardagentId.trim().equals("")) {
                    Timestamp dt_time = CustomConvert.javaDateToTimeStamp(new Date());
                    CallMst callmst = callMstLocal;
                    EmployeeCallStatus empCallStatus = null;
                    EmployeeMst em = employeeMstService.findEmployeeByUserId(forwardagentId);

                    logger.info("forwardagentId Fwd Agent:" + em);
                    List<EmployeeCallStatus> empClStatusList = employeeCallStatusService.findEmployeeCallStatusByEmpId(em);

                    if (!empClStatusList.isEmpty()) {
                        for (EmployeeCallStatus empstatus : empClStatusList) {
                            empCallStatus = empstatus;
                        }
                        if (null != empCallStatus) {
                            empCallStatus.setCallCount(0);
                            empCallStatus.setStatus(false);
                            employeeCallStatusService.saveEmployeeCallStatus(empCallStatus);
                        }
                    } else if (null != empCallStatus) {
                        empCallStatus.setEmpId(em);
                        empCallStatus.setCallCount(0);
                        empCallStatus.setStatus(false);
                        employeeCallStatusService.saveEmployeeCallStatus(empCallStatus);
                    }

                    EmployeeMst CurrEmp = currEmp;
                    List<TenancyEmployeeMap> tenancyEmployeeMaplist = tenancyEmployeeMapService.findVidyoTenantUrlByEmpId(CurrEmp.getId());
                    List<Long> unsortList = new ArrayList<>();
                    for (TenancyEmployeeMap tenancyEmployeeMaplist1 : tenancyEmployeeMaplist) {
                        unsortList.add(tenancyEmployeeMaplist1.getId());
                    }
                    Collections.sort(unsortList);
                    TenancyEmployeeMap tenancyEmployeeMap = tenancyEmployeeMapService.findById(unsortList.get(unsortList.size() - 1));
                    String roomLink = tenancyEmployeeMap.getRoomLink();
                    String roomName = tenancyEmployeeMap.getRoomName();
                    CallMst callMst = callMstService.findCallMstById(callmst.getId());

                    //Call_id = callMst.getId();
                    ForwardedCall forwardedCall = new ForwardedCall();
                    forwardedCall.setEmpId(em);
                    forwardedCall.setRoomLink(roomLink);
                    forwardedCall.setRoomName(roomName);
                    forwardedCall.setEntityId(tenancyEmployeeMap.getEntityId());
                    forwardedCall.setCallId(callMst);
                    forwardedCall.setForwardedDatetime(dt_time);
                    forwardedCall.setActiveFlg(true);
                    forwardedCall.setDeleteFlg(false);
                    forwardedCall.setPrevEmpId(CurrEmp.getId());
                    forwardedCall.setSelectedServiceId(callMst.getServiceId());

                    ForwardedCall currForwardedCall = forwardedCallService.findForwardedCallByCallIdAndEmpId(callMst);
                    if (currForwardedCall != null) {
                        forwardedCall.setFromServiceId(currForwardedCall.getSelectedServiceId());
                    } else {

                        forwardedCall.setFromServiceId(callMst.getServiceId());
                    }
                    forwardedCallService.saveForwardedCalls(forwardedCall, CurrEmp);
                    try {
                        /**
                         * Modified By VD on 04-11-2020
                         */
                        logger.info("em.getLoginId():" + em.getLoginId() + "forwardcallnotification#" + callMstLocal.getId() + "#" + CurrEmp.getFirstName() + " " + CurrEmp.getLastName() + "#" + roomLink + "#" + callMstLocal.getCallOption());
                        SocketMessage.send(callScheduler.getAdminSocket(), em.getLoginId(), "forwardcallnotification#" + callMstLocal.getId() + "#" + CurrEmp.getFirstName() + " " + CurrEmp.getLastName() + "#" + roomLink + "#" + callMstLocal.getCallOption());

                    } catch (Exception ex) {
                        logger.error(ex.getMessage());
                    }
                    agentLoginResponse.setValidateMsg("Waiting for Forwarding agent to take the call");
                }
            }
        }
        logger.info("agentLoginResponse:" + agentLoginResponse.getValidateMsg());
        return agentLoginResponse;

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

    public String getDownMessage() {
        return downMessage;
    }

    public void setDownMessage(String downMessage) {
        this.downMessage = downMessage;
    }

    @Override
    public IncomingCallResponse getCustomerToAvailableRMCallResponse(String credential) throws Exception {
        logger.info("INSIDE WEBSERVICE calltoavailableRM");

        CustomerMst cm;
        boolean newCall = true;

        IncomingCallResponse incomingCallResPonse = new IncomingCallResponse();
        CallMst callMstForRepeatcall = null;
        String location;
        String latitude;
        String longitude;

        /**
         * @Purpose To check whether method parameter credential is null or not
         */
        System.out.println("llllllllllllll"+credential);
        if (credential != null && credential.length() > 0) {
            System.out.println("............."+credential.length());
            JSONObject cred = new JSONObject(credential);
            String loginId = cred.getString("loginId");
            // String loginPassword = cred.getString("password");
            String category = cred.getString("category");
            String language = cred.getString("language");
            String service = cred.getString("service");
            String callOption = cred.getString("callOption");
            location = cred.getString("location");
            latitude = cred.getString("latitude");
            longitude = cred.getString("longitude");
            System.out.println("............."+loginId);

            if (loginId == null || loginId.isEmpty() || "".equals(loginId)) {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Please Provide loginId!");
                throw ccmsRestException;

            }

            if (category == null
                    || "".equals(language) || category.isEmpty() || "".equals(category) || language == null || language.isEmpty() || service == null || service.isEmpty() || "".equals(service)) {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Please Provide valid information!");
                throw ccmsRestException;
            }
           
            cm = customerMstService.findCustomerMstByCustId(loginId);
             System.out.println("nnnnnnnnnnnnnnnnnnn"+cm);
            if (cm != null) {

                incomingCallResPonse.setCustFname(cm.getFirstName());
                incomingCallResPonse.setCustLname(cm.getLastName());
                incomingCallResPonse.setSocketHostPublic(Constants.socketHostPublic);
                List<CallMst> callMstForRepeatcallList = callMstService.findAllNotRecevedCallsByCust(cm.getCustId());
                if (!callMstForRepeatcallList.isEmpty()) {

                    callMstForRepeatcall = callMstForRepeatcallList.get(0);
                    newCall = false;
                }

                /**
                 * the following portion need to be changed in CustomerMst
                 * Relation and Entity as well
                 */
                CategoryMst cat = categoryMstService.findCategoryByCategoryName(category);
                Long selectedCategoryName = cat.getId();
                LanguageMst langMst = langMstService.findLanguageMstByLanguageName(language);
                Long selectedLanguageName = langMst.getId();
                ServiceMst serviceMst = serviceMstService.findServiceByServiceName(service);
                Long selectedServiceName = serviceMst.getId();

                synchronized (this) {

                    if (newCall) {

                        List<CallMst> listOpenCalls = callMstService.findAllOnGoingCallsByCust(cm.getCustId());

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
                        local_callMst.setCallLocation(location);
                        local_callMst.setCallLatitude(latitude);
                        local_callMst.setCallLongitude(longitude);
                        local_callMst.setCustomerId(cm);
                        local_callMst.setCustId(cm.getCustId());
                        local_callMst.setCallOption(callOption);

                        local_callMst.setServiceId(serviceMst.getId());
                        local_callMst.setServiceId(selectedServiceName);
                        local_callMst.setCategoryId(selectedCategoryName);
                        local_callMst.setLanguageId(selectedLanguageName);
                        local_callMst.setCallMedium("");
                        local_callMst.setActiveFlg(true);
                        local_callMst.setDeleteFlg(false);
                        local_callMst.setStartTime(CustomConvert.javaDateToTimeStamp(new Date()));
                        local_callMst.setCustomerRequestTime(CustomConvert.javaDateToTimeStamp(new Date()));

                        local_callMst = callMstService.saveCallMst(local_callMst);
                        callMstForRepeatcall = callMstService.findCallMstById(local_callMst.getId());
                    } else if (callMstForRepeatcall != null) {
                        callMstForRepeatcall.setServiceId(selectedServiceName);
                        callMstForRepeatcall.setCategoryId(selectedCategoryName);
                        callMstForRepeatcall.setLanguageId(selectedLanguageName);
                        callMstForRepeatcall.setStartTime(CustomConvert.javaDateToTimeStamp(new Date()));
                        callMstForRepeatcall = callMstService.saveCallMst(callMstForRepeatcall);
                    }
                    Long callId;
                    CallMst callMaster;
                    callId = callMstForRepeatcall.getId();
                    callMaster = callMstService.findNonDeletedCallMstById(callId);
                    incomingCallResPonse.setCallId(callId + "");
                    incomingCallResPonse.setStatus("Not Initiated");

                    List<CustomerRmMap> mappedRMList = customerRMMapService.getRMMappedWithCustomer(cm.getId());
                    CustomerRmMap customerRmMap = null;
                    if (!mappedRMList.isEmpty()) {
                        customerRmMap = mappedRMList.get(0);
                    }
                    EmployeeMst emp = employeeMstService.findEmployeeMstById(customerRmMap.getRmId().getId());
                    String LoginId = emp.getLoginId();

                    if (!employeeCallStatusService.findFreeOnlineRMs(LoginId).isEmpty()) {

                        EmployeeMst em = employeeMstService.findEmployeeByUserId(LoginId);

                        if (em != null) {

                            EmployeeCallStatus empCallStatus = new EmployeeCallStatus();
                            List<EmployeeCallStatus> empClStatusList = employeeCallStatusService
                                    .findEmployeeCallStatusByEmpId(em);

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
                            String roomName = tenancyEmployeeMap.getRoomName();
                            String entityId = tenancyEmployeeMap.getEntityId();

                            incomingCallResPonse.setUrl(roomLink);
                            incomingCallResPonse.setEntityId(entityId);
                            incomingCallResPonse.setRoomName(roomName);
                            incomingCallResPonse.setAgentId(em.getId().toString());
                            incomingCallResPonse.setStatus("Initiated" + "||" + em.getFirstName() + " " + em.getLastName());
                            incomingCallResPonse.setCustomerId(cm.getCustId());
                            incomingCallResPonse.setSocketHostPublic(cm.getCustId());

                            Set<String> custIdSet = new TreeSet<>();
                            custIdSet.add(cm.getCustId());
                            CallScheduler.customerCallDetailsMap.put(callId.toString(), custIdSet);

                            CallEmployeeMap l_CallEmpMap = new CallEmployeeMap();
                            l_CallEmpMap.setCallId(callMaster.getId());
                            l_CallEmpMap.setEmployeeId(em.getId());
                            l_CallEmpMap.setCallType("Normal");
                            l_CallEmpMap.setCustId(cm.getCustId());
                            callMaster.setRoutingCallTime(CustomConvert.javaDateToTimeStamp(new Date()));
                            callMaster.setRoomName(roomLink);
                            callMaster = callMstService.saveCallMst(callMaster);

                            CallRecords callRecords = callRecordsService.findCallRecordsByCallId(callMaster.getId(),
                                    (long) 0);
                            if (null == callRecords) {
                                callRecords = new CallRecords();
                            }

                            callRecords.setCallId(callMaster);
                            callRecords.setEmpId(em);
                            callRecords.setRecorderId("");
                            callRecords.setRoomName(roomName);
                            callRecords.setCustomerId(incomingCallResPonse.getCustomerId());

                            callRecordsService.saveCallRecord(callRecords);
                            try {
                                SocketMessage.send(callScheduler.getAdminSocket(), em.getLoginId(),
                                        "incomingCall#" + callMaster.getId() + "#" + cm.getCustId() + "#" + callMaster.getCallOption());
                            } catch (Exception ex) {
                                logger.error(ex);
                            }

                            CallScheduler.listCallEmp.add(l_CallEmpMap);

                            CustomerDeviceDtl customerDeviceDtl = new CustomerDeviceDtl();
                            customerDeviceDtl.setLogout(0);

                            customerDeviceDtl.setDeviceBrand(null);
                            customerDeviceDtl.setDeviceId(null);
                            customerDeviceDtl.setDeviceIp(null);
                            customerDeviceDtl.setImeiNo(null);

                            customerDeviceDtl.setMobileOsType("android");
                            customerDeviceDtl.setStaticIp(null);
                            if (cm.getAccountNo() != null && cm.getAccountNo().trim().length() > 0) {
                                customerDeviceDtl.setLoginInfo(2);
                            } else {
                                customerDeviceDtl.setLoginInfo(1);
                            }

                            customerDeviceDtl.setCustomerId(cm);
                            customerDeviceDtl.setCustomerOtp(null);
                            customerDeviceDtl.setTimezone("Asia/Kolkata");
                            customerDeviceDtlService.saveCustomerDeviceDtl(customerDeviceDtl, null);

                        } else {
                            logger.info("No RM Found..........");
                            callMaster = callMstService
                                    .findNonDeletedCallMstById(Long.parseLong(incomingCallResPonse.getCallId()));
                            callMaster.setCallStatus("No Agent Found");
                            callMaster.setStartTime(CustomConvert.javaDateToTimeStamp(new Date()));
                            callMstService.saveCallMst(callMaster);

                        }

                    } else {
                        logger.info("All RM's are busy or no agent is available..........");
                        if (incomingCallResPonse.getCallId() != null
                                && incomingCallResPonse.getCallId().trim().length() > 0) {
                            callId = Long.parseLong(incomingCallResPonse.getCallId());
                            callMaster.setCallStatus("No Agent Found");
                            callMaster.setStartTime(CustomConvert.javaDateToTimeStamp(new Date()));
                            // callMaster.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                            callMstService.saveCallMst(callMaster);
                            incomingCallResPonse.setCallId("" + callId);
                        }

                    }

                    return incomingCallResPonse;

                }

            } else {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Please Provide valid credential!");
                throw ccmsRestException;
            }
        } else {
            CCMSRestException ccmsRestException = new CCMSRestException();
            ccmsRestException.setErrorCode("418");
            ccmsRestException.setErrorMessage("Please Provide valid credential!");
            throw ccmsRestException;
        }

    }

    @Override
    public IncomingCallResponse getCustomerToAvailableSRMCallResponse(String credential) throws Exception {
        logger.info("INSIDE WEBSERVICE calltoavailableSRM");

        CustomerMst cm;
        boolean newCall = true;
        String location;
        String latitude;
        String longitude;

        IncomingCallResponse incomingCallResPonse = new IncomingCallResponse();
        CallMst callMstForRepeatcall = null;

        /**
         * @Purpose To check whether method parameter credential is null or not
         */
        if (credential != null && credential.length() > 0) {
            JSONObject cred = new JSONObject(credential);
            String loginId = cred.getString("loginId");
            // String loginPassword = cred.getString("password");
            String category = cred.getString("category");
            String language = cred.getString("language");
            String service = cred.getString("service");
            location = cred.getString("location");
            latitude = cred.getString("latitude");
            longitude = cred.getString("longitude");

            if (loginId == null || loginId.isEmpty() || "".equals(loginId)) {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Please Provide loginId!");
                throw ccmsRestException;

            }

            /*
             * if (loginPassword == null || loginPassword.isEmpty() || loginPassword == "")
             * { CCMSRestException ccmsRestException = new CCMSRestException();
             * ccmsRestException.setErrorCode("418");
             * ccmsRestException.setErrorMessage("Please Provide password!"); throw
             * ccmsRestException; }
             */
            if (category == null
                    || "".equals(language) || category.isEmpty() || "".equals(category) || language == null || language.isEmpty() || service == null || service.isEmpty() || "".equals(service)) {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Please Provide valid information!");
                throw ccmsRestException;
            }

            cm = customerMstService.findCustomerMstByCustId(loginId);
            if (cm != null) {

                incomingCallResPonse.setCustFname(cm.getFirstName());
                incomingCallResPonse.setCustLname(cm.getLastName());
                incomingCallResPonse.setSocketHostPublic(Constants.socketHostPublic);
                List<CallMst> callMstForRepeatcallList = callMstService.findAllNotRecevedCallsByCust(cm.getCustId());
                if (!callMstForRepeatcallList.isEmpty()) {

                    callMstForRepeatcall = callMstForRepeatcallList.get(0);
                    newCall = false;
                }

                /**
                 * the following portion need to be changed in CustomerMst
                 * Relation and Entity as well
                 */
                CategoryMst cat = categoryMstService.findCategoryByCategoryName(category);
                Long selectedCategoryName = cat.getId();
                LanguageMst langMst = langMstService.findLanguageMstByLanguageName(language);
                Long selectedLanguageName = langMst.getId();
                ServiceMst serviceMst = serviceMstService.findServiceByServiceName(service);
                Long selectedServiceName = serviceMst.getId();
                String callOption = cred.getString("callOption");

                synchronized (this) {

                    if (newCall) {

                        List<CallMst> listOpenCalls = callMstService.findAllOnGoingCallsByCust(cm.getCustId());

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
                        local_callMst.setCallLocation(location);
                        local_callMst.setCallLatitude(latitude);
                        local_callMst.setCallLongitude(longitude);
                        local_callMst.setCustomerId(cm);
                        local_callMst.setCustId(cm.getCustId());
                        local_callMst.setCallOption(callOption);

                        local_callMst.setServiceId(serviceMst.getId());
                        local_callMst.setServiceId(selectedServiceName);
                        local_callMst.setCategoryId(selectedCategoryName);
                        local_callMst.setLanguageId(selectedLanguageName);
                        local_callMst.setCallMedium("");
                        local_callMst.setActiveFlg(true);
                        local_callMst.setDeleteFlg(false);
                        local_callMst.setStartTime(CustomConvert.javaDateToTimeStamp(new Date()));
                        local_callMst.setCustomerRequestTime(CustomConvert.javaDateToTimeStamp(new Date()));

                        local_callMst = callMstService.saveCallMst(local_callMst);
                        callMstForRepeatcall = callMstService.findCallMstById(local_callMst.getId());
                    } else if (callMstForRepeatcall != null) {
                        callMstForRepeatcall.setServiceId(selectedServiceName);
                        callMstForRepeatcall.setCategoryId(selectedCategoryName);
                        callMstForRepeatcall.setLanguageId(selectedLanguageName);
                        callMstForRepeatcall.setStartTime(CustomConvert.javaDateToTimeStamp(new Date()));
                        callMstForRepeatcall = callMstService.saveCallMst(callMstForRepeatcall);
                    }
                    Long callId;
                    CallMst callMaster;
                    callId = callMstForRepeatcall.getId();
                    callMaster = callMstService.findNonDeletedCallMstById(callId);
                    incomingCallResPonse.setCallId(callId + "");
                    incomingCallResPonse.setStatus("Not Initiated");

                    List<CustomerRmMap> mappedRMList = customerRMMapService.getRMMappedWithCustomer(cm.getId());
                    CustomerRmMap customerRmMap = null;
                    if (!mappedRMList.isEmpty()) {
                        customerRmMap = mappedRMList.get(0);
                    }
                    EmployeeMst rm = employeeMstService.findEmployeeMstById(customerRmMap.getRmId().getId());
                    RmSrmMap rmSrmMap = rmSrmMapService.getSRMMappedWithRM(rm.getId());

                    if (!employeeCallStatusService.findFreeOnlineSRMs(rmSrmMap.getSrmId().getId()).isEmpty()) {

                        /*List<Object> L_E_Mst = Arrays.asList(agentFindingService
                         .findAgents(cat.getCatgCd(), serviceMst.getServiceCd(), langMst.getLanguageCd())
                         .toArray());*/
                        EmployeeMst em = employeeMstService.findEmployeeMstById(rmSrmMap.getSrmId().getId());

                        if (em != null) {
                            //em = (EmployeeMst) L_E_Mst.get(0);

                            EmployeeCallStatus empCallStatus = new EmployeeCallStatus();
                            List<EmployeeCallStatus> empClStatusList = employeeCallStatusService
                                    .findEmployeeCallStatusByEmpId(em);

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

                            List<TenancyEmployeeMap> tenancyEmployeeMaplist = tenancyEmployeeMapService
                                    .findVidyoTenantUrlByEmpId(em.getId());
                            List<Long> unsortList = new ArrayList<>();
                            for (TenancyEmployeeMap tenancyEmployeeMaplist1 : tenancyEmployeeMaplist) {
                                unsortList.add(tenancyEmployeeMaplist1.getId());
                            }
                            Collections.sort(unsortList);

                            TenancyEmployeeMap tenancyEmployeeMap = tenancyEmployeeMapService
                                    .findById(unsortList.get(unsortList.size() - 1));

                            String roomLink = tenancyEmployeeMap.getRoomLink();
                            String roomName = tenancyEmployeeMap.getRoomName();
                            String entityId = tenancyEmployeeMap.getEntityId();

                            incomingCallResPonse.setUrl(roomLink);
                            incomingCallResPonse.setEntityId(entityId);
                            incomingCallResPonse.setRoomName(roomName);
                            incomingCallResPonse.setAgentId(em.getId().toString());
                            incomingCallResPonse.setStatus("Initiated" + "||" + em.getFirstName() + " " + em.getLastName());
                            incomingCallResPonse.setCustomerId(cm.getCustId());
                            incomingCallResPonse.setSocketHostPublic(cm.getCustId());

                            Set<String> custIdSet = new TreeSet<>();
                            custIdSet.add(cm.getCustId());
                            CallScheduler.customerCallDetailsMap.put(callId.toString(), custIdSet);

                            CallEmployeeMap l_CallEmpMap = new CallEmployeeMap();
                            l_CallEmpMap.setCallId(callMaster.getId());
                            l_CallEmpMap.setEmployeeId(em.getId());
                            l_CallEmpMap.setCallType("Normal");
                            l_CallEmpMap.setCustId(cm.getCustId());
                            callMaster.setRoutingCallTime(CustomConvert.javaDateToTimeStamp(new Date()));
                            callMaster.setRoomName(roomLink);
                            callMaster = callMstService.saveCallMst(callMaster);

                            CallRecords callRecords = callRecordsService.findCallRecordsByCallId(callMaster.getId(),
                                    (long) 0);
                            if (null == callRecords) {
                                callRecords = new CallRecords();
                            }

                            callRecords.setCallId(callMaster);
                            callRecords.setEmpId(em);
                            callRecords.setRecorderId("");
                            callRecords.setRoomName(roomName);
                            callRecords.setCustomerId(incomingCallResPonse.getCustomerId());

                            callRecordsService.saveCallRecord(callRecords);
                            try {
                                SocketMessage.send(callScheduler.getAdminSocket(), em.getLoginId(),
                                        "incomingCall#" + callMaster.getId() + "#" + cm.getCustId() + "#" + callMaster.getCallOption());
                            } catch (Exception ex) {
                                logger.error(ex);
                            }

                            CallScheduler.listCallEmp.add(l_CallEmpMap);

                            CustomerDeviceDtl customerDeviceDtl = new CustomerDeviceDtl();
                            customerDeviceDtl.setLogout(0);

                            customerDeviceDtl.setDeviceBrand(null);
                            customerDeviceDtl.setDeviceId(null);
                            customerDeviceDtl.setDeviceIp(null);
                            customerDeviceDtl.setImeiNo(null);

                            customerDeviceDtl.setMobileOsType("android");
                            customerDeviceDtl.setStaticIp(null);
                            if (cm.getAccountNo() != null && cm.getAccountNo().trim().length() > 0) {
                                customerDeviceDtl.setLoginInfo(2);
                            } else {
                                customerDeviceDtl.setLoginInfo(1);
                            }

                            customerDeviceDtl.setCustomerId(cm);
                            customerDeviceDtl.setCustomerOtp(null);
                            customerDeviceDtl.setTimezone("Asia/Kolkata");
                            customerDeviceDtlService.saveCustomerDeviceDtl(customerDeviceDtl, null);

                        } else {
                            logger.info("No SRM Found..........");
                            callMaster = callMstService
                                    .findNonDeletedCallMstById(Long.parseLong(incomingCallResPonse.getCallId()));
                            callMaster.setCallStatus("No Agent Found");
                            callMaster.setStartTime(CustomConvert.javaDateToTimeStamp(new Date()));
                            callMstService.saveCallMst(callMaster);

                        }

                    } else {
                        logger.info("All SRM's are busy or no agent is available..........");
                        if (incomingCallResPonse.getCallId() != null
                                && incomingCallResPonse.getCallId().trim().length() > 0) {
                            callId = Long.parseLong(incomingCallResPonse.getCallId());
                            callMaster.setCallStatus("No Agent Found");
                            callMaster.setStartTime(CustomConvert.javaDateToTimeStamp(new Date()));
                            // callMaster.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                            callMstService.saveCallMst(callMaster);
                            incomingCallResPonse.setCallId("" + callId);
                        }

                    }

                    return incomingCallResPonse;

                }

            } else {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Please Provide valid credential!");
                throw ccmsRestException;
            }
        } else {
            CCMSRestException ccmsRestException = new CCMSRestException();
            ccmsRestException.setErrorCode("418");
            ccmsRestException.setErrorMessage("Please Provide valid credential!");
            throw ccmsRestException;
        }

    }

    @Override
    public IncomingCallResponse getCustomerToAvailableBMCallResponse(String credential) throws Exception {
        logger.info("INSIDE WEBSERVICE calltoavailableBM");

        CustomerMst cm;
        boolean newCall = true;
        String location;
        String latitude;
        String longitude;

        IncomingCallResponse incomingCallResPonse = new IncomingCallResponse();
        CallMst callMstForRepeatcall = null;

        /**
         * @Purpose To check whether method parameter credential is null or not
         */
        if (credential != null && credential.length() > 0) {
            JSONObject cred = new JSONObject(credential);
            String loginId = cred.getString("loginId");
            // String loginPassword = cred.getString("password");
            String category = cred.getString("category");
            String language = cred.getString("language");
            String service = cred.getString("service");
            location = cred.getString("location");
            latitude = cred.getString("latitude");
            longitude = cred.getString("longitude");

            if (loginId == null || loginId.isEmpty() || "".equals(loginId)) {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Please Provide loginId!");
                throw ccmsRestException;

            }

            /*
             * if (loginPassword == null || loginPassword.isEmpty() || loginPassword == "")
             * { CCMSRestException ccmsRestException = new CCMSRestException();
             * ccmsRestException.setErrorCode("418");
             * ccmsRestException.setErrorMessage("Please Provide password!"); throw
             * ccmsRestException; }
             */
            if (category == null
                    || "".equals(language) || category.isEmpty() || "".equals(category) || language == null || language.isEmpty() || service == null || service.isEmpty() || "".equals(service)) {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Please Provide valid information!");
                throw ccmsRestException;
            }

            cm = customerMstService.findCustomerMstByCustId(loginId);
            if (cm != null) {

                incomingCallResPonse.setCustFname(cm.getFirstName());
                incomingCallResPonse.setCustLname(cm.getLastName());
                incomingCallResPonse.setSocketHostPublic(Constants.socketHostPublic);
                List<CallMst> callMstForRepeatcallList = callMstService.findAllNotRecevedCallsByCust(cm.getCustId());
                if (!callMstForRepeatcallList.isEmpty()) {

                    callMstForRepeatcall = callMstForRepeatcallList.get(0);
                    newCall = false;
                }

                /**
                 * the following portion need to be changed in CustomerMst
                 * Relation and Entity as well
                 */
                CategoryMst cat = categoryMstService.findCategoryByCategoryName(category);
                Long selectedCategoryName = cat.getId();
                LanguageMst langMst = langMstService.findLanguageMstByLanguageName(language);
                Long selectedLanguageName = langMst.getId();
                ServiceMst serviceMst = serviceMstService.findServiceByServiceName(service);
                Long selectedServiceName = serviceMst.getId();
                String callOption = cred.getString("callOption");

                synchronized (this) {

                    if (newCall) {

                        List<CallMst> listOpenCalls = callMstService.findAllOnGoingCallsByCust(cm.getCustId());

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
                        local_callMst.setCallLocation(location);
                        local_callMst.setCallLatitude(latitude);
                        local_callMst.setCallLongitude(longitude);

                        local_callMst.setCustomerId(cm);
                        local_callMst.setCustId(cm.getCustId());
                        local_callMst.setCallOption(callOption);

                        local_callMst.setServiceId(serviceMst.getId());
                        local_callMst.setServiceId(selectedServiceName);
                        local_callMst.setCategoryId(selectedCategoryName);
                        local_callMst.setLanguageId(selectedLanguageName);
                        local_callMst.setCallMedium("");
                        local_callMst.setActiveFlg(true);
                        local_callMst.setDeleteFlg(false);
                        local_callMst.setStartTime(CustomConvert.javaDateToTimeStamp(new Date()));
                        local_callMst.setCustomerRequestTime(CustomConvert.javaDateToTimeStamp(new Date()));

                        local_callMst = callMstService.saveCallMst(local_callMst);
                        callMstForRepeatcall = callMstService.findCallMstById(local_callMst.getId());
                    } else if (callMstForRepeatcall != null) {
                        callMstForRepeatcall.setServiceId(selectedServiceName);
                        callMstForRepeatcall.setCategoryId(selectedCategoryName);
                        callMstForRepeatcall.setLanguageId(selectedLanguageName);
                        callMstForRepeatcall.setStartTime(CustomConvert.javaDateToTimeStamp(new Date()));
                        callMstForRepeatcall = callMstService.saveCallMst(callMstForRepeatcall);
                    }
                    Long callId;
                    CallMst callMaster;
                    callId = callMstForRepeatcall.getId();
                    callMaster = callMstService.findNonDeletedCallMstById(callId);
                    incomingCallResPonse.setCallId(callId + "");
                    incomingCallResPonse.setStatus("Not Initiated");

                    List<CustomerRmMap> mappedRMList = customerRMMapService.getRMMappedWithCustomer(cm.getId());
                    CustomerRmMap customerRmMap = null;
                    if (!mappedRMList.isEmpty()) {
                        customerRmMap = mappedRMList.get(0);
                    }
                    EmployeeMst rm = employeeMstService.findEmployeeMstById(customerRmMap.getRmId().getId());
                    RmSrmMap rmSrmMap = rmSrmMapService.getSRMMappedWithRM(rm.getId());
                    EmployeeMst srm = employeeMstService.findEmployeeMstById(rmSrmMap.getSrmId().getId());
                    SrmBmMap srmBmMap = srmBmMapService.getBMMappedWithSRM(srm.getId());

                    if (!employeeCallStatusService.findFreeOnlineBMs(srmBmMap.getBmId().getId()).isEmpty()) {

                        /*List<Object> L_E_Mst = Arrays.asList(agentFindingService
                         .findAgents(cat.getCatgCd(), serviceMst.getServiceCd(), langMst.getLanguageCd())
                         .toArray());*/
                        EmployeeMst em = employeeMstService.findEmployeeMstById(srmBmMap.getBmId().getId());

                        if (em != null) {
                            //em = (EmployeeMst) L_E_Mst.get(0);

                            EmployeeCallStatus empCallStatus = new EmployeeCallStatus();
                            List<EmployeeCallStatus> empClStatusList = employeeCallStatusService
                                    .findEmployeeCallStatusByEmpId(em);

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

                            List<TenancyEmployeeMap> tenancyEmployeeMaplist = tenancyEmployeeMapService
                                    .findVidyoTenantUrlByEmpId(em.getId());
                            List<Long> unsortList = new ArrayList<>();
                            for (TenancyEmployeeMap tenancyEmployeeMaplist1 : tenancyEmployeeMaplist) {
                                unsortList.add(tenancyEmployeeMaplist1.getId());
                            }
                            Collections.sort(unsortList);

                            TenancyEmployeeMap tenancyEmployeeMap = tenancyEmployeeMapService
                                    .findById(unsortList.get(unsortList.size() - 1));

                            String roomLink = tenancyEmployeeMap.getRoomLink();
                            String roomName = tenancyEmployeeMap.getRoomName();
                            String entityId = tenancyEmployeeMap.getEntityId();

                            incomingCallResPonse.setUrl(roomLink);
                            incomingCallResPonse.setEntityId(entityId);
                            incomingCallResPonse.setRoomName(roomName);
                            incomingCallResPonse.setAgentId(em.getId().toString());
                            incomingCallResPonse.setStatus("Initiated" + "||" + em.getFirstName() + " " + em.getLastName());
                            incomingCallResPonse.setCustomerId(cm.getCustId());
                            incomingCallResPonse.setSocketHostPublic(cm.getCustId());

                            Set<String> custIdSet = new TreeSet<>();
                            custIdSet.add(cm.getCustId());
                            CallScheduler.customerCallDetailsMap.put(callId.toString(), custIdSet);

                            CallEmployeeMap l_CallEmpMap = new CallEmployeeMap();
                            l_CallEmpMap.setCallId(callMaster.getId());
                            l_CallEmpMap.setEmployeeId(em.getId());
                            l_CallEmpMap.setCallType("Normal");
                            l_CallEmpMap.setCustId(cm.getCustId());
                            callMaster.setRoutingCallTime(CustomConvert.javaDateToTimeStamp(new Date()));
                            callMaster.setRoomName(roomLink);
                            callMaster = callMstService.saveCallMst(callMaster);

                            CallRecords callRecords = callRecordsService.findCallRecordsByCallId(callMaster.getId(),
                                    (long) 0);
                            if (null == callRecords) {
                                callRecords = new CallRecords();
                            }

                            callRecords.setCallId(callMaster);
                            callRecords.setEmpId(em);
                            callRecords.setRecorderId("");
                            callRecords.setRoomName(roomName);
                            callRecords.setCustomerId(incomingCallResPonse.getCustomerId());

                            callRecordsService.saveCallRecord(callRecords);
                            try {
                                SocketMessage.send(callScheduler.getAdminSocket(), em.getLoginId(),
                                        "incomingCall#" + callMaster.getId() + "#" + cm.getCustId() + "#" + callMaster.getCallOption());
                            } catch (Exception ex) {
                                logger.error(ex);
                            }

                            CallScheduler.listCallEmp.add(l_CallEmpMap);

                            CustomerDeviceDtl customerDeviceDtl = new CustomerDeviceDtl();
                            customerDeviceDtl.setLogout(0);

                            customerDeviceDtl.setDeviceBrand(null);
                            customerDeviceDtl.setDeviceId(null);
                            customerDeviceDtl.setDeviceIp(null);
                            customerDeviceDtl.setImeiNo(null);

                            customerDeviceDtl.setMobileOsType("android");
                            customerDeviceDtl.setStaticIp(null);
                            if (cm.getAccountNo() != null && cm.getAccountNo().trim().length() > 0) {
                                customerDeviceDtl.setLoginInfo(2);
                            } else {
                                customerDeviceDtl.setLoginInfo(1);
                            }

                            customerDeviceDtl.setCustomerId(cm);
                            customerDeviceDtl.setCustomerOtp(null);
                            customerDeviceDtl.setTimezone("Asia/Kolkata");
                            customerDeviceDtlService.saveCustomerDeviceDtl(customerDeviceDtl, null);

                        } else {
                            logger.info("No BM Found..........");
                            callMaster = callMstService
                                    .findNonDeletedCallMstById(Long.parseLong(incomingCallResPonse.getCallId()));
                            callMaster.setCallStatus("No Agent Found");
                            callMaster.setStartTime(CustomConvert.javaDateToTimeStamp(new Date()));
                            callMstService.saveCallMst(callMaster);

                        }

                    } else {
                        logger.info("All BM's are busy or no agent is available..........");
                        if (incomingCallResPonse.getCallId() != null
                                && incomingCallResPonse.getCallId().trim().length() > 0) {
                            callId = Long.parseLong(incomingCallResPonse.getCallId());
                            callMaster.setCallStatus("No Agent Found");
                            callMaster.setStartTime(CustomConvert.javaDateToTimeStamp(new Date()));
                            // callMaster.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                            callMstService.saveCallMst(callMaster);
                            incomingCallResPonse.setCallId("" + callId);
                        }

                    }

                    return incomingCallResPonse;

                }

            } else {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Please Provide valid credential!");
                throw ccmsRestException;
            }
        } else {
            CCMSRestException ccmsRestException = new CCMSRestException();
            ccmsRestException.setErrorCode("418");
            ccmsRestException.setErrorMessage("Please Provide valid credential!");
            throw ccmsRestException;
        }

    }

    @Override
    public List<ScheduleDetailsResponse> getScheduledCallDtlsByRMId(String callInfo) throws Exception {
        String empId;
        List<ScheduleCallDto> scheduledCallDtlDtoList = new ArrayList<>();
        List<ScheduleDetailsResponse> scheduleDetailsResponseList = new ArrayList<>();
        if (callInfo != null && callInfo.length() > 0) {
            JSONObject callInformation = new JSONObject(callInfo);
            if (callInformation.has("empId")) {
                empId = callInformation.getString("empId");
                logger.info("with empId=" + empId);
                if (empId != null && !"".equals(empId)) {
                    scheduledCallDtlDtoList = callSchedulerService.getScheduledCallDtlsByRMId(Long.parseLong(empId));
                    if (null != scheduledCallDtlDtoList && !scheduledCallDtlDtoList.isEmpty()) {
                        for (ScheduleCallDto dto : scheduledCallDtlDtoList) {
                            ScheduleDetailsResponse detailsResponse = new ScheduleDetailsResponse();
                            detailsResponse.setCallType(dto.getCallType());
                            detailsResponse.setCustomerName(dto.getFirstName() + " " + dto.getLastName());
                            logger.info("dto.getScheduleEndDateTime():" + dto.getScheduleEndDateTime());
                            DateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            Date d = f.parse(dto.getScheduleEndDateTime() + "");
                            DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
                            DateFormat time = new SimpleDateFormat("HH:mm:ss");
                            logger.info("Date: " + date.format(d));
                            logger.info("Time: " + time.format(d));
                            detailsResponse.setEndDateText(date.format(d));
                            detailsResponse.setEndTimeText(time.format(d));
                            detailsResponse.setId(dto.getScheduleCallMstId());
                            ServiceMst serviceMst = servicesMstDao.findAllServiceMstById(dto.getService());
                            detailsResponse.setServiceName(serviceMst.getServiceName());
                            d = f.parse(dto.getScheduleStartDateTime() + "");
                            date = new SimpleDateFormat("yyyy-MM-dd");
                            time = new SimpleDateFormat("HH:mm:ss");
                            logger.info("Date: " + date.format(d));
                            logger.info("Time: " + time.format(d));
                            detailsResponse.setStartDateText(date.format(d));
                            detailsResponse.setStartTimeText(time.format(d));
                            scheduleDetailsResponseList.add(detailsResponse);

                        }
                        return scheduleDetailsResponseList;
                    } else {
                        CCMSRestException ccmsRestException = new CCMSRestException();
                        ccmsRestException.setErrorCode("418");
                        ccmsRestException.setErrorMessage("No Schedule Call Found");
                        throw ccmsRestException;
                    }
                } else {
                    CCMSRestException ccmsRestException = new CCMSRestException();
                    ccmsRestException.setErrorCode("418");
                    ccmsRestException.setErrorMessage("Wrong Credential!");
                    throw ccmsRestException;
                }
            } else {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Wrong Credential!");
                throw ccmsRestException;
            }
        } else {
            CCMSRestException ccmsRestException = new CCMSRestException();
            ccmsRestException.setErrorCode("418");
            ccmsRestException.setErrorMessage("Wrong Credential!");
            throw ccmsRestException;
        }
    }

    @Override
    public List<ScheduleDetailsResponse> checkScheduleCallCustomerInitiate(String callInfo) throws Exception {
        String empId;
        Long scheduledCallMstId;
        List<ScheduleDetailsResponse> scheduleDetailsResponseList = new ArrayList<>();
        if (callInfo != null && callInfo.length() > 0) {
            JSONObject callInformation = new JSONObject(callInfo);
            if (callInformation.has("empId") && callInformation.has("scheduledCallMstId")) {
                empId = callInformation.getString("empId");
                scheduledCallMstId = Long.parseLong(callInformation.getString("scheduledCallMstId"));

                logger.info("checkScheduleCallCustomerInitiate with empId=" + empId + "scheduledCallMstId:" + scheduledCallMstId);
                if (empId != null && !"".equals(empId)) {
                    EmployeeMst empLocal = employeeMstService.findEmployeeMstById(Long.parseLong(empId));
                    logger.info("empLocal:" + empLocal);

                    for (Iterator<CallEmployeeMap> itr = CallScheduler.listScheduleCallForEmp.iterator(); itr.hasNext();) {
                        logger.info("Case--2-------------");
                        CallEmployeeMap cem = itr.next();
                        ScheduleCall scheduleCall = callSchedulerService.findNonTakenById(scheduledCallMstId);

                        if (null != scheduleCall && Objects.equals(cem.getEmployeeId(), empLocal.getId()) && cem.getCallType().equalsIgnoreCase("ScheduleCall") && Objects.equals(scheduleCall.getId(), cem.getScheduleCallId())) {

                            ScheduleDetailsResponse response = new ScheduleDetailsResponse();
                            logger.info("Case--3-------------:" + scheduleCall);
                            Date scheduleStartDate = scheduleCall.getScheduleDate();
                            Date now = new Date();
                            Calendar toleranceDate = Calendar.getInstance();
                            toleranceDate.setTime(scheduleStartDate);
                            toleranceDate.add(Calendar.MINUTE, +5);
                            //TO-DO check if Schedule current time...
                            if (now.after(scheduleStartDate) && now.before(toleranceDate.getTime()) || now.equals(scheduleStartDate)) {
                                logger.info("Case--4------------:" + scheduleStartDate);
                                CallMst callMst = callMstService.findCallMstById(cem.getCallId());
                                logger.info("Case--5------------:" + callMst);
                                List<TenancyEmployeeMap> tenancyEmployeeMaplist = tenancyEmployeeMapService.findVidyoTenantUrlByEmpId(empLocal.getId());

                                List<Long> unsortList = new ArrayList<>();
                                for (TenancyEmployeeMap tenancyEmployeeMaplist1 : tenancyEmployeeMaplist) {
                                    unsortList.add(tenancyEmployeeMaplist1.getId());
                                }
                                Collections.sort(unsortList);
                                TenancyEmployeeMap tenancyEmployeeMap = null;
                                String roomLink = "";

                                if (!tenancyEmployeeMaplist.isEmpty()) {
                                    tenancyEmployeeMap = tenancyEmployeeMapService.findById(unsortList.get(unsortList.size() - 1));
                                    roomLink = tenancyEmployeeMap.getRoomLink();
                                    tenancyEmployeeMap.setEmpId(empLocal);

                                    tenancyEmployeeMap.setRoomName(callMst.getRoomName());

                                    tenancyEmployeeMapService.saveTenancyEmployeeMap(tenancyEmployeeMap);
                                }
                                logger.info("Case--6------------roomLink:" + roomLink + "\nroomName():" + callMst.getRoomName());

                                CustomerMst customerMst = customerMstService.findCustomerMstByCustId(callMst.getCustId());
                                Timestamp fromtime = CustomConvert.javaDateToTimeStamp(new Date());
                                ReasonMst reasonMst = reasonMstService.findReasonMstByReasonCode("CLS01");
                                EmployeeActivityDtl employeeActDtl = new EmployeeActivityDtl();
                                employeeActDtl.setEmpId(empLocal);
                                employeeActDtl.setActivity("Call Started");
                                employeeActDtl.setStartTime(fromtime);
                                employeeActDtl.setReasonId(reasonMst);
                                employeeActDtl.setReasonCd(reasonMst.getReasonCd());
                                employeeActDtl.setReasonDesc(reasonMst.getReasonDesc());
                                employeeActDtl.setCallMstId(cem.getCallId());
                                employeeActivityDtlService.save(employeeActDtl, empLocal);
                                EmployeeActivityDtl employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(empLocal.getId(), "NEXT CALL");
                                if (employeeActivityDtl != null) {
                                    employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                                    employeeActivityDtl = employeeActivityDtlService.save(employeeActivityDtl);

                                }

                                callMst.setStartTime(fromtime);
                                callMst.setCallStatus("Active");
                                CallMst callMstLocal = callMstService.saveCallMst(callMst);
                                CallRecords callRecords = null;
                                if (null != callMstLocal) {
                                    callRecords = callRecordsService.findCallRecordsByCallId(callMstLocal.getId(), (long) 0);
                                }
                                if (null == callRecords) {
                                    callRecords = new CallRecords();
                                }
                                tenancyEmployeeMaplist = tenancyEmployeeMapService.findVidyoTenantUrlByEmpId(empLocal.getId());
                                unsortList = new ArrayList<>();
                                for (TenancyEmployeeMap tenancyEmployeeMaplist1 : tenancyEmployeeMaplist) {
                                    unsortList.add(tenancyEmployeeMaplist1.getId());
                                }
                                Collections.sort(unsortList);

                                tenancyEmployeeMap = tenancyEmployeeMapService.findById(unsortList.get(unsortList.size() - 1));

                                String customerID = customerMst.getCustId();
                                callRecords.setCallId(callMstLocal);
                                callRecords.setEmpId(empLocal);
                                callRecords.setCustomerId(customerID);
                                String roomName = tenancyEmployeeMap.getRoomName();
                                callRecords.setRoomName(roomName);
                                callRecords.setExternalPlaybackLink("Not Saved");
                                callRecords.setRecorderId("");

                                callRecordsService.saveCallRecord(callRecords);
                                CallDtl callDtl = new CallDtl();
                                callDtl.setCallMstId(callMstLocal);
                                callDtl.setActiveFlg(true);
                                callDtl.setDeleteFlg(false);
                                callDtl.setHandeledById(empLocal);
                                callDtl.setStartTime(fromtime);
                                callDtl.setCallTypeInfo(cem.getCallType());
                                callDtlService.saveCallDtl(callDtl);
                                EmployeeCallStatus empCallStatus = new EmployeeCallStatus();
                                List<EmployeeCallStatus> empClStatusList = employeeCallStatusService.findEmployeeCallStatusByEmpId(empLocal);
                                if (empClStatusList != null) {
                                    for (EmployeeCallStatus empstatus : empClStatusList) {
                                        empCallStatus = empstatus;
                                    }
                                    empCallStatus.setStatus(false);
                                    employeeCallStatusService.saveEmployeeCallStatus(empCallStatus);
                                } else {
                                    empCallStatus.setEmpId(empLocal);
                                    empCallStatus.setStatus(false);
                                    employeeCallStatusService.saveEmployeeCallStatus(empCallStatus);
                                }
                                response.setRoomId(roomLink);
                                response.setRoomName(callMst.getRoomName());
                                response.setCallMstId(callMst.getId());
                                response.setCustomerName(customerMst.getFirstName() + " " + customerMst.getLastName());
                                response.setId(scheduleCall.getId());
                                response.setCustLoginId(customerID);
                                response.setCallType(scheduleCall.getCallType());
                                scheduleDetailsResponseList.add(response);
                                itr.remove();
                            }

                        }
                    }

                } else {
                    CCMSRestException ccmsRestException = new CCMSRestException();
                    ccmsRestException.setErrorCode("418");
                    ccmsRestException.setErrorMessage("Wrong Credential!");
                    throw ccmsRestException;
                }
            } else {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Wrong Credential!");
                throw ccmsRestException;
            }
        }
        if (null == scheduleDetailsResponseList || scheduleDetailsResponseList.isEmpty()) {
            CCMSRestException ccmsRestException = new CCMSRestException();
            ccmsRestException.setErrorCode("418");
            ccmsRestException.setErrorMessage("Wrong Credential!");
            throw ccmsRestException;
        } else {
            logger.info("Before Return Data Real-time checking");
            return scheduleDetailsResponseList;
        }
    }

    @Override
    public ScheduleDetailsResponse saveScheduleDetails(String callInfo) throws Exception {
        String empId;
        String custId;
        String callType;
        Long serviceMstId;
        String scheduleText;
        Date serviceStartTime;
        Date serviceEndTime;
        Date schDateTime;
        Date schEndDateTime;
        Date scheduleStartTimeeee;
        Date scheduleEndTimeeee;
        ScheduleDetailsResponse res = new ScheduleDetailsResponse();
        try {
            if (callInfo != null && callInfo.length() > 0) {
                JSONObject callInformation = new JSONObject(callInfo);
                if (callInformation.has("empId") && callInformation.has("custId") && callInformation.has("serviceMstId")
                        && callInformation.has("callType") && callInformation.has("scheduleDateText")
                        && callInformation.has("scheduleEndDateText")) {
                    empId = callInformation.getString("empId");
                    custId = callInformation.getString("custId");
                    serviceMstId = Long.parseLong(callInformation.getString("serviceMstId"));
                    String scheduleDateText = callInformation.getString("scheduleDateText");
                    String scheduleEndDateText = callInformation.getString("scheduleEndDateText");
                    callType = callInformation.getString("callType");
                    logger.info("callType:" + callType + "empId:" + empId);
                    logger.info("scheduleDateText:" + scheduleDateText + "scheduleEndDateText:" + scheduleEndDateText);
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                    schDateTime = formatter.parse(scheduleDateText);
                    schEndDateTime = formatter.parse(scheduleEndDateText);

                    if (empId != null && !"".equals(empId)) {
                        EmployeeMst empLocal = employeeMstService.findEmployeeMstById(Long.parseLong(empId));
                        logger.info("empLocal:" + empLocal);
                        final TimeZone timeZoneL = TimeZone.getDefault();

                        CustomerMst cm = customerMstService.findCustomerMstById(Long.parseLong(custId));

                        String serviceStrtTimeRM1 = Constants.RM1_Service_Start_Time;
                        String serviceendTimeRM1 = Constants.RM1_Service_End_Time;

                        SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");

                        String scheduleStartTime = sdf1.format(schDateTime);
                        String scheduleEndTime = sdf1.format(schEndDateTime);

                        serviceStartTime = sdf1.parse(serviceStrtTimeRM1);
                        serviceEndTime = sdf1.parse(serviceendTimeRM1);

                        scheduleStartTimeeee = sdf1.parse(scheduleStartTime);
                        scheduleEndTimeeee = sdf1.parse(scheduleEndTime);

                        List<ScheduleCallDto> scheduleCallDtlsDtos = callSchedulerService.getScheduledCallDtlsByEmployeeMstID(empLocal.getId());

                        ScheduleCall scheduleCall = new ScheduleCall();
                        ScheduleCall schCall;

                        try {
                            logger.info("serviceMstId:" + serviceMstId);
                            logger.info("schDateTime:" + schDateTime);
                            logger.info("callType:" + callType + "CM:" + cm + "schEndDateTime:" + schEndDateTime);
                            ThreadSafeSimpleDateFormat sdf = new ThreadSafeSimpleDateFormat("dd-MMM-yyyy HH:mm");
                            sdf.setTimeZone(timeZoneL);
                            schDateTime = sdf.parse(sdf.format(schDateTime));
                            schEndDateTime = sdf.parse(sdf.format(schEndDateTime));
                            java.sql.Timestamp timestamp = new java.sql.Timestamp(schDateTime.getTime());

                            java.sql.Timestamp timestampEndDate = new java.sql.Timestamp(schEndDateTime.getTime());
                            logger.info(schEndDateTime + "case-------------1-----------------------:" + schDateTime);

                            if ((schDateTime != null && schEndDateTime == null) || (schDateTime == null && schEndDateTime != null)) {
                                scheduleText = "Please Select Start Date & End Date Properly";
                                logger.info("scheduleText-res:" + scheduleText);
                                res.setMessage(scheduleText);
                                return res;
                            }
                            if (scheduleCallDtlsDtos != null && scheduleCallDtlsDtos.size() > 0 && !scheduleCallDtlsDtos.isEmpty()) {
                                if (schDateTime != null && schEndDateTime != null) {
                                    for (ScheduleCallDto obj : scheduleCallDtlsDtos) {
                                        if ((schDateTime.after(obj.getScheduleStartDateTime()) && schDateTime.before(obj.getScheduleEndDateTime())) || schDateTime.equals(obj.getScheduleStartDateTime()) || schDateTime.equals(obj.getScheduleEndDateTime())) {
                                            scheduleText = "Start Time overlapping with existing Schedule Call..!Please Select another Start Time!";
                                            logger.info("scheduleText-res:" + scheduleText);
                                            res.setMessage(scheduleText);
                                            return res;

                                        }
                                        if ((schEndDateTime.after(obj.getScheduleStartDateTime()) && schEndDateTime.before(obj.getScheduleEndDateTime())) || schEndDateTime.equals(obj.getScheduleStartDateTime()) || schEndDateTime.equals(obj.getScheduleEndDateTime())) {
                                            scheduleText = "End Time overlapping with existing Schedule Call..!Please Select another End Time!";
                                            logger.info("scheduleText-res:" + scheduleText);
                                            res.setMessage(scheduleText);
                                            return res;

                                        }

                                    }
                                }

                            }

                            if (schDateTime != null && schEndDateTime != null) {
                                if (schDateTime.compareTo(schEndDateTime) == 1) {
                                    scheduleText = "Start Date Time Should Not be Greater Than End Date Time..!!";
                                    logger.info("scheduleText-res:" + scheduleText);
                                    res.setMessage(scheduleText);
                                    return res;
                                }
                            }

                            if (schDateTime != null && schEndDateTime != null) {
                                if (scheduleStartTimeeee.before(serviceStartTime) || scheduleStartTimeeee.after(serviceEndTime) || scheduleStartTimeeee.equals(serviceEndTime) || scheduleEndTimeeee.after(serviceEndTime)) {
                                    scheduleText = "Please Schdeule in between Service Time!";
                                    logger.info("scheduleText-res:" + scheduleText);
                                    res.setMessage(scheduleText);
                                    return res;

                                }

                            }

                            if (schDateTime.after(new Date())) {
                                if (cm != null) {

                                    CategoryMst categoryMst = categoryMstService.findCategoryByCategoryCode("UGR");
                                    LanguageMst languageMst = langMstService.findLanguageMstByLanguageCode("ENG");

                                    //TO-DO validate If already there some schedule with same time slot with particular RM 
                                    ServiceMst serviceMst = serviceMstService.findAllServiceMstById(serviceMstId);

                                    scheduleCall.setCreationDatetime(CustomConvert.javaDateToTimeStamp(new Date()));
                                    scheduleCall.setExecuteStatus("Scheduled");
                                    scheduleCall.setScheduledBy("RM");
                                    scheduleCall.setSchedulerId((long) 0);
                                    scheduleCall.setScheduleDate(timestamp);
                                    scheduleCall.setCustomerId(cm);
                                    scheduleCall.setCallMedium("Mobile");

                                    scheduleCall.setCallType(callType);
                                    scheduleCall.setEmployeeId(empLocal);
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
                                    if (null != schCall && null != empLocal) {
                                        String messageBody;
                                        //Mail Send TO RM
                                        messageBody = "<html><body>Dear &nbsp; " + empLocal.getFirstName() + " " + empLocal.getLastName();
                                        messageBody += " ,&nbsp;<br></br><br></br>You have a scheduled meeting with customer " + cm.getFirstName() + " " + cm.getLastName() + " at " + schDateTime + " .";

                                        messageBody += "<br>";
                                        messageBody += "<b>Note:</b>Please do not refresh the browser during the call.";
                                        messageBody += "<br></br>";
                                        messageBody += SendingMailUtil.TELE_THX_HTML;
                                        messageBody += "</body><html/>";
                                        logger.info("Before Send Email...");
                                        boolean mailSend = SendingMailUtil.sendEMail(empLocal.getEmail(), messageBody, SendingMailUtil.SCHEDULE_CALL);
                                        logger.info("After Send Email...");
                                        if (mailSend) {
                                            logger.info("mail sending was successfull... to RM:" + empLocal.getEmail());
                                            //   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Email Send Was Successful", "Send Successful!!"));
                                        }

                                        messageBody = "<html><body>Dear &nbsp; Customer ";
                                        messageBody += ",&nbsp;<br>You have a scheduled meeting with your Relationship Manager at " + schDateTime + " .";
                                        //messageBody += "&nbsp;<br>Kindly click the following link, to join on Scheduled Call.";/
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
                                        }

                                        scheduleText = "Your call has been scheduled successfully";
                                        logger.info("scheduleText-res:" + scheduleText);
                                        res.setMessage(scheduleText);
                                        return res;
                                    } else {
                                        scheduleText = "Call Scheduleding Failed";
                                        logger.info("scheduleText-res:" + scheduleText);
                                        res.setMessage(scheduleText);
                                        return res;
                                    }
                                }
                            } else {
                                scheduleText = "Select valid Date and time";
                                logger.info("scheduleText-res:" + scheduleText);
                                res.setMessage(scheduleText);
                                return res;
                            }

                        } catch (Exception e) {
                            logger.error("Eroor:" + e);
                            scheduleText = "Some Error Occured, Please try again";
                            logger.info("scheduleText-res:" + scheduleText);
                            res.setMessage(scheduleText);
                            return res;
                        }
                    } else {
                        scheduleText = "Wrong Credential!";
                        logger.info("scheduleText-res:" + scheduleText);
                        res.setMessage(scheduleText);
                        return res;
                    }
                } else {
                    scheduleText = "Wrong Credential!";
                    logger.info("scheduleText-res:" + scheduleText);
                    res.setMessage(scheduleText);
                    return res;
                }
            }
            scheduleText = "Wrong Credential!";
            logger.info("scheduleText-res:" + scheduleText);
            res.setMessage(scheduleText);
            return res;
        } catch (JSONException | NumberFormatException | ParseException e) {
            logger.error(e);
            scheduleText = "Some Error Occured, Please try again";
            logger.info("scheduleText-res:" + scheduleText);
            res.setMessage(scheduleText);
            return res;
        }
    }

    @Override
    public ScheduleDetailsResponse saveScheduleDetailsCustomer(String callInfo) throws Exception {
        String customerLoginId;
        String callType;
        Long serviceMstId;
        String scheduleText;
        Date serviceStartTime;
        Date serviceEndTime;
        Date schDateTime;
        Date schEndDateTime;
        Date scheduleStartTimeeee;
        Date scheduleEndTimeeee;
        ScheduleDetailsResponse res = new ScheduleDetailsResponse();
        CustomerMst cm;
        EmployeeMst empLocal;
        String timeZone;

        try {
            if (callInfo != null && callInfo.length() > 0) {
                JSONObject callInformation = new JSONObject(callInfo);
                if (callInformation.has("customerLoginId") && callInformation.has("serviceMstId")
                        && callInformation.has("callType") && callInformation.has("scheduleDateText")
                        && callInformation.has("scheduleEndDateText")) {
                    customerLoginId = callInformation.getString("customerLoginId");
                    serviceMstId = Long.parseLong(callInformation.getString("serviceMstId"));
                    String scheduleDateText = callInformation.getString("scheduleDateText");
                    String scheduleEndDateText = callInformation.getString("scheduleEndDateText");
                    callType = callInformation.getString("callType");
                    timeZone = callInformation.getString("timeZone");
                    logger.info("timeZone:" + timeZone);
                    logger.info("callType:" + callType + "customerLoginId:" + customerLoginId);
                    logger.info("scheduleDateText:" + scheduleDateText + "scheduleEndDateText:" + scheduleEndDateText);

                    final TimeZone timeZoneL = TimeZone.getDefault();

                    Date localSchDateTime = CustomConvert.convertScheduleTimeZone(scheduleDateText, timeZone, timeZoneL.getID());

                    schDateTime = localSchDateTime;

                    Date localSchDateEndTime = CustomConvert.convertScheduleTimeZone(scheduleEndDateText, timeZone, timeZoneL.getID());

                    schEndDateTime = localSchDateEndTime;

                    if (customerLoginId != null && !"".equals(customerLoginId)) {

                        cm = customerMstService.findCustomerMstByCustId(customerLoginId);

                        CustomerMst cmst = customerMstService.findCustomerMstByCustId(customerLoginId);

                        List<CustomerRmMap> mappedRMList = customerRMMapService.getRMMappedWithCustomer(cmst.getId());
                        CustomerRmMap customerRmMap = null;
                        if (!mappedRMList.isEmpty()) {
                            customerRmMap = mappedRMList.get(0);
                        }

                        empLocal = employeeMstService.findEmployeeMstById(customerRmMap.getRmId().getId());

                        String serviceStrtTimeRM1 = Constants.RM1_Service_Start_Time;
                        String serviceendTimeRM1 = Constants.RM1_Service_End_Time;

                        SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");

                        String scheduleStartTime = sdf1.format(localSchDateTime);
                        String scheduleEndTime = sdf1.format(localSchDateEndTime);

                        serviceStartTime = sdf1.parse(serviceStrtTimeRM1);
                        serviceEndTime = sdf1.parse(serviceendTimeRM1);

                        scheduleStartTimeeee = sdf1.parse(scheduleStartTime);
                        scheduleEndTimeeee = sdf1.parse(scheduleEndTime);

                        List<ScheduleCallDto> scheduleCallDtlsDtos = callSchedulerService.getScheduledCallDtlsByCustomerMstID(cm.getId());

                        ScheduleCall scheduleCall = new ScheduleCall();
                        ScheduleCall schCall;

                        try {
                            logger.info("serviceMstId:" + serviceMstId);
                            logger.info("Local schDateTime:" + schDateTime);
                            logger.info("callType:" + callType + "CM:" + cm + "Local schEndDateTime:" + schEndDateTime);

//                            if ((schDateTime != null && schEndDateTime == null) || (schDateTime == null && schEndDateTime != null)) {
//                                scheduleText = "Please Select Start Date & End Date Properly";
//                                res.setMessage(scheduleText);
//                                return res;
//                            }
                            if (scheduleCallDtlsDtos != null && scheduleCallDtlsDtos.size() > 0 && !scheduleCallDtlsDtos.isEmpty()) {
//                                if (schDateTime != null && schEndDateTime != null) {
                                for (ScheduleCallDto obj : scheduleCallDtlsDtos) {
                                    if ((schDateTime.after(obj.getScheduleStartDateTime()) && schDateTime.before(obj.getScheduleEndDateTime())) || schDateTime.equals(obj.getScheduleStartDateTime()) || schDateTime.equals(obj.getScheduleEndDateTime())) {
                                        scheduleText = "Start Time overlapping with existing Schedule Call..!Please Select another Start Time!";
                                        res.setMessage(scheduleText);
                                        return res;

                                    }
                                    if ((schEndDateTime.after(obj.getScheduleStartDateTime()) && schEndDateTime.before(obj.getScheduleEndDateTime())) || schEndDateTime.equals(obj.getScheduleStartDateTime()) || schEndDateTime.equals(obj.getScheduleEndDateTime())) {
                                        scheduleText = "End Time overlapping with existing Schedule Call..!Please Select another End Time!";
                                        res.setMessage(scheduleText);
                                        return res;

                                    }

                                }
//                                }

                            }

//                            if (schDateTime != null && schEndDateTime != null) {
                            if (schDateTime.compareTo(schEndDateTime) == 1) {
                                scheduleText = "Start Date Time Should Not be Greater Than End Date Time..!!";
                                res.setMessage(scheduleText);
                                return res;
                            }
//                            }

//                            if (schDateTime != null && schEndDateTime != null) {
                            if (scheduleStartTimeeee.before(serviceStartTime) || scheduleStartTimeeee.after(serviceEndTime) || scheduleStartTimeeee.equals(serviceEndTime) || scheduleEndTimeeee.after(serviceEndTime)) {
                                scheduleText = "Please Schdeule in between Service Time!";
                                res.setMessage(scheduleText);
                                return res;

                            }

//                            }
                            if (schDateTime.after(new Date())) {
                                if (cm != null) {

                                    CategoryMst categoryMst = categoryMstService.findCategoryByCategoryCode("UGR");
                                    LanguageMst languageMst = langMstService.findLanguageMstByLanguageCode("ENG");

                                    //TO-DO validate If already there some schedule with same time slot with particular RM 
                                    ServiceMst serviceMst = serviceMstService.findAllServiceMstById(serviceMstId);

                                    scheduleCall.setCreationDatetime(CustomConvert.javaDateToTimeStamp(new Date()));
                                    scheduleCall.setExecuteStatus("Scheduled");
                                    scheduleCall.setScheduledBy("Customer");
                                    scheduleCall.setSchedulerId((long) 0);
                                    scheduleCall.setScheduleDate(schDateTime);
                                    scheduleCall.setCustomerId(cm);
                                    scheduleCall.setCallMedium("Mobile");

                                    scheduleCall.setCallType(callType);
                                    scheduleCall.setEmployeeId(empLocal);
                                    scheduleCall.setScheduleEndDate(schEndDateTime);

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
                                    if (null != schCall && null != empLocal) {
                                        String messageBody;
                                        //Mail Send TO RM
                                        messageBody = "<html><body>Dear &nbsp; " + empLocal.getFirstName() + " " + empLocal.getLastName();
                                        messageBody += " ,&nbsp;<br></br><br></br>You have a scheduled meeting with customer " + cm.getFirstName() + " " + cm.getLastName() + " at " + schDateTime + " .";
                                        // messageBody += "<br>Kindly click the following link, to join on Scheduled Call.";
                                        messageBody += "<br>";
                                        messageBody += "<b>Note:</b>Please do not refresh the browser during the call.";
                                        messageBody += "<br></br>";
                                        messageBody += SendingMailUtil.TELE_THX_HTML;
                                        messageBody += "</body><html/>";
                                        logger.info("Before Send Email...");
                                        boolean mailSend = SendingMailUtil.sendEMail(empLocal.getEmail(), messageBody, SendingMailUtil.SCHEDULE_CALL);
                                        logger.info("After Send Email...");
                                        if (mailSend) {
                                            logger.info("mail sending was successfull... to RM:" + empLocal.getEmail());
                                            //   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Email Send Was Successful", "Send Successful!!"));
                                        }

                                        messageBody = "<html><body>Dear &nbsp; Customer ";
                                        messageBody += ",&nbsp;<br>You have a scheduled meeting with your Relationship Manager at " + schDateTime + " .";
                                        // messageBody += "&nbsp;<br>Kindly click the following link, to join on Scheduled Call.";
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
                                        }

                                        scheduleText = "Your call has been scheduled successfully";
                                        res.setMessage(scheduleText);
                                        return res;
                                    } else {
                                        scheduleText = "Call Scheduleding Failed";
                                        res.setMessage(scheduleText);
                                        return res;
                                    }
                                }
                            } else {
                                scheduleText = "Select valid Date and time";
                                res.setMessage(scheduleText);
                                return res;
                            }

                        } catch (Exception e) {
                            logger.error("Eroor:" + e);
                            scheduleText = "Some Error Occured, Please try again";
                            res.setMessage(scheduleText);
                            return res;
                        }
                    } else {
                        scheduleText = "Wrong Credential!";
                        res.setMessage(scheduleText);
                        return res;
                    }
                } else {
                    scheduleText = "Wrong Credential!";
                    res.setMessage(scheduleText);
                    return res;
                }
            }
            scheduleText = "Wrong Credential!";
            res.setMessage(scheduleText);
            return res;
        } catch (JSONException | NumberFormatException | ParseException e) {
            logger.error(e);
            scheduleText = "Some Error Occured, Please try again";
            res.setMessage(scheduleText);
            return res;
        }
    }

    @Override
    public ScheduleDetailsResponse initiateSchrduleCallByCustomer(String callInfo) throws Exception {
        Long scheduledCallMstId;
        String customerLoginId;
        String location;
        String latitude;
        String longitude;
        ScheduleDetailsResponse response = new ScheduleDetailsResponse();
        try {
            if (callInfo != null && callInfo.length() > 0) {
                JSONObject callInformation = new JSONObject(callInfo);
                if (callInformation.has("customerLoginId") && callInformation.has("scheduledCallMstId")) {
                    customerLoginId = callInformation.getString("customerLoginId");
                    scheduledCallMstId = Long.parseLong(callInformation.getString("scheduledCallMstId"));
                    location = callInformation.getString("location");
                    latitude = callInformation.getString("latitude");
                    longitude = callInformation.getString("longitude");

                    logger.info("scheduleCallCallDataFromCustomer....." + scheduledCallMstId);
                    CustomerMst customerMst_local = customerMstService.findCustomerMstByCustId(customerLoginId);
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
                        response.setMessage(downMessage);
                        return response;
                    } else if (inservicedownTime) {
                        if ((this.getServiceTimeStart() != null && !this.getServiceTimeStart().trim().equals("")) && (this.getServiceTimeEnd() != null && !this.getServiceTimeEnd().trim().equals(""))) {
                            downMessage = "We are unavailable to attend to you via this channel at this time. We are only available on " + weekDay + " between " + this.getServiceTimeStart() + " to " + this.getServiceTimeEnd();
                            response.setMessage(downMessage);
                            return response;
                        } else {
                            downMessage = "We are unavailable to attend to you via this channel at this time on this week day ";
                            response.setMessage(downMessage);
                            return response;
                        }
                    }

                    if (customerMst_local != null && null != customerMst_local.getCustId() && scheduledCallMstId != 0) {
                        if (!indownTime && !inservicedownTime) {
                            CustomerDeviceDtl cdd = customerDeviceDtlService.findCustomerDeviceDtlByCustId(customerMst_local);
                            if (null != cdd) {
                                if (null != cdd.getId()) {
                                    cdd.setAudioVideo(1);

                                    customerDeviceDtlService.saveCustomerDeviceDtl(cdd, null);
                                }
                            }
                            //findNonTakenById
                            ScheduleCall scheduleCallLocal = callSchedulerService.findNonTakenById(scheduledCallMstId);
                            if (null == scheduleCallLocal) {
                                scheduleCallLocal = callSchedulerService.findAllNonTakenScheduleCallById(scheduledCallMstId);
                                if (null != scheduleCallLocal) {
                                    response.setMessage("This schedule call is already " + scheduleCallLocal.getExecuteStatus());
                                    return response;
                                }
                                response.setMessage("Please initiate a valid scchedule call");
                                return response;
                            }

                            scheduleCallLocal.setCustomerTime(CustomConvert.javaDateToTimeStamp(new Date()));
                            scheduleCallLocal = callSchedulerService.saveScheduleCall(scheduleCallLocal, null);

                            logger.info("scheduleCallLocal:" + scheduleCallLocal);
                            CategoryMst categoryMst = categoryMstService.findCategoryByCategoryCode("UGR");

                            LanguageMst languageMst = langMstService.findLanguageMstByLanguageCode("ENG");
                            ServiceMst serviceMst;

                            serviceMst = serviceMstService.findNonDeletedServiceMstById(scheduleCallLocal.getService());

                            logger.info("serviceMst:" + serviceMst);

                            CallMst local_callMst = new CallMst();
                            local_callMst.setCallStatus("Initialize");
                            local_callMst.setCallType("Schedule Call");
                            local_callMst.setDeviceBrand(null);
                            local_callMst.setDeviceOs(null);
                            local_callMst.setiMEIno(null);
                            local_callMst.setDeviceId(null);
                            local_callMst.setDeviceIp(null);
                            local_callMst.setStaticIp(null);
                            local_callMst.setDeviceName(null);
                            local_callMst.setCallLocation(location);
                            local_callMst.setCallLatitude(latitude);
                            local_callMst.setCallLongitude(longitude);

                            local_callMst.setCustomerId(customerMst_local);
                            local_callMst.setCustId(customerMst_local.getCustId());
                            local_callMst.setCallOption(scheduleCallLocal.getCallType());
                            local_callMst.setServiceId(serviceMst.getId());
                            local_callMst.setCategoryId(categoryMst.getId());
                            local_callMst.setLanguageId(languageMst.getId());
                            local_callMst.setCallMedium("WEB");
                            local_callMst.setActiveFlg(true);
                            local_callMst.setDeleteFlg(false);
                            local_callMst.setStartTime(CustomConvert.javaDateToTimeStamp(new Date(System.currentTimeMillis())));
                            local_callMst.setCustomerRequestTime(CustomConvert.javaDateToTimeStamp(new Date()));

                            Long number = (long) Math.floor(Math.random() * 9000000L) + 1000000L;
                            String room = customerMst_local.getCustId() + number;

                            local_callMst.setRoomName(room);

                            local_callMst = callMstService.saveCallMst(local_callMst);
                            CallMst callMst = callMstService.findCallMstById(local_callMst.getId());

                            logger.info("callMst:" + callMst);

                            scheduleCallLocal.setCallmstid(callMst.getId());
                            scheduleCallLocal = callSchedulerService.saveScheduleCall(scheduleCallLocal, null);

                            List<CustomerRmMap> mappedRMList = customerRMMapService.getRMMappedWithCustomer(customerMst_local.getId());
                            CustomerRmMap customerRmMap = null;
                            if (!mappedRMList.isEmpty()) {
                                customerRmMap = mappedRMList.get(0);
                            }
                            EmployeeMst employeeMstLocal = employeeMstService.findEmployeeMstById(customerRmMap.getRmId().getId());

                            if (null != employeeMstLocal) {
                                List<TenancyEmployeeMap> tenancyEmployeeMaplist = tenancyEmployeeMapService.findVidyoTenantUrlByEmpId(employeeMstLocal.getId());
                                List<Long> unsortList = new ArrayList<>();
                                for (TenancyEmployeeMap tenancyEmployeeMaplist1 : tenancyEmployeeMaplist) {
                                    unsortList.add(tenancyEmployeeMaplist1.getId());
                                }
                                Collections.sort(unsortList);
                                TenancyEmployeeMap tenancyEmployeeMap = tenancyEmployeeMapService.findById(unsortList.get(unsortList.size() - 1));
                                String roomLink = tenancyEmployeeMap.getRoomLink();
                                response.setRoomId(roomLink);
                                response.setAgentLoginId(employeeMstLocal.getLoginId());
                                response.setAgentName(employeeMstLocal.getFirstName() + " " + employeeMstLocal.getLastName());
                                CallEmployeeMap l_CallEmpMap = new CallEmployeeMap();
                                l_CallEmpMap.setCallId(callMst.getId());
                                l_CallEmpMap.setEmployeeId(employeeMstLocal.getId());
                                l_CallEmpMap.setCallType("ScheduleCall");
                                l_CallEmpMap.setScheduleCallId(scheduleCallLocal.getId());
                                l_CallEmpMap.setCustId(callMst.getCustId());
                                l_CallEmpMap.setRoomLink(roomLink);

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
                            response.setCallMstId(callMst.getId());

                            response.setRoomName(room);
                            response.setServiceName(serviceMst.getServiceName());
                            response.setCallType(scheduleCallLocal.getCallType());

                            return response;

                        }
                    }
                }

            }
            response.setMessage("Wrong Credential!");

        } catch (JSONException | NumberFormatException | ParseException e) {
            response.setMessage("There Some Error, Please Try Again.");
            logger.error(e);
        }
        return response;
    }

    @Override
    public List<ScheduleDetailsResponse> getScheduledCallDtlsByCustomerId(String callInfo) throws Exception {
        System.out.println("ddddddddddddddddddddddd"+callInfo);
        String customerLoginId;
        String timeZone;
        List<ScheduleCallDto> scheduledCallDtlDtoList;
        List<ScheduleDetailsResponse> scheduleDetailsResponseList = new ArrayList<>();
        if (callInfo != null && callInfo.length() > 0) {
            JSONObject callInformation = new JSONObject(callInfo);

            if (callInformation.has("customerLoginId")) {
                customerLoginId = callInformation.getString("customerLoginId");
                timeZone = callInformation.getString("timeZone");
                logger.info("with customerLoginId=" + customerLoginId);
                if (customerLoginId != null && !"".equals(customerLoginId)) {
                    CustomerMst customerMst = customerMstService.findCustomerMstByCustId(customerLoginId);
                    scheduledCallDtlDtoList = callSchedulerService.getScheduledCallDtlsByCustomerId(customerMst.getId());
                    if (null != scheduledCallDtlDtoList && !scheduledCallDtlDtoList.isEmpty()) {
                        for (ScheduleCallDto dto : scheduledCallDtlDtoList) {
                            ScheduleDetailsResponse detailsResponse = new ScheduleDetailsResponse();
                            detailsResponse.setCallType(dto.getCallType());
                            detailsResponse.setAgentName(dto.getFirstName() + " " + dto.getLastName());
                            final TimeZone timeZoneL = TimeZone.getDefault();

                            Date d = CustomConvert.convertScheduleTimeZoneF(dto.getScheduleEndDateTime() + "", timeZoneL.getID(), timeZone);

                            DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
                            DateFormat time = new SimpleDateFormat("HH:mm:ss");
                            detailsResponse.setEndDateText(date.format(d));
                            detailsResponse.setEndTimeText(time.format(d));
                            detailsResponse.setId(dto.getScheduleCallMstId());
                            ServiceMst serviceMst = servicesMstDao.findAllServiceMstById(dto.getService());
                            detailsResponse.setServiceName(serviceMst.getServiceName());
//                            d = f.parse(dto.getScheduleStartDateTime() + "");
                            d = CustomConvert.convertScheduleTimeZoneF(dto.getScheduleStartDateTime() + "", timeZoneL.getID(), timeZone);
                            date = new SimpleDateFormat("yyyy-MM-dd");
                            time = new SimpleDateFormat("HH:mm:ss");
                            detailsResponse.setStartDateText(date.format(d));
                            detailsResponse.setStartTimeText(time.format(d));
                            scheduleDetailsResponseList.add(detailsResponse);

                        }
                        return scheduleDetailsResponseList;
                    } else {
                        CCMSRestException ccmsRestException = new CCMSRestException();
                        ccmsRestException.setErrorCode("418");
                        ccmsRestException.setErrorMessage("No Schedule Call Found");
                        throw ccmsRestException;
                    }
                } else {
                    CCMSRestException ccmsRestException = new CCMSRestException();
                    ccmsRestException.setErrorCode("418");
                    ccmsRestException.setErrorMessage("Wrong Credential!");
                    throw ccmsRestException;
                }
            } else {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Wrong Credential!");
                throw ccmsRestException;
            }
        } else {
            CCMSRestException ccmsRestException = new CCMSRestException();
            ccmsRestException.setErrorCode("418");
            ccmsRestException.setErrorMessage("Wrong Credential!");
            throw ccmsRestException;
        }
    }

    @Override
    public List<ScheduleDetailsResponse> getRMScheduledCallDtlsByCustomerId(String callInfo) throws Exception {
        String customerLoginId;
        List<ScheduleCallDto> scheduledCallDtlDtoList;
        List<ScheduleDetailsResponse> scheduleDetailsResponseList = new ArrayList<>();
        if (callInfo != null && callInfo.length() > 0) {
            JSONObject callInformation = new JSONObject(callInfo);
            if (callInformation.has("customerLoginId")) {
                customerLoginId = callInformation.getString("customerLoginId");
                logger.info("with customerLoginId=" + customerLoginId);
                CustomerMst cm = customerMstService.findCustomerMstByCustId(customerLoginId);
                List<CustomerRmMap> mappedRMList = customerRMMapService.getRMMappedWithCustomer(cm.getId());
                CustomerRmMap customerRmMap = null;
                if (!mappedRMList.isEmpty()) {
                    customerRmMap = mappedRMList.get(0);
                }
                if (null != customerRmMap) {
                    EmployeeMst employeeMst = employeeMstService.findEmployeeMstById(customerRmMap.getRmId().getId());

                    if (employeeMst != null) {
                        scheduledCallDtlDtoList = callSchedulerService.getScheduledCallDtlsByRMId(employeeMst.getId());
                        if (null != scheduledCallDtlDtoList && !scheduledCallDtlDtoList.isEmpty()) {
                            for (ScheduleCallDto dto : scheduledCallDtlDtoList) {
                                ScheduleDetailsResponse detailsResponse = new ScheduleDetailsResponse();
                                detailsResponse.setCallType(dto.getCallType());
                                detailsResponse.setCustomerName(dto.getFirstName() + " " + dto.getLastName());
                                logger.info("dto.getScheduleEndDateTime():" + dto.getScheduleEndDateTime());
                                DateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                Date d = f.parse(dto.getScheduleEndDateTime() + "");
                                DateFormat date = new SimpleDateFormat("yyyy-MM-dd");
                                DateFormat time = new SimpleDateFormat("HH:mm:ss");
                                logger.info("Date: " + date.format(d));
                                logger.info("Time: " + time.format(d));
                                detailsResponse.setEndDateText(date.format(d));
                                detailsResponse.setEndTimeText(time.format(d));
                                detailsResponse.setId(dto.getScheduleCallMstId());
                                detailsResponse.setAgentLoginId(employeeMst.getLoginId());
                                detailsResponse.setAgentName(employeeMst.getFirstName() + " " + employeeMst.getLastName());
                                ServiceMst serviceMst = servicesMstDao.findAllServiceMstById(dto.getService());
                                detailsResponse.setServiceName(serviceMst.getServiceName());
                                d = f.parse(dto.getScheduleStartDateTime() + "");
                                date = new SimpleDateFormat("yyyy-MM-dd");
                                time = new SimpleDateFormat("HH:mm:ss");
                                logger.info("Date: " + date.format(d));
                                logger.info("Time: " + time.format(d));
                                detailsResponse.setStartDateText(date.format(d));
                                detailsResponse.setStartTimeText(time.format(d));
                                scheduleDetailsResponseList.add(detailsResponse);

                            }
                            return scheduleDetailsResponseList;
                        } else {
                            CCMSRestException ccmsRestException = new CCMSRestException();
                            ccmsRestException.setErrorCode("418");
                            ccmsRestException.setErrorMessage("No Schedule Call Found");
                            throw ccmsRestException;
                        }
                    } else {
                        CCMSRestException ccmsRestException = new CCMSRestException();
                        ccmsRestException.setErrorCode("418");
                        ccmsRestException.setErrorMessage("Wrong Credential!");
                        throw ccmsRestException;
                    }
                } else {
                    CCMSRestException ccmsRestException = new CCMSRestException();
                    ccmsRestException.setErrorCode("418");
                    ccmsRestException.setErrorMessage("Wrong Credential!");
                    throw ccmsRestException;
                }
            } else {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Wrong Credential!");
                throw ccmsRestException;
            }
        } else {
            CCMSRestException ccmsRestException = new CCMSRestException();
            ccmsRestException.setErrorCode("418");
            ccmsRestException.setErrorMessage("Wrong Credential!");
            throw ccmsRestException;
        }
    }

    @Override
    public ScheduleDetailsResponse cancelScheduleCallByCustomer(String callInfo) throws Exception {
        ScheduleDetailsResponse response = new ScheduleDetailsResponse();
        try {
            String scheduledCallMstId;
            if (callInfo != null && callInfo.length() > 0) {
                JSONObject callInformation = new JSONObject(callInfo);
                if (callInformation.has("scheduledCallMstId")) {
                    scheduledCallMstId = callInformation.getString("scheduledCallMstId");
                    logger.info("scheduledCallMstId:" + scheduledCallMstId);
                    ScheduleCall scheduleCallDtl = callSchedulerService.findAllNonTakenScheduleCallById(Long.parseLong(scheduledCallMstId));

                    scheduleCallDtl.setActiveFlg(false);
                    scheduleCallDtl.setDeleteFlg(true);
                    scheduleCallDtl.setExecuteStatus("Cancelled");

                    scheduleCallDtl = callSchedulerService.saveScheduleCall(scheduleCallDtl, null);

                    if (scheduleCallDtl != null) {
                        logger.info("success onCancelScheduleCall");
                        response.setMessage("Schedule Call Cancel Was Success");
                        return response;
                    } else {
                        logger.info("Not Found ScheduleCall");
                        response.setMessage("Not Found Schedule call.");
                    }
                } else {
                    response.setMessage("Wrong Credential!");

                }
            } else {
                response.setMessage("Wrong Credential!");

            }
        } catch (JSONException | NumberFormatException e) {
            logger.error(e);
            response.setMessage("Some Error Occured Please try again.");
        }
        return response;
    }

    @Override
    public ScheduleDetailsResponse rmInviteGuest(String callInfo) throws Exception {
        ScheduleDetailsResponse response = new ScheduleDetailsResponse();
        try {

            String callMstId;
            String guestEmail;
            String roomName;
            if (callInfo != null && callInfo.length() > 0) {
                JSONObject callInformation = new JSONObject(callInfo);
                if (callInformation.has("callMstId") && callInformation.has("guestEmail") && callInformation.has("roomName")) {
                    callMstId = callInformation.getString("callMstId");
                    guestEmail = callInformation.getString("guestEmail");
                    roomName = callInformation.getString("roomName");
                    logger.info("sendMultiWayCallEmailToGuest...." + guestEmail);
                    // HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();//
                    CallMst callMstLocal = callMstService.findCallMstById(Long.parseLong(callMstId));
                    String messageBody;
                    CustomerMst customerMst_local = null;
                    if (null != callMstLocal) {
                        customerMst_local = customerMstService.findCustomerMstByCustId(callMstLocal.getCustId());
                    }

                    logger.info("customerMst_local:" + customerMst_local + "callMstLocal:" + callMstLocal + "guestEmail:" + guestEmail);
                    if (null != guestEmail && !"".equals(guestEmail) && null != callMstLocal && null != customerMst_local) {
                        String toBeEncode = "callId=" + callMstLocal.getId() + "&resourceId=" + roomName;
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
                            response.setMessage("Mail sending was success");
                            return response;
//                         /   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Email Send Was Successful", "Send Successful!!"));//
                        } else {
                            response.setMessage("Mail sending Error");
                        }
                    } else {
                        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please Provide Correct Email", "Please Provide Correct Email"));//

                    }
                }
            }
            response.setMessage("Wrong Credential!");
        } catch (Exception e) {
            logger.error(e);
            response.setMessage("Some Error Occured Please try again.");
        }
        return response;
    }

    @Override
    public List<CustomerDto> getMappedCustomerList(String credential) throws Exception {

        EmployeeMst employeeMst;
        List<CustomerDto> customerMstList = new ArrayList<>();

        if (credential != null && credential.length() > 0) {
            JSONObject cred = new JSONObject(credential);
            String loginId = cred.getString("loginId");
            String loginPassword = cred.getString("password");

            if (loginId == null || loginId.isEmpty() || "".equals(loginId)) {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Please Provide loginId!");
                throw ccmsRestException;

            }

            if (loginPassword == null || loginPassword.isEmpty() || "".equals(loginPassword)) {
                CCMSRestException ccmsRestException = new CCMSRestException();
                ccmsRestException.setErrorCode("418");
                ccmsRestException.setErrorMessage("Please Provide password!");
                throw ccmsRestException;
            }

            employeeMst = employeeLoginService.checkLogin(loginId, loginPassword, "");

            List<CustomerRmMap> mappedList = customerRMMapService.getCustomersMappedWithEmployee(employeeMst.getId());

            for (CustomerRmMap map : mappedList) {
                CustomerDto cust = customerMstService.findCustomerById(map.getCustId().getId());
                customerMstList.add(cust);
            }

            return customerMstList;

        } else {
            CCMSRestException ccmsRestException = new CCMSRestException();
            ccmsRestException.setErrorCode("418");
            ccmsRestException.setErrorMessage("Please Provide valid login credential!");
            throw ccmsRestException;
        }

    }
}
