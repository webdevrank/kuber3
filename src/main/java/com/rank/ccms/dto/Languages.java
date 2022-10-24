package com.rank.ccms.dto;

import java.io.Serializable;

public class Languages implements Serializable {

    private Long languagePrimaryId;
    private String languagePrimaryCode;
    private String languagePrimaryName;

    private Long languageSecondaryId;
    private String languageSecondaryCode;
    private String languageSecondaryName;

    public Long getLanguagePrimaryId() {
        return languagePrimaryId;
    }

    public void setLanguagePrimaryId(Long languagePrimaryId) {
        this.languagePrimaryId = languagePrimaryId;
    }

    public String getLanguagePrimaryCode() {
        return languagePrimaryCode;
    }

    public void setLanguagePrimaryCode(String languagePrimaryCode) {
        this.languagePrimaryCode = languagePrimaryCode;
    }

    public String getLanguagePrimaryName() {
        return languagePrimaryName;
    }

    public void setLanguagePrimaryName(String languagePrimaryName) {
        this.languagePrimaryName = languagePrimaryName;
    }

    public Long getLanguageSecondaryId() {
        return languageSecondaryId;
    }

    public void setLanguageSecondaryId(Long languageSecondaryId) {
        this.languageSecondaryId = languageSecondaryId;
    }

    public String getLanguageSecondaryCode() {
        return languageSecondaryCode;
    }

    public void setLanguageSecondaryCode(String languageSecondaryCode) {
        this.languageSecondaryCode = languageSecondaryCode;
    }

    public String getLanguageSecondaryName() {
        return languageSecondaryName;
    }

    public void setLanguageSecondaryName(String languageSecondaryName) {
        this.languageSecondaryName = languageSecondaryName;
    }

}
