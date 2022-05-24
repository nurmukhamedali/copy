package kz.pine.domain;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class CartItemId implements Serializable {
    @JsonView(Views.Id.class)
    private Long cartId;
    @JsonView(Views.Id.class)
    private Long productId;

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public CartItemId(Long cartId, Long productId) {
        this.cartId = cartId;
        this.productId = productId;
    }

    public CartItemId() {
    }
}