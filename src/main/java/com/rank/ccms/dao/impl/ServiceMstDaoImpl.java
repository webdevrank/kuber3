package com.rank.ccms.dao.impl;

import com.rank.ccms.dao.ServiceMstDao;
import com.rank.ccms.entities.ServiceMst;
import com.rank.ccms.rest.response.ServiceDto;
import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("servicesMstDao")
@Transactional
public class ServiceMstDaoImpl extends GenericDaoImpl<ServiceMst> implements ServiceMstDao {

    @Transactional(readOnly = true)
    @Override
    public List<ServiceMst> getAllActiveServiceDetails() {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ServiceMst.class, "escm");
        detachedCriteria.add(Restrictions.eq("deleteFlg", false));
        detachedCriteria.addOrder(Order.asc("id"));
        return (List<ServiceMst>) findByCriteria(detachedCriteria);

    }

    @Override
    public ServiceMst findNonDeletedServiceMstById(Long id) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ServiceMst.class, "escm");
        detachedCriteria.add(Restrictions.idEq(id));
        detachedCriteria.add(Restrictions.eq("deleteFlg", false));
        List<ServiceMst> listServiceMst = (List<ServiceMst>) findByCriteria(detachedCriteria);
        return (ServiceMst) (listServiceMst != null ? listServiceMst.get(0) : null);
    }

    @Override
    public ServiceMst findAllServiceMstById(Long id) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ServiceMst.class, "escm");
        detachedCriteria.add(Restrictions.idEq(id));
        List<ServiceMst> ListServiceMst = (List<ServiceMst>) (findByCriteria(detachedCriteria));
        if (ListServiceMst.isEmpty()) {
            return null;
        } else {
            return ListServiceMst.get(0);
        }
    }

    @Override
    public List<ServiceDto> findAllNonDeletedServicesAsServiceDtoList() {
        List<ServiceDto> serviceDtoList;
        try {
            DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ServiceMst.class, "SR");
            detachedCriteria.add(Restrictions.eq("SR.activeFlg", true));
            detachedCriteria.add(Restrictions.eq("deleteFlg", false));
            detachedCriteria.addOrder(Order.asc("SR.serviceDesc"));

            ProjectionList projections = Projections.projectionList();
            detachedCriteria.setProjection(projections.add(Projections.property("SR.id"), "id"))
                    .setProjection(projections.add(Projections.property("SR.serviceCd"), "serviceCd"))
                    .setProjection(projections.add(Projections.property("SR.serviceName"), "serviceName"))
                    .setProjection(projections.add(Projections.property("SR.serviceDesc"), "serviceDesc"));

            detachedCriteria.setResultTransformer(Transformers.aliasToBean(ServiceDto.class));

            serviceDtoList = (List<ServiceDto>) findByCriteria(detachedCriteria);
            return serviceDtoList;
        } catch (Exception e) {
            logger.error("Error : ", e);
            return null;
        }
    }

    @Override
    public ServiceMst findServiceByServiceName(String serviceName) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ServiceMst.class)
                .add(Restrictions.eq("deleteFlg", false))
                .add(Restrictions.eq("activeFlg", true))
                .add(Restrictions.eq("serviceName", serviceName).ignoreCase());

        List<ServiceMst> serviceList = (List<ServiceMst>) findByCriteria(detachedCriteria);
        if (!serviceList.isEmpty()) {
            return serviceList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public ServiceMst findServiceByServiceCode(String serviceCode) {
        DetachedCriteria detachedCriteria = DetachedCriteria.forClass(ServiceMst.class)
                .add(Restrictions.eq("deleteFlg", false))
                .add(Restrictions.eq("activeFlg", true))
                .add(Restrictions.eq("serviceCd", serviceCode).ignoreCase());

        List<ServiceMst> serviceList = (List<ServiceMst>) findByCriteria(detachedCriteria);
        if (!serviceList.isEmpty()) {
            return serviceList.get(0);
        } else {
            return null;
        }
    }

}
