package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.entity.Customer;

import java.util.Optional;


public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Customer findFirstByOrderByIdAsc();
    Optional<Customer> findCustomerByUsername(String username);
}
