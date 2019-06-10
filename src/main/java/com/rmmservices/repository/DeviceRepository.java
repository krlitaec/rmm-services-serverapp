package com.rmmservices.repository;

import com.rmmservices.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface to manage the device table
 *
 * @author Karla
 * @since 08-06-2019
 */

@Repository
public interface DeviceRepository extends JpaRepository<Device, Integer> {

}