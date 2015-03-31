package ua.artcode.ws.endpoint;


import ua.artcode.model.Product;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface IProductService {

    @WebMethod
    Product getProductById(int id);

    @WebMethod
    boolean addProduct(Product product);

}
