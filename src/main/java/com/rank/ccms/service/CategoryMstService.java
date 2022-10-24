package com.rank.ccms.service;

import com.rank.ccms.entities.CategoryMst;
import com.rank.ccms.entities.EmployeeMst;
import java.io.Serializable;
import java.util.List;

public interface CategoryMstService extends Serializable {

    CategoryMst saveCategoryMst(CategoryMst categoryMst, EmployeeMst employeeMst);

    List<CategoryMst> findAllCategoryMsts();

    List<CategoryMst> findAllNonDeletedCategoryMsts();

    CategoryMst findCategoryMstById(Long id);

    CategoryMst findNonDeletedCategoryMstById(Long id);

    CategoryMst findCategoryByCategoryName(String categoryName);

    CategoryMst findCategoryByCategoryCode(String categoryCode);

    List<CategoryMst> finalAllNonDeletedOrderByDesc();

    CategoryMst findNonDeletedCategoryMstByCategoryMstId(Long id);

}
