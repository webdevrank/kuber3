package com.rank.ccms.entities;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(RmSrmMap.class)
public abstract class RmSrmMap_ {

	public static volatile SingularAttribute<RmSrmMap, EmployeeMst> srmId;
	public static volatile SingularAttribute<RmSrmMap, EmployeeMst> rmId;
	public static volatile SingularAttribute<RmSrmMap, Boolean> activeFlg;
	public static volatile SingularAttribute<RmSrmMap, Boolean> deleteFlg;
	public static volatile SingularAttribute<RmSrmMap, Long> id;

}

