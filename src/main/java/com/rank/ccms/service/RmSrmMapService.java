package com.rank.ccms.service;

import com.rank.ccms.entities.RmSrmMap;
import java.io.Serializable;
import java.util.List;

public interface RmSrmMapService extends Serializable {

    List<RmSrmMap> getRMsMappedWithSRM(Long id);

    RmSrmMap getSRMMappedWithRM(Long id);

    List<RmSrmMap> findSRMRMMapBySRMandRM(Long srmId, Long rmId);

    void deleteRmSrmMap(RmSrmMap rmSrmMap);

    RmSrmMap saveSRMRMMap(RmSrmMap rmSrmMap);
}
