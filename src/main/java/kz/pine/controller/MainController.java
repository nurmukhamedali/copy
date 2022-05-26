package kz.pine.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import kz.pine.domain.Cart;
import kz.pine.domain.Order;
import kz.pine.domain.User;
import kz.pine.domain.Views;
import kz.pine.dto.ProductPageDto;
import kz.pine.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    private final ObjectWriter itemWriter;
    private final ObjectWriter orderWriter;
    private final CategoryService categoryService;
    private final ProductService productService;
    private final CartService cartService;
    private final CartItemService itemService;
    private final OrderService orderService;


    @Autowired
    public MainController(CategoryService categoryService, ProductService productService, CartService cartService, CartItemService itemService, ObjectMapper mapper, OrderService orderService) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.cartService = cartService;
        this.itemService = itemService;
        this.orderService = orderService;

        ObjectMapper objectMapper = mapper
                .setConfig(mapper.getSerializationConfig());

        this.writer = objectMapper
                .writerWithView(Views.FullProductInfo.class);
        this.profileWriter = objectMapper
                .writerWithView(Views.FullProfileInfo.class);
        this.cartWriter = objectMapper
                .writerWithView(Views.FullCartInfo.class);
        this.itemWriter = objectMapper
                .writerWithView(Views.FullCartItemInfo.class);
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
        HashMap<Object, Object> data = new HashMap<>();
        if (user != null) {
            String stringCategories = writer.writeValueAsString(categoryService.findAll());

            Sort sort = Sort.by(Sort.Direction.DESC, "id");
            PageRequest pageRequest = PageRequest.of(0, ProductController.PRODUCTS_PER_PAGE, sort);
            ProductPageDto productPageDto = productService.findAll(pageRequest, null, 0, 100);

            String stringProducts = writer.writeValueAsString(productPageDto.getProducts());


            String stringProfile = profileWriter.writeValueAsString(user);

            Cart cart = cartService.getByUser(user);
            String stringCart = cartWriter.writeValueAsString(cart);
            String stringItems = itemWriter.writeValueAsString(itemService.findAllByCart(cart));

            String stringOrders = orderWriter.writeValueAsString(orderService.findAllByUser(user));

            model.addAttribute("profile", stringProfile);
            model.addAttribute("categories", stringCategories);
            model.addAttribute("products", stringProducts);
            model.addAttribute("cart", stringCart);
            model.addAttribute("cartItems", stringItems);
            model.addAttribute("orders", stringOrders);

            data.put("currentPage", productPageDto.getCurrentPage());
            data.put("totalPages", productPageDto.getTotalPages());
        }

        model.addAttribute("frontendData", data);
        model.addAttribute("isDevMode", "dev".equals(profile));
        return "index";
    }
}