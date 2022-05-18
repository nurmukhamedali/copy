package kz.pine.controller;

import com.fasterxml.jackson.annotation.JsonView;
import kz.pine.domain.Category;
import kz.pine.domain.Product;
import kz.pine.domain.Views;
import kz.pine.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
@CrossOrigin(origins="*")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    @JsonView(Views.FullProductInfo.class)
    public List<Product> getAll(@RequestParam(required = false, value = "categoryId", defaultValue = "0") Category category) {
        return productService.findAll(category);
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
