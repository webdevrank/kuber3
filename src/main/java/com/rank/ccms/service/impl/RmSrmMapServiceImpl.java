package com.rank.ccms.service.impl;

import com.rank.ccms.dao.RmSrmMapDao;
import com.rank.ccms.entities.RmSrmMap;
import com.rank.ccms.service.RmSrmMapService;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("rmSrmMapService")
public class RmSrmMapServiceImpl implements RmSrmMapService {

    Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private RmSrmMapDao rmSrmMapDao;

    @Override
    public List<RmSrmMap> getRMsMappedWithSRM(Long id) {
        return rmSrmMapDao.getRMsMappedWithSRM(id);
    }

    @Override
    public List<RmSrmMap> findSRMRMMapBySRMandRM(Long srmId, Long rmId) {
        return rmSrmMapDao.findSRMRMMapBySRMandRM(srmId, srmId);
    }

    @Override
    public void deleteRmSrmMap(RmSrmMap rmSrmMap) {
        rmSrmMapDao.deleteRow(rmSrmMap);

    }

    @Override
    public RmSrmMap saveSRMRMMap(RmSrmMap rmSrmMap) {

        if (rmSrmMap.getId() == null) {
            try {
                rmSrmMap = rmSrmMapDao.saveRow(rmSrmMap);
            } catch (Exception e) {

            }
        } else {
            RmSrmMap rmSrmMapExisting = null;
            try {
                rmSrmMapExisting = rmSrmMapDao.findById(rmSrmMap.getId());
            } catch (Exception e) {

            }
            if (rmSrmMapExisting != null) {
                if (rmSrmMapExisting != rmSrmMap) {
                    rmSrmMapExisting.setId(rmSrmMap.getId());
                    rmSrmMapExisting.setActiveFlg(rmSrmMap.getActiveFlg());
                    rmSrmMapExisting.setDeleteFlg(rmSrmMap.getDeleteFlg());
                    rmSrmMapExisting.setSrmId(rmSrmMap.getSrmId());
                    rmSrmMapExisting.setRmId(rmSrmMap.getRmId());

                }
                try {
                    rmSrmMap = rmSrmMapDao.mergeRow(rmSrmMapExisting);
                } catch (Exception e) {

                }
            } else {
                try {
                    rmSrmMap = rmSrmMapDao.saveRow(rmSrmMap);
                } catch (Exception e) {

                }
            }
        }
        return rmSrmMap;
    }

    @Override
    public RmSrmMap getSRMMappedWithRM(Long id) {
        List<RmSrmMap> rmSrmMapList = rmSrmMapDao.getSRMMappedWithRM(id);
        if (rmSrmMapList.isEmpty()) {
            return null;
        } else {
            return rmSrmMapList.get(0);
        }

    }

}
