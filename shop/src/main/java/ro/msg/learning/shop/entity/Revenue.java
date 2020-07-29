package ro.msg.learning.shop.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "revenue")
public class Revenue extends BaseEntity {

    @ManyToOne
    @JoinColumn(name="location")
    private Location location;

    @Column(name = "date_revenue")
    private Date localDate;

    private double sumTotal;

    public Revenue(){

    }

    public Revenue(Location location, Date localDate, double sumTotal) {
        this.location = location;
        this.localDate = localDate;
        this.sumTotal = sumTotal;
    }
}
