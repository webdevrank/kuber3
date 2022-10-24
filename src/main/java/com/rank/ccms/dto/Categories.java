package com.rank.ccms.dto;

import java.io.Serializable;

public class Categories implements Serializable {

    private Long categoryPrimaryId;
    private String categoryPrimaryCode;
    private String categoryPrimaryName;
    private Long categorySecondaryId;
    private String categorySecondaryCode;
    private String categorySecondaryName;

    public Long getCategoryPrimaryId() {
        return categoryPrimaryId;
    }

    public void setCategoryPrimaryId(Long categoryPrimaryId) {
        this.categoryPrimaryId = categoryPrimaryId;
    }

    public String getCategoryPrimaryCode() {
        return categoryPrimaryCode;
    }

    public void setCategoryPrimaryCode(String categoryPrimaryCode) {
        this.categoryPrimaryCode = categoryPrimaryCode;
    }

    public String getCategoryPrimaryName() {
        return categoryPrimaryName;
    }

    public void setCategoryPrimaryName(String categoryPrimaryName) {
        this.categoryPrimaryName = categoryPrimaryName;
    }

    public Long getCategorySecondaryId() {
        return categorySecondaryId;
    }

    public void setCategorySecondaryId(Long categorySecondaryId) {
        this.categorySecondaryId = categorySecondaryId;
    }

    public String getCategorySecondaryCode() {
        return categorySecondaryCode;
    }

    public void setCategorySecondaryCode(String categorySecondaryCode) {
        this.categorySecondaryCode = categorySecondaryCode;
    }

    public String getCategorySecondaryName() {
        return categorySecondaryName;
    }

    public void setCategorySecondaryName(String categorySecondaryName) {
        this.categorySecondaryName = categorySecondaryName;
    }

}
