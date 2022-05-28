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
public class CartItem implements Serializable {
    @EmbeddedId
    @JsonIgnore
    private CartItemId id;
    @JsonView(Views.ShortInfo.class)
    private int quantity;

    @MapsId("productId")
    @ManyToOne
    @JoinColumn(name="product_id", nullable = false)
    @JsonView(Views.FullCartInfo.class)
    private Product product;

    @MapsId("cartId")
    @ManyToOne
    @JoinColumn(name="cart_id", referencedColumnName = "id")
    @JsonIgnore
    private Cart cart;

    public CartItem(Cart cart, Product product, int quantity) {
        this.cart = cart;
        this.product = product;
        this.quantity = quantity;
        this.id = new CartItemId(cart.getId(), product.getId());
    }

    public CartItem() {
    }

    public CartItemId getId() {
        return id;
    }

    public void setId(CartItemId id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
