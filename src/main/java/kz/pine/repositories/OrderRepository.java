package kz.pine.repositories;

import kz.pine.domain.Order;
import kz.pine.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByCustomer(User user);
}
