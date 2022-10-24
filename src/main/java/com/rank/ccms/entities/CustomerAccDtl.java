package com.rank.ccms.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "customer_acc_dtl")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CustomerAccDtl.findAll", query = "SELECT c FROM CustomerAccDtl c")})
public class CustomerAccDtl implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @SequenceGenerator(name = "customerAccDtlIdSeq", sequenceName = "customer_acc_dtl_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "customerAccDtlIdSeq", strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Long id;
    @Size(max = 50)
    @Column(name = "balance_amt")
    private String balanceAmt;
    @Column(name = "effective_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date effectiveDate;
    @Size(max = 50)
    @Column(name = "acc_no")
    private String accNo;
    @Size(max = 50)
    @Column(name = "trx_type")
    private String trxType;
    @JoinColumn(name = "customer_dtl_id", referencedColumnName = "id")
    @ManyToOne
    private CustomerDtl customerDtlId;

    public CustomerAccDtl() {
    }

    public CustomerAccDtl(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBalanceAmt() {
        return balanceAmt;
    }

    public void setBalanceAmt(String balanceAmt) {
        this.balanceAmt = balanceAmt;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getAccNo() {
        return accNo;
    }

    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

    public String getTrxType() {
        return trxType;
    }

    public void setTrxType(String trxType) {
        this.trxType = trxType;
    }

    public CustomerDtl getCustomerDtlId() {
        return customerDtlId;
    }

    public void setCustomerDtlId(CustomerDtl customerDtlId) {
        this.customerDtlId = customerDtlId;
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
        if (!(object instanceof CustomerAccDtl)) {
            return false;
        }
        CustomerAccDtl other = (CustomerAccDtl) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.rank.ccms.entities.CustomerAccDtl[ id=" + id + " ]";
    }

}
