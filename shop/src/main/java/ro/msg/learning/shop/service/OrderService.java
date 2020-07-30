package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.dto.ProductOrderedDto;
import ro.msg.learning.shop.entity.Address;
import ro.msg.learning.shop.entity.Order;
import ro.msg.learning.shop.entity.OrderDetail;
import ro.msg.learning.shop.repository.LocationRepository;
import ro.msg.learning.shop.repository.OrderRepository;
import ro.msg.learning.shop.strategy.LocationStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final LocationStrategy locationStrategy;

    private final OrderRepository orderRepository;

    private final LocationRepository locationRepository;

    public Order createOrder(OrderDto orderDto) {
        Logger logger = Logger.getLogger(OrderService.class.getName());

        List<ProductOrderedDto> productOrderedDtoList = new ArrayList<>();

        try {
          productOrderedDtoList = locationStrategy.selectLocation(orderDto);
        }catch (NoSuchElementException e){
            logger.info("could not select location");
        }


        Order order = new Order();

        Address address =new Address();
        address.setCity(orderDto.getCity());
        address.setCountry(orderDto.getCountry());
        address.setStreetAddress(orderDto.getStreetAddress());
        order.setAddress(address);

        order.setCreatedAt(orderDto.getCreatedAt());

        List<OrderDetail> orderDetails = productOrderedDtoList
                                                .stream()
                                                .map(detail->new OrderDetail(order,detail.getProduct(),detail.getQuantity()))
                                                .collect(Collectors.toList());


        order.setOrderDetails(orderDetails);
        order.setLocation(locationRepository.findFirstByOrderByIdAsc());

        orderRepository.save(order);

        //update stock
        for(OrderDetail orderDetail:orderDetails){
            orderRepository.findById(order.getId()).get().getLocation().getStocks()
                    .forEach(stock -> stock.setQuantity(stock.getQuantity()-orderDetail.getQuantity()));
        }

    return order;
    }
}
