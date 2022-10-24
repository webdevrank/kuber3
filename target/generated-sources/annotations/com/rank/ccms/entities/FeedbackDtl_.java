package com.rank.ccms.entities;

import java.sql.Timestamp;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(FeedbackDtl.class)
public abstract class FeedbackDtl_ {

	public static volatile SingularAttribute<FeedbackDtl, String> comments;
	public static volatile SingularAttribute<FeedbackDtl, CallMst> callMstId;
	public static volatile SingularAttribute<FeedbackDtl, Boolean> activeFlg;
	public static volatile SingularAttribute<FeedbackDtl, String> feedbackQuery;
	public static volatile SingularAttribute<FeedbackDtl, Integer> hashCode;
	public static volatile SingularAttribute<FeedbackDtl, Long> feedbackParamId;
	public static volatile SingularAttribute<FeedbackDtl, Boolean> deleteFlg;
	public static volatile SingularAttribute<FeedbackDtl, CustomerMst> customerId;
	public static volatile SingularAttribute<FeedbackDtl, Timestamp> feedbackDate;
	public static volatile SingularAttribute<FeedbackDtl, Long> id;
	public static volatile SingularAttribute<FeedbackDtl, String> starRating;

}

