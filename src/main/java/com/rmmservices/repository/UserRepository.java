package com.rmmservices.repository;

import com.rmmservices.model.User;
import com.rmmservices.repository.util.UserRepCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository class to manage the user table
 *
 * @author Karla
 * @since 08-06-2019
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer>, UserRepCustom {

}