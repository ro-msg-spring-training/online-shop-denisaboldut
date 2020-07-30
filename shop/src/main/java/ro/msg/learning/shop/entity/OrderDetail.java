package ro.msg.learning.shop.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_detail")
public class OrderDetail extends BaseEntity {

    @ManyToOne
    @JoinColumn(name="orderShop")
    @JsonIgnore
    private Order orderShop;

    @ManyToOne
    @JoinColumn(name="product")
    private Product product;

    private int quantity;
}
