package com.huyvd.DBUtil;

import com.huyvd.model.Product;

import java.util.List;

public interface IProductConnector {
    List<Product> getProductList();

    void save(Product product);

    Product getProduct(String id);

    void delete(String id);

    void update(Product product);
}
