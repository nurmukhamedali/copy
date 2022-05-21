package kz.pine.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "a_category")
@Data
@ToString(of = {"id", "name", "image"})
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.Id.class)
    private Long id;
    @JsonView(Views.ShortInfo.class)
    private String name;
    @JsonView(Views.ShortInfo.class)
    private String image;

    @OneToMany(mappedBy = "category", orphanRemoval = true)
    @JsonIgnore
    private List<Product> products;
}
