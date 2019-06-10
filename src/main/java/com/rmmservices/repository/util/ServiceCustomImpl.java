package com.rmmservices.repository.util;

import com.rmmservices.model.CustomerService;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Repository Custom class to manage the customer_service table
 *
 * @author Karla
 * @since 10-06-2019
 */
@Repository
@Transactional(readOnly = true)
public class ServiceCustomImpl implements ServiceCustom {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<CustomerService> getByCustomer(Integer idCustomer) {
        Query query = entityManager.createNativeQuery("SELECT s.* FROM public.customer_service as s " +
                "WHERE s.id_customer = ?", CustomerService.class);
        query.setParameter(1, idCustomer);
        List list = query.getResultList();
        return list;
    }
}