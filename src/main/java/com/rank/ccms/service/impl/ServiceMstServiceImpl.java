package com.rank.ccms.service.impl;

import com.rank.ccms.dao.AuditTrailDao;
import com.rank.ccms.dao.ServiceMstDao;
import com.rank.ccms.entities.AuditTrail;
import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.entities.ServiceMst;
import com.rank.ccms.service.ServiceMstService;
import com.rank.ccms.util.CustomConvert;
import com.rank.ccms.util.EntityMetaData;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

@Service("serviceMstService")
public class ServiceMstServiceImpl extends SpringBeanAutowiringSupport implements ServiceMstService {

    Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private AuditTrailDao auditTrailDao;

    @Autowired
    private ServiceMstDao serviceMstDao;

    @Override
    public Integer countServiceMsts() {
        return serviceMstDao.findAll().size();
    }

    @Override
    public void deleteServiceMst(ServiceMst servicemst, EmployeeMst employeeMst) {
        AuditTrail auditTrail = new AuditTrail();
        EntityMetaData entityMetaData = serviceMstDao.getEntityMetaData();
        auditTrail.setTableName(entityMetaData.getRootTableName());
        try {
            auditTrail.setTableKey1(entityMetaData.getIdentifierPropertyName() + "=" + servicemst.getId().toString());
            auditTrail.setDisabledById(employeeMst.getId());
            servicemst.setDeleteFlg(true);

            serviceMstDao.mergeRow(servicemst);
            auditTrail.setDisabledOn(CustomConvert.javaDateToTimeStamp(new Date()));
            auditTrail.setReason("Disabled");
            auditTrail.setDateTime(CustomConvert.javaDateToTimeStamp(new Date()));
        } catch (ParseException e) {

            logger.error("1.Date Parsing Exception:" + e.getMessage(), e);
        }
        auditTrail = auditTrailDao.saveRow(auditTrail);
        if (null == auditTrail) {
            logger.error("Failed to Add Audit Data..");
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<ServiceMst> findAllNonDeletedServiceMsts() {
        return new java.util.ArrayList<>(serviceMstDao.findAllNonDeleted());
    }

    @Transactional(readOnly = true)
    @Override
    public List<ServiceMst> findAllServiceMsts() {
        return new java.util.ArrayList<>(serviceMstDao.findAll());

    }

    @Transactional(readOnly = true)
    @Override
    public ServiceMst findAllServiceMstById(Long id) {
        return serviceMstDao.findAllServiceMstById(id);

    }

    @Transactional(readOnly = true)
    @Override
    public ServiceMst findNonDeletedServiceMstById(Long id) {
        return serviceMstDao.findNonDeletedServiceMstById(id);

    }

    @Override
    public ServiceMst saveServiceMst(ServiceMst servicemst, EmployeeMst employeeMst) {
        AuditTrail auditTrail = new AuditTrail();
        EntityMetaData entityMetaData = serviceMstDao.getEntityMetaData();
        auditTrail.setTableName(entityMetaData.getRootTableName());
        if (servicemst.getId() == null) {
            try {
                servicemst = serviceMstDao.saveRow(servicemst);
                auditTrail.setTableKey1(entityMetaData.getIdentifierPropertyName() + "=" + servicemst.getId().toString());
                auditTrail.setCreatedById(employeeMst.getId());
                auditTrail.setCreatedOn(CustomConvert.javaDateToTimeStamp(new Date()));
                auditTrail.setReason("Created/Inserted first time");
                auditTrail.setDateTime(CustomConvert.javaDateToTimeStamp(new Date()));
            } catch (ParseException ex) {

                logger.error("1.Date Parsing Exception:" + ex.getMessage(), ex);
            }
        } else {
            ServiceMst existingServiceMst = serviceMstDao.findById(servicemst.getId());
            if (existingServiceMst != null) {
                try {
                    if (servicemst.getDeleteFlg() == true) {
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

                    servicemst = serviceMstDao.mergeRow(servicemst);
                    auditTrail.setTableKey1(entityMetaData.getIdentifierPropertyName() + "=" + servicemst.getId().toString());
                } catch (ParseException e) {

                    logger.error("2.Date Parsing Exception:" + e.getMessage(), e);
                }
            } else {
                try {
                    servicemst = serviceMstDao.saveRow(servicemst);
                    auditTrail.setTableKey1(entityMetaData.getIdentifierPropertyName() + "=" + servicemst.getId().toString());
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
        return servicemst;
    }

    @Transactional(readOnly = true)
    @Override
    public ServiceMst findServiceByServiceName(String serviceName) {
        ServiceMst serMst = serviceMstDao.findServiceByServiceName(serviceName);
        return serMst;
    }

    @Transactional(readOnly = true)
    @Override
    public ServiceMst findServiceByServiceCode(String serviceCode) {
        ServiceMst serMst = serviceMstDao.findServiceByServiceCode(serviceCode);

        return serMst;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ServiceMst> loadAllService() {
        return serviceMstDao.getAllActiveServiceDetails();
    }

}
