package ro.msg.learning.shop.service;

import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.entity.Order;

public interface OrderService {
    public abstract Order createOrder(OrderDto orderDto);
}
