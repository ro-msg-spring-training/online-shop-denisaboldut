package ro.msg.learning.shop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ro.msg.learning.shop.dto.OrderDetailDto;
import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.entity.*;
import ro.msg.learning.shop.repository.*;
import ro.msg.learning.shop.service.OrderService;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
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

    @BeforeEach
    public void setUp(){
        Customer customer = new Customer("Andrei","Pop","andreipop","andrei123","andreipop@yahoo.com",null);
        customerRepository.save(customer);

        Address address = new Address("Romania","Brasov","Tlc");
        Location location = new Location("Troc", address,null,null,null);


        Supplier supplier = new Supplier("SuplierName",null);


        ProductCategory productCategory = new ProductCategory("category","descriptCateg",null);


        Product product = new Product("prod","Descript",2.3,5.2,productCategory,supplier,"image_path",null,null);

        List<Product> products = new ArrayList<>();
        products.add(product);
        supplier.setProducts(products);
        supplierRepository.save(supplier);

        productCategory.setProducts(products);
        productCategoryRepository.save(productCategory);

        Stock stock = new Stock(product,location,45);

        List<Stock> stocks = new ArrayList<>();
        stocks.add(stock);
        location.setStocks(stocks);
        product.setStocks(stocks);
        productRepository.save(product);

        locationRepository.save(location);
        stockRepository.save(stock);
    }

    @Test
    void givenOrder_whenSave_thenGetOk() {

        Address address = new Address("Romania","Crs","Tlc");

        LocalDate date =LocalDate.of(2020, 7, 30);

        Location location = locationRepository.findFirstByOrderByIdAsc();
        Customer customer = customerRepository.findFirstByOrderByIdAsc();


        Order order = new Order(location,customer,date,address,null);

        orderRepository.save(order);

        if (orderRepository.findById(order.getId()).isPresent()){
            Order order1 = orderRepository.findById(order.getId()).get();
            assertEquals("Andrei",order1.getCustomer().getFirstName());
        }else {
            throw new NoSuchElementException("order not found");
        }
      }

      @Test
     void givenOrderDto_thenSave(){
        OrderDto orderDto = new OrderDto();

        orderDto.setCity("NCity");
        orderDto.setCountry("NCountry");
        orderDto.setStreetAddress("DStreet");

        orderDto.setCreatedAt(LocalDate.of(2020, 7, 30));

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
       void tryToCreateOrder_missingStock(){
          OrderDto orderDto = new OrderDto();

          orderDto.setCity("BCity");
          orderDto.setCountry("BCountry");
          orderDto.setStreetAddress("BStreet");
          orderDto.setCreatedAt(LocalDate.of(2020, 7, 30));

          OrderDetailDto orderDetailDto = new OrderDetailDto();
          orderDetailDto.setQuantity(3455);
          orderDetailDto.setIdProduct((long)33);
          List<OrderDetailDto> orderDetailDtoList = new ArrayList<>();
          orderDetailDtoList.add(orderDetailDto);

          orderDto.setOrderDetails(orderDetailDtoList);

          Order orderSaved = orderService.createOrder(orderDto);

          Address address = new Address("Romania","Brasov","Tlc");
          Location location = new Location("Troc", address,null,null,null);
          locationRepository.save(location);

          Supplier supplier = new Supplier("SuplierName",null);
          supplierRepository.save(supplier);

          ProductCategory productCategory = new ProductCategory("category","descriptCateg",null);
          productCategoryRepository.save(productCategory);

          Product product = new Product("prod","Descript",2.3,5.2,productCategory,supplier,"image_path",null,null);
          productRepository.save(product);

          Stock stock = new Stock(product,location,45);
          List<Stock> stocks = new ArrayList<>();
          stocks.add(stock);
          stockRepository.save(stock);

          if(locationRepository.findById(location.getId()).isPresent()) {
              locationRepository.findById(location.getId()).get().setStocks(stocks);
              orderSaved.getLocation().setStocks(stocks);
          }else {
              throw new NoSuchElementException("location not found");
          }

          assertThat(orderSaved.getLocation().getStocks().get(0).getQuantity()).isGreaterThan(orderDetailDto.getQuantity());

      }
}
