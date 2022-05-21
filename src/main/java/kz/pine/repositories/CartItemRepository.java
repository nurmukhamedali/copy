package kz.pine.repositories;

import kz.pine.domain.Cart;
import kz.pine.domain.CartItem;
import kz.pine.domain.CartItemId;
import kz.pine.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, CartItemId> {
    CartItem findByCartAndProduct(Cart cart, Product product);
    List<CartItem> findAllByCart(Cart cart);

    void deleteCartItemByCartAndProduct(Cart cart, Product product);
    List<CartItem> findByIdProductId(Long productId);

    List<CartItem> findByIdCartId(Long cartId);
}
