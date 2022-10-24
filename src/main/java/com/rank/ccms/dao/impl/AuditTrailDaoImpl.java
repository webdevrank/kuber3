package com.rank.ccms.dao.impl;

import com.rank.ccms.dao.AuditTrailDao;
import com.rank.ccms.entities.AuditTrail;
import java.sql.Timestamp;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("auditTrailDao")
@Transactional
public class AuditTrailDaoImpl extends GenericDaoImpl<AuditTrail> implements AuditTrailDao {

    @Transactional
    @Override
    public List<AuditTrail> findAuditTrails() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(AuditTrail.class)
                .addOrder(Order.asc("tableName")).addOrder(Order.desc("disabledOn")).addOrder(Order.desc("updatedOn")).addOrder(Order.desc("createdOn"));
        return (List<AuditTrail>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<AuditTrail> findAuditTrailsById(Integer id) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(AuditTrail.class).add(Restrictions.idEq(id)).addOrder((Order.asc("id")));
        return (List<AuditTrail>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<AuditTrail> findAuditTrailsBySelection(String table, Long idCreate, Long idUpdate, Long idDisable, Timestamp startDate, Timestamp endDate) {

        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(AuditTrail.class).add(Restrictions.disjunction());
        if (startDate != null && endDate != null) {
            detachedCriteria.add(Restrictions.between("dateTime", startDate, endDate));
        }
        if (table != null && !table.trim().equals("")) {
            detachedCriteria.add(Restrictions.eq("tableName", table).ignoreCase());
        }
        if (idCreate != null && idUpdate != null && idDisable != null) {
            if (idCreate.doubleValue() != 0 && idUpdate.doubleValue() == 0 && idDisable.doubleValue() == 0) {

                detachedCriteria.add(Restrictions.eq("createdById", idCreate));
            }
            if (idUpdate.doubleValue() != 0 && idCreate.doubleValue() == 0 && idDisable.doubleValue() == 0) {

                detachedCriteria.add(Restrictions.eq("updatedById", idUpdate));
            }

            if (idDisable.doubleValue() != 0 && idCreate.doubleValue() == 0 && idUpdate.doubleValue() == 0) {

                detachedCriteria.add(Restrictions.eq("disabledById", idDisable));
            }
            if (idCreate.doubleValue() != 0 && idUpdate.doubleValue() != 0 && idDisable.doubleValue() == 0) {

                detachedCriteria.add(Restrictions.or(Restrictions.eq("createdById", idCreate), Restrictions.eq("updatedById", idUpdate)));
            }
            if (idCreate.doubleValue() != 0 && idUpdate.doubleValue() == 0 && idDisable.doubleValue() != 0) {

                detachedCriteria.add(Restrictions.or(Restrictions.eq("createdById", idCreate), Restrictions.eq("disabledById", idDisable)));
            }
            if (idCreate.doubleValue() == 0 && idUpdate.doubleValue() != 0 && idDisable.doubleValue() != 0) {

                detachedCriteria.add(Restrictions.or(Restrictions.eq("updatedById", idUpdate), Restrictions.eq("disabledById", idDisable)));
            }
            if (idCreate.doubleValue() != 0 && idUpdate.doubleValue() != 0 && idDisable.doubleValue() != 0) {

                Junction conditionGroup = Restrictions.disjunction();
                conditionGroup.add(Restrictions.or(Restrictions.or(Restrictions.eq("createdById", idCreate), Restrictions.eq("updatedById", idUpdate)), Restrictions.eq("disabledById", idDisable)));
                detachedCriteria.add(conditionGroup);

            }
        }
        if (detachedCriteria != null) {
            detachedCriteria.addOrder(Order.desc("id"));
            return (List<AuditTrail>) findByCriteria(detachedCriteria);
        }
        return null;
    }
}
