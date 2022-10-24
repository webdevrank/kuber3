package com.rank.ccms.service;

import com.rank.ccms.entities.CallDtl;
import com.rank.ccms.entities.CallMst;
import com.rank.ccms.entities.CallRecords;
import com.rank.ccms.dto.RecordingData;
import java.io.Serializable;
import java.util.List;

public interface CallRecordsService extends Serializable {

    CallRecords saveCallRecord(CallRecords callRecord);

    List<CallDtl> findOngoingCallInfo(Integer empID);

    List<CallRecords> findAllActivenNonDeletedCallRecords();

    CallRecords findRecordsById(Long id);

    List<CallRecords> findCallRecords(RecordingData rd, List<Integer> callIdList);

    List<CallMst> findCallMst(RecordingData rd);

    CallRecords findCallRecordsByCallId(Long callId, Long empId);

    CallRecords findCallRecordsByCallIdOnly(Long callId);

    CallRecords findRecordsByRecoderId(String id);

    List<CallRecords> findAllNotSavedRoomLinkCallRecords();
    
    List<CallRecords> findListOfNotStopRecording();

}
