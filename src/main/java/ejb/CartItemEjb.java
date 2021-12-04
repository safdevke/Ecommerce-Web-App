package ejb;

import dao.BaseDaoI;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class CartItemEjb {

    @Inject
    BaseDaoI cartItemDao;

    
}
