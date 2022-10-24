package com.rank.ccms.entities;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(CategoryMst.class)
public abstract class CategoryMst_ {

	public static volatile SingularAttribute<CategoryMst, Boolean> activeFlg;
	public static volatile SingularAttribute<CategoryMst, Integer> hashCode;
	public static volatile SingularAttribute<CategoryMst, Boolean> deleteFlg;
	public static volatile SingularAttribute<CategoryMst, Long> id;
	public static volatile SingularAttribute<CategoryMst, String> catgDesc;
	public static volatile SingularAttribute<CategoryMst, String> catgCd;
	public static volatile SingularAttribute<CategoryMst, String> catgName;

}

