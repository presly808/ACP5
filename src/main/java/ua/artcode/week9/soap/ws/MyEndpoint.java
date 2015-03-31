package ua.artcode.week9.soap.ws;

import ua.artcode.week9.soap.model.Product;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface MyEndpoint {

    @WebMethod
    Product getProduct(int id);

}
