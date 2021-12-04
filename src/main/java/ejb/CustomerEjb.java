package ejb;

import dao.GenericDaoI;
import model.Customer;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class CustomerEjb {

    public final static String token = " ";

    @Inject
    GenericDaoI customerDao;

    public String getToken() {
        customerDao.setClazz(Customer.class);
        return "";
    }
}
