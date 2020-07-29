package ro.msg.learning.shop.strategy;


import lombok.RequiredArgsConstructor;
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

@RequiredArgsConstructor
public class MostAbundant implements LocationStrategy {

    private final ProductRepository productRepository;

    private final StockRepository stockRepository;

    private final LocationRepository locationRepository;


    @Override
    public List<ProductOrderedDto> selectLocation(OrderDto orderDto) throws Exception {
        List<ProductOrderedDto> productsOrdered = new ArrayList<>();

        List<Product> products = orderDto
                .getOrderDetails()
                .stream()
                .map(ord -> productRepository.findById(ord.getIdProduct()).get())
                .collect((Collectors.toList()));

        List<Location> locations = locationRepository.findAll();

        List<Stock> stocks = locations.stream()
                                      .map(stock-> stockRepository.findById(stock.getId()).get())
                                      .collect(Collectors.toList());


        for(Product product:products){
           if( stocks.stream()
                    .max(Comparator.comparing(Stock::getQuantity)).orElseThrow(NoSuchElementException::new)
                    .getProduct().getName()
                    .equals(product.getName())  ){

               Stock maxStock = stocks.stream().max(Comparator.comparing(Stock::getQuantity)).orElseThrow(Exception::new);

               ProductOrderedDto productOrderedDto =new ProductOrderedDto();
               productOrderedDto.setLocation(maxStock.getLocation());
               productOrderedDto.setProduct(product);
               productOrderedDto.setQuantity(maxStock.getQuantity());

               productsOrdered.add(productOrderedDto);
           }
        }
        return productsOrdered;
    }

}

