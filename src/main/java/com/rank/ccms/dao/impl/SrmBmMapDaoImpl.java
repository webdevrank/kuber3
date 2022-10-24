package com.rank.ccms.dao.impl;

import com.rank.ccms.dao.SrmBmMapDao;
import com.rank.ccms.entities.SrmBmMap;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("srmBmMapDao")
@Transactional
public class SrmBmMapDaoImpl extends GenericDaoImpl<SrmBmMap> implements SrmBmMapDao {

    @Override
    public List<SrmBmMap> getSRMsMappedWithBM(Long id) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SrmBmMap.class)
                .add(Restrictions.eq("bmId.id", id));

        return (List<SrmBmMap>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<SrmBmMap> findBMSRMMapByBMandSRM(Long bmId, Long srmId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SrmBmMap.class, "RS")
                .add(Restrictions.eq("bmId.id", bmId))
                .add(Restrictions.eq("srmId.id", srmId));

        return (List<SrmBmMap>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<SrmBmMap> getBMMappedWithSRM(Long id) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(SrmBmMap.class)
                .add(Restrictions.eq("srmId.id", id));

        return (List<SrmBmMap>) findByCriteria(detachedCriteria);
    }

}
