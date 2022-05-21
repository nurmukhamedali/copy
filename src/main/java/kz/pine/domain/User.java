package kz.pine.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

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

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    @JsonView(Views.FullProfileInfo.class)
    private Cart cart;
}