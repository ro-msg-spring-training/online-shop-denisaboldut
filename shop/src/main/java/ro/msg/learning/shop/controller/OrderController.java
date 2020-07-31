package ro.msg.learning.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.entity.Order;
import ro.msg.learning.shop.service.OrderService;

@RestController
@RequiredArgsConstructor
public class OrderController {

    public final OrderService orderService;

    @PostMapping(path = "/orders", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Order registerNewOrder(@RequestBody OrderDto orderDto){
        return orderService.createOrder(orderDto);
    }
}
