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
import ro.msg.learning.shop.service.OrderServiceImpl;
import ro.msg.learning.shop.strategy.LocationStrategy;
import ro.msg.learning.shop.strategy.MostAbundant;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
	private OrderServiceImpl orderService;

	@Mock
	private LocationRepository locationRepository;

	@Mock
	private ProductRepository productRepository;

	@Mock
	private LocationStrategy locationStrategy;

	@Mock
	private MostAbundant mostAbundant;


	@Test
	void singleLocationStrategy() throws ParseException {
		OrderDto orderDto = new OrderDto();
		OrderDetailDto orderDetailDto = new OrderDetailDto();
		List<OrderDetailDto> orderDetailDtoList = new ArrayList<>();
		List<Location> locations = new ArrayList<>();
		List<Order> orders = new ArrayList<>();
		List<Stock> stocks = new ArrayList<>();

		String sDate="28/07/2020";
		Date date=new SimpleDateFormat("dd/MM/yyyy").parse(sDate);

		orderDto.setStreetAddress("street");
		orderDto.setCreatedAt(date);
		orderDto.setCountry("Romania");
		orderDto.setCity("Trd");

		orderDetailDto.setIdProduct((long) 1);
		orderDetailDto.setQuantity(100);
		orderDetailDtoList.add(orderDetailDto);
		orderDto.setOrderDetails(orderDetailDtoList);

		Address address = new Address("Romania","Brasov","TRt");

		Location location = new Location("A",address);

		Customer customer = new Customer("Andrei","Pop","andreipop","andrei123","andreipop@yahoo.com");

		Order order = new Order(location,customer,date,address);
		orders.add(order);
		location.setOrders(orders);

		Product product = new Product("ad","description",2.3,4.6,null,null,"imagepath");

		Stock stock = new Stock(product,location,234);
		stocks.add(stock);
		location.setStocks(stocks);

		locations.add(location);

		//when(locationRepository.findAll()).thenReturn(locations);
		//when(productRepository.findById((long) 1)).thenReturn(java.util.Optional.of(product));
		when(locationRepository.findFirstByOrderByIdAsc()).thenReturn(location);

		Order createdOrder= orderService.createOrder(orderDto);

		assertThat(createdOrder.getLocation()).isNotNull();

	}

	@Test
	void mostAbundantStrategy() throws Exception {

		OrderDto orderDto = new OrderDto();
		OrderDetailDto orderDetailDto = new OrderDetailDto();
		List<OrderDetailDto> orderDetailDtoList = new ArrayList<>();
		List<Location> locations = new ArrayList<>();
		List<Order> orders = new ArrayList<>();
		List<Stock> stocks = new ArrayList<>();

		String sDate="28/07/2020";
		Date date=new SimpleDateFormat("dd/MM/yyyy").parse(sDate);

		orderDto.setStreetAddress("street");
		orderDto.setCreatedAt(date);
		orderDto.setCountry("Romania");
		orderDto.setCity("Trd");

		orderDetailDto.setIdProduct((long) 1);
		orderDetailDto.setQuantity(100);
		orderDetailDtoList.add(orderDetailDto);
		orderDto.setOrderDetails(orderDetailDtoList);

		List<ProductOrderedDto> productsOrdered = mostAbundant.selectLocation(orderDto);

		assertThat(productsOrdered.stream().map(ProductOrderedDto::getLocation).collect(Collectors.toList())).isNotNull();
	}

}
