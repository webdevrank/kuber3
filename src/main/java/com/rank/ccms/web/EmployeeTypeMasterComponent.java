package com.rank.ccms.web;

import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.entities.EmployeeTypeMst;
import com.rank.ccms.service.EmployeeMstService;
import com.rank.ccms.service.EmployeeTypeMstService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class EmployeeTypeMasterComponent implements Serializable {

    private EmployeeTypeMst employeeTypeMaster;
    private List<EmployeeTypeMst> listEmployeeTypeMst = new ArrayList<>();
    private List<EmployeeTypeMst> filteredlistEmployeeTypeMst;
    private List<EmployeeTypeMst> selectEmployeeTypeMst = new ArrayList<>();

    @Autowired
    private EmployeeTypeMstService employeeTypeMstservice;
    @Autowired
    private EmployeeMstService employeeMstService;

    public void newEmployeeTypeMstComponent() {
        employeeTypeMaster = new EmployeeTypeMst();
    }

    public List<EmployeeTypeMst> getAllEmployeeType() {
        listEmployeeTypeMst = employeeTypeMstservice.findAllNonDeletedEmployeeTypeMst();
        return listEmployeeTypeMst;
    }

    public void checkErrors() {

    }

    public void checkDuplicateEmployeeTypeName(FacesContext ctx, UIComponent component, Object value)
            throws ValidatorException {

        if (value != null) {

            String newEmployeeTypename = value.toString();
            if (!value.toString().trim().equals("")) {
                if (newEmployeeTypename.length() == newEmployeeTypename.trim().length()) {
                    EmployeeTypeMst employeeTypeMst = employeeTypeMstservice.findEmployeeTypeByEmployeeTypeName(newEmployeeTypename);

                    if (employeeTypeMst != null) {
                        FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "Type Name: Validation Error:Type Name already exists", "Type Name: Validation Error:Type Name already exists");
                        throw new ValidatorException(fma);

                    }
                } else {
                    FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Type Name: Validation Error: Please remove leading and trailing spaces ", "Type Name: Validation Error: Please remove leading and trailing spaces");
                    throw new ValidatorException(fma);
                }
            } else {
                FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Type Name: Validation Error: Type Name can not be blank", "Type Name: Validation Error:Type Name can not be blank");
                throw new ValidatorException(fma);
            }
        }
    }

    public void checkDescription(FacesContext ctx, UIComponent component, Object value)
            throws ValidatorException {
        String desc = value.toString();
        if (!value.toString().trim().equals("")) {

            if (desc.length() != desc.trim().length()) {

                FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Description: Validation Error: Please remove leading and trailing spaces ", "Description: Validation Error: Please remove leading and trailing spaces");
                throw new ValidatorException(fma);
            }
        }
    }

    public String save(HttpServletRequest request) {
        EmployeeTypeMst employeeTypeMst = getEmployeeTypeMaster();
        EmployeeMst empmst = employeeMstService.findEmployeeMstById((long) 1);
        //EmployeeMst empmst = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
        employeeTypeMst.setActiveFlg(true);
        employeeTypeMst.setDeleteFlg(false);
        employeeTypeMst = employeeTypeMstservice.saveEmployeeTypeMst(employeeTypeMst, empmst);

        if (employeeTypeMst == null) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Please Try Again");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "/pages/employee/newEmployeeType.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Saved Successfully", "Saved Successfully");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            employeeTypeMaster = new EmployeeTypeMst();
            loadEmployeeTypeMst();
            return "/pages/employee/newEmployeeType.xhtml";
        }

    }

    public List<EmployeeTypeMst> loadEmployeeTypeMst() {
        selectEmployeeTypeMst.clear();
        List<EmployeeTypeMst> empTypeMstList = employeeTypeMstservice.findAllNonDeletedEmployeeTypeMst();
        setListEmployeeTypeMst(empTypeMstList);
        this.setFilteredlistEmployeeTypeMst(empTypeMstList);
        return empTypeMstList;
    }

    public String checkEmployeeTypeEdit() {

        if (this.selectEmployeeTypeMst.size() > 0) {
            return "/pages/employee/editEmployeeType.xhtml";
        } else {
            RequestContext.getCurrentInstance().execute("PF('editEmpTypeDialog').show();");
            return "/pages/employee/listEmployeeType.xhtml";
        }
    }

    public void onEdit(RowEditEvent event) {

        HttpSession session = ((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getSession(false);

        EmployeeTypeMst employeeTypeMst = (EmployeeTypeMst) event.getObject();
        EmployeeMst empmst = (EmployeeMst) session.getAttribute("ormUserMaster");

        employeeTypeMst = employeeTypeMstservice.saveEmployeeTypeMst(employeeTypeMst, empmst);

        if (employeeTypeMst == null) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed to Update Employee Type id "
                    + ((EmployeeTypeMst) event.getObject()).getId(), "Please try again!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Employee Type id "
                    + ((EmployeeTypeMst) event.getObject()).getId() + " Updated Successfully!", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

    }

    public void onCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Update Cancelled");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public String updateSingleEmployeeType(HttpServletRequest request) {
        EmployeeMst empmst = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
        EmployeeTypeMst empTypeMst = getEmployeeTypeMaster();
        empTypeMst = employeeTypeMstservice.saveEmployeeTypeMst(empTypeMst, empmst);
        if (empTypeMst == null) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Please Try Again");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "/pages/employee/editEmployeeType.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Employee Type id " + empTypeMst.getId() + " updated Successfully", "Saved Successfully");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "/pages/employee/editEmployeeType.xhtml";
        }

    }

    public String back() {
        loadEmployeeTypeMst();
        return "/pages/employee/listEmployeeType.xhtml";
    }

    public String deleteEmployeeType(HttpServletRequest request) {
        EmployeeMst empmst = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
        List<EmployeeTypeMst> selectList = getSelectEmployeeTypeMst();
        try {
            for (EmployeeTypeMst selectList1 : selectList) {
                EmployeeTypeMst empTypMst;
                empTypMst = employeeTypeMstservice.findEmployeeTypeMstById(selectList1.getId());
                empTypMst.setDeleteFlg(true);
                employeeTypeMstservice.saveEmployeeTypeMst(empTypMst, empmst);
            }

            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "De-activated Successfully!", ""));
            loadEmployeeTypeMst();
            return "/pages/employee/listEmployeeType.xhtml";
        } catch (Exception e) {

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "De-activation Failed!", ""));
            return "/pages/employee/listEmployeeType.xhtml";
        }
    }

    public String activateEmployeeType(HttpServletRequest request) {
        EmployeeMst empmst = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
        List<EmployeeTypeMst> selectList = getSelectEmployeeTypeMst();
        try {
            for (EmployeeTypeMst selectList1 : selectList) {
                EmployeeTypeMst empTypMst;
                empTypMst = employeeTypeMstservice.findEmployeeTypeMstById(selectList1.getId());
                empTypMst.setActiveFlg(true);
                employeeTypeMstservice.saveEmployeeTypeMst(empTypMst, empmst);
            }
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Success", "Activated Successfully"));
            return "/pages/employee/listEmployeeType.xhtml";
        } catch (Exception e) {

            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "failed", "Please try again!!"));
            return "/pages/employee/listEmployeeType.xhtml";

        }
    }

    public String deactivateEmployeeType(HttpServletRequest request) {
        EmployeeMst empmst = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
        List<EmployeeTypeMst> selectList = getSelectEmployeeTypeMst();
        try {
            for (EmployeeTypeMst selectList1 : selectList) {
                EmployeeTypeMst empTypMst;
                empTypMst = employeeTypeMstservice.findEmployeeTypeMstById(selectList1.getId());
                empTypMst.setActiveFlg(false);
                employeeTypeMstservice.saveEmployeeTypeMst(empTypMst, empmst);
            }
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Success", "Deactivated Successfully"));
            return "/pages/employee/listEmployeeType.xhtml";
        } catch (Exception e) {

            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "failed", "Please try again!!"));
            return "/pages/employee/listEmployeeType.xhtml";

        }
    }

    public void selectEmployeeTypeById(Long id) {
        setEmployeeTypeMaster(employeeTypeMstservice.findEmployeeTypeMstById(id));

    }

    public EmployeeTypeMst getEmployeeTypeMaster() {
        return employeeTypeMaster;
    }

    public void setEmployeeTypeMaster(EmployeeTypeMst employeeTypeMaster) {
        this.employeeTypeMaster = employeeTypeMaster;
    }

    public List<EmployeeTypeMst> getListEmployeeTypeMst() {
        return listEmployeeTypeMst;
    }

    public void setListEmployeeTypeMst(List<EmployeeTypeMst> listEmployeeTypeMst) {
        this.listEmployeeTypeMst = listEmployeeTypeMst;
    }

    public List<EmployeeTypeMst> getSelectEmployeeTypeMst() {
        if (selectEmployeeTypeMst.size() == 1) {
            employeeTypeMaster = selectEmployeeTypeMst.get(0);
        }
        return selectEmployeeTypeMst;
    }

    public void setSelectEmployeeTypeMst(List<EmployeeTypeMst> selectEmployeeTypeMst) {
        this.selectEmployeeTypeMst = selectEmployeeTypeMst;
    }

    public List<EmployeeTypeMst> getFilteredlistEmployeeTypeMst() {
        return filteredlistEmployeeTypeMst;
    }

    public void setFilteredlistEmployeeTypeMst(List<EmployeeTypeMst> filteredlistEmployeeTypeMst) {
        this.filteredlistEmployeeTypeMst = filteredlistEmployeeTypeMst;
    }

}
