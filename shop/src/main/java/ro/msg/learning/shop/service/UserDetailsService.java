package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.repository.CustomerRepository;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserDetailsService {
    private final CustomerRepository customerRepository;

    private Map<String,String> usersAndPasswords = new HashMap<>();

    void getUsersAndPasswordsFromDb(){
        customerRepository.findAll()
                .forEach(customer -> usersAndPasswords.put(customer.getUsername(),customer.getPassword()));

    }

    String getPassword(String username){
       return customerRepository.findCustomerByUsername(username).getPassword();
    }
}
