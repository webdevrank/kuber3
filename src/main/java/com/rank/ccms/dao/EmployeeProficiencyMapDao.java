package com.rank.ccms.dao;

import com.rank.ccms.entities.EmployeeProficiencyMap;
import java.util.List;

public interface EmployeeProficiencyMapDao extends GenericDao<EmployeeProficiencyMap> {

    public List<EmployeeProficiencyMap> findAllActivenNonDeletedEmployeeProficiencyMapsByEmpId(Long empPkId);

    public List<EmployeeProficiencyMap> findAllActivenNonDeletedEmployeeProficiencyMapsByEmpId(Long empPkId, Long empTypPkId);

    public List<EmployeeProficiencyMap> findEmployeeProficiencyMapsByEmpPkId(Long empPkId);

    public List<EmployeeProficiencyMap> findEmployeeProficiencyMapsBySkillId(String type, Long skillId);

    public List<EmployeeProficiencyMap> findEmployeeProficiencyMapsBySkillId(String type, Integer skillId, Integer empTypPkId);

}
