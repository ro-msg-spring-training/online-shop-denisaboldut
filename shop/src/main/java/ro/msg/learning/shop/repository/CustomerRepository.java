package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.msg.learning.shop.entity.Customer;


public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Customer findFirstByOrderByIdAsc();
    Customer findCustomerByUsername(String username);
}
