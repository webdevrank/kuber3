package com.rank.ccms.dao.impl;

import com.rank.ccms.dao.CallRecordsDao;
import com.rank.ccms.entities.CallDtl;
import com.rank.ccms.entities.CallMst;
import com.rank.ccms.entities.CallRecords;
import com.rank.ccms.dto.RecordingData;
import java.sql.Timestamp;
import java.util.List;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("callRecordsDao")
@Transactional
public class CallRecordsDaoImpl extends GenericDaoImpl<CallRecords> implements CallRecordsDao {

    @Transactional(readOnly = true)
    @Override
    public List<CallRecords> findAllActivenNonDeletedCallRecords() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CallRecords.class);
        return (List<CallRecords>) findByCriteria(detachedCriteria);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CallDtl> findOngoingCallInformation(Integer empID) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CallDtl.class, "callDtl");
        detachedCriteria.add(Restrictions.eq("callDtl.handeledById.id", empID));
        detachedCriteria.add(Restrictions.eq("callDtl.deleteFlg", false));
        detachedCriteria.setFetchMode("callDtl.callMstId", FetchMode.SELECT);
        detachedCriteria.createCriteria("callDtl.callMstId.id", JoinType.INNER_JOIN);
        detachedCriteria.addOrder(Order.desc("callDtl.startTime"));
        return (List<CallDtl>) findByCriteria(detachedCriteria);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CallRecords> findCallRecords(RecordingData rd, List<Integer> callIdList) {

        Timestamp startDate = null;
        Timestamp endDate = null;

        if (rd.getRecordStartTime() != null) {
            startDate = rd.getRecordStartTime();
        }
        if (rd.getRecordEndTime() != null) {
            endDate = rd.getRecordEndTime();
        }

        Long employeeID = rd.getEmployeeID();
        String custID = rd.getCustomerID();
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CallRecords.class, "callRecords");

        detachedCriteria.add(addRestrictions("callRecords.callId", callIdList));
        detachedCriteria.add(Restrictions.in("callRecords.callId", callIdList));
        if (startDate != null && endDate != null) {
            detachedCriteria.add(Restrictions.between("callRecords.recordStartTime", startDate, endDate));
        }
        if (employeeID != 0) {
            detachedCriteria.add(Restrictions.eq("callRecords.empId.id", employeeID));
        }
        if (!custID.equals("")) {
            detachedCriteria.add(Restrictions.eq("callRecords.customerId", custID));
        }

        return (List<CallRecords>) findByCriteria(detachedCriteria);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CallMst> findCallMst(RecordingData rd) {

        Timestamp startDate = null;
        Timestamp endDate = null;

        if (rd.getRecordStartTime() != null) {
            startDate = rd.getRecordStartTime();
        }
        if (rd.getRecordEndTime() != null) {
            endDate = rd.getRecordEndTime();
        }

        Long serviceID = rd.getServiceID();
        Long categoryID = rd.getCategoryID();
        Long langID = rd.getSkillID();
        String custId = rd.getCustomerID();

        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CallMst.class, "callMst");
        if (startDate != null && endDate != null) {
            detachedCriteria.add(Restrictions.between("callMst.startTime", startDate, endDate));
        }
        if (null != langID && langID != 0) {
            detachedCriteria.add(Restrictions.eq("callMst.languageId", langID));
        }
        if (null != serviceID && serviceID != 0) {
            detachedCriteria.add(Restrictions.eq("callMst.serviceId", serviceID));
        }
        if (null != categoryID && categoryID != 0) {
            detachedCriteria.add(Restrictions.eq("callMst.categoryId", categoryID));
        }
        if (null != custId && !custId.equals("")) {
            detachedCriteria.add(Restrictions.eq("callMst.custId", custId));
        }

        detachedCriteria.addOrder(Order.asc("callMst.startTime"));
        return (List<CallMst>) findByCriteria(detachedCriteria);
    }

    public Criterion addRestrictions(String propertyName, List list) {
        int size = list.size();
        int fromIndex = 0;
        int toIndex = (size > 1000) ? 1000 : size;
        List subList = list.subList(fromIndex, toIndex);
        Criterion lhs = Restrictions.in(propertyName, subList);
        toIndex = subList.size();
        size = size - toIndex;
        while (size > 0) {
            fromIndex = toIndex;
            toIndex += (size > 1000) ? 1000 : size;
            subList = list.subList(fromIndex, toIndex);
            Criterion rhs = Restrictions.in(propertyName, subList);
            lhs = Restrictions.or(lhs, rhs);
            size = size - subList.size();
        }
        return lhs;
    }

    @Override
    public CallRecords findCallRecordsByCallId(Long callId, Long empId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CallRecords.class, "callRecords");

        if (null != empId && empId != 0) {
            detachedCriteria.add(Restrictions.eq("callRecords.empId.id", empId));
        }
        detachedCriteria.add(Restrictions.eq("callRecords.callId.id", callId));
        detachedCriteria.addOrder(Order.desc("callRecords.id"));
        List<CallRecords> recordsList = (List<CallRecords>) findByCriteria(detachedCriteria);
        if (recordsList.isEmpty()) {
            return null;
        } else {
            return (CallRecords) recordsList.get(0);
        }
    }

    @Override
    public CallRecords findRecordsByRecoderId(String id) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CallRecords.class, "callRecords");

        if (id != null && !id.trim().equalsIgnoreCase("")) {
            detachedCriteria.add(Restrictions.eq("callRecords.recorderId", id));
        }
        detachedCriteria.add(Restrictions.eq("callRecords.recorderId", id));
        detachedCriteria.addOrder(Order.desc("callRecords.id"));
        List<CallRecords> recordsList = (List<CallRecords>) findByCriteria(detachedCriteria);
        if (recordsList.isEmpty()) {
            return null;
        } else {
            return (CallRecords) recordsList.get(0);
        }
    }

    @Override
    public List<CallRecords> findAllNotSavedRoomLinkCallRecords() {
        DetachedCriteria subQuery = DetachedCriteria.forClass(CallMst.class, "callMst");
        subQuery.setProjection(Projections.property("callMst.id"))
                .add(Restrictions.isNotNull("callMst.endTime"));

        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CallRecords.class, "callRecords");
        detachedCriteria.add(Restrictions.eq("callRecords.externalPlaybackLink", "Not Saved").ignoreCase());
        detachedCriteria.add(Restrictions.isNotNull("callRecords.roomName"));
        detachedCriteria.add(Property.forName("callRecords.callId").in(subQuery));
        return (List<CallRecords>) findByCriteria(detachedCriteria);
    }

    @Override
    public CallRecords findCallRecordsByCallIdOnly(Long callId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CallRecords.class, "callRecords");

        detachedCriteria.add(Restrictions.eq("callRecords.callId.id", callId));
        detachedCriteria.addOrder(Order.desc("callRecords.id"));
        List<CallRecords> recordsList = (List<CallRecords>) findByCriteria(detachedCriteria);
        if (recordsList.isEmpty()) {
            return null;
        } else {
            return (CallRecords) recordsList.get(0);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<CallRecords> findListOfNotStopRecording() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CallRecords.class, "callRecords");

        detachedCriteria.add(Restrictions.isNotNull("callRecords.roomName"))
                .add(Restrictions.isNotNull("callRecords.externalPlaybackLink"))
                .add(Restrictions.eq("callRecords.isRecordingStop", false))
                .addOrder(Order.desc("callRecords.id"));
        List<CallRecords> recordsList = (List<CallRecords>) findByCriteria(detachedCriteria);

        return recordsList;

    }

}
