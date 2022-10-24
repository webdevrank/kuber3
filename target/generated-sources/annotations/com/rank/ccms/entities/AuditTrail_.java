package com.rank.ccms.entities;

import java.sql.Timestamp;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(AuditTrail.class)
public abstract class AuditTrail_ {

	public static volatile SingularAttribute<AuditTrail, Timestamp> dateTime;
	public static volatile SingularAttribute<AuditTrail, String> reason;
	public static volatile SingularAttribute<AuditTrail, Long> disabledById;
	public static volatile SingularAttribute<AuditTrail, Timestamp> deletedOn;
	public static volatile SingularAttribute<AuditTrail, Timestamp> updatedOn;
	public static volatile SingularAttribute<AuditTrail, Long> updatedById;
	public static volatile SingularAttribute<AuditTrail, Timestamp> createdOn;
	public static volatile SingularAttribute<AuditTrail, String> tableName;
	public static volatile SingularAttribute<AuditTrail, String> tableKey2;
	public static volatile SingularAttribute<AuditTrail, String> tableKey1;
	public static volatile SingularAttribute<AuditTrail, Long> enabledById;
	public static volatile SingularAttribute<AuditTrail, String> tableKey3;
	public static volatile SingularAttribute<AuditTrail, Long> deletedById;
	public static volatile SingularAttribute<AuditTrail, Timestamp> enabledOn;
	public static volatile SingularAttribute<AuditTrail, Timestamp> disabledOn;
	public static volatile SingularAttribute<AuditTrail, Long> id;
	public static volatile SingularAttribute<AuditTrail, Long> createdById;

}

