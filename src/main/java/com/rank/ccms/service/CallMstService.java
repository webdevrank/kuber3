package com.rank.ccms.service;

import com.rank.ccms.entities.CallMst;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public interface CallMstService extends Serializable {

    CallMst saveCallMst(CallMst callMst);

    List<CallMst> findAllCallMsts();

    List<CallMst> findAllNonDeletedCallMsts();

    CallMst findCallMstById(Long id);

    CallMst findNonDeletedCallMstById(Long id);

    Long findCallMstMaxIdByCustId(Long custId);

    List<CallMst> findCallMstByCallStatus(String callStatus, Timestamp startDate, Timestamp endDate);

    List<CallMst> findCallMstByCallStatus(String callStatus, Timestamp startDate, Timestamp endDate, String custId, String mobile, String account);

    CallMst findNonDeletedById(Long callMstId, String custId, String mobile, String account);

    List<CallMst> findAllNonDeletedCallMstsByCallType(String callType, Timestamp startDate, Timestamp endDate, String custId, String account, String mobile, Integer catgId);

    List<CallMst> findAllNonDeletedCallMsts(Timestamp startDate, Timestamp endDate, String custId, String custName, String account, String custMobile);

    public List<CallMst> findNonDeletedCallByDate(Timestamp startDate, Timestamp endDate);

    public List<CallMst> numberOfCalls(Timestamp startDate, Timestamp endDate, String custid, String deviceId, Long serviceId);

    public CallMst findNonDeletedCallById(Long callID);

    public List<CallMst> findDroppedCallByDate(Timestamp startDate, Timestamp endDate);

    public List<CallMst> findDroppedCallByDateCustid(Timestamp startDate, Timestamp endDate, String Custid);

    public List<CallMst> findEndedCallByDateCustid(Timestamp startDate, Timestamp endDate, String Custid);

    public List<CallMst> findDroppedByCustid(String Custid);

    public List<CallMst> findCallMstByExceptThisCallStatus(String callStatus, Timestamp startDate, Timestamp endDate);

    public List<CallMst> findNonDropedCallByDateCustid(Timestamp startDate, Timestamp endDate, String custId);

    public List<CallMst> numberOfReceviedCalls(Timestamp startDate, Timestamp endDate, String custid, String deviceId, Long serviceId, String callStatus, String callStatus2);

    public List<CallMst> findAllOnGoingCalls();

    public List<CallMst> findAllOnGoingCallsByCust(String custId);

    public List<CallMst> findAllNotRecevedCallsByCust(String custId);
    
    public CallMst findCallMstMaxByCustId(Long custId);
    
    public List<CallMst> findAllWaitingCalls();

	public CallMst findNonEndedCustomerCallByCallId(Long callId, String custId);
    
}
