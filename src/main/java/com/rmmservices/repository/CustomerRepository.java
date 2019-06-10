package com.rmmservices.repository;

import com.rmmservices.model.Customer;
import com.rmmservices.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface to manage the customer table
 *
 * @author Karla
 * @since 08-06-2019
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}