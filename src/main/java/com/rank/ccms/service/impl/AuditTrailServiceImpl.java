package com.rank.ccms.service.impl;

import com.rank.ccms.dao.AuditTrailDao;
import com.rank.ccms.entities.AuditTrail;
import com.rank.ccms.service.AuditTrailService;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("auditTrailService")
public class AuditTrailServiceImpl implements AuditTrailService {

    @Autowired
    private AuditTrailDao auditTrailDao;

    @Transactional(readOnly = true)
    @Override
    public List<AuditTrail> findAllAuditTrails() {
        return auditTrailDao.findAuditTrails();
    }

    @Transactional
    @Override
    public AuditTrail saveAuditTrail(AuditTrail inputAuditTrail) {
        return auditTrailDao.saveRow(inputAuditTrail);
    }

    @Override
    public AuditTrail findAuditTrailById(Long id) {
        return auditTrailDao.findById(id);
    }

    @Override
    public List<AuditTrail> findAllAuditTrailsByAll(String tableName, Long idCreate, Long idUpdate, Long idDisable, Timestamp startDate, Timestamp endDate) {
        tableName = tableName.toUpperCase();
        return auditTrailDao.findAuditTrailsBySelection(tableName, idCreate, idUpdate, idDisable, startDate, endDate);
    }
}
