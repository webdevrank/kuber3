package com.rank.ccms.entities;

import java.io.Serializable;
import java.sql.Timestamp;
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
@Table(name = "feedback_dtl")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FeedbackDtl.findAll", query = "SELECT f FROM FeedbackDtl f")})
public class FeedbackDtl implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long feedbackParamId;
    private String starRating;
    private String comments;
    private boolean activeFlg;
    private boolean deleteFlg;
    private CustomerMst customerId;
    private CallMst callMstId;
    private Timestamp feedbackDate;
    private String feedbackQuery;

    public FeedbackDtl() {
    }

    public FeedbackDtl(Long id) {
        this.id = id;
    }

    public FeedbackDtl(Long id, Long feedbackParamId, String starRating, boolean activeFlg, boolean deleteFlg) {
        this.id = id;
        this.feedbackParamId = feedbackParamId;
        this.starRating = starRating;
        this.activeFlg = activeFlg;
        this.deleteFlg = deleteFlg;
    }

    @Id
    @SequenceGenerator(name = "feedbackDtlSeq", sequenceName = "FEEDBACK_DTL_ID_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "feedbackDtlSeq", strategy = GenerationType.SEQUENCE)
    @Basic(optional = false)
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic(optional = false)
    @Column(name = "feedback_param_id")
    public Long getFeedbackParamId() {
        return feedbackParamId;
    }

    public void setFeedbackParamId(Long feedbackParamId) {
        this.feedbackParamId = feedbackParamId;
    }

    @Column(name = "star_rating")
    public String getStarRating() {
        return starRating;
    }

    public void setStarRating(String starRating) {
        this.starRating = starRating;
    }

    @Column(name = "comments")
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
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

    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    public CustomerMst getCustomerId() {
        return customerId;
    }

    public void setCustomerId(CustomerMst customerId) {
        this.customerId = customerId;
    }

    @JoinColumn(name = "call_mst_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    public CallMst getCallMstId() {
        return callMstId;
    }

    public void setCallMstId(CallMst callMstId) {
        this.callMstId = callMstId;
    }

    @Column(name = "FEEDBACK_DATE")
    public Timestamp getFeedbackDate() {
        return feedbackDate;
    }

    public void setFeedbackDate(Timestamp feedbackDate) {
        this.feedbackDate = feedbackDate;
    }

    @Column(name = "FEEDBACK_QUERY")
    public String getFeedbackQuery() {
        return feedbackQuery;
    }

    public void setFeedbackQuery(String feedbackQuery) {
        this.feedbackQuery = feedbackQuery;
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
        if (!(object instanceof FeedbackDtl)) {
            return false;
        }
        FeedbackDtl other = (FeedbackDtl) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "com.rank.ccms.entities.FeedbackDtl[ id=" + id + " ]";
    }

}
