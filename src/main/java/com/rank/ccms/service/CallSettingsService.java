package com.rank.ccms.service;

import com.rank.ccms.entities.CallSettings;
import java.io.Serializable;
import java.util.List;

public interface CallSettingsService extends Serializable {

    CallSettings saveCallSettings(CallSettings callSettings);

    CallSettings findCallSettingsById(Long id);

    List<CallSettings> findAllNonDeletedCallSettings();

    CallSettings findCallSettingsByWeekDay(String weekDay);

    void deleteServiceTime(CallSettings cs);

}
