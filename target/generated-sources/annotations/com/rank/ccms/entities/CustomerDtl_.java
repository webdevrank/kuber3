package com.rank.ccms.entities;

import java.util.Date;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(CustomerDtl.class)
public abstract class CustomerDtl_ {

	public static volatile SingularAttribute<CustomerDtl, String> address;
	public static volatile SingularAttribute<CustomerDtl, String> occupation;
	public static volatile SingularAttribute<CustomerDtl, String> education;
	public static volatile SingularAttribute<CustomerDtl, String> gender;
	public static volatile SingularAttribute<CustomerDtl, String> fullName;
	public static volatile SingularAttribute<CustomerDtl, String> maritailStatus;
	public static volatile SingularAttribute<CustomerDtl, String> salary;
	public static volatile SingularAttribute<CustomerDtl, String> phoneNo;
	public static volatile SingularAttribute<CustomerDtl, String> customerSign;
	public static volatile SingularAttribute<CustomerDtl, String> customerType;
	public static volatile SingularAttribute<CustomerDtl, String> nationality;
	public static volatile SingularAttribute<CustomerDtl, String> nationalId;
	public static volatile SingularAttribute<CustomerDtl, String> customerSignCord;
	public static volatile SingularAttribute<CustomerDtl, String> utilityBill;
	public static volatile SingularAttribute<CustomerDtl, Date> dob;
	public static volatile SingularAttribute<CustomerDtl, BankMst> bankMstId;
	public static volatile SingularAttribute<CustomerDtl, Integer> hashCode;
	public static volatile SingularAttribute<CustomerDtl, Long> id;
	public static volatile SingularAttribute<CustomerDtl, String> customerImage;
	public static volatile CollectionAttribute<CustomerDtl, CustomerAccDtl> customerAccDtlCollection;
	public static volatile SingularAttribute<CustomerDtl, String> email;

}

