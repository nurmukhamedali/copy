package kz.pine.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "userp")
@Data
public class User implements Serializable {
    @Id
    private String id;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @Column (nullable = false, unique = true, length = 45, name = "username")
    private String username;
    @Column (length = 45, name = "name")
    private String name;
    private String address;
    private String avatar;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "cart_id", nullable = true, updatable = true)
//    @JsonIgnore
//    private Cart cart;

//    @OneToMany(mappedBy = "customer")
//    @JsonIgnore
//    private List<Order> orders;
}