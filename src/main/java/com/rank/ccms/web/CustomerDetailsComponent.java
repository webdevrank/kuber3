package com.rank.ccms.web;

import com.rank.ccms.dto.CustomerAccountDetailsDto;
import com.rank.ccms.entities.CallMst;
import com.rank.ccms.entities.CategoryMst;
import com.rank.ccms.entities.CustomerMst;
import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.entities.LanguageMst;
import com.rank.ccms.entities.ScheduleCall;
import com.rank.ccms.entities.ServiceMst;
import com.rank.ccms.service.CallMstService;
import com.rank.ccms.service.CallSchedulerService;
import com.rank.ccms.service.CategoryMstService;
import com.rank.ccms.service.CustomerAccDtlService;
import com.rank.ccms.service.CustomerMstService;
import com.rank.ccms.service.EmployeeMstService;
import com.rank.ccms.service.LanguageMstService;
import com.rank.ccms.service.ServiceMstService;
import com.rank.ccms.util.Constants;
import com.rank.ccms.util.CustomConvert;
import com.rank.ccms.util.SendingMailUtil;
import com.rank.ccms.util.ThreadSafeSimpleDateFormat;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author lenovo
 *
 */

/*
 * This class will handle the Customer Account related information
 */
@Component
@Scope("session")
public class CustomerDetailsComponent implements Serializable {

    /**
     *
     */
    org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(this.getClass());
    private static final long serialVersionUID = -7974286544577478997L;
    private List<CustomerAccountDetailsDto> customerAccountDetailsDtoList;
    private List<CustomerAccountDetailsDto> selectCustomerAccountDetailsDtoList = new ArrayList<>();
    private List<CustomerAccountDetailsDto> filteredCustomerAccountDetailsDtoList = new ArrayList<>();

    private String scheduleText;
    private Date schDateTime = new Date();
    private Long agentId = Long.parseLong("0");
    private Long customerId;
    private String timeZone;

    private Date dob = new Date();
    private String fullName = "";
    private String gender = "";
    private String nationality = "";
    private String documentNo = "";

    private CategoryMst categoryMst;
    private ServiceMst serviceMst;
    private LanguageMst languageMst;

    @Autowired
    private CustomerAccDtlService customerAccDtlService;
    @Autowired
    private ServiceMstService serviceMstService;

    @Autowired
    private CategoryMstService categoryMstService;

    @Autowired
    private LanguageMstService langMstService;

    @Autowired
    CallSchedulerService callSchedulerService;
    @Autowired
    CallMstService callMstService;
    @Autowired
    private EmployeeMstService employeeMstService;

    @Autowired
    private CustomerMstService customerMstService;

    @Autowired
    private HttpServletRequest requests;

    public CustomerDetailsComponent() {

    }

    public void selectCustomerByAccountNo(String accountNo) {

    }

    public void listCustomerAccountDetails() {

        this.customerAccountDetailsDtoList = new ArrayList<>();
        String websiteURL = requests.getRequestURL().substring(0, requests.getRequestURL().indexOf("/", 9));
        this.filteredCustomerAccountDetailsDtoList = new ArrayList<>();
        this.selectCustomerAccountDetailsDtoList = new ArrayList<>();
        List<CustomerAccountDetailsDto> localCustomerAccountDetailsDtoList = customerAccDtlService.listCustomerAccountDetails();
        if (null != localCustomerAccountDetailsDtoList) {
            if (!localCustomerAccountDetailsDtoList.isEmpty()) {
                for (CustomerAccountDetailsDto cadd : localCustomerAccountDetailsDtoList) {
                    if (cadd != null) {
                        if (cadd.getCustomer_sign() != null && cadd.getCustomer_sign().length() > 0) {
                            cadd.setCustomer_sign(websiteURL + requests.getContextPath() + cadd.getCustomer_sign());
                        }
                        if (cadd.getCustomer_image() != null && cadd.getCustomer_image().length() > 0) {
                            cadd.setCustomer_image(websiteURL + requests.getContextPath() + cadd.getCustomer_image());
                        }
                        if (cadd.getNational_id() != null && cadd.getNational_id().length() > 0) {
                            cadd.setNational_id(websiteURL + requests.getContextPath() + cadd.getNational_id());
                        }
                        if (cadd.getUtility_bill() != null && cadd.getUtility_bill().length() > 0) {
                            cadd.setUtility_bill(websiteURL + requests.getContextPath() + cadd.getUtility_bill());
                        }
                        if (null != cadd.getAccount_no()) {
                            if (cadd.getAccount_no().equals("")) {
                                cadd.setVerifyStatus("Verification Pending");
                            } else {
                                cadd.setVerifyStatus("Verification Done");
                            }
                        } else {
                            cadd.setVerifyStatus("Verification Pending");
                        }
                        if (cadd.getCustomer_type() != null) {
                            if (cadd.getCustomer_type().equals("account")) {
                                cadd.setCustomer_type("CASA ACCOUNT");
                            } else if (cadd.getCustomer_type().equals("credit card")) {
                                cadd.setCustomer_type("CREDIT CARD");
                            }
                        }
                        this.customerAccountDetailsDtoList.add(cadd);
                    }
                }
            }
        }
        this.setFilteredCustomerAccountDetailsDtoList(this.customerAccountDetailsDtoList);
    }

    public void beforeSchedule() {
        if (this.selectCustomerAccountDetailsDtoList.size() == 1) {

            BigInteger custId = this.selectCustomerAccountDetailsDtoList.get(0).getCust_id();

            this.customerId = Long.parseLong(custId.toString());
            try {
                HttpSession session = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession(false);
                DateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
                String timeZone1 = "IST";
                formatter.setTimeZone(TimeZone.getTimeZone(timeZone1));
                this.schDateTime = formatter.parse(formatter.format(new Date()));
                RequestContext.getCurrentInstance().update("schDateTimePickerPanel");
                RequestContext.getCurrentInstance().execute("PF('doScheduleDialog').show();");
            } catch (ParseException ex) {
                Logger.getLogger(CustomerComponent.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {

        }

    }

    public void doScheduleCancel() {
//        schDateTime = null;
        serviceMst = null;
        languageMst = null;
        categoryMst = null;
    }

    public void checkReceiveIdCardFromCust(HttpServletRequest request) {
        String ss = selectCustomerAccountDetailsDtoList.get(0).getNational_id();
        String imagePath = ss.substring(ss.lastIndexOf("/") + 1);
        System.out.println("ImagePath========" + imagePath);
        String website = request.getRequestURL().substring(0, request.getRequestURL().indexOf("/", 9));
        //accountComponent.setUploadedNationalId(website + "/vbanking/" + imagePath);
        ThreadSafeSimpleDateFormat dateFormat = new ThreadSafeSimpleDateFormat("yyMMdd");
        Date dobL = new Date();
        String documentNoL = "1234567";
        String fullNameL = selectCustomerAccountDetailsDtoList.get(0).getFirst_name();
        String genderL = "Male";
        String nationalityL = selectCustomerAccountDetailsDtoList.get(0).getNationality();

        HttpSession session = request.getSession();
        boolean valid = true;
        if (valid) {
            ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String deploymentDirectoryPath = ctx.getRealPath("/");
            CustomerMst cm = customerMstService.findCustomerMstById(Long.parseLong(selectCustomerAccountDetailsDtoList.get(0).getCust_id().toString()));
            try {
                System.out.println("file path ===" + deploymentDirectoryPath + "/crm/" + imagePath);
                File ff = new File(deploymentDirectoryPath + "/crm/" + imagePath);

                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(Constants.ocrUrl + "getText");
                //httpPost.addHeader("Content-type", "multipart/form-data");

                FileBody uploadFilePart = new FileBody(ff);
                MultipartEntity reqEntity = new MultipartEntity();
                reqEntity.addPart("file", uploadFilePart);
                httpPost.setEntity(reqEntity);

                HttpResponse response1 = httpclient.execute(httpPost);
                HttpEntity responseEntity = response1.getEntity();
                String response = "Failure";
                if (responseEntity != null) {
                    response = EntityUtils.toString(responseEntity);
                }
                System.out.println("Response==========" + response);

                JSONObject jsonObject;
                try {
                    jsonObject = new JSONObject(response);
                    System.out.println("NAME IS====" + jsonObject.getString("name"));

                    String[] jsonarray = jsonObject.getString("others").split(",");
                    Map<String, String> outputss = new HashMap<>();
                    for (String jsonarray1 : jsonarray) {
                        String[] othersval = jsonarray1.trim().split(":");
                        String val = othersval[1];
                        if (!val.equals("") && !val.equals("others")) {
                            Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
                            Matcher m = p.matcher(val);
                            if (!m.find()) {
                                outputss.put(othersval[0], val);
                                if (othersval[0].equalsIgnoreCase("Name")) {

                                    fullNameL = val;
                                } else if (othersval[0].equalsIgnoreCase("Nationality")) {

                                    nationalityL = val;
                                } else if (othersval[0].equalsIgnoreCase("Sex")) {

                                    genderL = val;
                                } else if (othersval[0].equalsIgnoreCase("DOB")) {
                                    if (!val.equals("Not Available")) {

                                        dobL = dateFormat.parse(val);
                                    }
                                } else if (othersval[0].equalsIgnoreCase("Document No")) {

                                    documentNoL = val;
                                }

                            }
                        }
                    }

                } catch (JSONException | ParseException ex) {
                    Logger.getLogger(VidyoAgent.class.getName()).log(Level.SEVERE, null, ex);
                }

            } catch (IOException e) {
                logger.error("ERROR ISSSSSSSSSSSSSSSSSSS====" + e);
            }

        }

        this.setDob(dobL);

        this.setFullName(fullNameL);
        this.setGender(genderL);
        this.setNationality(nationalityL);
        this.setDocumentNo(documentNoL);
        RequestContext.getCurrentInstance().execute("$('#spinner').hide()");
        RequestContext.getCurrentInstance().update("custverifyform:verified");
        RequestContext.getCurrentInstance().update("custverifyform:verifyresult");
    }

    public void makeScheduleCallFromWeb(HttpServletRequest request) {
        final TimeZone timeZoneL = TimeZone.getDefault();
        CustomerMst cm = customerMstService.findCustomerMstById(customerId);

        String websiteURL = request.getRequestURL().substring(0, request.getRequestURL().indexOf("/", 9));
        ScheduleCall scheduleCall = new ScheduleCall();
        ScheduleCall schCall;

        try {
            ThreadSafeSimpleDateFormat sdf = new ThreadSafeSimpleDateFormat("dd-MMM-yyyy HH:mm");
            sdf.setTimeZone(timeZoneL);
            this.schDateTime = sdf.parse(sdf.format(schDateTime));
            java.sql.Timestamp timestamp = new java.sql.Timestamp(schDateTime.getTime());

            if (schDateTime.after(new Date())) {
                if (cm != null) {
                    CallMst callMst = callMstService.findCallMstMaxByCustId(customerId);
                    if (null != callMst) {

                        Long cellNo = (long) 0;

//                    categoryMst = categoryMstService.findCategoryByCategoryName(cm.getCustSeg());
//                    languageMst = langMstService.findLanguageMstByLanguageName(cm.getCustLang1());
                        categoryMst = categoryMstService.findCategoryMstById(callMst.getCategoryId());
                        languageMst = langMstService.findAllLanguageMstById(callMst.getLanguageId());
                        serviceMst = serviceMstService.findAllServiceMstById(callMst.getServiceId());

                        scheduleCall.setCreationDatetime(CustomConvert.javaDateToTimeStamp(new Date()));
                        scheduleCall.setExecuteStatus("Scheduled");
                        scheduleCall.setScheduledBy("Customer");
                        scheduleCall.setSchedulerId((long) 0);
                        scheduleCall.setScheduleDate(timestamp);
                        scheduleCall.setCustomerId(cm);
                        scheduleCall.setSchedulerId(agentId);
                        scheduleCall.setCallMedium("WEB");

                        if (serviceMst != null) {
                            scheduleCall.setService(serviceMst.getId());
                        }
                        if (languageMst != null) {
                            scheduleCall.setLanguage(languageMst.getId());
                        }
                        if (categoryMst != null) {
                            scheduleCall.setCategory(categoryMst.getId());
                        }

                        scheduleCall.setActiveFlg(true);
                        scheduleCall.setDeleteFlg(false);
                        schCall = callSchedulerService.saveScheduleCall(scheduleCall, null);
                        if (null != schCall) {
                            CustomerMst custMst = customerMstService.findCustomerMstById(schCall.getCustomerId().getId());
                            String messageBody;
                            //CallScheduler callScheduler = new CallScheduler();
                            EmployeeMst employeeMstLocal = employeeMstService.findEmployeeMstById(schCall.getSchedulerId());
                            String custEmail = custMst.getEmail();
                            if (null != custEmail) {
                                messageBody = "<html><body>Dear &nbsp;" + custMst.getFirstName();
                                messageBody += ",&nbsp;<br></br>We have scheduled for a verification call from CCMS with our Service Agent at ";
                                messageBody += sdf.format(schCall.getScheduleDate());
                                messageBody += "&nbsp;<br></br>Please click on the below link, preferably 10 minutes before the time.";
                                messageBody += "<br></br><br></br>";
                                //messageBody+= websiteURL+"/faces/pages/customer/customerScheduleCall.xhtml?scheduleCallId="+scCall.getId();
                                messageBody += "<a href=";
                                if (custMst.getAccountNo() != null && custMst.getAccountNo().length() > 0) {
                                    messageBody += websiteURL + request.getContextPath() + "/custScheduleCheck?scheduleCallId=" + schCall.getId();
                                } else {
                                    messageBody += websiteURL + request.getContextPath() + "/custScheduleCheck?scheduleCallId=" + schCall.getId() + "&type=guest";
                                }
                                messageBody += ">Meeting Link</a>";
                                messageBody += "<br></br><br></br><br></br>";
                                messageBody += "<b>Note:</b>Please do not refresh the browser during the call.";
                                messageBody += "<br></br>";
                                messageBody += SendingMailUtil.TELE_THX_HTML;
                                messageBody += "</body><html/>";

                                try {
                                    boolean mailSend = SendingMailUtil.sendEMail(custEmail, messageBody, SendingMailUtil.SCHEDULE_CALL);
                                    if (mailSend) {
                                        logger.info("mail sending was successfull... to Customer" + custEmail);
                                    }
                                } catch (Exception ex) {
                                    logger.error("Sending Email Error:" + ex.getMessage());
                                }

                            }
                            //callScheduler.loadCustomerMail(custMst.getEmail(), scCall.getScheduleDate());
                            if (null != employeeMstLocal) {
                                String emailTo = employeeMstLocal.getEmail();
                                if (null != emailTo) {
                                    messageBody = "<html><body>Dear &nbsp" + employeeMstLocal.getFirstName();
                                    messageBody += ",&nbsp;<br></br>you have a schedule call with our Customer name ";
                                    messageBody += custMst.getFirstName();
                                    messageBody += ".&nbsp;at&nbsp;";
                                    messageBody += sdf.format(schCall.getScheduleDate());
                                    messageBody += "&nbsp;Please login at least 10 minutes before the schedule time.";
//                                messageBody += "<br></br><br></br>";
                                    // messageBody+= websiteURL+"/faces/pages/customer/customerScheduleCall.xhtml?scheduleCallId="+scCall.getId();
//                                messageBody += websiteURL + "/videoScheduleCallCustomer?scheduleCallId=" + scCall.getId();
                                    messageBody += "<br></br><br></br><br></br>";
                                    messageBody += SendingMailUtil.TELE_THX;
                                    messageBody += "</body><html/>";

                                    try {
                                        boolean mailSend = SendingMailUtil.sendEMail(emailTo, messageBody, SendingMailUtil.SCHEDULE_CALL);
                                        if (mailSend) {
                                            logger.info("mail sending was successfull... to Agent" + emailTo);
                                        }
                                    } catch (Exception ex) {
                                        logger.error("Sending Email Error:" + ex.getMessage());
                                    }
                                }
                            }
                            this.scheduleText = "The customer has been scheduled successfully for Verification call";
                            RequestContext.getCurrentInstance().execute("PF('doScheduleDialog').hide();");
                            RequestContext.getCurrentInstance().execute("$('#spinner').hide();PF('confirmscheduleDlg').show();");

                        } else {

                            this.scheduleText = "Call Scheduleding Failed";
                            RequestContext.getCurrentInstance().execute("PF('confirmscheduleDlg').show();");
                        }
                    } else {
                        this.scheduleText = "No call records found!";
                        RequestContext.getCurrentInstance().execute("PF('confirmscheduleDlg').show();");
                    }
                }
            } else {

                this.scheduleText = "Select valid Date and time";
                RequestContext.getCurrentInstance().execute("PF('confirmscheduleDlg').show();");
            }

        } catch (ParseException | NumberFormatException e) {

        }

    }

    public List<CustomerAccountDetailsDto> getCustomerAccountDetailsDtoList() {
        return customerAccountDetailsDtoList;
    }

    public void setCustomerAccountDetailsDtoList(List<CustomerAccountDetailsDto> customerAccountDetailsDtoList) {
        this.customerAccountDetailsDtoList = customerAccountDetailsDtoList;
    }

    /**
     * @return the selectCustomerAccountDetailsDtoList
     */
    public List<CustomerAccountDetailsDto> getSelectCustomerAccountDetailsDtoList() {
        return selectCustomerAccountDetailsDtoList;
    }

    /**
     * @param selectCustomerAccountDetailsDtoList the
     * selectCustomerAccountDetailsDtoList to set
     */
    public void setSelectCustomerAccountDetailsDtoList(List<CustomerAccountDetailsDto> selectCustomerAccountDetailsDtoList) {
        this.selectCustomerAccountDetailsDtoList = selectCustomerAccountDetailsDtoList;
    }

    /**
     * @return the filteredCustomerAccountDetailsDtoList
     */
    public List<CustomerAccountDetailsDto> getFilteredCustomerAccountDetailsDtoList() {
        return filteredCustomerAccountDetailsDtoList;
    }

    /**
     * @param filteredCustomerAccountDetailsDtoList the
     * filteredCustomerAccountDetailsDtoList to set
     */
    public void setFilteredCustomerAccountDetailsDtoList(List<CustomerAccountDetailsDto> filteredCustomerAccountDetailsDtoList) {
        this.filteredCustomerAccountDetailsDtoList = filteredCustomerAccountDetailsDtoList;
    }

    public String getScheduleText() {
        return scheduleText;
    }

    public void setScheduleText(String scheduleText) {
        this.scheduleText = scheduleText;
    }

    public Date getSchDateTime() {
        return schDateTime;
    }

    public void setSchDateTime(Date schDateTime) {
        this.schDateTime = schDateTime;
    }

    public Long getAgentId() {
        return agentId;
    }

    public void setAgentId(Long agentId) {
        this.agentId = agentId;
    }

    public ServiceMst getServiceMst() {
        return serviceMst;
    }

    public void setServiceMst(ServiceMst serviceMst) {
        this.serviceMst = serviceMst;
    }

    public LanguageMst getLanguageMst() {
        return languageMst;
    }

    public void setLanguageMst(LanguageMst languageMst) {
        this.languageMst = languageMst;
    }

    public CustomerAccDtlService getCustomerAccDtlService() {
        return customerAccDtlService;
    }

    public void setCustomerAccDtlService(CustomerAccDtlService customerAccDtlService) {
        this.customerAccDtlService = customerAccDtlService;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getDocumentNo() {
        return documentNo;
    }

    public void setDocumentNo(String documentNo) {
        this.documentNo = documentNo;
    }

}
