package com.rank.ccms.entities;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(FeedbackQueryMst.class)
public abstract class FeedbackQueryMst_ {

	public static volatile SingularAttribute<FeedbackQueryMst, Boolean> selectedFlg;
	public static volatile SingularAttribute<FeedbackQueryMst, Boolean> activeFlg;
	public static volatile SingularAttribute<FeedbackQueryMst, String> feedbackQuery;
	public static volatile SingularAttribute<FeedbackQueryMst, Boolean> deleteFlg;
	public static volatile SingularAttribute<FeedbackQueryMst, Long> id;

}

