package kz.pine.services;

import kz.pine.domain.*;
import kz.pine.domain.enums.OrderStatus;
import kz.pine.exceptions.NotPermittedException;
import kz.pine.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private CartService cartService;
    private OrderItemService orderItemService;
    private CustomerService customerService;

    @Autowired
    public OrderService(OrderRepository orderRepository, CartService cartService, OrderItemService orderItemService, CustomerService customerService) {
        this.orderRepository = orderRepository;
        this.cartService = cartService;
        this.orderItemService = orderItemService;
        this.customerService = customerService;
    }

    public List<Order> findAllByUser(User user){
        List<Order> allByCustomer = orderRepository.findAllByCustomer(user);
        allByCustomer.forEach((order) -> this.computeTotals(order));
        return allByCustomer;
    }


    public Order create(Order order, User user){
        Cart cart = cartService.getByUser(user);
        order.setCustomer(user);
        order.setStatus(OrderStatus.CREATED);
        order.setCreatedDate(LocalDateTime.now());
        Order savedOrder = orderRepository.save(order);

        for (CartItem item: cart.getItems()) {
            orderItemService.addToOrder(savedOrder, item);
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

    public Order updateStatus(Order old, Order order, User user){
        if (customerService.hasAdminAuthority(user)) {
            old.setStatus(order.getStatus());
            if (old.getStatus() == OrderStatus.DELIVERED){
                old.setDeliveredDate(LocalDateTime.now());
            }
            return orderRepository.save(order);
        } else throw new NotPermittedException();

    }

    public List<Order> findAll(User user){
        if (customerService.hasAdminAuthority(user)) {
            List<Order> allByCustomer = orderRepository.findAll();
            allByCustomer.forEach((order) -> this.computeTotals(order));
            return allByCustomer;
        } else throw new NotPermittedException();
    }
}




