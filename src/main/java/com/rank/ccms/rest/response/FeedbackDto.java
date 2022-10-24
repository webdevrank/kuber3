package com.rank.ccms.rest.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class FeedbackDto implements Serializable {

    @JsonIgnore
    private static final long serialVersionUID = -3232911949058696718L;

    String question1Val;
    String question2Val;
    String question3Val;
    String comment;
    Long callId;
    String cust_id;
    String qualityQuery;
    String abilityQuery;
    String recommendQuery;

    public String getQuestion1Val() {
        return question1Val;
    }

    public void setQuestion1Val(String question1Val) {
        this.question1Val = question1Val;
    }

    public String getQuestion2Val() {
        return question2Val;
    }

    public void setQuestion2Val(String question2Val) {
        this.question2Val = question2Val;
    }

    public String getQuestion3Val() {
        return question3Val;
    }

    public void setQuestion3Val(String question3Val) {
        this.question3Val = question3Val;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getCallId() {
        return callId;
    }

    public void setCallId(Long callId) {
        this.callId = callId;
    }

    public String getCust_id() {
        return cust_id;
    }

    public void setCust_id(String cust_id) {
        this.cust_id = cust_id;
    }

    public String getQualityQuery() {
        return qualityQuery;
    }

    public void setQualityQuery(String qualityQuery) {
        this.qualityQuery = qualityQuery;
    }

    public String getAbilityQuery() {
        return abilityQuery;
    }

    public void setAbilityQuery(String abilityQuery) {
        this.abilityQuery = abilityQuery;
    }

    public String getRecommendQuery() {
        return recommendQuery;
    }

    public void setRecommendQuery(String recommendQuery) {
        this.recommendQuery = recommendQuery;
    }

    /* (non-Javadoc)
	 * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "FeedbackDto [question1Val=" + question1Val + ", question2Val=" + question2Val + ", question3Val="
                + question3Val + ", comment=" + comment + ", callId=" + callId + ", cust_id=" + cust_id
                + ", qualityQuery=" + qualityQuery + ", abilityQuery=" + abilityQuery + ", recommendQuery="
                + recommendQuery + "]";
    }

}
