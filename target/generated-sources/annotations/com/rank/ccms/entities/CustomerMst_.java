package com.rank.ccms.entities;

import java.util.Date;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(CustomerMst.class)
public abstract class CustomerMst_ {

	public static volatile SingularAttribute<CustomerMst, String> lastName;
	public static volatile SingularAttribute<CustomerMst, String> fatherName;
	public static volatile CollectionAttribute<CustomerMst, ScheduleCall> scheduleCallCollection;
	public static volatile SingularAttribute<CustomerMst, String> custDesc;
	public static volatile SingularAttribute<CustomerMst, String> salute;
	public static volatile SingularAttribute<CustomerMst, String> custPwd;
	public static volatile SingularAttribute<CustomerMst, String> addrsLine1;
	public static volatile SingularAttribute<CustomerMst, String> addrsLine2;
	public static volatile SingularAttribute<CustomerMst, Boolean> activeFlg;
	public static volatile SingularAttribute<CustomerMst, Integer> hashCode;
	public static volatile SingularAttribute<CustomerMst, String> accountNo;
	public static volatile SingularAttribute<CustomerMst, String> custId;
	public static volatile SingularAttribute<CustomerMst, Long> id;
	public static volatile SingularAttribute<CustomerMst, Date> custDob;
	public static volatile SingularAttribute<CustomerMst, String> email;
	public static volatile CollectionAttribute<CustomerMst, CustomerDeviceDtl> customerDeviceDtlList;
	public static volatile SingularAttribute<CustomerMst, Long> homePhone;
	public static volatile SingularAttribute<CustomerMst, Boolean> deleteFlg;
	public static volatile SingularAttribute<CustomerMst, String> midName;
	public static volatile SingularAttribute<CustomerMst, String> custLang3;
	public static volatile SingularAttribute<CustomerMst, String> custLang2;
	public static volatile SingularAttribute<CustomerMst, String> custLang1;
	public static volatile SingularAttribute<CustomerMst, String> natinality;
	public static volatile SingularAttribute<CustomerMst, String> firstName;
	public static volatile SingularAttribute<CustomerMst, String> custNameAsIdCard;
	public static volatile SingularAttribute<CustomerMst, String> custNationalityAsIdCard;
	public static volatile SingularAttribute<CustomerMst, String> nationalId;
	public static volatile CollectionAttribute<CustomerMst, CustomerRmMap> customerRmMapCollection;
	public static volatile SingularAttribute<CustomerMst, Integer> policyCount;
	public static volatile SingularAttribute<CustomerMst, String> custSeg;
	public static volatile SingularAttribute<CustomerMst, String> custType;
	public static volatile CollectionAttribute<CustomerMst, CallMst> callMstCollection;
	public static volatile SingularAttribute<CustomerMst, Boolean> isRegistered;
	public static volatile CollectionAttribute<CustomerMst, FeedbackDtl> feedbackDtlCollection;
	public static volatile SingularAttribute<CustomerMst, Long> cellPhone;
	public static volatile SingularAttribute<CustomerMst, String> custIdNo;

}

