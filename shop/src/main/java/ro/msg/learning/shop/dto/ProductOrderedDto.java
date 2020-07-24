package ro.msg.learning.shop.dto;

import lombok.Data;
import ro.msg.learning.shop.entity.Location;
import ro.msg.learning.shop.entity.Product;

@Data
public class ProductOrderedDto {
    private Product product;

    private Location location;

    private int quantity;
}
