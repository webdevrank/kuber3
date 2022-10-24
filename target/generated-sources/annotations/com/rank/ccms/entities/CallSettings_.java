package com.rank.ccms.entities;

import java.util.Date;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(CallSettings.class)
public abstract class CallSettings_ {

	public static volatile SingularAttribute<CallSettings, Date> serviceEndTime;
	public static volatile SingularAttribute<CallSettings, Boolean> activeFlg;
	public static volatile SingularAttribute<CallSettings, Date> serviceStartTime;
	public static volatile SingularAttribute<CallSettings, String> serviceDay;
	public static volatile SingularAttribute<CallSettings, Boolean> deleteFlg;
	public static volatile SingularAttribute<CallSettings, Boolean> otp;
	public static volatile SingularAttribute<CallSettings, Long> id;

}

