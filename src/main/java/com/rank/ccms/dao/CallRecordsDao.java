package com.rank.ccms.dao;

import com.rank.ccms.dto.RecordingData;
import com.rank.ccms.entities.CallDtl;
import com.rank.ccms.entities.CallMst;
import com.rank.ccms.entities.CallRecords;
import java.util.List;

public interface CallRecordsDao extends GenericDao<CallRecords> {

    public List<CallDtl> findOngoingCallInformation(Integer empID);

    public List<CallRecords> findAllActivenNonDeletedCallRecords();

    public List<CallRecords> findCallRecords(RecordingData rd, List<Integer> callIdList);

    public List<CallMst> findCallMst(RecordingData rd);

    public CallRecords findCallRecordsByCallId(Long callId, Long empId);

    public CallRecords findCallRecordsByCallIdOnly(Long callId);

    public CallRecords findRecordsByRecoderId(String id);

    public List<CallRecords> findAllNotSavedRoomLinkCallRecords();

    public List<CallRecords> findListOfNotStopRecording();

}
