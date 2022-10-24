package com.rank.ccms.service.impl;

import com.rank.ccms.dao.CategoryMstDao;
import com.rank.ccms.dao.EmployeeProficiencyMapDao;
import com.rank.ccms.dao.ServiceMstDao;
import com.rank.ccms.dao.LanguageMstDao;
import com.rank.ccms.entities.CategoryMst;
import com.rank.ccms.entities.EmployeeCallProficiency;
import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.entities.EmployeeProficiencyMap;
import com.rank.ccms.entities.EmployeeTypeMst;
import com.rank.ccms.entities.ServiceMst;
import com.rank.ccms.entities.LanguageMst;
import com.rank.ccms.dto.Categories;
import com.rank.ccms.dto.EmployeeCallProffDto;
import com.rank.ccms.dto.Languages;
import com.rank.ccms.dto.Services;
import com.rank.ccms.service.EmployeeCallProficiencyService;
import com.rank.ccms.service.PopulateAgentCallProficiency;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("populateAgentCallProficiency")
public class PopulateAgentCallProficiencyImpl implements PopulateAgentCallProficiency {

    @Autowired
    private EmployeeProficiencyMapDao employeeProficiencyMapDao;
    @Autowired
    private LanguageMstDao languageMstDao;
    @Autowired
    private ServiceMstDao serviceMstDao;
    @Autowired
    private CategoryMstDao categoryMstDao;
    @Autowired
    private EmployeeCallProficiencyService employeeCallProficiencyService;
    org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(this.getClass());

    private List<EmployeeProficiencyMap> getAgentProficiencyDetails(Long empPkId, Long empTypePkId) {
        return employeeProficiencyMapDao.findAllActivenNonDeletedEmployeeProficiencyMapsByEmpId(empPkId, empTypePkId);
    }

    private List<EmployeeCallProffDto> populateCallProffDto(Long empPkId,
            ArrayList<Long> langPrimaryList, ArrayList<Long> langSecondaryList,
            ArrayList<Long> servPrimaryList, ArrayList<Long> servSecondaryList,
            ArrayList<Long> catgPrimaryList, ArrayList<Long> catgSecondaryList)
            throws ArrayIndexOutOfBoundsException, NullPointerException {

        List<EmployeeCallProffDto> agentCallProffDtoList = new ArrayList<>();

        /**
         *
         * For Skill Language Sets, i.e. Language Skill Sets
         */
        ArrayList<Languages> languagesList = new ArrayList<>();
        // If Both Primary and Secondary cannot be empty
        if (langPrimaryList.isEmpty() && langSecondaryList.isEmpty()) {
            return null;
        }
        // If Primary is Not Empty and Secondary is Empty
        if (!(langPrimaryList.isEmpty()) && langSecondaryList.isEmpty()) {
            logger.info("Lang Only Primary Count " + langPrimaryList.size());
            for (Long langPId : langPrimaryList) {
                Languages langs = new Languages();
                LanguageMst skillMstPri = languageMstDao.findValidLanguageMstById(langPId);
                langs.setLanguagePrimaryId(skillMstPri.getId());
                langs.setLanguagePrimaryCode(skillMstPri.getLanguageCd());
                langs.setLanguagePrimaryName(skillMstPri.getLanguageName());
                languagesList.add(langs);
            }
        } else // If Primary is Empty and Secondary is NOT Empty
        {
            if (langPrimaryList.isEmpty() && !(langSecondaryList.isEmpty())) {
                logger.info("Lang Only Secondary Count " + langSecondaryList.size());
                for (Long langSId : langSecondaryList) {
                    Languages langs = new Languages();
                    LanguageMst skillMstSec = languageMstDao.findValidLanguageMstById(langSId);
                    langs.setLanguageSecondaryId(skillMstSec.getId());
                    langs.setLanguageSecondaryCode(skillMstSec.getLanguageCd());
                    langs.setLanguageSecondaryName(skillMstSec.getLanguageName());
                    languagesList.add(langs);
                }
            } else {
                // If both Primary and Secondary are Present
                logger.info("Lang Both Primary:" + langPrimaryList.size() + " Secondary:" + langSecondaryList.size());
                for (Long langPId : langPrimaryList) {
                    LanguageMst skillMstPri = languageMstDao.findValidLanguageMstById(langPId);
                    for (Long langSId : langSecondaryList) {
                        Languages langs = new Languages();
                        langs.setLanguagePrimaryId(skillMstPri.getId());
                        langs.setLanguagePrimaryCode(skillMstPri.getLanguageCd());
                        langs.setLanguagePrimaryName(skillMstPri.getLanguageName());
                        LanguageMst skillMstSec = languageMstDao.findValidLanguageMstById(langSId);
                        langs.setLanguageSecondaryId(skillMstSec.getId());
                        langs.setLanguageSecondaryCode(skillMstSec.getLanguageCd());
                        langs.setLanguageSecondaryName(skillMstSec.getLanguageName());
                        languagesList.add(langs);
                    }
                }
            }
        }

        ArrayList<Services> servicesList = new ArrayList<>();
        if (servPrimaryList.isEmpty() && servSecondaryList.isEmpty()) {
            return null;
        }
        // If Primary is Not Empty and Secondary is Empty
        if (!(servPrimaryList.isEmpty()) && servSecondaryList.isEmpty()) {
            logger.info("Service Only Primary Count " + servPrimaryList.size());
            for (Long servPId : servPrimaryList) {
                ServiceMst serviceMstPri = serviceMstDao.findNonDeletedById(servPId);
                Services services = new Services();
                services.setServicePrimaryId(serviceMstPri.getId());
                services.setServicePrimaryCode(serviceMstPri.getServiceCd());
                services.setServicePrimaryName(serviceMstPri.getServiceName());
                servicesList.add(services);
            }
        } else // If Primary is Empty and Secondary is NOT Empty
        {
            if (servPrimaryList.isEmpty() && !(servSecondaryList.isEmpty())) {
                logger.info("Service Only Secondary Count " + servSecondaryList.size());
                for (Long servSId : servSecondaryList) {
                    ServiceMst serviceMstSec = serviceMstDao.findNonDeletedById(servSId);
                    Services services = new Services();
                    services.setServiceSecondaryId(serviceMstSec.getId());
                    services.setServiceSecondaryCode(serviceMstSec.getServiceCd());
                    services.setServiceSecondaryName(serviceMstSec.getServiceName());
                    servicesList.add(services);
                }
            } else {
                // If both Primary and Secondary are Present
                logger.info("Service Both Primary:" + servPrimaryList.size() + " Secondary:" + servSecondaryList.size());
                for (Long servPId : servPrimaryList) {
                    ServiceMst serviceMstPri = serviceMstDao.findNonDeletedById(servPId);
                    for (Long servSId : servSecondaryList) {
                        Services services = new Services();
                        services.setServicePrimaryId(serviceMstPri.getId());
                        services.setServicePrimaryCode(serviceMstPri.getServiceCd());
                        services.setServicePrimaryName(serviceMstPri.getServiceName());
                        ServiceMst serviceMstSec = serviceMstDao.findNonDeletedById(servSId);
                        services.setServiceSecondaryId(serviceMstSec.getId());
                        services.setServiceSecondaryCode(serviceMstSec.getServiceCd());
                        services.setServiceSecondaryName(serviceMstSec.getServiceName());
                        servicesList.add(services);
                    }
                }
            }
        }

        ArrayList<Categories> segmentsList = new ArrayList<>();
        if (catgPrimaryList.isEmpty() && catgSecondaryList.isEmpty()) {
            return null;
        }
        // If Primary is Not Empty and Secondary is Empty
        if (!catgPrimaryList.isEmpty() && catgSecondaryList.isEmpty()) {
            logger.info("Segment Only Primary Count " + catgPrimaryList.size());
            for (Long catgPId : catgPrimaryList) {
                CategoryMst segmentMstPri = categoryMstDao.findNonDeletedById(catgPId);
                Categories segments = new Categories();
                segments.setCategoryPrimaryId(segmentMstPri.getId());
                segments.setCategoryPrimaryCode(segmentMstPri.getCatgCd());
                segments.setCategoryPrimaryName(segmentMstPri.getCatgName());
                segmentsList.add(segments);
            }
        } else // If Primary is Empty and Secondary is NOT Empty
        {
            if (catgPrimaryList.isEmpty() && !catgSecondaryList.isEmpty()) {
                logger.info("Segment Only Secondary Count " + catgSecondaryList.size());
                for (Long catgSId : catgSecondaryList) {
                    CategoryMst segmentMstSec = categoryMstDao.findNonDeletedById(catgSId);
                    Categories segments = new Categories();
                    segments.setCategorySecondaryId(segmentMstSec.getId());
                    segments.setCategorySecondaryCode(segmentMstSec.getCatgCd());
                    segments.setCategorySecondaryName(segmentMstSec.getCatgName());
                    segmentsList.add(segments);
                }
            } else {
                // If both Primary and Secondary are Present
                logger.info("Segment Both Primary:" + catgPrimaryList.size() + " Secondary:" + catgSecondaryList.size());
                for (Long catgPId : catgPrimaryList) {
                    CategoryMst segmentMstPri = categoryMstDao.findNonDeletedById(catgPId);
                    for (Long catgSId : catgSecondaryList) {
                        Categories segments = new Categories();
                        segments.setCategoryPrimaryId(segmentMstPri.getId());
                        segments.setCategoryPrimaryCode(segmentMstPri.getCatgCd());
                        segments.setCategoryPrimaryName(segmentMstPri.getCatgName());
                        CategoryMst segmentMstSec = categoryMstDao.findNonDeletedById(catgSId);
                        segments.setCategorySecondaryId(segmentMstSec.getId());
                        segments.setCategorySecondaryCode(segmentMstSec.getCatgCd());
                        segments.setCategorySecondaryName(segmentMstSec.getCatgName());
                        segmentsList.add(segments);
                    }
                }
            }
        }

        if (languagesList.isEmpty() || servicesList.isEmpty() || segmentsList.isEmpty()) {
            return null;
        } else {
            for (Languages langSkill : languagesList) {
                for (Services services : servicesList) {
                    for (Categories segments : segmentsList) {
                        EmployeeCallProffDto callProffDto = new EmployeeCallProffDto();
                        callProffDto.setEmpFkId(empPkId);
                        callProffDto.setLanguageList(langSkill);
                        callProffDto.setServiceList(services);
                        callProffDto.setSegmentList(segments);
                        agentCallProffDtoList.add(callProffDto);
                    }
                }
            }
        }

        return agentCallProffDtoList;
    }

    @Transactional
    @Override
    public synchronized Integer loadAgentCallProficiencies(EmployeeMst employeeMaster, EmployeeTypeMst employeeTypeAgentMst) {
        //Integer lReturn = 0;

        ArrayList<Long> servPrimaryList = new ArrayList<>();
        ArrayList<Long> servSecondaryList = new ArrayList<>();
        ArrayList<Long> catgPrimaryList = new ArrayList<>();
        ArrayList<Long> catgSecondaryList = new ArrayList<>();
        ArrayList<Long> langPrimaryList = new ArrayList<>();
        ArrayList<Long> langSecondaryList = new ArrayList<>();

        logger.info("Inside loadAgentCallProficiencies....");
        List<EmployeeProficiencyMap> agentProficiencyMapList = getAgentProficiencyDetails(employeeMaster.getId(), employeeMaster.getEmpTypId().getId());
        for (EmployeeProficiencyMap agentProficiencyMap : agentProficiencyMapList) {
            // It is the Languages Skills
            if (agentProficiencyMap.getType().equalsIgnoreCase("Language")) {
                LanguageMst skilMst = languageMstDao.findById(agentProficiencyMap.getSkillId());
                if (skilMst.getDeleteFlg() == false) {
                    if (agentProficiencyMap.getPrimarySkill() == 1 && agentProficiencyMap.getSecondarySkill() == 0) {
                        langPrimaryList.add(agentProficiencyMap.getSkillId());
                    }
                    if (agentProficiencyMap.getPrimarySkill() == 0 && agentProficiencyMap.getSecondarySkill() == 1) {
                        langSecondaryList.add(agentProficiencyMap.getSkillId());
                    }
                }
                // - NOTE: Rest all the Combinations of Primary & Secondary are Ignored.
            }
            // It is the Services Skills
            if (agentProficiencyMap.getType().equalsIgnoreCase("Service")) {
                ServiceMst serviceMst = serviceMstDao.findById(agentProficiencyMap.getSkillId());
                if (serviceMst.getDeleteFlg() == false) {
                    if (agentProficiencyMap.getPrimarySkill() == 1 && agentProficiencyMap.getSecondarySkill() == 0) {
                        servPrimaryList.add(agentProficiencyMap.getSkillId());
                    }
                    if (agentProficiencyMap.getPrimarySkill() == 0 && agentProficiencyMap.getSecondarySkill() == 1) {
                        servSecondaryList.add(agentProficiencyMap.getSkillId());
                    }
                }
                // - NOTE: Rest all the Combinations of Primary & Secondary are Ignored.
            }
            // It is the Segment/Category Skills
            if (agentProficiencyMap.getType().equalsIgnoreCase("Category")) {
                CategoryMst categoryMst = categoryMstDao.findById(agentProficiencyMap.getSkillId());
                if (categoryMst.getDeleteFlg() == false) {
                    if (agentProficiencyMap.getPrimarySkill() == 1 && agentProficiencyMap.getSecondarySkill() == 0) {
                        catgPrimaryList.add(agentProficiencyMap.getSkillId());
                    }
                    if (agentProficiencyMap.getPrimarySkill() == 0 && agentProficiencyMap.getSecondarySkill() == 1) {
                        catgSecondaryList.add(agentProficiencyMap.getSkillId());
                    }
                    // - NOTE: Rest all the Combinations of Primary & Secondary are Ignored.
                }
            }
        }
        List<EmployeeCallProffDto> agentCallProffDtoList;
        try {
            agentCallProffDtoList = populateCallProffDto(employeeMaster.getId(), langPrimaryList, langSecondaryList, servPrimaryList, servSecondaryList, catgPrimaryList, catgSecondaryList);
            if (null == agentCallProffDtoList || agentCallProffDtoList.isEmpty()) {
                return 0;
            } else if (Objects.equals(employeeMaster.getEmpTypId().getId(), employeeTypeAgentMst.getId())) {
                for (EmployeeCallProffDto agentCallProffDto : agentCallProffDtoList) {
                    EmployeeCallProficiency agentCallProficiency = new EmployeeCallProficiency();
                    //employeeCallProficiency.setEmpId(employeeMstDao.findById(agentCallProffDto.getEmpFkId()));
                    agentCallProficiency.setEmpId(employeeMaster);
                    agentCallProficiency.setLanguageP(agentCallProffDto.getLanguageList().getLanguagePrimaryCode());
                    agentCallProficiency.setLanguageS(agentCallProffDto.getLanguageList().getLanguageSecondaryCode());
                    agentCallProficiency.setServiceP(agentCallProffDto.getServiceList().getServicePrimaryCode());
                    agentCallProficiency.setServiceS(agentCallProffDto.getServiceList().getServiceSecondaryCode());
                    agentCallProficiency.setCategoryP(agentCallProffDto.getSegmentList().getCategoryPrimaryCode());
                    agentCallProficiency.setCategoryS(agentCallProffDto.getSegmentList().getCategorySecondaryCode());

                    agentCallProficiency = employeeCallProficiencyService.saveEmployeeCallProficiency(agentCallProficiency);
                    if (null == agentCallProficiency) {
                        return 0;
                    }
                }
            }
            /* ***** Not Implemented ====================
                 else if (employeeMaster.getEmpTypId().getId() == employeeTypePsMst.getId()) {
                 for (EmployeeCallProffDto agentCallProffDto : agentCallProffDtoList) {
                 PsCallProficiency psCallProficiency = new PsCallProficiency();
                 //employeeCallProficiency.setEmpId(employeeMstDao.findById(agentCallProffDto.getEmpFkId()));

                 psCallProficiency.setEmpId(employeeMaster);
                 psCallProficiency.setLanguageP(agentCallProffDto.getLanguageList().getSkillPrimaryName());
                 psCallProficiency.setLanguageS(agentCallProffDto.getLanguageList().getSkillSecondaryName());
                 psCallProficiency.setServiceP(agentCallProffDto.getServiceList().getServicePrimaryName());
                 psCallProficiency.setServiceS(agentCallProffDto.getServiceList().getServiceSecondaryName());
                 psCallProficiency.setSegmentP(agentCallProffDto.getSegmentList().getCategoryPrimaryName());
                 psCallProficiency.setSegmentS(agentCallProffDto.getSegmentList().getCategorySecondaryName());
                 psCallProficiency = psCallProficiencyService.savePsCallProficiency(psCallProficiency);
                         
                 }
                 }
             */
        } catch (DataAccessException dataAccessException) {
            logger.info("Error:" + dataAccessException.getMessage());
            return 0;
        }
        return 1;
    }

}
