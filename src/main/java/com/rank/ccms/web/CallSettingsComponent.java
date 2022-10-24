package com.rank.ccms.web;

import com.rank.ccms.dto.CallSettingsDto;
import com.rank.ccms.entities.CallSettings;
import com.rank.ccms.service.CallSettingsService;
import com.rank.ccms.util.CustomConvert;
import com.rank.ccms.util.ThreadSafeSimpleDateFormat;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class CallSettingsComponent implements Serializable {

    private static final long serialVersionUID = 4048216904713705442L;

    private final static Logger logger = Logger.getLogger(CallSettingsComponent.class);
    private CallSettings callSettings;
    private List<CallSettings> callSettingsList;
    private CallSettingsDto callSettingsDto;
    private List<CallSettingsDto> callSettingsDtoList;
    private String[] selectedConsoles2;
    private List<String> dayList;
    private Date presentDate;
    private Date startDate;
    private Date endDate;
    private CallSettingsDto valueDelete;

    @Autowired
    private CallSettingsService callSettingsService;

    @PostConstruct
    public void newCallSettings() {
        callSettings = new CallSettings();
        callSettingsDto = new CallSettingsDto();
        callSettingsList = new ArrayList<>();
        callSettingsDtoList = new ArrayList<>();
        dayList = new ArrayList<>();
        valueDelete = new CallSettingsDto();
    }

    public void init() {
        callSettings = new CallSettings();
        callSettingsDto = new CallSettingsDto();
        callSettingsList = new ArrayList<>();
        callSettingsDtoList = new ArrayList<>();
        dayList = new ArrayList<>();
        valueDelete = new CallSettingsDto();
    }

    public void onCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Update Cancelled", "Update Cancelled");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onEdit(RowEditEvent event) {

        CallSettings cs;
        CallSettingsDto csd = (CallSettingsDto) event.getObject();

        if (((null == csd.getServiceStartTime()) && (null == csd.getServiceEndTime()))) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!! Two fields must be selected", "Two fields must be selected");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else if (csd.getServiceStartTime() == null && csd.getServiceEndTime() != null) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!! Starting Date must be selected", "Starting Date must be selected");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else if (csd.getServiceStartTime() != null && csd.getServiceEndTime() == null) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!! Ending date must be selected", "Ending Date must be selected");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else if (null != csd.getStartDate() && null != csd.getEndDate()) {
            if (!csd.getStartDate().before(csd.getEndDate())) {

                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!! Second Date must be after first Date", "Second Date must be after first Date");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                cs = callSettingsService.findCallSettingsById(csd.getId());
                cs.setServiceStartTime(csd.getStartDate());
                cs.setServiceEndTime(csd.getEndDate());
                cs.setActiveFlg(true);
                cs.setDeleteFlg(false);
                cs = callSettingsService.saveCallSettings(cs);

                if (cs == null) {
                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Please Try Again");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                } else {
                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Service Time updated Successfully", "Service Time updated Successfully");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                }
                showAllCallSettings();
            }
        }
    }

    public void showAllCallSettings() {
        dayList = new ArrayList<>();
        dayList.add("Monday");
        dayList.add("Tuesday");
        dayList.add("Wednesday");
        dayList.add("Thursday");
        dayList.add("Friday");
        dayList.add("Saturday");
        dayList.add("Sunday");
        List<String> localDayList = new ArrayList<>();
        this.startDate = null;
        this.endDate = null;
        callSettingsDtoList = new ArrayList<>();
        valueDelete = new CallSettingsDto();
        callSettingsDto = new CallSettingsDto();
        callSettingsList = callSettingsService.findAllNonDeletedCallSettings();
        callSettings = new CallSettings();
        if (!callSettingsList.isEmpty()) {
            callSettings = callSettingsList.get(0);
            if (callSettings.getOtp() == true) {
                callSettingsDto.setOtpSelect("YES");
            } else {
                callSettingsDto.setOtpSelect("NO");
            }

            ThreadSafeSimpleDateFormat sdf = new ThreadSafeSimpleDateFormat("HH:mm");
            for (CallSettings cs : callSettingsList) {
                callSettings = cs;

                if (callSettings.getServiceStartTime() != null && callSettings.getServiceEndTime() != null) {

                    CallSettingsDto callSettingsDto1 = new CallSettingsDto();
                    callSettingsDto1.setId(callSettings.getId());
                    if (callSettings.getServiceDay() != null) {
                        localDayList.add(callSettings.getServiceDay());
                        callSettingsDto1.setServiceDay(callSettings.getServiceDay());
                    }
                    callSettingsDto1.setStartDate(callSettings.getServiceStartTime());
                    callSettingsDto1.setEndDate(callSettings.getServiceEndTime());
                    String stringStart;
                    if (callSettings.getServiceStartTime() != null) {
                        stringStart = sdf.format(callSettings.getServiceStartTime());
                    } else {
                        stringStart = "";
                    }
                    int startColonPos = stringStart.indexOf(":");

                    int startHour = Integer.parseInt(stringStart.substring(0, startColonPos));
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
                        callSettingsDto1.setServiceStartTime(stringStart + " PM");
                    } else {
                        callSettingsDto1.setServiceStartTime(stringStart + " AM");
                    }
                    String stringEnd;
                    if (callSettings.getServiceEndTime() != null) {
                        stringEnd = sdf.format(callSettings.getServiceEndTime());
                    } else {
                        stringEnd = "";
                    }

                    int endColonPos = stringEnd.indexOf(":");
                    int endHour = Integer.parseInt(stringEnd.substring(0, endColonPos));
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
                        callSettingsDto1.setServiceEndTime(stringEnd + " PM");
                    } else {
                        callSettingsDto1.setServiceEndTime(stringEnd + " AM");
                    }
                    callSettingsDtoList.add(callSettingsDto1);
                }
            }
            dayList.removeAll(localDayList);

        } else {
            callSettingsDto = new CallSettingsDto();
            callSettingsDto.setOtpSelect("NO");
            callSettingsDtoList = new ArrayList<>();
        }
    }

    public void saveOTP() {

        List<CallSettings> callSetlist = callSettingsService.findAllNonDeletedCallSettings();
        if (callSetlist != null && !callSetlist.isEmpty()) {
            try {
                for (CallSettings cs : callSetlist) {
                    if (callSettingsDto.getOtpSelect().equals("YES")) {
                        cs.setOtp(true);
                    } else {
                        cs.setOtp(false);
                    }
                    callSettings = callSettingsService.saveCallSettings(cs);
                }
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Saved Successfully", "Saved Successfully");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception e) {
                logger.info(" Error " + e.getMessage());
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Please Try Again");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } else {

            callSettings = new CallSettings();
            if (callSettingsDto.getOtpSelect().equals("YES")) {
                callSettings.setOtp(true);
            } else {
                callSettings.setOtp(false);
            }

            callSettings.setActiveFlg(true);
            callSettings.setDeleteFlg(false);
            callSettings = callSettingsService.saveCallSettings(callSettings);

            if (callSettings != null) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Saved Successfully", "Saved Successfully");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Please Try Again");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }
    }

    public void save() throws ParseException {

        if (((null == startDate) && (null == endDate))) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!! Two fields must be selected", "Two fields must be selected");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else if (startDate == null && endDate != null) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!! Starting Date must be selected", "Starting Date must be selected");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else if (startDate != null && endDate == null) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!! Ending date must be selected", "Ending Date must be selected");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else if (null != startDate && null != endDate) {

            if (endDate.compareTo(startDate) < 0) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!! Second Date must be after first Date", "Second Date must be after first Date");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {
                try {
                    List<CallSettings> callSetlist = callSettingsService.findAllNonDeletedCallSettings();
                    Boolean sOtp;

                    if (callSetlist != null && !callSetlist.isEmpty()) {
                        sOtp = callSetlist.get(0).getOtp();

                    } else {
                        sOtp = false;
                    }
                    for (String sDay : selectedConsoles2) {
                        callSettings = new CallSettings();

                        callSettings.setOtp(sOtp);
                        callSettings.setServiceStartTime(CustomConvert.javaDateToTimeStamp(startDate));
                        callSettings.setServiceEndTime(CustomConvert.javaDateToTimeStamp(endDate));
                        callSettings.setActiveFlg(true);
                        callSettings.setDeleteFlg(false);
                        callSettings.setServiceDay(sDay.trim());
                        callSettings = callSettingsService.saveCallSettings(callSettings);
                    }
                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Saved Successfully", "Saved Successfully");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                } catch (Exception e) {
                    logger.info(" Exp " + e.getMessage());
                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Please Try Again");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                } finally {
                    this.startDate = null;
                    this.endDate = null;
                    showAllCallSettings();
                }
            }
        }
    }

    public void clear() {
        this.startDate = null;
        this.endDate = null;
    }

    public void deleteServiceToBean(CallSettingsDto csd) {
        logger.info(" value when press delete " + csd.getId());
        valueDelete = csd;
    }

    public String deactivateService(HttpServletRequest request) {
        logger.info("In deactiveService ");

        try {
            CallSettings cs = callSettingsService.findCallSettingsById(valueDelete.getId());
            callSettingsService.deleteServiceTime(cs);

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Deleted Successfully", "Deleted Successfully"));
            showAllCallSettings();
            return "/pages/configuration/addOTPAndServiceTime.xhtml";

        } catch (Exception e) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Failed,Please try again", "Please try again!!"));
            return "/pages/configuration/addOTPAndServiceTime.xhtml";

        }
    }

    public CallSettings getCallSettings() {
        return callSettings;
    }

    public void setCallSettings(CallSettings callSettings) {
        this.callSettings = callSettings;
    }

    public CallSettingsDto getCallSettingsDto() {
        return callSettingsDto;
    }

    public void setCallSettingsDto(CallSettingsDto callSettingsDto) {
        this.callSettingsDto = callSettingsDto;
    }

    public List<CallSettingsDto> getCallSettingsDtoList() {
        return callSettingsDtoList;
    }

    public void setCallSettingsDtoList(List<CallSettingsDto> callSettingsDtoList) {
        this.callSettingsDtoList = callSettingsDtoList;
    }

    public List<CallSettings> getCallSettingsList() {
        return callSettingsList;
    }

    public void setCallSettingsList(List<CallSettings> callSettingsList) {
        this.callSettingsList = callSettingsList;
    }

    public Date getPresentDate() {
        presentDate = new Date();
        return presentDate;
    }

    public void setPresentDate(Date presentDate) {
        this.presentDate = presentDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String[] getSelectedConsoles2() {
        return selectedConsoles2;
    }

    public void setSelectedConsoles2(String[] selectedConsoles2) {
        this.selectedConsoles2 = selectedConsoles2;
    }

    public List<String> getDayList() {
        return dayList;
    }

    public void setDayList(List<String> dayList) {
        this.dayList = dayList;
    }

    public CallSettingsDto getValueDelete() {
        return valueDelete;
    }

    public void setValueDelete(CallSettingsDto valueDelete) {
        this.valueDelete = valueDelete;
    }

}
