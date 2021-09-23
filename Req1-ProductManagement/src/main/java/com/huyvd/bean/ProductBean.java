package com.huyvd.bean;

import com.huyvd.DBUtil.ICategoryConnector;
import com.huyvd.DBUtil.IProductConnector;
import com.huyvd.DBUtil.impl.CategoryConnector;
import com.huyvd.DBUtil.impl.ProductConnector;
import com.huyvd.model.Category;
import com.huyvd.model.Product;
import com.huyvd.utils.DataFileUtil;
import org.primefaces.model.file.UploadedFile;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ManagedBean(name = "Product")
@ViewScoped
public class ProductBean extends BaseBean {

    private static final String IMAGE_FOLDER = "D:\\LongVanSystem\\InternProject\\Req1-ProductManagement\\src\\main\\webapp\\resources\\images\\";

    private IProductConnector productConnector;
    private ICategoryConnector categoryConnector;
    private List<Product> productList;
    private Map<String, Category> categoryMap;
    private Product selectedProduct;
    private String name;
    private String categoryId;
    private String price;
    private UploadedFile image;

    public List<Product> getProductList() {
        productList = productConnector.getProductList();
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public Map<String, Category> getCategoryMap() {
        return categoryMap;
    }

    public void setCategoryMap(Map<String, Category> categoryMap) {
        this.categoryMap = categoryMap;
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public UploadedFile getImage() {
        return image;
    }

    public void setImage(UploadedFile image) {
        this.image = image;
    }

    @PostConstruct
    public void init() {
        productConnector = new ProductConnector();
        categoryConnector = new CategoryConnector();
        categoryMap = new HashMap<>();

        categoryConnector.getCategoryList().forEach((category) -> {
            categoryMap.put(category.getId(), category);
        });
    }

    public String getCategoryName(String categoryId) {
        return categoryMap.get(categoryId).getName();
    }

    public void add() {
        try {
            Product product = new Product();
            product.setCategoryId(categoryId);
            product.setName(name);
            product.setPrice(Double.parseDouble(price));
            if (null == image) {
                product.setImage("default.jpg");
            } else {
                String imageName = image.getFileName();
                DataFileUtil.saveToDisk(imageName, image.getInputStream());
                product.setImage(imageName);
            }
            productConnector.save(product);
            addMessage("Add Product Successful!");
        } catch (Exception ex) {
            addError("Exception: " + ex.getMessage());
        }
    }
}
