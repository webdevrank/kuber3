package com.rank.ccms.dao.impl;

import com.rank.ccms.dao.CategoryMstDao;
import com.rank.ccms.entities.CategoryMst;
import com.rank.ccms.rest.response.CategoryDto;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("categoryMstDao")
@Transactional
public class CategoryMstDaoImpl extends GenericDaoImpl<CategoryMst> implements CategoryMstDao {

    @Override
    public List<CategoryMst> finalAllNonDeletedOrderByDesc() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CategoryMst.class);
        detachedCriteria.add(Restrictions.eq("deleteFlg", false))
                .add(Restrictions.eq("activeFlg", true))
                .addOrder(Order.asc("catgDesc"));
        return (List<CategoryMst>) findByCriteria(detachedCriteria);
    }

    @Override
    public CategoryMst findNonDeletedCategoryMstByCategoryMstId(Long id) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CategoryMst.class);
        detachedCriteria.add(Restrictions.eq("deleteFlg", false))
                .add(Restrictions.eq("activeFlg", true))
                .add(Restrictions.idEq(id))
                .addOrder(Order.asc("id"));
        List<CategoryMst> ctgryMst = (List<CategoryMst>) findByCriteria(detachedCriteria);
        return ctgryMst != null ? ctgryMst.get(0) : null;
    }

    @Override
    public List<CategoryDto> findAllNonDeletedCategoriesAsCategoryDtoList() {
        List<CategoryDto> categoryDtoList;
        try {
            DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CategoryMst.class, "CT")
                    .add(Restrictions.eq("CT.activeFlg", true))
                    .add(Restrictions.eq("CT.deleteFlg", false))
                    .addOrder(Order.asc("CT.catgDesc"));

            ProjectionList projections = Projections.projectionList();
            detachedCriteria
                    .setProjection(projections.add(Projections.property("CT.catgCd"), "catgCd"))
                    .setProjection(projections.add(Projections.property("CT.catgName"), "catgName"))
                    .setProjection(projections.add(Projections.property("CT.catgDesc"), "catgDesc"));

            detachedCriteria.setResultTransformer(Transformers.aliasToBean(CategoryDto.class));

            categoryDtoList = (List<CategoryDto>) findByCriteria(detachedCriteria);
            return categoryDtoList;
        } catch (Exception e) {
            logger.error("Error : ", e);
            return null;
        }
    }

    @Override
    public CategoryMst findCategoryByCategoryName(String categoryName) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CategoryMst.class)
                .add(Restrictions.eq("deleteFlg", false))
                .add(Restrictions.eq("activeFlg", true))
                .add(Restrictions.eq("catgName", categoryName).ignoreCase());

        List<CategoryMst> categoryList = (List<CategoryMst>) findByCriteria(detachedCriteria);
        if (!categoryList.isEmpty()) {
            return categoryList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public CategoryMst findCategoryByCategoryCode(String categoryCode) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(CategoryMst.class)
                .add(Restrictions.eq("deleteFlg", false))
                .add(Restrictions.eq("activeFlg", true))
                .add(Restrictions.eq("catgCd", categoryCode).ignoreCase());

        List<CategoryMst> categoryList = (List<CategoryMst>) findByCriteria(detachedCriteria);
        if (!categoryList.isEmpty()) {
            return categoryList.get(0);
        } else {
            return null;
        }
    }

}
