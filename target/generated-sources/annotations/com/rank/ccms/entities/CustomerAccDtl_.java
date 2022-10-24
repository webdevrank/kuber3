package com.rank.ccms.entities;

import java.util.Date;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(CustomerAccDtl.class)
public abstract class CustomerAccDtl_ {

	public static volatile SingularAttribute<CustomerAccDtl, String> balanceAmt;
	public static volatile SingularAttribute<CustomerAccDtl, String> accNo;
	public static volatile SingularAttribute<CustomerAccDtl, CustomerDtl> customerDtlId;
	public static volatile SingularAttribute<CustomerAccDtl, String> trxType;
	public static volatile SingularAttribute<CustomerAccDtl, Long> id;
	public static volatile SingularAttribute<CustomerAccDtl, Date> effectiveDate;

}

