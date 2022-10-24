package com.rank.ccms.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rank.ccms.dao.CallFileUploadDtlsDao;
import com.rank.ccms.dto.CustomerDetails;
import com.rank.ccms.entities.CallFileUploadDtls;
import com.rank.ccms.service.CallFileUploadDtlsService;
import java.io.File;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;

@Service("callFileUploadDtlsService")
public class CallFileUploadDtlsServiceImpl implements Serializable, CallFileUploadDtlsService {

    private final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CallFileUploadDtlsServiceImpl.class);
    private static final String FSLASH = "/";

    @Autowired
    private CallFileUploadDtlsDao callFileUploadDtlsDao;

    @Override
    public Long saveCallFileDetails(CallFileUploadDtls callFileUploadDtls) {
        return callFileUploadDtlsDao.saveCallFileDetails(callFileUploadDtls);
    }

    @Override
    public List<CallFileUploadDtls> findAllFileByCallMst(Long callMstId) {
        return callFileUploadDtlsDao.findAllFileByCallMst(callMstId);
    }

    @Override
    public List<CallFileUploadDtls> findAllFileByFileReceivedTypeAndId(String fileReceivedByType, Long fileReceivedById) {
        return callFileUploadDtlsDao.findAllFileByFileReceivedTypeAndId(fileReceivedByType, fileReceivedById);
    }

    @Override
    public CallFileUploadDtls getAgentFile(CustomerDetails cm) throws Exception {

        CallFileUploadDtls agentFileMst = callFileUploadDtlsDao.findAgentInFileMst(cm);
        return agentFileMst;
    }

    @Override
    public String getVideoFileUrl(String filePath, HttpServletRequest request, ServletContext ctx) {

        String videoURL = "";
        try {
            String projectHome = System.getenv("VIDEOBANKING_HOME");
            String targetFolder = filePath;
            String dbFilePath = projectHome + File.separator + targetFolder;
            logger.info("dbFilePath :" + dbFilePath);
            File destFile = new File(dbFilePath);
            String deploymentDirectoryPath = ctx.getRealPath(File.separator);
            String websiteURL = request.getRequestURL().substring(0, request.getRequestURL().indexOf("/", 9));
            String imgLink = targetFolder.replace("\\", FSLASH);
            videoURL = websiteURL + request.getContextPath() + imgLink;
            logger.info("videoURL:#########################################################" + videoURL);
            deploymentDirectoryPath = deploymentDirectoryPath + FSLASH + targetFolder;
            File contxFile = new File(deploymentDirectoryPath);
            FileUtils.copyFile(destFile, contxFile);

        } catch (Exception ex) {
            logger.error(ex);

        }
        return videoURL;
    }

}
