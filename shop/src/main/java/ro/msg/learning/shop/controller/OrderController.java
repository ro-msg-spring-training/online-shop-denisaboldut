package ro.msg.learning.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.entity.Order;
import ro.msg.learning.shop.service.OrderService;

@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping(path = "/orders", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Order registerNewOrder(@RequestBody OrderDto orderDto){
        Order order = orderService.createOrder(orderDto);
        return order;
    }
}
