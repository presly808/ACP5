package ua.artcode.week9.soap.ws;

import ua.artcode.week9.soap.model.Product;

import javax.jws.WebService;

@WebService(endpointInterface = "ua.artcode.week9.soap.ws.MyEndpoint")
public class MyEndpointImpl implements MyEndpoint {

    @Override
    public Product getProduct(int id) {
        return new Product(1, "Monitor", 2000);
    }


}
