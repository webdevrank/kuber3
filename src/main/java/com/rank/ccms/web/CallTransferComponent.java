package com.rank.ccms.web;

import com.rank.ccms.dto.CallTransferDto;
import com.rank.ccms.entities.CallDtl;
import com.rank.ccms.entities.CallMst;
import com.rank.ccms.entities.CategoryMst;
import com.rank.ccms.entities.CustomerMst;
import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.entities.ForwardedCall;
import com.rank.ccms.entities.LanguageMst;
import com.rank.ccms.entities.ServiceMst;
import com.rank.ccms.service.CallDtlService;
import com.rank.ccms.service.CallMstService;
import com.rank.ccms.service.CategoryMstService;
import com.rank.ccms.service.CustomerMstService;
import com.rank.ccms.service.EmployeeMstService;
import com.rank.ccms.service.ForwardedCallService;
import com.rank.ccms.service.LanguageMstService;
import com.rank.ccms.service.ServiceMstService;
import com.rank.ccms.util.CustomConvert;
import java.io.File;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class CallTransferComponent implements Serializable {

    private static final Logger logger = Logger.getLogger(CallTransferComponent.class);
    private CallMst callMst;
    private CallDtl callDtl;
    private ForwardedCall forwardedCall;

    private List<CallMst> callMstList;
    private List<CallDtl> callDtlList;
    private List<ForwardedCall> forwardedCallList;
    private List<ServiceMst> serviceMstList;
    private boolean exportToCsvBooleanBtnStatus;
    private Date presentDate;
    private String mobileNo;
    private String accountNo;
    private String custid;
    private String toService;
    private String outputTextToExport = "";

    @Autowired
    private CategoryMstService categoryMstService;
    @Autowired
    private CustomerMstService customerMstService;
    @Autowired
    private EmployeeMstService employeeMstService;
    @Autowired
    private ServiceMstService serviceMstService;
    @Autowired
    private LanguageMstService languageMstService;
    @Autowired
    private CallMstService callMstService;
    @Autowired
    private CallDtlService callDtlService;
    @Autowired
    private ForwardedCallService forwardedCallService;

    private Date searchStartTime;
    private Date searchEndTime;

    private String downloadedFileName;
    private String downloadCSS;
    private File file;

    private List<CallTransferDto> listTransferCall;
    private List<CallTransferDto> selectTransferCall;

    public void newCallTransfer() {

        this.serviceMstList = new ArrayList<>();
        this.serviceMstList = serviceMstService.loadAllService();
        this.searchStartTime = null;
        this.searchEndTime = null;
        this.toService = null;
        this.downloadCSS = "display:none";
        this.downloadedFileName = "";
        this.custid = "";
        this.mobileNo = "";
        this.accountNo = "";
        this.toService = "";

        setListTransferCall(null);
        java.util.Date datepresent = new java.util.Date();
        java.sql.Timestamp timestamp1 = new Timestamp(datepresent.getTime());
        java.util.Date date3 = new java.sql.Date(timestamp1.getTime());
        setPresentDate(date3);
        setExportToCsvBooleanBtnStatus(false);
    }

    public void clear() {

        this.serviceMstList = new ArrayList<>();
        this.serviceMstList = serviceMstService.loadAllService();
        this.searchStartTime = null;
        this.searchEndTime = null;
        this.accountNo = null;
        this.mobileNo = null;
        this.custid = null;
        this.toService = null;
        this.downloadCSS = "display:none";
        this.downloadedFileName = "";
        if (listTransferCall != null) {
            if (!listTransferCall.isEmpty()) {
                listTransferCall.clear();
            }
        }

        setExportToCsvBooleanBtnStatus(false);
    }

    public void loadCallTransfer(Date starttime, Date endtime, String cust, String mobile, String account, String serviceTo) {
        this.exportToCsvBooleanBtnStatus = false;
        this.serviceMstList = new ArrayList<>();
        this.serviceMstList = serviceMstService.loadAllService();

        boolean valid = true;

        String startTimeOutput = "";
        String endTimeOutput = "";
        String custIdOutput = "";
        String mobileOutput = "";
        String accountOutput = "";
        String serviceToOutput = "";

        //render the header to the export links
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        if (starttime != null) {

            startTimeOutput = "Start Date Time : " + dateFormat.format(searchStartTime);

        }
        if (endtime != null) {

            endTimeOutput = "End Date Time : " + dateFormat.format(searchEndTime);
        }
        if (!(cust == null) && !(cust.trim().equals(""))) {

            custIdOutput = "Customer ID : " + custid;
        }
        if (!(mobile == null) && !(mobile.trim().equals(""))) {

            mobileOutput = "Mobile No : " + mobileNo;
        }
        if (!(account == null) && !(account.trim().equals(""))) {

            accountOutput = "Account No : " + accountNo;
        }
        if (!(serviceTo == null) && !(serviceTo.trim().equals("")) && !(serviceTo.equals("0"))) {

            ServiceMst serviceMst = serviceMstService.findServiceByServiceCode(serviceTo);
            serviceToOutput = "Service : " + serviceMst.getServiceDesc();
        }

        if ((mobile == null || mobile.trim().equals("")) && (cust == null || cust.trim().equals("")) && (account == null || account.trim().equals("")) && (serviceTo == null || serviceTo.trim().equals("") || serviceTo.trim().equals("0")) && starttime == null && endtime == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "No search input !", "Search Unsuccessful!!"));
            valid = false;

        } else if (starttime != null || endtime != null) {
            if (starttime == null && endtime != null || starttime != null && endtime == null || starttime == null && endtime == null) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please select Start Date and End Date,both!", "Search Unsuccessfull!!"));
                valid = false;
            }

        } else if (starttime != null && endtime != null) {
            if (starttime.after(endtime)) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Start Date is greater than End Date!", "Search Unsuccessfull!!"));
                valid = false;
            }
        }
        if (valid) {
            try {
                this.downloadCSS = "display:none";
                this.downloadedFileName = "";
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                if (starttime != null && endtime != null) {

                    Timestamp dt_time1 = CustomConvert.javaDateToTimeStamp(starttime);
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(endtime);
                    cal.add(Calendar.DATE, 1);
                    Date endDate1 = cal.getTime();
                    Timestamp dt_time2 = CustomConvert.javaDateToTimeStamp(endDate1);
                    callDtlList = new ArrayList<>();
                    callDtlList = callDtlService.AllForwardedCallDtlBetweenDate(dt_time1, dt_time2);

                }
                HashMap<String, CallDtl> hm = new HashMap();
                for (CallDtl callDtlList1 : callDtlList) {
                    String callID = callDtlList1.getCallMstId().getId() + "";
                    String key = callID.trim();
                    hm.put(key, callDtlList1);
                }
                List<CallDtl> valueList = new ArrayList<>(hm.values());
                List<CallTransferDto> ctList = new ArrayList();

                for (CallDtl cldtl : valueList) {

                    Long callID = cldtl.getCallMstId().getId();

                    callMst = callMstService.findNonDeletedCallById(callID);
                    CustomerMst customerMst = customerMstService.findCustomerMstByCustomerId(callMst.getCustId());
                    Boolean forwardCust = true;
                    if (cust != null && !cust.trim().equals("")) {
                        forwardCust = cust.trim().equals(customerMst.getCustId());

                    }
                    Boolean forwardAcc = true;
                    if (account != null && !account.trim().equals("")) {
                        forwardAcc = account.trim().equals(customerMst.getAccountNo());
                    }
                    Boolean forwardMob = true;
                    if (mobile != null && !mobile.trim().equals("")) {
                        forwardMob = mobile.trim().equals(customerMst.getCellPhone().toString());
                    }

                    if (forwardAcc && forwardCust && forwardMob) {

                        forwardedCallList = forwardedCallService.findForwardedCallByCallId(callMst);

                        for (ForwardedCall forwardedCall1 : forwardedCallList) {
                            ServiceMst localServiceMst = serviceMstService.findAllServiceMstById(forwardedCall1.getSelectedServiceId());
                            logger.info("service to" + serviceTo + localServiceMst);
                            if (serviceTo == null || serviceTo.equals(localServiceMst.getServiceCd()) || serviceTo.equals("0") || serviceTo.trim().equals("")) {

                                CallTransferDto callTransferDto = new CallTransferDto();
                                // To Employee
                                Long toAgent = forwardedCall1.getEmpId().getId();
                                EmployeeMst employeeMst = employeeMstService.findEmployeeMstById(toAgent);
                                String empFName = employeeMst.getFirstName();
                                String empLName = employeeMst.getLastName();
                                String toEmpName = empFName + " " + empLName;

                                //From Employee
                                Long fromAgent = forwardedCall1.getPrevEmpId();
                                EmployeeMst employeeMst1 = new EmployeeMst();
                                String fromEmpName;
                                if (fromAgent != null) {
                                    employeeMst1 = employeeMstService.findEmployeeMstById(fromAgent);
                                    String empFName1 = employeeMst1.getFirstName();
                                    String empLName1 = employeeMst1.getLastName();
                                    fromEmpName = empFName1 + " " + empLName1;
                                } else {
                                    fromEmpName = "";
                                }

                                // From Service
                                Long fromServiceID = forwardedCall1.getFromServiceId();
                                ServiceMst serviceMst1 = new ServiceMst();
                                String fromServiceDesc;
                                if (fromServiceID != null) {
                                    serviceMst1 = serviceMstService.findAllServiceMstById(fromServiceID);

                                    fromServiceDesc = serviceMst1.getServiceDesc();
                                } else {
                                    fromServiceDesc = "";
                                }

                                // To Service
                                Long toServiceID = forwardedCall1.getSelectedServiceId();
                                String toServiceDesc;
                                ServiceMst serviceMst2 = new ServiceMst();
                                if (fromServiceID != null) {
                                    serviceMst2 = serviceMstService.findAllServiceMstById(toServiceID);

                                    toServiceDesc = serviceMst2.getServiceDesc();
                                } else {
                                    toServiceDesc = "";
                                }

                                callTransferDto.setFromEmployeeECN(employeeMst1.getLoginId());
                                callTransferDto.setFromEmployee(fromEmpName);
                                callTransferDto.setToEmployeeECN(employeeMst.getLoginId());
                                callTransferDto.setToEmployee(toEmpName);
                                callTransferDto.setFromServiceCd(serviceMst1.getServiceCd());
                                callTransferDto.setFromService(fromServiceDesc);
                                callTransferDto.setToServiceCd(serviceMst2.getServiceCd());
                                callTransferDto.setToService(toServiceDesc);

                                if (callID != null) {
                                    // Cust ID
                                    String customerID = callMst.getCustId();
                                    String custFName = customerMst.getFirstName();
                                    String custLName = customerMst.getLastName();
                                    // Customer Name
                                    String custName = custFName + " " + custLName;

                                    // Cell Phone
                                    Long cellPhone = customerMst.getCellPhone();

                                    //Device ID
                                    String deviceID = callMst.getDeviceId();
                                    //Device Name
                                    String deviceName = callMst.getDeviceName();

                                    String categoryName = "";
                                    if (callMst.getCategoryId() != 0) {
                                        CategoryMst categoryMst = categoryMstService.findNonDeletedCategoryMstById(callMst.getCategoryId());
                                        if (categoryMst.getCatgName() != null) {
                                            if (!"".equals(categoryMst.getCatgName().trim())) {
                                                categoryName = categoryMst.getCatgDesc();
                                                callTransferDto.setCustomerSeg(categoryMst.getCatgCd());
                                            }
                                        }
                                    }

                                    if (callMst.getLanguageId() != 0) {
                                        LanguageMst skillMst = languageMstService.findAllLanguageMstById(callMst.getLanguageId());
                                        if (skillMst.getLanguageCd() != null) {
                                            if (!"".equals(skillMst.getLanguageCd().trim())) {
                                                callTransferDto.setCustomerLanguageCode(skillMst.getLanguageCd());
                                                callTransferDto.setCustomerLanguage(skillMst.getLanguageName());
                                            }
                                        }
                                    }

                                    // Category Name
                                    callTransferDto.setCallId(callID);
                                    callTransferDto.setCategoryName(categoryName);
                                    callTransferDto.setCellNo(cellPhone);
                                    callTransferDto.setCustId(customerID);
                                    callTransferDto.setCustomerName(custName);
                                    callTransferDto.setDeviceId(deviceID);
                                    callTransferDto.setDeviceName(deviceName);
                                    callTransferDto.setCustomerAccountNo(customerMst.getAccountNo());
                                    callTransferDto.setCustomerEmail(customerMst.getEmail());
                                    callTransferDto.setCustomerSegDesc(categoryName);
                                    callTransferDto.setCallMedium(callMst.getCallMedium());
                                    callTransferDto.setDeviceIp(callMst.getDeviceIp());
                                    callTransferDto.setDeviceOs(callMst.getDeviceOs());
                                    callTransferDto.setSourceStartTime(sdf.format(callMst.getStartTime()));
                                    callTransferDto.setForwardTime(sdf.format(forwardedCall1.getForwardedDatetime()));

                                }

                                ctList.add(callTransferDto);

                            }
                        }
                    }

                }

                setListTransferCall(ctList);
                if (!listTransferCall.isEmpty()) {
                    setExportToCsvBooleanBtnStatus(true);
                    outputTextToExport = startTimeOutput + "   " + endTimeOutput + "  " + custIdOutput + "  " + mobileOutput + "  " + accountOutput + "  " + serviceToOutput;
                }

            } catch (ParseException e) {
                this.listTransferCall.clear();

                RequestContext.getCurrentInstance().execute("setLoadRender2();");
            } finally {
                RequestContext.getCurrentInstance().execute("setLoadRender2();");
            }
        }
    }

    public List<ServiceMst> getServiceMstList() {
        return serviceMstList;
    }

    public void setServiceMstList(List<ServiceMst> serviceMstList) {
        this.serviceMstList = serviceMstList;
    }

    public String getToService() {
        return toService;
    }

    public void setToService(String toService) {
        this.toService = toService;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getCustid() {
        return custid;
    }

    public void setCustid(String custid) {
        this.custid = custid;
    }

    public CallMst getCallMst() {
        return callMst;
    }

    public void setCallMst(CallMst callMst) {
        this.callMst = callMst;
    }

    public CallDtl getCallDtl() {
        return callDtl;
    }

    public void setCallDtl(CallDtl callDtl) {
        this.callDtl = callDtl;
    }

    public ForwardedCall getForwardedCall() {
        return forwardedCall;
    }

    public void setForwardedCall(ForwardedCall forwardedCall) {
        this.forwardedCall = forwardedCall;
    }

    public List<CallMst> getCallMstList() {
        return callMstList;
    }

    public void setCallMstList(List<CallMst> callMstList) {
        this.callMstList = callMstList;
    }

    public List<CallDtl> getCallDtlList() {
        return callDtlList;
    }

    public void setCallDtlList(List<CallDtl> callDtlList) {
        this.callDtlList = callDtlList;
    }

    public List<ForwardedCall> getForwardedCallList() {
        return forwardedCallList;
    }

    public void setForwardedCallList(List<ForwardedCall> forwardedCallList) {
        this.forwardedCallList = forwardedCallList;
    }

    public CategoryMstService getCategoryMstService() {
        return categoryMstService;
    }

    public void setCategoryMstService(CategoryMstService categoryMstService) {
        this.categoryMstService = categoryMstService;
    }

    public CustomerMstService getCustomerMstService() {
        return customerMstService;
    }

    public void setCustomerMstService(CustomerMstService customerMstService) {
        this.customerMstService = customerMstService;
    }

    public EmployeeMstService getEmployeeMstService() {
        return employeeMstService;
    }

    public void setEmployeeMstService(EmployeeMstService employeeMstService) {
        this.employeeMstService = employeeMstService;
    }

    public CallMstService getCallMstService() {
        return callMstService;
    }

    public void setCallMstService(CallMstService callMstService) {
        this.callMstService = callMstService;
    }

    public CallDtlService getCallDtlService() {
        return callDtlService;
    }

    public void setCallDtlService(CallDtlService callDtlService) {
        this.callDtlService = callDtlService;
    }

    public ForwardedCallService getForwardedCallService() {
        return forwardedCallService;
    }

    public void setForwardedCallService(ForwardedCallService forwardedCallService) {
        this.forwardedCallService = forwardedCallService;
    }

    public Date getSearchStartTime() {
        return searchStartTime;
    }

    public void setSearchStartTime(Date searchStartTime) {
        this.searchStartTime = searchStartTime;
    }

    public Date getSearchEndTime() {
        return searchEndTime;
    }

    public void setSearchEndTime(Date searchEndTime) {
        this.searchEndTime = searchEndTime;
    }

    public String getDownloadedFileName() {
        return downloadedFileName;
    }

    public void setDownloadedFileName(String downloadedFileName) {
        this.downloadedFileName = downloadedFileName;
    }

    public String getDownloadCSS() {
        return downloadCSS;
    }

    public void setDownloadCSS(String downloadCSS) {
        this.downloadCSS = downloadCSS;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public ServiceMstService getServiceMstService() {
        return serviceMstService;
    }

    public void setServiceMstService(ServiceMstService serviceMstService) {
        this.serviceMstService = serviceMstService;
    }

    public List<CallTransferDto> getListTransferCall() {
        return listTransferCall;
    }

    public void setListTransferCall(List<CallTransferDto> listTransferCall) {
        this.listTransferCall = listTransferCall;
    }

    public List<CallTransferDto> getSelectTransferCall() {
        return selectTransferCall;
    }

    public void setSelectTransferCall(List<CallTransferDto> selectTransferCall) {
        this.selectTransferCall = selectTransferCall;
    }

    public boolean isExportToCsvBooleanBtnStatus() {
        return exportToCsvBooleanBtnStatus;
    }

    public void setExportToCsvBooleanBtnStatus(boolean exportToCsvBooleanBtnStatus) {
        this.exportToCsvBooleanBtnStatus = exportToCsvBooleanBtnStatus;
    }

    public Date getPresentDate() {
        return presentDate;
    }

    public void setPresentDate(Date presentDate) {
        this.presentDate = presentDate;
    }

    public String getOutputTextToExport() {
        return outputTextToExport;
    }

    public void setOutputTextToExport(String outputTextToExport) {
        this.outputTextToExport = outputTextToExport;
    }

}
