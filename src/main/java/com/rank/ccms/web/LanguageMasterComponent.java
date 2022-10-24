package com.rank.ccms.web;

import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.entities.LanguageMst;
import com.rank.ccms.service.LanguageMstService;
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
public class LanguageMasterComponent implements Serializable {

    private LanguageMst languageMst;
    private List<LanguageMst> listLanguageMst = new ArrayList<>();
    private List<LanguageMst> selectLanguageMst = new ArrayList<>();
    private int first;

    @Autowired
    private LanguageMstService languagemstService;

    public String save(HttpServletRequest request) {
        EmployeeMst empmst = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
        LanguageMst languageMstr = getLanguageMst();
        languageMstr.setActiveFlg(true);
        languageMstr.setDeleteFlg(false);
        languageMstr = languagemstService.saveLanguageMst(languageMstr, empmst);

        if (languageMstr != null) {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Saved successfully!", ""));

            languageMst = new LanguageMst();
            return "/pages/language/addNewLanguage.xhtml";
        } else {
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ""));
            return "/pages/language/addNewLanguage.xhtml";
        }
    }

    public void loadLanguageMstMenu() {
        loadLanguageMst();
        selectLanguageMst.clear();
        this.first = 0;
    }

    public List<LanguageMst> loadLanguageMst() {
        setListLanguageMst(languagemstService.findAllNonDeletedLanguageMsts());

        return listLanguageMst;
    }

    public String checkLanguageEdit() {

        if (this.selectLanguageMst.size() > 0) {
            return "/pages/language/editLanguage.xhtml?faces-redirect=true";
        } else {
            RequestContext.getCurrentInstance().execute("PF('editLanguageDialog').show();");
            return "/pages/language/listLanguage.xhtml";
        }
    }

    public void onEdit(RowEditEvent event) {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        EmployeeMst empmst = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
        LanguageMst languageMast = (LanguageMst) event.getObject();
        languageMast = languagemstService.saveLanguageMst(languageMast, empmst);
        if (null == languageMast) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed to Update Language id "
                    + ((LanguageMst) event.getObject()).getId(), "Please try again!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {

            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Updated Language id "
                    + ((LanguageMst) event.getObject()).getId() + " Successfully!", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

    }

    public void checkErrors() {

    }

    public void checkDuplicateLanguageCode(FacesContext ctx, UIComponent component, Object value)
            throws ValidatorException {

        if (value != null) {
            String newlanguageCode = value.toString();
            if (!value.toString().trim().equals("")) {
                if (newlanguageCode.length() == newlanguageCode.trim().length()) {
                    LanguageMst serMst = languagemstService.findLanguageMstByLanguageCode(newlanguageCode);

                    if (serMst != null) {
                        FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "Code: Validation Error: Code already exists", "Code: Validation Error:Code already exists");

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

    public void checkDuplicateLanguageName(FacesContext ctx, UIComponent component, Object value)
            throws ValidatorException {

        if (value != null) {
            String newlanguageName = value.toString();
            if (!value.toString().trim().equals("")) {
                if (newlanguageName.length() == newlanguageName.trim().length()) {
                    LanguageMst serMst = languagemstService.findLanguageMstByLanguageName(newlanguageName);

                    if (serMst != null) {
                        FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "Name: Validation Error:Name already exists", "Name: Validation Error: Name already exists");

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

    public void onCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Update Cancelled");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public String back() {
        loadLanguageMst();
        return "/pages/language/listLanguage.xhtml?faces-redirect=true";
    }

    public String updateSingleLanguage(HttpServletRequest request) {
        EmployeeMst empmst = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
        LanguageMst languageMaster = getLanguageMst();
        languageMaster = languagemstService.saveLanguageMst(languageMaster, empmst);
        if (languageMaster == null) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Please Try Again");
            FacesContext.getCurrentInstance().addMessage(null, msg);

            return "/pages/language/editLanguage.xhtml";
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Language id " + languageMaster.getId() + " Updated Successfully", "Language id " + languageMaster.getId() + " updated Successfully");
            FacesContext.getCurrentInstance().addMessage(null, msg);

            return "/pages/language/editLanguage.xhtml";
        }

    }

    public void selectLanguageById(Long id) {
        setLanguageMst(languagemstService.findNonDeletedLanguageMstById(id));

    }

    public String deleteLanguage(HttpServletRequest request) {
        EmployeeMst empmst = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
        List<LanguageMst> selectList = getSelectLanguageMst();
        try {
            for (LanguageMst selectList1 : selectList) {
                LanguageMst languageMster;
                languageMster = languagemstService.findNonDeletedLanguageMstById(selectList1.getId());
                languageMster.setDeleteFlg(true);
                languagemstService.saveLanguageMst(languageMster, empmst);
            }

            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "De-activated Successfully!", ""));
            loadLanguageMst();
            return "/pages/language/listLanguage.xhtml";
        } catch (Exception e) {

            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "De-activated Failed!", ""));
            return "/pages/language/listLanguage.xhtml";
        }
    }

    public String activateLanguage(HttpServletRequest request) {
        EmployeeMst empmst = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
        List<LanguageMst> selectList = getSelectLanguageMst();
        try {
            for (LanguageMst selectList1 : selectList) {
                LanguageMst languageMaster;
                languageMaster = languagemstService.findNonDeletedLanguageMstById(selectList1.getId());
                languageMaster.setActiveFlg(true);
                languagemstService.saveLanguageMst(languageMaster, empmst);
            }
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Success", "Activated Successfully"));
            loadLanguageMst();
            return "/pages/language/listLanguage.xhtml";
        } catch (Exception e) {

            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "failed", "Please try again!!"));
            return "/pages/language/listLanguage.xhtml";

        }
    }

    public String deactivateLanguage(HttpServletRequest request) {
        EmployeeMst empmst = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
        List<LanguageMst> selectList = getSelectLanguageMst();
        try {
            for (LanguageMst selectList1 : selectList) {
                LanguageMst languageMaster;
                languageMaster = languagemstService.findNonDeletedLanguageMstById(selectList1.getId());
                languageMaster.setActiveFlg(false);
                languagemstService.saveLanguageMst(languageMaster, empmst);
            }
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Success", "Deactivated Successfully"));
            loadLanguageMst();
            return "/pages/language/listLanguage.xhtml";
        } catch (Exception e) {

            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "failed", "Please try again!!"));
            return "/pages/language/listLanguage.xhtml";

        }
    }

    public void checkDuplicateLanguageMstCode(FacesContext ctx, UIComponent component, Object value)
            throws ValidatorException {

        if (value != null) {
            String newLanguageCode = value.toString();

            LanguageMst languageMaster = languagemstService.findLanguageMstByLanguageCode(newLanguageCode);

            if (languageMaster != null) {
                FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Language Code: Validation Error:Language Code already exists", "Language Code: Validation Error:Language Code already exists");
                throw new ValidatorException(fma);
            }
        }
    }

    public void checkDuplicateLanguageMstName(FacesContext ctx, UIComponent component, Object value)
            throws ValidatorException {

        if (value != null) {

            String newLanguageName = value.toString();
            LanguageMst languageMaster = languagemstService.findLanguageMstByLanguageName(newLanguageName);
            if (languageMaster != null) {
                FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Language Name: Validation Error:Language Name already exists", "Language Name: Validation Error:Language Name already exists");

                throw new ValidatorException(fma);
            }
        }
    }

    public void checkDuplicateLanguageMstNameEdit(FacesContext ctx, UIComponent component, Object value)
            throws ValidatorException {
        Long lanId = (Long) component.getAttributes().get("lanId");
        if (value != null) {

            String newLanguageName = value.toString();
            if (newLanguageName.length() == newLanguageName.trim().length()) {
                LanguageMst languageMaster = languagemstService.findLanguageMstByLanguageName(newLanguageName);

                if (languageMaster != null) {
                    if (!Objects.equals(languageMaster.getId(), lanId)) {
                        FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "Language Name: Validation Error:Language Name already exists", "Language Name: Validation Error:Language Name already exists");

                        throw new ValidatorException(fma);
                    }
                }
            } else {
                FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Name: Validation Error: Please remove leading and trailing spaces ", "Name: Validation Error: Please remove leading and trailing spaces");
                throw new ValidatorException(fma);
            }
        }
    }

    public void onPageChange(PageEvent event) {
        this.setFirst(((DataTable) event.getSource()).getFirst());
    }

    public List<LanguageMst> getListLanguageMst() {
        return listLanguageMst;
    }

    public void setListLanguageMst(List<LanguageMst> listLanguageMst) {
        this.listLanguageMst = listLanguageMst;
    }

    public void newlanguageMasterComponent() {
        languageMst = new LanguageMst();
    }

    public LanguageMst getLanguageMst() {
        return languageMst;
    }

    public void setLanguageMst(LanguageMst languageMst) {
        this.languageMst = languageMst;
    }

    public List<LanguageMst> getSelectLanguageMst() {

        return selectLanguageMst;
    }

    public void setSelectLanguageMst(List<LanguageMst> selectLanguageMst) {

        if (selectLanguageMst.size() == 1) {
            languageMst = selectLanguageMst.get(0);
        }
        this.selectLanguageMst = selectLanguageMst;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

}
