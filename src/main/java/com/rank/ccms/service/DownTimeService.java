package com.rank.ccms.service;

import com.rank.ccms.entities.DownTime;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public interface DownTimeService extends Serializable {

    DownTime saveDownTime(DownTime downTime);

    DownTime findDownTimeById(Long id);

    List<DownTime> findAllNonDeletedDownTime();

    List<DownTime> findDownTimeByDateRange(Timestamp startDate, Timestamp endDate);

}
