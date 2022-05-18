package kz.pine.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Data
@ToString(of = {"id", "name", "image", "brand", "price"})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.Id.class)
    private Long id;
    @JsonView(Views.ShortInfo.class)
    private String name;
    @JsonView(Views.ShortInfo.class)
    private String image;
    @JsonView(Views.ShortInfo.class)
    private String brand;
    @JsonView(Views.ShortInfo.class)
    private double price;

    @ManyToOne
    @JoinColumn(name="category_id", nullable = false)
    @JsonView(Views.FullProductInfo.class)
    private Category category;
}
