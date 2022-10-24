package com.rank.ccms.entities;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(LanguageMst.class)
public abstract class LanguageMst_ {

	public static volatile SingularAttribute<LanguageMst, String> languageDesc;
	public static volatile SingularAttribute<LanguageMst, String> languageCd;
	public static volatile SingularAttribute<LanguageMst, Boolean> activeFlg;
	public static volatile SingularAttribute<LanguageMst, Integer> hashCode;
	public static volatile SingularAttribute<LanguageMst, Boolean> deleteFlg;
	public static volatile SingularAttribute<LanguageMst, Long> id;
	public static volatile SingularAttribute<LanguageMst, String> languageName;

}

