package com.rank.ccms.service;

import java.io.Serializable;
import java.util.List;
import com.rank.ccms.dto.ScheduleCallDto;

public interface ScheduleCallDtlsService extends Serializable {

    List<ScheduleCallDto> getScheduledCallDtlsByEmployeeMstID(Long employeeMstId);

    List<ScheduleCallDto> findScheduledCallDtlsByScheduledMstID(Long scheduledCallMstId);

}
