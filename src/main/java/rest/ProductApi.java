package rest;

import ejb.ProductEjb;
import ejb.ProductEjbI;
import model.Product;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/products")
public class ProductApi {

    @EJB
    ProductEjbI productEjb;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> listProducts() {
        return productEjb.listAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Product getProduct(@PathParam(value = "id") int id) {
        return productEjb.findProduct(id);
    }

}
