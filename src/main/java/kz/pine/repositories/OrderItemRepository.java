package kz.pine.repositories;

import kz.pine.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemId> {
    OrderItem findByOrderAndProduct(Order order, Product product);
    List<OrderItem> findAllByOrder(Order order);
}
