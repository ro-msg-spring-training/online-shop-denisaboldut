package ro.msg.learning.shop.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "order_detail")
public class OrderDetail extends BaseEntity {

    @ManyToOne
    @JoinColumn(name="orderShop")
    private Order orderShop;

    @ManyToOne
    @JoinColumn(name="product")
    private Product product;

    private int quantity;

    public OrderDetail(){

    }

    public OrderDetail(Order orderShop, Product product, int quantity) {
        this.orderShop = orderShop;
        this.product = product;
        this.quantity = quantity;
    }
}
