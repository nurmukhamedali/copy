package kz.pine.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import kz.pine.domain.User;
import kz.pine.domain.Views;
import kz.pine.services.CategoryService;
import kz.pine.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Controller
@RequestMapping("/")
public class MainController {
    private final ObjectWriter writer;
    private CategoryService categoryService;
    private ProductService productService;

    @Autowired
    public MainController(CategoryService categoryService, ProductService productService, ObjectMapper mapper) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.writer = mapper
                .setConfig(mapper.getSerializationConfig())
                .writerWithView(Views.FullCategoryInfo.class);
    }

    @Value("${spring.profiles.active}")
    private String profile;

    @GetMapping
    public String main(
            Model model,
            @AuthenticationPrincipal User user
    ) throws JsonProcessingException {
        HashMap<Object, Object> data = new HashMap<>();
        if (user != null) {
            data.put("profile", user);
            String categories = writer.writeValueAsString(categoryService.findAll());
            String products = writer.writeValueAsString(productService.findAll(null));
            model.addAttribute("categories", categories);
            model.addAttribute("products", products);
        }
        model.addAttribute("frontendData", data);
        model.addAttribute("isDevMode", "dev".equals(profile));
        return "index";
    }
}