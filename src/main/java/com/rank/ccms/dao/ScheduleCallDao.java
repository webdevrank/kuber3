package com.rank.ccms.dao;

import com.rank.ccms.dto.ScheduleCallDto;
import com.rank.ccms.entities.CustomerMst;
import com.rank.ccms.entities.ScheduleCall;
import java.sql.Timestamp;
import java.util.List;

public interface ScheduleCallDao extends GenericDao<ScheduleCall> {

    @Override
    public ScheduleCall findById(Long id);

    public List<ScheduleCall> findAllNonTakenScheduleCalls();

    public List<ScheduleCall> findAllScheduleCalls();

    public List<ScheduleCall> findAllNonTakenScheduleCallsByDate(Timestamp fromDateTime);

    public List<ScheduleCall> findAllNonTakenScheduleCallForGivenTimeRange(Timestamp fromDateTime, Timestamp toDateTime);

    public List<ScheduleCall> findAllNonTakenScheduleCallForGivenTime(Timestamp currDateTime);

    public List<ScheduleCall> findAllNonTakenScheduleCallMissedByGivenCustomer(Long customerId, Timestamp currDateTime);

    public List<ScheduleCall> findAllNonTakenScheduleCallByGivenCustomer(Long customerId, Timestamp currDateTime);

    public List<ScheduleCall> findAllScheduledCallByCustomerId(Long customerId);

    public List<ScheduleCall> findAllTakenScheduleCallForGivenTimeRange(Timestamp fromDateTime, Timestamp toDateTime);

    public List<ScheduleCall> findAllTakenScheduleCallByGivenTimeRangeAndCustId(Timestamp fromDateTime, Timestamp toDateTime, CustomerMst custId);

    public List<ScheduleCall> findAllNonTakenScheduleCallsByDateRange(Timestamp fromDateTime, Timestamp toDateTime);

    public List<ScheduleCall> findScheduleCallByCustomerIdCallMstId(Long callMstId, Long customerPkId);

    public ScheduleCall findNonTakenById(Long id);

    public List<ScheduleCallDto> getScheduledCallDtlsByEmployeeMstID(Long employeeMstId);

    public List<ScheduleCallDto> findScheduledCallDtlsByScheduledMstID(Long scheduledCallMstId);

    public List<ScheduleCallDto> getScheduledCallDtlsByCustomerMstID(Long customerMstId);

    public List<ScheduleCallDto> getScheduledCallDtlsByRMId(long rmId);

    public List<ScheduleCallDto> getScheduledCallDtlsByCustomerId(long customerMstId);

}
