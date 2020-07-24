package ro.msg.learning.shop.dto;

import lombok.Data;
import ro.msg.learning.shop.entity.Address;
import ro.msg.learning.shop.entity.Customer;
import ro.msg.learning.shop.entity.Location;
import ro.msg.learning.shop.entity.OrderDetail;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class OrderDto {

    private Date createdAt;

    //delivery address
    private String country;

    private String city;

    private String streetAddress;
    //

    private List<OrderDetailDto> orderDetails;

}
