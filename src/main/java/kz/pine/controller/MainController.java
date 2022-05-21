package kz.pine.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import kz.pine.domain.User;
import kz.pine.domain.Views;
import kz.pine.services.CartItemService;
import kz.pine.services.CategoryService;
import kz.pine.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {
    private final ObjectWriter writer;
    private final ObjectWriter profileWriter;
    private final ObjectWriter itemWriter;
    private final CategoryService categoryService;
    private final ProductService productService;
    private final CartItemService itemService;

    @Autowired
    public MainController(CategoryService categoryService, ProductService productService, CartItemService itemService, ObjectMapper mapper) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.itemService = itemService;

        ObjectMapper objectMapper = mapper
                .setConfig(mapper.getSerializationConfig());

        this.writer = objectMapper
                .writerWithView(Views.FullProductInfo.class);
        this.profileWriter = objectMapper
                .writerWithView(Views.FullProfileInfo.class);
        this.itemWriter = objectMapper
                .writerWithView(Views.FullCartItemInfo.class);
    }

    @Value("${spring.profiles.active}")
    private String profile;

    @GetMapping
    public String main(
            Model model,
            @AuthenticationPrincipal User user
    ) throws JsonProcessingException {
        if (user != null) {
            String categories = writer.writeValueAsString(categoryService.findAll());
            String products = writer.writeValueAsString(productService.findAll(null));
            String profile = profileWriter.writeValueAsString(user);
            String items = itemWriter.writeValueAsString(itemService.findAllByUser(user));
            model.addAttribute("profile", profile);
            model.addAttribute("categories", categories);
            model.addAttribute("products", products);
            model.addAttribute("cart", items);
        }
        model.addAttribute("isDevMode", "dev".equals(profile));
        return "index";
    }
}