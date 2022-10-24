package com.rank.ccms.web;

import com.rank.ccms.dto.CallDroppedDto;
import com.rank.ccms.entities.CallMst;
import com.rank.ccms.entities.CategoryMst;
import com.rank.ccms.entities.CustomerMst;
import com.rank.ccms.entities.LanguageMst;
import com.rank.ccms.entities.ServiceMst;
import com.rank.ccms.service.CallMstService;
import com.rank.ccms.service.CategoryMstService;
import com.rank.ccms.service.CustomerMstService;
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
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class CallDroppedComponent implements Serializable {

    private List<CallMst> callMstList;
    private List<CallDroppedDto> listDroppedCall;
    private List<CallDroppedDto> selectDroppedCall;
    private Date presentDate;
    private String mobileNo;
    private String accountNo;
    private String custid;

    @Autowired
    private CategoryMstService categoryMstService;
    @Autowired
    private ServiceMstService serviceMstService;
    @Autowired
    private LanguageMstService languageMstService;
    @Autowired
    private CustomerMstService customerMstService;
    @Autowired
    private CallMstService callMstService;

    private Date searchStartTime;
    private Date searchEndTime;

    private String downloadedFileName;
    private String downloadCSS;
    private File file;
    private boolean exportToCsvBtnStatus;
    private String outputTextToExport = "";

    public void newDropCall() {
        this.searchStartTime = null;
        this.searchEndTime = null;
        this.custid = "";
        this.mobileNo = "";
        this.accountNo = "";
        this.downloadCSS = "display:none";
        this.downloadedFileName = "";
        setListDroppedCall(null);
        setExportToCsvBtnStatus(false);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        Date d = cal.getTime();
        setPresentDate(d);
    }

    public void clear() {
        this.searchStartTime = null;
        this.searchEndTime = null;
        this.mobileNo = "";
        this.accountNo = "";
        this.custid = "";
        this.downloadCSS = "display:none";
        this.downloadedFileName = "";

        if (listDroppedCall != null) {
            if (!listDroppedCall.isEmpty()) {
                listDroppedCall.clear();
            }
        }

        setExportToCsvBtnStatus(false);
    }

    public void loadDroppedCalls(Date starttime, Date endtime, String cust, String mobile, String account) {
        this.exportToCsvBtnStatus = false;
        this.downloadCSS = "display:none";
        this.downloadedFileName = "";

        boolean valid = true;

        String startTimeOutput = "";
        String endTimeOutput = "";
        String custIdOutput = "";
        String mobileOutput = "";
        String accountOutput = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        if (starttime != null) {
            startTimeOutput = "Start Date and Time : " + dateFormat.format(searchStartTime);
        }
        if (endtime != null) {
            endTimeOutput = "End Date and Time : " + dateFormat.format(searchEndTime);
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

        if (listDroppedCall != null) {
            if (!listDroppedCall.isEmpty()) {
                listDroppedCall.clear();
            }
        }
        setExportToCsvBtnStatus(false);

        List<CallDroppedDto> cdList = new ArrayList();
        callMstList = new ArrayList();

        if ((mobile == null || mobile.trim().equals("")) && (cust == null || cust.trim().equals("")) && (account == null || account.trim().equals("")) && starttime == null && endtime == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "No search input !", "Search Unsuccessful!!"));
            valid = false;

        } else if (starttime != null || endtime != null) {
            if (starttime == null && endtime != null || starttime != null && endtime == null || starttime == null && endtime == null) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please select Start Date and End Date,both!", "Search Unsuccessfull!!"));
                valid = false;
            }

        }
        if (valid) {
            try {

                if (starttime != null && endtime != null) {
                    Timestamp dt_time1 = CustomConvert.javaDateToTimeStamp(starttime);
                    Timestamp dt_time2 = CustomConvert.javaDateToTimeStamp(endtime);
                    if (starttime.after(endtime)) {
                        FacesContext.getCurrentInstance().addMessage(null,
                                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Start Date Time is greater than End Date Time!", "Search Unsuccessful!!"));
                    } else if (cust == null || cust.equals("")) {
                        callMstList = callMstService.findDroppedCallByDate(dt_time1, dt_time2);

                    } else {
                        callMstList = callMstService.findDroppedCallByDateCustid(dt_time1, dt_time2, cust);

                    }
                } else if (starttime == null && endtime == null) {
                    if (cust == null || cust.equals("")) {
                        callMstList = callMstService.findDroppedCallByDate(null, null);

                    } else {
                        callMstList = callMstService.findDroppedByCustid(cust);
                    }
                }

                this.downloadCSS = "display:none";
                this.downloadedFileName = "";

                int id = 0;
                for (CallMst callMstList1 : callMstList) {
                    CallMst callMst = callMstList1;

                    CallDroppedDto callDroppedDto = new CallDroppedDto();
                    id++;
                    String deviceId;
                    String deviceName;
                    String deviceIp;
                    String deviceOs;
                    String callMedium;
                    String callStatus;
                    if (null != callMst.getDeviceId()) {
                        deviceId = callMst.getDeviceId();
                    } else {
                        deviceId = "";
                    }
                    if (null != callMst.getDeviceName()) {
                        deviceName = callMst.getDeviceName();
                    } else {
                        deviceName = "";
                    }
                    if (null != callMst.getDeviceIp()) {
                        deviceIp = callMst.getDeviceIp();
                    } else {
                        deviceIp = "";
                    }
                    if (callMst.getDeviceOs() == null) {
                        deviceOs = "";
                    } else {
                        deviceOs = callMst.getDeviceOs();
                    }
                    if (null != callMst.getCallMedium()) {
                        callMedium = callMst.getCallMedium();
                    } else {
                        callMedium = "";
                    }
                    if (null != callMst.getCallStatus()) {
                        callStatus = callMst.getCallStatus();
                    } else {
                        callStatus = "";
                    }
                    CustomerMst custMst = customerMstService.findCustomerMstByCustomerId(callMst.getCustId());
                    if (custMst != null) {
                        if (mobile != null && !mobile.trim().equals("")) {
                            if (custMst.getCellPhone() != Long.parseLong(mobile)) {
                                custMst = null;
                            }
                        }

                        if (account != null && !account.trim().equals("")) {
                            if (custMst != null && !custMst.getAccountNo().equals(account)) {
                                custMst = null;
                            }
                        }
                    }
                    if (custMst != null) {

                        String customerID = callMst.getCustId();
                        String custFName = "";

                        if (custMst.getFirstName() != null) {
                            custFName = custMst.getFirstName();
                        }
                        String custLName = "";
                        if (custMst.getLastName() != null) {
                            custLName = custMst.getLastName();
                        }

                        String custName;
                        if (custFName.equals("") && custLName.equals("")) {
                            custName = "";
                        } else {
                            custName = custFName;
                        }
                        String email;
                        if (null != custMst.getEmail()) {
                            email = custMst.getEmail();
                        } else {
                            email = "";
                        }

                        String acctNo;
                        if (null != custMst.getAccountNo()) {
                            acctNo = custMst.getAccountNo();
                        } else {
                            acctNo = "";
                        }

                        Long cellPhoneNo;
                        if (null != custMst.getCellPhone()) {
                            cellPhoneNo = custMst.getCellPhone();
                        } else {
                            cellPhoneNo = Long.parseLong("0");
                        }

                        callDroppedDto.setId(id);
                        callDroppedDto.setDeviceId(deviceId);
                        callDroppedDto.setDeviceIP(deviceIp);
                        callDroppedDto.setDeviceName(deviceName);
                        callDroppedDto.setDeviceOs(deviceOs);
                        callDroppedDto.setCallMedium(callMedium);
                        callDroppedDto.setCallStatus(callStatus);
                        callDroppedDto.setCustId(customerID);
                        callDroppedDto.setCustomerName(custName);
                        callDroppedDto.setAccountNo(acctNo);
                        callDroppedDto.setEmail(email);
                        callDroppedDto.setCellNo(cellPhoneNo);
                    }
                    if (null != callMst.getStartTime()) {
                        callDroppedDto.setDroppedCallTime(callMst.getStartTime());
                    } else {
                        callDroppedDto.setDroppedCallTime(null);
                    }
                    if (null == callMst.getServiceId() || callMst.getServiceId() == 0) {
                        callDroppedDto.setServiceName("");
                    } else {

                        ServiceMst serviceMst = serviceMstService.findAllServiceMstById(callMst.getServiceId());
                        callDroppedDto.setServiceCode(serviceMst.getServiceCd());
                        callDroppedDto.setServiceName(serviceMst.getServiceDesc());

                    }
                    if (null == callMst.getLanguageId() || callMst.getLanguageId() == 0) {
                        callDroppedDto.setLanguageName("");
                    } else {
                        LanguageMst language = languageMstService.findAllLanguageMstById(callMst.getLanguageId());
                        if (language != null) {
                            callDroppedDto.setLanguageName(language.getLanguageName());
                            callDroppedDto.setLanguageCode(language.getLanguageCd());
                        } else {
                            callDroppedDto.setLanguageName("");
                            callDroppedDto.setLanguageCode("");
                        }
                    }
                    if (null == callMst.getCategoryId() || callMst.getCategoryId() == 0) {
                        callDroppedDto.setCategoryName("");
                    } else {

                        CategoryMst category = categoryMstService.findCategoryMstById(callMst.getCategoryId());
                        if (category != null) {
                            callDroppedDto.setCategoryName(category.getCatgDesc());
                            callDroppedDto.setCategoryCode(category.getCatgCd());
                        } else {
                            callDroppedDto.setCategoryName("");
                            callDroppedDto.setCategoryCode("");
                        }
                    }
                    if (custMst != null) {
                        cdList.add(callDroppedDto);
                    }
                }
                setListDroppedCall(cdList);
                if (!listDroppedCall.isEmpty()) {
                    setExportToCsvBtnStatus(true);
                }
            } catch (ParseException | NumberFormatException e) {
                this.listDroppedCall.clear();
                RequestContext.getCurrentInstance().execute("setLoadRender2();");

            } finally {
                RequestContext.getCurrentInstance().execute("setLoadRender2();");
            }

            setListDroppedCall(cdList);
            if (!listDroppedCall.isEmpty()) {
                setExportToCsvBtnStatus(true);
                outputTextToExport = startTimeOutput + "   " + endTimeOutput + "  " + custIdOutput + "  " + mobileOutput + "  " + accountOutput;
            }
        }

    }

    public List<CallMst> getCallMstList() {
        return callMstList;
    }

    public void setCallMstList(List<CallMst> callMstList) {
        this.callMstList = callMstList;
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

    public List<CallDroppedDto> getListDroppedCall() {
        return listDroppedCall;
    }

    public void setListDroppedCall(List<CallDroppedDto> listDroppedCall) {
        this.listDroppedCall = listDroppedCall;
    }

    public List<CallDroppedDto> getSelectDroppedCall() {
        return selectDroppedCall;
    }

    public void setSelectDroppedCall(List<CallDroppedDto> selectDroppedCall) {
        this.selectDroppedCall = selectDroppedCall;
    }

    public boolean isExportToCsvBtnStatus() {
        return exportToCsvBtnStatus;
    }

    public void setExportToCsvBtnStatus(boolean exportToCsvBtnStatus) {
        this.exportToCsvBtnStatus = exportToCsvBtnStatus;
    }

    public Date getPresentDate() {
        return presentDate;
    }

    public void setPresentDate(Date presentDate) {
        this.presentDate = presentDate;
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

    public String getOutputTextToExport() {
        return outputTextToExport;
    }

    public void setOutputTextToExport(String outputTextToExport) {
        this.outputTextToExport = outputTextToExport;
    }

}
