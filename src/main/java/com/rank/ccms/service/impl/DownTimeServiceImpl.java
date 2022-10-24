package com.rank.ccms.service.impl;

import com.rank.ccms.dao.DownTimeDao;
import com.rank.ccms.entities.DownTime;
import com.rank.ccms.service.DownTimeService;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("downTimeService")
public class DownTimeServiceImpl implements DownTimeService {

    private final static Logger logger = Logger.getLogger(CallSettingsServiceImpl.class);

    @Autowired
    private DownTimeDao downTimeDao;

    @Override
    public DownTime saveDownTime(DownTime downTime) {

        if (downTime.getId() == null) {
            downTime = downTimeDao.saveRow(downTime);

        } else {
            DownTime existingDownTime = downTimeDao.findById(downTime.getId());
            if (existingDownTime != null) {
                if (existingDownTime != downTime) {
                    existingDownTime.setId(downTime.getId());
                    existingDownTime.setStartTime(downTime.getStartTime());
                    existingDownTime.setEndTime(downTime.getEndTime());
                    existingDownTime.setActiveFlg(downTime.getActiveFlg());
                    existingDownTime.setDeleteFlg(downTime.getDeleteFlg());
                    existingDownTime.setReason(downTime.getReason());
                }
                try {
                    downTime = downTimeDao.mergeRow(existingDownTime);
                } catch (Exception e) {
                    logger.error("Error:saveCallSettingsDao:" + e.getMessage());
                }
            } else {
                downTime = downTimeDao.saveRow(downTime);
            }
        }
        return downTime;
    }

    @Override
    public DownTime findDownTimeById(Long id) {
        return downTimeDao.findDownTimeById(id);
    }

    @Override
    public List<DownTime> findAllNonDeletedDownTime() {

        return new ArrayList<>(downTimeDao.findAllNonDeleted());
    }

    @Override
    public List<DownTime> findDownTimeByDateRange(Timestamp startDate, Timestamp endDate) {
        return downTimeDao.findDownTimeByDateRange(startDate, endDate);
    }

}
