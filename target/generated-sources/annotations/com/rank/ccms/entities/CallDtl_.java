package com.rank.ccms.entities;

import java.sql.Timestamp;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(CallDtl.class)
public abstract class CallDtl_ {

	public static volatile SingularAttribute<CallDtl, CallMst> callMstId;
	public static volatile SingularAttribute<CallDtl, Boolean> activeFlg;
	public static volatile SingularAttribute<CallDtl, String> agentComments;
	public static volatile SingularAttribute<CallDtl, Integer> hashCode;
	public static volatile SingularAttribute<CallDtl, Boolean> deleteFlg;
	public static volatile SingularAttribute<CallDtl, EmployeeMst> handeledById;
	public static volatile SingularAttribute<CallDtl, Timestamp> startTime;
	public static volatile SingularAttribute<CallDtl, Long> id;
	public static volatile SingularAttribute<CallDtl, Timestamp> endTime;
	public static volatile SingularAttribute<CallDtl, String> callTypeInfo;

}

