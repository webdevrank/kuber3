package com.rank.ccms.service;

import com.rank.ccms.dto.CustomerDetails;
import com.rank.ccms.entities.CallFileUploadDtls;
import java.io.Serializable;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

public interface CallFileUploadDtlsService extends Serializable {

    Long saveCallFileDetails(CallFileUploadDtls callFileUploadDtls);

    List<CallFileUploadDtls> findAllFileByCallMst(Long callMstId);

    CallFileUploadDtls getAgentFile(CustomerDetails cm) throws Exception;

    String getVideoFileUrl(String filePath, HttpServletRequest request, ServletContext ctx);

    List<CallFileUploadDtls> findAllFileByFileReceivedTypeAndId(String fileReceivedByType, Long fileReceivedById);

}
