package com.rank.ccms.dao.impl;

import com.rank.ccms.dao.CallSettingsDao;
import com.rank.ccms.entities.CallSettings;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("callSettingsDao")
@Transactional
public class CallSettingsDaoImpl extends GenericDaoImpl<CallSettings> implements CallSettingsDao {

    private static final long serialVersionUID = 3304148688950431496L;

    @Override
    public CallSettings findCallSettingsByWeekDay(String weekDay) {
        List<CallSettings> settingsList;
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CallSettings.class)
                .add(Restrictions.eq("serviceDay", weekDay))
                .add(Restrictions.eq("deleteFlg", false))
                .add(Restrictions.eq("activeFlg", true));
        settingsList = (List<CallSettings>) findByCriteria(detachedCriteria);
        if (settingsList.isEmpty()) {
            return null;
        } else {
            return settingsList.get(0);
        }

    }

}
