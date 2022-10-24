package com.rank.ccms.service;

import com.rank.ccms.dto.ConferenceAgentsDto;
import com.rank.ccms.entities.CallDtl;
import com.rank.ccms.entities.CallMst;
import com.rank.ccms.entities.EmployeeMst;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public interface CallDtlService extends Serializable {

    CallDtl saveCallDtl(CallDtl callDtl);

    List<CallDtl> findAllCallDtls();

    List<CallDtl> findAllNonDeletedCallDtls();

    CallDtl findNonDeletedCallDtlId(Long id);

    List<CallDtl> findNonDeletedCallDtlByCallId(CallMst callId);

    CallDtl findNonDeletedCallDtlByCallIdAndActivity(CallMst callId, String Activity);

    CallDtl findNonDeletedCallDtlByCallIdAndActivity(CallMst callId, List<String> activityList);

    CallDtl findNonDeletedCallDtlByEmployee(Long id);

    List<CallDtl> findCallDtlByEmployeeWithinTimeRange(Long id, Timestamp stTime, Timestamp endTime);

    List<CallDtl> findCallDtlByCallMasterIdAndCallTypeInfo(Long callMasterId, String callTypeInfo);

    List<CallDtl> findEndedCallDtlBetweenDate(Timestamp fromDateTime, Timestamp toDateTime, Long empPkId);

    List<CallDtl> AgentEndedCallDtlBetweenDate(Timestamp fromDateTime, Timestamp toDateTime);

    List<CallDtl> findInboundCallDtlBetweenDate(Timestamp fromDateTime, Timestamp toDateTime, Long empPkId);

    List<CallDtl> findInboundCallDtlBetweenDateForService(Timestamp fromDateTime, Timestamp toDateTime, Long empPkId);

    List<CallDtl> AllInboundCallDtlBetweenDate(Timestamp fromDateTime, Timestamp toDateTime);

    List<CallDtl> findOutboundCallDtlBetweenDate(Timestamp fromDateTime, Timestamp toDateTime, Long empPkId);

    List<CallDtl> AllOutboundCallDtlBetweenDate(Timestamp fromDateTime, Timestamp toDateTime);

    List<CallDtl> findCallDtlByCallMasterIdAndAgent(Long callMasterId, Long employeeId);

    List<CallDtl> findForwardedCallDtlBetweenDate(Timestamp fromDateTime, Timestamp toDateTime, Long empPkId);

    List<CallDtl> AllForwardedCallDtlBetweenDate(Timestamp fromDateTime, Timestamp toDateTime);

    CallDtl findCallDtlByCallMasterIdAndAgentNotForwarded(Long callMasterId, Long employeeId);

    CallDtl findCurrentCallAgent(Long callId);

    List<CallDtl> findCallDtlByCallMasterId(Long callMasterId);

    List<CallDtl> findCallDtlByCallMasterIdOtherThanThreeWay(Long callMasterId);

    CallDtl findCallDtlByCallMstIdForSchduledCall(Long callMstId);

    List<CallDtl> findCallDtlByCallMstIdAndTimeRange(Timestamp fromTime, Timestamp toTime, Long callId);

    CallDtl findLastNonEndedCallByEmpId(EmployeeMst employeeMst);

    List<CallDtl> findNonEndedCallDtlByCallMasterId(Long id);

    List<ConferenceAgentsDto> findParticipantCallDtlsByCallMstId(CallMst callMstLocal);

    List<CallDtl> findNonEndedCallDtlByCallMasterIdAndExcludeCurrentEmployee(Long id, Long empId);

    CallDtl findNonEndedCallDtlByCallMasterIdAndEmployeeId(Long callId, Long empId);
}
