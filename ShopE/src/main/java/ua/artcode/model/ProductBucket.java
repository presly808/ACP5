package ua.artcode.model;

import java.util.List;

/**
 * Created by serhii on 24.02.15.
 */
public class ProductBucket {


    private String accessKey;
    private List<Product> products;
    private long amount;
    private Client client;

    public ProductBucket() {
    }

    public ProductBucket(String accessKey, long amount, Client client) {
        this.accessKey = accessKey;
        this.amount = amount;
        this.client = client;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void addProduct(Product product){
        products.add(product);
    }

    public void deleteProduct(Product product){
        products.remove(product);
    }
}
