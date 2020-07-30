package ro.msg.learning.shop.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@EqualsAndHashCode(exclude = "orders", callSuper = false)
@ToString(exclude = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer")
public class Customer extends BaseEntity {

    private String firstName;

    private String lastName;

    private String username;

    private String password;

    private String emailAddress;

    @OneToMany
    private List<Order> orders;

}
