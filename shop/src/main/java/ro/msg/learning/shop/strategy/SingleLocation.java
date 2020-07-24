package ro.msg.learning.shop.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dto.OrderDto;
import ro.msg.learning.shop.dto.ProductOrderedDto;
import ro.msg.learning.shop.entity.Location;
import ro.msg.learning.shop.entity.Product;
import ro.msg.learning.shop.entity.Stock;
import ro.msg.learning.shop.repository.LocationRepository;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.repository.StockRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class SingleLocation implements LocationStrategy {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private LocationRepository locationRepository;


    @Override
    public List<ProductOrderedDto> selectLocation(OrderDto orderDto) {
        List<ProductOrderedDto> productsOrdered = new ArrayList<>();

        List<Optional<Product>> products = orderDto.getOrderDetails().stream().map(ord -> productRepository.findById(ord.getIdProduct())).collect((Collectors.toList()));

        List<Location> locations = locationRepository.findAll();
        //locations.stream().map(loc-> loc.getStocks().stream().map(stock -> stock.getProduct().getId().equals( products.stream().map(pr->pr.get().getId()))).collect(Collectors.toList());

        for (Optional<Product> product : products) {
            for (Location location : locations) {
                for (Stock stock : location.getStocks()) {
                    if (stock.getProduct().getId().equals(product.get().getId())) {
                        ProductOrderedDto productOrderedDto = new ProductOrderedDto();
                        productOrderedDto.setProduct(product.get());
                        productOrderedDto.setLocation(location);
                        productsOrdered.add(productOrderedDto);
                    }
                }
            }
        }
        return productsOrdered;
    }
}
