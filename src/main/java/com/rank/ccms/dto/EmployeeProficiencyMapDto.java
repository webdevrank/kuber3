package com.rank.ccms.dto;

import com.rank.ccms.entities.CategoryMst;
import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.entities.ServiceMst;
import com.rank.ccms.entities.LanguageMst;
import java.io.Serializable;

public class EmployeeProficiencyMapDto implements Serializable {

    private EmployeeMst employeeMst;
    private LanguageMst languageMst;
    private CategoryMst categoryMst;
    private ServiceMst serviceMst;
    private String languageType;
    private String primary;
    private String secondary;
    private Long languageId;
    private Long id;
    private String languageName;

    public EmployeeMst getEmployeeMst() {
        return employeeMst;
    }

    public void setEmployeeMst(EmployeeMst employeeMst) {
        this.employeeMst = employeeMst;
    }

    public LanguageMst getLanguageMst() {
        return languageMst;
    }

    public void setLanguageMst(LanguageMst languageMst) {
        this.languageMst = languageMst;
    }

    public CategoryMst getCategoryMst() {
        return categoryMst;
    }

    public void setCategoryMst(CategoryMst categoryMst) {
        this.categoryMst = categoryMst;
    }

    public ServiceMst getServiceMst() {
        return serviceMst;
    }

    public void setServiceMst(ServiceMst serviceMst) {
        this.serviceMst = serviceMst;
    }

    public String getLanguageType() {
        return languageType;
    }

    public void setLanguageType(String languageType) {
        this.languageType = languageType;
    }

    public String getPrimary() {
        return primary;
    }

    public void setPrimary(String primary) {
        this.primary = primary;
    }

    public String getSecondary() {
        return secondary;
    }

    public void setSecondary(String secondary) {
        this.secondary = secondary;
    }

    public Long getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Long languageId) {
        this.languageId = languageId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

}
