package com.rank.ccms.service;

import com.rank.ccms.dto.AgentStatusDto;
import com.rank.ccms.dto.EmployeeActivityDto;
import com.rank.ccms.entities.EmployeeActivityDtl;
import com.rank.ccms.entities.EmployeeMst;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public interface EmployeeActivityDtlService extends Serializable {

    EmployeeActivityDtl save(EmployeeActivityDtl employeeActivityDtl, EmployeeMst employeeMst);

    EmployeeActivityDtl findLastNonEndedActivityByType(Long empPkId, String activity);

    List<EmployeeActivityDtl> findListNonEndedActivityByType(Long empPkId, String activity);

    EmployeeActivityDtl save(EmployeeActivityDtl employeeActivityDtl);

    List<EmployeeActivityDtl> findAllActivityDtl();

    EmployeeActivityDtl findAgentStatus(Long empPkId, String activity);

    List<EmployeeActivityDtl> findStatusByAgent(Long empPkId);

    List<EmployeeActivityDtl> findAllEndedActivityBetweenDate(Timestamp fromDateTime, Timestamp toDateTime, String activity, Long empPkId);

    List<EmployeeActivityDtl> allAgentEndedActivityBetweenDate(Timestamp fromDateTime, Timestamp toDateTime, String activity);

    List<EmployeeActivityDtl> findStatusByAgentTypeTimeRange(Long empPkId, String activity, Timestamp startTime, Timestamp endTime);

    List<EmployeeActivityDtl> findAllLoggedInEmployeeList();

    Long findMaxLoginIdByEmpId(Long empId);

    Long findMaxIdByEmpId(Long empId);

    Long findMaxCallStartedIdByEmpId(Long empId);

    List<EmployeeActivityDtl> findbyActivityReasonCode(String activity, String reasonCd, Timestamp startDate, Timestamp endDate);

    EmployeeActivityDtl findByActivityId(Long id);

    EmployeeActivityDtl findLoginByEndTime(Timestamp endTime, Long empId);

    List<EmployeeActivityDtl> findListNonEndedActivityByActivity(String activity);

    List<EmployeeActivityDtl> findListNonEndedActivityByActivites(String activity, String activity2);

    List<EmployeeActivityDtl> findAgentEndedActivityByActivity(Timestamp startDate, Timestamp endDate, Long employeeId, Long empTypeId, String activity);

    List<EmployeeActivityDtl> findEndedLogoutData(Timestamp startDate, Timestamp endDate, Long referencedLoginId, Long empId);

    List<EmployeeActivityDtl> findAgentEndedActivityByActivity(Timestamp startDate, Timestamp endDate, List<Integer> employeeIdList, String activity);

    List<AgentStatusDto> listCurrentAgentStatus();

    List<EmployeeActivityDtl> findAllNonEndedActivity();

    List<EmployeeActivityDto> findLastNonEndedActivityByAllType(Long empPkId, String[] activityArray);

    EmployeeActivityDtl updateEndTimeByEmpId(Long id, List<EmployeeActivityDto> employeeActivityDtlList, Timestamp endTime);

    List<EmployeeActivityDtl> findLastEmployeeActivityIdOfAgentByAgentId(Long empId);

}
