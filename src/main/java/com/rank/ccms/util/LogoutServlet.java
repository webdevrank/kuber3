package com.rank.ccms.util;

import com.rank.ccms.entities.CallDtl;
import com.rank.ccms.entities.CallMst;
import com.rank.ccms.entities.CustomerMst;
import com.rank.ccms.entities.EmployeeActivityDtl;
import com.rank.ccms.entities.EmployeeCallStatus;
import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.entities.ForwardedCall;
import com.rank.ccms.entities.ReasonMst;
import com.rank.ccms.service.CallDtlService;
import com.rank.ccms.service.CallMstService;
import com.rank.ccms.service.CustomerMstService;
import com.rank.ccms.service.EmployeeActivityDtlService;
import com.rank.ccms.service.EmployeeCallStatusService;
import com.rank.ccms.service.ForwardedCallService;
import com.rank.ccms.service.ReasonMstService;
import com.rank.ccms.web.CallRecordsComponent;
import com.rank.ccms.web.EmployeeLoginComponent;
import com.rank.ccms.web.VidyoAgent;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

@WebServlet(name = "LogoutServlet", urlPatterns = {"/LogoutServlet"})
public class LogoutServlet extends HttpServlet {

    @Autowired
    private CallMstService callMstService;
    @Autowired
    private CallDtlService callDtlService;
    @Autowired
    EmployeeActivityDtlService employeeActivityDtlService;
    @Autowired
    ReasonMstService reasonMstService;
    @Autowired
    EmployeeCallStatusService employeeCallStatusService;
    @Autowired
    private VidyoAgent vidyoAgent;
    @Autowired
    private CallRecordsComponent callRecordsComponent;
    @Autowired
    CallScheduler callScheduler;
    @Autowired
    EmployeeLoginComponent employeeLoginComponent;
    @Autowired
    CustomerMstService customerMstService;
    @Autowired
    ForwardedCallService forwardedCallService;

    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(LogoutServlet.class);

    @Override
    public void init(ServletConfig config) {
        try {
            super.init(config);
        } catch (ServletException ex) {
            Logger.getLogger(LogoutServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,
                config.getServletContext());
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getSession().setAttribute("ormAdminMaster", null);
        try {
            Thread.sleep(2000);

        } catch (InterruptedException ex) {
            Logger.getLogger(LogoutServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.setContentType("text/html;charset=UTF-8");

        CallMst clMst = (CallMst) request.getSession().getAttribute("callMst");
        if (null != clMst) {
            clMst = callMstService.findCallMstById(clMst.getId());
        }
        EmployeeMst employeeMaster = (EmployeeMst) request.getSession().getAttribute("ormAgentMaster");

        if (null == clMst) {

        } else {
            logger.info("LogoutServlet called.....for callId " + clMst.getId());
            try {

                EmployeeActivityDtl employeeActivityDtl = new EmployeeActivityDtl();
                ReasonMst lReasonMst = reasonMstService.findReasonMstByReasonCode("ABN01");
                employeeActivityDtl.setActivity("Hang Up");
                employeeActivityDtl.setEmpId(employeeMaster);
                employeeActivityDtl.setStartTime(CustomConvert.javaDateToTimeStamp(new Date()));
                employeeActivityDtl.setReasonId(lReasonMst);
                employeeActivityDtl.setCallMstId(clMst.getId());

                employeeActivityDtlService.save(employeeActivityDtl);

                clMst = callMstService.findCallMstById(clMst.getId());
                CustomerMst cm = customerMstService.findCustomerMstByCustomerId(clMst.getCustId());
                if (cm != null) {
                    logger.info("hangup#");
                    if (clMst.getCustomerHangupTime() == null) {
                        SocketMessage.send(callScheduler.getAdminSocket(), cm.getCellPhone().toString(), "hangup#");
                    }

                }

            } catch (Exception ex) {
                Logger.getLogger(VidyoAgent.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (clMst.getEndTime() == null) {

                vidyoAgent.setAgentStatus("Ready");
                vidyoAgent.setAgentColor("");
                vidyoAgent.setHangupenabled(true);

                vidyoAgent.setAvailableenabled(false);
                vidyoAgent.setDialenabled(true);
                vidyoAgent.setForwardenabled(true);
                vidyoAgent.setHoldenabled(true);
                vidyoAgent.setThreewayenabled(true);
                vidyoAgent.setForwardedcall(true);
                vidyoAgent.setNotreadyenabled(false);
                vidyoAgent.setLogoutenabled(false);
                vidyoAgent.setRecordingStatus("");
                vidyoAgent.setIworkImage(false);

                callRecordsComponent.setRecordingStatus("");
                vidyoAgent.setSelfviewenabled(false);

                try {
                    if (null != employeeMaster) {

                        List<CallDtl> clDtlList = callDtlService.findCallDtlByCallMasterIdAndAgent(clMst.getId(), employeeMaster.getId());
                        if (clDtlList.isEmpty()) {
                            // Nothing to Update
                        } else {
                            for (CallDtl clDtl : clDtlList) {
                                if (!clDtl.getCallTypeInfo().equalsIgnoreCase("Threeway")) {
                                    clMst.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                                    clMst.setCallStatus("ABANDONED");
                                }

                                clMst = callMstService.saveCallMst(clMst);
                                clDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                                clDtl.setAgentComments("Call Abandoned due to System Close.");
                                callDtlService.saveCallDtl(clDtl);
                            }
                        }
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(LogoutServlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(LogoutServlet.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    request.getSession().setAttribute("callMst", null);

                }
            }
        }

        try {
            if (null != employeeMaster) {
                logger.info("logout servlet called..." + employeeMaster.getLoginId() + " " + employeeMaster.getFirstName());
                Date date = new Date();

                EmployeeActivityDtl employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(employeeMaster.getId(), "login");
                ReasonMst reasonMst = reasonMstService.findReasonMstByReasonCode("SOUT");
                if (null == reasonMst) {
                    reasonMst = new ReasonMst();
                    reasonMst.setType("Logout");
                    reasonMst.setReasonCd("SOUT");
                    reasonMst.setReasonDesc("Session time over, idle timeout.");
                    reasonMst.setActiveFlg(true);
                    reasonMst.setDeleteFlg(false);
                    reasonMst = reasonMstService.save(reasonMst, employeeMaster);
                }
                if (null != employeeActivityDtl) {
                    employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(date));
                    employeeActivityDtlService.save(employeeActivityDtl, employeeMaster);
                }

                employeeActivityDtl = new EmployeeActivityDtl();
                employeeActivityDtl.setEmpId(employeeMaster);
                employeeActivityDtl.setActivity("logout");
                employeeActivityDtl.setStartTime(CustomConvert.javaDateToTimeStamp(date));
                employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(date));
                employeeActivityDtl.setRespectiveLoginId(null);
                employeeActivityDtl.setReasonId(reasonMst);

                employeeActivityDtl = employeeActivityDtlService.save(employeeActivityDtl, employeeMaster);

                if (null != employeeActivityDtl) {
                    employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(date));
                    employeeActivityDtlService.save(employeeActivityDtl, employeeMaster);

                }

                employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(employeeMaster.getId(), "Self View");
                if (employeeActivityDtl != null) {
                    employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                    employeeActivityDtlService.save(employeeActivityDtl);
                    vidyoAgent.setSelfviewenabled(false);
                }

                employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(employeeMaster.getId(), "not ready");
                if (employeeActivityDtl != null) {
                    employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(date));
                    employeeActivityDtlService.save(employeeActivityDtl);
                }

                employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(employeeMaster.getId(), "NEXT CALL");
                if (employeeActivityDtl != null) {
                    employeeActivityDtl.setEndTime(CustomConvert.javaDateToTimeStamp(date));
                    employeeActivityDtlService.save(employeeActivityDtl);

                }

                employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(employeeMaster.getId(), "Call Started");
                if (employeeActivityDtl != null) {
                    if (employeeActivityDtl.getCallMstId() != null) {
                        CallMst cl = callMstService.findCallMstById(employeeActivityDtl.getCallMstId());
                        employeeActivityDtl.setEndTime(cl.getEndTime());
                        employeeActivityDtlService.save(employeeActivityDtl);
                    }

                }

                employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(employeeMaster.getId(), "Hang Up");

                if (employeeActivityDtl != null) {
                    if (employeeActivityDtl.getCallMstId() != null) {
                        CallMst cl = callMstService.findCallMstById(employeeActivityDtl.getCallMstId());
                        employeeActivityDtl.setEndTime(cl.getEndTime());
                        employeeActivityDtlService.save(employeeActivityDtl);
                    }

                }

                employeeActivityDtl = employeeActivityDtlService.findLastNonEndedActivityByType(employeeMaster.getId(), "HOLD CALL");

                if (employeeActivityDtl != null) {
                    if (employeeActivityDtl.getCallMstId() != null) {
                        CallMst cl = callMstService.findCallMstById(employeeActivityDtl.getCallMstId());
                        employeeActivityDtl.setEndTime(cl.getEndTime());
                        employeeActivityDtlService.save(employeeActivityDtl);
                    }

                }

                // Update the Call Status to Zero
                List<EmployeeCallStatus> empCallStatusList = employeeCallStatusService.findEmployeeCallStatusByEmpId(employeeMaster);
                for (EmployeeCallStatus callStatus : empCallStatusList) {
                    //callStatus.setCallCount(0);
                    callStatus.setStatus(false);

                    employeeCallStatusService.saveEmployeeCallStatus(callStatus);
                }
                ForwardedCall forwardedCall = forwardedCallService.findActiveForwardedCallByEmployeeMst(employeeMaster);
                if (null != forwardedCall) {
                    forwardedCall.setActiveFlg(false);
                    forwardedCallService.saveForwardedCalls(forwardedCall, employeeMaster);
                }

            }

        } catch (ParseException ex) {

            logger.info("Deletion of User Connection Data Failed, though Logging Out. " + ex);
        } catch (Exception ex) {
            Logger.getLogger(LogoutServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
