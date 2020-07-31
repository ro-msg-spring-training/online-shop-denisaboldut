package ro.msg.learning.shop;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ro.msg.learning.shop.dto.OrderDetailDto;
import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.dto.ProductOrderedDto;
import ro.msg.learning.shop.entity.*;
import ro.msg.learning.shop.repository.LocationRepository;
import ro.msg.learning.shop.repository.OrderRepository;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.service.OrderService;
import ro.msg.learning.shop.strategy.LocationStrategy;
import ro.msg.learning.shop.strategy.MostAbundant;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RegisterOrderTests {

	@Mock
	private OrderRepository orderRepository;

	@InjectMocks
	private OrderService orderService;

	@Mock
	private LocationRepository locationRepository;

	@Mock
	private ProductRepository productRepository;

	@Mock
	private LocationStrategy locationStrategy;

	@Mock
	private MostAbundant mostAbundant;


	@Test
	void singleLocationStrategy()  {
		OrderDto orderDto = new OrderDto();
		OrderDetailDto orderDetailDto = new OrderDetailDto();
		List<OrderDetailDto> orderDetailDtoList = new ArrayList<>();
		List<Location> locations = new ArrayList<>();
		List<Order> orders = new ArrayList<>();
		List<Stock> stocks = new ArrayList<>();

		LocalDate date = LocalDate.of(2020, 7, 30);

		orderDto.setStreetAddress("street");
		orderDto.setCreatedAt(date);
		orderDto.setCountry("Romania");
		orderDto.setCity("Trd");

		orderDetailDto.setQuantity(100);
		orderDetailDtoList.add(orderDetailDto);
		orderDto.setOrderDetails(orderDetailDtoList);

		Address address = new Address("Romania","Brasov","TRt");

		Location location = new Location("A",address,null,null,null);

		Customer customer = new Customer("Andrei","Pop","andreipop","andrei123","andreipop@yahoo.com",null);

		Order order = new Order(location,customer,date,address,null);
		orders.add(order);
		location.setOrders(orders);

		Product product = new Product("ad","description",2.3,4.6,null,null,"imagepath",null,null);

		Stock stock = new Stock(product,location,234);
		stocks.add(stock);
		location.setStocks(stocks);

		locations.add(location);

		when(locationRepository.findFirstByOrderByIdAsc()).thenReturn(location);

		Order createdOrder= orderService.createOrder(orderDto);

		assertThat(createdOrder.getLocation()).isNotNull();

	}

	@Test
	void mostAbundantStrategy(){

		OrderDto orderDto = new OrderDto();
		OrderDetailDto orderDetailDto = new OrderDetailDto();
		List<OrderDetailDto> orderDetailDtoList = new ArrayList<>();

		LocalDate date = LocalDate.of(2020, 7, 30);

		orderDto.setStreetAddress("street");
		orderDto.setCreatedAt(date);
		orderDto.setCountry("Romania");
		orderDto.setCity("Trd");

		orderDetailDto.setQuantity(100);
		orderDetailDtoList.add(orderDetailDto);
		orderDto.setOrderDetails(orderDetailDtoList);

		List<ProductOrderedDto> productsOrdered = mostAbundant.selectLocation(orderDto);

		assertThat(productsOrdered.stream().map(ProductOrderedDto::getLocation).collect(Collectors.toList())).isNotNull();
	}

}
