package com.rank.ccms.web;

import com.rank.ccms.dto.EmployeeMstDto;
import com.rank.ccms.entities.CategoryMst;
import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.entities.EmployeeProficiencyMap;
import com.rank.ccms.entities.EmployeeTypeMst;
import com.rank.ccms.service.CategoryMstService;
import com.rank.ccms.service.EmployeeMstService;
import com.rank.ccms.service.EmployeeProficiencyMapService;
import com.rank.ccms.service.EmployeeTypeMstService;
import com.rank.ccms.util.ThreadSafeSimpleDateFormat;
import org.apache.commons.validator.EmailValidator;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.data.PageEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class EmployeeMstComponent implements Serializable {

    private EmployeeMst employeeMst;
    private EmployeeTypeMst employeeTypeMst;
    private List<EmployeeMstDto> listEmployee;
    private List<EmployeeMstDto> selectEmployees;
    private List<EmployeeMstDto> filteredEmployee;
    private String cell = "";
    private String pin = "";
    private String officePhone = "";
    private String homePhone = "";
    private Long empTypeId;
    private String password = "";
    private int first;
    org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(this.getClass());

    @Autowired
    private EmployeeMstService employeeMstService;
    @Autowired
    private EmployeeTypeMstService employeeTypeMstService;
    @Autowired
    private EmployeeProficiencyMapService proficiencyMapService;
    @Autowired
    private CategoryMstService categoryMstService;

    public EmployeeMstComponent() {
        employeeMst = new EmployeeMst();
        employeeTypeMst = new EmployeeTypeMst();
        this.listEmployee = new ArrayList<>();
        this.selectEmployees = new ArrayList<>();
    }

    public EmployeeMst getEmployeeMst() {
        return employeeMst;
    }

    public void setEmployeeMst(EmployeeMst employeeMst) {
        this.employeeMst = employeeMst;
    }

    public EmployeeTypeMst getEmployeeTypeMst() {
        return employeeTypeMst;
    }

    public void setEmployeeTypeMst(EmployeeTypeMst employeeTypeMst) {
        this.employeeTypeMst = employeeTypeMst;
    }

    public List<EmployeeMstDto> getListEmployee() {
        return listEmployee;
    }

    public void setListEmployee(List<EmployeeMstDto> listEmployee) {
        this.listEmployee = listEmployee;
    }

    public List<EmployeeMst> getAllEmployee() {
        return employeeMstService.findAllNonDeletedEmployeeMsts();

    }

    /**
     * Method to refresh the Model Object and go to the New Entry Page
     */
    public void newEmployeeMstComponent() {
        employeeMst = new EmployeeMst();
        employeeTypeMst = new EmployeeTypeMst();
        cell = "";
        pin = "";
        officePhone = "";
        homePhone = "";
        password = "";
    }

    public String save(HttpServletRequest request) {

        EmployeeMst l_employeeMst = getEmployeeMst();
        l_employeeMst.setActiveFlg(true);
        l_employeeMst.setDeleteFlg(false);

        if ("".equals(this.getPin().trim())) {
            l_employeeMst.setPin(0);
        } else {
            l_employeeMst.setPin(Integer.parseInt(this.getPin()));
        }

        if ("".equals(this.getOfficePhone().trim())) {
            l_employeeMst.setOfficePhone(Long.parseLong("0"));
        } else {
            l_employeeMst.setOfficePhone(Long.parseLong(this.getOfficePhone()));
        }

        if ("".equals(this.getHomePhone().trim())) {
            l_employeeMst.setHomePhone(Long.parseLong("0"));
        } else {
            l_employeeMst.setHomePhone(Long.parseLong(this.getHomePhone()));
        }

        if ("".equals(l_employeeMst.getOfficePhoneStr().trim())) {
            l_employeeMst.setOfficePhone(Long.parseLong("0"));
        } else {
            l_employeeMst.setOfficePhone(Long.parseLong(l_employeeMst.getOfficePhoneStr()));
        }

        if ("".equals(l_employeeMst.getHomePhoneStr().trim())) {
            l_employeeMst.setHomePhone(Long.parseLong("0"));
        } else {
            l_employeeMst.setHomePhone(Long.parseLong(l_employeeMst.getHomePhoneStr()));
        }

        l_employeeMst.setCellPhone(Long.parseLong(this.getCell()));
        l_employeeMst.setLoginPasswd(this.password);
        EmployeeMst empmst = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
        employeeTypeMst = employeeTypeMstService.findEmployeeTypeMstById(getEmployeeTypeMst().getId());     //Will replace with load method to core for efficient performance
        l_employeeMst.setEmpTypId(employeeTypeMst);
        l_employeeMst.setDeactivateFlg(false);
        try {

            l_employeeMst = employeeMstService.saveEmployeeMst(employeeMst, empmst);

            if (null == l_employeeMst) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error! could not create employee", ""));
                return "/pages/employee/createEmployee.xhtml";
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Saved Successfully!", ""));

                EmployeeProficiencyMap proficiencyMap = new EmployeeProficiencyMap();
                proficiencyMap.setActiveFlg(true);
                proficiencyMap.setDeleteFlg(false);
                proficiencyMap.setEmpId(l_employeeMst);
                proficiencyMap.setEmpTypId(l_employeeMst.getEmpTypId().getId());

                proficiencyMap.setType("Category");
                CategoryMst cm = categoryMstService.findCategoryByCategoryName("Urgent");
                proficiencyMap.setPrimarySkill(Long.parseLong("1"));
                proficiencyMap.setSecondarySkill(Long.parseLong("0"));
                if (null != cm) {
                    proficiencyMap.setSkillId(cm.getId());
                    proficiencyMapService.saveEmployeeProficiency(proficiencyMap, empmst);
                }

                employeeMst = new EmployeeMst();
                this.pin = "";
                this.cell = "";
                this.homePhone = "";
                this.officePhone = "";
                this.password = "";
                employeeTypeMst = new EmployeeTypeMst();

                loadEmployeeList();
                return "/pages/employee/createEmployee.xhtml";
            }
        } catch (NumberFormatException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error! could not create employee", ""));
            return "/pages/employee/createEmployee.xhtml";

        }
    }

    public String update(HttpServletRequest request) {
        EmployeeMst emp = getEmployeeMst();

        if ("".equals(this.getPin().trim())) {
            emp.setPin(0);
        } else {
            emp.setPin(Integer.parseInt(this.getPin()));
        }

        if ("".equals(this.getOfficePhone().trim())) {
            emp.setOfficePhone(Long.parseLong("0"));
        } else {
            emp.setOfficePhone(Long.parseLong(this.getOfficePhone()));
        }

        if ("".equals(this.getHomePhone().trim())) {
            emp.setHomePhone(Long.parseLong("0"));
        } else {
            emp.setHomePhone(Long.parseLong(this.getHomePhone()));
        }

        emp.setCellPhone(Long.parseLong(this.getCell()));
        emp.setLoginPasswd(this.password);

        employeeTypeMst = employeeTypeMstService.findEmployeeTypeMstById(this.getEmpTypeId());
        emp.setEmpTypId(employeeTypeMst);

        EmployeeMst empmst = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
        emp = employeeMstService.saveEmployeeMst(emp, empmst);

        if (null == emp) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed to update Employee ", "Please try again!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "/pages/employee/editEmployee.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Employee id " + emp.getId() + " updated Successfully!    ", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);

            return "/pages/employee/editEmployee.xhtml";
        }
    }

    public void activate(HttpServletRequest request) {
        List<EmployeeMstDto> employeeMstList = getSelectEmployees();
        try {
            EmployeeMst empmst = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
            for (EmployeeMstDto employeeMstList1 : employeeMstList) {
                EmployeeMst emp;
                emp = employeeMstService.findEmployeeMstById(Long.parseLong(employeeMstList1.getId()));
                emp.setActiveFlg(true);
                employeeMstService.saveEmployeeMst(emp, empmst);
            }
            loadEmployeeList();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully Activated!", ""));

        } catch (NumberFormatException e) {

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Activation Failed!", ""));

        }
    }

    public void deActivate(HttpServletRequest request) {
        List<EmployeeMstDto> employeeMstList = getSelectEmployees();
        try {
            EmployeeMst empmst = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
            for (EmployeeMstDto employeeMstList1 : employeeMstList) {
                EmployeeMst emp;
                emp = employeeMstService.findEmployeeMstById(Long.parseLong(employeeMstList1.getId()));
                emp.setActiveFlg(true);
                employeeMstService.saveEmployeeMst(emp, empmst);
            }
            loadEmployeeList();
        } catch (NumberFormatException e) {

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "De-activation Failed!", ""));

        }
    }

    public String back() {
        loadEmployeeList();
        return "/pages/employee/listEmployee.xhtml?faces-redirect=true";
    }

    public void selectEmployeeById(Long id) {
        setEmployeeMst(employeeMstService.findEmployeeMstById(id));
        setEmployeeTypeMst(employeeTypeMstService.findEmployeeTypeMstById(getEmployeeMst().getEmpTypId().getId()));

    }

    public void deactivateEmployee(HttpServletRequest request) {
        try {
            logger.info(" deactivateEmployee");
            EmployeeMst empmst = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
            EmployeeMst emp = employeeMstService.findEmployeeMstById(getEmployeeMst().getId());
            emp.setDeactivateFlg(true);
            emp = employeeMstService.saveEmployeeMst(emp, empmst);
            if (emp != null) {
                logger.info(" emp.setDeactivateFlg " + emp.getDeactivateFlg());
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "De-activated Successfully!", ""));
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "De-activation Failed!", ""));
            }

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "De-activation Failed!", ""));

        } finally {
            loadEmployeeList();
        }

    }

    public void activateEmployee(HttpServletRequest request) {
        try {
            logger.info(" activateEmployee" + getEmployeeMst());
            EmployeeMst empmst = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
            EmployeeMst emp = employeeMstService.findEmployeeMstById(getEmployeeMst().getId());
            emp.setDeactivateFlg(false);
            logger.info(" emp.setDeactivateFlg " + emp.getDeactivateFlg());
            emp = employeeMstService.saveEmployeeMst(emp, empmst);
            if (emp != null) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Activated Successfully!", ""));
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Activation Failed!", ""));
            }

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Activation Failed!", ""));

        } finally {
            loadEmployeeList();
        }

    }

    /* For Inline Editing */
    public void onEdit(RowEditEvent eve) {
        HttpSession session = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession(false);
        EmployeeMstDto empMstDto = (EmployeeMstDto) eve.getObject();
        EmployeeMst empMst = employeeMstService.findEmployeeMstById(Long.parseLong(empMstDto.getId()));
        EmployeeTypeMst employeeTypeMstLocal = employeeTypeMstService.findEmployeeTypeMstById(Long.parseLong(empMstDto.getEmployeeTypeId()));
        empMst.setEmpTypId(employeeTypeMstLocal);
        empMstDto.setEmployeeType(employeeTypeMstLocal.getTypeName());
        empMst.setFirstName(empMstDto.getFirstName());
        empMst.setMidName(empMstDto.getMidName());
        empMst.setLastName(empMstDto.getLastName());
        empMst.setLoginPasswd(empMstDto.getLoginPasswd());
        empMst.setAddrsLine1(empMstDto.getAddrsLine1());
        empMst.setAddrsLine2(empMstDto.getAddrsLine2());
        empMst.setCity(empMstDto.getCity());
        empMst.setStateNm(empMstDto.getStateNm());
        if (!empMstDto.getPin().trim().equals("")) {
            empMst.setPin(Integer.parseInt(empMstDto.getPin()));
        } else {
            empMst.setPin(Integer.parseInt("0"));
        }

        empMst.setCountry(empMstDto.getCountry());
        empMst.setEmail(empMstDto.getEmail());
        if (!empMstDto.getOfficePhone().trim().equals("")) {
            empMst.setOfficePhone(Long.parseLong(empMstDto.getOfficePhone()));
        } else {
            empMst.setOfficePhone(Long.parseLong("0"));
        }
        if (!empMstDto.getHomePhone().trim().equals("")) {
            empMst.setHomePhone(Long.parseLong(empMstDto.getHomePhone()));
        } else {
            empMst.setHomePhone(Long.parseLong("0"));
        }
        if (!empMstDto.getCellPhone().trim().equals("")) {
            empMst.setCellPhone(Long.parseLong(empMstDto.getCellPhone()));
        } else {
            empMst.setCellPhone(Long.parseLong("0"));
        }
        EmployeeMst emp = (EmployeeMst) session.getAttribute("ormUserMaster");
        empMst = employeeMstService.saveEmployeeMst(empMst, emp);
        if (empMst == null) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed to Update Employee id "
                    + ((EmployeeMstDto) eve.getObject()).getId(), "Please try again!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Employee id "
                    + ((EmployeeMstDto) eve.getObject()).getId() + " updated Successfully!", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);

        }
    }

    public void onCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Update Cancelled");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void delete(HttpServletRequest request) {
        List<EmployeeMstDto> employeeMstList = getSelectEmployees();
        try {
            EmployeeMst empmst = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");

            for (EmployeeMstDto employeeMstList1 : employeeMstList) {
                EmployeeMst emp;
                emp = employeeMstService.findEmployeeMstById(Long.parseLong(employeeMstList1.getId()));
                emp.setDeleteFlg(true);
                employeeMstService.saveEmployeeMst(emp, empmst);

            }

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "De-activated Successfully!", ""));

        } catch (NumberFormatException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "De-activation Failed!", ""));

        } finally {
            loadEmployeeList();
        }
    }

    public String checkEdit() {

        if (this.selectEmployees.size() > 0) {
            return "/pages/employee/editEmployee.xhtml?faces-redirect=true";
        } else {
            RequestContext.getCurrentInstance().execute("PF('empNoeditViewDialog').show();");
            return "/pages/employee/listEmployee.xhtml";
        }
    }

    public List<EmployeeMstDto> getSelectEmployees() {
        return selectEmployees;
    }

    public void setSelectEmployees(List<EmployeeMstDto> selectEmployees) {

        if (selectEmployees.size() == 1) {
            this.setEmployeeMst(employeeMstService.findEmployeeMstById(Long.parseLong(selectEmployees.get(0).getId())));
            EmployeeTypeMst employeeTypeMstl = employeeMstService.findAllEmployeeByUserId(getEmployeeMst().getLoginId()).getEmpTypId();
            this.password = getEmployeeMst().getLoginPasswd();
            if (null != getEmployeeMst().getPin() && getEmployeeMst().getPin() != 0) {
                this.pin = getEmployeeMst().getPin().toString();
            } else {
                this.pin = "";
            }
            if (null != getEmployeeMst().getHomePhone() && getEmployeeMst().getHomePhone() != 0) {
                this.homePhone = getEmployeeMst().getHomePhone().toString();
            } else {
                this.homePhone = "";
            }
            if (null != getEmployeeMst().getOfficePhone() && getEmployeeMst().getOfficePhone() != 0) {
                this.officePhone = getEmployeeMst().getOfficePhone().toString();
            } else {
                this.officePhone = "";
            }
            if (null != getEmployeeMst().getCellPhone() && getEmployeeMst().getCellPhone() != 0) {
                this.cell = getEmployeeMst().getCellPhone().toString();
            } else {
                this.cell = "";
            }

            this.setEmpTypeId(employeeTypeMstl.getId());
            this.getEmployeeMst().setEmpTypId(employeeTypeMstl);

        }

        this.selectEmployees = selectEmployees;
    }

    public void checkErrors() {

    }

    public void callRegister() {
        RequestContext.getCurrentInstance().execute("register();");
    }

    public void checkDuplicateLogId(FacesContext ctx, UIComponent component, Object value)
            throws ValidatorException {
        String newUsername = value.toString();
        if (!value.toString().trim().equals("")) {

            if (newUsername.length() == newUsername.trim().length()) {
                EmployeeMst l_employeeMst = employeeMstService.findAllEmployeeByUserId(newUsername);
                if (l_employeeMst != null) {
                    FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error: Login Id is already been used ", "Error: Login Id is already been used");
                    throw new ValidatorException(fma);
                }
            } else {
                FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Login Id: Validation Error: Please remove leading and trailing spaces ", "Login Id: Validation Error: Please remove leading and trailing spaces");
                throw new ValidatorException(fma);
            }
        } else {
            FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Login Id: Validation Error: Login Id can not be blank", "Login Id: Validation Error:Login Id can not be blank");
            throw new ValidatorException(fma);
        }
    }

    public void checkDuplicateEmailId(FacesContext ctx, UIComponent component, Object value)
            throws ValidatorException {
        String newEmailId = value.toString();

        if (!value.toString().trim().equals("")) {

            if (newEmailId.length() == newEmailId.trim().length()) {
                boolean valid = EmailValidator.getInstance().isValid(newEmailId);
                if (valid) {
                    EmployeeMst l_employeeMst = employeeMstService.findEmployeeByEmailId(newEmailId);
                    if (l_employeeMst != null) {
                        FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "Error: Email Id is already been registered ", "Error: Email Id is already been registered");
                        throw new ValidatorException(fma);
                    }
                } else {
                    FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Email: Validation Error:Invalid email format ", "Email: Validation Error:Invalid email format");
                    throw new ValidatorException(fma);
                }
            } else {
                FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Email Id: Validation Error: Please remove leading and trailing spaces ", "Email Id: Validation Error: Please remove leading and trailing spaces");
                throw new ValidatorException(fma);
            }
        } else {
            FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Email Id: Validation Error: Email Id can not be blank", "Email Id: Validation Error:Email Id can not be blank");
            throw new ValidatorException(fma);
        }
    }

    public void checkDuplicateEmailIdEdit(FacesContext ctx, UIComponent component, Object value)
            throws ValidatorException {
        String newEmailId = value.toString();
        Long empId = (Long) component.getAttributes().get("empId");

        if (!value.toString().trim().equals("")) {

            if (newEmailId.length() == newEmailId.trim().length()) {
                boolean valid = EmailValidator.getInstance().isValid(newEmailId);
                if (valid) {
                    EmployeeMst l_employeeMst = employeeMstService.findEmployeeByEmailId(newEmailId);
                    if (l_employeeMst != null) {

                        if (!Objects.equals(l_employeeMst.getId(), empId)) {
                            FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                    "Error: Email Id is already been registered ", "Error: Email Id is already been registered");
                            throw new ValidatorException(fma);
                        }
                    }
                } else {
                    FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Email: Validation Error:Invalid email format ", "Email: Validation Error:Invalid email format");
                    throw new ValidatorException(fma);
                }
            } else {
                FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Email Id: Validation Error: Please remove leading and trailing spaces ", "Email Id: Validation Error: Please remove leading and trailing spaces");
                throw new ValidatorException(fma);
            }
        } else {
            FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Email Id: Validation Error: Email Id can not be blank", "Email Id: Validation Error:Email Id can not be blank");
            throw new ValidatorException(fma);
        }
    }

    public void checkDuplicateEmailIdEditMultiple(FacesContext ctx, UIComponent component, Object value)
            throws ValidatorException {
        String newEmailId = value.toString();
        String empId = (String) component.getAttributes().get("empId");
        Long eid = Long.parseLong(empId);

        if (!value.toString().trim().equals("")) {

            if (newEmailId.length() == newEmailId.trim().length()) {
                boolean valid = EmailValidator.getInstance().isValid(newEmailId);
                if (valid) {
                    EmployeeMst l_employeeMst = employeeMstService.findEmployeeByEmailId(newEmailId);
                    if (l_employeeMst != null) {

                        if (!Objects.equals(l_employeeMst.getId(), eid)) {
                            FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                    "Error: Email Id is already been registered ", "Error: Email Id is already been registered");
                            throw new ValidatorException(fma);
                        }
                    }
                } else {
                    FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Email: Validation Error:Invalid email format ", "Email: Validation Error:Invalid email format");
                    throw new ValidatorException(fma);
                }
            } else {
                FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Email Id: Validation Error: Please remove leading and trailing spaces ", "Email Id: Validation Error: Please remove leading and trailing spaces");
                throw new ValidatorException(fma);
            }
        } else {
            FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Email Id: Validation Error: Email Id can not be blank", "Email Id: Validation Error:Email Id can not be blank");
            throw new ValidatorException(fma);
        }
    }

    public void checkDuplicateMobileNo(FacesContext ctx, UIComponent component, Object value)
            throws ValidatorException {
        String newMobileNo = value.toString();

        if (!value.toString().trim().equals("")) {

            if (newMobileNo.length() == 10) {

                EmployeeMst l_employeeMst = employeeMstService.findEmployeeByMobileno(newMobileNo);
                if (l_employeeMst != null) {
                    FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error: Mobile Number is already been registered ", "Error: Mobile Number is already been registered");
                    throw new ValidatorException(fma);
                }

            } else {
                FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Mobile Number:Validation Error:Only numeric value with minimum 10 digits allowed", "Mobile Number:Validation Error:Only numeric value with minimum 10 digits allowed");
                throw new ValidatorException(fma);
            }
        } else {
            FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Mobile Number: Validation Error: Mobile Number can not be blank", "Mobile Number: Validation Error:Mobile Number can not be blank");
            throw new ValidatorException(fma);
        }
    }

    public void checkDuplicateMobileNoEdit(FacesContext ctx, UIComponent component, Object value)
            throws ValidatorException {
        String newMobileNo = value.toString();
        Long empId = (Long) component.getAttributes().get("empId");

        if (!value.toString().trim().equals("")) {

            if (newMobileNo.length() == 10) {

                EmployeeMst l_employeeMst = employeeMstService.findEmployeeByMobileno(newMobileNo);
                if (l_employeeMst != null) {
                    if (!Objects.equals(l_employeeMst.getId(), empId)) {
                        FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "Error: Mobile Number is already been registered ", "Error: Mobile Number is already been registered");
                        throw new ValidatorException(fma);
                    }

                }

            } else {
                FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Mobile Number:Validation Error:Only numeric value with minimum 10 digits allowed", "Mobile Number:Validation Error:Only numeric value with minimum 10 digits allowed");
                throw new ValidatorException(fma);
            }
        } else {
            FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Mobile Number: Validation Error: Mobile Number can not be blank", "Mobile Number: Validation Error:Mobile Number can not be blank");
            throw new ValidatorException(fma);
        }
    }

    public void checkDuplicateMobileNoEditMultiple(FacesContext ctx, UIComponent component, Object value)
            throws ValidatorException {
        String newMobileNo = value.toString();
        String empId = (String) component.getAttributes().get("empId");
        Long eid = Long.parseLong(empId);

        if (!value.toString().trim().equals("")) {

            if (newMobileNo.length() == 10) {

                EmployeeMst l_employeeMst = employeeMstService.findEmployeeByMobileno(newMobileNo);
                if (l_employeeMst != null) {
                    if (!Objects.equals(l_employeeMst.getId(), eid)) {
                        FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "Error: Mobile Number is already been registered ", "Error: Mobile Number is already been registered");
                        throw new ValidatorException(fma);
                    }
                }

            } else {
                FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Mobile Number:Validation Error:Only numeric value with minimum 10 digits allowed", "Mobile Number:Validation Error:Only numeric value with minimum 10 digits allowed");
                throw new ValidatorException(fma);
            }
        } else {
            FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Mobile Number: Validation Error: Mobile Number can not be blank", "Mobile Number: Validation Error:Mobile Number can not be blank");
            throw new ValidatorException(fma);
        }
    }

    public void checkAddress(FacesContext ctx, UIComponent component, Object value)
            throws ValidatorException {
        String address = value.toString();
        if (!value.toString().trim().equals("")) {

            if (address.length() != address.trim().length()) {

                FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Address 1: Validation Error: Please remove leading and trailing spaces ", "Address 1: Validation Error: Please remove leading and trailing spaces");
                throw new ValidatorException(fma);
            }
        } else {
            FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Address 1: Validation Error: Address 1 can not be blank", "Address 1: Validation Error:Address 1 can not be blank");
            throw new ValidatorException(fma);
        }
    }

    public void checkAddress1(FacesContext ctx, UIComponent component, Object value)
            throws ValidatorException {
        String address = value.toString();
        if (!value.toString().trim().equals("")) {

            if (address.length() != address.trim().length()) {

                FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Address 2: Validation Error: Please remove leading and trailing spaces ", "Address 1: Validation Error: Please remove leading and trailing spaces");
                throw new ValidatorException(fma);
            }
        }
    }

    public void checkCity(FacesContext ctx, UIComponent component, Object value)
            throws ValidatorException {
        String city = value.toString();
        if (!value.toString().trim().equals("")) {

            if (city.length() != city.trim().length()) {

                FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "City: Validation Error: Please remove leading and trailing spaces ", "City: Validation Error: Please remove leading and trailing spaces");
                throw new ValidatorException(fma);
            } else {
                Pattern p = Pattern.compile("^$|^[a-zA-Z ]+$");
                Matcher m = p.matcher(city);
                if (!m.matches()) {
                    FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "City: Validation Error: City can not be blank", "City: Validation Error:Only characters and spaces allowed");
                }
            }
        }
    }

    public void checkState(FacesContext ctx, UIComponent component, Object value)
            throws ValidatorException {
        String state = value.toString();
        if (!value.toString().trim().equals("")) {

            if (state.length() != state.trim().length()) {

                FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "State: Validation Error: Please remove leading and trailing spaces ", "State: Validation Error: Please remove leading and trailing spaces");
                throw new ValidatorException(fma);
            } else {
                Pattern p = Pattern.compile("^$|^[a-zA-Z ]+$");
                Matcher m = p.matcher(state);
                if (!m.matches()) {
                    FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "State: Validation Error: City can not be blank", "State: Validation Error:Only characters and spaces allowed");
                }
            }
        }
    }

    public void checkCountry(FacesContext ctx, UIComponent component, Object value)
            throws ValidatorException {
        String country = value.toString();
        if (!value.toString().trim().equals("")) {

            if (country.length() != country.trim().length()) {

                FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Country: Validation Error: Please remove leading and trailing spaces ", "Country: Validation Error: Please remove leading and trailing spaces");
                throw new ValidatorException(fma);
            } else {
                Pattern p = Pattern.compile("^$|^[a-zA-Z ]+$");
                Matcher m = p.matcher(country);
                if (!m.matches()) {
                    FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Country: Validation Error: City can not be blank", "Country: Validation Error:Only characters and spaces allowed");
                }
            }
        }
    }

    public void checkPin(FacesContext ctx, UIComponent component, Object value)
            throws ValidatorException {
        String pinn = value.toString();
        if (!value.toString().trim().equals("")) {

            if (pinn.length() != 6) {

                FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Pin: Validation Error:Only numeric value with 6 digits allowed", "Pin: Validation Error:Only numeric value with 6 digits allowed");
                throw new ValidatorException(fma);
            }
        }
    }

    public void checkOfficePhone(FacesContext ctx, UIComponent component, Object value)
            throws ValidatorException {
        String offph = value.toString();
        if (!value.toString().trim().equals("")) {

            if (offph.length() < 10) {

                FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Office Phone: Validation Error:Only numeric value with minimum 10 digits allowed", "Office Phone: Validation Error:Only numeric value with minimum 10 digits allowed");
                throw new ValidatorException(fma);
            }
        }
    }

    public void checkHomePhone(FacesContext ctx, UIComponent component, Object value)
            throws ValidatorException {
        String hph = value.toString();
        if (!value.toString().trim().equals("")) {

            if (hph.length() < 10) {

                FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Home Phone: Validation Error:Only numeric value with minimum 10 digits allowed", "Home Phone: Validation Error:Only numeric value with minimum 10 digits allowed");
                throw new ValidatorException(fma);
            }
        }
    }

    public void loadEmployeeListMenu() {
        loadEmployeeList();
        this.first = 0;
    }

    public void loadEmployeeList() {
        SimpleDateFormat sdf = new ThreadSafeSimpleDateFormat("dd-MM-yyyy HH:mm");
        selectEmployees.clear();
        
        List<EmployeeMst> employeeMstList = employeeMstService.findAllNonDeletedEmployeeMsts();
        List<EmployeeMstDto> employeeMstListDto = new ArrayList<>();
        for (EmployeeMst employeeMstList1 : employeeMstList) {
            EmployeeMstDto employeeMstDto = new EmployeeMstDto();
            employeeMstDto.setId(employeeMstList1.getId().toString());
            employeeMstDto.setFirstName(employeeMstList1.getFirstName());
            employeeMstDto.setMidName(employeeMstList1.getMidName());
            employeeMstDto.setLastName(employeeMstList1.getLastName());
            if (null != employeeMstList1.getMidName() && !employeeMstList1.getMidName().trim().equals("")) {
                employeeMstDto.setFullName(employeeMstList1.getFirstName().trim() + " " + employeeMstList1.getMidName().trim() + " " + employeeMstList1.getLastName().trim());
            } else {
                employeeMstDto.setFullName(employeeMstList1.getFirstName().trim() + " " + employeeMstList1.getLastName().trim());
            }
            employeeMstDto.setLoginId(employeeMstList1.getLoginId());
            employeeMstDto.setLoginPasswd(employeeMstList1.getLoginPasswd());
            if (employeeMstList1.getEmpDob() != null) {
                employeeMstDto.setEmpDob(sdf.format(employeeMstList1.getEmpDob()));
            } else {
                employeeMstDto.setEmpDob("");
            }
            employeeMstDto.setAddrsLine1(employeeMstList1.getAddrsLine1());
            employeeMstDto.setAddrsLine2(employeeMstList1.getAddrsLine2());
            employeeMstDto.setCity(employeeMstList1.getCity());
            employeeMstDto.setStateNm(employeeMstList1.getStateNm());

            if (null != employeeMstList1.getPin() && employeeMstList1.getPin() != 0) {
                employeeMstDto.setPin(employeeMstList1.getPin().toString());
            } else {
                employeeMstDto.setPin("");
            }
            employeeMstDto.setCountry(employeeMstList1.getCountry());
            employeeMstDto.setEmail(employeeMstList1.getEmail());
            if (null != employeeMstList1.getHomePhone() && employeeMstList1.getHomePhone() != 0) {
                employeeMstDto.setHomePhone(employeeMstList1.getHomePhone().toString());
            } else {
                employeeMstDto.setHomePhone("");
            }
            if (null != employeeMstList1.getOfficePhone() && employeeMstList1.getOfficePhone() != 0) {
                employeeMstDto.setOfficePhone(employeeMstList1.getOfficePhone().toString());
            } else {
                employeeMstDto.setOfficePhone("");
            }
            if (null != employeeMstList1.getCellPhone() && employeeMstList1.getCellPhone() != 0) {
                employeeMstDto.setCellPhone(employeeMstList1.getCellPhone().toString());
            } else {
                employeeMstDto.setCellPhone("");
            }

            employeeMstDto.setEmployeeType(employeeMstList1.getEmpTypId().getTypeName());
            employeeMstDto.setEmployeeTypeId((employeeMstList1.getEmpTypId().getId().toString()));
            employeeMstDto.setDeactiveFlg(employeeMstList1.getDeactivateFlg());
            employeeMstListDto.add(employeeMstDto);

        }
        setListEmployee(employeeMstListDto);
        this.setFilteredEmployee(getListEmployee());
    }

    public void onPageChange(PageEvent event) {
        this.setFirst(((DataTable) event.getSource()).getFirst());
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public Long getEmpTypeId() {
        return empTypeId;
    }

    public void setEmpTypeId(Long empTypeId) {
        this.empTypeId = empTypeId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public List<EmployeeMstDto> getFilteredEmployee() {
        return filteredEmployee;
    }

    public void setFilteredEmployee(List<EmployeeMstDto> filteredEmployee) {
        this.filteredEmployee = filteredEmployee;
    }

}
