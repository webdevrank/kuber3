package com.rank.ccms.dao.impl;

import com.rank.ccms.dao.ScheduleCallDao;
import com.rank.ccms.dto.ScheduleCallDto;
import com.rank.ccms.entities.CustomerMst;
import com.rank.ccms.entities.ScheduleCall;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("scheduleCallDao")
@Transactional
public class ScheduleCallDaoImpl extends GenericDaoImpl<ScheduleCall> implements ScheduleCallDao {

    private final Logger log = Logger.getLogger(ScheduleCallDaoImpl.class);

    @Transactional(readOnly = true)
    @Override
    public List<ScheduleCall> findAllNonTakenScheduleCalls() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ScheduleCall.class)
                .add(Restrictions.eq("executeStatus", "Scheduled"));
        return (List<ScheduleCall>) findByCriteria(detachedCriteria);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ScheduleCall> findAllNonTakenScheduleCallForGivenTimeRange(Timestamp fromDateTime, Timestamp toDateTime) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ScheduleCall.class)
                .add(Restrictions.eq("executeStatus", "Scheduled"))
                .add(Restrictions.between("scheduleDate", fromDateTime, toDateTime));
        return (List<ScheduleCall>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<ScheduleCall> findAllNonTakenScheduleCallForGivenTime(Timestamp currDateTime) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ScheduleCall.class)
                .add(Restrictions.eq("executeStatus", "Scheduled"))
                .add(Restrictions.eq("scheduleDate", currDateTime));
        return (List<ScheduleCall>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<ScheduleCall> findAllNonTakenScheduleCallMissedByGivenCustomer(Long customerId, Timestamp currDateTime) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ScheduleCall.class)
                .add(Restrictions.eq("executeStatus", "Scheduled"))
                .add(Restrictions.eq("customerId", customerId))
                .add(Restrictions.le("scheduleDate", currDateTime));
        return (List<ScheduleCall>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<ScheduleCall> findAllNonTakenScheduleCallByGivenCustomer(Long customerId, Timestamp currDateTime) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ScheduleCall.class)
                .add(Restrictions.eq("executeStatus", "Scheduled"))
                .add(Restrictions.eq("customerId", customerId))
                .add(Restrictions.gt("scheduleDate", currDateTime));
        return (List<ScheduleCall>) findByCriteria(detachedCriteria);
    }

    @Override
    public ScheduleCall findNonTakenById(Long id) {
        List<ScheduleCall> schList = null;
        ScheduleCall sch = null;
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ScheduleCall.class)
                .add(Restrictions.eq("executeStatus", "Scheduled"))
                .add(Restrictions.eq("id", id));
        schList = (List<ScheduleCall>) findByCriteria(detachedCriteria);
        if (schList != null && schList.size() > 0) {
            sch = schList.get(0);
        }
        return sch;
    }

    @Override
    public List<ScheduleCall> findAllScheduledCallByCustomerId(Long customerId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ScheduleCall.class)
                .add(Restrictions.eq("executeStatus", "Scheduled"))
                .add(Restrictions.eq("customerId", customerId));
        return (List<ScheduleCall>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<ScheduleCall> findAllNonTakenScheduleCallsByDate(Timestamp fromDateTime) {
        Disjunction myQueryDisjunc = Restrictions.disjunction();
        myQueryDisjunc.add(Restrictions.eq("executeStatus", "Scheduled"));
        myQueryDisjunc.add(Restrictions.eq("executeStatus", "Request"));
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ScheduleCall.class)
                .add(Restrictions.gt("scheduleDate", fromDateTime))
                .add(myQueryDisjunc);
        detachedCriteria.addOrder(Order.asc("scheduleDate"));
        return (List<ScheduleCall>) findByCriteria(detachedCriteria);

    }

    @Override
    public List<ScheduleCall> findAllTakenScheduleCallForGivenTimeRange(Timestamp fromDateTime, Timestamp toDateTime) {
        Disjunction myQueryDisjunc = Restrictions.disjunction();
        myQueryDisjunc.add(Restrictions.eq("executeStatus", "Refuse"));
        myQueryDisjunc.add(Restrictions.eq("executeStatus", "Reject"));
        myQueryDisjunc.add(Restrictions.eq("executeStatus", "Executed"));
        myQueryDisjunc.add(Restrictions.eq("executeStatus", "Completed"));
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ScheduleCall.class)
                .add(myQueryDisjunc);
        if (fromDateTime != null && toDateTime != null) {
            detachedCriteria.add(Restrictions.between("scheduleDate", fromDateTime, toDateTime));
        }
        return (List<ScheduleCall>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<ScheduleCall> findAllTakenScheduleCallByGivenTimeRangeAndCustId(Timestamp fromDateTime, Timestamp toDateTime, CustomerMst custId) {
        Disjunction myQueryDisjunc = Restrictions.disjunction();
        myQueryDisjunc.add(Restrictions.eq("executeStatus", "Refuse"));
        myQueryDisjunc.add(Restrictions.eq("executeStatus", "Executed"));
        myQueryDisjunc.add(Restrictions.eq("executeStatus", "Completed"));
        myQueryDisjunc.add(Restrictions.eq("executeStatus", "Reject"));
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ScheduleCall.class)
                .add(Restrictions.eq("customerId", custId))
                .add(myQueryDisjunc);
        if (fromDateTime != null && toDateTime != null) {
            detachedCriteria.add(Restrictions.between("scheduleDate", fromDateTime, toDateTime));
        }
        return (List<ScheduleCall>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<ScheduleCall> findAllNonTakenScheduleCallsByDateRange(Timestamp fromDateTime, Timestamp toDateTime) {
        Disjunction myQueryDisjunc = Restrictions.disjunction();
        myQueryDisjunc.add(Restrictions.eq("executeStatus", "Scheduled"));
        myQueryDisjunc.add(Restrictions.eq("executeStatus", "Request"));

        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ScheduleCall.class)
                .add(Restrictions.between("scheduleDate", fromDateTime, toDateTime))
                .add(myQueryDisjunc);
        detachedCriteria.addOrder(Order.asc("scheduleDate"));
        return (List<ScheduleCall>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<ScheduleCall> findScheduleCallByCustomerIdCallMstId(Long callMstId, Long customerPkId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ScheduleCall.class)
                .add(Restrictions.eq("callmstid", callMstId))
                .add(Restrictions.eq("customerId.id", customerPkId))
                .add(Restrictions.eq("activeFlg", true))
                .add(Restrictions.eq("deleteFlg", false));
        detachedCriteria.addOrder(Order.desc("scheduleDate"));
        return (List<ScheduleCall>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<ScheduleCall> findAllScheduleCalls() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ScheduleCall.class);
        return (List<ScheduleCall>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<ScheduleCallDto> getScheduledCallDtlsByEmployeeMstID(Long employeeMstId) {
        Session session;
        List<ScheduleCallDto> scheduleCallDtlsDtos = new ArrayList<>();
        try {
            session = getSessionFactory().getCurrentSession();
            Criteria criteria = session.createCriteria(ScheduleCall.class, "SC")
                    .createAlias("SC.customerId", "CM")
                    .createAlias("SC.employeeId", "EM")
                    .add(Restrictions.eq("SC.activeFlg", true))
                    .add(Restrictions.eq("SC.deleteFlg", false))
                    .add(Restrictions.eq("EM.id", employeeMstId))
                    .setProjection(Projections.distinct(Projections.projectionList()
                            .add(Projections.property("SC.id"), "scheduleCallMstId")
                            .add(Projections.property("SC.scheduleDate"), "scheduleStartDateTime")
                            .add(Projections.property("SC.scheduleEndDate"), "scheduleEndDateTime")))
                    .addOrder(Order.desc("SC.id"))
                    .setResultTransformer(Transformers.aliasToBean(ScheduleCallDto.class));

            List<ScheduleCallDto> list = criteria.list();

            if (list != null && list.size() > 0 && !list.isEmpty()) {
                scheduleCallDtlsDtos.addAll(list);
            }
            

        } catch (Exception e) {
            log.error("ERROR: ", e);
            scheduleCallDtlsDtos = null;
        }

        return scheduleCallDtlsDtos;
    }

    @Override
    public List<ScheduleCallDto> findScheduledCallDtlsByScheduledMstID(Long scheduledCallMstId) {
        Session session;
        List<ScheduleCallDto> scheduleCallDtlsDtos = new ArrayList<>();
        try {
            session = getSessionFactory().getCurrentSession();

            /*FOR EMPLOYEE*/
            Criteria criteria = session.createCriteria(ScheduleCall.class, "SC")
                    .createAlias("SC.customerId", "CM")
                    .createAlias("SC.employeeId", "EM")
                    .add(Restrictions.eq("SC.activeFlg", true))
                    .add(Restrictions.eq("SC.deleteFlg", false))
                    .add(Restrictions.eq("SC.id", scheduledCallMstId))
                    .setProjection(Projections.distinct(Projections.projectionList()
                            .add(Projections.property("SC.id"), "scheduleCallMstId")
                            .add(Projections.property("SC.scheduleDate"), "scheduleStartDateTime")
                            .add(Projections.property("SC.scheduleEndDate"), "scheduleEndDateTime")
                            .add(Projections.property("SC.service"), "service")
                            .add(Projections.property("SC.callType"), "callType")
                            .add(Projections.property("EM.id"), "employeeMstId")
                            .add(Projections.property("EM.firstName"), "empFirstName")
                            .add(Projections.property("EM.midName"), "empMidName")
                            .add(Projections.property("EM.lastName"), "empLastName")
                            .add(Projections.property("EM.cellPhone"), "empCellPhone")
                            .add(Projections.property("CM.id"), "customerMstId")
                            .add(Projections.property("CM.firstName"), "customerName")))
                    .addOrder(Order.desc("EM.id"))
                    .setResultTransformer(Transformers.aliasToBean(ScheduleCallDto.class));

            List<ScheduleCallDto> empScheduleCallDtlsList = criteria.list();

            if (empScheduleCallDtlsList != null && empScheduleCallDtlsList.size() > 0 && !empScheduleCallDtlsList.isEmpty()) {
                scheduleCallDtlsDtos.addAll(empScheduleCallDtlsList);
            }

        } catch (Exception e) {
            log.error("ERROR: ", e);
            scheduleCallDtlsDtos = null;
        }
        return scheduleCallDtlsDtos;
    }

    @Override
    public List<ScheduleCallDto> getScheduledCallDtlsByCustomerMstID(Long customerMstId) {
        Session session;
        List<ScheduleCallDto> scheduleCallDtlsDtos = new ArrayList<>();
        try {
            session = getSessionFactory().getCurrentSession();
            Criteria criteria = session.createCriteria(ScheduleCall.class, "SC")
                    .createAlias("SC.customerId", "CM")
                    .createAlias("SC.employeeId", "EM")
                    .add(Restrictions.eq("SC.activeFlg", true))
                    .add(Restrictions.eq("SC.deleteFlg", false))
                    .add(Restrictions.eq("CM.id", customerMstId))
                    .setProjection(Projections.distinct(Projections.projectionList()
                            .add(Projections.property("SC.id"), "scheduleCallMstId")
                            .add(Projections.property("SC.scheduleDate"), "scheduleStartDateTime")
                            .add(Projections.property("SC.scheduleEndDate"), "scheduleEndDateTime")))
                    .addOrder(Order.desc("SC.id"))
                    .setResultTransformer(Transformers.aliasToBean(ScheduleCallDto.class));

            List<ScheduleCallDto> list = criteria.list();

            if (list != null && list.size() > 0 && !list.isEmpty()) {
                scheduleCallDtlsDtos.addAll(list);
            }

        } catch (Exception e) {
            log.error("ERROR: ", e);
            scheduleCallDtlsDtos = null;
        }

        return scheduleCallDtlsDtos;
    }

    @Override
    public List<ScheduleCallDto> getScheduledCallDtlsByRMId(long rmId) {
        Session session;
        List<ScheduleCallDto> scheduleCallDtlsDtos = new ArrayList<>();
        try {
            session = getSessionFactory().getCurrentSession();
            Criteria criteria = session.createCriteria(ScheduleCall.class, "SC")
                    .createAlias("SC.customerId", "CM")
                    .createAlias("SC.employeeId", "EM")
                    .add(Restrictions.eq("SC.activeFlg", true))
                    .add(Restrictions.eq("SC.deleteFlg", false))
                    .add(Restrictions.eq("EM.id", rmId))
                    .setProjection(Projections.distinct(Projections.projectionList()
                            .add(Projections.property("SC.id"), "scheduleCallMstId")
                            .add(Projections.property("SC.scheduleDate"), "scheduleStartDateTime")
                            .add(Projections.property("SC.scheduleEndDate"), "scheduleEndDateTime")
                            .add(Projections.property("CM.firstName"), "firstName")
                            .add(Projections.property("CM.lastName"), "lastName")
                            .add(Projections.property("SC.callType"), "callType")
                            .add(Projections.property("SC.service"), "service")
                    ))
                    .addOrder(Order.desc("SC.id"))
                    .setResultTransformer(Transformers.aliasToBean(ScheduleCallDto.class));

            List<ScheduleCallDto> list = criteria.list();

            if (list != null && list.size() > 0 && !list.isEmpty()) {
                scheduleCallDtlsDtos.addAll(list);
            }
        } catch (Exception e) {
            log.error("ERROR: ", e);
            scheduleCallDtlsDtos = null;
        }

        return scheduleCallDtlsDtos;
    }

    @Override
    public List<ScheduleCallDto> getScheduledCallDtlsByCustomerId(long customerMstId) {
        Session session;
        List<ScheduleCallDto> scheduleCallDtlsDtos = new ArrayList<>();
        try {
            session = getSessionFactory().getCurrentSession();
            Criteria criteria = session.createCriteria(ScheduleCall.class, "SC")
                    .createAlias("SC.customerId", "CM")
                    .createAlias("SC.employeeId", "EM")
                    .add(Restrictions.eq("SC.activeFlg", true))
                    .add(Restrictions.eq("SC.deleteFlg", false))
                    .add(Restrictions.eq("CM.id", customerMstId))
                    .setProjection(Projections.distinct(Projections.projectionList()
                            .add(Projections.property("SC.id"), "scheduleCallMstId")
                            .add(Projections.property("SC.scheduleDate"), "scheduleStartDateTime")
                            .add(Projections.property("SC.scheduleEndDate"), "scheduleEndDateTime")
                            .add(Projections.property("EM.firstName"), "firstName")
                            .add(Projections.property("EM.lastName"), "lastName")
                            .add(Projections.property("SC.callType"), "callType")
                            .add(Projections.property("SC.service"), "service")
                    ))
                    .addOrder(Order.desc("SC.id"))
                    .setResultTransformer(Transformers.aliasToBean(ScheduleCallDto.class));

            List<ScheduleCallDto> list = criteria.list();

            if (list != null && list.size() > 0 && !list.isEmpty()) {
                scheduleCallDtlsDtos.addAll(list);
            }
        } catch (Exception e) {
            log.error("ERROR: ", e);
            scheduleCallDtlsDtos = null;
        }

        return scheduleCallDtlsDtos;
    }

}
