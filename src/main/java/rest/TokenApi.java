package rest;

import dto.RegisterResponse;
import ejb.TokenEjbI;
import model.Customer;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import java.awt.*;

@Path("/token")
public class TokenApi {

    @EJB
    TokenEjbI tokenEjb;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public RegisterResponse newToken(Customer customer) {
        return tokenEjb.generateNewToken(customer);
    }

}
