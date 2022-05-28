package kz.pine.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import kz.pine.domain.enums.Role;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "userp")
@ToString(of = {"id", "name"})
@NoArgsConstructor
public class User implements Serializable, UserDetails {
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

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER) /**FETCH EAGER LOADS ALL ROLES OF USER WHEN REQUESTS USER */
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @JsonView(Views.FullProfileInfo.class)
    private Set<Role> roles;

    @OneToMany(mappedBy = "customer", orphanRemoval = true)
    @JsonIgnore
    private List<Order> orders;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}