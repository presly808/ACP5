package ua.artcode.manager;

import ua.artcode.model.Product;

import java.util.List;

/**
 * Created by serhii on 24.02.15.
 */
public interface ProductManager {

    public void addProduct(String accessKey,String name,
                           String desc, long price, String type);

    public void removeProduct(String accessKey, int id);

    public List<Product> getProducts(int page, int length, String type);


}
