package com.rank.ccms.entities;

import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(BankMst.class)
public abstract class BankMst_ {

	public static volatile SingularAttribute<BankMst, String> branchCode;
	public static volatile SingularAttribute<BankMst, String> address;
	public static volatile CollectionAttribute<BankMst, CustomerDtl> customerDtlCollection;
	public static volatile SingularAttribute<BankMst, String> bankName;
	public static volatile SingularAttribute<BankMst, Long> id;
	public static volatile SingularAttribute<BankMst, String> ifscCode;

}

