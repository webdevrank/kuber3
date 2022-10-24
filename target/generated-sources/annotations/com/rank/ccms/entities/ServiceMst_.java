package com.rank.ccms.entities;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(ServiceMst.class)
public abstract class ServiceMst_ {

	public static volatile SingularAttribute<ServiceMst, String> serviceDesc;
	public static volatile SingularAttribute<ServiceMst, Boolean> activeFlg;
	public static volatile SingularAttribute<ServiceMst, Integer> hashCode;
	public static volatile SingularAttribute<ServiceMst, Boolean> deleteFlg;
	public static volatile SingularAttribute<ServiceMst, String> serviceCd;
	public static volatile SingularAttribute<ServiceMst, Long> id;
	public static volatile SingularAttribute<ServiceMst, String> serviceName;

}

