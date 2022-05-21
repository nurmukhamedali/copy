package kz.pine.repositories;

import kz.pine.domain.Cart;
import kz.pine.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findAllByUser(User user);
}
