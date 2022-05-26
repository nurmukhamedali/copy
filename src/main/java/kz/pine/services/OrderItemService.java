package kz.pine.services;

import kz.pine.domain.*;
import kz.pine.repositories.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemService {
    private final OrderItemRepository itemRepository;

    @Autowired
    public OrderItemService(OrderItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public OrderItem addToOrder(OrderItem form, Order order){
        OrderItem old = this.itemRepository.findByOrderAndProduct(order, form.getProduct());
        if (old == null)
            old = new OrderItem(order, form.getProduct(), form.getQuantity());
        else{
            old.setQuantity(form.getQuantity() + old.getQuantity());
        }
        return itemRepository.save(old);
    }

    public List<OrderItem> findAllByUser(Order order){
        return itemRepository.findAllByOrder(order);
    }

    public OrderItem addToOrder(Order order, CartItem cartItem){
        OrderItem orderItem = new OrderItem(order, cartItem.getProduct(), cartItem.getQuantity());
        return itemRepository.save(orderItem);
    }

}




