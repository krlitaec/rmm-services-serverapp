package com.rmmservices.repository;

import com.rmmservices.model.SmartService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface to manage the smart_service table
 *
 * @author Karla
 * @since 09-06-2019
 */

@Repository
public interface SmartServiceRepository extends JpaRepository<SmartService, Integer> {

}