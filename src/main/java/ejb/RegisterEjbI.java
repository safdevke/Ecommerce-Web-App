package ejb;

import dto.GenericResponse;
import dto.RegisterResponse;
import model.Customer;

import java.util.List;

public interface RegisterEjbI {

    RegisterResponse registerCustomer(Customer customer);

    GenericResponse validateRegistrationToken();

    void sendMail(Customer customer);

    Customer issueToken(Customer customer);

    List<Customer> findCustomer(Customer customer);
}
