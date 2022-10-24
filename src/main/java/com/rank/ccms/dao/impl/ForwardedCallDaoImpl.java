package com.rank.ccms.dao.impl;

import com.rank.ccms.dao.ForwardedCallDao;
import com.rank.ccms.entities.CallMst;
import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.entities.ForwardedCall;
import java.util.List;
import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("forwardedCallDao")
@Transactional
public class ForwardedCallDaoImpl extends GenericDaoImpl<ForwardedCall> implements ForwardedCallDao {

    @Override
    public ForwardedCall findNonDeletedById(Long id) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ForwardedCall.class)
                .add(Restrictions.idEq(id))
                .add(Restrictions.eq("deleteFlg", false));                         //For Delete status check
        return (ForwardedCall) findByCriteria(detachedCriteria).get(0);
    }

    @Override
    public ForwardedCall findByIdAll(Integer id) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ForwardedCall.class);
        detachedCriteria.add(Restrictions.idEq(id));
        detachedCriteria.setFetchMode("callId", FetchMode.SELECT);
        detachedCriteria.createCriteria("callId", JoinType.INNER_JOIN);
        detachedCriteria.setFetchMode("empId", FetchMode.SELECT);
        detachedCriteria.createCriteria("empId", JoinType.INNER_JOIN);
        return (ForwardedCall) get(ForwardedCall.class, id);
    }

    @Override
    public List<ForwardedCall> findAllActivenNonDeletedForwardedCalls() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ForwardedCall.class)
                .add(Restrictions.eq("deleteFlg", false))
                .add(Restrictions.eq("activeFlg", true));
        detachedCriteria.setFetchMode("empId", FetchMode.SELECT);
        detachedCriteria.createCriteria("empId", JoinType.INNER_JOIN);
        detachedCriteria.setFetchMode("callId", FetchMode.SELECT);
        detachedCriteria.createCriteria("callId", JoinType.INNER_JOIN);
        return (List<ForwardedCall>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<ForwardedCall> findActiveForwardedCallByEmployeeMst(EmployeeMst employee) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ForwardedCall.class)
                .add(Restrictions.eq("empId.id", employee.getId()))
                .add(Restrictions.eq("activeFlg", true));
        detachedCriteria.setFetchMode("empId", FetchMode.SELECT);
        detachedCriteria.createCriteria("empId", JoinType.INNER_JOIN);
        detachedCriteria.setFetchMode("callId", FetchMode.SELECT);
        detachedCriteria.createCriteria("callId", JoinType.INNER_JOIN);
        detachedCriteria.addOrder(Order.desc("id"));
        return (List<ForwardedCall>) findByCriteria(detachedCriteria);
    }

    @Override
    public ForwardedCall findForwardedCallByCallIdAndEmpId(CallMst callMst) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ForwardedCall.class)
                .add(Restrictions.eq("callId.id", callMst.getId()));
        detachedCriteria.setFetchMode("empId", FetchMode.SELECT);
        detachedCriteria.createCriteria("empId", JoinType.INNER_JOIN);
        detachedCriteria.setFetchMode("callId", FetchMode.SELECT);
        detachedCriteria.createCriteria("callId", JoinType.INNER_JOIN);
        detachedCriteria.addOrder(Order.desc("id"));
        List<ForwardedCall> localList = (List<ForwardedCall>) findByCriteria(detachedCriteria);
        if (localList.isEmpty()) {
            return null;
        } else {
            return localList.get(0);
        }
    }

    @Override
    public List<ForwardedCall> findForwardedCallByCallId(CallMst callMst) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ForwardedCall.class)
                .add(Restrictions.eq("callId.id", callMst.getId()))
                .addOrder(Order.asc("id"));
        detachedCriteria.setFetchMode("empId", FetchMode.SELECT);
        detachedCriteria.createCriteria("empId", JoinType.INNER_JOIN);
        detachedCriteria.setFetchMode("callId", FetchMode.SELECT);
        detachedCriteria.createCriteria("callId", JoinType.INNER_JOIN);
        return (List<ForwardedCall>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<ForwardedCall> findForwardedCallByCallIdAndEmpId(CallMst callMst, EmployeeMst employee) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ForwardedCall.class)
                .add(Restrictions.eq("callId.id", callMst.getId()))
                .add(Restrictions.eq("empId.id", employee.getId()))
                .add(Restrictions.or(Restrictions.ne("forwardStatus", "missed"), Restrictions.isNull("forwardStatus")))
                .addOrder(Order.desc("id"));
        detachedCriteria.setFetchMode("empId", FetchMode.SELECT);
        detachedCriteria.createCriteria("empId", JoinType.INNER_JOIN);
        detachedCriteria.setFetchMode("callId", FetchMode.SELECT);
        detachedCriteria.createCriteria("callId", JoinType.INNER_JOIN);
        return (List<ForwardedCall>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<ForwardedCall> findForwardedCallByCallDtlId(Long calldtlId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ForwardedCall.class)
                .add(Restrictions.eq("callDtlId", calldtlId))
                .addOrder(Order.asc("id"));
        detachedCriteria.setFetchMode("empId", FetchMode.SELECT);
        detachedCriteria.setFetchMode("callId", FetchMode.SELECT);

        return (List<ForwardedCall>) findByCriteria(detachedCriteria);
    }

}
