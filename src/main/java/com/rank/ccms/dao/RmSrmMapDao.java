package com.rank.ccms.dao;

import java.util.List;
import com.rank.ccms.entities.RmSrmMap;

public interface RmSrmMapDao extends GenericDao<RmSrmMap> {

    public List<RmSrmMap> getRMsMappedWithSRM(Long id);

    public List<RmSrmMap> findSRMRMMapBySRMandRM(Long srmId, Long rmId);

    public List<RmSrmMap> getSRMMappedWithRM(Long id);

}
