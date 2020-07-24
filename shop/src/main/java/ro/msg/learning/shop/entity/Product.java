package ro.msg.learning.shop.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
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

    public Product(){

    }

    public Product(String name, String description, double price, double weight, ProductCategory productCategory, Supplier supplier, String imageUrl) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.weight = weight;
        this.productCategory = productCategory;
        this.supplier = supplier;
        this.imageUrl = imageUrl;
    }
}
