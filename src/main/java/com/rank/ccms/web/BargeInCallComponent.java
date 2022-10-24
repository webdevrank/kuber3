package com.rank.ccms.web;

import com.rank.ccms.dto.BargeInCallDto;
import com.rank.ccms.entities.CallDtl;
import com.rank.ccms.entities.CallMst;
import com.rank.ccms.entities.CustomerMst;
import com.rank.ccms.entities.EmployeeActivityDtl;
import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.entities.EmployeeTypeMst;
import com.rank.ccms.entities.ForwardedCall;
import com.rank.ccms.entities.ScheduleCall;
import com.rank.ccms.entities.TenancyEmployeeMap;
import com.rank.ccms.service.CallDtlService;
import com.rank.ccms.service.CallMstService;
import com.rank.ccms.service.CallSchedulerService;
import com.rank.ccms.service.CustomerMstService;
import com.rank.ccms.service.EmployeeActivityDtlService;
import com.rank.ccms.service.EmployeeMstService;
import com.rank.ccms.service.EmployeeTypeMstService;
import com.rank.ccms.service.ForwardedCallService;
import com.rank.ccms.service.TenancyEmployeeMapService;
import com.rank.ccms.util.Constants;
import com.rank.ccms.vidyo.util.VidyoAccessUser;
import com.rank.ccms.ws.user.GetParticipantsResponse;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.data.PageEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class BargeInCallComponent implements Serializable {

    org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(this.getClass());
    private List<BargeInCallDto> listBargeInData = new ArrayList<>();

    @Autowired
    private EmployeeMstService employeeMstService;
    @Autowired
    private CustomerMstService customerMstService;
    @Autowired
    private TenancyEmployeeMapService tenancyEmployeeMapService;
    @Autowired
    private CallMstService callMstService;
    @Autowired
    private CallDtlService callDtlService;
    @Autowired
    private CallSchedulerService callSchedulerService;
    @Autowired
    public ForwardedCallService forwardedCallService;
    @Autowired
    public EmployeeActivityDtlService employeeActivityDtlService;
    @Autowired
    public EmployeeTypeMstService employeeTypeMstService;

    private String roomUrl;
    private Long burginPkId;
    private boolean barge = true;
    private String bargeInName = "";
    private String agentPortal;
    private Long callId;
    private String callType;
    private String chatBargeInName = "";
    private String chat = "";
    private String roomKey;
    private Integer first = 0;

    public void bargeInCallMenu() {
        first = 0;
        bargeInCall();
    }

    public void bargeInCall() {
        try {
            boolean entered;
            this.setBarge(true);
            listBargeInData.clear();
            List<BargeInCallDto> bargeInDataList = new ArrayList<>();
            List<CallMst> listCallMst = callMstService.findAllOnGoingCalls();
            EmployeeMst employeeMst = null;

            for (CallMst callMst : listCallMst) {
                entered = false;
                List<CallDtl> listCallDtl = callDtlService.findCallDtlByCallMasterId(callMst.getId());
                BargeInCallDto bargeInCallDto = new BargeInCallDto();
                if (!listCallDtl.isEmpty()) {
                    entered = true;
                    bargeInCallDto.setCallType(callMst.getCallType());
                    CallDtl callDtl = null;

                    if (listCallDtl.size() == 1) {
                        callDtl = listCallDtl.get(0);
                    } else {

                        for (CallDtl listCallDtl1 : listCallDtl) {

                            if (listCallDtl1.getCallTypeInfo().equalsIgnoreCase("Forward")) {

                                List<ForwardedCall> forwardedCallList = forwardedCallService.findForwardedCallByCallDtlId(listCallDtl1.getId());
                                if (!forwardedCallList.isEmpty()) {
                                    if (forwardedCallList.get(0).getCallPickupTime() != null) {
                                        callDtl = listCallDtl1;
                                        bargeInCallDto.setCallType("Forward Call");
                                        break;
                                    }
                                }
                            }
                        }

                    }

                    if (callDtl == null) {
                        callDtl = listCallDtl.get(listCallDtl.size() - 1);
                    }

                    employeeMst = employeeMstService.findEmployeeMstById(callDtl.getHandeledById().getId());
                    bargeInCallDto.setAgentPkId(employeeMst.getId());
                    bargeInCallDto.setAgentECN(employeeMst.getLoginId());
                    bargeInCallDto.setAgentName(employeeMst.getFirstName() + " " + employeeMst.getLastName());
                    bargeInCallDto.setCallId(callMst.getId());

                    CustomerMst customerMst = customerMstService.findCustomerMstById(callMst.getCustomerId().getId());
                    bargeInCallDto.setCustomerPkId(customerMst.getId());
                    bargeInCallDto.setCustId(customerMst.getCustId());
                    bargeInCallDto.setCustomerPhone(customerMst.getCellPhone() + "");
                    bargeInCallDto.setCustomerName(customerMst.getFirstName() + " " + customerMst.getLastName());

                    if (bargeInCallDto.getCallType().toLowerCase().startsWith("schedule".toLowerCase())) {
                        List<ScheduleCall> callSchedulerList = callSchedulerService.findScheduleCallByCustomerIdCallMstId(callMst.getId(), customerMst.getId());

                        if (callSchedulerList == null || callSchedulerList.isEmpty()) {
                            bargeInCallDto.setScheduleCallId(null);

                        } else {
                            ScheduleCall sc = callSchedulerList.get(0);
                            bargeInCallDto.setScheduleCallId(sc.getId());

                        }
                    } else {
                        bargeInCallDto.setScheduleCallId(null);

                    }

                    List<ForwardedCall> forwardedCallList = forwardedCallService.findForwardedCallByCallId(callMst);

                    if (forwardedCallList.isEmpty()) {

                        List<TenancyEmployeeMap> tenancyEmployeeMaplist = tenancyEmployeeMapService.findVidyoTenantUrlByEmpId(callDtl.getHandeledById().getId());
                        List<Long> unsortList = new ArrayList<>();
                        for (TenancyEmployeeMap tenancyEmployeeMaplist1 : tenancyEmployeeMaplist) {
                            unsortList.add(tenancyEmployeeMaplist1.getId());
                        }
                        Collections.sort(unsortList);
                        TenancyEmployeeMap tenancyEmployeeMap;
                        if (!tenancyEmployeeMaplist.isEmpty()) {
                            tenancyEmployeeMap = tenancyEmployeeMapService.findById(unsortList.get(unsortList.size() - 1));
                            bargeInCallDto.setEntityId(tenancyEmployeeMap.getEntityId());
                            bargeInCallDto.setRoomUrl(tenancyEmployeeMap.getRoomLink());
                            bargeInCallDto.setRoomKey(tenancyEmployeeMap.getRoomLink().substring(tenancyEmployeeMap.getRoomLink().lastIndexOf("/") + 1));

                        }
                    } else {

                        bargeInCallDto.setEntityId(forwardedCallList.get(0).getEntityId());
                        bargeInCallDto.setRoomUrl(forwardedCallList.get(0).getRoomLink());
                        bargeInCallDto.setRoomKey(forwardedCallList.get(0).getRoomLink().substring(forwardedCallList.get(0).getRoomLink().lastIndexOf("/") + 1));

                    }

                }

                if (entered) {
                    if (bargeInCallDto.getAgentName() != null && !bargeInCallDto.getAgentName().trim().equalsIgnoreCase("")) {
                        boolean alreadyAdded = false;
                        for (BargeInCallDto bargeInDataList1 : bargeInDataList) {
                            if (bargeInCallDto.getAgentECN().equalsIgnoreCase(bargeInDataList1.getAgentECN())) {
                                alreadyAdded = true;
                            }
                        }

                        if (!alreadyAdded) {
                            if (employeeMst != null) {

                                List<EmployeeActivityDtl> employeeActivityDtl = employeeActivityDtlService.findLastEmployeeActivityIdOfAgentByAgentId(employeeMst.getId());

                                if (employeeActivityDtl.get(0).getActivity().equalsIgnoreCase("Call Started") || employeeActivityDtl.get(0).getActivity().equalsIgnoreCase("HOLD CALL") || employeeActivityDtl.get(0).getActivity().equalsIgnoreCase("Call forwarded")) {
                                    bargeInDataList.add(bargeInCallDto);
                                }
                            }
                        }
                    }
                }
            }

            setListBargeInData(bargeInDataList);
        } catch (Exception e) {
            logger.info("Exception in barge in component " + e);
        }
    }

    public String goToRoom(String callType, Long callId, String roomlink, String entityId) {
        HttpSession session = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession();
        EmployeeMst empmst = (EmployeeMst) session.getAttribute("ormAdminMaster");

        VidyoAccessUser vidyoAccessUser = new VidyoAccessUser(Constants.vidyoportalUserServiceWSDL);

        GetParticipantsResponse getParticipantsResponse = vidyoAccessUser.getParticipants(entityId, Constants.adminUserId, Constants.adminPwd, Constants.vidyoportalUserServiceWSDL);
        boolean bargeIn = true;
        if (!getParticipantsResponse.getEntity().isEmpty()) {
            for (int i = 0; i < getParticipantsResponse.getEntity().size(); i++) {
                if (getParticipantsResponse.getEntity().get(i).getDisplayName().trim().contains("ccmsSupervisor") || getParticipantsResponse.getEntity().get(i).getDisplayName().trim().contains("ccmsAdministrator")) {
                    bargeIn = false;
                    break;
                }
            }
        }

        if (bargeIn) {
            this.setCallType(callType);
            this.setCallId(callId);
            this.setRoomUrl(roomlink);
            this.setAgentPortal(roomlink.split("/")[2]);
            this.setRoomKey(roomlink.substring(roomlink.lastIndexOf("/") + 1));
            Random rn = new Random();
            int num = rn.nextInt(1000) + 1;
            EmployeeTypeMst employeeTypeMst = employeeTypeMstService.findEmployeeTypeMstById(empmst.getEmpTypId().getId());
            
            if (employeeTypeMst.getTypeName().equalsIgnoreCase("Admin")) {
                this.setBargeInName("ccmsAdministrator~ccmsAdministrator" + num);
                this.setChatBargeInName("ccmsAdministrator" + num);
            } else {
                this.setBargeInName("ccmsSupervisor~ccmsSupervisor" + num);
                this.setChatBargeInName("ccmsSupervisor" + num);
            }
            return "/pages/agent/bargeInCall.xhtml?faces-redirect=true";
        } else {

            RequestContext.getCurrentInstance().execute("$('#spinner').hide();");
            RequestContext.getCurrentInstance().execute("PF('dupBargein').show();");
            return "";
        }
    }

    public String backToList(Long burgInPkId) {
        this.setBarge(true);

        this.burginPkId = null;
        return "/pages/agent/bargeInCallReport.xhtml?faces-redirect=true";
    }

    public String visibleRebargeRoom() {
        this.setBarge(false);
        HttpSession session = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession(false);
        EmployeeMst employeeMst = (EmployeeMst) session.getAttribute("ormAdminMaster");
        Random rn = new Random();
        int num = rn.nextInt(1000) + 1;
        this.setBargeInName(employeeMst.getLoginId() + "(ccmsSupervisor)~" + employeeMst.getLoginId() + num);
        this.setChatBargeInName(employeeMst.getLoginId() + num);

        return "/pages/agent/bargeInCall.xhtml?faces-redirect=true";
    }

    public void checkConferenceAlive(Long callId) {
        CallMst callMst = callMstService.findCallMstById(callId);
        if (callMst.getEndTime() != null || callMst.getCustomerHangupTime() != null) {
            this.barge = false;
            RequestContext.getCurrentInstance().execute("closeAllDialog();PF('bargeinend').show();");
        }
    }

    public String backToList() {
        bargeInCall();
        return "/pages/agent/bargeInCallReport.xhtml?faces-redirect=true";
    }

    public void onPageChange(PageEvent event) {
        this.setFirst(((DataTable) event.getSource()).getFirst());
    }

    public List<BargeInCallDto> getListBargeInData() {
        return listBargeInData;
    }

    public void setListBargeInData(List<BargeInCallDto> listBargeInData) {
        this.listBargeInData = listBargeInData;
    }

    public Long getBurginPkId() {
        return burginPkId;
    }

    public void setBurginPkId(Long burginPkId) {
        this.burginPkId = burginPkId;
    }

    public String getRoomUrl() {
        return roomUrl;
    }

    public void setRoomUrl(String roomUrl) {
        this.roomUrl = roomUrl;
    }

    public boolean isBarge() {
        return barge;
    }

    public void setBarge(boolean barge) {
        this.barge = barge;
    }

    public String getBargeInName() {
        return bargeInName;
    }

    public void setBargeInName(String bargeInName) {
        this.bargeInName = bargeInName;
    }

    public Long getCallId() {
        return callId;
    }

    public void setCallId(Long callId) {
        this.callId = callId;
    }

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    public String getChatBargeInName() {
        return chatBargeInName;
    }

    public void setChatBargeInName(String chatBargeInName) {
        this.chatBargeInName = chatBargeInName;
    }

    public String getChat() {
        return chat;
    }

    public void setChat(String chat) {
        this.chat = chat;
    }

    public String getAgentPortal() {
        return agentPortal;
    }

    public void setAgentPortal(String agentPortal) {
        this.agentPortal = agentPortal;
    }

    public String getRoomKey() {
        return roomKey;
    }

    public void setRoomKey(String roomKey) {
        this.roomKey = roomKey;
    }

    public Integer getFirst() {
        return first;
    }

    public void setFirst(Integer first) {
        this.first = first;
    }

}
