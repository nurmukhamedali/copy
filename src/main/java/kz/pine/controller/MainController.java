package kz.pine.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import kz.pine.domain.Cart;
import kz.pine.domain.User;
import kz.pine.domain.Views;
import kz.pine.dto.ProductPageDto;
import kz.pine.services.CartItemService;
import kz.pine.services.CartService;
import kz.pine.services.CategoryService;
import kz.pine.services.ProductService;
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


@Controller
@RequestMapping("/")
public class MainController {
    private final ObjectWriter writer;
    private final ObjectWriter profileWriter;
    private final ObjectWriter cartWriter;
    private final ObjectWriter itemWriter;
    private final CategoryService categoryService;
    private final ProductService productService;
    private final CartService cartService;
    private final CartItemService itemService;

    @Autowired
    public MainController(CategoryService categoryService, ProductService productService, CartService cartService,CartItemService itemService, ObjectMapper mapper) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.cartService = cartService;
        this.itemService = itemService;

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
            Cart cart = cartService.getByUser(user);
            String stringCategories = writer.writeValueAsString(categoryService.findAll());

            Sort sort = Sort.by(Sort.Direction.DESC, "id");
            PageRequest pageRequest = PageRequest.of(0, ProductController.PRODUCTS_PER_PAGE, sort);
            ProductPageDto productPageDto = productService.findAll(pageRequest);

            String stringProducts = writer.writeValueAsString(productPageDto.getProducts());


            String stringProfile = profileWriter.writeValueAsString(user);
            String stringCart = cartWriter.writeValueAsString(cart);
            String stringItems = itemWriter.writeValueAsString(itemService.findAllByCart(cart));
            model.addAttribute("profile", stringProfile);
            model.addAttribute("categories", stringCategories);
            model.addAttribute("products", stringProducts);
            model.addAttribute("cart", stringCart);
            model.addAttribute("cartItems", stringItems);

            data.put("currentPage", productPageDto.getCurrentPage());
            data.put("totalPages", productPageDto.getTotalPages());
        }

        model.addAttribute("frontendData", data);
        model.addAttribute("isDevMode", "dev".equals(profile));
        return "index";
    }
}