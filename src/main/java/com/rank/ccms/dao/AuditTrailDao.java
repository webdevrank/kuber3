package com.rank.ccms.dao;

import com.rank.ccms.entities.AuditTrail;
import java.sql.Timestamp;
import java.util.List;

public interface AuditTrailDao extends GenericDao<AuditTrail> {

    public List<AuditTrail> findAuditTrails();

    public List<AuditTrail> findAuditTrailsById(Integer id);

    public List<AuditTrail> findAuditTrailsBySelection(String table, Long idCreate, Long idUpdate, Long idDisable, Timestamp startDate, Timestamp endDate);

}
