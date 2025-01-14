package com.rmmservices.repository.util;

import com.rmmservices.model.CustomerService;

import java.util.List;

/**
 * Repository Custom interface to manage the customer_service table
 *
 * @author Karla
 * @since 10-06-2019
 */
public interface ServiceCustom {
    List<CustomerService> findByCustomer(Integer idCustomer);
    CustomerService findExistent(Integer idCustomer, Integer idDevice, Integer idSmartService);
}