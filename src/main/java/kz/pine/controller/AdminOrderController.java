package kz.pine.controller;

import com.fasterxml.jackson.annotation.JsonView;
import kz.pine.domain.Order;
import kz.pine.domain.User;
import kz.pine.domain.Views;
import kz.pine.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminOrderController {

    private OrderService orderService;

    @Autowired
    public AdminOrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PutMapping("/orders/{id}")
    public Order updateStatus(
            @PathVariable("id") Order old,
            @RequestBody Order order,
            @AuthenticationPrincipal User user
    ){
        Order updatedOrder = orderService.updateStatus(old, order, user);
        return updatedOrder;
    }

    @GetMapping("/orders")
    @JsonView(Views.FullCartInfo.class)
    public List<Order> getAll(
            @AuthenticationPrincipal User user
    ){
        return orderService.findAll(user);
    }
}
