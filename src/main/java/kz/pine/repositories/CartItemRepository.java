package kz.pine.repositories;

import kz.pine.domain.CartItem;
import kz.pine.domain.CartItemId;
import kz.pine.domain.Product;
import kz.pine.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, CartItemId> {
    CartItem findByUserAndProduct(User user, Product product);
    List<CartItem> findAllByUser(User user);

    List<CartItem> findByIdProductId(Long productId);

    List<CartItem> findByIdUserId(String userId);
}
