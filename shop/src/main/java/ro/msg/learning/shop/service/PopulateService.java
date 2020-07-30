package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entity.*;
import ro.msg.learning.shop.repository.*;

import java.time.LocalDate;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class PopulateService {

    private final OrderRepository orderRepository;

    private final CustomerRepository customerRepository;

    private final LocationRepository locationRepository;

    private final ProductRepository productRepository;

    private final StockRepository stockRepository;

    private final SupplierRepository supplierRepository;

    private final ProductCategoryRepository productCategoryRepository;

    private final OrderDetailRepository orderDetailRepository;

    private final RevenueRepository revenueRepository;


    public void populateDatabase() {
        Customer customer = new Customer("Dan","Pop","danpop","dan123","danpop@yahoo.com",null);
        customerRepository.save(customer);

        Address address = new Address("DCountry","BCity","Tlc");
        Location location = new Location("Troc", address,null,null,null);
        locationRepository.save(location);

        Supplier supplier = new Supplier("SuplierName",null);
        supplierRepository.save(supplier);

        ProductCategory productCategory = new ProductCategory("category","descriptCateg",null);
        productCategoryRepository.save(productCategory);

        Product product = new Product("prod","Descript",2.3,5.2,productCategory,supplier,"image_path",null,null);
        productRepository.save(product);

        Order order = new Order(location,customer, LocalDate.of(2020, 7, 30),address,null);
        orderRepository.save(order);

        OrderDetail orderDetail= new OrderDetail(order,product,34);
        orderDetailRepository.save(orderDetail);

        Stock stock = new Stock(product,location,35);
        stockRepository.save(stock);

        Revenue revenue = new Revenue(location,new Date(),345.5);
        revenueRepository.save(revenue);
    }

    public void deleteData(){

       locationRepository.deleteAll();
       supplierRepository.deleteAll();
       productCategoryRepository.deleteAll();
       productRepository.deleteAll();
       orderDetailRepository.deleteAll();
       orderRepository.deleteAll();
       stockRepository.deleteAll();
       revenueRepository.deleteAll();
    }
}
