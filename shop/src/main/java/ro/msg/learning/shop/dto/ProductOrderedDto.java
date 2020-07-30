package ro.msg.learning.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ro.msg.learning.shop.entity.Location;
import ro.msg.learning.shop.entity.Product;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductOrderedDto {
    private Product product;

    private Location location;

    private int quantity;
}
