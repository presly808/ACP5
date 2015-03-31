package ua.artcode.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by serhii on 30.03.15.
 */
public class ProductHolder {

    private Map<Integer, Product> productMap = new HashMap<>();

    public ProductHolder() {

        productMap.put(1, new Product(1,"LG", 1200));
        productMap.put(2, new Product(2,"Samsung", 200));
        productMap.put(3, new Product(3,"Fly", 1288));

    }

    public Map<Integer, Product> getProductMap() {
        return productMap;
    }

    public void setProductMap(Map<Integer, Product> productMap) {
        this.productMap = productMap;
    }
}
