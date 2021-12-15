package ejb;

import dao.GenericDaoI;
import dto.GenericResponse;
import dto.RegisterResponse;
import model.Customer;
import model.Email;
import model.Secret;
import services.EmailServiceI;
import services.JwtTokenProviderI;
import utility.Consts;
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
        List<Customer> result = findCustomer(customer);

        if (!result.isEmpty()){
            return new RegisterResponse(false, false, "User already exists!");
        }

        customer = issueToken(customer);

        try {
            sendMail(customer);
        } catch (Exception e) {
            e.printStackTrace();
            return new RegisterResponse(false);
        }
        return new RegisterResponse("Registration successful. Please check your email to confirm your account");
    }

    public void sendMail(Customer customer) {
//        Email email = new Email();
//        email.setMail(System.getenv("email"));
//        email.setPassword(System.getenv("password"));
//        email =  (Email) dao.update(email);
        dao.setClazz(Email.class);
        Email email =  (Email) dao.findById(1); //
        emailSender.sendRegistrationEmail(email,customer);
    }

    public Customer issueToken(Customer customer) {
        byte[] hashBytes= passwordEncoder.computeHash(customer.getPassword());
        customer.setPassword(new String(hashBytes));
        HashMap<String, Object> map =  jwtTokenProvider.issueToken(customer,true);

        tokenEvent.fire((String) map.get("token"));

        customer.setSecret((byte[]) map.get("secret"));
        customer = (Customer) dao.update(customer);
        custIdEvent.fire(customer.getCustomerId());
        return customer;
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

    public List<Customer> findCustomer(Customer customer) {
        HashMap<String,String> items = new HashMap<>();
        items.put("email", customer.getEmail());

        List<Customer> result = dao.findByColumn(customer, items);
        return result;
    }

}
