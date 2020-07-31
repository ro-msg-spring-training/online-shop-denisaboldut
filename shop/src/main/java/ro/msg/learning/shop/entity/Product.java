package ro.msg.learning.shop.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Data
@EqualsAndHashCode(exclude = {"stocks","orderDetails"}, callSuper = false)
@ToString(exclude = {"stocks","orderDetails"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product extends BaseEntity {

    private String name;

    private String description;

    private double price;

    private double weight;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="category")
    private ProductCategory productCategory;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="supplier")
    private Supplier supplier;

    @Column(name = "image_url")
    private String imageUrl;


    @OneToMany (mappedBy = "product",
                 fetch = FetchType.LAZY,
                 cascade = CascadeType.ALL,
                 orphanRemoval = true)
    @Transient
    private List<Stock> stocks;


    @OneToMany (mappedBy = "product",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @Transient
    private List<OrderDetail> orderDetails;

}
