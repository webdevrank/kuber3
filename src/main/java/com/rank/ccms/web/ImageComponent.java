package com.rank.ccms.web;

import com.rank.ccms.entities.CallMst;
import com.rank.ccms.entities.CallRecords;
import com.rank.ccms.entities.CustomerMst;
import com.rank.ccms.service.CallRecordsService;
import com.rank.ccms.service.CategoryMstService;
import com.rank.ccms.service.CustomerMstService;
import com.rank.ccms.service.LanguageMstService;
import com.rank.ccms.service.ServiceMstService;
import java.io.Serializable;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class ImageComponent implements Serializable {

    private final Logger logger = Logger.getLogger(this.getClass());
    @Autowired
    private CustomerMstService customerMstService;
    @Autowired
    private CategoryMstService categoryMstService;
    @Autowired
    private ServiceMstService serviceMstService;
    @Autowired
    private LanguageMstService languageMstService;
    @Autowired
    private CallRecordsService callRecordsService;

    private String userNameText;
    private String fileLocationCustomerText;
    private String refreshedImagePath;
    private boolean showLink = false;

    static int cnt = 0;
    String fileLocation = "";
    String realPath = "";
    String custId = "";
    String custNm = "";
    String custPhone = "";
    String custEmail = "";
    String custAcctNo = "";
    String custSegment = "";
    String custNationality = "";
    String category = "";
    String service = "";
    String language = "";
    String filemessage = "";
    String idCardName = "";
    String idCardNumber = "";
    String idCardNationality = "";
    String customerLocation = "";
    String latitude;
    String longitude;
    private boolean forwardChatHistory = false;
    private boolean forwardFileShow = false;
    String uploadedFile = "";

    public String getRealPath() {
        if (null == realPath) {
            return realPath = "";
        } else {
            return realPath;
        }
    }

    public void setRealPath(String realPath) {
        this.realPath = realPath;
    }

    public String getFileLocation() {
        if (null == fileLocation) {
            return fileLocation = "../../resources/images/blank_note_document.jpg";
        } else {
            return fileLocation;
        }
    }

    public void reset() {
        this.filemessage = "  ";
        this.uploadedFile = "";
        RequestContext.getCurrentInstance().update("filepanelGrid1");
    }

    public void getCustomerDetails(HttpServletRequest request) {

    }

    public void resetFileForForwardCall(HttpServletRequest request) {
        this.filemessage = " ";
        this.userNameText = (String) request.getSession().getAttribute("userNameText");
    }

    public String getFileLocationCustomerText() {
        return fileLocationCustomerText;
    }

    public void setFileLocationCustomerText(String fileLocationCustomerText) {
        this.fileLocationCustomerText = fileLocationCustomerText;
    }

    public String getUserNameText() {
        return userNameText;
    }

    public void setUserNameText(String userNameText) {
        this.userNameText = userNameText;
    }

    public String getRefreshedImagePath() {
        return refreshedImagePath;
    }

    public void setRefreshedImagePath(String refreshedImagePath) {
        this.refreshedImagePath = refreshedImagePath;
    }

    public boolean getShowLink() {
        return showLink;
    }

    public void setShowLink(boolean showLink) {
        this.showLink = showLink;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getCustNm() {
        return custNm;
    }

    public void setCustNm(String custNm) {
        this.custNm = custNm;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    public String getCustEmail() {
        return custEmail;
    }

    public void setCustEmail(String custEmail) {
        this.custEmail = custEmail;
    }

    public String getCustAcctNo() {
        return custAcctNo;
    }

    public void setCustAcctNo(String custAcctNo) {
        this.custAcctNo = custAcctNo;
    }

    public void setFileLocation(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    public String getCustSegment() {
        return custSegment;
    }

    public void setCustSegment(String custSegment) {
        this.custSegment = custSegment;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean getForwardChatHistory() {
        return forwardChatHistory;
    }

    public void setForwardChatHistory(boolean forwardChatHistory) {
        this.forwardChatHistory = forwardChatHistory;
    }

    public String getFilemessage() {
        return filemessage;
    }

    public void setFilemessage(String filemessage) {
        this.filemessage = filemessage;
    }

    public String getIdCardName() {
        return idCardName;
    }

    public void setIdCardName(String idCardName) {
        this.idCardName = idCardName;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getIdCardNationality() {
        return idCardNationality;
    }

    public void setIdCardNationality(String idCardNationality) {
        this.idCardNationality = idCardNationality;
    }

    public String getCustNationality() {
        return custNationality;
    }

    public void setCustNationality(String custNationality) {
        this.custNationality = custNationality;
    }

    public String getCustomerLocation() {
        return customerLocation;
    }

    public void setCustomerLocation(String customerLocation) {
        this.customerLocation = customerLocation;
    }

    public void getCustomerData() {
        custId = "";
        custNm = "";
        custAcctNo = "";
//        String customerAccountNumber;
        try {
            ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
            HttpSession session = ((HttpServletRequest) extContext.getRequest()).getSession(false);

            CallMst callMst = (CallMst) session.getAttribute("callMst");

            if (null == callMst
                    || null == callMst.getId()) {
                logger.error("Call Master Data Not Found in the current Session");
            } else {
                CustomerMst customerMst = customerMstService.findCustomerMstById(callMst.getCustomerId().getId());

                if (null == customerMst) {

                    logger.error("Customer Master Data Not Found For the Session Call Master in the current Session");
                } else {
//                    customerAccountNumber = customerMst.getAccountNo();
                    custId = customerMst.getCustId();
                    this.setCustNm(customerMst.getFirstName() + " " + customerMst.getLastName());
                    this.setCustAcctNo(customerMst.getAccountNo());
                    this.setCustSegment(customerMst.getCustSeg());
                    this.setCustEmail(customerMst.getEmail());
                    this.setCustPhone(customerMst.getCellPhone() + "");
                    this.setLanguage(customerMst.getCustLang1());
                    this.setCustId(customerMst.getCustId());
                    this.setCustNationality(customerMst.getNatinality());
                    this.setCustomerLocation(callMst.getCallLocation());
                    this.setCustomerLocation(callMst.getCallLocation());
                    this.setLatitude(callMst.getCallLatitude());
                    this.setLongitude(callMst.getCallLongitude());
                }

                // need to change as to different call
                try {
                    category = categoryMstService.findCategoryMstById(callMst.getCategoryId()).getCatgName();
                    service = serviceMstService.findAllServiceMstById(callMst.getServiceId()).getServiceName();
                    language = languageMstService.findNonDeletedLanguageMstById(callMst.getLanguageId()).getLanguageName();
                } catch (Exception ex) {
                    System.err.println("Error while Retrieving Category/Service/Language: 3" + ex.getMessage());
                }
            }

        } catch (Exception ex) {
            logger.error("imagecomponent:getCustomerData:Error:" + ex.getMessage());
            logger.info(ex.getMessage());

        }
    }

    public void userFileCompletePath() {

        logger.info("userFileCompletePath..........called");
        custId = "";
        custNm = "";
        custAcctNo = "";
        fileLocation = "../../resources/images/blank_note_document.jpg";
        //  String customerAccountNumber = "101100519888";
        String customerAccountNumber;
        try {
            ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
            HttpSession session = ((HttpServletRequest) extContext.getRequest()).getSession(false);
            CallMst callMst = (CallMst) session.getAttribute("callMst");

            if (null == callMst
                    || null == callMst.getId()) {
                logger.error("Call Master Data Not Found in the current Session");
            } else {
                CustomerMst customerMst = customerMstService.findCustomerMstById(callMst.getCustomerId().getId());

                if (null == customerMst) {

                    logger.error("Customer Master Data Not Found For the Session Call Master in the current Session");
                } else {
                    customerAccountNumber = customerMst.getAccountNo();
                    custId = customerMst.getCustId();
                    custNm = customerMst.getFirstName().toUpperCase() + " " + (null == customerMst.getMidName() ? "" : customerMst.getMidName().toUpperCase()) + " " + customerMst.getLastName().toUpperCase();
                    custAcctNo = customerAccountNumber;
                    custSegment = customerMst.getCustSeg();
                }

                // need to change as to different call
                try {
                    category = categoryMstService.findCategoryMstById(callMst.getCategoryId()).getCatgName();
                    service = serviceMstService.findAllServiceMstById(callMst.getServiceId()).getServiceName();
                    language = languageMstService.findNonDeletedLanguageMstById(callMst.getLanguageId()).getLanguageName();
                } catch (Exception ex) {
                    logger.info("Error userFileCompletePath while Retrieving Category/Service/Language 1:" + ex.getMessage());
                    System.err.println("Error userFileCompletePath while Retrieving Category/Service/Language 1:" + ex.getMessage());
                }

            }

        } catch (Exception ex) {

            logger.info("Error:userFileCompletePath" + ex.getMessage());
        }

    }

    public void getPreviousChatHistory() {
        // logger.info("getPreviousChatHistory called....");
        HttpSession session = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession(false);
        CallMst callMstLocal = (CallMst) session.getAttribute("callMst");

        if (callMstLocal != null && !this.forwardChatHistory) {
            CallRecords callRecords = callRecordsService.findCallRecordsByCallId(callMstLocal.getId(), (long) 0);
            if (callRecords != null) {
                if (null != callRecords.getChatText()) {
                    if (!callRecords.getChatText().trim().equals("")) {
                        if (callRecords.getChatText().contains("chatMessage")) {
                            this.forwardChatHistory = true;
                            logger.info("getPreviousChatHistory found....");
                            RequestContext.getCurrentInstance().update("forwardchattext");
                        }

                    }
                }
            }
        }
    }

    public boolean getForwardFileShow() {
        return forwardFileShow;
    }

    public void setForwardFileShow(boolean forwardFileShow) {
        this.forwardFileShow = forwardFileShow;
    }

    public String getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(String uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

}
