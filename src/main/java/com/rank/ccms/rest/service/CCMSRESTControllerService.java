package com.rank.ccms.rest.service;

import com.rank.ccms.dto.SpecialistDto;
import com.rank.ccms.rest.response.AgentLoginResponse;
import com.rank.ccms.rest.response.CategoryDto;
import com.rank.ccms.rest.response.CustomerAccountInformationDetailsDto;
import com.rank.ccms.rest.response.CustomerDto;
import com.rank.ccms.rest.response.FeedbackDto;
import com.rank.ccms.rest.response.FeedbackQuestionsResponse;
import com.rank.ccms.rest.response.FeedbackResponse;
import com.rank.ccms.rest.response.FileHandleDto;
import com.rank.ccms.rest.response.IncomingCallResponse;
import com.rank.ccms.rest.response.LanguageDto;
import com.rank.ccms.rest.response.ParticipantDto;
import com.rank.ccms.rest.response.ReasonDto;
import com.rank.ccms.rest.response.ScheduleDetailsResponse;
import com.rank.ccms.rest.response.ServiceDto;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public interface CCMSRESTControllerService {

    public List<CategoryDto> getCategoryList();

    public List<ServiceDto> getServiceList();

    public List<LanguageDto> getLanguageList();

    public List<ReasonDto> getReasonList();

    public AgentLoginResponse getAgentLoginResponse(String credential) throws Exception;

    public AgentLoginResponse getAgentReadyResponse(String credential) throws Exception;

    public AgentLoginResponse getAgentLogoutResponse(String callInfo) throws Exception;

    public AgentLoginResponse getAgentSelfView(String credential) throws Exception;

    public CustomerDto getCustomerInfo(String credential) throws Exception;

    public IncomingCallResponse agentReceiveCall(String credential) throws Exception;

    public IncomingCallResponse agentReceiveForwardCall(String credential) throws Exception;

    public IncomingCallResponse agentHangupForwardCall(String credential) throws Exception;

    public IncomingCallResponse agentMissedCall(String credential) throws Exception;

    public IncomingCallResponse agentMissedForwardCall(String credential) throws Exception;

    public IncomingCallResponse agentPickUpCall(String credential) throws Exception;

    public IncomingCallResponse agentPickUpForwardCall(String credential) throws Exception;

    public IncomingCallResponse agentHoldList(String credential) throws Exception;

    public IncomingCallResponse agentHold(String credential) throws Exception;

    public IncomingCallResponse agentUnHold(String credential) throws Exception;

    public AgentLoginResponse agentNotReady(String credential) throws Exception;

    public CustomerDto getAgentCallEndResponse(String callInfo) throws Exception;

    public IncomingCallResponse getCustomerLoginResponse(HttpServletRequest request, String credential) throws Exception;

    public IncomingCallResponse getCustomerToAvailabeAgentCallResponse(String credential) throws Exception;

    public IncomingCallResponse getCustomerToAvailableRMCallResponse(String credential) throws Exception;

    public IncomingCallResponse getCustomerToAvailableSRMCallResponse(String credential) throws Exception;

    public IncomingCallResponse getCustomerToAvailableBMCallResponse(String credential) throws Exception;

    public IncomingCallResponse getCustomerRegisterResponse(CustomerDto customerDto) throws Exception;

    public CustomerDto getCustomerCallEndResponse(String callInfo) throws Exception;

    public CustomerDto getCustomerLogoutResponse(String callInfo) throws Exception;

    public AgentLoginResponse getCustomerCallPickedResponse(CustomerDto customerDto) throws Exception;

    public AgentLoginResponse getAgentCallMissedResponse(String callInfo) throws Exception;

    public AgentLoginResponse getCustomerCallMissedResponse(String callInfo) throws Exception;

    public List<ParticipantDto> getInCallEmployeeListToCustomerForFileUploadResponse(String callInfo) throws Exception;

    public List<ParticipantDto> getInCallUserListToAgentforFileUploadResponse(String callInfo) throws Exception;

    public AgentLoginResponse getFileUploadResponse(FileHandleDto model, HttpServletRequest request) throws Exception;

    public List<SpecialistDto> getMultiwaySpecialistList() throws Exception;

    public List<SpecialistDto> getForwardAgentList() throws Exception;

    public AgentLoginResponse getMultiwaySpecialistCallResponse(String callInfo) throws Exception;

    public AgentLoginResponse getScheduleCallResponse(CustomerDto customerDto) throws Exception;

    public AgentLoginResponse getScheduleCallCheckResponse(String callInfo) throws Exception;

    public FeedbackQuestionsResponse getFeedbackResponse() throws Exception;

    public FeedbackResponse getFeedbackSaveResponse(FeedbackDto feedbackDto) throws Exception;

    public CustomerAccountInformationDetailsDto getCustomerAccountDetailsbyPhoneNoResponse(String callInfo) throws Exception;

    public AgentLoginResponse getCustomerProfileImageUploadResponse(FileHandleDto fileHandleDto, HttpServletRequest request) throws Exception;

    public AgentLoginResponse getCustomerNationalIdCardUploadResponse(FileHandleDto fileHandleDto, HttpServletRequest request) throws Exception;

    public AgentLoginResponse getCustomerAddressProofUploadResponse(FileHandleDto fileHandleDto, HttpServletRequest request) throws Exception;

    public AgentLoginResponse getServerDownTimeResponse() throws Exception;

    public IncomingCallResponse getScheduleCallVerificationResponse(String callInfo) throws Exception;

    public AgentLoginResponse getScheduleCallRejectResponse(String callInfo) throws Exception;

    public AgentLoginResponse getForwardResponse(String callInfo) throws Exception;

    public List<ScheduleDetailsResponse> getScheduledCallDtlsByRMId(String callInfo) throws Exception;

    public List<ScheduleDetailsResponse> checkScheduleCallCustomerInitiate(String callInfo) throws Exception;

    public ScheduleDetailsResponse saveScheduleDetails(String callInfo) throws Exception;

    public ScheduleDetailsResponse saveScheduleDetailsCustomer(String callInfo) throws Exception;

    public ScheduleDetailsResponse initiateSchrduleCallByCustomer(String callInfo) throws Exception;

    public List<ScheduleDetailsResponse> getScheduledCallDtlsByCustomerId(String callInfo) throws Exception;

    public List<ScheduleDetailsResponse> getRMScheduledCallDtlsByCustomerId(String callInfo) throws Exception;

    public ScheduleDetailsResponse cancelScheduleCallByCustomer(String callInfo) throws Exception;

    public ScheduleDetailsResponse rmInviteGuest(String callInfo) throws Exception;

    public List<CustomerDto> getMappedCustomerList(String credential) throws Exception;

}
