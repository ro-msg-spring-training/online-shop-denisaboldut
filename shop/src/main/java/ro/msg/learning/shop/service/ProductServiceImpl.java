package ro.msg.learning.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entity.Product;
import ro.msg.learning.shop.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findProducts() {
         return productRepository.findAll();
    }

    public Product findProductById(Long id){
        Optional<Product> optionalProduct;
         optionalProduct =  productRepository.findById(id);
        return optionalProduct.orElse(null);
    }

    public Product createProduct(Product product){
         return productRepository.save(product);
    }

    public void deleteProduct(Product product){
         productRepository.delete(product);
    }

    public boolean updateProduct(Product product){
        Optional<Product> optionalProduct;
        optionalProduct = productRepository.findById(product.getId());

        if(optionalProduct.isPresent()){
           Product productFromDb = optionalProduct.get();

           productFromDb.setDescription(product.getDescription());
           productFromDb.setImageUrl(product.getImageUrl());
           productFromDb.setName(product.getName());
           productFromDb.setPrice(product.getPrice());
           productFromDb.setWeight(product.getWeight());

           productRepository.save(productFromDb);
           return true;
        }else
            return false;
    }




}
