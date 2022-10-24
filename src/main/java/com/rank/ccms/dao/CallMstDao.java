package com.rank.ccms.dao;

import com.rank.ccms.entities.CallMst;
import java.sql.Timestamp;
import java.util.List;

public interface CallMstDao extends GenericDao<CallMst> {

    public CallMst findNonDeletedCallById(Long id);

    public Long findMaxCallIdByCustId(Long custId);

    public CallMst findMaxCallMstByCustId(Long custId);

    public CallMst findCallById(Long id);

    public List<CallMst> findAllNonDeletedCallMsts();

    public List<CallMst> findAllNonDeletedCallMstsByCallStatus(String callStatus, Timestamp startDate, Timestamp endDate);

    public List<CallMst> findAllNonDeletedCallMstsByCallStatus(String callStatus, Timestamp startDate, Timestamp endDate, String custId, Long mobile, String account);

    public CallMst findNonDeletedById(Long callId, String custId, Long mobile, String account);

    public List<CallMst> findNonDeletedCallByDate(Timestamp startDate, Timestamp endDate);

    public List<CallMst> numberOfCalls(Timestamp startDate, Timestamp endDate, String custid, String deviceId, Long serviceId);

    public List<CallMst> findDroppedCallByDate(Timestamp startDate, Timestamp endDate);

    public List<CallMst> findDroppedCallByDateCustId(Timestamp startDate, Timestamp endDate, String Custid);

    public List<CallMst> findEndedCallByDateCustId(Timestamp startDate, Timestamp endDate, String Custid);

    public CallMst findForwardedCallByDateCustId(Timestamp startDate, Timestamp endDate, String Custid, Integer id);

    public List<CallMst> findDroppedCallByCustId(String Custid);

    public List<CallMst> findCallMstByExceptThisCallStatus(String callStatus, Timestamp startDate, Timestamp endDate);

    public List<CallMst> findNonDropedCallByDateCustid(Timestamp startDate, Timestamp endDate, String custId);

    public List<CallMst> findAllNonDeletedCallMstsByCallType(String callType, Timestamp startDate, Timestamp endDate, String custId, String account, Long mobileNo, Integer catgId);

    public List<CallMst> findAllNonDeletedCallMsts(Timestamp startDate, Timestamp endDate, String custId, String custName, String account, Long custMobile);

    public List<CallMst> numberOfReceviedCalls(Timestamp startDate, Timestamp endDate, String custid, String deviceId, Long serviceId, String callStatus, String callstatus2);

    public List<CallMst> findAllOnGoingCalls();

    public List<CallMst> findAllOnGoingCallsByCust(String custId);

    public List<CallMst> findAllNotRecevedCallsByCust(String custId);

    public List<CallMst> findAllWaitingCalls();

    public CallMst findNonEndedCustomerCallByCallId(Long callId, String custId);

}
