package com.huyvd.bean;

import com.huyvd.DBUtil.ICategoryConnector;
import com.huyvd.DBUtil.impl.CategoryConnector;
import com.huyvd.model.Category;
import org.primefaces.PrimeFaces;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ManagedBean(name = "Category")
@SessionScoped
public class CategoryBean extends BaseBean{
    private List<Category> categoryList;
    private ICategoryConnector categoryConnector;
    private String categoryId;
    private String categoryName;
    private Category selectedCategory;
    private String searchValue;

    public List<Category> getCategoryList() {
        categoryList = categoryConnector.getCategoryList();
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public String getCategoryId() {
        categoryList = categoryConnector.getCategoryList();
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(Category selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

    public ICategoryConnector getCategoryConnector() {
        return categoryConnector;
    }

    public void setCategoryConnector(ICategoryConnector categoryConnector) {
        this.categoryConnector = categoryConnector;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    @PostConstruct
    public void init() {
        categoryConnector = new CategoryConnector();
    }

    public void add() {
        try {
            Category model = new Category();
            model.setName(categoryName);
            categoryConnector.save(model);
            addMessage("Add Category Success");
//            return "category.page";
        } catch (Exception ex) {
            addError("Exception: " + ex.getMessage());
        }
//        return "";
    }

    public void update() {
        try {
            categoryConnector.save(selectedCategory);
            addMessage("Update Category Success");
        } catch (Exception ex) {
            addError("Exception: " + ex.getMessage());
        }
    }

    public void delete(String id) {
        try {
            categoryConnector.delete(id);
            addMessage("Record deleted!");
        } catch (Exception ex) {
            addError("Exception: " + ex.getMessage());
        }
    }
}
