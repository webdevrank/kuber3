/**
 *
 */
package com.rank.ccms.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rank.ccms.rest.common.CCMSCommonExceptionHandlingController;
import com.rank.ccms.rest.event.ResponseEvent;
import com.rank.ccms.rest.exception.CCMSRestException;

import io.swagger.annotations.ApiOperation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

@RestController("testController")
public class TestController extends CCMSCommonExceptionHandlingController {

    @ApiOperation(value = "Testing Page")
    @RequestMapping(value = "/hello/{id}", method = RequestMethod.GET, produces = {"application/json"})
    public ResponseEntity<ResponseEvent> hellow(@PathVariable("id") int id) throws Exception {
        switch (id) {
            case 1:
                CCMSRestException ccmse = new CCMSRestException();
                ccmse.setErrorCode(String.valueOf(id));
                ccmse.setErrorMessage("Goal!!");
                throw ccmse;
            case 2:
                throw new IOException("IOException, id=" + id);
            default:
                return new ResponseEntity<ResponseEvent>(ResponseEvent.response(new ArrayList<>(Arrays.asList("London", "Tokyo", "New York"))), HttpStatus.OK);
        }

    }
}
