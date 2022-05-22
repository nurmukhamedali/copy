package kz.pine.controller;

import com.fasterxml.jackson.annotation.JsonView;
import kz.pine.domain.Category;
import kz.pine.domain.Product;
import kz.pine.domain.Views;
import kz.pine.dto.ProductPageDto;
import kz.pine.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin")
@CrossOrigin(origins="*")
public class ProductController {
    public static final int PRODUCTS_PER_PAGE = 12;

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    @JsonView(Views.FullProductInfo.class)
    public ProductPageDto getAll(
            @PageableDefault(size = PRODUCTS_PER_PAGE, sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable
//            @RequestParam(required = false, value = "categoryId", defaultValue = "0") Category category
    ) {
        return productService.findAll(pageable);
    }

    @GetMapping("/products/{id}")
    @JsonView(Views.FullProductInfo.class)
    public Product get(@PathVariable("id") Product product){
        return product;
    }

    @PostMapping("/products")
    @JsonView(Views.FullProductInfo.class)
    public Product create(@RequestBody Product product) {

        return productService.create(product);
    }

    @PutMapping("/products/{id}")
    @JsonView(Views.FullProductInfo.class)
    public Product update(
            @PathVariable("id") Product old,
            @RequestBody Product product
    ){
        return productService.update(old, product);
    }

    @DeleteMapping("/products/{id}")
    public void delete(@PathVariable("id") Product product){
        productService.delete(product);
    }


}
