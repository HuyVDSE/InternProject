package com.huyvd.factory;

import com.huyvd.model.Product;
import org.bson.Document;
import system.mongo.core.IDynamicObject;
import system.mongo.core.data.IMongoObjectFactory;
import system.mongo.core.data.MongoDynamicObject;

import java.util.UUID;

public class ProductFactory implements IMongoObjectFactory<Product> {
    @Override
    public Product createObject(Document document) {
        Product model = new Product();
        if (document.get("id") != null) {
            model.setId(document.get("id").toString());
        }
        if (document.get("name") != null) {
            model.setName(document.get("name").toString());
        }
        if (document.get("image") != null) {
            model.setImage(document.get("image").toString());
        }
        if (document.get("price") != null) {
            model.setPrice((double) document.get("price"));
        }
        if (document.get("categoryId") != null) {
            model.setCategoryId(document.get("categoryId").toString());
        }
        return model;
    }

    public IDynamicObject createObject(Product model) {
        IDynamicObject iDynamicObject;
        if (model.getId() == null || model.getId().length() == 0) {
            iDynamicObject = new MongoDynamicObject(UUID.randomUUID().toString());
        } else {
            iDynamicObject = new MongoDynamicObject(model.getId());
        }
        iDynamicObject.put("name", model.getName());
        iDynamicObject.put("image", model.getImage());
        iDynamicObject.put("price", model.getPrice());
        iDynamicObject.put("categoryId", model.getCategoryId());
        return iDynamicObject;
    }
}
