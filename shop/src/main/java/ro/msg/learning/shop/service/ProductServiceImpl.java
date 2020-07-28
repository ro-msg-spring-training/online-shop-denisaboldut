package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entity.Product;
import ro.msg.learning.shop.repository.ProductRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public List<Product> findProducts() {
         return productRepository.findAll();
    }

    public Product findProductById(Long id){
        Optional<Product> optionalProduct;
         optionalProduct =  productRepository.findById(id);
        return optionalProduct.orElse(null);
    }

    @Transactional
    public Product createProduct(Product product){
         return productRepository.save(product);
    }

    @Transactional
    public void deleteProduct(Product product){
        productRepository.delete(product);
    }

    @Transactional
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
