package kz.pine.services;

import kz.pine.domain.Cart;
import kz.pine.domain.CartItem;
import kz.pine.domain.User;
import kz.pine.repositories.CartItemRepository;
import kz.pine.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository itemRepository;

    @Autowired
    public CartService(CartRepository cartRepository, CartItemRepository itemRepository) {
        this.cartRepository = cartRepository;
        this.itemRepository = itemRepository;
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

    public void clearCart(Cart cart){
        for (CartItem item: cart.getItems()) {
            itemRepository.delete(item);
        }
    }
}




