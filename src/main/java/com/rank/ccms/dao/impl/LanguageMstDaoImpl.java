package com.rank.ccms.dao.impl;

import com.rank.ccms.dao.LanguageMstDao;
import com.rank.ccms.entities.LanguageMst;
import com.rank.ccms.rest.response.LanguageDto;

import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("languageMstDao")
@Transactional
public class LanguageMstDaoImpl extends GenericDaoImpl<LanguageMst> implements LanguageMstDao {

    @Override
    public LanguageMst findValidLanguageMstById(Long id) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(LanguageMst.class)
                .add(Restrictions.idEq(id))
                .add(Restrictions.eq("activeFlg", true))
                .add(Restrictions.eq("deleteFlg", false));
        List<LanguageMst> languageMst = (List<LanguageMst>) findByCriteria(detachedCriteria);

        return languageMst != null ? languageMst.get(0) : null;
    }

    @Override
    public List<LanguageMst> getAllValidLanguageMsts() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(LanguageMst.class, "languageMst")
                .add(Restrictions.eq("activeFlg", (short) 1))
                .add(Restrictions.eq("deleteFlg", (short) 0));

        return (List<LanguageMst>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<LanguageMst> getAllNonDeletedLanguageMsts() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(LanguageMst.class, "languageMst")
                .add(Restrictions.eq("deleteFlg", (short) 0));
        return (List<LanguageMst>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<LanguageDto> findAllNonDeletedLanguagesAsLanguageDtoList() {
        List<LanguageDto> languageDtoList;
        try {
            DetachedCriteria detachedCriteria = DetachedCriteria.forClass(LanguageMst.class, "LN")
                    .add(Restrictions.eq("LN.activeFlg", true))
                    .add(Restrictions.eq("LN.deleteFlg", false))
                    .addOrder(Order.asc("LN.languageDesc"));

            ProjectionList projections = Projections.projectionList();
            detachedCriteria
                    .setProjection(projections.add(Projections.property("LN.languageCd"), "languageCd"))
                    .setProjection(projections.add(Projections.property("LN.languageName"), "languageName"))
                    .setProjection(projections.add(Projections.property("LN.languageDesc"), "languageDesc"));

            detachedCriteria.setResultTransformer(Transformers.aliasToBean(LanguageDto.class));

            languageDtoList = (List<LanguageDto>) findByCriteria(detachedCriteria);
            return languageDtoList;
        } catch (Exception e) {
            logger.error("Error : ", e);
            return null;
        }
    }

    @Override
    public LanguageMst findLanguageMstByLanguageCode(String languageCode) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(LanguageMst.class)
                .add(Restrictions.eq("deleteFlg", false))
                .add(Restrictions.eq("activeFlg", true))
                .add(Restrictions.eq("languageCd", languageCode).ignoreCase());

        List<LanguageMst> languageList = (List<LanguageMst>) findByCriteria(detachedCriteria);
        if (!languageList.isEmpty()) {
            return languageList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public LanguageMst findLanguageMstByLanguageName(String languageName) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(LanguageMst.class)
                .add(Restrictions.eq("deleteFlg", false))
                .add(Restrictions.eq("activeFlg", true))
                .add(Restrictions.eq("languageName", languageName).ignoreCase());

        List<LanguageMst> languageList = (List<LanguageMst>) findByCriteria(detachedCriteria);
        if (!languageList.isEmpty()) {
            return languageList.get(0);
        } else {
            return null;
        }
    }

}
