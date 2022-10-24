package com.rank.ccms.entities;

import java.util.Date;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(EmployeeMst.class)
public abstract class EmployeeMst_ {

	public static volatile CollectionAttribute<EmployeeMst, EmployeeCallProficiency> employeeCallProficiencyCollection;
	public static volatile SingularAttribute<EmployeeMst, String> lastName;
	public static volatile SingularAttribute<EmployeeMst, String> country;
	public static volatile SingularAttribute<EmployeeMst, Date> empDob;
	public static volatile SingularAttribute<EmployeeMst, String> loginId;
	public static volatile CollectionAttribute<EmployeeMst, ScheduleCall> scheduleCallCollection;
	public static volatile CollectionAttribute<EmployeeMst, RmSrmMap> rmSrmMapCollection1;
	public static volatile SingularAttribute<EmployeeMst, String> city;
	public static volatile CollectionAttribute<EmployeeMst, SrmBmMap> srmBmMapCollection1;
	public static volatile SingularAttribute<EmployeeMst, EmployeeTypeMst> empTypId;
	public static volatile CollectionAttribute<EmployeeMst, TenancyEmployeeMap> tenancyEmployeeMapCollection;
	public static volatile SingularAttribute<EmployeeMst, String> loginPasswd;
	public static volatile SingularAttribute<EmployeeMst, String> addrsLine1;
	public static volatile SingularAttribute<EmployeeMst, String> addrsLine2;
	public static volatile SingularAttribute<EmployeeMst, Integer> pin;
	public static volatile SingularAttribute<EmployeeMst, Long> officePhone;
	public static volatile SingularAttribute<EmployeeMst, Boolean> activeFlg;
	public static volatile CollectionAttribute<EmployeeMst, SrmBmMap> srmBmMapCollection;
	public static volatile SingularAttribute<EmployeeMst, Integer> hashCode;
	public static volatile SingularAttribute<EmployeeMst, Long> id;
	public static volatile SingularAttribute<EmployeeMst, String> stateNm;
	public static volatile CollectionAttribute<EmployeeMst, EmployeeProficiencyMap> employeeProficiencyMapCollection;
	public static volatile SingularAttribute<EmployeeMst, String> email;
	public static volatile CollectionAttribute<EmployeeMst, RmSrmMap> rmSrmMapCollection;
	public static volatile CollectionAttribute<EmployeeMst, EmployeeCallStatus> employeeCallStatusCollection;
	public static volatile CollectionAttribute<EmployeeMst, CallRecords> callRecordsCollection;
	public static volatile SingularAttribute<EmployeeMst, Long> homePhone;
	public static volatile SingularAttribute<EmployeeMst, Boolean> deleteFlg;
	public static volatile SingularAttribute<EmployeeMst, String> midName;
	public static volatile CollectionAttribute<EmployeeMst, EmployeeActivityDtl> employeeActivityDtlCollection;
	public static volatile SingularAttribute<EmployeeMst, Boolean> deactivateFlg;
	public static volatile SingularAttribute<EmployeeMst, String> firstName;
	public static volatile CollectionAttribute<EmployeeMst, CustomerRmMap> customerRmMapCollection;
	public static volatile CollectionAttribute<EmployeeMst, CallDtl> callDtlCollection;
	public static volatile SingularAttribute<EmployeeMst, Long> cellPhone;
	public static volatile CollectionAttribute<EmployeeMst, ForwardedCall> forwardedCallCollection;

}

