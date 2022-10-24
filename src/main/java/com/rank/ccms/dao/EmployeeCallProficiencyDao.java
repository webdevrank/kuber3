package com.rank.ccms.dao;

import com.rank.ccms.entities.EmployeeCallProficiency;
import com.rank.ccms.entities.EmployeeMst;
import java.util.List;
import java.util.Set;

public interface EmployeeCallProficiencyDao extends GenericDao<EmployeeCallProficiency> {

    @Override
    public EmployeeCallProficiency findById(Long id);

    public List<EmployeeCallProficiency> findByPrimaryCategory(String category);

    public List<EmployeeCallProficiency> findBySecondaryCategory(String category);

    public List<EmployeeCallProficiency> findByPrimaryCategoryAndEmployeeList(String category, Set<EmployeeMst> employeeList);

    public List<EmployeeCallProficiency> findBySecondaryCategoryAndEmployeeList(String category, Set<EmployeeMst> employeeList);

    public List<EmployeeCallProficiency> findByPrimaryService(String service);

    public List<EmployeeCallProficiency> findBySecondaryService(String service);

    public List<EmployeeCallProficiency> findByPrimaryServiceAndEmployeeList(String service, Set<EmployeeMst> employeeList);

    public List<EmployeeCallProficiency> findBySecondaryServiceAndEmployeeList(String service, Set<EmployeeMst> employeeList);

    public List<EmployeeCallProficiency> findByPrimaryLanguage(String language);

    public List<EmployeeCallProficiency> findBySecondaryLanguage(String language);

    public List<EmployeeCallProficiency> findByPrimaryLanguageAndEmployeeList(String language, Set<EmployeeMst> employeeList);

    public List<EmployeeCallProficiency> findBySecondaryLanguageAndEmployeeList(String language, Set<EmployeeMst> employeeList);

    public List<EmployeeCallProficiency> findByPrimarySkillAndEmployeeList(String skill, Set<EmployeeMst> employeeList);

    public List<EmployeeCallProficiency> findBySecondarySkillAndEmployeeList(String skill, Set<EmployeeMst> employeeList);

    public List<EmployeeCallProficiency> findCallProficiencyListByEmployeeMst(EmployeeMst employeeMst);

    public List<EmployeeCallProficiency> findByPrimaryCategoryVideorText(Long empId);

    public List<EmployeeCallProficiency> findEmployeeCallProficiencyBySecondaryLanguage(String language);

    public List<EmployeeCallProficiency> findEmployeeCallProficiencyByPrimaryLanguage(String language);

    public List<EmployeeCallProficiency> findEmployeeCallProficiencyBySecondaryService(String service);

    public List<EmployeeCallProficiency> findEmployeeCallProficiencyByPrimaryService(String service);

    public List<EmployeeCallProficiency> findEmployeeCallProficiencyBySecondarySegment(String segment);

    public List<EmployeeCallProficiency> findEmployeeCallProficiencyByPrimarySegment(String segment);

}
