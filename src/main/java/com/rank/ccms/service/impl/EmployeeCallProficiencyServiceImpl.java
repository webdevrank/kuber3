package com.rank.ccms.service.impl;

import com.rank.ccms.dao.EmployeeCallProficiencyDao;
import com.rank.ccms.entities.EmployeeCallProficiency;
import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.service.EmployeeCallProficiencyService;
import java.util.List;
import java.util.Set;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("employeeCallProficiencyService")
public class EmployeeCallProficiencyServiceImpl implements EmployeeCallProficiencyService {

    Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    private EmployeeCallProficiencyDao employeeCallProficiencyDao;

    @Transactional
    @Override
    public EmployeeCallProficiency saveEmployeeCallProficiency(EmployeeCallProficiency employeeCallProficiency) {
        logger.debug("Activity Details Not Applicable for the EmployeeCallProficiency");
        if (employeeCallProficiency.getId() == null) {
            employeeCallProficiency = employeeCallProficiencyDao.saveRow(employeeCallProficiency);
        } else {
            EmployeeCallProficiency existingEmployeeCallProficiency = employeeCallProficiencyDao.findById(employeeCallProficiency.getId());
            if (existingEmployeeCallProficiency != null) {
                if (existingEmployeeCallProficiency != employeeCallProficiency) {
                    existingEmployeeCallProficiency.setId(employeeCallProficiency.getId());
                    existingEmployeeCallProficiency.setEmpId(employeeCallProficiency.getEmpId());
                    existingEmployeeCallProficiency.setLanguageP(employeeCallProficiency.getLanguageP());
                    existingEmployeeCallProficiency.setLanguageS(employeeCallProficiency.getLanguageS());
                    existingEmployeeCallProficiency.setCategoryP(employeeCallProficiency.getCategoryP());
                    existingEmployeeCallProficiency.setCategoryS(employeeCallProficiency.getCategoryS());
                    existingEmployeeCallProficiency.setServiceP(employeeCallProficiency.getServiceP());
                    existingEmployeeCallProficiency.setServiceS(employeeCallProficiency.getServiceS());
                }
                try {
                    employeeCallProficiency = employeeCallProficiencyDao.mergeRow(existingEmployeeCallProficiency);
                } catch (Exception e) {
                }
            } else {
                employeeCallProficiency = employeeCallProficiencyDao.saveRow(employeeCallProficiency);
            }
        }
        return employeeCallProficiency;
    }

    @Transactional(readOnly = true)
    @Override
    public List<EmployeeCallProficiency> findAllEmployeeCallProficiency() {
        return new java.util.ArrayList<>(employeeCallProficiencyDao.findAll());
    }

    @Transactional(readOnly = true)
    @Override
    public EmployeeCallProficiency findEmployeeCallProficiencyById(Long id) {
        return employeeCallProficiencyDao.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<EmployeeCallProficiency> findEmployeeCallProficiencyByPrimarySegment(String segment) {
        List<EmployeeCallProficiency> employeeCallProficiencyList = employeeCallProficiencyDao.findEmployeeCallProficiencyByPrimarySegment(segment);

        return employeeCallProficiencyList;
    }

    @Transactional(readOnly = true)
    @Override
    public List<EmployeeCallProficiency> findEmployeeCallProficiencyByPrimarySegmentAndEmployeeList(String segment, Set<EmployeeMst> employeeList) {

        List<EmployeeCallProficiency> employeeCallProficiencyList = employeeCallProficiencyDao.findByPrimaryCategoryAndEmployeeList(segment, employeeList);
        return employeeCallProficiencyList;
    }

    @Transactional(readOnly = true)
    @Override
    public List<EmployeeCallProficiency> findEmployeeCallProficiencyBySecondarySegment(String segment) {

        List<EmployeeCallProficiency> employeeCallProficiencyList = employeeCallProficiencyDao.findEmployeeCallProficiencyBySecondarySegment(segment);

        return employeeCallProficiencyList;
    }

    @Transactional(readOnly = true)
    @Override
    public List<EmployeeCallProficiency> findEmployeeCallProficiencyBySecondarySegmentAndEmployeeList(String segment, Set<EmployeeMst> employeeList) {

        List<EmployeeCallProficiency> employeeCallProficiencyList = employeeCallProficiencyDao.findBySecondaryCategoryAndEmployeeList(segment, employeeList);
        return employeeCallProficiencyList;
    }

    @Transactional(readOnly = true)
    @Override
    public List<EmployeeCallProficiency> findEmployeeCallProficiencyByPrimaryService(String service) {

        List<EmployeeCallProficiency> employeeCallProficiencyList = employeeCallProficiencyDao.findEmployeeCallProficiencyByPrimaryService(service);

        return employeeCallProficiencyList;
    }

    @Transactional(readOnly = true)
    @Override
    public List<EmployeeCallProficiency> findEmployeeCallProficiencyByPrimaryServiceAndEmployeeList(String service, Set<EmployeeMst> employeeList) {

        List<EmployeeCallProficiency> employeeCallProficiencyList = employeeCallProficiencyDao.findByPrimaryServiceAndEmployeeList(service, employeeList);
        return employeeCallProficiencyList;
    }

    @Transactional(readOnly = true)
    @Override
    public List<EmployeeCallProficiency> findEmployeeCallProficiencyBySecondaryService(String service) {

        List<EmployeeCallProficiency> employeeCallProficiencyList = employeeCallProficiencyDao.findEmployeeCallProficiencyBySecondaryService(service);

        return employeeCallProficiencyList;
    }

    @Transactional(readOnly = true)
    @Override
    public List<EmployeeCallProficiency> findEmployeeCallProficiencyBySecondaryServiceAndEmployeeList(String service, Set<EmployeeMst> employeeList) {

        List<EmployeeCallProficiency> employeeCallProficiencyList = employeeCallProficiencyDao.findBySecondaryServiceAndEmployeeList(service, employeeList);
        return employeeCallProficiencyList;
    }

    @Transactional(readOnly = true)
    @Override
    public List<EmployeeCallProficiency> findEmployeeCallProficiencyByPrimaryLanguage(String language) {

        List<EmployeeCallProficiency> employeeCallProficiencyList = employeeCallProficiencyDao.findEmployeeCallProficiencyByPrimaryLanguage(language);

        return employeeCallProficiencyList;
    }

    @Transactional(readOnly = true)
    @Override
    public List<EmployeeCallProficiency> findEmployeeCallProficiencyByPrimaryLanguageAndEmployeeList(String language, Set<EmployeeMst> employeeList) {

        List<EmployeeCallProficiency> employeeCallProficiencyList = employeeCallProficiencyDao.findByPrimaryLanguageAndEmployeeList(language, employeeList);
        return employeeCallProficiencyList;
    }

    @Transactional(readOnly = true)
    @Override
    public List<EmployeeCallProficiency> findEmployeeCallProficiencyBySecondaryLanguage(String language) {

        List<EmployeeCallProficiency> employeeCallProficiencyList = employeeCallProficiencyDao.findEmployeeCallProficiencyBySecondaryLanguage(language);

        return employeeCallProficiencyList;
    }

    @Transactional(readOnly = true)
    @Override
    public List<EmployeeCallProficiency> findEmployeeCallProficiencyBySecondaryLanguageAndEmployeeList(String language, Set<EmployeeMst> employeeList) {

        List<EmployeeCallProficiency> employeeCallProficiencyList = employeeCallProficiencyDao.findBySecondaryLanguageAndEmployeeList(language, employeeList);
        return employeeCallProficiencyList;
    }

    @Transactional
    @Override
    public void deleteActuallyFromDb(EmployeeCallProficiency employeeCallProficiency) {
        logger.debug("Logger Not Applicable for the EmployeeCallProficiency");
        employeeCallProficiencyDao.deleteRow(employeeCallProficiency);

    }

    @Override
    public List<EmployeeCallProficiency> findCallProficiencyListByEmployeeMst(EmployeeMst employeeMst) {
        return employeeCallProficiencyDao.findCallProficiencyListByEmployeeMst(employeeMst);
    }

    @Override
    public List<EmployeeCallProficiency> findEmployeeCallProficiencyByPrimarySkillAndEmployeeList(String skill, Set<EmployeeMst> employeeList) {
        return employeeCallProficiencyDao.findByPrimarySkillAndEmployeeList(skill, employeeList);
    }

    @Override
    public List<EmployeeCallProficiency> findEmployeeCallProficiencyBySecondarySkillAndEmployeeList(String skill, Set<EmployeeMst> employeeList) {
        return employeeCallProficiencyDao.findBySecondarySkillAndEmployeeList(skill, employeeList);
    }

    @Override
    public List<EmployeeCallProficiency> findByPrimaryCategoryVideorText(Long empId) {
        return employeeCallProficiencyDao.findByPrimaryCategoryVideorText(empId);

    }

}
