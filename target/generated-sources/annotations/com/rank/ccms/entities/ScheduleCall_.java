package com.rank.ccms.entities;

import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ScheduleCall.class)
public abstract class ScheduleCall_ {

	public static volatile SingularAttribute<ScheduleCall, Long> callmstid;
	public static volatile SingularAttribute<ScheduleCall, Timestamp> customerTime;
	public static volatile SingularAttribute<ScheduleCall, String> executeStatus;
	public static volatile SingularAttribute<ScheduleCall, Boolean> deleteFlg;
	public static volatile SingularAttribute<ScheduleCall, Long> schedulerId;
	public static volatile SingularAttribute<ScheduleCall, Long> language;
	public static volatile SingularAttribute<ScheduleCall, EmployeeMst> employeeId;
	public static volatile SingularAttribute<ScheduleCall, String> callMedium;
	public static volatile SingularAttribute<ScheduleCall, Long> supervisorId;
	public static volatile SingularAttribute<ScheduleCall, Date> creationDatetime;
	public static volatile SingularAttribute<ScheduleCall, String> callType;
	public static volatile SingularAttribute<ScheduleCall, Boolean> activeFlg;
	public static volatile SingularAttribute<ScheduleCall, Date> scheduleEndDate;
	public static volatile SingularAttribute<ScheduleCall, Long> service;
	public static volatile SingularAttribute<ScheduleCall, Integer> hashCode;
	public static volatile SingularAttribute<ScheduleCall, CustomerMst> customerId;
	public static volatile SingularAttribute<ScheduleCall, Date> scheduleDate;
	public static volatile SingularAttribute<ScheduleCall, String> scheduledBy;
	public static volatile SingularAttribute<ScheduleCall, Long> id;
	public static volatile SingularAttribute<ScheduleCall, Long> category;

}

