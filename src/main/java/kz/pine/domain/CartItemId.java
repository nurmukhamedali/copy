package kz.pine.domain;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemId implements Serializable {
    @JsonView(Views.Id.class)
    private String userId;
    @JsonView(Views.Id.class)
    private Long productId;
}