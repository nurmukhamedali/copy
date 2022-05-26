package kz.pine.services;

import kz.pine.domain.*;
import kz.pine.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private CartService cartService;
    private OrderItemService orderItemService;

    @Autowired
    public OrderService(OrderRepository orderRepository, CartService cartService, OrderItemService orderItemService) {
        this.orderRepository = orderRepository;
        this.cartService = cartService;
        this.orderItemService = orderItemService;
    }

    public List<Order> findAllByUser(User user){
        List<Order> allByCustomer = orderRepository.findAllByCustomer(user);
        allByCustomer.forEach((order) -> this.computeTotals(order));
        return allByCustomer;
    }

    public Order create(Order order, User user){
        Cart cart = cartService.getByUser(user);
        order.setCustomer(user);
        Order savedOrder = orderRepository.save(order);

        for (CartItem item: cart.getItems()) {
            orderItemService.addToOrder(order, item);
        }
        cartService.clearCart(cart);
        return savedOrder;
    }

    public Order getComputedOrder(Order order){
        return computeTotals(order);
    }

    private Order computeTotals(Order order){
        int tItems = 0;
        Double tPrice = 0.0;

        for (OrderItem item: order.getItems()) {
            tPrice += item.getProduct().getPrice() * item.getQuantity();
            tItems += item.getQuantity();
        }

        order.setTotalItems(tItems);
        order.setTotalPrice(tPrice);

        return orderRepository.save(order);
    }
}




