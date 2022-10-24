package com.rank.ccms.service;

import com.rank.ccms.entities.SrmBmMap;
import java.io.Serializable;
import java.util.List;

public interface SrmBmMapService extends Serializable {

    List<SrmBmMap> getSRMsMappedWithBM(Long id);

    SrmBmMap getBMMappedWithSRM(Long id);

    List<SrmBmMap> findBMSRMMapByBMandSRM(Long bmId, Long srmId);

    void deleteSrmBmMap(SrmBmMap srmBmMap);

    SrmBmMap saveBMSRMMap(SrmBmMap srmBmMap);
}
