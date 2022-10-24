package com.rank.ccms.service.impl;

import com.rank.ccms.dao.EmployeeProficiencyMapDao;
import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.entities.EmployeeProficiencyMap;
import com.rank.ccms.service.EmployeeProficiencyMapService;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("employeeProficiencyMapService")
public class EmployeeProficiencyMapServiceImpl implements EmployeeProficiencyMapService {

    Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private EmployeeProficiencyMapDao employeeProficiencyMapDao;

    @Transactional
    @Override
    public EmployeeProficiencyMap saveEmployeeProficiency(EmployeeProficiencyMap employeeProficiencyMap, EmployeeMst employeeMst) {

        if (employeeProficiencyMap.getId() == null) {
            employeeProficiencyMap = employeeProficiencyMapDao.saveRow(employeeProficiencyMap);
        } else {
            EmployeeProficiencyMap existingemployeeProficiencyMap = employeeProficiencyMapDao.findById(employeeProficiencyMap.getId());
            if (existingemployeeProficiencyMap != null) {
                if (existingemployeeProficiencyMap != employeeProficiencyMap) {
                    existingemployeeProficiencyMap.setId(employeeProficiencyMap.getId());
                    existingemployeeProficiencyMap.setEmpId(employeeProficiencyMap.getEmpId());
                    existingemployeeProficiencyMap.setActiveFlg(employeeProficiencyMap.getActiveFlg());
                    existingemployeeProficiencyMap.setDeleteFlg(employeeProficiencyMap.getDeleteFlg());
                    existingemployeeProficiencyMap.setPrimarySkill(employeeProficiencyMap.getPrimarySkill());
                    existingemployeeProficiencyMap.setSecondarySkill(employeeProficiencyMap.getSecondarySkill());
                    existingemployeeProficiencyMap.setSkillId(employeeProficiencyMap.getSkillId());
                    existingemployeeProficiencyMap.setType(employeeProficiencyMap.getType());
                }
                employeeProficiencyMap = employeeProficiencyMapDao.mergeRow(existingemployeeProficiencyMap);
            } else {
                employeeProficiencyMap = employeeProficiencyMapDao.saveRow(employeeProficiencyMap);
            }
        }

        return employeeProficiencyMap;
    }

    @Transactional
    @Override
    public List<EmployeeProficiencyMap> findAllNonDeletedEmployeeProficiencyMaps() {
        return new ArrayList<>(employeeProficiencyMapDao.findAllNonDeleted());
    }

    @Transactional(readOnly = true)
    @Override
    public List<EmployeeProficiencyMap> findAllActivenNonDeletedEmployeeProficiencyMapsByEmpId(Long empPkId) {
        return new ArrayList<>(employeeProficiencyMapDao.findAllActivenNonDeletedEmployeeProficiencyMapsByEmpId(empPkId));
    }

    @Transactional(readOnly = true)
    @Override
    public List<EmployeeProficiencyMap> findAllActivenNonDeletedEmployeeProficiencyMapsByEmpId(Long empPkId, Long empTypPkId) {
        return new ArrayList<>(employeeProficiencyMapDao.findAllActivenNonDeletedEmployeeProficiencyMapsByEmpId(empPkId, empTypPkId));
    }

    @Override
    public EmployeeProficiencyMap findNonDeletedEmployeeProficiencyById(Long id) {
        return employeeProficiencyMapDao.findNonDeletedById(id);
    }

    @Override
    public List<EmployeeProficiencyMap> findEmployeeProficiencyMapListBySkillId(String typeName, Long id) {
        return employeeProficiencyMapDao.findEmployeeProficiencyMapsBySkillId(typeName, id);
    }

    @Override
    public List<EmployeeProficiencyMap> findEmployeeProficiencyMapsBySkillId(String type, Integer skillId, Integer empTypPkId) {
        return employeeProficiencyMapDao.findEmployeeProficiencyMapsBySkillId(type, skillId, empTypPkId);
    }

}
