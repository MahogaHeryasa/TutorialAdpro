package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.List;

public interface ProductService {
    public Product create(Product product);
    public List<Product> findAll();
    public void delete(String productId);
    public Product findById(String productId);
    public void update(Product product, String productName, int productQuantity);

}
