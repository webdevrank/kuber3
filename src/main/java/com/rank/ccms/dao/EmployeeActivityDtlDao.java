package com.rank.ccms.dao;

import com.rank.ccms.dto.AgentStatusDto;
import com.rank.ccms.dto.EmployeeActivityDto;
import com.rank.ccms.entities.EmployeeActivityDtl;
import java.sql.Timestamp;
import java.util.List;

public interface EmployeeActivityDtlDao extends GenericDao<EmployeeActivityDtl> {

    public List<EmployeeActivityDtl> findLastNonEndedActivityByType(Long empPkId, String activity);

    public List<EmployeeActivityDtl> findListNonEndedActivityByActivity(String activity);

    public List<EmployeeActivityDtl> findListNonEndedActivityByActivites(String activity, String activity2);

    public Long findMaxLoginIdByEmpId(Long empId);

    public Long findMaxIdByEmpId(Long empId);

    public Long findMaxCallStartedIdByEmpId(Long empId);

    public List<EmployeeActivityDtl> findAgentLogggedIn(Long empPkId, String activity);

    public List<EmployeeActivityDtl> findAllEndedActivityBetweenDate(Timestamp fromDateTime, Timestamp toDateTime, String activity, Long empPkId);

    public List<EmployeeActivityDtl> findStatusByAgent(Long empPkId);

    public List<EmployeeActivityDtl> findStatusByAgentTypeTimeRange(Long empPkId, String activity, Timestamp startTime, Timestamp endTime);

    public List<EmployeeActivityDtl> findAllLoggedInEmployeeList();

    public List<EmployeeActivityDtl> allAgentEndedActivityBetweenDate(Timestamp fromDateTime, Timestamp toDateTime, String activity);

    public List<EmployeeActivityDtl> findbyActivityReasonCode(String activity, String reasonCd, Timestamp startDate, Timestamp endDate);

    public EmployeeActivityDtl findByActivityId(Long id);

    public EmployeeActivityDtl findLoginByEndTime(Timestamp endTime, Long empId);

    public List<EmployeeActivityDtl> findAgentEndedActivityByActivity(Timestamp startDate, Timestamp endDate, Long employeeId, Long empTypeId, String activity);

    public List<EmployeeActivityDtl> findEndedLogoutData(Timestamp startDate, Timestamp endDate, Long referencedLoginId, Long empId);

    public List<EmployeeActivityDtl> findAgentEndedActivityByActivity(Timestamp startDate, Timestamp endDate, List<Integer> employeeIdList, String activity);

    public List<AgentStatusDto> listCurrentAgentStatus();

    public List<EmployeeActivityDtl> findAllNonEndedActivity();

    public List<EmployeeActivityDto> findLastNonEndedActivityByAllType(Long empPkId, String[] activityArray);

    public EmployeeActivityDtl updateEndTimeByEmpId(Long id, List<EmployeeActivityDto> employeeActivityDtlList, Timestamp endTime);

    public List<EmployeeActivityDtl> findLastEmployeeActivityIdOfAgentByAgentId(Long empId);

}
