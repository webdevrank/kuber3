package com.rank.ccms.rest.exception;

public class CCMSRestException extends Exception {

    private static final long serialVersionUID = 4689640754895923304L;
    private String errorMessage;
    private String errorCode;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

}
