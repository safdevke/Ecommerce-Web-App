package ejb;

import dto.GenericResponse;
import dto.RegisterResponse;
import model.Customer;

public interface TokenEjbI {

    RegisterResponse generateNewToken(Customer customer);

    GenericResponse validateCustomerToken(String token);

}
