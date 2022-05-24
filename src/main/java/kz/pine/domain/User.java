package kz.pine.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "userp")
@ToString(of = {"id", "name"})
@NoArgsConstructor
public class User implements Serializable {
    @Id
    @JsonView(Views.Id.class)
    private String id;

    @Column (nullable = false, unique = true, length = 45, name = "username")
    @JsonView(Views.ShortInfo.class)
    private String username;
    @Column (length = 45, name = "name")
    @JsonView(Views.FullProfileInfo.class)
    private String name;
    @JsonView(Views.FullProfileInfo.class)
    private String address;
    @JsonView(Views.FullProfileInfo.class)
    private String avatar;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    @JsonView(Views.FullProfileInfo.class)
    private Cart cart;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}