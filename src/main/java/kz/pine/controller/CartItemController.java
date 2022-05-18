package kz.pine.controller;

import kz.pine.uuu.CartItem;
import kz.pine.services.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class CartItemController {
    private final CartItemService itemService;

    @Autowired
    public CartItemController(CartItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public CartItem create(@RequestBody CartItem item){

        return itemService.create(item);
    }

}
