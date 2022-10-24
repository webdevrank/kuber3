package com.rank.ccms.web;

import com.rank.ccms.entities.CallMst;
import com.rank.ccms.entities.CustomerDeviceDtl;
import com.rank.ccms.entities.CustomerMst;
import com.rank.ccms.service.CallMstService;
import com.rank.ccms.service.CustomerDeviceDtlService;
import com.rank.ccms.service.CustomerMstService;
import com.rank.ccms.util.CallScheduler;
import com.rank.ccms.util.Constants;
import com.rank.ccms.util.CustomConvert;
import com.rank.ccms.util.CustomerConstant;
import com.rank.ccms.util.JavascriptCryptoDecryptor;
import com.rank.ccms.util.SocketMessage;
import com.rank.ccms.util.ThreadSafeSimpleDateFormat;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class CustomerLoginProcessesComponent implements Serializable {

    private String custId;
    private String password;
    private CustomerMst customerMst;
    private CustomerDeviceDtl customerDeviceDtl;
    private String timeZone;
    private String salt;
    private String four;
    org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(this.getClass());

    @Autowired
    CustomerMstService customerMstService;
    @Autowired
    CustomerDeviceDtlService customerDeviceDtlService;
    @Autowired
    CustomerComponent customerComponent;

    @Autowired
    CallMstService callMstService;
    @Autowired
    VidyoAgent vidyoAgent;
    @Autowired
    CallScheduler callScheduler;

    public String customerWebLogin() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        session.setAttribute("socketHostPublic", Constants.socketHostPublic);
        String msg = "";
        CustomerMst cm;
        boolean loggedin = false;

        try {
            if (null != custId && !"".equals(custId)) {
                String originalPass;
                try {
//            looger.info("decryptAESEncryptWithSaltAndIV(" + userPassword + "," + request.getSession().getId() + "," + salt + "," + four + ")");
                    originalPass = JavascriptCryptoDecryptor.decryptAESEncryptWithSaltAndIV(password, session.getId(), salt, four);

                    logger.info("Original Password:" + originalPass);
                    this.setPassword(originalPass);
                } catch (Exception ex) {
                    logger.error("Unable to decrypt: " + ex.getMessage());
                }

                cm = customerMstService.findCustomerMstByCustIdPassword(custId, password);
                if (null != cm) {
                    ThreadSafeSimpleDateFormat dateFormat = new ThreadSafeSimpleDateFormat("yyyy-MM-dd kk:mm:ss:SSSSS");
                    Date date = new Date();
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(date);

                    Timestamp dt_time = new Timestamp(dateFormat.parse(dateFormat.format(cal.getTimeInMillis())).getTime());
                    customerMstService.saveCustomerMst(cm, null);
                    CustomerConstant.customerLoginTime.put(cm.getCustId(), dt_time);
                    Random rn = new Random();
                    int slow = rn.nextInt(1000);
                    try {
                        Thread.sleep(slow);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(CustomerLoginProcessesComponent.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    session.setAttribute("lastLoginTime", dt_time);

                    if (cm.getActiveFlg()) {

                        if (null != cm.getCustId()) {

                            customerDeviceDtl = customerDeviceDtlService.findCustomerDeviceDtlByCustId(cm);
                            if (null != customerDeviceDtl) {
                                if (customerDeviceDtl.getLoginInfo() == 1 || customerDeviceDtl.getLoginInfo() == 2) {
                                    if (CustomerConstant.customerLoginFlag.containsKey(cm.getCustId())) {
                                        CustomerConstant.customerLoginFlag.remove(cm.getCustId());
                                        CustomerConstant.customerMediumFlag.put(cm.getCustId(), customerDeviceDtl.getMobileOsType());

                                    }
                                    loggedin = true;
                                    List<CallMst> listOpenCalls = callMstService.findAllOnGoingCallsByCust(cm.getCustId());
                                    if (!listOpenCalls.isEmpty()) {
                                        for (CallMst cmst : listOpenCalls) {
                                            try {
                                                CallMst cmt;
                                                cmst.setEndTime(CustomConvert.javaDateToTimeStamp(new Date()));
                                                cmt = callMstService.saveCallMst(cmst);
                                                vidyoAgent.setHangupcalled(false);
                                                if (!CallScheduler.customerCallDetailsMap.isEmpty() && null != cmt) {
                                                    if (CallScheduler.customerCallDetailsMap.containsKey(cm.getId().toString())) {
                                                        Set<String> custIdSet = new TreeSet<>();
                                                        if (!CallScheduler.customerCallDetailsMap.isEmpty()) {
                                                            custIdSet = (Set<String>) CallScheduler.customerCallDetailsMap.get(cm.getId().toString());
                                                        }
                                                        if (null == custIdSet) {
                                                            custIdSet = new TreeSet<>();
                                                        }
                                                        custIdSet.add(cm.getCustId());

                                                        CallScheduler.customerCallDetailsMapDisconnected.put(cm.getId().toString(), custIdSet);

                                                    }
                                                }
                                            } catch (ParseException ex) {

                                            }

                                        }
                                    }
                                    if (!customerDeviceDtl.getMobileOsType().trim().equalsIgnoreCase("WEB")) {

                                        SocketMessage.send(callScheduler.getAdminSocket(), cm.getCustId(), "logout");

                                    }

                                }
                            } else {
                                logger.info("customerDeviceDtl not found");
                                customerDeviceDtl = new CustomerDeviceDtl();
                                customerDeviceDtl.setLogout(0);
                            }

                            customerDeviceDtl.setDeviceBrand(null);
                            customerDeviceDtl.setDeviceId(null);
                            customerDeviceDtl.setDeviceIp(null);
                            customerDeviceDtl.setImeiNo(null);

                            customerDeviceDtl.setMobileOsType("WEB");
                            customerDeviceDtl.setStaticIp(null);
                            customerDeviceDtl.setLoginInfo(1);
                            customerDeviceDtl.setCustomerId(cm);
                            customerDeviceDtl.setCustomerOtp(null);
                            customerDeviceDtl.setTimezone(this.getTimeZone());
                            customerDeviceDtl = customerDeviceDtlService.saveCustomerDeviceDtl(customerDeviceDtl, null);

                            if (null != customerDeviceDtl) {

//                                String custMessage = "Your One Time Password (OTP) is " + otpText + " for Registration . Please do not share this password with any one - RANKCO Thank You.";
//                                String otpResponse = SendTranscationalSMS.SMSSender("rankconsultancy", "644366", cm.getCellPhone() + "", custMessage, "RANKCO", "0");
                                msg = "Success";
                                customerMst = cm;

                            } else {

                            }
                        }

                    } else {

                        msg = "NotActiveCustomer";
                    }
                } else {

                    msg = "userNotExist";

                }

            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        if (loggedin) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(CustomerLoginProcessesComponent.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return msg;
    }

    public String doCustomerLoginThroughWeb(HttpServletRequest request) {
        String loginInfo = customerWebLogin();

        if (!"".equals(loginInfo) && loginInfo.equals("Success")) {
            request.getSession().setAttribute("timeZone", this.timeZone);
            request.getSession().setAttribute("ormCustomerMaster", customerMst);

            String mName;

            if (null != customerMst.getMidName()) {
                mName = customerMst.getMidName();
            } else {
                mName = "";
            }

            request.getSession().setAttribute("ormCustomerName", (customerMst.getFirstName() == null || customerMst.getFirstName().trim().length() <= 0) ? "User" : (customerMst.getFirstName() + ((customerMst.getLastName() == null || customerMst.getLastName().trim().length() <= 0) ? "" : ((customerMst.getMidName() == null || customerMst.getMidName().trim().length() <= 0) ? "" : " " + customerMst.getMidName()) + " " + customerMst.getLastName())));
            request.getSession().setAttribute("ormCustomerNameSocket", (customerMst.getFirstName() == null || customerMst.getFirstName().trim().length() <= 0) ? "User" : (customerMst.getFirstName() + ((customerMst.getLastName() == null || customerMst.getLastName().trim().length() <= 0) ? "" : ((customerMst.getMidName() == null || customerMst.getMidName().trim().length() <= 0) ? "" : " " + customerMst.getMidName()) + " " + customerMst.getLastName())) + "~" + this.custId);
            request.getSession().setAttribute("ormCustomerID", customerMst.getCustId());
            customerComponent.setCustomerSocket(customerMst.getCustId());
            customerComponent.setCustName((customerMst.getFirstName() == null || customerMst.getFirstName().trim().length() <= 0) ? "User" : (customerMst.getFirstName() + ((customerMst.getLastName() == null || customerMst.getLastName().trim().length() <= 0) ? "" : ((customerMst.getMidName() == null || customerMst.getMidName().trim().length() <= 0) ? "" : " " + customerMst.getMidName()) + " " + customerMst.getLastName())) + "~" + this.custId);
            logger.info("before null Customer Session");
            request.getSession().setAttribute("callMstThroughWeb", null);

            CustomerConstant.customerLoginFlag.put(customerMst.getCustId(), 0);
            CustomerConstant.customerMediumFlag.put(customerMst.getCustId(), "WEB");
            if (customerMst.getAccountNo() != null && customerMst.getAccountNo().trim().length() > 0) {
                logger.info("Existing Customer call end!");
                CustomerDeviceDtl customerDeviceDtlL = new CustomerDeviceDtl();
                customerDeviceDtlL.setLogout(0);

                customerDeviceDtlL.setDeviceBrand(null);
                customerDeviceDtlL.setDeviceId(null);
                customerDeviceDtlL.setDeviceIp(null);
                customerDeviceDtlL.setImeiNo(null);

                customerDeviceDtlL.setMobileOsType("WEB");
                customerDeviceDtlL.setStaticIp(null);
                customerDeviceDtlL.setLoginInfo(1);
                customerDeviceDtlL.setCustomerId(customerMst);
                customerDeviceDtlL.setCustomerOtp(null);
                customerDeviceDtlL.setTimezone("Asia/Kolkata");
                customerDeviceDtlService.saveCustomerDeviceDtl(customerDeviceDtlL, null);
            }

            return "/pages/customer/customerDashboard.xhtml?faces-redirect=true";
        } else if (!"".equals(loginInfo) && loginInfo.equals("NotActiveCustomer")) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Customer is not active now!", "Customer is not active now! Login Unsuccessfull!!"));
            return "";
        } else if (!"".equals(loginInfo) && loginInfo.equals("userNotExist")) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wrong login credentials!", " Wrong login credentials! Login Unsuccessfull!!"));
            return "";
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login Unsuccessfull!", "Login Unsuccessfull!!"));
            return "";
        }
    }

    public void refreshOperations() {
        customerMst = new CustomerMst();
        customerDeviceDtl = new CustomerDeviceDtl();
        custId = "";
        password = "";
    }

    public String customerLogout(HttpServletRequest request) {
        logger.info("customerLogout...........");
        CustomerMst cm = (CustomerMst) request.getSession().getAttribute("ormCustomerMaster");
        CustomerDeviceDtl customerDeviceDtlL = new CustomerDeviceDtl();
        customerDeviceDtlL.setLogout(1);

        customerDeviceDtlL.setDeviceBrand(null);
        customerDeviceDtlL.setDeviceId(null);
        customerDeviceDtlL.setDeviceIp(null);
        customerDeviceDtlL.setImeiNo(null);

        customerDeviceDtlL.setMobileOsType("WEB");
        customerDeviceDtlL.setStaticIp(null);
        customerDeviceDtlL.setLoginInfo(0);
        customerDeviceDtlL.setCustomerId(cm);
        customerDeviceDtlL.setCustomerOtp(null);
        customerDeviceDtlL.setTimezone("Asia/Kolkata");
        customerDeviceDtlService.saveCustomerDeviceDtl(customerDeviceDtlL, null);
        customerComponent.refreshWhenCallEnds1(request);
        if (CustomerConstant.customerLoginFlag.containsKey(cm.getCustId())) {
            CustomerConstant.customerLoginFlag.remove(cm.getCustId());
        }
        if (CustomerConstant.customerMediumFlag.containsKey(cm.getCustId())) {
            CustomerConstant.customerMediumFlag.remove(cm.getCustId());
        }

        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/loginCustomer.xhtml?faces-redirect=true";
    }

    public void customerLogout1(HttpServletRequest request) {
        CustomerMst cm = (CustomerMst) request.getSession().getAttribute("ormCustomerMaster");
        CustomerDeviceDtl customerDeviceDtlL = new CustomerDeviceDtl();
        customerDeviceDtlL.setLogout(1);

        customerDeviceDtlL.setDeviceBrand(null);
        customerDeviceDtlL.setDeviceId(null);
        customerDeviceDtlL.setDeviceIp(null);
        customerDeviceDtlL.setImeiNo(null);

        customerDeviceDtlL.setMobileOsType("WEB");
        customerDeviceDtlL.setStaticIp(null);
        customerDeviceDtlL.setLoginInfo(0);
        customerDeviceDtlL.setCustomerId(cm);
        customerDeviceDtlL.setCustomerOtp(null);
        customerDeviceDtlL.setTimezone("Asia/Kolkata");
        customerDeviceDtlService.saveCustomerDeviceDtl(customerDeviceDtlL, null);

        customerComponent.refreshWhenCallEnds1(request);

    }

    public String customerForceLogout(HttpServletRequest request) {
        logger.info("customerForceLogout...........");
        CustomerMst cm = (CustomerMst) request.getSession().getAttribute("ormCustomerMaster");

        CustomerDeviceDtl customerDeviceDtlL = new CustomerDeviceDtl();
        customerDeviceDtlL.setLogout(1);

        customerDeviceDtlL.setDeviceBrand(null);
        customerDeviceDtlL.setDeviceId(null);
        customerDeviceDtlL.setDeviceIp(null);
        customerDeviceDtlL.setImeiNo(null);

        customerDeviceDtlL.setMobileOsType("WEB");
        customerDeviceDtlL.setStaticIp(null);
        customerDeviceDtlL.setLoginInfo(0);
        customerDeviceDtlL.setCustomerId(cm);
        customerDeviceDtlL.setCustomerOtp(null);
        customerDeviceDtlL.setTimezone("Asia/Kolkata");
        customerDeviceDtlService.saveCustomerDeviceDtl(customerDeviceDtlL, null);
        customerComponent.refreshWhenCallEnds1(request);

        CustomerConstant.customerMediumFlag.put(cm.getCustId(), customerDeviceDtlL.getMobileOsType());

        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/loginCustomer.xhtml?faces-redirect=true";
    }

    public String customerForceLogoutWhenAnotherUserLoggedIn(HttpServletRequest request) {
        logger.info("customerForceLogoutWhenAnotherUserLoggedIn called...........");
        CustomerMst cm = (CustomerMst) request.getSession().getAttribute("ormCustomerMaster");
        customerDeviceDtl = customerDeviceDtlService.findCustomerDeviceDtlByCustId(cm);
        customerComponent.refreshWhenCallEnds1(request);

        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/loginCustomer.xhtml?faces-redirect=true";
    }

    public void checkCustomerLoggedInWithOtherDevice(HttpServletRequest request) {
        Timestamp lastlogin = (Timestamp) request.getSession().getAttribute("lastLoginTime");
        CustomerMst custFromSession = (CustomerMst) request.getSession().getAttribute("ormCustomerMaster");
        Timestamp newlogin = null;

        if (null != custFromSession) {
            if (CustomerConstant.customerLoginTime.containsKey(custFromSession.getCustId())) {
                newlogin = (Timestamp) CustomerConstant.customerLoginTime.get(custFromSession.getCustId());
            }

            if (null != newlogin) {
                if (newlogin.compareTo(lastlogin) != 0) {
                    request.getSession().setAttribute("ormCustomerMaster", null);
                    RequestContext.getCurrentInstance().execute("closeAllDialog();PF('antotherLoggedIn').show();setTimeout(function(){ PF('antotherLoggedIn').hide();}, 3000);");
                    customerComponent.setStatusOfEndCall(true);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(CustomerLoginProcessesComponent.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    RequestContext.getCurrentInstance().execute("document.getElementById('logout2').click();");
                }
            }
        }

    }

    public String customerLogoutFromOtherDeviceAuto() {

        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/loginCustomer.xhtml?faces-redirect=true";
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public CustomerMst getCustomerMst() {
        return customerMst;
    }

    public void setCustomerMst(CustomerMst customerMst) {
        this.customerMst = customerMst;
    }

    public CustomerDeviceDtl getCustomerDeviceDtl() {
        return customerDeviceDtl;
    }

    public void setCustomerDeviceDtl(CustomerDeviceDtl customerDeviceDtl) {
        this.customerDeviceDtl = customerDeviceDtl;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getFour() {
        return four;
    }

    public void setFour(String four) {
        this.four = four;
    }

}
