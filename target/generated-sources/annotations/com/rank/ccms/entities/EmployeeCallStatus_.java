package com.rank.ccms.entities;

import java.sql.Timestamp;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(EmployeeCallStatus.class)
public abstract class EmployeeCallStatus_ {

	public static volatile SingularAttribute<EmployeeCallStatus, EmployeeMst> empId;
	public static volatile SingularAttribute<EmployeeCallStatus, Integer> hashCode;
	public static volatile SingularAttribute<EmployeeCallStatus, Integer> callCount;
	public static volatile SingularAttribute<EmployeeCallStatus, Long> id;
	public static volatile SingularAttribute<EmployeeCallStatus, Timestamp> endTime;
	public static volatile SingularAttribute<EmployeeCallStatus, Boolean> status;

}

