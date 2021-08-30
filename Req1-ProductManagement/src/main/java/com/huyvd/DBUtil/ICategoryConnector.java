package com.huyvd.DBUtil;

import com.huyvd.model.Category;

import java.util.List;

public interface ICategoryConnector {

    List<Category> getCategoryList();

    void save(Category category);

    Category getCategory(String id);

    void delete(String id);

    void update(Category category);
}
