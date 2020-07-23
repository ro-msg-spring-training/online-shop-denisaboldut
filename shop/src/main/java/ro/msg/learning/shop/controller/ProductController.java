package ro.msg.learning.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.converter.ProductConverter;
import ro.msg.learning.shop.dto.ProductDto;
import ro.msg.learning.shop.entity.Product;
import ro.msg.learning.shop.service.ProductService;
import ro.msg.learning.shop.service.ProductServiceImpl;



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
}
