package com.rank.ccms.service.impl;

import com.rank.ccms.dao.AuditTrailDao;
import com.rank.ccms.dao.LanguageMstDao;
import com.rank.ccms.entities.AuditTrail;
import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.entities.LanguageMst;
import com.rank.ccms.service.LanguageMstService;
import com.rank.ccms.util.CustomConvert;
import com.rank.ccms.util.EntityMetaData;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("languageMstService")
public class LanguageMstServiceImpl implements LanguageMstService {

    Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private AuditTrailDao auditTrailDao;

    @Autowired
    private LanguageMstDao languageMstDao;

    @Transactional
    @Override
    public LanguageMst findValidLanguageMstById(Long id) {
        return languageMstDao.findValidLanguageMstById(id);
    }

    @Transactional
    @Override
    public List<LanguageMst> findAllNonDeletedLanguageMsts() {
        return new ArrayList<>(languageMstDao.findAllNonDeleted());
    }

    @Transactional
    @Override
    public LanguageMst saveLanguageMst(LanguageMst languageMst, EmployeeMst employeeMst) {
        AuditTrail auditTrail = new AuditTrail();
        EntityMetaData entityMetaData = languageMstDao.getEntityMetaData();
        auditTrail.setTableName(entityMetaData.getRootTableName());
        if (languageMst.getId() == null) {
            try {
                languageMst = languageMstDao.saveRow(languageMst);
                auditTrail.setTableKey1(entityMetaData.getIdentifierPropertyName() + "=" + languageMst.getId().toString());
                auditTrail.setCreatedById(employeeMst.getId());
                auditTrail.setCreatedOn(CustomConvert.javaDateToTimeStamp(new Date()));
                auditTrail.setReason("Created/Inserted first time");
                auditTrail.setDateTime(CustomConvert.javaDateToTimeStamp(new Date()));
            } catch (ParseException ex) {

                logger.error("1.Date Parsing Exception:" + ex.getMessage(), ex);
            }
        } else {
            LanguageMst existinglanguageMst = languageMstDao.findById(languageMst.getId());
            if (existinglanguageMst != null) {
                if (existinglanguageMst != languageMst) {
                    existinglanguageMst.setId(languageMst.getId());
                    existinglanguageMst.setLanguageCd(languageMst.getLanguageCd());
                    existinglanguageMst.setLanguageName(languageMst.getLanguageName());
                    existinglanguageMst.setLanguageDesc(languageMst.getLanguageDesc());
                    existinglanguageMst.setDeleteFlg(languageMst.getDeleteFlg());
                    existinglanguageMst.setActiveFlg(languageMst.getActiveFlg());
                }
                try {
                    if (existinglanguageMst.getDeleteFlg()) {
                        auditTrail.setDisabledById(employeeMst.getId());
                        auditTrail.setDisabledOn(CustomConvert.javaDateToTimeStamp(new Date()));
                        auditTrail.setReason("Disabled");
                        auditTrail.setDateTime(CustomConvert.javaDateToTimeStamp(new Date()));
                    } else {
                        auditTrail.setUpdatedById(employeeMst.getId());
                        auditTrail.setUpdatedOn(CustomConvert.javaDateToTimeStamp(new Date()));
                        auditTrail.setReason("Updated");
                        auditTrail.setDateTime(CustomConvert.javaDateToTimeStamp(new Date()));
                    }

                    languageMst = languageMstDao.mergeRow(existinglanguageMst);
                    auditTrail.setTableKey1(entityMetaData.getIdentifierPropertyName() + "=" + languageMst.getId().toString());
                } catch (ParseException e) {

                    logger.error("2.Date Parsing Exception:" + e.getMessage(), e);
                }
            } else {
                try {
                    languageMst = languageMstDao.saveRow(languageMst);
                    auditTrail.setTableKey1(entityMetaData.getIdentifierPropertyName() + "=" + languageMst.getId().toString());
                    auditTrail.setCreatedById(employeeMst.getId());
                    auditTrail.setCreatedOn(CustomConvert.javaDateToTimeStamp(new Date()));
                    auditTrail.setReason("Created/Inserted first time");
                    auditTrail.setDateTime(CustomConvert.javaDateToTimeStamp(new Date()));
                } catch (ParseException ex) {

                    logger.error("3.Date Parsing Exception:" + ex.getMessage(), ex);
                }
            }
        }
        auditTrail = auditTrailDao.saveRow(auditTrail);
        if (null == auditTrail) {
            logger.error("Failed to Add Audit Data..");
            return null;
        }
        return languageMst;
    }

    @Transactional
    @Override
    public LanguageMst findNonDeletedLanguageMstById(Long id) {
        return languageMstDao.findNonDeletedById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public LanguageMst findLanguageMstByLanguageCode(String languageCode) {
        LanguageMst languageMst = languageMstDao.findLanguageMstByLanguageCode(languageCode);

        return languageMst;
    }

    @Transactional(readOnly = true)
    @Override
    public LanguageMst findLanguageMstByLanguageName(String languageName) {
        LanguageMst languageMst = languageMstDao.findLanguageMstByLanguageName(languageName);

        return languageMst;
    }

    @Transactional
    @Override
    public LanguageMst findAllLanguageMstById(Long id) {
        return languageMstDao.findById(id);
    }

}
