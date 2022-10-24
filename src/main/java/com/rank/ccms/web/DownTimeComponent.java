package com.rank.ccms.web;

import com.rank.ccms.dto.DownTimeDto;
import com.rank.ccms.entities.DownTime;
import com.rank.ccms.service.DownTimeService;
import com.rank.ccms.util.CustomConvert;
import com.rank.ccms.util.ThreadSafeSimpleDateFormat;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class DownTimeComponent implements Serializable {

    private static final long serialVersionUID = 6582900894900350924L;

    private final Logger logger = Logger.getLogger(CallSettingsComponent.class);

    private DownTime downTime;
    private List<DownTime> downTimeList;
    private DownTimeDto downTimeDto;
    private List<DownTimeDto> downTimeDtoList;

    private Date presentDate;
    private Date startDate;
    private Date endDate;
    private String reason;
    private Boolean exportToCSVBtnStatus;
    private String outputTextToExport = "";

    @Autowired
    private DownTimeService downTimeService;

    @PostConstruct
    public void newCallSettings() {
        downTime = new DownTime();
        downTimeDto = new DownTimeDto();
        downTimeList = new ArrayList<>();
        downTimeDtoList = new ArrayList<>();
        reason = "";
        this.startDate = null;
        this.endDate = null;
    }

    public void init() {
        downTime = new DownTime();
        downTimeDto = new DownTimeDto();
        downTimeList = new ArrayList<>();
        downTimeDtoList = new ArrayList<>();
        reason = "";
        this.startDate = null;
        this.endDate = null;
        exportToCSVBtnStatus = false;
    }

    public void showAllDownTimeForReportInitialize() {
        if (null != startDate && null != endDate) {
            if (startDate.after(endDate)) {
            } else {
                RequestContext.getCurrentInstance().execute("document.getElementById('search').click();");
            }
        }
    }

    public void showAllDownTimeForReport() {
        logger.info(" In showAllDownTimeForReport");
        ThreadSafeSimpleDateFormat dateFormat1 = new ThreadSafeSimpleDateFormat("dd.MM.yyyy HH:mm");
        String startTimeOutput = "";
        String endTimeOutput = "";
        try {
            if (null == startDate && null == endDate) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!! Two fields must be selected", "Two fields must be selected");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else if (startDate == null && endDate != null) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!! Starting Date must be selected", "Starting Date must be selected");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else if (startDate != null && endDate == null) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!! Ending date must be selected", "Ending Date must be selected");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else if (startDate != null && endDate != null && endDate.before(startDate)) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!! Second Date must be after first Date", "Second Date must be after first Date");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } else {

                if (startDate != null) {
                    startTimeOutput = "Start Date : " + dateFormat1.format(startDate);
                }
                if (endDate != null) {
                    endTimeOutput = "End Date : " + dateFormat1.format(endDate);
                }
                exportToCSVBtnStatus = false;
                Timestamp lStartDate = CustomConvert.javaDateToTimeStamp(startDate);
                Timestamp lEndDate = CustomConvert.javaDateToTimeStamp(endDate);

                downTimeDtoList = new ArrayList<>();
                downTimeList = downTimeService.findDownTimeByDateRange(lStartDate, lEndDate);
                ThreadSafeSimpleDateFormat sdf = new ThreadSafeSimpleDateFormat("dd-MM-yyyy HH:mm");
                if (!downTimeList.isEmpty()) {
                    for (DownTime downTimeLocal : downTimeList) {
                        downTimeDto = new DownTimeDto();
                        downTimeDto.setId(downTimeLocal.getId());
                        if (downTimeLocal.getReason() != null) {
                            downTimeDto.setReason(downTimeLocal.getReason());
                        } else {
                            downTimeDto.setReason("");
                        }
                        String stringStart = sdf.format(downTimeLocal.getStartTime());

                        int startColonPos = stringStart.indexOf(":");

                        int startBlankPos = stringStart.indexOf(" ");

                        String dateStart = stringStart.substring(0, startBlankPos);
                        int startHour = Integer.parseInt(stringStart.substring(startBlankPos + 1, startColonPos));
                        int startMin = Integer.parseInt(stringStart.substring(startColonPos + 1));

                        if(startHour==12) {
                        	downTimeDto.setDownStartTime(stringStart + " PM");
                        } else if (startHour > 12 && startHour < 24) {
                            if ((startHour - 12) < 10) {
                                if (startMin < 10) {
                                    stringStart = "0" + String.valueOf(startHour - 12) + ":" + "0" + startMin;
                                } else {
                                    stringStart = String.valueOf(startHour - 12) + ":" + startMin;
                                }
                            } else {
                                if (startMin < 10) {
                                    stringStart = String.valueOf(startHour - 12) + ":" + "0" + startMin;
                                } else {
                                    stringStart = String.valueOf(startHour - 12) + ":" + startMin;
                                }
                            }
                            downTimeDto.setDownStartTime(dateStart + " " + stringStart + " PM");
                        } else {
                            downTimeDto.setDownStartTime(stringStart + " AM");
                        }
                        downTimeDto.setDownStartDate(downTimeLocal.getStartTime());

                        String stringEnd = sdf.format(downTimeLocal.getEndTime());

                        int endColonPos = stringEnd.indexOf(":");

                        int endBlankPos = stringEnd.indexOf(" ");

                        String dateEnd = stringEnd.substring(0, endBlankPos);
                        int endHour = Integer.parseInt(stringEnd.substring(endBlankPos + 1, endColonPos));
                        int endMin = Integer.parseInt(stringEnd.substring(endColonPos + 1));

                        if(startHour==12) {
                        	 downTimeDto.setDownEndTime(stringEnd + " PM");
                        } else if (endHour > 12 && startHour < 24) {
                            if ((endHour - 12) < 10) {
                                if (endMin < 10) {
                                    stringEnd = "0" + String.valueOf(endHour - 12) + ":" + "0" + endMin;
                                } else {
                                    stringEnd = String.valueOf(endHour - 12) + ":" + endMin;
                                }
                            } else {
                                if (endMin < 10) {
                                    stringEnd = String.valueOf(endHour - 12) + ":" + "0" + endMin;
                                } else {
                                    stringEnd = String.valueOf(endHour - 12) + ":" + endMin;
                                }
                            }
                            downTimeDto.setDownEndTime(dateEnd + " " + stringEnd + " PM");
                        } else {
                            downTimeDto.setDownEndTime(stringEnd + " AM");
                        }
                        downTimeDto.setDownEndDate(downTimeLocal.getEndTime());

                        downTimeDtoList.add(downTimeDto);
                        exportToCSVBtnStatus = true;
                    }
                } else {
                    downTimeDto = new DownTimeDto();
                    downTimeDtoList = new ArrayList<>();
                }
                if (!downTimeDtoList.isEmpty()) {

                    outputTextToExport = startTimeOutput + "   " + endTimeOutput;
                }
            }
        } catch (ParseException | NumberFormatException ex) {
            logger.info(" Error " + ex);
        }
    }

    public void showAllDownTime() {

        this.startDate = null;
        this.endDate = null;
        reason = "";
        downTimeDtoList = new ArrayList<>();
        downTimeList = downTimeService.findAllNonDeletedDownTime();

        ThreadSafeSimpleDateFormat sdf = new ThreadSafeSimpleDateFormat("dd-MM-yyyy HH:mm");
        if (!downTimeList.isEmpty()) {
            for (DownTime downTimeLocal : downTimeList) {
                downTimeDto = new DownTimeDto();
                downTimeDto.setId(downTimeLocal.getId());
                if (downTimeLocal.getReason() != null) {
                    downTimeDto.setReason(downTimeLocal.getReason());
                } else {
                    downTimeDto.setReason("");
                }
                String stringStart = sdf.format(downTimeLocal.getStartTime());

                int startColonPos = stringStart.indexOf(":");

                int startBlankPos = stringStart.indexOf(" ");

                String dateStart = stringStart.substring(0, startBlankPos);
                int startHour = Integer.parseInt(stringStart.substring(startBlankPos + 1, startColonPos));
                int startMin = Integer.parseInt(stringStart.substring(startColonPos + 1));

                if(startHour==12) {
                	downTimeDto.setDownStartTime(stringStart + " PM");
                } else if (startHour > 12 && startHour < 24) {
                    if ((startHour - 12) < 10) {
                        if (startMin < 10) {
                            stringStart = "0" + String.valueOf(startHour - 12) + ":" + "0" + startMin;
                        } else {
                            stringStart = String.valueOf(startHour - 12) + ":" + startMin;
                        }
                    } else {
                        if (startMin < 10) {
                            stringStart = String.valueOf(startHour - 12) + ":" + "0" + startMin;
                        } else {
                            stringStart = String.valueOf(startHour - 12) + ":" + startMin;
                        }
                    }
                    downTimeDto.setDownStartTime(dateStart + " " + stringStart + " PM");
                } else {
                    downTimeDto.setDownStartTime(stringStart + " AM");
                }
                downTimeDto.setDownStartDate(downTimeLocal.getStartTime());

                String stringEnd = sdf.format(downTimeLocal.getEndTime());

                int endColonPos = stringEnd.indexOf(":");

                int endBlankPos = stringEnd.indexOf(" ");

                String dateEnd = stringEnd.substring(0, endBlankPos);
                int endHour = Integer.parseInt(stringEnd.substring(endBlankPos + 1, endColonPos));
                int endMin = Integer.parseInt(stringEnd.substring(endColonPos + 1));

                if(startHour==12) {
                	downTimeDto.setDownEndTime(stringEnd + " PM");
                } else if (endHour > 12 && startHour < 24) {
                    if ((endHour - 12) < 10) {
                        if (endMin < 10) {
                            stringEnd = "0" + String.valueOf(endHour - 12) + ":" + "0" + endMin;
                        } else {
                            stringEnd = String.valueOf(endHour - 12) + ":" + endMin;
                        }
                    } else {
                        if (endMin < 10) {
                            stringEnd = String.valueOf(endHour - 12) + ":" + "0" + endMin;
                        } else {
                            stringEnd = String.valueOf(endHour - 12) + ":" + endMin;
                        }
                    }
                    downTimeDto.setDownEndTime(dateEnd + " " + stringEnd + " PM");
                } else {
                    downTimeDto.setDownEndTime(stringEnd + " AM");
                }
                downTimeDto.setDownEndDate(downTimeLocal.getEndTime());

                if (downTimeDto.getDownEndDate().compareTo(new Date()) < 0) {
                    downTimeDto.setRenderEdit(false);

                } else {
                    downTimeDto.setRenderEdit(true);

                }

                downTimeDtoList.add(downTimeDto);
            }
        } else {
            downTimeDto = new DownTimeDto();
            downTimeDtoList = new ArrayList<>();
        }
    }

    public void save() throws ParseException {

        if (null == startDate && null == endDate && reason.equals("")) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!! Three fields must be selected", "Three fields must be selected");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else if (startDate == null && endDate != null) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!! Starting Date must be selected", "Starting Date must be selected");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else if (startDate != null && endDate == null) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!! Ending date must be selected", "Ending Date must be selected");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            if (null != startDate && null != endDate && !reason.equals("")) {

                if (endDate.before(startDate)) {
                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!! Second Date must be after first Date", "Second Date must be after first Date");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                } else {
                    downTime = new DownTime();
                    downTime.setStartTime(CustomConvert.javaDateToTimeStamp(startDate));
                    downTime.setEndTime(CustomConvert.javaDateToTimeStamp(endDate));
                    downTime.setReason(reason);
                    downTime.setActiveFlg(true);
                    downTime.setDeleteFlg(false);
                    downTime = downTimeService.saveDownTime(downTime);

                    if (downTime != null) {
                        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Saved Successfully", "Saved Successfully");
                        FacesContext.getCurrentInstance().addMessage(null, msg);
                    } else {
                        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Please Try Again");
                        FacesContext.getCurrentInstance().addMessage(null, msg);
                    }
                    showAllDownTime();

                }
            }
        }
    }

    public void checkErrors() {

    }

    public void onCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Update Cancelled", "Update Cancelled");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onEdit(RowEditEvent event) {

        downTime = new DownTime();
        downTimeDto = new DownTimeDto();
        downTimeDto = (DownTimeDto) event.getObject();

        if (((null == downTimeDto.getDownStartDate()) && (null == downTimeDto.getDownEndDate()) && (null == downTimeDto.getReason()))) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!! Three fields must be selected", "Three fields must be selected");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else if (downTimeDto.getDownStartDate() == null && downTimeDto.getDownEndDate() != null) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!! Starting Date must be selected", "Starting Date must be selected");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else if (downTimeDto.getDownStartDate() != null && downTimeDto.getDownEndDate() == null) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!! Ending date must be selected", "Ending Date must be selected");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            if (null != downTimeDto.getDownStartDate() && null != downTimeDto.getDownEndDate()) {

                if (downTimeDto.getDownEndDate().compareTo(downTimeDto.getDownStartDate()) < 0) {
                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!! Second Date must be after first Date", "Second Date must be after first Date");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                } else {
                    downTime = downTimeService.findDownTimeById(downTimeDto.getId());
                    downTime.setStartTime(downTimeDto.getDownStartDate());
                    downTime.setEndTime(downTimeDto.getDownEndDate());
                    downTime.setActiveFlg(true);
                    downTime.setDeleteFlg(false);
                    downTime.setReason(downTimeDto.getReason());
                    downTime = downTimeService.saveDownTime(downTime);

                    if (downTime == null) {
                        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Please Try Again");
                        FacesContext.getCurrentInstance().addMessage(null, msg);
                    } else {
                        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Down Time id " + downTime.getId() + " updated Successfully", "Down Time id " + downTime.getId() + " updated Successfully");
                        FacesContext.getCurrentInstance().addMessage(null, msg);
                    }
                    showAllDownTime();
                }
            }
        }
    }

    public void clear() {
        this.startDate = null;
        this.endDate = null;
        this.reason = "";
        exportToCSVBtnStatus = false;
        showAllDownTime();
    }

    public void clearReport() {
        downTimeDtoList = new ArrayList<>();
        this.startDate = null;
        this.endDate = null;
        this.reason = "";
        exportToCSVBtnStatus = false;
    }

    public DownTime getDownTime() {
        return downTime;
    }

    public void setDownTime(DownTime downTime) {
        this.downTime = downTime;
    }

    public List<DownTime> getDownTimeList() {
        return downTimeList;
    }

    public void setDownTimeList(List<DownTime> downTimeList) {
        this.downTimeList = downTimeList;
    }

    public DownTimeDto getDownTimeDto() {
        return downTimeDto;
    }

    public void setDownTimeDto(DownTimeDto downTimeDto) {
        this.downTimeDto = downTimeDto;
    }

    public List<DownTimeDto> getDownTimeDtoList() {
        return downTimeDtoList;
    }

    public void setDownTimeDtoList(List<DownTimeDto> downTimeDtoList) {
        this.downTimeDtoList = downTimeDtoList;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Boolean getExportToCSVBtnStatus() {
        return exportToCSVBtnStatus;
    }

    public void setExportToCSVBtnStatus(Boolean exportToCSVBtnStatus) {
        this.exportToCSVBtnStatus = exportToCSVBtnStatus;
    }

    public String getOutputTextToExport() {
        return outputTextToExport;
    }

    public void setOutputTextToExport(String outputTextToExport) {
        this.outputTextToExport = outputTextToExport;
    }

}
