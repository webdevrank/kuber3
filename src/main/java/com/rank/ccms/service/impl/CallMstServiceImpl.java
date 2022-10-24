package com.rank.ccms.service.impl;

import com.rank.ccms.dao.CallMstDao;
import com.rank.ccms.entities.CallMst;
import com.rank.ccms.service.CallMstService;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("callMstService")
public class CallMstServiceImpl implements CallMstService {

    org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(this.getClass());

    @Autowired
    private CallMstDao callMstDao;

    @Override
    public synchronized CallMst saveCallMst(CallMst callMst) {
        if (callMst.getId() == null) {
            callMst = callMstDao.saveRow(callMst);

        } else {
            CallMst existingCallMst = callMstDao.findById(callMst.getId());
            if (existingCallMst != null) {
                if (existingCallMst != callMst) {

                    existingCallMst.setId(callMst.getId());
                    existingCallMst.setCallStatus(callMst.getCallStatus());
                    existingCallMst.setCallType(callMst.getCallType());
                    existingCallMst.setCategoryId(callMst.getCategoryId());
                    existingCallMst.setCustomerId(callMst.getCustomerId());
                    existingCallMst.setCustId(callMst.getCustId());
                    existingCallMst.setDeviceBrand(callMst.getDeviceBrand());
                    existingCallMst.setDeviceId(callMst.getDeviceId());
                    existingCallMst.setDeviceIp(callMst.getDeviceIp());
                    existingCallMst.setDeviceName(callMst.getDeviceName());
                    existingCallMst.setDeviceOs(callMst.getDeviceOs());
                    existingCallMst.setCallOption(callMst.getCallOption());
                    existingCallMst.setiMEIno(callMst.getiMEIno());
                    existingCallMst.setActiveFlg(callMst.getActiveFlg());
                    existingCallMst.setCallMedium(callMst.getCallMedium());
                    existingCallMst.setDeleteFlg(callMst.getDeleteFlg());
                    existingCallMst.setEndTime(callMst.getEndTime());
                    existingCallMst.setStartTime(callMst.getStartTime());
                    existingCallMst.setAgentPickupTime(callMst.getAgentPickupTime());
                    existingCallMst.setCustomerRequestTime(callMst.getCustomerRequestTime());
                    existingCallMst.setRoutingCallTime(callMst.getRoutingCallTime());
                    existingCallMst.setCustomerHangupTime(callMst.getCustomerHangupTime());
                    existingCallMst.setRoomName(callMst.getRoomName());
                    existingCallMst.setCustomerPickupTime(callMst.getCustomerPickupTime());
                    existingCallMst.setCallLatitude(callMst.getCallLatitude());
                    existingCallMst.setCallLongitude(callMst.getCallLongitude());
                    existingCallMst.setCallLocation(callMst.getCallLocation());
                }
                try {
                    callMst = callMstDao.mergeRow(existingCallMst);
                } catch (Exception e) {
                    logger.error("Error:saveCallMst:" + e.getMessage());
                }
            } else {
                callMst = callMstDao.saveRow(callMst);
            }
        }
        return callMst;
    }

    @Override
    public List<CallMst> findAllCallMsts() {
        return new java.util.ArrayList<>(callMstDao.findAll());
    }

    @Override
    public List<CallMst> findAllNonDeletedCallMsts() {
        return new java.util.ArrayList<>(callMstDao.findAllNonDeletedCallMsts());
    }

    @Override
    public CallMst findCallMstById(Long id) {
        return callMstDao.findCallById(id);
    }

    @Override
    public CallMst findNonDeletedCallMstById(Long id) {
        return callMstDao.findNonDeletedById(id);
    }

    @Override
    public Long findCallMstMaxIdByCustId(Long custId) {
        return callMstDao.findMaxCallIdByCustId(custId);
    }

    @Override
    public List<CallMst> findCallMstByCallStatus(String callStatus, Timestamp startDate, Timestamp endDate) {
        return callMstDao.findAllNonDeletedCallMstsByCallStatus(callStatus, startDate, endDate);
    }

    @Override
    public List<CallMst> findCallMstByCallStatus(String callStatus, Timestamp startDate, Timestamp endDate, String custId, String mobile, String account) {
        if (mobile == null || mobile.equals("")) {
            mobile = "0";
        }
        return callMstDao.findAllNonDeletedCallMstsByCallStatus(callStatus, startDate, endDate, custId, Long.parseLong(mobile), account);
    }

    @Override
    public CallMst findNonDeletedById(Long callMstId, String custId, String mobile, String account) {
        if (mobile == null || mobile.equals("")) {
            mobile = "0";
        }
        return callMstDao.findNonDeletedById(callMstId, custId, Long.parseLong(mobile), account);
    }

    @Override
    public List<CallMst> findAllNonDeletedCallMstsByCallType(String callType, Timestamp startDate, Timestamp endDate, String custId, String account, String mobile, Integer catgId) {
        if (catgId == null) {
            catgId = 0;
        }
        if (mobile == null || mobile.trim().equals("")) {
            mobile = "0";
        }

        return callMstDao.findAllNonDeletedCallMstsByCallType(callType, startDate, endDate, custId, account, Long.parseLong(mobile), catgId);
    }

    @Override
    public List<CallMst> findAllNonDeletedCallMsts(Timestamp startDate, Timestamp endDate, String custId, String custName, String account, String custMobile) {
        if (custMobile == null || custMobile.equals("") || custMobile.length() <= 0) {
            custMobile = "0";
        }
        return callMstDao.findAllNonDeletedCallMsts(startDate, endDate, custId, custName, account, Long.parseLong(custMobile));
    }

    @Override
    public List<CallMst> findNonDeletedCallByDate(Timestamp startDate, Timestamp endDate) {
        return callMstDao.findNonDeletedCallByDate(startDate, endDate);
    }

    @Override
    public List<CallMst> numberOfCalls(Timestamp startDate, Timestamp endDate, String custid, String deviceId, Long serviceId) {
        return callMstDao.numberOfCalls(startDate, endDate, custid, deviceId, serviceId);
    }

    @Override
    public CallMst findNonDeletedCallById(Long callID) {
        return callMstDao.findNonDeletedCallById(callID);
    }

    @Override
    public List<CallMst> findDroppedCallByDate(Timestamp startDate, Timestamp endDate) {
        return callMstDao.findDroppedCallByDate(startDate, endDate);
    }

    @Override
    public List<CallMst> findDroppedCallByDateCustid(Timestamp startDate, Timestamp endDate, String Custid) {
        return callMstDao.findDroppedCallByDateCustId(startDate, endDate, Custid);

    }

    @Override
    public List<CallMst> findEndedCallByDateCustid(Timestamp startDate, Timestamp endDate, String Custid) {
        return callMstDao.findEndedCallByDateCustId(startDate, endDate, Custid);

    }

    @Override
    public List<CallMst> findDroppedByCustid(String Custid) {
        return callMstDao.findDroppedCallByCustId(Custid);
    }

    @Override
    public List<CallMst> findCallMstByExceptThisCallStatus(String callStatus, Timestamp startDate, Timestamp endDate) {
        return callMstDao.findCallMstByExceptThisCallStatus(callStatus, startDate, endDate);
    }

    @Override
    public List<CallMst> findNonDropedCallByDateCustid(Timestamp startDate, Timestamp endDate, String custId) {
        return callMstDao.findNonDropedCallByDateCustid(startDate, endDate, custId);
    }

    @Override
    public List<CallMst> numberOfReceviedCalls(Timestamp startDate, Timestamp endDate, String custid, String deviceId, Long serviceId, String callStatus, String callstatus2) {
        return callMstDao.numberOfReceviedCalls(startDate, endDate, custid, deviceId, serviceId, callStatus, callstatus2);
    }

    @Override
    public List<CallMst> findAllOnGoingCalls() {
        return callMstDao.findAllOnGoingCalls();
    }

    @Override
    public List<CallMst> findAllOnGoingCallsByCust(String custId) {
        return callMstDao.findAllOnGoingCallsByCust(custId);
    }

    @Override
    public List<CallMst> findAllNotRecevedCallsByCust(String custId) {
        return callMstDao.findAllNotRecevedCallsByCust(custId);
    }

    @Override
    public CallMst findCallMstMaxByCustId(Long custId) {
        return callMstDao.findMaxCallMstByCustId(custId);
    }

    @Override
    public List<CallMst> findAllWaitingCalls() {
        return callMstDao.findAllWaitingCalls();
    }

    @Override
    public CallMst findNonEndedCustomerCallByCallId(Long callId, String custId) {
        return callMstDao.findNonEndedCustomerCallByCallId(callId, custId);
    }

}
