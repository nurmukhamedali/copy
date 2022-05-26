package kz.pine.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.Id.class)
    private Long id;
    @JsonView(Views.FullCartInfo.class)
    private double totalPrice;
    @JsonView(Views.FullCartInfo.class)
    private int totalItems;
    @JsonView(Views.FullCartInfo.class)
    private String recipientName;
    @JsonView(Views.FullCartInfo.class)
    private String recipientPhoneNumber;
    @JsonView(Views.FullCartInfo.class)
    private String deliveryInstructions;
    @JsonView(Views.FullCartInfo.class)
    private String deliveryAddress;
    @JsonView(Views.FullCartInfo.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yy HH:mm")
    private LocalDateTime deliveryDate;
    @JsonView(Views.FullCartInfo.class)
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;


    @JsonView(Views.FullCartInfo.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yy HH:mm")
    private LocalDateTime createdDate;

    @JsonView(Views.FullCartInfo.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yy HH:mm")
    private LocalDateTime deliveredDate;


    @ManyToOne
    @JoinColumn(name="customer_id")
    @JsonView(Views.FullCartInfo.class)
    private User customer;

    @OneToMany(mappedBy = "order")
    @JsonView(Views.FullCartInfo.class)
    private List<OrderItem> items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
}