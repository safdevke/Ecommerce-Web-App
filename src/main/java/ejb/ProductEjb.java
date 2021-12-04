package ejb;

import dao.BaseDaoI;
import model.Product;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class ProductEjb implements ProductEjbI {

    @Inject
    BaseDaoI productDao;

    public List<Product> listAll() {
        productDao.setClazz(Product.class);
        return productDao.findAll();
    }

    public Product findProduct(int id) {
        productDao.setClazz(Product.class);
        return (Product) productDao.findById(id);
    }


}
