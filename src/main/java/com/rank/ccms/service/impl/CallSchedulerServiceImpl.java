package com.rank.ccms.service.impl;

import com.rank.ccms.dao.ScheduleCallDao;
import com.rank.ccms.dto.ScheduleCallDto;
import com.rank.ccms.entities.CustomerMst;
import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.entities.ScheduleCall;
import com.rank.ccms.service.CallSchedulerService;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

@Service("callSchedulerService")
public class CallSchedulerServiceImpl extends SpringBeanAutowiringSupport implements CallSchedulerService {

    Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private ScheduleCallDao callSchedulerDao;

    @Transactional(readOnly = true)
    @Override
    public List<ScheduleCall> findAllNonTakenScheduleCallByGivenTimeRange(Timestamp fromDateTime, Timestamp toDateTime) {
        List<ScheduleCall> scheduleCallList = callSchedulerDao.findAllNonTakenScheduleCallForGivenTimeRange(fromDateTime, toDateTime);
        return scheduleCallList;
    }

    @Transactional
    @Override
    public ScheduleCall saveScheduleCall(ScheduleCall scheduleCall, EmployeeMst employeeMst) {

        if (scheduleCall.getId() == null) {
            scheduleCall = callSchedulerDao.saveRow(scheduleCall);
        } else {
            ScheduleCall existingScheduleCall = callSchedulerDao.findById(scheduleCall.getId());
            if (existingScheduleCall != null) {
                if (existingScheduleCall != scheduleCall) {
                    existingScheduleCall.setCustomerId(scheduleCall.getCustomerId());
                    existingScheduleCall.setExecuteStatus(scheduleCall.getExecuteStatus());
                    existingScheduleCall.setSchedulerId(scheduleCall.getSchedulerId());
                    existingScheduleCall.setScheduleDate(scheduleCall.getScheduleDate());
                    existingScheduleCall.setCallmstid(scheduleCall.getCallmstid());
                    existingScheduleCall.setCustomerTime(scheduleCall.getCustomerTime());
                    existingScheduleCall.setActiveFlg(scheduleCall.getActiveFlg());
                    existingScheduleCall.setDeleteFlg(scheduleCall.getDeleteFlg());

                }
                scheduleCall = callSchedulerDao.mergeRow(existingScheduleCall);

            } else {
                scheduleCall = callSchedulerDao.saveRow(scheduleCall);
            }
        }

        return scheduleCall;
    }

    @Override
    public List<ScheduleCall> findAllNonTakenScheduleCallByGivenTime(Timestamp currDateTime) {
        //Oracle
//        List<ScheduleCall> scheduleCallList = callSchedulerDao.createQuerySingleResult(
//                "from ScheduleCall where executeStatus='Scheduled' and to_date(to_char (scheduleDate, 'dd-mon-yyyy hh24:mi'),'dd-mon-yyyy hh24:mi')=to_date(to_char (?, 'dd-mon-yyyy hh24:mi'),'dd-mon-yyyy hh24:mi')", currDateTime);

//         List<ScheduleCall> scheduleCallList = callSchedulerDao.createQuerySingleResult(
//                "FROM ScheduleCall WHERE execute_status='Scheduled' "+
//                "AND STR_TO_DATE(DATE_FORMAT(scheduleDate, '%Y-%m-%d %H:%i'),'%Y-%m-%d %H:%i')=STR_TO_DATE(DATE_FORMAT(?, '%Y-%m-%d %H:%i'),'%Y-%m-%d %H:%i')", currDateTime.toString().substring(0, currDateTime.toString().length()-4));
        List<ScheduleCall> scheduleCallList = callSchedulerDao.createQuerySingleResult(
                "from ScheduleCall where executeStatus='Scheduled' and to_timestamp(to_char (scheduleDate, 'DD-mon-YYYY HH24:MI'),'DD-mon-YYYY HH24:MI')=to_timestamp('" + currDateTime + "','YYYY-MM-DD HH24:MI')");

        return scheduleCallList;
    }

    @Override
    public List<ScheduleCall> findAllNonTakenScheduleCallByTimeRange(Timestamp currDateTime1, Timestamp currDateTime2) {
        //Oracle
//        List<ScheduleCall> scheduleCallList = callSchedulerDao.createQuerySingleResult(
//                "from ScheduleCall where executeStatus='Scheduled' and to_date(to_char (scheduleDate, 'dd-mon-yyyy hh24:mi'),'dd-mon-yyyy hh24:mi')=to_date(to_char (?, 'dd-mon-yyyy hh24:mi'),'dd-mon-yyyy hh24:mi')", currDateTime);

//         List<ScheduleCall> scheduleCallList = callSchedulerDao.createQuerySingleResult(
//                "FROM ScheduleCall WHERE execute_status='Scheduled' "+
//                "AND STR_TO_DATE(DATE_FORMAT(scheduleDate, '%Y-%m-%d %H:%i'),'%Y-%m-%d %H:%i')=STR_TO_DATE(DATE_FORMAT(?, '%Y-%m-%d %H:%i'),'%Y-%m-%d %H:%i')", currDateTime.toString().substring(0, currDateTime.toString().length()-4));
        String query = "from ScheduleCall where executeStatus='Scheduled' and to_timestamp(to_char (scheduleDate, 'DD-mon-YYYY HH24:MI'),'DD-mon-YYYY HH24:MI') between to_timestamp('" + currDateTime1 + "','YYYY-MM-DD HH24:MI') and to_timestamp('" + currDateTime2 + "','YYYY-MM-DD HH24:MI')";

        List<ScheduleCall> scheduleCallList = callSchedulerDao.createQuerySingleResult(query);

        return scheduleCallList;
    }

    @Override
    public List<ScheduleCall> findAllNonTakenScheduleCallByTimeRangeByCustId(Timestamp currDateTime1, Timestamp currDateTime2, CustomerMst custMstId) {
        List<ScheduleCall> scheduleCallList = callSchedulerDao.createQuerySingleResult(
                "from ScheduleCall where executeStatus='Scheduled' and customerId.id='" + custMstId.getId() + "' and to_timestamp(to_char (scheduleDate, 'DD-mon-YYYY HH24:MI'),'DD-mon-YYYY HH24:MI') between to_timestamp('" + currDateTime1 + "','YYYY-MM-DD HH24:MI') and to_timestamp('" + currDateTime2 + "','YYYY-MM-DD HH24:MI')");

        return scheduleCallList;
    }

    @Override
    public List<ScheduleCall> findAllNonTakenScheduleCallForGivenCustomer(Long customerId, Timestamp currDateTime) {
        return new ArrayList<>(callSchedulerDao.findAllNonTakenScheduleCallByGivenCustomer(customerId, currDateTime));
    }

    @Override
    public List<ScheduleCall> findAllNonTakenScheduleCallMissedByGivenCustomer(Long customerId, Timestamp currDateTime) {
        return new ArrayList<>(callSchedulerDao.findAllNonTakenScheduleCallMissedByGivenCustomer(customerId, currDateTime));
    }

    @Override
    public List<ScheduleCall> findAllNonTakenScheduleCall() {
        return new ArrayList<>(callSchedulerDao.findAllNonTakenScheduleCalls());

    }

    @Override
    public ScheduleCall findAllNonTakenScheduleCallById(Long id) {
        return callSchedulerDao.findById(id);
    }

    @Override
    public List<ScheduleCall> findAllNonTakenScheduleCallsByDate(Timestamp fromDateTime) {
        return callSchedulerDao.findAllNonTakenScheduleCallsByDate(fromDateTime);
    }

    @Override
    public ScheduleCall findNonDeletedScheduleCallById(Long id) {
        return callSchedulerDao.findNonDeletedById(id);
    }

    @Override
    public List<ScheduleCall> findAllTakenScheduleCallByGivenTimeRange(Timestamp fromDateTime, Timestamp toDateTime) {
        List<ScheduleCall> scheduleCallList = callSchedulerDao.findAllTakenScheduleCallForGivenTimeRange(fromDateTime, toDateTime);
        return scheduleCallList;
    }

    @Override
    public List<ScheduleCall> findAllTakenScheduleCallByGivenTimeRangeAndCustId(Timestamp fromDateTime, Timestamp toDateTime, CustomerMst custId) {
        List<ScheduleCall> scheduleCallList = callSchedulerDao.findAllTakenScheduleCallByGivenTimeRangeAndCustId(fromDateTime, toDateTime, custId);
        return scheduleCallList;
    }

    @Override
    public List<ScheduleCall> findAllNonTakenScheduleCallsByDateRange(Timestamp fromDateTime, Timestamp toDateTime) {
        return callSchedulerDao.findAllNonTakenScheduleCallsByDateRange(fromDateTime, toDateTime);
    }

    @Override
    public List<ScheduleCall> findScheduleCallByCustomerIdCallMstId(Long callMstId, Long customerPkId) {
        return callSchedulerDao.findScheduleCallByCustomerIdCallMstId(callMstId, customerPkId);
    }

    @Override
    public List<ScheduleCall> findAllScheduleCall() {
        return new ArrayList<>(callSchedulerDao.findAllScheduleCalls());
    }

    @Override
    public ScheduleCall findNonTakenById(Long id) {
        return callSchedulerDao.findNonTakenById(id);
    }

    @Override
    public List<ScheduleCallDto> getScheduledCallDtlsByEmployeeMstID(Long employeeMstId) {
        return callSchedulerDao.getScheduledCallDtlsByEmployeeMstID(employeeMstId);
    }

    @Override
    public List<ScheduleCallDto> getScheduledCallDtlsByCustomerMstID(Long customerMstId) {
        return callSchedulerDao.getScheduledCallDtlsByCustomerMstID(customerMstId);
    }

    @Override
    public List<ScheduleCallDto> findScheduledCallDtlsByScheduledMstID(Long scheduledCallMstId) {
        return callSchedulerDao.findScheduledCallDtlsByScheduledMstID(scheduledCallMstId);
    }

    @Override
    public List<ScheduleCallDto> getScheduledCallDtlsByRMId(long rmId) {
        return callSchedulerDao.getScheduledCallDtlsByRMId(rmId);
    }

    @Override
    public List<ScheduleCallDto> getScheduledCallDtlsByCustomerId(long customerMstId) {
        return callSchedulerDao.getScheduledCallDtlsByCustomerId(customerMstId);
    }

}
