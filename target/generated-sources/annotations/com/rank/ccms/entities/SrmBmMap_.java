package com.rank.ccms.entities;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(SrmBmMap.class)
public abstract class SrmBmMap_ {

	public static volatile SingularAttribute<SrmBmMap, EmployeeMst> srmId;
	public static volatile SingularAttribute<SrmBmMap, Boolean> activeFlg;
	public static volatile SingularAttribute<SrmBmMap, Boolean> deleteFlg;
	public static volatile SingularAttribute<SrmBmMap, EmployeeMst> bmId;
	public static volatile SingularAttribute<SrmBmMap, Long> id;

}

