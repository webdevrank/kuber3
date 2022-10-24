package com.rank.ccms.service;

import com.rank.ccms.entities.EmployeeCallProficiency;
import com.rank.ccms.entities.EmployeeMst;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

public interface EmployeeCallProficiencyService extends Serializable {

    EmployeeCallProficiency saveEmployeeCallProficiency(EmployeeCallProficiency employeeCallProficiency);

    List<EmployeeCallProficiency> findAllEmployeeCallProficiency();

    EmployeeCallProficiency findEmployeeCallProficiencyById(Long id);

    List<EmployeeCallProficiency> findEmployeeCallProficiencyByPrimarySegment(String segment);

    List<EmployeeCallProficiency> findEmployeeCallProficiencyBySecondarySegment(String segment);

    List<EmployeeCallProficiency> findEmployeeCallProficiencyByPrimaryService(String service);

    List<EmployeeCallProficiency> findEmployeeCallProficiencyBySecondaryService(String service);

    List<EmployeeCallProficiency> findEmployeeCallProficiencyByPrimaryLanguage(String language);

    List<EmployeeCallProficiency> findEmployeeCallProficiencyBySecondaryLanguage(String language);

    List<EmployeeCallProficiency> findEmployeeCallProficiencyByPrimarySegmentAndEmployeeList(String segment, Set<EmployeeMst> employeeList);

    List<EmployeeCallProficiency> findEmployeeCallProficiencyBySecondarySegmentAndEmployeeList(String segment, Set<EmployeeMst> employeeList);

    List<EmployeeCallProficiency> findEmployeeCallProficiencyByPrimaryServiceAndEmployeeList(String service, Set<EmployeeMst> employeeList);

    List<EmployeeCallProficiency> findEmployeeCallProficiencyBySecondaryServiceAndEmployeeList(String service, Set<EmployeeMst> employeeList);

    List<EmployeeCallProficiency> findEmployeeCallProficiencyByPrimaryLanguageAndEmployeeList(String language, Set<EmployeeMst> employeeList);

    List<EmployeeCallProficiency> findEmployeeCallProficiencyBySecondaryLanguageAndEmployeeList(String language, Set<EmployeeMst> employeeList);

    List<EmployeeCallProficiency> findEmployeeCallProficiencyByPrimarySkillAndEmployeeList(String skill, Set<EmployeeMst> employeeList);

    List<EmployeeCallProficiency> findEmployeeCallProficiencyBySecondarySkillAndEmployeeList(String skill, Set<EmployeeMst> employeeList);

    void deleteActuallyFromDb(EmployeeCallProficiency employeeCallProficiency);

    List<EmployeeCallProficiency> findCallProficiencyListByEmployeeMst(EmployeeMst employeeMst);

    List<EmployeeCallProficiency> findByPrimaryCategoryVideorText(Long empId);

}
