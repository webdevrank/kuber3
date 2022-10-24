package com.rank.ccms.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.rank.ccms.dao.ScheduleCallDao;
import com.rank.ccms.dto.EmployeeMstDto;
import com.rank.ccms.dto.ScheduleCallDto;
import com.rank.ccms.entities.ScheduleCall;
import com.rank.ccms.service.ScheduleCallDtlsService;

@Service
public class ScheduleCallDtlsServiceImpl extends SpringBeanAutowiringSupport implements ScheduleCallDtlsService, Serializable {

    @Autowired
    private ScheduleCallDao scheduleCallDtlsDao;

      
    @Override
    public List<ScheduleCallDto> getScheduledCallDtlsByEmployeeMstID(Long employeeMstId) {
        return scheduleCallDtlsDao.getScheduledCallDtlsByEmployeeMstID(employeeMstId);
    }

    @Override
    public List<ScheduleCallDto> findScheduledCallDtlsByScheduledMstID(Long scheduledCallMstId) {
        return scheduleCallDtlsDao.findScheduledCallDtlsByScheduledMstID(scheduledCallMstId);
    }

    

}
