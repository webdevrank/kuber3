package com.rank.ccms.dao.impl;

import com.rank.ccms.dao.CallFileUploadDtlsDao;
import com.rank.ccms.dto.CustomerDetails;
import com.rank.ccms.entities.CallFileUploadDtls;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("callFileUploadDtlsDao")
@Transactional
public class CallFileUploadDtlsDaoImpl extends GenericDaoImpl<CallFileUploadDtls> implements Serializable, CallFileUploadDtlsDao {

    private static final long serialVersionUID = -3890386640250097701L;

    @Override
    public Long saveCallFileDetails(CallFileUploadDtls callFileUploadDtls) {
        Session session;
        Long status;
        try {
            session = getSessionFactory().getCurrentSession();
            status = (Long) session.save(callFileUploadDtls);

            return status;
        } catch (Exception e) {
            logger.error("ERROR : ", e);
            return null;
        }
    }

    @Override
    public CallFileUploadDtls findAgentInFileMst(CustomerDetails cm) throws Exception {
        CallFileUploadDtls cfm = null;
        try {
            DetachedCriteria dc = DetachedCriteria.forClass(CallFileUploadDtls.class, "cfm");
            dc.add(Restrictions.eq("cfm.customerId", cm));

            List<CallFileUploadDtls> agentFileMst = (List<CallFileUploadDtls>) findByCriteria(dc);
            if (!agentFileMst.isEmpty()) {
                cfm = agentFileMst.get(0);
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
        return cfm;

    }

    @Override
    public List<CallFileUploadDtls> findAllFileByCallMst(Long callMstId) {
        DetachedCriteria dc = DetachedCriteria.forClass(CallFileUploadDtls.class, "cfm");
        dc.add(Restrictions.eq("cfm.callMstId.id", callMstId));
        return (List<CallFileUploadDtls>) findByCriteria(dc);
    }

    @Override
    public List<CallFileUploadDtls> findAllFileByFileReceivedTypeAndId(String fileReceivedByType, Long fileReceivedById) {
        DetachedCriteria dc = DetachedCriteria.forClass(CallFileUploadDtls.class, "cfm");
        dc.add(Restrictions.or(
                (Restrictions.and(
                        Restrictions.eq("cfm.fileReceivedbyType", fileReceivedByType),
                        Restrictions.eq("cfm.fileReceivedBy", fileReceivedById))),
                Restrictions.and(
                        Restrictions.eq("cfm.fileSentbyType", fileReceivedByType),
                        Restrictions.eq("cfm.fileSentBy", fileReceivedById))));

        return (List<CallFileUploadDtls>) findByCriteria(dc);
    }

}
