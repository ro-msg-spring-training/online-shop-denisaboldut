package ro.msg.learning.shop.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(exclude = "products", callSuper = false)
@ToString(exclude = "products")
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_category")
public class ProductCategory extends BaseEntity {

    private String name;

    private String description;

    @OneToMany(mappedBy = "productCategory",
               fetch = FetchType.LAZY,
               cascade = CascadeType.ALL,
               orphanRemoval = true)
    @Transient
    private List<Product> products=new ArrayList<>();

}
