package com.rank.ccms.service.impl;

import com.rank.ccms.dao.SrmBmMapDao;
import com.rank.ccms.entities.SrmBmMap;
import com.rank.ccms.service.SrmBmMapService;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("srmBmMapService")
public class SrmBmMapServiceImpl implements SrmBmMapService {

    Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private SrmBmMapDao srmBmMapDao;

    @Override
    public List<SrmBmMap> getSRMsMappedWithBM(Long id) {
        return srmBmMapDao.getSRMsMappedWithBM(id);
    }

    @Override
    public List<SrmBmMap> findBMSRMMapByBMandSRM(Long bmId, Long srmId) {
        return srmBmMapDao.findBMSRMMapByBMandSRM(bmId, srmId);
    }

    @Override
    public void deleteSrmBmMap(SrmBmMap srmBmMap) {
        srmBmMapDao.deleteRow(srmBmMap);

    }

    @Override
    public SrmBmMap saveBMSRMMap(SrmBmMap srmBmMap) {

        if (srmBmMap.getId() == null) {
            try {
                srmBmMap = srmBmMapDao.saveRow(srmBmMap);
            } catch (Exception e) {

            }
        } else {
            SrmBmMap srmBmMapExisting = null;
            try {
                srmBmMapExisting = srmBmMapDao.findById(srmBmMap.getId());
            } catch (Exception e) {

            }
            if (srmBmMapExisting != null) {
                if (srmBmMapExisting != srmBmMap) {
                    srmBmMapExisting.setId(srmBmMap.getId());
                    srmBmMapExisting.setActiveFlg(srmBmMap.getActiveFlg());
                    srmBmMapExisting.setDeleteFlg(srmBmMap.getDeleteFlg());
                    srmBmMapExisting.setBmId(srmBmMap.getBmId());
                    srmBmMapExisting.setSrmId(srmBmMap.getSrmId());

                }
                try {
                    srmBmMap = srmBmMapDao.mergeRow(srmBmMapExisting);
                } catch (Exception e) {

                }
            } else {
                try {
                    srmBmMap = srmBmMapDao.saveRow(srmBmMap);
                } catch (Exception e) {

                }
            }
        }
        return srmBmMap;
    }

    @Override
    public SrmBmMap getBMMappedWithSRM(Long id) {

        List<SrmBmMap> mapList = srmBmMapDao.getBMMappedWithSRM(id);
        if (mapList.isEmpty()) {
            return null;
        } else {
            return mapList.get(0);
        }
    }
}
