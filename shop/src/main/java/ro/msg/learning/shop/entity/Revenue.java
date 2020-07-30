package ro.msg.learning.shop.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "revenue")
public class Revenue extends BaseEntity {

    @ManyToOne
    @JoinColumn(name="location")
    private Location location;

    @Column(name = "date_revenue")
    private Date localDate;

    private double sumTotal;

}
