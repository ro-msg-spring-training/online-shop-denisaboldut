package ro.msg.learning.shop.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="order_shop")
public class Order extends BaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id", insertable = false, updatable = false)
    private Location location;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id",insertable = false, updatable = false)
    private Customer customer;

    private Date createdAt;

    @Embedded
    private Address address;

    @OneToMany (mappedBy = "orderShop",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<OrderDetail> orderDetails = new ArrayList<>();

    public Order(){

    }

    public Order(Location location, Customer customer, Date createdAt, Address address) {
        this.location = location;
        this.customer = customer;
        this.createdAt = createdAt;
        this.address = address;
    }
}
