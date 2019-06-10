package com.rmmservices.repository.util;

import com.rmmservices.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Repository Custom class to manage the user table
 *
 * @author Karla
 * @since 08-06-2019
 */
@Repository
@Transactional(readOnly = true)
public class UserRepCustomImpl implements UserRepCustom {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Boolean verifyUser(String userName, String password) {
        Boolean correct = false;
        Query query = entityManager.createNativeQuery("SELECT u.* FROM public.user as u " +
                "WHERE u.user_name = ? and u.password = ? and u.status=true", User.class);
        query.setParameter(1, userName);
        query.setParameter(2, password);
        List list = query.getResultList();
        if (list != null && list.size() != 0) {
            User user = (User) list.get(0);//query.getSingleResult();
            if (user != null) {
                correct = true;
            }
        }
        return correct;
    }

    @Override
    public User getUserByLogin(String userName, String password) {
        User user = null;
        Query query = entityManager.createNativeQuery("SELECT u.* FROM public.user as u " +
                "WHERE u.user_name = ? and u.password = ? and u.status=true", User.class);
        query.setParameter(1, userName);
        query.setParameter(2, password);
        List list = query.getResultList();
        if (list != null && list.size() != 0) {
            user = (User) list.get(0);
        }
        return user;
    }
}