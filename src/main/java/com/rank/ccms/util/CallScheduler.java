package com.rank.ccms.util;

import com.rank.ccms.dto.CallEmployeeMap;
import com.rank.ccms.entities.CallMst;
import com.rank.ccms.entities.CustomerMst;
import com.rank.ccms.entities.EmployeeActivityDtl;
import com.rank.ccms.entities.EmployeeCallStatus;
import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.entities.ReasonMst;
import com.rank.ccms.entities.ScheduleCall;
import com.rank.ccms.service.CallMstService;
import com.rank.ccms.service.CallSchedulerService;
import com.rank.ccms.service.CustomerMstService;
import com.rank.ccms.service.EmployeeActivityDtlService;
import com.rank.ccms.service.EmployeeCallStatusService;
import com.rank.ccms.service.EmployeeMstService;
import com.rank.ccms.service.ReasonMstService;
import com.github.nkzawa.socketio.client.Socket;
import com.rank.ccms.dto.ScheduleCallDto;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PreDestroy;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.ocpsoft.rewrite.servlet.config.Scheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

public class CallScheduler {

    private final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CallScheduler.class);
    public static List<CallEmployeeMap> listCallEmp = Collections.synchronizedList(new ArrayList<CallEmployeeMap>());
    public static List<CallEmployeeMap> listCallNotificationEmp = Collections.synchronizedList(new ArrayList<CallEmployeeMap>());
    public static List<CallEmployeeMap> listScheduleCallForEmp = Collections.synchronizedList(new ArrayList<CallEmployeeMap>());

    public static Map<String, Set<String>> customerCallDetailsMap = new HashMap<>();
    public static Map<String, Set<String>> customerCallDetailsMapDisconnected = new HashMap<>();

    List<CustomerMst> listOfCustomer = new ArrayList<>();
    List<ScheduleCall> listOfCall = new ArrayList<>();
    List<ScheduleCall> listOfCurrCall = new ArrayList<>();

    public static DefaultHttpClient getDefaultHttpClient() throws Exception {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        SSLContext ssl_ctx = SSLContext.getInstance("TLS");
        TrustManager[] certs = new TrustManager[]{new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            public void checkClientTrusted(X509Certificate[] certs, String t) {
            }

            public void checkServerTrusted(X509Certificate[] certs, String t) {
            }
        }};
        ssl_ctx.init(null, certs, new SecureRandom());
        SSLSocketFactory ssf = new SSLSocketFactory(ssl_ctx, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        ClientConnectionManager ccm = httpClient.getConnectionManager();
        SchemeRegistry sr = ccm.getSchemeRegistry();
        //sr.register(new Scheme("https", 443, ssf));
        return new DefaultHttpClient(ccm, httpClient.getParams());
    }

    @Autowired
    private CallSchedulerService callSchedulerService;

    @Autowired
    private CallMstService callMstService;

    @Autowired
    private CustomerMstService customerMstService;

    @Autowired
    private EmployeeMstService employeeMstService;

    @Autowired
    private EmployeeCallStatusService employeeCallStatusService;

    @Autowired
    private EmployeeActivityDtlService employeeActivityDtlService;

    @Autowired
    private ReasonMstService reasonMstService;

    @Autowired
    public EmployeeCallStatusService employeecallStatusService;

    private Socket adminSocket;

    @Autowired
    public void init() {
        SocketMessage sm = new SocketMessage();
        Socket employeeSkt = sm.createAdminSocket("VIDEOBANKING_SCHEDULER");
        this.setAdminSocket(employeeSkt);

        try {
            List<CallMst> callMstList = callMstService.findAllOnGoingCalls();

            List<CallMst> callMstListWaiting = callMstService.findAllWaitingCalls();
            for (CallMst callMstList1 : callMstListWaiting) {
                callMstList1.setCallStatus("No Agent Found");
                try {
                    callMstList1.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                } catch (ParseException ex) {
                    Logger.getLogger(CallScheduler.class.getName()).log(Level.SEVERE, null, ex);
                }
                callMstService.saveCallMst(callMstList1);
            }

            for (CallMst callMstList1 : callMstList) {
                if (callMstList1.getStartTime() != null) {
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(callMstList1.getStartTime());
                    cal.add(Calendar.MINUTE, 60);
                    Calendar calnow = Calendar.getInstance();
                    if (cal.after(calnow)) {
                        try {
                            callMstList1.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                            callMstList1.setCallStatus("Terminated");
                            callMstService.saveCallMst(callMstList1);
                        } catch (ParseException ex) {
                            Logger.getLogger(CallScheduler.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss:SS");
                        Timestamp ttime;
                        try {
                            ttime = new Timestamp(dateFormat.parse(dateFormat.format(cal.getTimeInMillis())).getTime());
                            callMstList1.setEndTime(ttime);
                            callMstList1.setCallStatus("Terminated");
                            callMstService.saveCallMst(callMstList1);
                        } catch (ParseException ex) {
                            Logger.getLogger(CallScheduler.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }

            List<EmployeeCallStatus> employeeCallStatusList = employeeCallStatusService.findAllEmployeeCallStatus();

            for (EmployeeCallStatus employeeCallStatusList1 : employeeCallStatusList) {
                employeeCallStatusList1.setStatus(false);
                employeeCallStatusService.saveEmployeeCallStatus(employeeCallStatusList1);
            }

            List<EmployeeActivityDtl> activityList = employeeActivityDtlService.findAllNonEndedActivity();

            if (null != activityList && !activityList.isEmpty()) {
                for (EmployeeActivityDtl activity : activityList) {
                    String act = activity.getActivity();
                    EmployeeMst employeeMst = activity.getEmpId();
                    Date date = new Date();
                    Long resLoginId;
                    if (act.equalsIgnoreCase("login")) {
                        try {
                            ReasonMst reasonMst = reasonMstService.findReasonMstByReasonCode("SOUT");
                            if (null == reasonMst) {
                                reasonMst = new ReasonMst();
                                reasonMst.setType("Logout");
                                reasonMst.setReasonCd("SOUT");
                                reasonMst.setReasonDesc("ReInitalized");
                                reasonMst.setActiveFlg(true);
                                reasonMst.setDeleteFlg(false);
                                reasonMst = reasonMstService.save(reasonMst, employeeMst);
                            }
                            resLoginId = activity.getId();

                            activity.setEndTime(CustomConvert.javaDateToTimeStamp(date));

                            employeeActivityDtlService.save(activity, employeeMst);

                            EmployeeActivityDtl employeeActivityDtlL = new EmployeeActivityDtl();
                            employeeActivityDtlL.setEmpId(employeeMst);
                            employeeActivityDtlL.setActivity("logout");
                            employeeActivityDtlL.setStartTime(CustomConvert.javaDateToTimeStamp(date));
                            employeeActivityDtlL.setEndTime(CustomConvert.javaDateToTimeStamp(date));
                            employeeActivityDtlL.setRespectiveLoginId(resLoginId);
                            employeeActivityDtlL.setReasonId(reasonMst);
                            employeeActivityDtlL.setReasonCd(reasonMst.getReasonCd());
                            employeeActivityDtlL.setReasonDesc(reasonMst.getReasonDesc());
                            employeeActivityDtlL = employeeActivityDtlService.save(employeeActivityDtlL, employeeMst);

                            if (null != employeeActivityDtlL) {
                                employeeActivityDtlL.setEndTime(CustomConvert.javaDateToTimeStamp(date));
                                employeeActivityDtlL.setNotification(null);
                                employeeActivityDtlService.save(employeeActivityDtlL, employeeMst);

                            }
                        } catch (ParseException ex) {
                            Logger.getLogger(CallScheduler.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    if (act.equalsIgnoreCase("Self View")) {
                        try {
                            activity.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                            employeeActivityDtlService.save(activity);
                        } catch (ParseException ex) {
                            Logger.getLogger(CallScheduler.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    if (act.equalsIgnoreCase("not ready")) {
                        try {
                            activity.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                            employeeActivityDtlService.save(activity);
                        } catch (ParseException ex) {
                            Logger.getLogger(CallScheduler.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    if (act.equalsIgnoreCase("NEXT CALL")) {
                        try {
                            activity.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                            employeeActivityDtlService.save(activity);
                        } catch (ParseException ex) {
                            Logger.getLogger(CallScheduler.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    if (act.equalsIgnoreCase("Call Started")) {
                        try {
                            activity.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                            employeeActivityDtlService.save(activity);
                        } catch (ParseException ex) {
                            Logger.getLogger(CallScheduler.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    if (act.equalsIgnoreCase("Hang Up")) {
                        try {
                            activity.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                            employeeActivityDtlService.save(activity);
                        } catch (ParseException ex) {
                            Logger.getLogger(CallScheduler.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    if (act.equalsIgnoreCase("HOLD CALL")) {
                        try {
                            activity.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                            employeeActivityDtlService.save(activity);
                        } catch (ParseException ex) {
                            Logger.getLogger(CallScheduler.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }

        } catch (Exception e) {
            logger.error("Exception ", e);
        }

    }

    @PreDestroy
    public void disconnectAdminSocket() {
        getAdminSocket().disconnect();
        logger.info("pre destroy............................ ");
    }

    public void getParticipantList(String senderId, String message) {
        try {

            SocketMessage.send(getAdminSocket(), senderId, message);
        } catch (Exception ex) {
            Logger.getLogger(CallScheduler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Scheduled(cron = "*/60 * * * * ?")
    public void sendNotificationMailRM() {
        try {
            //before 15 min
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss:SS");
            int notiFicationBefore = Integer.parseInt(Constants.SCHEDULE_NOTIFICATION_TIME);
            Date date = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.MINUTE, notiFicationBefore);
            cal.add(Calendar.SECOND, 15);
            Calendar cal1 = Calendar.getInstance();
            cal1.setTime(date);
            cal1.add(Calendar.MINUTE, notiFicationBefore);

            Timestamp fromTime = new Timestamp(dateFormat.parse(dateFormat.format(cal.getTimeInMillis())).getTime());
            Timestamp totime = new Timestamp(dateFormat.parse(dateFormat.format(cal1.getTimeInMillis())).getTime());
            listOfCall = callSchedulerService.findAllNonTakenScheduleCallByTimeRange(fromTime, totime);

            EmployeeMst employeeMst = null;
            String messageBody;
            for (ScheduleCall sc : listOfCall) {
                List<ScheduleCallDto> tmpScheduledCallDtlDtoList = callSchedulerService.findScheduledCallDtlsByScheduledMstID(sc.getId());
                CustomerMst l_customerMst = customerMstService.findCustomerMstById(sc.getCustomerId().getId());
                logger.info("l_customerMst Before 15 Min:" + l_customerMst);
                Long rmId = null;
                for (ScheduleCallDto scheduleCallDto : tmpScheduledCallDtlDtoList) {
                    rmId = scheduleCallDto.getEmployeeMstId();
                    employeeMst = employeeMstService.findEmployeeMstById(rmId);
                    try {
                        logger.info(employeeMst.getLoginId() + "schheduleNotiBeforeJoin#" + sc.getScheduleDate());
                        SocketMessage.send(getAdminSocket(), employeeMst.getLoginId(), "schheduleNotiBeforeJoin#" + sc.getScheduleDate());
                    } catch (Exception ex) {
                        logger.error(ex);
                    }
                    break;
                }

                logger.info("employeeMst Before 15 Min:" + employeeMst);

                CallEmployeeMap callEmployeeMap = new CallEmployeeMap();
                callEmployeeMap.setScheduleCallId(sc.getId());
                callEmployeeMap.setCustId(l_customerMst.getCustId());
                callEmployeeMap.setEmployeeId(rmId);
                listCallNotificationEmp.add(callEmployeeMap);
                //Email Code Goes Here

                //Mail Send TO customer
                messageBody = "<html><body>Dear &nbsp; Customer ";
                messageBody += ",&nbsp;<br>You have a scheduled meeting with your Relationship Manager at " + sc.getScheduleDate() + " .";
                messageBody += "<br>";
                messageBody += "<b>Note:</b>Please do not refresh the browser during the call.";
                messageBody += "<br></br>";
                messageBody += SendingMailUtil.TELE_THX_HTML;
                messageBody += "</body><html/>";
                logger.info("Before Send Email...");
                boolean mailSend = false;
                try {
                    mailSend = SendingMailUtil.sendEMail(l_customerMst.getEmail(), messageBody, SendingMailUtil.SCHEDULE_CALL);
                } catch (Exception ex) {
                    logger.error(ex);
                }
                logger.info("After Send Email...");
                if (mailSend) {
                    logger.info("mail sending was successfull... to Customer:" + l_customerMst.getEmail());
                }

            }

            //before 10 min 
            date = new Date();
            cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.MINUTE, notiFicationBefore - 5);
            cal.add(Calendar.SECOND, 15);
            cal1 = Calendar.getInstance();
            cal1.setTime(date);
            cal1.add(Calendar.MINUTE, notiFicationBefore - 5);
            fromTime = new Timestamp(dateFormat.parse(dateFormat.format(cal.getTimeInMillis())).getTime());
            totime = new Timestamp(dateFormat.parse(dateFormat.format(cal1.getTimeInMillis())).getTime());
            listOfCall = callSchedulerService.findAllNonTakenScheduleCallByTimeRange(fromTime, totime);

            for (ScheduleCall sc : listOfCall) {
                List<ScheduleCallDto> tmpScheduledCallDtlDtoList = callSchedulerService.findScheduledCallDtlsByScheduledMstID(sc.getId());
                CustomerMst l_customerMst = customerMstService.findCustomerMstById(sc.getCustomerId().getId());
                logger.info("l_customerMst 10 min remain:" + l_customerMst);
                Long rmId = null;
                for (ScheduleCallDto scheduleCallDto : tmpScheduledCallDtlDtoList) {
                    rmId = scheduleCallDto.getEmployeeMstId();
                    employeeMst = employeeMstService.findEmployeeMstById(rmId);
                    try {
                        logger.info(employeeMst.getLoginId() + "schheduleNotiBeforeJoin#" + sc.getScheduleDate());
                        SocketMessage.send(getAdminSocket(), employeeMst.getLoginId(), "schheduleNotiBeforeJoin#" + sc.getScheduleDate());
                    } catch (Exception ex) {
                        logger.error(ex);
                    }
                    break;
                }
                logger.info("employeeMst 10 min remain:" + employeeMst);
                //check if already there on list
                boolean isPrev = false;
                if (null != listCallNotificationEmp) {
                    for (CallEmployeeMap callEmployeeMap : listCallNotificationEmp) {
                        if (Objects.equals(callEmployeeMap.getScheduleCallId(), sc.getId()) && Objects.equals(callEmployeeMap.getEmployeeId(), rmId)) {
                            isPrev = true;
                        }
                    }
                }
                if (!isPrev) {
                    CallEmployeeMap callEmployeeMap = new CallEmployeeMap();
                    callEmployeeMap.setScheduleCallId(sc.getId());
                    callEmployeeMap.setCustId(l_customerMst.getCustId());
                    callEmployeeMap.setEmployeeId(rmId);
                    listCallNotificationEmp.add(callEmployeeMap);
                }
            }

            //before 5 min 
            date = new Date();
            cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.MINUTE, notiFicationBefore - 10);
            cal.add(Calendar.SECOND, 15);
            cal1 = Calendar.getInstance();
            cal1.setTime(date);
            cal1.add(Calendar.MINUTE, notiFicationBefore - 10);
            fromTime = new Timestamp(dateFormat.parse(dateFormat.format(cal.getTimeInMillis())).getTime());
            totime = new Timestamp(dateFormat.parse(dateFormat.format(cal1.getTimeInMillis())).getTime());
            listOfCall = callSchedulerService.findAllNonTakenScheduleCallByTimeRange(fromTime, totime);
            for (ScheduleCall sc : listOfCall) {
                List<ScheduleCallDto> tmpScheduledCallDtlDtoList = callSchedulerService.findScheduledCallDtlsByScheduledMstID(sc.getId());
                CustomerMst l_customerMst = customerMstService.findCustomerMstById(sc.getCustomerId().getId());
                logger.info("l_customerMst 5 min remain:" + l_customerMst);

                Long rmId = null;
                for (ScheduleCallDto scheduleCallDto : tmpScheduledCallDtlDtoList) {
                    rmId = scheduleCallDto.getEmployeeMstId();
                    employeeMst = employeeMstService.findEmployeeMstById(rmId);

                    try {
                        logger.info(employeeMst.getLoginId() + "schheduleNotiBeforeJoin#" + sc.getScheduleDate());
                        SocketMessage.send(getAdminSocket(), employeeMst.getLoginId(), "schheduleNotiBeforeJoin#" + sc.getScheduleDate());
                    } catch (Exception ex) {
                        logger.error(ex);
                    }
                    break;
                }
                logger.info("employeeMst 5 min remain:" + employeeMst);
                //check if already there on list
                boolean isPrev = false;
                if (null != listCallNotificationEmp) {
                    for (CallEmployeeMap callEmployeeMap : listCallNotificationEmp) {
                        if (Objects.equals(callEmployeeMap.getScheduleCallId(), sc.getId()) && Objects.equals(callEmployeeMap.getEmployeeId(), rmId)) {
                            isPrev = true;
                        }
                    }
                }
                if (!isPrev) {
                    CallEmployeeMap callEmployeeMap = new CallEmployeeMap();
                    callEmployeeMap.setScheduleCallId(sc.getId());
                    callEmployeeMap.setCustId(l_customerMst.getCustId());
                    callEmployeeMap.setEmployeeId(rmId);
                    listCallNotificationEmp.add(callEmployeeMap);
                }
            }

        } catch (NumberFormatException | ParseException e) {
            logger.error("Got Some Errror @ sendFeedFromCacheRealTime" + e.getMessage());
        }

    }

    public Socket getAdminSocket() {
        return adminSocket;
    }

    public void setAdminSocket(Socket adminSocket) {
        this.adminSocket = adminSocket;
    }

}
