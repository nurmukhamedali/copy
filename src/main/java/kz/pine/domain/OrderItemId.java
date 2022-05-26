package kz.pine.domain;

import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class OrderItemId implements Serializable {
    @JsonView(Views.Id.class)
    private Long orderId;
    @JsonView(Views.Id.class)
    private Long productId;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public OrderItemId(Long orderId, Long productId) {
        this.orderId = orderId;
        this.productId = productId;
    }

    public OrderItemId() {
    }
}