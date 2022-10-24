package com.rank.ccms.service;

import com.rank.ccms.dto.ScheduleCallDto;
import com.rank.ccms.entities.CustomerMst;
import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.entities.ScheduleCall;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public interface CallSchedulerService extends Serializable {

    List<ScheduleCall> findAllNonTakenScheduleCallByGivenTimeRange(Timestamp fromDateTime, Timestamp toDateTime);

    List<ScheduleCall> findAllNonTakenScheduleCallByGivenTime(Timestamp currDateTime);

    List<ScheduleCall> findAllNonTakenScheduleCallByTimeRange(Timestamp currDateTime1, Timestamp currDateTime2);

    ScheduleCall findAllNonTakenScheduleCallById(Long id);

    List<ScheduleCall> findAllNonTakenScheduleCall();

    List<ScheduleCall> findAllScheduleCall();

    List<ScheduleCall> findAllNonTakenScheduleCallForGivenCustomer(Long customerId, Timestamp currDateTime);

    List<ScheduleCall> findAllNonTakenScheduleCallMissedByGivenCustomer(Long customerId, Timestamp currDateTime);

    ScheduleCall saveScheduleCall(ScheduleCall scheduleCall, EmployeeMst employeeMst);

    List<ScheduleCall> findAllNonTakenScheduleCallsByDate(Timestamp fromDateTime);

    List<ScheduleCall> findAllTakenScheduleCallByGivenTimeRange(Timestamp fromDateTime, Timestamp toDateTime);

    List<ScheduleCall> findAllTakenScheduleCallByGivenTimeRangeAndCustId(Timestamp fromDateTime, Timestamp toDateTime, CustomerMst custId);

    ScheduleCall findNonDeletedScheduleCallById(Long id);

    List<ScheduleCall> findAllNonTakenScheduleCallsByDateRange(Timestamp fromDateTime, Timestamp toDateTime);

    List<ScheduleCall> findScheduleCallByCustomerIdCallMstId(Long callMstId, Long customerPkId);

    List<ScheduleCall> findAllNonTakenScheduleCallByTimeRangeByCustId(Timestamp currDateTime1, Timestamp currDateTime2, CustomerMst custMstId);

    ScheduleCall findNonTakenById(Long id);

    List<ScheduleCallDto> getScheduledCallDtlsByEmployeeMstID(Long employeeMstId);

    List<ScheduleCallDto> getScheduledCallDtlsByCustomerMstID(Long customerMstId);

    List<ScheduleCallDto> findScheduledCallDtlsByScheduledMstID(Long scheduledCallMstId);

    List<ScheduleCallDto> getScheduledCallDtlsByRMId(long rmId);

    List<ScheduleCallDto> getScheduledCallDtlsByCustomerId(long customerMstId);

}
