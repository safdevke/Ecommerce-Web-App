package ejb;

import dao.GenericDaoI;
import model.Product;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class ProductEjb implements ProductEjbI {

    @Inject
    GenericDaoI dao;

    public List<Product> listAll() {
        dao.setClazz(Product.class);
        return dao.findAll();
    }

    public Product findProduct(int id) {
        dao.setClazz(Product.class);
        return (Product) dao.findById(id);
    }

}
