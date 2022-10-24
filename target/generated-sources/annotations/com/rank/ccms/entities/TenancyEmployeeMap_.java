package com.rank.ccms.entities;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(TenancyEmployeeMap.class)
public abstract class TenancyEmployeeMap_ {

	public static volatile SingularAttribute<TenancyEmployeeMap, EmployeeMst> empId;
	public static volatile SingularAttribute<TenancyEmployeeMap, String> roomLink;
	public static volatile SingularAttribute<TenancyEmployeeMap, Integer> hashCode;
	public static volatile SingularAttribute<TenancyEmployeeMap, String> entityId;
	public static volatile SingularAttribute<TenancyEmployeeMap, Long> id;
	public static volatile SingularAttribute<TenancyEmployeeMap, String> roomName;

}

