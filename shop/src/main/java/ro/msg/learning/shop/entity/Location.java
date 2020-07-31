package ro.msg.learning.shop.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"stocks","orders","revenues"}, callSuper = false)
@ToString(exclude = {"stocks","orders","revenues"})
@NoArgsConstructor
@AllArgsConstructor
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

}
