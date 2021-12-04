package ejb;

import dao.GenericDaoI;
import dto.GenericResponse;
import dto.RegisterResponse;
import io.jsonwebtoken.JwtException;
import model.Customer;
import model.Secret;
import services.JwtTokenProviderI;
import utility.Consts;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

@Stateless
public class TokenEjb implements TokenEjbI {

    @EJB
    RegisterEjbI registerEjb;

    @Inject
    GenericDaoI dao;

    @Inject
    JwtTokenProviderI jwtTokenProvider;

    @Inject
    Event<String> tokenEvent;

    @Inject
    Event<Integer> custIdEvent;


    public RegisterResponse generateNewToken(Customer customer) {

        HashMap<String, String> map = new HashMap<>();
        map.put("email", customer.getEmail());

        List<Customer> result = dao.findByColumn(customer, map);

        if (result.isEmpty()) {
            return new RegisterResponse(true, false, "Customer doesn't exist! Please register first");
        }

        customer = registerEjb.issueRegistrationToken(customer);
        registerEjb.sendMail(customer);

        return new RegisterResponse(true,true,"New token generated. Check your email");
    }

    public GenericResponse validateRegistrationToken() {
        dao.setClazz(Customer.class);
        Customer customer = (Customer) dao.findById(Consts.customerId);
        if (!jwtTokenProvider.validateToken(customer)) {
            return new GenericResponse(false, "Token Expired");
        }
        customer.setAccountEnabled(true);
        dao.update(customer);
        return new GenericResponse();
    }

}
