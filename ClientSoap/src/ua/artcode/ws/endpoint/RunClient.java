package ua.artcode.ws.endpoint;

/**
 * Created by serhii on 30.03.15.
 */
public class RunClient {

    public static void main(String[] args) {
        ProductServiceService productService = new ProductServiceService();
        IProductService service = productService.getProductServicePort();
        Product product = service.getProductById(2);
        System.out.printf("id %d, name %s, price %.3f",
                                product.getId(),
                                product.getName(),
                                product.getPrice());

    }
}
