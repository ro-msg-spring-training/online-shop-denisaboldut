package ro.msg.learning.shop.dto;

import lombok.Data;

@Data
public class OrderDetailDto {

    private Long idProduct;

    private int quantity;
}
