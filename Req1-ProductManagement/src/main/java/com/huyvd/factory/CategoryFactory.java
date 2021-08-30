package com.huyvd.factory;

import com.huyvd.model.Category;
import org.bson.Document;
import system.mongo.core.IDynamicObject;
import system.mongo.core.data.IMongoObjectFactory;
import system.mongo.core.data.MongoDynamicObject;

import java.util.UUID;

public class CategoryFactory implements IMongoObjectFactory<Category> {
    @Override
    public Category createObject(Document document) {
        Category model = new Category();
        if (document.get("id") != null) {
            model.setId(document.get("id").toString());
        }
        if (document.get("name") != null) {
            model.setName(document.get("name").toString());
        }
        return model;
    }

    public IDynamicObject createObject(Category model) {
        IDynamicObject iDynamicObject = null;
        if (model.getId() == null || model.getId().length() == 0) {
            iDynamicObject = new MongoDynamicObject(UUID.randomUUID().toString());
        } else {
            iDynamicObject = new MongoDynamicObject(model.getId());
        }
        iDynamicObject.put("name", model.getName());
        return iDynamicObject;
    }
}
