package kz.pine.controller;

import com.fasterxml.jackson.annotation.JsonView;
import kz.pine.domain.User;
import kz.pine.domain.Views;
import kz.pine.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
@CrossOrigin(origins="*")
public class CustomerController {
    private CustomerService customerService;

    @Autowired
    private CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @GetMapping("customers")
    @JsonView(Views.FullProfileInfo.class)
    public List<User> findAll(){
        return customerService.findAll();
    }

    @PutMapping("customers/{id}")
    @JsonView(Views.FullProfileInfo.class)
    public User update(
            @PathVariable("id") User old,
            @RequestBody User user,
            @AuthenticationPrincipal User sender
    ){
        return customerService.update(old, user, sender);
    }
}
