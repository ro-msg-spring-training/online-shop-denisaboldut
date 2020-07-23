package ro.msg.learning.shop;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.shop.entity.Product;
import ro.msg.learning.shop.entity.ProductCategory;
import ro.msg.learning.shop.entity.Revenue;
import ro.msg.learning.shop.entity.Supplier;
import ro.msg.learning.shop.repository.ProductCategoryRepository;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.repository.SupplierRepository;

//testing database insertion
@Configuration
class LoadDataBase {

    private static final Logger log = LoggerFactory.getLogger(LoadDataBase.class);

    @Bean
    CommandLineRunner initDatabase(ProductCategoryRepository productCategoryRepository, SupplierRepository supplierRepository, ProductRepository productRepository) {

        return args -> {
            log.info("Preloading " + productCategoryRepository.save(new ProductCategory("Bilbo Baggins", "burglar")));
            log.info("Preloading " + productCategoryRepository.save(new ProductCategory("Frodo Baggins", "thief")));

            Supplier supplier = new Supplier("supplier");
            supplierRepository.save(supplier);
            ProductCategory productCategory=new ProductCategory("s","desc");
            productCategoryRepository.save(productCategory);
            Product product = new Product("s","desc",2.34,1.34,productCategory,supplier,"imgurl");
            productRepository.save(product);

        };


    }
}