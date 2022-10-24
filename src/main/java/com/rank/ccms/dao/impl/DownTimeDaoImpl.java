package com.rank.ccms.dao.impl;

import com.rank.ccms.dao.DownTimeDao;
import com.rank.ccms.entities.DownTime;
import java.sql.Timestamp;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("unchecked")
@Repository("downTimeDao")
@Transactional
public class DownTimeDaoImpl extends GenericDaoImpl<DownTime> implements DownTimeDao {

    private static final long serialVersionUID = -2352377100192632057L;

    @Override
    public DownTime findDownTimeById(Long id) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(DownTime.class)
                .add(Restrictions.eq("id", id))
                .add(Restrictions.eq("deleteFlg", false))
                .add(Restrictions.eq("activeFlg", true));
        detachedCriteria.addOrder(Order.desc("id"));

        List<DownTime> ls = (List<DownTime>) findByCriteria(detachedCriteria);
        if (ls.isEmpty()) {
            return null;
        } else {
            return ls.get(0);
        }
    }

    @Override
    public List<DownTime> findDownTimeByDateRange(Timestamp startDate, Timestamp endDate) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(DownTime.class)
                .add(Restrictions.between("startTime", startDate, endDate))
                .add(Restrictions.eq("deleteFlg", false))
                .add(Restrictions.eq("activeFlg", true));
        detachedCriteria.addOrder(Order.desc("id"));

        return (List<DownTime>) findByCriteria(detachedCriteria);
    }

}
