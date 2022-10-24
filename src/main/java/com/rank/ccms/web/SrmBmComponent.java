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

import com.rank.ccms.dto.SrmBmMapDto;
import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.entities.EmployeeTypeMst;
import com.rank.ccms.entities.SrmBmMap;
import com.rank.ccms.service.EmployeeMstService;
import com.rank.ccms.service.SrmBmMapService;
import java.util.Objects;

@Component
@Scope("session")
public class SrmBmComponent implements Serializable {

    org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(this.getClass());

    private Long bmId;
    private Long srmId;
    private String bmName;
    private String srmName;

    private SortedMap<String, Long> srmMap = new TreeMap<>();
    private DualListModel<SrmBmMapDto> srms = new DualListModel<>();
    private List<SrmBmMapDto> source;
    private List<SrmBmMapDto> target;

    List<SrmBmMapDto> srmBmMapDtoList = new ArrayList<>();

    SrmBmMapDto selectedSrmBmMapDto;

    @Autowired
    EmployeeMstService employeeMstService;
    @Autowired
    SrmBmMapService srmBmMapService;

    public SrmBmComponent() {

    }

    public String onLoad() {
        logger.info("inside onLoad........");
        refresh();
        return "/pages/maps/srmBmMap.xhtml?faces-redirect=true";
    }

    public void refresh() {
        try {

            if (srmBmMapDtoList != null) {
                srmBmMapDtoList.clear();
            }

            selectedSrmBmMapDto = null;
            EmployeeTypeMst employeeTypeMst = new EmployeeTypeMst();
            employeeTypeMst.setId(9l);
            List<EmployeeMst> employeeList = employeeMstService.findNonDeletedEmployeeByEmpType("BranchManager");
            for (EmployeeMst employeeList1 : employeeList) {
                SrmBmMapDto srmBmMapDto = new SrmBmMapDto();
                srmBmMapDto.setBmId(employeeList1.getId());
                srmBmMapDto.setBmName(employeeList1.getFirstName() + employeeList1.getMidName() + employeeList1.getLastName());
                List<SrmBmMap> srmBmMapList = srmBmMapService.getSRMsMappedWithBM(employeeList1.getId());
                String mappedSrms = "";
                for (SrmBmMap srmBmMap : srmBmMapList) {
                    EmployeeMst empMst = employeeMstService.findEmployeeMstById(srmBmMap.getSrmId().getId());
                    mappedSrms = mappedSrms + empMst.getFirstName() + empMst.getMidName() + empMst.getLastName() + ", ";
                }
                if (mappedSrms.trim().length() > 0) {

                    mappedSrms = mappedSrms.substring(0, mappedSrms.length() - 2);

                }
                srmBmMapDto.setMappedSrm(mappedSrms);
                srmBmMapDtoList.add(srmBmMapDto);
            }
            logger.info("srmBmMapDtoList size : " + srmBmMapDtoList.size());

        } catch (Exception e) {
            logger.error("refresh ERROR ", e);
        }
    }

    public void onBeforeEditTypeMapList(Long employeeId) {
        source = new ArrayList<>();
        target = new ArrayList<>();
        srms = new DualListModel<>(source, target);
        this.setSrmMap(getSRMList());
        populatePickList(employeeId);
        try {
            EmployeeMst emp = employeeMstService.findEmployeeMstById(employeeId);
            this.setBmId(emp.getId());
            this.setBmName(emp.getFirstName() + emp.getMidName() + emp.getLastName());

        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(SrmBmComponent.class.getName()).log(Level.SEVERE, null, ex);
        }
        RequestContext.getCurrentInstance().execute("PF('editSrmBmMapDialogVar').show();");

    }

    public void populatePickList(Long employeeId) {

        source = new ArrayList<>();
        target = new ArrayList<>();
        logger.info("employeeId : " + employeeId);
        if (employeeId != null && employeeId > 0) {

            try {
                //Get All the Roles
                for (EmployeeMst em : employeeMstService.findNonDeletedEmployeeByEmpType("SeniorRelationshipManager")) {
                    logger.info("Employee  : " + em.getFirstName());
                    SrmBmMapDto srmBmMapDto = new SrmBmMapDto();
                    List<SrmBmMap> mappedList = srmBmMapService.getSRMsMappedWithBM(employeeId);
                    logger.info("mappedList size : " + mappedList.size());
                    if (mappedList.size() > 0) {
                        for (SrmBmMap obj : mappedList) {
                            if (Objects.equals(obj.getSrmId().getId(), em.getId())) {
                                srmBmMapDto.setId(mappedList.get(0).getId());
                                srmBmMapDto.setBmId(employeeId);
                                srmBmMapDto.setSrmId(em.getId());
                                srmBmMapDto.setSrmName(em.getFirstName() + em.getMidName() + em.getLastName());
                                getTarget().add(srmBmMapDto);
                            } else {
                                SrmBmMap mappedBM = srmBmMapService.getBMMappedWithSRM(em.getId());
                                if (mappedBM == null) {
                                    srmBmMapDto.setBmId(employeeId);
                                    srmBmMapDto.setSrmId(em.getId());
                                    srmBmMapDto.setSrmName(em.getFirstName() + em.getMidName() + em.getLastName());
                                    getSource().add(srmBmMapDto);
                                    break;
                                }
                            }

                        }

                    } else {
                        SrmBmMap mappedBM = srmBmMapService.getBMMappedWithSRM(em.getId());
                        if (mappedBM == null) {
                            srmBmMapDto.setBmId(employeeId);
                            srmBmMapDto.setSrmId(em.getId());
                            srmBmMapDto.setSrmName(em.getFirstName() + em.getMidName() + em.getLastName());
                            getSource().add(srmBmMapDto);
                        }
                    }

                }
            } catch (Exception ex) {
                java.util.logging.Logger.getLogger(SrmBmComponent.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        srms = new DualListModel<>(source, target);

    }

    private TreeMap<String, Long> getSRMList() {
        TreeMap<String, Long> allSrms = new TreeMap();

        EmployeeTypeMst employeeTypeMst = new EmployeeTypeMst();
        employeeTypeMst.setId(9l);
        List<EmployeeMst> empMstList = new ArrayList<>();
        try {
            empMstList = employeeMstService.findNonDeletedEmployeeByEmpType("BranchManager");
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(SrmBmComponent.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (EmployeeMst empMst : empMstList) {
            allSrms.put(empMst.getFirstName() + empMst.getMidName() + empMst.getLastName(), empMst.getId());

        }
        return allSrms;
    }

    public void save() {

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();

        try {
            //First Delete All Roles of Employee Type From Source
            for (SrmBmMapDto srmBmMapDto : srms.getSource()) {
                if (srmBmMapDto.getId() != null) {

                    if (srmBmMapDto.getBmId().equals(this.bmId)) {

                        List<SrmBmMap> srmBmMapList = srmBmMapService.findBMSRMMapByBMandSRM(srmBmMapDto.getBmId(), srmBmMapDto.getSrmId());
                        for (SrmBmMap srmBmMap : srmBmMapList) {
                            srmBmMapService.deleteSrmBmMap(srmBmMap);
                        }
                    }

                }
            }

            //Sava Data 
            for (SrmBmMapDto srmBmMapDto : srms.getTarget()) {

                SrmBmMap srmBmMap = geEntityFromDto(srmBmMapDto);
                srmBmMap.setActiveFlg(true);
                srmBmMap.setDeleteFlg(false);

                if (srmBmMapDto.getBmId().equals(this.bmId)) {

                    srmBmMapService.saveBMSRMMap(srmBmMap);
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

    private SrmBmMap geEntityFromDto(SrmBmMapDto srmBmMapDto) {
        SrmBmMap srmBmMap = new SrmBmMap();

        srmBmMap.setId(srmBmMapDto.getId());
        try {
            srmBmMap.setBmId(employeeMstService.findEmployeeMstById(srmBmMapDto.getBmId()));

            srmBmMap.setSrmId(employeeMstService.findEmployeeMstById(srmBmMapDto.getSrmId()));
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(SrmBmComponent.class.getName()).log(Level.SEVERE, null, ex);
        }

        return srmBmMap;
    }

    public Long getBmId() {
        return bmId;
    }

    public void setBmId(Long bmId) {
        this.bmId = bmId;
    }

    public Long getSrmId() {
        return srmId;
    }

    public void setSrmId(Long srmId) {
        this.srmId = srmId;
    }

    public String getBmName() {
        return bmName;
    }

    public void setBmName(String bmName) {
        this.bmName = bmName;
    }

    public String getSrmName() {
        return srmName;
    }

    public void setSrmName(String srmName) {
        this.srmName = srmName;
    }

    public SortedMap<String, Long> getSrmMap() {
        return srmMap;
    }

    public void setSrmMap(SortedMap<String, Long> srmMap) {
        this.srmMap = srmMap;
    }

    public DualListModel<SrmBmMapDto> getSrms() {
        return srms;
    }

    public void setSrms(DualListModel<SrmBmMapDto> srms) {
        this.srms = srms;
    }

    public List<SrmBmMapDto> getSource() {
        return source;
    }

    public void setSource(List<SrmBmMapDto> source) {
        this.source = source;
    }

    public List<SrmBmMapDto> getTarget() {
        return target;
    }

    public void setTarget(List<SrmBmMapDto> target) {
        this.target = target;
    }

    public List<SrmBmMapDto> getSrmBmMapDtoList() {
        return srmBmMapDtoList;
    }

    public void setSrmBmMapDtoList(List<SrmBmMapDto> srmBmMapDtoList) {
        this.srmBmMapDtoList = srmBmMapDtoList;
    }

    public SrmBmMapDto getSelectedSrmBmMapDto() {
        return selectedSrmBmMapDto;
    }

    public void setSelectedSrmBmMapDto(SrmBmMapDto selectedSrmBmMapDto) {
        this.selectedSrmBmMapDto = selectedSrmBmMapDto;
    }

}
