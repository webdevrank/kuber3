package com.rank.ccms.dao.impl;

import com.rank.ccms.dao.EmployeeCallStatusDao;
import com.rank.ccms.entities.EmployeeCallStatus;
import com.rank.ccms.entities.EmployeeMst;
import java.util.List;
import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("employeeCallStatusDao")
@Transactional
public class EmployeeCallStatusDaoImpl extends GenericDaoImpl<EmployeeCallStatus> implements EmployeeCallStatusDao {

    @Transactional
    @Override
    public List<EmployeeCallStatus> findOnlineFreeAgents() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeCallStatus.class, "ECS")
                .createAlias("ECS.empId", "EM")
                .createAlias("EM.empTypId", "ETM")
                .add(Restrictions.eq("ETM.typeName", "agent").ignoreCase())
                .add(Restrictions.eq("status", true));
        detachedCriteria.setFetchMode("empId", FetchMode.JOIN);
        return (List<EmployeeCallStatus>) findByCriteria(detachedCriteria);
    }

    @Transactional
    @Override
    public boolean updateAllEmplyeeStatusToZero() {
        try {
            org.hibernate.Query query = getSessionFactory().getCurrentSession().createSQLQuery("CALL updateStatus()");
            List results = query.list();
            logger.info("query.list()query.list()query.list()query.list()query.list()query.list()query.list()query.list()query.list()query.list()query.list()query.list()query.list()query.list()query.list()query.list()query.list()query.list()query.list()" + query.list());
            return results.size() > 0;
        } catch (Exception ex) {
            logger.info(" error in uapdate status " + ex.getMessage());
            return false;
        }

    }

    @Override
    public List<EmployeeCallStatus> findFreeOnlineRMs(String loginId) {

        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeCallStatus.class, "ECS")
                .createAlias("ECS.empId", "EM")
                .createAlias("EM.empTypId", "ETM")
                .add(Restrictions.eq("ETM.typeName", "RelationshipManager").ignoreCase())
                .add(Restrictions.eq("EM.loginId", loginId))
                .add(Restrictions.eq("status", true));
        detachedCriteria.setFetchMode("empId", FetchMode.JOIN);
        return (List<EmployeeCallStatus>) findByCriteria(detachedCriteria);

    }

    @Override
    public List<EmployeeCallStatus> findFreeOnlineSRMs(Long srmId) {

        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeCallStatus.class, "ECS")
                .createAlias("ECS.empId", "EM")
                .createAlias("EM.empTypId", "ETM")
                .add(Restrictions.eq("ETM.typeName", "SeniorRelationshipManager").ignoreCase())
                .add(Restrictions.eq("EM.id", srmId))
                .add(Restrictions.eq("status", true));
        detachedCriteria.setFetchMode("empId", FetchMode.JOIN);
        return (List<EmployeeCallStatus>) findByCriteria(detachedCriteria);

    }

    @Override
    public List<EmployeeCallStatus> findFreeOnlineBMs(Long bmId) {

        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeCallStatus.class, "ECS")
                .createAlias("ECS.empId", "EM")
                .createAlias("EM.empTypId", "ETM")
                .add(Restrictions.eq("ETM.typeName", "BranchManager").ignoreCase())
                .add(Restrictions.eq("EM.id", bmId))
                .add(Restrictions.eq("status", true));
        detachedCriteria.setFetchMode("empId", FetchMode.JOIN);
        return (List<EmployeeCallStatus>) findByCriteria(detachedCriteria);

    }

    @Override
    public List<EmployeeCallStatus> findEmployeeCallStatusEmployeeMst(EmployeeMst employeeMst) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeCallStatus.class, "CALLEFF")
                .add(Restrictions.eq("CALLEFF.empId.id", employeeMst.getId()));
        detachedCriteria.createAlias("CALLEFF.empId", "EMP");
        return (List<EmployeeCallStatus>) findByCriteria(detachedCriteria);
    }

    @Transactional
    @Override
    public List<EmployeeCallStatus> findOnlineFreeSpecialists() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeCallStatus.class, "ECS")
                .createAlias("ECS.empId", "EM")
                .createAlias("EM.empTypId", "ETM")
                .add(Restrictions.eq("ETM.typeName", "specialist").ignoreCase())
                .add(Restrictions.eq("status", true));
        detachedCriteria.setFetchMode("empId", FetchMode.JOIN);
        return (List<EmployeeCallStatus>) findByCriteria(detachedCriteria);
    }

    @Override
    public EmployeeCallStatus findEmployeeCurrentCallStatusByEmpId(Long agentId) {
        List<EmployeeCallStatus> employeeCallStatusList;
        EmployeeCallStatus returnEmployeeStatus = null;
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(EmployeeCallStatus.class)
                .add(Restrictions.eq("status", true))
                .add(Restrictions.eq("empId.id", agentId));

        employeeCallStatusList = (List<EmployeeCallStatus>) findByCriteria(detachedCriteria);

        if (!employeeCallStatusList.isEmpty() && employeeCallStatusList.size() > 0) {
            returnEmployeeStatus = employeeCallStatusList.get(0);
        }

        return returnEmployeeStatus;
    }

}
