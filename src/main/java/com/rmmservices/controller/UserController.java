package com.rmmservices.controller;

import com.rmmservices.model.User;
import com.rmmservices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * RestController class to create/change the user table
 *
 * @author Karla
 * @since 08-06-2019
 */
@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/userList")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/createUser")
    public User createUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("/verifyUser/{userName}/{password}")
    public String verifyUser(@PathVariable("userName") String userName, @PathVariable("password") String password) {
        Boolean validUser = userRepository.verifyUser(userName, password);
        String message = "The user is VALID";
        if (validUser.equals(false)) {
            message = "The user is INVALID";
        }
        return message;
    }
}