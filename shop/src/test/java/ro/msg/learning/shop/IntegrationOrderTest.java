package ro.msg.learning.shop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import ro.msg.learning.shop.config.OrderJpaConfig;
import ro.msg.learning.shop.entity.*;
import ro.msg.learning.shop.repository.*;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@ContextConfiguration(
        classes = {OrderJpaConfig.class},
        loader = AnnotationConfigContextLoader.class
)
@Transactional
@ActiveProfiles("test")
public class IntegrationOrderTest {

    @Resource
    private OrderRepository orderRepository;


    @Test
    public void givenOrder_whenSave_thenGetOk() throws ParseException {
        Customer customer = new Customer("Andrei","Pop","andreipop","andrei123","andreipop@yahoo.com");
        Address address = new Address("Romania","Brasov","Tlc");
        Location location = new Location("Troc", address);
        String sDate="28/07/2020";
        Date date=new SimpleDateFormat("dd/MM/yyyy").parse(sDate);

        Order order = new Order(location,customer,date,address);

        orderRepository.save(order);

        Order order1 = orderRepository.findById(order.getId()).get();
        assertEquals("Andrei",order1.getCustomer().getFirstName());
    }
}
