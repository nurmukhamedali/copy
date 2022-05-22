package kz.pine.services;

import kz.pine.domain.Cart;
import kz.pine.domain.CartItem;
import kz.pine.domain.CartItemId;
import kz.pine.domain.User;
import kz.pine.repositories.CartItemRepository;
import kz.pine.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    private final CartRepository cartRepository;

    @Autowired
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Cart getByUser(User user){

        Long id = user.getCart().getId();
        Cart cart = cartRepository.getById(id);

        return computeTotals(cart);
    }

    private Cart computeTotals(Cart cart){
        int tItems = 0;
        Double tPrice = 0.0;

        for (CartItem item: cart.getItems()) {
            tPrice += item.getProduct().getPrice() * item.getQuantity();
            tItems += item.getQuantity();
        }

        cart.setTotalItems(tItems);
        cart.setTotalPrice(tPrice);

        return cartRepository.save(cart);
    }
}




