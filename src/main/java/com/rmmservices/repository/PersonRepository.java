package com.rmmservices.repository;

import com.rmmservices.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface to manage the person table
 *
 * @author Karla
 * @since 08-06-2019
 */
@Repository
public interface PersonRepository extends JpaRepository<Person, Integer>{

}