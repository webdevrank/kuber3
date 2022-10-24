package com.rank.ccms.service.impl;

import com.rank.ccms.dao.CallSettingsDao;
import com.rank.ccms.entities.CallSettings;
import com.rank.ccms.service.CallSettingsService;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("callSettingsService")
public class CallSettingsServiceImpl implements CallSettingsService {

    private static final long serialVersionUID = -2357598520749232931L;

    private final static Logger logger = Logger.getLogger(CallSettingsServiceImpl.class);

    @Autowired
    private CallSettingsDao callSettingsDao;

    @Override
    public CallSettings saveCallSettings(CallSettings callSettings) {

        if (callSettings.getId() == null) {
            callSettings = callSettingsDao.saveRow(callSettings);

        } else {
            CallSettings existingCallSettings = callSettingsDao.findById(callSettings.getId());
            if (existingCallSettings != null) {
                if (existingCallSettings != callSettings) {

                    existingCallSettings.setId(callSettings.getId());
                    existingCallSettings.setOtp(callSettings.getOtp());
                    existingCallSettings.setServiceStartTime(callSettings.getServiceStartTime());
                    existingCallSettings.setServiceEndTime(callSettings.getServiceEndTime());
                    existingCallSettings.setServiceDay(callSettings.getServiceDay());
                    existingCallSettings.setActiveFlg(callSettings.getActiveFlg());
                    existingCallSettings.setDeleteFlg(callSettings.getDeleteFlg());

                }
                try {
                    callSettings = callSettingsDao.mergeRow(existingCallSettings);
                } catch (Exception e) {
                    logger.error("Error:saveCallSettingsDao:" + e.getMessage());
                }
            } else {
                callSettings = callSettingsDao.saveRow(callSettings);
            }
        }
        return callSettings;
    }

    @Override
    public CallSettings findCallSettingsById(Long id) {
        return callSettingsDao.findById(id);
    }

    @Override
    public List<CallSettings> findAllNonDeletedCallSettings() {
        return new ArrayList<>(callSettingsDao.findAllNonDeleted());
    }

    @Override
    public CallSettings findCallSettingsByWeekDay(String weekDay) {
        return callSettingsDao.findCallSettingsByWeekDay(weekDay);
    }

    @Override
    public void deleteServiceTime(CallSettings cs) {
        callSettingsDao.deleteRow(cs);
    }

}
