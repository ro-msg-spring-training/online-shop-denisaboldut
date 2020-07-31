package ro.msg.learning.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
