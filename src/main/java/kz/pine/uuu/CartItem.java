package kz.pine.uuu;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table/*(uniqueConstraints = {
        @UniqueConstraint(name = "UniqueProductPerCart", columnNames = { "cart_id", "product_id" })
})*/
@Data
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int quantity;

//    @ManyToOne
//    @JoinColumn(name="product_id", nullable = false)
//    @JsonIgnore
//    private Product product;
//
//    @ManyToOne
//    @JoinColumn(name="cart_id", nullable = false)
//    @JsonIgnore
//    private Cart cart;

}
