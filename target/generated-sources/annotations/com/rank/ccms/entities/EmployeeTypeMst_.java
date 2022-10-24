package com.rank.ccms.entities;

import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(EmployeeTypeMst.class)
public abstract class EmployeeTypeMst_ {

	public static volatile SingularAttribute<EmployeeTypeMst, String> typeDesc;
	public static volatile SingularAttribute<EmployeeTypeMst, Boolean> activeFlg;
	public static volatile SingularAttribute<EmployeeTypeMst, Integer> hashCode;
	public static volatile SingularAttribute<EmployeeTypeMst, String> typeName;
	public static volatile SingularAttribute<EmployeeTypeMst, Boolean> deleteFlg;
	public static volatile SingularAttribute<EmployeeTypeMst, Long> id;
	public static volatile CollectionAttribute<EmployeeTypeMst, EmployeeMst> employeeMstCollection;

}

