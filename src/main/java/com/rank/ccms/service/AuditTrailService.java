package com.rank.ccms.service;

import com.rank.ccms.entities.AuditTrail;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public interface AuditTrailService extends Serializable {

    List<AuditTrail> findAllAuditTrails();

    List<AuditTrail> findAllAuditTrailsByAll(String tableName, Long idCreate, Long idUpdate, Long idDisable, Timestamp startDate, Timestamp endDate);

    AuditTrail findAuditTrailById(Long id);

    AuditTrail saveAuditTrail(AuditTrail inputAuditTrail);

}
