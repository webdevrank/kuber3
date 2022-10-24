package com.rank.ccms.service.impl;

import com.rank.ccms.dao.AuditTrailDao;
import com.rank.ccms.dao.ReasonMstDao;
import com.rank.ccms.entities.AuditTrail;
import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.entities.ReasonMst;
import com.rank.ccms.service.ReasonMstService;
import com.rank.ccms.util.CustomConvert;
import com.rank.ccms.util.EntityMetaData;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("reasonMstService")
public class ReasonMstServiceImpl implements ReasonMstService {

    Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private AuditTrailDao auditTrailDao;
    @Autowired
    private ReasonMstDao reasonMstDao;

    @Transactional
    @Override
    public ReasonMst save(ReasonMst reasonMst, EmployeeMst employeeMst) {
        AuditTrail auditTrail = new AuditTrail();
        EntityMetaData entityMetaData = reasonMstDao.getEntityMetaData();
        auditTrail.setTableName(entityMetaData.getRootTableName());
        if (reasonMst.getId() == null) {
            try {
                reasonMst = reasonMstDao.saveRow(reasonMst);
                auditTrail.setTableKey1(entityMetaData.getIdentifierPropertyName() + "=" + reasonMst.getId().toString());
                auditTrail.setCreatedById(employeeMst.getId());
                auditTrail.setCreatedOn(CustomConvert.javaDateToTimeStamp(new Date()));
                auditTrail.setReason("Created/Inserted first time");
            } catch (ParseException ex) {

                logger.error("1.Date Parsing Exception:" + ex.getMessage(), ex);
            }
        } else {
            ReasonMst existingreasonMst = reasonMstDao.findById(reasonMst.getId());
            if (existingreasonMst != null) {
                if (existingreasonMst != reasonMst) {
                    existingreasonMst.setId(reasonMst.getId());
                    existingreasonMst.setType(reasonMst.getType());
                    existingreasonMst.setReasonCd(reasonMst.getReasonCd());
                    existingreasonMst.setReasonDesc(reasonMst.getReasonDesc());
                    existingreasonMst.setActiveFlg(reasonMst.getActiveFlg());
                    existingreasonMst.setDeleteFlg(reasonMst.getDeleteFlg());
                }
                try {
                    if (existingreasonMst.getDeleteFlg() == true) {
                        auditTrail.setDisabledById(employeeMst.getId());
                        auditTrail.setDisabledOn(CustomConvert.javaDateToTimeStamp(new Date()));
                        auditTrail.setReason("Disabled");

                    } else {
                        auditTrail.setUpdatedById(employeeMst.getId());
                        auditTrail.setUpdatedOn(CustomConvert.javaDateToTimeStamp(new Date()));
                        auditTrail.setReason("Updated");
                        auditTrail.setDateTime(CustomConvert.javaDateToTimeStamp(new Date()));
                    }
                    reasonMst = reasonMstDao.mergeRow(existingreasonMst);
                    auditTrail.setTableKey1(entityMetaData.getIdentifierPropertyName() + "=" + reasonMst.getId().toString());
                } catch (ParseException e) {

                    logger.error("2.Date Parsing Exception:" + e.getMessage(), e);
                }
            } else {
                try {
                    reasonMst = reasonMstDao.saveRow(reasonMst);
                    auditTrail.setTableKey1(entityMetaData.getIdentifierPropertyName() + "=" + reasonMst.getId().toString());
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
        return reasonMst;
    }

    @Override
    public List<ReasonMst> getAllNonDeletedReasons() {
        return reasonMstDao.findAllNonDeleted();
    }

    @Override
    public List<ReasonMst> findAllActivenNonDeletedReasonMsts() {
        return reasonMstDao.findAllActivenNonDeletedReasonMsts();
    }

    @Override
    public List<ReasonMst> findAllActivenNonDeletedReasonMsts(String reasonType) {
        return reasonMstDao.findAllActivenNonDeletedReasonMsts(reasonType);
    }

    @Override
    public ReasonMst findNonDeletedById(Long id) {
        return reasonMstDao.findNonDeletedById(id);
    }

    @Transactional
    @Override
    public ReasonMst findReasonMstByReasonCode(String reasonCode) {
        ReasonMst reasonMst = reasonMstDao.findReasonMstByReasonCode(reasonCode);

        return reasonMst;
    }

    @Transactional
    @Override
    public ReasonMst findReasonMstByReasonType(String reasonType) {
        ReasonMst reasonMst = reasonMstDao.findReasonMstByReasonType(reasonType);
        return reasonMst;
    }

}
