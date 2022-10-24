package com.rank.ccms.web;

import com.rank.ccms.entities.CallMst;
import com.rank.ccms.entities.CategoryMst;
import com.rank.ccms.entities.EmployeeCallStatus;
import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.entities.EmployeeTypeMst;
import com.rank.ccms.entities.ForwardedCall;
import com.rank.ccms.entities.LanguageMst;
import com.rank.ccms.entities.ServiceMst;
import com.rank.ccms.entities.TenancyEmployeeMap;
import com.rank.ccms.service.AgentFindingService;
import com.rank.ccms.service.CallMstService;
import com.rank.ccms.service.CategoryMstService;
import com.rank.ccms.service.EmployeeCallStatusService;
import com.rank.ccms.service.EmployeeMstService;
import com.rank.ccms.service.EmployeeTypeMstService;
import com.rank.ccms.service.ForwardedCallService;
import com.rank.ccms.service.LanguageMstService;
import com.rank.ccms.service.ServiceMstService;
import com.rank.ccms.service.TenancyEmployeeMapService;
import com.rank.ccms.util.CallScheduler;
import com.rank.ccms.util.CustomConvert;
import com.rank.ccms.util.SocketMessage;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class CallForwardComponent implements Serializable {

    private final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CallForwardComponent.class);
    private String Room_link;
    private Long Call_id;
    private Long service_id;
    private Long skill_id;
    private Long category_id;
    private List<ServiceMst> selectService = new ArrayList<>();
    private List<LanguageMst> selectSkill = new ArrayList<>();
    private List<CategoryMst> selectCategory = new ArrayList<>();
    List<EmployeeCallStatus> availableSpecialists = new ArrayList<>();
    List<EmployeeCallStatus> availableAgents = new ArrayList<>();
    private Long agentId;

    @Autowired
    private ServiceMstService serviceMstService;

    @Autowired
    private LanguageMstService skillMstService;

    @Autowired
    private CategoryMstService categoryMstService;

    @Autowired
    private ForwardedCallService forwardedCallService;

    @Autowired
    private TenancyEmployeeMapService tenancyEmployeeMapService;

    @Autowired
    private AgentFindingService agentFindingService;

    @Autowired
    private EmployeeMstService employeeMstService;

    @Autowired
    private EmployeeTypeMstService employeeTypeMstService;

    @Autowired
    private EmployeeCallStatusService employeeCallStatusService;

    @Autowired
    private CallMstService callMstService;

    @Autowired
    CallScheduler callScheduler;

    public void newCallForwardComponent(HttpServletRequest request) {

        selectService = serviceMstService.findAllNonDeletedServiceMsts();
        selectSkill = skillMstService.findAllNonDeletedLanguageMsts();
        selectCategory = categoryMstService.findAllNonDeletedCategoryMsts();
        if (!selectService.isEmpty()) {
            this.setService_id(selectService.get(0).getId());
            RequestContext.getCurrentInstance().execute("PF('forwardDlg').show();");
        }
    }

    public void availlableAgentForForwardPoll(HttpServletRequest request) {
        try {
            List<EmployeeCallStatus> availableAgentsL = availableAgents;
            availableAgents = employeeCallStatusService.findFreeOnlineAgents();
            if (availableAgentsL.size() == availableAgents.size()) {
                if (!availableAgentsL.equals(availableAgents)) {
                    RequestContext.getCurrentInstance().update("forwardNameId");
                }
            } else {
                RequestContext.getCurrentInstance().update("forwardNameId");
            }
            if (!availableAgents.isEmpty()) {
                if (agentId != null) {
                    this.setAgentId(agentId);
                }
            }

        } catch (Exception e) {
            logger.error("Exception ", e);
        }

    }

    public void forwardByGroup(HttpServletRequest request) throws ParseException {

        Timestamp dt_time = CustomConvert.javaDateToTimeStamp(new Date());
        CallMst callmst = (CallMst) request.getSession().getAttribute("callMst");

        if (null != callmst) {

            callmst = callMstService.findCallMstById(callmst.getId());

            Set set = agentFindingService.findAgents(categoryMstService.findCategoryMstById(callmst.getCategoryId()).getCatgCd(), serviceMstService.findNonDeletedServiceMstById(this.service_id).getServiceCd(), skillMstService.findAllLanguageMstById(callmst.getLanguageId()).getLanguageCd());

            List<Object> L_E_Mst = new ArrayList<>(set);

            if (L_E_Mst.size() > 0) {
                EmployeeMst em = (EmployeeMst) L_E_Mst.get(0);
                request.getSession().setAttribute("forwardedAgent", em);

                List<EmployeeCallStatus> empClStatusList = employeeCallStatusService.findEmployeeCallStatusByEmpId(em);
                EmployeeCallStatus empCallStatus = new EmployeeCallStatus();

                if (!empClStatusList.isEmpty()) {
                    for (EmployeeCallStatus empstatus : empClStatusList) {
                        empCallStatus = empstatus;
                    }

                    empCallStatus.setCallCount(0);
                    empCallStatus.setStatus(false);
                    employeeCallStatusService.saveEmployeeCallStatus(empCallStatus);
                } else {
                    empCallStatus.setEmpId(em);
                    empCallStatus.setCallCount(0);
                    empCallStatus.setStatus(false);
                    employeeCallStatusService.saveEmployeeCallStatus(empCallStatus);
                }

                EmployeeMst CurrEmp = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
                List<TenancyEmployeeMap> tenancyEmployeeMaplist = tenancyEmployeeMapService.findVidyoTenantUrlByEmpId(CurrEmp.getId());
                List<Long> unsortList = new ArrayList<>();
                for (TenancyEmployeeMap tenancyEmployeeMaplist1 : tenancyEmployeeMaplist) {
                    unsortList.add(tenancyEmployeeMaplist1.getId());
                }
                Collections.sort(unsortList);
                TenancyEmployeeMap tenancyEmployeeMap = tenancyEmployeeMapService.findById(unsortList.get(unsortList.size() - 1));
                String roomLink = tenancyEmployeeMap.getRoomLink();
                String entityId = tenancyEmployeeMap.getEntityId();
                String roomName = tenancyEmployeeMap.getRoomName();

                Call_id = callmst.getId();

                ForwardedCall forwardedCall = new ForwardedCall();
                forwardedCall.setEmpId(em);
                forwardedCall.setRoomLink(roomLink);
                forwardedCall.setRoomName(roomName);
                forwardedCall.setEntityId(entityId);
                forwardedCall.setCallId(callmst);
                forwardedCall.setForwardedDatetime(dt_time);
                forwardedCall.setForwardType("group");
                forwardedCall.setActiveFlg(true);
                forwardedCall.setDeleteFlg(false);
                forwardedCall.setPrevEmpId(CurrEmp.getId());
                forwardedCall.setSelectedServiceId(serviceMstService.findNonDeletedServiceMstById(this.service_id).getId());

                ForwardedCall currForwardedCall = forwardedCallService.findForwardedCallByCallIdAndEmpId(callmst);
                if (currForwardedCall != null) {
                    forwardedCall.setFromServiceId(currForwardedCall.getSelectedServiceId());
                } else {

                    forwardedCall.setFromServiceId(callmst.getServiceId());
                }
                forwardedCallService.saveForwardedCalls(forwardedCall, CurrEmp);
                RequestContext.getCurrentInstance().execute(" $('#spinner').hide();");
                RequestContext.getCurrentInstance().execute("closeAllDialog();PF('callforwarding').show();");

                try {
                    logger.info(callScheduler.getAdminSocket() + ":" + em.getLoginId() + "forwardcallnotification#" + Call_id + "#" + CurrEmp.getFirstName() + " " + CurrEmp.getLastName() + "#" + roomLink + "#" + callmst.getCallOption());
                    SocketMessage.send(callScheduler.getAdminSocket(), em.getLoginId(), "forwardcallnotification#" + Call_id + "#" + CurrEmp.getFirstName() + " " + CurrEmp.getLastName() + "#" + roomLink + "#" + callmst.getCallOption());
                    logger.info("Forward Call Send Success!!!!!!!!!");
                } catch (Exception ex) {
                    logger.error(ex);
                }
            } else {

                RequestContext.getCurrentInstance().execute(" $('#spinner').hide();");
                RequestContext.getCurrentInstance().execute("closeAllDialog();PF('noagent').show();");

            }
        }
    }

    public void forwardByName(HttpServletRequest request) throws Exception {
        RequestContext.getCurrentInstance().execute("PF('forwardDlgName').hide();");
        RequestContext.getCurrentInstance().execute("$('#spinner').show();");
        Random random = new Random();
        int sleepTime = random.nextInt(20) * 100;
        Thread.sleep(sleepTime);

        synchronized (this) {

            Timestamp dt_time = CustomConvert.javaDateToTimeStamp(new Date());

            CallMst callmst = (CallMst) request.getSession().getAttribute("callMst");
            logger.info("Trying Forwarding(forwardByName) for Call id=======================>" + callmst.getId());

            EmployeeCallStatus empCurrentStatus = employeeCallStatusService.findEmployeeCurrentCallStatusByEmpId(this.agentId);

            if (empCurrentStatus != null) {

                EmployeeMst empmst = employeeMstService.findEmployeeMstById(this.agentId);
                if (empmst != null) {
                    request.getSession().setAttribute("forwardedAgent", empmst);

                    List<EmployeeCallStatus> empClStatusList = employeeCallStatusService.findEmployeeCallStatusByEmpId(empmst);
                    EmployeeCallStatus empCallStatus = new EmployeeCallStatus();
                    if (!empClStatusList.isEmpty()) {
                        for (EmployeeCallStatus empstatus : empClStatusList) {
                            empCallStatus = empstatus;
                        }

                        empCallStatus.setCallCount(0);
                        empCallStatus.setStatus(false);
                        employeeCallStatusService.saveEmployeeCallStatus(empCallStatus);
                    } else {
                        empCallStatus.setEmpId(empmst);
                        empCallStatus.setCallCount(0);
                        empCallStatus.setStatus(false);

                        employeeCallStatusService.saveEmployeeCallStatus(empCallStatus);
                    }

                    EmployeeMst CurrEmp = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
                    List<TenancyEmployeeMap> tenancyEmployeeMaplist = tenancyEmployeeMapService.findVidyoTenantUrlByEmpId(CurrEmp.getId());
                    List<Long> unsortList = new ArrayList<>();
                    for (TenancyEmployeeMap tenancyEmployeeMaplist1 : tenancyEmployeeMaplist) {
                        unsortList.add(tenancyEmployeeMaplist1.getId());
                    }
                    Collections.sort(unsortList);
                    TenancyEmployeeMap tenancyEmployeeMap = tenancyEmployeeMapService.findById(unsortList.get(unsortList.size() - 1));
                    String roomLink = tenancyEmployeeMap.getRoomLink();
                    String entityId = tenancyEmployeeMap.getEntityId();
                    String roomName = tenancyEmployeeMap.getRoomName();
                    CallMst callMst = (CallMst) request.getSession().getAttribute("callMst");

                    Call_id = callMst.getId();
                    ForwardedCall forwardedCall = new ForwardedCall();
                    forwardedCall.setEmpId(empmst);
                    forwardedCall.setRoomLink(roomLink);
                    forwardedCall.setEntityId(entityId);
                    forwardedCall.setRoomName(roomName);
                    forwardedCall.setCallId(callMst);
                    forwardedCall.setForwardedDatetime(dt_time);
                    forwardedCall.setForwardStatus("Initiated");
                    forwardedCall.setActiveFlg(true);
                    forwardedCall.setDeleteFlg(false);
                    forwardedCall.setPrevEmpId(CurrEmp.getId());
                    forwardedCall.setForwardType("name");
                    forwardedCall.setSelectedServiceId(serviceMstService.findNonDeletedServiceMstById(callmst.getServiceId()).getId());

                    ForwardedCall currForwardedCall = forwardedCallService.findForwardedCallByCallIdAndEmpId(callMst);
                    if (currForwardedCall != null) {

                        forwardedCall.setFromServiceId(currForwardedCall.getSelectedServiceId());
                    } else {

                        forwardedCall.setFromServiceId(callMst.getServiceId());
                    }
                    forwardedCallService.saveForwardedCalls(forwardedCall, CurrEmp);
                    RequestContext.getCurrentInstance().execute(" $('#spinner').hide();");
                    RequestContext.getCurrentInstance().execute("PF('callforwarding').show();");

                    try {
                        logger.info(callScheduler.getAdminSocket() + ":" + empmst.getLoginId() + "forwardcallnotification#" + Call_id + "#" + CurrEmp.getFirstName() + " " + CurrEmp.getLastName() + "#" + roomLink + "#" + callmst.getCallOption());
                        SocketMessage.send(callScheduler.getAdminSocket(), empmst.getLoginId(), "forwardcallnotification#" + Call_id + "#" + CurrEmp.getFirstName() + " " + CurrEmp.getLastName() + "#" + roomLink + "#" + callmst.getCallOption());
                        logger.info("Forward Call Send Success!!!!!!!!!");
                    } catch (Exception ex) {
                        logger.error(ex);
                    }

                } else {
                    RequestContext.getCurrentInstance().execute(" $('#spinner').hide();");
                    RequestContext.getCurrentInstance().execute("PF('noagent').show();");
                }
            } else {
                RequestContext.getCurrentInstance().execute(" $('#spinner').hide();");
                RequestContext.getCurrentInstance().execute("PF('noagent').show();");
            }
        }
    }

    public void forwardToScheduleAgent(HttpServletRequest request) throws ParseException {

        Timestamp dt_time = CustomConvert.javaDateToTimeStamp(new Date());

        EmployeeMst em = null;
        EmployeeTypeMst employeeTypeMst = employeeTypeMstService.findEmployeeTypeByEmployeeTypeName("ScheduleAgent");
        List<EmployeeMst> employeeMstList = employeeMstService.findEmployeeByEmpTypeId(employeeTypeMst);
        CallMst callMst = (CallMst) request.getSession().getAttribute("callMst");
        for (EmployeeMst employeeMst : employeeMstList) {
            logger.info("employeeMst:" + employeeMst);

            List<EmployeeCallStatus> callStatusList = employeeCallStatusService.findEmployeeCallStatusByEmpId(employeeMst);
            if (!callStatusList.isEmpty()) {
                EmployeeCallStatus callStatus = callStatusList.get(0);
                if (callStatus.getStatus()) {
                    em = employeeMst;
                }
            }
        }
        if (null != em && null != callMst) {
            request.getSession().setAttribute("forwardedAgent", em);

            List<EmployeeCallStatus> empClStatusList = employeeCallStatusService.findEmployeeCallStatusByEmpId(em);
            EmployeeCallStatus empCallStatus = new EmployeeCallStatus();
            if (!empClStatusList.isEmpty()) {
                for (EmployeeCallStatus empstatus : empClStatusList) {
                    empCallStatus = empstatus;
                }

                empCallStatus.setCallCount(0);
                empCallStatus.setStatus(false);
                employeeCallStatusService.saveEmployeeCallStatus(empCallStatus);
            } else {
                empCallStatus.setEmpId(em);
                empCallStatus.setCallCount(0);
                empCallStatus.setStatus(false);
                employeeCallStatusService.saveEmployeeCallStatus(empCallStatus);
            }

            EmployeeMst CurrEmp = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
            List<TenancyEmployeeMap> tenancyEmployeeMaplist = tenancyEmployeeMapService.findVidyoTenantUrlByEmpId(CurrEmp.getId());
            List<Long> unsortList = new ArrayList<>();
            for (TenancyEmployeeMap tenancyEmployeeMaplist1 : tenancyEmployeeMaplist) {
                unsortList.add(tenancyEmployeeMaplist1.getId());
            }
            Collections.sort(unsortList);
            TenancyEmployeeMap tenancyEmployeeMap = tenancyEmployeeMapService.findById(unsortList.get(unsortList.size() - 1));
            String roomLink = tenancyEmployeeMap.getRoomLink();
            String entityId = tenancyEmployeeMap.getEntityId();
            String roomName = tenancyEmployeeMap.getRoomName();

            Call_id = callMst.getId();

            ForwardedCall forwardedCall = new ForwardedCall();
            forwardedCall.setEmpId(em);
            forwardedCall.setRoomLink(roomLink);
            forwardedCall.setRoomName(roomName);
            forwardedCall.setEntityId(entityId);
            forwardedCall.setCallId(callMst);
            forwardedCall.setForwardedDatetime(dt_time);
            forwardedCall.setActiveFlg(true);
            forwardedCall.setDeleteFlg(false);
            forwardedCall.setPrevEmpId(CurrEmp.getId());
            forwardedCall.setSelectedServiceId(serviceMstService.findNonDeletedServiceMstById(this.service_id).getId());

            ForwardedCall currForwardedCall = forwardedCallService.findForwardedCallByCallIdAndEmpId(callMst);
            if (currForwardedCall != null) {
                forwardedCall.setFromServiceId(currForwardedCall.getSelectedServiceId());
            } else {

                forwardedCall.setFromServiceId(callMst.getServiceId());
            }
            forwardedCallService.saveForwardedCalls(forwardedCall, CurrEmp);
            RequestContext.getCurrentInstance().execute(" $('#spinner').hide();");
            RequestContext.getCurrentInstance().execute("closeAllDialog();PF('callforwarding').show();");
        } else {

            RequestContext.getCurrentInstance().execute(" $('#spinner').hide();");
            RequestContext.getCurrentInstance().execute("closeAllDialog();PF('noagent').show();");
        }

    }

    public void availlableSpecialistForMultiway(HttpServletRequest request) {
        try {
            availableSpecialists = employeeCallStatusService.findFreeOnlineSpecialists();
            if (!availableSpecialists.isEmpty()) {
                RequestContext.getCurrentInstance().execute("PF('multiwayDlgName').show();");
            } else {
                RequestContext.getCurrentInstance().execute("PF('multiwayNoAgent').show();");
            }

        } catch (Exception e) {
            logger.error("Exception ", e);
        }

    }

    public void availlableSpecialistForMultiwayPoll(HttpServletRequest request) {
        try {
            List<EmployeeCallStatus> availableAgentsL = availableSpecialists;
            availableSpecialists = employeeCallStatusService.findFreeOnlineSpecialists();
            if (availableAgentsL.size() == availableSpecialists.size()) {
                if (!availableAgentsL.equals(availableSpecialists)) {
                    RequestContext.getCurrentInstance().update("multiwayNameId");
                }
            } else {
                RequestContext.getCurrentInstance().update("multiwayNameId");
            }

        } catch (Exception e) {
            logger.error("Exception ", e);
        }

    }

    public void availlableAgentForForward(HttpServletRequest request) {
        try {
            availableAgents = employeeCallStatusService.findFreeOnlineAgents();
            if (!availableAgents.isEmpty()) {
                RequestContext.getCurrentInstance().execute("PF('forwardDlgName').show();");
            } else {
                RequestContext.getCurrentInstance().execute("PF('noagent').show();");
            }

        } catch (Exception e) {
            logger.error("Exception ", e);
        }

    }

    public List<ServiceMst> getSelectService() {
        return selectService;
    }

    public void setSelectService(List<ServiceMst> selectService) {
        this.selectService = selectService;
    }

    public List<CategoryMst> getSelectCategory() {
        return selectCategory;
    }

    public void setSelectCategory(List<CategoryMst> selectCategory) {
        this.selectCategory = selectCategory;
    }

    public String getRoom_link() {
        return Room_link;
    }

    public void setRoom_link(String Room_link) {
        this.Room_link = Room_link;
    }

    public Long getCall_id() {
        return Call_id;
    }

    public void setCall_id(Long Call_id) {
        this.Call_id = Call_id;
    }

    public List<LanguageMst> getSelectSkill() {
        return selectSkill;
    }

    public void setSelectSkill(List<LanguageMst> selectSkill) {
        this.selectSkill = selectSkill;
    }

    public Long getService_id() {
        return service_id;
    }

    public void setService_id(Long service_id) {
        this.service_id = service_id;
    }

    public Long getSkill_id() {
        return skill_id;
    }

    public void setSkill_id(Long skill_id) {
        this.skill_id = skill_id;
    }

    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }

    public List<EmployeeCallStatus> getAvailableSpecialists() {
        return availableSpecialists;
    }

    public void setAvailableSpecialists(List<EmployeeCallStatus> availableSpecialists) {
        this.availableSpecialists = availableSpecialists;
    }

    public List<EmployeeCallStatus> getAvailableAgents() {
        return availableAgents;
    }

    public void setAvailableAgents(List<EmployeeCallStatus> availableAgents) {
        this.availableAgents = availableAgents;
    }

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

}
