package kz.pine.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class OrderItem {
    @EmbeddedId
    @JsonIgnore
    private OrderItemId id;
    @JsonView(Views.ShortInfo.class)
    private int quantity;

    @MapsId("productId")
    @ManyToOne
    @JoinColumn(name="product_id", nullable = false)
    @JsonView(Views.FullCartInfo.class)
    private Product product;

    @MapsId("orderId")
    @ManyToOne()
    @JoinColumn(name="order_id", referencedColumnName = "id")
    @JsonIgnore
    private Order order;

    public OrderItem(Order order, Product product, int quantity) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.id = new OrderItemId(order.getId(), product.getId());
    }

    public OrderItem() {
    }

    public OrderItemId getId() {
        return id;
    }

    public void setId(OrderItemId id) {
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
