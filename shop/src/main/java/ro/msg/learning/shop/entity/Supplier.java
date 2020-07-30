package ro.msg.learning.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "supplier")
public class Supplier extends BaseEntity {

    private String name;

    @OneToMany(mappedBy = "supplier",
              fetch = FetchType.LAZY,
              cascade = CascadeType.ALL,
              orphanRemoval = true)
    @Transient
    private List<Product> products=new ArrayList<>();

}
