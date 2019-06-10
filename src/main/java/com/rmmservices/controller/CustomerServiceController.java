package com.rmmservices.controller;

import com.rmmservices.exception.ResourceNotFoundException;
import com.rmmservices.model.*;
import com.rmmservices.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Calendar;
import java.util.List;

/**
 * RestController class to create/change the customer_service table
 *
 * @author Karla
 * @since 10-06-2019
 */
@RestController
public class CustomerServiceController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerServiceRepository customerServiceRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private SmartServiceRepository smartServiceRepository;
    @Autowired
    private DeviceRepository deviceRepository;

    @GetMapping("/customerServiceList/{idCustomer}")
    public List<CustomerService> getCustomerService(@PathVariable Integer idCustomer) {
        return customerServiceRepository.getByCustomer(idCustomer);
    }

    @PostMapping("/createCustomerService/{userName}/{password}")
    public CustomerService createCustomerService(@Valid @RequestBody CustomerService service, @PathVariable String userName, @PathVariable String password) {
        Boolean validUser = userRepository.verifyUser(userName, password);
        if (validUser.equals(true)) {
            User userLogin = userRepository.getUserByLogin(userName, password);
            service.setCreatedBy(userLogin);
            service.setCreatedAt(Calendar.getInstance().getTime());
            service.setUpdatedBy(userLogin);
            service.setUpdatedAt(Calendar.getInstance().getTime());
            Customer customer = customerRepository.findByUser(userLogin.getIdUser());
            service.setIdCustomer(customer);
            SmartService smartService = smartServiceRepository.findById(service.getIdSmartService().getIdSmartService()).get();
            service.setIdSmartService(smartService);
            Device device = deviceRepository.findById(service.getIdDevice().getIdDevice()).get();
            service.setIdDevice(device);
            service = customerServiceRepository.save(service);
        } else {
            service = null;
        }
        return service;
    }

    @DeleteMapping("/deleteCustomerService/{idCustomerService}/{userName}/{password}")
    public ResponseEntity<?> deleteCustomerService(@PathVariable Integer idCustomerService, @PathVariable String userName, @PathVariable String password) {
        Boolean validUser = userRepository.verifyUser(userName, password);
        if (validUser.equals(true)) {
            return customerServiceRepository.findById(idCustomerService)
                    .map(service -> {
                        customerServiceRepository.delete(service);
                        return ResponseEntity.ok().build();
                    }).orElseThrow(() -> new ResourceNotFoundException("Service not found with id: " + idCustomerService));
        } else {
            return null;
        }
    }
}