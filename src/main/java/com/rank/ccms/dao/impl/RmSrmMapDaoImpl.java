package com.rank.ccms.dao.impl;

import com.rank.ccms.dao.RmSrmMapDao;
import com.rank.ccms.entities.RmSrmMap;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("rmSrmMapDao")
@Transactional
public class RmSrmMapDaoImpl extends GenericDaoImpl<RmSrmMap> implements RmSrmMapDao {

    @Override
    public List<RmSrmMap> getRMsMappedWithSRM(Long id) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(RmSrmMap.class)
                .add(Restrictions.eq("srmId.id", id));

        return (List<RmSrmMap>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<RmSrmMap> findSRMRMMapBySRMandRM(Long srmId, Long rmId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(RmSrmMap.class, "RS")
                .add(Restrictions.eq("srmId.id", srmId))
                .add(Restrictions.eq("rmId.id", rmId));

        return (List<RmSrmMap>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<RmSrmMap> getSRMMappedWithRM(Long id) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(RmSrmMap.class)
                .add(Restrictions.eq("rmId.id", id));

        return (List<RmSrmMap>) findByCriteria(detachedCriteria);
    }

}
