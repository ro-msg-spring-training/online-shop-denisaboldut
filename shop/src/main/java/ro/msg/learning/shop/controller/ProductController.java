package ro.msg.learning.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.converter.ProductConverter;
import ro.msg.learning.shop.dto.ProductDto;
import ro.msg.learning.shop.entity.Product;
import ro.msg.learning.shop.service.ProductService;
import org.springframework.http.MediaType;

import java.util.List;


@RestController
public class ProductController {

   @Autowired
    ProductService productService;

   @Autowired
   ProductConverter productConverter;

   @GetMapping("/products/{id}")
   ProductDto getProduct(@PathVariable Long id){
       Product product= productService.findProductById(id);
       if(product==null){
        throw new IllegalArgumentException();
       }
       return productConverter.convertToDto(product);
   }

   @GetMapping("/products")
    List<ProductDto> getProducts(){
       List<Product> products = (List<Product>) productService.findProducts();
       return productConverter.convertAllToDto(products);
   }

    @PostMapping(path = "/products", consumes = MediaType.APPLICATION_JSON_VALUE)
    void addNewProduct( @RequestBody ProductDto productDto){
       Product product = productConverter.convertToEntity(productDto);
       productService.createProduct(product);
   }

   @DeleteMapping("/products/{id}")
   void deleteProductById(@PathVariable Long id){
       Product product= productService.findProductById(id);
       if(product == null){
           throw new NullPointerException();
       }
       productService.deleteProduct(product);
   }

}
