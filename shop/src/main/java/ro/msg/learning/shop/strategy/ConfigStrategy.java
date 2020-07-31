package ro.msg.learning.shop.strategy;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.shop.repository.LocationRepository;
import ro.msg.learning.shop.repository.ProductRepository;
import ro.msg.learning.shop.repository.StockRepository;

@Configuration
@RequiredArgsConstructor
public class ConfigStrategy {

    private final ProductRepository productRepository;

    private final StockRepository stockRepository;

    private final LocationRepository locationRepository;

    @Bean
    @ConditionalOnProperty(prefix = "location", name ="strategy", havingValue = "singleLocation")
    public LocationStrategy singleLocation(){
        return new SingleLocation(productRepository,stockRepository,locationRepository);
    }

    @Bean
    @ConditionalOnProperty(prefix = "location", name= "strategy" , havingValue = "mostAbundant")
    public LocationStrategy mostAbundant(){
        return new MostAbundant(productRepository,stockRepository,locationRepository);
    }

}
