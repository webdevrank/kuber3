package com.rank.ccms.rest.response;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class FeedbackResponse implements Serializable {

    @JsonIgnore
    private static final long serialVersionUID = 195479175696098636L;
    private boolean resStatus;
    private String resMessage;

    public boolean isResStatus() {
        return resStatus;
    }

    public void setResStatus(boolean resStatus) {
        this.resStatus = resStatus;
    }

    public String getResMessage() {
        return resMessage;
    }

    public void setResMessage(String resMessage) {
        this.resMessage = resMessage;
    }

}
