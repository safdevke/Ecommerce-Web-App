package ejb;

import dao.GenericDaoI;
import dto.RegisterResponse;
import model.Customer;
import model.Email;
import model.Secret;
import services.EmailServiceI;
import services.JwtTokenProviderI;
import utility.PasswordEncoderI;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;

@Stateless
public class RegisterEjb implements RegisterEjbI {

    @Inject
    GenericDaoI dao;

    @Inject
    PasswordEncoderI passwordEncoder;

    @Inject
    JwtTokenProviderI jwtTokenProvider;

    @Inject
    EmailServiceI emailSender;

    @Inject
    Event<String> tokenEvent;

    @Inject
    Event<Integer> custIdEvent;

    public RegisterResponse registerCustomer(Customer customer) {

        HashMap<String,String> items = new HashMap<>();
        items.put("email", customer.getEmail());

        List<Customer> result = dao.findByColumn(customer, items);

        if (!result.isEmpty()){
            return new RegisterResponse(false, false, "Customer already exists!");
        }

        customer = issueRegistrationToken(customer);

        try {
            sendMail(customer);
        } catch (Exception e) {
            e.printStackTrace();
            return new RegisterResponse(false);
        }
        return new RegisterResponse();
    }

    public RegisterResponse confirmRegistration(Customer customer) {
        return new RegisterResponse();
    }

    public void sendMail(Customer customer) {
        Email email = new Email();
        email.setMail(System.getenv("email"));
        email.setPassword(System.getenv("password"));
        email =  (Email) dao.update(email);
        emailSender.sendRegistrationEmail(email,customer);
    }

    public Customer issueRegistrationToken(Customer customer) {
        byte[] hashBytes= passwordEncoder.computeHash(customer.getPassword());
        customer.setPassword(new String(hashBytes));
        HashMap<String, Object> map =  jwtTokenProvider.issueToken(customer,true);
        tokenEvent.fire((String) map.get("token"));

        customer.setSecret((String) map.get("secret"));
        customer = (Customer) dao.update(customer);
        custIdEvent.fire(customer.getCustomerId());
        return customer;
    }



}
