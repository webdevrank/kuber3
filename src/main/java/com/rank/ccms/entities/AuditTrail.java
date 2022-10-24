package com.rank.ccms.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "audit_trail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AuditTrail.findAll", query = "SELECT a FROM AuditTrail a")})
public class AuditTrail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @SequenceGenerator(name = "auditTrailSeq", sequenceName = "audit_trail_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "auditTrailSeq", strategy = GenerationType.SEQUENCE)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "table_name")
    private String tableName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "table_key1")
    private String tableKey1;
    @Size(max = 100)
    @Column(name = "table_key2")
    private String tableKey2;
    @Size(max = 100)
    @Column(name = "table_key3")
    private String tableKey3;
    @Column(name = "created_by_id")
    private Long createdById;
    @Column(name = "created_on")

    private Timestamp createdOn;
    @Column(name = "updated_by_id")
    private Long updatedById;
    @Column(name = "updated_on")

    private Timestamp updatedOn;
    @Column(name = "deleted_by_id")
    private Long deletedById;
    @Column(name = "deleted_on")

    private Timestamp deletedOn;
    @Column(name = "enabled_by_id")
    private Long enabledById;
    @Column(name = "enabled_on")

    private Timestamp enabledOn;
    @Column(name = "disabled_by_id")
    private Long disabledById;
    @Column(name = "disabled_on")

    private Timestamp disabledOn;
    @Size(max = 250)
    @Column(name = "reason")
    private String reason;
    @Column(name = "date_time")

    private Timestamp dateTime;

    public AuditTrail() {
    }

    public AuditTrail(Long id) {
        this.id = id;
    }

    public AuditTrail(Long id, String tableName, String tableKey1) {
        this.id = id;
        this.tableName = tableName;
        this.tableKey1 = tableKey1;
    }

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

    public Long getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    public Long getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public Timestamp getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Timestamp updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Long getDeletedById() {
        return deletedById;
    }

    public void setDeletedById(Long deletedById) {
        this.deletedById = deletedById;
    }

    public Timestamp getDeletedOn() {
        return deletedOn;
    }

    public void setDeletedOn(Timestamp deletedOn) {
        this.deletedOn = deletedOn;
    }

    public Long getEnabledById() {
        return enabledById;
    }

    public void setEnabledById(Long enabledById) {
        this.enabledById = enabledById;
    }

    public Timestamp getEnabledOn() {
        return enabledOn;
    }

    public void setEnabledOn(Timestamp enabledOn) {
        this.enabledOn = enabledOn;
    }

    public Long getDisabledById() {
        return disabledById;
    }

    public void setDisabledById(Long disabledById) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AuditTrail)) {
            return false;
        }
        AuditTrail other = (AuditTrail) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.rank.ccms.entities.AuditTrail[ id=" + id + " ]";
    }

}
