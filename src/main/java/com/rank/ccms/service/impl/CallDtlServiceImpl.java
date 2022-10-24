package com.rank.ccms.service.impl;

import com.rank.ccms.dao.CallDtlDao;
import com.rank.ccms.dto.ConferenceAgentsDto;
import com.rank.ccms.entities.CallDtl;
import com.rank.ccms.entities.CallMst;
import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.service.CallDtlService;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("callDtlService")
public class CallDtlServiceImpl implements CallDtlService {

    @Autowired
    private CallDtlDao callDtlDao;

    @Override
    public CallDtl saveCallDtl(CallDtl callDtl) {

        if (callDtl.getId() == null) {
            callDtl = callDtlDao.saveRow(callDtl);

        } else {
            CallDtl existingCallDtl = callDtlDao.findById(callDtl.getId());
            if (existingCallDtl != null) {
                if (existingCallDtl != callDtl) {
                    existingCallDtl.setEndTime(callDtl.getEndTime());
                    existingCallDtl.setDeleteFlg(callDtl.getDeleteFlg());
                    existingCallDtl.setActiveFlg(callDtl.getActiveFlg());
                    existingCallDtl.setAgentComments(callDtl.getAgentComments());
                    existingCallDtl.setStartTime(callDtl.getStartTime());
                    existingCallDtl.setCallTypeInfo(callDtl.getCallTypeInfo());

                }
                try {
                    callDtl = callDtlDao.mergeRow(existingCallDtl);
                } catch (Exception e) {
                }
            } else {

                callDtl = callDtlDao.saveRow(callDtl);
            }
        }

        return callDtl;
    }

    @Override
    public List<CallDtl> findAllCallDtls() {
        return new java.util.ArrayList<>(callDtlDao.findAll());
    }

    @Override
    public List<CallDtl> findAllNonDeletedCallDtls() {
        return new java.util.ArrayList<>(callDtlDao.findAllNonDeletedCallDtls());
    }

    @Override
    public CallDtl findNonDeletedCallDtlId(Long id) {
        return callDtlDao.findNonDeletedCallDtlById(id);
    }

    @Override
    public List<CallDtl> findNonDeletedCallDtlByCallId(CallMst callId) {
        // NOTE: Please do not change the order of searching
        return new java.util.ArrayList<>(callDtlDao.findNonDeletedCallDtlByCallId(callId));
    }

    @Override
    public CallDtl findNonDeletedCallDtlByEmployee(Long id) {
        return callDtlDao.findCallDtlByEmployee(id);
    }

    @Override
    public List<CallDtl> findCallDtlByEmployeeWithinTimeRange(Long id, Timestamp stTime, Timestamp endTime) {
        return callDtlDao.findCallDtlByEmployeeWithinTimeRange(id, stTime, endTime);
    }

    @Override
    public List<CallDtl> findCallDtlByCallMasterIdAndCallTypeInfo(Long callMasterId, String callTypeInfo) {
        return callDtlDao.findCallDtlByCallMasterIdAndCallTypeInfo(callMasterId, callTypeInfo);
    }

    @Override
    public List<CallDtl> findEndedCallDtlBetweenDate(Timestamp fromDateTime, Timestamp toDateTime, Long empPkId) {
        return callDtlDao.findEndedCallDtlBetweenDate(fromDateTime, toDateTime, empPkId);
    }

    @Override
    public List<CallDtl> AgentEndedCallDtlBetweenDate(Timestamp fromDateTime, Timestamp toDateTime) {
        return callDtlDao.AgentEndedCallDtlBetweenDate(fromDateTime, toDateTime);
    }

    @Override
    public List<CallDtl> findInboundCallDtlBetweenDate(Timestamp fromDateTime, Timestamp toDateTime, Long empPkId) {
        return callDtlDao.findInboundCallDtlBetweenDate(fromDateTime, toDateTime, empPkId);
    }

    @Override
    public List<CallDtl> findInboundCallDtlBetweenDateForService(Timestamp fromDateTime, Timestamp toDateTime, Long empPkId) {
        return callDtlDao.findInboundCallDtlBetweenDateForService(fromDateTime, toDateTime, empPkId);
    }

    @Override
    public List<CallDtl> AllInboundCallDtlBetweenDate(Timestamp fromDateTime, Timestamp toDateTime) {
        return callDtlDao.AllInboundCallDtlBetweenDate(fromDateTime, toDateTime);
    }

    @Override
    public List<CallDtl> findOutboundCallDtlBetweenDate(Timestamp fromDateTime, Timestamp toDateTime, Long empPkId) {
        return callDtlDao.findOutboundCallDtlBetweenDate(fromDateTime, toDateTime, empPkId);
    }

    @Override
    public List<CallDtl> AllOutboundCallDtlBetweenDate(Timestamp fromDateTime, Timestamp toDateTime) {
        return callDtlDao.AllOutboundCallDtlBetweenDate(fromDateTime, toDateTime);
    }

    @Override
    public CallDtl findNonDeletedCallDtlByCallIdAndActivity(CallMst callId, String Activity) {
        List<CallDtl> callDtlList = callDtlDao.findNonDeletedCallDtlByCallIdAndActivity(callId, Activity);
        if (null == callDtlList || callDtlList.isEmpty()) {
            return null;
        } else {
            return (CallDtl) callDtlList.get(0);
        }
    }

    @Override
    public CallDtl findNonDeletedCallDtlByCallIdAndActivity(CallMst callId, List<String> activityList) {
        List<CallDtl> callDtlList = callDtlDao.findNonDeletedCallDtlByCallIdAndActivity(callId, activityList);
        if (null == callDtlList || callDtlList.isEmpty()) {
            return null;
        } else {
            return (CallDtl) callDtlList.get(0);
        }
    }

    @Override
    public List<CallDtl> findCallDtlByCallMasterIdAndAgent(Long callMasterId, Long employeeId) {
        return callDtlDao.findCallDtlByCallMasterIdAndAgent(callMasterId, employeeId);
    }

    @Override
    public List<CallDtl> findForwardedCallDtlBetweenDate(Timestamp fromDateTime, Timestamp toDateTime, Long empPkId) {
        return callDtlDao.findForwardedCallDtlBetweenDate(fromDateTime, toDateTime, empPkId);
    }

    @Override
    public List<CallDtl> AllForwardedCallDtlBetweenDate(Timestamp fromDateTime, Timestamp toDateTime) {
        return callDtlDao.AllForwardedCallDtlBetweenDate(fromDateTime, toDateTime);
    }

    @Override
    public CallDtl findCallDtlByCallMasterIdAndAgentNotForwarded(Long callMasterId, Long employeeId) {
        return callDtlDao.findCallDtlByCallMasterIdAndAgentNotForwarded(callMasterId, employeeId);
    }

    @Override
    public List<CallDtl> findCallDtlByCallMasterId(Long callMasterId) {
        return callDtlDao.findCallDtlByCallMasterId(callMasterId);
    }

    @Override
    public List<CallDtl> findCallDtlByCallMasterIdOtherThanThreeWay(Long callMasterId) {
        return callDtlDao.findCallDtlByCallMasterIdOtherThanThreeWay(callMasterId);
    }

    @Override
    public CallDtl findCallDtlByCallMstIdForSchduledCall(Long callMstId) {
        return callDtlDao.findCallDtlByCallMstIdForSchduledCall(callMstId);
    }

    @Override
    public List<CallDtl> findCallDtlByCallMstIdAndTimeRange(Timestamp fromTime, Timestamp toTime, Long callId) {
        return callDtlDao.findCallDtlByCallMstIdAndTimeRange(fromTime, toTime, callId);
    }

    @Override
    public CallDtl findLastNonEndedCallByEmpId(EmployeeMst employeeMst) {
        List<CallDtl> callDtlList = callDtlDao.findLastNonEndedCallByEmpId(employeeMst);
        if (null == callDtlList || callDtlList.isEmpty()) {
            return null;
        } else {
            return (CallDtl) callDtlList.get(0);
        }
    }

    @Override
    public List<CallDtl> findNonEndedCallDtlByCallMasterId(Long callMasterId) {
        return callDtlDao.findNonEndedCallDtlByCallMasterId(callMasterId);
    }

    @Override
    public CallDtl findCurrentCallAgent(Long callId) {
        return callDtlDao.findCurrentCallAgent(callId);
    }

    @Override
    public List<ConferenceAgentsDto> findParticipantCallDtlsByCallMstId(CallMst callMst) {
        return callDtlDao.findParticipantCallDtlsByCallMstId(callMst);
    }

    @Override
    public List<CallDtl> findNonEndedCallDtlByCallMasterIdAndExcludeCurrentEmployee(Long id, Long empId) {
        return callDtlDao.findNonEndedCallDtlByCallMasterIdAndExcludeCurrentEmployee(id, empId);
    }

    @Override
    public CallDtl findNonEndedCallDtlByCallMasterIdAndEmployeeId(Long callId, Long empId) {
        return callDtlDao.findNonEndedCallDtlByCallMasterIdAndEmployeeId(callId, empId);
    }

}
