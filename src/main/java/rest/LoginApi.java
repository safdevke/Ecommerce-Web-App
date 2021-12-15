package rest;

import dto.LoginResponse;
import ejb.LoginEjbI;
import model.Customer;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.awt.*;

@Path("/login")
public class LoginApi {

    @EJB
    LoginEjbI loginEjb;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public LoginResponse loginCustomer(Customer customer) {
        return loginEjb.validateLoginCredentials(customer);
    }

}
