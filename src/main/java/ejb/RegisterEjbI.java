package ejb;

import dto.RegisterResponse;
import model.Customer;

public interface RegisterEjbI {

    RegisterResponse registerCustomer(Customer customer);

    RegisterResponse confirmRegistration(Customer customer);

    void sendMail(Customer customer);

    Customer issueRegistrationToken(Customer customer);
}
