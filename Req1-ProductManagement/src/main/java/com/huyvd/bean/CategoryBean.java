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
        } catch (Exception ex) {
            addError("Exception: " + ex.getMessage());
        }
    }

    public void update() {
        try {
            categoryConnector.update(selectedCategory);
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

    public void searchCategory() {
        categoryList = categoryConnector.searchCategoryByName(searchValue);
    }
}
