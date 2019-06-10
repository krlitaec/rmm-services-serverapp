package com.rmmservices.controller;

import com.rmmservices.exception.ResourceNotFoundException;
import com.rmmservices.model.Device;
import com.rmmservices.model.User;
import com.rmmservices.repository.DeviceRepository;
import com.rmmservices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @PutMapping("/updateDevice/{userName}/{password}")
    public Device updateDevice(@Valid @RequestBody Device deviceUpdate, @PathVariable String userName, @PathVariable String password) {
        Boolean validUser = userRepository.verifyUser(userName, password);
        if (validUser.equals(true)) {
            User userLogin = userRepository.getUserByLogin(userName, password);
            return deviceRepository.findById(deviceUpdate.getIdDevice())
                    .map(device -> {
                        device.setSystemName(deviceUpdate.getSystemName());
                        device.setType(deviceUpdate.getType());
                        device.setUpdatedBy(userLogin);
                        device.setUpdatedAt(Calendar.getInstance().getTime());
                        return deviceRepository.save(device);
                    }).orElseThrow(() -> new ResourceNotFoundException("Device not found with id: " + deviceUpdate.getIdDevice()));
        } else {
            Device device = new Device();
            device.setSystemName("User Invalid to update the device!");
            return device;
        }
    }

    @DeleteMapping("/deleteDevice/{idDevice}/{userName}/{password}")
    public ResponseEntity<?> deleteDevice(@PathVariable Integer idDevice, @PathVariable String userName, @PathVariable String password) {
        Boolean validUser = userRepository.verifyUser(userName, password);
        if (validUser.equals(true)) {
            return deviceRepository.findById(idDevice)
                    .map(device -> {
                        deviceRepository.delete(device);
                        return ResponseEntity.ok().build();
                    }).orElseThrow(() -> new ResourceNotFoundException("Device not found with id: " + idDevice));
        } else {
            return null;
        }
    }
}