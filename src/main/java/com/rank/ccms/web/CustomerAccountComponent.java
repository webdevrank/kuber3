package com.rank.ccms.web;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.rank.ccms.dto.CustomerAccountDto;
import com.rank.ccms.dto.CustomerLoanDto;
import com.rank.ccms.entities.BankMst;
import com.rank.ccms.entities.CallMst;
import com.rank.ccms.entities.CustomerAccDtl;
import com.rank.ccms.entities.CustomerDtl;
import com.rank.ccms.entities.CustomerLoanDtl;
import com.rank.ccms.entities.CustomerMst;
import com.rank.ccms.service.BankMstService;
import com.rank.ccms.service.CallMstService;
import com.rank.ccms.service.CustomerAccDtlService;
import com.rank.ccms.service.CustomerDtlService;
import com.rank.ccms.service.CustomerLoanDtlService;
import com.rank.ccms.service.CustomerMstService;
import com.rank.ccms.service.EmployeeMstService;
import com.rank.ccms.service.EmployeeTypeMstService;
import com.rank.ccms.service.ServiceMstService;
import com.rank.ccms.util.CallScheduler;
import com.rank.ccms.util.SendingMailUtil;
import com.rank.ccms.util.SocketMessage;
import com.rank.ccms.util.ThreadSafeSimpleDateFormat;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.codehaus.jackson.annotate.JsonProperty;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("customerAccountComponent")
@Scope("session")
public class CustomerAccountComponent {

    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CustomerAccountComponent.class);
    @Autowired
    private CustomerDtlService customerDtlService;
    @Autowired
    private CustomerLoanDtlService customerLoanDtlService;
    @Autowired
    private BankMstService bankMstService;
    @Autowired
    private CallMstService callMasterService;
    @Autowired
    private CustomerMstService customerMstService;
    @Autowired
    private ServiceMstService serviceMstService;
    @Autowired
    CallScheduler callScheduler;
    private String snapImage = "/resources/images/no_img.jpg";
    private String uploadedNationalId = "/resources/images/no_img.jpg";
    private String uploadedAddProof = "/resources/images/no_img.jpg";
    private String newSnapImage = "/resources/images/no_img.jpg";
    private String newUploadedNationalId = "/resources/images/no_img.jpg";
    private String newUploadedAddProof = "/resources/images/no_img.jpg";
    private String faceFromPanCard = "/resources/images/no_img.jpg";
    private String signFromPanCard = "/resources/images/no_img.jpg";
    private String customerFormPdfPath;
    private String matchresult = "0%";
    private CustomerAccountDto customerAccountDto;
    private CustomerLoanDto customerLoanDto;
    private Date presentDate;
    private String signatureCoOrdinates;
    private CustomerMst customerMst;
    @JsonProperty("Signature")
    private String signature;

    @Autowired
    private CustomerAccDtlService customerAccDtlService;

    @Autowired
    private EmployeeMstService employeeMstService;

    @Autowired
    private EmployeeTypeMstService employeeTypeMstService;

    @Autowired
    private HttpServletRequest requests;

    @Autowired
    public void init() {
        logger.info("CustomerAccountComponent init....................................................................");
        customerAccountDto = new CustomerAccountDto();
        customerLoanDto = new CustomerLoanDto();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        this.setSnapImage(requests.getContextPath() + "/resources/images/no_img.jpg");
        this.setUploadedNationalId(requests.getContextPath() + "/resources/images/no_img.jpg");
        this.setSignFromPanCard(requests.getContextPath() + "/resources/images/no_img.jpg");
        this.setFaceFromPanCard(requests.getContextPath() + "/resources/images/no_img.jpg");
        this.setMatchresult("0");
        newSnapImage = requests.getContextPath() + "/resources/images/no_img.jpg";
        newUploadedNationalId = requests.getContextPath() + "/resources/images/no_img.jpg";
        newUploadedAddProof = requests.getContextPath() + "/resources/images/no_img.jpg";

        presentDate = cal.getTime();
        logger.info("presentDate...................................................................." + presentDate);
    }

    public void newCustomerAccount() {
        logger.info("newCustomerAccount....................................................................");
        customerAccountDto = new CustomerAccountDto();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        this.setSnapImage(requests.getContextPath() + "/resources/images/no_img.jpg");
        this.setUploadedNationalId(requests.getContextPath() + "/resources/images/no_img.jpg");
        this.setSignFromPanCard(requests.getContextPath() + "/resources/images/no_img.jpg");
        this.setFaceFromPanCard(requests.getContextPath() + "/resources/images/no_img.jpg");
        this.setUploadedAddProof(requests.getContextPath() + "/resources/images/no_img.jpg");
        this.setMatchresult("0");
        newSnapImage = requests.getContextPath() + "/resources/images/no_img.jpg";
        newUploadedNationalId = requests.getContextPath() + "/resources/images/no_img.jpg";
        newUploadedAddProof = requests.getContextPath() + "/resources/images/no_img.jpg";

        presentDate = cal.getTime();
        logger.info("presentDate...................................................................." + presentDate);
    }

    public void existingCustomerLoanAccount() {
        logger.info("existingCustomerLoanAccount....................................................................");
        customerLoanDto = new CustomerLoanDto();
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        this.setSnapImage(requests.getContextPath() + "/resources/images/no_img.jpg");
        this.setUploadedNationalId(requests.getContextPath() + "/resources/images/no_img.jpg");
        this.setSignFromPanCard(requests.getContextPath() + "/resources/images/no_img.jpg");
        this.setFaceFromPanCard(requests.getContextPath() + "/resources/images/no_img.jpg");
        this.setUploadedAddProof(requests.getContextPath() + "/resources/images/no_img.jpg");
        this.setMatchresult("0");
        newSnapImage = requests.getContextPath() + "/resources/images/no_img.jpg";
        newUploadedNationalId = requests.getContextPath() + "/resources/images/no_img.jpg";
        newUploadedAddProof = requests.getContextPath() + "/resources/images/no_img.jpg";

        presentDate = cal.getTime();
        logger.info("presentDate...................................................................." + presentDate);
    }

//This method is used by Agent
    public void saveLoanFrm(HttpServletRequest request) {
        logger.info("customerLoanDto:" + customerLoanDto);
        CustomerLoanDtl customerLoanDtl = new CustomerLoanDtl();
        String pathToDatabaseSnap;
        String pathToDatabaseNationalId;
        String pathToDatabaseUtilityBill;

        try {
            HttpSession session = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession();
            CallMst cm = (CallMst) session.getAttribute("callMst");
            cm = callMasterService.findCallMstById(cm.getId());
            logger.info("cm:#CUST-ID::" + cm.getCustId());
            customerLoanDtl.setAddress(customerLoanDto.getAddress());
            List<BankMst> bankMstList = bankMstService.findAllNonDeleted();
            BankMst bankMst = null;
            if (!bankMstList.isEmpty()) {
                bankMst = bankMstList.get(0);
            }
            customerLoanDtl.setBankMstId(bankMst.getId());
            customerLoanDtl.setDob(customerLoanDto.getDob());
            customerLoanDtl.setEducation(customerLoanDto.getEducation());
            customerLoanDtl.setEmail(customerLoanDto.getEmail());
            customerLoanDtl.setFullName(customerLoanDto.getFullName());
            customerLoanDtl.setGender(customerLoanDto.getGender());
            customerLoanDtl.setMaritailStatus(customerLoanDto.getMaritialStatus());
            customerLoanDtl.setNationality(customerLoanDto.getNationality());
            customerLoanDtl.setOccupation(customerLoanDto.getOccupation());
            customerLoanDtl.setPhoneNo(customerLoanDto.getPhone());
            customerLoanDtl.setAnnualIncome(customerLoanDto.getAnnualIncome());
            customerLoanDtl.setAccountNumber(customerLoanDto.getAccountNo());
            customerLoanDtl.setLoanAmount(customerLoanDto.getLoanAmount());
            String tempFile;
            tempFile = this.getSnapImage();
            if (this.getSnapImage().indexOf("\\") > 0) {
                tempFile = this.getSnapImage().replace("\\", "/");
            }
            logger.info("snap this.getSnapImage():::::::::: " + this.getSnapImage());
            pathToDatabaseSnap = "/resources/File_Upload/Call/" + cm.getId() + "/" + tempFile.substring(tempFile.lastIndexOf("/") + 1);

            customerLoanDtl.setCustomerImage(pathToDatabaseSnap);
            tempFile = this.getUploadedNationalId();
            if (this.getUploadedNationalId().indexOf("\\") > 0) {
                tempFile = this.getUploadedNationalId().replace("\\", "/");
            }
            logger.info("NationalId temp:::::::::: " + tempFile);
            pathToDatabaseNationalId = "/resources/File_Upload/Call/" + cm.getId() + "/" + tempFile.substring(tempFile.lastIndexOf("/") + 1);
            customerLoanDtl.setNationalId(pathToDatabaseNationalId);
            tempFile = this.getUploadedAddProof();
            if (this.getUploadedAddProof().indexOf("\\") > 0) {
                tempFile = this.getUploadedAddProof().replace("\\", "/");
            }
            logger.info("AddProof temp:::::::::: " + tempFile);
            pathToDatabaseUtilityBill = "/resources/File_Upload/Call/" + cm.getId() + "/" + tempFile.substring(tempFile.lastIndexOf("/") + 1);
            customerLoanDtl.setUtilityBill(pathToDatabaseUtilityBill);

            customerLoanDtl = customerLoanDtlService.saveCustomerLoanDtl(customerLoanDtl);
            logger.info("customerLoanDtl:" + customerLoanDtl);
            if (null != customerLoanDtl) {
                //RequestContext.getCurrentInstance().execute("sendNotiAccoundSave('" + cm.getCustId() + "')");
//                sendMail(String mailTo, StringBuffer sbMessageBody, String subject);
                try {
                    SocketMessage.send(callScheduler.getAdminSocket(), cm.getCustId(), "loanAccountSaveNoti#Loan Account Application Signature Form");
                } catch (Exception ex) {
                    logger.error(ex);
                }
                this.existingCustomerLoanAccount();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Saved Successfully", "Saved Successfully");
                FacesContext.getCurrentInstance().addMessage(null, msg);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    //This method is used by Agent
    public void saveAccFrm(HttpServletRequest request) {
        logger.info("customerAccountDto:" + customerAccountDto);
        CustomerDtl customerDtl = new CustomerDtl();

        try {
            HttpSession session = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession();
            CallMst cm = (CallMst) session.getAttribute("callMst");
            cm = callMasterService.findCallMstById(cm.getId());
            logger.info("cm:#CUST-ID::" + cm.getCustId());
            customerDtl.setAddress(customerAccountDto.getAddress());
            List<BankMst> bankMstList = bankMstService.findAllNonDeleted();
            BankMst bankMst = null;
            String pathToDatabaseSnap;
            String pathToDatabaseNationalId;
            String pathToDatabaseUtilityBill;

            if (!bankMstList.isEmpty()) {
                bankMst = bankMstList.get(0);
            }
            logger.info("cm:#CUST-ID:::" + cm.getCustId());
            customerDtl.setBankMstId(bankMst);
            customerDtl.setDob(customerAccountDto.getDob());
            customerDtl.setEducation(customerAccountDto.getEducation());
            customerDtl.setEmail(customerAccountDto.getEmail());
            customerDtl.setFullName(customerAccountDto.getFullName());
            customerDtl.setGender(customerAccountDto.getGender());
            customerDtl.setMaritailStatus(customerAccountDto.getMaritialStatus());
            customerDtl.setNationality(customerAccountDto.getNationality());
            customerDtl.setOccupation(customerAccountDto.getOccupation());
            customerDtl.setPhoneNo(customerAccountDto.getPhone());
            customerDtl.setSalary(customerAccountDto.getSalary());
            String tempFile = null;
            logger.info("cm:#CUST-ID::::" + cm.getCustId());
            tempFile = this.getSnapImage();
            if (this.getSnapImage().indexOf("\\") > 0) {
                tempFile = this.getSnapImage().replace("\\", "/");
            }
            pathToDatabaseSnap = "/resources/File_Upload/Call/" + cm.getId() + "/" + tempFile.substring(tempFile.lastIndexOf("/") + 1);
            logger.info("cm:#CUST-ID:::::" + cm.getCustId());
            customerDtl.setCustomerImage(pathToDatabaseSnap);
            tempFile = this.getUploadedNationalId();
            if (this.getUploadedNationalId().indexOf("\\") > 0) {
                tempFile = this.getUploadedNationalId().replace("\\", "/");
            }
            logger.info("cm:#CUST-ID::::::" + cm.getCustId());
            pathToDatabaseNationalId = "/resources/File_Upload/Call/" + cm.getId() + "/" + tempFile.substring(tempFile.lastIndexOf("/") + 1);
            customerDtl.setNationalId(pathToDatabaseNationalId);
            tempFile = this.getUploadedAddProof();
            if (this.getUploadedAddProof().indexOf("\\") > 0) {
                tempFile = this.getUploadedAddProof().replace("\\", "/");
            }
            logger.info("cm:#CUST-ID:::::::" + cm.getCustId());
            pathToDatabaseUtilityBill = "/resources/File_Upload/Call/" + cm.getId() + "/" + tempFile.substring(tempFile.lastIndexOf("/") + 1);
            customerDtl.setUtilityBill(pathToDatabaseUtilityBill);

            switch (serviceMstService.findAllServiceMstById(cm.getServiceId()).getServiceCd()) {
                case "CASA":
                    customerDtl.setCustomerType("account");
                    break;
                case "CC":
                    customerDtl.setCustomerType("credit card");
                    break;
            }

            customerDtl = customerDtlService.saveCustomerDtl(customerDtl);
            logger.info("customerDtl:" + customerDtl);
            if (null != customerDtl) {
                //RequestContext.getCurrentInstance().execute("sendNotiAccoundSave('" + cm.getCustId() + "')");
//                sendMail(String mailTo, StringBuffer sbMessageBody, String subject);
                try {
                    SocketMessage.send(callScheduler.getAdminSocket(), cm.getCustId(), "accountSaveNoti#Account Opening Signature Form");
                } catch (Exception ex) {
                    logger.error(ex);
                }
                this.newCustomerAccount();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Saved Successfully", "Saved Successfully");
                FacesContext.getCurrentInstance().addMessage(null, msg);

            }
        } catch (Exception ex) {
            logger.error(ex);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void saveSignedLoanAccFrm(HttpServletRequest request) {
        logger.info("customerAccountDto:" + customerLoanDto.toString());
        //customerAccountDto=new CustomerAccountDto();
        CustomerLoanDtl customerLoanDtl = new CustomerLoanDtl();
        CallMst callMstFromCustomerSession = (CallMst) request.getSession().getAttribute("callMstThroughWeb");

        ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String deploymentDirectoryPath = ctx.getRealPath("/");
        String jbossHome = System.getenv("JBOSS_HOME");
        String websiteURL = request.getRequestURL().substring(0, request.getRequestURL().indexOf("/", 9));
        boolean check = false;

        this.saveSignatureForLoan();
        try {
            InputStream signatureFileData = new ByteArrayInputStream(Base64.decodeBase64(customerLoanDto.getSignature()));
            /*  BufferedImage bImageFromConvert = ImageIO.read(signatureFileData);
            String imagePath = no + "snapshot.jpg";

            File ff = new File(deploymentDirectoryPath + "/crm/" + imagePath);
            File n = new File(deploymentDirectoryPath + "/crm");
            if (!n.exists()) {
                n.mkdirs();
            }
            //File outputfile = new File("E:\\Learning\\image3.jpg");
            ImageIO.write(bImageFromConvert, "png", ff);
            String website = request.getRequestURL().substring(0, request.getRequestURL().indexOf("/", 9));
            accountComponent.setSnapImage(website + request.getContextPath() + "/crm/" + imagePath);*/

            Random rand = new Random();
            int no = rand.nextInt(1000) + 1;
            String tempFilePath = File.separator + "File_Upload" + File.separator + "Call" + File.separator
                    + callMstFromCustomerSession.getId() + File.separator + no
                    + "eSign.png";
            File n = new File(deploymentDirectoryPath + "resources" + File.separator + "File_Upload" + File.separator + "Call" + File.separator
                    + callMstFromCustomerSession.getId());
            if (!n.exists()) {
                n.mkdirs();
            }
            String backupFileLocation = jbossHome + File.separator + "standalone" + tempFilePath;
            n = new File(jbossHome + File.separator + "standalone" + File.separator + "File_Upload" + File.separator + "Call" + File.separator
                    + callMstFromCustomerSession.getId());
            if (!n.exists()) {
                n.mkdirs();
            }
            File backupFile = new File(backupFileLocation);
            FileUtils.copyInputStreamToFile(signatureFileData, backupFile);
            String finalFilePath = deploymentDirectoryPath + "resources" + tempFilePath;

            File finalFile = new File(finalFilePath);
            FileUtils.copyFile(backupFile, finalFile);

            InputStream finput = new FileInputStream(finalFile);
            byte[] imageBytes = new byte[(int) finalFile.length()];
            finput.read(imageBytes, 0, imageBytes.length);
            finput.close();

            String pathToDatabase = null;
            pathToDatabase = "/resources/File_Upload/Call/" + callMstFromCustomerSession.getId() + "/" + no + "eSign.png";

            if (pathToDatabase != null && pathToDatabase.length() > 0) {
                customerLoanDtl.setCustomerSign(pathToDatabase);
            }
            customerLoanDtl.setId(customerLoanDto.getCustDtlId());
            customerLoanDtl.setAddress(customerLoanDto.getAddress());
            List<BankMst> bankMstList = bankMstService.findAllNonDeleted();
            BankMst bankMst = null;
            if (!bankMstList.isEmpty()) {
                bankMst = bankMstList.get(0);
            }
            customerLoanDtl.setBankMstId(bankMst.getId());
            customerLoanDtl.setDob(customerLoanDto.getDob());
            customerLoanDtl.setEducation(customerLoanDto.getEducation());
            customerLoanDtl.setEmail(customerLoanDto.getEmail());
            customerLoanDtl.setFullName(customerLoanDto.getFullName());
            customerLoanDtl.setGender(customerLoanDto.getGender());
            customerLoanDtl.setMaritailStatus(customerLoanDto.getMaritialStatus());
            customerLoanDtl.setNationality(customerLoanDto.getNationality());
            customerLoanDtl.setOccupation(customerLoanDto.getOccupation());
            customerLoanDtl.setPhoneNo(customerLoanDto.getPhone());
            customerLoanDtl.setAnnualIncome(customerLoanDto.getAnnualIncome());
            customerLoanDtl.setLoanAmount(customerLoanDto.getLoanAmount());
            customerLoanDtl.setAccountNumber(customerLoanDto.getAccountNo());
            //customerLoanDtl.setCustomerSign(customerLoanDto.getSignature());
            customerLoanDtl.setCustomerSignCord(customerLoanDto.getSignatureCord());
            customerLoanDtl = customerLoanDtlService.saveCustomerLoanDtl(customerLoanDtl);

            if (customerLoanDtl.getId() != null) {
                String custEmail = customerLoanDtl.getEmail();
                if (null != custEmail) {
                    StringBuffer sb = new StringBuffer(255);
                    sb.append("Dear ").append(customerLoanDtl.getFullName());
                    sb.append(",\n\nThanks for showing your interest\n");
                    sb.append("\nYour Loan application is recieved and has been proccessed for verification ");

                    sb.append(SendingMailUtil.TELE_THX);
                    try {

                        String pdfPath = deploymentDirectoryPath + "/" + customerLoanDtl.getAccountNumber() + ".pdf";
                        this.setCustomerFormPdfPath(customerLoanDtl.getAccountNumber() + ".pdf");
                        if (pdfGeneration(pdfPath, customerLoanDtl)) {
                            boolean mailSend = SendingMailUtil.sendMailWithAttachment(custEmail, sb, SendingMailUtil.LOAN_CUSTOMER, pdfPath, "LoanReqForm.pdf");
                            if (mailSend) {

                                check = true;
                                logger.info("mail sending was successfull... to Customer" + custEmail);
                            }
                        }
                    } catch (Exception ex) {
                        logger.error("Sending Email Error:" + ex.getMessage());
                    }
                }

            } else {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }

            logger.info("customerDtl:" + customerLoanDtl);

            if (check) {
                RequestContext.getCurrentInstance().execute("$('#_savensubmit').hide();");
                this.setSnapImage(request.getContextPath() + "/resources/images/no_img.jpg");
                this.setUploadedNationalId(request.getContextPath() + "/resources/images/no_img.jpg");
                this.setUploadedAddProof(request.getContextPath() + "/resources/images/no_img.jpg");
                this.setSignFromPanCard(requests.getContextPath() + "/resources/images/no_img.jpg");
                this.setFaceFromPanCard(requests.getContextPath() + "/resources/images/no_img.jpg");
                this.setMatchresult("0");
                RequestContext.getCurrentInstance().execute("$('#spinner').hide()");
                RequestContext.getCurrentInstance().execute("controlparent()");
                signature = null;
                signatureCoOrdinates = null;
                this.existingCustomerLoanAccount();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Submitted Successfully", "Saved Successfully");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                RequestContext.getCurrentInstance().execute("setTimeout(()=>{self.close();}, 3000);");
            } else {
                RequestContext.getCurrentInstance().execute("$('#spinner').hide()");
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Something went wrong!", "Opps! something went wrong.");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }

        } catch (Exception ex) {
            logger.error(ex);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void getCustomerAccountDetails(HttpServletRequest request, String custId) {
        logger.info("getCustomerAccountDetails:" + custId);

        String link;
        String linkName = "Open Document";

        String website;
        logger.info(request.getRequestURL());
        website = request.getRequestURL().substring(0, request.getRequestURL().indexOf("/", 9));

        RequestContext requestContext = RequestContext.getCurrentInstance();

        link = website + request.getContextPath() + "/signAccount";

        requestContext.execute("openDocumentsCustom('" + link + "','" + linkName + "')");

    }

    public void getCustomerLoanAccountDetails(HttpServletRequest request, String custId) {
        logger.info("getCustomerLoanAccountDetails:" + custId);

        String link;
        String linkName = "Open Document";

        String website;
        logger.info(request.getRequestURL());
        website = request.getRequestURL().substring(0, request.getRequestURL().indexOf("/", 9));

        RequestContext requestContext = RequestContext.getCurrentInstance();

        link = website + request.getContextPath() + "/signLoanAccount";

        requestContext.execute("openDocumentsCustom('" + link + "','" + linkName + "')");

    }

    public boolean pdfGeneration(String Path, CustomerDtl customerDtl) {
        boolean flag = false;
        try {
            HttpSession session = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession();
            CallMst callMstFromCustomerSession = (CallMst) session.getAttribute("callMstThroughWeb");
            ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String deploymentDirectoryPath = ctx.getRealPath("/");
            ThreadSafeSimpleDateFormat dateFormat = new ThreadSafeSimpleDateFormat("yyyy-MM-dd");
            try (OutputStream file = new FileOutputStream(new File(Path))) {
                Document document = new Document(PageSize.A4, 50, 50, 50, 50);
                PdfWriter.getInstance(document, file);
                document.addAuthor("Kuber");
                document.addTitle("Terms Of Services");

                document.open();
                PdfPTable table = new PdfPTable(4);
                table.setTotalWidth(400);
                table.setLockedWidth(true);
                table.getDefaultCell().setBorder(0);
                PdfPCell cell;
                cell = new PdfPCell(new Phrase("Customer Information"));
                cell.setColspan(4);
                cell.setBorder(0);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("   "));
                cell.setColspan(4);
                cell.setBorder(0);
                table.addCell(cell);

                table.addCell("Customer Name : ");
                table.addCell(customerDtl.getFullName() + " ");
                table.addCell("Customer Image : ");
                String ss = customerDtl.getCustomerImage();
                //String custFace = ss.substring(ss.lastIndexOf(File.separator) + 1);
                Image img = Image.getInstance(deploymentDirectoryPath + ss);
                PdfPCell cell1 = new PdfPCell(img, true);
                table.addCell(cell1);
                table.addCell("Customer DOB : ");
                table.addCell(dateFormat.format(customerDtl.getDob()) + " ");
                table.addCell(" ");
                table.addCell(" ");
                table.addCell("Nationality : ");
                table.addCell(customerDtl.getNationality() + " ");
                table.addCell("Id Card : ");
                ss = customerDtl.getNationalId();
                //String custIdCard = ss.substring(ss.lastIndexOf(File.separator) + 1);
                Image img1 = Image.getInstance(deploymentDirectoryPath + ss);
                PdfPCell cell11 = new PdfPCell(img1, true);
                table.addCell(cell11);
                table.addCell("Gender : ");
                table.addCell(customerDtl.getGender().equals("M") ? "Male" : "Female" + " ");
                table.addCell("Maritial Status : ");
                table.addCell(customerDtl.getMaritailStatus() + " ");
                table.addCell("Email : ");
                table.addCell(customerDtl.getEmail() + " ");
                table.addCell("Phone No : ");
                table.addCell(customerDtl.getPhoneNo() + " ");
                table.addCell("Address : ");
                table.addCell(customerDtl.getAddress() + " ");
                table.addCell("Address Proof : ");
                ss = customerDtl.getUtilityBill();
                //String custAddproof = ss.substring(ss.lastIndexOf(File.separator) + 1);
                Image img2 = Image.getInstance(deploymentDirectoryPath + ss);
                PdfPCell cell12 = new PdfPCell(img2, true);
                table.addCell(cell12);
                table.addCell("Occupation : ");
                table.addCell(customerDtl.getOccupation() + " ");
                table.addCell("Annual Income : ");
                table.addCell(customerDtl.getSalary() + " ");
                table.addCell("Education : ");
                table.addCell(customerDtl.getEducation() + " ");
                table.addCell("eSign : ");
                ss = customerDtl.getCustomerSign();
                Image img3 = Image.getInstance(deploymentDirectoryPath + ss);
                PdfPCell cell13 = new PdfPCell(img3, true);

                table.addCell(cell13);

                document.add(table);

                document.close();
            }
            logger.info("PDF FILE CREATED SUCCESSFULLY ................");
            flag = true;
        } catch (IOException | DocumentException e) {

        }
        return flag;
    }

    public boolean pdfGeneration(String Path, CustomerLoanDtl customerLoanDtl) {
        boolean flag = false;
        try {
            ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String deploymentDirectoryPath = ctx.getRealPath("/");
            ThreadSafeSimpleDateFormat dateFormat = new ThreadSafeSimpleDateFormat("yyyy-MM-dd");
            try (OutputStream file = new FileOutputStream(new File(Path))) {
                Document document = new Document(PageSize.A4, 50, 50, 50, 50);
                PdfWriter.getInstance(document, file);
                document.addAuthor("Kuber");
                document.addTitle("Terms Of Services");

                document.open();
                PdfPTable table = new PdfPTable(4);
                table.setTotalWidth(400);
                table.setLockedWidth(true);
                table.getDefaultCell().setBorder(0);
                PdfPCell cell;
                cell = new PdfPCell(new Phrase("Customer Information"));
                cell.setColspan(4);
                cell.setBorder(0);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("   "));
                cell.setColspan(4);
                cell.setBorder(0);
                table.addCell(cell);

                table.addCell("Customer Name : ");
                table.addCell(customerLoanDtl.getFullName() + " ");
                table.addCell("Customer Image : ");
                String ss = customerLoanDtl.getCustomerImage();
                //String custFace = ss.substring(ss.lastIndexOf("/") + 1);
                Image img = Image.getInstance(deploymentDirectoryPath + ss);
                PdfPCell cell1 = new PdfPCell(img, true);
                table.addCell(cell1);
                table.addCell("Customer DOB : ");
                table.addCell(dateFormat.format(customerLoanDtl.getDob()) + " ");
                table.addCell(" ");
                table.addCell(" ");
                table.addCell("Nationality : ");
                table.addCell(customerLoanDtl.getNationality() + " ");
                table.addCell("Id Card : ");
                ss = customerLoanDtl.getNationalId();
                //String custIdCard = ss.substring(ss.lastIndexOf("/") + 1);
                Image img1 = Image.getInstance(deploymentDirectoryPath + ss);
                PdfPCell cell11 = new PdfPCell(img1, true);
                table.addCell(cell11);
                table.addCell("Gender : ");
                table.addCell(customerLoanDtl.getGender().equals("M") ? "Male" : "Female" + " ");
                table.addCell("Maritial Status : ");
                table.addCell(customerLoanDtl.getMaritailStatus() + " ");
                table.addCell("Email : ");
                table.addCell(customerLoanDtl.getEmail() + " ");
                table.addCell("Phone No : ");
                table.addCell(customerLoanDtl.getPhoneNo() + " ");
                table.addCell("Address : ");
                table.addCell(customerLoanDtl.getAddress() + " ");
                table.addCell("Address Proof : ");
                ss = customerLoanDtl.getUtilityBill();
                //String custAddproof = ss.substring(ss.lastIndexOf("/") + 1);
                Image img2 = Image.getInstance(deploymentDirectoryPath + ss);
                PdfPCell cell12 = new PdfPCell(img2, true);
                table.addCell(cell12);
                table.addCell("Occupation : ");
                table.addCell(customerLoanDtl.getOccupation() + " ");
                table.addCell("Annual Income : ");
                table.addCell(customerLoanDtl.getAnnualIncome() + " ");
                table.addCell("Education : ");
                table.addCell(customerLoanDtl.getEducation() + " ");
                table.addCell("eSign : ");
                ss = customerLoanDtl.getCustomerSign();
                Image img3 = Image.getInstance(deploymentDirectoryPath + ss);
                PdfPCell cell13 = new PdfPCell(img3, true);

                table.addCell(cell13);

                document.add(table);

                document.close();
            }
            logger.info("PDF FILE CREATED SUCCESSFULLY ................");
            flag = true;
        } catch (IOException | DocumentException e) {

        }
        return flag;
    }

    public void getCustomerForm(HttpServletRequest request) {
        logger.info("aaaaaaaaaaa");
        CustomerDtl customerDtl;
        customerAccountDto = getCustomerAccountDto();
        try {
            HttpSession session = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession();
            //Get Customer master from session
            logger.info("bbbbbbbbbbbbbbbb");
            CustomerMst customerMstL = (CustomerMst) session.getAttribute("ormCustomerMaster");
            logger.info("ccccccccccc");
            if (null != customerMstL.getAccountNo() && !customerMstL.getAccountNo().equals("")) {
                logger.info("dddddddddddddddd");
                this.customerFormPdfPath = customerMstL.getAccountNo() + ".pdf";
                request.getSession().setAttribute("formPdfPath", customerMstL.getAccountNo() + ".pdf");
                RequestContext.getCurrentInstance().update("downloadPDFId");
            } else {
                //Get Customer Detail by email id of Customer assuming they are same.
                customerDtl = customerDtlService.findIDByPhoneNo(customerMstL.getCellPhone().toString());
                if (null != customerDtl.getId()) {
                    CustomerAccDtl customerAccDtl = customerAccDtlService.getAccDtlByCustomerDtl(customerDtl);
                    if (null != customerAccDtl) {
                        this.customerFormPdfPath = customerAccDtl.getAccNo() + ".pdf";
                        request.getSession().setAttribute("formPdfPath", customerAccDtl.getAccNo() + ".pdf");
                        RequestContext.getCurrentInstance().update("downloadPDFId");
                    }
                }
            }
        } catch (Exception e) {

        }
    }

    //This method is used by customer
    public void getCustomerAccountDetailsbyPhoneNo(HttpServletRequest request) {
        CustomerDtl customerDtl;
        customerAccountDto = getCustomerAccountDto();
        try {
            HttpSession session = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession();
            //Get Customer master from session
            CustomerMst customerMstL = (CustomerMst) session.getAttribute("ormCustomerMaster");

            //Get Customer Detail by email id of Customer assuming they are same.
            customerDtl = customerDtlService.findIDByPhoneNo(customerMstL.getCellPhone().toString());
            String website = request.getRequestURL().substring(0, request.getRequestURL().indexOf("/", 9));

            if (null != customerDtl.getId()) {
                logger.info("::::::::::::::::cm:#CUST-ID:::::::::::::::::::::::" + customerMstL.getCustId());
                if (null == customerAccountDto) {
                    customerAccountDto = new CustomerAccountDto();
                }
                customerAccountDto.setCustDtlId(customerDtl.getId());
                customerAccountDto.setAddress(customerDtl.getAddress());
                customerAccountDto.setDob(customerDtl.getDob());
                customerAccountDto.setEducation(customerDtl.getEducation());
                customerAccountDto.setEmail(customerDtl.getEmail());
                customerAccountDto.setFullName(customerDtl.getFullName());
                customerAccountDto.setGender(customerDtl.getGender());
                customerAccountDto.setMaritialStatus(customerDtl.getMaritailStatus());
                customerAccountDto.setNationality(customerDtl.getNationality());
                customerAccountDto.setOccupation(customerDtl.getOccupation());
                customerAccountDto.setPhone(customerDtl.getPhoneNo());
                customerAccountDto.setSalary(customerDtl.getSalary());
                customerAccountDto.setCustImage(website + request.getContextPath() + customerDtl.getCustomerImage());
                customerAccountDto.setAddressProof(website + request.getContextPath() + customerDtl.getUtilityBill());
                customerAccountDto.setIdCard(website + request.getContextPath() + customerDtl.getNationalId());
                customerAccountDto.setCustomerType(customerDtl.getCustomerType());
                logger.info("customerDtl:" + customerAccountDto.toString());
            } else {
                logger.info("In getCustomerAccountDetailsbyEmailId no record for customerDtl object has found");
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } catch (Exception ex) {
            logger.error(ex);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    //This method is used by customer
    public void getCustomerLoanAccountDetailsbyPhoneNo(HttpServletRequest request) {
        CustomerLoanDtl customerLoanDtl;
        customerLoanDto = getCustomerLoanDto();
        String website = request.getRequestURL().substring(0, request.getRequestURL().indexOf("/", 9));
        try {
            HttpSession session = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession();
            //Get Customer master from session
            CustomerMst customerMstL = (CustomerMst) session.getAttribute("ormCustomerMaster");
            //Get Customer Detail by email id of Customer assuming they are same.
            customerLoanDtl = customerLoanDtlService.findIDByPhoneNo(String.valueOf(customerMstL.getCellPhone()));
            if (null != customerLoanDtl.getId()) {
                logger.info("::::::::::::::::cm:#CUST-ID:::::::::::::::::::::::" + customerMstL.getCustId());
                if (null == customerLoanDto) {
                    customerLoanDto = new CustomerLoanDto();
                }
                customerLoanDto.setCustDtlId(customerLoanDtl.getId());
                customerLoanDto.setAddress(customerLoanDtl.getAddress());
                customerLoanDto.setDob(customerLoanDtl.getDob());
                customerLoanDto.setEducation(customerLoanDtl.getEducation());
                customerLoanDto.setEmail(customerLoanDtl.getEmail());
                customerLoanDto.setFullName(customerLoanDtl.getFullName());
                customerLoanDto.setGender(customerLoanDtl.getGender());
                customerLoanDto.setMaritialStatus(customerLoanDtl.getMaritailStatus());
                customerLoanDto.setNationality(customerLoanDtl.getNationality());
                customerLoanDto.setOccupation(customerLoanDtl.getOccupation());
                customerLoanDto.setPhone(customerLoanDtl.getPhoneNo());
                customerLoanDto.setAnnualIncome(customerLoanDtl.getAnnualIncome());
                customerLoanDto.setCustImage(website + request.getContextPath() + customerLoanDtl.getCustomerImage());
                customerLoanDto.setAddressProof(website + request.getContextPath() + customerLoanDtl.getUtilityBill());
                customerLoanDto.setIdCard(website + request.getContextPath() + customerLoanDtl.getNationalId());
                customerLoanDto.setAccountNo(customerLoanDtl.getAccountNumber());
                customerLoanDto.setLoanAmount(customerLoanDtl.getLoanAmount());
                logger.info("customerLoanDtl:" + customerLoanDto.toString());
            } else {
                logger.info("In getCustomerLoanAccountDetailsbyEmailId no record for customerLoanDtl object has found");
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } catch (Exception ex) {
            logger.error(ex);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    //This method will be used by agent
    public void getCustomerAccountDetailsbyId(HttpServletRequest request) {
        customerAccountDto = getCustomerAccountDto() == null ? new CustomerAccountDto() : getCustomerAccountDto();
        try {
            HttpSession session = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession();
            CallMst cm = (CallMst) session.getAttribute("callMst");
            //Get Customer Detail by email id of Customer assuming they are same.
            customerMst = customerMstService.findCustomerMstByCustomerId(cm.getCustId());
            logger.info("3333333333" + customerMst);
            if (null != customerMst.getId()) {
                logger.info("Inside of getCustomerAccountDetailsbyId");
                customerAccountDto.setEmail(customerMst.getEmail());
                //if(customerAccountDto.getFullName)
                //customerAccountDto.setFullName(customerMst.getFirstName());
                //customerAccountDto.setNationality(customerMst.getNatinality());
                customerAccountDto.setPhone(String.valueOf(customerMst.getCellPhone()));
                logger.info("customerDtl:" + customerAccountDto.toString());
            } else {
                logger.info("In getCustomerAccountDetailsbyEmailId no record for customerDtl object has found");
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } catch (Exception ex) {
            logger.error(ex);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

    }

    //This method will be used by Agent
    public void getCustomerLoanAccountDetailsbyId(HttpServletRequest request) {
        customerLoanDto = getCustomerLoanDto();
        try {
            HttpSession session = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession();
            CallMst cm = (CallMst) session.getAttribute("callMst");
            //Get Customer Detail by email id of Customer assuming they are same.
            customerMst = customerMstService.findCustomerMstByCustomerId(cm.getCustId());
            if (null != customerMst.getId()) {
                logger.info("0000000000000");
                if (null == customerLoanDto) {
                    logger.info("01010101010101");
                    customerLoanDto = new CustomerLoanDto();
                }
                customerLoanDto.setEmail(customerMst.getEmail());
                //if(customerAccountDto.getFullName)
                if (null == customerLoanDto.getFullName()) {
                    if (customerMst.getMidName() != null && !customerMst.getMidName().trim().equalsIgnoreCase("")) {
                        customerLoanDto.setFullName(customerMst.getFirstName() + " " + customerMst.getMidName() + " " + customerMst.getLastName());
                    } else {
                        customerLoanDto.setFullName(customerMst.getFirstName() + " " + customerMst.getLastName());
                    }
                }
                //customerLoanDto.setNationality(customerMst.getNatinality());
                customerLoanDto.setPhone(customerMst.getCellPhone().toString());
                customerLoanDto.setAccountNo(customerMst.getAccountNo());
                logger.info("customerDtl:" + customerLoanDto.toString());
            } else {
                logger.info("In getCustomerAccountDetailsbyEmailId no record for customerDtl object has found");
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } catch (Exception ex) {
            logger.error(ex);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

    }

    private void saveSignature() {
        String base64ImageByte;
        try {
            logger.info("saveSignature----------");
            logger.info("signatureCoOrdinates----------" + signatureCoOrdinates);
            customerAccountDto.setSignatureCord(signatureCoOrdinates);
            logger.info("signature----------" + signature);
            base64ImageByte = signature.substring("data:image/png;base64,".length());
            customerAccountDto.setSignature(base64ImageByte);
            logger.info("base64ImageByte----------" + base64ImageByte);

        } catch (Exception e) {
            logger.error("saveSignature ERROR: ", e);
        }

    }

    private void saveSignatureForLoan() {
        String base64ImageByte;
        try {
            logger.info("saveSignature----------");
            logger.info("signatureCoOrdinates----------" + signatureCoOrdinates);
            customerLoanDto.setSignatureCord(signatureCoOrdinates);
            logger.info("signature----------" + signature);
            base64ImageByte = signature.substring("data:image/png;base64,".length());
            customerLoanDto.setSignature(base64ImageByte);
            logger.info("base64ImageByte----------" + base64ImageByte);

        } catch (Exception e) {
            logger.error("saveSignature ERROR: ", e);
        }

    }

    public void sendUploadIdRequest() {
        try {
            HttpSession session = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession();
            CallMst cm = (CallMst) session.getAttribute("callMst");
            SocketMessage.send(callScheduler.getAdminSocket(), cm.getCustId(), "idcard#request");
        } catch (Exception ex) {
            Logger.getLogger(CustomerAccountComponent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendUploadAddressProofRequest() {
        try {
            HttpSession session = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession();
            CallMst cm = (CallMst) session.getAttribute("callMst");
            SocketMessage.send(callScheduler.getAdminSocket(), cm.getCustId(), "addressproof#request");
        } catch (Exception ex) {
            Logger.getLogger(CustomerAccountComponent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void clearSignature() {
        try {
            logger.info("clearSignature----------");
            signatureCoOrdinates = null;
            signature = null;
        } catch (Exception e) {
            logger.error("clearSignature ERROR : ", e);
        }
    }

    public CustomerAccountDto getCustomerAccountDto() {
        return customerAccountDto;
    }

    public void setCustomerAccountDto(CustomerAccountDto customerAccountDto) {
        this.customerAccountDto = customerAccountDto;
    }

    public CustomerLoanDto getCustomerLoanDto() {
        return customerLoanDto;
    }

    public void setCustomerLoanDto(CustomerLoanDto customerLoanDto) {
        this.customerLoanDto = customerLoanDto;
    }

    public void checkErrors() {

    }

    public Date getPresentDate() {
        return presentDate;
    }

    public void setPresentDate(Date presentDate) {
        this.presentDate = presentDate;
    }

    public String getSignatureCoOrdinates() {
        return signatureCoOrdinates;
    }

    public void setSignatureCoOrdinates(String signatureCoOrdinates) {
        this.signatureCoOrdinates = signatureCoOrdinates;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getSnapImage() {
        return snapImage;
    }

    public void setSnapImage(String snapImage) {
        this.snapImage = snapImage;
    }

    public String getUploadedNationalId() {
        return uploadedNationalId;
    }

    public void setUploadedNationalId(String uploadedNationalId) {
        this.uploadedNationalId = uploadedNationalId;
    }

    public String getFaceFromPanCard() {
        return faceFromPanCard;
    }

    public void setFaceFromPanCard(String faceFromPanCard) {
        this.faceFromPanCard = faceFromPanCard;
    }

    public String getSignFromPanCard() {
        return signFromPanCard;
    }

    public void setSignFromPanCard(String signFromPanCard) {
        this.signFromPanCard = signFromPanCard;
    }

    public String getMatchresult() {
        return matchresult;
    }

    public void setMatchresult(String matchresult) {
        this.matchresult = matchresult;
    }

    public String getUploadedAddProof() {
        return uploadedAddProof;
    }

    public void setUploadedAddProof(String uploadedAddProof) {
        this.uploadedAddProof = uploadedAddProof;
    }

    public String getNewSnapImage() {
        return newSnapImage;
    }

    public void setNewSnapImage(String newSnapImage) {
        this.newSnapImage = newSnapImage;
    }

    public String getNewUploadedNationalId() {
        return newUploadedNationalId;
    }

    public void setNewUploadedNationalId(String newUploadedNationalId) {
        this.newUploadedNationalId = newUploadedNationalId;
    }

    public String getNewUploadedAddProof() {
        return newUploadedAddProof;
    }

    public void setNewUploadedAddProof(String newUploadedAddProof) {
        this.newUploadedAddProof = newUploadedAddProof;
    }

    public String getCustomerFormPdfPath() {
        return customerFormPdfPath;
    }

    public void setCustomerFormPdfPath(String customerFormPdfPath) {
        this.customerFormPdfPath = customerFormPdfPath;
    }

}
