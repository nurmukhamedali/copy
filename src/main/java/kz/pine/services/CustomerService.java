package kz.pine.services;

import kz.pine.domain.enums.Role;
import kz.pine.domain.User;
import kz.pine.exceptions.NotPermittedException;
import kz.pine.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public List<User> findAll(){
        return customerRepository.findAll();
    }

    public boolean hasAdminAuthority(User user){
        return user.getRoles().contains(Role.ADMIN);
    }

    public User update(User old, User user, User sender){
        if(hasAdminAuthority(sender) || old.getId().equals(sender.getId())){
            old.setAddress(user.getAddress());
            old.setName(user.getName());
            old.setUsername(user.getUsername());
            return customerRepository.save(old);
        } else throw new NotPermittedException();
    }

    public boolean hasUserAuthority(User user){
        return user.getRoles().contains(Role.USER);
    }

}
