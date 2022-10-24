package com.rank.ccms.dto;

import java.io.Serializable;

public class LanguageTypeDto implements Serializable {

    private Long id;
    private String name;
    private String type;
    private String primaryOrSecondary;
    private Long empId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrimaryOrSecondary() {
        return primaryOrSecondary;
    }

    public void setPrimaryOrSecondary(String primaryOrSecondary) {
        this.primaryOrSecondary = primaryOrSecondary;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

}
