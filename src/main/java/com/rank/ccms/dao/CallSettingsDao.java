package com.rank.ccms.dao;

import com.rank.ccms.entities.CallSettings;

public interface CallSettingsDao extends GenericDao<CallSettings> {

    public CallSettings findCallSettingsByWeekDay(String weekDay);

}
