package com.rank.ccms.entities;

import java.sql.Timestamp;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(CallMst.class)
public abstract class CallMst_ {

	public static volatile SingularAttribute<CallMst, String> deviceIp;
	public static volatile SingularAttribute<CallMst, Timestamp> routingCallTime;
	public static volatile SingularAttribute<CallMst, String> deviceOs;
	public static volatile SingularAttribute<CallMst, Timestamp> customerPickupTime;
	public static volatile SingularAttribute<CallMst, String> deviceId;
	public static volatile SingularAttribute<CallMst, String> deviceName;
	public static volatile SingularAttribute<CallMst, String> callType;
	public static volatile SingularAttribute<CallMst, String> callLatitude;
	public static volatile SingularAttribute<CallMst, Boolean> activeFlg;
	public static volatile SingularAttribute<CallMst, Integer> hashCode;
	public static volatile SingularAttribute<CallMst, String> callStatus;
	public static volatile SingularAttribute<CallMst, String> callOption;
	public static volatile SingularAttribute<CallMst, String> custId;
	public static volatile SingularAttribute<CallMst, CustomerMst> customerId;
	public static volatile SingularAttribute<CallMst, Timestamp> startTime;
	public static volatile SingularAttribute<CallMst, Timestamp> agentPickupTime;
	public static volatile SingularAttribute<CallMst, Long> id;
	public static volatile SingularAttribute<CallMst, Long> serviceId;
	public static volatile SingularAttribute<CallMst, String> iMEIno;
	public static volatile CollectionAttribute<CallMst, CallRecords> callRecordsCollection;
	public static volatile SingularAttribute<CallMst, String> callLongitude;
	public static volatile SingularAttribute<CallMst, String> staticIp;
	public static volatile SingularAttribute<CallMst, Boolean> deleteFlg;
	public static volatile SingularAttribute<CallMst, Long> languageId;
	public static volatile SingularAttribute<CallMst, String> callMedium;
	public static volatile SingularAttribute<CallMst, Timestamp> customerHangupTime;
	public static volatile SingularAttribute<CallMst, String> callLocation;
	public static volatile SingularAttribute<CallMst, String> roomName;
	public static volatile SingularAttribute<CallMst, Timestamp> customerRequestTime;
	public static volatile CollectionAttribute<CallMst, CallDtl> callDtlCollection;
	public static volatile SingularAttribute<CallMst, Timestamp> endTime;
	public static volatile CollectionAttribute<CallMst, FeedbackDtl> feedbackDtlCollection;
	public static volatile SingularAttribute<CallMst, String> deviceBrand;
	public static volatile SingularAttribute<CallMst, Long> categoryId;
	public static volatile CollectionAttribute<CallMst, ForwardedCall> forwardedCallCollection;

}

