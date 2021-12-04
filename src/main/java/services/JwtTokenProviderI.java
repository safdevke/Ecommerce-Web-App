package services;

import model.Customer;
import model.Secret;

import java.util.HashMap;

public interface JwtTokenProviderI {

    HashMap<String,Object> issueToken(Customer customer, boolean newCustomer);

    boolean validateToken(Customer customer);

}
