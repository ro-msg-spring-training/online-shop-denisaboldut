package ro.msg.learning.shop.strategy;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigStrategy {

    @Bean
    @ConditionalOnProperty(prefix = "location", name ="strategy", havingValue = "singleLocation")
    public LocationStrategy singleLocation(){
        return new SingleLocation();
    }

    @Bean
    @ConditionalOnProperty(prefix = "location", name= "strategy" , havingValue = "mostAbundant")
    public LocationStrategy mostAbundant(){
        return new MostAbundant();
    }

}
