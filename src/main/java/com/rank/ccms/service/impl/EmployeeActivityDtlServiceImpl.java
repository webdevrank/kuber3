package com.rank.ccms.service.impl;

import com.rank.ccms.dao.EmployeeActivityDtlDao;
import com.rank.ccms.dto.AgentStatusDto;
import com.rank.ccms.dto.EmployeeActivityDto;
import com.rank.ccms.entities.EmployeeActivityDtl;
import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.service.EmployeeActivityDtlService;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("employeeActivityDtlService")
public class EmployeeActivityDtlServiceImpl implements EmployeeActivityDtlService {

    Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private EmployeeActivityDtlDao employeeActivityDtlDao;

    @Transactional
    @Override
    public EmployeeActivityDtl save(EmployeeActivityDtl employeeActivityDtl) {

        if (employeeActivityDtl.getId() == null) {
            employeeActivityDtl = employeeActivityDtlDao.saveRow(employeeActivityDtl);
        } else {
            EmployeeActivityDtl existingEmployeeActivityDtl = employeeActivityDtlDao.findById(employeeActivityDtl.getId());
            if (existingEmployeeActivityDtl != null) {
                if (existingEmployeeActivityDtl != employeeActivityDtl) {
                    existingEmployeeActivityDtl.setId(employeeActivityDtl.getId());
                    existingEmployeeActivityDtl.setActivity(employeeActivityDtl.getActivity());
                    existingEmployeeActivityDtl.setEmpId(employeeActivityDtl.getEmpId());
                    existingEmployeeActivityDtl.setEndTime(employeeActivityDtl.getEndTime());
                    existingEmployeeActivityDtl.setReasonCd(employeeActivityDtl.getReasonCd());
                    existingEmployeeActivityDtl.setReasonDesc(employeeActivityDtl.getReasonDesc());
                    existingEmployeeActivityDtl.setReasonId(employeeActivityDtl.getReasonId());
                    existingEmployeeActivityDtl.setStartTime(employeeActivityDtl.getStartTime());
                    existingEmployeeActivityDtl.setNotification(employeeActivityDtl.getNotification());
                    existingEmployeeActivityDtl.setCallMstId(employeeActivityDtl.getCallMstId());
                    existingEmployeeActivityDtl.setRespectiveLoginId(employeeActivityDtl.getRespectiveLoginId());
                }
                try {
                    employeeActivityDtl = employeeActivityDtlDao.mergeRow(existingEmployeeActivityDtl);
                } catch (Exception e) {
                    logger.error("Error:EmployeeActivityDtl save" + e.getMessage());
                }
            } else {
                employeeActivityDtl = employeeActivityDtlDao.saveRow(employeeActivityDtl);
            }
        }
        return employeeActivityDtl;
    }

    @Override
    public EmployeeActivityDtl save(EmployeeActivityDtl employeeActivityDtl, EmployeeMst employeeMst) {

        if (null == employeeActivityDtl.getId()) {
            employeeActivityDtl = employeeActivityDtlDao.saveRow(employeeActivityDtl);
        } else {
            employeeActivityDtl = employeeActivityDtlDao.mergeRow(employeeActivityDtl);
        }

        return employeeActivityDtl;

    }

    @Transactional
    @Override
    public EmployeeActivityDtl findLastNonEndedActivityByType(Long empPkId, String activity) {
        List<EmployeeActivityDtl> activityDtlList = employeeActivityDtlDao.findLastNonEndedActivityByType(empPkId, activity);
        if (null == activityDtlList || activityDtlList.isEmpty()) {
            return null;
        } else {
            return (EmployeeActivityDtl) activityDtlList.get(0);
        }
    }

    @Transactional
    @Override
    public List<EmployeeActivityDtl> findListNonEndedActivityByType(Long empPkId, String activity) {
        return employeeActivityDtlDao.findLastNonEndedActivityByType(empPkId, activity);

    }

    @Override
    public List<EmployeeActivityDtl> findAllActivityDtl() {
        return new ArrayList<>(employeeActivityDtlDao.findAll());
    }

    @Transactional
    @Override
    public EmployeeActivityDtl findAgentStatus(Long empPkId, String activity) {
        List<EmployeeActivityDtl> activityDtlList = employeeActivityDtlDao.findAgentLogggedIn(empPkId, activity);
        if (null == activityDtlList || activityDtlList.isEmpty()) {
            return null;
        } else {
            return (EmployeeActivityDtl) activityDtlList.get(0);
        }
    }

    @Override
    public List<EmployeeActivityDtl> findStatusByAgent(Long empPkId) {
        return employeeActivityDtlDao.findStatusByAgent(empPkId);
    }

    @Override
    public List<EmployeeActivityDtl> findStatusByAgentTypeTimeRange(Long empPkId, String activity, Timestamp startTime, Timestamp endTime) {
        return employeeActivityDtlDao.findStatusByAgentTypeTimeRange(empPkId, activity, startTime, endTime);
    }

    @Override
    public List<EmployeeActivityDtl> findAllEndedActivityBetweenDate(Timestamp fromDateTime, Timestamp toDateTime, String activity, Long empPkId) {
        return employeeActivityDtlDao.findAllEndedActivityBetweenDate(fromDateTime, toDateTime, activity, empPkId);
    }

    @Override
    public List<EmployeeActivityDtl> findAllLoggedInEmployeeList() {
        return employeeActivityDtlDao.findAllLoggedInEmployeeList();
    }

    @Override
    public List<EmployeeActivityDtl> allAgentEndedActivityBetweenDate(Timestamp fromDateTime, Timestamp toDateTime, String activity) {
        return employeeActivityDtlDao.allAgentEndedActivityBetweenDate(fromDateTime, toDateTime, activity);
    }

    @Override
    public Long findMaxLoginIdByEmpId(Long empId) {
        return employeeActivityDtlDao.findMaxLoginIdByEmpId(empId);
    }

    @Override
    public Long findMaxIdByEmpId(Long empId) {
        return employeeActivityDtlDao.findMaxIdByEmpId(empId);
    }

    @Transactional
    @Override
    public List<EmployeeActivityDtl> findbyActivityReasonCode(String activity, String reasonCd, Timestamp startDate, Timestamp endDate) {
        return employeeActivityDtlDao.findbyActivityReasonCode(activity, reasonCd, startDate, endDate);
    }

    @Override
    public EmployeeActivityDtl findByActivityId(Long id) {
        return employeeActivityDtlDao.findByActivityId(id);
    }

    @Override
    public EmployeeActivityDtl findLoginByEndTime(Timestamp endTime, Long empId) {
        return employeeActivityDtlDao.findLoginByEndTime(endTime, empId);
    }

    @Override
    public List<EmployeeActivityDtl> findListNonEndedActivityByActivity(String activity) {
        return employeeActivityDtlDao.findListNonEndedActivityByActivity(activity);
    }

    @Override
    public List<EmployeeActivityDtl> findListNonEndedActivityByActivites(String activity, String activity2) {
        return employeeActivityDtlDao.findListNonEndedActivityByActivites(activity, activity2);
    }

    @Override
    public List<EmployeeActivityDtl> findAgentEndedActivityByActivity(Timestamp startDate, Timestamp endDate, Long employeeId, Long empTypeId, String activity) {
        return employeeActivityDtlDao.findAgentEndedActivityByActivity(startDate, endDate, employeeId, empTypeId, activity);
    }

    @Override
    public List<EmployeeActivityDtl> findEndedLogoutData(Timestamp startDate, Timestamp endDate, Long referencedLoginId, Long empId) {
        return employeeActivityDtlDao.findEndedLogoutData(startDate, endDate, referencedLoginId, empId);
    }

    @Override
    public List<EmployeeActivityDtl> findAgentEndedActivityByActivity(Timestamp startDate, Timestamp endDate, List<Integer> employeeIdList, String activity) {
        return employeeActivityDtlDao.findAgentEndedActivityByActivity(startDate, endDate, employeeIdList, activity);
    }

    @Override
    public Long findMaxCallStartedIdByEmpId(Long empId) {
        return employeeActivityDtlDao.findMaxCallStartedIdByEmpId(empId);
    }

    @Override
    public List<AgentStatusDto> listCurrentAgentStatus() {
        return employeeActivityDtlDao.listCurrentAgentStatus();
    }

    @Transactional
    @Override
    public List<EmployeeActivityDtl> findAllNonEndedActivity() {
        List<EmployeeActivityDtl> activityDtlList = employeeActivityDtlDao.findAllNonEndedActivity();
        if (null == activityDtlList || activityDtlList.isEmpty()) {
            return null;
        } else {
            return activityDtlList;
        }
    }

    @Override
    public List<EmployeeActivityDto> findLastNonEndedActivityByAllType(Long empPkId, String[] activityArray) {
        return employeeActivityDtlDao.findLastNonEndedActivityByAllType(empPkId, activityArray);
    }

    @Override
    public EmployeeActivityDtl updateEndTimeByEmpId(Long empId, List<EmployeeActivityDto> employeeActivityDtlList, Timestamp endTime) {
        return employeeActivityDtlDao.updateEndTimeByEmpId(empId, employeeActivityDtlList, endTime);
    }

    @Override
    public List<EmployeeActivityDtl> findLastEmployeeActivityIdOfAgentByAgentId(Long empId) {
        return employeeActivityDtlDao.findLastEmployeeActivityIdOfAgentByAgentId(empId);
    }

}
