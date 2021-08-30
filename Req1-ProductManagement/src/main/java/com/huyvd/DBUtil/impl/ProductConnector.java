package com.huyvd.DBUtil.impl;

import com.huyvd.DBUtil.IProductConnector;
import com.huyvd.DBUtil.base.BaseConnector;
import com.huyvd.factory.ProductFactory;
import com.huyvd.model.Product;
import system.mongo.core.IDynamicObject;
import system.mongo.core.connector.MongoObjectConnector;

import java.util.List;

public class ProductConnector extends BaseConnector implements IProductConnector {
    private MongoObjectConnector getMongoObjectConnectorProduct() {
        return getMongoObjectConnector("Product");
    }

    @Override
    public List<Product> getProductList() {
        return getMongoObjectConnectorProduct().listAll(new ProductFactory());
    }

    @Override
    public void save(Product product) {
        ProductFactory factory = new ProductFactory();
        IDynamicObject iDynamicObject = factory.createObject(product);
        getMongoObjectConnectorProduct().insert(iDynamicObject);
    }

    @Override
    public Product getProduct(String id) {
        return (Product) getMongoObjectConnectorProduct().get(id, new ProductFactory());
    }

    @Override
    public void delete(String id) {
        getMongoObjectConnectorProduct().removeDocument(id);
    }

    @Override
    public void update(Product product) {
        ProductFactory factory = new ProductFactory();
        IDynamicObject iDynamicObject = factory.createObject(product);
        getMongoObjectConnectorProduct().update(iDynamicObject);
    }
}
