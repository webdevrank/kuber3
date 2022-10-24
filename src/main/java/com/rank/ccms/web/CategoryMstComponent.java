package com.rank.ccms.web;

import com.rank.ccms.entities.CategoryMst;
import com.rank.ccms.entities.EmployeeMst;
import com.rank.ccms.service.CategoryMstService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class CategoryMstComponent implements Serializable {

    private CategoryMst categoryMst;
    private List<CategoryMst> listCategory = new ArrayList<>();
    private List<CategoryMst> selectCategory = new ArrayList<>();
    private List<CategoryMst> filteredCategory = new ArrayList<>();
    private String currentCatId;
    @Autowired
    private CategoryMstService categoryMstService;

    public void newCategoryMstComponent() {
        categoryMst = new CategoryMst();
    }

    public String save(HttpServletRequest request) throws Exception {

        EmployeeMst emst = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
        CategoryMst cat = new CategoryMst();
        cat.setCatgName(this.getCategoryMst().getCatgName());
        cat.setCatgCd(this.getCategoryMst().getCatgCd());
        cat.setCatgDesc(this.getCategoryMst().getCatgDesc());
        cat.setActiveFlg(true);
        cat.setDeleteFlg(false);
        cat = categoryMstService.saveCategoryMst(cat, emst);

        if (cat == null) {

            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            categoryMst = new CategoryMst();
            return "/pages/category/createCategory.xhtml";
        } else {

            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Saved Successfully", "Saved Successfully");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            categoryMst = new CategoryMst();
            return "/pages/category/createCategory.xhtml";
        }

    }

    public String update(HttpServletRequest request) {

        EmployeeMst emst = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
        CategoryMst cat = categoryMstService.saveCategoryMst(categoryMst, emst);
        if (cat == null) {

            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed to update Category ", "Please try again!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            return "/pages/category/editCategory.xhtml";
        } else {

            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Category id " + cat.getId() + " updated Successfully! ", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);

            return "/pages/category/editCategory.xhtml";
        }

    }

    public String back() {
        loadCategoryList();
        return "/pages/category/listCategory.xhtml";
    }

    public String checkEdit() {
        if (this.selectCategory.size() > 0) {

            return "/pages/category/editCategory.xhtml";
        } else {
            RequestContext.getCurrentInstance().execute("PF('categoryNoeditViewDialog').show();");
            return "/pages/category/listCategory.xhtml";
        }
    }

    public void delete(HttpServletRequest request) {

        List<CategoryMst> sList = getSelectCategory();
        try {
            EmployeeMst emst = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
            for (CategoryMst sList1 : sList) {
                CategoryMst cat;
                cat = categoryMstService.findCategoryMstById(sList1.getId());
                cat.setDeleteFlg(true);
                categoryMstService.saveCategoryMst(cat, emst);
            }

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "De-activated Successfully!", ""));
            loadCategoryList();
        } catch (Exception e) {

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "De-activation Failed!", ""));

        }
    }

    public void activate(HttpServletRequest request) {

        List<CategoryMst> sList = getSelectCategory();
        try {
            EmployeeMst emst = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
            for (CategoryMst sList1 : sList) {
                CategoryMst cat;
                cat = categoryMstService.findCategoryMstById(sList1.getId());
                cat.setActiveFlg(true);
                categoryMstService.saveCategoryMst(cat, emst);
            }

            loadCategoryList();
        } catch (Exception e) {

            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Activation Failed!", ""));

        }
    }

    public void deactivate(HttpServletRequest request) {

        List<CategoryMst> sList = getSelectCategory();
        try {
            EmployeeMst emst = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
            for (CategoryMst sList1 : sList) {
                CategoryMst cat;
                cat = categoryMstService.findCategoryMstById(sList1.getId());
                cat.setActiveFlg(false);
                categoryMstService.saveCategoryMst(cat, emst);
            }

            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "De-activated successfully", ""));
            loadCategoryList();
        } catch (Exception e) {

            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "De-activation falied", ""));

        }
    }

    public void onEdit(RowEditEvent eve) {
        CategoryMst catMst = (CategoryMst) eve.getObject();
        HttpServletRequest request = (HttpServletRequest) (FacesContext.getCurrentInstance().getExternalContext().getRequest());
        EmployeeMst emst = (EmployeeMst) request.getSession().getAttribute("ormUserMaster");
        catMst = categoryMstService.saveCategoryMst(catMst, emst);
        if (catMst == null) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Failed to Update Category id "
                    + ((CategoryMst) eve.getObject()).getId(), "Please try again!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Updated Category id "
                    + ((CategoryMst) eve.getObject()).getId() + " Successfully!", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }

    }

    public void onCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Update Cancelled");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public List<CategoryMst> loadCategoryList() {

        selectCategory.clear();
        setListCategory(categoryMstService.findAllNonDeletedCategoryMsts());
        this.setFilteredCategory(getListCategory());
        return categoryMstService.findAllNonDeletedCategoryMsts();

    }

    public void selectCategoryById(Long id) {
        setCategoryMst(categoryMstService.findCategoryMstById(id));

    }

    public CategoryMst getCategoryMst() {
        return categoryMst;
    }

    public void setCategoryMst(CategoryMst categoryMst) {
        this.categoryMst = categoryMst;
    }

    public List<CategoryMst> getListCategory() {
        return listCategory;
    }

    public void setListCategory(List<CategoryMst> listCategory) {
        this.listCategory = listCategory;
    }

    public List<CategoryMst> getSelectCategory() {
        if (selectCategory.size() == 1) {
            categoryMst = selectCategory.get(0);
        }
        return selectCategory;
    }

    public void setSelectCategory(List<CategoryMst> selectCategory) {
        this.selectCategory = selectCategory;
    }

    public String getCurrentCatId() {
        return currentCatId;
    }

    public void setCurrentCatId(String currentCatId) {
        this.currentCatId = currentCatId;
    }

    public void checkErrors() {

    }

    public List<CategoryMst> getFilteredCategory() {
        return filteredCategory;
    }

    public void setFilteredCategory(List<CategoryMst> filteredCategory) {
        this.filteredCategory = filteredCategory;
    }

    public void checkDuplicateCategoryName(FacesContext ctx, UIComponent component, Object value)
            throws ValidatorException {
        String newCategoryname = value.toString();
        if (!value.toString().trim().equals("")) {
            if (newCategoryname.length() == newCategoryname.trim().length()) {
                CategoryMst serMst = categoryMstService.findCategoryByCategoryName(newCategoryname);

                if (serMst != null) {
                    FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Category Name: Validation Error:Category Name already exists", "Category Name: Validation Error:Category Name already exists");

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

    public void checkDuplicateCategoryNameEdit(FacesContext ctx, UIComponent component, Object value)
            throws ValidatorException {
        String newCategoryname = value.toString();
        Long catId = (Long) component.getAttributes().get("catId");
        if (!value.toString().trim().equals("")) {
            if (newCategoryname.length() == newCategoryname.trim().length()) {
                CategoryMst serMst = categoryMstService.findCategoryByCategoryName(newCategoryname);
                if (serMst != null) {

                    if (!Objects.equals(serMst.getId(), catId)) {
                        FacesMessage fma = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "Name: Validation Error:Category Name already exists", "Name: Validation Error:Category Name already exists");

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

    public void checkcat() {
        UIComponent component = UIComponent.getCurrentComponent(FacesContext.getCurrentInstance());
        String catId = (String) component.getAttributes().get("catId");
        this.setCurrentCatId(catId);
    }

    public void checkDuplicateCategoryCd(FacesContext ctx, UIComponent component, Object value)
            throws ValidatorException {
        String newCategoryCd = value.toString();
        if (!value.toString().trim().equals("")) {
            if (newCategoryCd.length() == newCategoryCd.trim().length()) {
                CategoryMst serMst = categoryMstService.findCategoryByCategoryCode(newCategoryCd);

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
