package com.rank.ccms.service;

import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.entities.EmployeeProficiencyMap;
import java.io.Serializable;
import java.util.List;

public interface EmployeeProficiencyMapService extends Serializable {

    EmployeeProficiencyMap saveEmployeeProficiency(EmployeeProficiencyMap employeeProficiencyMap, EmployeeMst employeeMst);

    List<EmployeeProficiencyMap> findAllNonDeletedEmployeeProficiencyMaps();

    List<EmployeeProficiencyMap> findAllActivenNonDeletedEmployeeProficiencyMapsByEmpId(Long empPkId);

    List<EmployeeProficiencyMap> findAllActivenNonDeletedEmployeeProficiencyMapsByEmpId(Long empPkId, Long empTypPkId);

    List<EmployeeProficiencyMap> findEmployeeProficiencyMapListBySkillId(String typeName, Long id);

    EmployeeProficiencyMap findNonDeletedEmployeeProficiencyById(Long id);

    List<EmployeeProficiencyMap> findEmployeeProficiencyMapsBySkillId(String type, Integer skillId, Integer empTypPkId);

}
