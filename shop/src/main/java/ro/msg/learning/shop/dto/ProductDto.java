package ro.msg.learning.shop.dto;

import lombok.Data;

@Data
public class ProductDto {

    private Long idProduct;

    private Long idSupplier;

    private Long idCategory;

    private String productName;

    private String productDescription;

    private double productPrice;

    private double productWeight;

    private String productImageUrl;

    private String supplierName;

    private String categoryName;

    private String categoryDescription;

}
