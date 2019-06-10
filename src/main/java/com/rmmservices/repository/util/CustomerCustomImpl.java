package com.rmmservices.repository.util;

import com.rmmservices.model.Customer;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Repository Custom class to manage the customer table
 *
 * @author Karla
 * @since 10-06-2019
 */
@Repository
@Transactional(readOnly = true)
public class CustomerCustomImpl implements CustomerCustom {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Customer findByUser(Integer idUser) {
        Customer customer = null;
        Query query = entityManager.createNativeQuery("SELECT c.* FROM public.customer as c " +
                "WHERE c.id_user = ?", Customer.class);
        query.setParameter(1, idUser);
        List list = query.getResultList();
        if (list != null && list.size() != 0) {
            customer = (Customer) list.get(0);
        }
        return customer;
    }
}