package com.rank.ccms.web;

import com.rank.ccms.dto.CallEmployeeMap;
import com.rank.ccms.entities.CallDtl;
import com.rank.ccms.entities.CallMst;
import com.rank.ccms.entities.EmployeeActivityDtl;
import com.rank.ccms.entities.EmployeeCallStatus;
import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.entities.EmployeeTypeMst;
import com.rank.ccms.entities.ForwardedCall;
import com.rank.ccms.entities.ReasonMst;
import com.rank.ccms.entities.TenancyEmployeeMap;
import com.rank.ccms.service.CallDtlService;
import com.rank.ccms.service.CallMstService;
import com.rank.ccms.service.EmployeeActivityDtlService;
import com.rank.ccms.service.EmployeeCallStatusService;
import com.rank.ccms.service.EmployeeLoginService;
import com.rank.ccms.service.EmployeeMstService;
import com.rank.ccms.service.EmployeeTypeMstService;
import com.rank.ccms.service.ForwardedCallService;
import com.rank.ccms.service.ReasonMstService;
import com.rank.ccms.service.TenancyEmployeeMapService;
import com.rank.ccms.util.CallScheduler;
import com.rank.ccms.util.Constants;
import com.rank.ccms.util.CustomConvert;
import com.rank.ccms.util.JavascriptCryptoDecryptor;
import com.rank.ccms.vidyo.util.VidyoAccessAdmin;
import com.rank.ccms.vidyo.util.VidyoAccessUser;
import java.io.File;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class EmployeeLoginComponent implements Serializable {

    Logger LOGGER = Logger.getLogger(this.getClass());

    private String userLoginId;
    private String userPassword;
    private String welcomeMessage;
    private boolean notAgent = true;
    private boolean adminmenuRenderer = true;
    private boolean supervisormenuRenderer = false;
    private EmployeeMst employeeMaster;
    private boolean logoutRenderer;
    private boolean reasonsRenderer;
    private Long selectedReasonId;
    private List<ReasonMst> reasonMstList;
    private Long employeeId;
    private String salt;
    private String four;
    private String timeZone;

    @Autowired
    private EmployeeActivityDtlService employeeActivityDtlService;
    @Autowired
    private EmployeeMstService employeeMstService;
    @Autowired
    private EmployeeLoginService employeeLoginService;
    @Autowired
    private EmployeeTypeMstService employeeTypeMstService;
    @Autowired
    private ReasonMstService reasonMstService;
    @Autowired
    private TenancyEmployeeMapService tenancyEmployeeMapService;
    @Autowired
    private VidyoAgent vidyoAgent;
    @Autowired
    private EmployeeCallStatusService employeeCallStatusService;
    @Autowired
    private ForwardedCallService forwardedCallService;
    @Autowired
    private CallMstService callMstService;
    @Autowired
    private CallDtlService callDtlService;
    @Autowired
    private CustomerDetailsComponent customerDetailsComponent;
    @Autowired
    ImageComponent imageComponent;

    public void showLoginReasons() {
        reasonsRenderer = true;
        reasonMstList = reasonMstService.findAllActivenNonDeletedReasonMsts("login");
    }

    public void showLogoutReasons() {
        reasonsRenderer = true;
        reasonMstList = reasonMstService.findAllActivenNonDeletedReasonMsts("logout");
        if (!reasonMstList.isEmpty()) {
            selectedReasonId = reasonMstList.get(0).getId();
        }
    }

    public void cancelReasonList() {
        reasonsRenderer = false;
    }

    public String handleLogin(HttpServletRequest request) {

        vidyoAgent.setUserName(userLoginId);
        vidyoAgent.setSocketHost(Constants.socketHostPublic);
        vidyoAgent.setIworkImage(false);
        imageComponent.setCustEmail("");
        imageComponent.setCustId("");
        imageComponent.setCustNationality("");
        imageComponent.setCustAcctNo("");
        imageComponent.setCustomerLocation("");
        imageComponent.setCustPhone("");

        request.getSession().setAttribute("socketHostPublic", Constants.socketHostPublic);

        String originalPass;
        try {
            originalPass = JavascriptCryptoDecryptor.decryptAESEncryptWithSaltAndIV(userPassword, request.getSession().getId(), salt, four);
            LOGGER.info("Original Password:" + originalPass);
            this.setUserPassword(originalPass);
        } catch (Exception ex) {
            LOGGER.error("Unable to decrypt: " + ex.getMessage());
        }
        employeeMaster = employeeLoginService.checkLogin(userLoginId, userPassword, request.getSession().getId());
        if (employeeMaster != null) {
            beforeEmployeeLoginCheck(employeeMaster);

            if (!CallScheduler.listCallEmp.isEmpty()) {
                for (CallEmployeeMap cem : CallScheduler.listCallEmp) {
                    if (employeeMaster != null && employeeMaster.getId() == cem.getEmployeeId().longValue()) {
                        CallScheduler.listCallEmp.remove(cem);
                        break;
                    }
                }
            }

            if (employeeMaster.getActiveFlg() == true) {
                EmployeeMst empMst = employeeMstService.findEmployeeMstById(employeeMaster.getId());
                request.getSession().setAttribute("ormUserMaster", empMst);

                EmployeeTypeMst employeeTypeAgentMst = employeeTypeMstService.findEmployeeTypeByEmployeeTypeName("agent");
                notAgent = false;

                if (null == employeeTypeAgentMst) {
                    LOGGER.info("L1 ~ Sorry! Employee Type of Name 'Agent' Not Found in the Database.");
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sorry! Employee Type of Name 'Agent' Not Found in the Database.", "Employee Type of Name 'Agent' Not Found in the Database."));
                    return "/loginEmployee.xhtml";
                } else if (null != employeeMaster.getEmpTypId().getId()) {
                    beforeEmployeeLoginCheck(employeeMaster);
                    // Employee Is Valid and Is Eligible to Login In
                    if (Objects.equals(employeeMaster.getEmpTypId().getId(), employeeTypeAgentMst.getId())) {
                        //First delete all the old Call Proficiencies from Database then insert again for fresh values
                        Integer lReturn = employeeLoginService.deleteAndRePopulateCallProficiencies(employeeMaster, employeeTypeAgentMst);
                        if (lReturn != 0) {
                            LOGGER.info("EmpId:" + employeeMaster.getId() + " LoginId:" + employeeMaster.getLoginId() + " is Available in the Agent Call Routing Process.");
                        } else {
                            LOGGER.info("L2 ~ EmpId:" + employeeMaster.getId() + " LoginId:" + employeeMaster.getLoginId() + " has incomplete Proficiencies. Thus not available in Agent Routing Process.");
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Not in Agent Routing. Contact your Supervisor.", "Your Call Proficiencies are incomplete. Thus not available in Call Routing Process."));
                            return "/loginEmployee.xhtml";
                        }
                    }
                } else {
                    LOGGER.info("L5 ~ Sorry! Employee Type Id Not found in the Database for EmpId:" + employeeMaster.getId() + " LoginId:" + employeeMaster.getLoginId());
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sorry! Employee Type Id Not found", "Employee Type Id Not found in the Database for EmpId:" + employeeMaster.getId() + " LoginId:" + employeeMaster.getLoginId()));
                    return "/loginEmployee.xhtml";
                }

                List<ReasonMst> lReasonMstList = reasonMstService.findAllActivenNonDeletedReasonMsts("login");
                ReasonMst reasonMst;
                if (null == lReasonMstList || lReasonMstList.isEmpty()) {
                    reasonMst = new ReasonMst((long) 0, "login", true, false);
                    reasonMst.setReasonCd("LN001");
                    reasonMst.setReasonDesc("Login First Time - Day Start.");
                } else {
                    reasonMst = lReasonMstList.get(0);
                }
                EmployeeActivityDtl employeeActivityDtl = new EmployeeActivityDtl();
                employeeActivityDtl.setEmpId(employeeMaster);
                employeeActivityDtl.setActivity("login");
                try {
                    employeeActivityDtl.setStartTime(CustomConvert.javaDateToTimeStamp(new Date()));
                } catch (ParseException e) {
                    LOGGER.info("Error:" + e.getMessage());
                }
                employeeActivityDtl.setReasonId(reasonMst);
                employeeActivityDtl.setReasonCd(reasonMst.getReasonCd());
                employeeActivityDtl.setReasonDesc(reasonMst.getReasonDesc());
                employeeActivityDtl = employeeActivityDtlService.save(employeeActivityDtl, empMst);

                request.getSession().setAttribute("currentEmpLoginId", employeeActivityDtl);

                if (null == employeeActivityDtl) {
                    LOGGER.info("L4 ~ Sorry! Unable to add activity details for EmpId:" + employeeMaster.getId() + " LoginId:" + employeeMaster.getLoginId());
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Failed! Activity Not Populated.", "Failed! Unable to add activity details."));
                    return "/loginEmployee.xhtml";
                } else {

                    request.getSession().setAttribute("userFNameLName", ((employeeMaster.getFirstName() == null || employeeMaster.getFirstName().trim().length() <= 0) ? "User" : (employeeMaster.getFirstName() + ((employeeMaster.getLastName() == null || employeeMaster.getLastName().trim().length() <= 0) ? "" : ((employeeMaster.getMidName() == null || employeeMaster.getMidName().trim().length() <= 0) ? "" : " " + employeeMaster.getMidName()) + " " + employeeMaster.getLastName()))));
                    request.getSession().setAttribute("loginstatus", "true");

                    String entityId;

                    if (employeeMaster.getEmpTypId().getTypeName().equalsIgnoreCase("SuperAdmin")) {
                        welcomeMessage = "Welcome to the Video Admin Console";
                        request.getSession().setAttribute("ormAdminMaster", employeeMaster);
                        logoutRenderer = true;
                        notAgent = true;
                        return "/pages/employee/dashboard.xhtml?faces-redirect=true";
                    } else if (employeeMaster.getEmpTypId().getTypeName().equalsIgnoreCase("Admin")) {
                        welcomeMessage = "Welcome to the Video Admin Console";
                        request.getSession().setAttribute("ormAdminMaster", employeeMaster);
                        logoutRenderer = true;
                        notAgent = true;
                        return "/pages/employee/dashboard.xhtml?faces-redirect=true";
                    } else if (employeeMaster.getEmpTypId().getTypeName().equalsIgnoreCase("Supervisor") ) {
                        welcomeMessage = "WelCome to the CCMS Supervisor Console";
                        request.getSession().setAttribute("ormAdminMaster", employeeMaster);
                        logoutRenderer = true;
                        notAgent = true;
                        supervisormenuRenderer = true;
                        adminmenuRenderer = false;
                        return "/pages/employee/supervisorDashboard.xhtml?faces-redirect=true";
                    } else {
                        request.getSession().setAttribute("ormAgentMaster", employeeMaster);
                        try {
                            vidyoAgent.setHangupenabled(true);
                            vidyoAgent.setAvailableenabled(true);
                            vidyoAgent.setDialenabled(true);
                            vidyoAgent.setHoldenabled(true);
                            vidyoAgent.setThreewayenabled(true);
                            vidyoAgent.setForwardenabled(true);
                            vidyoAgent.setNotreadyenabled(true);
                            vidyoAgent.setLogoutenabled(false);
                            vidyoAgent.setDashboardCheck(false);
                            vidyoAgent.setSetupCheck(true);
                            vidyoAgent.setAgentStatus("Ready");
                            vidyoAgent.setAgentColor("");
                            vidyoAgent.setIsRm(false);

                            vidyoAgent.setPortal(Constants.vidyoportal);
                            vidyoAgent.setVidyoUserid(Constants.adminUserId);
                            vidyoAgent.setPassword(Constants.adminPwd);
                            TenancyEmployeeMap tenancyEmployeeMap = new TenancyEmployeeMap();
                            tenancyEmployeeMap.setEmpId(employeeMaster);
                            Long number1 = (long) Math.floor(Math.random() * 9000000L) + 1000000L;
                            String room1 = userLoginId + number1;
                            vidyoAgent.setRoomName(room1);
                            VidyoAccessUser vidyoAccessUser = new VidyoAccessUser(Constants.vidyoportalUserServiceWSDL);
                            String ret = vidyoAccessUser.createRoom(Constants.adminUserId, Constants.adminPwd, room1, Constants.vidyoportalUserServiceWSDL);
                            String roomlink = ret.split(",")[0];
                            entityId = ret.split(",")[1];
//                            String roomname = ret.split(",")[2];
                            tenancyEmployeeMap.setRoomLink(roomlink);
                            tenancyEmployeeMap.setEntityId(entityId);
                            tenancyEmployeeMap.setRoomName(room1);
                            vidyoAgent.setRoomUrl(roomlink.substring(roomlink.lastIndexOf("/") + 1));

                            vidyoAgent.setDisplayName((employeeMaster.getFirstName() == null || employeeMaster.getFirstName().trim().length() <= 0) ? "User" : (employeeMaster.getFirstName() + ((employeeMaster.getLastName() == null || employeeMaster.getLastName().trim().length() <= 0) ? "" : ((employeeMaster.getMidName() == null || employeeMaster.getMidName().trim().length() <= 0) ? "" : " " + employeeMaster.getMidName()) + " " + employeeMaster.getLastName())));
                            vidyoAgent.setAgentName((employeeMaster.getFirstName() == null || employeeMaster.getFirstName().trim().length() <= 0) ? "User" : (employeeMaster.getFirstName() + ((employeeMaster.getLastName() == null || employeeMaster.getLastName().trim().length() <= 0) ? "" : ((employeeMaster.getMidName() == null || employeeMaster.getMidName().trim().length() <= 0) ? "" : " " + employeeMaster.getMidName()) + " " + employeeMaster.getLastName())) + "~" + this.userLoginId);

                            tenancyEmployeeMapService.saveTenancyEmployeeMap(tenancyEmployeeMap);
                            request.getSession().setAttribute("tenancyEmployeeMap", tenancyEmployeeMap);

                            List<ForwardedCall> forwardedCallList = forwardedCallService.findActiveForwardedCallByEmployeeMstList(employeeMaster);
                            for (ForwardedCall forwardedCall : forwardedCallList) {
                                forwardedCall.setForwardStatus("NotPicked");
                                forwardedCallService.saveForwardedCalls(forwardedCall, employeeMaster);
                            }

                            List<TenancyEmployeeMap> tenancyEmployeeMaplist = tenancyEmployeeMapService.findVidyoTenantUrlByEmpId(employeeMaster.getId());
                            request.getSession().setAttribute("logincount", tenancyEmployeeMaplist.size());
                            logoutRenderer = false;

                            employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(employeeMaster.getId(), "NEXT CALL");
                            if (employeeActivityDtl != null) {
                                employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                                employeeActivityDtlService.save(employeeActivityDtl);
                            }

                            EmployeeCallStatus empCallStatus = new EmployeeCallStatus();
                            List<EmployeeCallStatus> empClStatusList = employeeCallStatusService.findEmployeeCallStatusByEmpId(employeeMaster);

                            if (!empClStatusList.isEmpty()) {
                                for (EmployeeCallStatus empstatus : empClStatusList) {
                                    empCallStatus = empstatus;
                                }

                                empCallStatus.setStatus(false);
                                empCallStatus.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                                employeeCallStatusService.saveEmployeeCallStatus(empCallStatus);
                            }

                        } catch (Exception e) {
                            LOGGER.info("error in createRoom " + e.getMessage());
                            FacesContext.getCurrentInstance().addMessage(null,
                                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sorry! Unable to get into Vidyo Portal", ""));

                            EmployeeActivityDtl activityDtl_local = employeeActivityDtlService.findLastNonEndedActivityByType(employeeMaster.getId(), "login");
                            if (activityDtl_local != null) {
                                try {
                                    activityDtl_local.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                                } catch (Exception ex) {
                                    LOGGER.error(ex.getMessage());
                                }
                                employeeActivityDtlService.save(activityDtl_local);
                            }

                            request.getSession().setAttribute("ormAgentMaster", null);
                            request.getSession().setAttribute("ormAdminMaster", null);
                            request.getSession().setAttribute("ormSupervisorMaster", null);
                            request.getSession().setAttribute("ormUserMaster", null);

                            request.getSession().setAttribute("callMst", null);
                            request.getSession().setAttribute("loginstatus", "false");
                            request.getSession().setAttribute("userFNameLName", "");
                            request.getSession().setAttribute("lastLoginTime", "");
                            RequestContext.getCurrentInstance().execute("$('#spinner').hide();");
                            return "/loginEmployee.xhtml";
                        }
                    }

                    if (employeeMaster.getEmpTypId().getTypeName().equalsIgnoreCase("ScheduleAgent")) {
                        try {

                            vidyoAgent.setScheduleAgentName((employeeMaster.getFirstName() == null || employeeMaster.getFirstName().trim().length() <= 0) ? "User" : (employeeMaster.getFirstName() + ((employeeMaster.getLastName() == null || employeeMaster.getLastName().trim().length() <= 0) ? "" : ((employeeMaster.getMidName() == null || employeeMaster.getMidName().trim().length() <= 0) ? "" : " " + employeeMaster.getMidName()) + " " + employeeMaster.getLastName())) + "~" + this.userLoginId);

                            request.getSession().setAttribute("currentEmpLoginId", employeeActivityDtl);

                            List<CallMst> listOpenCalls = callMstService.findAllOnGoingCalls();
                            for (CallMst cmst : listOpenCalls) {
                                List<CallDtl> listcalldtl = callDtlService.findCallDtlByCallMasterId(cmst.getId());
                                if (!listcalldtl.isEmpty()) {
                                    CallDtl callDtl = listcalldtl.get(0);
                                    if (callDtl.getHandeledById().getId().equals(empMst.getId())) {
                                        cmst.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                                        cmst = callMstService.saveCallMst(cmst);
                                    }
                                }
                            }

                            List<TenancyEmployeeMap> tenancyEmployeeMaplist = tenancyEmployeeMapService.findVidyoTenantUrlByEmpId(employeeMaster.getId());
                            request.getSession().setAttribute("logincount", tenancyEmployeeMaplist.size());
                            logoutRenderer = false;
                            return "/pages/agent/scheduledagentDashboard.xhtml?faces-redirect=true";
                        } catch (ParseException e) {
                            FacesContext.getCurrentInstance().addMessage(null,
                                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sorry! Unable to get into Vidyo Portal", ""));

                            request.getSession().setAttribute("ormAgentMaster", null);
                            request.getSession().setAttribute("ormAdminMaster", null);
                            return "/loginEmployee.xhtml";
                        }

                    } else if (employeeMaster.getEmpTypId().getTypeName().equalsIgnoreCase("Specialist")) {

                        return "/pages/agent/specialistDashboard.xhtml?faces-redirect=true";

                    } else if (employeeMaster.getEmpTypId().getTypeName().equalsIgnoreCase("RelationshipManager")) {
                        vidyoAgent.setIsRm(true);
                        return "/pages/agent/agentDashboard.xhtml?faces-redirect=true";

                    } else if (employeeMaster.getEmpTypId().getTypeName().equalsIgnoreCase("SeniorRelationshipManager")) {

                        return "/pages/agent/agentDashboard.xhtml?faces-redirect=true";

                    } else if (employeeMaster.getEmpTypId().getTypeName().equalsIgnoreCase("BranchManager")) {

                        return "/pages/agent/agentDashboard.xhtml?faces-redirect=true";

                    } else {

                        return "/pages/agent/agentDashboard.xhtml?faces-redirect=true";

                    }
                }
            } else {
                LOGGER.info("L6 ~ Sorry! You have been Deactivated.");
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sorry! You have been Deactivated. Please contact Administrator", "Failed! User has been Deactivated."));
                return "/loginEmployee.xhtml";
            }
        } else {
            LOGGER.info("L7 ~ Failed! Wrong Login Credentials.");
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wrong Login Credentials.", "Failed! Wrong Login Credentials."));
            return "/loginEmployee.xhtml";
        }

    }

    public void browserClosed() {
        LOGGER.info("Inside Browser Closed....");
        HttpSession session = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession();
        EmployeeMst empMaster = (EmployeeMst) session.getAttribute("ormUserMaster");
        beforeEmployeeLoginCheck(empMaster);
        sessionhandleLogout((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest());
        LOGGER.info("End Browser Closed....");

    }

    public void beforeEmployeeLoginCheck(EmployeeMst emst) {

        if (emst != null) {
            try {

                Date date = new Date();

                EmployeeActivityDtl employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(employeeMaster.getId(), "login");
                Long resLoginId = Long.parseLong("0");
                ReasonMst reasonMst = reasonMstService.findReasonMstByReasonCode("SOUT");
                if (null != employeeActivityDtl) {
                    resLoginId = employeeActivityDtl.getId();
                    employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(date));
                    employeeActivityDtlService.save(employeeActivityDtl, emst);
                }

                employeeActivityDtl = new EmployeeActivityDtl();
                employeeActivityDtl.setEmpId(emst);
                employeeActivityDtl.setActivity("logout");
                employeeActivityDtl.setStartTime(CustomConvert.javaDateToTimeStamp(date));
                employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(date));
                employeeActivityDtl.setRespectiveLoginId(resLoginId);
                employeeActivityDtl.setReasonId(reasonMst);
                employeeActivityDtl.setReasonCd(reasonMst.getReasonCd());
                employeeActivityDtl.setReasonDesc(reasonMst.getReasonDesc());

                employeeActivityDtl = employeeActivityDtlService.save(employeeActivityDtl, emst);

                if (null != employeeActivityDtl) {
                    employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(date));
                    employeeActivityDtl.setNotification(null);
                    employeeActivityDtlService.save(employeeActivityDtl, emst);

                }

                employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(emst.getId(), "Self View");
                if (employeeActivityDtl != null) {
                    employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                    employeeActivityDtlService.save(employeeActivityDtl);
                }

                employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(emst.getId(), "not ready");
                if (employeeActivityDtl != null) {
                    employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(date));
                    employeeActivityDtlService.save(employeeActivityDtl);
                }

                employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(emst.getId(), "NEXT CALL");
                if (employeeActivityDtl != null) {
                    employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(date));
                    employeeActivityDtlService.save(employeeActivityDtl);

                }

                employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(emst.getId(), "Call Started");
                if (employeeActivityDtl != null) {
                    employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(date));
                    employeeActivityDtlService.save(employeeActivityDtl);

                }

                employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(emst.getId(), "Hang Up");
                if (employeeActivityDtl != null) {
                    employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(date));
                    employeeActivityDtlService.save(employeeActivityDtl);

                }

                // Update the Call Status to Zero
                List<EmployeeCallStatus> empCallStatusList = employeeCallStatusService.findEmployeeCallStatusByEmpId(emst);
                for (EmployeeCallStatus callStatus : empCallStatusList) {
                    //callStatus.setCallCount(0);
                    callStatus.setStatus(false);
                    employeeCallStatusService.saveEmployeeCallStatus(callStatus);
                }

                //Update the Foward Call Active Flag to Zero
                List<ForwardedCall> forwardedCallList = forwardedCallService.findActiveForwardedCallByEmployeeMstList(emst);
                for (ForwardedCall forwardedCall : forwardedCallList) {
                    forwardedCall.setActiveFlg(false);
                    forwardedCall.setDeleteFlg(true);
                    forwardedCallService.saveForwardedCalls(forwardedCall, emst);
                }

                LOGGER.info(
                        ">>>>Logout Successfully>>> Employee UserId: " + emst.getLoginId()
                        + " Employee Name:" + emst.getFirstName() + " " + employeeMaster.getMidName() + " " + emst.getLastName() + ".");

            } catch (ParseException ex) {

                LOGGER.error("Deletion of User Connection Data Failed, though Logging Out. ", ex);
            }
        } else {
            LOGGER.debug(">>>>Logout Successfully, but NO User Details found in this session, OR is requesting again after Logout.");
        }
    }

    public String sessionhandleLogout(HttpServletRequest request) {

        LOGGER.info("Inside sessionhandleLogout......");
        HttpSession session = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession();
        CallMst cm = (CallMst) session.getAttribute("callMst");

        employeeMaster = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
        LOGGER.info("CallMst====>" + cm);

        if (cm != null) {
            cm = callMstService.findCallMstById(cm.getId());
            List<EmployeeCallStatus> employeeCallStatusList = employeeCallStatusService.findEmployeeCallStatusByEmpId(employeeMaster);
            for (EmployeeCallStatus employeeCallStatusList1 : employeeCallStatusList) {
                employeeCallStatusList1.setStatus(false);
                employeeCallStatusService.saveEmployeeCallStatus(employeeCallStatusList1);
            }
            try {
                cm.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                callMstService.saveCallMst(cm);
            } catch (ParseException ex) {
                java.util.logging.Logger.getLogger(EmployeeLoginComponent.class.getName()).log(Level.SEVERE, null, ex);
            }

            vidyoAgent.showhangUpReasons(request);
            vidyoAgent.setHangupcalled(false);
            vidyoAgent.sendHangUpMessage(request);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                java.util.logging.Logger.getLogger(EmployeeLoginComponent.class.getName()).log(Level.SEVERE, null, ex);
            }
            RequestContext.getCurrentInstance().execute("chatSave();");

        }
        vidyoAgent.setDashboardCheck(false);
        vidyoAgent.setSetupCheck(true);
        session.setAttribute("callMstChat", cm);
        session.setAttribute("ormAgentMaster", null);
        session.setAttribute("ormAdminMaster", null);
        session.setAttribute("ormUserMaster", null);

        session.setAttribute("callMst", null);
        session.setAttribute("loginstatus", "false");
        session.setAttribute("userFNameLName", "");

        employeeMaster = null;
        reasonsRenderer = false;
        vidyoAgent.setHangupcalled(true);

        return "/loginEmployee.xhtml?faces-redirect=true";
    }

    public String sessionhandleLogoutSpecialist(HttpServletRequest request) {

        LOGGER.info("Inside sessionhandleLogoutSpecialist......");
        HttpSession session = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession();
        CallMst cm = (CallMst) session.getAttribute("callMst");
        LOGGER.info("CallMst====>" + cm);

        if (cm != null) {
            vidyoAgent.showhangUpReasons(request);
            vidyoAgent.setHangupcalled(false);
        }
        vidyoAgent.setDashboardCheck(false);
        vidyoAgent.setSetupCheck(true);

        session.setAttribute("ormAgentMaster", null);
        session.setAttribute("ormAdminMaster", null);
        session.setAttribute("ormUserMaster", null);

        session.setAttribute("callMst", null);
        session.setAttribute("loginstatus", "false");
        session.setAttribute("userFNameLName", "");

        employeeMaster = null;
        reasonsRenderer = false;
        vidyoAgent.setHangupcalled(true);

        return "/loginEmployee.xhtml?faces-redirect=true";
    }

    public String handleLogout(HttpServletRequest request) {

        employeeMaster = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
        ReasonMst reasonMst = reasonMstService.findNonDeletedById(selectedReasonId);
        if (null != employeeMaster) {
            VidyoAccessAdmin vidyoAccessAdmin = new VidyoAccessAdmin(Constants.vidyoportalAdminServiceWSDL);

            if (null != employeeMaster.getLoginId()) {
                try {
                    vidyoAccessAdmin.searchRoomsAndDelete(Constants.adminUserId, Constants.adminPwd, employeeMaster.getLoginId(), Constants.vidyoportalAdminServiceWSDL);
                } catch (Exception ex) {
                    java.util.logging.Logger.getLogger(EmployeeLoginComponent.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            List<EmployeeCallStatus> employeeCallStatusList = employeeCallStatusService.findEmployeeCallStatusByEmpId(employeeMaster);
            for (EmployeeCallStatus employeeCallStatusList1 : employeeCallStatusList) {
                employeeCallStatusList1.setStatus(false);
                employeeCallStatusService.saveEmployeeCallStatus(employeeCallStatusList1);
            }
            try {

                Date date = new Date();
                EmployeeActivityDtl employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(employeeMaster.getId(), "login");
                Long resLoginId = Long.parseLong("0");
                if (null != employeeActivityDtl) {
                    resLoginId = employeeActivityDtl.getId();
                    employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(date));
                    employeeActivityDtlService.save(employeeActivityDtl, employeeMaster);
                }

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
                List<TenancyEmployeeMap> tenancyEmployeeMaplst = tenancyEmployeeMapService.findVidyoTenantUrlByEmpId(employeeMaster.getId());
                for (TenancyEmployeeMap tenancyEmployeeMaplst1 : tenancyEmployeeMaplst) {
                    tenancyEmployeeMapService.deleteTenancyEmployeeMap(tenancyEmployeeMaplst1);
                }

                LOGGER.info(
                        ">>>>Logout Successfully>>> Employee UserId: " + employeeMaster.getLoginId()
                        + " Employee Name:" + employeeMaster.getFirstName() + " " + employeeMaster.getMidName() + " " + employeeMaster.getLastName() + ".");

            } catch (ParseException ex) {
                RequestContext.getCurrentInstance().execute(" $('#spinner').hide();");

                LOGGER.error("Deletion of User Connection Data Failed, though Logging Out. ", ex);
            }
        } else {
            LOGGER.debug(">>>>Logout Successfully, but NO User Details found in this session, OR is requesting again after Logout.");
        }
        request.getSession().invalidate();
        request.getSession(true);
        request.getSession().setAttribute("callMst", null);
        request.getSession().setAttribute("loginstatus", "false");
        request.getSession().setAttribute("userFNameLName", "");
        request.getSession().setAttribute("ormAgentMaster", null);
        request.getSession().setAttribute("ormAdminMaster", null);
        request.getSession().setAttribute("ormUserMaster", null);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Logged Out!", "User Logged Out Successfully."));
        RequestContext.getCurrentInstance().execute(" $('#spinner').hide();");
        employeeMaster = null;
        return "/loginEmployee.xhtml?faces-redirect=true";
    }

    public String handleAbnormalLogout() {

        ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpSession session = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession(false);

        employeeMaster = (EmployeeMst) session.getAttribute("ormUserMaster");
        String realPath = extContext.getRealPath(File.separator);
        if (employeeMaster != null) {
            List<EmployeeCallStatus> employeeCallStatusList = employeeCallStatusService.findEmployeeCallStatusByEmpId(employeeMaster);
            for (EmployeeCallStatus employeeCallStatusList1 : employeeCallStatusList) {
                employeeCallStatusList1.setStatus(false);
                employeeCallStatusService.saveEmployeeCallStatus(employeeCallStatusList1);
            }

            File file = new File(realPath + "resources" + File.separator + "images" + File.separator + employeeMaster.getLoginId());
            LOGGER.info("current directory " + file.getAbsolutePath());
            try {

                Date date = new Date();
                // Update the Latest Login Status of the Employee
                EmployeeMst emst = (EmployeeMst) session.getAttribute("ormUserMaster");

                Long resLoginId = Long.parseLong("0");

                EmployeeActivityDtl employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(employeeMaster.getId(), "login");
                if (null != employeeActivityDtl) {
                    resLoginId = employeeActivityDtl.getId();
                    employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(date));
                    employeeActivityDtlService.save(employeeActivityDtl, emst);

                }

                employeeActivityDtl = new EmployeeActivityDtl();
                employeeActivityDtl.setEmpId(employeeMaster);
                employeeActivityDtl.setActivity("logout");
                employeeActivityDtl.setStartTime(CustomConvert.javaDateToTimeStamp(date));
                employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(date));
                employeeActivityDtl.setRespectiveLoginId(resLoginId);
                ReasonMst reason = reasonMstService.findReasonMstByReasonCode("SOUT");
                employeeActivityDtl.setReasonId(reason);
                employeeActivityDtl.setReasonCd(reason.getReasonCd());
                employeeActivityDtl.setReasonDesc("Session time over, idle timeout.");

                LOGGER.info("Logout from EmployeeLoginComponent.handleAbnormalLogout().");
                employeeActivityDtlService.save(employeeActivityDtl, emst);

                //TODO: If Activity Details Failed
                employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(emst.getId(), "Self View");
                if (employeeActivityDtl != null) {
                    employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                    employeeActivityDtlService.save(employeeActivityDtl);

                }

                employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(emst.getId(), "not ready");
                if (employeeActivityDtl != null) {
                    employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(date));
                    employeeActivityDtlService.save(employeeActivityDtl);

                }

                employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(emst.getId(), "NEXT CALL");
                if (employeeActivityDtl != null) {
                    employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(date));
                    employeeActivityDtlService.save(employeeActivityDtl);

                }

                employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(emst.getId(), "Call Started");
                if (employeeActivityDtl != null) {
                    employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(date));
                    employeeActivityDtlService.save(employeeActivityDtl);

                }

                employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(emst.getId(), "Hang Up");
                if (employeeActivityDtl != null) {
                    employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(date));
                    employeeActivityDtlService.save(employeeActivityDtl);

                }

                // Update the Call Status to Zero
                List<EmployeeCallStatus> empCallStatusList = employeeCallStatusService.findEmployeeCallStatusByEmpId(emst);
                for (EmployeeCallStatus callStatus : empCallStatusList) {
                    callStatus.setStatus(false);
                    employeeCallStatusService.saveEmployeeCallStatus(callStatus);
                }

                //Update the Foward Call Active Flag to Zero
                List<ForwardedCall> forwardedCallList = forwardedCallService.findActiveForwardedCallByEmployeeMstList(emst);
                for (ForwardedCall forwardedCall : forwardedCallList) {
                    forwardedCall.setActiveFlg(false);
                    forwardedCall.setDeleteFlg(true);
                    forwardedCallService.saveForwardedCalls(forwardedCall, emst);
                }

                //FileUtils.cleanDirectory(file);
                EmployeeTypeMst employeeTypeSuperAdminMst = employeeTypeMstService.findEmployeeTypeByEmployeeTypeName("SuperAdmin");

                EmployeeTypeMst employeeTypeAdministratorMst = employeeTypeMstService.findEmployeeTypeByEmployeeTypeName("Admin");

                if (null == employeeTypeAdministratorMst || null == employeeTypeSuperAdminMst) {
                    LOGGER.error("Deletion of Video User Details Data Failed, due to unavailability of Employee Type 'SuperAdmin' or 'Admin'");
                } else if (Objects.equals(employeeMaster.getEmpTypId().getId(), employeeTypeSuperAdminMst.getId())
                        || Objects.equals(employeeMaster.getEmpTypId().getId(), employeeTypeAdministratorMst.getId())) {
                    LOGGER.info("Employee Type SuperAdministrator and Administrator do not have any Tenency Details to be Deleted for Emp Id:" + employeeMaster.getId());
                } else {
                    List<TenancyEmployeeMap> tenancyEmployeeMaplist = tenancyEmployeeMapService.findVidyoTenantUrlByEmpId(employeeMaster.getId());
                    if (!tenancyEmployeeMaplist.isEmpty()) {
                        List<Long> unsortList = new ArrayList<>();
                        for (TenancyEmployeeMap tenancyEmployeeMaplist1 : tenancyEmployeeMaplist) {
                            unsortList.add(tenancyEmployeeMaplist1.getId());
                        }

                        Collections.sort(unsortList);

//                            tenancyEmployeeMap = tenancyEmployeeMapService.findById(unsortList.get(unsortList.size() - 1));
                        List<TenancyEmployeeMap> tenancyEmployeeMaplst = tenancyEmployeeMapService.findVidyoTenantUrlByEmpId(employeeMaster.getId());
                        for (TenancyEmployeeMap tenancyEmployeeMaplst1 : tenancyEmployeeMaplst) {
                            tenancyEmployeeMapService.deleteTenancyEmployeeMap(tenancyEmployeeMaplst1);
                        }

                    }
                }
                LOGGER.info(
                        ">>>>Logout Successfully>>> Employee UserId: " + employeeMaster.getLoginId()
                        + " Employee Name:" + employeeMaster.getFirstName() + " " + employeeMaster.getMidName() + " " + employeeMaster.getLastName() + ".");

            } catch (ParseException ex) {

                LOGGER.error("Deletion of User Connection Data Failed, though Logging Out. ", ex);
            }
        } else {
            LOGGER.debug(">>>>Logout Successfully, but NO User Details found in this session, OR is requesting again after Logout.");
        }
        session.setAttribute("ormAgentMaster", null);
        session.setAttribute("ormAdminMaster", null);
        session.setAttribute("ormUserMaster", null);

        session.setAttribute("callMst", null);
        session.setAttribute("loginstatus", "false");
        session.setAttribute("userFNameLName", "");

        employeeMaster = null;
        reasonsRenderer = false;
        return "/loginEmployee.xhtml?faces-redirect=true";
    }

    public String leave() {
        LOGGER.info("leave method called");
        return "/loginEmployee.xhtml?faces-redirect=true";
    }

    public void gotoCustomerList(HttpServletRequest request) {
        vidyoAgent.setSocketHost(Constants.socketHostPublic);
        request.getSession().setAttribute("socketHostPublic", Constants.socketHostPublic);

        LOGGER.info(this.employeeId);
        employeeMaster = employeeMstService.findEmployeeMstById(employeeId);
        if (null != employeeMaster) {
            request.getSession().setAttribute("userFNameLName", ((employeeMaster.getFirstName() == null || employeeMaster.getFirstName().trim().length() <= 0) ? "User" : (employeeMaster.getFirstName() + ((employeeMaster.getLastName() == null || employeeMaster.getLastName().trim().length() <= 0) ? "" : ((employeeMaster.getMidName() == null || employeeMaster.getMidName().trim().length() <= 0) ? "" : " " + employeeMaster.getMidName()) + " " + employeeMaster.getLastName()))));
            request.getSession().setAttribute("loginstatus", "true");
            welcomeMessage = "WelCome to the CCMS Supervisor Console";
            logoutRenderer = true;
            notAgent = true;
            supervisormenuRenderer = true;
            adminmenuRenderer = false;
            request.getSession().setAttribute("ormAdminMaster", employeeMaster);
            request.getSession().setAttribute("ormUserMaster", employeeMaster);
            customerDetailsComponent.listCustomerAccountDetails();
            // ===========> Insert the Activity Detail ===============
            List<ReasonMst> lReasonMstList = reasonMstService.findAllActivenNonDeletedReasonMsts("login");
            ReasonMst reasonMst;
            if (null == lReasonMstList || lReasonMstList.isEmpty()) {
                reasonMst = new ReasonMst((long) 0, "login", true, false);
                reasonMst.setReasonCd("LN001");
                reasonMst.setReasonDesc("Login First Time - Day Start.");
            } else {
                reasonMst = lReasonMstList.get(0);
            }
            EmployeeActivityDtl employeeActivityDtl = new EmployeeActivityDtl();
            employeeActivityDtl.setEmpId(employeeMaster);
            employeeActivityDtl.setActivity("login");
            try {
                employeeActivityDtl.setStartTime(CustomConvert.javaDateToTimeStamp(new Date()));
            } catch (ParseException e) {
                LOGGER.info("Error:" + e.getMessage());
            }
            employeeActivityDtl.setReasonId(reasonMst);
            employeeActivityDtl.setReasonCd(reasonMst.getReasonCd());
            employeeActivityDtl.setReasonDesc(reasonMst.getReasonDesc());
            employeeActivityDtl = employeeActivityDtlService.save(employeeActivityDtl, employeeMaster);
            request.getSession().setAttribute("currentEmpLoginId", employeeActivityDtl);

            RequestContext.getCurrentInstance().execute("redirect();");
        }

    }

    public String getUserLoginId() {
        return userLoginId;
    }

    public void setUserLoginId(String userLoginId) {
        this.userLoginId = userLoginId;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    public void setWelcomeMessage(String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }

    public boolean isNotAgent() {
        return notAgent;
    }

    public void setNotAgent(boolean notAgent) {
        this.notAgent = notAgent;
    }

    public boolean getAdminmenuRenderer() {
        return adminmenuRenderer;
    }

    public void setAdminmenuRenderer(boolean adminmenuRenderer) {
        this.adminmenuRenderer = adminmenuRenderer;
    }

    public boolean getSupervisormenuRenderer() {
        return supervisormenuRenderer;
    }

    public void setSupervisormenuRenderer(boolean supervisormenuRenderer) {
        this.supervisormenuRenderer = supervisormenuRenderer;
    }

    public EmployeeMst getEmployeeMaster() {
        return employeeMaster;
    }

    public void setEmployeeMaster(EmployeeMst employeeMaster) {
        this.employeeMaster = employeeMaster;
    }

    public boolean getLogoutRenderer() {
        return logoutRenderer;
    }

    public void setLogoutRenderer(boolean logoutRenderer) {
        this.logoutRenderer = logoutRenderer;
    }

    public boolean getReasonsRenderer() {
        return reasonsRenderer;
    }

    public void setReasonsRenderer(boolean reasonsRenderer) {
        this.reasonsRenderer = reasonsRenderer;
    }

    public Long getSelectedReasonId() {
        return selectedReasonId;
    }

    public void setSelectedReasonId(Long selectedReasonId) {
        this.selectedReasonId = selectedReasonId;
    }

    public List<ReasonMst> getReasonMstList() {
        return reasonMstList;
    }

    public void setReasonMstList(List<ReasonMst> reasonMstList) {
        this.reasonMstList = reasonMstList;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getFour() {
        return four;
    }

    public void setFour(String four) {
        this.four = four;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

}
