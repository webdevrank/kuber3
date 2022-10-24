package com.rank.ccms.dao;

import com.rank.ccms.dto.ConferenceAgentsDto;
import com.rank.ccms.entities.CallDtl;
import com.rank.ccms.entities.CallMst;
import com.rank.ccms.entities.EmployeeMst;
import java.sql.Timestamp;
import java.util.List;

public interface CallDtlDao extends GenericDao<CallDtl> {

    public CallDtl findNonDeletedCallDtlById(Long id);

    public List<CallDtl> findNonDeletedCallDtlByCallId(CallMst callId);

    public List<CallDtl> findNonDeletedCallDtlByCallIdAndActivity(CallMst callId, String Activity);

    public List<CallDtl> findNonDeletedCallDtlByCallIdAndActivity(CallMst callId, List<String> activityList);

    public List<CallDtl> findCallDtlByCallMasterId(Long callMasterId);

    public List<CallDtl> findCallDtlByCallMasterIdOtherThanThreeWay(Long callMasterId);

    public List<CallDtl> findCallDtlByCallMasterIdAndAgent(Long callMasterId, Long employeeId);

    public CallDtl findCallDtlById(Long id);

    public List<CallDtl> findAllNonDeletedCallDtls();

    public CallDtl findCallDtlByEmployee(Long id);

    public List<CallDtl> findCallDtlByEmployeeWithinTimeRange(Long id, Timestamp stTime, Timestamp endTime);

    public List<CallDtl> findCallDtlByCallMasterIdAndCallTypeInfo(Long callMasterId, String callTypeInfo);

    public List<CallDtl> findEndedCallDtlBetweenDate(Timestamp fromDateTime, Timestamp toDateTime, Long empPkId);

    public List<CallDtl> AgentEndedCallDtlBetweenDate(Timestamp fromDateTime, Timestamp toDateTime);

    public List<CallDtl> findInboundCallDtlBetweenDate(Timestamp fromDateTime, Timestamp toDateTime, Long empPkId);

    public List<CallDtl> findInboundCallDtlBetweenDateForService(Timestamp fromDateTime, Timestamp toDateTime, Long empPkId);

    public List<CallDtl> AllInboundCallDtlBetweenDate(Timestamp fromDateTime, Timestamp toDateTime);

    public List<CallDtl> findOutboundCallDtlBetweenDate(Timestamp fromDateTime, Timestamp toDateTime, Long empPkId);

    public List<CallDtl> findForwardedCallDtlBetweenDate(Timestamp fromDateTime, Timestamp toDateTime, Long empPkId);

    public List<CallDtl> AllOutboundCallDtlBetweenDate(Timestamp fromDateTime, Timestamp toDateTime);

    public List<CallDtl> AllForwardedCallDtlBetweenDate(Timestamp fromDateTime, Timestamp toDateTime);

    public CallDtl findCallDtlByCallMasterIdAndAgentNotForwarded(Long callMasterId, Long employeeId);

    public CallDtl findCallDtlByCallMstIdForSchduledCall(Long callMstId);

    public List<CallDtl> findCallDtlByCallMstIdAndTimeRange(Timestamp fromTime, Timestamp toTime, Long callId);

    public List<CallDtl> findLastNonEndedCallByEmpId(EmployeeMst employeeMst);

    public List<CallDtl> findNonEndedCallDtlByCallMasterId(long l);

    public CallDtl findCurrentCallAgent(Long callId);

    public List<ConferenceAgentsDto> findParticipantCallDtlsByCallMstId(CallMst callMst);

    public List<CallDtl> findNonEndedCallDtlByCallMasterIdAndExcludeCurrentEmployee(Long id, Long empId);

    public CallDtl findNonEndedCallDtlByCallMasterIdAndEmployeeId(Long id, Long empId);
}
