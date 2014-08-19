package com.backcountry.web.repositories;

import com.backcountry.web.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Edwin Dalorzo.
 */
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer findByEmail(String email);
}
