package com.rank.ccms.entities;

import java.util.Date;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(DownTime.class)
public abstract class DownTime_ {

	public static volatile SingularAttribute<DownTime, String> reason;
	public static volatile SingularAttribute<DownTime, Boolean> activeFlg;
	public static volatile SingularAttribute<DownTime, Boolean> deleteFlg;
	public static volatile SingularAttribute<DownTime, Date> startTime;
	public static volatile SingularAttribute<DownTime, Long> id;
	public static volatile SingularAttribute<DownTime, Date> endTime;

}

