package com.rank.ccms.entities;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(CustomerRmMap.class)
public abstract class CustomerRmMap_ {

	public static volatile SingularAttribute<CustomerRmMap, EmployeeMst> rmId;
	public static volatile SingularAttribute<CustomerRmMap, Boolean> activeFlg;
	public static volatile SingularAttribute<CustomerRmMap, Boolean> deleteFlg;
	public static volatile SingularAttribute<CustomerRmMap, CustomerMst> custId;
	public static volatile SingularAttribute<CustomerRmMap, Long> id;

}

