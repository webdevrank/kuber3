package com.rank.ccms.dao;

import com.rank.ccms.entities.LanguageMst;
import com.rank.ccms.rest.response.LanguageDto;

import java.util.List;

public interface LanguageMstDao extends GenericDao<LanguageMst> {

    public LanguageMst findValidLanguageMstById(Long id);

    public List<LanguageMst> getAllValidLanguageMsts();

    public List<LanguageMst> getAllNonDeletedLanguageMsts();

    public List<LanguageDto> findAllNonDeletedLanguagesAsLanguageDtoList();

    public LanguageMst findLanguageMstByLanguageCode(String languageCode);

    public LanguageMst findLanguageMstByLanguageName(String languageName);

}
