package ro.msg.learning.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entity.Customer;
import ro.msg.learning.shop.repository.CustomerRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService implements UserDetailsService {

    private final CustomerRepository customerRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Optional<Customer> customer = customerRepository.findCustomerByUsername(s);

        if (customer.isPresent()) {
            return customer.get();
        } else {
            throw new UsernameNotFoundException("Username not found");
        }
    }

    //for authentication test
    public Customer saveCustomer() {
        Customer customer = new Customer();
        customer.setUsername("user1");
        customer.setPassword(passwordEncoder.encode("password"));
        customer.setFirstName("Andrei");
        customer.setLastName("Pop");
        customer.setEmailAddress("popandrei@yahoo.com");
        return customerRepository.save(customer);
    }
}
