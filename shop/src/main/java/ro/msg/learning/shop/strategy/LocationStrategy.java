package ro.msg.learning.shop.strategy;

import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.dto.ProductOrderedDto;

import java.util.List;

public interface LocationStrategy {
    List<ProductOrderedDto> selectLocation(OrderDto orderDto) throws Exception;
}
