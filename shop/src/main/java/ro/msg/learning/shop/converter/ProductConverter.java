package ro.msg.learning.shop.converter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dto.ProductDto;
import ro.msg.learning.shop.entity.Product;
import ro.msg.learning.shop.entity.ProductCategory;
import ro.msg.learning.shop.entity.Supplier;
import ro.msg.learning.shop.repository.ProductCategoryRepository;
import ro.msg.learning.shop.repository.SupplierRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class ProductConverter {

    private final SupplierRepository supplierRepository;

    private final ProductCategoryRepository productCategoryRepository;

    public ProductDto convertToDto(Product product){
        ProductDto productDto = new ProductDto();

        productDto.setIdProduct(product.getId());
        productDto.setProductName(product.getName());
        productDto.setProductDescription(product.getDescription());
        productDto.setProductWeight(product.getWeight());
        productDto.setProductPrice(product.getPrice());
        productDto.setProductImageUrl(product.getImageUrl());

        productDto.setCategoryDescription(product.getProductCategory().getDescription());
        productDto.setCategoryName(product.getProductCategory().getName());

        productDto.setSupplierName(product.getSupplier().getName());

        productDto.setIdCategory(product.getProductCategory().getId());
        productDto.setIdSupplier(product.getSupplier().getId());

        return productDto;
    }

    public List<ProductDto> convertAllToDto(List<Product> products){
        return products.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public Product convertToEntity(ProductDto productDto) {
        Product product = new Product();

        product.setId(productDto.getIdProduct());
        product.setName(productDto.getProductName());
        product.setDescription(productDto.getProductDescription());
        product.setWeight(productDto.getProductWeight());
        product.setPrice(productDto.getProductPrice());
        product.setImageUrl(productDto.getProductImageUrl());

        Optional<Supplier> supplierOptional;
        supplierOptional=supplierRepository.findById(productDto.getIdProduct());
        if(supplierOptional.isPresent()){
            Supplier supplier = supplierOptional.get();
            product.setSupplier(supplier);
        }

        Optional<ProductCategory> productCategoryOptional;
        productCategoryOptional = productCategoryRepository.findById(productDto.getIdCategory());
        if(productCategoryOptional.isPresent()){
            ProductCategory productCategory = productCategoryOptional.get();
            product.setProductCategory(productCategory);
        }

        return product;
    }
}
