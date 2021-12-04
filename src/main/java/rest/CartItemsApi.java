//package rest;
//
//import ejb.ProductEjb;
//import ejb.ProductEjbI;
//import model.Product;
//
//import javax.ejb.EJB;
//import javax.ws.rs.*;
//import javax.ws.rs.core.MediaType;
//import java.util.List;
//
//@Path("/cartitems")
//public class CartItemsApi {
//
//    @EJB
//    ProductEjbI productEjb;
//
//    @GET
//    @Path("/list-products")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public List<Product> listItems() {
//        return productEjb.listAll();
//    }
//
//    @GET
//    @Path("{id}")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Product getProduct(@PathParam(value = "id") int id) {
//        return productEjb.findProduct(id);
//    }
//
//}
