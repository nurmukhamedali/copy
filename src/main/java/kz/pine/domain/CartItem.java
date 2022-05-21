package kz.pine.domain;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
@Data
@EqualsAndHashCode(of = {"id"})
@ToString(of = {"id", "quantity"})
@NoArgsConstructor
public class CartItem implements Serializable {
    @EmbeddedId
    @JsonIgnore
    private CartItemId id;
    @JsonView(Views.ShortInfo.class)
    private int quantity;

    @MapsId("productId")
    @ManyToOne
    @JoinColumn(name="product_id", nullable = false)
    @JsonView(Views.FullCartItemInfo.class)
    private Product product;

    @MapsId("userId")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id", referencedColumnName = "id")
    @JsonView(Views.FullCartItemInfo.class)
    private User user;

    public CartItem(User user, Product product, int quantity) {
        this.user = user;
        this.product = product;
        this.quantity = quantity;
        this.id = new CartItemId(user.getId(), product.getId());
    }
}