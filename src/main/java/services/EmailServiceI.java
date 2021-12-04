package services;

import model.Customer;
import model.Email;

public interface EmailServiceI {

    void sendRegistrationEmail(Email email, Customer customer);
}
