package com.rank.ccms.dto;

import java.io.Serializable;
import java.sql.Timestamp;

public class AuditTrailDto implements Serializable {

    private Long id;
    private String tableName;
    private String tableKey1;
    private String tableKey2;
    private String tableKey3;
    private String createdById;
    private Timestamp createdOn;
    private String updatedById;
    private Timestamp updatedOn;
    private String deletedById;
    private Timestamp deletedOn;
    private String enabledById;
    private Timestamp enabledOn;
    private String disabledById;
    private Timestamp disabledOn;
    private String reason;
    private Timestamp dateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableKey1() {
        return tableKey1;
    }

    public void setTableKey1(String tableKey1) {
        this.tableKey1 = tableKey1;
    }

    public String getTableKey2() {
        return tableKey2;
    }

    public void setTableKey2(String tableKey2) {
        this.tableKey2 = tableKey2;
    }

    public String getTableKey3() {
        return tableKey3;
    }

    public void setTableKey3(String tableKey3) {
        this.tableKey3 = tableKey3;
    }

    public String getCreatedById() {
        return createdById;
    }

    public void setCreatedById(String createdById) {
        this.createdById = createdById;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(String updatedById) {
        this.updatedById = updatedById;
    }

    public Timestamp getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Timestamp updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getDeletedById() {
        return deletedById;
    }

    public void setDeletedById(String deletedById) {
        this.deletedById = deletedById;
    }

    public Timestamp getDeletedOn() {
        return deletedOn;
    }

    public void setDeletedOn(Timestamp deletedOn) {
        this.deletedOn = deletedOn;
    }

    public String getEnabledById() {
        return enabledById;
    }

    public void setEnabledById(String enabledById) {
        this.enabledById = enabledById;
    }

    public Timestamp getEnabledOn() {
        return enabledOn;
    }

    public void setEnabledOn(Timestamp enabledOn) {
        this.enabledOn = enabledOn;
    }

    public String getDisabledById() {
        return disabledById;
    }

    public void setDisabledById(String disabledById) {
        this.disabledById = disabledById;
    }

    public Timestamp getDisabledOn() {
        return disabledOn;
    }

    public void setDisabledOn(Timestamp disabledOn) {
        this.disabledOn = disabledOn;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

}
