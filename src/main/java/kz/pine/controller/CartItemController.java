package kz.pine.controller;

import com.fasterxml.jackson.annotation.JsonView;
import kz.pine.domain.CartItem;
import kz.pine.domain.Product;
import kz.pine.domain.User;
import kz.pine.domain.Views;
import kz.pine.services.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cartItem")
@CrossOrigin(origins = "*")
public class CartItemController {
    private final CartItemService itemService;

    @Autowired
    public CartItemController(CartItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    @JsonView(Views.FullCartInfo.class)
    public CartItem add(
            @RequestBody CartItem item,
            @AuthenticationPrincipal User user
    ){
        return itemService.addToCart(item, user);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable("id") Product product,
            @AuthenticationPrincipal User user
    ){
        itemService.deleteByProduct(product, user);
    }

}
