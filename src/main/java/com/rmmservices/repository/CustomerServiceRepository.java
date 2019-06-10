package com.rmmservices.repository;

import com.rmmservices.model.Customer;
import com.rmmservices.model.CustomerService;
import com.rmmservices.repository.util.ServiceCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface to manage the customer_service table
 *
 * @author Karla
 * @since 10-06-2019
 */
@Repository
public interface CustomerServiceRepository extends JpaRepository<CustomerService, Integer>, ServiceCustom {

}