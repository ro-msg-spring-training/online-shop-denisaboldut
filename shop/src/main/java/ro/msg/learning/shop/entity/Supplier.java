package ro.msg.learning.shop.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "supplier")
public class Supplier extends BaseEntity {

    private String name;

    @OneToMany(mappedBy = "supplier",
              fetch = FetchType.LAZY,
              cascade = CascadeType.ALL,
              orphanRemoval = true)
    private List<Product> products=new ArrayList<>();

    public Supplier(){

    }

    public Supplier(String name) {
        this.name = name;
    }
}
