package com.rank.ccms.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "employee_proficiency_map")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmployeeProficiencyMap.findAll", query = "SELECT e FROM EmployeeProficiencyMap e")})
public class EmployeeProficiencyMap implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long empTypId;
    private String type;
    private Long skillId;
    private Long primarySkill;
    private Long secondarySkill;
    private boolean activeFlg;
    private boolean deleteFlg;
    private EmployeeMst empId;

    public EmployeeProficiencyMap() {
    }

    public EmployeeProficiencyMap(Long id) {
        this.id = id;
    }

    public EmployeeProficiencyMap(Long id, Long empTypId, String type, Long skillId, Long primary, Long secondary, boolean activeFlg, boolean deleteFlg) {
        this.id = id;
        this.empTypId = empTypId;
        this.type = type;
        this.skillId = skillId;
        this.primarySkill = primary;
        this.secondarySkill = secondary;
        this.activeFlg = activeFlg;
        this.deleteFlg = deleteFlg;
    }

    @Id
    @SequenceGenerator(name = "employeeProficiencyMapSeq", sequenceName = "EMPLOYEE_PROFICIENCY_MAP_ID_SE", allocationSize = 1)
    @GeneratedValue(generator = "employeeProficiencyMapSeq", strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic(optional = false)
    @Column(name = "emp_typ_id")
    public Long getEmpTypId() {
        return empTypId;
    }

    public void setEmpTypId(Long empTypId) {
        this.empTypId = empTypId;
    }

    @Basic(optional = false)
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic(optional = false)
    @Column(name = "skill_id")
    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }

    @Basic(optional = false)
    @Column(name = "primary_skill")
    public Long getPrimarySkill() {
        return primarySkill;
    }

    public void setPrimarySkill(Long primary) {
        this.primarySkill = primary;
    }

    @Basic(optional = false)
    @Column(name = "secondary_skill")
    public Long getSecondarySkill() {
        return secondarySkill;
    }

    public void setSecondarySkill(Long secondary) {
        this.secondarySkill = secondary;
    }

    @Basic(optional = false)
    @Column(name = "active_flg")
    public boolean getActiveFlg() {
        return activeFlg;
    }

    public void setActiveFlg(boolean activeFlg) {
        this.activeFlg = activeFlg;
    }

    @Basic(optional = false)
    @Column(name = "delete_flg")
    public boolean getDeleteFlg() {
        return deleteFlg;
    }

    public void setDeleteFlg(boolean deleteFlg) {
        this.deleteFlg = deleteFlg;
    }

    @JoinColumn(name = "emp_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    public EmployeeMst getEmpId() {
        return empId;
    }

    public void setEmpId(EmployeeMst empId) {
        this.empId = empId;
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
        if (!(object instanceof EmployeeProficiencyMap)) {
            return false;
        }
        EmployeeProficiencyMap other = (EmployeeProficiencyMap) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "com.rank.ccms.entities.EmployeeProficiencyMap[ id=" + id + " ]";
    }

}
