package com.rmmservices.controller;

import com.rmmservices.model.Customer;
import com.rmmservices.model.Person;
import com.rmmservices.model.User;
import com.rmmservices.repository.CustomerRepository;
import com.rmmservices.repository.PersonRepository;
import com.rmmservices.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.List;

/**
 * RestController class to create/change the person table
 *
 * @author Karla
 * @since 08-06-2019
 */
@RestController
public class PersonController {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/personList")
    public List<Person> getPersons() {
        return personRepository.findAll();
    }


    @PostMapping("/createCustomer/{userName}/{password}")
    public User createPerson(@Valid @RequestBody Person person, @PathVariable("userName") String userName
            , @PathVariable("password") String password) {
        User user = new User();
        String newUserName = person.getFirstName().toLowerCase() + "." + person.getLastName().toLowerCase();
        String newPassword = "123456";
        Boolean validUser = userRepository.verifyUser(userName, password);
        if (validUser.equals(true)) {
            User userLogin = userRepository.getUserByLogin(userName, password);
            person.setCreatedBy(userLogin);
            person.setCreatedAt(Calendar.getInstance().getTime());
            person.setUpdatedBy(userLogin);
            person.setUpdatedAt(Calendar.getInstance().getTime());
            person = personRepository.save(person);
            user.setUserName(newUserName);
            user.setPassword(newPassword);
            user.setStatus(true);
            user = userRepository.save(user);
            Customer customer = new Customer();
            customer.setIdPerson(person);
            customer.setCode("");
            customer.setIdUser(user);
            customer.setCreatedBy(userLogin);
            customer.setCreatedAt(Calendar.getInstance().getTime());
            customer.setUpdatedBy(userLogin);
            customer.setUpdatedAt(Calendar.getInstance().getTime());
            customerRepository.save(customer);
        } else {
            user.setUserName("USER INVALID!");
        }
        return user;
    }
}