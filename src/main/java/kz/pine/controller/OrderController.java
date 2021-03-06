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
@RequestMapping("")
@CrossOrigin(origins="*")
public class OrderController {
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    @JsonView(Views.FullCartInfo.class)
    public List<Order> getAllByUser(
            @AuthenticationPrincipal User user
    ) {
        return orderService.findAllByUser(user);
    }

    @GetMapping("/orders/{id}")
    @JsonView(Views.FullCartInfo.class)
    public Order get(@PathVariable("id") Order order){
        return orderService.getComputedOrder(order);
    }

    @PostMapping("/orders")
    @JsonView(Views.FullCartInfo.class)
    public Order create(
            @RequestBody Order order,
            @AuthenticationPrincipal User user
    ) {
        return orderService.create(order, user);
    }
}
