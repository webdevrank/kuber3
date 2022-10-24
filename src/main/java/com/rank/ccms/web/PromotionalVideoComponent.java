package com.rank.ccms.web;

import com.rank.core.util.OTP;
import com.rank.ccms.dto.PromotionalVideoDto;
import com.rank.ccms.entities.PromotionalVideoMst;
import com.rank.ccms.service.PromotionalVideoMstService;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import org.apache.commons.io.FileUtils;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class PromotionalVideoComponent implements Serializable {

    private static final long serialVersionUID = 4310860313478330118L;

    private static final Logger logger = Logger.getLogger(PromotionalVideoComponent.class);
    private static final String FSLASH = "/";
    private final static Pattern VIDEO_FILE_PATTERN = Pattern.compile("([^\\s]+(\\.(?i)(mp4))$)");//avi|mkv|3gp|mpg|riff|vob|mp4|m2ts|mov

    private PromotionalVideoDto promotionalVideoDto = new PromotionalVideoDto();
    private String fileUploadMsg;
    private String videoFileName;
    private Long promoVideoId;
    private String playFileUrl;
    private FileUploadEvent event1 = null;
    private boolean promotionalVideoUploadRender = true;
    private boolean promotionalVideoPlayRender = false;
    String uploadMsg = "";

    private List<PromotionalVideoDto> listPromotionalVideoDto = new ArrayList<>();

    @Autowired
    private PromotionalVideoMstService promotionalVideoMstService;

    public void newPromotionalVideoComponent() {
        promotionalVideoDto = new PromotionalVideoDto();
        fileUploadMsg = "";
        videoFileName = "";
        promoVideoId = null;
        event1 = null;
    }

    public void openDialog() {
        event1 = null;
        promotionalVideoDto = new PromotionalVideoDto();
        RequestContext.getCurrentInstance().update("prompVideoUploadDigForm");
    }

    public void sDeletePromoVideoId(Long promotionalVideoMstId) {
        try {

            this.setPromoVideoId(promotionalVideoMstId);
            listPromotionalVideo();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void sPlayPromoVideoId(Long promotionalVideoMstId) {
        HttpServletRequest request = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest());
        ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        try {
            PromotionalVideoMst promoVideo = promotionalVideoMstService.findPromotionalVideoMstById(promotionalVideoMstId);
            if (null != promoVideo) {
                String localPalyFileUrl = promotionalVideoMstService.getVideoFileUrl(promoVideo.getFileUrl(), request, ctx);
                this.setPlayFileUrl(localPalyFileUrl);
                this.setPromotionalVideoPlayRender(true);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void sStopPlayPromoVideoId() {
        try {

            this.setPromotionalVideoPlayRender(false);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void checkErrors() {
    }

    public static boolean validateFileExtn(String userName) {

        Matcher mtch = VIDEO_FILE_PATTERN.matcher(userName);
        return mtch.matches();
    }

    public String back() {
        listPromotionalVideo();
        return "/pages/promotionalVideo/listPromotionalVideo.xhtml";
    }

    public void checkDuplicateCaptionNameForPromoVideo(FacesContext ctx, UIComponent component, Object value)
            throws ValidatorException {
        String dsgnNm = value.toString();

        if (!value.toString().trim().equals("")) {
            PromotionalVideoMst lDesignationMst = promotionalVideoMstService.findByCaptionName(dsgnNm);
            if (lDesignationMst != null) {
                FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Error: Promotional Video Caption Name is already exists ", "Error: Promotional Video Caption Name is already exists ");
                throw new ValidatorException(fma);
            }
        } else {
            FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Promotional Video Caption Name: Validation Error: Promotional Video Caption Name can not be blank", "Promotional Video Caption Name: Validation Error:Promotional Video Caption Name can not be blank");
            throw new ValidatorException(fma);
        }
    }

    public void checkDuplicateFileNameForPromoVideo(FacesContext ctx, UIComponent component, Object value)
            throws ValidatorException {
        String dsgnNm = value.toString();

        if (!value.toString().trim().equals("")) {
            PromotionalVideoMst lDesignationMst = promotionalVideoMstService.findByVideoFileName(dsgnNm);
            if (lDesignationMst != null) {
                FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Error: Promotional Video File Name is already exists ", "Error: Promotional Video File Name is already exists ");
                throw new ValidatorException(fma);
            }
        } else {
            FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Promotional Video File Name: Validation Error: Promotional Video Caption File can not be blank", "Promotional Video File Name: Validation Error:Promotional Video File Name can not be blank");
            throw new ValidatorException(fma);
        }
    }

    public void delete() {
        try {
            logger.info("PromoIdIs========================" + promoVideoId);
            PromotionalVideoMst lVideoMst = promotionalVideoMstService.findPromotionalVideoMstById(promoVideoId);
            String projectHome = System.getenv("VIDEOBANKING_HOME");

            if (lVideoMst != null) {
                String realFilePath = projectHome + File.separator + lVideoMst.getFileUrl();
                File file = new File(realFilePath);
                promotionalVideoMstService.deletePromotionalVideoMst(lVideoMst);
                file.delete();
                String jbossHome = System.getenv("JBOSS_HOME");
                String path = jbossHome + File.separator + "standalone" + File.separator + "tmp" + File.separator + "vfs" + File.separator + lVideoMst.getFileUrl();
                File file2 = new File(path);
                file2.delete();
                FacesContext.getCurrentInstance()
                        .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, lVideoMst.getFileName() + " has been deleted", ""));
                file.delete();
            } else {
                logger.info("Delete operation is failed.");
                FacesContext.getCurrentInstance()
                        .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, " deletion Failed", ""));
            }
        } catch (Exception e) {
            logger.info("error " + e.getMessage());
        } finally {
            listPromotionalVideo();
            promoVideoId = null;
        }
    }

    public void select(Long promoVideoId) {
        try {

            List<PromotionalVideoMst> listPromoVideo = promotionalVideoMstService.findActivePromotionalVideo();
            for (PromotionalVideoMst lVideoMst : listPromoVideo) {
                if (promoVideoId == (long) lVideoMst.getId()) {
                    lVideoMst.setSelectedFlg(true);
                    videoFileName = lVideoMst.getFileName();
                } else {
                    lVideoMst.setSelectedFlg(false);
                }
                promotionalVideoMstService.save(lVideoMst);
            }
            listPromotionalVideo();
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "File has been Selected for Promotional Successfully", ""));
        } catch (Exception e) {
            logger.info("error " + e.getMessage());
        }
    }

    public void listPromotionalVideo() {
        try {
            HttpServletRequest request = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest());
            ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            listPromotionalVideoDto = new ArrayList<>();
            List<PromotionalVideoMst> listPromoVideo = promotionalVideoMstService.findActivePromotionalVideo();
            for (PromotionalVideoMst lVideoMst : listPromoVideo) {
                PromotionalVideoDto lVideoDto = new PromotionalVideoDto();
                lVideoDto.setFileName(lVideoMst.getFileName());
                lVideoDto.setPromotionalVideoMstId(lVideoMst.getId());
                if (lVideoMst.getVideoCaption() != null) {
                    lVideoDto.setCaption(lVideoMst.getVideoCaption());
                } else {
                    lVideoDto.setCaption("");
                }
                lVideoDto.setSelectFlag(lVideoMst.getSelectedFlg());

                listPromotionalVideoDto.add(lVideoDto);
            }
        } catch (Exception e) {
            logger.error(" Error " + e.getMessage());
        }

    }

    public void uploadFile1(FileUploadEvent event) {

        event1 = event;
        if (event1 == null) {

        } else {
            promotionalVideoUploadRender = false;

        }
    }

    public void addPromoVideo(HttpServletRequest request) {

        try {

            if (event1 == null) {
                uploadMsg = "Error! Please upload a promotional video";

            } else {
                PromotionalVideoMst lDesignationMst = promotionalVideoMstService.findByVideoFileName(event1.getFile().getFileName());
                if (lDesignationMst != null) {
                    uploadMsg = "Upload failed: Promotional Video File Name is already exists";
                } else {
                    uploadFile(event1, promotionalVideoDto.getCaption());
                    uploadMsg = "Promotional video uploaded successFully";

                }
            }
        } catch (Exception ex) {
            uploadMsg = "Error! could not upload promotional video";

            logger.info("Error " + ex);
        } finally {
            RequestContext.getCurrentInstance().update("sform");
            RequestContext.getCurrentInstance().execute("PF('prompVideoUploadWid').hide();PF('promoVideoSuccessDialog').show();");

            promotionalVideoUploadRender = true;
        }
    }

    public void reset() {
        promotionalVideoUploadRender = true;
    }

    public void uploadFile(FileUploadEvent event, String caption) throws IOException {
        HttpServletRequest request = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest());
        RequestContext.getCurrentInstance().execute("$('#spinner').show();");
        boolean flag;
        String gen = OTP.generateFileName();
        String newFileName;
        String videoURL;
        fileUploadMsg = "";
        try {
            String fileName1 = event.getFile().getFileName();
            String extension = fileName1.substring(fileName1.lastIndexOf(".") + 1, fileName1.length());

            if (!extension.equalsIgnoreCase("mp4")) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid File Extension. Only mp4 allowed", ""));
            } else {
                String jbossHome = System.getenv("JBOSS_HOME");
//                String fileName=fileName1.replaceAll(" ","_").replaceAll("'", "");
                String fileName = URLEncoder.encode(fileName1, "UTF-8");
                String projectHome = System.getenv("VIDEOBANKING_HOME");
                newFileName = gen + fileName;
                String targetFolder = "PromotionalVideo" + File.separator + newFileName;
                String dbFilePath = projectHome + File.separator + targetFolder;
                File destFile = new File(dbFilePath);
                FileUtils.copyInputStreamToFile(event.getFile().getInputstream(), destFile);
                ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
                String deploymentDirectoryPath = ctx.getRealPath(File.separator);
                String websiteURL = request.getRequestURL().substring(0, request.getRequestURL().indexOf("/", 9));
                videoURL = websiteURL + request.getContextPath() + FSLASH + "PromotionalVideo" + File.separator + newFileName;
                deploymentDirectoryPath = deploymentDirectoryPath + FSLASH + targetFolder;
                String path = jbossHome + File.separator + "standalone" + File.separator + "tmp" + File.separator + "vfs" + File.separator + targetFolder;

                File contxFile = new File(deploymentDirectoryPath);
                File serverFile = new File(path);
                FileUtils.copyFile(destFile, contxFile);
                FileUtils.copyInputStreamToFile(event.getFile().getInputstream(), serverFile);
                flag = true;

                if (flag) {
                    PromotionalVideoMst lpromotionalVideoMst = new PromotionalVideoMst();

                    lpromotionalVideoMst.setFileUrl(targetFolder);
                    lpromotionalVideoMst.setActiveFlg(true);
                    lpromotionalVideoMst.setSelectedFlg(false);
                    lpromotionalVideoMst.setDeleteFlg(false);
                    lpromotionalVideoMst.setFileName(fileName);
                    lpromotionalVideoMst.setVideoCaption(caption);

                    promotionalVideoMstService.save(lpromotionalVideoMst);

                    listPromotionalVideo();
                    fileUploadMsg = "File Upload SuccessFully";
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO, fileUploadMsg, ""));
                    //RequestContext.getCurrentInstance().update("promoVideoId");
                    RequestContext.getCurrentInstance().execute("PF('prompVideoUploadWid').hide();");
                } else {
                    fileUploadMsg = " File Not Uploaded Successfully";
                }
            }
        } catch (IOException ex) {
            fileUploadMsg = "File Not Uploaded SuccessFully";
            logger.error(ex);

        } catch (Exception e) {
            logger.info(e);
            fileUploadMsg = "File Not Uploaded SuccessFully";

        } finally {
            logger.info(" In log " + fileUploadMsg);
            RequestContext.getCurrentInstance().execute("$('#spinner').hide();");
            event1 = null;
        }
    }

    public Long getPromoVideoId() {
        return promoVideoId;
    }

    public void setPromoVideoId(Long promoVideoId) {
        this.promoVideoId = promoVideoId;
    }

    public String getPlayFileUrl() {
        return playFileUrl;
    }

    public void setPlayFileUrl(String playFileUrl) {
        this.playFileUrl = playFileUrl;
    }

    public PromotionalVideoDto getPromotionalVideoDto() {
        return promotionalVideoDto;
    }

    public void setPromotionalVideoDto(PromotionalVideoDto promotionalVideoDto) {
        this.promotionalVideoDto = promotionalVideoDto;
    }

    public String getFileUploadMsg() {
        return fileUploadMsg;
    }

    public void setFileUploadMsg(String fileUploadMsg) {
        this.fileUploadMsg = fileUploadMsg;
    }

    public List<PromotionalVideoDto> getListPromotionalVideoDto() {
        return listPromotionalVideoDto;
    }

    public void setListPromotionalVideoDto(List<PromotionalVideoDto> listPromotionalVideoDto) {
        this.listPromotionalVideoDto = listPromotionalVideoDto;
    }

    public PromotionalVideoMstService getPromotionalVideoMstService() {
        return promotionalVideoMstService;
    }

    public void setPromotionalVideoMstService(PromotionalVideoMstService promotionalVideoMstService) {
        this.promotionalVideoMstService = promotionalVideoMstService;
    }

    public String getVideoFileName() {
        return videoFileName;
    }

    public void setVideoFileName(String videoFileName) {
        this.videoFileName = videoFileName;
    }

    public FileUploadEvent getEvent1() {
        return event1;
    }

    public void setEvent1(FileUploadEvent event1) {
        this.event1 = event1;
    }

    public boolean isPromotionalVideoUploadRender() {
        return promotionalVideoUploadRender;
    }

    public void setPromotionalVideoUploadRender(boolean promotionalVideoUploadRender) {
        this.promotionalVideoUploadRender = promotionalVideoUploadRender;
    }

    public boolean isPromotionalVideoPlayRender() {
        return promotionalVideoPlayRender;
    }

    public void setPromotionalVideoPlayRender(boolean promotionalVideoPlayRender) {
        this.promotionalVideoPlayRender = promotionalVideoPlayRender;
    }

    public String getUploadMsg() {
        return uploadMsg;
    }

    public void setUploadMsg(String uploadMsg) {
        this.uploadMsg = uploadMsg;
    }
}
