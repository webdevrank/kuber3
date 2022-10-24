package com.rank.ccms.entities;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(CallRecords.class)
public abstract class CallRecords_ {

	public static volatile SingularAttribute<CallRecords, CallMst> callId;
	public static volatile SingularAttribute<CallRecords, EmployeeMst> empId;
	public static volatile SingularAttribute<CallRecords, String> externalPlaybackLink;
	public static volatile SingularAttribute<CallRecords, String> conferenceId;
	public static volatile SingularAttribute<CallRecords, Integer> hashCode;
	public static volatile SingularAttribute<CallRecords, String> customerId;
	public static volatile SingularAttribute<CallRecords, String> chatText;
	public static volatile SingularAttribute<CallRecords, Long> id;
	public static volatile SingularAttribute<CallRecords, String> recorderId;
	public static volatile SingularAttribute<CallRecords, String> roomName;

}

