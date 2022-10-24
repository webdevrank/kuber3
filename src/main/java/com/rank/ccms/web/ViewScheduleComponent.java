package com.rank.ccms.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.rank.ccms.dto.ScheduleCallDto;
import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.entities.ScheduleCall;
import com.rank.ccms.service.CallSchedulerService;
import com.rank.ccms.service.ScheduleCallDtlsService;
import com.rank.ccms.service.ServiceMstService;

@Component
@Scope("session")
public class ViewScheduleComponent implements Serializable {

    private final Logger logger = Logger.getLogger(ViewScheduleComponent.class);

    @Autowired
    private ScheduleCallDtlsService scheduleCallDtlsService;
    @Autowired
    private ServiceMstService serviceMstService;
    @Autowired
    CallSchedulerService callSchedulerService;

    private ScheduleModel eventModel = new DefaultScheduleModel();
    private ScheduleEvent event = new DefaultScheduleEvent();
    private Long scheduledCallMstId;
    private Boolean renderedCallBtn;
    private Boolean renderedCallBtnForCustomer;
    private Boolean renderedResendLinkBtn;
    private Boolean renderedCancelSchdlBtn;

    private List<ScheduleCallDto> scheduledCallDtlDtoList = new ArrayList<>();

    public void refresh() {
        logger.info("Inside refresh");
        try {
            if (scheduledCallDtlDtoList != null) {
                scheduledCallDtlDtoList.clear();
            }

            renderedCallBtn = null;
            renderedResendLinkBtn = null;
            renderedCallBtnForCustomer = null;

            eventModel = new DefaultScheduleModel();

            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            EmployeeMst employeeMstDtoFromSession = (EmployeeMst) request.getSession().getAttribute("ormAgentMaster");

            List<ScheduleCallDto> scheduleCallDtlsDtos = callSchedulerService.getScheduledCallDtlsByEmployeeMstID(employeeMstDtoFromSession.getId());

            if (scheduleCallDtlsDtos != null && scheduleCallDtlsDtos.size() > 0 && !scheduleCallDtlsDtos.isEmpty()) {

                for (ScheduleCallDto obj : scheduleCallDtlsDtos) {
                    eventModel.addEvent(new DefaultScheduleEvent("Schedule", obj.getScheduleStartDateTime(), obj.getScheduleEndDateTime(), obj.getScheduleCallMstId()));
                }
            }
        } catch (Exception e) {
            logger.error("ERROR : ", e);
        }
    }

    public void onLoad() {
        logger.info("Inside onLoad");
        refresh();

        RequestContext.getCurrentInstance().execute("PF('calVar').show();");
    }

    public void onEventSelect(SelectEvent selectEvent) {
        try {
            if (selectEvent != null) {
                event = (ScheduleEvent) selectEvent.getObject();

                Date now = new Date();
                Calendar beforeCal = Calendar.getInstance();
                beforeCal.setTime(event.getStartDate());
                beforeCal.add(Calendar.MINUTE, -5);
                logger.info("CurrentTime : " + now + "beforeCal : " + beforeCal.getTime());

                renderedCancelSchdlBtn = now.before(beforeCal.getTime());

                scheduledCallMstId = (Long) event.getData();
                logger.info("scheduledCallMstId : " + scheduledCallMstId);

                if (scheduledCallDtlDtoList != null) {
                    scheduledCallDtlDtoList.clear();
                }

                List<ScheduleCallDto> tmpScheduledCallDtlDtoList = callSchedulerService.findScheduledCallDtlsByScheduledMstID(scheduledCallMstId);

                if (tmpScheduledCallDtlDtoList == null || tmpScheduledCallDtlDtoList.isEmpty()) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR : ", "Schedule Details Not Found"));

                } else {
                    for (ScheduleCallDto obj : tmpScheduledCallDtlDtoList) {
                        ScheduleCallDto scheduleCallDtlsDto = obj;
                        if (scheduleCallDtlsDto.getService() != null) {
                            obj.setServiceName((serviceMstService.findAllServiceMstById(scheduleCallDtlsDto.getService())).getServiceName());
                        }

                        if (scheduleCallDtlsDto.getEmployeeMstId() != null) {
                            renderedCallBtnForCustomer = true;
                        }

                        if (scheduleCallDtlsDto.getCustomerMstId() != null) {
                            renderedCallBtnForCustomer = false;
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
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Schedule Call Cancelled Successfully", ""));
                RequestContext.getCurrentInstance().execute("PF('calVar').hide();");
            }

        } catch (Exception e) {
            logger.error("error :" + e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", ""));
        }

    }

    /*GETTER SETTER*/
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

    public ScheduleCallDtlsService getScheduleCallDtlsService() {
        return scheduleCallDtlsService;
    }

    public void setScheduleCallDtlsService(ScheduleCallDtlsService scheduleCallDtlsService) {
        this.scheduleCallDtlsService = scheduleCallDtlsService;
    }

    public Boolean getRenderedCallBtn() {
        return renderedCallBtn;
    }

    public void setRenderedCallBtn(Boolean renderedCallBtn) {
        this.renderedCallBtn = renderedCallBtn;
    }

    public List<ScheduleCallDto> getScheduledCallDtlDtoList() {
        return scheduledCallDtlDtoList;
    }

    public void setScheduledCallDtlDtoList(List<ScheduleCallDto> scheduledCallDtlDtoList) {
        this.scheduledCallDtlDtoList = scheduledCallDtlDtoList;
    }

    public Long getScheduledCallMstId() {
        return scheduledCallMstId;
    }

    public void setScheduledCallMstId(Long scheduledCallMstId) {
        this.scheduledCallMstId = scheduledCallMstId;
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

    public ServiceMstService getServiceMstService() {
        return serviceMstService;
    }

    public void setServiceMstService(ServiceMstService serviceMstService) {
        this.serviceMstService = serviceMstService;
    }

    public Boolean getRenderedCancelSchdlBtn() {
        return renderedCancelSchdlBtn;
    }

    public void setRenderedCancelSchdlBtn(Boolean renderedCancelSchdlBtn) {
        this.renderedCancelSchdlBtn = renderedCancelSchdlBtn;
    }
}
