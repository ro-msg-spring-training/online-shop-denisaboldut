package ro.msg.learning.shop.service;

import ro.msg.learning.shop.entity.Product;

import java.util.Collection;

public interface ProductService {
    public abstract Product createProduct(Product product);
    public abstract boolean updateProduct(Product product);
    public abstract void deleteProduct(Product product);
    public abstract Collection<Product> findProducts();
    public abstract Product findProductById(Long id);
}
