package com.rank.ccms.dao.impl;

import com.rank.ccms.dao.ReasonMstDao;
import com.rank.ccms.entities.ReasonMst;
import com.rank.ccms.rest.response.ReasonDto;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("reasonMstDao")
@Transactional
public class ReasonMstDaoImpl extends GenericDaoImpl<ReasonMst> implements ReasonMstDao {

    @Transactional(readOnly = true)
    @Override
    public List<ReasonMst> findAllActivenNonDeletedReasonMsts() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ReasonMst.class)
                .add(Restrictions.eq("deleteFlg", false))
                .add(Restrictions.eq("activeFlg", true))
                .add(Restrictions.ne("reasonCd", "NRDFT"));
        return (List<ReasonMst>) findByCriteria(detachedCriteria);
    }

    @Transactional(readOnly = true)
    @Override
    public List<ReasonMst> findAllActivenNonDeletedReasonMsts(String reasonType) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ReasonMst.class)
                .add(Restrictions.eq("type", reasonType).ignoreCase())
                .add(Restrictions.eq("deleteFlg", false))
                .add(Restrictions.eq("activeFlg", true))
                .add(Restrictions.ne("reasonCd", "NRDFT"));
        detachedCriteria.addOrder(Order.desc("reasonCd"));
        detachedCriteria.addOrder(Order.asc("id"));
        return (List<ReasonMst>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<ReasonDto> findAllNonDeletedReasonsAsReasonDtoList() {
        List<ReasonDto> reasonDtoList;
        try {
            DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ReasonMst.class, "RS")
                    .add(Restrictions.eq("RS.activeFlg", true))
                    .add(Restrictions.eq("RS.deleteFlg", false))
                    .addOrder(Order.asc("RS.reasonDesc"));

            ProjectionList projections = Projections.projectionList();
            detachedCriteria
                    .setProjection(projections.add(Projections.property("RS.type"), "type"))
                    .setProjection(projections.add(Projections.property("RS.reasonCd"), "reasonCd"))
                    .setProjection(projections.add(Projections.property("RS.reasonDesc"), "reasonDesc"));

            detachedCriteria.setResultTransformer(Transformers.aliasToBean(ReasonDto.class));

            reasonDtoList = (List<ReasonDto>) findByCriteria(detachedCriteria);
            return reasonDtoList;
        } catch (Exception e) {
            logger.error("Error : ", e);
            return null;
        }
    }

    @Override
    public ReasonMst findReasonMstByReasonCode(String reasonCode) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ReasonMst.class)
                .add(Restrictions.eq("deleteFlg", false))
                .add(Restrictions.eq("activeFlg", true))
                .add(Restrictions.eq("reasonCd", reasonCode).ignoreCase());

        List<ReasonMst> reasonList = (List<ReasonMst>) findByCriteria(detachedCriteria);
        if (!reasonList.isEmpty()) {
            return reasonList.get(0);
        } else {
            return null;
        }

    }

    @Override
    public ReasonMst findReasonMstByReasonType(String reasonType) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ReasonMst.class)
                .add(Restrictions.eq("deleteFlg", false))
                .add(Restrictions.eq("activeFlg", true))
                .add(Restrictions.eq("type", reasonType).ignoreCase());

        List<ReasonMst> reasonList = (List<ReasonMst>) findByCriteria(detachedCriteria);
        if (!reasonList.isEmpty()) {
            return reasonList.get(0);
        } else {
            return null;
        }
    }

}
