package com.rmmservices.controller;

import com.rmmservices.model.Device;
import com.rmmservices.model.User;
import com.rmmservices.repository.DeviceRepository;
import com.rmmservices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Calendar;
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
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/deviceList")
    public List<Device> getDevices() {
        return deviceRepository.findAll();
    }

    @PostMapping("/createDevice/{userName}/{password}")
    public Device createDevice(@Valid @RequestBody Device device, @PathVariable String userName, @PathVariable String password) {
        Boolean validUser = userRepository.verifyUser(userName, password);
        if (validUser.equals(true)) {
            User userLogin = userRepository.getUserByLogin(userName, password);
            device.setCreatedBy(userLogin);
            device.setCreatedAt(Calendar.getInstance().getTime());
            device.setUpdatedBy(userLogin);
            device.setUpdatedAt(Calendar.getInstance().getTime());
            device = deviceRepository.save(device);
        } else {
            device.setSystemName("User Invalid to create the device!");
        }
        return device;
    }
}