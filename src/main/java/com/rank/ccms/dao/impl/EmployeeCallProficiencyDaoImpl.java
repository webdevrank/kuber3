package com.rank.ccms.dao.impl;

import com.rank.ccms.dao.EmployeeCallProficiencyDao;
import com.rank.ccms.entities.EmployeeCallProficiency;
import com.rank.ccms.entities.EmployeeMst;
import java.util.List;
import java.util.Set;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("employeeCallProficiencyDao")
@Transactional
public class EmployeeCallProficiencyDaoImpl extends GenericDaoImpl<EmployeeCallProficiency> implements EmployeeCallProficiencyDao {

    @Transactional
    @Override
    public EmployeeCallProficiency findById(Long id) {
        return get(EmployeeCallProficiency.class, id);
    }

    @Transactional
    @Override
    public List<EmployeeCallProficiency> findByPrimaryCategory(String segment) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeCallProficiency.class)
                .add(Restrictions.eq("categoryP", segment));
        detachedCriteria.setFetchMode("empId", FetchMode.JOIN);
        return (List<EmployeeCallProficiency>) findByCriteria(detachedCriteria);
    }

    @Transactional
    @Override
    public List<EmployeeCallProficiency> findByPrimaryCategoryAndEmployeeList(String segment, Set<EmployeeMst> employeeList) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeCallProficiency.class);

        detachedCriteria.add(Restrictions.eq("categoryP", segment)).add(Restrictions.in("empId", employeeList));

        return (List<EmployeeCallProficiency>) findByCriteria(detachedCriteria);
    }

    @Transactional
    @Override
    public List<EmployeeCallProficiency> findBySecondaryCategory(String segment) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeCallProficiency.class)
                .add(Restrictions.eq("categoryS", segment));
        detachedCriteria.setFetchMode("empId", FetchMode.JOIN);
        return (List<EmployeeCallProficiency>) findByCriteria(detachedCriteria);
    }

    @Transactional
    @Override
    public List<EmployeeCallProficiency> findBySecondaryCategoryAndEmployeeList(String segment, Set<EmployeeMst> employeeList) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeCallProficiency.class);
        detachedCriteria.setFetchMode("empId", FetchMode.JOIN);
        detachedCriteria.add(Restrictions.eq("categoryS", segment)).add(Restrictions.in("empId", employeeList));

        return (List<EmployeeCallProficiency>) findByCriteria(detachedCriteria);
    }

    @Transactional
    @Override
    public List<EmployeeCallProficiency> findByPrimaryService(String service) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeCallProficiency.class)
                .add(Restrictions.eq("serviceP", service));
        detachedCriteria.setFetchMode("empId", FetchMode.JOIN);
        return (List<EmployeeCallProficiency>) findByCriteria(detachedCriteria);
    }

    @Transactional
    @Override
    public List<EmployeeCallProficiency> findBySecondaryService(String service) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeCallProficiency.class)
                .add(Restrictions.eq("serviceS", service));
        detachedCriteria.setFetchMode("empId", FetchMode.JOIN);
        return (List<EmployeeCallProficiency>) findByCriteria(detachedCriteria);
    }

    @Transactional
    @Override
    public List<EmployeeCallProficiency> findByPrimaryServiceAndEmployeeList(String service, Set<EmployeeMst> employeeList) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeCallProficiency.class);
        detachedCriteria.setFetchMode("empId", FetchMode.JOIN);
        detachedCriteria.add(Restrictions.eq("serviceP", service)).add(Restrictions.in("empId", employeeList));
        return (List<EmployeeCallProficiency>) findByCriteria(detachedCriteria);
    }

    @Transactional
    @Override
    public List<EmployeeCallProficiency> findBySecondaryServiceAndEmployeeList(String service, Set<EmployeeMst> employeeList) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeCallProficiency.class);
        detachedCriteria.setFetchMode("empId", FetchMode.JOIN);
        detachedCriteria.add(Restrictions.eq("serviceS", service)).add(Restrictions.in("empId", employeeList));

        return (List<EmployeeCallProficiency>) findByCriteria(detachedCriteria);
    }

    @Transactional
    @Override
    public List<EmployeeCallProficiency> findByPrimaryLanguage(String language) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeCallProficiency.class)
                .add(Restrictions.eq("languageP", language));
        detachedCriteria.setFetchMode("empId", FetchMode.JOIN);
        return (List<EmployeeCallProficiency>) findByCriteria(detachedCriteria);
    }

    @Transactional
    @Override
    public List<EmployeeCallProficiency> findBySecondaryLanguage(String language) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeCallProficiency.class)
                .add(Restrictions.eq("languageS", language));
        detachedCriteria.setFetchMode("empId", FetchMode.JOIN);
        return (List<EmployeeCallProficiency>) findByCriteria(detachedCriteria);
    }

    @Transactional
    @Override
    public List<EmployeeCallProficiency> findByPrimaryLanguageAndEmployeeList(String language, Set<EmployeeMst> employeeList) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeCallProficiency.class);
        detachedCriteria.setFetchMode("empId", FetchMode.JOIN);
        detachedCriteria.add(Restrictions.eq("languageP", language)).add(Restrictions.in("empId", employeeList));

        return (List<EmployeeCallProficiency>) findByCriteria(detachedCriteria);
    }

    @Transactional
    @Override
    public List<EmployeeCallProficiency> findBySecondaryLanguageAndEmployeeList(String language, Set<EmployeeMst> employeeList) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeCallProficiency.class);
        detachedCriteria.setFetchMode("empId", FetchMode.JOIN);
        detachedCriteria.add(Restrictions.eq("languageS", language)).add(Restrictions.in("empId", employeeList));

        return (List<EmployeeCallProficiency>) findByCriteria(detachedCriteria);
    }

    @Transactional
    @Override
    public List<EmployeeCallProficiency> findByPrimarySkillAndEmployeeList(String skill, Set<EmployeeMst> employeeList) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeCallProficiency.class);
        detachedCriteria.setFetchMode("empId", FetchMode.JOIN);
        detachedCriteria.add(Restrictions.eq("languageP", skill)).add(Restrictions.in("empId", employeeList));

        return (List<EmployeeCallProficiency>) findByCriteria(detachedCriteria);
    }

    @Transactional
    @Override
    public List<EmployeeCallProficiency> findBySecondarySkillAndEmployeeList(String skill, Set<EmployeeMst> employeeList) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeCallProficiency.class);
        detachedCriteria.setFetchMode("empId", FetchMode.JOIN);
        detachedCriteria.add(Restrictions.eq("languageS", skill)).add(Restrictions.in("empId", employeeList));

        return (List<EmployeeCallProficiency>) findByCriteria(detachedCriteria);
    }

    @Transactional
    @Override
    public List<EmployeeCallProficiency> findCallProficiencyListByEmployeeMst(EmployeeMst employeeMst) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeCallProficiency.class);
        detachedCriteria.add(Restrictions.eq("empId.id", employeeMst.getId()));
        detachedCriteria.setFetchMode("empId", FetchMode.JOIN);
        return (List<EmployeeCallProficiency>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<EmployeeCallProficiency> findByPrimaryCategoryVideorText(Long empId) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeCallProficiency.class);
        Criterion vid = Restrictions.eq("categoryP", "VID");
        Criterion txt = Restrictions.eq("categoryP", "TXT");
        LogicalExpression orExp = Restrictions.or(vid, txt);
        detachedCriteria.add(Restrictions.eq("empId.id", empId));
        detachedCriteria.add(orExp);
        return (List<EmployeeCallProficiency>) findByCriteria(detachedCriteria);

    }

    @Override
    public List<EmployeeCallProficiency> findEmployeeCallProficiencyBySecondaryLanguage(String language) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeCallProficiency.class, "CALLEFF")
                .add(Restrictions.eq("CALLEFF.languageS", language));

        return (List<EmployeeCallProficiency>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<EmployeeCallProficiency> findEmployeeCallProficiencyByPrimaryLanguage(String language) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeCallProficiency.class, "CALLEFF")
                .add(Restrictions.eq("CALLEFF.languageP", language));

        return (List<EmployeeCallProficiency>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<EmployeeCallProficiency> findEmployeeCallProficiencyBySecondaryService(String service) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeCallProficiency.class, "CALLEFF")
                .add(Restrictions.eq("CALLEFF.serviceS", service));

        return (List<EmployeeCallProficiency>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<EmployeeCallProficiency> findEmployeeCallProficiencyByPrimarySegment(String service) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeCallProficiency.class, "CALLEFF")
                .add(Restrictions.eq("CALLEFF.serviceP", service));

        return (List<EmployeeCallProficiency>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<EmployeeCallProficiency> findEmployeeCallProficiencyBySecondarySegment(String segment) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeCallProficiency.class, "CALLEFF")
                .add(Restrictions.eq("CALLEFF.segmentS", segment));

        return (List<EmployeeCallProficiency>) findByCriteria(detachedCriteria);
    }

    @Override
    public List<EmployeeCallProficiency> findEmployeeCallProficiencyByPrimaryService(String segment) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeCallProficiency.class, "CALLEFF")
                .add(Restrictions.eq("CALLEFF.segmentP", segment));

        return (List<EmployeeCallProficiency>) findByCriteria(detachedCriteria);
    }

}
