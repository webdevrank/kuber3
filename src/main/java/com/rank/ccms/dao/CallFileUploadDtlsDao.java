package com.rank.ccms.dao;

import com.rank.ccms.dto.CustomerDetails;
import com.rank.ccms.entities.CallFileUploadDtls;
import java.io.Serializable;
import java.util.List;

public interface CallFileUploadDtlsDao extends Serializable, GenericDao<CallFileUploadDtls> {

    public Long saveCallFileDetails(CallFileUploadDtls callFileUploadDtls);

    public CallFileUploadDtls findAgentInFileMst(CustomerDetails cm) throws Exception;

    public List<CallFileUploadDtls> findAllFileByCallMst(Long callMstId);

    public List<CallFileUploadDtls> findAllFileByFileReceivedTypeAndId(String fileReceivedByType, Long fileReceivedById);

}
