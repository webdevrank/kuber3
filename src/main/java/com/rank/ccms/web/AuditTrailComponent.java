package com.rank.ccms.web;

import com.rank.ccms.dto.AuditTrailDto;
import com.rank.ccms.entities.AuditTrail;
import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.service.AuditTrailService;
import com.rank.ccms.service.EmployeeMstService;
import com.rank.ccms.util.CustomConvert;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class AuditTrailComponent implements Serializable {

    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(AbandonCallComponent.class);
    private AuditTrail auditTrail;
    private List<AuditTrail> listAuditTrail = new ArrayList<>();
    private List<AuditTrail> selectAuditTrail = new ArrayList<>();
    private List<AuditTrailDto> selectAuditTrailDto = new ArrayList<>();
    private List<AuditTrailDto> listAuditTrailDto = new ArrayList<>();
    private Date startTime;
    private Date endTime;
    private Date presentDate;
    private String empName;
    private Long empId;
    private String selectTable;
    private Long selectCreatedBy;
    private Long selectUpdatedBy;
    private Long selectDisabledBy;

    @Autowired
    private AuditTrailService auditTrailService;

    @Autowired
    private EmployeeMstService employeeMstService;

    public void newAuditTrailComponent() {
        this.auditTrail = new AuditTrail();
        if (!selectAuditTrail.isEmpty()) {
            selectAuditTrail.clear();
        }
        if (!selectAuditTrailDto.isEmpty()) {
        	selectAuditTrailDto.clear();
        }
        this.selectTable = "";
        this.selectCreatedBy = null;
        this.selectDisabledBy = null;
        this.selectUpdatedBy = null;
        this.startTime = null;
        this.endTime = null;
    }

    public String getEmployeeName(long id) {

        if (id != 0) {
            EmployeeMst empMst = employeeMstService.findEmployeeMstById(id);
            if (empMst != null) {
                String fname = empMst.getFirstName();
                String mname = empMst.getMidName();

                this.empId = id;
                if (mname == null) {
                    mname = "";
                }
                String lname = empMst.getLastName();
                if (lname == null) {
                    lname = "";
                }

                this.empName = fname + " " + mname + " " + lname;
                return fname + " " + mname + " " + lname;
            } else {
                return null;
            }
        } else {
            return null;
        }

    }

    public void loadAuditTrailRecordsInitialze(String tablename, Long created, Long updated, Long disabled, Date starttime, Date endtime) {
        if (starttime == null && endtime != null || starttime != null && endtime == null || starttime == null && endtime == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please select Start Date and End Date,both!", "Search Unsuccessfull!!"));
            this.startTime = null;
            this.endTime = null;

        } else {
            RequestContext.getCurrentInstance().execute("setLoadRender1();");
            RequestContext.getCurrentInstance().execute("document.getElementById('search').click();");
        }

    }

    public void loadAuditTrailRecords(String tablename, Long created, Long updated, Long disabled, Date starttime, Date endtime) {
        try {
            logger.info("value " + starttime + " " + endtime);

            if (starttime != null && endtime != null) {
                if (starttime.after(endtime)) {
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Start Date should be less than End Date!", "Search Unsuccessfull!!"));
                } else {

                    List<AuditTrail> auditRows = new ArrayList<>();
                    List<AuditTrailDto> auditDtoRows = new ArrayList<>();
                    Timestamp dt_time1 = CustomConvert.javaDateToTimeStamp(starttime);

                    Calendar cal = Calendar.getInstance();
                    cal.setTime(endtime);
                    cal.set(Calendar.HOUR_OF_DAY, 23);
                    cal.set(Calendar.MINUTE, 59);
                    cal.set(Calendar.SECOND, 0);
                    cal.set(Calendar.MILLISECOND, 0);
                    Date d = cal.getTime();

                    Timestamp dt_time2 = CustomConvert.javaDateToTimeStamp(d);
                    List<AuditTrail> auditTrailLst = auditTrailService.findAllAuditTrailsByAll(tablename, created, updated, disabled, dt_time1, dt_time2);
                    if (auditTrailLst == null || auditTrailLst.isEmpty()) {
                        FacesContext.getCurrentInstance().addMessage(null,
                                new FacesMessage(FacesMessage.SEVERITY_ERROR, "No Records Found!", "Search Unsuccessfull!!"));
                        selectAuditTrail.clear();
                        selectAuditTrailDto.clear();
                    } else {
                        //AuditTrail auditTrl;
                        for (AuditTrail auditTrl : auditTrailLst) {
                            // auditTrl = auditTrailLst1;
                        	AuditTrailDto auditTrlDto = new AuditTrailDto();
                        	auditTrlDto.setCreatedById(this.getEmployeeName(auditTrl.getCreatedById()==null?0:auditTrl.getCreatedById()));
                        	auditTrlDto.setCreatedOn(auditTrl.getCreatedOn());
                        	auditTrlDto.setDateTime(auditTrl.getDateTime());
                        	auditTrlDto.setDeletedById(this.getEmployeeName(auditTrl.getDeletedById()==null?0:auditTrl.getDeletedById()));
                        	auditTrlDto.setDeletedOn(auditTrl.getDeletedOn());
                        	auditTrlDto.setDisabledById(this.getEmployeeName(auditTrl.getDisabledById()==null?0:auditTrl.getDisabledById()));
                        	auditTrlDto.setDisabledOn(auditTrl.getDisabledOn());
                        	auditTrlDto.setEnabledById(this.getEmployeeName(auditTrl.getEnabledById()==null?0:auditTrl.getEnabledById()));
                        	auditTrlDto.setEnabledOn(auditTrl.getEnabledOn());
                        	auditTrlDto.setId(auditTrl.getId());
                        	auditTrlDto.setReason(auditTrl.getReason());
                        	auditTrlDto.setTableKey1(auditTrl.getTableKey1());
                        	auditTrlDto.setTableKey2(auditTrl.getTableKey2());
                        	auditTrlDto.setTableKey3(auditTrl.getTableKey3());
                        	auditTrlDto.setTableName(auditTrl.getTableName());
                        	auditTrlDto.setUpdatedById(this.getEmployeeName(auditTrl.getUpdatedById()==null?0:auditTrl.getUpdatedById()));
                        	auditTrlDto.setUpdatedOn(auditTrl.getUpdatedOn());
                        	
                            auditRows.add(auditTrl);
                            auditDtoRows.add(auditTrlDto);
                        }
                        setSelectAuditTrail(auditRows);
                        setSelectAuditTrailDto(auditDtoRows);
                    }
                    RequestContext.getCurrentInstance().execute("setLoadRender2();");
                }
            }
        } catch (ParseException e) {
            logger.info(" ex " + e);
        }
    }

    public void clear() {
        this.selectTable = "";
        this.selectCreatedBy = Long.valueOf(0);
        this.selectUpdatedBy = Long.valueOf(0);
        this.selectDisabledBy = Long.valueOf(0);
        this.startTime = null;
        this.endTime = null;
        if (selectAuditTrail != null) {
            if (!selectAuditTrail.isEmpty()) {
                selectAuditTrail.clear();
            }
        }
        if (selectAuditTrailDto != null) {
            if (!selectAuditTrailDto.isEmpty()) {
            	selectAuditTrailDto.clear();
            }
        }

    }

    public void selectAuditTrailById(Long id) {
        setAuditTrail(auditTrailService.findAuditTrailById(id));
    }

    public List<AuditTrail> getListAuditTrail() {
        return listAuditTrail;
    }

    public void setListAuditTrail(List<AuditTrail> listAuditTrail) {
        this.listAuditTrail = listAuditTrail;
    }

    public List<AuditTrail> getSelectAuditTrail() {
        return selectAuditTrail;
    }

    public void setSelectAuditTrail(List<AuditTrail> selectAuditTrail) {
        this.selectAuditTrail = selectAuditTrail;
    }

    public AuditTrailService getAuditTrailService() {
        return auditTrailService;
    }

    public void setAuditTrailService(AuditTrailService auditTrailService) {
        this.auditTrailService = auditTrailService;
    }

    public AuditTrail getAuditTrail() {
        return auditTrail;
    }

    public void setAuditTrail(AuditTrail auditTrail) {
        this.auditTrail = auditTrail;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public EmployeeMstService getEmployeeMstService() {
        return employeeMstService;
    }

    public void setEmployeeMstService(EmployeeMstService employeeMstService) {
        this.employeeMstService = employeeMstService;
    }

    public String getSelectTable() {
        return selectTable;
    }

    public void setSelectTable(String selectTable) {
        this.selectTable = selectTable;
    }

    public Long getSelectCreatedBy() {
        return selectCreatedBy;
    }

    public void setSelectCreatedBy(Long selectCreatedBy) {
        this.selectCreatedBy = selectCreatedBy;
    }

    public Long getSelectUpdatedBy() {
        return selectUpdatedBy;
    }

    public void setSelectUpdatedBy(Long selectUpdatedBy) {
        this.selectUpdatedBy = selectUpdatedBy;
    }

    public Long getSelectDisabledBy() {
        return selectDisabledBy;
    }

    public void setSelectDisabledBy(Long selectDisabledBy) {
        this.selectDisabledBy = selectDisabledBy;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public List<EmployeeMst> getAllEmpMstLst() {
        return employeeMstService.findAllNonDeletedEmployeeMsts();

    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getPresentDate() {
        presentDate = new Date();
        return presentDate;
    }

    public void setPresentDate(Date presentDate) {
        this.presentDate = presentDate;
    }

	public List<AuditTrailDto> getSelectAuditTrailDto() {
		return selectAuditTrailDto;
	}

	public void setSelectAuditTrailDto(List<AuditTrailDto> selectAuditTrailDto) {
		this.selectAuditTrailDto = selectAuditTrailDto;
	}

	public List<AuditTrailDto> getListAuditTrailDto() {
		return listAuditTrailDto;
	}

	public void setListAuditTrailDto(List<AuditTrailDto> listAuditTrailDto) {
		this.listAuditTrailDto = listAuditTrailDto;
	}
}
