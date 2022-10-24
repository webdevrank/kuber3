package com.rank.ccms.entities;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(EmployeeProficiencyMap.class)
public abstract class EmployeeProficiencyMap_ {

	public static volatile SingularAttribute<EmployeeProficiencyMap, Long> skillId;
	public static volatile SingularAttribute<EmployeeProficiencyMap, EmployeeMst> empId;
	public static volatile SingularAttribute<EmployeeProficiencyMap, Boolean> activeFlg;
	public static volatile SingularAttribute<EmployeeProficiencyMap, Long> primarySkill;
	public static volatile SingularAttribute<EmployeeProficiencyMap, Integer> hashCode;
	public static volatile SingularAttribute<EmployeeProficiencyMap, Boolean> deleteFlg;
	public static volatile SingularAttribute<EmployeeProficiencyMap, Long> id;
	public static volatile SingularAttribute<EmployeeProficiencyMap, Long> empTypId;
	public static volatile SingularAttribute<EmployeeProficiencyMap, String> type;
	public static volatile SingularAttribute<EmployeeProficiencyMap, Long> secondarySkill;

}

