package ua.artcode.ws.endpoint;

import ua.artcode.model.Product;
import ua.artcode.model.ProductHolder;

import javax.jws.WebService;
import java.util.Map;

@WebService(endpointInterface = "ua.artcode.ws.endpoint.IProductService")
public class ProductService implements IProductService {

    private ProductHolder productHolder = new ProductHolder();

    @Override
    public Product getProductById(int id) {
        return productHolder.getProductMap().get(id);
    }

    @Override
    public boolean addProduct(Product product) {
        Map<Integer, Product> productMap = productHolder.getProductMap();
        productMap.put(productMap.size()+1, product);
        return true;
    }
}