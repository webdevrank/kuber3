package com.rank.ccms.service.impl;

import com.rank.ccms.dao.PromotionalVideoMstDao;
import com.rank.ccms.entities.PromotionalVideoMst;
import com.rank.ccms.service.PromotionalVideoMstService;
import java.io.File;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("promotionalVideoMstService")
public class PromotionalVideoMstServiceImpl implements PromotionalVideoMstService {

    private final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(PromotionalVideoMstServiceImpl.class);
    private static final String fSlash = "/";

    @Autowired
    private PromotionalVideoMstDao promotionalVideoMstDao;

    @Transactional
    @Override
    public PromotionalVideoMst save(PromotionalVideoMst promotionalVideoMst) {
        if (promotionalVideoMst.getId() == null) {
            try {
                promotionalVideoMst = promotionalVideoMstDao.saveRow(promotionalVideoMst);
                logger.info(" In sevice save method " + promotionalVideoMst);
            } catch (Exception ex) {

                logger.error("1.Date Parsing Exception:" + ex.getMessage(), ex);
            }
        } else {
            PromotionalVideoMst existingPromotionalVideoMst = promotionalVideoMstDao.findById(promotionalVideoMst.getId());
            if (existingPromotionalVideoMst != null) {
                if (existingPromotionalVideoMst != promotionalVideoMst) {
                    existingPromotionalVideoMst.setId(promotionalVideoMst.getId());
                    existingPromotionalVideoMst.setFileUrl(promotionalVideoMst.getFileUrl());
                    existingPromotionalVideoMst.setActiveFlg(promotionalVideoMst.getActiveFlg());
                    existingPromotionalVideoMst.setDeleteFlg(promotionalVideoMst.getDeleteFlg());
                    existingPromotionalVideoMst.setSelectedFlg(promotionalVideoMst.getSelectedFlg());
                    existingPromotionalVideoMst.setFileName(promotionalVideoMst.getFileName());
                }
                try {

                    promotionalVideoMst = promotionalVideoMstDao.mergeRow(existingPromotionalVideoMst);

                } catch (Exception e) {

                    logger.error("2.Date Parsing Exception:" + e.getMessage(), e);
                }
            } else {
                try {
                    promotionalVideoMst = promotionalVideoMstDao.saveRow(promotionalVideoMst);

                } catch (Exception ex) {

                    logger.error("3.Date Parsing Exception:" + ex.getMessage(), ex);
                }
            }
        }

        return promotionalVideoMst;
    }

    @Override
    public String getVideoFileUrl(String filePath, HttpServletRequest request, ServletContext ctx) {
        logger.info("filePath issss=================" + filePath);
        String videoURL = "";
        try {
            String projectHome = System.getenv("VIDEOBANKING_HOME");
            String targetFolder = filePath;
            String dbFilePath = projectHome + File.separator + targetFolder;
            logger.info("dbFilePath :" + dbFilePath);
            String deploymentDirectoryPath = ctx.getRealPath(File.separator);
            String websiteURL = request.getRequestURL().substring(0, request.getRequestURL().indexOf("/", 9));
            String imgLink = targetFolder.replace("\\", fSlash);
            logger.info(" imgLink " + imgLink);
            videoURL = websiteURL + request.getContextPath() + fSlash + imgLink;
            logger.info("videoURL:#########################################################" + videoURL);
            deploymentDirectoryPath = deploymentDirectoryPath + fSlash+ targetFolder;
            logger.info("==========deploymentDirectoryPath=====" + deploymentDirectoryPath);

        } catch (Exception ex) {
            logger.error(ex);

        }
        return videoURL;
    }

    @Override
    public PromotionalVideoMst findPromotionalVideoMstById(Long id) {
        return promotionalVideoMstDao.findNonDeletedById(id);
    }

    @Override
    public List<PromotionalVideoMst> findActivePromotionalVideo() {
        return promotionalVideoMstDao.findAllNonDeleted();
    }

    @Override
    public void deletePromotionalVideoMst(PromotionalVideoMst promotionalVideoMst) {
        promotionalVideoMstDao.deleteRow(promotionalVideoMst);
    }

    @Override
    public PromotionalVideoMst findSelectedPromotionalVideo() {
        PromotionalVideoMst promoVid = promotionalVideoMstDao.findSelectedPromotionalVideo();

        return promoVid;
    }

    @Override
    public PromotionalVideoMst findByCaptionName(String caption) {
        PromotionalVideoMst promoVid = promotionalVideoMstDao.findByCaptionName(caption);
        return promoVid;
    }

    @Override
    public PromotionalVideoMst findByVideoFileName(String fileName) {
        PromotionalVideoMst promoVid = promotionalVideoMstDao.findByVideoFileName(fileName);
        return promoVid;

    }
}
