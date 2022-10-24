package com.rank.ccms.entities;

import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ReasonMst.class)
public abstract class ReasonMst_ {

	public static volatile SingularAttribute<ReasonMst, Boolean> activeFlg;
	public static volatile SingularAttribute<ReasonMst, Integer> hashCode;
	public static volatile SingularAttribute<ReasonMst, Boolean> deleteFlg;
	public static volatile CollectionAttribute<ReasonMst, EmployeeActivityDtl> employeeActivityDtlCollection;
	public static volatile SingularAttribute<ReasonMst, Long> id;
	public static volatile SingularAttribute<ReasonMst, String> type;
	public static volatile SingularAttribute<ReasonMst, String> reasonDesc;
	public static volatile SingularAttribute<ReasonMst, String> reasonCd;

}

