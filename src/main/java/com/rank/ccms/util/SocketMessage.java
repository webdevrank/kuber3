package com.rank.ccms.util;

import org.json.JSONException;
import org.json.JSONObject;
import com.github.nkzawa.socketio.client.Socket;
import com.rank.ccms.entities.CallDtl;
import com.rank.ccms.entities.CallMst;
import com.rank.ccms.entities.CustomerMst;
import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.service.CallDtlService;
import com.rank.ccms.service.CallMstService;
import com.rank.ccms.service.CustomerMstService;
import com.rank.ccms.service.EmployeeMstService;
import com.rank.socketlib.SocketLibrary;
import com.rank.socketlib.SocketListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class SocketMessage {

    private final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(SocketMessage.class);
    private static String socketURL = null;
    @Context
    private ServletContext context;

    private CallMstService callMstService;
    private EmployeeMstService employeeMstService;
    private CallDtlService callDtlService;
    private CustomerMstService customerMstService;
    private CallScheduler callScheduler;
    private String participants;

    public SocketMessage() {
        if (null == socketURL) {
            logger.info("Called...........................SocketMessage.....................................");
            Constants cc = new Constants();
            socketURL = Constants.socketHostPublic;
        }
    }

    private Object getGenericAutoWire(String beanName) {
        logger.info(context + "context");
        if (null == context) {
            context = ContextListener.getContext();
        }

        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
        return webApplicationContext.getAutowireCapableBeanFactory().getBean(beanName);
    }

    public Socket createSocket(String agnentLoginId) {
        SocketLibrary socketLib = new SocketLibrary();
        Socket socket = socketLib.startSocket(socketURL, agnentLoginId, new SocketListener() {

            @Override
            public void onReceive(String string, JSONObject jsono) {

            }
        });
        return socket;
    }

    public Socket createAdminSocket(String adminId) {
        logger.info("socketURL................................." + socketURL);
        logger.info("adminId................................." + adminId);
        SocketLibrary socketLib = new SocketLibrary();
        Socket socket = socketLib.startSocket(socketURL, adminId, new SocketListener() {

            @Override
            public void onReceive(String event, JSONObject obj) {
                if (event.equalsIgnoreCase("private")) {

                    try {
                        if (null == callMstService) {
                            callMstService = (CallMstService) getGenericAutoWire("callMstService");
                        }
                        if (null == employeeMstService) {
                            employeeMstService = (EmployeeMstService) getGenericAutoWire("employeeMstService");
                        }
                        if (null == callDtlService) {
                            callDtlService = (CallDtlService) getGenericAutoWire("callDtlService");
                        }
                        if (null == customerMstService) {
                            customerMstService = (CustomerMstService) getGenericAutoWire("customerMstService");
                        }
                        if (null == callScheduler) {
                            callScheduler = (CallScheduler) getGenericAutoWire("callScheduler");
                        }

                        String message = obj.getString("msg");

                        String callId = message.split("#")[0];
                        String senderId = message.split("#")[1];

                        CallMst cm = callMstService.findCallMstById(Long.parseLong(callId));
                        List<CallDtl> cList = callDtlService.findCallDtlByCallMasterId(cm.getId());
                        Set<String> hash_Set = new HashSet<>();
                        if (!senderId.equals((cm.getCustId()))) {
                            if (cm.getEndTime() == null) {
                                CustomerMst cust = customerMstService.findCustomerMstByCustId(cm.getCustId());
                                hash_Set.add(cm.getCustId() + "~" + cust.getFirstName());
                            }
                        }
                        for (int i = 0; i < cList.size(); i++) {
                            if (cList.get(i).getEndTime() == null) {
                                EmployeeMst em = employeeMstService.findEmployeeMstById(cList.get(i).getHandeledById().getId());
                                if (!senderId.equals((em.getLoginId()))) {
                                    hash_Set.add(em.getLoginId() + "~" + em.getFirstName() + " " + em.getLastName());
                                }
                            }
                        }
                        String participants = "";

                        for (String temp : hash_Set) {
                            participants = participants + temp + "|";
                        }

                        callScheduler.getParticipantList(senderId, "participantlist#" + participants);

                    } catch (JSONException | NumberFormatException ex) {
                        Logger.getLogger(SocketMessage.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        });
        return socket;
    }

    public static synchronized void send(Socket socket, final String sendTo, final String msg) throws Exception {
        logger.info("Socket :" + socket + "SendTo:" + sendTo + "Message:" + msg);
        logger.info("socketURL:" + socketURL);
        try {
            JSONObject payLoad = new JSONObject();
            try {
                payLoad.put("customer", sendTo);
                payLoad.put("message", msg);
            } catch (JSONException e) {
                throw new Exception(e);
            }
            socket.emit("send message", payLoad);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public String getParticipants() {
        return participants;
    }

    public void setParticipants(String participants) {
        this.participants = participants;
    }

}
