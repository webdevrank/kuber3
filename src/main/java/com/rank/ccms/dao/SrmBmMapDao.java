package com.rank.ccms.dao;

import java.util.List;
import com.rank.ccms.entities.SrmBmMap;

public interface SrmBmMapDao extends GenericDao<SrmBmMap> {

    public List<SrmBmMap> getSRMsMappedWithBM(Long id);

    public List<SrmBmMap> findBMSRMMapByBMandSRM(Long bmId, Long srmId);

    public List<SrmBmMap> getBMMappedWithSRM(Long id);

}
