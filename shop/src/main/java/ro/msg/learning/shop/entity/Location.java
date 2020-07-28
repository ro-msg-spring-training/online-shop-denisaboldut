package ro.msg.learning.shop.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table (name = "location")
public class Location extends BaseEntity {

    private String name;

    @Embedded
    private Address address;

    @OneToMany (mappedBy = "location",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @Transient
    private List<Stock> stocks = new ArrayList<>();

    @OneToMany (mappedBy = "location",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @Transient
    private List<Order> orders=new ArrayList<>();

    @OneToMany (mappedBy = "location",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @Transient
    private List<Revenue> revenues = new ArrayList<>();

    public Location(){

    }

    public Location(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
}
