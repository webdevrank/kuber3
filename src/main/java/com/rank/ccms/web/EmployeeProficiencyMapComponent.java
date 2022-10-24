package com.rank.ccms.web;

import com.rank.ccms.dto.EmployeeMstDto;
import com.rank.ccms.dto.EmployeeProficiencyMapDto;
import com.rank.ccms.dto.LanguageTypeDto;
import com.rank.ccms.entities.CategoryMst;
import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.entities.EmployeeProficiencyMap;
import com.rank.ccms.entities.EmployeeTypeMst;
import com.rank.ccms.entities.LanguageMst;
import com.rank.ccms.entities.ServiceMst;
import com.rank.ccms.service.CategoryMstService;
import com.rank.ccms.service.EmployeeMstService;
import com.rank.ccms.service.EmployeeProficiencyMapService;
import com.rank.ccms.service.EmployeeTypeMstService;
import com.rank.ccms.service.LanguageMstService;
import com.rank.ccms.service.ServiceMstService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class EmployeeProficiencyMapComponent implements Serializable {

    private EmployeeMst employeeMst;
    private List<EmployeeMstDto> listEmployee;
    private List<EmployeeMstDto> filterdListEmployee;
    private EmployeeMstDto selectedEmp = null;
    private EmployeeTypeMst employeeTypeMst;
    private LanguageTypeDto languageTypeDto;
    private List<LanguageTypeDto> listLanguageTypeDto;
    private List<LanguageTypeDto> listCategoryTypeDto;
    private List<LanguageTypeDto> listServiceTypeDto;
    private EmployeeProficiencyMap employeeProficiencyMap;
    private List<EmployeeProficiencyMap> employeeProficiencyList;
    private List<EmployeeProficiencyMapDto> selectEmployeeProficiency = new ArrayList<>();
    private EmployeeProficiencyMapDto employeeProficiencyMapDto;
    private CategoryMst categoryMst;
    private LanguageMst languageMst;
    private ServiceMst serviceMst;
    private List<EmployeeProficiencyMapDto> employeeProficienctMapDtolist = new ArrayList<>();
    private List<EmployeeProficiencyMapDto> filteredemployeeProficienctMapDtolist;
    private List<EmployeeMstDto> filteredEmployeeList;
    private Long languageId;
    private Long serviceId;
    private Long categoryId;
    private Boolean languageFlag;
    private Boolean categoryFlag;
    private Boolean serviceFlag;
    private Boolean noneFlag;
    private Boolean priFlag;
    private String employeeName;

    @Autowired
    private EmployeeMstService employeeMstService;
    @Autowired
    private EmployeeTypeMstService employeeTypeMstService;
    @Autowired
    private CategoryMstService categoryMstService;
    @Autowired
    private LanguageMstService languagemstService;
    @Autowired
    private EmployeeProficiencyMapService employeeProficiencyMapService;
    @Autowired
    private ServiceMstService serviceMstService;

    public EmployeeProficiencyMapComponent() {
        employeeMst = new EmployeeMst();
        employeeTypeMst = new EmployeeTypeMst();
        employeeProficiencyMap = new EmployeeProficiencyMap();
        selectedEmp = new EmployeeMstDto();
        this.listEmployee = new ArrayList<>();
        languageTypeDto = new LanguageTypeDto();
        this.listLanguageTypeDto = new ArrayList<>();
        this.employeeProficiencyList = new ArrayList<>();
    }

    public void loadEmployeeList() {
        listEmployee.clear();
        List<EmployeeMst> employeeList = employeeMstService.findAllNonDeletedEmployeeMsts();
        employeeMst = new EmployeeMst();

        employeeTypeMst = employeeTypeMstService.findEmployeeTypeByEmployeeTypeName("Agent");
        selectedEmp = new EmployeeMstDto();
        List<EmployeeMstDto> localEmployeeMstList = new ArrayList<>();
        for (EmployeeMst employeeList1 : employeeList) {
            if (Objects.equals(employeeList1.getEmpTypId().getId(), employeeTypeMst.getId())) {
                EmployeeMstDto employeeMstDto = new EmployeeMstDto();
                employeeMstDto.setAddrsLine1(employeeList1.getAddrsLine1());
                employeeMstDto.setAddrsLine2(employeeList1.getAddrsLine2());
                employeeMstDto.setCellPhone(employeeList1.getCellPhoneStr());
                employeeMstDto.setCity(employeeList1.getCity());
                employeeMstDto.setCountry(employeeList1.getCountry());
                employeeMstDto.setEmail(employeeList1.getEmail());
                employeeMstDto.setFirstName(employeeList1.getFirstName());
                employeeMstDto.setHomePhone(employeeList1.getHomePhoneStr());
                employeeMstDto.setId(employeeList1.getId().toString());
                employeeMstDto.setLastName(employeeList1.getLastName());
                employeeMstDto.setLoginId(employeeList1.getLoginId());
                employeeMstDto.setLoginPasswd(employeeList1.getLoginPasswd());
                employeeMstDto.setMidName(employeeList1.getMidName());
                employeeMstDto.setOfficePhone(employeeList1.getOfficePhoneStr());
                employeeMstDto.setPin(employeeList1.getPin().toString());
                employeeMstDto.setStateNm(employeeList1.getStateNm());
                localEmployeeMstList.add(employeeMstDto);
            }
        }
        this.setListEmployee(localEmployeeMstList);
        this.setFilterdListEmployee(getListEmployee());
    }

    public void loadSkillAccordingToType() {

        this.noneFlag = true;
        this.categoryFlag = false;
        this.serviceFlag = false;
        this.languageFlag = false;
        this.priFlag = false;

        if (languageTypeDto.getType().equalsIgnoreCase("Category")) {
            this.categoryFlag = true;
            this.serviceFlag = false;
            this.languageFlag = false;
            this.noneFlag = false;
            this.priFlag = true;

        }
        if (languageTypeDto.getType().equalsIgnoreCase("service")) {
            this.categoryFlag = false;
            this.serviceFlag = true;
            this.languageFlag = false;
            this.noneFlag = false;
            this.priFlag = true;

        }
        if (languageTypeDto.getType().equalsIgnoreCase("language")) {
            this.categoryFlag = false;
            this.serviceFlag = false;
            this.languageFlag = true;
            this.noneFlag = false;
            this.priFlag = true;

        }

    }

    public void saveEmployeeProficiency(HttpServletRequest request) {
        int check = 0;
        LanguageTypeDto languageDto = getLanguageTypeDto();
        employeeProficiencyMap = new EmployeeProficiencyMap();
        EmployeeMst empmst = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
        EmployeeMstDto employeeMstDto = new EmployeeMstDto();
        employeeMstDto.setAddrsLine1(empmst.getAddrsLine1());
        employeeMstDto.setAddrsLine2(empmst.getAddrsLine2());
        employeeMstDto.setCellPhone(empmst.getCellPhoneStr());
        employeeMstDto.setCity(empmst.getCity());
        employeeMstDto.setCountry(empmst.getCountry());
        employeeMstDto.setEmail(empmst.getEmail());
        employeeMstDto.setFirstName(empmst.getFirstName());
        employeeMstDto.setHomePhone(empmst.getHomePhoneStr());
        employeeMstDto.setId(empmst.getId().toString());
        employeeMstDto.setLastName(empmst.getLastName());
        employeeMstDto.setLoginPasswd(empmst.getLoginPasswd());
        employeeMstDto.setMidName(empmst.getMidName());
        employeeMstDto.setOfficePhone(empmst.getOfficePhoneStr());
        employeeMstDto.setPin(empmst.getPin().toString());
        employeeMstDto.setStateNm(empmst.getStateNm());

        this.setSelectedEmp(employeeMstDto);
        employeeMst = employeeMstService.findEmployeeMstById(languageDto.getEmpId());
        employeeTypeMst = employeeTypeMstService.findEmployeeTypeMstById(employeeMst.getEmpTypId().getId());
        List<EmployeeProficiencyMap> employeeProfMapList;
        employeeProfMapList = employeeProficiencyMapService.findAllActivenNonDeletedEmployeeProficiencyMapsByEmpId(employeeMst.getId());
        if (languageDto.getType().equalsIgnoreCase("Language")) {
            languageDto.setId(getLanguageId());
        } else if (languageDto.getType().equalsIgnoreCase("Service")) {
            languageDto.setId(getServiceId());
        } else if (languageDto.getType().equalsIgnoreCase("Category")) {
            languageDto.setId(getCategoryId());
        }
        for (EmployeeProficiencyMap employeeProfMapList1 : employeeProfMapList) {
            if (employeeProfMapList1.getType().equals(languageDto.getType())) {
                if (employeeProfMapList1.getSkillId().equals(languageDto.getId())) {
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!Agent has already been mapped with this skill!", "Agent has already been mapped with this skill!"));
                    check = 1;
                    break;
                }
            }
        }

        if (check == 0) {
            employeeProficiencyMap.setActiveFlg(true);
            employeeProficiencyMap.setDeleteFlg(false);
            if (languageDto.getPrimaryOrSecondary().equalsIgnoreCase("primary")) {
                employeeProficiencyMap.setPrimarySkill((long) 1);
                employeeProficiencyMap.setSecondarySkill((long) 0);

            } else if (languageDto.getPrimaryOrSecondary().equalsIgnoreCase("secondary")) {
                employeeProficiencyMap.setPrimarySkill((long) 0);
                employeeProficiencyMap.setSecondarySkill((long) 1);
            }
            employeeProficiencyMap.setType(languageDto.getType());
            employeeProficiencyMap.setSkillId(languageDto.getId());
            employeeProficiencyMap.setEmpTypId(employeeMst.getEmpTypId().getId());
            employeeMst = employeeMstService.findEmployeeMstById(languageDto.getEmpId());
            employeeProficiencyMap.setEmpId(employeeMst);

            employeeProficiencyMap = employeeProficiencyMapService.saveEmployeeProficiency(employeeProficiencyMap, empmst);

            if (null == employeeProficiencyMap) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Save Unsuccessfull!!"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Saved Successfully!", ""));
                checkEdit();

            }
        }
    }

    public String back() {
        loadEmployeeProficiencyMapDto();
        selectedEmp = new EmployeeMstDto();
        return "/pages/language/employeeSkill.xhtml";
    }

    public void loadEmployeeProficiencyMapDto() {
        selectEmployeeProficiency = new ArrayList<>();
        List<EmployeeProficiencyMap> employeeProficiencyListTemp;
        selectEmployeeProficiency = new ArrayList<>();
        employeeProficiencyListTemp = employeeProficiencyMapService.findAllNonDeletedEmployeeProficiencyMaps();
        String type;
        Long languageIdlocal;
        String languageName = null;
        Long primaryLanguage;
        Long secondaryLanguage;
        employeeMst = new EmployeeMst();
        categoryMst = new CategoryMst();
        languageMst = new LanguageMst();
        serviceMst = new ServiceMst();
        employeeProficienctMapDtolist.clear();

        for (EmployeeProficiencyMap employeeProficiencyListTemp1 : employeeProficiencyListTemp) {
            employeeProficiencyMapDto = new EmployeeProficiencyMapDto();
            employeeMst = employeeMstService.findEmployeeMstById(employeeProficiencyListTemp1.getEmpId().getId());
            type = employeeProficiencyListTemp1.getType();
            languageIdlocal = employeeProficiencyListTemp1.getSkillId();
            if (null != type && type.equalsIgnoreCase("Category")) {
                categoryMst = categoryMstService.findCategoryMstById(languageIdlocal);
                languageName = categoryMst.getCatgDesc();
            }
            if (null != type && type.equalsIgnoreCase("language")) {

                languageMst = languagemstService.findAllLanguageMstById(languageIdlocal);
                languageName = languageMst.getLanguageDesc();
            }
            if (null != type && type.equalsIgnoreCase("Service")) {
                serviceMst = serviceMstService.findAllServiceMstById(languageIdlocal);
                languageName = serviceMst.getServiceDesc();
            }
            primaryLanguage = employeeProficiencyListTemp1.getPrimarySkill();
            if (primaryLanguage == 0) {
                employeeProficiencyMapDto.setPrimary("NO");
            } else {
                employeeProficiencyMapDto.setPrimary("YES");
            }
            secondaryLanguage = employeeProficiencyListTemp1.getSecondarySkill();
            if (secondaryLanguage == 0) {
                employeeProficiencyMapDto.setSecondary("NO");
            } else {
                employeeProficiencyMapDto.setSecondary("YES");
            }
            employeeProficiencyMapDto.setCategoryMst(categoryMst);
            employeeProficiencyMapDto.setEmployeeMst(employeeMst);
            employeeProficiencyMapDto.setServiceMst(serviceMst);
            employeeProficiencyMapDto.setLanguageMst(languageMst);
            employeeProficiencyMapDto.setLanguageType(type);
            employeeProficiencyMapDto.setLanguageId(languageIdlocal);
            employeeProficiencyMapDto.setLanguageName(languageName);
            employeeProficiencyMapDto.setId(employeeProficiencyListTemp1.getId());
            employeeProficienctMapDtolist.add(employeeProficiencyMapDto);
        }

        this.setFilteredemployeeProficienctMapDtolist(this.getEmployeeProficienctMapDtolist());
    }

    public String deactivateSkill(HttpServletRequest request) {

        List<EmployeeProficiencyMapDto> selectList = getSelectEmployeeProficiency();
        try {
            EmployeeMst empmst = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
            for (EmployeeProficiencyMapDto selectList1 : selectList) {
                EmployeeProficiencyMap employeeProficiencyMapping;
                employeeProficiencyMapping = employeeProficiencyMapService.findNonDeletedEmployeeProficiencyById(selectList1.getId());
                employeeProficiencyMapping.setDeleteFlg(true);
                employeeProficiencyMapService.saveEmployeeProficiency(employeeProficiencyMapping, empmst);
            }
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "De-activated Successfully", "De-activated Successfully"));
            loadEmployeeProficiencyMapDto();
            return "/pages/language/deleteEmployeeLanguage.xhtml";
        } catch (Exception e) {

            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "failed,Please try again", "Please try again!!"));
            return "/pages/language/deleteEmployeeLanguage.xhtml";

        }
    }

    public String checkEdit() {
        if (selectedEmp != null) {
            languageTypeDto.setType("");
            languageTypeDto.setId((long) 0);
            languageTypeDto.setPrimaryOrSecondary("");
            this.categoryFlag = false;
            this.serviceFlag = false;
            this.languageFlag = false;
            this.noneFlag = true;
            this.priFlag = false;
            this.setLanguageId((long) 0);
            this.setCategoryId((long) 0);
            this.setServiceId((long) 0);

            listLanguageTypeDto = new ArrayList<>();
            listServiceTypeDto = new ArrayList<>();
            listCategoryTypeDto = new ArrayList<>();

            List<CategoryMst> listCategoryMst;
            listCategoryMst = categoryMstService.findAllNonDeletedCategoryMsts();
            listCategoryTypeDto.clear();

            for (CategoryMst listCategoryMst1 : listCategoryMst) {
                languageTypeDto = new LanguageTypeDto();
                languageTypeDto.setId(listCategoryMst1.getId());
                languageTypeDto.setName(listCategoryMst1.getCatgDesc());
                listCategoryTypeDto.add(languageTypeDto);
            }
            setListCategoryTypeDto(listCategoryTypeDto);

            List<ServiceMst> listServiceMst;

            listServiceMst = serviceMstService.findAllNonDeletedServiceMsts();

            listServiceTypeDto.clear();

            for (ServiceMst listServiceMst1 : listServiceMst) {
                languageTypeDto = new LanguageTypeDto();
                languageTypeDto.setId(listServiceMst1.getId());
                languageTypeDto.setName(listServiceMst1.getServiceDesc());
                listServiceTypeDto.add(languageTypeDto);
            }
            setListServiceTypeDto(listServiceTypeDto);

            List<LanguageMst> listLanguageMst;
            listLanguageMst = languagemstService.findAllNonDeletedLanguageMsts();

            listLanguageTypeDto.clear();

            for (LanguageMst listLanguageMst1 : listLanguageMst) {
                languageTypeDto = new LanguageTypeDto();
                languageTypeDto.setId(listLanguageMst1.getId());
                languageTypeDto.setName(listLanguageMst1.getLanguageDesc());
                languageTypeDto.setType("Language");
                listLanguageTypeDto.add(languageTypeDto);
            }
            languageTypeDto = new LanguageTypeDto();
            languageTypeDto.setEmpId(employeeMst.getId());
            this.employeeName = employeeMst.getFirstName() + " " + employeeMst.getLastName() + " ( " + employeeMst.getLoginId() + " )";
            setListLanguageTypeDto(listLanguageTypeDto);

            return "/pages/language/addSkillToEmployee.xhtml";
        } else {
            RequestContext.getCurrentInstance().execute(" PF('editDialog').show();");

            return "";
        }

    }

    public Boolean getPriFlag() {
        return priFlag;
    }

    public void setPriFlag(Boolean priFlag) {
        this.priFlag = priFlag;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Boolean getNoneFlag() {
        return noneFlag;
    }

    public void setNoneFlag(Boolean noneFlag) {
        this.noneFlag = noneFlag;
    }

    public Boolean getLanguageFlag() {
        return languageFlag;
    }

    public void setLanguageFlag(Boolean languageFlag) {
        this.languageFlag = languageFlag;
    }

    public Boolean getCategoryFlag() {
        return categoryFlag;
    }

    public void setCategoryFlag(Boolean categoryFlag) {
        this.categoryFlag = categoryFlag;
    }

    public Boolean getServiceFlag() {
        return serviceFlag;
    }

    public void setServiceFlag(Boolean serviceFlag) {
        this.serviceFlag = serviceFlag;
    }

    public Long getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Long languageId) {
        this.languageId = languageId;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public List<LanguageTypeDto> getListCategoryTypeDto() {
        return listCategoryTypeDto;
    }

    public void setListCategoryTypeDto(List<LanguageTypeDto> listCategoryTypeDto) {
        this.listCategoryTypeDto = listCategoryTypeDto;
    }

    public List<LanguageTypeDto> getListServiceTypeDto() {
        return listServiceTypeDto;
    }

    public void setListServiceTypeDto(List<LanguageTypeDto> listServiceTypeDto) {
        this.listServiceTypeDto = listServiceTypeDto;
    }

    public EmployeeMst getEmployeeMst() {
        return employeeMst;
    }

    public void setEmployeeMst(EmployeeMst employeeMst) {
        this.employeeMst = employeeMst;
    }

    public List<EmployeeMstDto> getListEmployee() {
        return listEmployee;
    }

    public void setListEmployee(List<EmployeeMstDto> listEmployee) {
        this.listEmployee = listEmployee;
    }

    public EmployeeTypeMst getEmployeeTypeMst() {
        return employeeTypeMst;
    }

    public void setEmployeeTypeMst(EmployeeTypeMst employeeTypeMst) {
        this.employeeTypeMst = employeeTypeMst;
    }

    public EmployeeMstDto getSelectedEmp() {
        return selectedEmp;
    }

    public void setSelectedEmp(EmployeeMstDto selectedEmp) {

        if (selectedEmp != null) {

            setEmployeeMst(employeeMstService.findEmployeeMstById(Long.parseLong(selectedEmp.getId())));
            this.selectedEmp = selectedEmp;
        } else {
            this.selectedEmp = null;
        }

    }

    public LanguageTypeDto getLanguageTypeDto() {
        return languageTypeDto;
    }

    public void setLanguageTypeDto(LanguageTypeDto languageTypeDto) {
        this.languageTypeDto = languageTypeDto;
    }

    public List<LanguageTypeDto> getListLanguageTypeDto() {
        return listLanguageTypeDto;
    }

    public void setListLanguageTypeDto(List<LanguageTypeDto> listLanguageTypeDto) {
        this.listLanguageTypeDto = listLanguageTypeDto;
    }

    public EmployeeProficiencyMap getEmployeeProficiencyMap() {
        return employeeProficiencyMap;
    }

    public void setEmployeeProficiencyMap(EmployeeProficiencyMap employeeProficiencyMap) {
        this.employeeProficiencyMap = employeeProficiencyMap;
    }

    public List<EmployeeProficiencyMap> getEmployeeProficiencyList() {
        return employeeProficiencyList;
    }

    public void setEmployeeProficiencyList(List<EmployeeProficiencyMap> employeeProficiencyList) {
        this.employeeProficiencyList = employeeProficiencyList;
    }

    public List<EmployeeProficiencyMapDto> getSelectEmployeeProficiency() {
        return selectEmployeeProficiency;
    }

    public void setSelectEmployeeProficiency(List<EmployeeProficiencyMapDto> selectEmployeeProficiency) {
        this.selectEmployeeProficiency = selectEmployeeProficiency;
    }

    public EmployeeProficiencyMapDto getEmployeeProficiencyMapDto() {
        return employeeProficiencyMapDto;
    }

    public void setEmployeeProficiencyMapDto(EmployeeProficiencyMapDto employeeProficiencyMapDto) {
        this.employeeProficiencyMapDto = employeeProficiencyMapDto;
    }

    public CategoryMst getCategoryMst() {
        return categoryMst;
    }

    public LanguageMst getLanguageMst() {
        return languageMst;
    }

    public void setLanguageMst(LanguageMst languageMst) {
        this.languageMst = languageMst;
    }

    public void setCategoryMst(CategoryMst categoryMst) {
        this.categoryMst = categoryMst;
    }

    public List<EmployeeProficiencyMapDto> getEmployeeProficienctMapDtolist() {
        return employeeProficienctMapDtolist;
    }

    public void setEmployeeProficienctMapDtolist(List<EmployeeProficiencyMapDto> employeeProficienctMapDtolist) {
        this.employeeProficienctMapDtolist = employeeProficienctMapDtolist;
    }

    public List<EmployeeProficiencyMapDto> getFilteredemployeeProficienctMapDtolist() {
        return filteredemployeeProficienctMapDtolist;
    }

    public void setFilteredemployeeProficienctMapDtolist(List<EmployeeProficiencyMapDto> filteredemployeeProficienctMapDtolist) {
        this.filteredemployeeProficienctMapDtolist = filteredemployeeProficienctMapDtolist;
    }

    public List<EmployeeMstDto> getFilteredEmployeeList() {
        return filteredEmployeeList;
    }

    public void setFilteredEmployeeList(List<EmployeeMstDto> filteredEmployeeList) {
        this.filteredEmployeeList = filteredEmployeeList;
    }

    public List<EmployeeMstDto> getFilterdListEmployee() {
        return filterdListEmployee;
    }

    public void setFilterdListEmployee(List<EmployeeMstDto> filterdListEmployee) {
        this.filterdListEmployee = filterdListEmployee;
    }

}
