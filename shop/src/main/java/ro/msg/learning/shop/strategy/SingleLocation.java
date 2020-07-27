package ro.msg.learning.shop.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.dto.ProductOrderedDto;
import ro.msg.learning.shop.entity.Location;
import ro.msg.learning.shop.entity.Product;
import ro.msg.learning.shop.entity.Stock;
import ro.msg.learning.shop.repository.LocationRepository;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.repository.StockRepository;

import java.util.*;
import java.util.stream.Collectors;


public class SingleLocation implements LocationStrategy {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private LocationRepository locationRepository;


    @Override
    public List<ProductOrderedDto> selectLocation(OrderDto orderDto) throws Exception {
        List<ProductOrderedDto> productsOrdered = new ArrayList<>();

        List<Product> products = orderDto.getOrderDetails()
                                    .stream()
                                    .map(ord -> productRepository.findById(ord.getIdProduct()).get())
                                    .collect((Collectors.toList()));

        List<Location> locations = locationRepository.findAll();

        for (Product product : products) {
            for (Location location : locations) {

                List<Product> productsInStock = location.getStocks()
                                                            .stream()
                                                            .map(Stock::getProduct)
                                                            .collect(Collectors.toList());

                boolean containAll = productsInStock.containsAll(products);

                    if (containAll) {
                        ProductOrderedDto productOrderedDto = new ProductOrderedDto();
                        productOrderedDto.setProduct(product);
                        productOrderedDto.setLocation(location);

                        productsOrdered.add(productOrderedDto);
                    }else{
                        throw new Exception();
                    }
                }
            }
        return productsOrdered;
    }
}
