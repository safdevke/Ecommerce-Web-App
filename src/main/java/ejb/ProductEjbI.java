package ejb;

import model.Product;

import java.util.List;

public interface ProductEjbI {

    public List<Product> listAll();

    public Product findProduct(int id);
}
