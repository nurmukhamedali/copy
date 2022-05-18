package kz.pine.uuu;



import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table
@Data
public class Cart implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @OneToOne(mappedBy = "cart")
//    @JsonIgnore
//    private User customer;
//
//    @OneToMany(mappedBy = "cart")
//    @JsonIgnore
//    private List<CartItem> items;
}
