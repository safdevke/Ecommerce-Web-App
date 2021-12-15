package ejb;

import dao.GenericDaoI;
import dto.LoginResponse;
import model.Customer;
import utility.Consts;
import utility.PasswordEncoderI;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class LoginEjb implements LoginEjbI {

    @Inject
    GenericDaoI dao;

    @Inject
    PasswordEncoderI passwordEncoder;

    @EJB
    RegisterEjbI registerEjb;

    public LoginResponse validateLoginCredentials(Customer customer) {
        List<Customer> result = registerEjb.findCustomer(customer);

        if (result.isEmpty()) {
            return new LoginResponse(false, "User doesn't exist! Please register first");
        }

        Customer existingCustomer = result.get(0);

        if (!existingCustomer.isAccountEnabled()) {
            return new LoginResponse(false, "Please activate your account first!");
        }

        if (!passwordEncoder.compare(customer.getPassword(), existingCustomer.getPassword().getBytes())) {
             return new LoginResponse(false, "Incorrect password entered");
        }

        customer = registerEjb.issueToken(customer);

        return new LoginResponse(Consts.token);
    }
}
