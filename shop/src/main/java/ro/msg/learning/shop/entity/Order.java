package ro.msg.learning.shop.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(exclude = "orderDetails", callSuper = false)
@ToString(exclude = "orderDetails")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="order_shop")
public class Order extends BaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="shipped_from")
    private Location location;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="customer")
    private Customer customer;

    private LocalDate createdAt;

    @Embedded
    private Address address;

    @OneToMany (mappedBy = "orderShop",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @Transient
    private List<OrderDetail> orderDetails = new ArrayList<>();

}
