package com.rank.ccms.entities;

import java.sql.Timestamp;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(EmployeeActivityDtl.class)
public abstract class EmployeeActivityDtl_ {

	public static volatile SingularAttribute<EmployeeActivityDtl, Timestamp> notification;
	public static volatile SingularAttribute<EmployeeActivityDtl, EmployeeMst> empId;
	public static volatile SingularAttribute<EmployeeActivityDtl, Long> callMstId;
	public static volatile SingularAttribute<EmployeeActivityDtl, Long> respectiveLoginId;
	public static volatile SingularAttribute<EmployeeActivityDtl, String> activity;
	public static volatile SingularAttribute<EmployeeActivityDtl, ReasonMst> reasonId;
	public static volatile SingularAttribute<EmployeeActivityDtl, Integer> hashCode;
	public static volatile SingularAttribute<EmployeeActivityDtl, Timestamp> startTime;
	public static volatile SingularAttribute<EmployeeActivityDtl, Long> id;
	public static volatile SingularAttribute<EmployeeActivityDtl, Timestamp> endTime;
	public static volatile SingularAttribute<EmployeeActivityDtl, String> reasonDesc;
	public static volatile SingularAttribute<EmployeeActivityDtl, String> reasonCd;

}

