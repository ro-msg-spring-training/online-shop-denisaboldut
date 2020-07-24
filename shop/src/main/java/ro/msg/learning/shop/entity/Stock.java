package ro.msg.learning.shop.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table (name = "stock")
public class Stock extends BaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="product")
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="location")
    private Location location;

    private int quantity;

    public Stock(){

    }

    public Stock(Product product, Location location, int quantity) {
        this.product = product;
        this.location = location;
        this.quantity = quantity;
    }
}
