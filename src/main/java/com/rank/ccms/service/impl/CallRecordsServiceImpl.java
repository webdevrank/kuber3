package com.rank.ccms.service.impl;

import com.rank.ccms.dao.CallRecordsDao;
import com.rank.ccms.entities.CallDtl;
import com.rank.ccms.entities.CallMst;
import com.rank.ccms.entities.CallRecords;
import com.rank.ccms.dto.RecordingData;
import com.rank.ccms.service.CallRecordsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("callRecordsService")
public class CallRecordsServiceImpl implements CallRecordsService {

    org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(this.getClass());

    @Autowired
    private CallRecordsDao callRecordsDao;

    @Transactional
    @Override
    public CallRecords saveCallRecord(CallRecords callRecord) {

        try {
            logger.info("Service : saveCallRecord: Start");
            if (callRecord.getId() == null) {
                callRecord = callRecordsDao.saveRow(callRecord);
                if (callRecord == null) {
                    logger.info("saveCallRecords: Data not saved!!!!");
                } else {
                    logger.info("saveCallRecords: Data saved Successfully!!!!");
                }
            } else {
                CallRecords existingCallRecord = callRecordsDao.findById(callRecord.getId());
                if (existingCallRecord != null) {
                    if (existingCallRecord != callRecord) {
                        existingCallRecord.setCallId(callRecord.getCallId());
                        existingCallRecord.setConferenceId(callRecord.getConferenceId());
                        existingCallRecord.setCustomerId(callRecord.getCustomerId());
                        existingCallRecord.setEmpId(callRecord.getEmpId());
                        existingCallRecord.setExternalPlaybackLink(callRecord.getExternalPlaybackLink());
                        existingCallRecord.setRecorderId(callRecord.getRecorderId());
                        existingCallRecord.setChatText(callRecord.getChatText());
                        existingCallRecord.setRoomName(callRecord.getRoomName());
                        callRecord = callRecordsDao.saveRow(existingCallRecord);
                        if (callRecord == null) {
                            logger.info("saveCallRecords: Data not updated!!!!");
                        } else {
                            logger.info("saveCallRecords: Data updated successfully!!!!");
                        }
                    }
                } else {
                    callRecord = callRecordsDao.saveRow(callRecord);
                    if (callRecord == null) {

                    } else {
                        logger.info("saveCallRecords: Data saved successfully!!!!");
                    }
                }
            }
            logger.info("Service : saveCallRecord: End");
        } catch (Exception e) {
        }
        return callRecord;
    }

    @Transactional(readOnly = true)
    @Override
    public List<CallDtl> findOngoingCallInfo(Integer empID) {

        List<CallDtl> callDtls = null;
        try {

            callDtls = callRecordsDao.findOngoingCallInformation(empID);

        } catch (Exception e) {
            logger.error("Error:findOngoingCallInfo" + e.getMessage());
        }
        return callDtls;
    }

    @Transactional(readOnly = true)
    @Override
    public List<CallRecords> findAllActivenNonDeletedCallRecords() {
        List<CallRecords> cr = callRecordsDao.findAllActivenNonDeletedCallRecords();
        return cr;
    }

    @Transactional(readOnly = true)
    @Override
    public CallRecords findRecordsById(Long id) {
        return callRecordsDao.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CallRecords> findCallRecords(RecordingData rd, List<Integer> callIdList) {
        return callRecordsDao.findCallRecords(rd, callIdList);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CallMst> findCallMst(RecordingData rd) {
        List<CallMst> cmList = callRecordsDao.findCallMst(rd);
        return cmList;
    }

    @Override
    public CallRecords findCallRecordsByCallId(Long callId, Long empId) {
        return callRecordsDao.findCallRecordsByCallId(callId, empId);
    }

    @Override
    public CallRecords findCallRecordsByCallIdOnly(Long callId) {
        return callRecordsDao.findCallRecordsByCallIdOnly(callId);
    }

    @Override
    public CallRecords findRecordsByRecoderId(String id) {
        return callRecordsDao.findRecordsByRecoderId(id);
    }

    @Override
    public List<CallRecords> findAllNotSavedRoomLinkCallRecords() {
        return callRecordsDao.findAllNotSavedRoomLinkCallRecords();
    }

    @Transactional(readOnly = true)
    @Override
    public List<CallRecords> findListOfNotStopRecording() {
        return callRecordsDao.findListOfNotStopRecording();
    }

}
