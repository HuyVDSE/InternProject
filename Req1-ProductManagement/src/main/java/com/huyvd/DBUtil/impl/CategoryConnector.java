package com.huyvd.DBUtil.impl;

import com.huyvd.DBUtil.ICategoryConnector;
import com.huyvd.DBUtil.base.BaseConnector;
import com.huyvd.factory.CategoryFactory;
import com.huyvd.model.Category;
import system.mongo.core.IDynamicObject;
import system.mongo.core.connector.MongoObjectConnector;

import java.util.List;

public class CategoryConnector extends BaseConnector implements ICategoryConnector {
    private MongoObjectConnector getMongoObjectConnectorCategory() {
        return getMongoObjectConnector("Category");
    }

    @Override
    public List<Category> getCategoryList() {
        return getMongoObjectConnectorCategory().listAll(new CategoryFactory());
    }

    @Override
    public List<Category> searchCategoryByName(String searchValue) {
        String propertyName = "name";
        return getMongoObjectConnectorCategory().getObjectMatchProperty(propertyName, searchValue);
    }

    @Override
    public void save(Category category) {
        CategoryFactory factory = new CategoryFactory();
        IDynamicObject iDynamicObject = factory.createObject(category);
        getMongoObjectConnectorCategory().insert(iDynamicObject);
    }

    @Override
    public Category getCategory(String id) {
        return (Category) getMongoObjectConnectorCategory().get(id, new CategoryFactory());
    }

    @Override
    public void delete(String id) {
        getMongoObjectConnectorCategory().removeDocument(id);
    }

    @Override
    public void update(Category category) {
        CategoryFactory factory = new CategoryFactory();
        IDynamicObject iDynamicObject = factory.createObject(category);
        getMongoObjectConnectorCategory().update(iDynamicObject);
    }
}
