package kz.pine.domain;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Data
@ToString(of = {"id"})
@NoArgsConstructor
public class Cart implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.Id.class)
    private Long id;

    @OneToOne(mappedBy = "cart", fetch = FetchType.EAGER)
    @JsonView(Views.FullCartInfo.class)
    private User user;

    @OneToMany(mappedBy = "cart", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    private List<CartItem> items;

}