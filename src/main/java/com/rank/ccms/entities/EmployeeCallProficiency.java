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
@Table(name = "employee_call_proficiency")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmployeeCallProficiency.findAll", query = "SELECT e FROM EmployeeCallProficiency e")})
public class EmployeeCallProficiency implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String languageP;
    private String languageS;
    private String serviceP;
    private String serviceS;
    private String categoryP;
    private String categoryS;
    private EmployeeMst empId;

    public EmployeeCallProficiency() {
    }

    public EmployeeCallProficiency(Long id) {
        this.id = id;
    }

    @Id
    @SequenceGenerator(name = "employeeCallProficiencySeq", sequenceName = "EMPLOYEE_CALL_PROFICIENCY_ID_S", allocationSize = 1)
    @GeneratedValue(generator = "employeeCallProficiencySeq", strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "language_p")
    public String getLanguageP() {
        return languageP;
    }

    public void setLanguageP(String languageP) {
        this.languageP = languageP;
    }

    @Column(name = "language_s")
    public String getLanguageS() {
        return languageS;
    }

    public void setLanguageS(String languageS) {
        this.languageS = languageS;
    }

    @Column(name = "service_p")
    public String getServiceP() {
        return serviceP;
    }

    public void setServiceP(String serviceP) {
        this.serviceP = serviceP;
    }

    @Column(name = "service_s")
    public String getServiceS() {
        return serviceS;
    }

    public void setServiceS(String serviceS) {
        this.serviceS = serviceS;
    }

    @Column(name = "category_p")
    public String getCategoryP() {
        return categoryP;
    }

    public void setCategoryP(String categoryP) {
        this.categoryP = categoryP;
    }

    @Column(name = "category_s")
    public String getCategoryS() {
        return categoryS;
    }

    public void setCategoryS(String categoryS) {
        this.categoryS = categoryS;
    }

    @JoinColumn(name = "emp_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
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
        if (!(object instanceof EmployeeCallProficiency)) {
            return false;
        }
        EmployeeCallProficiency other = (EmployeeCallProficiency) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "com.rank.ccms.entities.EmployeeCallProficiency[ id=" + id + " ]";
    }

}
