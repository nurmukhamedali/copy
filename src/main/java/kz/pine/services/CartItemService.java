package kz.pine.services;

import kz.pine.domain.*;
import kz.pine.repositories.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemService {
    private final CartItemRepository itemRepository;

    @Autowired
    public CartItemService(CartItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public CartItem addToCart(CartItem form, User user){
        CartItem old = this.itemRepository.findByCartAndProduct(user.getCart(), form.getProduct());
        if (old == null)
            old = new CartItem(user.getCart(), form.getProduct(), form.getQuantity());
        else{
            old.setQuantity(form.getQuantity() + old.getQuantity());
        }
        return itemRepository.save(old);
    }

    public void deleteByProduct(Product product){
        CartItem item = itemRepository.findAllBy
        itemRepository.delete(item);
    }

    public List<CartItem> findAllByUser(User user){
        return itemRepository.findAllByCart(user.getCart());
    }

    public List<CartItem> findAllByCart(Cart cart) {
        return itemRepository.findAllByCart(cart);
    }
}




