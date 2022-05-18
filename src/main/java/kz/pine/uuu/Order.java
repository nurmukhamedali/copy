package kz.pine.uuu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table
@Data
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double totalCost;
    private Long totalItems;

//    @ManyToOne
//    @JoinColumn(name="customer_id")
//    @JsonIgnore
//    private User customer;
//
//    @OneToMany(mappedBy = "order")
//    @JsonIgnore
//    private List<OrderItem> items;

}