package com.rank.ccms.entities;

import java.sql.Timestamp;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ForwardedCall.class)
public abstract class ForwardedCall_ {

	public static volatile SingularAttribute<ForwardedCall, CallMst> callId;
	public static volatile SingularAttribute<ForwardedCall, EmployeeMst> empId;
	public static volatile SingularAttribute<ForwardedCall, Timestamp> callPickupTime;
	public static volatile SingularAttribute<ForwardedCall, Boolean> deleteFlg;
	public static volatile SingularAttribute<ForwardedCall, String> entityId;
	public static volatile SingularAttribute<ForwardedCall, String> roomName;
	public static volatile SingularAttribute<ForwardedCall, Long> callDtlId;
	public static volatile SingularAttribute<ForwardedCall, String> roomLink;
	public static volatile SingularAttribute<ForwardedCall, Timestamp> forwardedDatetime;
	public static volatile SingularAttribute<ForwardedCall, Boolean> activeFlg;
	public static volatile SingularAttribute<ForwardedCall, Long> selectedServiceId;
	public static volatile SingularAttribute<ForwardedCall, String> forwardType;
	public static volatile SingularAttribute<ForwardedCall, Integer> hashCode;
	public static volatile SingularAttribute<ForwardedCall, Long> prevEmpId;
	public static volatile SingularAttribute<ForwardedCall, Long> id;
	public static volatile SingularAttribute<ForwardedCall, Long> fromServiceId;
	public static volatile SingularAttribute<ForwardedCall, String> forwardStatus;

}

