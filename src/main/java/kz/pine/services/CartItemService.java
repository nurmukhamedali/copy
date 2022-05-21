package kz.pine.services;

import kz.pine.domain.CartItem;
import kz.pine.domain.CartItemId;
import kz.pine.domain.User;
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
        CartItem old = this.itemRepository.findByUserAndProduct(user, form.getProduct());
        if (old == null)
            old = new CartItem(user, form.getProduct(), form.getQuantity());
        else{
            old.setQuantity(form.getQuantity() + old.getQuantity());
        }
        return itemRepository.save(old);
    }

    private CartItem getById(CartItemId id){
        return itemRepository.getById(id);
    }


    public List<CartItem> findAllByUser(User user){
        return itemRepository.findAllByUser(user);
    }

    public void delete(CartItem item){
        itemRepository.delete(item);
    }
}




