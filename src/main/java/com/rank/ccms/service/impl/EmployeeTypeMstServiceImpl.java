package com.rank.ccms.service.impl;

import com.rank.ccms.dao.AuditTrailDao;
import com.rank.ccms.dao.EmployeeTypeMstDao;
import com.rank.ccms.entities.AuditTrail;
import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.entities.EmployeeTypeMst;
import com.rank.ccms.service.EmployeeTypeMstService;
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

@Service("employeeTypeMstService")
public class EmployeeTypeMstServiceImpl implements EmployeeTypeMstService {

    Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private AuditTrailDao auditTrailDao;
    @Autowired
    private EmployeeTypeMstDao employeeTypeMstDao;

    @Transactional
    @Override
    public EmployeeTypeMst saveEmployeeTypeMst(EmployeeTypeMst employeeTypeMst, EmployeeMst employeeMst) {
        AuditTrail auditTrail = new AuditTrail();
        EntityMetaData entityMetaData = employeeTypeMstDao.getEntityMetaData();
        auditTrail.setTableName(entityMetaData.getRootTableName());
        if (employeeTypeMst.getId() == null) {
            try {
                employeeTypeMst = employeeTypeMstDao.saveRow(employeeTypeMst);
                auditTrail.setTableKey1(entityMetaData.getIdentifierPropertyName() + "=" + employeeTypeMst.getId().toString());
                auditTrail.setCreatedById(employeeMst.getId());
                auditTrail.setCreatedOn(CustomConvert.javaDateToTimeStamp(new Date()));
                auditTrail.setReason("Created/Inserted first time");
                auditTrail.setDateTime(CustomConvert.javaDateToTimeStamp(new Date()));
            } catch (ParseException ex) {

                logger.error("1.Date Parsing Exception:" + ex.getMessage(), ex);
            }
        } else {
            EmployeeTypeMst existingemployeeTypeMst = employeeTypeMstDao.findById(employeeTypeMst.getId());
            if (existingemployeeTypeMst != null) {
                if (existingemployeeTypeMst != employeeTypeMst) {
                    existingemployeeTypeMst.setId(employeeTypeMst.getId());
                    existingemployeeTypeMst.setTypeName(employeeTypeMst.getTypeName());
                    existingemployeeTypeMst.setTypeDesc(employeeTypeMst.getTypeDesc());
                    existingemployeeTypeMst.setDeleteFlg(employeeTypeMst.getDeleteFlg());
                    existingemployeeTypeMst.setActiveFlg(employeeTypeMst.getActiveFlg());
                }
                try {
                    if (existingemployeeTypeMst.getDeleteFlg() == true) {
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
                    employeeTypeMst = employeeTypeMstDao.mergeRow(existingemployeeTypeMst);
                    auditTrail.setTableKey1(entityMetaData.getIdentifierPropertyName() + "=" + existingemployeeTypeMst.getId().toString());
                } catch (ParseException e) {

                    logger.error("2.Date Parsing Exception:" + e.getMessage(), e);
                }
            } else {
                try {
                    employeeTypeMst = employeeTypeMstDao.saveRow(employeeTypeMst);
                    auditTrail.setTableKey1(entityMetaData.getIdentifierPropertyName() + "=" + employeeTypeMst.getId().toString());
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
            return null;
        }
        return employeeTypeMst;
    }

    @Transactional
    @Override
    public List<EmployeeTypeMst> findAllEmployeeTypeMsts() {
        return new ArrayList<>(employeeTypeMstDao.findAll());
    }

    @Transactional
    @Override
    public List<EmployeeTypeMst> findAllNonDeletedEmployeeTypeMst() {
        return new ArrayList<>(employeeTypeMstDao.findAllNonDeleted());
    }

    @Transactional
    @Override
    public EmployeeTypeMst findNonDeletedEmployeeTypeMstById(Long id) {
        return employeeTypeMstDao.findNonDeletedById(id);
    }

    @Transactional
    @Override
    public EmployeeTypeMst findEmployeeTypeMstById(Long id) {
        return employeeTypeMstDao.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public EmployeeTypeMst findEmployeeTypeByEmployeeTypeName(String employeeTypeName) {
        EmployeeTypeMst empMst = employeeTypeMstDao.findEmployeeTypeByEmployeeTypeName(employeeTypeName);

        return empMst;
    }

}
