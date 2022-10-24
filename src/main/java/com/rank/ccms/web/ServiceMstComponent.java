package com.rank.ccms.web;

import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.entities.ServiceMst;
import com.rank.ccms.service.EmployeeMstService;
import com.rank.ccms.service.ServiceMstService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.data.PageEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class ServiceMstComponent implements Serializable {

    private ServiceMst servicesMst;
    private EmployeeMst employeeMst;
    private List<ServiceMst> listService = new ArrayList<>();
    private List<ServiceMst> selectService = new ArrayList<>();
    private List<EmployeeMst> listEmployee = new ArrayList<>();
    private int first;

    @Autowired
    private ServiceMstService serviceMstService;

    @Autowired
    private EmployeeMstService employeeMstService;

    public void newServiceMstComponent() {
        servicesMst = new ServiceMst();
        employeeMst = new EmployeeMst();
        listEmployee = employeeMstService.findAllNonDeletedEmployeeMsts();
    }

    public void selectServiceById(Long id) {
        setServicesMst(serviceMstService.findNonDeletedServiceMstById(id));
    }

    public void onEdit(RowEditEvent eve) {

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        listEmployee = employeeMstService.findAllNonDeletedEmployeeMsts();

        ServiceMst sm = (ServiceMst) eve.getObject();
        EmployeeMst empmst = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
        setServicesMst(serviceMstService.findNonDeletedServiceMstById(sm.getId()));

        sm = serviceMstService.saveServiceMst(sm, empmst);

        if (sm == null) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed to Update Service id "
                    + ((ServiceMst) eve.getObject()).getId(), "Please try again!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Updated Service id "
                    + ((ServiceMst) eve.getObject()).getId() + " Successfully!", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

    }

    public void onCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Update Cancelled");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void delete(HttpServletRequest request) {
        List<ServiceMst> sList = getSelectService();
        EmployeeMst empmst = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
        try {
            for (ServiceMst sList1 : sList) {
                ServiceMst ser = serviceMstService.findNonDeletedServiceMstById(sList1.getId());
                ser.setDeleteFlg(true);
                serviceMstService.saveServiceMst(ser, empmst);
            }
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "De-activated Successfully!", ""));
            loadServiceList();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "De-activation Failed!", ""));

        }
    }

    public String save(HttpServletRequest request) {

        ServiceMst sm = new ServiceMst();
        EmployeeMst empmst = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
        sm.setActiveFlg(true);
        sm.setDeleteFlg(false);
        sm.setServiceCd(getServicesMst().getServiceCd());
        sm.setServiceName(getServicesMst().getServiceName());
        sm.setServiceDesc(getServicesMst().getServiceDesc());
        sm = serviceMstService.saveServiceMst(sm, empmst);
        if (sm == null) {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ""));
            return "/pages/service/createService.xhtml";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Saved Successfully!", ""));

            servicesMst = new ServiceMst();
            return "/pages/service/createService.xhtml";
        }

    }

    public String update(HttpServletRequest request) {
        EmployeeMst empmst = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
        ServiceMst sm = getServicesMst();
        sm.setId(getServicesMst().getId());
        sm.setServiceCd(getServicesMst().getServiceCd());
        sm.setServiceName(getServicesMst().getServiceName());
        sm.setServiceDesc(getServicesMst().getServiceDesc());
        sm = serviceMstService.saveServiceMst(sm, empmst);

        if (sm == null) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed to update Service ", "Please try again!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "/pages/service/editService.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Service id " + sm.getId() + " updated Successfully! ", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);

            return "/pages/service/editService.xhtml";
        }
    }

    public void activate(HttpServletRequest request) {
        List<ServiceMst> sList = getSelectService();
        EmployeeMst empmst = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
        try {
            for (ServiceMst sList1 : sList) {
                ServiceMst ser = serviceMstService.findAllServiceMstById(sList1.getId());
                ser.setActiveFlg(true);
                serviceMstService.saveServiceMst(ser, empmst);
            }
            loadServiceList();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Activation Failed!", ""));
        }
    }

    public void deactivate(HttpServletRequest request) {
        List<ServiceMst> sList = getSelectService();
        EmployeeMst empmst = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
        try {
            for (ServiceMst sList1 : sList) {
                ServiceMst ser = serviceMstService.findAllServiceMstById(sList1.getId());
                ser.setActiveFlg(false);
                serviceMstService.saveServiceMst(ser, empmst);
            }
            loadServiceList();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "De-activation Failed!", ""));
        }
    }

    public String back() {
        loadServiceList();
        return "/pages/service/listServices.xhtml?faces-redirect=true";
    }

    public String checkEdit() {

        if (this.selectService.size() > 0) {
            return "/pages/service/editService.xhtml?faces-redirect=true";
        } else {
            RequestContext.getCurrentInstance().execute("PF('serviceNoeditViewDialog').show();");
            return "/pages/service/listServices.xhtml";
        }
    }

    public String showServiceList() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete Successfully!", ""));
        setListService(serviceMstService.findAllNonDeletedServiceMsts());
        return "/pages/service/listServices.xhtml";
    }

    public void loadServiceListMenu() {
        loadServiceList();
        this.selectService.clear();
        this.first = 0;
    }

    public void loadServiceList() {
        setListService(serviceMstService.loadAllService());

    }

    public List<ServiceMst> loadServiceListAll() {
        return serviceMstService.findAllNonDeletedServiceMsts();
    }

    public List<ServiceMst> getSelectService() {
        return selectService;
    }

    public void setSelectService(List<ServiceMst> selectService) {
        listEmployee = employeeMstService.findAllNonDeletedEmployeeMsts();

        List<ServiceMst> selectService1 = new ArrayList<>();
        for (ServiceMst selectService2 : selectService) {
            setServicesMst(serviceMstService.findAllServiceMstById(selectService2.getId()));
            selectService1.add(servicesMst);
        }
        this.selectService = selectService1;

    }

    public void checkErrors() {

    }

    public void checkDuplicateServiceName(FacesContext ctx, UIComponent component, Object value) throws ValidatorException {

        if (value != null) {

            String newServicename = value.toString();
            if (!value.toString().trim().equals("")) {
                if (newServicename.length() == newServicename.trim().length()) {
                    ServiceMst serMst = serviceMstService.findServiceByServiceName(newServicename);

                    if (serMst != null) {
                        FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "Name: Validation Error:Name already exists", "Name: Validation Error:Name already exists");

                        throw new ValidatorException(fma);
                    }
                } else {
                    FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Name: Validation Error: Please remove leading and trailing spaces ", "Name: Validation Error: Please remove leading and trailing spaces");
                    throw new ValidatorException(fma);

                }
            } else {
                FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Name: Validation Error: Name can not be blank", "Name: Validation Error:Name can not be blank");
                throw new ValidatorException(fma);
            }
        }
    }

    public void checkDuplicateServiceNameEdit(FacesContext ctx, UIComponent component, Object value)
            throws ValidatorException {
        Long serId = (Long) component.getAttributes().get("serId");
        if (value != null) {

            String newServicename = value.toString();
            if (!value.toString().trim().equals("")) {
                if (newServicename.length() == newServicename.trim().length()) {
                    ServiceMst serMst = serviceMstService.findServiceByServiceName(newServicename);

                    if (serMst != null) {
                        if (!Objects.equals(serMst.getId(), serId)) {
                            FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                    "Name: Validation Error:Service Name already exists", "Name: Validation Error:Service Name already exists");

                            throw new ValidatorException(fma);
                        }
                    }
                } else {
                    FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Name: Validation Error: Please remove leading and trailing spaces ", "Name: Validation Error: Please remove leading and trailing spaces");
                    throw new ValidatorException(fma);
                }
            } else {
                FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Name: Validation Error: Name can not be blank", "Name: Validation Error:Name can not be blank");
                throw new ValidatorException(fma);
            }
        }
    }

    public void checkDuplicateServiceCode(FacesContext ctx, UIComponent component, Object value)
            throws ValidatorException {

        if (value != null) {
            String newServiceCode = value.toString();
            if (!value.toString().trim().equals("")) {
                if (newServiceCode.length() == newServiceCode.trim().length()) {
                    ServiceMst serMst = serviceMstService.findServiceByServiceCode(newServiceCode);

                    if (serMst != null) {
                        FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "Code: Validation Error:Code already exists", "Code: Validation Error:Code already exists");

                        throw new ValidatorException(fma);
                    }
                } else {
                    FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Code: Validation Error: Please remove leading and trailing spaces ", "Code: Validation Error: Please remove leading and trailing spaces");
                    throw new ValidatorException(fma);
                }
            } else {
                FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Code: Validation Error: Code can not be blank", "Code: Validation Error:Code can not be blank");
                throw new ValidatorException(fma);
            }
        }
    }

    public void checkDuplicateServiceCodeEdit(FacesContext ctx, UIComponent component, Object value)
            throws ValidatorException {
        Long serId = (Long) component.getAttributes().get("servId");
        if (value != null) {
            String newServiceCode = value.toString();
            if (!value.toString().trim().equals("")) {
                if (newServiceCode.length() == newServiceCode.trim().length()) {
                    ServiceMst serMst = serviceMstService.findServiceByServiceCode(newServiceCode);

                    if (serMst != null) {
                        if (!Objects.equals(serMst.getId(), serId)) {
                            FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                    "Code: Validation Error:Code already exists", "Code: Validation Error:Code already exists");

                            throw new ValidatorException(fma);
                        }
                    }
                } else {
                    FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Code: Validation Error: Please remove leading and trailing spaces ", "Code: Validation Error: Please remove leading and trailing spaces");
                    throw new ValidatorException(fma);
                }
            } else {
                FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Code: Validation Error: Code can not be blank", "Code: Validation Error:Code can not be blank");
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

    public void onPageChange(PageEvent event) {
        this.setFirst(((DataTable) event.getSource()).getFirst());
    }

    public List<EmployeeMst> getListEmployee() {
        return listEmployee;
    }

    public void setListEmployee(List<EmployeeMst> listEmployee) {
        this.listEmployee = listEmployee;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public ServiceMst getServicesMst() {
        return servicesMst;
    }

    public void setServicesMst(ServiceMst servicesMst) {
        this.servicesMst = servicesMst;
    }

    public EmployeeMst getEmployeeMst() {
        return employeeMst;
    }

    public void setEmployeeMst(EmployeeMst employeeMst) {
        this.employeeMst = employeeMst;
    }

    public List<ServiceMst> getListService() {
        return listService;
    }

    public void setListService(List<ServiceMst> listService) {
        this.listService = listService;
    }

}
