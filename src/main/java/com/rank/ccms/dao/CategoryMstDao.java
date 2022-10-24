package com.rank.ccms.dao;

import com.rank.ccms.entities.CategoryMst;
import com.rank.ccms.rest.response.CategoryDto;

import java.util.List;

public interface CategoryMstDao extends GenericDao<CategoryMst> {

    public List<CategoryMst> finalAllNonDeletedOrderByDesc();

    public CategoryMst findNonDeletedCategoryMstByCategoryMstId(Long id);

    public List<CategoryDto> findAllNonDeletedCategoriesAsCategoryDtoList();

    public CategoryMst findCategoryByCategoryName(String categoryName);

    public CategoryMst findCategoryByCategoryCode(String categoryCode);

}
