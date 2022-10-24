package com.rank.ccms.rest.event;

import java.io.Serializable;

import com.rank.ccms.rest.exception.ErrorResponse;

public class ResponseEvent<T> implements Serializable {

    private static final long serialVersionUID = 7528116395502899296L;

    private T payload;

    private ErrorResponse error;

    private Boolean status;

    public ResponseEvent(T payload) {
        this.payload = payload;
        this.setStatus(true);
    }

    public ResponseEvent(ErrorResponse error) {
        this.error = error;
        this.setStatus(false);
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    public ErrorResponse getError() {
        return error;
    }

    public void setError(ErrorResponse error) {
        this.error = error;
    }

    public static <P> ResponseEvent<P> response(P payload) {
        return new ResponseEvent<>(payload);
    }

    public static <P> ResponseEvent<P> error(ErrorResponse error) {
        return new ResponseEvent<>(error);
    }

    /**
     * @return the status
     */
    public Boolean getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    private void setStatus(Boolean status) {
        this.status = status;
    }

}
