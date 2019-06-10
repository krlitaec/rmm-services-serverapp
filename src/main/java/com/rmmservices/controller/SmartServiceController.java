package com.rmmservices.controller;

import com.rmmservices.model.SmartService;
import com.rmmservices.model.User;
import com.rmmservices.repository.SmartServiceRepository;
import com.rmmservices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.List;

/**
 * RestController class to create/change the smart_service table
 *
 * @author Karla
 * @since 08-06-2019
 */
@RestController
public class SmartServiceController {
    @Autowired
    private SmartServiceRepository smartServiceRepository;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/smartServiceList")
    public List<SmartService> getSmartServices() {
        return smartServiceRepository.findAll();
    }

    @PostMapping("/createSmartService/{userName}/{password}")
    public SmartService createSmartService(@Valid @RequestBody SmartService smartService, @PathVariable String userName, @PathVariable String password) {
        Boolean validUser = userRepository.verifyUser(userName, password);
        if (validUser.equals(true)) {
            User userLogin = userRepository.getUserByLogin(userName, password);
            smartService.setCreatedBy(userLogin);
            smartService.setCreatedAt(Calendar.getInstance().getTime());
            smartService.setUpdatedBy(userLogin);
            smartService.setUpdatedAt(Calendar.getInstance().getTime());
            smartService = smartServiceRepository.save(smartService);
        } else {
            smartService.setDescription("User Invalid to create the SmartService!");
        }
        return smartService;
    }
}