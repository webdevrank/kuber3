package com.rank.ccms.service;

import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.entities.LanguageMst;
import java.io.Serializable;
import java.util.List;

public interface LanguageMstService extends Serializable {

    LanguageMst findNonDeletedLanguageMstById(Long id);

    List<LanguageMst> findAllNonDeletedLanguageMsts();

    LanguageMst saveLanguageMst(LanguageMst languageMst, EmployeeMst employeeMst);

    LanguageMst findLanguageMstByLanguageCode(String languageCode);

    LanguageMst findLanguageMstByLanguageName(String languageName);

    LanguageMst findAllLanguageMstById(Long id);

    LanguageMst findValidLanguageMstById(Long id);

}
