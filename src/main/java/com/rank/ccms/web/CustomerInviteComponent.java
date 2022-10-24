package com.rank.ccms.web;

import com.rank.ccms.entities.CustomerMst;
import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.service.CustomerMstService;
import com.rank.ccms.util.AddXLSXFile;
import com.rank.ccms.util.Constants;
import com.rank.ccms.util.SendingMailUtil;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import jxl.read.biff.BiffException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class CustomerInviteComponent implements Serializable {

    org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(this.getClass());

    private CustomerMst customerMst;
    private String customerPhone;
    private String nationality;
    private List<String> errorList = new ArrayList<>();

    List<String> l1;
    List<String> exception = new ArrayList<>();
    List<String> successList = new ArrayList<>();

    Integer success;
    private Integer error;

    @Autowired
    private CustomerMstService customerMstService;

    public void newCustomerMstInviteComponent() {
        customerPhone = "";
        customerMst = new CustomerMst();
    }

    public String invite(HttpServletRequest request) throws Exception {

        logger.info("chayan in side invite");
        EmployeeMst emst = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
        CustomerMst cust = new CustomerMst();
        cust.setFirstName(this.getCustomerMst().getFirstName());
        cust.setLastName(" ");
        String custSocket = "Cust_" + ((int) (Math.random() * 9000) + 1000);
        cust.setCustId(this.getCustomerMst().getFirstName() + "~" + custSocket);
        cust.setEmail(this.getCustomerMst().getEmail());
        cust.setCellPhone(Long.parseLong(this.getCustomerPhone()));
        cust.setNatinality(this.getNationality());
        cust.setActiveFlg(true);
        cust.setDeleteFlg(false);
        cust = customerMstService.saveCustomerMst(cust, emst);

        if (cust == null) {

            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            customerPhone = "";
            customerMst = new CustomerMst();
            return "/pages/category/inviteCustomer.xhtml";
        } else {

            String fname[] = cust.getFirstName().split(" ");
            String website = request.getRequestURL().substring(0, request.getRequestURL().indexOf("/", 9));
            String videoLink = website + request.getContextPath() + "/customerInvite?name=" + fname[0] + "&email=" + cust.getEmail() + "&phone=" + cust.getCellPhone() + "&nationality=" + cust.getNatinality();

            StringBuffer sb = new StringBuffer(255);
            sb.append("Dear ").append(cust.getFirstName());
            sb.append(",\n\n").append("You are coridially invited to experience our new channel called 'Face to Face Service Solution'. \n");
            sb.append("\nKindly Click on the below link to initiate Video Call.\n");
            sb.append(videoLink).append("\n\n");
            sb.append(SendingMailUtil.TELE_THX);

            boolean mailSend = SendingMailUtil.sendMail(cust.getEmail(), sb, "Invitation For Video Call");
            RequestContext.getCurrentInstance().execute("$('#spinner').hide();");
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Invitition Send Successfully", "Invitition Send Successfully");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            customerMst = new CustomerMst();
            customerPhone = "";
            nationality = "";
            return "/pages/category/inviteCustomer.xhtml";
        }

    }

    public CustomerMst getCustomerMst() {
        return customerMst;
    }

    public void setCustomerMst(CustomerMst customerMst) {
        this.customerMst = customerMst;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void exampleShow(HttpServletRequest request) throws IOException {
        logger.info("exampleShow mwthod");
        String projectHome = System.getenv("VIDEOBANKING_HOME");
        if (null == projectHome) {

        } else {
            String filePath = projectHome + File.separator + "configuration" + File.separator + "customerList.xlsx";
            logger.info("FILE PATH IS +++++++++++++++++++++++++++++++++++++++++" + filePath);
//            Boolean status = updateCandidateExcelFile(filePath);
//            logger.info("Bulk Upload File Update Status " + status);
            request.getSession().setAttribute("imagePath", filePath);
//            Desktop dt = Desktop.getDesktop();
//            dt.open(new File(filePath));
        }

    }

    public void customerFileUpload(FileUploadEvent event) throws BiffException {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession();
        EmployeeMst localEmpMst = (EmployeeMst) session.getAttribute("ormUserMaster");
        String fileName = event.getFile().getFileName();
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
        String excel2007 = "xlsx";
        logger.info("extension=================" + extension);
        exception.clear();
        successList.clear();
        success = 0;
        error = 0;

        List<List<String>> mstList;
        logger.info("In customerFileUpload success====" + success);
        if (excel2007.equalsIgnoreCase(extension)) {
            logger.info("excel2007.equalsIgnoreCase(extension)===============");
            AddXLSXFile addXLSXFile = new AddXLSXFile();

            try {
                int loc_count = 1;
                int not_count = 1;
                String fileNm = fileName.substring(0, fileName.lastIndexOf("."));
                logger.info(" File Name " + fileNm);
                try (InputStream inputStream = event.getFile().getInputstream()) {
                    mstList = addXLSXFile.saveXLSX(inputStream, 4);
                    if (addXLSXFile.getNotSavedListIndex() != null) {
                        exception = addXLSXFile.getNotSavedListIndex();
                    }
                    logger.info("size of master list" + mstList.size());
                    if (mstList.isEmpty()) {
                        exception.add("No record added");
                    }
                    for (List<String> ms : mstList) {
                        error = 0;
                        logger.info("In for loop count==============" + loc_count);
                        CustomerMst cust = new CustomerMst();
                        if (error == 0) {
                            if ((!ms.get(0).trim().isEmpty())) {
                                String regex = "^[a-zA-Z\\s]*$";
                                if (!Pattern.matches(regex, ms.get(0).trim())) {
                                    String msg = "Name: Validation Error:Only characters allowed in line number " + (loc_count + 1) + " . This record is not added!!";
                                    logger.info(msg);
                                    exception.add(msg);
                                    error = error + 1;
                                } else {
                                    cust.setFirstName(ms.get(0).trim());
                                    cust.setLastName(" ");
                                    String custSocket = "Cust_" + ((int) (Math.random() * 9000) + 1000);
                                    cust.setCustId(ms.get(0).trim() + "~" + custSocket);
                                }
                            } else {
                                logger.info("inside if");
                                String msg = "Please provide Name in row number " + (loc_count + 1) + " then try to save again!!";
                                logger.info(msg);
                                exception.add(msg);
                                error = error + 1;
                            }

                            if ((!ms.get(1).trim().isEmpty())) {
                                String regex = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
                                if (!Pattern.matches(regex, ms.get(1).trim())) {
                                    String msg = "Email: Invalid Email pattern Ex: abc@wxyz.pqr in line number " + (loc_count + 1) + " . This record is not added!!";
                                    logger.info(msg);
                                    exception.add(msg);
                                    error = error + 1;
                                } else {
                                    cust.setEmail(ms.get(1).trim());
                                }
                            } else {
                                logger.info("inside if");
                                logger.info("Mid name..." + ms.get(1).trim());
                            }

                            if ((!ms.get(2).trim().isEmpty())) {
                                String regex = "\\d{10,12}$";
                                if (!Pattern.matches(regex, ms.get(2).trim())) {
                                    String msg = "Phone: Invalid Phone No, Only numeric value with minimum 10 digits allowed in line number " + (loc_count + 1) + " . This record is not added!!";
                                    logger.info(msg);
                                    exception.add(msg);
                                    error = error + 1;
                                } else {
                                    cust.setCellPhone(Long.parseLong(ms.get(2).trim()));
                                }
                            } else {
                                logger.info("inside if");
                                String msg = "Please provide Phone no in row number " + (loc_count + 1) + " then try to save again!!";
                                logger.info(msg);
                                exception.add(msg);
                                error = error + 1;
                            }

                            if ((!ms.get(3).trim().isEmpty())) {
                                cust.setNatinality(ms.get(3).trim());

                            } else {
                                logger.info("inside if");
                                String msg = "Please provide Nationality in row number " + (loc_count + 1) + " then try to save again!!";
                                logger.info(msg);
                                exception.add(msg);
//                                        flag3 = false;
                                error = error + 1;
                            }

                        }
                        if (error == 0) {
                            success = success + 1;
                            cust.setActiveFlg(true);
                            cust.setDeleteFlg(false);
                            cust = customerMstService.saveCustomerMst(cust, localEmpMst);

                            if (cust == null) {

                                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "");
                                FacesContext.getCurrentInstance().addMessage(null, msg);
                            } else {
                                Properties prop;
                                String host = Constants.smtp_host;
                                String port = "25";
                                final String user = Constants.from_address;
                                final String password = Constants.password;
                                String subject = "Invitation For Video Call";
                                String fname[] = cust.getFirstName().split(" ");
                                String website = request.getRequestURL().substring(0, request.getRequestURL().indexOf("/", 9));
                                String videoLink = website + request.getContextPath() + "/customerInvite?name=" + fname[0] + "&email=" + cust.getEmail() + "&phone=" + cust.getCellPhone() + "&nationality=" + cust.getNatinality();
                                String content = "Dear " + cust.getFirstName() + ",<br><br>Thank You for choosing our Video Call Service	<br>Kindly Click on the below link to initiate Video Call with our Executive<br><br>" + videoLink + "<br><br>Thanking You<br>With Regards,<br>CCMS Team";

                                prop = new Properties();
                                prop.put("mail.smtp.host", host);
                                prop.put("mail.smtp.port", port);
                                prop.put("mail.smtp.starttls.enable", "true");
                                prop.put("mail.smtp.auth", "true");

                                Session session1 = Session.getInstance(prop,
                                        new javax.mail.Authenticator() {
                                    @Override
                                    protected PasswordAuthentication getPasswordAuthentication() {
                                        return new PasswordAuthentication(user, password);
                                    }
                                });

                                try {
                                    MimeMessage message1 = new MimeMessage(session1);
                                    try {
                                        message1.setFrom(new InternetAddress(user));
                                        //message1.setFrom(new InternetAddress(user, "Bajaj Allianz"));
                                    } catch (MessagingException ex) {
                                        logger.error(ex);
                                    }
                                    message1.addRecipient(Message.RecipientType.TO, new InternetAddress(cust.getEmail()));
                                    message1.setSubject(subject);
                                    message1.setContent(content, "text/html");
                                    Transport.send(message1);
                                    logger.info("message sent successfully...");

                                } catch (MessagingException e) {
                                    logger.error(e);
                                }

                                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Invitition Send Successfully", "Invitition Send Successfully");
                                FacesContext.getCurrentInstance().addMessage(null, msg);
                            }
                        }

                        not_count = not_count + 1;
                        loc_count = loc_count + 1;

                        logger.info("In last for loop count" + not_count);
                        logger.info("In last for loop count" + loc_count);

                    }
                    if (error > 0) {
                        String errorMsg;
                        if (success == 0) {
                            errorMsg = "No record added";
                        } else {
                            errorMsg = "Total " + ((not_count - 1) - success) + " Customer(s) not intimated";
                        }
                        getException().add(errorMsg);

                    }

                    if (success > 0) {
                        String successMsg = "Total " + success + " customers are intimated successfully";
                        getSuccessList().add(successMsg);
                    }
                }
            } catch (IOException e) {
                Logger.getLogger(CustomerInviteComponent.class.getName()).log(Level.SEVERE, null, e);
            }

        }
        RequestContext.getCurrentInstance().execute("$('#spinner').hide();");
        logger.info("EXCEPION===============" + exception);
        logger.info("SUCCESS===============" + successList);
        RequestContext.getCurrentInstance().update("messagesform:basicDialog");
        RequestContext.getCurrentInstance().update("main");
    }

    public List<String> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<String> errorList) {
        this.errorList = errorList;
    }

    public List<String> getL1() {
        return l1;
    }

    public void setL1(List<String> l1) {
        this.l1 = l1;
    }

    public List<String> getException() {
        return exception;
    }

    public void setException(List<String> exception) {
        this.exception = exception;
    }

    public List<String> getSuccessList() {
        return successList;
    }

    public void setSuccessList(List<String> successList) {
        this.successList = successList;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

}
