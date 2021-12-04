package events;

import utility.Consts;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.event.Observes;

@Stateless
public class TokenListener {

    public void getCurrentToken(@Observes String token) {
        Consts.token = token;
    }

    public void getCurrentCustomerId(@Observes Integer id) {
        Consts.customerId = id;
    }
}
