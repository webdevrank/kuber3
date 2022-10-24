package com.rank.ccms.rest.controller;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.rank.ccms.rest.common.CCMSCommonExceptionHandlingController;
import com.rank.ccms.rest.event.ResponseEvent;
import com.rank.ccms.rest.exception.ErrorResponse;
import com.rank.ccms.rest.response.CustomerDto;
import com.rank.ccms.rest.response.FeedbackDto;
import com.rank.ccms.rest.response.FileHandleDto;
import com.rank.ccms.rest.response.IncomingCallResponse;
import com.rank.ccms.rest.service.CCMSRESTControllerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-03-14T07:52:19.544+05:30")

@Api(value = "Kuber Rest Controller", description = "customer REST API")
@RestController("ccmsRestController")
public class CCMSRestController extends CCMSCommonExceptionHandlingController {

    private static final Logger logger = Logger.getLogger(CCMSRestController.class);

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private CCMSRESTControllerService ccmsRESTControllerServiceImpl;

    @RequestMapping(value = "/loginagent", method = RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent> agentLogin(@RequestBody String credential) throws Exception {
        logger.info("############### Inside of agentLogin " + credential + " ##############");
        return new ResponseEntity<ResponseEvent>(ResponseEvent.response(ccmsRESTControllerServiceImpl.getAgentLoginResponse(credential)), HttpStatus.OK);

    }

    @RequestMapping(value = "/getcategorylist", method = RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent> getCategoryList() throws Exception {
        logger.info("############### Inside of getCategoryList ##############");
        return new ResponseEntity<ResponseEvent>(ResponseEvent.response(ccmsRESTControllerServiceImpl.getCategoryList()), HttpStatus.OK);

    }

    @RequestMapping(value = "/getservicelist", method = RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent> getServiceList() throws Exception {
        logger.info("############### Inside of getServiceList ##############");
        return new ResponseEntity<ResponseEvent>(ResponseEvent.response(ccmsRESTControllerServiceImpl.getServiceList()), HttpStatus.OK);

    }

    @RequestMapping(value = "/getlanguagelist", method = RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent> getLanguageList() throws Exception {
        logger.info("############### Inside of getLanguageList ##############");
        return new ResponseEntity<ResponseEvent>(ResponseEvent.response(ccmsRESTControllerServiceImpl.getLanguageList()), HttpStatus.OK);

    }

    @RequestMapping(value = "/getreasons", method = RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent> getReasonList() throws Exception {
        logger.info("############### Inside of getReasonList ##############");
        return new ResponseEntity<ResponseEvent>(ResponseEvent.response(ccmsRESTControllerServiceImpl.getReasonList()), HttpStatus.OK);

    }

    @RequestMapping(value = "/readyagent", method = RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent> agentReady(@RequestBody String credential) throws Exception {
        logger.info("############### Inside of agentReady " + credential + " ##############");
        return new ResponseEntity<ResponseEvent>(ResponseEvent.response(ccmsRESTControllerServiceImpl.getAgentReadyResponse(credential)), HttpStatus.OK);

    }

    @RequestMapping(value = "/selfviewagent", method = RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent> agentSelfView(@RequestBody String credential) throws Exception {
        logger.info("############### Inside of agentSelfView " + credential + " ##############");
        return new ResponseEntity<ResponseEvent>(ResponseEvent.response(ccmsRESTControllerServiceImpl.getAgentSelfView(credential)), HttpStatus.OK);

    }

    @RequestMapping(value = "/getcustomerinfo", method = RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent> getCustomerInfo(@RequestBody String credential) throws Exception {
        logger.info("############### Inside of getCustomerInfo " + credential + " ##############");
        return new ResponseEntity<ResponseEvent>(ResponseEvent.response(ccmsRESTControllerServiceImpl.getCustomerInfo(credential)), HttpStatus.OK);

    }

    @RequestMapping(value = "/receivecallagent", method = RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent> agentReceiveCall(@RequestBody String credential) throws Exception {
        logger.info("############### Inside of agentReceiveCall " + credential + " ##############");
        return new ResponseEntity<ResponseEvent>(ResponseEvent.response(ccmsRESTControllerServiceImpl.agentReceiveCall(credential)), HttpStatus.OK);

    }

    @RequestMapping(value = "/receiveforwardcallagent", method = RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent> agentReceiveForwardCall(@RequestBody String credential) throws Exception {
        logger.info("############### Inside of agentReceiveForwardCall " + credential + " ##############");
        return new ResponseEntity<ResponseEvent>(ResponseEvent.response(ccmsRESTControllerServiceImpl.agentReceiveForwardCall(credential)), HttpStatus.OK);

    }
    
    @RequestMapping(value = "/afterforwardhangup", method = RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent> agentHangupForwardCall(@RequestBody String credential) throws Exception {
        logger.info("############### Inside of agentHangupForwardCall " + credential + " ##############");
        return new ResponseEntity<ResponseEvent>(ResponseEvent.response(ccmsRESTControllerServiceImpl.agentHangupForwardCall(credential)), HttpStatus.OK);

    }

    @RequestMapping(value = "/missedcallagent", method = RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent> agentMissedCall(@RequestBody String credential) throws Exception {
        logger.info("############### Inside of agentMissedCall " + credential + " ##############");
        return new ResponseEntity<ResponseEvent>(ResponseEvent.response(ccmsRESTControllerServiceImpl.agentMissedCall(credential)), HttpStatus.OK);

    }

    @RequestMapping(value = "/missedforwardcallagent", method = RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent> agentMissedForwardCall(@RequestBody String credential) throws Exception {
        logger.info("############### Inside of agentMissedForwardCall " + credential + " ##############");
        return new ResponseEntity<ResponseEvent>(ResponseEvent.response(ccmsRESTControllerServiceImpl.agentMissedForwardCall(credential)), HttpStatus.OK);

    }

    @RequestMapping(value = "/pickupcallagent", method = RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent> agentPickUpCall(@RequestBody String credential) throws Exception {
        logger.info("############### Inside of agentPickUpCall " + credential + " ##############");
        return new ResponseEntity<ResponseEvent>(ResponseEvent.response(ccmsRESTControllerServiceImpl.agentPickUpCall(credential)), HttpStatus.OK);

    }

    @RequestMapping(value = "/pickupforwardcallagent", method = RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent> agentPickUpForwardCall(@RequestBody String credential) throws Exception {
        logger.info("############### Inside of agentPickUpForwardCall " + credential + " ##############");
        return new ResponseEntity<ResponseEvent>(ResponseEvent.response(ccmsRESTControllerServiceImpl.agentPickUpForwardCall(credential)), HttpStatus.OK);

    }

    @RequestMapping(value = "/holdlistagent", method = RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent> agentHoldList(@RequestBody String credential) throws Exception {
        logger.info("############### Inside of agentHoldList " + credential + " ##############");
        return new ResponseEntity<ResponseEvent>(ResponseEvent.response(ccmsRESTControllerServiceImpl.agentHoldList(credential)), HttpStatus.OK);

    }

    @RequestMapping(value = "/holdagent", method = RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent> agentHold(@RequestBody String credential) throws Exception {
        logger.info("############### Inside of agentHold " + credential + " ##############");
        return new ResponseEntity<ResponseEvent>(ResponseEvent.response(ccmsRESTControllerServiceImpl.agentHold(credential)), HttpStatus.OK);

    }

    @RequestMapping(value = "/unholdagent", method = RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent> agentUnHold(@RequestBody String credential) throws Exception {
        logger.info("############### Inside of agentUnHold " + credential + " ##############");
        return new ResponseEntity<ResponseEvent>(ResponseEvent.response(ccmsRESTControllerServiceImpl.agentUnHold(credential)), HttpStatus.OK);

    }

    @RequestMapping(value = "/notreadyagent", method = RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent> agentNotReady(@RequestBody String credential) throws Exception {
        logger.info("############### Inside of agentNotReady " + credential + " ##############");
        return new ResponseEntity<ResponseEvent>(ResponseEvent.response(ccmsRESTControllerServiceImpl.agentNotReady(credential)), HttpStatus.OK);

    }

    @RequestMapping(value = "/hangupagent", method = RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent> agentHangup(@RequestBody String callInfo) throws Exception {
        logger.info("############### Inside of agentHangup " + callInfo + " ##############");
        return new ResponseEntity<ResponseEvent>(ResponseEvent.response(ccmsRESTControllerServiceImpl.getAgentCallEndResponse(callInfo)), HttpStatus.OK);

    }
    
    @RequestMapping(value = "/specialistlist", method = RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent> listspecialist() throws Exception {
        logger.info("############### Inside of specialistlist ##############");
        return new ResponseEntity<ResponseEvent>(ResponseEvent.response(ccmsRESTControllerServiceImpl.getMultiwaySpecialistList()), HttpStatus.OK);

    }
    
     @RequestMapping(value = "/forwardagentlist", method = RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent> listAgent() throws Exception {
        logger.info("############### Inside of agentlist  ##############");
        return new ResponseEntity<ResponseEvent>(ResponseEvent.response(ccmsRESTControllerServiceImpl.getForwardAgentList()), HttpStatus.OK);

    }

    @RequestMapping(value = "/multiwayagent", method = RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent> agentMultiway(@RequestBody String callInfo) throws Exception {
        logger.info("############### Inside of agentMultiway " + callInfo + " ##############");
        return new ResponseEntity<ResponseEvent>(ResponseEvent.response(ccmsRESTControllerServiceImpl.getMultiwaySpecialistCallResponse(callInfo)), HttpStatus.OK);

    }

    @RequestMapping(value = "/forwardagent", method = RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent> getForwardResponse(@RequestBody String callInfo) throws Exception {
        logger.info("############### Inside of getForwardResponse " + callInfo + " ##############");
        return new ResponseEntity<ResponseEvent>(ResponseEvent.response(ccmsRESTControllerServiceImpl.getForwardResponse(callInfo)), HttpStatus.OK);

    }

    @RequestMapping(value = "/logoutcustomer", method = RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent> customerLogout(@RequestBody String callInfo) throws Exception {
        logger.info("############### Inside of customerLogout " + callInfo + " ##############");
        return new ResponseEntity<ResponseEvent>(ResponseEvent.response(ccmsRESTControllerServiceImpl.getCustomerLogoutResponse(callInfo)), HttpStatus.OK);

    }

    @ApiOperation(value = "Find Customer by Loginid and Password", nickname = "customerLogin", notes = "Returns Customer Information", response = ResponseEvent.class, tags = {"Customer Login",})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Login success", response = IncomingCallResponse.class),
        @ApiResponse(code = 201, message = "Invalid Credentials", response = ErrorResponse.class),
        @ApiResponse(code = 500, message = "Oops! Something went wrong. Please, contact your administrator.", response = ErrorResponse.class)})
    @RequestMapping(value = "/logincustomer", method = RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent> customerLogin(@ApiParam(value = "Information of customer to return on callin with \n {\"loginId\": \"cust1\", \"password\": \"123\"}\n as credential", required = true) @RequestBody String credential) throws Exception {
        logger.info("############### Inside of customerLogin " + credential + " ##############");
        return new ResponseEntity<ResponseEvent>(ResponseEvent.response(ccmsRESTControllerServiceImpl.getCustomerLoginResponse(request, credential)), HttpStatus.OK);

    }

    @ApiOperation(value = "Find available agent", nickname = "customerCallToAvailableAgent", notes = "Returns Available agent information", response = ResponseEvent.class, tags = {"Customer call to available agent",})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Call to availbable agent success", response = IncomingCallResponse.class),
        @ApiResponse(code = 201, message = "Invalid Credentials", response = ErrorResponse.class),
        @ApiResponse(code = 500, message = "Oops! Something went wrong. Please, contact your administrator.", response = ErrorResponse.class)})
    @RequestMapping(value = "/calltoavailabeagent", method = RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent> customerCallToAvailableAgent(@ApiParam(value = "Information of available agent to return on calling with \n {\"loginId\": \"cust1\", \"category\" : \"Urgent\", \"language\" : \"English\", \"service\":\"CASA Account\"}\n as credential", required = true) @RequestBody String credential) throws Exception {
        logger.info("############### Inside of customerCallToAvailableAgent " + credential + " ##############");
        return new ResponseEntity<ResponseEvent>(ResponseEvent.response(ccmsRESTControllerServiceImpl.getCustomerToAvailabeAgentCallResponse(credential)), HttpStatus.OK);

    }

    @ApiOperation(value = "Find available RM", nickname = "customerCallToAvailableRM", notes = "Returns Available RM information", response = ResponseEvent.class, tags = {"Customer call to available RM",})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Call to available RM success", response = IncomingCallResponse.class),
        @ApiResponse(code = 201, message = "Invalid Credentials", response = ErrorResponse.class),
        @ApiResponse(code = 500, message = "Oops! Something went wrong. Please, contact your administrator.", response = ErrorResponse.class)})
    @RequestMapping(value = "/calltoavailableRM", method = RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent> customerCallToAvailableRM(@ApiParam(value = "Information of available RM to return on calling with \n {\"loginId\": \"cust1\", \"category\" : \"Urgent\", \"language\" : \"English\", \"service\":\"CASA Account\"}\n as credential", required = true) @RequestBody String credential) throws Exception {
        logger.info("############### Inside of customerCallToAvailableRM " + credential + " ##############");
        return new ResponseEntity<ResponseEvent>(ResponseEvent.response(ccmsRESTControllerServiceImpl.getCustomerToAvailableRMCallResponse(credential)), HttpStatus.OK);

    }

    @ApiOperation(value = "Find available SRM", nickname = "customerCallToAvailableSRM", notes = "Returns Available SRM information", response = ResponseEvent.class, tags = {"Customer call to available SRM",})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Call to available SRM success", response = IncomingCallResponse.class),
        @ApiResponse(code = 201, message = "Invalid Credentials", response = ErrorResponse.class),
        @ApiResponse(code = 500, message = "Oops! Something went wrong. Please, contact your administrator.", response = ErrorResponse.class)})
    @RequestMapping(value = "/calltoavailableSRM", method = RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent> customerCallToAvailableSRM(@ApiParam(value = "Information of available SRM to return on calling with \n {\"loginId\": \"cust1\", \"category\" : \"Urgent\", \"language\" : \"English\", \"service\":\"CASA Account\"}\n as credential", required = true) @RequestBody String credential) throws Exception {
        logger.info("############### Inside of customerCallToAvailableSRM " + credential + " ##############");
        return new ResponseEntity<ResponseEvent>(ResponseEvent.response(ccmsRESTControllerServiceImpl.getCustomerToAvailableSRMCallResponse(credential)), HttpStatus.OK);

    }

    @ApiOperation(value = "Find available BM", nickname = "customerCallToAvailableBM", notes = "Returns Available BM information", response = ResponseEvent.class, tags = {"Customer call to available BM",})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Call to availbable BM success", response = IncomingCallResponse.class),
        @ApiResponse(code = 201, message = "Invalid Credentials", response = ErrorResponse.class),
        @ApiResponse(code = 500, message = "Oops! Something went wrong. Please, contact your administrator.", response = ErrorResponse.class)})
    @RequestMapping(value = "/calltoavailableBM", method = RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent> customerCallToAvailableBM(@ApiParam(value = "Information of available BM to return on calling with \n {\"loginId\": \"cust1\", \"category\" : \"Urgent\", \"language\" : \"English\", \"service\":\"CASA Account\"}\n as credential", required = true) @RequestBody String credential) throws Exception {
        logger.info("############### Inside of customerCallToAvailableBM " + credential + " ##############");
        return new ResponseEntity<ResponseEvent>(ResponseEvent.response(ccmsRESTControllerServiceImpl.getCustomerToAvailableBMCallResponse(credential)), HttpStatus.OK);

    }

    @RequestMapping(value = "/registercustomer", method = RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent> customerRegisterResponse(@RequestBody CustomerDto customerDto) throws Exception {
        logger.info("############### Inside of customerRegisterResponse " + customerDto.toString() + " ##############");
        return new ResponseEntity<ResponseEvent>(ResponseEvent.response(ccmsRESTControllerServiceImpl.getCustomerRegisterResponse(customerDto)), HttpStatus.OK);

    }

    @RequestMapping(value = "/hangupcustomer", method = RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent> customerHangup(@RequestBody String callInfo) throws Exception {
        logger.info("############### Inside of customerHangup " + callInfo + " ##############");
        return new ResponseEntity<ResponseEvent>(ResponseEvent.response(ccmsRESTControllerServiceImpl.getCustomerCallEndResponse(callInfo)), HttpStatus.OK);

    }

    @RequestMapping(value = "/logoutagent", method = RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent> agentLogout(@RequestBody String callInfo) throws Exception {
        logger.info("############### Inside of agentLogout " + callInfo + " ##############");
        return new ResponseEntity<ResponseEvent>(ResponseEvent.response(ccmsRESTControllerServiceImpl.getAgentLogoutResponse(callInfo)), HttpStatus.OK);

    }

    @RequestMapping(value = "/uploadfile", method = RequestMethod.POST, consumes = {"multipart/form-data", "multipart/mixed"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent> fileUploadResponse(@ModelAttribute FileHandleDto model) throws Exception {
        logger.info("############### Inside of fileUploadResponse " + model.toString() + " ##############");
        return new ResponseEntity<ResponseEvent>(ResponseEvent.response(ccmsRESTControllerServiceImpl.getFileUploadResponse(model, request)), HttpStatus.OK);

    }

    @RequestMapping(value = "/pickedcallbycustomer", method = RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent> customercallPicked(@RequestBody CustomerDto customerDto) throws Exception {
        logger.info("############### Inside of customercallPicked " + customerDto.toString() + " ##############");
        return new ResponseEntity<ResponseEvent>(ResponseEvent.response(ccmsRESTControllerServiceImpl.getCustomerCallPickedResponse(customerDto)), HttpStatus.OK);

    }

    @RequestMapping(value = "/missedcallbycustomer", method = RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent> customerCallMissed(@RequestBody String callInfo) throws Exception {
        logger.info("############### Inside of customerCallMissed " + callInfo + " ##############");
        return new ResponseEntity<ResponseEvent>(ResponseEvent.response(ccmsRESTControllerServiceImpl.getCustomerCallMissedResponse(callInfo)), HttpStatus.OK);

    }

    @RequestMapping(value = "/getincallemployees", method = RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent> getIncallEmployeeList(@RequestBody String callInfo) throws Exception {
        logger.info("############### Inside of getIncallEmployeeList " + callInfo + " ##############");
        return new ResponseEntity<ResponseEvent>(ResponseEvent.response(ccmsRESTControllerServiceImpl.getInCallEmployeeListToCustomerForFileUploadResponse(callInfo)), HttpStatus.OK);

    }

    @RequestMapping(value = "/makecustomerschedulecall", method = RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent> customerScheduleCallMake(@RequestBody CustomerDto customerDto) throws Exception {
        logger.info("############### Inside of customerScheduleCallMake " + customerDto.toString() + " ##############");
        return new ResponseEntity<ResponseEvent>(ResponseEvent.response(ccmsRESTControllerServiceImpl.getScheduleCallResponse(customerDto)), HttpStatus.OK);

    }

    @RequestMapping(value = "/checkcustomerschedulecall", method = RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent> customerScheduleCallCheck(@RequestBody String callInfo) throws Exception {
        logger.info("############### Inside of customerScheduleCallCheck " + callInfo + " ##############");
        return new ResponseEntity<ResponseEvent>(ResponseEvent.response(ccmsRESTControllerServiceImpl.getScheduleCallCheckResponse(callInfo)), HttpStatus.OK);

    }

    @RequestMapping(value = "/getfeedback", method = RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent> getFeedBack() throws Exception {
        logger.info("############### Inside of getFeedBack ##############");
        return new ResponseEntity<ResponseEvent>(ResponseEvent.response(ccmsRESTControllerServiceImpl.getFeedbackResponse()), HttpStatus.OK);

    }

    @RequestMapping(value = "/savecustomerfeedback", method = RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent> saveCustomerFeedBack(@RequestBody FeedbackDto feedbackDto) throws Exception {
        logger.info("############### Inside of saveCustomerFeedBack " + feedbackDto.toString() + " ##############");
        return new ResponseEntity<ResponseEvent>(ResponseEvent.response(ccmsRESTControllerServiceImpl.getFeedbackSaveResponse(feedbackDto)), HttpStatus.OK);

    }

    @RequestMapping(value = "/getcustomeraccountdetails", method = RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent> getCustomerAccountInformationDetails(@RequestBody String callInfo) throws Exception {
        logger.info("############### Inside of getCustomerAccountInformationDetails " + callInfo + " ##############");
        return new ResponseEntity<ResponseEvent>(ResponseEvent.response(ccmsRESTControllerServiceImpl.getCustomerAccountDetailsbyPhoneNoResponse(callInfo)), HttpStatus.OK);

    }

    @RequestMapping(value = "/uploadcustomernationalid", method = RequestMethod.POST, consumes = {"multipart/form-data", "multipart/mixed"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent> customerNationalIdUpload(@ModelAttribute FileHandleDto fileHandleDto) throws Exception {
        logger.info("############### Inside of customerNationalIdUpload " + fileHandleDto.toString() + " ##############");
        return new ResponseEntity<ResponseEvent>(ResponseEvent.response(ccmsRESTControllerServiceImpl.getCustomerNationalIdCardUploadResponse(fileHandleDto, request)), HttpStatus.OK);

    }

    @RequestMapping(value = "/uploadcustomeraddressproof", method = RequestMethod.POST, consumes = {"multipart/form-data", "multipart/mixed"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent> customerAddressProofUpload(@ModelAttribute FileHandleDto fileHandleDto) throws Exception {
        logger.info("############### Inside of customerAddressProofUpload " + fileHandleDto.toString() + " ##############");
        return new ResponseEntity<ResponseEvent>(ResponseEvent.response(ccmsRESTControllerServiceImpl.getCustomerAddressProofUploadResponse(fileHandleDto, request)), HttpStatus.OK);

    }

  
    @RequestMapping(value = "/sendsnapimagetoagent", method = RequestMethod.POST, consumes = {"multipart/form-data", "multipart/mixed"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent> customerSnapShotUploadToAgent(@ModelAttribute FileHandleDto fileHandleDto) throws Exception {
        logger.info("############### Inside of customerSnapShotUploadToAgent " + fileHandleDto.toString() + " ##############");
        return new ResponseEntity<ResponseEvent>(ResponseEvent.response(ccmsRESTControllerServiceImpl.getCustomerProfileImageUploadResponse(fileHandleDto, request)), HttpStatus.OK);

    }

    @RequestMapping(value = "/getservicedowntime", method = RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent> serviceDownTimeResponse() throws Exception {
        logger.info("############### Inside of serviceDownTimeResponse ##############");
        return new ResponseEntity<ResponseEvent>(ResponseEvent.response(ccmsRESTControllerServiceImpl.getServerDownTimeResponse()), HttpStatus.OK);

    }

    @RequestMapping(value = "/validateschedulecallcustomer", method = RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent> validateScheduleCallResponse(@RequestBody String callInfo) throws Exception {
        logger.info("############### Inside of validateScheduleCallResponse " + callInfo + " ##############");
        return new ResponseEntity<ResponseEvent>(ResponseEvent.response(ccmsRESTControllerServiceImpl.getScheduleCallVerificationResponse(callInfo)), HttpStatus.OK);

    }

    @RequestMapping(value = "/rejectschedulecallcustomer", method = RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent> rejectScheduleCallResponse(@RequestBody String callInfo) throws Exception {
        logger.info("############### Inside of rejectScheduleCallResponse " + callInfo + " ##############");
        return new ResponseEntity<ResponseEvent>(ResponseEvent.response(ccmsRESTControllerServiceImpl.getScheduleCallRejectResponse(callInfo)), HttpStatus.OK);

    }

    @RequestMapping(value = "/getScheduledCallDtlsByRMId", method = RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent> getScheduledCallDtlsByRMId(@RequestBody String callInfo) throws Exception {
        logger.info("############### Inside of getScheduledCallDtlsByRMId " + callInfo + " ##############");
        return new ResponseEntity<ResponseEvent>(ResponseEvent.response(ccmsRESTControllerServiceImpl.getScheduledCallDtlsByRMId(callInfo)), HttpStatus.OK);

    }

    @RequestMapping(value = "/checkScheduleCallCustomerInitiate", method = RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent> checkScheduleCallCustomerInitiate(@RequestBody String callInfo) throws Exception {
        logger.info("############### Inside of checkScheduleCallCustomerInitiate " + callInfo + " ##############");
        return new ResponseEntity<ResponseEvent>(ResponseEvent.response(ccmsRESTControllerServiceImpl.checkScheduleCallCustomerInitiate(callInfo)), HttpStatus.OK);

    }

    @RequestMapping(value = "/saveScheduleDetails", method = RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent> saveScheduleDetails(@RequestBody String callInfo) throws Exception {
        logger.info("############### Inside of saveScheduleDetails " + callInfo + " ##############");
        return new ResponseEntity<ResponseEvent>(ResponseEvent.response(ccmsRESTControllerServiceImpl.saveScheduleDetails(callInfo)), HttpStatus.OK);

    }

    @RequestMapping(value = "/saveScheduleDetailsCustomer", method = RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent> saveScheduleDetailsCustomer(@RequestBody String callInfo) throws Exception {
        logger.info("############### Inside of saveScheduleDetailsCustomer " + callInfo + " ##############");
        return new ResponseEntity<ResponseEvent>(ResponseEvent.response(ccmsRESTControllerServiceImpl.saveScheduleDetailsCustomer(callInfo)), HttpStatus.OK);

    }

    @RequestMapping(value = "/initiateSchrduleCallByCustomer", method = RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent> initiateSchrduleCallByCustomer(@RequestBody String callInfo) throws Exception {
        logger.info("############### Inside of initiateSchrduleCallByCustomer " + callInfo + " ##############");
        return new ResponseEntity<ResponseEvent>(ResponseEvent.response(ccmsRESTControllerServiceImpl.initiateSchrduleCallByCustomer(callInfo)), HttpStatus.OK);

    }

    @RequestMapping(value = "/getScheduledCallDtlsByCustomerId", method = RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent> getScheduledCallDtlsByCustomerId(@RequestBody String callInfo) throws Exception {
        logger.info("############### Inside of getScheduledCallDtlsByCustomerId " + callInfo + " ##############");
        return new ResponseEntity<ResponseEvent>(ResponseEvent.response(ccmsRESTControllerServiceImpl.getScheduledCallDtlsByCustomerId(callInfo)), HttpStatus.OK);

    }

    @RequestMapping(value = "/getRMScheduledCallDtlsByCustomerId", method = RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent> getRMScheduledCallDtlsByCustomerId(@RequestBody String callInfo) throws Exception {
        logger.info("############### Inside of getRMScheduledCallDtlsByCustomerId" + callInfo + " ##############");
        return new ResponseEntity<ResponseEvent>(ResponseEvent.response(ccmsRESTControllerServiceImpl.getRMScheduledCallDtlsByCustomerId(callInfo)), HttpStatus.OK);

    }

    @RequestMapping(value = "/cancelScheduleCallByCustomer", method = RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent> cancelScheduleCallByCustomer(@RequestBody String callInfo) throws Exception {
        logger.info("############### Inside of cancelScheduleCallByCustomer" + callInfo + " ##############");
        return new ResponseEntity<ResponseEvent>(ResponseEvent.response(ccmsRESTControllerServiceImpl.cancelScheduleCallByCustomer(callInfo)), HttpStatus.OK);

    }

    @RequestMapping(value = "/rmInviteGuest", method = RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent> rmInviteGuest(@RequestBody String callInfo) throws Exception {
        logger.info("############### Inside of rmInviteGuest" + callInfo + " ##############");
        return new ResponseEntity<ResponseEvent>(ResponseEvent.response(ccmsRESTControllerServiceImpl.rmInviteGuest(callInfo)), HttpStatus.OK);

    }

    @RequestMapping(value = "/getMappedCustomerList", method = RequestMethod.POST, consumes = {"application/json", "application/xml"}, produces = {"application/json", "application/xml"})
    public ResponseEntity<ResponseEvent> getMappedCustomerList(@RequestBody String credential) throws Exception {
        logger.info("############### Inside of getMappedCustomerList" + credential + " ##############");
        return new ResponseEntity<ResponseEvent>(ResponseEvent.response(ccmsRESTControllerServiceImpl.getMappedCustomerList(credential)), HttpStatus.OK);

    }
}
