package com.rmmservices.controller;

import com.rmmservices.model.Device;
import com.rmmservices.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * RestController class to create/change the device table
 *
 * @author Karla
 * @since 08-06-2019
 */
@RestController
public class DeviceController {
    @Autowired
    private DeviceRepository deviceRepository;

    @GetMapping("/deviceList")
    public List<Device> getDevices() {
        return deviceRepository.findAll();
    }

    @PostMapping("/createDevice")
    public Device createDevice(@Valid @RequestBody Device device, @PathVariable String userName, @PathVariable String password) {
        return deviceRepository.save(device);
    }
}