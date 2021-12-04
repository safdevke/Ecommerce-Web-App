package rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("/customer")
public class CustomerApi {


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void registerCustomer() {

    }
}
