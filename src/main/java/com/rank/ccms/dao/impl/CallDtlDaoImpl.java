package com.rank.ccms.dao.impl;

import com.rank.ccms.dao.CallDtlDao;
import com.rank.ccms.dto.ConferenceAgentsDto;
import com.rank.ccms.entities.CallDtl;
import com.rank.ccms.entities.CallMst;
import com.rank.ccms.entities.EmployeeMst;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("callDtlDao")
@Transactional
public class CallDtlDaoImpl extends GenericDaoImpl<CallDtl> implements CallDtlDao {

    @Override
    public CallDtl findNonDeletedCallDtlById(Long id) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CallDtl.class)
                .add(Restrictions.eq("id", id))
                .add(Restrictions.eq("deleteFlg", false))
                .add(Restrictions.eq("activeFlg", true));
        return (CallDtl) findByCriteria(detachedCriteria).get(0);
    }

    @Override
    public List<CallDtl> findNonDeletedCallDtlByCallId(CallMst callId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CallDtl.class)
                .add(Restrictions.eq("callMstId", callId))
                .add(Restrictions.eq("deleteFlg", false))
                .add(Restrictions.eq("activeFlg", true));
        detachedCriteria.addOrder(Order.desc("id")); // NOTE: Please do not change the order of searching
        detachedCriteria.setFetchMode("callMstId", FetchMode.JOIN);
        return (List<CallDtl>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<CallDtl> findCallDtlByCallMasterId(Long callMasterId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CallDtl.class)
                .add(Restrictions.eq("callMstId.id", callMasterId));
        detachedCriteria.addOrder(Order.desc("id"));
        return (List<CallDtl>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<CallDtl> findCallDtlByCallMasterIdOtherThanThreeWay(Long callMasterId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CallDtl.class)
                .add(Restrictions.eq("callMstId.id", callMasterId))
                .add(Restrictions.ne("callTypeInfo", "Threeway Specialist"));
        detachedCriteria.addOrder(Order.desc("id"));
        return (List<CallDtl>) findByCriteria(detachedCriteria);
    }

    @Override
    public CallDtl findCallDtlById(Long id) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CallDtl.class)
                .add(Restrictions.eq("id", id));
        return (CallDtl) findByCriteria(detachedCriteria).get(0);
    }

    @Override
    public List<CallDtl> findAllNonDeletedCallDtls() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CallDtl.class)
                .add(Restrictions.eq("deleteFlg", false))
                .add(Restrictions.eq("activeFlg", true));
        return (List<CallDtl>) findByCriteria(detachedCriteria);
    }

    @Override
    public CallDtl findCallDtlByEmployee(Long id) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CallDtl.class)
                .add(Restrictions.eq("handeledById.id", id))
                .add(Restrictions.isNull("endTime"))
                .add(Restrictions.eq("deleteFlg", false))
                .add(Restrictions.eq("activeFlg", true));
        detachedCriteria.addOrder(Order.desc("id"));
        return (CallDtl) findByCriteria(detachedCriteria).get(0);
    }

    @Override
    public List<CallDtl> findCallDtlByEmployeeWithinTimeRange(Long id, Timestamp stTime, Timestamp endTime) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CallDtl.class)
                .add(Restrictions.eq("handeledById.id", id))
                .add(Restrictions.isNotNull("endTime"))
                .add(Restrictions.between("startTime", stTime, endTime))
                .add(Restrictions.eq("deleteFlg", false))
                .add(Restrictions.eq("activeFlg", true));
        return (List<CallDtl>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<CallDtl> findCallDtlByCallMasterIdAndCallTypeInfo(Long callMasterId, String callTypeInfo) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CallDtl.class)
                .add(Restrictions.eq("callMstId.id", callMasterId))
                .add(Restrictions.isNotNull("endTime"))
                .add(Restrictions.eq("callTypeInfo", callTypeInfo).ignoreCase())
                .add(Restrictions.eq("deleteFlg", false))
                .add(Restrictions.eq("activeFlg", true));
        return (List<CallDtl>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<CallDtl> findEndedCallDtlBetweenDate(Timestamp fromDateTime, Timestamp toDateTime, Long empPkId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CallDtl.class)
                .add(Restrictions.or(Restrictions.eq("callTypeInfo", "Normal"), Restrictions.eq("callTypeInfo", "Incoming Call")))
                .add(Restrictions.eq("handeledById.id", empPkId))
                .add(Restrictions.isNotNull("endTime"))
                .add(Restrictions.between("startTime", fromDateTime, toDateTime));

        return (List<CallDtl>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<CallDtl> AgentEndedCallDtlBetweenDate(Timestamp fromDateTime, Timestamp toDateTime) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CallDtl.class)
                .add(Restrictions.or(Restrictions.eq("callTypeInfo", "Normal"), Restrictions.eq("callTypeInfo", "Incoming Call")))
                .add(Restrictions.isNotNull("endTime"))
                .add(Restrictions.between("startTime", fromDateTime, toDateTime));
        detachedCriteria.addOrder(Order.asc("handeledById.id"));
        return (List<CallDtl>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<CallDtl> findInboundCallDtlBetweenDate(Timestamp fromDateTime, Timestamp toDateTime, Long empPkId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CallDtl.class)
                .add(Restrictions.eq("callTypeInfo", "Incoming Call"))
                .add(Restrictions.eq("handeledById.id", empPkId))
                .add(Restrictions.isNotNull("endTime"))
                .add(Restrictions.between("startTime", fromDateTime, toDateTime));

        return (List<CallDtl>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<CallDtl> findInboundCallDtlBetweenDateForService(Timestamp fromDateTime, Timestamp toDateTime, Long empPkId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CallDtl.class)
                .add(Restrictions.eq("handeledById.id", empPkId))
                .add(Restrictions.isNotNull("endTime"))
                .add(Restrictions.between("startTime", fromDateTime, toDateTime));

        return (List<CallDtl>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<CallDtl> AllInboundCallDtlBetweenDate(Timestamp fromDateTime, Timestamp toDateTime) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CallDtl.class)
                .add(Restrictions.eq("callTypeInfo", "Incoming Call"))
                .add(Restrictions.isNotNull("endTime"))
                .add(Restrictions.between("startTime", fromDateTime, toDateTime));
        detachedCriteria.addOrder(Order.asc("handeledById.id"));
        return (List<CallDtl>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<CallDtl> findOutboundCallDtlBetweenDate(Timestamp fromDateTime, Timestamp toDateTime, Long empPkId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CallDtl.class)
                .add(Restrictions.eq("callTypeInfo", "Normal"))
                .add(Restrictions.eq("handeledById.id", empPkId))
                .add(Restrictions.isNotNull("endTime"))
                .add(Restrictions.between("startTime", fromDateTime, toDateTime));

        return (List<CallDtl>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<CallDtl> AllOutboundCallDtlBetweenDate(Timestamp fromDateTime, Timestamp toDateTime) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CallDtl.class)
                .add(Restrictions.eq("callTypeInfo", "Normal"))
                .add(Restrictions.isNotNull("endTime"))
                .add(Restrictions.between("startTime", fromDateTime, toDateTime));
        detachedCriteria.addOrder(Order.asc("handeledById.id"));
        return (List<CallDtl>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<CallDtl> findNonDeletedCallDtlByCallIdAndActivity(CallMst callId, String Activity) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CallDtl.class)
                .add(Restrictions.eq("callMstId", callId))
                .add(Restrictions.eq("deleteFlg", false))
                .add(Restrictions.eq("activeFlg", true))
                .add(Restrictions.eq("callTypeInfo", Activity));
        detachedCriteria.setFetchMode("callMstId", FetchMode.JOIN);
        return (List<CallDtl>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<CallDtl> findNonDeletedCallDtlByCallIdAndActivity(CallMst callId, List<String> activityList) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CallDtl.class)
                .add(Restrictions.eq("callMstId", callId))
                .add(Restrictions.eq("deleteFlg", false))
                .add(Restrictions.eq("activeFlg", true))
                .add(Restrictions.in("callTypeInfo", activityList));
        detachedCriteria.setFetchMode("callMstId", FetchMode.JOIN);
        return (List<CallDtl>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<CallDtl> findCallDtlByCallMasterIdAndAgent(Long callMasterId, Long employeeId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CallDtl.class)
                .add(Restrictions.eq("handeledById.id", employeeId))
                .add(Restrictions.eq("callMstId.id", callMasterId))
                .add(Restrictions.isNull("endTime"))
                .add(Restrictions.eq("deleteFlg", false))
                .add(Restrictions.eq("activeFlg", true))
                .addOrder(Order.desc("id"));
        return (List<CallDtl>) findByCriteria(detachedCriteria);
    }

    @Override
    public CallDtl findCallDtlByCallMasterIdAndAgentNotForwarded(Long callMasterId, Long employeeId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CallDtl.class)
                .add(Restrictions.eq("handeledById.id", employeeId))
                .add(Restrictions.eq("callMstId.id", callMasterId))
                //.add(Restrictions.isNull("endTime"))
                //.add(Restrictions.or(Restrictions.eq("callTypeInfo", "Normal"), Restrictions.eq("callTypeInfo", "Incoming Call")))
                .add(Restrictions.eq("deleteFlg", false))
                .add(Restrictions.eq("activeFlg", true));
        detachedCriteria.addOrder(Order.desc("id"));
        List<CallDtl> callDtl = (List<CallDtl>) findByCriteria(detachedCriteria);
        if (!callDtl.isEmpty()) {
            return callDtl.get(0);
        } else {
            return null;
        }

    }

    @Override
    public List<CallDtl> findForwardedCallDtlBetweenDate(Timestamp fromDateTime, Timestamp toDateTime, Long empPkId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CallDtl.class)
                .add(Restrictions.eq("callTypeInfo", "Forward"))
                .add(Restrictions.eq("handeledById.id", empPkId))
                .add(Restrictions.isNotNull("endTime"))
                .add(Restrictions.between("startTime", fromDateTime, toDateTime));

        return (List<CallDtl>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<CallDtl> AllForwardedCallDtlBetweenDate(Timestamp fromDateTime, Timestamp toDateTime) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CallDtl.class)
                .add(Restrictions.eq("callTypeInfo", "Forward"))
                .add(Restrictions.isNotNull("endTime"));
        if (fromDateTime != null && toDateTime != null) {
            detachedCriteria.add(Restrictions.between("startTime", fromDateTime, toDateTime));
        }
        detachedCriteria.addOrder(Order.asc("handeledById.id"));
        return (List<CallDtl>) findByCriteria(detachedCriteria);
    }

    @Override
    public CallDtl findCallDtlByCallMstIdForSchduledCall(Long callMstId) {

        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CallDtl.class)
                .add(Restrictions.eq("callMstId.id", callMstId))
                .add(Restrictions.eq("deleteFlg", false))
                .add(Restrictions.eq("activeFlg", true));

        if (!(findByCriteria(detachedCriteria).isEmpty())) {
            return (CallDtl) findByCriteria(detachedCriteria).get(0);
        } else {
            return null;
        }

    }

    @Override
    public List<CallDtl> findCallDtlByCallMstIdAndTimeRange(Timestamp fromTime, Timestamp toTime, Long callId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CallDtl.class)
                .add(Restrictions.eq("callMstId.id", callId))
                .add(Restrictions.isNotNull("endTime"))
                .add(Restrictions.between("startTime", fromTime, toTime))
                .add(Restrictions.eq("deleteFlg", false))
                .add(Restrictions.eq("activeFlg", true));
        detachedCriteria.addOrder(Order.asc("startTime"));
        return (List<CallDtl>) findByCriteria(detachedCriteria);
    }

    @Transactional
    @Override
    public List<CallDtl> findLastNonEndedCallByEmpId(EmployeeMst employeeMst) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CallDtl.class)
                .add(Restrictions.eq("handeledById", employeeMst))
                .add(Restrictions.isNull("endTime"));
        detachedCriteria.addOrder(Order.desc("startTime"));
        return (ArrayList<CallDtl>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<CallDtl> findNonEndedCallDtlByCallMasterId(long callMstId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CallDtl.class)
                .add(Restrictions.eq("callMstId.id", callMstId))
                .add(Restrictions.isNull("endTime"));
        return (List<CallDtl>) findByCriteria(detachedCriteria);
    }

    @Override
    public CallDtl findCurrentCallAgent(Long callId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CallDtl.class)
                .add(Restrictions.eq("callMstId.id", callId))
                .add(Restrictions.ne("callTypeInfo", "Threeway Specialist").ignoreCase())
                .addOrder(Order.desc("id"));
        if (!(findByCriteria(detachedCriteria).isEmpty())) {
            return (CallDtl) findByCriteria(detachedCriteria).get(0);
        } else {
            return null;
        }

    }

    @Override
    public List<ConferenceAgentsDto> findParticipantCallDtlsByCallMstId(CallMst callMst) {
        // List<ConferenceAgentsDto> conferenceAgentsDtoList = null;
        try {
            DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CallDtl.class, "CD")
                    .createAlias("CD.handeledById", "EM")
                    .createAlias("EM.empTypId", "ETM")
                    .add(Restrictions.eq("callMstId.id", callMst.getId()))
                    .add(Restrictions.eq("activeFlg", true))
                    .add(Restrictions.isNull("endTime"))
                    .addOrder(Order.desc("id"));

            ProjectionList projections = Projections.projectionList();
            detachedCriteria.setProjection(projections.add(Projections.property("EM.id"), "empId"))
                    .setProjection(projections.add(Projections.property("EM.firstName"), "empFirstName"))
                    .setProjection(projections.add(Projections.property("EM.midName"), "empMidName"))
                    .setProjection(projections.add(Projections.property("EM.lastName"), "empLastName"))
                    .setProjection(projections.add(Projections.property("EM.loginId"), "empLoginId"))
                    .setProjection(projections.add(Projections.property("CD.id"), "callDtlsId"))
                    .setProjection(projections.add(Projections.property("ETM.id"), "empTypeId"))
                    .setProjection(projections.add(Projections.property("ETM.typeName"), "empTypeName"));

            detachedCriteria.setResultTransformer(Transformers.aliasToBean(ConferenceAgentsDto.class));

            return (List<ConferenceAgentsDto>) findByCriteria(detachedCriteria);
        } catch (Exception e) {
            logger.error("Error : ", e);
            return null;
        }
    }

    @Override
    public List<CallDtl> findNonEndedCallDtlByCallMasterIdAndExcludeCurrentEmployee(Long id, Long empId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CallDtl.class)
                .add(Restrictions.eq("callMstId.id", id))
                .add(Restrictions.ne("handeledById.id", empId))
                .add(Restrictions.isNull("endTime"));
        return (List<CallDtl>) findByCriteria(detachedCriteria);
    }

    @Override
    public CallDtl findNonEndedCallDtlByCallMasterIdAndEmployeeId(Long callId, Long empId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CallDtl.class, "cd")
                .createAlias("cd.handeledById", "em")
                .createAlias("cd.callMstId", "cm")
                .add(Restrictions.eq("cm.id", callId))
                .add(Restrictions.ne("em.id", empId))
                .add(Restrictions.isNull("cd.endTime"))
                .add(Restrictions.isNull("cm.endTime"));
        if ((findByCriteria(detachedCriteria) != null && !findByCriteria(detachedCriteria).isEmpty())) {
            return (CallDtl) findByCriteria(detachedCriteria).get(0);
        } else {
            return null;
        }
    }
}
