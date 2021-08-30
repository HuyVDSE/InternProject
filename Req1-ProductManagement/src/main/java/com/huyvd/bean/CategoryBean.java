package com.huyvd.bean;

import com.huyvd.DBUtil.ICategoryConnector;
import com.huyvd.DBUtil.impl.CategoryConnector;
import com.huyvd.model.Category;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.List;

@ManagedBean(name = "Category")
@SessionScoped
public class CategoryBean extends BaseBean{
    private List<Category> categoryList;
    private ICategoryConnector categoryConnector;
    private String categoryId;
    private String categoryName;
    private Category selectedCategory;

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

    @PostConstruct
    public void init() {
        categoryConnector = new CategoryConnector();
    }

    public String add() {
        try {
            Category model = new Category();
            model.setName(categoryName);
            categoryConnector.save(model);
            addMessage("Add Category Success");
            return "category.page";
        } catch (Exception e) {
            addError("Exception: " + e.getMessage());
        }
        return "";
    }

    public String update() {
        try {
            categoryConnector.save(selectedCategory);
            addMessage("Update Category Success");
            return "category.page";
        } catch (Exception e) {
            addError("Exception: " + e.getMessage());
        }
        return "";
    }
}
