package kz.pine.domain;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "userp")
@Data
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

//    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
//    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
//    @Enumerated(EnumType.STRING)
//    @JsonView(Views.FullProfileInfo.class)
//    private Set<Role> roles;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<CartItem> items;

    public User(String id, String username, String name, String address, String avatar) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.address = address;
        this.avatar = avatar;
    }
}