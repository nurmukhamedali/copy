package kz.pine.controller;

import com.fasterxml.jackson.annotation.JsonView;
import kz.pine.domain.Category;
import kz.pine.domain.Views;
import kz.pine.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
@CrossOrigin(origins="*")
public class CategoryController {
    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    @JsonView(Views.FullCategoryInfo.class)
    public List<Category> getAll() {
        return categoryService.findAll();
    }

    @GetMapping("/categories/{id}")
    @JsonView(Views.FullCategoryInfo.class)
    public Category get(@PathVariable("id") Category category){
        return category;
    }

    @PostMapping("/categories")
    @JsonView(Views.FullCategoryInfo.class)
    public Category create(@RequestBody Category category) {
        return categoryService.create(category);
    }

    @PutMapping("/categories/{id}")
    @JsonView(Views.FullCategoryInfo.class)
    public Category update(
            @PathVariable("id") Category old,
            @RequestBody Category category
    ){
        return categoryService.update(old, category);
    }

    @DeleteMapping("/categories/{id}")
    public void delete(@PathVariable("id") Category category){
        categoryService.delete(category);
    }


}
