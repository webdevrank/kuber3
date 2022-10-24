package com.rank.ccms.entities;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "service_mst")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ServiceMst.findAll", query = "SELECT s FROM ServiceMst s")})
public class ServiceMst implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String serviceCd;
    private String serviceName;
    private String serviceDesc;
    private boolean activeFlg;
    private boolean deleteFlg;

    public ServiceMst() {
    }

    public ServiceMst(Long id) {
        this.id = id;
    }

    public ServiceMst(Long id, String serviceCd, String serviceName, boolean activeFlg, boolean deleteFlg) {
        this.id = id;
        this.serviceCd = serviceCd;
        this.serviceName = serviceName;
        this.activeFlg = activeFlg;
        this.deleteFlg = deleteFlg;
    }

    @Id
    @SequenceGenerator(name = "serviceMstSeq", sequenceName = "SERVICE_MST_ID_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "serviceMstSeq", strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic(optional = false)
    @Column(name = "service_cd")
    public String getServiceCd() {
        return serviceCd;
    }

    public void setServiceCd(String serviceCd) {
        this.serviceCd = serviceCd;
    }

    @Basic(optional = false)
    @Column(name = "service_name")
    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    @Column(name = "service_desc")
    public String getServiceDesc() {
        return serviceDesc;
    }

    public void setServiceDesc(String serviceDesc) {
        this.serviceDesc = serviceDesc;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServiceMst)) {
            return false;
        }
        ServiceMst other = (ServiceMst) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "com.rank.ccms.entities.ServiceMst[ id=" + id + " ]";
    }

}
