package kz.pine.dto;

import com.fasterxml.jackson.annotation.JsonView;
import kz.pine.domain.Product;
import kz.pine.domain.Views;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@JsonView(Views.FullProductInfo.class)
public class ProductPageDto {
    private List<Product> products;
    private int currentPage;
    private int totalPages;
}
