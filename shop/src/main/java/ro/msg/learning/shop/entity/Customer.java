package ro.msg.learning.shop.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
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

    public Customer(){

    }

    public Customer(String firstName, String lastName, String username, String password, String emailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
    }
}
