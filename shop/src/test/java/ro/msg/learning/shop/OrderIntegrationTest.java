package ro.msg.learning.shop;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ro.msg.learning.shop.dto.OrderDetailDto;
import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.entity.*;
import ro.msg.learning.shop.repository.*;
import ro.msg.learning.shop.service.OrderService;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Profile("test")
public class OrderIntegrationTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Before
    public void setUp(){
        Customer customer = new Customer("Andrei","Pop","andreipop","andrei123","andreipop@yahoo.com");
        customerRepository.save(customer);

        Address address = new Address("Romania","Brasov","Tlc");
        Location location = new Location("Troc", address);


        Supplier supplier = new Supplier("SuplierName");
        supplierRepository.save(supplier);

        ProductCategory productCategory = new ProductCategory("category","descriptCateg");
        productCategoryRepository.save(productCategory);

        Product product = new Product("prod","Descript",2.3,5.2,productCategory,supplier,"image_path");
        productRepository.save(product);

        Stock stock = new Stock(product,location,45);

        List<Stock> stocks = new ArrayList<>();
        stocks.add(stock);
        location.setStocks(stocks);

        locationRepository.save(location);
        stockRepository.save(stock);
    }

    @Test
    public void givenOrder_whenSave_thenGetOk() {

        Address address = new Address("Romania","Crs","Tlc");

        Date date = new Date(Date.parse("29/07/2020"));

        Location location = locationRepository.findFirstByOrderByIdAsc();
        Customer customer = customerRepository.findFirstByOrderByIdAsc();

        Order order = new Order(location,customer,date,address);

        orderRepository.save(order);

        Order order1 = orderRepository.findById(order.getId()).get();

        assertEquals("Dan",order1.getCustomer().getFirstName());
      }

      @Test
     public void givenOrderDto_thenSave(){
        OrderDto orderDto = new OrderDto();

        orderDto.setCity("NCity");
        orderDto.setCountry("NCountry");
        orderDto.setStreetAddress("DStreet");
        orderDto.setCreatedAt(new Date(Date.parse("29/07/2020")));

          OrderDetailDto orderDetailDto = new OrderDetailDto();
          orderDetailDto.setQuantity(23);
          orderDetailDto.setIdProduct((long)33);
          List<OrderDetailDto> orderDetailDtoList = new ArrayList<>();
          orderDetailDtoList.add(orderDetailDto);

          orderDto.setOrderDetails(orderDetailDtoList);

          Order orderSaved = orderService.createOrder(orderDto);

          assertEquals(orderSaved.getAddress().getCity(),orderDto.getCity());

      }
      @Test
    public void tryToCreateOrder_missingStock(){
          OrderDto orderDto = new OrderDto();

          orderDto.setCity("BCity");
          orderDto.setCountry("BCountry");
          orderDto.setStreetAddress("BStreet");
          orderDto.setCreatedAt(new Date(Date.parse("29/07/2020")));

          OrderDetailDto orderDetailDto = new OrderDetailDto();
          orderDetailDto.setQuantity(3455);
          orderDetailDto.setIdProduct((long)33);
          List<OrderDetailDto> orderDetailDtoList = new ArrayList<>();
          orderDetailDtoList.add(orderDetailDto);

          orderDto.setOrderDetails(orderDetailDtoList);

          Order orderSaved = orderService.createOrder(orderDto);

          Address address = new Address("Romania","Brasov","Tlc");
          Location location = new Location("Troc", address);
          locationRepository.save(location);

          Supplier supplier = new Supplier("SuplierName");
          supplierRepository.save(supplier);

          ProductCategory productCategory = new ProductCategory("category","descriptCateg");
          productCategoryRepository.save(productCategory);

          Product product = new Product("prod","Descript",2.3,5.2,productCategory,supplier,"image_path");
          productRepository.save(product);

          Stock stock = new Stock(product,location,45);
          List<Stock> stocks = new ArrayList<>();
          stocks.add(stock);
          stockRepository.save(stock);
          locationRepository.findById(location.getId()).get().setStocks(stocks);
          orderSaved.getLocation().setStocks(stocks);

          assertThat(orderSaved.getLocation().getStocks().get(0).getQuantity()).isGreaterThan(orderDetailDto.getQuantity());

      }
}
