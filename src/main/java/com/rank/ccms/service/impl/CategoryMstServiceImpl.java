package com.rank.ccms.service.impl;

import com.rank.ccms.dao.AuditTrailDao;
import com.rank.ccms.dao.CategoryMstDao;
import com.rank.ccms.entities.AuditTrail;
import com.rank.ccms.entities.CategoryMst;
import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.service.CategoryMstService;
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

@Service("categoryMstService")
public class CategoryMstServiceImpl extends SpringBeanAutowiringSupport implements CategoryMstService {

    Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private AuditTrailDao auditTrailDao;

    @Autowired
    private CategoryMstDao categoryMstDao;

    @Transactional
    @Override
    public CategoryMst saveCategoryMst(CategoryMst inputCategoryMst, EmployeeMst employeeMst) {
        CategoryMst outputCategoryMst = null;
        AuditTrail auditTrail = new AuditTrail();
        EntityMetaData entityMetaData = categoryMstDao.getEntityMetaData();
        auditTrail.setTableName(entityMetaData.getRootTableName());
        if (inputCategoryMst.getId() == null) {
            try {
                outputCategoryMst = categoryMstDao.saveRow(inputCategoryMst);
                auditTrail.setTableKey1(entityMetaData.getIdentifierPropertyName() + "=" + outputCategoryMst.getId().toString());
                auditTrail.setCreatedById(employeeMst.getId());
                auditTrail.setCreatedOn(CustomConvert.javaDateToTimeStamp(new Date()));
                auditTrail.setReason("Created/Inserted first time");
                auditTrail.setDateTime(CustomConvert.javaDateToTimeStamp(new Date()));
            } catch (ParseException ex) {

                logger.error("1.Date Parsing Exception:" + ex.getMessage(), ex);
            }
        } else {
            CategoryMst existingCategoryMst = categoryMstDao.findById(inputCategoryMst.getId());
            if (existingCategoryMst != null) {
                if (existingCategoryMst != inputCategoryMst) {
                    try {
                        existingCategoryMst.setId(inputCategoryMst.getId());
                        existingCategoryMst.setCatgName(inputCategoryMst.getCatgName());
                        existingCategoryMst.setCatgDesc(inputCategoryMst.getCatgDesc());
                        existingCategoryMst.setDeleteFlg(inputCategoryMst.getDeleteFlg());
                        existingCategoryMst.setActiveFlg(inputCategoryMst.getActiveFlg());
                        if (existingCategoryMst.getDeleteFlg()) {
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
                    } catch (ParseException e) {

                        logger.error("2.Date Parsing Exception:" + e.getMessage(), e);
                    }
                }

                outputCategoryMst = categoryMstDao.mergeRow(existingCategoryMst);
                auditTrail.setTableKey1(entityMetaData.getIdentifierPropertyName() + "=" + outputCategoryMst.getId().toString());
            } else {
                try {
                    outputCategoryMst = categoryMstDao.saveRow(inputCategoryMst);
                    auditTrail.setTableKey1(entityMetaData.getIdentifierPropertyName() + "=" + outputCategoryMst.getId().toString());
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
        return outputCategoryMst;
    }

    @Override
    public CategoryMst findNonDeletedCategoryMstByCategoryMstId(Long id) {
        return categoryMstDao.findNonDeletedCategoryMstByCategoryMstId(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<CategoryMst> findAllCategoryMsts() {
        return new java.util.ArrayList<>(categoryMstDao.findAll());
    }

    @Transactional(readOnly = true)
    @Override
    public List<CategoryMst> findAllNonDeletedCategoryMsts() {
        return new java.util.ArrayList<>(categoryMstDao.findAllNonDeleted());
    }

    @Transactional(readOnly = true)
    @Override
    public CategoryMst findCategoryMstById(Long id) {
        return categoryMstDao.findById(id);
    }

    @Transactional
    @Override
    public CategoryMst findNonDeletedCategoryMstById(Long id) {
        return categoryMstDao.findNonDeletedById(id);
    }

    @Transactional
    @Override
    public List<CategoryMst> finalAllNonDeletedOrderByDesc() {
        return categoryMstDao.finalAllNonDeletedOrderByDesc();
    }

    @Transactional(readOnly = true)
    @Override
    public CategoryMst findCategoryByCategoryName(String categoryName) {
        CategoryMst catMst = categoryMstDao.findCategoryByCategoryName(categoryName);

        return catMst;
    }

    @Transactional(readOnly = true)
    @Override
    public CategoryMst findCategoryByCategoryCode(String categoryCode) {
        CategoryMst catMst = categoryMstDao.findCategoryByCategoryCode(categoryCode);

        return catMst;
    }

}
