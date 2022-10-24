package com.rank.ccms.service.impl;

import com.rank.ccms.dao.AuditTrailDao;
import com.rank.ccms.dao.EmployeeMstDao;
import com.rank.ccms.dao.EmployeeTypeMstDao;
import com.rank.ccms.entities.AuditTrail;
import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.entities.EmployeeTypeMst;
import com.rank.ccms.service.EmployeeMstService;
import com.rank.ccms.util.CustomConvert;
import com.rank.ccms.util.EntityMetaData;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("employeeMstService")
public class EmployeeMstServiceImpl implements EmployeeMstService {

    Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    private AuditTrailDao auditTrailDao;
    @Autowired
    private EmployeeTypeMstDao employeeTypeMstDao;
    @Autowired
    private EmployeeMstDao employeeMstDao;

    @Transactional
    @Override
    public EmployeeMst findEmployeeMstById(Long id) {
        return employeeMstDao.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<EmployeeMst> findAllEmployeeMsts() {
        return new ArrayList<>(employeeMstDao.findAll());
    }

    @Transactional(readOnly = true)
    @Override
    public List<EmployeeMst> findAllNonDeletedEmployeeMsts() {
        return new ArrayList<>(employeeMstDao.findAllNonDeletedEmployee());
    }

    @Transactional
    @Override
    public EmployeeMst saveEmployeeMst(EmployeeMst employeemst, EmployeeMst employeeMst) {
        AuditTrail auditTrail = new AuditTrail();
        EntityMetaData entityMetaData = employeeMstDao.getEntityMetaData();
        auditTrail.setTableName(entityMetaData.getRootTableName());
        try {
            if (employeemst.getId() == null) {
                employeemst = employeeMstDao.saveRow(employeemst);

            } else {
                EmployeeMst existingEmployeeMst = employeeMstDao.findById(employeemst.getId());
                if (existingEmployeeMst != null) {
                    if (existingEmployeeMst != employeemst) {
                        existingEmployeeMst.setId(employeemst.getId());
                        existingEmployeeMst.setFirstName(employeemst.getFirstName());
                        existingEmployeeMst.setMidName(employeemst.getMidName());
                        existingEmployeeMst.setLastName(employeemst.getLastName());
                        existingEmployeeMst.setLoginId(employeemst.getLoginId());
                        existingEmployeeMst.setLoginPasswd(employeemst.getLoginPasswd());
                        existingEmployeeMst.setEmpTypId(employeemst.getEmpTypId());
                        existingEmployeeMst.setEmpDob(employeemst.getEmpDob());
                        existingEmployeeMst.setAddrsLine1(employeemst.getAddrsLine1());
                        existingEmployeeMst.setAddrsLine2(employeemst.getAddrsLine2());
                        existingEmployeeMst.setCity(employeemst.getCity());
                        existingEmployeeMst.setStateNm(employeemst.getStateNm());
                        existingEmployeeMst.setPin(employeemst.getPin());
                        existingEmployeeMst.setCountry(employeemst.getCountry());
                        existingEmployeeMst.setEmail(employeemst.getEmail());
                        existingEmployeeMst.setOfficePhone(employeemst.getOfficePhone());
                        existingEmployeeMst.setHomePhone(employeemst.getHomePhone());
                        existingEmployeeMst.setCellPhone(employeemst.getCellPhone());
                        existingEmployeeMst.setActiveFlg(employeemst.getActiveFlg());
                        existingEmployeeMst.setDeleteFlg(employeemst.getDeleteFlg());
                        existingEmployeeMst.setDeactivateFlg(employeemst.getDeactivateFlg());
                    }

                    if (existingEmployeeMst.getDeleteFlg() == true) {
                        auditTrail.setDeletedById(employeeMst.getId());
                        auditTrail.setDeletedOn(CustomConvert.javaDateToTimeStamp(new Date()));
                        auditTrail.setReason("Deleted");
                        auditTrail.setDateTime(CustomConvert.javaDateToTimeStamp(new Date()));
                    } else if (existingEmployeeMst.getDeactivateFlg() == true) {
                        auditTrail.setDisabledById(employeeMst.getId());
                        auditTrail.setDisabledOn(CustomConvert.javaDateToTimeStamp(new Date()));
                        auditTrail.setReason("De-Activated");
                        auditTrail.setDateTime(CustomConvert.javaDateToTimeStamp(new Date()));
                    } else if (existingEmployeeMst.getDeactivateFlg() == false) {
                        auditTrail.setDisabledById(employeeMst.getId());
                        auditTrail.setDisabledOn(CustomConvert.javaDateToTimeStamp(new Date()));
                        auditTrail.setReason("Activated");
                        auditTrail.setDateTime(CustomConvert.javaDateToTimeStamp(new Date()));
                    } else {
                        auditTrail.setUpdatedById(employeeMst.getId());
                        auditTrail.setUpdatedOn(CustomConvert.javaDateToTimeStamp(new Date()));
                        auditTrail.setReason("Updated");
                        auditTrail.setDateTime(CustomConvert.javaDateToTimeStamp(new Date()));
                    }

                    employeemst = employeeMstDao.saveRow(existingEmployeeMst);
                    auditTrail.setTableKey1(entityMetaData.getIdentifierPropertyName() + "=" + employeemst.getId().toString());
                    auditTrailDao.saveRow(auditTrail);

                } else {
                    employeemst = employeeMstDao.saveRow(employeemst);
                    auditTrail.setTableKey1(entityMetaData.getIdentifierPropertyName() + "=" + employeemst.getId().toString());
                    auditTrailDao.saveRow(auditTrail);
                }
            }
        } catch (Exception e) {

            logger.error("2.Date Parsing Exception:" + e.getMessage(), e);
        }

        return employeemst;
    }

    @Transactional
    @Override
    public void deleteEmployeeMst(EmployeeMst employeemst, EmployeeMst employeeMst) {

        employeeMstDao.deleteRow(employeemst);

    }

    @Transactional(readOnly = true)
    @Override
    public Integer countEmployeeMsts() {
        return (Integer) (employeeMstDao.createQuerySingleResult("select count(o) from EmployeeMst o")).get(0);
    }

    @Transactional(readOnly = true)
    @Override
    public EmployeeMst findEmployeeInfoForLogin(String userLoginId, String userPassword) {
        return employeeMstDao.findEmployeeInfoForLogin(userLoginId, userPassword);

    }

    @Transactional(readOnly = true)
    @Override
    public EmployeeMst findEmployeeByUserId(String userLoginId) {
        EmployeeMst employeeMst = employeeMstDao.findEmployeeByUserId(userLoginId);

        return employeeMst;
    }
    
    @Transactional(readOnly = true)
    @Override
    public EmployeeMst findEmployeeByEmailId(String userEmailId) {
        EmployeeMst employeeMst = employeeMstDao.findEmployeeByEmailId(userEmailId);

        return employeeMst;
    }
    
    @Transactional(readOnly = true)
    @Override
    public EmployeeMst findEmployeeByMobileno(String mobileno) {
        EmployeeMst employeeMst = employeeMstDao.findEmployeeByMobileno(mobileno);

        return employeeMst;
    }

    @Transactional(readOnly = true)
    @Override
    public List<EmployeeMst> findEmployeeByEmpTypeId(EmployeeTypeMst employeeTypeMst) {
        List<EmployeeMst> employeeMasterList = employeeMstDao.findEmployeeByEmpType(employeeTypeMst);

        return employeeMasterList;
    }

    @Transactional(readOnly = true)
    @Override
    public List<EmployeeMst> findNonDeletedEmployeeByEmpTypeId(EmployeeTypeMst employeeTypeMst) {
        List<EmployeeMst> employeeMasterList = employeeMstDao.findNonDeletedEmployeeByEmpType(employeeTypeMst);
        return employeeMasterList;
    }

    @Transactional(readOnly = true)
    @Override
    public EmployeeMst findAllEmployeeByUserId(String userLoginId) {
        EmployeeMst employeeMst = employeeMstDao.findEmployeeByUserId(userLoginId);

        return employeeMst;
    }

    @Transactional(readOnly = true)
    @Override
    public EmployeeMst findEmployeeById_FirstName(Integer employeeId, String firstName) {
        return employeeMstDao.findEmployeeById_FirstName(employeeId, firstName);
    }

    @Override
    public List<EmployeeMst> findNonDeletedEmployeeByEmpType(String empType) {
        List<EmployeeMst> employeeMasterList = employeeMstDao.findNonDeletedEmployeeByEmpType(empType);
        return employeeMasterList;
    }

}
