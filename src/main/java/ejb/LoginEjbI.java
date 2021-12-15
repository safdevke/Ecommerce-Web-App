package ejb;

import dto.LoginResponse;
import model.Customer;

import java.util.Map;

public interface LoginEjbI {

    LoginResponse validateLoginCredentials(Customer customer);
}
