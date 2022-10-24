package com.rank.ccms.web;

import com.rank.ccms.dto.CallEmployeeMap;
import com.rank.ccms.dto.ScheduleCallDto;
import com.rank.ccms.entities.CallDtl;
import com.rank.ccms.entities.CallMst;
import com.rank.ccms.entities.CallRecords;
import com.rank.ccms.entities.CustomerDeviceDtl;
import com.rank.ccms.entities.CustomerLoanDtl;
import com.rank.ccms.entities.CustomerMst;
import com.rank.ccms.entities.EmployeeActivityDtl;
import com.rank.ccms.entities.EmployeeCallStatus;
import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.entities.ForwardedCall;
import com.rank.ccms.entities.ReasonMst;
import com.rank.ccms.entities.ScheduleCall;
import com.rank.ccms.entities.TenancyEmployeeMap;
import com.rank.ccms.service.CallDtlService;
import com.rank.ccms.service.CallMstService;
import com.rank.ccms.service.CallRecordsService;
import com.rank.ccms.service.CallSchedulerService;
import com.rank.ccms.service.CustomerDeviceDtlService;
import com.rank.ccms.service.CustomerLoanDtlService;
import com.rank.ccms.service.CustomerMstService;
import com.rank.ccms.service.EmployeeActivityDtlService;
import com.rank.ccms.service.EmployeeCallStatusService;
import com.rank.ccms.service.EmployeeMstService;
import com.rank.ccms.service.ForwardedCallService;
import com.rank.ccms.service.ReasonMstService;
import com.rank.ccms.service.TenancyEmployeeMapService;
import com.rank.ccms.util.CallScheduler;
import com.rank.ccms.util.Constants;
import com.rank.ccms.util.CustomConvert;
import com.rank.ccms.util.CustomerConstant;
import com.rank.ccms.util.SocketMessage;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeSet;
import java.util.logging.Level;
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
public class CallStartedComponent {

    Logger logger = Logger.getLogger(this.getClass());

    private String popUpMessage;
    boolean hangupcalled = false;

    @Autowired
    public CallMstService callMstService;

    @Autowired
    public CallDtlService callDtlService;

    @Autowired
    public EmployeeActivityDtlService employeeActivityDtlService;

    @Autowired
    public ReasonMstService reasonMstService;

    @Autowired
    public ForwardedCallService forwardedCallService;

    @Autowired
    public EmployeeCallStatusService employeecallStatusService;

    @Autowired
    private TenancyEmployeeMapService tenancyEmployeeMapService;

    @Autowired
    private EmployeeMstService employeeMstService;

    @Autowired
    private VidyoAgent vidyoAgent;

    @Autowired
    CustomerComponent customerComponent;

    @Autowired
    ImageComponent imageComponent;

    @Autowired
    private CustomerMstService customerMstService;

    @Autowired
    private CustomerDeviceDtlService customerDeviceDtlService;

    @Autowired
    private CallRecordsService callRecordsService;

    @Autowired
    private EmployeeLoginComponent employeeLoginComponent;

    @Autowired
    CustomerLoginProcessesComponent customerLoginProcessesComponent;

    @Autowired
    EmployeeCallStatusService employeeCallStatusService;

    @Autowired
    CallScheduler callScheduler;

    @Autowired
    CallSchedulerService callSchedulerService;

    @Autowired
    private CustomerLoanDtlService customerLoanDtlService;

    public void specialistCallStarted(HttpServletRequest request) {

        EmployeeMst localEmpMst = (EmployeeMst) request.getSession().getAttribute("ormAgentMaster");

        if (localEmpMst != null) {
            List<TenancyEmployeeMap> tenancyEmployeeMaplist = tenancyEmployeeMapService.findVidyoTenantUrlByEmpId(localEmpMst.getId());

            Integer logincount = tenancyEmployeeMaplist.size();
            if (request.getSession() != null) {
                if (request.getSession().getAttribute("logincount") != null) {
                    localEmpMst = (EmployeeMst) request.getSession().getAttribute("ormAgentMaster");
                    logincount = (Integer) request.getSession().getAttribute("logincount");
                }
            }

            if (tenancyEmployeeMaplist.size() > logincount) {
                logger.info("Session Going to destroy Now");

                RequestContext.getCurrentInstance().execute("closeAllDialog();PF('anotherLoggedIn').show();");
                employeeLoginComponent.sessionhandleLogout(request);
                RequestContext.getCurrentInstance().execute("leavecall();");

            }
        }
        if (vidyoAgent.getAutoPollingStart().equals("true")) {
            for (Iterator<CallEmployeeMap> itr = CallScheduler.listCallEmp.iterator(); itr.hasNext();) {
                CallEmployeeMap cem = itr.next();
                if (localEmpMst != null && localEmpMst.getId() == cem.getEmployeeId().longValue()) {
                    if (!cem.getCallType().equals("Internal")) {
                        if (cem.getCallType().equalsIgnoreCase("ThreeWay")) {

                            CallMst callmst = callMstService.findCallMstById(cem.getCallId());
                            CallRecords callR = callRecordsService.findCallRecordsByCallIdOnly(cem.getCallId());
                            vidyoAgent.setPortal(Constants.vidyoportal);
                            vidyoAgent.setUserName(localEmpMst.getLoginId());
                            vidyoAgent.setAgentName(localEmpMst.getFirstName() + " " + localEmpMst.getLastName() + "~" + localEmpMst.getLoginId());
                            vidyoAgent.setRoomUrl(cem.getRoomLink().substring(cem.getRoomLink().lastIndexOf("/") + 1));
                            if (null != callmst) {
                                vidyoAgent.setRoomId(callmst.getRoomName());
                            }
                            if (null != callR) {
                                vidyoAgent.setRoomName(callR.getRoomName());
                            }

                            RequestContext.getCurrentInstance().update("vidyowebrtcform");
                            this.popUpMessage = "Agent is waiting for your response.Please join the conference";
                        }
                        RequestContext.getCurrentInstance().execute("deskNotification('" + this.popUpMessage + "');");
                        vidyoAgent.specialistGetNotification();

                        itr.remove();

                        Timestamp fromtime = null;
                        try {
                            fromtime = CustomConvert.javaDateToTimeStamp(new Date());
                        } catch (ParseException ex) {
                            java.util.logging.Logger.getLogger(CallStartedComponent.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        if (!cem.getCallType().equalsIgnoreCase("Internal")) {

                            ReasonMst reasonMst = reasonMstService.findReasonMstByReasonCode("CLS01");
                            EmployeeActivityDtl employeeActDtl = new EmployeeActivityDtl();
                            employeeActDtl.setEmpId(localEmpMst);
                            employeeActDtl.setActivity("Call Started");
                            employeeActDtl.setStartTime(fromtime);
                            employeeActDtl.setReasonId(reasonMst);
                            employeeActDtl.setReasonCd(reasonMst.getReasonCd());
                            employeeActDtl.setReasonDesc(reasonMst.getReasonDesc());
                            employeeActDtl.setCallMstId(cem.getCallId());
                            employeeActivityDtlService.save(employeeActDtl, localEmpMst);

                            employeeActDtl = new EmployeeActivityDtl();
                            employeeActDtl.setEmpId(localEmpMst);
                            employeeActDtl.setActivity("Threeway Specialist");
                            employeeActDtl.setStartTime(fromtime);
                            employeeActDtl.setEndTime(fromtime);
                            employeeActDtl.setReasonId(reasonMst);
                            employeeActDtl.setReasonCd(reasonMst.getReasonCd());
                            employeeActDtl.setReasonDesc(reasonMst.getReasonDesc());
                            employeeActDtl.setCallMstId(cem.getCallId());
                            employeeActivityDtlService.save(employeeActDtl, localEmpMst);
                        }

                        CallMst callMstLocalCallMst = callMstService.findCallMstById(cem.getCallId());
                        logger.info(callMstLocalCallMst);
                        if (null != callMstLocalCallMst) {
                            request.getSession().setAttribute("userNameText", callMstLocalCallMst.getCustId());
                        }
                        request.getSession().setAttribute("callMst", callMstLocalCallMst);
                        request.getSession().setAttribute("incomingCallType", cem.getCallType());
                    }
                }
            }
        }
    }

    public void joinSpecialistRoom() {
        HttpServletRequest request = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest());
        EmployeeMst employeeMaster = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
        RequestContext.getCurrentInstance().execute("stopReceiveForwardInterval();stopIntervalForNoti();");
        RequestContext.getCurrentInstance().execute("stopAudio();");
        this.hangupcalled = false;
        vidyoAgent.setHangupcalled(false);
        vidyoAgent.setIworkImage(true);
        RequestContext.getCurrentInstance().execute(" $('#downloadFileId').hide();");
        ForwardedCall forwardedCall = forwardedCallService.findActiveForwardedCallByEmployeeMst(employeeMaster);
        if (forwardedCall != null) {
            CallMst call = callMstService.findCallMstById(forwardedCall.getCallId().getId());
            if (call.getCallOption().equalsIgnoreCase("chat")) {
                List<CallDtl> cList = callDtlService.findCallDtlByCallMasterId(call.getId());
                Set<String> hash_Set = new HashSet<>();
                if (call.getEndTime() == null) {
                    CustomerMst cust = customerMstService.findCustomerMstByCustId(call.getCustId());
                    hash_Set.add(call.getCustId() + "~" + cust.getFirstName());
                }
                for (int i = 0; i < cList.size(); i++) {
                    if (cList.get(i).getEndTime() == null) {
                        EmployeeMst em = employeeMstService.findEmployeeMstById(cList.get(i).getHandeledById().getId());
                        if (!em.getLoginId().equals(employeeMaster.getLoginId())) {
                            hash_Set.add(em.getLoginId() + "~" + em.getFirstName() + " " + em.getLastName());
                        }
                    }
                }

                for (String temp : hash_Set) {
                    RequestContext.getCurrentInstance().execute("insertParticipants('" + temp + "');");
                }
                RequestContext.getCurrentInstance().execute("forwardcalljoinchat()");
            } else if (call.getCallOption().equalsIgnoreCase("audio")) {
                RequestContext.getCurrentInstance().execute("callaudio()");
                RequestContext.getCurrentInstance().execute("forwardcalljoinother()");
            } else {
                RequestContext.getCurrentInstance().execute("joinViaBrowser();forwardcalljoinother()");
            }

            forwardedCall.setActiveFlg(false);
            try {
                forwardedCall.setCallPickupTime(CustomConvert.javaDateToTimeStamp(new Date()));
            } catch (ParseException ex) {
                java.util.logging.Logger.getLogger(CallStartedComponent.class.getName()).log(Level.SEVERE, null, ex);
            }
            forwardedCall = forwardedCallService.saveForwardedCalls(forwardedCall, employeeMaster);
            request.getSession().setAttribute("ForwardedCall", forwardedCall);
        }

        imageComponent.getCustomerData();
        RequestContext.getCurrentInstance().execute("PF('multiwayjoin').hide();");
        try {
            callAfterSpecialistHandling();
        } catch (Exception e) {
            logger.info("Exception callAfterSpecialistHandling" + e.getMessage());
        }
    }

    public void callAfterSpecialistHandling() throws Exception {

        HttpServletRequest request = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest());
        EmployeeMst localEmpMst = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
        CallMst callMstr = (CallMst) request.getSession().getAttribute("callMst");
        vidyoAgent.setHangupenabled(false);
        vidyoAgent.setDialenabled(true);
        vidyoAgent.setHangupenabled(false);
        vidyoAgent.setHoldenabled(false);
        vidyoAgent.setThreewayenabled(false);
        vidyoAgent.setForwardenabled(false);
        vidyoAgent.setNotreadyenabled(true);
        vidyoAgent.setAgentStatus("Busy");
        vidyoAgent.setLogoutenabled(true);
        vidyoAgent.setAgentColor("background:red");
        vidyoAgent.setForwardedcall(true);
        vidyoAgent.setIworkImage(true);
        vidyoAgent.setSelfviewenabled(true);

        EmployeeCallStatus employeeCallStatus = new EmployeeCallStatus();

        List<EmployeeCallStatus> employeeCallStatusList = employeecallStatusService.findEmployeeCallStatusByEmpId(localEmpMst);

        if (!employeeCallStatusList.isEmpty()) {
            for (EmployeeCallStatus empstatus : employeeCallStatusList) {
                employeeCallStatus = empstatus;
            }
            Integer count = employeeCallStatus.getCallCount() + 1;
            employeeCallStatus.setCallCount(count);
            employeeCallStatus.setStatus(false);
            employeecallStatusService.saveEmployeeCallStatus(employeeCallStatus);
        } else {
            employeeCallStatus.setEmpId(localEmpMst);
            employeeCallStatus.setCallCount(1);
            employeeCallStatus.setStatus(false);
            employeecallStatusService.saveEmployeeCallStatus(employeeCallStatus);
        }

        CallMst localcallMst = callMstService.findCallMstById(callMstr.getId());
        CallDtl callDtl = callDtlService.findNonEndedCallDtlByCallMasterIdAndEmployeeId(localcallMst.getId(), localEmpMst.getId());

        try {
            callDtl.setStartTime(CustomConvert.javaDateToTimeStamp(new Date()));

        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(CallStartedComponent.class.getName()).log(Level.SEVERE, null, ex);
        }

        EmployeeActivityDtl employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(localEmpMst.getId(), "NEXT CALL");
        if (employeeActivityDtl != null) {
            try {
                employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));

            } catch (ParseException ex) {
                java.util.logging.Logger.getLogger(CallStartedComponent.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            employeeActivityDtlService.save(employeeActivityDtl);

        }

        employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(localEmpMst.getId(), "HOLD CALL");
        if (employeeActivityDtl != null) {
            try {
                employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));

            } catch (ParseException ex) {
                java.util.logging.Logger.getLogger(CallStartedComponent.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void checkSpecialistCallReceive(HttpServletRequest request) {

        EmployeeMst forwardedAgent = (EmployeeMst) request.getSession().getAttribute("specialist");
        CallMst callMstr = (CallMst) request.getSession().getAttribute("callMst");
        List<CallDtl> callDtllist = callDtlService.findNonDeletedCallDtlByCallId(callMstr);
        ForwardedCall threeway = forwardedCallService.findForwardedCallByCallIdAndEmpId(callMstr);

        CallDtl callDtllocal = null;

        if (!callDtllist.isEmpty()) {
            callDtllocal = callDtllist.get(0);
        }

        if (callDtllocal != null) {
            if (forwardedAgent != null) {
                if (forwardedAgent.getId().equals(callDtllocal.getHandeledById().getId()) && threeway.getCallPickupTime() != null) {
                    RequestContext.getCurrentInstance().execute("PF('forspecialist').hide();");
                    RequestContext.getCurrentInstance().execute("stopIntervalforSpecialistResponse();");
                    RequestContext.getCurrentInstance().execute("stopAudio();");
                    RequestContext.getCurrentInstance().execute("PF('specialistsuccess').show();setTimeout(function(){ PF('specialistsuccess').hide(); }, 3000);");
                    logger.info("call received by specialist");
                    request.getSession().removeAttribute("specialist");
                }
            }

        }
    }

    public void callEndWhenAgentHangUp() {
        try {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            CallMst callMstFromCustomerSession = (CallMst) request.getSession().getAttribute("callMstThroughWeb");
            EmployeeMst localEmpMst = (EmployeeMst) request.getSession().getAttribute("ormAgentMaster");

            if (null != callMstFromCustomerSession) {

                CallMst cm = callMstService.findCallMstById(callMstFromCustomerSession.getId());
                Thread.sleep(3000);

                if (cm.getCallType().equalsIgnoreCase("Schedule")) {

                    List<ScheduleCall> scall = callSchedulerService.findScheduleCallByCustomerIdCallMstId(cm.getId(), customerMstService.findCustomerMstByCustId(cm.getCustId()).getId());
                    if (!scall.isEmpty()) {
                        scall.get(0).setExecuteStatus("Completed");
                        callSchedulerService.saveScheduleCall(scall.get(0), localEmpMst);
                    }
                }
                customerComponent.setStatusOfEndCall(true);
                customerComponent.refreshWhenCallEnds1(request);
                RequestContext.getCurrentInstance().execute("closeAllDialog();disconnectCall();PF('agenthangup').show();");
                RequestContext.getCurrentInstance().execute("setTimeout(function(){$('#backBtn').click();PF('agenthangup').hide();}, 5000);");

            }
        } catch (InterruptedException e) {
            logger.error("ERROR: ", e);
        }
    }

    public void callStarted(HttpServletRequest request) {
        HttpSession session = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession();
        String userIdText = (String) session.getAttribute("userNameText");
        boolean inCall = false;
        CustomerDeviceDtl cdd = null;
        String custStatus = "No";
        boolean anotherLoggedIn = false;
        EmployeeMst employeeMstLocal = (EmployeeMst) request.getSession().getAttribute("ormAgentMaster");
        CallMst callMstfromsession = (CallMst) session.getAttribute("callMst");
        if (null != callMstfromsession && employeeMstLocal != null) {
            List<CallDtl> callDtlList = callDtlService.findCallDtlByCallMasterIdAndAgent(callMstfromsession.getId(), employeeMstLocal.getId());
            if (!callDtlList.isEmpty()) {
                if (callDtlList.get(0).getCallTypeInfo().equalsIgnoreCase("Forward")) {
                    imageComponent.getPreviousChatHistory();
                }
            }

            CallMst oldcallmst = callMstService.findCallMstById(callMstfromsession.getId());

            EmployeeMst localEmpMst = null;
            if (request.getSession() != null) {
                if (request.getSession().getAttribute("logincount") != null) {
                    localEmpMst = (EmployeeMst) request.getSession().getAttribute("ormAgentMaster");

                }
            }
            if (localEmpMst != null) {
                List<TenancyEmployeeMap> tenancyEmployeeMaplist = tenancyEmployeeMapService.findVidyoTenantUrlByEmpId(localEmpMst.getId());

                Integer logincount = tenancyEmployeeMaplist.size();
                if (request.getSession() != null) {
                    if (request.getSession().getAttribute("logincount") != null) {
                        logincount = (Integer) request.getSession().getAttribute("logincount");
                    }
                }
                if (tenancyEmployeeMaplist.size() > logincount) {
                    logger.info("Session Going to destroy Now");
                    anotherLoggedIn = true;
                    RequestContext.getCurrentInstance().execute("PF('anotherLoggedIn').show();");
                    employeeLoginComponent.sessionhandleLogout(request);

                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException ex) {
                        java.util.logging.Logger.getLogger(CallStartedComponent.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    RequestContext.getCurrentInstance().execute("leavecall();");
                }
            }

            boolean flagCallDisconnected = false;
            if (null != oldcallmst) {
                if (!CallScheduler.customerCallDetailsMapDisconnected.isEmpty()) {
                    if (CallScheduler.customerCallDetailsMapDisconnected.containsKey(oldcallmst.getId().toString())) {
                        logger.info("Call Going to Disconnect.....");
                        flagCallDisconnected = true;
                    }
                }

                if ((!hangupcalled && null != oldcallmst.getEndTime()) || (flagCallDisconnected)) {
                    inCall = true;
                    vidyoAgent.setAgentStatus("Ready");
                    vidyoAgent.setAgentColor("");
                    vidyoAgent.setHangupenabled(true);
                    vidyoAgent.setDialenabled(true);
                    vidyoAgent.setHoldenabled(true);
                    vidyoAgent.setThreewayenabled(true);
                    vidyoAgent.setForwardenabled(true);
                    vidyoAgent.setNotreadyenabled(true);
                    vidyoAgent.setSelfviewenabled(false);
                    vidyoAgent.setAvailableenabled(false);
                    vidyoAgent.setLogoutenabled(false);
                    hangupcalled = true;
                    vidyoAgent.setHangupenabled(true);
                    imageComponent.filemessage = "";
                    RequestContext.getCurrentInstance().update("imgIworksForm");

                    RequestContext.getCurrentInstance().execute("PF('notDialog').hide();stopAudio();stopIntervalForNoti();PF('timerDivW').hide();");

                    if (!anotherLoggedIn) {
                        if (!callMstfromsession.getCallStatus().equals("ABANDONED")) {

                            CallMst cm = callMstService.findCallMstById(callMstfromsession.getId());

                            if (!cm.getCallStatus().trim().equalsIgnoreCase("Customer Missed")) {
                                logger.info("Confused in callStarted() of callStarted Component callMStId=" + cm.getId());

                                RequestContext.getCurrentInstance().execute("closeAllDialog();customerDisconnected();");
                            } else {
                                RequestContext.getCurrentInstance().execute("customerMissed();");
                            }
                            vidyoAgent.setHangupcalled(true);

                            RequestContext.getCurrentInstance().execute("disconnectCall();");
                            RequestContext.getCurrentInstance().execute("callHangup();");

                        }

                    }

                    if (!CallScheduler.customerCallDetailsMapDisconnected.isEmpty()) {
                        if (CallScheduler.customerCallDetailsMapDisconnected.containsKey(oldcallmst.getId().toString())) {
                            logger.info("Call Going to Disconnect...2222222222..");
                            CallScheduler.customerCallDetailsMapDisconnected.remove(oldcallmst.getId().toString());
                        }
                    }

                }
            }
        }
        if (!inCall) {
            if (employeeMstLocal != null) {
                List<TenancyEmployeeMap> tenancyEmployeeMaplist = tenancyEmployeeMapService.findVidyoTenantUrlByEmpId(employeeMstLocal.getId());

                List<Long> unsortList = new ArrayList<>();
                for (TenancyEmployeeMap tenancyEmployeeMaplist1 : tenancyEmployeeMaplist) {
                    unsortList.add(tenancyEmployeeMaplist1.getId());
                }
                Collections.sort(unsortList);
                TenancyEmployeeMap tenancyEmployeeMap;
                String roomLink = "";

                if (!tenancyEmployeeMaplist.isEmpty()) {
                    tenancyEmployeeMap = tenancyEmployeeMapService.findById(unsortList.get(unsortList.size() - 1));
                    roomLink = tenancyEmployeeMap.getRoomLink();

                }

                EmployeeMst localEmpMst = null;
                if (request.getSession() != null) {
                    if (request.getSession().getAttribute("logincount") != null) {
                        localEmpMst = (EmployeeMst) request.getSession().getAttribute("ormAgentMaster");

                    }
                }

                if (localEmpMst != null) {
                    tenancyEmployeeMaplist = tenancyEmployeeMapService.findVidyoTenantUrlByEmpId(localEmpMst.getId());

                    Integer logincount = tenancyEmployeeMaplist.size();
                    if (request.getSession() != null) {
                        if (request.getSession().getAttribute("logincount") != null) {
                            localEmpMst = (EmployeeMst) request.getSession().getAttribute("ormAgentMaster");
                            logincount = (Integer) request.getSession().getAttribute("logincount");
                        }
                    }

                    if (tenancyEmployeeMaplist.size() > logincount) {
                        logger.info("Session Going to destroy Now");

                        RequestContext.getCurrentInstance().execute("closeAllDialog();PF('anotherLoggedIn').show();");
                        employeeLoginComponent.sessionhandleLogout(request);

                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException ex) {
                            java.util.logging.Logger.getLogger(CallStartedComponent.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        RequestContext.getCurrentInstance().execute("leavecall();");

                    } else {

                        for (Iterator<CallEmployeeMap> itr = CallScheduler.listCallEmp.iterator(); itr.hasNext();) {

                            CallEmployeeMap cem = itr.next();

                            if (localEmpMst != null && localEmpMst.getId() == cem.getEmployeeId().longValue()) {
                                try {
                                    if (vidyoAgent.getAutoPollingStart().equals("true")) {

                                        vidyoAgent.setExistingCustomer(false);
                                        vidyoAgent.setLoanCustomer(false);
                                        RequestContext.getCurrentInstance().execute("PF('reasonHangupSelectDlg').hide();PF('reasonSelectDlg').hide();");
                                        CallMst callMstLocalCallMst = callMstService.findCallMstById(cem.getCallId());
                                        CustomerMst customerMst = customerMstService.findCustomerMstByCustId(callMstLocalCallMst.getCustId());
                                        if (null != customerMst) {
                                            logger.info("Account No ======>" + customerMst.getAccountNo() + "<====");
                                            if (customerMst.getAccountNo() != null) {
                                                vidyoAgent.setExistingCustomer(true);
                                            }
                                        }
                                        vidyoAgent.customerCallingNotification();
                                        this.hangupcalled = false;
                                        vidyoAgent.setHangupenabled(false);
                                        itr.remove();

                                        Timestamp fromtime = CustomConvert.javaDateToTimeStamp(new Date());

                                        if (!cem.getCallType().equalsIgnoreCase("Scheduled")) {

                                            ReasonMst reasonMst = reasonMstService.findReasonMstByReasonCode("CLS01");
                                            EmployeeActivityDtl employeeActDtl = new EmployeeActivityDtl();
                                            employeeActDtl.setEmpId(localEmpMst);
                                            employeeActDtl.setActivity("Call Started");
                                            employeeActDtl.setStartTime(fromtime);
                                            employeeActDtl.setReasonId(reasonMst);
                                            employeeActDtl.setReasonCd(reasonMst.getReasonCd());
                                            employeeActDtl.setReasonDesc(reasonMst.getReasonDesc());
                                            employeeActDtl.setCallMstId(cem.getCallId());
                                            employeeActivityDtlService.save(employeeActDtl, localEmpMst);
                                        }
                                        EmployeeActivityDtl employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(localEmpMst.getId(), "NEXT CALL");
                                        if (employeeActivityDtl != null) {
                                            employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                                            employeeActivityDtlService.save(employeeActivityDtl);

                                        }

                                        callMstLocalCallMst.setStartTime(fromtime);
                                        callMstLocalCallMst.setCallStatus("Active");
                                        CallMst callMstLocal = callMstService.saveCallMst(callMstLocalCallMst);

                                        CallRecords callRecords = null;
                                        if (null != callMstLocal) {
                                            callRecords = callRecordsService.findCallRecordsByCallId(callMstLocal.getId(), (long) 0);
                                        }
                                        if (null == callRecords) {
                                            callRecords = new CallRecords();
                                        }

                                        tenancyEmployeeMaplist = tenancyEmployeeMapService.findVidyoTenantUrlByEmpId(localEmpMst.getId());
                                        unsortList = new ArrayList<>();
                                        for (TenancyEmployeeMap tenancyEmployeeMaplist1 : tenancyEmployeeMaplist) {
                                            unsortList.add(tenancyEmployeeMaplist1.getId());
                                        }
                                        Collections.sort(unsortList);

                                        tenancyEmployeeMap = tenancyEmployeeMapService.findById(unsortList.get(unsortList.size() - 1));

                                        String customerID = callMstLocalCallMst.getCustId();
                                        callRecords.setCallId(callMstLocalCallMst);
                                        callRecords.setEmpId(localEmpMst);
                                        callRecords.setCustomerId(customerID);
                                        callRecords.setRoomName(tenancyEmployeeMap.getRoomName());
                                        callRecords.setConferenceId(tenancyEmployeeMap.getEntityId());
                                        callRecords.setExternalPlaybackLink("Not Saved");
                                        callRecords.setRecorderId("");

                                        callRecordsService.saveCallRecord(callRecords);

                                        if (null != callMstLocal) {
                                            if (null != callMstLocal.getCustId() && !"".equals(callMstLocal.getCustId())) {
                                                request.getSession().setAttribute("userNameText", callMstLocal.getCustId());
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
                                                request.getSession().setAttribute("callMst", callMstLocal);
                                            }
                                        }

                                        request.getSession().setAttribute("incomingCallType", cem.getCallType());
                                        CallDtl callDtl = new CallDtl();
                                        callDtl.setCallMstId(callMstLocal);
                                        callDtl.setActiveFlg(true);
                                        callDtl.setDeleteFlg(false);
                                        callDtl.setHandeledById(localEmpMst);
                                        callDtl.setStartTime(fromtime);
                                        callDtl.setCallTypeInfo(cem.getCallType());
                                        callDtlService.saveCallDtl(callDtl);

                                        if (null != userIdText && !"".equals(userIdText) && cem.getCallType().equalsIgnoreCase("Scheduled")) {
                                            CustomerMst cm = customerMstService.findCustomerMstByCustId(userIdText);
                                            logger.info("userIdText:::" + userIdText);
                                            cdd = customerDeviceDtlService.findCustomerDeviceDtlByCustId(cm);

                                            if (null != cdd) {
                                                if (0 == cdd.getLoginInfo() && cm.getAccountNo() != null && cm.getAccountNo().length() > 0) {
                                                    custStatus = "No";
                                                } else if (0 == cdd.getLoginInfo()) {
                                                    custStatus = "Yes";
                                                }
                                                if (2 == cdd.getLoginInfo()) {
                                                    custStatus = "Busy";
                                                }
                                                if (1 == cdd.getLoginInfo() && cm.getAccountNo() != null && cm.getAccountNo().length() > 0) {
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

                                                if (null != osType) {

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
                                                    CallMst callMst_L = (CallMst) request.getSession().getAttribute("callMst");
                                                    if (null != callMst_L) {
                                                        callMst_L = callMstService.findCallMstById(callMst_L.getId());
                                                    }
                                                    callMst_L.setCallMedium("Mobile");
                                                    callMst_L.setRoomName(room);
                                                    callMstService.saveCallMst(callMst_L);

                                                }

                                            }

                                        }

                                        if (null != cdd && null != callMstLocal) {
                                            String osType = cdd.getMobileOsType();
                                            if (null != osType) {
                                                if (osType.equalsIgnoreCase("WEB")) {
                                                    ScheduleCallDto l_ScheduleCallDto = new ScheduleCallDto();
                                                    localEmpMst = (EmployeeMst) request.getSession().getAttribute("ormAgentMaster");
                                                    String empName = "";
                                                    if (null != localEmpMst && null != localEmpMst.getMidName()) {
                                                        empName = localEmpMst.getFirstName() + " " + localEmpMst.getMidName() + " " + localEmpMst.getLastName();

                                                    } else if (null != localEmpMst) {
                                                        empName = localEmpMst.getFirstName() + " " + localEmpMst.getLastName();
                                                    }
                                                    l_ScheduleCallDto.setAgentName(empName);
                                                    l_ScheduleCallDto.setCustomerPkId(callMstLocal.getCustomerId().getId());
                                                    if (null != localEmpMst) {
                                                        l_ScheduleCallDto.setRefAgentSchId(localEmpMst.getId());
                                                    }
                                                    l_ScheduleCallDto.setRoomLink(roomLink);
                                                    CallMst callMst_L = (CallMst) request.getSession().getAttribute("callMst");
                                                    if (null != callMst_L) {
                                                        callMst_L = callMstService.findCallMstById(callMst_L.getId());
                                                    }
                                                    callMst_L.setCallMedium("WEB");
                                                    callMst_L.setRoomName(room);
                                                    l_ScheduleCallDto.setCallMstPkId(callMst_L.getId());

                                                    callMst_L = callMstService.saveCallMst(callMst_L);
                                                    if (null != callMst_L) {
                                                        CustomerConstant.scheduleCallList.add(l_ScheduleCallDto);
                                                    }

                                                } else {
                                                    CallMst callMst_L = (CallMst) request.getSession().getAttribute("callMst");
                                                    if (null != callMst_L) {
                                                        callMst_L = callMstService.findCallMstById(callMst_L.getId());
                                                    }
                                                    callMst_L.setCallMedium("Mobile");
                                                    callMst_L.setRoomName(room);
                                                    callMstService.saveCallMst(callMst_L);
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

                                            }

                                        }

                                    }
                                } catch (ParseException ex) {
                                    java.util.logging.Logger.getLogger(CallStartedComponent.class
                                            .getName()).log(Level.SEVERE, null, ex);

                                }

                            }
                        }

                        checkForwardedCall(localEmpMst, request);
                        checkScheduleCall(localEmpMst, request);
                    }
                }
            } else {
                RequestContext.getCurrentInstance().execute("window.location.reload(true);");
            }
        }

    }

    private void checkScheduleCall(EmployeeMst localEmpMst, HttpServletRequest request) {
        try {

            EmployeeMst empLocal = (EmployeeMst) request.getSession().getAttribute("ormAgentMaster");

            if (null != empLocal) {
                if (!employeeCallStatusService.findFreeOnlineRMs(empLocal.getLoginId()).isEmpty()) {

                    for (Iterator<CallEmployeeMap> itr = CallScheduler.listScheduleCallForEmp.iterator(); itr.hasNext();) {
                        CallEmployeeMap cem = itr.next();
                        if (Objects.equals(cem.getEmployeeId(), localEmpMst.getId()) && cem.getCallType().equalsIgnoreCase("ScheduleCall")) {
                            logger.info(cem.getScheduleCallId() + "case---------4----------------------" + cem.getCallId());
                            ScheduleCall scheduleCall = callSchedulerService.findNonTakenById(cem.getScheduleCallId());
                            if (null != scheduleCall && Objects.equals(cem.getScheduleCallId(), scheduleCall.getId())) {

                                Date scheduleStartDate = scheduleCall.getScheduleDate();
                                Date now = new Date();
                                Calendar toleranceDate = Calendar.getInstance();
                                toleranceDate.setTime(scheduleStartDate);
                                toleranceDate.add(Calendar.MINUTE, +5);
                                //TO-DO check if Schedule current time...
                                if (now.after(scheduleStartDate) && now.before(toleranceDate.getTime()) || now.equals(scheduleStartDate)) {
                                    CallMst callMst = callMstService.findCallMstById(cem.getCallId());
                                    List<TenancyEmployeeMap> tenancyEmployeeMaplist = tenancyEmployeeMapService.findVidyoTenantUrlByEmpId(localEmpMst.getId());

                                    List<Long> unsortList = new ArrayList<>();
                                    for (TenancyEmployeeMap tenancyEmployeeMaplist1 : tenancyEmployeeMaplist) {
                                        unsortList.add(tenancyEmployeeMaplist1.getId());
                                    }
                                    Collections.sort(unsortList);
                                    TenancyEmployeeMap tenancyEmployeeMap = null;
                                    String roomLink = "";
                                    String room = "";
                                    if (!tenancyEmployeeMaplist.isEmpty() && !unsortList.isEmpty()) {
                                        tenancyEmployeeMap = tenancyEmployeeMapService.findById(unsortList.get(unsortList.size() - 1));
                                        roomLink = tenancyEmployeeMap.getRoomLink();
                                        room = tenancyEmployeeMap.getRoomName();
                                    }

                                    vidyoAgent.setRoomId(roomLink);
                                    vidyoAgent.setRoomName(room);
                                    vidyoAgent.setRoomUrl(roomLink.substring(roomLink.lastIndexOf("/") + 1));

                                    request.getSession().setAttribute("userNameText", callMst.getCustId());

                                    CustomerMst customerMst = customerMstService.findCustomerMstByCustId(callMst.getCustId());
                                    if (null != customerMst && null != tenancyEmployeeMap) {
                                        logger.info("Account No ======>" + customerMst.getAccountNo() + "<====");
                                        if (customerMst.getAccountNo() != null) {
                                            vidyoAgent.setExistingCustomer(true);
                                        }
                                        this.hangupcalled = false;
                                        vidyoAgent.setHangupenabled(false);

                                        Timestamp fromtime = CustomConvert.javaDateToTimeStamp(new Date());
                                        ReasonMst reasonMst = reasonMstService.findReasonMstByReasonCode("CLS01");
                                        EmployeeActivityDtl employeeActDtl = new EmployeeActivityDtl();
                                        employeeActDtl.setEmpId(localEmpMst);
                                        employeeActDtl.setActivity("Call Started");
                                        employeeActDtl.setStartTime(fromtime);
                                        employeeActDtl.setReasonId(reasonMst);
                                        employeeActDtl.setReasonCd(reasonMst.getReasonCd());
                                        employeeActDtl.setReasonDesc(reasonMst.getReasonDesc());
                                        employeeActDtl.setCallMstId(cem.getCallId());
                                        employeeActivityDtlService.save(employeeActDtl, localEmpMst);

                                        EmployeeActivityDtl employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(localEmpMst.getId(), "NEXT CALL");
                                        if (employeeActivityDtl != null) {
                                            employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                                            employeeActivityDtlService.save(employeeActivityDtl);

                                        }
                                        callMst.setStartTime(fromtime);
                                        callMst.setCallStatus("Active");
                                        CallMst callMstLocal = callMstService.saveCallMst(callMst);
                                        request.getSession().setAttribute("callMst", callMstLocal);
                                        CallRecords callRecords = null;
                                        if (null != callMstLocal) {
                                            callRecords = callRecordsService.findCallRecordsByCallId(callMstLocal.getId(), (long) 0);
                                        }
                                        if (null == callRecords) {
                                            callRecords = new CallRecords();
                                        }

                                        String customerID = customerMst.getCustId();
                                        callRecords.setCallId(callMstLocal);
                                        callRecords.setEmpId(localEmpMst);
                                        callRecords.setCustomerId(customerID);
                                        callRecords.setRoomName(tenancyEmployeeMap.getRoomName());
                                        callRecords.setConferenceId(tenancyEmployeeMap.getEntityId());
                                        callRecords.setExternalPlaybackLink("Not Saved");
                                        callRecords.setRecorderId("");
                                        callRecordsService.saveCallRecord(callRecords);

                                        CallDtl callDtl = new CallDtl();
                                        callDtl.setCallMstId(callMstLocal);
                                        callDtl.setActiveFlg(true);
                                        callDtl.setDeleteFlg(false);
                                        callDtl.setHandeledById(localEmpMst);
                                        callDtl.setStartTime(fromtime);
                                        callDtl.setCallTypeInfo(cem.getCallType());
                                        callDtlService.saveCallDtl(callDtl);

                                        EmployeeCallStatus empCallStatus = new EmployeeCallStatus();
                                        List<EmployeeCallStatus> empClStatusList = employeeCallStatusService.findEmployeeCallStatusByEmpId(localEmpMst);
                                        if (empClStatusList != null) {
                                            for (EmployeeCallStatus empstatus : empClStatusList) {
                                                empCallStatus = empstatus;
                                            }
                                            empCallStatus.setStatus(false);
                                            employeeCallStatusService.saveEmployeeCallStatus(empCallStatus);
                                        } else {
                                            empCallStatus.setEmpId(localEmpMst);
                                            empCallStatus.setStatus(false);
                                            employeeCallStatusService.saveEmployeeCallStatus(empCallStatus);
                                        }
                                        request.getSession().setAttribute("incomingCallType", "Scheduled");
                                        RequestContext.getCurrentInstance().update("vidyowebrtcform");
                                        RequestContext.getCurrentInstance().execute("closeAllDialog();PF('scheduleCallNotiWid').show();playAudio();intervalFotScheduleNoti()");
                                        RequestContext.getCurrentInstance().execute("deskNotification('Customer is waiting for your response.Please join the conference');");
                                        itr.remove();
                                    }

                                }

                            } else {
                                logger.info("Only remove..");
                                itr.remove();
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error(e);
        }
    }

    public void checkCallEndedAtSpecialistSession() {

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        CallMst callMstFromSession = (CallMst) request.getSession().getAttribute("callMst");
        EmployeeMst employeeMstLocal = (EmployeeMst) request.getSession().getAttribute("ormAgentMaster");
        if (null != callMstFromSession) {
            CallMst cm = callMstService.findCallMstById(callMstFromSession.getId());

            if (null != cm) {
                if (cm.getCallOption().equalsIgnoreCase("chat")) {
                    List<CallDtl> cList = callDtlService.findCallDtlByCallMasterId(cm.getId());
                    Set<String> hash_Set = new HashSet<>();
                    if (cm.getEndTime() == null) {
                        CustomerMst cust = customerMstService.findCustomerMstByCustId(cm.getCustId());
                        hash_Set.add(cm.getCustId() + "~" + cust.getFirstName());
                    }
                    for (int i = 0; i < cList.size(); i++) {
                        EmployeeMst em = employeeMstService.findEmployeeMstById(cList.get(i).getHandeledById().getId());
                        if (!em.getLoginId().equals(employeeMstLocal.getLoginId())) {
                            hash_Set.add(em.getLoginId() + "~" + em.getFirstName() + " " + em.getLastName());
                        }
                    }

                    for (String temp : hash_Set) {
                        RequestContext.getCurrentInstance().execute("insertParticipants('" + temp + "');");
                    }

                    CallRecords callRecord = callRecordsService.findCallRecordsByCallIdOnly(cm.getId());
                    if (null != callRecord && null != callRecord.getRecorderId()) {
                        RequestContext.getCurrentInstance().execute("showRecording();");
                    }
                }
            }
            if (null != cm && null != cm.getStartTime() && null != cm.getEndTime() && null == cm.getCustomerHangupTime() && vidyoAgent.getHangupcalled() == false) {
                vidyoAgent.showhangUpReasons(request);
                RequestContext.getCurrentInstance().execute("specialistCallHangup();");

                vidyoAgent.specialistGetEndNotification();

            } else if (null != cm && null != cm.getStartTime() && null != cm.getCustomerHangupTime() && vidyoAgent.getHangupcalled() == false) {
                if (!cm.getCallStatus().trim().equalsIgnoreCase("Customer Missed")) {
                    logger.info("Confused in checkCallEndedAtSpecialistSession of callStarted Component callMstID=" + cm.getId());
                    RequestContext.getCurrentInstance().execute("disconnect();disconnectCall();customerDisconnected();");
                } else {
                    RequestContext.getCurrentInstance().execute("customerMissed();");
                }
                vidyoAgent.setAgentStatus("Ready");
                vidyoAgent.setAgentColor("");
                vidyoAgent.setHangupenabled(true);
                vidyoAgent.setDialenabled(true);
                vidyoAgent.setHoldenabled(true);
                vidyoAgent.setThreewayenabled(true);
                vidyoAgent.setForwardenabled(true);
                vidyoAgent.setNotreadyenabled(true);
                vidyoAgent.setSelfviewenabled(false);
                vidyoAgent.setAvailableenabled(false);
                vidyoAgent.setLogoutenabled(false);
                hangupcalled = true;
                vidyoAgent.setHangupenabled(true);
                imageComponent.filemessage = "";
                vidyoAgent.setIworkImage(false);

                logger.info("checkCallEndedAtSpecialistSession.........specialistCallHangup()");

                RequestContext.getCurrentInstance().update("imgIworksForm");
                RequestContext.getCurrentInstance().execute("stopAudio();stopIntervalForNoti();");
                RequestContext.getCurrentInstance().execute("disconnect();");
                RequestContext.getCurrentInstance().execute("specialistCallHangup();");

            } else if (null != cm && null != cm.getStartTime() && null != cm.getEndTime() && vidyoAgent.getHangupcalled() == true) {

                vidyoAgent.setHangupcalled(false);

            }
        }

    }

    public void checkCallEndedAtAgentSession() {

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        CallMst callMstFromSession = (CallMst) request.getSession().getAttribute("callMst");
        EmployeeMst employeeMstLocal = (EmployeeMst) request.getSession().getAttribute("ormAgentMaster");
        if (null != callMstFromSession) {

            CallMst cm = callMstService.findCallMstById(callMstFromSession.getId());

            if (null != cm) {
                if (cm.getCallOption().equalsIgnoreCase("chat")) {
                    List<CallDtl> cList = callDtlService.findCallDtlByCallMasterId(cm.getId());
                    Set<String> hash_Set = new HashSet<>();
                    if (cm.getEndTime() == null) {
                        CustomerMst cust = customerMstService.findCustomerMstByCustId(cm.getCustId());
                        hash_Set.add(cm.getCustId() + "~" + cust.getFirstName());
                    }
                    for (int i = 0; i < cList.size(); i++) {
                        EmployeeMst em = employeeMstService.findEmployeeMstById(cList.get(i).getHandeledById().getId());
                        if (!em.getLoginId().equals(employeeMstLocal.getLoginId())) {
                            hash_Set.add(em.getLoginId() + "~" + em.getFirstName() + " " + em.getLastName());
                        }
                    }

                    for (String temp : hash_Set) {
                        RequestContext.getCurrentInstance().execute("insertParticipants('" + temp + "');");
                    }

                    CallRecords callRecord = callRecordsService.findCallRecordsByCallIdOnly(cm.getId());
                    if (null != callRecord && null != callRecord.getRecorderId()) {
                        RequestContext.getCurrentInstance().execute("showRecording();");
                    }
                }
            }
            if (employeeMstLocal != null) {
                List<CallDtl> callDtlList = callDtlService.findCallDtlByCallMasterIdAndAgent(callMstFromSession.getId(), employeeMstLocal.getId());
                if (!callDtlList.isEmpty()) {
                    if (callDtlList.get(0).getCallTypeInfo().equalsIgnoreCase("Forward")) {
                        imageComponent.getPreviousChatHistory();
                    }
                }
            }

            if (null != cm && null != cm.getStartTime() && null != cm.getCustomerHangupTime() && vidyoAgent.getHangupcalled() == false) {
                if (cm.getCallType().equalsIgnoreCase("Schedule") || cm.getCallType().equalsIgnoreCase("Schedule Call")) {
                    List<ScheduleCall> scall = callSchedulerService.findScheduleCallByCustomerIdCallMstId(cm.getId(), customerMstService.findCustomerMstByCustId(cm.getCustId()).getId());
                    if (!scall.isEmpty()) {
                        scall.get(0).setExecuteStatus("Completed");
                        callSchedulerService.saveScheduleCall(scall.get(0), employeeMstLocal);
                    }
                }
                if (!cm.getCallStatus().trim().equalsIgnoreCase("Customer Missed")) {
                    imageComponent.setIdCardName("");
                    logger.info("Confused in checkCallEndedAtAgentSession() of callStarted Component callMStId=" + cm.getId());
                    RequestContext.getCurrentInstance().execute("disconnect();disconnectCall();customerDisconnected();");
                } else {
                    RequestContext.getCurrentInstance().execute("customerMissed();");
                }
                vidyoAgent.setAgentStatus("Ready");
                vidyoAgent.setAgentColor("");
                vidyoAgent.setHangupenabled(true);
                vidyoAgent.setDialenabled(true);
                vidyoAgent.setHoldenabled(true);
                vidyoAgent.setThreewayenabled(true);
                vidyoAgent.setForwardenabled(true);
                vidyoAgent.setNotreadyenabled(true);
                vidyoAgent.setSelfviewenabled(false);
                vidyoAgent.setAvailableenabled(false);
                vidyoAgent.setLogoutenabled(false);
                hangupcalled = true;
                vidyoAgent.setHangupenabled(true);
                imageComponent.filemessage = "";
                logger.info("checkCallEndedAtAgentSession.........callHangup()");

                RequestContext.getCurrentInstance().update("imgIworksForm");
                RequestContext.getCurrentInstance().execute("PF('notDialog').hide();stopAudio();stopIntervalForNoti();PF('timerDivW').hide();");
                //RequestContext.getCurrentInstance().execute("stopCallRecording();");
                RequestContext.getCurrentInstance().execute("disconnectCall();");
                RequestContext.getCurrentInstance().execute("callHangup();");

            } else if (null != cm && null != cm.getStartTime() && null != cm.getEndTime() && vidyoAgent.getHangupcalled() == true) {

                vidyoAgent.setHangupcalled(false);

            }
        }
        try {
            List<CallEmployeeMap> listCallNotificationEmp = CallScheduler.listCallNotificationEmp;
            for (Iterator<CallEmployeeMap> it = listCallNotificationEmp.iterator(); it.hasNext();) {
                CallEmployeeMap callEmployeeMap = it.next();
                if (null != employeeMstLocal && Objects.equals(callEmployeeMap.getEmployeeId(), employeeMstLocal.getId())) {
                    ScheduleCall scheduleCall = callSchedulerService.findAllNonTakenScheduleCallById(callEmployeeMap.getScheduleCallId());
                    Date now = new Date();
                    Calendar beforeCal = Calendar.getInstance();

                    beforeCal.add(Calendar.MINUTE, 4);
                    logger.info("CurrentTime : " + now + "beforeCal : " + beforeCal.getTime() + "scheduleDate:" + scheduleCall.getScheduleDate());

                    if (scheduleCall.getScheduleDate().after(beforeCal.getTime())) {
                        TimeZone timeZoneL = TimeZone.getDefault();
                        
                       
                        Date d=CustomConvert.convertScheduleTimeZoneF(scheduleCall.getScheduleDate()+"", timeZoneL.getID(), employeeLoginComponent.getTimeZone());
                        popUpMessage = "You Have a Schedule Call On " + d + " With customer, Please be ready.";
                        logger.info("Schedule popUpMessage:" + popUpMessage);
                        RequestContext.getCurrentInstance().execute("deskNotification('" + this.popUpMessage + "');");
                        RequestContext.getCurrentInstance().execute("PF('scheduleNotifiWid').show();");
                        RequestContext.getCurrentInstance().update("scheduleNotiFicFrmId");
                        it.remove();
                    } else {
                        logger.info("RM login late , bcz of this the schedule future message remove.");
                        it.remove();
                    }
                }
            }
        } catch (Exception e) {
            logger.error(e);
        }

    }

    public void checkSessionCallMstForCustomer(HttpServletRequest request) {

        CallMst callMster = (CallMst) request.getSession().getAttribute("callMstThroughWeb");
        ScheduleCall scheduleCallLocal = (ScheduleCall) request.getSession().getAttribute("scheduleCallLocal");

        if (null != callMster && null != scheduleCallLocal) {
            callMster = callMstService.findCallMstById(callMster.getId());
            ScheduleCall scheduleCallDtl = callSchedulerService.findAllNonTakenScheduleCallById(scheduleCallLocal.getId());
            Date scheduleStartDate = scheduleCallDtl.getScheduleDate();
            Date now = new Date();
            Calendar toleranceDate = Calendar.getInstance();
            toleranceDate.setTime(scheduleStartDate);
            toleranceDate.add(Calendar.MINUTE, +5);

            //logger.info("scheduleStartDate : " + scheduleStartDate + "............" + "toleranceDate : " + toleranceDate);
            if (callMster.getAgentPickupTime() == null) {
                if (now.after(toleranceDate.getTime())) {
                    logger.info("Agent did not attend the schedule call");
                    customerComponent.setSelectedServiceName(callMster.getServiceId());
                    customerComponent.setCallType(scheduleCallLocal.getCallType());
                    request.getSession().setAttribute("callMstThroughWeb", null);
                    request.getSession().setAttribute("scheduleCallLocal", null);
                    scheduleCallDtl.setExecuteStatus("Abandoned");
                    callSchedulerService.saveScheduleCall(scheduleCallDtl, null);

                    RequestContext.getCurrentInstance().execute("closeAllDialog();hideSpinner();PF('routeToAgentOnScheduleWidget').show();");
                }
            }
            //If RM picks the call
            if (null != callMster.getAgentPickupTime() && null == callMster.getEndTime() && null == callMster.getCustomerPickupTime()) {
                //Call Join 
                logger.info("Before Call Join Customer Schedule");
                CallRecords callRecords = callRecordsService.findCallRecordsByCallIdOnly(callMster.getId());

                List<TenancyEmployeeMap> tenancyEmployeeMaplist = tenancyEmployeeMapService.findVidyoTenantUrlByEmpId(callRecords.getEmpId().getId());

                List<Long> unsortList = new ArrayList<>();
                for (TenancyEmployeeMap tenancyEmployeeMaplist1 : tenancyEmployeeMaplist) {
                    unsortList.add(tenancyEmployeeMaplist1.getId());
                }
                Collections.sort(unsortList);
                TenancyEmployeeMap tenancyEmployeeMap;
                String roomLink = "";

                if (!tenancyEmployeeMaplist.isEmpty() && !unsortList.isEmpty()) {
                    tenancyEmployeeMap = tenancyEmployeeMapService.findById(unsortList.get(unsortList.size() - 1));
                    roomLink = tenancyEmployeeMap.getRoomLink();

                }
                customerComponent.setRoomUrl(roomLink.substring(roomLink.lastIndexOf("/") + 1));
                RequestContext.getCurrentInstance().update("vidyowebrtcform");
                RequestContext.getCurrentInstance().execute("checkDialog2();closeAllDialog();hideSpinner();");

                try {
                    callMster.setCustomerPickupTime(CustomConvert.javaDateToTimeStamp(new Date()));
                    callMstService.saveCallMst(callMster);
                } catch (ParseException ex) {
                    logger.error(ex);
                }
            }
        }

        checkCallEnded();
    }

    public void checkCallEnded() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        CallMst callMstFromCustomerSession = (CallMst) request.getSession().getAttribute("callMstThroughWeb");
        EmployeeMst employeeMstLocal = (EmployeeMst) request.getSession().getAttribute("ormAgentMaster");

        if (null != callMstFromCustomerSession) {

            CallMst cm = callMstService.findCallMstById(callMstFromCustomerSession.getId());
            if (null != cm.getCallOption() && cm.getCallOption().equalsIgnoreCase("chat")) {
                List<CallDtl> cList = callDtlService.findCallDtlByCallMasterId(cm.getId());
                Set<String> hash_Set = new HashSet<>();
                for (int i = 0; i < cList.size(); i++) {
                    if (cList.get(i).getEndTime() == null) {
                        EmployeeMst em = employeeMstService.findEmployeeMstById(cList.get(i).getHandeledById().getId());
                        hash_Set.add(em.getLoginId() + "~" + em.getFirstName() + " " + em.getLastName());
                    }
                }

                for (String temp : hash_Set) {
                    RequestContext.getCurrentInstance().execute("insertParticipants('" + temp + "');");
                }
            }
            if (null != cm && null != cm.getStartTime() && null != cm.getEndTime() && customerComponent.getStatusOfEndCall() == false && cm.getCustomerHangupTime() == null) {
                if (cm.getCallType().equalsIgnoreCase("Schedule") || cm.getCallType().equalsIgnoreCase("Schedule Call")) {

                    List<ScheduleCall> scall = callSchedulerService.findScheduleCallByCustomerIdCallMstId(cm.getId(), customerMstService.findCustomerMstByCustId(cm.getCustId()).getId());
                    if (!scall.isEmpty()) {
                        scall.get(0).setExecuteStatus("Completed");
                        callSchedulerService.saveScheduleCall(scall.get(0), employeeMstLocal);
                    }
                }
                customerComponent.refreshWhenCallEnds1(request);
                customerComponent.setStatusOfEndCall(true);

                if (null != cm.getAgentPickupTime()) {
                    logger.info("---->>>call Started Component agenthangup Dialog showing From Here." + cm.getId());
                    RequestContext.getCurrentInstance().execute("closeAllDialog();PF('agenthangup').show();");
                } else {
//                    RequestContext.getCurrentInstance().execute("closeAllDialog();PF('agentNotReceived').show();PF('scheduledCallDlg').hide();setTimeout(function(){ PF('agentNotReceived').hide(); }, 3000);");
                }

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    java.util.logging.Logger.getLogger(CallStartedComponent.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    RequestContext.getCurrentInstance().execute(" $('#backBtn').click();");
                }

            } else if (null != cm && null != cm.getStartTime() && null != cm.getEndTime() && customerComponent.getStatusOfEndCall() == false && cm.getCustomerHangupTime() != null) {

                RequestContext.getCurrentInstance().execute("closeAllDialog();PF('antotherLoggedIn').show();setTimeout(function(){ PF('antotherLoggedIn').hide();}, 3000);");
                customerComponent.setStatusOfEndCall(true);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    java.util.logging.Logger.getLogger(CallStartedComponent.class.getName()).log(Level.SEVERE, null, ex);
                }
                customerLoginProcessesComponent.customerLogout1(request);
                RequestContext.getCurrentInstance().execute("document.getElementById('logout1').click();");
            }
        }

    }

    public void reasonHangUp(HttpServletRequest request) {
        this.hangupcalled = true;
        vidyoAgent.setHangupcalled(false);
        imageComponent.filemessage = "";
        RequestContext.getCurrentInstance().update("imgIworksForm");
    }

    public void checkForwardedCall(EmployeeMst employeeMst, HttpServletRequest request) {
        ForwardedCall forwardedCall = forwardedCallService.findActiveForwardedCallByEmployeeMst(employeeMst);
        CallMst oldCall = (CallMst) request.getSession().getAttribute("callMst");
        if (null != forwardedCall && null == oldCall && null == forwardedCall.getCallPickupTime()) {

            EmployeeMst employeeMstLocal = (EmployeeMst) request.getSession().getAttribute("ormAgentMaster");
            CallMst callMstLocal = callMstService.findCallMstById(forwardedCall.getCallId().getId());
            request.getSession().setAttribute("callMst", callMstLocal);

            EmployeeMst forwardingemployee = employeeMstService.findEmployeeMstById(forwardedCall.getPrevEmpId());
            this.popUpMessage = "Forwarded call from " + forwardingemployee.getFirstName() + ".Please join the conference";

            this.hangupcalled = false;
            vidyoAgent.setPortal(Constants.vidyoportal);
            vidyoAgent.setUserName(employeeMstLocal.getLoginId());
            vidyoAgent.setAgentName(employeeMstLocal.getFirstName() + " " + employeeMstLocal.getLastName() + "~" + employeeMstLocal.getLoginId());
            vidyoAgent.setHangupcalled(false);
            vidyoAgent.setRoomId(forwardedCall.getRoomLink());
            vidyoAgent.setRoomName(forwardedCall.getRoomName());
            vidyoAgent.setRoomUrl(forwardedCall.getRoomLink().substring(forwardedCall.getRoomLink().lastIndexOf("/") + 1));

            RequestContext.getCurrentInstance().execute("deskNotification('" + this.popUpMessage + "');");
            RequestContext.getCurrentInstance().update("cutomerDashboardForm");
            RequestContext.getCurrentInstance().update("receiveForward");
            RequestContext.getCurrentInstance().update("vidyowebrtcform");
            RequestContext.getCurrentInstance().execute("closeAllDialog();PF('forwardedNotification').show();");
            RequestContext.getCurrentInstance().execute("playAudio();");
            EmployeeActivityDtl employeeActivityDtlL = new EmployeeActivityDtl();
            employeeActivityDtlL.setActivity("Call Started");
            employeeActivityDtlL.setEmpId(employeeMstLocal);

            try {
                employeeActivityDtlL.setStartTime(CustomConvert.javaDateToTimeStamp(new Date()));

            } catch (ParseException ex) {
                logger.error(ex);
            }
            ReasonMst reason_P = reasonMstService.findReasonMstByReasonCode("CS02");

            employeeActivityDtlL.setReasonId(reason_P);
            employeeActivityDtlL.setReasonCd(reason_P.getReasonCd());
            employeeActivityDtlL.setReasonDesc(reason_P.getReasonDesc());
            employeeActivityDtlL.setCallMstId(callMstLocal.getId());
            employeeActivityDtlService.save(employeeActivityDtlL);

            logger.info("DONE@@@@!!!!!!!!!!!!!!!!!!");
        }
    }

    public void joinForwardingRoom() {
        logger.info("In joinForwardingRoom   .......................");
        RequestContext.getCurrentInstance().execute("stopReceiveForwardInterval();");
        RequestContext.getCurrentInstance().execute("stopAudio();");
        HttpServletRequest request = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest());
        EmployeeMst localEmpMst = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
        this.hangupcalled = false;
        vidyoAgent.setHangupcalled(false);
        ForwardedCall forwardedCall = forwardedCallService.findActiveForwardedCallByEmployeeMst(localEmpMst);

        if (forwardedCall != null) {
            CallMst call = callMstService.findCallMstById(forwardedCall.getCallId().getId());

            if (call.getCallOption().equalsIgnoreCase("chat")) {
                List<CallDtl> cList = callDtlService.findCallDtlByCallMasterId(call.getId());
                Set<String> hash_Set = new HashSet<>();
                if (call.getEndTime() == null) {
                    CustomerMst cust = customerMstService.findCustomerMstByCustId(call.getCustId());
                    hash_Set.add(call.getCustId() + "~" + cust.getFirstName());
                }
                for (int i = 0; i < cList.size(); i++) {
                    if (cList.get(i).getEndTime() == null) {
                        EmployeeMst em = employeeMstService.findEmployeeMstById(cList.get(i).getHandeledById().getId());
                        if (!em.getLoginId().equals(localEmpMst.getLoginId())) {
                            hash_Set.add(em.getLoginId() + "~" + em.getFirstName() + " " + em.getLastName());
                        }
                    }
                }

                for (String temp : hash_Set) {
                    RequestContext.getCurrentInstance().execute("insertParticipants('" + temp + "');");
                }
                RequestContext.getCurrentInstance().execute("forwardcalljoinchat()");
            } else if (call.getCallOption().equalsIgnoreCase("audio")) {
                RequestContext.getCurrentInstance().execute("callaudio()");
                RequestContext.getCurrentInstance().execute("forwardcalljoinother()");

            } else {
                RequestContext.getCurrentInstance().execute("joinViaBrowser();forwardcalljoinother()");
            }

            forwardedCall.setActiveFlg(false);
            try {
                forwardedCall.setCallPickupTime(CustomConvert.javaDateToTimeStamp(new Date()));
            } catch (ParseException ex) {
                java.util.logging.Logger.getLogger(CallStartedComponent.class.getName()).log(Level.SEVERE, null, ex);
            }
            forwardedCall = forwardedCallService.saveForwardedCalls(forwardedCall, localEmpMst);
            request.getSession().setAttribute("ForwardedCall", forwardedCall);
        } else {
            forwardedCall = (ForwardedCall) request.getSession().getAttribute("ForwardedCall");
        }
        String roomUrl = forwardedCall.getRoomLink();
        String roomName = forwardedCall.getRoomName();
        CallRecords callRecord = callRecordsService.findCallRecordsByCallIdOnly(forwardedCall.getCallId().getId());
        if (null != callRecord && null != callRecord.getRecorderId()) {
            RequestContext.getCurrentInstance().execute("showRecording();");
        } else {
            RequestContext.getCurrentInstance().execute("hideRecording();");
        }

        EmployeeMst employeeMaster = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");

        List<TenancyEmployeeMap> currentAgent = tenancyEmployeeMapService.findVidyoTenantUrlByEmpId(employeeMaster.getId());
        for (TenancyEmployeeMap currentAgent1 : currentAgent) {
            currentAgent1.setRoomLink(roomUrl);
            currentAgent1.setRoomName(roomName);
            tenancyEmployeeMapService.saveTenancyEmployeeMap(currentAgent1);
        }
        if (currentAgent.size() > 0) {
            request.getSession().setAttribute("tenancyEmployeeMap", currentAgent.get(0));
        }

        RequestContext.getCurrentInstance().execute("PF('forwardjoin').hide();");
        try {
            forwardedCallAfterHandling();
        } catch (Exception e) {
            logger.info("Exception forwardedCallAfterHandling" + e.getMessage());
        }

        try {
            joinOtherRoom(forwardedCall.getRoomLink(), forwardedCall.getCallId().getId());
        } catch (Exception e) {
            logger.info("exception in joinOtherRoom" + e.getMessage());
        }
        request.getSession().setAttribute("ForwardedCall", null);
    }

    public void joinOtherRoom(String roomUrl, Long callId) throws Exception {

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = request.getSession(false);

        ForwardedCall forwardedCall = (ForwardedCall) request.getSession().getAttribute("ForwardedCall");
        EmployeeMst forwrdingagent = employeeMstService.findEmployeeMstById(forwardedCall.getPrevEmpId());
        CallMst callMstLocal = callMstService.findCallMstById(callId);
        session.setAttribute("userNameText", callMstLocal.getCustId());
        EmployeeCallStatus employeeCallStatus = new EmployeeCallStatus();
        List<EmployeeCallStatus> employeeCallStatusList = employeecallStatusService.findEmployeeCallStatusByEmpId(forwrdingagent);

        if (!employeeCallStatusList.isEmpty()) {
            for (EmployeeCallStatus empstatus : employeeCallStatusList) {
                employeeCallStatus = empstatus;
            }
            Integer count = employeeCallStatus.getCallCount() + 1;
            employeeCallStatus.setCallCount(count);
            employeeCallStatus.setStatus(false);
            employeecallStatusService.saveEmployeeCallStatus(employeeCallStatus);
        } else {
            employeeCallStatus.setEmpId(forwrdingagent);
            employeeCallStatus.setCallCount(1);
            employeeCallStatus.setStatus(false);
            employeecallStatusService.saveEmployeeCallStatus(employeeCallStatus);
        }

        EmployeeActivityDtl employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(forwrdingagent.getId(), "Call Started");

        if (employeeActivityDtl != null) {
            employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
            employeeActivityDtlService.save(employeeActivityDtl);
        }

        employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(forwrdingagent.getId(), "Incoming Call");

        if (employeeActivityDtl != null) {
            try {
                employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));

            } catch (ParseException ex) {
                java.util.logging.Logger.getLogger(CallStartedComponent.class
                        .getName()).log(Level.SEVERE, null, ex);
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
        employeeActivityDtl = employeeActivityDtlService.save(employeeActivityDtl);
        logger.info("forwrdingagent.getLoginId():" + forwrdingagent.getLoginId() + "pickedforward#" + callMstLocal.getId());
        SocketMessage.send(callScheduler.getAdminSocket(), forwrdingagent.getLoginId(), "pickedforward#" + callMstLocal.getId());

    }

    public void forwardedCallAfterHandling() throws Exception {

        HttpServletRequest request = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest());
        EmployeeMst localEmpMst = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
        CallMst callMst = (CallMst) request.getSession().getAttribute("callMst");
        try {
            imageComponent.getCustomerData();
            imageComponent.getPreviousChatHistory();
        } catch (Exception e) {
        }

        vidyoAgent.setHangupenabled(false);
        vidyoAgent.setDialenabled(true);
        vidyoAgent.setHangupenabled(false);
        vidyoAgent.setHoldenabled(false);
        vidyoAgent.setThreewayenabled(false);
        vidyoAgent.setForwardenabled(false);
        vidyoAgent.setNotreadyenabled(true);
        vidyoAgent.setAgentStatus("Busy");
        vidyoAgent.setLogoutenabled(true);
        vidyoAgent.setAgentColor("background:red");
        vidyoAgent.setRecordingStatus("Recording....");
        vidyoAgent.setForwardedcall(true);
        vidyoAgent.setIworkImage(true);
        vidyoAgent.setSelfviewenabled(true);

        EmployeeCallStatus employeeCallStatus = new EmployeeCallStatus();

        List<EmployeeCallStatus> employeeCallStatusList = employeecallStatusService.findEmployeeCallStatusByEmpId(localEmpMst);

        if (!employeeCallStatusList.isEmpty()) {
            for (EmployeeCallStatus empstatus : employeeCallStatusList) {
                employeeCallStatus = empstatus;
            }
            Integer count = employeeCallStatus.getCallCount() + 1;
            employeeCallStatus.setCallCount(count);
            employeeCallStatus.setStatus(false);
            employeecallStatusService.saveEmployeeCallStatus(employeeCallStatus);
        } else {
            employeeCallStatus.setEmpId(localEmpMst);
            employeeCallStatus.setCallCount(1);
            employeeCallStatus.setStatus(false);
            employeecallStatusService.saveEmployeeCallStatus(employeeCallStatus);
        }

        CallMst localcallMst = callMstService.findCallMstById(callMst.getId());
        CallDtl callDtl = new CallDtl();
        callDtl.setCallMstId(localcallMst);
        callDtl.setActiveFlg(true);
        callDtl.setDeleteFlg(false);
        callDtl.setHandeledById(localEmpMst);
        try {
            callDtl.setStartTime(CustomConvert.javaDateToTimeStamp(new Date()));

        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(CallStartedComponent.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        callDtl.setCallTypeInfo("Forward");
        callDtlService.saveCallDtl(callDtl);
        if (localEmpMst != null) {
            EmployeeMst currentLocalEmpMst = employeeMstService.findEmployeeMstById(localEmpMst.getId());
            if (currentLocalEmpMst != null) {
                if (currentLocalEmpMst.getEmpTypId().getTypeName().equalsIgnoreCase("ScheduleAgent")) {

                    CustomerMst localCustomerMst = customerMstService.findCustomerMstByCustId(localcallMst.getCustId());
                    if (localCustomerMst != null && localCustomerMst.getAccountNo() != null) {
                        CustomerLoanDtl cld = customerLoanDtlService.findIDByPhoneNo(String.valueOf(localCustomerMst.getCellPhone()));
                        if (null != cld) {
                            vidyoAgent.setLoanCustomer(true);
                            vidyoAgent.setLoanCustomerId(cld.getId());
                        }
                        vidyoAgent.setExistingCustomer(true);
                    }

                }
            }
        }
        request.getSession().setAttribute("callMst", localcallMst);

        EmployeeActivityDtl employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(localEmpMst.getId(), "NEXT CALL");
        if (employeeActivityDtl != null) {
            try {
                employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));

            } catch (ParseException ex) {
                java.util.logging.Logger.getLogger(CallStartedComponent.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            employeeActivityDtlService.save(employeeActivityDtl);

        }
        employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(localEmpMst.getId(), "HOLD CALL");
        if (employeeActivityDtl != null) {
            try {
                employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));

            } catch (ParseException ex) {
                java.util.logging.Logger.getLogger(CallStartedComponent.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            employeeActivityDtlService.save(employeeActivityDtl);

        }

    }

    public void resetbutton(HttpServletRequest request) {
        imageComponent.setCategory("");
        imageComponent.setCustAcctNo("");
        imageComponent.setCustNm("");
        imageComponent.setFileLocation("");
        imageComponent.setService("");
        imageComponent.setRealPath("");
        imageComponent.setLanguage("");

        vidyoAgent.setHangupenabled(true);
        vidyoAgent.setAvailableenabled(false);
        vidyoAgent.setDialenabled(false);
        vidyoAgent.setHoldenabled(true);
        vidyoAgent.setThreewayenabled(true);
        vidyoAgent.setForwardenabled(true);
        vidyoAgent.setNotreadyenabled(false);
        vidyoAgent.setAgentStatus("Ready");
        vidyoAgent.setAvailableenabled(false);
        vidyoAgent.setLogoutenabled(false);
        vidyoAgent.setAgentColor("");
        vidyoAgent.setRecordingStatus("");
        vidyoAgent.setIworkImage(false);
        vidyoAgent.setSelfviewenabled(false);

        request.getSession();
        vidyoAgent.AfterForwardHangUp(request);

    }

    public void checkForwardCallReceive(HttpServletRequest request) {
        logger.info("In checkForwardCallReceive");
        EmployeeMst localEmpMst = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");

        EmployeeMst forwardedAgent = (EmployeeMst) request.getSession().getAttribute("forwardedAgent");
        CallMst callMstr = (CallMst) request.getSession().getAttribute("callMst");
        this.hangupcalled = true;
        vidyoAgent.setHangupcalled(true);
        List<CallDtl> callDtllist = callDtlService.findNonDeletedCallDtlByCallId(callMstr);
        CallDtl callDtllocal = null;
        if (!callDtllist.isEmpty()) {
            callDtllocal = callDtllist.get(0);

        }

        if (callDtllocal != null) {
            if (forwardedAgent.getId().equals(callDtllocal.getHandeledById().getId())) {

                logger.info("===>Call is received by forwarded agent");
                RequestContext.getCurrentInstance().execute("PF('forfailure').hide();");
                RequestContext.getCurrentInstance().execute("PF('callforwarding').hide();");
                RequestContext.getCurrentInstance().execute("stopIntervalforForwardResponse();");
                RequestContext.getCurrentInstance().execute("stopAudio();");

                RequestContext.getCurrentInstance().execute("forwardingCall();");
                RequestContext.getCurrentInstance().execute("PF('forsuccess').show();");
                RequestContext.getCurrentInstance().execute("disconnectCall();");

                RequestContext.getCurrentInstance().execute("hitNotReadyForward();");
                String ss = localEmpMst.getLoginId() + "~" + localEmpMst.getFirstName() + " " + localEmpMst.getLastName();
                RequestContext.getCurrentInstance().execute("sendForwardSuccessAcknowledgement('" + callMstr.getCustId() + "','" + ss + "')");

                /*for chat saving by apurba*/
//                RequestContext.getCurrentInstance().execute("chatSave();");
                this.hangupcalled = true;
                vidyoAgent.setHangupcalled(true);

            }

        }

    }

    public void showNotMockReadyReasonsForward(HttpServletRequest request) {
        logger.info("In showNotMockReadyReasonsForward");
        this.hangupcalled = true;
        vidyoAgent.setHangupcalled(true);
        EmployeeMst localEmpMst = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
        vidyoAgent.setAgentStatus("Ready");
        vidyoAgent.setAgentColor("");
        vidyoAgent.setHangupenabled(true);
        vidyoAgent.setDialenabled(true);
        vidyoAgent.setHoldenabled(true);
        vidyoAgent.setThreewayenabled(true);
        vidyoAgent.setForwardenabled(true);
        vidyoAgent.setNotreadyenabled(true);
        vidyoAgent.setSelfviewenabled(false);
        vidyoAgent.setAvailableenabled(false);
        vidyoAgent.setLogoutenabled(false);

        request.getSession();

        ForwardedCall forwardedCalllocal = forwardedCallService.findActiveForwardedCallByEmployeeMst(localEmpMst);
        if (forwardedCalllocal != null) {
            request.getSession().setAttribute("callMst", null);
            RequestContext.getCurrentInstance().execute("PF('missfor').show();");
            forwardedCalllocal.setActiveFlg(false);
            forwardedCalllocal.setForwardStatus("missed");
            forwardedCalllocal = forwardedCallService.saveForwardedCalls(forwardedCalllocal, localEmpMst);
            //TO-DO 09-11-2020 VD-MISSES MULTY WAY SOCKET TO BE IMPLEMENT
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
                    java.util.logging.Logger.getLogger(CallStartedComponent.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            List<EmployeeCallStatus> empClStatusList = employeeCallStatusService.findEmployeeCallStatusByEmpId(localEmpMst);

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
                        java.util.logging.Logger.getLogger(CallStartedComponent.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    employeeCallStatusService.saveEmployeeCallStatus(empCallStatus);
                }
            } else {
                empCallStatus = new EmployeeCallStatus();
                empCallStatus.setEmpId(localEmpMst);
                empCallStatus.setCallCount(1);
                empCallStatus.setStatus(false);
                try {
                    empCallStatus.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                } catch (ParseException ex) {
                    java.util.logging.Logger.getLogger(CallStartedComponent.class.getName()).log(Level.SEVERE, null, ex);
                }
                employeeCallStatusService.saveEmployeeCallStatus(empCallStatus);
            }
            request.getSession().setAttribute("callMst", null);
            request.getSession().setAttribute("incomingCallType", null);

        }

    }

    public String getPopUpMessage() {
        return popUpMessage;
    }

    public void setPopUpMessage(String popUpMessage) {
        this.popUpMessage = popUpMessage;
    }

    public boolean getHangupcalled() {
        return hangupcalled;
    }

    public void setHangupcalled(boolean hangupcalled) {
        this.hangupcalled = hangupcalled;
    }

}
