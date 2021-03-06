package rest;

import dto.RegisterResponse;
import ejb.RegisterEjbI;
import model.Customer;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/register")
public class RegisterApi {

    @EJB
    RegisterEjbI registerEjb;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public RegisterResponse createCustomer(Customer customer) {
        return registerEjb.registerCustomer(customer);
    }
}
