package com.rank.ccms.dao;

import com.rank.ccms.entities.DownTime;
import java.sql.Timestamp;
import java.util.List;

public interface DownTimeDao extends GenericDao<DownTime> {

    public DownTime findDownTimeById(Long id);

    public List<DownTime> findDownTimeByDateRange(Timestamp startDate, Timestamp endDate);

}
