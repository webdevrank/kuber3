package com.rank.ccms.dao.impl;

import com.rank.ccms.dao.CallMstDao;
import com.rank.ccms.entities.CallMst;
import java.sql.Timestamp;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("callMstDao")
@Transactional
public class CallMstDaoImpl extends GenericDaoImpl<CallMst> implements CallMstDao {

    @Override
    public CallMst findNonDeletedCallById(Long id) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CallMst.class)
                .add(Restrictions.eq("id", id))
                .add(Restrictions.eq("deleteFlg", false))
                .add(Restrictions.eq("activeFlg", true));
        List<CallMst> listCallMst = (List<CallMst>) findByCriteria(detachedCriteria);
        if (listCallMst == null || listCallMst.isEmpty() || listCallMst.size() < 1) {
            return null;
        } else {
            return listCallMst.get(0);
        }
    }

    @Override
    public CallMst findCallById(Long id) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CallMst.class)
                .add(Restrictions.eq("id", id));
        List<CallMst> listCallMst = (List<CallMst>) findByCriteria(detachedCriteria);
        if (listCallMst == null || listCallMst.isEmpty() || listCallMst.size() < 1) {
            return null;
        } else {
            return listCallMst.get(0);
        }
    }

    @Override
    public List<CallMst> findAllNonDeletedCallMsts() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CallMst.class)
                .add(Restrictions.eq("deleteFlg", false))
                .add(Restrictions.eq("activeFlg", true));
        return (List<CallMst>) findByCriteria(detachedCriteria);
    }

    @Override
    public Long findMaxCallIdByCustId(Long custId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CallMst.class)
                .setProjection(Projections.max("id"))
                .add(Restrictions.eq("customerId.id", custId))
                .add(Restrictions.eq("deleteFlg", false))
                .add(Restrictions.eq("activeFlg", true));
        return (Long) findByCriteria(detachedCriteria).get(0);
    }

    @Override
    public CallMst findMaxCallMstByCustId(Long custId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CallMst.class)
                .add(Restrictions.eq("customerId.id", custId))
                .add(Restrictions.eq("deleteFlg", false))
                .add(Restrictions.eq("activeFlg", true));
        List<CallMst> listCallMst = (List<CallMst>) findByCriteria(detachedCriteria);
        if (listCallMst == null || listCallMst.isEmpty() || listCallMst.size() < 1) {
            return null;
        } else {
            return listCallMst.get(0);
        }
    }

    @Override
    public List<CallMst> findAllNonDeletedCallMstsByCallStatus(String callStatus, Timestamp startDate, Timestamp endDate) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CallMst.class, "callMst");
        if (startDate != null && endDate != null) {
            detachedCriteria.add(Restrictions.between("callMst.startTime", startDate, endDate));
        }
        detachedCriteria.add(Restrictions.eq("callStatus", callStatus).ignoreCase());
        detachedCriteria.add(Restrictions.eq("deleteFlg", false));
        detachedCriteria.add(Restrictions.eq("activeFlg", true));
        detachedCriteria.addOrder(Order.desc("startTime"));
        return (List<CallMst>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<CallMst> findNonDeletedCallByDate(Timestamp startDate, Timestamp endDate) {

        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CallMst.class, "callMst");
        detachedCriteria.add(Restrictions.ne("callStatus", "No Agent Found").ignoreCase());
        detachedCriteria.add(Restrictions.ne("callStatus", "ABANDONED").ignoreCase());
        if (startDate != null && endDate != null) {
            detachedCriteria.add(Restrictions.between("callMst.startTime", startDate, endDate));
        }

        detachedCriteria.add(Restrictions.eq("deleteFlg", false));
        detachedCriteria.add(Restrictions.eq("activeFlg", true));
        return (List<CallMst>) findByCriteria(detachedCriteria);

    }

    @Override
    public List<CallMst> numberOfCalls(Timestamp startDate, Timestamp endDate, String custid, String deviceId, Long serviceId) {

        DetachedCriteria criteria = DetachedCriteria.forClass(CallMst.class, "callMst");
        if (startDate != null && endDate != null) {
            criteria.add(Restrictions.between("callMst.startTime", startDate, endDate));
        }
        if (custid != null && !"".equals(custid)) {
            criteria.add(Restrictions.eq("callMst.custId", custid));
        }
        if (deviceId != null && !"".equals(deviceId)) {
            criteria.add(Restrictions.eq("callMst.deviceId", deviceId));
        }
        if (serviceId != null) {
            criteria.add(Restrictions.eq("callMst.serviceId", serviceId));
        }

        return (List<CallMst>) findByCriteria(criteria);
    }

    @Override
    public List<CallMst> findDroppedCallByDate(Timestamp startDate, Timestamp endDate) {

        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CallMst.class, "callMst");
        detachedCriteria.add(Restrictions.eq("callStatus", "No Agent Found").ignoreCase());
        detachedCriteria.add(Restrictions.eq("deleteFlg", false));
        detachedCriteria.add(Restrictions.eq("activeFlg", true));
        if (startDate != null && endDate != null) {
            detachedCriteria.add(Restrictions.between("callMst.startTime", startDate, endDate));
        }
        return (List<CallMst>) findByCriteria(detachedCriteria);

    }

    @Override
    public List<CallMst> findDroppedCallByDateCustId(Timestamp startDate, Timestamp endDate, String Custid) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CallMst.class, "callMst");
        detachedCriteria.add(Restrictions.eq("callStatus", "No Agent Found").ignoreCase());
        detachedCriteria.add(Restrictions.eq("deleteFlg", false));
        detachedCriteria.add(Restrictions.eq("activeFlg", true));
        if (startDate != null && endDate != null) {
            detachedCriteria.add(Restrictions.between("callMst.startTime", startDate, endDate));
        }
        detachedCriteria.add(Restrictions.eq("callMst.custId", Custid));
        return (List<CallMst>) findByCriteria(detachedCriteria);

    }

    @Override
    public List<CallMst> findAllNonDeletedCallMstsByCallStatus(String callStatus, Timestamp startDate, Timestamp endDate, String custId, Long mobile, String account) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CallMst.class, "callMst");
        detachedCriteria.createCriteria("callMst.customerId", "custMst", Criteria.INNER_JOIN);
        if (startDate == null && endDate == null) {
        } else if (startDate != null && endDate == null) {
        } else if (startDate == null && endDate != null) {
        } else {
            detachedCriteria.add(Restrictions.between("callMst.startTime", startDate, endDate));
        }
        if (custId == null || custId.equals("")) {
        } else {
            detachedCriteria.add(Restrictions.eq("callMst.custId", custId));
        }
        if (mobile == null || mobile.equals((long) 0)) {
        } else {
            detachedCriteria.add(Restrictions.eq("custMst.cellPhone", mobile));
        }
        if (account == null || account.equals("")) {
        } else {
            detachedCriteria.add(Restrictions.eq("custMst.accountNo", account));
        }
        detachedCriteria.add(Restrictions.eq("callMst.callStatus", callStatus).ignoreCase());
        detachedCriteria.add(Restrictions.eq("callMst.deleteFlg", false));
        detachedCriteria.add(Restrictions.eq("callMst.activeFlg", true));
        detachedCriteria.setFetchMode("callMst.customerId", FetchMode.SELECT);
        detachedCriteria.addOrder(Order.desc("startTime"));
        return (List<CallMst>) findByCriteria(detachedCriteria);
    }

    @Override
    public CallMst findNonDeletedById(Long callId, String custId, Long mobile, String account) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CallMst.class, "callMst");
        detachedCriteria.createCriteria("callMst.customerId", "custMst", Criteria.INNER_JOIN);
        detachedCriteria.add(Restrictions.eq("callMst.id", callId));
        if (custId == null || custId.equals("")) {
        } else {
            detachedCriteria.add(Restrictions.eq("callMst.custId", custId));
        }
        if (mobile == null || mobile.equals((long) 0)) {
        } else {
            detachedCriteria.add(Restrictions.eq("custMst.cellPhone", mobile));
        }
        if (account == null || account.equals("")) {
        } else {
            detachedCriteria.add(Restrictions.eq("custMst.accountNo", account));
        }
        detachedCriteria.add(Restrictions.eq("callMst.deleteFlg", false));
        detachedCriteria.add(Restrictions.eq("callMst.activeFlg", true));
        detachedCriteria.setFetchMode("callMst.customerId", FetchMode.SELECT);
        detachedCriteria.addOrder(Order.desc("startTime"));
        List<CallMst> listCallMst = (List<CallMst>) findByCriteria(detachedCriteria);
        if (listCallMst == null || listCallMst.isEmpty() || listCallMst.size() < 1) {
            return null;
        } else {
            return listCallMst.get(0);
        }
    }

    @Override
    public List<CallMst> findDroppedCallByCustId(String Custid) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CallMst.class, "callMst");
        detachedCriteria.add(Restrictions.eq("callStatus", "No Agent Found").ignoreCase());
        detachedCriteria.add(Restrictions.eq("deleteFlg", false));
        detachedCriteria.add(Restrictions.eq("activeFlg", true));
        detachedCriteria.add(Restrictions.eq("callMst.custId", Custid));
        return (List<CallMst>) findByCriteria(detachedCriteria);

    }

    @Override
    public List<CallMst> findEndedCallByDateCustId(Timestamp startDate, Timestamp endDate, String Custid) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CallMst.class, "callMst");
        detachedCriteria.add(Restrictions.eq("callStatus", "Ended").ignoreCase());
        detachedCriteria.add(Restrictions.eq("deleteFlg", false));
        detachedCriteria.add(Restrictions.eq("activeFlg", true));
        if (startDate != null && endDate != null) {
            detachedCriteria.add(Restrictions.between("callMst.startTime", startDate, endDate));
        }
        detachedCriteria.add(Restrictions.eq("callMst.custId", Custid));
        return (List<CallMst>) findByCriteria(detachedCriteria);

    }

    @Override
    public CallMst findForwardedCallByDateCustId(Timestamp startDate, Timestamp endDate, String Custid, Integer id) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CallMst.class, "callMst");
        detachedCriteria.add(Restrictions.eq("callStatus", "Ended").ignoreCase());
        detachedCriteria.add(Restrictions.eq("deleteFlg", false));
        detachedCriteria.add(Restrictions.eq("activeFlg", true));
        if (startDate != null && endDate != null) {
            detachedCriteria.add(Restrictions.between("callMst.startTime", startDate, endDate));
        }
        detachedCriteria.add(Restrictions.eq("callMst.custId", Custid));
        return null;

    }

    @Override
    public List<CallMst> findCallMstByExceptThisCallStatus(String callStatus, Timestamp startDate, Timestamp endDate) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CallMst.class, "callMst");
        if (startDate != null && endDate != null) {
            detachedCriteria.add(Restrictions.between("callMst.startTime", startDate, endDate));
        }
        detachedCriteria.add(Restrictions.ne("callStatus", callStatus).ignoreCase());
        detachedCriteria.add(Restrictions.eq("deleteFlg", false));
        detachedCriteria.add(Restrictions.eq("activeFlg", true));
        detachedCriteria.addOrder(Order.desc("startTime"));
        return (List<CallMst>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<CallMst> findNonDropedCallByDateCustid(Timestamp startDate, Timestamp endDate, String custId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CallMst.class, "callMst");
        if (startDate != null && endDate != null) {
            detachedCriteria.add(Restrictions.between("callMst.startTime", startDate, endDate));
        }
        detachedCriteria.add(Restrictions.eq("custId", custId));
        detachedCriteria.add(Restrictions.ne("callStatus", "No Agent Found").ignoreCase());
        detachedCriteria.add(Restrictions.eq("deleteFlg", false));
        detachedCriteria.add(Restrictions.eq("activeFlg", true));
        detachedCriteria.addOrder(Order.desc("startTime"));
        return (List<CallMst>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<CallMst> findAllNonDeletedCallMstsByCallType(String callType, Timestamp startDate, Timestamp endDate, String custId, String account, Long mobileNo, Integer catgId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CallMst.class, "callMst");
        detachedCriteria.createCriteria("callMst.customerId", "custMst", Criteria.INNER_JOIN);
        if (startDate == null && endDate == null) {
        } else if (startDate != null && endDate == null) {
        } else if (startDate == null && endDate != null) {
        } else {
            detachedCriteria.add(Restrictions.between("callMst.startTime", startDate, endDate));
        }
        if (custId == null || custId.equals("")) {
        } else {
            detachedCriteria.add(Restrictions.eq("callMst.custId", custId));
        }
        if (account == null || account.equals("")) {
        } else {
            detachedCriteria.add(Restrictions.eq("custMst.accountNo", account));
        }
        if (mobileNo == null || mobileNo.equals((long) 0)) {
        } else {
            detachedCriteria.add(Restrictions.eq("custMst.cellPhone", mobileNo));
        }
        if (catgId == null || catgId == 0) {
        } else {
            detachedCriteria.add(Restrictions.eq("callMst.categoryId", catgId));
        }
        detachedCriteria.add(Restrictions.eq("callMst.callType", callType).ignoreCase());
        detachedCriteria.add(Restrictions.not(Restrictions.in("callMst.callStatus", new String[]{"No Agent Found", "ABANDONED", "Abandoned"})));
        detachedCriteria.add(Restrictions.eq("callMst.deleteFlg", false));
        detachedCriteria.add(Restrictions.eq("callMst.activeFlg", true));
        detachedCriteria.setFetchMode("callMst.customerId", FetchMode.SELECT);
        detachedCriteria.addOrder(Order.desc("startTime"));
        return (List<CallMst>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<CallMst> findAllNonDeletedCallMsts(Timestamp startDate, Timestamp endDate, String custId, String custName, String account, Long custMobile) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CallMst.class, "callMst");
        detachedCriteria.createCriteria("callMst.customerId", "custMst", Criteria.INNER_JOIN);
        if (startDate == null && endDate == null) {
        } else if (startDate != null && endDate == null) {
        } else if (startDate == null && endDate != null) {
        } else {
            detachedCriteria.add(Restrictions.between("callMst.startTime", startDate, endDate));
        }
        if (custId == null || custId.equals("")) {
        } else {
            detachedCriteria.add(Restrictions.eq("custMst.custId", custId));
        }
        if (custName == null || custName.equals("")) {
        } else {
            detachedCriteria.add(Restrictions.ilike("custMst.firstName", custName));
        }
        if (account == null || account.equals("")) {
        } else {
            detachedCriteria.add(Restrictions.eq("custMst.accountNo", account));
        }
        if (custMobile == null || custMobile == 0) {
        } else {
            detachedCriteria.add(Restrictions.eq("custMst.cellPhone", custMobile));
        }

        detachedCriteria.add(Restrictions.not(Restrictions.in("callMst.callStatus", new String[]{"No Agent Found", "ABANDONED", "Abandoned"})));
        detachedCriteria.add(Restrictions.eq("callMst.deleteFlg", false));
        detachedCriteria.add(Restrictions.eq("callMst.activeFlg", true));
        detachedCriteria.setFetchMode("callMst.customerId", FetchMode.SELECT);
        detachedCriteria.addOrder(Order.desc("startTime"));
        return (List<CallMst>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<CallMst> numberOfReceviedCalls(Timestamp startDate, Timestamp endDate, String custid, String deviceId, Long serviceId, String callStatus, String callstatus2) {

        DetachedCriteria criteria = DetachedCriteria.forClass(CallMst.class, "callMst");

        if (startDate != null && endDate != null) {
            criteria.add(Restrictions.between("callMst.startTime", startDate, endDate));
        }
        if (custid != null && !"".equals(custid)) {
            criteria.add(Restrictions.eq("callMst.custId", custid));
        }
        if (deviceId != null && !"".equals(deviceId)) {
            criteria.add(Restrictions.eq("callMst.deviceId", deviceId));
        }
        if (serviceId != null) {
            criteria.add(Restrictions.eq("callMst.serviceId", serviceId));
        }
        if (callStatus != null && callstatus2 != null) {
            criteria.add(Restrictions.or(Restrictions.eq("callMst.callStatus", callStatus).ignoreCase(), Restrictions.eq("callMst.callStatus", callstatus2).ignoreCase()));
        } else if (callStatus != null) {
            criteria.add(Restrictions.eq("callMst.callStatus", callStatus).ignoreCase());
        } else if (callstatus2 != null) {
            criteria.add(Restrictions.eq("callMst.callStatus", callstatus2).ignoreCase());
        }

        return (List<CallMst>) findByCriteria(criteria);
    }

    @Override
    public List<CallMst> findAllOnGoingCalls() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CallMst.class, "callMst");
        detachedCriteria.add(Restrictions.isNull("endTime"));
        detachedCriteria.add(Restrictions.ne("callStatus", "No Agent Found").ignoreCase());
        detachedCriteria.add(Restrictions.eq("deleteFlg", false));
        detachedCriteria.add(Restrictions.eq("activeFlg", true));
        detachedCriteria.addOrder(Order.desc("startTime"));
        return (List<CallMst>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<CallMst> findAllOnGoingCallsByCust(String custId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CallMst.class, "callMst");
        detachedCriteria.add(Restrictions.isNull("endTime"));
        detachedCriteria.add(Restrictions.eq("custId", custId));
        detachedCriteria.add(Restrictions.eq("deleteFlg", false));
        detachedCriteria.add(Restrictions.eq("activeFlg", true));
        detachedCriteria.addOrder(Order.desc("startTime"));
        return (List<CallMst>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<CallMst> findAllNotRecevedCallsByCust(String custId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CallMst.class, "callMst");
        detachedCriteria.add(Restrictions.isNull("endTime"));
        detachedCriteria.add(Restrictions.eq("custId", custId));
        detachedCriteria.add(Restrictions.eq("callStatus", "No Agent Found").ignoreCase());
        detachedCriteria.add(Restrictions.eq("deleteFlg", false));
        detachedCriteria.add(Restrictions.eq("activeFlg", true));
        detachedCriteria.addOrder(Order.desc("id"));
        return (List<CallMst>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<CallMst> findAllWaitingCalls() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CallMst.class, "callMst")
                .add(Restrictions.isNull("endTime"))
                .add(Restrictions.eq("callStatus", "Initialize").ignoreCase())
                .add(Restrictions.eq("deleteFlg", false))
                .add(Restrictions.eq("activeFlg", true))
                .addOrder(Order.desc("routingCallTime"));

        return (List<CallMst>) findByCriteria(detachedCriteria);
    }

    @Override
    public CallMst findNonEndedCustomerCallByCallId(Long callId, String custId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CallMst.class)
                .add(Restrictions.eq("id", callId));
        detachedCriteria.add(Restrictions.eq("custId", custId));
        detachedCriteria.add(Restrictions.isNull("endTime"));
        detachedCriteria.add(Restrictions.ne("callStatus", "No Agent Found").ignoreCase());
        List<CallMst> listCallMst = (List<CallMst>) findByCriteria(detachedCriteria);
        if (listCallMst == null || listCallMst.isEmpty() || listCallMst.size() < 1) {
            return null;
        } else {
            return listCallMst.get(0);
        }
    }

}
