package rest.endpoint;

import jaxb.Product;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/product")
public class ProductEndpoint {

    @GET
    @Produces("text/plain")
    @Path("/format")
    public Product getProductFormat(){
        return new Product(1,"name",2000);
    }

    @GET
    @Produces("application/xml")
    @Path("/{id}")
    public Product getProduct(@PathParam("id") String idStr){
        return new Product(1,"name",2000);
    }

}
