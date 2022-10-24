package com.rank.ccms.rest.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class CategoryDto implements Serializable {

    @JsonIgnore
    private static final long serialVersionUID = 6483498465419292048L;

    private Long id;
    private String catgCd;
    private String catgName;
    private String catgDesc;
    @JsonIgnore
    private boolean activeFlg;
    @JsonIgnore
    private boolean deleteFlg;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCatgCd() {
        return catgCd;
    }

    public void setCatgCd(String catgCd) {
        this.catgCd = catgCd;
    }

    public String getCatgName() {
        return catgName;
    }

    public void setCatgName(String catgName) {
        this.catgName = catgName;
    }

    public String getCatgDesc() {
        return catgDesc;
    }

    public void setCatgDesc(String catgDesc) {
        this.catgDesc = catgDesc;
    }

    public boolean isActiveFlg() {
        return activeFlg;
    }

    public void setActiveFlg(boolean activeFlg) {
        this.activeFlg = activeFlg;
    }

    public boolean isDeleteFlg() {
        return deleteFlg;
    }

    public void setDeleteFlg(boolean deleteFlg) {
        this.deleteFlg = deleteFlg;
    }

}
