package com.rank.ccms.rest.common;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.rank.ccms.rest.event.ResponseEvent;
import com.rank.ccms.rest.exception.CCMSRestException;
import com.rank.ccms.rest.exception.ErrorResponse;

@RestController("ccmsCommonExceptionHandlingController")
public class CCMSCommonExceptionHandlingController {

    private static final Logger logger = Logger.getLogger(CCMSCommonExceptionHandlingController.class);

    @SuppressWarnings("rawtypes")
    @ExceptionHandler(CCMSRestException.class)
    public ResponseEntity<ResponseEvent> exceptionHandler(Exception ex) {
        logger.info(" Inside CCMSBaseController" + ex.getClass());
        CCMSRestException ccmsException = (CCMSRestException) ex;
        ErrorResponse error = new ErrorResponse();
        error.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
        error.setErrorMessage(ccmsException.getErrorMessage());
        logger.info(ccmsException.getErrorMessage());
        return new ResponseEntity<ResponseEvent>(ResponseEvent.error(error), HttpStatus.OK);
    }

}
