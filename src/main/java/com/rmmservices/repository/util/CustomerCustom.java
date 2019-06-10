package com.rmmservices.repository.util;

import com.rmmservices.model.Customer;

/**
 * Repository Custom interface to manage the customer table
 *
 * @author Karla
 * @since 10-06-2019
 */
public interface CustomerCustom {
    Customer findByUser(Integer idUser);
}