package com.rank.ccms.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "bank_mst")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BankMst.findAll", query = "SELECT b FROM BankMst b")})
public class BankMst implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "bankMstIdSeq", sequenceName = "bank_mst_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "bankMstIdSeq", strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Size(max = 50)
    @Column(name = "bank_name")
    private String bankName;
    @Size(max = 150)
    @Column(name = "branch_code")
    private String branchCode;
    @Size(max = 150)
    @Column(name = "address")
    private String address;
    @Size(max = 20)
    @Column(name = "ifsc_code")
    private String ifscCode;
    @OneToMany(mappedBy = "bankMstId")
    private Collection<CustomerDtl> customerDtlCollection;

    public BankMst() {
    }

    public BankMst(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    @XmlTransient
    public Collection<CustomerDtl> getCustomerDtlCollection() {
        return customerDtlCollection;
    }

    public void setCustomerDtlCollection(Collection<CustomerDtl> customerDtlCollection) {
        this.customerDtlCollection = customerDtlCollection;
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
        if (!(object instanceof BankMst)) {
            return false;
        }
        BankMst other = (BankMst) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.rank.ccms.entities.BankMst[ id=" + id + " ]";
    }

}
