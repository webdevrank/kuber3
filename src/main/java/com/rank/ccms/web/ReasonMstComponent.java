package com.rank.ccms.web;

import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.entities.ReasonMst;
import com.rank.ccms.service.EmployeeMstService;
import com.rank.ccms.service.ReasonMstService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
public class ReasonMstComponent implements Serializable {

    private ReasonMst reasonMst;
    private List<ReasonMst> listReasonMst = new ArrayList<>();
    private List<ReasonMst> selectReasonMst = new ArrayList<>();
    private int first;

    @Autowired
    private ReasonMstService reasonMstService;

    @Autowired
    private EmployeeMstService employeeMstService;

    public void checkErrors() {
    }

    public void checkDuplicateReasonCode(FacesContext ctx, UIComponent component, Object value)
            throws ValidatorException {
        if (value != null) {
            String newreasonCode = value.toString();
            if (!value.toString().trim().equals("")) {
                if (newreasonCode.length() == newreasonCode.trim().length()) {
                    ReasonMst reasonMaster = reasonMstService.findReasonMstByReasonCode(newreasonCode);

                    if (reasonMaster != null) {
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

    public void checkDuplicateReasonType(FacesContext ctx, UIComponent component, Object value)
            throws ValidatorException {
        if (value != null) {
            String newreasonType = value.toString();
            if (!value.toString().trim().equals("")) {
                ReasonMst reasonMaster = reasonMstService.findReasonMstByReasonType(newreasonType);

                if (reasonMaster != null) {
                    FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Type: Validation Error:Type already exists", "Type: Validation Error:Type already exists");
                    throw new ValidatorException(fma);
                }
            } else {
                FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Type: Validation Error: Code can not be blank", "Type: Validation Error:Type can not be blank");
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
        } else {
            FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Description: Validation Error: Description can not be blank", "Description: Validation Error:Description can not be blank");
            throw new ValidatorException(fma);
        }
    }

    public void newReasonMst() {
        reasonMst = new ReasonMst();
    }

    public String save(HttpServletRequest request) {
        ReasonMst reasonMster = getReasonMst();
        EmployeeMst empmst = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
        reasonMster.setActiveFlg(true);
        reasonMster.setDeleteFlg(false);
        reasonMster = reasonMstService.save(reasonMster, empmst);
        if (reasonMster == null) {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ""));
            return "/pages/reason/addNewReason.xhtml";
        } else {
            newReasonMst();
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Saved Successfully!", ""));
            return "/pages/reason/addNewReason.xhtml";
        }
    }

    public String checkReasonEdit() {

        if (this.selectReasonMst.size() > 0) {
            return "/pages/reason/editReason.xhtml";
        } else {
            RequestContext.getCurrentInstance().execute("PF('editReasonDialog').show();");
            return "/pages/reason/listReason.xhtml";
        }
    }

    public void onEdit(RowEditEvent event) {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        ReasonMst reasonMster = (ReasonMst) event.getObject();
        EmployeeMst empmst = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
        reasonMster = reasonMstService.save(reasonMster, empmst);
        if (null == reasonMster) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed to Update Reason id "
                    + ((ReasonMst) event.getObject()).getId(), "Please try again!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Updated Reason id "
                    + ((ReasonMst) event.getObject()).getId() + " Successfully!", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public void onCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Update Cancelled");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public String back() {
        loadReasonMst();
        return "/pages/reason/listReason.xhtml";
    }

    public String updateSingleReason(HttpServletRequest request) {
        EmployeeMst empmst = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
        ReasonMst reasonMaster = getReasonMst();
        reasonMaster = reasonMstService.save(reasonMaster, empmst);
        if (reasonMaster == null) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Please Try Again");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "/pages/reason/editReason.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Reason updated Successfully", "Saved Successfully");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "/pages/reason/editReason.xhtml";
        }

    }

    public String deleteReason(HttpServletRequest request) {
        List<ReasonMst> selectList = getSelectReasonMst();
        try {
            EmployeeMst empmst = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
            for (ReasonMst selectList1 : selectList) {
                ReasonMst reasonMster;
                reasonMster = reasonMstService.findNonDeletedById(selectList1.getId());
                reasonMster.setDeleteFlg(true);
                reasonMstService.save(reasonMster, empmst);
            }
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "De-activated Successfully!", ""));
            loadReasonMst();
            return "/pages/reason/listReason.xhtml";
        } catch (Exception e) {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "De-activated Failed!", ""));
            return "/pages/reason/listReason.xhtml";
        }
    }

    public void loadReasonMstMenu() {
        loadReasonMst();
        this.selectReasonMst.clear();
        this.first = 0;
    }

    public List<ReasonMst> loadReasonMst() {
        setListReasonMst(reasonMstService.getAllNonDeletedReasons());
        return listReasonMst;

    }

    public void onPageChange(PageEvent event) {
        this.setFirst(((DataTable) event.getSource()).getFirst());
    }

    public void selectReasonById(Long id) {
        setReasonMst(reasonMstService.findNonDeletedById(id));
    }

    public ReasonMst getReasonMst() {
        return reasonMst;
    }

    public void setReasonMst(ReasonMst reasonMst) {
        this.reasonMst = reasonMst;
    }

    public List<ReasonMst> getListReasonMst() {
        return listReasonMst;
    }

    public void setListReasonMst(List<ReasonMst> listReasonMst) {
        this.listReasonMst = listReasonMst;
    }

    public List<ReasonMst> getSelectReasonMst() {
        return selectReasonMst;
    }

    public void setSelectReasonMst(List<ReasonMst> selectReasonMst) {
        if (selectReasonMst.size() == 1) {
            reasonMst = selectReasonMst.get(0);
        }
        this.selectReasonMst = selectReasonMst;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

}
