package kz.pine.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import kz.pine.domain.Cart;
import kz.pine.domain.Order;
import kz.pine.domain.User;
import kz.pine.domain.Views;
import kz.pine.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;


@Controller
@RequestMapping("/")
public class MainController {
    private final ObjectWriter writer;
    private final ObjectWriter profileWriter;
    private final ObjectWriter cartWriter;
    private final ObjectWriter orderWriter;
    private final CategoryService categoryService;
    private final ProductService productService;
    private final CartService cartService;
    private final OrderService orderService;
    private final CustomerService customerService;


    @Autowired
    public MainController(CategoryService categoryService, ProductService productService, CartService cartService, ObjectMapper mapper, OrderService orderService, CustomerService customerService) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.cartService = cartService;
        this.orderService = orderService;
        this.customerService = customerService;

        ObjectMapper objectMapper = mapper
                .setConfig(mapper.getSerializationConfig());

        this.writer = objectMapper
                .writerWithView(Views.FullProductInfo.class);
        this.profileWriter = objectMapper
                .writerWithView(Views.FullProfileInfo.class);
        this.cartWriter = objectMapper
                .writerWithView(Views.FullCartInfo.class);
        this.orderWriter = objectMapper
                .writerWithView(Views.FullCartInfo.class);
    }

    @Value("${spring.profiles.active}")
    private String profile;

    @GetMapping
    public String main(
            Model model,
            @AuthenticationPrincipal User user
    ) throws JsonProcessingException {
        String stringCategories = writer.writeValueAsString(categoryService.findAll());
        if (user != null) {
            String stringProfile = profileWriter.writeValueAsString(user);

            Cart cart = cartService.getByUser(user);
            String stringCart = cartWriter.writeValueAsString(cart);

            String stringOrders = orderWriter.writeValueAsString(orderService.findAllByUser(user));

            model.addAttribute("profile", stringProfile);
            model.addAttribute("cart", stringCart);
            model.addAttribute("orders", stringOrders);
        } else {
            model.addAttribute("profile", "null");
            model.addAttribute("cart", "null");
            model.addAttribute("orders", "null");
        }

        model.addAttribute("categories", stringCategories);
        model.addAttribute("isDevMode", "dev".equals(profile));
        return "index";
    }
}