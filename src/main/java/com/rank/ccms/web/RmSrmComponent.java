package com.rank.ccms.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.logging.Level;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.context.RequestContext;
import org.primefaces.model.DualListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.rank.ccms.dto.RmSrmMapDto;
import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.entities.EmployeeTypeMst;
import com.rank.ccms.entities.RmSrmMap;
import com.rank.ccms.service.EmployeeMstService;
import com.rank.ccms.service.RmSrmMapService;
import java.util.Objects;

@Component
@Scope("session")
public class RmSrmComponent implements Serializable {

    private static final long serialVersionUID = -4471545621101734447L;
    org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(this.getClass());

    private Long srmId;
    private Long rmId;
    private String srmName;
    private String rmName;

    private SortedMap<String, Long> rmMap = new TreeMap<>();
    private DualListModel<RmSrmMapDto> rms = new DualListModel<>();
    private List<RmSrmMapDto> source;
    private List<RmSrmMapDto> target;

    List<RmSrmMapDto> rmSrmMapDtoList = new ArrayList<>();

    RmSrmMapDto selectedRmSrmMapDto;

    @Autowired
    EmployeeMstService employeeMstService;
    @Autowired
    RmSrmMapService rmSrmMapService;

    public RmSrmComponent() {

    }

    public String onLoad() {
        logger.info("inside onLoad........");
        refresh();
        return "/pages/maps/rmSrmMap.xhtml?faces-redirect=true";
    }

    public void refresh() {
        try {

            if (rmSrmMapDtoList != null) {
                rmSrmMapDtoList.clear();
            }

            selectedRmSrmMapDto = null;
            EmployeeTypeMst employeeTypeMst = new EmployeeTypeMst();
            employeeTypeMst.setId(8l);
            List<EmployeeMst> employeeList = employeeMstService.findNonDeletedEmployeeByEmpType("SeniorRelationshipManager");
            for (EmployeeMst employeeList1 : employeeList) {
                RmSrmMapDto rmSrmMapdto = new RmSrmMapDto();
                rmSrmMapdto.setSrmId(employeeList1.getId());
                rmSrmMapdto.setSrmName(employeeList1.getFirstName() + employeeList1.getMidName() + employeeList1.getLastName());
                List<RmSrmMap> rmSrmMapList = rmSrmMapService.getRMsMappedWithSRM(employeeList1.getId());
                String mappedRms = "";
                for (RmSrmMap rmSrmMap : rmSrmMapList) {
                    EmployeeMst empMst = employeeMstService.findEmployeeMstById(rmSrmMap.getRmId().getId());
                    mappedRms = mappedRms + empMst.getFirstName() + empMst.getMidName() + empMst.getLastName() + ", ";
                }
                if (mappedRms.trim().length() > 0) {

                    mappedRms = mappedRms.substring(0, mappedRms.length() - 2);

                }
                rmSrmMapdto.setMappedRm(mappedRms);
                rmSrmMapDtoList.add(rmSrmMapdto);
            }
            logger.info("rmSrmMapDtoList size : " + rmSrmMapDtoList.size());

        } catch (Exception e) {
            logger.error("refresh ERROR ", e);
        }
    }

    public void onBeforeEditTypeMapList(Long employeeId) {
        source = new ArrayList<>();
        target = new ArrayList<>();
        rms = new DualListModel<>(source, target);
        this.setRmMap(getRMList());
        populatePickList(employeeId);
        try {
            EmployeeMst emp = employeeMstService.findEmployeeMstById(employeeId);
            this.setSrmId(emp.getId());
            this.setSrmName(emp.getFirstName() + emp.getMidName() + emp.getLastName());

        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(RmSrmComponent.class.getName()).log(Level.SEVERE, null, ex);
        }
        RequestContext.getCurrentInstance().execute("PF('editRmSrmMapDialogVar').show();");

    }

    public void populatePickList(Long employeeId) {

        source = new ArrayList<>();
        target = new ArrayList<>();
        logger.info("employeeId : " + employeeId);
        if (employeeId != null && employeeId > 0) {

            try {
                //Get All the Roles
                for (EmployeeMst em : employeeMstService.findNonDeletedEmployeeByEmpType("RelationshipManager")) {
                    logger.info("Employee  : " + em.getFirstName());
                    RmSrmMapDto rmSrmMapDto = new RmSrmMapDto();
                    List<RmSrmMap> mappedList = rmSrmMapService.getRMsMappedWithSRM(employeeId);
                    logger.info("mappedList size : " + mappedList.size());
                    if (mappedList.size() > 0) {
                        for (RmSrmMap obj : mappedList) {
                            if (Objects.equals(obj.getRmId().getId(), em.getId())) {
                                rmSrmMapDto.setId(mappedList.get(0).getId());
                                rmSrmMapDto.setSrmId(employeeId);
                                rmSrmMapDto.setRmId(em.getId());
                                rmSrmMapDto.setRmName(em.getFirstName() + em.getMidName() + em.getLastName());
                                getTarget().add(rmSrmMapDto);
                            } else {
                                RmSrmMap mappedSRM = rmSrmMapService.getSRMMappedWithRM(em.getId());
                                if (mappedSRM == null) {
                                    rmSrmMapDto.setSrmId(employeeId);
                                    rmSrmMapDto.setRmId(em.getId());
                                    rmSrmMapDto.setRmName(em.getFirstName() + em.getMidName() + em.getLastName());
                                    getSource().add(rmSrmMapDto);
                                    break;

                                }
                            }

                        }
                    } else {
                        RmSrmMap mappedSRM = rmSrmMapService.getSRMMappedWithRM(em.getId());
                        if (mappedSRM == null) {
                            rmSrmMapDto.setSrmId(employeeId);
                            rmSrmMapDto.setRmId(em.getId());
                            rmSrmMapDto.setRmName(em.getFirstName() + em.getMidName() + em.getLastName());
                            getSource().add(rmSrmMapDto);
                        }
                    }

                }
            } catch (Exception ex) {
                java.util.logging.Logger.getLogger(RmSrmComponent.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        rms = new DualListModel<>(source, target);

    }

    private TreeMap<String, Long> getRMList() {
        TreeMap<String, Long> allRms = new TreeMap();

        EmployeeTypeMst employeeTypeMst = new EmployeeTypeMst();
        employeeTypeMst.setId(8l);
        List<EmployeeMst> empMstList = new ArrayList<>();
        try {
            empMstList = employeeMstService.findNonDeletedEmployeeByEmpType("SeniorRelationshipManager");
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(RmSrmComponent.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (EmployeeMst empMst : empMstList) {
            allRms.put(empMst.getFirstName() + empMst.getMidName() + empMst.getLastName(), empMst.getId());

        }
        return allRms;
    }

    public void save() {

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

        try {
            //First Delete All Roles of Employee Type From Source
            for (RmSrmMapDto rmSrmMapDto : rms.getSource()) {
                if (rmSrmMapDto.getId() != null) {

                    if (rmSrmMapDto.getSrmId().equals(this.srmId)) {

                        List<RmSrmMap> rmSrmMapList = rmSrmMapService.findSRMRMMapBySRMandRM(rmSrmMapDto.getSrmId(), rmSrmMapDto.getRmId());
                        for (RmSrmMap rmSrmMap : rmSrmMapList) {
                            rmSrmMapService.deleteRmSrmMap(rmSrmMap);
                        }
                    }

                }
            }

            //Sava Data 
            for (RmSrmMapDto rmSrmMapDto : rms.getTarget()) {

                RmSrmMap rmSrmMap = geEntityFromDto(rmSrmMapDto);
                rmSrmMap.setActiveFlg(true);
                rmSrmMap.setDeleteFlg(false);

                if (rmSrmMapDto.getSrmId().equals(this.srmId)) {

                    rmSrmMapService.saveSRMRMMap(rmSrmMap);
                }

            }

            refresh();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Mapped Successfully", "Mapped Successfully");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Mapping Failure");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

    }

    private RmSrmMap geEntityFromDto(RmSrmMapDto rmSrmMapDto) {
        RmSrmMap rmSrmMap = new RmSrmMap();

        rmSrmMap.setId(rmSrmMapDto.getId());
        try {
            rmSrmMap.setSrmId(employeeMstService.findEmployeeMstById(rmSrmMapDto.getSrmId()));

            rmSrmMap.setRmId(employeeMstService.findEmployeeMstById(rmSrmMapDto.getRmId()));
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(RmSrmComponent.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rmSrmMap;
    }

    public Long getSrmId() {
        return srmId;
    }

    public void setSrmId(Long srmId) {
        this.srmId = srmId;
    }

    public Long getRmId() {
        return rmId;
    }

    public void setRmId(Long rmId) {
        this.rmId = rmId;
    }

    public String getSrmName() {
        return srmName;
    }

    public void setSrmName(String srmName) {
        this.srmName = srmName;
    }

    public String getRmName() {
        return rmName;
    }

    public void setRmName(String rmName) {
        this.rmName = rmName;
    }

    public SortedMap<String, Long> getRmMap() {
        return rmMap;
    }

    public void setRmMap(SortedMap<String, Long> rmMap) {
        this.rmMap = rmMap;
    }

    public DualListModel<RmSrmMapDto> getRms() {
        return rms;
    }

    public void setRms(DualListModel<RmSrmMapDto> rms) {
        this.rms = rms;
    }

    public List<RmSrmMapDto> getSource() {
        return source;
    }

    public void setSource(List<RmSrmMapDto> source) {
        this.source = source;
    }

    public List<RmSrmMapDto> getTarget() {
        return target;
    }

    public void setTarget(List<RmSrmMapDto> target) {
        this.target = target;
    }

    public List<RmSrmMapDto> getRmSrmMapDtoList() {
        return rmSrmMapDtoList;
    }

    public void setRmSrmMapDtoList(List<RmSrmMapDto> rmSrmMapDtoList) {
        this.rmSrmMapDtoList = rmSrmMapDtoList;
    }

    public RmSrmMapDto getSelectedRmSrmMapDto() {
        return selectedRmSrmMapDto;
    }

    public void setSelectedRmSrmMapDto(RmSrmMapDto selectedRmSrmMapDto) {
        this.selectedRmSrmMapDto = selectedRmSrmMapDto;
    }

}
