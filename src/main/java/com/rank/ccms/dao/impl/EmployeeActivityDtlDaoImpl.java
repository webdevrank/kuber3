package com.rank.ccms.dao.impl;

import com.rank.ccms.dao.EmployeeActivityDtlDao;
import com.rank.ccms.dto.AgentStatusDto;
import com.rank.ccms.dto.EmployeeActivityDto;
import com.rank.ccms.entities.EmployeeActivityDtl;
import com.rank.ccms.entities.EmployeeMst;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("employeeActivityDtlDao")
@Transactional
public class EmployeeActivityDtlDaoImpl extends GenericDaoImpl<EmployeeActivityDtl> implements EmployeeActivityDtlDao {

    @Transactional
    @Override
    public List<EmployeeActivityDtl> findLastNonEndedActivityByType(Long empPkId, String activity) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeActivityDtl.class)
                .add(Restrictions.eq("empId.id", empPkId))
                .add(Restrictions.eq("activity", activity.toLowerCase()).ignoreCase())
                .add(Restrictions.isNull("endTime"));
        detachedCriteria.addOrder(Order.desc("id"));
        return (ArrayList<EmployeeActivityDtl>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<EmployeeActivityDtl> findAgentLogggedIn(Long empPkId, String activity) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeActivityDtl.class)
                .add(Restrictions.eq("empId.id", empPkId))
                .add(Restrictions.eq("activity", activity.toLowerCase()).ignoreCase());

        detachedCriteria.addOrder(Order.desc("startTime"));
        return (ArrayList<EmployeeActivityDtl>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<EmployeeActivityDtl> findStatusByAgent(Long empPkId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeActivityDtl.class)
                .add(Restrictions.eq("empId.id", empPkId));
        detachedCriteria.addOrder(Order.desc("startTime"));
        return (ArrayList<EmployeeActivityDtl>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<EmployeeActivityDtl> findStatusByAgentTypeTimeRange(Long empPkId, String activity, Timestamp startTime, Timestamp endTime) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeActivityDtl.class)
                .add(Restrictions.eq("empId.id", empPkId))
                .add(Restrictions.between("startTime", startTime, endTime))
                .add(Restrictions.eq("activity", activity.toLowerCase()).ignoreCase());
        detachedCriteria.addOrder(Order.desc("startTime"));
        return (ArrayList<EmployeeActivityDtl>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<EmployeeActivityDtl> findAllEndedActivityBetweenDate(Timestamp fromDateTime, Timestamp toDateTime, String activity, Long empPkId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeActivityDtl.class)
                .add(Restrictions.eq("activity", activity.toLowerCase()).ignoreCase())
                .add(Restrictions.eq("empId.id", empPkId))
                .add(Restrictions.isNotNull("endTime"))
                .add(Restrictions.between("startTime", fromDateTime, toDateTime));
        return (List<EmployeeActivityDtl>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<EmployeeActivityDtl> findAllLoggedInEmployeeList() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeActivityDtl.class)
                .add(Restrictions.eq("activity", "login").ignoreCase())
                .add(Restrictions.isNull("endTime"));

        detachedCriteria.addOrder(Order.desc("startTime"));
        return (List<EmployeeActivityDtl>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<EmployeeActivityDtl> allAgentEndedActivityBetweenDate(Timestamp fromDateTime, Timestamp toDateTime, String activity) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeActivityDtl.class)
                .add(Restrictions.eq("activity", activity.toLowerCase()).ignoreCase())
                .add(Restrictions.isNotNull("endTime"))
                .add(Restrictions.between("startTime", fromDateTime, toDateTime));
        detachedCriteria.addOrder(Order.asc("empId.id"));
        return (List<EmployeeActivityDtl>) findByCriteria(detachedCriteria);
    }

    @Override
    public Long findMaxLoginIdByEmpId(Long empId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeActivityDtl.class)
                .setProjection(Projections.max("id"))
                .add(Restrictions.eq("activity", "login").ignoreCase())
                .add(Restrictions.eq("empId.id", empId));

        return (Long) findByCriteria(detachedCriteria).get(0);
    }

    @Override
    public Long findMaxIdByEmpId(Long empId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeActivityDtl.class)
                .setProjection(Projections.max("id"))
                .add(Restrictions.eq("empId.id", empId));

        return (Long) findByCriteria(detachedCriteria).get(0);
    }

    @Transactional
    @Override
    public List<EmployeeActivityDtl> findbyActivityReasonCode(String activity, String reasonCd, Timestamp startDate, Timestamp endDate) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeActivityDtl.class)
                .add(Restrictions.eq("activity", activity.toLowerCase()).ignoreCase())
                .add(Restrictions.eq("reasonCd", reasonCd));
        if (startDate == null && endDate == null) {
        } else if (startDate != null && endDate == null) {
        } else if (startDate == null && endDate != null) {
        } else {
            detachedCriteria.add(Restrictions.between("startTime", startDate, endDate));
        }
        detachedCriteria.addOrder(Order.asc("startTime"));
        return (List<EmployeeActivityDtl>) findByCriteria(detachedCriteria);
    }

    @Override
    public EmployeeActivityDtl findByActivityId(Long id) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeActivityDtl.class)
                .add(Restrictions.eq("id", id));
        List<EmployeeActivityDtl> empActivityDtlList = (List<EmployeeActivityDtl>) findByCriteria(detachedCriteria);
        if (empActivityDtlList == null || empActivityDtlList.isEmpty()) {
            return null;
        }
        return (EmployeeActivityDtl) empActivityDtlList.get(0);
    }

    @Override
    public EmployeeActivityDtl findLoginByEndTime(Timestamp endTime, Long empId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeActivityDtl.class)
                .add(Restrictions.eq("activity", "login").ignoreCase())
                .add(Restrictions.lt("endTime", endTime))
                .add(Restrictions.eq("empId.id", empId));

        detachedCriteria.addOrder(Order.desc("id"));
        List<EmployeeActivityDtl> listEmpActDtl = (List<EmployeeActivityDtl>) findByCriteria(detachedCriteria);
        if (!listEmpActDtl.isEmpty()) {
            return listEmpActDtl.get(0);
        } else {
            return null;
        }

    }

    @Override
    public List<EmployeeActivityDtl> findListNonEndedActivityByActivity(String activity) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeActivityDtl.class)
                .add(Restrictions.eq("activity", activity.toLowerCase()).ignoreCase())
                .add(Restrictions.isNull("endTime"));
        detachedCriteria.addOrder(Order.desc("startTime"));
        return (ArrayList<EmployeeActivityDtl>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<EmployeeActivityDtl> findListNonEndedActivityByActivites(String activity, String activity2) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeActivityDtl.class)
                .add(Restrictions.or(Restrictions.eq("activity", activity.toLowerCase()).ignoreCase(), Restrictions.eq("activity", activity2.toLowerCase()).ignoreCase()))
                .add(Restrictions.isNull("endTime"));
        detachedCriteria.addOrder(Order.desc("startTime"));
        return (ArrayList<EmployeeActivityDtl>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<EmployeeActivityDtl> findAgentEndedActivityByActivity(Timestamp startDate, Timestamp endDate, Long employeeId, Long empTypeId, String activity) {

        DetachedCriteria criteriaEmployeeMst = DetachedCriteria.forClass(EmployeeMst.class, "emp")
                .add(Restrictions.eq("emp.empTypId.id", empTypeId));
        criteriaEmployeeMst.setProjection(Property.forName("id"));
        if (employeeId == null || employeeId == (long) 0) {
        } else {
            criteriaEmployeeMst.add(Restrictions.eq("emp.id", employeeId));
        }
        Criteria criteriaActivityDtl = getSessionFactory().getCurrentSession()
                .createCriteria(EmployeeActivityDtl.class, "employeeActivityDtl")
                .add(Subqueries.propertyIn("employeeActivityDtl.empId.id", criteriaEmployeeMst))
                .add(Restrictions.isNotNull("employeeActivityDtl.endTime"))
                .add(Restrictions.eq("employeeActivityDtl.activity", activity.toLowerCase()).ignoreCase())
                .addOrder(Order.asc("employeeActivityDtl.empId.id"))
                .addOrder(Order.asc("employeeActivityDtl.id"))
                .addOrder(Order.asc("employeeActivityDtl.startTime"));
        if (activity.equalsIgnoreCase("logout")) {
            criteriaActivityDtl.add(Restrictions.isNotNull("employeeActivityDtl.respectiveLoginId"));
        } else {
        }
        if (startDate == null && endDate == null) {
        } else if (startDate != null && endDate == null) {
        } else if (startDate == null && endDate != null) {
        } else {
            criteriaActivityDtl.add(Restrictions.between("employeeActivityDtl.startTime", startDate, endDate));
        }
        ArrayList<EmployeeActivityDtl> employeeActivityDtlList = (ArrayList<EmployeeActivityDtl>) criteriaActivityDtl.list();
        return employeeActivityDtlList;
    }

    @Override
    public List<EmployeeActivityDtl> findEndedLogoutData(Timestamp startDate, Timestamp endDate, Long referencedLoginId, Long empId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeActivityDtl.class)
                .add(Restrictions.eq("activity", "logout").ignoreCase())
                .add(Restrictions.eq("respectiveLoginId", referencedLoginId))
                .add(Restrictions.eq("empId.id", empId))
                .add(Restrictions.isNotNull("endTime"));
        if (startDate == null && endDate == null) {
        } else if (startDate != null && endDate == null) {
        } else if (startDate == null && endDate != null) {
        } else {
            detachedCriteria.add(Restrictions.between("startTime", startDate, endDate));
        }
        detachedCriteria.addOrder(Order.desc("startTime")).addOrder(Order.desc("id"));
        return (ArrayList<EmployeeActivityDtl>) findByCriteria(detachedCriteria);

    }

    @Override
    public List<EmployeeActivityDtl> findAgentEndedActivityByActivity(Timestamp startDate, Timestamp endDate, List<Integer> employeeIdList, String activity) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeActivityDtl.class, "employeeActivityDtl");
        detachedCriteria.add(Restrictions.in("employeeActivityDtl.empId", employeeIdList))
                .add(Restrictions.isNotNull("employeeActivityDtl.endTime"))
                .add(Restrictions.eq("employeeActivityDtl.activity", activity.toLowerCase()).ignoreCase())
                .addOrder(Order.asc("employeeActivityDtl.empId.id"))
                .addOrder(Order.asc("employeeActivityDtl.id"))
                .addOrder(Order.asc("employeeActivityDtl.startTime"));
        if (activity.equalsIgnoreCase("logout")) {
            detachedCriteria.add(Restrictions.isNotNull("employeeActivityDtl.respectiveLoginId"));
        } else {
        }
        if (startDate == null && endDate == null) {
        } else if (startDate != null && endDate == null) {
        } else if (startDate == null && endDate != null) {
        } else {
            detachedCriteria.add(Restrictions.between("employeeActivityDtl.startTime", startDate, endDate));
        }
        return (ArrayList<EmployeeActivityDtl>) findByCriteria(detachedCriteria);
    }

    @Override
    public Long findMaxCallStartedIdByEmpId(Long empId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeActivityDtl.class)
                .setProjection(Projections.max("id"))
                .add(Restrictions.eq("activity", "Call Started"))
                .add(Restrictions.eq("empId.id", empId));
        return (Long) findByCriteria(detachedCriteria).get(0);
    }

    @Override
    public List<AgentStatusDto> listCurrentAgentStatus() {

        org.hibernate.Query query = getSessionFactory().getCurrentSession().
                createSQLQuery("select\n"
                        + "                employee_mst.id employeeID,employee_mst.login_id as loginId ,\n"
                        + "                case\n"
                        + "                 when mid_name is null or mid_name=''  then\n"
                        + "                first_name||' '||last_name\n"
                        + "                 else\n"
                        + "                first_name||' '||mid_name||' '||last_name\n"
                        + "                end agentFullName,\n"
                        + "                case \n"
                        + "                when upper(employee_activity_dtl.activity)=upper('login') then\n"
                        + "                    'Agent is logged in on the dashboard'\n"
                        + "                when upper(employee_activity_dtl.activity)=upper('Self View') then\n"
                        + "                    'Agent is self viewing on the dashboard' \n"
                        + "                when upper(employee_activity_dtl.activity)=upper('NEXT CALL') then\n"
                        + "                    'Agent is available now and waiting for call to receive' \n"
                        + "                when upper(employee_activity_dtl.activity)=upper('Call Started') then\n"
                        + "                    'Agent is on a call'\n"
                        + "                when upper(employee_activity_dtl.activity)=upper('HOLD CALL') then\n"
                        + "                    'Agent has put on hold on an ongoing call'\n"
                        + "                when upper(employee_activity_dtl.activity)=upper('not ready') and upper(employee_activity_dtl.reason_cd)=upper('NRDFT') then\n"
                        + "                    'Agent is not ready'\n"
                        + "                when upper(employee_activity_dtl.activity)=upper('not ready') and upper(employee_activity_dtl.reason_cd)<>upper('NRDFT') then\n"
                        + "                    'Agent has make himself or herself not ready ('|| employee_activity_dtl.reason_desc ||')'\n"
                        + "                when upper(employee_activity_dtl.activity)=upper('Hang Up') then\n"
                        + "                    'On going call ended and is on not ready state'\n"
                        + "                when upper(employee_activity_dtl.activity)=upper('Call forwarded') then\n"
                        + "                    'On going call forwarded and is on not ready state'\n"
                        + "                  end statusAgent,\n"
                        + "                  \n"
                        + "              case \n"
                        + "                when upper(employee_activity_dtl.activity)=upper('login') then\n"
                        + "                    0\n"
                        + "                when upper(employee_activity_dtl.activity)=upper('Self View') then\n"
                        + "                    0\n"
                        + "                when upper(employee_activity_dtl.activity)=upper('NEXT CALL') then\n"
                        + "                    1 \n"
                        + "                when upper(employee_activity_dtl.activity)=upper('Call Started') then\n"
                        + "                    1\n"
                        + "                when upper(employee_activity_dtl.activity)=upper('HOLD CALL') then\n"
                        + "                    1\n"
                        + "                when upper(employee_activity_dtl.activity)=upper('not ready') then\n"
                        + "                    0\n"
                        + "                when upper(employee_activity_dtl.activity)=upper('Hang Up') then\n"
                        + "                    0\n"
                        + "                when upper(employee_activity_dtl.activity)=upper('Call forwarded') then\n"
                        + "                    0\n"
                        + "                  end statusReady,\n"
                        + "                  \n"
                        + "                case \n"
                        + "                when upper(employee_activity_dtl.activity)=upper('login') then\n"
                        + "                    0\n"
                        + "                when upper(employee_activity_dtl.activity)=upper('Self View') then\n"
                        + "                    0\n"
                        + "                when upper(employee_activity_dtl.activity)=upper('NEXT CALL') then\n"
                        + "                    1 \n"
                        + "                when upper(employee_activity_dtl.activity)=upper('Call Started') then\n"
                        + "                    0\n"
                        + "                when upper(employee_activity_dtl.activity)=upper('HOLD CALL') then\n"
                        + "                    0\n"
                        + "                when upper(employee_activity_dtl.activity)=upper('not ready') then\n"
                        + "                    0\n"
                        + "                when upper(employee_activity_dtl.activity)=upper('Hang Up') then\n"
                        + "                    0\n"
                        + "                when upper(employee_activity_dtl.activity)=upper('Call forwarded') then\n"
                        + "                    0\n"
                        + "                  end statusAvailable,\n"
                        + "                  \n"
                        + "               case \n"
                        + "                when upper(employee_activity_dtl.activity)=upper('login') then\n"
                        + "                    0\n"
                        + "                when upper(employee_activity_dtl.activity)=upper('Self View') then\n"
                        + "                    0\n"
                        + "                when upper(employee_activity_dtl.activity)=upper('NEXT CALL') then\n"
                        + "                    0\n"
                        + "                when upper(employee_activity_dtl.activity)=upper('Call Started') then\n"
                        + "                    1\n"
                        + "                when upper(employee_activity_dtl.activity)=upper('HOLD CALL') then\n"
                        + "                    1\n"
                        + "                when upper(employee_activity_dtl.activity)=upper('not ready') then\n"
                        + "                    0\n"
                        + "                when upper(employee_activity_dtl.activity)=upper('Hang Up') then\n"
                        + "                    0\n"
                        + "                when upper(employee_activity_dtl.activity)=upper('Call forwarded') then\n"
                        + "                    0\n"
                        + "                  end statusBusy,\n"
                        + "                 employee_activity_dtl.start_time startTime\n"
                        + "                from employee_mst,employee_activity_dtl\n"
                        + "                where employee_mst.id in (select distinct(emp_id) from TENANCY_EMPLOYEE_MAP) and \n"
                        + "                employee_activity_dtl.emp_id=employee_mst.id and \n"
                        + "                employee_activity_dtl.end_time is null and employee_activity_dtl.id in (select max(id) from employee_activity_dtl where end_time is null group by emp_id)\n"
                        + "                order by employee_mst.id,employee_activity_dtl.id")
                .setResultTransformer(Transformers.aliasToBean(AgentStatusDto.class));
        List<AgentStatusDto> results = query.list();
        return results;

    }

    @Override
    public List<EmployeeActivityDtl> findAllNonEndedActivity() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeActivityDtl.class)
                .add(Restrictions.isNull("endTime"));
        detachedCriteria.addOrder(Order.desc("startTime"));
        return (ArrayList<EmployeeActivityDtl>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<EmployeeActivityDto> findLastNonEndedActivityByAllType(Long empPkId, String[] activityArray) {

        List<EmployeeActivityDto> employeeActivityList;

        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeActivityDtl.class, "EMPACTIVITY")
                .add(Restrictions.eq("EMPACTIVITY.empId.id", empPkId))
                .add(Restrictions.in("EMPACTIVITY.activity", activityArray))
                .add(Restrictions.isNull("EMPACTIVITY.endTime"));
        detachedCriteria.addOrder(Order.desc("EMPACTIVITY.id"));

        ProjectionList projectTionlist = Projections.projectionList();

        projectTionlist.add(Projections.property("EMPACTIVITY.endTime"), "activityEndTime")
                .add(Projections.property("EMPACTIVITY.id"), "activityId");
        detachedCriteria.setProjection(projectTionlist);
        detachedCriteria.setResultTransformer(Transformers.aliasToBean(EmployeeActivityDto.class));

        employeeActivityList = (List<EmployeeActivityDto>) findByCriteria(detachedCriteria);

        return employeeActivityList;
    }

    @Override
    public EmployeeActivityDtl updateEndTimeByEmpId(Long empId, List<EmployeeActivityDto> employeeActivityDtlList,
            Timestamp endTime) {

        Session session;
        try {
            session = getSessionFactory().getCurrentSession();
            for (EmployeeActivityDto employeeActivity : employeeActivityDtlList) {

                Query query = session.createQuery(""
                        + "UPDATE "
                        + "EmployeeActivityDtl "
                        + "SET endTime= :endTime "
                        + "WHERE "
                        + "id = :id");

                query.setParameter("endTime", endTime)
                        .setParameter("id", employeeActivity.getActivityId());
                query.executeUpdate();

            }

        } catch (Exception e) {
            logger.info("updateEndTimeByEmpId erreor :" + e);
        }
        return null;
    }

    @Override
    public List<EmployeeActivityDtl> findLastEmployeeActivityIdOfAgentByAgentId(Long empId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeActivityDtl.class)
                .add(Restrictions.eq("empId.id", empId));

        detachedCriteria.addOrder(Order.desc("id"));
        return (ArrayList<EmployeeActivityDtl>) findByCriteria(detachedCriteria);
    }

}
